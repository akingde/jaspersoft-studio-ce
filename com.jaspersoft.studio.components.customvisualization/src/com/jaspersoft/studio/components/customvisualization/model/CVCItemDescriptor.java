/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.customvisualization.model;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.graphics.Image;

import com.jaspersoft.studio.components.customvisualization.CustomVisualizationActivator;
import com.jaspersoft.studio.components.customvisualization.ui.UIManager;
import com.jaspersoft.studio.components.customvisualization.ui.framework.CVCWidgetsDescriptor;
import com.jaspersoft.studio.components.customvisualization.ui.framework.DatasetPropertyDescriptor;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.util.ItemPropertyUtil;
import com.jaspersoft.studio.property.itemproperty.desc.ADescriptor;
import com.jaspersoft.studio.utils.ExpressionInterpreter;
import com.jaspersoft.studio.utils.ExpressionUtil;
import com.jaspersoft.studio.utils.ModelUtils;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;
import com.jaspersoft.studio.widgets.framework.IPropertyEditor;
import com.jaspersoft.studio.widgets.framework.PropertyEditorAdapter;
import com.jaspersoft.studio.widgets.framework.manager.WidgetFactory;
import com.jaspersoft.studio.widgets.framework.model.SectionPropertyDescriptor;
import com.jaspersoft.studio.widgets.framework.model.WidgetPropertyDescriptor;
import com.jaspersoft.studio.widgets.framework.ui.ItemPropertyDescription;

import net.sf.jasperreports.components.items.Item;
import net.sf.jasperreports.components.items.ItemData;
import net.sf.jasperreports.components.items.ItemProperty;
import net.sf.jasperreports.components.items.StandardItem;
import net.sf.jasperreports.components.items.StandardItemProperty;
import net.sf.jasperreports.customvisualization.design.CVDesignComponent;
import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JasperDesign;

/**
 * @author Veaceslav Chicu (schicu@users.sourceforge.net)
 * 
 */
public class CVCItemDescriptor extends ADescriptor {
	
	private IPropertyEditor standardItemEditor = new PropertyEditorAdapter() {
		
		@Override
		public JRExpression getPropertyValueExpression(String propertyName) {

			for(ItemProperty prop : item.getProperties()){
				if (prop.getName().equals(propertyName)){
					StandardItemProperty stdProp = (StandardItemProperty)prop;
					return stdProp.getValueExpression();
				}
			}
			return null;
		}
		
		@Override
		public String getPropertyValue(String propertyName) {

			for(ItemProperty prop : item.getProperties()){
				if (prop.getName().equals(propertyName)){
					StandardItemProperty stdProp = (StandardItemProperty)prop;
					return stdProp.getValue();
				}
			}
			return null;
		}
		
		@Override
		public void createUpdateProperty(String propertyName, String value, JRExpression valueExpression) {
			boolean found = false;
			
			for(ItemProperty prop : item.getProperties()){
				if (prop.getName().equals(propertyName)){
					StandardItemProperty stdProp = (StandardItemProperty)prop;
					stdProp.setValue(value);
					stdProp.setValueExpression(valueExpression);
					found = true; 
					break;
				}
			}
			if (!found){
				((StandardItem)item).addItemProperty(new StandardItemProperty(propertyName, value, valueExpression));
			}
		}
		
		@Override
		public void removeProperty(String propertyName) {
			for(ItemProperty prop : item.getProperties()){
				if (prop.getName().equals(propertyName)){
					((StandardItem)item).removeItemProperty(prop);
					break;
				}
			}
		}
	};

	public CVCItemDescriptor() {
		super();
		showAllProperties = true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.property.itemproperty.desc.ADescriptor#
	 * getDisplayName ()
	 */
	@Override
	public String getDisplayName() {
		return "Properties";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jaspersoft.studio.property.itemproperty.desc.ADescriptor#getIcon(
	 * java.lang.Object)
	 */
	@Override
	public Image getIcon(Object element) {
		if (element instanceof Item)
			return CustomVisualizationActivator.getDefault().getImage("icons/path-icon-16.png"); //$NON-NLS-1$
		return super.getIcon(element);
	}

	@Override
	public void setItemDatas(List<ItemData> itemDatas, APropertyNode pnode) {
		this.itemDatas = itemDatas;
		if (this.pnode != pnode) {
			this.pnode = pnode;
			initItemPropertyDescriptors();
		} else
			this.pnode = pnode;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.tibcomaps.property.desc.AItemDescriptor#
	 * initItemPropertyDescriptors()
	 */
	@Override
	protected void initItemPropertyDescriptors() {
		if (pnode != null) {
			// let's look if we have some files with our properties
			CVCProprtiesExpressionDTO cvcDto = (CVCProprtiesExpressionDTO) pnode.getPropertyValue(CVDesignComponent.PROPERTY_ITEM_PROPERTIES);
			List<ItemProperty> p = cvcDto.getItemProps();
			if (!Misc.isNullOrEmpty(p)) {
				// let's get our description
				// let's get our description
				JasperDesign jd = pnode.getJasperDesign();
				JasperReportsConfiguration jConf = pnode.getJasperConfiguration();
				JRDesignDataset dataset = null;
				if (dataset == null)
					dataset = ModelUtils.getDataset(pnode);
				if (dataset == null)
					dataset = (JRDesignDataset) jd.getMainDataset();

				ExpressionInterpreter expIntr = ExpressionUtil.getCachedInterpreter(dataset, jd, jConf);
				List<ItemPropertyDescription<?>> props = new ArrayList<ItemPropertyDescription<?>>();
				for (ItemProperty ip : p)
					if (ip.getName().equals("module")) {
						String module = ItemPropertyUtil.getItemPropertyString((StandardItemProperty) ip, expIntr);
						if (!Misc.isNullOrEmpty(module)) {
							CVCWidgetsDescriptor cd = UIManager.getDescriptor(jConf, module);
							if (cd != null && !Misc.isNullOrEmpty(cd.getDatasets())) {
								for (DatasetPropertyDescriptor cdd : cd.getDatasets()) {
									if (cdd.getSections() != null)
										for (SectionPropertyDescriptor csd : cdd.getSections()){
											for (WidgetPropertyDescriptor cpd : csd.getProperties()){
												ItemPropertyDescription<?> itemDesc = WidgetFactory.createItemPropertyDescriptor(cd, cpd, jConf);
												if (itemDesc != null) {
													props.add(itemDesc);
												}
											}
										}

								}
								itemProperties = props.toArray(new ItemPropertyDescription[props.size()]);
								return;
							}
						}
					}
			}

		}
		itemProperties = new ItemPropertyDescription[] {};
	}

	@Override
	public IPropertyEditor getPropertyEditor() {
		return standardItemEditor;
	}
}
