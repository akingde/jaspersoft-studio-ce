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
package com.jaspersoft.studio.server.model;

import java.io.File;
import java.io.IOException;

import net.sf.jasperreports.engine.JRConstants;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.utils.UIUtils;

public abstract class AFileResource extends MResource {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	public AFileResource(ANode parent, ResourceDescriptor rd, int index) {
		super(parent, rd, index);
	}

	private File file;

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileName() {
		if (file != null)
			try {
				return file.getCanonicalPath();
			} catch (IOException e) {
				UIUtils.showError(e);
			}
		return "";
	}

}
