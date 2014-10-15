/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.server.editor.input;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;

public interface IInputControls {

	public IInputControls getInstance();

	public void createControl(Composite composite, VInputControls icForm);

	public ResourceDescriptor getICContainerUri(String uri);

	public void initICOptions(InputControlsManager icm, ResourceDescriptor rdrepunit, IProgressMonitor monitor);
}
