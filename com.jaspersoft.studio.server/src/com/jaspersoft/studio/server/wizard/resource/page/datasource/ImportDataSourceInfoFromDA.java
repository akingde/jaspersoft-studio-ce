/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.server.wizard.resource.page.datasource;

import java.text.MessageFormat;
import java.util.Collection;

import net.sf.jasperreports.data.DataAdapter;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.data.DataAdapterManager;
import com.jaspersoft.studio.server.messages.Messages;

/**
 * Popup dialog that allows to select an existing JSS data adapter to 
 * retrieve the information that can be used to create a data source 
 * resource on JasperServer.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public class ImportDataSourceInfoFromDA extends Dialog {

	/* selected data adapter */
	private DataAdapter selectedDA;
	/* text info on data adapter kind */
	private String daKind;
	/* class type for the kind of data adapter(s) we are looking for */
	private Class<?> daClass;
	
	public ImportDataSourceInfoFromDA(Shell parentShell, String daKind, Class<?> daClass) {
		super(parentShell);
		this.daKind=daKind;
		this.daClass=daClass;
	}

	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText(Messages.ImportDataSourceInfoFromDA_DialogTitle);
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		GridLayout gl_container = new GridLayout(1, false);
		gl_container.marginHeight = 10;
		gl_container.marginWidth = 10;
		container.setLayout(gl_container);
		
		Label lblInfo = new Label(container, SWT.NONE);
		lblInfo.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, true, false, 1, 1));
		lblInfo.setText(
				MessageFormat.format(Messages.ImportDataSourceInfoFromDA_InfoLabel,new Object[]{daKind}));
		
		final Combo combo = new Combo(container, SWT.READ_ONLY);
		GridData gd_combo = new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1);
		gd_combo.verticalIndent = 10;
		combo.setLayoutData(gd_combo);
		Collection<DataAdapterDescriptor> dataAdapterDescriptors = DataAdapterManager.getPreferencesStorage().getDataAdapterDescriptors();
		for (DataAdapterDescriptor da : dataAdapterDescriptors){
			DataAdapter dataAdapter = da.getDataAdapter();
			if(daClass.isInstance(dataAdapter)){
				combo.add(da.getName());
				combo.setData(combo.getItemCount()-1+"_DA",dataAdapter); //$NON-NLS-1$
			}
		}
		combo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				selectedDA=(DataAdapter)combo.getData(combo.getSelectionIndex()+"_DA"); //$NON-NLS-1$
			}
		});
		if(combo.getItemCount()>0){
			combo.select(0);
			selectedDA=(DataAdapter)combo.getData("1_DA"); //$NON-NLS-1$
		}
		
		return container;
	}

	@Override
	protected Point getInitialSize() {
		return new Point(400, 150);
	}
	
	public DataAdapter getSelectedDataAdapter(){
		return selectedDA;
	}
}
