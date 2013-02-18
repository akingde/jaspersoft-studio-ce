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
package com.jaspersoft.studio.data.jrdsprovider;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.data.DataAdapterService;
import net.sf.jasperreports.data.provider.DataSourceProviderDataAdapterService;
import net.sf.jasperreports.eclipse.builder.JasperReportCompiler;
import net.sf.jasperreports.engine.JRDataSourceProvider;
import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignField;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;

import com.jaspersoft.studio.data.fields.IFieldsProvider;
import com.jaspersoft.studio.plugin.IEditorContributor;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class JRDSProviderFieldsProvider implements IFieldsProvider {

	private JRDataSourceProvider jrdsp;

	public void setProvider(JRDataSourceProvider jrdsp) {
		this.jrdsp = jrdsp;
	}

	public boolean supportsGetFieldsOperation(JasperReportsConfiguration jConfig) {
		if (jrdsp != null)
			return jrdsp.supportsGetFieldsOperation();
		return false;
	}

	public List<JRDesignField> getFields(DataAdapterService con,
			JasperReportsConfiguration jConfig, JRDataset reportDataset)
			throws JRException, UnsupportedOperationException {
		jrdsp = ((DataSourceProviderDataAdapterService) con).getProvider();
		if (jrdsp != null) {
			JasperReport jr = null;
			try {
				JasperReportCompiler compiler = new JasperReportCompiler();
				IFile file = (IFile) jConfig.get(IEditorContributor.KEY_FILE);
				if (file != null && file.exists()) {
					compiler.setProject(file.getProject());
					compiler.setJrxmlFile(file);

					jr = compiler.compileReport(jConfig);
				}
			} catch (CoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			JRField[] aray = jrdsp.getFields(jr);
			if (aray != null) {
				List<JRDesignField> fields = new ArrayList<JRDesignField>();
				for (JRField f : aray) {
					if (f instanceof JRDesignField)
						fields.add((JRDesignField) f);
					else {
						JRDesignField jdf = new JRDesignField();
						jdf.setName(f.getName());
						jdf.setValueClassName(f.getValueClassName());
						jdf.setDescription(f.getDescription());
						fields.add(jdf);
					}
				}
				return fields;
			}
		}
		return new ArrayList<JRDesignField>();
	}
}
