/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer.
 * Copyright (C) 2005 - 2010 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of Jaspersoft Open Studio.
 *
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Jaspersoft Open Studio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with Jaspersoft Open Studio. If not, see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.editor.preview.view.control;

import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.design.JRDesignParameter;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.jaspersoft.studio.preferences.util.PropertiesHelper;

public class VReportParameters extends VParameters {

	public VReportParameters(Composite parent, PropertiesHelper ph) {
		super(parent, ph);
	}

	@Override
	public void createInputControls(List<JRParameter> prompts, Map<String, Object> params) {
		for (Control c : composite.getChildren()) {
			c.dispose();
		}
		for (JRParameter p : prompts)
			if (p.isSystemDefined()) {
				createInput(composite, (JRDesignParameter) p, params);
			}
		scompo.setMinSize(composite.getSize());
		composite.pack();
	}

}
