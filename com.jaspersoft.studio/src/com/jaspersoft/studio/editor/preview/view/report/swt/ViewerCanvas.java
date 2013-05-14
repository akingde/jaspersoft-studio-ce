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

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.ImageMapRenderable;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRPrintAnchorIndex;
import net.sf.jasperreports.engine.JRPrintElement;
import net.sf.jasperreports.engine.JRPrintHyperlink;
import net.sf.jasperreports.engine.JRPrintImage;
import net.sf.jasperreports.engine.JRPrintImageAreaHyperlink;
import net.sf.jasperreports.engine.JRPrintPage;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReportsContext;
import net.sf.jasperreports.engine.Renderable;
import net.sf.jasperreports.engine.export.JRGraphics2DExporter;
import net.sf.jasperreports.engine.export.JRGraphics2DExporterParameter;
import net.sf.jasperreports.engine.type.HyperlinkTypeEnum;
import net.sf.jasperreports.view.JRHyperlinkListener;

import org.eclipse.core.runtime.Assert;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.PaletteData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.ScrollBar;

/**
 * SWT custom component capable of displaying a JasperPrint document page.
 * 
 * @author Peter Severin (peter_p_s@users.sourceforge.net)
 */
class ViewerCanvas extends Canvas {

	/**
	 * Margin width around the report.
	 */
	public static final int MARGIN = 5;

	/**
	 * Cursor shown while dragging the page
	 */
	private static final Cursor CURSOR_SIZEALL = new Cursor(null, SWT.CURSOR_SIZEALL);

	/**
	 * Cursor shown over hyperlinks
	 */
	private static final Cursor CURSOR_HAND = new Cursor(null, SWT.CURSOR_HAND);

	/**
	 * The SWT palette that maps the ARGB AWT image color model
	 */
	private static final PaletteData palette = new PaletteData(0x00FF0000, 0x0000FF00, 0x000000FF);

	private JRPrintPage page;

	private List<IHyperlinkContainer> hyperlinkElements = new ArrayList<IHyperlinkContainer>();

	private Image reportImage;

	private String errorMessage;

	private boolean dragging;

	private JRPrintHyperlink currentLink;

	private int dragSelectionX;

	private int dragSelectionH;

	private int dragSelectionY;

	private int dragSelectionV;

	private IReportViewer viewer;

	private Listener eventListener = new Listener() {
		public void handleEvent(Event event) {
			ViewerCanvas.this.handleEvent(event);
		}
	};

	private IReportViewerListener listener = new IReportViewerListener() {
		public void viewerStateChanged(EventObject evt) {
			refresh();
		}
	};
	private JasperReportsContext jContext;

	ViewerCanvas(Composite parent, int style, JasperReportsContext jContext) {
		super(parent, style | SWT.H_SCROLL | SWT.V_SCROLL);
		this.jContext = jContext;
		addListener(SWT.Paint, eventListener);
		addListener(SWT.MouseMove, eventListener);
		addListener(SWT.MouseDown, eventListener);
		addListener(SWT.MouseUp, eventListener);
		addListener(SWT.KeyDown, eventListener);
		addListener(SWT.Traverse, eventListener);
		addListener(SWT.Resize, eventListener);
		addListener(SWT.Dispose, eventListener);

		ScrollBar sb = getHorizontalBar();
		sb.setIncrement(20);
		sb.addListener(SWT.Selection, eventListener);

		sb = getVerticalBar();
		sb.setIncrement(20);
		sb.addListener(SWT.Selection, eventListener);
	}

	/**
	 * @param event
	 */
	protected void handleEvent(Event event) {
		if (isDisposed())
			return;

		switch (event.type) {
		case SWT.Paint:
			paint(event.gc);
			break;
		case SWT.MouseMove:
			onMouseMove(event);
			break;
		case SWT.MouseDown:
			onMouseDown(event);
			break;
		case SWT.MouseUp:
			onMouseUp(event);
			break;
		case SWT.KeyDown:
			onKeyDown(event);
			break;
		case SWT.Traverse:
			event.doit = true;
			break;
		case SWT.Resize:
			resize();
			break;
		case SWT.Selection:
			repaint();
			break;
		case SWT.Dispose:
			onDispose();
			break;
		}
	}

	private void onKeyDown(Event event) {
		switch (event.keyCode) {
		case SWT.ARROW_UP: {
			ScrollBar sb = getVerticalBar();
			int selection = sb.getSelection();
			setVerticalSelection(selection - sb.getIncrement());
			if (sb.getSelection() == selection) {
				if (viewer.canGotoPreviousPage()) {
					viewer.gotoPreviousPage();
					setVerticalSelection(sb.getMaximum());
					repaint();
				}
			} else {
				repaint();
			}
			break;
		}
		case SWT.ARROW_DOWN: {
			ScrollBar sb = getVerticalBar();
			int selection = sb.getSelection();
			setVerticalSelection(selection + sb.getIncrement());
			if (sb.getSelection() == selection) {
				if (viewer.canGotoNextPage()) {
					viewer.gotoNextPage();
					setVerticalSelection(sb.getMinimum());
					repaint();
				}
			} else {
				repaint();
			}
			break;
		}
		case SWT.ARROW_LEFT:
			setHorizontalSelection(getHorizontalBar().getSelection() - getHorizontalBar().getIncrement());
			repaint();
			break;
		case SWT.ARROW_RIGHT:
			setHorizontalSelection(getHorizontalBar().getSelection() + getHorizontalBar().getIncrement());
			repaint();
			break;
		case SWT.PAGE_UP: {
			ScrollBar sb = getVerticalBar();
			int selection = sb.getSelection();
			setVerticalSelection(selection - sb.getPageIncrement());
			if (sb.getSelection() == selection) {
				if (viewer.canGotoPreviousPage()) {
					viewer.gotoPreviousPage();
					setVerticalSelection(sb.getMaximum());
					repaint();
				}
			} else {
				repaint();
			}
			break;
		}
		case SWT.PAGE_DOWN: {
			ScrollBar sb = getVerticalBar();
			int selection = sb.getSelection();
			setVerticalSelection(selection + sb.getPageIncrement());
			if (sb.getSelection() == selection) {
				if (viewer.canGotoNextPage()) {
					viewer.gotoNextPage();
					setVerticalSelection(sb.getMinimum());
					repaint();
				}
			} else {
				repaint();
			}
			break;
		}
		case SWT.HOME:
			if (viewer.canGotoFirstPage()) {
				viewer.gotoFirstPage();
			}
			setVerticalSelection(getVerticalBar().getMinimum());
			repaint();
			break;
		case SWT.END:
			if (viewer.canGotoLastPage()) {
				viewer.gotoLastPage();
			}
			break;
		}
	}

	/**
	 * Handles the resize of the canvas. This method is intented to be overriden to plug-in additional resize handling.
	 */
	protected void resize() {
		// remove the hyperlink cursor if any
		if (currentLink != null) {
			currentLink = null;
			setCursor(null);
			setToolTipText(null);
		}
		updateScrollbars();
	}

	private void repaint() {
		if (!isDisposed()) {
			GC gc = new GC(this);
			paint(gc);
			gc.dispose();
		}
	}

	/**
	 * @see org.eclipse.swt.widgets.Composite#computeSize(int, int, boolean)
	 */
	public Point computeSize(int wHint, int hHint, boolean changed) {
		Point size = new Point(0, 0);
		Rectangle contentBounds = getContentBounds();

		if (wHint != SWT.DEFAULT) {
			size.x = wHint;
		} else {
			if (contentBounds.width > 0)
				size.x = contentBounds.width;
			else
				size.x = 64 + 2 * MARGIN;
		}

		if (hHint != SWT.DEFAULT) {
			size.y = hHint;
		} else {
			if (contentBounds.height > 0)
				size.y = contentBounds.height;
			else
				size.y = 64 + 2 * MARGIN;
		}

		Rectangle trim = computeTrim(0, 0, size.x, size.y);
		return new Point(trim.width, trim.height);
	}

	/**
     *  
     */
	protected void refresh() {
		if (this.reportImage != null) {
			this.reportImage.dispose();
			this.reportImage = null;
		}

		// remove the hyperlink cursor if any
		if (currentLink != null) {
			currentLink = null;
			setCursor(null);
			setToolTipText(null);
		}

		if (viewer == null) {
			refresh(null, null, null, g2d);
		} else if (viewer.hasDocument()) {
			try {
				if (viewer.getDocument().getPages().isEmpty()) {
					refresh(null, "Document is Empty", null, g2d);
				} else {
					Image image = renderPage();
					refresh(image, null, (JRPrintPage) viewer.getDocument().getPages().get(viewer.getPageIndex()), g2d);
				}
			} catch (JRException e) {
				refresh(null, e.getMessage(), null, g2d);
			}
		} else {
			refresh(null, "No document", null, g2d);
		}

		setFocus();
	}

	private Image renderPage() throws JRException {
		JasperPrint jasperPrint = viewer.getDocument();

		int imageWidth = (int) (jasperPrint.getPageWidth() * viewer.getZoom()) + 1;
		int imageHeight = (int) (jasperPrint.getPageHeight() * viewer.getZoom()) + 1;
		BufferedImage awtImage = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);

		g2d = (Graphics2D) awtImage.getGraphics();
		try {
			JRGraphics2DExporter exporter = new JRGraphics2DExporter(jContext);
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRGraphics2DExporterParameter.GRAPHICS_2D, g2d);
			exporter.setParameter(JRExporterParameter.PAGE_INDEX, new Integer(viewer.getPageIndex()));
			exporter.setParameter(JRGraphics2DExporterParameter.ZOOM_RATIO, new Float(viewer.getZoom()));
			exporter.exportReport();
		} finally {
			g2d.dispose();
		}

		// draw border
		g2d = (Graphics2D) awtImage.getGraphics();
		try {
			g2d.setColor(Color.black);
			g2d.setStroke(new BasicStroke(1));
			g2d.drawRect(0, 0, imageWidth - 1, imageHeight - 1);
		} finally {
			g2d.dispose();
		}

		int[] data = ((DataBufferInt) awtImage.getData().getDataBuffer()).getData();
		ImageData imageData = new ImageData(awtImage.getWidth(), awtImage.getHeight(), 32, palette);
		imageData.setPixels(0, 0, data.length, data, 0);
		return new Image(getDisplay(), imageData);
	}

	/**
	 * Attaches the report viewer to the canvas
	 * 
	 * @param viewer
	 *          the viewer
	 */
	public void setReportViewer(IReportViewer viewer) {
		Assert.isTrue(this.viewer == null);
		Assert.isNotNull(viewer);

		this.viewer = viewer;
		if (viewer != null)
			viewer.addReportViewerListener(listener);

		refresh();
	}

	private void paint(GC gc) {
		if (isDisposed())
			return;

		if (reportImage != null) {
			Rectangle bounds = reportImage.getBounds();
			Rectangle clientArea = getClientArea();

			int x;
			if (bounds.width <= clientArea.width)
				x = (clientArea.width - bounds.width) / 2;
			else {
				x = -getHorizontalBar().getSelection() + MARGIN;

				if (x > 0) {
					// draw left margin
					gc.fillRectangle(0, 0, x, clientArea.height);
				}

				if (x + bounds.width < clientArea.width) {
					// draw right margin
					gc.fillRectangle(x + bounds.width, 0, clientArea.width - x - bounds.width, bounds.height);
				}
			}

			int y;
			if (bounds.height <= clientArea.height)
				y = (clientArea.height - bounds.height) / 2;
			else {
				y = -getVerticalBar().getSelection() + MARGIN;

				if (y > 0) {
					// draw top margin
					gc.fillRectangle(0, 0, clientArea.width, y);
				}

				if (y + bounds.height < clientArea.height) {
					// draw bottom margin
					gc.fillRectangle(0, y + bounds.height, clientArea.width, clientArea.height - y - bounds.height);
				}
			}
			gc.drawImage(reportImage, x, y);
		} else if (errorMessage != null) {
			Rectangle bounds = getContentBounds();
			Rectangle clientArea = getClientArea();

			int x = 0;
			if (bounds.width > clientArea.width) {
				x = -getHorizontalBar().getSelection();
			}

			int y = 0;
			if (bounds.height > clientArea.height) {
				y = -getVerticalBar().getSelection();
			}

			gc.fillRectangle(clientArea);
			gc.setForeground(getDisplay().getSystemColor(SWT.COLOR_RED));
			gc.drawText(errorMessage, x + MARGIN, y + MARGIN);
		}

	}

	private void refresh(Image reportImage, String errorMessage, JRPrintPage page, Graphics2D grx) {
		if (isDisposed())
			return;

		this.reportImage = reportImage;
		this.errorMessage = errorMessage;
		this.page = page;

		// remove the hyperlink cursor if any
		if (currentLink != null) {
			currentLink = null;
			setCursor(null);
			setToolTipText(null);
		}

		initializeHypelinks(grx);

		getHorizontalBar().setSelection(0);
		getVerticalBar().setSelection(0);
		updateScrollbars();
		redraw();
	}

	boolean updatingScrollbars;

	private Graphics2D g2d;

	private void updateScrollbars() {
		if (!updatingScrollbars) {
			updatingScrollbars = true;
			try {
				updateScrollbarsSafe();
			} finally {
				updatingScrollbars = false;
			}
		}
	}

	private void updateScrollbarsSafe() {
		Rectangle bounds = getContentBounds();

		ScrollBar hBar = getHorizontalBar();
		ScrollBar vBar = getVerticalBar();
		boolean hVisible = needHScroll(bounds.width, false);
		boolean vVisible = needVScroll(bounds.height, hVisible);
		if (!hVisible && vVisible)
			hVisible = needHScroll(bounds.width, vVisible);
		if (hBar != null) {
			hBar.setVisible(hVisible);
			if (!hVisible)
				hBar.setSelection(0);
		}

		if (vBar != null) {
			vBar.setVisible(vVisible);
			if (!vVisible)
				vBar.setSelection(0);
		}

		Point size = getSize();
		Rectangle clientArea = getClientArea();

		if (hVisible) {
			hBar.setPageIncrement(clientArea.width - hBar.getIncrement());
			int max = bounds.width + (size.x - clientArea.width);
			hBar.setMaximum(max);
			hBar.setThumb(size.x > max ? max : size.x);
		}

		if (vVisible) {
			vBar.setPageIncrement(clientArea.height - vBar.getIncrement());
			int max = bounds.height + (size.y - clientArea.height);
			vBar.setMaximum(max);
			vBar.setThumb(size.y > max ? max : size.y);
		}
	}

	private void onDispose() {
		if (reportImage != null)
			reportImage.dispose();
	}

	/**
	 * Calculates the available size that can be used to render the content. The calculated size does not include the size
	 * of scroll bars that if visible can reduce the available size.
	 * 
	 * @return the calculated size
	 */
	public Point getFitSize() {
		Rectangle bounds = getBounds();
		int borderWidth = getBorderWidth();
		return new Point(bounds.width - 2 * borderWidth - 2 * MARGIN, bounds.height - 2 * borderWidth - 2 * MARGIN);
	}

	/**
	 * Calculates the available size that can be used to render the content of the specified width and height. This method
	 * determines whether the scrollbars will be visible and adjusts the available size accordingly.
	 * 
	 * @param contentWidth
	 *          the desired content width
	 * @param contentHeight
	 *          the desired content height
	 * @return the calculated size
	 */
	public Point getFitSize(int contentWidth, int contentHeight) {
		Rectangle bounds = getBounds();
		int borderWidth = getBorderWidth();
		Point size = new Point(bounds.width - 2 * borderWidth - 2 * MARGIN, bounds.height - 2 * borderWidth - 2 * MARGIN);
		boolean vBarVisible = false;
		boolean hBarVisible = false;

		if (contentWidth > size.x) {
			size.y -= getHorizontalBar().getSize().y;
			hBarVisible = true;
		}

		if (contentHeight > size.y) {
			size.x -= getVerticalBar().getSize().x;
			vBarVisible = true;
		}

		if (vBarVisible && !hBarVisible) {
			if (contentWidth > size.x)
				size.y -= getHorizontalBar().getSize().y;
		}

		return size;
	}

	private void onMouseDown(Event event) {
		if (reportImage != null) {
			if (event.button == 1) {
				if (currentLink == null) {
					dragging = true;
					dragSelectionX = event.x;
					dragSelectionH = getHorizontalBar().getSelection();
					dragSelectionY = event.y;
					dragSelectionV = getVerticalBar().getSelection();
					setCursor(CURSOR_SIZEALL);
				}
			}
		}
	}

	private void onMouseMove(Event event) {
		if (reportImage != null) {
			if (dragging) {
				setHorizontalSelection(dragSelectionH - event.x + dragSelectionX);
				setVerticalSelection(dragSelectionV - event.y + dragSelectionY);
				repaint();
			} else {
				updateHyperlink(event.x, event.y);
			}
		}
	}

	private void updateHyperlink(int x, int y) {
		JRPrintHyperlink link = getHyperlinkAt(x, y);
		if (link != null) {
			if (currentLink == null || currentLink != link) {
				currentLink = link;
				setCursor(CURSOR_HAND);
				setToolTipText(getLinkToolTip(link));
			}
		} else if (currentLink != null) {
			currentLink = null;
			setCursor(null);
			setToolTipText(null);
		}
	}

	private void onMouseUp(Event event) {
		if (reportImage != null) {
			if (dragging) {
				dragging = false;
				setCursor(null);
			}

			if (event.button == 1) {
				if (currentLink != null && currentLink == getHyperlinkAt(event.x, event.y)) {
					// handle click
					setCursor(null);
					try {
						BusyIndicator.showWhile(getDisplay(), new Runnable() {
							public void run() {
								handleHyperlinkClick();
							}
						});
					} finally {
						setCursor(CURSOR_HAND);
					}
				}
			}

			// if jumped to a different page image can be missing
			if (reportImage != null)
				updateHyperlink(event.x, event.y);
			else {
				currentLink = null;
				setCursor(null);
			}
		}
	}

	private void handleHyperlinkClick() {
		JRPrintHyperlink link = currentLink;

		if (link.getHyperlinkTypeValue().equals(HyperlinkTypeEnum.REFERENCE)) {
			notifyHyperlinkListeners(link);
		} else if (link.getHyperlinkTypeValue().equals(HyperlinkTypeEnum.LOCAL_ANCHOR)) {
			Map<String, JRPrintAnchorIndex> anchorIndexes = viewer.getDocument().getAnchorIndexes();
			JRPrintAnchorIndex anchorIndex = anchorIndexes.get(currentLink.getHyperlinkAnchor());
			if (anchorIndex != null) {
				if (anchorIndex.getPageIndex() != viewer.getPageIndex()) {
					viewer.setPageIndex(anchorIndex.getPageIndex());
				}

				if (reportImage != null) {
					setHorizontalSelection((int) (anchorIndex.getElement().getX() * viewer.getZoom()));
					setVerticalSelection((int) (anchorIndex.getElement().getY() * viewer.getZoom()));
					repaint();
				}
			}
		} else if (link.getHyperlinkTypeValue().equals(HyperlinkTypeEnum.LOCAL_PAGE)) {
			if (link.getHyperlinkPage() != null) {
				int page = link.getHyperlinkPage().intValue();
				viewer.setPageIndex(page - 1);
			}
		} else if (link.getHyperlinkTypeValue().equals(HyperlinkTypeEnum.REMOTE_ANCHOR)) {
			notifyHyperlinkListeners(link);
		} else if (link.getHyperlinkTypeValue().equals(HyperlinkTypeEnum.REMOTE_PAGE)) {
			notifyHyperlinkListeners(link);
		} else if (link.getHyperlinkTypeValue().equals(HyperlinkTypeEnum.CUSTOM)) {
			notifyHyperlinkListeners(link);
		}
	}

	private void setHorizontalSelection(int selection) {
		ScrollBar sb = getHorizontalBar();
		sb.setSelection(Math.max(sb.getMinimum(), Math.min(selection, sb.getMaximum())));
	}

	private void setVerticalSelection(int selection) {
		ScrollBar sb = getVerticalBar();
		sb.setSelection(Math.max(sb.getMinimum(), Math.min(selection, sb.getMaximum())));
	}

	private boolean needHScroll(int contentWidth, boolean vVisible) {
		ScrollBar hBar = getHorizontalBar();
		if (hBar == null)
			return false;

		Rectangle hostRect = getBounds();
		int border = getBorderWidth();
		hostRect.width -= 2 * border;
		ScrollBar vBar = getVerticalBar();
		if (vVisible && vBar != null)
			hostRect.width -= vBar.getSize().x;

		return contentWidth > hostRect.width;
	}

	private boolean needVScroll(int contentHeight, boolean hVisible) {
		ScrollBar vBar = getVerticalBar();
		if (vBar == null)
			return false;

		Rectangle hostRect = getBounds();
		int border = getBorderWidth();
		hostRect.height -= 2 * border;
		ScrollBar hBar = getHorizontalBar();
		if (hVisible && hBar != null)
			hostRect.height -= hBar.getSize().y;

		return contentHeight > hostRect.height;
	}

	private Rectangle getContentBounds() {
		if (reportImage != null) {
			Rectangle bounds = reportImage.getBounds();
			bounds.width += 2 * MARGIN;
			bounds.height += 2 * MARGIN;
			return bounds;
		} else if (errorMessage != null) {
			GC gc = new GC(this);
			Point extent = gc.textExtent(errorMessage);
			gc.dispose();

			return new Rectangle(0, 0, extent.x + 2 * MARGIN, extent.y + 2 * MARGIN);
		}

		return new Rectangle(0, 0, 0, 0);
	}

	private Point translateRelativeToPage(int x, int y) {
		Rectangle bounds = reportImage.getBounds();
		Rectangle clientArea = getClientArea();

		int originX;
		if (bounds.width <= clientArea.width)
			originX = (clientArea.width - bounds.width) / 2;
		else
			originX = -getHorizontalBar().getSelection() + MARGIN;

		int originY;
		if (bounds.height <= clientArea.height)
			originY = (clientArea.height - bounds.height) / 2;
		else
			originY = -getVerticalBar().getSelection() + MARGIN;

		return new Point((int) ((x - originX - clientArea.x) / viewer.getZoom()),
				(int) ((y - originY - clientArea.y) / viewer.getZoom()));
	}

	private JRPrintHyperlink getHyperlinkAt(int x, int y) {
		Point point = translateRelativeToPage(x, y);
		JRPrintHyperlink hyperlink = null;

		for (int i = hyperlinkElements.size() - 1; i >= 0 && hyperlink == null; i--) {
			IHyperlinkContainer hlink = hyperlinkElements.get(i);
			hyperlink = hlink.getHyperlink(point);
		}

		return hyperlink;
	}

	private void initializeHypelinks(Graphics2D grx) {
		hyperlinkElements.clear();

		if (page != null) {
			List<JRPrintElement> elements = page.getElements();
			try {
				initializeHyperlinks(0, 0, elements, grx);
			} catch (JRException e) {
				throw new RuntimeException(e);
			}
		}
	}

	private void initializeHyperlinks(int originX, int originY, List<JRPrintElement> elements, Graphics2D grx)
			throws JRException {
		if (elements != null) {
			for (JRPrintElement element : elements) {
				ImageMapRenderable ir = getImageMapRenderer(element);
				if (ir != null) {
					List<JRPrintImageAreaHyperlink> hyperlinks = ir.renderWithHyperlinks(grx, new java.awt.Rectangle(0, 0,
							element.getWidth(), element.getHeight()));
					if (hyperlinks != null)
						hyperlinkElements
								.add(new ImageAreaHyperlink(originX + element.getX(), originY + element.getY(), hyperlinks));
				} else if (element instanceof JRPrintHyperlink
						&& !((JRPrintHyperlink) element).getHyperlinkTypeValue().equals(HyperlinkTypeEnum.NONE)) {
					hyperlinkElements.add(new PrintHyperlink(originX, originY, element));
				}
			}
		}
	}

	private static ImageMapRenderable getImageMapRenderer(JRPrintElement element) {
		if (element instanceof JRPrintImage) {
			Renderable renderer = ((JRPrintImage) element).getRenderable();
			if (renderer instanceof ImageMapRenderable)
				return (ImageMapRenderable) renderer;
		}

		return null;
	}

	private static interface IHyperlinkContainer {
		public JRPrintHyperlink getHyperlink(Point point);
	}

	private static class PrintHyperlink implements IHyperlinkContainer {
		private final int x;

		private final int y;

		private final JRPrintElement element;

		public PrintHyperlink(int originX, int originY, JRPrintElement element) {
			this.x = originX + element.getX();
			this.y = originY + element.getY();
			this.element = element;
		}

		private boolean inside(Point point) {
			return (point.x >= x && point.x < x + element.getWidth() && point.y >= y && point.y < y + element.getHeight());
		}

		public JRPrintHyperlink getHyperlink(Point point) {
			if (inside(point))
				return (JRPrintHyperlink) element;

			return null;
		}
	}

	private static class ImageAreaHyperlink implements IHyperlinkContainer {
		private final int originX;

		private final int originY;

		private final List<JRPrintImageAreaHyperlink> imageAreaHyperlinks;

		public ImageAreaHyperlink(int originX, int originY, List<JRPrintImageAreaHyperlink> imageAreaHyperlinks) {
			this.originX = originX;
			this.originY = originY;
			this.imageAreaHyperlinks = imageAreaHyperlinks;
		}

		public JRPrintHyperlink getHyperlink(Point point) {
			final int x = point.x - originX;
			final int y = point.y - originY;

			for (JRPrintImageAreaHyperlink areaHyperlink : imageAreaHyperlinks) {
				if (areaHyperlink.getArea().containsPoint(x, y))
					return areaHyperlink.getHyperlink();
			}

			return null;
		}
	}

	private String getLinkToolTip(JRPrintHyperlink link) {
		String toolTip = null;

		toolTip = link.getHyperlinkTooltip();

		if (toolTip != null)
			return toolTip;

		if (link.getHyperlinkTypeValue().equals(HyperlinkTypeEnum.REFERENCE)) {
			toolTip = link.getHyperlinkReference();
		} else if (link.getHyperlinkTypeValue().equals(HyperlinkTypeEnum.LOCAL_ANCHOR)) {
			if (link.getHyperlinkAnchor() != null)
				toolTip = "#" + link.getHyperlinkAnchor(); //$NON-NLS-1$
		} else if (link.getHyperlinkTypeValue().equals(HyperlinkTypeEnum.LOCAL_PAGE)) {
			if (link.getHyperlinkPage() != null)
				toolTip = "#page " + link.getHyperlinkPage(); //$NON-NLS-1$
		} else if (link.getHyperlinkTypeValue().equals(HyperlinkTypeEnum.REMOTE_ANCHOR)) {
			if (link.getHyperlinkReference() != null || link.getHyperlinkAnchor() != null) {
				toolTip = ""; //$NON-NLS-1$
				if (link.getHyperlinkReference() != null)
					toolTip = toolTip + link.getHyperlinkReference();
				if (currentLink.getHyperlinkAnchor() != null)
					toolTip = toolTip + "#" + currentLink.getHyperlinkAnchor(); //$NON-NLS-1$
			}
		} else if (link.getHyperlinkTypeValue().equals(HyperlinkTypeEnum.REMOTE_PAGE)) {
			if (link.getHyperlinkReference() != null || link.getHyperlinkPage() != null) {
				toolTip = ""; //$NON-NLS-1$
				if (link.getHyperlinkReference() != null)
					toolTip = toolTip + link.getHyperlinkReference();
				if (link.getHyperlinkPage() != null)
					toolTip = toolTip + "#page " + link.getHyperlinkPage(); //$NON-NLS-1$
			}
		}

		return toolTip;
	}

	private void notifyHyperlinkListeners(JRPrintHyperlink link) {
		JRHyperlinkListener[] listeners = viewer.getHyperlinkListeners();
		for (int i = 0; i < listeners.length; i++) {
			try {
				listeners[i].gotoHyperlink(link);
			} catch (JRException e) {
				throw new RuntimeException(e);
			}
		}
	}
	
	/**
	 * Return the current report image
	 * 
	 * @return return an image of the actual shown page, could be null
	 */
	public Image getActualImage(){
		return reportImage;
	}

}
