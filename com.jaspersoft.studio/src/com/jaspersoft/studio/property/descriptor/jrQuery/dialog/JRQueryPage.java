/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2010 Jaspersoft Corporation. All
 * rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of Jaspersoft Open Studio.
 * 
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify it under the terms of the GNU Affero
 * General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * Jaspersoft Open Studio is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with Jaspersoft Open Studio. If not,
 * see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.property.descriptor.jrQuery.dialog;

import net.sf.jasperreports.engine.design.JRDesignQuery;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import com.jaspersoft.studio.model.MQuery;
import com.jaspersoft.studio.utils.ModelUtils;

public class JRQueryPage extends WizardPage {
	private MQuery value;
	private Combo langCombo;
	private StyledText queryText;

	public MQuery getValue() {
		return value;
	}

	@Override
	public void dispose() {
		JRDesignQuery jrQuery = new JRDesignQuery();
		try {
			int selectionIndex = langCombo.getSelectionIndex();
			if (selectionIndex < 0)
				selectionIndex = 0;
			jrQuery.setLanguage(langCombo.getItem(selectionIndex));
		} catch (Exception e) {
			e.printStackTrace();
		}
		jrQuery.setText(queryText.getText());
		value = new MQuery(jrQuery);
		super.dispose();
	}

	public void setValue(MQuery list) {
		this.value = list;
	}

	protected JRQueryPage(String pageName) {
		super(pageName);
		setTitle("Query Editor");
		setDescription("Query editor.");
	}

	@Override
	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		composite.setLayout(layout);
		setControl(composite);

		Label lbl1 = new Label(composite, SWT.NONE);
		lbl1.setText("Language:");

		langCombo = new Combo(composite, SWT.DROP_DOWN | SWT.FLAT | SWT.BORDER);
		langCombo.setItems(ModelUtils.getQueryLanguages());
		langCombo.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				// set value into the MQuery, attention, thru commands!

			}
		});

		Label lbl2 = new Label(composite, SWT.NONE);
		lbl2.setText("Query:");
		GridData gd = new GridData();
		gd.horizontalSpan = 2;
		lbl2.setLayoutData(gd);

		queryText = new StyledText(composite, SWT.BORDER);
		gd = new GridData();
		gd.horizontalAlignment = GridData.FILL;
		gd.grabExcessHorizontalSpace = true;
		gd.verticalAlignment = GridData.FILL;
		gd.grabExcessVerticalSpace = true;
		gd.horizontalSpan = 2;
		queryText.setLayoutData(gd);

		setWidgets();
		queryText.setFocus();
	}

	private void setWidgets() {
		String lang = (String) value.getPropertyValue(JRDesignQuery.PROPERTY_LANGUAGE);
		String[] items = langCombo.getItems();
		for (int i = 0; i < items.length; i++) {
			if (items[i].equals(lang)) {
				langCombo.select(i);
				break;
			}
		}
		queryText.setText((String) value.getPropertyValue(JRDesignQuery.PROPERTY_TEXT));
	}

}
