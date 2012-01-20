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
package com.jaspersoft.studio.editor.style;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.draw.DrawVisitor;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.eclipse.gef.EditPartViewer;

import com.jaspersoft.studio.editor.gef.parts.FigureEditPart;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.MRoot;
import com.jaspersoft.studio.model.style.MStyle;
import com.jaspersoft.studio.model.style.MStyleTemplateReference;
import com.jaspersoft.studio.model.style.MStylesTemplate;
import com.jaspersoft.studio.model.util.ModelVisitor;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

/*
 * A factory for creating JasperDesignEditPart objects.
 * 
 * @author Chicu Veaceslav
 */
public class StyleEditPartFactory implements EditPartFactory {
	private DrawVisitor drawVisitor;
	private JasperDesign jDesign;
	private JasperReportsConfiguration jrContext;

	public DrawVisitor getDrawVisitor(ANode model) {
		if (model == null)
			return null;
		JasperDesign tjd = model.getJasperDesign();
		if (tjd != jDesign) {
			jDesign = tjd;
			drawVisitor = new DrawVisitor(jDesign, null);
		}
		if (drawVisitor == null) {
			drawVisitor = new DrawVisitor(jDesign, null);
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
			Object prop = gv.getProperty("FILERESOLVER");
			if (prop != null && prop instanceof JasperReportsConfiguration) {
				jrContext = (JasperReportsConfiguration) prop;
			}
		}
		EditPart editPart = null;
		if (model instanceof MRoot || model instanceof MStylesTemplate)
			editPart = new StylesTemplateEditPart() {
				protected List<Object> getModelChildren() {
					final List<Object> list = new ArrayList<Object>();
					new ModelVisitor((INode) getModel()) {

						@Override
						public boolean visit(INode n) {
							if (n instanceof MStyle && n.getValue() != null)
								list.add(n);
							else if (n instanceof MStyleTemplateReference)
								return false;
							return true;
						}
					};
					return list;
				}
			};
		else if (model instanceof MStyle) {
			editPart = new StyleEditPart();
		}

		if (editPart != null) {
			editPart.setModel(model);
			if (editPart instanceof FigureEditPart)
				((FigureEditPart) editPart).setDrawVisitor(getDrawVisitor((ANode) model), jrContext);
		}
		return editPart;
	}
}
