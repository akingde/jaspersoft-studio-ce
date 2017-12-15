/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.model.style.command;

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.model.MReport;
import com.jaspersoft.studio.model.style.MStyles;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.design.JRDesignStyle;
import net.sf.jasperreports.engine.design.JasperDesign;

/**
 * Remove a style from the report. It doesn not update the 
 * refrence to this style, only remove it
 * 
 * @author Orlandin Marco
 */
public class SimpleDeleteStyleCommand extends Command {

	/** The jr design. */
	private JasperDesign jrDesign;

	/** The jr style. */
	private JRDesignStyle jrStyle;

	/** The element position. */
	private int elementPosition = 0;

	/**
	 * Instantiates a new delete style command.
	 * 
	 * @param reportNode the report where the style is contained
	 * @param style the style to delete
	 */
	public SimpleDeleteStyleCommand(MReport reportNode, JRDesignStyle style) {
		super();
		this.jrDesign = reportNode.getJasperDesign();
		this.jrStyle = style;
	}
	
	
	/**
	 * Instantiates a new delete style command. 
	 * 
	 * @param styleNode a node that is used to get the report where this style is contained
	 * @param style the style to delete
	 */
	public SimpleDeleteStyleCommand(MStyles styleNode, JRDesignStyle style) {
		super();
		this.jrDesign = styleNode.getJasperDesign();
		this.jrStyle = style;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	@Override
	public void execute() {
		elementPosition = jrDesign.getStylesList().indexOf(jrStyle);
		jrDesign.removeStyle(jrStyle);
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
		} catch (JRException e) {
			e.printStackTrace();
		}
	}
}
