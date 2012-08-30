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
package com.jaspersoft.studio.components.list.commands.element;

import java.util.Map;

import net.sf.jasperreports.components.list.DesignListContents;
import net.sf.jasperreports.components.list.StandardListComponent;
import net.sf.jasperreports.engine.JRElement;
import net.sf.jasperreports.engine.JRPropertiesHolder;
import net.sf.jasperreports.engine.design.JRDesignComponentElement;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.components.list.model.MList;
import com.jaspersoft.studio.editor.layout.ILayout;
import com.jaspersoft.studio.editor.layout.LayoutManager;
import com.jaspersoft.studio.model.IContainerLayout;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.utils.SelectionHelper;

/*
 * link nodes & together.
 * 
 * @author Chicu Veaceslav
 */
public class CreateElementCommand extends Command {

	protected MGraphicElement srcNode;
	protected JRDesignElement jrElement;
	private JasperDesign jDesign;
	private StandardListComponent listcomponent;

	private Rectangle location;

	private int index;
	private JRPropertiesHolder[] pholder;

	/**
	 * Instantiates a new creates the element command.
	 * 
	 * @param destNode
	 *            the dest node
	 * @param srcNode
	 *            the src node
	 * @param index
	 *            the index
	 */
	public CreateElementCommand(MList destNode, MGraphicElement srcNode,
			Rectangle position, int index) {
		super();
		this.srcNode = srcNode;
		if (srcNode != null)
			jrElement = (JRDesignElement) srcNode.getValue();
		jrElement2 = (JRDesignComponentElement) destNode.getValue();
		listcomponent = (StandardListComponent) jrElement2.getComponent();
		this.index = index;
		this.location = position;
		jDesign = destNode.getJasperDesign();
		pholder = ((IContainerLayout) destNode).getPropertyHolder();
	}

	/**
	 * Creates the object.
	 */
	protected void createObject() {
		if (jrElement == null) {
			jrElement = srcNode.createJRElement(srcNode.getJasperDesign());
		}
		if (jrElement != null)
			setElementBounds();
	}

	protected void setElementBounds() {
		if (location == null)
			location = new Rectangle(0, 0, srcNode.getDefaultWidth(),
					srcNode.getDefaultHeight());
		if (location.width < 0)
			location.width = srcNode.getDefaultWidth();
		if (location.height < 0)
			location.height = srcNode.getDefaultHeight();

		jrElement.setX(location.x);
		jrElement.setY(location.y);
		jrElement.setWidth(location.width);
		jrElement.setHeight(location.height);

		DesignListContents contents = (DesignListContents) listcomponent
				.getContents();
		contents.setHeight(jrElement.getHeight());
		contents.setWidth(jrElement.getWidth());
	}

	public void setJrGroup(StandardListComponent jrGroup) {
		this.listcomponent = jrGroup;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	@Override
	public void execute() {
		createObject();
		if (jrElement != null) {
			removeElements(jrElement);
			DesignListContents dlist = (DesignListContents) listcomponent
					.getContents();
			if (index < 0 || index > dlist.getChildren().size())
				dlist.addElement(jrElement);
			else
				dlist.addElement(index, jrElement);

			ILayout layout = LayoutManager.getLayout(pholder, jDesign,
					jrElement2.getUUID().toString());
			map = layout.layout(dlist.getElements(),
					new Dimension(jrElement.getWidth(), jrElement.getHeight()));
		}
		if (firstTime) {
			SelectionHelper.setSelection(jrElement, false);
			firstTime = false;
		}
	}

	private Map<JRElement, Rectangle> map;
	private boolean firstTime = true;
	private JRDesignComponentElement jrElement2;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#canUndo()
	 */
	@Override
	public boolean canUndo() {
		if (listcomponent == null || jrElement == null)
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	@Override
	public void undo() {
		for (JRElement el : map.keySet()) {
			JRDesignElement del = (JRDesignElement) el;
			Rectangle r = map.get(el);
			del.setX(r.x);
			del.setY(r.y);
			del.setWidth(r.width);
			del.setHeight(r.height);
		}
		DesignListContents dlist = (DesignListContents) listcomponent
				.getContents();
		dlist.removeElement(jrElement);

	}

	private void removeElements(JRDesignElement element) {
		com.jaspersoft.studio.model.command.CreateElementCommand.removeElement(
				jDesign, jrElement);

		DesignListContents dlist = (DesignListContents) listcomponent
				.getContents();
		com.jaspersoft.studio.model.command.CreateElementCommand.removeElement(
				jrElement, dlist.getElements());
	}

}
