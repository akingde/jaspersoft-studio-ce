/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.section.widgets;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.charts.type.EdgeEnum;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.wb.swt.ResourceManager;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.property.combomenu.ComboItem;
import com.jaspersoft.studio.property.combomenu.ComboItemAction;
import com.jaspersoft.studio.property.combomenu.ComboMenuViewer;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.descriptors.NamedEnumPropertyDescriptor;
import com.jaspersoft.studio.property.section.AbstractSection;

/**
 * This class define a position selector as a popoup combo menu
 * 
 * @author Orlandin Marco
 *
 */
public class SPLegendAlignementEnum<T extends NamedEnumPropertyDescriptor<EdgeEnum>> extends ASPropertyWidget<T> {

	/**
	 * The combo popup
	 */
	ComboMenuViewer combo;

	public SPLegendAlignementEnum(Composite parent, AbstractSection section, T pDescriptor) {
		super(parent, section, pDescriptor);
		createComponent(parent, section, pDescriptor.getId().toString());
		combo.setToolTipText(pDescriptor.getDescription());
	}

	/**
	 * Return the selected value into the combo popup
	 * 
	 * @return In this case it's an integer representing the index of the selected item
	 */
	public Object getSelectedValue() {
		return combo.getSelectionValue();
	}

	/**
	 * Create the component and initialize the combo popup with the necessary value
	 * 
	 * @param parent
	 *          composite where the combo popup is palced
	 * @param section
	 *          section that contains the property that this combo will change when an entry is selected
	 * @param property
	 *          id of the property that this combo will change when an entry is selected
	 */
	public void createComponent(Composite parent, final AbstractSection section, final String property) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setBackground(parent.getBackground());
		RowLayout layout = new RowLayout();
		layout.marginLeft = 0;
		composite.setLayout(layout);
		// Creating the list of entry
		List<ComboItem> itemsList = new ArrayList<ComboItem>();
		itemsList.add(new ComboItem(Messages.SPLegendAlignementEnum_default, true, ResourceManager.getImage(
				this.getClass(), "/icons/resources/blank-none.png"), 0, NullEnum.UNDEFINED, null)); //$NON-NLS-2$
		itemsList.add(new ComboItem(Messages.SPLegendAlignementEnum_top, true, ResourceManager.getImage(this.getClass(),
				"/icons/resources/eclipse/align-edge-top.gif"), 1, EdgeEnum.TOP, pDescriptor.getIntValue(EdgeEnum.TOP))); //$NON-NLS-2$
		itemsList
				.add(new ComboItem(
						Messages.SPLegendAlignementEnum_bottom,
						true,
						ResourceManager.getImage(this.getClass(), "/icons/resources/eclipse/align-edge-bottom.gif"), 2, EdgeEnum.BOTTOM, pDescriptor.getIntValue(EdgeEnum.BOTTOM))); //$NON-NLS-2$
		itemsList.add(new ComboItem(Messages.SPLegendAlignementEnum_left, true, ResourceManager.getImage(this.getClass(),
				"/icons/resources/eclipse/align-edge-left.gif"), 3, EdgeEnum.LEFT, pDescriptor.getIntValue(EdgeEnum.LEFT))); //$NON-NLS-2$
		itemsList.add(new ComboItem(Messages.SPLegendAlignementEnum_right, true, ResourceManager.getImage(this.getClass(),
				"/icons/resources/eclipse/align-edge-right.gif"), 4, EdgeEnum.RIGHT, pDescriptor.getIntValue(EdgeEnum.RIGHT))); //$NON-NLS-2$
		// Creating the combo popup
		combo = new ComboMenuViewer(composite, SWT.NORMAL, SPRWPopUpCombo.getLongest(itemsList));
		combo.setItems(itemsList);
		combo.addSelectionListener(new ComboItemAction() {
			/**
			 * The action to execute when an entry is selected
			 */
			@Override
			public void exec() {
				propertyChange(section, property, combo.getSelectionValue() != null ? (Integer) combo.getSelectionValue()
						: null);
			}
		});
	}

	public void propertyChange(AbstractSection section, String property, Integer value) {
		section.changeProperty(property, value);
	}

	@Override
	protected void createComponent(Composite parent) {
	}

	@Override
	public void setData(APropertyNode pnode, Object value) {
		combo.select(value != null ? (Integer) value : 0);
	}

	@Override
	public Control getControl() {
		return combo != null ? combo.getControl() : null;
	}

}
