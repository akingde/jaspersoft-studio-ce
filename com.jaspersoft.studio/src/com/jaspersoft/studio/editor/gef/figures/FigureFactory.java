/*******************************************************************************
 * Copyright (C) 2010 - 2012 Jaspersoft Corporation. All rights reserved.
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
package com.jaspersoft.studio.editor.gef.figures;

import net.sf.jasperreports.engine.JRComponentElement;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.XYLayout;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.MEllipse;
import com.jaspersoft.studio.model.MLine;
import com.jaspersoft.studio.model.MRectangle;
import com.jaspersoft.studio.model.frame.MFrame;
import com.jaspersoft.studio.model.genericElement.MGenericElement;
import com.jaspersoft.studio.model.image.MImage;
import com.jaspersoft.studio.model.subreport.MSubreport;
import com.jaspersoft.studio.model.text.MStaticText;
import com.jaspersoft.studio.model.text.MTextField;
import com.jaspersoft.studio.plugin.ExtensionManager;

/*
 * A factory for creating Figure objects.
 * 
 * @author Chicu Veaceslav
 */
public class FigureFactory {

	/**
	 * Creates a new Figure object.
	 * 
	 * @param node
	 *          the node
	 * @return the i figure
	 */
	public static IFigure createFigure(final ANode node) {
		ExtensionManager m = JaspersoftStudioPlugin.getExtensionManager();
		IFigure f = m.createFigure(node);
		if (f != null)
			return f;
		if (node instanceof MEllipse) {
			return new EllipseFigure();
		} else if (node instanceof MRectangle) {
			return new RectangleFigure();
		} else if (node instanceof MStaticText) {
			return new StaticTextFigure();
		} else if (node instanceof MTextField) {
			return new TextFieldFigure();
		} else if (node instanceof MLine) {
			return new LineFigure();
		} else if (node instanceof MFrame) {
			return new FrameFigure();
		} else if (node instanceof MImage) {
			return new ImageFigure();
		} else if (node instanceof MSubreport) {
			return new SubreportFigure();
		} else if (node instanceof MGenericElement) {
			return new GenericElementFigure();
		} else if (node.getValue() instanceof JRComponentElement) {
			return new ComponentFigure();
		} else {
			org.eclipse.draw2d.RectangleFigure rfig = new org.eclipse.draw2d.RectangleFigure();
			rfig.setLayoutManager(new XYLayout());
			return rfig;
		}
	}

	/**
	 * Creates a new Figure object.
	 * 
	 * @param jdesign
	 *          the jdesign
	 * @return the page figure
	 */
	public static ReportPageFigure createNewPage(JasperDesign jdesign) {
		ReportPageFigure page = new ReportPageFigure(jdesign, true);
		return page;
	}

}
