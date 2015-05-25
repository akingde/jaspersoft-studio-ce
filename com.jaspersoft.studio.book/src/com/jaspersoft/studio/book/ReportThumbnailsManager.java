/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.book;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import javax.imageio.ImageIO;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.FileUtils;
import net.sf.jasperreports.engine.DefaultJasperReportsContext;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.JRReport;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReportsContext;
import net.sf.jasperreports.engine.convert.ReportConverter;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignPart;
import net.sf.jasperreports.engine.export.JRGraphics2DExporter;
import net.sf.jasperreports.engine.part.PartComponent;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleGraphics2DExporterOutput;
import net.sf.jasperreports.export.SimpleGraphics2DReportConfiguration;
import net.sf.jasperreports.parts.subreport.SubreportPartComponent;

import org.eclipse.core.resources.IFile;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;

import com.jaspersoft.studio.book.model.MReportPart;
import com.jaspersoft.studio.utils.ExpressionUtil;
import com.jaspersoft.studio.utils.jasper.ExtensionLoader;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;


/**
 * This class helps in creating and caching report thumbnails to be displayed
 * inside the book designer.
 * 
 * The class provides a nice thumbnail of the first page of a report design
 * or, if no report design is provided, a simple placeholder image.
 * 
 * 
 * Caching is performed by file location, but the images returned are copies that must managed by the user
 * that requests the image.
 * 
 * 
 * @author gtoffoli
 *
 */
public class ReportThumbnailsManager {

	/**
	 * The size of the thumbnail image (which is a square).
	 */
	public static final int THUMBNAIL_SIZE = 120;
	
	public static final int SHADOW_SIZE = 4;
	
	/**
	 * The cache for the preview (AWT) images...
	 */
	private static Map<String, ThumbnailCacheItem> cachedItems = new HashMap<String, ThumbnailCacheItem>();
	
	/**
	 * Map where are listed the currently loading cache items, the key is the same of the map cachedItems
	 * and if an element is inside this set it means that it is currently loading and will be saved 
	 * inside cachedItems
	 */
	private static HashSet<String> loadingItems = new HashSet<String>();
	
	private static java.awt.Image ERROR_IMAGE = null;
	
	private static String ERROR_IMAGE_LOCATION = "/icons/report_no_preview.png";
			
	/**
	 * This is a temporary map to help transition of figure images to new figures referencing the same
	 * jasperReport object (JRDesignPart).
	 * As key is used the uuid of the element to avoid object hard links.
	 */
	private static Map<String, Image> temporarySwap = new HashMap<String, Image>();
	
	
	private static java.awt.Image getErrorImage()
	{
		if (ERROR_IMAGE == null)
		{
			try {
				ERROR_IMAGE = ImageIO.read( new File(JRBookActivator.getDefault().getFileLocation(ERROR_IMAGE_LOCATION) ));
			} catch (IOException e) {
				/// it should never happen...
				e.printStackTrace();
			}
		}
		
		return ERROR_IMAGE;
	}
	
	
	/**
	 * Convenient method to look for a file.
	 * Find the file with an euristic approach....
     * Attention... this could be slow in very very large environments..
	 * Search is performed in the following way:
	 * 
	 * 1. Check if location can be found on the file system (assuming location an absolute path)
	 * 2. Check if the location can be loaded from the classpath (assuming a file in a folder present in the classpath of the report)
	 * 3. Check if the file can be found relatively to the current report file (pointed by this context)
	 * 4. Check if the file can be found relatively to the current project (to which the file at point 3 belongs to)
	 * 
	 * @param location
	 * @param context
	 * @return
	 */
	public static File findFile(String location, JasperReportsConfiguration context)
	{
		// Check if the location is an absolute path...
		File f = new File(location);
		if (f.exists()) return f;
		
		
		// Check if the file can be found in the classpath...
		URL url = context.getClassLoader().getResource(location);
		
		if (url != null)
		{
			try {
				f = new File( url.toURI());
				if (f.exists()) return f;
			} catch (URISyntaxException e) {
			}
		}
		
		// We check locally to the current file directory... maybe we are more lucky...
		IFile file = (IFile)context.get(FileUtils.KEY_FILE);
		if (file != null)
		{
			// Located the parent...
			f = new File(file.getParent().getLocationURI());
			// ... try to build the full path...
			f = new File(f, location);
			if (f.exists()) return f;
			
			// Finally we look inside this project...
			// We check locally to the current file directory... maybe we are more lucky...
			f = new File(file.getProject().getLocationURI());
			// ... try to build the full path...
			f = new File(f, location);
			if (f.exists()) return f;
		}
		
		return null;
	}
	
	/**
	 * Given a report (jasper or jrxml), the file is loaded (if exists, and an SWT image is provided).
	 * This image is not cached or managed in any way! You are responsible to dispose it! 
	 * 
	 * The size of the image is ReportThumbnailsManager.THUMBNAIL_SIZE (120x120)
	 * The image includes a 3 pixel border to simulate a shadow.
	 * 
	 * This method is a shortcut of produceImage(file, context, ReportThumbnailsManager.THUMBNAIL_SIZE, true, false)
	 * 
	 * @param location - A file location.
	 * @param context - Can be null, but in this case don't expect images to be resolved...or properly previewed.
	 * @return Image
	 * 
	 * @see ReportThumbnailsManager.produceImage(File file, JasperReportsContext context)
	 */
	public static Image produceImage(String location, JasperReportsConfiguration context) {
		return  produceImage(location, context, ReportThumbnailsManager.THUMBNAIL_SIZE, true, false);
	}
	
	/**
	 * In case of error, a simple error image is provided.
	 * 
	 * 
	 * @param file - A file location. 
	 * @param context - A context to be used when rendering the preview and to locate the file
	 * @param thumbnailSize - If 0 it defaults to ReportThumbnailsManager.THUMBNAIL_SIZE, but the maximum precision used is still THUMBNAIL_SIZE
	 * 		               which means that this preview will cached at THUMBNAIL_SIZE, even if the final image could be at any size.
	 *                     Clearly, requesting a bigger size will result in an up-scaled poor image.
	 * @param drawShadow - If the shadow is shown, the report preview will be a little bit smaller to leave room
	 *                     for the shadow. The size of the shadow (ReportThumbnailsManager.SHADOW_SIZE) is by default 4 pixels. 
	 * @param cropImage - By default the thumbnail image is square, to accommodate any size. But in some cases, a proper image
	 *                    cropped to the document is preferred, so this is what crop does. If true, the shadow will ignored.
	 * @return
	 */
	public static Image produceImage(String location, JasperReportsConfiguration context, int thumbnailSize, boolean drawShadow, boolean cropDocument)
	{
	
		if (thumbnailSize == 0) thumbnailSize = THUMBNAIL_SIZE;
		
		java.awt.Image previewImage = null;
		
		File file = findFile(location, context);
    	if (file == null || !file.exists()) { 
    		previewImage = getErrorImage();
		}
    	else
    	{
	    	ThumbnailCacheItem cachedItem = null;
	    	
	    	// Check if we have a cached image, in that case we can get it ...
	    	if (cachedItems.containsKey(location))
	    	{
	    		cachedItem = cachedItems.get(location);
	    		if (cachedItem!=null && file.lastModified() > cachedItem.getTimestamp().getTime())
	    		{
	    			// This cache item is old, we can delete it...
	    			cachedItems.remove(location);
	    			cachedItem = null;
	    		}
	    	}
	    	
	    	JasperReportsContext previewContext = context;
			if (previewContext == null) previewContext =  DefaultJasperReportsContext.getInstance();
			
			// Generate a simple preview of our report, and add it to the cache...
			if (cachedItem == null)
			{
					setLoadingItem(location);
					ExtensionLoader.waitIfLoading();
					JRReport report = null;
					// by default we assume we are loading a jrxml until the file name ends with .jasper
					if (!file.getName().toLowerCase().endsWith(".jasper"))
					{
						try {
							report = (JRReport)JRXmlLoader.load(file);
						} catch (JRException e) {
							// Problem loading the file for preview...
							previewImage = getErrorImage();
						}
					}
					else // it is a jasper file
					{
						try {
							report = (JRReport)JRLoader.loadObject(file);
						} catch (JRException e) {
							e.printStackTrace();
							// Problem loading the file for preview...
							previewImage = getErrorImage(); 
						}
					}
					// If there was an error, previewImage is now pointing to a default error image...
					if (previewImage == null)
					{
					
							float previewZoom = (float)(THUMBNAIL_SIZE) / (Math.max(report.getPageHeight() , report.getPageWidth()));
							
							int previewWidth = (int)(report.getPageWidth()*previewZoom);
							int previewHeight = (int)(report.getPageHeight()*previewZoom);
							
							
							previewImage = new BufferedImage(previewWidth, previewHeight, BufferedImage.TYPE_INT_RGB);
							Graphics2D previewG2d = (Graphics2D)previewImage.getGraphics();
							
							// Fill the document area with White.
							previewG2d.setBackground(Color.WHITE);
							previewG2d.fillRect(0, 0, previewWidth, previewHeight);
							
							previewG2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
							previewG2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
							previewG2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
							
							// Here is where the magic happens... JasperReports will fill our image...
							try {
								
								JasperPrint jasperPrint = new ReportConverter(previewContext, report, false).getJasperPrint();
								
								JRGraphics2DExporter exporter = new JRGraphics2DExporter(previewContext);
								exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
								
								SimpleGraphics2DExporterOutput output = new SimpleGraphics2DExporterOutput();
								output.setGraphics2D(previewG2d);
								exporter.setExporterOutput(output);
								
								SimpleGraphics2DReportConfiguration grxConfiguration = new SimpleGraphics2DReportConfiguration();
								grxConfiguration.setPageIndex(0); // We always create design preview for the first page only
								grxConfiguration.setZoomRatio(previewZoom); // This is based on the zoom we calculated previously
								exporter.setConfiguration(grxConfiguration);
								exporter.exportReport(); // Here is where the magic happens...
								
								// Let's cache this result!
								cachedItem = new ThumbnailCacheItem(file.toString(), previewImage, report);
								cachedItems.put(location, cachedItem);
								
							} catch (Exception ex)
							{
								// Error creating the thumbnail...
								// In this could be due to a problem loading the document or something else...
								ex.printStackTrace();
								previewImage = getErrorImage();
							}
					}
					removeLoadingItem(location);
			}
			else
			{
				previewImage = cachedItem.getPreview();
			}
    	}
		
		// At this point we have a preview Image (either an error image or the real preview).
		// Let's produce the requeste SWT image starting with a new AWT Image on which the
		// preview will be painted with the proper requested size...
		
		// The size of the thumbnail is always the same, we will just apply a zoom to the
		// Graphics 2D for an optimal rendering based on the real page size...
		// Please also note the the thumbnail are squared, to accomodate any possible size
		// of the document (i.e. portrait or landscape) until is not requested differently...
		
    	return generateImage(previewImage, thumbnailSize, drawShadow, cropDocument);
	}
	
	/**
	 * Provides a no preview image with the requested properties.
	 * 
	 * 
	 * @param thumbnailSize - If 0 it defaults to ReportThumbnailsManager.THUMBNAIL_SIZE, but the maximum precision used is still THUMBNAIL_SIZE
	 * 		               which means that this preview will cached at THUMBNAIL_SIZE, even if the final image could be at any size.
	 *                     Clearly, requesting a bigger size will result in an up-scaled poor image.
	 * @param drawShadow - If the shadow is shown, the report preview will be a little bit smaller to leave room
	 *                     for the shadow. The size of the shadow (ReportThumbnailsManager.SHADOW_SIZE) is by default 4 pixels. 
	 * @param cropImage - By default the thumbnail image is square, to accommodate any size. But in some cases, a proper image
	 *                    cropped to the document is preferred, so this is what crop does. If true, the shadow will ignored.
	 * @return
	 */
	public static final Image getNoPreviewImage(int thumbnailSize, boolean drawShadow, boolean cropDocument)
	{
			if (thumbnailSize == 0) thumbnailSize = THUMBNAIL_SIZE;
			return generateImage(getErrorImage(), thumbnailSize, drawShadow, cropDocument);
	}

	/**
	 * Take the provided image and creates a thumbail with the requested properties.
	 * 
	 * 
	 * @param thumbnailSize - If 0 it defaults to ReportThumbnailsManager.THUMBNAIL_SIZE, but the maximum precision used is still THUMBNAIL_SIZE
	 * 		               which means that this preview will cached at THUMBNAIL_SIZE, even if the final image could be at any size.
	 *                     Clearly, requesting a bigger size will result in an up-scaled poor image.
	 * @param drawShadow - If the shadow is shown, the report preview will be a little bit smaller to leave room
	 *                     for the shadow. The size of the shadow (ReportThumbnailsManager.SHADOW_SIZE) is by default 4 pixels. 
	 * @param cropImage - By default the thumbnail image is square, to accommodate any size. But in some cases, a proper image
	 *                    cropped to the document is preferred, so this is what crop does. If true, the shadow will ignored.
	 * @return
	 */
	private static final Image generateImage(java.awt.Image previewImage, int thumbnailSize, boolean drawShadow, boolean cropDocument)
	{
			if (thumbnailSize == 0) thumbnailSize = THUMBNAIL_SIZE;
			
			int imageWidth = thumbnailSize;
			int imageHeight = thumbnailSize;
			float documentWidth = (float)previewImage.getWidth(null);
			float documentHeight = (float)previewImage.getHeight(null);
			
			if (cropDocument)
			{
				imageWidth  = (int)((documentWidth > documentHeight) ? thumbnailSize : (documentWidth/documentHeight)*thumbnailSize);
				imageHeight = (int)((documentWidth > documentHeight) ? (documentHeight/documentWidth)*thumbnailSize : thumbnailSize);
			}
			
			BufferedImage bi = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
			
			Graphics2D g2d = (Graphics2D)bi.getGraphics();
			
			g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
			
			// Set the proper documentSize if we use the shadow or not based on the thumbnailSize..
			float zoom = (float)(thumbnailSize-(drawShadow ? SHADOW_SIZE*2 : 0)) / (Math.max(documentWidth , documentHeight));
	
			documentWidth =  documentWidth*zoom;
			documentHeight =  documentHeight*zoom;
			
			
			// Paint the shadow...
			if (!cropDocument && drawShadow)
			{
					
					// Fill the background
					g2d.setBackground(Color.WHITE);
					g2d.fillRect(0, 0, imageWidth, imageHeight);
					
					// Draw a shadow...
					for (int i=SHADOW_SIZE; i>=0; --i)
					{
						RoundRectangle2D shadowTemplate = new RoundRectangle2D.Double(
								thumbnailSize/2.0 - documentWidth/2.0 - i,
								thumbnailSize/2.0 - documentHeight/2.0 - i,
								documentWidth + 2*i,
								documentHeight + 2*i, SHADOW_SIZE, SHADOW_SIZE);
						
						g2d.setColor(new Color(250-8*(SHADOW_SIZE-i),250-8*(SHADOW_SIZE-i),250-8*(SHADOW_SIZE-i) ));
						g2d.fill(shadowTemplate);
					}
					
					// Fill the page (white)
					g2d.setColor(Color.WHITE);
					Rectangle2D pageTemplate = new Rectangle2D.Double(
							thumbnailSize/2.0 - documentWidth/2.0,
							thumbnailSize/2.0 - documentHeight/2.0,
							documentWidth,
							documentHeight);
					g2d.fill(pageTemplate);
			}
			
			
			// Translate the graphics so the preview page will be printed centered vertically and horizontally...
			if (!cropDocument)
			{
			
				g2d.setTransform(AffineTransform.getTranslateInstance(
						thumbnailSize/2.0 - documentWidth/2.0,
						thumbnailSize/2.0 - documentHeight/2.0));
				
			}
		
			// Paint the preview image on the final image
			g2d.drawImage(previewImage, 0, 0, (int)documentWidth, (int)documentHeight, null);
		
			return UIUtils.awt2Swt(bi);
		
	}
	
	/**
	 * This method allows to cache an image to be used in a Figure.
	 * The image is copied and stored.
	 * 
	 * To get the image use popElementImage();
	 * 
	 * @param uuid
	 * @param swtImage
	 */
	public static void pushElementImage(String uuid, Image swtImage)
	{
		if (temporarySwap.containsKey(uuid))
		{
			return;
			
			/*
			Image cachedImage = temporarySwap.get(uuid);
			if (cachedImage != null && !cachedImage.isDisposed())
			{
				cachedImage.dispose();
			}
			*/
		}
		
		if (swtImage == null || swtImage.isDisposed()) return;
		
		Image newCachedImage  = new Image(UIUtils.getDisplay(), swtImage, SWT.IMAGE_COPY);
		temporarySwap.put( uuid, newCachedImage);
	}
	
	
	/**
	 * Consume (if available) a cached image for a particular jasperReports part element.
	 * 
	 * @param uuid
	 * @return
	 */
	public static synchronized Image popElementImage(String uuid)
	{
		if (temporarySwap.containsKey(uuid))
		{
			Image cachedImage = temporarySwap.get(uuid);
			temporarySwap.remove(uuid);
			return cachedImage;
		}
		return null;
	}

	/**
	 * Return the location path of a report referenced by a report part.
	 * If the report is not found and the referenced one is a .jasper it's 
	 * jrxml is searched. 
	 * 
	 * @param model the model used to reference the report
	 * @return the path of the report or null if it can't be located
	 */
	public static String getLocation(MReportPart model){
		Object reportFileName = null;
		// Try to find the expression used to reference the jrxml or
		// jasper file
		// used to fill this part.
		JRDesignPart jrDesignPart = model.getValue();
		JasperReportsConfiguration context = model.getJasperConfiguration();
		if (jrDesignPart != null) {
			PartComponent partComponent = jrDesignPart.getComponent();
			if (partComponent instanceof SubreportPartComponent) {
				JRExpression subreportExp = ((SubreportPartComponent) partComponent).getExpression();
				if (subreportExp != null) {

					// Try to evaluate the subreport expression for
					// this part.
					// The dataset to use is clearly the main
					// dataset, since we don't have
					// other options in Jasperbook...
					JRDesignDataset dataset = (JRDesignDataset)model.getJasperDesign().getMainDataset();
					reportFileName =  ExpressionUtil.cachedExpressionEvaluation(subreportExp, context, dataset);

				}
			}
		}

		if (reportFileName == null) return null;
		if (reportFileName instanceof File) {
			reportFileName = ((File) reportFileName).toURI().toString();
		} else if (!(reportFileName instanceof String)) {
			return null;
		}
		//The file to be loaded  can reference either a .jasper or a .jrxml.
		//If the file does not have a proper extension, jrxml is assumed.
		String location = (String)reportFileName;
		File f = findFile(location, context);
		if (( f == null || !f.exists()) && location.toLowerCase().endsWith(".jasper"))
		{
			// check for a jrxml...
			 location = location.substring(0, location.length() - ".jasper".length()) + ".jrxml";
		}
		return location;
	}
	
	/**
	 * Set a key as currently loading item
	 * 
	 * @param key key to set
	 */
	private static void setLoadingItem(String key){
		synchronized (loadingItems) {
			loadingItems.add(key);
		}
	}
	
	/**
	 * Set a key as currently loaded item
	 * 
	 * @param key key to set
	 */
	private static void removeLoadingItem(String key){
		synchronized (loadingItems) {
			loadingItems.remove(key);
		}
	}
	
	/**
	 * Check if a key belong to a currently loading item
	 * 
	 * @param key key to check
	 * @return true if the key is of an item currently loading,
	 * false otherwise
	 */
	private static boolean isLoadingItem(String key){
		synchronized (loadingItems) {
			return loadingItems.contains(key);
		}
	}
	
	/**
	 * Return a loaded jasperdesign. If it is currently
	 * loading then it wait until the load is complete
	 * 
	 * @param the location of the jasper or a jrxml file of a report part
	 * @return The jasperdesign or null if the jasper design
	 * can not be found
	 */
	public static JRReport getJasperDesign(String location){
		while(isLoadingItem(location)){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		ThumbnailCacheItem item = cachedItems.get(location);
		if (item != null) return item.getReport();
		return null;
	}
}


