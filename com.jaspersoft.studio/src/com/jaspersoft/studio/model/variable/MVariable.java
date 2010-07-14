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
package com.jaspersoft.studio.model.variable;

import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRGroup;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignVariable;
import net.sf.jasperreports.engine.type.CalculationEnum;
import net.sf.jasperreports.engine.type.IncrementTypeEnum;
import net.sf.jasperreports.engine.type.ResetTypeEnum;

import org.eclipse.ui.views.properties.ComboBoxPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.IIconDescriptor;
import com.jaspersoft.studio.model.MExpression;
import com.jaspersoft.studio.model.NodeIconDescriptor;
import com.jaspersoft.studio.model.dataset.MDataset;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.descriptor.classname.NClassTypePropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.combo.RWComboBoxPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.expression.JRExpressionPropertyDescriptor;
import com.jaspersoft.studio.utils.EnumHelper;
import com.jaspersoft.studio.utils.ModelUtils;

/**
 * The Class MVariable.
 * 
 * @author Chicu Veaceslav
 */
public class MVariable extends MVariableSystem {

	/** The icon descriptor. */
	private static IIconDescriptor iconDescriptor;

	/**
	 * Gets the icon descriptor.
	 * 
	 * @return the icon descriptor
	 */
	public static IIconDescriptor getIconDescriptor() {
		if (iconDescriptor == null)
			iconDescriptor = new NodeIconDescriptor("variable");
		return iconDescriptor;
	}

	/**
	 * Instantiates a new m variable.
	 */
	public MVariable() {
		super();
	}

	/**
	 * Instantiates a new m variable.
	 * 
	 * @param parent
	 *          the parent
	 * @param jrVariable
	 *          the jr variable
	 * @param newIndex
	 *          the new index
	 */
	public MVariable(ANode parent, JRDesignVariable jrVariable, int newIndex) {
		super(parent, jrVariable, newIndex);
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
	protected void createPropertyDescriptors(List<IPropertyDescriptor> desc, Map<String, Object> defaultsMap) {
		super.createPropertyDescriptors(desc, defaultsMap);

		resetGroupD = new RWComboBoxPropertyDescriptor(JRDesignVariable.PROPERTY_RESET_GROUP, "Reset Group",
				new String[] { "" }, NullEnum.NULL);
		resetGroupD.setDescription("Reset group name.");
		desc.add(resetGroupD);

		ComboBoxPropertyDescriptor calculationD = new ComboBoxPropertyDescriptor(JRDesignVariable.PROPERTY_CALCULATION,
				"Calculation", EnumHelper.getEnumNames(CalculationEnum.values(), NullEnum.NOTNULL));
		calculationD
				.setDescription("Calculation to perform on the master report variable when returning the value from the subreport.");
		desc.add(calculationD);

		ComboBoxPropertyDescriptor resetTypeD = new ComboBoxPropertyDescriptor(JRDesignVariable.PROPERTY_RESET_TYPE,
				"Reset type", EnumHelper.getEnumNames(ResetTypeEnum.values(), NullEnum.NOTNULL));
		resetTypeD.setDescription("Reset level for variables that perform calculations.");
		desc.add(resetTypeD);

		ComboBoxPropertyDescriptor incrementTypeD = new ComboBoxPropertyDescriptor(
				JRDesignVariable.PROPERTY_INCREMENT_TYPE, "Increment type", EnumHelper.getEnumNames(IncrementTypeEnum.values(),
						NullEnum.NOTNULL));
		incrementTypeD.setDescription("Increment level for variables that perform calculations.");
		desc.add(incrementTypeD);

		JRExpressionPropertyDescriptor expressionD = new JRExpressionPropertyDescriptor(
				JRDesignVariable.PROPERTY_EXPRESSION, "Expression");
		expressionD
				.setDescription("Definition of the expression associated with the variable. The value of this expression will be calculated at runtime and will represent the value of the corresponding variable or it will be used in calculation to obtain the value of the calculated variable.");
		desc.add(expressionD);

		JRExpressionPropertyDescriptor iniValExprD = new JRExpressionPropertyDescriptor(
				JRDesignVariable.PROPERTY_INITIAL_VALUE_EXPRESSION, "Initial Value Expression");
		iniValExprD
				.setDescription("Definition of the expression that will be used to calculate the initial value of the variable, before any calculations are made.");
		desc.add(iniValExprD);

		NClassTypePropertyDescriptor factoryClassName = new NClassTypePropertyDescriptor(
				JRDesignVariable.PROPERTY_INCREMENTER_FACTORY_CLASS_NAME, "Incrementer Factory Class Name");
		factoryClassName
				.setDescription("The name of a class that implements the net.sf.jasperreports.engine.fill.JRIncrementerFactory interface to use when creating the incrementer instance for this variable. Incrementers are objects that implement the net.sf.jasperreports.engine.fill.JRIncrementer interface and handle the incremental calculation performed on the variable's current value with every iteration in the data source.");
		desc.add(factoryClassName);

		defaultsMap.put(JRDesignVariable.PROPERTY_CALCULATION, EnumHelper.getValue(CalculationEnum.NOTHING, 0, false));
		defaultsMap.put(JRDesignVariable.PROPERTY_RESET_TYPE, EnumHelper.getValue(ResetTypeEnum.REPORT, 1, false));
		defaultsMap.put(JRDesignVariable.PROPERTY_INCREMENT_TYPE, EnumHelper.getValue(IncrementTypeEnum.NONE, 1, false));
	}

	private MExpression mExpression;
	private MExpression mIniValExpression;
	private RWComboBoxPropertyDescriptor resetGroupD;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.variable.MVariableSystem#getPropertyValue(java.lang.Object)
	 */
	@Override
	public Object getPropertyValue(Object id) {
		JRDesignVariable jrVariable = (JRDesignVariable) getValue();
		Object s = super.getPropertyValue(id);
		if (s != null)
			return s;
		if (id.equals(JRDesignVariable.PROPERTY_RESET_GROUP)) {
			if (jrVariable.getResetTypeValue().equals(ResetTypeEnum.GROUP) && resetGroupD != null) {
				JRDesignDataset jrDataset = getDataSet();
				JRGroup[] groups = jrDataset.getGroups();
				if (groups != null) {
					String[] items = new String[groups.length + 1];
					items[0] = "";
					for (int j = 0; j < groups.length; j++) {
						items[j + 1] = groups[j].getName();
					}
					resetGroupD.setItems(items);
					if (jrVariable.getResetGroup() != null)
						return jrVariable.getResetGroup().getName();
				}
			}
			return "";
		}
		if (id.equals(JRDesignVariable.PROPERTY_CALCULATION))
			return EnumHelper.getValue(jrVariable.getCalculationValue(), 0, false);
		if (id.equals(JRDesignVariable.PROPERTY_RESET_TYPE))
			return EnumHelper.getValue(jrVariable.getResetTypeValue(), 1, false);
		if (id.equals(JRDesignVariable.PROPERTY_INCREMENT_TYPE))
			return EnumHelper.getValue(jrVariable.getIncrementTypeValue(), 1, false);
		if (id.equals(JRDesignVariable.PROPERTY_INCREMENTER_FACTORY_CLASS_NAME))
			return jrVariable.getIncrementerFactoryClassName();
		if (id.equals(JRDesignVariable.PROPERTY_EXPRESSION)) {
			if (mExpression == null)
				mExpression = new MExpression(jrVariable.getExpression());
			return mExpression;
		}
		if (id.equals(JRDesignVariable.PROPERTY_INITIAL_VALUE_EXPRESSION)) {
			if (mIniValExpression == null)
				mIniValExpression = new MExpression(jrVariable.getInitialValueExpression());
			return mIniValExpression;
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.variable.MVariableSystem#setPropertyValue(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void setPropertyValue(Object id, Object value) {
		super.setPropertyValue(id, value);
		JRDesignVariable jrVariable = (JRDesignVariable) getValue();
		if (id.equals(JRDesignVariable.PROPERTY_RESET_GROUP)) {
			if (!value.equals("") && jrVariable.getResetTypeValue().equals(ResetTypeEnum.GROUP)) {
				JRDesignDataset jrDataset = getDataSet();
				JRGroup group = (JRGroup) jrDataset.getGroupsMap().get(value);
				jrVariable.setResetGroup(group);
			}
		} else if (id.equals(JRDesignVariable.PROPERTY_CALCULATION))
			jrVariable.setCalculation((CalculationEnum) EnumHelper.getSetValue(CalculationEnum.values(), value, 0, false));
		else if (id.equals(JRDesignVariable.PROPERTY_RESET_TYPE)) {
			jrVariable.setResetType((ResetTypeEnum) EnumHelper.getSetValue(ResetTypeEnum.values(), value, 1, false));
			if (!jrVariable.getResetTypeValue().equals(ResetTypeEnum.GROUP))
				jrVariable.setResetGroup(null);
		} else if (id.equals(JRDesignVariable.PROPERTY_INCREMENT_TYPE))
			jrVariable.setIncrementType((IncrementTypeEnum) EnumHelper.getSetValue(IncrementTypeEnum.values(), value, 1,
					false));
		else if (id.equals(JRDesignVariable.PROPERTY_INCREMENTER_FACTORY_CLASS_NAME))
			jrVariable.setIncrementerFactoryClassName((String) value);
	}

	private JRDesignDataset dataset;

	protected JRDesignDataset getDataSet() {
		if (dataset != null)
			return dataset;
		ANode n = (ANode) getParent();
		while (true) {
			if (n == null)
				break;
			else if (n instanceof MDataset) {
				dataset = (JRDesignDataset) ((MDataset) n).getValue();
				break;
			}
			n = (ANode) n.getParent();
		}
		if (dataset == null)
			dataset = getJasperDesign().getMainDesignDataset();
		return dataset;
	}

	/**
	 * Creates the jr variable.
	 * 
	 * @param jrDataset
	 *          the jr dataset
	 * @return the jR design variable
	 */
	public static JRDesignVariable createJRVariable(JRDesignDataset jrDataset) {
		JRDesignVariable jrDesignVariable = new JRDesignVariable();
		jrDesignVariable.setSystemDefined(false);
		jrDesignVariable.setName(ModelUtils.getDefaultName(jrDataset.getVariablesMap(), "Variable_"));
		return jrDesignVariable;
	}

}
