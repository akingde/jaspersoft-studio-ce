/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.section.widgets;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.base.JRBasePen;
import net.sf.jasperreports.engine.type.LineStyleEnum;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.wb.swt.ResourceManager;

import com.jaspersoft.studio.property.combomenu.ComboItem;
import com.jaspersoft.studio.property.combomenu.ComboItemAction;
import com.jaspersoft.studio.property.combomenu.ComboMenuViewer;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.section.AbstractSection;

/**
 * Reperesent the lineStyle of an element as a combo popup element.
 * 
 * @author Orlandin Marco
 *
 */
public class SPLineStyleEnum {

	/**
	 * The combo popup
	 */
	private ComboMenuViewer combo;

	/**
	 * The list of element in the combo
	 */
	private List<ComboItem> itemsList;

	public SPLineStyleEnum(Composite parent, AbstractSection section, String property) {
		createComponent(parent, section, property);
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
		RowLayout layout = new RowLayout();
		layout.marginLeft = 0;
		composite.setLayout(layout);
		// Creating the list of entry
		itemsList = new ArrayList<ComboItem>();
		// A blank space is added at the end of the string to compensate the size enlargement because a selected element is
		// in bold
		itemsList.add(new ComboItem("Inherited ", true,
				ResourceManager.getImage(this.getClass(), "/icons/resources/inherited.png"), 0, NullEnum.INHERITED, null));
		itemsList.add(
				new ComboItem("Solid line ", true, ResourceManager.getImage(this.getClass(), "/icons/resources/line-solid.png"),
						1, LineStyleEnum.SOLID, LineStyleEnum.SOLID));
		itemsList.add(new ComboItem("Dashed line ", true,
				ResourceManager.getImage(this.getClass(), "/icons/resources/line-dashed.png"), 2, LineStyleEnum.DASHED,
				LineStyleEnum.DASHED));
		itemsList.add(new ComboItem("Dotted line ", true,
				ResourceManager.getImage(this.getClass(), "/icons/resources/line-dotted.png"), 3, LineStyleEnum.DOTTED,
				LineStyleEnum.DOTTED));
		itemsList.add(new ComboItem("Double line ", true,
				ResourceManager.getImage(this.getClass(), "/icons/resources/line-double.png"), 4, LineStyleEnum.DOUBLE,
				LineStyleEnum.DOUBLE));
		// Creating the combo popup
		combo = new ComboMenuViewer(composite, SWT.NORMAL, SPRWPopUpCombo.getLongest(itemsList));
		combo.setItems(itemsList);
		combo.addSelectionListener(new ComboItemAction() {
			/**
			 * The action to execute when an entry is selected
			 */
			@Override
			public void exec() {
				propertyChange(section, JRBasePen.PROPERTY_LINE_STYLE,
						combo.getSelectionValue() != null ? combo.getSelectionValue() : null);
			}
		});
	}

	/**
	 * Set the contextual help for the control
	 * 
	 * @param href
	 *          uri to open when the help is requested
	 */
	public void setHelp(String href) {
		combo.setHelp(href);
	}

	public void propertyChange(AbstractSection section, String property, Object value) {
		section.changeProperty(property, value);
	}

	public int getIndexByType(LineStyleEnum searched) {
		int index = 0;
		for (ComboItem item : itemsList) {
			if (searched.equals(item.getItem())) {
				break;
			}
			index++;
		}
		return index;
	}

	/**
	 * Set the selected index of the popup combo
	 * 
	 * @param b
	 *          index
	 */
	public void setData(Integer b) {
		combo.select(b);
	}

	public void setData(LineStyleEnum lse) {
		if (lse == null)
			combo.select(0);
		else if (lse == LineStyleEnum.SOLID)
			combo.select(1);
		else if (lse == LineStyleEnum.DASHED)
			combo.select(2);
		else if (lse == LineStyleEnum.DOTTED)
			combo.select(3);
		else if (lse == LineStyleEnum.DOUBLE)
			combo.select(4);
	}

	public Control getControl() {
		return combo.getControl();
	}
}
