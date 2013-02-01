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
package com.jaspersoft.studio.data.mongodb;

import net.sf.jasperreports.data.DataAdapter;

import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.mongodb.adapter.MongoDbDataAdapter;
import com.jaspersoft.studio.data.ADataAdapterComposite;
import com.jaspersoft.studio.data.DataAdapterDescriptor;

/**
 * 
 * @author Eric Diaz
 * 
 */
public class MongoDbDataAdapterComposite extends ADataAdapterComposite {
    private Text mongoUriField;

    private Text usernameField;

    private Text passwordField;

    private MongoDbDataAdapterDescriptor dataAdapterDescriptor;

    public MongoDbDataAdapterComposite(Composite parent, int style) {
        super(parent, style);
        initComponents();
    }

    private void initComponents() {
        setLayout(new GridLayout(2, false));

        createLabel("Mongo URI");
        mongoUriField = createTextField(false);
        createLabel("Username");
        usernameField = createTextField(false);
        createLabel("Password");
        passwordField = createTextField(true);
    }

    private void createLabel(String text) {
        Label label = new Label(this, SWT.NONE);
        label.setText(text);
        label.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
    }

    private Text createTextField(boolean password) {
        Text textField = new Text(this, !password ? SWT.BORDER : SWT.BORDER | SWT.PASSWORD);
        textField.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        return textField;
    }

    public DataAdapterDescriptor getDataAdapter() {
        if (dataAdapterDescriptor == null) {
            dataAdapterDescriptor = new MongoDbDataAdapterDescriptor();
        }
        return dataAdapterDescriptor;
    }

    @Override
    public void setDataAdapter(DataAdapterDescriptor dataAdapterDescriptor) {
        this.dataAdapterDescriptor = (MongoDbDataAdapterDescriptor) dataAdapterDescriptor;
        MongoDbDataAdapter dataAdapter = (MongoDbDataAdapter) dataAdapterDescriptor.getDataAdapter();
        bindWidgets(dataAdapter);
    }

    @Override
    protected void bindWidgets(DataAdapter dataAdapter) {
        bindingContext.bindValue(SWTObservables.observeText(mongoUriField, SWT.Modify),
                PojoObservables.observeValue(dataAdapter, "mongoURI"));
        bindingContext.bindValue(SWTObservables.observeText(usernameField, SWT.Modify),
                PojoObservables.observeValue(dataAdapter, "username"));
        bindingContext.bindValue(SWTObservables.observeText(passwordField, SWT.Modify),
                PojoObservables.observeValue(dataAdapter, "password"));
    }
    
    @Override
    public String getHelpContextId() {
    	return PREFIX.concat("adapter_mongodb");
    }
}
