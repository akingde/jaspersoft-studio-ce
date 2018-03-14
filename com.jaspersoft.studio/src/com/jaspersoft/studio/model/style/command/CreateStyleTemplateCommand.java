/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.model.style.command;

import org.eclipse.core.runtime.Assert;
import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.model.style.MStyleTemplate;
import com.jaspersoft.studio.model.style.MStyles;
import com.jaspersoft.studio.utils.SelectionHelper;

import net.sf.jasperreports.engine.design.JRDesignReportTemplate;
import net.sf.jasperreports.engine.design.JasperDesign;

/*
 * link nodes & together.
 * 
 * @author Chicu Veaceslav
 */
public class CreateStyleTemplateCommand extends Command {

	/** The jr template. */
	private JRDesignReportTemplate jrTemplate;

	/** The jr design. */
	private JasperDesign jrDesign;

	/** The index. */
	private int index;

	/**
	 * Instantiates a new creates the style template command.
	 * 
	 * @param destNode
	 *          the dest node
	 * @param srcNode
	 *          the src node
	 * @param index
	 *          the index
	 */
	public CreateStyleTemplateCommand(MStyles destNode, MStyleTemplate srcNode, int index) {
		this(destNode, (JRDesignReportTemplate)srcNode.getValue(), index);
	}
	
	public CreateStyleTemplateCommand(MStyles destNode, JRDesignReportTemplate srcNode, int index) {
		super();
		Assert.isNotNull(srcNode);
		this.jrDesign = destNode.getJasperDesign();
		this.index = index;
		this.jrTemplate = srcNode;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	@Override
	public void execute() {
		if (jrTemplate != null) {
			if (index < 0 || index > jrDesign.getTemplatesList().size())
				jrDesign.addTemplate(jrTemplate);
			else
				jrDesign.addTemplate(index, jrTemplate);
			SelectionHelper.setOutlineSelection(jrTemplate);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#canUndo()
	 */
	@Override
	public boolean canUndo() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	@Override
	public void undo() {
		jrDesign.removeTemplate(jrTemplate);
	}
}
