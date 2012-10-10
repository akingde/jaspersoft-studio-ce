/*
 * JasperReports - Free Java Reporting Library. Copyright (C) 2001 - 2011 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of JasperReports.
 * 
 * JasperReports is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser General
 * Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * JasperReports is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License along with JasperReports. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.property.section.widgets;

import net.sf.jasperreports.engine.JRFont;
import net.sf.jasperreports.engine.base.JRBaseStyle;
import net.sf.jasperreports.engine.design.JRDesignFont;
import net.sf.jasperreports.engine.util.JRStyleResolver;

import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.jface.IntegerCellEditorValidator;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.text.MFont;
import com.jaspersoft.studio.preferences.fonts.FontsPreferencePage;
import com.jaspersoft.studio.property.descriptor.combo.RWComboBoxPropertyDescriptor;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.utils.ModelUtils;

public class SPFont extends ASPropertyWidget {
	private final class PreferenceListener implements IPropertyChangeListener {

		public void propertyChange(org.eclipse.jface.util.PropertyChangeEvent event) {
			if (event.getProperty().equals(FontsPreferencePage.FPP_FONT_LIST)) {
				if (parentNode != null) {
					fontName.setItems(ModelUtils.getFontNames(parentNode.getJasperConfiguration()));
				}
			}
		}
	}

	private PreferenceListener preferenceListener;
	private Combo fontName;
	private Combo fontSize;
	private ToolItem boldButton;
	private ToolItem italicButton;
	private ToolItem underlineButton;
	private ToolItem strikeTroughtButton;

	public SPFont(Composite parent, AbstractSection section, IPropertyDescriptor pDescriptor) {
		super(parent, section, pDescriptor);
		preferenceListener = new PreferenceListener();
		JaspersoftStudioPlugin.getInstance().getPreferenceStore().addPropertyChangeListener(preferenceListener);
		fontName.addDisposeListener(new DisposeListener() {

			@Override
			public void widgetDisposed(DisposeEvent e) {
				JaspersoftStudioPlugin.getInstance().getPreferenceStore().removePropertyChangeListener(preferenceListener);
			}
		});
	}

	@Override
	public Control getControl() {
		return group.getParent();
	}

	protected void createComponent(Composite parent) {
		mfont = new MFont(new JRDesignFont(null));

		group = section.getWidgetFactory().createSection(parent, pDescriptor.getDisplayName(), true, 2);

		final RWComboBoxPropertyDescriptor pd = (RWComboBoxPropertyDescriptor) mfont
				.getPropertyDescriptor(JRBaseStyle.PROPERTY_FONT_NAME);
		fontName = section.getWidgetFactory().createCombo(group, SWT.NONE);
		fontName.setItems(pd.getItems());
		fontName.addModifyListener(new ModifyListener() {
			private int time = 0;

			public void modifyText(ModifyEvent e) {
				if (e.time - time > 100) {
					String value = fontName.getText();
					if (!value.equals("______________")) //$NON-NLS-1$
						changeProperty(section, pDescriptor.getId(), pd.getId(), value);
				}
				time = e.time;
			}
		});
		fontName.setToolTipText(pd.getDescription());

		final RWComboBoxPropertyDescriptor pd1 = (RWComboBoxPropertyDescriptor) mfont
				.getPropertyDescriptor(JRBaseStyle.PROPERTY_FONT_SIZE);

		fontSize = section.getWidgetFactory().createCombo(group, SWT.FLAT);
		fontSize.setItems(pd1.getItems());
		fontSize.addModifyListener(new ModifyListener() {
			private int time = 0;

			public void modifyText(ModifyEvent e) {
				if (e.time - time > 100) {
					String value = fontSize.getText();
					if (IntegerCellEditorValidator.instance().isValid(value) == null)
						changeProperty(section, pDescriptor.getId(), pd1.getId(), value);
				}
				time = e.time;
			}
		});
		fontSize.setToolTipText(pd1.getDescription());

		ToolBar toolBar = new ToolBar(group, SWT.FLAT | SWT.WRAP | SWT.LEFT);
		GridData gd = new GridData();
		gd.horizontalSpan = 2;
		toolBar.setLayoutData(gd);

		boldButton = createItem(toolBar, JRBaseStyle.PROPERTY_BOLD, "icons/resources/edit-bold.png");

		italicButton = createItem(toolBar, JRBaseStyle.PROPERTY_ITALIC, "icons/resources/edit-italic.png");

		underlineButton = createItem(toolBar, JRBaseStyle.PROPERTY_UNDERLINE, "icons/resources/edit-underline.png");

		strikeTroughtButton = createItem(toolBar, JRBaseStyle.PROPERTY_STRIKE_THROUGH, "icons/resources/edit-strike.png");
	}

	private ToolItem createItem(ToolBar toolBar, Object id, String image) {
		final IPropertyDescriptor ipd = mfont.getPropertyDescriptor(id);

		final ToolItem item = new ToolItem(toolBar, SWT.CHECK);
		item.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				changeProperty(section, pDescriptor.getId(), ipd.getId(), new Boolean(item.getSelection()));
			}
		});
		item.setImage(JaspersoftStudioPlugin.getImage(image)); //$NON-NLS-1$
		item.setToolTipText(ipd.getDescription());
		return item;
	}

	private void changeProperty(AbstractSection section, Object property, Object prop, Object value) {
		section.changePropertyOn(prop, value, mfont);
		if (property != null && parentNode != null)
			section.changePropertyOn(property, new MFont((JRFont) mfont.getValue()), parentNode);
	}

	private MFont mfont;
	private APropertyNode parentNode;
	private Composite group;

	public void setData(APropertyNode pnode, Object value) {
		this.parentNode = pnode;
		this.mfont = (MFont) value;
		if (mfont != null) {
			
			JRFont fontValue = (JRFont) mfont.getValue();
			
			fontName.setItems(ModelUtils.getFontNames(parentNode.getJasperConfiguration()));
			String strfontname =  JRStyleResolver.getFontName(fontValue);//(String) mfont.getPropertyValue(JRBaseStyle.PROPERTY_FONT_NAME);
			fontName.setText(strfontname != null ? strfontname : ""); //$NON-NLS-1$
			String[] items = fontName.getItems();
			for (int i = 0; i < items.length; i++) {
				if (items[i].equals(strfontname)) {
					fontName.select(i);
					break;
				}
			}

			String strfontsize =  Integer.toString(JRStyleResolver.getFontSize(fontValue)); //(String) mfont.getPropertyValue(JRBaseStyle.PROPERTY_FONT_SIZE);
			items = fontSize.getItems();
			fontSize.setText(strfontsize != null ? strfontsize : ""); //$NON-NLS-1$
			for (int i = 0; i < items.length; i++) {
				if (items[i].equals(strfontsize)) {
					fontSize.select(i);
					break;
				}
			}

			Boolean b = JRStyleResolver.isBold(fontValue); //(Boolean) mfont.getPropertyValue(JRBaseStyle.PROPERTY_BOLD);
			boldButton.setSelection(b != null ? b.booleanValue() : false);
			b = JRStyleResolver.isItalic(fontValue); //(Boolean) mfont.getPropertyValue(JRBaseStyle.PROPERTY_ITALIC);
			italicButton.setSelection(b != null ? b.booleanValue() : false);
			b = JRStyleResolver.isUnderline(fontValue);  //(Boolean) mfont.getPropertyValue(JRBaseStyle.PROPERTY_UNDERLINE);
			underlineButton.setSelection(b != null ? b.booleanValue() : false);
			b = JRStyleResolver.isStrikeThrough(fontValue); //(Boolean) mfont.getPropertyValue(JRBaseStyle.PROPERTY_STRIKE_THROUGH);
			strikeTroughtButton.setSelection(b != null ? b.booleanValue() : false);
		}
	}
}
