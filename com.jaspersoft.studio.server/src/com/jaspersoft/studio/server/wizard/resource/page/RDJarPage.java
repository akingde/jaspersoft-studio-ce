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
package com.jaspersoft.studio.server.wizard.resource.page;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.server.messages.Messages;
import com.jaspersoft.studio.server.model.MJar;

public class RDJarPage extends AFileResourcePage {

	public RDJarPage(ANode parent, MJar resource) {
		super(Messages.RDJarPage_id, parent, resource);
		setTitle(Messages.RDJarPage_title);
		setDescription(Messages.RDJarPage_desc);
	}

	@Override
	protected String[] getFilter() {
		return new String[] { "*.zip", "*.jar" }; //$NON-NLS-1$ //$NON-NLS-2$
	}
}
