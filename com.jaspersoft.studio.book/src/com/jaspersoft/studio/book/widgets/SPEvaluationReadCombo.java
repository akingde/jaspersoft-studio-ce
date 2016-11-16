/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.book.widgets;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.JRGroup;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.part.StandardPartEvaluationTime;
import net.sf.jasperreports.engine.type.PartEvaluationTimeType;

import org.apache.commons.lang.ArrayUtils;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.book.model.MReportPart;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.property.section.widgets.SPReadCombo;

public class SPEvaluationReadCombo extends SPReadCombo {
	
	private static final List<String> noGroupItems ;
	
	static{
		noGroupItems = new ArrayList<String>();
		for(PartEvaluationTimeType type : PartEvaluationTimeType.values()){
			if (!PartEvaluationTimeType.GROUP.equals(type)) noGroupItems.add(type.getName());
		}
	}
	
	public SPEvaluationReadCombo(Composite parent, AbstractSection section, IPropertyDescriptor pDescriptor) {
		super(parent, section, pDescriptor);
	}
	
	private void updateItems(APropertyNode node){
		if (node != null){
			JasperDesign jd = node.getJasperDesign();
			List<String> groupsName = new ArrayList<String>();
			if (jd != null) {
				JRDataset dataset = jd.getMainDataset();
				//Calculate the groups list for the current element
				if (dataset != null){
					JRGroup[] groups = dataset.getGroups();
					for (JRGroup group : groups) {
						groupsName.add(group.getName());
					}
				}
			}
			List<String> fixedEvaluationTime = new ArrayList<String>(noGroupItems);
			fixedEvaluationTime.addAll(groupsName);
			fixedEvaluationTime.add(0, "Default (Now)");//Add the empty element
			combo.setItems(fixedEvaluationTime.toArray(new String[fixedEvaluationTime.size()]));
			combo.pack(true);
		}
	}

	public void setData(APropertyNode pnode, Object b) {
		setRefreshing(true);
		updateItems(pnode);
		if (b == null){
			combo.select(0); //select the default item //combo.getItems().length-1
		} else if (b instanceof PartEvaluationTimeType){
			PartEvaluationTimeType evaluation = (PartEvaluationTimeType) b;
			if (evaluation.equals(PartEvaluationTimeType.GROUP)){
				String evaluationGroup = (String)pnode.getPropertyValue(MReportPart.PROPERTY_EVALTIME_GROUP);
				int index = ArrayUtils.indexOf(combo.getItems(), evaluationGroup);
				combo.select(index);
			} else {
				int index = ArrayUtils.indexOf(combo.getItems(), evaluation.getName());
				combo.select(index);
			}
		} else {
			setRefreshing(false);
			super.setData(pnode, b);
		}
		setRefreshing(false);
	}
	
	protected void handlePropertyChange() {
		if (!isRefreshing()){
			Object value = null;
			int selectedIndex = combo.getSelectionIndex();
			
			String[] values = combo.getItems();
			String selection = values[selectedIndex];
			
			if (selectedIndex == 0 || selection.isEmpty()){
				value = null;
			} else if (noGroupItems.contains(selection)){
				value  = StandardPartEvaluationTime.forType(selection);
			} else {
				value = StandardPartEvaluationTime.forGroup(selection);
			}
			section.changeProperty(pDescriptor.getId(), value);
		}
	}
}
