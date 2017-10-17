/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
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
