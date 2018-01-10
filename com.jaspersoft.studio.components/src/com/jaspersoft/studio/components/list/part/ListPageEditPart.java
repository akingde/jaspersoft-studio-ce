/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.list.part;

import net.sf.jasperreports.engine.design.JRDesignElement;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.geometry.Dimension;

import com.jaspersoft.studio.callout.MCallout;
import com.jaspersoft.studio.callout.pin.MPinConnection;
import com.jaspersoft.studio.components.list.model.MList;
import com.jaspersoft.studio.editor.gef.figures.APageFigure;
import com.jaspersoft.studio.editor.gef.figures.ContainerPageFigure;
import com.jaspersoft.studio.editor.gef.parts.PageEditPart;
import com.jaspersoft.studio.model.IGraphicElement;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.util.ShowChildrenModelVisitor;
import com.jaspersoft.studio.property.dataset.dialog.IDatasetDialogSupport;

public class ListPageEditPart extends PageEditPart implements
		IDatasetDialogSupport {

	private Dimension containerSize;
	
	@Override
	protected void setupPageFigure(APageFigure figure2) {
		updateContainerSize();
		((ContainerPageFigure) figure2).setContainerSize(getContaierSize());
		setupPagePreferences(figure2);
	}

	public Dimension getContaierSize() {
		return containerSize;
	}

	private void updateContainerSize() {
		MList table = null;
		for (INode n : getPage().getChildren()) {
			if (n instanceof MList) {
				table = (MList) n;
				break;
			}
		}
		if (table != null) {
			Dimension d = table.getContainerSize();
			d.height = Math.max(d.height, (Integer) table
					.getPropertyValue(JRDesignElement.PROPERTY_HEIGHT));
			d.width = Math.max(d.width, (Integer) table
					.getPropertyValue(JRDesignElement.PROPERTY_WIDTH));
			containerSize = d;
		} else
			containerSize = null;
	}
	
	@Override
	protected List<Object> getModelChildren() {
		final List<Object> list = new ArrayList<Object>();
		new ShowChildrenModelVisitor<Object>(getPage()) {

			@Override
			public boolean visit(INode n) {
				if (n instanceof MCallout) {
					list.add(n);
					for (INode child : n.getChildren()){
						//the connection must not be returned, since their edit part 
						//must not be created trough the edit part factory but from the createConnection
						//method of the Pin/Callout edit part
						if (!(child instanceof MPinConnection)) {
							list.add(child);
						}
					}
				} else if (n instanceof IGraphicElement){
					list.add(n);
				}
				return true;
			}
		};
		return list;
	}
}
