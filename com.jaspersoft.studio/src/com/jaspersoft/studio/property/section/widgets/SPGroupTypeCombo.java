/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.section.widgets;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.messages.MessagesByKeys;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.property.descriptor.combo.RComboBoxPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.combo.RWComboBoxPropertyDescriptor;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.utils.ModelUtils;

import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.eclipse.util.Pair;
import net.sf.jasperreports.engine.type.JREnum;

/**
 * Can offer a generic combo to select an evaluation, reset, increment time for a components handling the presence of
 * groups. Can also provide an error message when a previously selected group is not found. The list of groups is read
 * directly by the property descriptor. Internally it used both list inside the combo. The first is an array of string that
 * are the item shown at UI level inside the combo. The second is a list of entry stored in the data property of the combo.
 * This list mirror the label one, has the same number of element and to each element into a specific index of the label list
 * there is its real value in the same index of the data list
 * 
 * @author Orlandin Marco
 * 
 */
public abstract class SPGroupTypeCombo<T extends IPropertyDescriptor> extends ASPropertyWidget<T> {

	/**
	 * Class used in the data list, keep both the enumeration value and
	 * the group name. The group name is used if the enumeration is a group one.
	 * The group can be set to null if the enumeration is not a group enum
	 * 
	 * @author Orlandin Marco
	 *
	 */
	protected class DataEntry{
		
		private JREnum enumValue;
		
		private String groupName;
		
		public DataEntry(JREnum value, String groupName){
			this.enumValue = value;
			this.groupName = groupName;
		}

		public DataEntry(JREnum value){
			this(value, null);
		}
	}
	
	/**
	 * Prefix string for the group elements
	 */
	protected static final String GROUPPREFIX = "[Group] "; //$NON-NLS-1$

	/**
	 * Combo where the elements are shown
	 */
	protected Combo combo;

	/**
	 * Boolean flag to know if the first element is a missing group error message
	 */
	protected boolean hasFirstFakeElement = false;

	/**
	 * The property descriptor of the group
	 */
	protected IPropertyDescriptor gDescriptor;

	public SPGroupTypeCombo(Composite parent, AbstractSection section, T pDescriptor,
			IPropertyDescriptor gDescriptor) {
		super(parent, section, pDescriptor);
		this.gDescriptor = gDescriptor;
	}

	@Override
	public Control getControl() {
		return combo;
	}

	public void createComponent(Composite parent) {
		combo = section.getWidgetFactory().createCombo(parent, SWT.READ_ONLY);
		combo.addSelectionListener(new SelectionListener() {

			@SuppressWarnings("unchecked")
			public void widgetSelected(SelectionEvent e) {
				// If the selected entry is the first and it is fake then don't do anything
				if (hasFirstFakeElement && combo.getSelectionIndex() == 0)
					return;
				String group = null;
				List<DataEntry> dataEntries = (List<DataEntry>)combo.getData();
				DataEntry comboVal = dataEntries.get(combo.getSelectionIndex());
				boolean isGroup = false;
				if (comboVal.enumValue == getGroupEnum()) {
					isGroup = true;
					group = comboVal.groupName;
				}
				// It is important to set first the group because the group changing dosen't trigger an event
				// so otherwise setting the type first trigger the event but the group has not been set to the
				// setData method dosen't find the group and set always the element 0.
				section.changeProperty(gDescriptor.getId(), Misc.nvl(group));
				if(isGroup) {
					section.changeProperty(pDescriptor.getId(), getGroupEnum());
				} else {
					section.changeProperty(pDescriptor.getId(), comboVal.enumValue);
				}
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});
		combo.setToolTipText(pDescriptor.getDescription());
	}

	@Override
	public void setData(APropertyNode pnode, Object value) {
		createContextualMenu(pnode);
		setData((JREnum) pnode.getPropertyValue(pDescriptor.getId()),
				(String) pnode.getPropertyValue(gDescriptor.getId()));
	}

	/**
	 * Check if two group are equivalents. They are equivalents if they 
	 * are both empty (a group is empty when the name is null or the empty string)
	 * or if they have the same name
	 * 
	 * @param group1 the group1
	 * @param group2 the group2
	 * @return true if the groups are equivalent, false otherwise
	 */
	protected boolean groupEquivalents(String group1, String group2){
		boolean emptyGroup1 = group1 == null || group1.trim().isEmpty();
		boolean emptyGroup2 = group2 == null || group2.trim().isEmpty();
		if (emptyGroup1 || emptyGroup2) return (emptyGroup1 && emptyGroup2);
		else {
			//the group are both not empty, check if they are the same one
			return ModelUtils.safeEquals(group1, group2);
		}
	}
	
	protected void setData(JREnum jrEnum, String group) {
		hasFirstFakeElement = false;
		int selection = 0;
		boolean found = false;
		Pair<String[], List<DataEntry>> entries = getItems();
		String[] comboEntries = entries.getKey();
		List<DataEntry> dataEntries = entries.getValue();
		
		if (jrEnum != null){
			int index = 0;
			for (DataEntry entry : dataEntries) {
				if (entry.enumValue == jrEnum && groupEquivalents(entry.groupName, group)){
					selection = index;
					found = true;
					break;
				}
				index++;
			}
		}
		String[] comboItems = comboEntries;
		// If the group can't be found set a fake item
		if (!found && group != null && !group.isEmpty()) {
			List<String> newItems = new ArrayList<String>();
			newItems.add(MessageFormat.format(Messages.SPGroupTypeCombo_groupNotFounError, group));
			newItems.addAll(new ArrayList<String>(Arrays.asList(comboEntries)));
			comboItems = newItems.toArray(new String[newItems.size()]);
			//since we create a fake entry on the string items list lets add it also on the value list
			dataEntries.add(0, null);
			hasFirstFakeElement = true;
		}
		combo.setItems(comboItems);
		combo.setData(dataEntries);
		combo.select(selection);
		combo.getParent().layout();
	}

	/**
	 * Return the items selectable on the combo. Essentially they are the elements in the enumeration. but if in the
	 * enumeration is there a value for the group elements then also the available groups will be added to the items. The
	 * available groups are read from the property descriptor
	 * 
	 * @return
	 */
	protected Pair<String[], List<DataEntry>> getItems() {
		List<String> lsIncs = new ArrayList<String>();
		//entryList will mime the itemlist, having in the same position of each string the related value
		List<DataEntry> dataEntries = new ArrayList<DataEntry>();
		for (JREnum en : getEnumValues()) {
			if (en.equals(getGroupEnum())) {
				if (gDescriptor instanceof RWComboBoxPropertyDescriptor) {
					String[] groupItems = ((RWComboBoxPropertyDescriptor) gDescriptor).getItems();
					for (String gr : groupItems) {
						if(gr!=null && !gr.trim().isEmpty()) {
							lsIncs.add(GROUPPREFIX + gr);
							dataEntries.add(new DataEntry(en, gr));
						}
					}
				} else if (gDescriptor instanceof RComboBoxPropertyDescriptor) {
					String[] groupItems = ((RComboBoxPropertyDescriptor) gDescriptor).getItems();
					for (String gr : groupItems) {
						if(gr!=null && !gr.trim().isEmpty()) { 
							lsIncs.add(GROUPPREFIX + gr);
							dataEntries.add(new DataEntry(en, gr));
						}
					}
				}
			} else {
				lsIncs.add(MessagesByKeys.getString(en.getName()));
				dataEntries.add(new DataEntry(en));
			}
		}
		String[] comboEntries = lsIncs.toArray(new String[lsIncs.size()]);
		return new Pair<String[], List<DataEntry>>(comboEntries, dataEntries);
	}

	/**
	 * Return the enumerations selectable on this element
	 * 
	 * @return the list of enumerations that can be selected
	 */
	protected abstract JREnum[] getEnumValues();

	/**
	 * Return the group enumeration for the element
	 * 
	 * @return the enumeration corresponding to group
	 */
	protected abstract JREnum getGroupEnum();

	/**
	 * Return the enumeration value starting from it's name
	 * 
	 * @param name
	 *          string representation of the enumeration value
	 * @return the enumeration value
	 */
	protected abstract JREnum getByName(String name);

}
