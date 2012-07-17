package com.jaspersoft.studio.editor.gef.figures;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Rectangle;

public class ContainerPageFigure extends APageFigure {
	private Dimension containerSize;

	public ContainerPageFigure(boolean viewMargins) {
		super(viewMargins);
	}

	public void setContainerSize(Dimension d) {
		this.containerSize = d;
		setSize(d);
	}

	@Override
	public void paintFigure(Graphics g) {
		if (viewMargins) {
			Rectangle clientArea = getClientArea();
			clientArea.x -= dx;
			clientArea.y -= dy;
			Rectangle rectangle = new Rectangle(clientArea.x, clientArea.y, containerSize.width, containerSize.height);
			g.fillRectangle(rectangle);

			paintGrid(g, rectangle);
		}
		if (getBorder() != null)
			getBorder().paint(this, g, NO_INSETS);
	}

	@Override
	public Rectangle getHandleBounds() {
		Rectangle clientArea = getClientArea();
		clientArea.x -= dx;
		clientArea.y -= dy;
		Insets insets = getInsets();
		return new Rectangle(clientArea.x - insets.right, clientArea.y - insets.top, containerSize.width + insets.left
				+ insets.right, containerSize.height + insets.top + insets.bottom);
	}

}
