/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2010 Jaspersoft Corporation. All
 * rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of Jaspersoft Open Studio.
 * 
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify it under the terms of the GNU Affero
 * General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * Jaspersoft Open Studio is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with Jaspersoft Open Studio. If not,
 * see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.editor.gef.figures;

import net.sf.jasperreports.engine.JRComponentElement;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.draw2d.IFigure;

import com.jaspersoft.studio.ExtensionManager;
import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.MEllipse;
import com.jaspersoft.studio.model.MFrame;
import com.jaspersoft.studio.model.MImage;
import com.jaspersoft.studio.model.MLine;
import com.jaspersoft.studio.model.MRectangle;
import com.jaspersoft.studio.model.MSubreport;
import com.jaspersoft.studio.model.genericElement.MGenericElement;
import com.jaspersoft.studio.model.text.MStaticText;
import com.jaspersoft.studio.model.text.MTextField;

/**
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
		} else
			return new org.eclipse.draw2d.RectangleFigure();
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
