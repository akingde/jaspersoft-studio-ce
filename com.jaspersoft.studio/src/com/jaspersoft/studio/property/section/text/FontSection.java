/*
 * JasperReports - Free Java Reporting Library. Copyright (C) 2001 - 2009 Jaspersoft Corporation. All rights reserved.
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
package com.jaspersoft.studio.property.section.text;

import net.sf.jasperreports.engine.base.JRBaseStyle;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.jface.IntegerCellEditorValidator;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.utils.ModelUtils;

/*
 * The location section on the location tab.
 * 
 * @author Chicu Veaceslav
 */
public class FontSection extends AbstractSection {

	private CCombo fontName;
	private CCombo fontSize;
	private Button boldButton;
	private Button italicButton;
	private Button underlineButton;
	private Button strikeTroughtButton;

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.ITabbedPropertySection#createControls(org.eclipse.swt.widgets.Composite,
	 *      org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
	 */
	public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);

		Composite composite = new Composite(parent, SWT.NONE);
		composite.setBackground(composite.getDisplay().getSystemColor(SWT.COLOR_WHITE));
		// GridLayout layout = new GridLayout(7, false);
		composite.setLayout(new RowLayout());

		CLabel label = getWidgetFactory().createCLabel(composite, Messages.common_font + ":", SWT.RIGHT); //$NON-NLS-1$
		// GridData gd = new GridData();
		// gd.widthHint = 100;
		RowData rd = new RowData();
		rd.width = 102;
		label.setLayoutData(rd);

		fontName = new CCombo(composite, SWT.BORDER | SWT.FLAT);
		fontName.setItems(ModelUtils.getFontNames());
		// fontName.addSelectionListener(new SelectionListener() {
		// @Override
		// public void widgetSelected(SelectionEvent e) {
		// changeProperty(JRBaseStyle.PROPERTY_FONT_NAME, fontName.getText());
		// }
		//
		// @Override
		// public void widgetDefaultSelected(SelectionEvent e) {
		// }
		// });
		fontName.addModifyListener(new ModifyListener() {
			private int time = 0;

			public void modifyText(ModifyEvent e) {
				if (e.time - time > 100) {
					String value = fontName.getText();
					if (!value.equals("______________")) //$NON-NLS-1$
						changeProperty(JRBaseStyle.PROPERTY_FONT_NAME, value);
				}
				time = e.time;
			}
		});
		fontName.setToolTipText(Messages.FontSection_font_name_tool_tip);

		fontSize = new CCombo(composite, SWT.BORDER | SWT.FLAT);
		fontSize.setItems(ModelUtils.getFontSizes());
		fontSize.addModifyListener(new ModifyListener() {
			private int time = 0;

			public void modifyText(ModifyEvent e) {
				if (e.time - time > 100) {
					String value = fontSize.getText();
					if (IntegerCellEditorValidator.instance().isValid(value) == null)
						changeProperty(JRBaseStyle.PROPERTY_FONT_SIZE, value);
				}
				time = e.time;
			}
		});

		fontSize.setToolTipText(Messages.FontSection_font_size_tool_tip);

		boldButton = new Button(composite, SWT.FLAT | SWT.TOGGLE);
		boldButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				changeProperty(JRBaseStyle.PROPERTY_BOLD, new Boolean(boldButton.getSelection()));
			}
		});
		boldButton.setImage(JaspersoftStudioPlugin.getImage("icons/resources/bold.png"));
		boldButton.setToolTipText(Messages.FontSection_bold_tool_tip);

		italicButton = new Button(composite, SWT.FLAT | SWT.TOGGLE);
		italicButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				changeProperty(JRBaseStyle.PROPERTY_ITALIC, new Boolean(italicButton.getSelection()));
			}
		});
		italicButton.setImage(JaspersoftStudioPlugin.getImage("icons/resources/italic.png")); //$NON-NLS-1$
		italicButton.setToolTipText(Messages.FontSection_italic_tool_tip);

		underlineButton = new Button(composite, SWT.FLAT | SWT.TOGGLE);
		underlineButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				changeProperty(JRBaseStyle.PROPERTY_UNDERLINE, new Boolean(underlineButton.getSelection()));
			}
		});
		underlineButton.setImage(JaspersoftStudioPlugin.getImage("icons/resources/underline.png")); //$NON-NLS-1$
		underlineButton.setToolTipText(Messages.FontSection_underline_tool_tip);

		strikeTroughtButton = new Button(composite, SWT.FLAT | SWT.TOGGLE);
		strikeTroughtButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				changeProperty(JRBaseStyle.PROPERTY_STRIKE_THROUGH, new Boolean(strikeTroughtButton.getSelection()));
			}
		});
		strikeTroughtButton.setImage(JaspersoftStudioPlugin.getImage("icons/resources/strikethrought.png")); //$NON-NLS-1$
		strikeTroughtButton.setToolTipText(Messages.FontSection_strike_through_tool_tip);
	}

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.view.ITabbedPropertySection#refresh()
	 */
	public void refresh() {
		isRefreshing = true;
		APropertyNode element = getElement();
		if (element != null) {
			String strfontname = (String) element.getPropertyValue(JRBaseStyle.PROPERTY_FONT_NAME);
			fontName.setText(strfontname != null ? strfontname : ""); //$NON-NLS-1$
			String[] items = fontName.getItems();
			for (int i = 0; i < items.length; i++) {
				if (items[i].equals(strfontname)) {
					fontName.select(i);
					break;
				}
			}

			String strfontsize = (String) element.getPropertyValue(JRBaseStyle.PROPERTY_FONT_SIZE);
			items = fontSize.getItems();
			fontSize.setText(strfontsize != null ? strfontsize : ""); //$NON-NLS-1$
			for (int i = 0; i < items.length; i++) {
				if (items[i].equals(strfontsize)) {
					fontSize.select(i);
					break;
				}
			}

			Boolean b = (Boolean) element.getPropertyValue(JRBaseStyle.PROPERTY_BOLD);
			boldButton.setSelection(b != null ? b.booleanValue() : false);
			b = (Boolean) element.getPropertyValue(JRBaseStyle.PROPERTY_ITALIC);
			italicButton.setSelection(b != null ? b.booleanValue() : false);
			b = (Boolean) element.getPropertyValue(JRBaseStyle.PROPERTY_UNDERLINE);
			underlineButton.setSelection(b != null ? b.booleanValue() : false);
			b = (Boolean) element.getPropertyValue(JRBaseStyle.PROPERTY_STRIKE_THROUGH);
			strikeTroughtButton.setSelection(b != null ? b.booleanValue() : false);
		}
		isRefreshing = false;
	}

	@Override
	public boolean isDisposed() {
		return underlineButton.isDisposed();
	}
}
