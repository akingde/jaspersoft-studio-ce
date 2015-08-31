/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.property.descriptor.resource;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.help.HelpSystem;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.MReport;
import com.jaspersoft.studio.model.dataset.MDataset;
import com.jaspersoft.studio.model.dataset.SelectDefaultDatasetWizard;
import com.jaspersoft.studio.property.descriptor.text.NTextPropertyDescriptor;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.property.section.widgets.ASPropertyWidget;
import com.jaspersoft.studio.property.section.widgets.SPResourceType;

/**
 * 
 * Widget descriptor with a button to select a properties resource file and a text area where the path of the selected
 * resource is shown. From the selected filename is calculated the bundle base name. Other than this if the parent
 * folder of the file is not in the classpath it is proposed to the user to add it automatically
 * 
 * @author Orlandin Marco & Slavic
 * 
 */
public class DefaultDatasetPropertyDescriptor extends NTextPropertyDescriptor {

	/**
	 * This class extends the original widget to select a resource to be used only with resource bundle. For example the
	 * selection of the file is limited to the files with .properties extension and contained in the actually opened
	 * project or in one of its dependences
	 * 
	 * @author Orlandin Marco
	 * 
	 */
	private class SPDatasetWidget extends SPResourceType {

		public SPDatasetWidget(Composite parent, AbstractSection section, IPropertyDescriptor pDescriptor) {
			super(parent, section, pDescriptor);
		}


		@Override
		protected SelectionAdapter buttonPressed() {
			return new SelectionAdapter() {

				@Override
				public void widgetSelected(SelectionEvent e) {
					if (section.getElement() instanceof MReport){
						MReport report = (MReport)section.getElement();
						SelectDefaultDatasetWizard defaultDAwizard = new SelectDefaultDatasetWizard(report);
						WizardDialog defaultDAdialog = new WizardDialog(UIUtils.getShell(), defaultDAwizard);
						defaultDAdialog.open();
						ftext.setText(defaultDAwizard.getValue() != null ? defaultDAwizard.getValue() : "");
						handleTextChanged(section,pDescriptor.getId(), defaultDAwizard.getValue());	
					} else if (section.getElement() instanceof MDataset){
						MDataset dataset = (MDataset)section.getElement();
						SelectDefaultDatasetWizard defaultDAwizard = new SelectDefaultDatasetWizard(dataset);
						WizardDialog defaultDAdialog = new WizardDialog(UIUtils.getShell(), defaultDAwizard);
						defaultDAdialog.open();
						ftext.setText(defaultDAwizard.getValue() != null ? defaultDAwizard.getValue() : "");
						handleTextChanged(section,pDescriptor.getId(), defaultDAwizard.getValue());
					}
				}
			};
		}
		
		@Override
		public void setData(APropertyNode pnode, Object b) {
			// TODO Auto-generated method stub
			super.setData(pnode, b);
		}
	};

	public DefaultDatasetPropertyDescriptor(Object id, String displayName) {
		super(id, displayName);
	}

	public CellEditor createPropertyEditor(Composite parent) {
		CellEditor editor =  new TextCellEditor(parent);
		HelpSystem.bindToHelp(this, editor.getControl());
		return editor;
	}

	public ASPropertyWidget createWidget(Composite parent, AbstractSection section) {
		ASPropertyWidget textWidget = new SPDatasetWidget(parent, section, this);
		return textWidget;
	}
	
}
