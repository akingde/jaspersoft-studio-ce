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
package com.jaspersoft.studio.data.mongodb;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.mongodb.MongoDbDataSource;
import com.jaspersoft.studio.data.mongodb.querydesigner.MongoDBLineStyler;
import com.jaspersoft.studio.data.ui.SimpleQueryWizardDataEditorComposite;

public class MongoDBWizardDataEditorComposite extends
		SimpleQueryWizardDataEditorComposite {

	private MongoDBLineStyler lineStyler = new MongoDBLineStyler();

	public MongoDBWizardDataEditorComposite(Composite parent, WizardPage page,
			MongoDbDataAdapterDescriptor dataAdapterDescriptor) {
		super(parent, page);
		setQueryLanguage(MongoDbDataSource.QUERY_LANGUAGE);
		setTitle("Insert a MongoDB query and press Next");

		this.setDataAdapterDescriptor(dataAdapterDescriptor);

		styledText.addLineStyleListener(lineStyler);
	}

}
