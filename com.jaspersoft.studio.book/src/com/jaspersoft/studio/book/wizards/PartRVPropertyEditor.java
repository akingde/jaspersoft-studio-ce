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

import java.util.List;

import net.sf.jasperreports.parts.subreport.StandardSubreportPartComponent;

import org.eclipse.jface.wizard.Wizard;

import com.jaspersoft.studio.book.model.MReportPart;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.property.descriptor.returnvalue.ReturnValueContainer;

public class PartRVPropertyEditor extends Wizard {
	
	private MReportPart part;
	
	private PartRvPRopertyPage page0;

	public PartRVPropertyEditor(MReportPart part) {
		super();
		setWindowTitle(Messages.common_properties);
		setNeedsProgressMonitor(false);
		this.part = part;
	}

	@Override
	public void addPages() {
		page0 = new PartRvPRopertyPage(part);
		StandardSubreportPartComponent component = part.getSubreportComponent();
		List<ReturnValueContainer> actaulValues = ReturnValueContainer.convertFromSubreportReturn(component.getReturnValuesList());
		page0.setValue(actaulValues);
		addPage(page0);
	}

	@Override
	public boolean performFinish() {
		return true;
	}
	
	public void saveData(){
		page0.saveValuesIntoDataset();
	}

}
