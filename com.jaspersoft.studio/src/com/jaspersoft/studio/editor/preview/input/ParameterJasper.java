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
package com.jaspersoft.studio.editor.preview.input;

import net.sf.jasperreports.engine.design.JRDesignParameter;

public class ParameterJasper implements IParameter {
	private JRDesignParameter param;

	public ParameterJasper(JRDesignParameter param) {
		this.param = param;
	}

	public String getName() {
		return param.getName();
	}

	public Class<?> getValueClass() {
		return param.getValueClass();
	}

	public String getDescription() {
		return param.getDescription();
	}

	public String getLabel() {
		return param.getName();
	}

	public boolean isMandatory() {
		return false;
	}

	public boolean isReadOnly() {
		return false;
	}

	public boolean isStrictMin() {
		return false;
	}

	public String getMinValue() {
		return null;
	}

	public boolean isStrictMax() {
		return false;
	}

	public String getMaxValue() {
		return null;
	}

	public String getPattern() {
		return null;
	}
}
