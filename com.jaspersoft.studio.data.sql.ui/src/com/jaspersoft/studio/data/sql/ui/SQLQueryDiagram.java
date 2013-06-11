package com.jaspersoft.studio.data.sql.ui;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;

import com.jaspersoft.studio.data.sql.SQLQueryDesigner;
import com.jaspersoft.studio.editor.java2d.J2DLightweightSystem;

public class SQLQueryDiagram {
	private SQLQueryDesigner designer;

	public SQLQueryDiagram(SQLQueryDesigner designer) {
		this.designer = designer;
	}

	public Control createDiagram(Composite parent) {
		Canvas square = new Canvas(parent, SWT.NO_REDRAW_RESIZE | SWT.NO_BACKGROUND | SWT.BORDER);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.verticalSpan = 2;
		square.setLayoutData(gd);

		J2DLightweightSystem lws = new J2DLightweightSystem();
		lws.setControl(square);
		Figure parentFigure = new Figure();
		parentFigure.setLayoutManager(new XYLayout());
		lws.setContents(parentFigure);

		return square;
	}

	protected void refreshViewer() {
		Display.getDefault().asyncExec(new Runnable() {

			@Override
			public void run() {

			}
		});
	}

	public void scheduleRefresh() {

	}

	public void dispose() {

	}
}
