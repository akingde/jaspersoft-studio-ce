/*******************************************************************************
 * Copyright (C) 2010 - 2012 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.model.sortfield.command.wizard;

import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignSortField;

import org.eclipse.jface.wizard.Wizard;

import com.jaspersoft.studio.messages.Messages;

public class SortFieldWizard extends Wizard {
	private WizardSortFieldPage page5;

	public SortFieldWizard() {
		super();
		setWindowTitle(Messages.common_image);
	}

	@Override
	public void addPages() {
		page5 = new WizardSortFieldPage(jrDataSet, jrSortField);
		addPage(page5);
	}

	@Override
	public boolean performFinish() {
		return true;
	}

	private JRDesignDataset jrDataSet;
	private JRDesignSortField jrSortField;

	public void init(JRDesignDataset jd, JRDesignSortField sortField) {
		this.jrDataSet = jd;
		this.jrSortField = sortField;
	}
}
