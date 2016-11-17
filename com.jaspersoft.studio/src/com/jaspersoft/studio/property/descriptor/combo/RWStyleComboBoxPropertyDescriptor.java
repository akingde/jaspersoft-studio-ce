/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.descriptor.combo;

import java.util.Arrays;

import net.sf.jasperreports.engine.base.JRBaseStyle;
import net.sf.jasperreports.engine.design.JRDesignElement;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.help.HelpSystem;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.style.MConditionalStyle;
import com.jaspersoft.studio.model.style.StyleTemplateFactory;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.property.section.widgets.ASPropertyWidget;
import com.jaspersoft.studio.property.section.widgets.SPRWCombo;

/**
 * Property descriptor used to select the parent style for an element or another 
 * style. This descriptor do the initialization of the available styles before to
 * set the data. Doing this the informations are more updated and the initialization is
 * done only once (before it was done in the post descriptor method and in that case
 * it was done many times for each selection).
 * 
 * @author Orlandin Marco
 *
 */
public class RWStyleComboBoxPropertyDescriptor extends RWComboBoxPropertyDescriptor {

	public RWStyleComboBoxPropertyDescriptor(Object id, String displayName, String[] labelsArray, NullEnum canBeNull) {
		super(id, displayName, labelsArray, canBeNull);
	}

	/**
	 * Only store the items in the class, dosen't update the graphical widget.
	 * The widget is updated only when the setdata is called
	 */
	@Override
	public void setItems(String[] items) {
		labels = items;
	}
	
	protected String[] getStyleItems(ANode element){
		if (element instanceof MConditionalStyle) element = element.getParent();
		if (element != null && element.getJasperDesign() != null){
			if (element.getValue() != null) {
				String[] newitems = {};
				if (element.getValue() instanceof JRBaseStyle) {
				 newitems = StyleTemplateFactory.getAllStyles(element.getJasperConfiguration(), (JRBaseStyle)element.getValue());
				} else if (element.getValue() instanceof JRDesignElement){
					newitems = StyleTemplateFactory.getAllStyles(element.getJasperConfiguration(), (JRDesignElement)element.getValue());
				} else {
					newitems = StyleTemplateFactory.getAllStyles(element.getJasperConfiguration(), (JRDesignElement)null);
				}
				return newitems;
			}
		}
		return null;
	}
	
	/**
	 * Create a modified version of the combo widget
	 */
	public ASPropertyWidget<RWComboBoxPropertyDescriptor> createWidget(Composite parent, AbstractSection section) {
		combo = new SPRWCombo<RWComboBoxPropertyDescriptor>(parent, section, this){
			
			/**
			 * Calculate the available styles for the selected element and save it in the descriptor
			 */
			private void initializeItems(){
				// initialize style
				ANode element = section.getElement();
				String[] newitems = getStyleItems(element);
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
				String[] newitems = getStyleItems(selectedModel);
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
