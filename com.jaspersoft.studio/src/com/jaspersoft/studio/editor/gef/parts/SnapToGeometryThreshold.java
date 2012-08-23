package com.jaspersoft.studio.editor.gef.parts;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.SnapToGeometry;

/**
 * Extend the original SnapToGeometry to give the possibility to set the threshold of snap
 * and to change the indexing of the items
 * @author Orlandin Marco
 *
 */
public class SnapToGeometryThreshold extends SnapToGeometry{
	
	public SnapToGeometryThreshold(GraphicalEditPart container) {
		super(container);
	}
	
	/**
	 * Set A new threshold
	 */
	public void setThreshold(double value){
		super.setThreshold(value);
	}
	
	/**
	 * The list of element is based on the father of first selected item
	 */
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected List generateSnapPartsList(List exclusions) {
		// Don't snap to any figure that is being dragged

		List children = new ArrayList(((EditPart)exclusions.get(0)).getParent().getChildren());
		children.removeAll(exclusions);

		// Don't snap to hidden figures
		List hiddenChildren = new ArrayList();
		for (Iterator iter = children.iterator(); iter.hasNext();) {
			GraphicalEditPart child = (GraphicalEditPart) iter.next();
			if (!child.getFigure().isVisible())
				hiddenChildren.add(child);
		}
		children.removeAll(hiddenChildren);

		return children;
	}

}