/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.model;

import java.util.List;
import java.util.Map;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.dataset.MDataset;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.descriptor.combo.RWComboBoxPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.text.NTextPropertyDescriptor;
import com.jaspersoft.studio.utils.ModelUtils;

import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.JRQuery;
import net.sf.jasperreports.engine.design.JRDesignQuery;

public class MQuery extends APropertyNode implements IPropertySource {
	
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
	
	private static IPropertyDescriptor[] descriptors;

	private MDataset mdataset;

	private RWComboBoxPropertyDescriptor languageD;
	
	public MQuery(JRQuery jrQuery, MDataset mdataset) {
		super();
		this.mdataset = mdataset;
		setValue(jrQuery);
	}

	public MDataset getMdataset() {
		return mdataset;
	}

	@Override
	public JRDesignQuery getValue() {
		return (JRDesignQuery) super.getValue();
	}

	@Override
	protected void postDescriptors(IPropertyDescriptor[] descriptors) {
		super.postDescriptors(descriptors);
		if (languageD != null)
			languageD.setItems(ModelUtils.getQueryLanguages(getJasperConfiguration()));
	}

	@Override
	public void createPropertyDescriptors(List<IPropertyDescriptor> desc) {
		// pen
		NTextPropertyDescriptor textD = new NTextPropertyDescriptor(JRDesignQuery.PROPERTY_TEXT, Messages.common_text);
		textD.setDescription(Messages.MQuery_text_description);
		desc.add(textD);

		languageD = new RWComboBoxPropertyDescriptor(JRDesignQuery.PROPERTY_LANGUAGE, Messages.common_language,
				ModelUtils.getQueryLanguages(getJasperConfiguration()), NullEnum.NOTNULL);
		languageD.setDescription(Messages.MQuery_language_description);
		languageD.setCategory(Messages.common_report);
		desc.add(languageD);
	}

	@Override
	public IPropertyDescriptor[] getDescriptors() {
		return descriptors;
	}

	@Override
	public void setDescriptors(IPropertyDescriptor[] descriptors1) {
		descriptors = descriptors1;
	}
	
	@Override
	protected Map<String, DefaultValue> createDefaultsMap() {
		Map<String, DefaultValue> defaultsMap = super.createDefaultsMap();
		
		defaultsMap.put(JRDesignQuery.PROPERTY_LANGUAGE, new DefaultValue("SQL", false)); //$NON-NLS-1$
		
		return defaultsMap;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#getPropertyValue(java.lang.Object)
	 */
	public Object getPropertyValue(Object id) {
		JRQuery jrQuery = (JRQuery) getValue();
		if (jrQuery != null) {
			if (id.equals(JRDesignQuery.PROPERTY_TEXT))
				return jrQuery.getText();
			if (id.equals(JRDesignQuery.PROPERTY_LANGUAGE))
				return jrQuery.getLanguage();
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#setPropertyValue(java.lang.Object, java.lang.Object)
	 */
	public void setPropertyValue(Object id, Object value) {
		JRDesignQuery jrQuery = (JRDesignQuery) getValue();
		if (jrQuery != null) {
			if (id.equals(JRDesignQuery.PROPERTY_TEXT)) {
				jrQuery.setText((String) value);
			} else if (id.equals(JRDesignQuery.PROPERTY_LANGUAGE)) {
				String lang = Misc.nullValue((String) value);
				jrQuery.setLanguage(ModelUtils.getLanguage(lang));
			}
		}
	}

	public String getDisplayText() {
		return null;
	}

	public ImageDescriptor getImagePath() {
		return null;
	}

}
