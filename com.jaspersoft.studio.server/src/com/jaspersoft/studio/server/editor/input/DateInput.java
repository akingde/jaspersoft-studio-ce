/*
 * JasperReports - Free Java Reporting Library. Copyright (C) 2001 - 2012 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of JasperReports.
 * 
 * JasperReports is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser General
 * Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * JasperReports is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License along with JasperReports. If not, see
 * <http://www.gnu.org/licenses/>.
 */
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
