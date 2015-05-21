/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.editor.gef.commands;

import net.sf.jasperreports.engine.JRCommonElement;
import net.sf.jasperreports.engine.JRElementGroup;
import net.sf.jasperreports.engine.JRPropertiesHolder;
import net.sf.jasperreports.engine.base.JRBaseElement;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.editor.layout.ILayout;
import com.jaspersoft.studio.editor.layout.LayoutCommand;
import com.jaspersoft.studio.editor.layout.LayoutManager;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.IContainerLayout;
import com.jaspersoft.studio.model.IGraphicElement;
import com.jaspersoft.studio.model.IGraphicElementContainer;
import com.jaspersoft.studio.model.IGroupElement;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

/**
 * Command to move and element, it is similar to SetConstraintCommand but
 * with this one the parent of the element doesn't change
 * 
 * @author Orlandin Marco
 *
 */
public class SetPositionCommand extends Command {

	/** The new bounds. */
	private Rectangle newBounds;

	/** The old bounds. */
	private Rectangle oldBounds;

	/** The jr element. */
	private JRDesignElement jrElement;

	/** The jr design. */
	private JasperDesign jrDesign;
	
	/** The jr configuration */
	private JasperReportsConfiguration jrConfig;

	/** The parent bounds. */
	private Rectangle parentBounds;
	
	protected JRElementGroup jrGroup;
	
	private Dimension d;
	
	private JRPropertiesHolder[] pholder;
	
	private LayoutCommand lCmd;

	/**
	 * Sets the context.
	 * 
	 * @param parent
	 *          the parent
	 * @param child
	 *          the child
	 * @param constraint
	 *          the constraint
	 */
	public void setContext(ANode child, Rectangle constraint) {
		jrConfig = child.getJasperConfiguration();
		jrDesign = jrConfig.getJasperDesign();
		if (child.getValue() instanceof JRDesignElement) {
			jrElement = (JRDesignElement) child.getValue();
			newBounds = constraint;
			parentBounds = ((IGraphicElement) child).getBounds();
			if (child instanceof IGroupElement)
				jrGroup = ((IGroupElement) child).getJRElementGroup();
			else if (child.getValue() instanceof JRElementGroup)
				jrGroup = (JRElementGroup) child.getValue();
			if (child instanceof IGraphicElementContainer)
				d = ((IGraphicElementContainer) child).getSize();
			if (child instanceof IContainerLayout)
				pholder = ((IContainerLayout) child).getPropertyHolder();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	@Override
	public void execute() {
		if (jrElement != null) {
			oldBounds = new Rectangle(jrElement.getX(), jrElement.getY(), jrElement.getWidth(), jrElement.getHeight());
			// check position,
			// if top-left corner outside the bottom bar bands, move to bottom band
			// if bottom-left corner outside the top bar, move to top band
			int y = jrElement.getY() + newBounds.y - parentBounds.y;
			jrElement.setX(jrElement.getX() + newBounds.x - parentBounds.x);
			jrElement.setY(y);
			jrElement.setWidth(newBounds.width);
			jrElement.setHeight(newBounds.height);

			if (jrElement instanceof JRPropertiesHolder && jrGroup != null) {
				String uuid = null;
				if (jrElement instanceof JRBaseElement)
					uuid = ((JRBaseElement) jrElement).getUUID().toString();
				if (jrElement instanceof JRCommonElement) {
					JRCommonElement jce = (JRCommonElement) jrElement;
					// Commented for back-compatibility in 3.6. 
					// Replaced with the following line.
					// d.setSize(jce.getWidth(), jce.getHeight());
					d.setSize(new Dimension(jce.getWidth(), jce.getHeight()));
				}
				if (lCmd == null) {
					ILayout layout = LayoutManager.getLayout(pholder, jrDesign, uuid);
					lCmd = new LayoutCommand(jrGroup, layout, d);
				}
				lCmd.execute();
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	@Override
	public void undo() {
		if (lCmd != null)
			lCmd.undo();
		if (jrElement != null) {
			jrElement.setWidth(oldBounds.width);
			jrElement.setHeight(oldBounds.height);
			jrElement.setX(oldBounds.x);
			jrElement.setY(oldBounds.y);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#getLabel()
	 */
	@Override
	public String getLabel() {
		if (oldBounds != null && (oldBounds.x != newBounds.x || oldBounds.y != newBounds.y))
			return "set location"; //$NON-NLS-1$
		return "resize"; //$NON-NLS-1$
	}
}
