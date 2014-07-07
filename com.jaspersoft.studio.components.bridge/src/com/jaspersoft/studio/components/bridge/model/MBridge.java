/*******************************************************************************
 * Copyright (C) 2010 - 2014 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *  
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 * 	Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.components.bridge.model;

import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.component.ComponentKey;
import net.sf.jasperreports.engine.design.JRDesignComponentElement;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.design.events.JRChangeEventsSupport;
import net.sf.jasperreports.engine.type.EvaluationTimeEnum;
import net.sf.jasperreports.engine.util.JRCloneUtils;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.jasperreports.components.bridge.BridgeItemData;
import com.jaspersoft.jasperreports.components.bridge.BridgeItemProperty;
import com.jaspersoft.jasperreports.components.bridge.design.BridgeDesignComponent;
import com.jaspersoft.studio.components.bridge.BridgeNodeIconDescriptor;
import com.jaspersoft.studio.components.bridge.messages.Messages;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.IDatasetContainer;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.model.dataset.MDatasetRun;
import com.jaspersoft.studio.model.util.IIconDescriptor;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.descriptor.classname.NClassTypePropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.combo.RComboBoxPropertyDescriptor;
import com.jaspersoft.studio.property.descriptors.JSSEnumPropertyDescriptor;

/**
 * Model object representing the Bridge component element.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public class MBridge extends MGraphicElement implements IDatasetContainer {

	private static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
	private static IIconDescriptor iconDescriptor;
	private IPropertyDescriptor[] descriptors;
	private static Map<String, Object> defaultsMap;
	private RComboBoxPropertyDescriptor evaluationGroupNameD;
	private static JSSEnumPropertyDescriptor evaluationTimeD;

	public MBridge() {
		super();
	}

	public MBridge(ANode parent, JRDesignComponentElement jrObject, int newIndex) {
		super(parent, jrObject, newIndex);
	}

	@Override
	public Map<String, Object> getDefaultsMap() {
		return defaultsMap;
	}

	@Override
	public IPropertyDescriptor[] getDescriptors() {
		return descriptors;
	}

	@Override
	public void setDescriptors(IPropertyDescriptor[] descriptors1,
			Map<String, Object> defaultsMap1) {
		descriptors = descriptors1;
		defaultsMap = defaultsMap1;
	}
	
	@Override
	public List<MDatasetRun> getDatasetRunList() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static IIconDescriptor getIconDescriptor() {
		if (iconDescriptor == null)
			iconDescriptor = new BridgeNodeIconDescriptor("bridge"); //$NON-NLS-1$
		return iconDescriptor;
	}

	@Override
	public String getDisplayText() {
		return getIconDescriptor().getTitle();
	}

	@Override
	public ImageDescriptor getImagePath() {
		return getIconDescriptor().getIcon16();
	}

	@Override
	public String getToolTip() {
		return getIconDescriptor().getToolTip();
	}
	
	@Override
	public JRDesignComponentElement createJRElement(JasperDesign jasperDesign) {
		JRDesignComponentElement el = new JRDesignComponentElement();
		BridgeDesignComponent bridgeComp = new BridgeDesignComponent();
		el.setComponent(bridgeComp);
		el.setComponentKey(new ComponentKey(
				"http://www.jaspersoft.com/bridgecomponent", "bc", "bridge")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return el;
	}
	
	@Override
	public void setValue(Object value) {
		if (getValue() != null) {
			Object obj = getComponent();
			if (obj instanceof JRChangeEventsSupport)
				((JRChangeEventsSupport) obj).getEventSupport()
						.removePropertyChangeListener(this);
		}
		if (value != null) {
			Object obj = getComponent(value);
			if (value instanceof JRChangeEventsSupport)
				((JRChangeEventsSupport) obj).getEventSupport()
						.addPropertyChangeListener(this);
		}
		super.setValue(value);
	}

	private Object getComponent() {
		return getComponent(getValue());
	}

	private Object getComponent(Object value) {
		if (value != null) {
			JRDesignComponentElement jrElement = (JRDesignComponentElement) value;
			return jrElement.getComponent();
		}
		return null;
	}
	
	@Override
	public void createPropertyDescriptors(List<IPropertyDescriptor> desc,
			Map<String, Object> defaultsMap) {
		super.createPropertyDescriptors(desc, defaultsMap);

		evaluationTimeD = new JSSEnumPropertyDescriptor(
				BridgeDesignComponent.PROPERTY_EVALUATION_TIME,
				Messages.MBridge_EvalTime, EvaluationTimeEnum.class,
				NullEnum.NOTNULL);
		evaluationTimeD
				.setDescription(Messages.MBridge_EvalTimeDesc);
		desc.add(evaluationTimeD);

		evaluationGroupNameD = new RComboBoxPropertyDescriptor(
				BridgeDesignComponent.PROPERTY_EVALUATION_GROUP,
				"Evaluation Group", new String[] { "" }); //$NON-NLS-1$ //$NON-NLS-2$
		evaluationGroupNameD
				.setDescription(Messages.MBridge_EvalGroupDesc);
		desc.add(evaluationGroupNameD);

		NClassTypePropertyDescriptor processingClassD = new NClassTypePropertyDescriptor(
				BridgeDesignComponent.PROPERTY_PROCESSING_CLASS, Messages.MBridge_ProcessingClass);
		processingClassD.setDescription(Messages.MBridge_ProcessingClassDesc);
		desc.add(processingClassD);
		
		BridgeItemPropertiesDescriptor bItemPropsD = new BridgeItemPropertiesDescriptor(BridgeDesignComponent.PROPERTY_ITEM_PROPERTIES, Messages.MBridge_ItemProperties);
		bItemPropsD.setDescription(Messages.MBridge_ItemPropertiesDesc);
		desc.add(bItemPropsD);

		BridgeItemDataDescriptor bItemDataD = new BridgeItemDataDescriptor(BridgeDesignComponent.PROPERTY_ITEM_DATA, Messages.MBridge_ItemData);
		bItemDataD.setDescription(Messages.MBridge_ItemDataDesc);
		desc.add(bItemDataD);
		
		evaluationTimeD.setCategory(Messages.MBridge_BridgePropertiesCategory);
		evaluationGroupNameD.setCategory(Messages.MBridge_BridgePropertiesCategory);
		processingClassD.setCategory(Messages.MBridge_BridgePropertiesCategory);
		bItemPropsD.setCategory(Messages.MBridge_BridgePropertiesCategory);
		bItemDataD.setCategory(Messages.MBridge_BridgePropertiesCategory);
		
		defaultsMap.put(BridgeDesignComponent.PROPERTY_EVALUATION_TIME,
				evaluationTimeD.getEnumValue(EvaluationTimeEnum.NOW));
	}

	@Override
	protected void setGroupItems(String[] items) {
		super.setGroupItems(items);
		if (evaluationGroupNameD != null)
			evaluationGroupNameD.setItems(items);
	}
	
	@Override
	public Object getPropertyValue(Object id) {
		JRDesignComponentElement jrElement = (JRDesignComponentElement) getValue();
		BridgeDesignComponent bridgeComp = (BridgeDesignComponent) jrElement.getComponent();
		if (BridgeDesignComponent.PROPERTY_EVALUATION_TIME.equals(id)) {
			return evaluationTimeD.getEnumValue(bridgeComp.getEvaluationTime());
		}
		else if (BridgeDesignComponent.PROPERTY_EVALUATION_GROUP.equals(id)) {
			return bridgeComp.getEvaluationGroup();			
		}
		else if (BridgeDesignComponent.PROPERTY_PROCESSING_CLASS.equals(id)) {
			return bridgeComp.getProcessingClass();
		}
		else if (BridgeDesignComponent.PROPERTY_ITEM_PROPERTIES.equals(id)){
			return JRCloneUtils.cloneList(bridgeComp.getItemProperties());
		}
		else if (BridgeDesignComponent.PROPERTY_ITEM_DATA.equals(id)) {
			return JRCloneUtils.cloneList(bridgeComp.getItemData());
		}
		else {
			return super.getPropertyValue(id);
		}
	}

	@Override
	public void setPropertyValue(Object id, Object value) {
		JRDesignComponentElement jrElement = (JRDesignComponentElement) getValue();
		BridgeDesignComponent bridgeComp = (BridgeDesignComponent) jrElement.getComponent();
		if (BridgeDesignComponent.PROPERTY_EVALUATION_TIME.equals(id)) {
			bridgeComp.setEvaluationTime((EvaluationTimeEnum) evaluationTimeD
					.getEnumValue(value));
		}
		else if (BridgeDesignComponent.PROPERTY_EVALUATION_GROUP.equals(id)) {
			bridgeComp.setEvaluationGroup((String) value);
		}
		else if (BridgeDesignComponent.PROPERTY_PROCESSING_CLASS.equals(id)) {
			if (value instanceof String && ((String) value).trim().isEmpty())
				value = null;
			bridgeComp.setProcessingClass((String) value);
		}
		else if (BridgeDesignComponent.PROPERTY_ITEM_PROPERTIES.equals(id)) {
			BridgeItemProperty[] toRemove = bridgeComp.getItemProperties().toArray(new BridgeItemProperty[]{});
			for (BridgeItemProperty i : toRemove) {
				bridgeComp.removeItemProperty(i);
			}
			for (BridgeItemProperty i : (List<BridgeItemProperty>) value) {
				bridgeComp.addItemProperty(i);
			}
		}
		else if (BridgeDesignComponent.PROPERTY_ITEM_DATA.equals(id)) {
			BridgeItemData[] toRemove = bridgeComp.getItemData().toArray(new BridgeItemData[]{});
			for (BridgeItemData i : toRemove) {
				bridgeComp.removeItemData(i);
			}
			for (BridgeItemData i : (List<BridgeItemData>)value){
				bridgeComp.addItemData(i);
			}
		}
		else {
			super.setPropertyValue(id, value);
		}
	}
	
}
