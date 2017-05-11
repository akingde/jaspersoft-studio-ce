/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.model.dataset;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.help.HelpReferenceBuilder;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.property.descriptor.combo.RComboBoxPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.expression.ExprUtil;
import com.jaspersoft.studio.property.descriptor.expression.JRExpressionPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.parameter.ParameterPropertyDescriptor;
import com.jaspersoft.studio.utils.ModelUtils;
import com.jaspersoft.studio.utils.SyncDatasetRunParameters;

import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.JRDatasetParameter;
import net.sf.jasperreports.engine.JRDatasetRun;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.ReturnValue;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignDatasetRun;
import net.sf.jasperreports.engine.design.JRDesignParameter;
import net.sf.jasperreports.engine.design.JRDesignSubreport;
import net.sf.jasperreports.engine.design.JasperDesign;

public class MDatasetRun extends APropertyNode {
	
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	private RComboBoxPropertyDescriptor subdatasetnameD;
	
	private JasperDesign jasperDesign;
	
	private IPropertyDescriptor[] descriptors;
	
	public MDatasetRun(JRDatasetRun value, JasperDesign jasperDesign) {
		super();
		setValue(value);
		this.jasperDesign = jasperDesign;
	}

	@Override
	public JRDesignDatasetRun getValue() {
		return (JRDesignDatasetRun) super.getValue();
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
	public void createPropertyDescriptors(List<IPropertyDescriptor> desc) {
		subdatasetnameD = new RComboBoxPropertyDescriptor(JRDesignDatasetRun.PROPERTY_DATASET_NAME,
				Messages.MDatasetRun_dataset_name, new String[] { "" }); //$NON-NLS-1$
		subdatasetnameD.setDescription(Messages.MDatasetRun_dataset_name_description);
		desc.add(subdatasetnameD);
		subdatasetnameD.setHelpRefBuilder(new HelpReferenceBuilder(
				"net.sf.jasperreports.doc/docs/schema.reference.html?cp=0_1#datasetRun_subDataset"));

		JRExpressionPropertyDescriptor connExprD = new JRExpressionPropertyDescriptor(
				JRDesignDatasetRun.PROPERTY_CONNECTION_EXPRESSION, Messages.common_connection_expression);
		connExprD.setDescription(Messages.MDatasetRun_connection_expression_description);
		desc.add(connExprD);
		connExprD.setHelpRefBuilder(new HelpReferenceBuilder(
				"net.sf.jasperreports.doc/docs/schema.reference.html?cp=0_1#connectionExpression"));

		JRExpressionPropertyDescriptor dsExprD = new JRExpressionPropertyDescriptor(
				JRDesignDatasetRun.PROPERTY_DATA_SOURCE_EXPRESSION, Messages.MDatasetRun_data_source_expression);
		dsExprD.setDescription(Messages.MDatasetRun_data_source_expression_description);
		desc.add(dsExprD);
		dsExprD.setHelpRefBuilder(new HelpReferenceBuilder(
				"net.sf.jasperreports.doc/docs/schema.reference.html?cp=0_1#dataSourceExpression"));

		JRExpressionPropertyDescriptor pmExprD = new JRExpressionPropertyDescriptor(
				JRDesignDatasetRun.PROPERTY_PARAMETERS_MAP_EXPRESSION, Messages.common_parameters_map_expression);
		pmExprD.setDescription(Messages.MDatasetRun_parameters_map_expression_description);
		desc.add(pmExprD);
		dsExprD.setHelpRefBuilder(new HelpReferenceBuilder(
				"net.sf.jasperreports.doc/docs/schema.reference.html?cp=0_1#parametersMapExpression"));

		ParameterPropertyDescriptor propertiesD = new ParameterPropertyDescriptor(JRDesignDatasetRun.PROPERTY_PARAMETERS,
				Messages.common_parameters);
		propertiesD.setDescription(Messages.MDatasetRun_parameters_description);
		desc.add(propertiesD);
		dsExprD.setHelpRefBuilder(new HelpReferenceBuilder(
				"net.sf.jasperreports.doc/docs/schema.reference.html?cp=0_1#property"));
	}

	@Override
	protected void postDescriptors(IPropertyDescriptor[] descriptors) {
		super.postDescriptors(descriptors);
		if (subdatasetnameD != null && jasperDesign != null) {
			List<JRDataset> datasets = jasperDesign.getDatasetsList();
			String[] sds = new String[datasets.size() + 1];
			sds[0] = "";
			for (int i = 0; i < datasets.size(); i++) {
				sds[i + 1] = datasets.get(i).getName();
			}
			subdatasetnameD.setItems(sds);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#getPropertyValue(java.lang.Object)
	 */
	public Object getPropertyValue(Object id) {
		JRDesignDatasetRun jrElement = getValue();
		if (jrElement == null)
			return null;

		if (id.equals(JRDesignDatasetRun.PROPERTY_PARAMETERS_MAP_EXPRESSION)) {
			return ExprUtil.getExpression(jrElement.getParametersMapExpression());
		}
		if (id.equals(JRDesignDatasetRun.PROPERTY_CONNECTION_EXPRESSION)) {
			return ExprUtil.getExpression(jrElement.getConnectionExpression());
		}
		if (id.equals(JRDesignDatasetRun.PROPERTY_DATA_SOURCE_EXPRESSION)) {
			return ExprUtil.getExpression(jrElement.getDataSourceExpression());
		}
		if (id.equals(JRDesignDatasetRun.PROPERTY_PARAMETERS)) {
			return jrElement.getParameters();
		}
		if (id.equals(JRDesignDatasetRun.PROPERTY_DATASET_NAME)) {
			return jrElement.getDatasetName() != null ? jrElement.getDatasetName() : ""; //$NON-NLS-1$
		}
		if (id.equals(JRDesignDatasetRun.PROPERTY_RETURN_VALUES)){
			return jrElement.getReturnValuesList();
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#setPropertyValue(java.lang.Object, java.lang.Object)
	 */
	public void setPropertyValue(Object id, Object value) {
		JRDesignDatasetRun jrElement = getValue();
		if (id.equals(JRDesignDatasetRun.PROPERTY_CONNECTION_EXPRESSION)) {
			jrElement.getEventSupport().removePropertyChangeListener(this);
			jrElement.setDataSourceExpression(null);
			jrElement.getEventSupport().addPropertyChangeListener(this);
			jrElement.setConnectionExpression(ExprUtil.setValues(jrElement.getConnectionExpression(), value));
		} else if (id.equals(JRDesignDatasetRun.PROPERTY_PARAMETERS_MAP_EXPRESSION))
			jrElement.setParametersMapExpression(ExprUtil.setValues(jrElement.getParametersMapExpression(), value));
		else if (id.equals(JRDesignDatasetRun.PROPERTY_DATA_SOURCE_EXPRESSION)) {
			jrElement.getEventSupport().removePropertyChangeListener(this);
			jrElement.setConnectionExpression(null);
			jrElement.getEventSupport().addPropertyChangeListener(this);
			jrElement.setDataSourceExpression(ExprUtil.setValues(jrElement.getDataSourceExpression(), value));
		} else if (id.equals(JRDesignSubreport.PROPERTY_PARAMETERS)) {
			// The parameter must be updated also into the referenced dataset since the
			// JRDatasetRunParameterExpressionFactory check that every parameter in the dataset run
			// is associated to a parameter with the same name in the referenced dataset
			JRDesignDataset originalDataset = ModelUtils.getDesignDatasetByName(getJasperDesign(),
					getPropertyValue(JRDesignDatasetRun.PROPERTY_DATASET_NAME).toString());
			JRDatasetParameter[] oldParameters = jrElement.getParameters();
			for (JRDatasetParameter prm : oldParameters) {
				jrElement.removeParameter(prm);
			}
			JRDatasetParameter[] newParameters = (JRDatasetParameter[])value;
			for (JRDatasetParameter param : newParameters) {
				try {
					jrElement.addParameter(param);
					if (!originalDataset.getParametersMap().containsKey(param.getName())) {
						JRDesignParameter designParam = new JRDesignParameter();
						designParam.setName(param.getName());
						originalDataset.addParameter(designParam);
					}
				} catch (JRException e) {
					e.printStackTrace();
				}
			}
		} else if (id.equals(JRDesignDatasetRun.PROPERTY_DATASET_NAME)) {
			String oldValue = jrElement.getDatasetName();
			if (Misc.isNullOrEmpty((String) value))
				value = null;
			jrElement.setDatasetName((String) value);
			try {
				SyncDatasetRunParameters.syncDatasetRun(this, oldValue, (String) value);
			} catch (JRException e) {
				e.printStackTrace();
			}
		} else if (id.equals(JRDesignDatasetRun.PROPERTY_RETURN_VALUES)){
			@SuppressWarnings("unchecked")
			List<ReturnValue> returnList = (List<ReturnValue>)value;
			List<ReturnValue> oldValues = new ArrayList<ReturnValue>(jrElement.getReturnValuesList());
			//The element dosen't allow to swap the list, must remove the old element 
			for(ReturnValue oldValue : oldValues){
				jrElement.removeReturnValue(oldValue);
			}
			//and add the new one
			for(ReturnValue newValue : returnList){
				jrElement.addReturnValue(newValue);
			}
		}
	}

	@Override
	public JasperDesign getJasperDesign() {
		return jasperDesign;
	}

	public ImageDescriptor getImagePath() {
		return null;
	}

	public String getDisplayText() {
		return "DatasetRun";
	}

}
