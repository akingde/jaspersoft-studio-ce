/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
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
package com.jaspersoft.studio.data.hive;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.hadoop.hive.HiveDataSource;
import com.jaspersoft.studio.data.hive.querydesigner.HiveQLLineStyler;
import com.jaspersoft.studio.data.ui.SimpleQueryWizardDataEditorComposite;

public class HiveWizardDataEditorComposite extends
		SimpleQueryWizardDataEditorComposite {

	private HiveQLLineStyler lineStyler = new HiveQLLineStyler();

	public HiveWizardDataEditorComposite(Composite parent, WizardPage page,
			HiveDataAdapterDescriptor dataAdapterDescriptor) {

		super(parent, page);
		setQueryLanguage(HiveDataSource.QUERY_LANGUAGE);
		setTitle("Insert a HiveQL query and press Next");

		this.setDataAdapterDescriptor(dataAdapterDescriptor);

		styledText.addLineStyleListener(lineStyler);
		styledText.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				lineStyler.parseBlockComments(styledText.getText());
			}
		});
	}

}
