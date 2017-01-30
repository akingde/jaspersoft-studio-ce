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

import org.eclipse.jface.util.IPropertyChangeListener;
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
import com.jaspersoft.studio.property.combomenu.WritableComboMenuViewer;
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
	protected WritableComboMenuViewer combo;

	/**
	 * True if the combo popup was already initialized with the data, false otherwise
	 */
	protected boolean dataSetted;

	public SPFontNamePopUp(Composite parent, AbstractSection section, T pDescriptor) {
		super(parent, section, pDescriptor);
		dataSetted = false;
		JaspersoftStudioPlugin.getInstance().addPreferenceListener(preferenceListener);
	}

	public static BufferedImage createFontImage(final String fontName, FontUtil util) {
		int width = 55;
		int height = 15;
	    BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
	
	    Graphics2D ig2 = bi.createGraphics();
		ig2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		ig2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

		JRFont jrFont = new JRDesignFont(null);
		jrFont.setFontName(fontName);
		jrFont.setFontSize(12f);
	    Font font = util.getAwtFont(jrFont, Locale.getDefault());
	    ig2.setFont(font);
	    String message = "Sample";
	    FontMetrics fontMetrics = ig2.getFontMetrics();
	    int stringWidth = fontMetrics.stringWidth(message);
	    int stringHeight = fontMetrics.getAscent();
	    ig2.setPaint(Color.black);
	    ig2.drawString(message, Math.max((width - stringWidth), 0) / 2,  height / 2 + stringHeight / 4);
		return bi;
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
			combo = new WritableComboMenuViewer(parent, WritableComboMenuViewer.NO_IMAGE, "SampleSampleSample");
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
