/*
 * JasperReports - Free Java Reporting Library. Copyright (C) 2001 - 2011 Jaspersoft Corporation. All rights reserved.
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
package com.jaspersoft.studio.components.crosstab.part.editpolicy;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Handle;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ResizableEditPolicy;
import org.eclipse.gef.handles.MoveHandle;
import org.eclipse.gef.requests.ChangeBoundsRequest;

import com.jaspersoft.studio.components.crosstab.part.CrosstabCellEditPart;
import com.jaspersoft.studio.components.table.part.TableCellEditPart;
import com.jaspersoft.studio.editor.gef.parts.handles.CellMoveHandle;
import com.jaspersoft.studio.editor.gef.parts.handles.CellResizeHandle2;
import com.jaspersoft.studio.editor.gef.util.GEFUtil;
import com.jaspersoft.studio.editor.java2d.J2DGraphics;

/*
 * The Class BandResizableEditPolicy.
 */
public class CrosstabCellResizableEditPolicy extends ResizableEditPolicy {

	/**
	 * Instantiates a new band resizable edit policy.
	 */
	public CrosstabCellResizableEditPolicy() {
		super();
		setDragAllowed(false);
	}

	@Override
	public CrosstabCellEditPart getHost() {
		return (CrosstabCellEditPart) super.getHost();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.gef.editpolicies.ResizableEditPolicy#createSelectionHandles()
	 */
	@Override
	protected List<Handle> createSelectionHandles() {
		List<Handle> list = new ArrayList<Handle>();

		GraphicalEditPart geditPart = getHost();
		list.add(new CellResizeHandle2(geditPart, PositionConstants.SOUTH));
		list.add(new CellResizeHandle2(geditPart, PositionConstants.NORTH));
		list.add(new CellResizeHandle2(geditPart, PositionConstants.EAST));
		list.add(new CellResizeHandle2(geditPart, PositionConstants.WEST));

		MoveHandle hand = new CellMoveHandle(geditPart);
		list.add(hand);

		return list;
	}

	@Override
	protected Command getResizeCommand(ChangeBoundsRequest request) {
		return CreateResize.createResizeCommand(request, getHost());
	}

	/*
	 * Shows or updates feedback for a change bounds request.
	 * 
	 * @param request the request
	 */
	protected void showChangeBoundsFeedback(ChangeBoundsRequest request) {
		Point moveDelta = request.getMoveDelta().getCopy();
		Dimension sizeDelta = request.getSizeDelta().getCopy();
		moveDelta.y = 0;
		getFeedbackLayer().translateToParent(moveDelta);
		int delta = moveDelta.x;
		if (request.getType().equals(REQ_MOVE)) {
			request.setMoveDelta(new Point(delta, 0));
			if (delta == 0)
				return;
		}
		if (request.getType().equals(REQ_RESIZE) && sizeDelta.width == 0
				&& sizeDelta.height == 0)
			return;
		PrecisionRectangle rdelta = new PrecisionRectangle(moveDelta, sizeDelta);

		FeedbackFigure feedback = (FeedbackFigure) getDragSourceFeedbackFigure();
		IFigure hfig = getHostFigure();
		feedback.setRequest(request);
		if (request.getType().equals(REQ_MOVE)) {
			// double zoom = GEFUtil.getZoom(getHost());
			// movePlace.calcMovePlace((int) Math.floor(moveDelta.x / zoom),
			// hfig
			// .getBounds().getCopy());
			// if (movePlace.x1 != movePlace.xRef)
			// feedback.setInsertLine(movePlace.x1, movePlace.y1, movePlace.y2);
		}

		Rectangle rect = new PrecisionRectangle(getInitialFeedbackBounds()
				.getCopy());
		Dimension contaierSize = getHost().getContaierSize();

		if (request.getType().equals(REQ_RESIZE)
				&& request.getResizeDirection() == PositionConstants.SOUTH) {
			rect.x = TableCellEditPart.X_OFFSET;
			rect.width = contaierSize.width;
		} else if (request.getType().equals(REQ_RESIZE)
				&& request.getResizeDirection() == PositionConstants.NORTH) {
			rect.x = TableCellEditPart.X_OFFSET;
			rdelta.y = -rdelta.height;
			rect.width = contaierSize.width;
		} else if (request.getType().equals(REQ_MOVE)) {
			rect.y = 0;
			rect.height = contaierSize.height + TableCellEditPart.Y_OFFSET * 2;
		} else {
			rect.y = TableCellEditPart.Y_OFFSET;
			rect.height = contaierSize.height + 1;
		}
		hfig.translateToAbsolute(rect);
		rect.translate(rdelta.x, rdelta.y);
		rect.resize(rdelta.width, rdelta.height);
		feedback.translateToRelative(rect);
		feedback.setBounds(rect);
		feedback.validate();
	}

	protected IFigure createDragSourceFeedbackFigure() {
		RectangleFigure r = new FeedbackFigure();
		r.setOpaque(false);
		r.setAlpha(50);
		r.setBackgroundColor(ColorConstants.gray);
		r.setFill(false);
		r.setBorder(new LineBorder(ColorConstants.gray, 1));
		addFeedback(r);
		return r;
	}

	private final class FeedbackFigure extends RectangleFigure {
		private ChangeBoundsRequest request;

		public void setRequest(ChangeBoundsRequest request) {
			this.request = request;
		}

		private int x1, y1, y2;

		public void setInsertLine(int x1, int y1, int y2) {
			this.x1 = x1;
			this.y1 = y1;
			this.y2 = y2;
		}

		@Override
		public void paintFigure(Graphics g) {
			PrecisionRectangle b = new PrecisionRectangle(getBounds().getCopy());
			if (request.getType().equals(REQ_RESIZE)) {
				super.paintFigure(g);
				Graphics2D gr = ((J2DGraphics) g).getGraphics2D();
				gr.fillRect(b.x, b.y, b.width, b.height);
				AlphaComposite ac = AlphaComposite.getInstance(
						AlphaComposite.SRC_OVER, 1f);
				gr.setComposite(ac);

				gr.fillOval(b.x + (b.width) / 2 - 3, b.y - 3, 7, 7);
				gr.fillOval(b.x + (b.width) / 2 - 3, b.y + b.height - 4, 7, 7);

				gr.drawLine(b.x + (b.width) / 2, b.y, b.x + (b.width) / 2, b.y
						+ b.height - 2);
			} else if (request.getType().equals(REQ_MOVE)) {
				double zoom = GEFUtil.getZoom(b, getHostFigure());

				Graphics2D gr = ((J2DGraphics) g).getGraphics2D();
				gr.setColor(Color.gray);
				AlphaComposite ac = AlphaComposite.getInstance(
						AlphaComposite.SRC_OVER, 0.1f);
				gr.setComposite(ac);

				int h = TableCellEditPart.Y_OFFSET;
				b.y = b.y + (int) Math.floor(h * zoom) - h - 3;
				int height = b.height - (int) Math.floor(h * zoom);

				gr.fillRect(b.x, b.y, b.width, height - b.y);

				// draw handle representation
				ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f);
				gr.setComposite(ac);
				gr.fillRect(b.x, b.y, b.width, h);
				gr.fillRect(b.x, height + 3, b.width, h);

				// move placer feedback
				gr.setStroke(new BasicStroke(2.0f));
				ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f);
				gr.setComposite(ac);
				gr.setColor(Color.red);

				PrecisionRectangle r = new PrecisionRectangle(x1, y1, x1, y2);
				getHostFigure().translateToAbsolute(r);
				getFeedbackLayer().translateToParent(r);
				gr.drawLine(r.x, r.y, r.width, r.height);
			}
		}
	}
}
