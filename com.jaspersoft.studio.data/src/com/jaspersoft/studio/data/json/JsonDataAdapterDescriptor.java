/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.json;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jaspersoft.studio.data.AWizardDataEditorComposite;
import com.jaspersoft.studio.data.Activator;
import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.data.DataAdapterEditor;
import com.jaspersoft.studio.data.IWizardDataEditorProvider;
import com.jaspersoft.studio.data.fields.IFieldsProvider;
import com.jaspersoft.studio.data.querydesigner.json.JsonDataManager;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.data.DataAdapterService;
import net.sf.jasperreports.data.json.JsonDataAdapter;
import net.sf.jasperreports.data.json.JsonDataAdapterImpl;
import net.sf.jasperreports.data.json.JsonExpressionLanguageEnum;
import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.design.JRDesignField;

public class JsonDataAdapterDescriptor extends DataAdapterDescriptor
		implements IFieldsProvider, IWizardDataEditorProvider {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	@Override
	public JsonDataAdapter getDataAdapter() {
		if (dataAdapter == null)
			dataAdapter = new JsonDataAdapterImpl();
		return (JsonDataAdapter) dataAdapter;
	}

	@Override
	public DataAdapterEditor getEditor() {
		return new JsonDataAdapterEditor();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.data.DataAdapterFactory#getIcon(int)
	 */
	@Override
	public Image getIcon(int size) {
		if (size == 16) {
			return Activator.getDefault().getImage("icons/json.png");
		}
		return null;
	}

	@Override
	public boolean supportsGetFieldsOperation(JasperReportsConfiguration jConfig) {
		return true;
	}

	@Override
	public List<JRDesignField> getFields(DataAdapterService con, JasperReportsConfiguration jConfig, JRDataset jDataset)
			throws JRException, UnsupportedOperationException {
		Throwable err = null;
		List<JRDesignField> fields = new ArrayList<JRDesignField>();
		try {
			String language = JsonExpressionLanguageEnum.JSON.getName();
			if(getDataAdapter().getLanguage()!=null) {
				language = getDataAdapter().getLanguage().getName();
			}
			if(jDataset!=null && jDataset.getQuery()!=null){
				language = jDataset.getQuery().getLanguage();
			}
			JsonDataManager m = new JsonDataManager(language);
			m.loadJsonDataFile(getDataAdapter().getDataFile(), jConfig, jDataset);
			fields.addAll(m.extractFields(jDataset.getQuery().getText()));
		} catch (JsonProcessingException e) {
			err = e;
		} catch (IOException e) {
			err = e;
		}
		if (err != null) {
			throw new JRException(err);
		}
		return fields;
	}

	@Override
	public AWizardDataEditorComposite createDataEditorComposite(Composite parent, WizardPage page) {
		return new JsonWizardDataEditorComposite(parent, page, this);
	}

	@Override
	public String[] getLanguages() {
		return new String[] { 
				JsonExpressionLanguageEnum.JSON.getName(),
				JsonExpressionLanguageEnum.JSONQL.getName()};
	}
}
