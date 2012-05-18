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

import java.util.List;

import net.sf.jasperreports.engine.JasperReportsContext;
import net.sf.jasperreports.engine.convert.ReportConverter;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.draw.DrawVisitor;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.eclipse.gef.EditPartViewer;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.editor.gef.parts.band.BandEditPart;
import com.jaspersoft.studio.editor.gef.parts.text.StaticTextFigureEditPart;
import com.jaspersoft.studio.editor.gef.parts.text.TextFieldFigureEditPart;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.IGraphicElement;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.MPage;
import com.jaspersoft.studio.model.MReport;
import com.jaspersoft.studio.model.MRoot;
import com.jaspersoft.studio.model.band.MBand;
import com.jaspersoft.studio.model.image.MImage;
import com.jaspersoft.studio.model.subreport.MSubreport;
import com.jaspersoft.studio.model.text.MStaticText;
import com.jaspersoft.studio.model.text.MTextField;
import com.jaspersoft.studio.plugin.ExtensionManager;

/*
 * A factory for creating JasperDesignEditPart objects.
 * 
 * @author Chicu Veaceslav
 */
public class JasperDesignEditPartFactory implements EditPartFactory {
	private DrawVisitor drawVisitor;
	private JasperDesign jDesign;
	private JasperReportsContext jrContext;

	public DrawVisitor getDrawVisitor(ANode model) {
		if (model == null)
			return null;
		JasperDesign tjd = model.getJasperDesign();
		if (tjd != jDesign) {
			jDesign = tjd;
			drawVisitor = new DrawVisitor(new ReportConverter(jrContext, jDesign, true), null);
		}
		if (drawVisitor == null) {
			drawVisitor = new DrawVisitor(new ReportConverter(jrContext, jDesign, true), null);
		}
		return drawVisitor;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.EditPartFactory#createEditPart(org.eclipse.gef.EditPart, java.lang.Object)
	 */
	public EditPart createEditPart(EditPart context, Object model) {
		if (context != null) {
			EditPartViewer gv = context.getViewer();
			Object prop = gv.getProperty("JRCONTEXT");
			if (prop != null && prop instanceof JasperReportsContext) {
				jrContext = (JasperReportsContext) prop;
			}
		}
		ExtensionManager m = JaspersoftStudioPlugin.getExtensionManager();
		EditPart editPart = m.createEditPart(context, model);
		if (editPart == null) {
			if (model instanceof MRoot) {
				List<INode> children = ((MRoot) model).getChildren();
				if (children != null && !children.isEmpty() && children.get(0) instanceof MReport)
					editPart = new ReportPageEditPart();
				else
					editPart = new PageEditPart();
			} else if (model instanceof MPage) {
				editPart = new PageEditPart();
			} else if (model instanceof MReport) {
				editPart = new ReportPageEditPart();
			} else if (model instanceof MBand) {
				editPart = new BandEditPart();
			} else if (model instanceof MStaticText) {
				editPart = new StaticTextFigureEditPart();
			} else if (model instanceof MTextField) {
				editPart = new TextFieldFigureEditPart();
			} else if (model instanceof MSubreport) {
				editPart = new SubreportFigureEditPart();
			} else if (model instanceof MImage) {
				editPart = new ImageFigureEditPart();

			} else if (model instanceof IGraphicElement) {
				editPart = new FigureEditPart();
			}
		}
		if (editPart != null) {
			editPart.setModel(model);
			if (editPart instanceof FigureEditPart)
				((FigureEditPart) editPart).setDrawVisitor(getDrawVisitor((ANode) model));
		}
		return editPart;
	}
}
