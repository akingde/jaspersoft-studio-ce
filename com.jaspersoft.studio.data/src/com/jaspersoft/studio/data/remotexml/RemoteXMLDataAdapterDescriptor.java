/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.remotexml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.w3c.dom.Document;

import com.jaspersoft.studio.data.AWizardDataEditorComposite;
import com.jaspersoft.studio.data.Activator;
import com.jaspersoft.studio.data.DataAdapterEditor;
import com.jaspersoft.studio.data.IWizardDataEditorProvider;
import com.jaspersoft.studio.data.fields.IFieldsProvider;
import com.jaspersoft.studio.data.xml.XMLDataAdapterDescriptor;
import com.jaspersoft.studio.utils.XMLUtils;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.data.DataAdapterService;
import net.sf.jasperreports.data.DataFileStream;
import net.sf.jasperreports.data.DataFileUtils;
import net.sf.jasperreports.data.xml.XmlDataAdapterImpl;
import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.ParameterContributorContext;
import net.sf.jasperreports.engine.design.JRDesignField;
import net.sf.jasperreports.engine.util.JRXmlUtils;

public class RemoteXMLDataAdapterDescriptor extends XMLDataAdapterDescriptor
		implements IFieldsProvider, IWizardDataEditorProvider {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	@Override
	public XmlDataAdapterImpl getDataAdapter() {
		if (dataAdapter == null)
			dataAdapter = new XmlDataAdapterImpl();
		return (XmlDataAdapterImpl) dataAdapter;
	}

	@Override
	public DataAdapterEditor getEditor() {
		return new RemoteXMLDataAdapterEditor();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.data.DataAdapterFactory#getIcon(int)
	 */
	@Override
	public Image getIcon(int size) {
		// TODO Auto-generated method stub
		if (size == 16) {
			return Activator.getDefault().getImage("icons/blue-document-code.png"); //$NON-NLS-1$
		}
		return null;
	}

	@Override
	public List<JRDesignField> getFields(DataAdapterService con, JasperReportsConfiguration jConfig, JRDataset jDataset)
			throws JRException, UnsupportedOperationException {
		setRecursiveRetrieval(jConfig);
		setConsiderEmptyNodes(jConfig);
		Throwable t = null;
		ArrayList<JRDesignField> fields = new ArrayList<JRDesignField>();
		DataFileStream ins = null;
		try {
			Map<String, Object> parameters = new HashMap<String, Object>();
			// FIXME - We need to proper populate the map!!!
			ins = DataFileUtils.instance(new ParameterContributorContext(jConfig, null, null))
					.getDataStream(getDataAdapter().getDataFile(), parameters);
			Document doc = JRXmlUtils.parse(ins,
					XMLUtils.isNamespaceAware(getDataAdapter(), jConfig.getJasperDesign()));
			fields.addAll(getFieldsFromDocument(doc, jConfig, jDataset));
		} catch (Exception e) {
			t = e;
		} finally {
			IOUtils.closeQuietly(ins);
		}
		if (t != null) {
			UIUtils.showError(t);
		}
		return fields;
	}

	@Override
	public AWizardDataEditorComposite createDataEditorComposite(Composite parent, WizardPage page) {
		return new RemoteXMLWizardDataEditorComposite(parent, page, this);
	}

}
