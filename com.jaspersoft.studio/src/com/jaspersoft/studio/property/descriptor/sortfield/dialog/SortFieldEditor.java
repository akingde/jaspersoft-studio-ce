/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.descriptor.sortfield.dialog;

import java.util.List;

import org.eclipse.jface.wizard.Wizard;

import com.jaspersoft.studio.messages.Messages;

public class SortFieldEditor extends Wizard {
	private List<?> list;
	private SortFieldPage page0;

	public List<?> getList() {
		if (page0 != null)
			return page0.getList();
		return list;
	}

	public void setList(List<?> list) {
		this.list = list;
	}

	public SortFieldEditor() {
		super();
		setWindowTitle(Messages.common_sort_field_editor);
	}

	@Override
	public void addPages() {
		page0 = new SortFieldPage("sort.field.editor"); //$NON-NLS-1$
		page0.setList(getList());
		addPage(page0);
	}

	@Override
	public boolean performFinish() {
		return true;
	}

}
