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
package com.jaspersoft.studio.data.hibernate;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.data.messages.Messages;
import com.jaspersoft.studio.data.querydesigner.hql.HQLLineStyler;
import com.jaspersoft.studio.data.ui.SimpleQueryWizardDataEditorComposite;

/**
 * Editor composite for the HQL query language.
 * This is supposed to used by {@link HibernateDataAdapterDescriptor}.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public class HQLWizardDataEditorComposite extends SimpleQueryWizardDataEditorComposite {

	private HQLLineStyler lineStyler = new HQLLineStyler();
	
	public HQLWizardDataEditorComposite(Composite parent, WizardPage page,
			DataAdapterDescriptor dataAdapterDescriptor) {
		super(parent, page, dataAdapterDescriptor);
		setQueryLanguage("hql"); //$NON-NLS-1$
		setTitle(Messages.HQLWizardDataEditorComposite_Title);
		
		styledText.addLineStyleListener(lineStyler);
		styledText.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				lineStyler.parseBlockComments(styledText.getText());
			}
		});
	}

}
