/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.itemproperty.dialog;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;

import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.property.itemproperty.desc.ADescriptor;
import com.jaspersoft.studio.property.itemproperty.desc.AItemDataListPropertyDescriptor;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class AItemPropertiesUtil {
	protected APropertyNode pnode;
	protected AItemDataListPropertyDescriptor pDescriptor;
	protected AbstractSection section;

	public AItemPropertiesUtil(AItemDataListPropertyDescriptor pDescriptor, AbstractSection section) {
		this.pDescriptor = pDescriptor;
		this.section = section;
	}

	public void setPnode(APropertyNode pnode) {
		this.pnode = pnode;
	}

	protected AItemDialog createItemDialog() {
		return new TableItemDialog(UIUtils.getShell(), getDescriptor(),
				(JasperReportsConfiguration) section.getJasperReportsContext(), true);
	}

	private ADescriptor getDescriptor() {
		return pDescriptor.getDescriptor();
	}
}
