/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.editor.action.text;

import java.util.Iterator;

import net.sf.jasperreports.engine.design.JRDesignStyle;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.wb.swt.ResourceManager;

import com.jaspersoft.studio.JSSCompoundCommand;
import com.jaspersoft.studio.utils.Misc;
import com.jaspersoft.studio.utils.ModelUtils;

/**
 * Zoom contribution item
 * 
 * @author Peter Severin (peter_p_s@users.sourceforge.net)
 */
public class FontSizeComboContributionItem extends APropertyComboContributionItem {
	public static final String ID = "com.jaspersoft.studio.editor.action.text.fontsize";

	/**
	 * Constructs the action by specifying the report viewer to associate with the item.
	 * 
	 * @param viewer
	 *          the report viewer
	 */
	public FontSizeComboContributionItem() {
		super(ID);
	}
	
	/**
	 * The combo background default color
	 */
	private Color comboBackgroundDefault;

	@Override
	protected Control createControl(Composite parent) {
		Control ctrl = super.createControl(parent);
		comboBackgroundDefault = combo.getBackground();
		ctrl.setSize(70, ctrl.getSize().y);
		combo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				changeProperty();
			}
		});
		
		return ctrl;
	}
	
	/**
	 * Change the font size of one or more elements
	 */
	private void changeProperty(){
		if (selection.isEmpty())
			return;
		JSSCompoundCommand cc = new JSSCompoundCommand(null);
		StructuredSelection ss = (StructuredSelection) selection;
		String text = combo.getText().trim();
		//If the string ends with the separator probably the user must still insert char, so don't set it 
		if (!(text.endsWith(",") || text.endsWith("."))){
			try{
				Float realValue = Float.valueOf(text.replace(",", "."));
				for (Iterator<?> it = ss.iterator(); it.hasNext();) {
					Object obj = it.next();
					if (obj instanceof EditPart)
						obj = ((EditPart) obj).getModel();

					Command changeValueCmd = createCommand(obj, realValue.toString());
					if (changeValueCmd != null) {
						cc.add(changeValueCmd);
						cc.setReferenceNodeIfNull(obj);
					}
				}
				getCommandStack().execute(cc);
				combo.setBackground(comboBackgroundDefault);
			} catch(NumberFormatException ex){
				//If the value is not a valid number the the background of the textarea became red
				combo.setBackground(ResourceManager.getColor(255, 0, 0));
			}
		}
	
	}
	
	/**
	 * Remove all the decimal zeros from a string. If after the remove the remaining trail char 
	 * is a . the it is also removed
	 * 
	 * @param value a string
	 * @return a string without decimal zeros at the end
	 */
	private String removeUnnecessaryZeros(String value){
		String newValue = value.replaceAll("(\\.(\\d*[1-9])?)0+", "$1");
		if (newValue.endsWith(".")) newValue = newValue.substring(0, newValue.length()-1);
		return newValue;
	}

	/**
	 * When the combo text is set the unnecessary deciaml zeros are removed
	 */
	protected void setComboText(Object value) {
		super.setComboText(value);
		if (value ==  null) return;
		
		String str = removeUnnecessaryZeros((String) value);
		String[] items = combo.getItems();
		int selection = -1;
		for (int i = 0; i < items.length; i++) {
			if (Misc.compare(items[i], str, false)) {
				selection = i;
				break;
			}
		}
		if (selection != -1) combo.select(selection);
		else combo.setText(Misc.nvl(str));
		int stringLength = combo.getText().length();

		combo.setSelection(new Point(stringLength, stringLength));
		combo.getParent().layout(true);
		combo.setEnabled(model != null ? model.isEditable() : true);
	}


	@Override
	protected void setComboItems() {
		combo.setItems(ModelUtils.FONT_SIZES);
	}

	@Override
	protected Object getDefaultValue() {
		return new Integer(10);
	}

	protected Object getPropertyName() {
		return JRDesignStyle.PROPERTY_FONT_SIZE;
	}

}
