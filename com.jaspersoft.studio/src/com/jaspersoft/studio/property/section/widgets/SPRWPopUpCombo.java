package com.jaspersoft.studio.property.section.widgets;
/*
 * JasperReports - Free Java Reporting Library. Copyright (C) 2001 - 2011 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of JasperReports.
 * 
 * JasperReports is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser General
 * Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * JasperReports is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License along with JasperReports. If not, see
 * <http://www.gnu.org/licenses/>.
*/

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.property.combomenu.ComboItem;
import com.jaspersoft.studio.property.combomenu.ComboItemAction;
import com.jaspersoft.studio.property.combomenu.ComboMenuViewer;
import com.jaspersoft.studio.property.section.AbstractSection;

/**
 * The widget for a popup combo box
 * @author Orlandin Marco
 *
 */
public class SPRWPopUpCombo extends ASPropertyWidget {
	
	/**
	 * The combo item
	 */
	protected ComboMenuViewer combo;

	/**
	 * The list of entry in the popup menu
	 */
	protected List<ComboItem> items = null;
		
	/**
	 * Create a new widget
	 * @param parent parent of the widget
	 * @param section section where the command will be executed
	 * @param pDescriptor descriptor of the property of this item
	 * @param items List of entry in the popup menu
	 */
	public SPRWPopUpCombo(Composite parent, AbstractSection section, IPropertyDescriptor pDescriptor, List<ComboItem> items) {
		super(parent, section, pDescriptor);
		this.items = items;
		createComponent(parent);
	}

	@Override
	public Control getControl() {
		return combo.getControl();
	}

	public void setData(APropertyNode pnode, Object b) {
		int index = 0;
		if (b != null)
			index = ((Number) b).intValue();
		combo.select(index);
	}
	
	/**
	 * Return the longest text in the list of entry
	 * @param itemList a list of entry
	 * @return the longest label
	 */
	public static String getLongest(List<ComboItem> itemList){
		String longest = "";
		for(ComboItem item : itemList)
			if (longest.length()<item.getText().length()){
				longest = item.getText();
			}
		return longest;
	}

	protected void createComponent(Composite parent) {
		if (items != null){
			combo = new ComboMenuViewer(parent, SWT.NORMAL, getLongest(items));
			combo.setItems(items);
			combo.addSelectionListener(new ComboItemAction() {
					@Override
					public void exec() {
							section.changeProperty(pDescriptor.getId(), combo.getSelectionValue());			
							System.out.println("changed " +pDescriptor.getId()+" to " + combo.getSelectionValue().toString());
					}
			});
			combo.setToolTipText(pDescriptor.getDescription());
		}
	}

}
