/*
 * JasperReports - Free Java Reporting Library. Copyright (C) 2001 - 2009 Jaspersoft Corporation. All rights reserved.
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
package com.jaspersoft.studio.editor.gef.parts;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.JRBand;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.SnapToGrid;
import org.eclipse.gef.editpolicies.SnapFeedbackPolicy;

import com.jaspersoft.studio.editor.gef.figures.APageFigure;
import com.jaspersoft.studio.editor.gef.figures.ReportPageFigure;
import com.jaspersoft.studio.editor.gef.parts.editPolicy.PageLayoutEditPolicy;
import com.jaspersoft.studio.editor.gef.rulers.ReportRuler;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.IGraphicElement;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.MReport;
import com.jaspersoft.studio.model.band.MBand;
import com.jaspersoft.studio.utils.ModelUtils;

/*
 * The Class PageEditPart.
 * 
 * @author Chicu Veaceslav
 */
public class ReportPageEditPart extends PageEditPart implements PropertyChangeListener {

	protected APageFigure newPageFigure() {
		return new ReportPageFigure(getJasperDesign(), true);
	}

	/**
	 * Setup page figure.
	 * 
	 * @param jd
	 *          the jasper design
	 * @param figure2
	 *          the figure2
	 */
	protected void setupPageFigure(APageFigure figure2) {
		JasperDesign jd = getJasperDesign();
		List<JRBand> bands = ModelUtils.getAllBands(jd);
		int dh = ModelUtils.getDesignHeight(bands);
		int designHeight = dh + jd.getTopMargin() + jd.getBottomMargin();

		int w = jd.getPageWidth() + 20;
		int h = designHeight + 10;

		((ReportPageFigure) figure2).setBandsHeight(designHeight);
		figure2.setSize(w, h);
	}

	public void updateRullers() {
		JasperDesign jd = getJasperDesign();

		List<JRBand> bands = ModelUtils.getAllBands(jd);
		int dh = ModelUtils.getDesignHeight(bands);
		int tx = jd.getLeftMargin() + ReportPageFigure.PAGE_BORDER.left;
		int ty = jd.getTopMargin() + ReportPageFigure.PAGE_BORDER.top;

		getViewer().setProperty(ReportRuler.PROPERTY_HOFFSET, tx);
		getViewer().setProperty(ReportRuler.PROPERTY_VOFFSET, ty);
		getViewer().setProperty(ReportRuler.PROPERTY_HEND, jd.getPageWidth() - jd.getLeftMargin() - jd.getRightMargin());
		getViewer().setProperty(ReportRuler.PROPERTY_VEND, dh);

		getViewer().setProperty(SnapToGrid.PROPERTY_GRID_ORIGIN,
				new Point(tx, ReportPageFigure.PAGE_BORDER.top + jd.getTopMargin()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
	 */
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new PageLayoutEditPolicy());
		installEditPolicy("Snap Feedback", new SnapFeedbackPolicy()); //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.AbstractEditPart#getModelChildren()
	 */
	protected List<Object> getModelChildren() {
		List<Object> list = new ArrayList<Object>();
		List<Object> sList = new ArrayList<Object>();
		// put bands first

		for (INode node : getPage().getChildren()) {
			if (node instanceof IGraphicElement && node.getValue() != null) {
				if (node instanceof MBand) {
					MBand band = (MBand) node;
					// if (!(band.getBandType().equals(BandTypeEnum.BACKGROUND) ||
					// band.getBandType().equals(BandTypeEnum.NO_DATA))) {
					list.add(band);
					getNodeChildren(node, sList);
					// }
					continue;
				}
				sList.add(node);
			}
		}
		list.addAll(sList);
		return list;
	}

	/**
	 * Gets the node children.
	 * 
	 * @param node
	 *          the node
	 * @param list
	 *          the list
	 * @return the node children
	 */
	private void getNodeChildren(INode node, List<Object> list) {
		for (INode nod : node.getChildren()) {
			if (nod instanceof IGraphicElement)
				list.add(nod);
			getNodeChildren(nod, list);
		}
	}

	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		if (arg0.getSource() instanceof MReport) {
			ANode model = (ANode) getModel();
			if (model.getChildren() != null)
				for (Object node : getModelChildren()) {
					if (node instanceof INode) {
						EditPart ep = (EditPart) getViewer().getEditPartRegistry().get(node);
						if (ep instanceof FigureEditPart)
							((FigureEditPart) ep).propertyChange(arg0);
					}
				}
		}
		super.propertyChange(arg0);
	}
}
