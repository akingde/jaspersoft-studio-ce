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
package com.jaspersoft.studio.editor.style;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.JasperReportsContext;
import net.sf.jasperreports.engine.convert.ReportConverter;
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

/*
 * A factory for creating JasperDesignEditPart objects.
 * 
 * @author Chicu Veaceslav
 */
public class StyleEditPartFactory implements EditPartFactory {
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
		if (model != null && model instanceof ANode)
			jrContext = ((ANode) model).getJasperConfiguration();
		if (jrContext == null && context != null) {
			EditPartViewer gv = context.getViewer();
			Object prop = gv.getProperty("JRCONTEXT");
			if (prop != null && prop instanceof JasperReportsContext) {
				jrContext = (JasperReportsContext) prop;
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
				((FigureEditPart) editPart).setDrawVisitor(getDrawVisitor((ANode) model));
		}
		return editPart;
	}
}
