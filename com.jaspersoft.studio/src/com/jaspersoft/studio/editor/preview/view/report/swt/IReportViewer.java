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
package com.jaspersoft.studio.editor.preview.view.report.swt;

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JRHyperlinkListener;

public interface IReportViewer {
	/** The zoom mode constant that stands for "no zoom" */
	public static final int ZOOM_MODE_NONE = 0;

	/**
	 * The zoom mode that instructs the viewer to display report pages at their actual size.
	 */
	public static final int ZOOM_MODE_ACTUAL_SIZE = 1;

	/**
	 * The zoom mode that instructs the viewer to display report pages so that they fit the available width.
	 */
	public static final int ZOOM_MODE_FIT_WIDTH = 2;

	/**
	 * The zoom mode that instructs the viewer to display report pages so that they fit the available height.
	 */
	public static final int ZOOM_MODE_FIT_HEIGHT = 3;

	/**
	 * The zoom mode that instructs the viewer to display report pages so that they fit the available width and height.
	 */
	public static final int ZOOM_MODE_FIT_PAGE = 4;

	public void setDocument(JasperPrint document);

	public boolean hasDocument();

	public JasperPrint getDocument();

	public void addReportViewerListener(IReportViewerListener listener);

	public void removeReportViewerListener(IReportViewerListener listener);

	// page navigation
	public int getPageIndex();

	public void setPageIndex(int pageIndex);

	public void gotoNextPage();

	public boolean canGotoNextPage();

	public void gotoPreviousPage();

	public boolean canGotoPreviousPage();

	public void gotoLastPage();

	public boolean canGotoLastPage();

	public void gotoFirstPage();

	public boolean canGotoFirstPage();

	// Zoom management --------------------------------------------------------------------

	public void setZoom(double zoom);

	public double getZoom();

	public boolean canChangeZoom();

	public void setZoomMode(int zoomMode);

	public int getZoomMode();

	public double[] getZoomLevels();

	public void setZoomLevels(double[] zoomLevels);

	public void zoomIn();

	public boolean canZoomIn();

	public void zoomOut();

	public boolean canZoomOut();

	// Hyperlinks --------------------------------------------------------------------

	public void addHyperlinkListener(JRHyperlinkListener listener);

	public void removeHyperlinkListener(JRHyperlinkListener listener);

	public JRHyperlinkListener[] getHyperlinkListeners();
}
