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

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.jface.IntegerCellEditorValidator;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.text.MFont;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.utils.ModelUtils;

public class SPFont {
	private CCombo fontName;
	private CCombo fontSize;
	private Button boldButton;
	private Button italicButton;
	private Button underlineButton;
	private Button strikeTroughtButton;

	public SPFont(Composite parent, AbstractSection section, String property) {
		this(parent, section, property, true);
	}

	public SPFont(Composite parent, AbstractSection section, String property,
			boolean withLabel) {
		createComponent(parent, section, property, withLabel);
	}

	private void createComponent(Composite parent,
			final AbstractSection section, final String property,
			boolean withLabel) {
		Composite composite = parent;
		if (withLabel) {
			composite = new Composite(parent, SWT.NONE);
			composite.setBackground(parent.getBackground());
			composite.setLayout(new RowLayout());

			CLabel label = section.getWidgetFactory().createCLabel(composite,
					Messages.common_font + ":", SWT.RIGHT); //$NON-NLS-1$

			RowData rd = new RowData();
			rd.width = 102;
			label.setLayoutData(rd);
		}

		fontName = new CCombo(composite, SWT.BORDER | SWT.FLAT);
		fontName.setItems(ModelUtils.getFontNames());
		fontName.addModifyListener(new ModifyListener() {
			private int time = 0;

			public void modifyText(ModifyEvent e) {
				if (e.time - time > 100) {
					String value = fontName.getText();
					if (!value.equals("______________")) //$NON-NLS-1$
						changeProperty(section, property,
								JRBaseStyle.PROPERTY_FONT_NAME, value);
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
						changeProperty(section, property,
								JRBaseStyle.PROPERTY_FONT_SIZE, value);
				}
				time = e.time;
			}
		});

		fontSize.setToolTipText(Messages.FontSection_font_size_tool_tip);

		boldButton = new Button(composite, SWT.FLAT | SWT.TOGGLE);
		boldButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				changeProperty(section, property, JRBaseStyle.PROPERTY_BOLD,
						new Boolean(boldButton.getSelection()));
			}
		});
		boldButton.setImage(JaspersoftStudioPlugin
				.getImage("icons/resources/bold.png"));
		boldButton.setToolTipText(Messages.FontSection_bold_tool_tip);

		italicButton = new Button(composite, SWT.FLAT | SWT.TOGGLE);
		italicButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				changeProperty(section, property, JRBaseStyle.PROPERTY_ITALIC,
						new Boolean(italicButton.getSelection()));
			}
		});
		italicButton.setImage(JaspersoftStudioPlugin
				.getImage("icons/resources/italic.png")); //$NON-NLS-1$
		italicButton.setToolTipText(Messages.FontSection_italic_tool_tip);

		underlineButton = new Button(composite, SWT.FLAT | SWT.TOGGLE);
		underlineButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				changeProperty(section, property,
						JRBaseStyle.PROPERTY_UNDERLINE, new Boolean(
								underlineButton.getSelection()));
			}
		});
		underlineButton.setImage(JaspersoftStudioPlugin
				.getImage("icons/resources/underline.png")); //$NON-NLS-1$
		underlineButton.setToolTipText(Messages.FontSection_underline_tool_tip);

		strikeTroughtButton = new Button(composite, SWT.FLAT | SWT.TOGGLE);
		strikeTroughtButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				changeProperty(section, property,
						JRBaseStyle.PROPERTY_STRIKE_THROUGH, new Boolean(
								strikeTroughtButton.getSelection()));
			}
		});
		strikeTroughtButton.setImage(JaspersoftStudioPlugin
				.getImage("icons/resources/strikethrought.png")); //$NON-NLS-1$
		strikeTroughtButton
				.setToolTipText(Messages.FontSection_strike_through_tool_tip);
	}

	private void changeProperty(AbstractSection section, String property,
			String prop, Object value) {
		section.changePropertyOn(prop, value, mfont);
		section.changePropertyOn(property,
				new MFont((JRFont) mfont.getValue()), parent);
	}

	private MFont mfont;
	private APropertyNode parent;

	public void setData(APropertyNode parent, MFont element) {
		this.parent = parent;
		this.mfont = element;
		if (element != null) {
			String strfontname = (String) element
					.getPropertyValue(JRBaseStyle.PROPERTY_FONT_NAME);
			fontName.setText(strfontname != null ? strfontname : ""); //$NON-NLS-1$
			String[] items = fontName.getItems();
			for (int i = 0; i < items.length; i++) {
				if (items[i].equals(strfontname)) {
					fontName.select(i);
					break;
				}
			}

			String strfontsize = (String) element
					.getPropertyValue(JRBaseStyle.PROPERTY_FONT_SIZE);
			items = fontSize.getItems();
			fontSize.setText(strfontsize != null ? strfontsize : ""); //$NON-NLS-1$
			for (int i = 0; i < items.length; i++) {
				if (items[i].equals(strfontsize)) {
					fontSize.select(i);
					break;
				}
			}

			Boolean b = (Boolean) element
					.getPropertyValue(JRBaseStyle.PROPERTY_BOLD);
			boldButton.setSelection(b != null ? b.booleanValue() : false);
			b = (Boolean) element.getPropertyValue(JRBaseStyle.PROPERTY_ITALIC);
			italicButton.setSelection(b != null ? b.booleanValue() : false);
			b = (Boolean) element
					.getPropertyValue(JRBaseStyle.PROPERTY_UNDERLINE);
			underlineButton.setSelection(b != null ? b.booleanValue() : false);
			b = (Boolean) element
					.getPropertyValue(JRBaseStyle.PROPERTY_STRIKE_THROUGH);
			strikeTroughtButton.setSelection(b != null ? b.booleanValue()
					: false);
		}
	}
}
