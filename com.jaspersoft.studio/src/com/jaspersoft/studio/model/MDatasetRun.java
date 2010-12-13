package com.jaspersoft.studio.model;

import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.JRDatasetParameter;
import net.sf.jasperreports.engine.JRDatasetRun;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.design.JRDesignDatasetRun;
import net.sf.jasperreports.engine.design.JRDesignSubreport;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.MExpression;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.descriptor.combo.RWComboBoxPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.expression.JRExpressionPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.parameter.ParameterPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.parameter.dialog.ParameterDTO;

public class MDatasetRun extends APropertyNode {

	public MDatasetRun(JRDatasetRun value, JasperDesign jasperDesign) {
		super();
		setValue(value);
		this.jasperDesign = jasperDesign;
	}

	private static IPropertyDescriptor[] descriptors;
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
		subdatasetnameD = new RWComboBoxPropertyDescriptor(JRDesignDatasetRun.PROPERTY_DATASET_NAME, Messages.MDatasetRun_dataset_name,
				new String[] { "" }, NullEnum.NOTNULL); //$NON-NLS-1$
		subdatasetnameD.setDescription(Messages.MDatasetRun_dataset_name_description);
		desc.add(subdatasetnameD);

		JRExpressionPropertyDescriptor connExprD = new JRExpressionPropertyDescriptor(
				JRDesignDatasetRun.PROPERTY_CONNECTION_EXPRESSION, Messages.MDatasetRun_connection_expression);
		connExprD.setDescription(Messages.MDatasetRun_connection_expression_description);
		desc.add(connExprD);

		JRExpressionPropertyDescriptor dsExprD = new JRExpressionPropertyDescriptor(
				JRDesignDatasetRun.PROPERTY_DATA_SOURCE_EXPRESSION, Messages.MDatasetRun_data_source_expression);
		dsExprD.setDescription(Messages.MDatasetRun_data_source_expression_description);
		desc.add(dsExprD);

		JRExpressionPropertyDescriptor pmExprD = new JRExpressionPropertyDescriptor(
				JRDesignDatasetRun.PROPERTY_PARAMETERS_MAP_EXPRESSION, Messages.MDatasetRun_parameters_map_expression);
		pmExprD.setDescription(Messages.MDatasetRun_parameters_map_expression_description);
		desc.add(pmExprD);

		ParameterPropertyDescriptor propertiesD = new ParameterPropertyDescriptor(JRDesignDatasetRun.PROPERTY_PARAMETERS,
				Messages.MDatasetRun_parameters);
		propertiesD.setDescription(Messages.MDatasetRun_parameters_description);
		desc.add(propertiesD);

	}

	private MExpression cnExpression;
	private MExpression dsExpression;
	private MExpression pmExpression;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#getPropertyValue(java.lang.Object)
	 */
	public Object getPropertyValue(Object id) {
		JRDesignDatasetRun jrElement = (JRDesignDatasetRun) getValue();

		if (id.equals(JRDesignDatasetRun.PROPERTY_PARAMETERS_MAP_EXPRESSION)) {
			if (pmExpression == null)
				pmExpression = new MExpression(jrElement.getParametersMapExpression());
			return pmExpression;
		}
		if (id.equals(JRDesignDatasetRun.PROPERTY_CONNECTION_EXPRESSION)) {
			if (cnExpression == null)
				cnExpression = new MExpression(jrElement.getConnectionExpression());
			return cnExpression;
		}
		if (id.equals(JRDesignDatasetRun.PROPERTY_DATA_SOURCE_EXPRESSION)) {
			if (dsExpression == null)
				dsExpression = new MExpression(jrElement.getDataSourceExpression());
			return dsExpression;
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
			JasperDesign jd = getJasperDesign();
			List<JRDataset> datasets = jd.getDatasetsList();
			String[] sds = new String[datasets.size()];
			for (int i = 0; i < sds.length; i++) {
				sds[i] = datasets.get(i).getName();
			}
			subdatasetnameD.setItems(sds);
			return jrElement.getDatasetName() != null ? jrElement.getDatasetName() : ""; //$NON-NLS-1$
		}
		return null;
	}

	private ParameterDTO propertyDTO;
	private RWComboBoxPropertyDescriptor subdatasetnameD;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#setPropertyValue(java.lang.Object, java.lang.Object)
	 */
	public void setPropertyValue(Object id, Object value) {
		JRDesignDatasetRun jrElement = (JRDesignDatasetRun) getValue();
		if (id.equals(JRDesignDatasetRun.PROPERTY_CONNECTION_EXPRESSION)) {
			if (value instanceof MExpression) {
				cnExpression = (MExpression) value;
				JRExpression expression = (JRExpression) cnExpression.getValue();
				jrElement.setConnectionExpression(expression);
			}
		} else if (id.equals(JRDesignDatasetRun.PROPERTY_PARAMETERS_MAP_EXPRESSION)) {
			if (value instanceof MExpression) {
				pmExpression = (MExpression) value;
				JRExpression expression = (JRExpression) pmExpression.getValue();
				jrElement.setParametersMapExpression(expression);
			}
		} else if (id.equals(JRDesignDatasetRun.PROPERTY_DATA_SOURCE_EXPRESSION)) {
			if (value instanceof MExpression) {
				dsExpression = (MExpression) value;
				JRExpression expression = (JRExpression) dsExpression.getValue();
				jrElement.setDataSourceExpression(expression);
			}
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
			if (!value.equals("")) //$NON-NLS-1$
				jrElement.setDatasetName((String) value);

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
		return null;
	}

}