/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2010 Jaspersoft Corporation. All
 * rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of iReport.
 * 
 * iReport is free software: you can redistribute it and/or modify it under the terms of the GNU Affero General Public
 * License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * iReport is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with iReport. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.crosstab;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.crosstabs.JRCrosstab;
import net.sf.jasperreports.crosstabs.JRCrosstabColumnGroup;
import net.sf.jasperreports.crosstabs.JRCrosstabMeasure;
import net.sf.jasperreports.crosstabs.JRCrosstabParameter;
import net.sf.jasperreports.crosstabs.JRCrosstabRowGroup;
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstab;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;
import org.eclipse.jface.action.Action;
import org.eclipse.ui.part.WorkbenchPart;

import com.jaspersoft.studio.IComponentFactory;
import com.jaspersoft.studio.crosstab.model.MCrosstab;
import com.jaspersoft.studio.crosstab.model.columngroup.MColumnGroup;
import com.jaspersoft.studio.crosstab.model.columngroup.MColumnGroups;
import com.jaspersoft.studio.crosstab.model.measure.MMeasure;
import com.jaspersoft.studio.crosstab.model.measure.MMeasures;
import com.jaspersoft.studio.crosstab.model.rowgroup.MRowGroup;
import com.jaspersoft.studio.crosstab.model.rowgroup.MRowGroups;
import com.jaspersoft.studio.editor.gef.figures.CrosstabFigure;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.ReportFactory;
import com.jaspersoft.studio.model.parameter.MParameters;

public class CrosstabComponentFactory implements IComponentFactory {

	public ANode createNode(ANode parent, Object jrObject, int newIndex) {
		if (jrObject instanceof JRDesignCrosstab) {
			JRCrosstab ct = (JRCrosstab) jrObject;
			MCrosstab mCrosstab = new MCrosstab(parent, ct, newIndex);
			MParameters mp = new MParameters(mCrosstab, ct, JRDesignCrosstab.PROPERTY_PARAMETERS);
			if (ct.getParameters() != null)
				for (JRCrosstabParameter p : ct.getParameters())
					ReportFactory.createNode(mp, p, -1);
			MRowGroups mrg = new MRowGroups(mCrosstab, ct, JRDesignCrosstab.PROPERTY_ROW_GROUPS);
			if (ct.getRowGroups() != null)
				for (JRCrosstabRowGroup p : ct.getRowGroups())
					ReportFactory.createNode(mrg, p, -1);
			MColumnGroups mcg = new MColumnGroups(mCrosstab, ct, JRDesignCrosstab.PROPERTY_COLUMN_GROUPS);
			if (ct.getColumnGroups() != null)
				for (JRCrosstabColumnGroup p : ct.getColumnGroups())
					ReportFactory.createNode(mcg, p, -1);
			MMeasures mm = new MMeasures(mCrosstab, ct, JRDesignCrosstab.PROPERTY_MEASURES);
			if (ct.getMeasures() != null)
				for (JRCrosstabMeasure p : ct.getMeasures())
					ReportFactory.createNode(mm, p, -1);

			return mCrosstab;
		}
		if (jrObject instanceof JRCrosstabRowGroup) {
			return new MRowGroup(parent, (JRCrosstabRowGroup) jrObject, newIndex);
		}
		if (jrObject instanceof JRCrosstabColumnGroup) {
			return new MColumnGroup(parent, (JRCrosstabColumnGroup) jrObject, newIndex);
		}
		if (jrObject instanceof JRCrosstabMeasure) {
			return new MMeasure(parent, (JRCrosstabMeasure) jrObject, newIndex);
		}
		return null;
	}

	public IFigure createFigure(ANode node) {
		if (node instanceof MCrosstab)
			return new CrosstabFigure();
		return null;
	}

	public List<?> getChildren4Element(Object jrObject) {
		if (jrObject instanceof JRCrosstab) {
			JRCrosstab ct = (JRCrosstab) jrObject;
			List<Object> lst = new ArrayList<Object>();

			// lst.add(ct.getParameters());
			// lst.add(ct.getRowGroups());
			// lst.add(ct.getColumnGroups());
			// lst.add(ct.getMeasures());

			// lst.add(Arrays.asList(ct.getHeaderCell()));

			return lst;
		}
		return null;
	}

	public List<Class<?>> getPaletteEntries() {
		List<Class<?>> list = new ArrayList<Class<?>>();
		list.add(MCrosstab.class);
		return list;
	}

	public Command getCreateCommand(ANode parent, ANode child, Point location, int newIndex) {
		// if (child instanceof MBarcode) {
		// if (parent instanceof MElementGroup)
		// return new CreateElementCommand((MElementGroup) parent, (MGraphicElement) child, newIndex);
		// if (parent instanceof MBand)
		// return new CreateElementCommand((MBand) parent, (MGraphicElement) child, newIndex);
		// if (parent instanceof MFrame)
		// return new CreateElementCommand((MFrame) parent, (MGraphicElement) child, newIndex);
		// if (parent instanceof MReport)
		// return new CreateElementCommand(parent, (MGraphicElement) child, location, newIndex);
		//
		// if (parent instanceof IGroupElement) {
		// return new CreateElementCommand(parent, (MGraphicElement) child, location, newIndex);
		// }
		// }
		return null;
	}

	public Command getDeleteCommand(ANode parent, ANode child) {
		return null;
	}

	public Command getReorderCommand(ANode parent, ANode child, int newIndex) {
		return null;
	}

	public List<Action> getActions(WorkbenchPart part) {
		return null;
	}

	public List<String> getActionsID() {
		return null;
	}

}
