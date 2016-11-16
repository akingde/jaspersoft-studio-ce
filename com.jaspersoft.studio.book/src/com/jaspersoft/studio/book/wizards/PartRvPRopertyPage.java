/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.book.wizards;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.JRSubreportReturnValue;
import net.sf.jasperreports.engine.JRVariable;
import net.sf.jasperreports.parts.subreport.StandardSubreportPartComponent;

import com.jaspersoft.studio.book.model.MReportPart;
import com.jaspersoft.studio.property.descriptor.returnvalue.RVPropertyPage;
import com.jaspersoft.studio.property.descriptor.returnvalue.ReturnValueContainer;

public class PartRvPRopertyPage extends RVPropertyPage {

	private MReportPart part;
	
	public PartRvPRopertyPage(MReportPart part){
		super("partproperties", part.getJasperDesign());
		this.part = part;
		setTitle("Part Return Values");
		setDescription("Define the return values for the current part");
	}
	
	@Override
	public JRVariable[] getDatasetVariables() {
		return design.getVariables();	
	}
	
	/**
	 * Take the information on the return values from the table and save 
	 * them inside the dataset run
	 */
	public void saveValuesIntoDataset(){
		if (part != null){
			StandardSubreportPartComponent component = part.getSubreportComponent();
			List<ReturnValueContainer> actaulValues = getValue();
			List<JRSubreportReturnValue> newValues = ReturnValueContainer.convertToSubreport(actaulValues);
			List<JRSubreportReturnValue> oldValues = new ArrayList<JRSubreportReturnValue>(component.getReturnValuesList());
			//The element dosen't allow to swap the list, must remove the old element 
			for(JRSubreportReturnValue oldValue : oldValues){
				component.removeReturnValue(oldValue);
			}
			//and add the new one
			for(JRSubreportReturnValue newValue : newValues){
				component.addReturnValue(newValue);
			}
		}
	}

}
