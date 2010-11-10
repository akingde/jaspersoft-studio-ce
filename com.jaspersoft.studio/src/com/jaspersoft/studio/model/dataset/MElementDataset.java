package com.jaspersoft.studio.model.dataset;

import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRDatasetRun;
import net.sf.jasperreports.engine.JRElementDataset;
import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.JRGroup;
import net.sf.jasperreports.engine.design.JRDesignDatasetRun;
import net.sf.jasperreports.engine.design.JRDesignElementDataset;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.type.IncrementTypeEnum;
import net.sf.jasperreports.engine.type.ResetTypeEnum;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.views.properties.ComboBoxPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.IContainer;
import com.jaspersoft.studio.model.IContainerEditPart;
import com.jaspersoft.studio.model.IIconDescriptor;
import com.jaspersoft.studio.model.MDatasetRun;
import com.jaspersoft.studio.model.MExpression;
import com.jaspersoft.studio.model.NodeIconDescriptor;
import com.jaspersoft.studio.property.descriptor.JRPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.descriptor.combo.RComboBoxPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.expression.JRExpressionPropertyDescriptor;
import com.jaspersoft.studio.utils.EnumHelper;

public class MElementDataset extends APropertyNode implements IContainer, IContainerEditPart {
	private static IIconDescriptor iconDescriptor;

	/**
	 * Gets the icon descriptor.
	 * 
	 * @return the icon descriptor
	 */
	public static IIconDescriptor getIconDescriptor() {
		if (iconDescriptor == null)
			iconDescriptor = new NodeIconDescriptor("dataset");
		return iconDescriptor;
	}

	public ImageDescriptor getImagePath() {
		return getIconDescriptor().getIcon16();
	}

	public String getDisplayText() {
		return getIconDescriptor().getTitle();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.INode#getToolTip()
	 */
	@Override
	public String getToolTip() {
		return getIconDescriptor().getToolTip();
	}

	public MElementDataset(JRElementDataset value, JasperDesign jasperDesign) {
		super();
		setValue(value);
		this.jasperDesign = jasperDesign;
	}

	public MElementDataset(ANode parent, JRElementDataset value, JasperDesign jasperDesign) {
		super(parent, -1);
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
		ComboBoxPropertyDescriptor resetTypeD = new ComboBoxPropertyDescriptor(JRDesignElementDataset.PROPERTY_RESET_TYPE,
				"Reset Type", EnumHelper.getEnumNames(ResetTypeEnum.values(), NullEnum.NOTNULL));
		resetTypeD.setDescription("Reset type.");
		desc.add(resetTypeD);

		ComboBoxPropertyDescriptor inctypeD = new ComboBoxPropertyDescriptor(
				JRDesignElementDataset.PROPERTY_INCREMENT_TYPE, "Increment Type", EnumHelper.getEnumNames(
						IncrementTypeEnum.values(), NullEnum.NOTNULL));
		inctypeD.setDescription("Increment type.");
		desc.add(inctypeD);

		JRExpressionPropertyDescriptor incWhenExprD = new JRExpressionPropertyDescriptor(
				JRDesignElementDataset.PROPERTY_INCREMENT_WHEN_EXPRESSION, "Increment When Expression");
		incWhenExprD.setDescription("Increment When Expression.");
		desc.add(incWhenExprD);

		resetGroupD = new RComboBoxPropertyDescriptor(JRDesignElementDataset.PROPERTY_RESET_GROUP, "Reset Group",
				new String[] { "" });
		resetGroupD.setDescription("Reset Group.");
		desc.add(resetGroupD);

		incGroupD = new RComboBoxPropertyDescriptor(JRDesignElementDataset.PROPERTY_INCREMENT_GROUP, "Increment Group",
				new String[] { "" });
		incGroupD.setDescription("Increment Group.");
		desc.add(incGroupD);

		JRPropertyDescriptor datasetRunD = new JRPropertyDescriptor(JRDesignElementDataset.PROPERTY_DATASET_RUN,
				"Dataset Run");
		datasetRunD.setDescription("Dataset run.");
		desc.add(datasetRunD);

	}

	public void setGroupItems(String[] items) {
		if (resetGroupD != null)
			resetGroupD.setItems(items);
		if (incGroupD != null)
			incGroupD.setItems(items);
	}

	private MExpression incWhenExp;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#getPropertyValue(java.lang.Object)
	 */
	public Object getPropertyValue(Object id) {
		JRDesignElementDataset jrElement = (JRDesignElementDataset) getValue();
		if (id.equals(JRDesignElementDataset.PROPERTY_RESET_TYPE))
			return EnumHelper.getValue(jrElement.getResetTypeValue(), 1, false);
		if (id.equals(JRDesignElementDataset.PROPERTY_INCREMENT_TYPE))
			return EnumHelper.getValue(jrElement.getIncrementTypeValue(), 1, false);
		if (id.equals(JRDesignElementDataset.PROPERTY_INCREMENT_WHEN_EXPRESSION)) {
			if (incWhenExp == null)
				incWhenExp = new MExpression(jrElement.getIncrementWhenExpression());
			return incWhenExp;
		}
		if (id.equals(JRDesignElementDataset.PROPERTY_RESET_GROUP)) {
			if (jrElement.getResetGroup() != null)
				return jrElement.getResetGroup().getName();
			return "";
		}
		if (id.equals(JRDesignElementDataset.PROPERTY_INCREMENT_GROUP)) {
			if (jrElement.getIncrementGroup() != null)
				return jrElement.getIncrementGroup().getName();
			return "";
		}
		if (id.equals(JRDesignElementDataset.PROPERTY_DATASET_RUN)) {
			if (mDatasetRun == null) {
				JRDatasetRun j = jrElement.getDatasetRun();
				if (j == null)
					j = new JRDesignDatasetRun();
				mDatasetRun = new MDatasetRun(j, getJasperDesign());
			}
			return mDatasetRun;

		}
		return null;
	}

	private MDatasetRun mDatasetRun;
	private RComboBoxPropertyDescriptor incGroupD;
	private RComboBoxPropertyDescriptor resetGroupD;

	private JasperDesign jasperDesign;

	@Override
	public JasperDesign getJasperDesign() {
		return jasperDesign;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#setPropertyValue(java.lang.Object, java.lang.Object)
	 */
	public void setPropertyValue(Object id, Object value) {
		JRDesignElementDataset jrElement = (JRDesignElementDataset) getValue();
		if (id.equals(JRDesignElementDataset.PROPERTY_INCREMENT_TYPE))
			jrElement
					.setIncrementType((IncrementTypeEnum) EnumHelper.getSetValue(IncrementTypeEnum.values(), value, 1, false));
		else if (id.equals(JRDesignElementDataset.PROPERTY_RESET_TYPE))
			jrElement.setResetType((ResetTypeEnum) EnumHelper.getSetValue(ResetTypeEnum.values(), value, 1, false));
		else if (id.equals(JRDesignElementDataset.PROPERTY_INCREMENT_WHEN_EXPRESSION)) {
			if (value instanceof MExpression) {
				incWhenExp = (MExpression) value;
				JRExpression expression = (JRExpression) incWhenExp.getValue();
				jrElement.setIncrementWhenExpression(expression);
			}
		} else if (id.equals(JRDesignElementDataset.PROPERTY_INCREMENT_GROUP)) {
			if (!value.equals("")) {
				JRGroup group = (JRGroup) getJasperDesign().getGroupsMap().get(value);
				jrElement.setIncrementGroup(group);
			}
		} else if (id.equals(JRDesignElementDataset.PROPERTY_RESET_GROUP)) {
			if (!value.equals("")) {
				JRGroup group = (JRGroup) getJasperDesign().getGroupsMap().get(value);
				jrElement.setResetGroup(group);
			}
		}
	}

}
