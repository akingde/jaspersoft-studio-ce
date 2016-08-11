/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved. http://www.jaspersoft.com.
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.widgets.framework.ui;

import org.eclipse.jface.util.Util;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;

import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;
import com.jaspersoft.studio.widgets.framework.model.WidgetPropertyDescriptor;
import com.jaspersoft.studio.widgets.framework.model.WidgetsDescriptor;

/**
 * Widget used to provide a combo where it is not possible to type
 */
public class SelectableComboItemPropertyDescription<T> extends ComboItemPropertyDescription<T> {
	
	/**
	 * On MacOS seems the contextual menu is not opened on combo, this
	 * lister will force it to open when a right click is found
	 */
	protected static MouseAdapter macComboMenuOpener = new MouseAdapter() {
		
		@Override
		public void mouseUp(MouseEvent e) {	
			if (e.button == 3 && ((Control)e.widget).getMenu() != null){
				Menu menu = ((Control)e.widget).getMenu();
				if (menu != null && !menu.isDisposed() && !menu.isVisible()){
	        		Point location = e.widget.getDisplay().getCursorLocation();
					menu.setLocation(location.x, location.y);
					menu.setVisible(true);
				}
			}
		}
	};

	public SelectableComboItemPropertyDescription() {
		super();
	}

	public SelectableComboItemPropertyDescription(String name, String label, String description, boolean mandatory, T defaultValue, String[] values) {
		super(name, label, description, mandatory, defaultValue, values);
	}

	public SelectableComboItemPropertyDescription(String name, String label, String description, boolean mandatory, String[] values) {
		super(name, label, description, mandatory, values);
	}

	public SelectableComboItemPropertyDescription(String name, String label, String description, boolean mandatory, String[] values, boolean readOnly) {
		super(name, label, description, mandatory, values);
	}

	public SelectableComboItemPropertyDescription(String name, String label, String description, boolean mandatory, T defaultValue,	String[][] keyValues) {
		super(name, label, description, mandatory, defaultValue, keyValues);
	}
	
	@Override
	protected Combo createComboControl(Composite parent) {
		Combo result = new Combo(parent, SWT.READ_ONLY);
		//MacOS fix, the combo on MacOS doesn't have a contextual menu, so we need to handle this listener manually
		boolean handleComboListener = Util.isMac();
		if (handleComboListener){
			result.addMouseListener(macComboMenuOpener);
		}
		return result;
	}
	
	@Override
	public ItemPropertyDescription<T> clone(){
		SelectableComboItemPropertyDescription<T> result = new SelectableComboItemPropertyDescription<T>();
		result.defaultValue = defaultValue;
		result.description = description;
		result.jConfig = jConfig;
		result.label = label;
		result.mandatory = mandatory;
		result.name = name;
		result.readOnly = readOnly;
		result.keyValues = keyValues;
		return result;
	}
	
	@Override
	public ItemPropertyDescription<?> getInstance(WidgetsDescriptor cd, WidgetPropertyDescriptor cpd, JasperReportsConfiguration jConfig) {
		if (cpd.getComboOptions() != null) {
			String[][] opts = cpd.getComboOptions();
			String[][] i18nOpts = new String[opts.length][2];
			for (int i = 0; i < opts.length; i++) {
				i18nOpts[i][0] = opts[i][0];
				i18nOpts[i][1] = cd.getLocalizedString(opts[i][1]);
			}
			SelectableComboItemPropertyDescription<String> result = new SelectableComboItemPropertyDescription<String>(cpd.getName(), cd.getLocalizedString(cpd.getLabel()), cd.getLocalizedString(cpd.getDescription()), cpd.isMandatory(), cpd.getDefaultValue(), i18nOpts);
			result.setReadOnly(cpd.isReadOnly());
			return result;
		}
		return null;
	}
}
