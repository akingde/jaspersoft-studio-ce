/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2010 Jaspersoft Corporation. All
 * rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of Jaspersoft Open Studio.
 * 
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify it under the terms of the GNU Affero
 * General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * Jaspersoft Open Studio is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with Jaspersoft Open Studio. If not,
 * see <http://www.gnu.org/licenses/>.
 */
/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2010 Jaspersoft Corporation. All
 * rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of Jaspersoft Open Studio.
 * 
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify it under the terms of the GNU Affero
 * General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * Jaspersoft Open Studio is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with Jaspersoft Open Studio. If not,
 * see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.model.dataset;

import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.JRDatasetParameter;
import net.sf.jasperreports.engine.JRDatasetRun;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.design.JRDesignDatasetRun;
import net.sf.jasperreports.engine.design.JRDesignSubreport;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.property.descriptor.combo.RComboBoxPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.expression.ExprUtil;
import com.jaspersoft.studio.property.descriptor.expression.JRExpressionPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.parameter.ParameterPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.parameter.dialog.ParameterDTO;

public class MDatasetRun extends APropertyNode {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	public MDatasetRun(JRDatasetRun value, JasperDesign jasperDesign) {
		super();
		setValue(value);
		this.jasperDesign = jasperDesign;
	}

	@Override
	public JRDesignDatasetRun getValue() {
		return (JRDesignDatasetRun) super.getValue();
	}

	private IPropertyDescriptor[] descriptors;
	private static Map<String, Object> defaultsMap;

	@Override
	public Map<String, Object> getDefaultsMap() {
		return defaultsMap;
	}

	@Override
	public IPropertyDescriptor[] getDescriptors() {
		return descriptors;
	}

	@Override
	public void setDescriptors(IPropertyDescriptor[] descriptors1, Map<String, Object> defaultsMap1) {
		descriptors = descriptors1;
		defaultsMap = defaultsMap1;
	}

	@Override
	public void createPropertyDescriptors(List<IPropertyDescriptor> desc, Map<String, Object> defaultsMap) {
		subdatasetnameD = new RComboBoxPropertyDescriptor(JRDesignDatasetRun.PROPERTY_DATASET_NAME,
				Messages.MDatasetRun_dataset_name, new String[] { "" }); //$NON-NLS-1$
		subdatasetnameD.setDescription(Messages.MDatasetRun_dataset_name_description);
		desc.add(subdatasetnameD);

		JRExpressionPropertyDescriptor connExprD = new JRExpressionPropertyDescriptor(
				JRDesignDatasetRun.PROPERTY_CONNECTION_EXPRESSION, Messages.common_connection_expression);
		connExprD.setDescription(Messages.MDatasetRun_connection_expression_description);
		desc.add(connExprD);

		JRExpressionPropertyDescriptor dsExprD = new JRExpressionPropertyDescriptor(
				JRDesignDatasetRun.PROPERTY_DATA_SOURCE_EXPRESSION, Messages.MDatasetRun_data_source_expression);
		dsExprD.setDescription(Messages.MDatasetRun_data_source_expression_description);
		desc.add(dsExprD);

		JRExpressionPropertyDescriptor pmExprD = new JRExpressionPropertyDescriptor(
				JRDesignDatasetRun.PROPERTY_PARAMETERS_MAP_EXPRESSION, Messages.common_parameters_map_expression);
		pmExprD.setDescription(Messages.MDatasetRun_parameters_map_expression_description);
		desc.add(pmExprD);

		ParameterPropertyDescriptor propertiesD = new ParameterPropertyDescriptor(JRDesignDatasetRun.PROPERTY_PARAMETERS,
				Messages.common_parameters);
		propertiesD.setDescription(Messages.MDatasetRun_parameters_description);
		desc.add(propertiesD);

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
			if (propertyDTO == null) {
				propertyDTO = new ParameterDTO();
				propertyDTO.setJasperDesign(getJasperDesign());
				propertyDTO.setValue(jrElement.getParameters());
			}
			return propertyDTO;
		}
		if (id.equals(JRDesignDatasetRun.PROPERTY_DATASET_NAME)) {
			return jrElement.getDatasetName() != null ? jrElement.getDatasetName() : ""; //$NON-NLS-1$
		}
		return null;
	}

	private ParameterDTO propertyDTO;
	private RComboBoxPropertyDescriptor subdatasetnameD;

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
			if (value instanceof ParameterDTO) {
				ParameterDTO v = (ParameterDTO) value;

				for (JRDatasetParameter prm : propertyDTO.getValue())
					jrElement.removeParameter(prm);

				for (JRDatasetParameter param : v.getValue())
					try {
						jrElement.addParameter(param);
					} catch (JRException e) {
						e.printStackTrace();
					}
				propertyDTO = v;
			}
		} else if (id.equals(JRDesignDatasetRun.PROPERTY_DATASET_NAME)) {
			if (value != null && !value.equals(""))//$NON-NLS-1$
				jrElement.setDatasetName((String) value);
			else
				jrElement.setDatasetName(null);
		}
	}

	private JasperDesign jasperDesign;

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
