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
package com.jaspersoft.studio.book.wizards;

import java.util.Arrays;
import java.util.List;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRSubreportParameter;
import net.sf.jasperreports.parts.subreport.StandardSubreportPartComponent;

import com.jaspersoft.studio.book.messages.Messages;
import com.jaspersoft.studio.book.model.MReportPart;
import com.jaspersoft.studio.property.descriptor.subreport.parameter.dialog.SubreportPropertyPage;
import com.jaspersoft.studio.wizards.JSSWizard;

/**
 * Wizard to add, remove or edit the parameters from an
 * MReportPart
 * 
 * @author Orlandin Marco
 *
 */
public class PartPropertyEditor extends JSSWizard {
	
	/**
	 * The edited part
	 */
	private MReportPart part;
	
	/**
	 * The page to edit the parameters
	 */
	private PartPropertyPage page0;

	
	private class PartPropertyPage extends SubreportPropertyPage{
		
		public PartPropertyPage() {
			super("partproperties"); //$NON-NLS-1$
			setTitle(Messages.PartPropertyEditor_pageTitle);
			setDescription(Messages.PartPropertyEditor_pageDescription);
		}
	}
	
	public JRSubreportParameter[] getValue() {
		if (page0 != null)
			return page0.getValue();
		return part.getSubreportComponent().getParameters();
	}

	public void setValue() {
		setConfig(part.getJasperConfiguration());
		if (page0 != null)
			page0.setValue(part.getSubreportComponent().getParameters());
	}
	
	public PartPropertyEditor(MReportPart part) {
		super();
		this.part = part;
		setWindowTitle(Messages.PartPropertyEditor_wizardTitle);
	}

	@Override
	public void addPages() {
		page0 = new PartPropertyPage(); //$NON-NLS-1$
		page0.setValue(part.getSubreportComponent().getParameters());
		addPage(page0);
	}

	@Override
	public boolean performFinish() {
		return true;
	}
	
	/**
	 * Save the parameters in the page inside the part
	 */
	public void saveData() {
		if (part != null) {
			StandardSubreportPartComponent component = part.getSubreportComponent();
			JRSubreportParameter[] actaulValues = page0.getValue();

			List<JRSubreportParameter> oldValues = Arrays.asList(component.getParameters());
			// The element dosen't allow to swap the list, must remove the old element
			for (JRSubreportParameter oldValue : oldValues) {
				component.removeParameter(oldValue.getName());
			}
			// and add the new one
			for (JRSubreportParameter newValue : actaulValues) {
				try {
					component.addParameter(newValue);
				} catch (JRException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
