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
package com.jaspersoft.studio.editor.expression;

import net.sf.jasperreports.crosstabs.JRCrosstabColumnGroup;
import net.sf.jasperreports.crosstabs.JRCrosstabMeasure;
import net.sf.jasperreports.crosstabs.JRCrosstabRowGroup;

/**
 * 
 * @author gtoffoli
 */
public class CrosstabTotalVariable extends ExpObject {

	private JRCrosstabColumnGroup columnGroup = null;
	private JRCrosstabRowGroup rowGroup = null;
	private JRCrosstabMeasure measure = null;

	public CrosstabTotalVariable(JRCrosstabMeasure measure, JRCrosstabRowGroup rowGroup, JRCrosstabColumnGroup columnGroup) {
		this.measure = measure;
		this.rowGroup = rowGroup;
		this.columnGroup = columnGroup;

		setClassType(measure.getValueClassName());
		setName(toString());
		setType(TYPE_VARIABLE);
	}

	public JRCrosstabColumnGroup getColumnGroup() {
		return columnGroup;
	}

	public void setColumnGroup(JRCrosstabColumnGroup columnGroup) {
		this.columnGroup = columnGroup;
	}

	public JRCrosstabRowGroup getRowGroup() {
		return rowGroup;
	}

	public void setRowGroup(JRCrosstabRowGroup rowGroup) {
		this.rowGroup = rowGroup;
	}

	public JRCrosstabMeasure getMeasure() {
		return measure;
	}

	public void setMeasure(JRCrosstabMeasure measure) {
		this.measure = measure;
	}

	@Override
	public String toString() {
		String s = measure.getName();
		if (columnGroup == null && rowGroup == null)
			return s;
		if (columnGroup == null) {
			return s + " (total by " + rowGroup.getName() + ")";
		} else if (rowGroup == null) {
			return s + " (total by " + columnGroup.getName() + ")";
		} else {
			return s + " (total by " + rowGroup.getName() + " and " + columnGroup.getName() + ")";
		}
	}

	@Override
	public String getExpression() {
		String s = "$V{" + measure.getName();
		if (rowGroup != null) {
			s += "_" + rowGroup.getName();
		}

		if (columnGroup != null) {
			s += "_" + columnGroup.getName();
		}

		return s + "_ALL}";
	}
}
