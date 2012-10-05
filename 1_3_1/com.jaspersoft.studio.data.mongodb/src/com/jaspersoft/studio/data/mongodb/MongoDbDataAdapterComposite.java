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
}