/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.book.editors.figures;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.OrderedLayout;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.progress.UIJob;
import org.eclipse.wb.swt.ResourceManager;

import com.jaspersoft.studio.book.ReportThumbnailsManager;
import com.jaspersoft.studio.book.model.MReportPart;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;

/**
 * This is the figure that shows a single document icon in a book section.
 * The label is the expression used to reference the jrxml in the subreportPart.
 * 
 */
public class BookPagesFigure extends RectangleFigure {

	/**
	 * Height of the figure
	 */
	public static final int PREFERRED_HEIGHT = 150;
	
	/**
	 * Width of the figure
	 */
	public static final int PREFERRED_WIDTH = 150;
	
	/**
	 * Height of the label
	 */
	public static final int PREFERRED_LABEL_HEIGHT = 20;
	
	/**
	 * Model of the report
	 */
	private MReportPart model;
	
	/**
	 * The preview image for this figure
	 */
	private Image previewImage = null;
	
	/**
	 * The circle busy image for the figure
	 */
	private BusyImageFigure imageFigure = null;
	
	/**
	 * The tooltip for the figure
	 */
	private TooltipFigure toolTipFigure;
	
	/**
	 * The textual image of the figure
	 */
	private Label textFigure;
	
	private double lastScaleValue = 1.0d;

	private boolean loadingJobRunning = false;
	
	/**
	 * Create the figure
	 * 
	 * @param model model for which the figure is created
	 */
	public BookPagesFigure(MReportPart model){
		this(model, null);
	}
	
	/**
	 * Creates a new BookPagesFigure.
	 * 
	 * The model is declared final because we use it in a thread that will
	 * asynchronously load the report (if available) used by this part to create
	 * a thumbnail to show in the book editor.
	 * 
	 * @param model
	 */
	public BookPagesFigure(final MReportPart model, Image previewImage){
		super();
		this.model = model;
		setPreferredSize(PREFERRED_WIDTH, PREFERRED_HEIGHT);
		setOutline(false);
		ToolbarLayout layout = new ToolbarLayout(false);
		layout.setMinorAlignment(OrderedLayout.ALIGN_CENTER);
		layout.setSpacing(5);
		setLayoutManager(layout);
		setBackgroundColor(ResourceManager.getColor(255, 255, 255));
		
		if (previewImage == null || previewImage.isDisposed())
		{
			Image defaultImage = ResourceManager.getImage(model.getImageDescriptor()); //ReportThumbnailsManager.getNoPreviewImage(0, true, false);
			// The images for this jrxml.
			// This is a scaled version of the icon used to represent a single
			// document.
			imageFigure = new BusyImageFigure(defaultImage);
		}
		else
		{
			this.previewImage = previewImage;
			imageFigure = new BusyImageFigure(previewImage);
		}
		
		imageFigure.setAlignment(PositionConstants.CENTER);
		
		
		add(imageFigure);
		
		
		
		String text = this.model.getDisplayText();
		if(text.length()>=20){
			text = text.substring(0, 16)+"..\"";
		}
		
		textFigure = new Label(text);
		textFigure.setPreferredSize(180, PREFERRED_LABEL_HEIGHT);
		textFigure.setTextAlignment(PositionConstants.CENTER);
		add(textFigure);
		toolTipFigure = new TooltipFigure();
		toolTipFigure.setMessage(model.getDisplayText());
		setToolTip(toolTipFigure);
	}
	
	protected void loadPreviewImage(int size)
	{
		loadingJobRunning = true;
		imageFigure.setBusy(true);
		UIJob j = new UIJob("Loading preview image") {
			@Override
			public IStatus runInUIThread(IProgressMonitor monitor) {
				try {
					String reportFileName = ReportThumbnailsManager.getLocation(model);
					if (reportFileName != null){
						final Image sourceImage = ReportThumbnailsManager.produceImage((String) reportFileName,model.getJasperConfiguration(), size, true, false);
						if (sourceImage != null) {
							updateFigure(sourceImage);
						}
					}
				} catch(Exception ex){
					ex.printStackTrace();
					return null;
				} finally {
					imageFigure.setBusy(false);
				}
				loadingJobRunning = false;
				return Status.OK_STATUS;
			}
		};
		j.setPriority(Job.DECORATE);
		j.schedule();
	}
	
	
	/**
	 * Replace the figure image and dispose the old image if any
	 * 
	 * @param newImage
	 */
	public synchronized void updateFigure(final Image newImage)
    {
		updateFigure(newImage, false);
    }
	
	
	/**
	 * Replace the figure and provides an option to prevent the disposal of the
	 * the currently shown image.
	 * 
	 * @param newImage
	 * @param preventDispose
	 */
	public synchronized void updateFigure(final Image newImage, boolean preventDispose)
    {
        if (UIUtils.getDisplay() == null || UIUtils.getDisplay().isDisposed()) 
            return;
        UIUtils.getDisplay().asyncExec(new Runnable() {

            public void run() {
                
            	// Save the old image, that will be disposed later
            	Image oldImage = previewImage;
				
            	previewImage = newImage;
            	if (previewImage != oldImage)
            	{
            		imageFigure.setImage(newImage);
            		// dispose the old one...
            		if (oldImage != null && !oldImage.isDisposed())
            		{
            			oldImage.dispose();
            		}
            	}
				BookPagesFigure.this.invalidateTree();
                BookPagesFigure.this.repaint();
            }
        });

    }
	
	public void updateText(){
		String text = this.model.getDisplayText();
		if(text.length()>=20){
			text = text.substring(0, 16)+"..\"";
		}
		textFigure.setText(text);
		toolTipFigure.setMessage(model.getDisplayText());
	}
	
	@Override
	public void setBounds(Rectangle rect) {
		int offsetY = BookSectionFigure.HORIZONTAL_LINE_OFFSET + 4;
		super.setBounds(new Rectangle(rect.x+15, rect.y+offsetY, rect.width, rect.height));
	}

	/**
	 * Scaled image is disposed when the part is deactivated
	 */
	public void dispose(){
		if (previewImage != null && !previewImage.isDisposed()){
			previewImage.dispose();
		}
	}
	
	@Override
	public void paint(Graphics graphics) {
		// If the preview image has not been provided yet...let's load it.
		// Attention, during startup, this operation may hang...
		if ((this.previewImage == null || graphics.getAbsoluteScale() != lastScaleValue) && !loadingJobRunning){
			lastScaleValue = graphics.getAbsoluteScale();
			int width = (int)Math.round(imageFigure.getPreferredSize().width * lastScaleValue);
			int height = (int)Math.round(imageFigure.getPreferredSize().height * lastScaleValue);
			loadPreviewImage(Math.max(width, height));
		}
		super.paint(graphics);
	}
	
	
	public Image getThubmnailImage()
	{
		return previewImage;
	}
}
