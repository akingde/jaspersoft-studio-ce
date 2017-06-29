/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.model.style.command;

import java.util.List;

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.MReport;
import com.jaspersoft.studio.model.style.MStyles;
import com.jaspersoft.studio.utils.ModelUtils;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.design.JRDesignStyle;
import net.sf.jasperreports.engine.design.JasperDesign;

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

	/**
	 * The node of the current report
	 */
	private MReport reportNode;

	/**
	 * The list of nodes from where the removed style reference
	 * was deleted, used for the undo
	 */
	private List<ANode> elementsUsingStyle = null;
	
	/**
	 * Instantiates a new delete style command.
	 * 
	 * @param reportNode the report where the style is contained
	 * @param style the style to delete
	 */
	public DeleteStyleCommand(MReport reportNode, JRDesignStyle style) {
		super();
		this.jrDesign = reportNode.getJasperDesign();
		this.jrStyle = style;
		this.reportNode = reportNode;
	}
	
	
	/**
	 * Instantiates a new delete style command. 
	 * 
	 * @param styleNode a node that is used to get the report where this style is contained
	 * @param style the style to delete
	 */
	public DeleteStyleCommand(MStyles styleNode, JRDesignStyle style) {
		super();
		this.jrDesign = styleNode.getJasperDesign();
		this.jrStyle = style;
		reportNode = ModelUtils.getReport(styleNode);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	@Override
	public void execute() {
		if (reportNode != null){
			elementsUsingStyle = reportNode.getUsedStyles().get(jrStyle.getName());
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
