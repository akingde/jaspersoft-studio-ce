package com.jaspersoft.studio.editor.gef.parts.handles;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.Locator;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.handles.MoveHandle;

import com.jaspersoft.studio.editor.gef.figures.borders.TBLineBorder;
import com.jaspersoft.studio.editor.gef.util.GEFUtil;

public class CellMoveHandle extends MoveHandle {
	public static final int Y_OFFSET = 10;

	public CellMoveHandle(GraphicalEditPart owner, Locator loc) {
		super(owner, loc);

	}

	public CellMoveHandle(GraphicalEditPart owner) {
		this(owner, new CellMoveHandleLocator(owner));
	}

	@Override
	protected void initialize() {
		super.initialize();
		setBorder(new TBLineBorder(ColorConstants.gray, Y_OFFSET));
		setOpaque(true);
		setBackgroundColor(ColorConstants.lightGray);
	}

	@Override
	protected void paintFigure(Graphics graphics) {
		graphics.setAlpha(10);
		super.paintFigure(graphics);
	}

	public boolean containsPoint(int x, int y) {
		if (!getBounds().contains(x, y))
			return false;
		Rectangle r = getBounds();

		int offset = getOffset(r);
		return (y <= r.y + offset && y >= r.y) || (y >= r.y + r.height - offset && y <= r.y + r.height);
	}

	public int getOffset(Rectangle r) {
		double zoom = GEFUtil.getZoom(getOwner());
		return (int) Math.floor(Y_OFFSET * zoom);
	}
}
