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
package com.jaspersoft.studio.server.editor.input;

import java.util.Map;

import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.editor.preview.input.IParameter;
import com.jaspersoft.studio.server.WSClientHelper;
import com.jaspersoft.studio.server.model.server.ServerProfile;

public class DateInput extends
		com.jaspersoft.studio.editor.preview.input.DateInput {
	public DateInput() {
		super(true, true);
	}

	@Override
	public void createInput(Composite parent, IParameter param,
			Map<String, Object> params) {
		if (param instanceof PResourceDescriptor) {
			PResourceDescriptor p = (PResourceDescriptor) param;
			ServerProfile sp = WSClientHelper.getServerProfile(p.getWsClient());
			if (sp != null)
				setSupportDateRange(sp.isSupportsDateRanges());
		}
		super.createInput(parent, param, params);
	}
}
