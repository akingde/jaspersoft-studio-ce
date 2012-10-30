/*
 * JasperReports - Free Java Reporting Library. Copyright (C) 2001 - 2012 Jaspersoft Corporation. All rights reserved.
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
package com.jaspersoft.studio.server.wizard.resource.page;

import net.sf.jasperreports.data.jndi.JndiDataAdapter;

import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.server.messages.Messages;
import com.jaspersoft.studio.server.model.datasource.MRDatasourceJNDI;
import com.jaspersoft.studio.utils.UIUtils;

public class RDDatasourceJNDIPage extends AResourcePage {

	public RDDatasourceJNDIPage(ANode parent, MRDatasourceJNDI resource) {
		super("rdjndidatasource", parent, resource); //$NON-NLS-1$
		setTitle(Messages.RDDatasourceJNDIPage_Title);
		setDescription(Messages.RDDatasourceJNDIPage_Description);
	}

	@Override
	protected void createTabs(TabFolder tabFolder) {
		super.createTabs(tabFolder);
		createDatasourceTab(tabFolder);
		if (!res.getValue().getIsNew())
			tabFolder.setSelection(1);
	}

	protected void createDatasourceTab(TabFolder tabFolder) {
		TabItem item = new TabItem(tabFolder, SWT.NONE);
		item.setText(Messages.RDDatasourceJNDIPage_DatasourceTabItem);

		Composite composite = new Composite(tabFolder, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		item.setControl(composite);

		UIUtils.createLabel(composite, Messages.RDDatasourceJNDIPage_JNDIName);

		final Text tname = new Text(composite, SWT.BORDER);
		tname.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		Button importDA=new Button(composite, SWT.NONE);
		importDA.setText(Messages.RDDatasourceJNDIPage_ImportButton);
		importDA.setToolTipText(Messages.RDDatasourceJNDIPage_ImportButtonTooltip);
		importDA.setLayoutData(new GridData(SWT.RIGHT,SWT.TOP,true,false,2,1));
		importDA.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ImportDataSourceInfoFromDA dialog=new ImportDataSourceInfoFromDA(getShell(), "JNDI", JndiDataAdapter.class); //$NON-NLS-1$
				if(dialog.open()==Window.OK){
					// get information from the selected DA
					JndiDataAdapter da = (JndiDataAdapter) dialog.getSelectedDataAdapter();
					if(da!=null){
						tname.setText(da.getDataSourceName());
					}
					else{
						tname.setText(""); //$NON-NLS-1$
					}
				}
			}
		});

		bindingContext.bindValue(SWTObservables.observeText(tname, SWT.Modify),
				PojoObservables.observeValue(res.getValue(), "jndiName")); //$NON-NLS-1$
	}
}
