/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.gef.parts.text;

import org.eclipse.draw2d.FigureCanvas;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.tools.CellEditorLocator;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Shell;

final public class LabelCellEditorLocator implements CellEditorLocator {

	private IFigure figure;

	public LabelCellEditorLocator(IFigure figure) {
		this.figure = figure;
	}

	/**
	 * Calculate the absolute position of the the figure in the application window,
	 * to place the shell correctly. The shell must have in the data field the {@link FigureCanvas}
	 * of the editor
	 */
	public void relocate(CellEditor celleditor) {
		Shell text = (Shell) celleditor.getControl();
		FigureCanvas canvas = (FigureCanvas)text.getData();

    org.eclipse.draw2d.geometry.Rectangle r =  figure.getBounds().getCopy();
    if (r.height < 10) r.height = 10;
    if (r.width < 10) r.width = 10;
    figure.translateToAbsolute(r);

    Rectangle swtRect = new Rectangle(r.x, r.y, r.width + 100, r.height + 30);
    text.setBounds(canvas.getDisplay().map(canvas, null, swtRect));
	}

}
