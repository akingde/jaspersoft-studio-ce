/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.section.widgets;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.Util;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.wb.swt.ResourceManager;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.preferences.fonts.FontsPreferencePage;
import com.jaspersoft.studio.property.combomenu.ComboItem;
import com.jaspersoft.studio.property.combomenu.ComboItemAction;
import com.jaspersoft.studio.property.combomenu.ComboItemSeparator;
import com.jaspersoft.studio.property.combomenu.WritableComboTableViewer;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.utils.ImageUtils;
import com.jaspersoft.studio.utils.ModelUtils;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.engine.JRFont;
import net.sf.jasperreports.engine.base.JRBaseFont;
import net.sf.jasperreports.engine.design.JRDesignFont;
import net.sf.jasperreports.engine.fonts.FontUtil;

/**
 * A combo popup menu that could be used to represent a font
 * 
 * @author Orlandin Marco
 * 
 */
public class SPFontNamePopUp<T extends IPropertyDescriptor> extends ASPropertyWidget<T> {
	
	private static final String FONT_PREVIEW_MESSAGE = "Sample";
	
	private PreferenceListener preferenceListener = new PreferenceListener();

	private final class PreferenceListener implements IPropertyChangeListener {

		public void propertyChange(org.eclipse.jface.util.PropertyChangeEvent event) {
			String property = event.getProperty();
			if (property.equals(FontsPreferencePage.FPP_FONT_LIST))
				refreshFont();
		}
	}

	/**
	 * The combo popup
	 */
	protected WritableComboTableViewer combo;
	
	private APropertyNode pnode;

	/**
	 * True if the combo popup was already initialized with the data, false otherwise
	 */
	protected boolean dataSetted;

	public SPFontNamePopUp(Composite parent, AbstractSection section, T pDescriptor) {
		super(parent, section, pDescriptor);
		dataSetted = false;
		JaspersoftStudioPlugin.getInstance().addPreferenceListener(preferenceListener);
	}
	
	/**
	 * Resize the passed image keeping its aspect ratio. If the image has already the 
	 * the correct width or height the original one is returned
	 * 
	 * @param img the source image, must be not null
	 * @param newWidth the new width, can be different if the new height keeping the ratio is bigger than the passed newHeight
	 * @param newHeight the new height, can be different if the new width keeping the ratio is bigger than the passed newWidth
	 * @return
	 */
	private static BufferedImage resize(BufferedImage img, int newWidth, int newHeight) {
		
		int oldWidth = img.getWidth();
		int oldHeight = img.getHeight();
		
		if (newWidth == oldWidth || newHeight == oldHeight) return img;

    int scaledHeight = newHeight;
    int scaledWidth = (int)((float)oldWidth*scaledHeight)/oldHeight;
    if (scaledWidth > newWidth){
    	scaledWidth = newWidth;
    	scaledHeight = (int)((float)oldHeight*scaledWidth)/oldWidth;
    } 
    
    /*BufferedImage after = new BufferedImage(scaledWidth, scaledHeight, BufferedImage.TYPE_INT_ARGB);
    AffineTransform at = new AffineTransform();
    at.scale((float)scaledWidth / oldWidth, (float)scaledHeight / oldHeight);
    AffineTransformOp scaleOp = new AffineTransformOp(at, AffineTransformOp.TYPE_BICUBIC);
    after = scaleOp.filter(img, after);*/

    
    java.awt.Image tmp = img.getScaledInstance(scaledWidth, scaledHeight, BufferedImage.SCALE_AREA_AVERAGING);
    BufferedImage dimg = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
    
    int yOffset = (newHeight - scaledHeight)/2;
    int xOffset = (newWidth - scaledWidth)/2;
    Graphics2D g2d = dimg.createGraphics();
    g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
    g2d.drawImage(tmp, xOffset, yOffset, null);
    g2d.dispose();

    return dimg;
  }  

	/**
	 * Create the font image preview
	 * 
	 * @param fontName name of the font
	 * @param util {@link FontUtil} used to resolve the font
	 * @return and image with the preview of the font
	 */
	public static BufferedImage createFontImage(final String fontName, FontUtil util) {
		int height = 16;
		int width = Util.isLinux() ? 35  : 55;
		BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
	  Graphics2D ig2 = bi.createGraphics();
		ig2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		ig2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

		JRFont jrFont = new JRDesignFont(null);
		jrFont.setFontName(fontName);
		jrFont.setFontSize(30f);
		Font font = util.getAwtFont(jrFont, Locale.getDefault());
		ig2.setFont(font);
		FontMetrics metrics = ig2.getFontMetrics(font);
		int stringWidth =  metrics.stringWidth(FONT_PREVIEW_MESSAGE);
		int stringHeight = metrics.getAscent() + 5;
		ig2.dispose();
		
		bi = new BufferedImage(stringWidth, stringHeight, BufferedImage.TYPE_INT_ARGB);
	  ig2 = bi.createGraphics();
		ig2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		ig2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		ig2.setFont(font);
		ig2.setPaint(Color.black);
		ig2.drawString(FONT_PREVIEW_MESSAGE, 0, bi.getHeight() / 2 + stringHeight / 4);
		ig2.dispose();
		return resize(bi, width, height);
	}

	/**
	 * Set the data and uses the text color of the text to show if the value is inherited or not
	 */
	@Override
	public void setData(APropertyNode pnode, Object resolvedValue, Object elementValue) {
		setData(pnode, resolvedValue);
		if (combo != null && !combo.getControl().isDisposed()){
			if (elementValue != null){
				combo.setTextColor(ColorConstants.black);
			} else {
				combo.setTextColor(ColorConstants.gray);
			}
		}
	}
	
	/**
	 * Set the data of the combo popup, and if it wasn't initialized the fonts will be added
	 */
	@Override
	public void setData(APropertyNode pnode, Object b) {
		this.pnode = pnode;
		if (pnode != null) {
			createContextualMenu(pnode);
			combo.setEnabled(pnode.isEditable());
			if (!dataSetted) {
				combo.addSelectionListener(new ComboItemAction() {
					/**
					 * The action to execute when an entry is selected
					 */
					@Override
					public void exec() {
						propertyChange(section, JRBaseFont.PROPERTY_FONT_NAME,
								combo.getSelectionValue() != null ? combo.getSelectionValue().toString() : null);
					}
				});
				refreshFont();
				dataSetted = true;
			}
			combo.setText(b.toString());
		}
	}

	protected void refreshFont() {
		if (pnode == null)
			return;
		List<String[]> fontsList = ModelUtils.getFontNames(pnode.getJasperConfiguration());
		List<ComboItem> itemsList = new ArrayList<ComboItem>();
		int i = 0;
		FontUtil util = FontUtil.getInstance(pnode.getJasperConfiguration());
		for (int index = 0; index < fontsList.size(); index++) {
			String[] fonts = fontsList.get(index);
			for (String element : fonts) {
				Image resolvedImage = ResourceManager.getImage(element);
				if (resolvedImage == null){
					resolvedImage = new Image(UIUtils.getDisplay(), ImageUtils.convertToSWT(createFontImage(element, util)));
					ResourceManager.addImage(element, resolvedImage);
				}
				itemsList.add(new ComboItem(element, true, resolvedImage, i, element, element));
				i++;
			}
			if (index + 1 != fontsList.size() && fonts.length > 0) {
				itemsList.add(new ComboItemSeparator(i));
				i++;
			}
		}
		combo.setItems(itemsList);
	}

	public void propertyChange(AbstractSection section, String property, String value) {
		section.changeProperty(property, value);
	}

	@Override
	protected void createComponent(Composite parent) {
		if (combo == null) {
			combo = new WritableComboTableViewer(parent, SWT.FLAT);
			combo.getControl().addDisposeListener(new DisposeListener() {

				@Override
				public void widgetDisposed(DisposeEvent e) {
					JaspersoftStudioPlugin.getInstance().removePreferenceListener(preferenceListener);
				}
			});
		}
	}

	@Override
	public Control getControl() {
		return combo != null ? combo.getControl() : null;
	}

}
