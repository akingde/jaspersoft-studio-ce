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
package com.jaspersoft.studio.server.properties;

import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.model.MQuery;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.server.model.MFolder;
import com.jaspersoft.studio.server.model.MListOfValues;
import com.jaspersoft.studio.server.model.MRQuery;
import com.jaspersoft.studio.server.model.MResource;
import com.jaspersoft.studio.server.properties.dialog.RepositoryDialog;

public class InputControlSection extends ASection {
	private CCombo ctype;
	private Button bmand;
	private Button bread;
	private Button bvisible;
	private Text trefuri;
	private Button bbrowse;

	@Override
	protected void createSectionControls(Composite parent,
			TabbedPropertySheetPage aTabbedPropertySheetPage) {
		AbstractSection.createLabel(parent, getWidgetFactory(), "", 120);

		Composite cmp = new Composite(parent, SWT.NONE);
		cmp.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		cmp.setLayout(new RowLayout());
		cmp.setBackground(parent.getBackground());

		bmand = getWidgetFactory().createButton(cmp, "Mandatory", SWT.CHECK);
		// bmand.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		bread = getWidgetFactory().createButton(cmp, "Read Only", SWT.CHECK);
		// bread.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		bvisible = getWidgetFactory().createButton(cmp, "Visible", SWT.CHECK);
		// bvisible.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		AbstractSection.createLabel(parent, getWidgetFactory(), "Type", 120);

		ctype = getWidgetFactory().createCCombo(parent,
				SWT.BORDER | SWT.READ_ONLY);
		ctype.setItems(new String[] { "Boolean", "Single Value",
				"Single Select List of Values",
				"Single Select List of Values (Radio)",
				"Multi Select List of Values",
				"Multi Select List of Values (Checkbox)",
				"Single Select Query", "Single Select Query (Radio)",
				"Multi Select Query", "Multi Select Query (Checkbox)" });

		AbstractSection.createLabel(parent, getWidgetFactory(),
				"Referenced List of values", 120);

		cmp = new Composite(parent, SWT.NONE);
		cmp.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		GridLayout layout = new GridLayout(2, false);
		layout.marginWidth = 0;
		cmp.setLayout(layout);
		cmp.setBackground(parent.getBackground());

		trefuri = getWidgetFactory().createText(cmp, "",
				SWT.BORDER | SWT.READ_ONLY);
		trefuri.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		bbrowse = new Button(cmp, SWT.PUSH);
		bbrowse.setText("...");
		bbrowse.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent e) {
				Shell shell = Display.getDefault().getActiveShell();
				RepositoryDialog rd = new RepositoryDialog(shell, res.getRoot()) {

					@Override
					public boolean isResourceCompatible(MResource r) {
						return (r instanceof MListOfValues)
								|| (r instanceof MRQuery);
					}

				};
				if (rd.open() == Dialog.OK) {
					MResource rs = rd.getResource();
					if (rs != null) {
						res.getValue().setReferenceUri(
								rs.getValue().getUriString());
						bindingContext.updateTargets();
					}
				}
			}

		});
	}

	@Override
	public void enableFields(boolean enable) {
		ctype.setEnabled(enable);
		bmand.setEnabled(enable);
		bread.setEnabled(enable);
		bvisible.setEnabled(enable);
		trefuri.setEditable(enable);
		bbrowse.setEnabled(enable);
	}

	@Override
	protected void bind() {
		bindingContext.bindValue(SWTObservables
				.observeSingleSelectionIndex(ctype), PojoObservables
				.observeValue(getProxy(res.getValue()), "controlType"));

		bindingContext.bindValue(SWTObservables.observeSelection(bmand),
				PojoObservables.observeValue(res.getValue(), "mandatory"));
		bindingContext.bindValue(SWTObservables.observeSelection(bread),
				PojoObservables.observeValue(res.getValue(), "readOnly"));
		bindingContext.bindValue(SWTObservables.observeSelection(bvisible),
				PojoObservables.observeValue(res.getValue(), "visible"));
	}

	private ShiftMapProxy getProxy(ResourceDescriptor rd) {
		proxy.setResourceDescriptor(rd);
		return proxy;
	}

	private ShiftMapProxy proxy = new ShiftMapProxy();

	class ShiftMapProxy {
		private ResourceDescriptor rd;
		private final int[] shift = new int[] { 1, 2, 3, 8, 6, 10, 4, 9, 7, 11 };

		public void setResourceDescriptor(ResourceDescriptor rd) {
			this.rd = rd;
		}

		public void setControlType(int type) {
			rd.setControlType((byte) shift[type]);
		}

		public int getControlType() {
			for (int i = 0; i < shift.length; i++)
				if (shift[i] == rd.getControlType())
					return i;
			return -1;
		}
	}
}
