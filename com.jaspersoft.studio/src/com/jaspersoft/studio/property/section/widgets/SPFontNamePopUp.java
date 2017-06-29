/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.section.widgets;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
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
import com.jaspersoft.studio.property.combomenu.ComboMenuViewer;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.utils.ModelUtils;

import net.sf.jasperreports.engine.base.JRBaseFont;

/**
 * A combo popup menu that could be used to represent a font
 * 
 * @author Orlandin Marco
 * 
 */
public class SPFontNamePopUp<T extends IPropertyDescriptor> extends ASPropertyWidget<T> {
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
	protected ComboMenuViewer combo;

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
	 * Create a sample image for a font. when an image is created it is cashed, so future request for that sample doesn't
	 * Require the image recreation.
	 * 
	 * @param fontName
	 *          name of the font for the requested sample
	 * @return image of the sample
	 */
	private static Image getBaseImage() {
		Image backGround = ResourceManager.getImage("baseFontBackGroundImage");
		if (backGround == null) {
			backGround = new Image(null, 55, 15);
			ResourceManager.addImage("baseFontBackGroundImage", backGround);
		}
		return backGround;
	}

	public static Image createFontImage(final String fontName) {
		Image stringImage = ResourceManager.getImage(fontName);
		// Check if the image is cached
		if (stringImage == null) {
			ImageData imageData = getBaseImage().getImageData();
			imageData.transparentPixel = imageData.getPixel(0, 0);
			stringImage = new Image(null, imageData);
			GC stringGc = new GC(stringImage);
			try {
				stringGc.setFont(ResourceManager.getFont(fontName, 10, 0));
				stringGc.setTextAntialias(SWT.ON);
				stringGc.drawText("Sample", 0, 0, SWT.DRAW_TRANSPARENT);
			} finally {
				stringGc.dispose();
			}
			ResourceManager.addImage(fontName, stringImage);
		}
		return stringImage;
	}

	private APropertyNode pnode;

	/**
	 * Set the data of the combo popup, and if it wasn't initialized the fonts will be added
	 */
	@Override
	public void setData(APropertyNode pnode, Object b) {
		this.pnode = pnode;
		if (pnode != null) {
			if (!dataSetted) {
				refreshFont();
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
		for (int index = 0; index < fontsList.size(); index++) {
			String[] fonts = fontsList.get(index);
			for (String element : fonts) {
				itemsList.add(new ComboItem(element, true, createFontImage(element), i, element, element));
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
			combo = new ComboMenuViewer(parent, ComboMenuViewer.NO_IMAGE, "SampleSampleSample");
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
