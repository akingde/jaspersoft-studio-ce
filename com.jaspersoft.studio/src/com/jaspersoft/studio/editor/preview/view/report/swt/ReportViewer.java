/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer.
 * Copyright (C) 2005 - 2010 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of Jaspersoft Open Studio.
 *
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Jaspersoft Open Studio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with Jaspersoft Open Studio. If not, see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.editor.preview.view.report.swt;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.EventListenerList;

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JRHyperlinkListener;

import org.eclipse.core.runtime.Assert;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.jasperassistant.designer.viewer.ReportViewerEvent;

public class ReportViewer implements IReportViewer {

	private EventListenerList listenerList = new EventListenerList();

	private JasperPrint document;

	private double zoom = 1.0f;

	private int pageIndex;

	private int style;

	private ViewerCanvas viewerComposite;

	private List<JRHyperlinkListener> hyperlinkListeners;

	/**
	 * Default constructor. The default style will be used for the SWT control associated to the viewer.
	 */
	public ReportViewer() {
		this(SWT.NONE);
	}

	/**
	 * Constructor that allows to specify a SWT control style. For possible styles see the
	 * {@link org.eclipse.swt.widgets.Canvas} class. Most frequently you will wont to specify the
	 * <code>SWT.NONE<code> style.
	 * 
	 * @param style
	 *          the style
	 */
	public ReportViewer(int style) {
		this.style = style;
	}

	/**
	 * @see com.jasperassistant.designer.viewer.IReportViewer#setDocument(net.sf.jasperreports.engine.JasperPrint)
	 */
	public void setDocument(JasperPrint document) {
		Assert.isNotNull(document, "ReportViewer.documentNotNull"); //$NON-NLS-1$
		Assert.isNotNull(document.getPages(), "ReportViewer.documentNotEmpty"); //$NON-NLS-1$
		Assert.isTrue(!document.getPages().isEmpty(), "ReportViewer.documentNotEmpty"); //$NON-NLS-1$

		this.document = document;
		this.pageIndex = Math.min(Math.max(0, pageIndex), getPageCount() - 1);
		setZoomInternal(computeZoom());
		fireViewerModelChanged();
	}

	/**
	 * @see com.jasperassistant.designer.viewer.IReportViewer#hasDocument()
	 */
	public boolean hasDocument() {
		return document != null;
	}

	public JasperPrint getDocument() {
		return document;
	}

	public void setZoom(double zoom) {
		if (!canChangeZoom())
			return;

		if (Math.abs(zoom - getZoom()) > 0.00001) {
			setZoomInternal(zoom);
			fireViewerModelChanged();
		}
	}

	/**
	 * @see com.jasperassistant.designer.viewer.IReportViewer#canChangeZoom()
	 */
	public boolean canChangeZoom() {
		return hasDocument();
	}

	private void setZoomInternal(double zoom) {
		this.zoom = zoom;
	}

	/**
	 * @see com.jasperassistant.designer.viewer.IReportViewer#getZoom()
	 */
	public double getZoom() {
		return zoom;
	}

	private double computeZoom() {
		// switch (zoomMode) {
		// case ZOOM_MODE_ACTUAL_SIZE:
		// return 1.0;
		// case ZOOM_MODE_FIT_WIDTH: {
		// double ratio = ratio(viewerComposite.getFitSize().x, document.getPageWidth());
		// return ratio(
		// viewerComposite.getFitSize((int) (document.getPageWidth() * ratio), (int) (document.getPageHeight() * ratio)).x,
		// document.getPageWidth());
		// }
		// case ZOOM_MODE_FIT_HEIGHT: {
		// double ratio = ratio(viewerComposite.getFitSize().y, document.getPageHeight());
		// return ratio(
		// viewerComposite.getFitSize((int) (document.getPageWidth() * ratio), (int) (document.getPageHeight() * ratio)).y,
		// document.getPageHeight());
		// }
		// case ZOOM_MODE_FIT_PAGE:
		// Point fitSize = viewerComposite.getFitSize();
		// return Math.min(ratio(fitSize.x, document.getPageWidth()), ratio(fitSize.y, document.getPageHeight()));
		// }

		return zoom;
	}

	// private double ratio(int a, int b) {
	// return (a * 100 / b) / 100.0;
	// }

	private int getPageCount() {
		return document == null ? 0 : document.getPages().size();
	}

	/**
	 * @see com.jasperassistant.designer.viewer.IReportViewer#getPageIndex()
	 */
	public int getPageIndex() {
		return pageIndex;
	}

	/**
	 * @see com.jasperassistant.designer.viewer.IReportViewer#setPageIndex(int)
	 */
	public void setPageIndex(int pageIndex) {
		if (pageIndex != getPageIndex()) {
			this.pageIndex = Math.min(Math.max(0, pageIndex), getPageCount() - 1);
			fireViewerModelChanged();
		}
	}

	/**
	 * @see com.jasperassistant.designer.viewer.IReportViewer#canGotoFirstPage()
	 */
	public boolean canGotoFirstPage() {
		return hasDocument() && pageIndex > 0;
	}

	/**
	 * @see com.jasperassistant.designer.viewer.IReportViewer#gotoFirstPage()
	 */
	public void gotoFirstPage() {
		if (canGotoFirstPage()) {
			setPageIndex(0);
		}
	}

	/**
	 * @see com.jasperassistant.designer.viewer.IReportViewer#canGotoLastPage()
	 */
	public boolean canGotoLastPage() {
		return hasDocument() && pageIndex < getPageCount() - 1;
	}

	/**
	 * @see com.jasperassistant.designer.viewer.IReportViewer#gotoLastPage()
	 */
	public void gotoLastPage() {
		if (canGotoLastPage()) {
			setPageIndex(getPageCount() - 1);
		}
	}

	/**
	 * @see com.jasperassistant.designer.viewer.IReportViewer#canGotoNextPage()
	 */
	public boolean canGotoNextPage() {
		return hasDocument() && pageIndex < getPageCount() - 1;
	}

	/**
	 * @see com.jasperassistant.designer.viewer.IReportViewer#gotoNextPage()
	 */
	public void gotoNextPage() {
		if (canGotoNextPage()) {
			setPageIndex(pageIndex + 1);
		}
	}

	/**
	 * @see com.jasperassistant.designer.viewer.IReportViewer#canGotoPreviousPage()
	 */
	public boolean canGotoPreviousPage() {
		return hasDocument() && pageIndex > 0;
	}

	/**
	 * @see com.jasperassistant.designer.viewer.IReportViewer#gotoPreviousPage()
	 */
	public void gotoPreviousPage() {
		if (canGotoPreviousPage()) {
			setPageIndex(pageIndex - 1);
		}
	}

	/**
	 * @see com.jasperassistant.designer.viewer.IReportViewer#addReportViewerListener(com.jasperassistant.designer.viewer.IReportViewerListener)
	 */
	public void addReportViewerListener(IReportViewerListener listener) {
		listenerList.add(IReportViewerListener.class, listener);
	}

	/**
	 * @see com.jasperassistant.designer.viewer.IReportViewer#removeReportViewerListener(com.jasperassistant.designer.viewer.IReportViewerListener)
	 */
	public void removeReportViewerListener(IReportViewerListener listener) {
		listenerList.remove(IReportViewerListener.class, listener);
	}

	private void fireViewerModelChanged() {
		Object[] listeners = listenerList.getListenerList();
		ReportViewerEvent e = null;

		for (int i = listeners.length - 2; i >= 0; i -= 2) {
			if (listeners[i] == IReportViewerListener.class) {
				if (e == null) {
					e = new ReportViewerEvent(this);
				}
				((IReportViewerListener) listeners[i + 1]).viewerStateChanged(e);
			}
		}
	}

	/**
	 * Creates the SWT control for the report viewer. Later calls to this method will return the same instance of the
	 * control.
	 * 
	 * @param parent
	 *          the parent
	 * @return the created control
	 */
	public Control createControl(Composite parent) {
		if (viewerComposite == null) {
			viewerComposite = new ViewerCanvas(parent, style) {
				/**
				 * @see com.jasperassistant.designer.viewer.ViewerCanvas#resize()
				 */
				protected void resize() {
					setZoom(computeZoom());
					super.resize();
				}
			};
			viewerComposite.setReportViewer(this);
		}

		return viewerComposite;
	}

	/**
	 * @see com.jasperassistant.designer.viewer.IReportViewer#addHyperlinkListener(net.sf.jasperreports.view.JRHyperlinkListener)
	 */
	public void addHyperlinkListener(JRHyperlinkListener listener) {
		if (hyperlinkListeners == null) {
			hyperlinkListeners = new ArrayList<JRHyperlinkListener>();
		} else {
			hyperlinkListeners.remove(listener); // add once
		}

		hyperlinkListeners.add(listener);
	}

	/**
	 * @see com.jasperassistant.designer.viewer.IReportViewer#removeHyperlinkListener(net.sf.jasperreports.view.JRHyperlinkListener)
	 */
	public void removeHyperlinkListener(JRHyperlinkListener listener) {
		if (hyperlinkListeners != null)
			hyperlinkListeners.remove(listener);
	}

	/**
	 * @see com.jasperassistant.designer.viewer.IReportViewer#getHyperlinkListeners()
	 */
	public JRHyperlinkListener[] getHyperlinkListeners() {
		return hyperlinkListeners == null ? new JRHyperlinkListener[0] : (JRHyperlinkListener[]) hyperlinkListeners
				.toArray(new JRHyperlinkListener[hyperlinkListeners.size()]);
	}
}
