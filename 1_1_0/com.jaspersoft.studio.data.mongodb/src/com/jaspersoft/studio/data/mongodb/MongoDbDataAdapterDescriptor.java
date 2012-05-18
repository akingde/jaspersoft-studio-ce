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

import java.util.List;

import net.sf.jasperreports.data.DataAdapter;
import net.sf.jasperreports.data.DataAdapterService;
import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.design.JRDesignField;

import org.eclipse.swt.graphics.Image;

import com.jaspersoft.mongodb.adapter.MongoDbDataAdapter;
import com.jaspersoft.mongodb.adapter.MongoDbDataAdapterImplementation;
import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.data.fields.IFieldsProvider;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

/**
 * 
 * @author gtoffoli
 * @author Eric Diaz
 * 
 */
public class MongoDbDataAdapterDescriptor extends DataAdapterDescriptor implements IFieldsProvider {
    private MongoDbDataAdapter dataAdapter = new MongoDbDataAdapterImplementation();

    private IFieldsProvider fieldsProvider;

    @Override
    public MongoDbDataAdapter getDataAdapter() {
        return dataAdapter;
    }

    @Override
    public void setDataAdapter(DataAdapter dataAdapter) {
        this.dataAdapter = (MongoDbDataAdapter) dataAdapter;
    }

    @Override
    public MongoDbDataAdapterEditor getEditor() {
        return new MongoDbDataAdapterEditor();
    }

    @Override
    public Image getIcon(int size) {
        if (size == 16) {
            return Activator.getImage(Activator.ICON_NAME);
        }
        return null;
    }

    public List<JRDesignField> getFields(DataAdapterService con, JasperReportsConfiguration jConfig,
            JRDataset reportDataset) throws JRException, UnsupportedOperationException {
        getFieldProvider();
        return fieldsProvider.getFields(con, jConfig, reportDataset);
    }

    private void getFieldProvider() {
        if (fieldsProvider == null)
            fieldsProvider = new MongoDbFieldsProvider();
    }

    public boolean supportsGetFieldsOperation() {
        getFieldProvider();
        return fieldsProvider.supportsGetFieldsOperation();
    }
}