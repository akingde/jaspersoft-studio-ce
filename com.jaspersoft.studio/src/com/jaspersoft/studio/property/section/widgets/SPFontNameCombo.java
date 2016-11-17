/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.section.widgets;

import java.awt.GraphicsEnvironment;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.preferences.fonts.FontsPreferencePage;
import com.jaspersoft.studio.preferences.fonts.utils.FontUtils;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.engine.base.JRBaseFont;

/**
 * A combo menu that could be used to represent a font
 * 
 * @author Orlandin Marco
 * 
 */
public class SPFontNameCombo<T extends IPropertyDescriptor> extends ASPropertyWidget<T> {
	private PreferenceListener preferenceListener = new PreferenceListener();

	private final class PreferenceListener implements IPropertyChangeListener {

		public void propertyChange(org.eclipse.jface.util.PropertyChangeEvent event) {
			String property = event.getProperty();
			if (property.equals(FontsPreferencePage.FPP_FONT_LIST))
				refreshFonts();
		}
	}

	/**
	 * The combo popup
	 */
	protected Combo combo;

	/**
	 * True if the combo popup was already initialized with the data, false otherwise
	 */
	protected boolean isRefreshing;

	protected String[] lastFonts = null;

	/**
	 * String used in the combobox to print a separator
	 */
	private static String separator = "__________________";

	public SPFontNameCombo(Composite parent, AbstractSection section, T pDescriptor) {
		super(parent, section, pDescriptor);
		JaspersoftStudioPlugin.getInstance().addPreferenceListener(preferenceListener);
	}

	/**
	 * Given a combo and and a string return the index of the string in the combo
	 * 
	 * @param combo
	 * @param searchedString
	 * @return the index of the string in the combo, or 0 if the string is not found
	 */
	private int indexOf(Combo combo, String searchedString) {
		String[] elements = combo.getItems();
		for (int i = 0; i < elements.length; i++) {
			if (elements[i].equals(searchedString)) {
				return i;
			}
		}
		return 0;
	}

	public static List<String[]> getFontNames() {
		java.util.List<String[]> classes = new ArrayList<String[]>();
		java.util.List<String> elements = new ArrayList<String>();
		classes.add(elements.toArray(new String[elements.size()]));
		elements = new ArrayList<String>();
		String[] names = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
		for (int i = 0; i < names.length; i++) {
			String name = names[i];
			elements.add(name);
		}
		classes.add(elements.toArray(new String[elements.size()]));
		return classes;
	}

	private APropertyNode pnode;

	/**
	 * Set the data of the combo popup, and if it wasn't initialized the fonts will be added
	 */
	@Override
	public void setData(final APropertyNode pnode, Object b) {
		this.pnode = pnode;
		if (pnode != null) {
			createContextualMenu(pnode);
			isRefreshing = true;
			combo.setEnabled(pnode.isEditable());
			refreshFonts();
			if (b != null)
				combo.setText(b.toString());
			isRefreshing = false;
		}
	}
	
	@Override
	public void setData(APropertyNode pnode, Object resolvedValue, Object elementValue) {
		setData(pnode, resolvedValue);
		if (combo != null && !combo.isDisposed()){
			if (elementValue != null){
				combo.setForeground(ColorConstants.black);
			} else {
				combo.setForeground(ColorConstants.gray);
			}
		}
	}

	protected void refreshFonts() {
		if (pnode != null) {
			JasperReportsConfiguration jConfig = pnode.getJasperConfiguration();
			if (jConfig != null) {
				String[] fontList = jConfig.getFontList();
				boolean sameList = lastFonts == fontList;
				if (!sameList) {
					combo.setItems(fontList);
					lastFonts = fontList;
				}
			} else
				FontUtils.stringToItems(getFontNames());
		}
	}

	public void propertyChange(AbstractSection section, String property, String value) {
		section.changeProperty(property, value);
	}

	@Override
	protected void createComponent(Composite parent) {
		if (combo == null) {
			combo = new Combo(parent, SWT.NONE);
			combo.addModifyListener(new ModifyListener() {

				private int time = 0;

				public void modifyText(ModifyEvent e) {
					if (!isRefreshing) {
						if (e.time - time > 100) {
							String value = combo.getText();
							if (!value.equals(separator))
								propertyChange(section, JRBaseFont.PROPERTY_FONT_NAME, combo.getText());
							else
								combo.select(indexOf(combo,
										(String) section.getElement().getPropertyActualValue(JRBaseFont.PROPERTY_FONT_NAME)));
							int stringLength = combo.getText().length();
							combo.setSelection(new Point(stringLength, stringLength));
						}
						time = e.time;
					}
				}
			});
			combo.addDisposeListener(new DisposeListener() {

				@Override
				public void widgetDisposed(DisposeEvent e) {
					JaspersoftStudioPlugin.getInstance().removePreferenceListener(preferenceListener);
				}
			});
		}
	}

	@Override
	public Control getControl() {
		return combo;
	}

}
