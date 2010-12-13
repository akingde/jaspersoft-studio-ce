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
			iconDescriptor = new NodeIconDescriptor("dataset"); //$NON-NLS-1$
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
				Messages.MElementDataset_reset_type, EnumHelper.getEnumNames(ResetTypeEnum.values(), NullEnum.NOTNULL));
		resetTypeD.setDescription(Messages.MElementDataset_reset_type_description);
		desc.add(resetTypeD);

		ComboBoxPropertyDescriptor inctypeD = new ComboBoxPropertyDescriptor(
				JRDesignElementDataset.PROPERTY_INCREMENT_TYPE, Messages.MElementDataset_increment_type, EnumHelper.getEnumNames(
						IncrementTypeEnum.values(), NullEnum.NOTNULL));
		inctypeD.setDescription(Messages.MElementDataset_increment_type_description);
		desc.add(inctypeD);

		JRExpressionPropertyDescriptor incWhenExprD = new JRExpressionPropertyDescriptor(
				JRDesignElementDataset.PROPERTY_INCREMENT_WHEN_EXPRESSION, Messages.MElementDataset_increment_when_expression);
		incWhenExprD.setDescription(Messages.MElementDataset_increment_when_expression_description);
		desc.add(incWhenExprD);

		resetGroupD = new RComboBoxPropertyDescriptor(JRDesignElementDataset.PROPERTY_RESET_GROUP, Messages.MElementDataset_reset_group,
				new String[] { "" }); //$NON-NLS-1$
		resetGroupD.setDescription(Messages.MElementDataset_reset_group_description);
		desc.add(resetGroupD);

		incGroupD = new RComboBoxPropertyDescriptor(JRDesignElementDataset.PROPERTY_INCREMENT_GROUP, Messages.MElementDataset_increment_group,
				new String[] { "" }); //$NON-NLS-1$
		incGroupD.setDescription(Messages.MElementDataset_increment_group_description);
		desc.add(incGroupD);

		JRPropertyDescriptor datasetRunD = new JRPropertyDescriptor(JRDesignElementDataset.PROPERTY_DATASET_RUN,
				Messages.MElementDataset_dataset_run);
		datasetRunD.setDescription(Messages.MElementDataset_dataset_run_description);
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
			return ""; //$NON-NLS-1$
		}
		if (id.equals(JRDesignElementDataset.PROPERTY_INCREMENT_GROUP)) {
			if (jrElement.getIncrementGroup() != null)
				return jrElement.getIncrementGroup().getName();
			return ""; //$NON-NLS-1$
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
			if (!value.equals("")) { //$NON-NLS-1$
				JRGroup group = (JRGroup) getJasperDesign().getGroupsMap().get(value);
				jrElement.setIncrementGroup(group);
			}
		} else if (id.equals(JRDesignElementDataset.PROPERTY_RESET_GROUP)) {
			if (!value.equals("")) { //$NON-NLS-1$
				JRGroup group = (JRGroup) getJasperDesign().getGroupsMap().get(value);
				jrElement.setResetGroup(group);
			}
		}
	}

}
