/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.chart.property.widget;

import java.util.Arrays;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.components.chart.util.ChartHelper;
import com.jaspersoft.studio.help.HelpSystem;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.descriptor.combo.RWComboBoxCellEditor;
import com.jaspersoft.studio.property.descriptor.combo.RWComboBoxPropertyDescriptor;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.property.section.widgets.ASPropertyWidget;
import com.jaspersoft.studio.property.section.widgets.SPRWCombo;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

/**
 * Custom widget used for the chart themes, initlialize the combo with the extension value
 * on the setData of the widget, this allow to show always the correct value
 */
public class ChartThemeComboBoxPropertyDescriptor extends RWComboBoxPropertyDescriptor {

	public ChartThemeComboBoxPropertyDescriptor(Object id, String displayName, NullEnum canBeNull) {
		super(id, displayName, new String[0], canBeNull);
	}
	
	/**
	 * Only store the items in the class, dosen't update the graphical widget.
	 * The widget is updated only when the setdata is called
	 */
	@Override
	public void setItems(String[] items) {
		labels = items;
	}
	
	protected String[] getThemeItems(ANode element){
		JasperReportsConfiguration jConfig = element.getJasperConfiguration();
		return ChartHelper.getChartThemesNull(jConfig);
	}
	
	public ASPropertyWidget<RWComboBoxPropertyDescriptor> createWidget(Composite parent, AbstractSection section) {
		combo = new SPRWCombo<RWComboBoxPropertyDescriptor>(parent, section, this) {
	
			/**
			 * Calculate the available themes for the selected element and save it in the descriptor
			 */
			private void initializeItems(){
				// initialize style
				ANode element = section.getElement();
				String[] newitems = getThemeItems(element);
				if (newitems != null){
					((RWComboBoxPropertyDescriptor)pDescriptor).setItems(newitems);
				}
			}
			
			/**
			 * Get the element from the descriptor and the set them on the combo, but only if 
			 * the combo is available and the current items are different from the new ones
			 */
			private void setComboItems() {
				initializeItems();
				String[] labels = ((RWComboBoxPropertyDescriptor)pDescriptor).getItems();
				if (combo != null && !combo.isDisposed() && !Arrays.equals(labels, combo.getItems())){
					combo.setItems(labels);
				}
				//Avoid to set the items if they are already the same
				if (cellEditor != null && !cellEditor.getComboBox().isDisposed() && !Arrays.equals(labels, cellEditor.getItems())){
					cellEditor.setItems(labels);
				}
			}
			
			public void setData(APropertyNode pnode, Object b) {
				refresh = true;
				setComboItems();
				refresh = false;
				super.setData(pnode, b);
			}
			
		};
		return combo;
	}
	
	@Override
	public CellEditor createPropertyEditor(Composite parent) {
		cellEditor = new RWComboBoxCellEditor(parent, labels){
			
			/**
			 * Refresh the cell editor by set the updated list of styles
			 */
			@Override
			public void refresh(ANode selectedModel) {
				super.refresh(selectedModel);
				String[] newitems = getThemeItems(selectedModel);
				if (newitems != null){
					//Avoid to set the items if they are already the same
					if (!getComboBox().isDisposed() && !Arrays.equals(newitems, getItems())){
						setItems(newitems);
					}
				}
			}
			
		};
		if (getValidator() != null)
			cellEditor.setValidator(getValidator());
		HelpSystem.bindToHelp(this, cellEditor.getControl());
		return cellEditor;
	}

}
