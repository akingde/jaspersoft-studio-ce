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
package com.jaspersoft.studio.model.style.command;

import java.util.List;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.design.JRDesignStyle;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.style.MStyle;
import com.jaspersoft.studio.model.style.MStyles;

/**
 * Remove a style and update the reference to the node that
 * are using it to not not reference the style anymore
 * 
 * @author Orlandin Marco
 */
public class DeleteStyleCommand extends Command {

	/** The jr design. */
	private JasperDesign jrDesign;

	/** The jr style. */
	private JRDesignStyle jrStyle;

	/** The element position. */
	private int elementPosition = 0;

	private MStyles destNode;

	/**
	 * The list of nodes from where the removed style reference
	 * was deleted, used for the undo
	 */
	private List<ANode> elementsUsingStyle = null;
	
	/**
	 * Instantiates a new delete style command. This 
	 * constructor support the update of the node to not point
	 * anymore to the removed style
	 * 
	 * @param destNode the styles parent node
	 * @param srcNode the style to remove
	 */
	public DeleteStyleCommand(MStyles destNode, MStyle srcNode) {
		super();
		this.jrDesign = srcNode.getJasperDesign();
		this.jrStyle = (JRDesignStyle) srcNode.getValue();
		this.destNode = destNode;
	}

	/**
	 * Instantiates a new delete style command. This 
	 * constructor NOT support the update of the node to not point
	 * anymore to the removed style
	 * 
	 * @param design the JasperDesign of the report
	 * @param style the style to remove
	 */
	public DeleteStyleCommand(JasperDesign design, JRDesignStyle style) {
		this.jrDesign = design;
		this.jrStyle = style;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	@Override
	public void execute() {
		//if it was created with the first constructor check the element using the style
		if (destNode != null){
			elementsUsingStyle = ((ANode)destNode.getRoot()).getUsedStyles().get(jrStyle.getName());
		}
			
		elementPosition = jrDesign.getStylesList().indexOf(jrStyle);
		jrDesign.removeStyle(jrStyle);

		//Remove the style from the element using it
		if (elementsUsingStyle != null){
			for(ANode node : elementsUsingStyle){
				node.setStyle(null);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#canUndo()
	 */
	@Override
	public boolean canUndo() {
		if (jrDesign == null || jrStyle == null)
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
		try {
			if (elementPosition < 0 || elementPosition > jrDesign.getStylesList().size())
				jrDesign.addStyle(jrStyle);
			else
				jrDesign.addStyle(elementPosition, jrStyle);

			//restore the style in the element using it
			if (elementsUsingStyle != null){
				for(ANode node : elementsUsingStyle){
					node.setStyle(jrStyle);
				}
				elementsUsingStyle = null;
			}
		} catch (JRException e) {
			e.printStackTrace();
		}
	}
}
