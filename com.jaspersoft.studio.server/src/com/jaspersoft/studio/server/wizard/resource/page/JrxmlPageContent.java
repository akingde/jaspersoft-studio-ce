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
package com.jaspersoft.studio.server.wizard.resource.page;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.window.Window;
import org.eclipse.ui.dialogs.FilteredResourcesSelectionDialog;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.server.messages.Messages;
import com.jaspersoft.studio.server.model.MResource;

public class JrxmlPageContent extends AFileResourcePageContent {

	public JrxmlPageContent(ANode parent, MResource resource, DataBindingContext bindingContext) {
		super(parent, resource, bindingContext);
	}

	public JrxmlPageContent(ANode parent, MResource resource) {
		super(parent, resource);
	}

	@Override
	public String getPageName() {
		return "com.jaspersoft.studio.server.page.jrxml";
	}

	@Override
	public String getName() {
		return Messages.RDJrxmlPage_title;
	}

	@Override
	protected String[] getFilter() {
		return new String[] { "*.jrxml" }; //$NON-NLS-1$
	}

	@Override
	protected String getFileDialog() {
		FilteredResourcesSelectionDialog dialog = new FilteredResourcesSelectionDialog(trefuri.getShell(), false, ResourcesPlugin.getWorkspace().getRoot(), IResource.FILE);
		dialog.setTitle(com.jaspersoft.studio.messages.Messages.ResourceCellEditor_open_resource);
		dialog.setInitialPattern("*.jrxml"); //$NON-NLS-1$
		if (dialog.open() == Window.OK) {
			IFile file = (IFile) dialog.getFirstResult();
			return file.getLocation().toPortableString();
		}
		return null;
	}
}
