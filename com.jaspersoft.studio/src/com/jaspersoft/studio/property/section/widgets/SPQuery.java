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

import net.sf.jasperreports.engine.design.JRDesignQuery;

import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.MQuery;
import com.jaspersoft.studio.model.dataset.MDataset;
import com.jaspersoft.studio.property.dataset.dialog.DatasetDialog;
import com.jaspersoft.studio.property.descriptor.pattern.dialog.PatternEditor;
import com.jaspersoft.studio.property.section.AbstractSection;

public class SPQuery extends SPText {

	public SPQuery(Composite parent, AbstractSection section, IPropertyDescriptor pDescriptor) {
		super(parent, section, pDescriptor);
	}

	protected void createComponent(Composite parent) {
		super.createComponent(parent);

		Button btn = section.getWidgetFactory().createButton(parent, "...", SWT.PUSH);
		btn.setToolTipText(pDescriptor.getDescription());
		btn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				PatternEditor wizard = new PatternEditor();
				wizard.setValue(ftext.getText());
				DatasetDialog dlg = new DatasetDialog(ftext.getShell(), mdataset, mquery.getJasperConfiguration());
				if (dlg.open() == Window.OK)
					section.getEditDomain().getCommandStack().execute(dlg.getCommand());
			}
		});
	}

	protected void handleTextChanged(final AbstractSection section, final Object property, String text) {
		JRDesignQuery query = null;
		if (mquery.getValue() != null)
			query = (JRDesignQuery) mquery.getValue().clone();
		else
			query = new JRDesignQuery();
		query.setText(text);
		section.changePropertyOn(property, new MQuery(query, mdataset), mdataset);
	}

	private MQuery mquery;
	private MDataset mdataset;

	@Override
	public void setData(APropertyNode pnode, Object b) {
		mdataset = (MDataset) pnode;
		mquery = (MQuery) b;
		super.setData(mquery, mquery.getPropertyValue(JRDesignQuery.PROPERTY_TEXT));
	}

}
