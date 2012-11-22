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
package com.jaspersoft.studio.data.jdbc;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.data.messages.Messages;
import com.jaspersoft.studio.data.querydesigner.sql.SQLLineStyler;
import com.jaspersoft.studio.data.ui.SimpleQueryWizardDataEditorComposite;

public class JDBCWizardDataEditorComposite extends SimpleQueryWizardDataEditorComposite {

	private SQLLineStyler lineStyler = new SQLLineStyler();
	
	public JDBCWizardDataEditorComposite(Composite parent, WizardPage page, JDBCDataAdapterDescriptor dataAdapterDescriptor) {
	
		super(parent, page);
		setQueryLanguage("sql"); //$NON-NLS-1$
		setTitle(Messages.JDBCWizardDataEditorComposite_Title);
		
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
