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
package com.jaspersoft.studio.model;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.JRSubreportReturnValue;
import net.sf.jasperreports.engine.base.JRBaseSubreport;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JRDesignSubreport;
import net.sf.jasperreports.engine.design.JRDesignSubreportParameter;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.descriptor.checkbox.CheckBoxPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.expression.JRExpressionPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.subreport.parameter.SubreportPropertiesPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.subreport.returnvalue.RVPropertyDescriptor;

/**
 * The Class MSubreport.
 */
public class MSubreport extends MGraphicElement {

	/** The icon descriptor. */
	private static IIconDescriptor iconDescriptor;

	/**
	 * Gets the icon descriptor.
	 * 
	 * @return the icon descriptor
	 */
	public static IIconDescriptor getIconDescriptor() {
		if (iconDescriptor == null)
			iconDescriptor = new NodeIconDescriptor("subreport");
		return iconDescriptor;
	}

	/**
	 * Instantiates a new m subreport.
	 */
	public MSubreport() {
		super();
	}

	/**
	 * Instantiates a new m subreport.
	 * 
	 * @param parent
	 *          the parent
	 * @param jrSubreport
	 *          the jr subreport
	 * @param newIndex
	 *          the new index
	 */
	public MSubreport(ANode parent, JRDesignSubreport jrSubreport, int newIndex) {
		super(parent, newIndex);
		setValue(jrSubreport);
		if (jrSubreport != null)
			(jrSubreport).getEventSupport().addPropertyChangeListener(this);
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

	/**
	 * Creates the property descriptors.
	 * 
	 * @param desc
	 *          the desc
	 */
	public void createPropertyDescriptors(List<IPropertyDescriptor> desc, Map<String, Object> defaultsMap) {
		super.createPropertyDescriptors(desc, defaultsMap);

		CheckBoxPropertyDescriptor runToBottomD = new CheckBoxPropertyDescriptor(JRBaseSubreport.PROPERTY_RUN_TO_BOTTOM,
				"Run To Bottom", NullEnum.NULL);
		runToBottomD
				.setDescription("Flag to specify if the subreport should consume all the available space on the current page. When this flag is set, the subreport would consume the entire vertical space available on the master page, and its column and page footers will be printed at the bottom of this space.");
		desc.add(runToBottomD);

		CheckBoxPropertyDescriptor useCacheD = new CheckBoxPropertyDescriptor(JRBaseSubreport.PROPERTY_USING_CACHE,
				"Using Cache", NullEnum.INHERITED);
		useCacheD
				.setDescription("If true, tells the report engine to cache the report definition objects that are loaded from the same location.");
		desc.add(useCacheD);

		JRExpressionPropertyDescriptor exprD = new JRExpressionPropertyDescriptor(JRDesignSubreport.PROPERTY_EXPRESSION,
				"Expression");
		exprD.setDescription("The subreport expression.");
		desc.add(exprD);

		JRExpressionPropertyDescriptor paramExprD = new JRExpressionPropertyDescriptor(
				JRDesignSubreport.PROPERTY_PARAMETERS_MAP_EXPRESSION, "Parameters Map Expression");
		paramExprD.setDescription("The parameters map expression.");
		desc.add(paramExprD);

		JRExpressionPropertyDescriptor connExprD = new JRExpressionPropertyDescriptor(
				JRDesignSubreport.PROPERTY_CONNECTION_EXPRESSION, "Connection Expression");
		connExprD.setDescription("The connection expression.");
		desc.add(connExprD);

		JRExpressionPropertyDescriptor dsExprD = new JRExpressionPropertyDescriptor(
				JRDesignSubreport.PROPERTY_DATASOURCE_EXPRESSION, "Datasource Expression");
		dsExprD.setDescription("The datasource expression.");
		desc.add(dsExprD);

		SubreportPropertiesPropertyDescriptor propertiesD = new SubreportPropertiesPropertyDescriptor(
				JRDesignSubreport.PROPERTY_PARAMETERS, "Parameters");
		propertiesD.setDescription("The datasource expression.");
		desc.add(propertiesD);

		RVPropertyDescriptor returnValuesD = new RVPropertyDescriptor(JRDesignSubreport.PROPERTY_RETURN_VALUES,
				"Return Values");
		returnValuesD.setDescription("Return Values.");
		desc.add(returnValuesD);

		returnValuesD.setCategory("Subreport Properties");
		propertiesD.setCategory("Subreport Properties");
		dsExprD.setCategory("Subreport Properties");
		connExprD.setCategory("Subreport Properties");
		paramExprD.setCategory("Subreport Properties");
		exprD.setCategory("Subreport Properties");
		useCacheD.setCategory("Subreport Properties");
		runToBottomD.setCategory("Subreport Properties");

	}

	private MExpression mExpression;
	private MExpression pmExpression;
	private MExpression cnExpression;
	private MExpression dsExpression;

	private JReportsDTO returnValuesDTO;

	@Override
	public Object getPropertyValue(Object id) {
		JRDesignSubreport jrElement = (JRDesignSubreport) getValue();
		if (id.equals(JRBaseSubreport.PROPERTY_RUN_TO_BOTTOM))
			return jrElement.isRunToBottom();
		if (id.equals(JRBaseSubreport.PROPERTY_USING_CACHE))
			return jrElement.isOwnUsingCache();
		if (id.equals(JRDesignSubreport.PROPERTY_EXPRESSION)) {
			if (mExpression == null)
				mExpression = new MExpression(jrElement.getExpression());
			return mExpression;
		}
		if (id.equals(JRDesignSubreport.PROPERTY_PARAMETERS_MAP_EXPRESSION)) {
			if (pmExpression == null)
				pmExpression = new MExpression(jrElement.getParametersMapExpression());
			return pmExpression;
		}
		if (id.equals(JRDesignSubreport.PROPERTY_CONNECTION_EXPRESSION)) {
			if (cnExpression == null)
				cnExpression = new MExpression(jrElement.getConnectionExpression());
			return cnExpression;
		}
		if (id.equals(JRDesignSubreport.PROPERTY_DATASOURCE_EXPRESSION)) {
			if (dsExpression == null)
				dsExpression = new MExpression(jrElement.getDataSourceExpression());
			return dsExpression;
		}
		if (id.equals(JRDesignSubreport.PROPERTY_PARAMETERS))
			return jrElement.getParametersMap();
		if (id.equals(JRDesignSubreport.PROPERTY_RETURN_VALUES)) {
			if (returnValuesDTO == null) {
				returnValuesDTO = new JReportsDTO();
				returnValuesDTO.setJasperDesign(getJasperDesign());
				returnValuesDTO.setValue(jrElement.getReturnValuesList());
			}
			return returnValuesDTO;

		}

		return super.getPropertyValue(id);
	}

	@Override
	public void setPropertyValue(Object id, Object value) {
		JRDesignSubreport jrElement = (JRDesignSubreport) getValue();
		if (id.equals(JRBaseSubreport.PROPERTY_RUN_TO_BOTTOM))
			jrElement.setRunToBottom((Boolean) value);
		else if (id.equals(JRBaseSubreport.PROPERTY_RUN_TO_BOTTOM))
			jrElement.setUsingCache((Boolean) value);
		else if (id.equals(JRDesignSubreport.PROPERTY_EXPRESSION)) {
			if (value instanceof MExpression) {
				mExpression = (MExpression) value;
				JRExpression expression = (JRExpression) mExpression.getValue();
				jrElement.setExpression(expression);
			}
		} else if (id.equals(JRDesignSubreport.PROPERTY_PARAMETERS_MAP_EXPRESSION)) {
			if (value instanceof MExpression) {
				pmExpression = (MExpression) value;
				JRExpression expression = (JRExpression) pmExpression.getValue();
				jrElement.setParametersMapExpression(expression);
			}
		} else if (id.equals(JRDesignSubreport.PROPERTY_CONNECTION_EXPRESSION)) {
			if (value instanceof MExpression) {
				cnExpression = (MExpression) value;
				JRExpression expression = (JRExpression) cnExpression.getValue();
				jrElement.setConnectionExpression(expression);
			}
		} else if (id.equals(JRDesignSubreport.PROPERTY_DATASOURCE_EXPRESSION)) {
			if (value instanceof MExpression) {
				dsExpression = (MExpression) value;
				JRExpression expression = (JRExpression) dsExpression.getValue();
				jrElement.setDataSourceExpression(expression);
			}
		} else if (id.equals(JRDesignSubreport.PROPERTY_PARAMETERS)) {
			if (value instanceof Map) {
				Map<String, JRDesignSubreportParameter> v = (Map<String, JRDesignSubreportParameter>) value;
				Set<String> names = new HashSet<String>(jrElement.getParametersMap().keySet());
				for (String name : names)
					jrElement.removeParameter(name);

				for (JRDesignSubreportParameter param : v.values())
					try {
						jrElement.addParameter(param);
					} catch (JRException e) {
						e.printStackTrace();
					}
			}
		} else if (id.equals(JRDesignSubreport.PROPERTY_RETURN_VALUES)) {
			returnValuesDTO = (JReportsDTO) value;
			if (returnValuesDTO.getValue() instanceof List) {
				List<JRSubreportReturnValue> list = (List<JRSubreportReturnValue>) returnValuesDTO.getValue();
				jrElement.getReturnValuesList().clear();
				for (JRSubreportReturnValue j : list)
					jrElement.addReturnValue(j);
			}
		}
		super.setPropertyValue(id, value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.MGeneric#getDefaultHeight()
	 */
	@Override
	public int getDefaultHeight() {
		return 200;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.MGeneric#getDefaultWidth()
	 */
	@Override
	public int getDefaultWidth() {
		return 200;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.MGeneric#createJRElement(net.sf.jasperreports.engine.design.JasperDesign)
	 */
	@Override
	public JRDesignElement createJRElement(JasperDesign jasperDesign) {
		return new JRDesignSubreport(jasperDesign);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.MGeneric#getDisplayText()
	 */
	@Override
	public String getDisplayText() {
		return getIconDescriptor().getTitle();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.MGeneric#getImagePath()
	 */
	@Override
	public ImageDescriptor getImagePath() {
		return getIconDescriptor().getIcon16();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.MGeneric#getToolTip()
	 */
	@Override
	public String getToolTip() {
		return getIconDescriptor().getToolTip();
	}

}
