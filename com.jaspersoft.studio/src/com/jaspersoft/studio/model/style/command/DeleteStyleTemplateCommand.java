/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.model.style.command;

import net.sf.jasperreports.engine.design.JRDesignReportTemplate;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.ExternalStylesManager;
import com.jaspersoft.studio.model.style.MStyleTemplate;
import com.jaspersoft.studio.model.style.MStyles;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;
/*
 * link nodes & together.
 * 
 * @author Chicu Veaceslav
 */
public class DeleteStyleTemplateCommand extends Command {

	/** The jr design. */
	private JasperDesign jrDesign;

	/** The jr template. */
	private JRDesignReportTemplate jrTemplate;

	/** The element position. */
	private int elementPosition = 0;
	
	private JasperReportsConfiguration jConfig;

	/**
	 * Instantiates a new delete style template command.
	 */
	public DeleteStyleTemplateCommand(MStyles destNode, MStyleTemplate srcNode) {
		super();
		this.jrDesign = srcNode.getJasperDesign();
		this.jrTemplate = (JRDesignReportTemplate) srcNode.getValue();
		this.jConfig = destNode.getJasperConfiguration();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	@Override
	public void execute() {
		elementPosition = jrDesign.getTemplatesList().indexOf(jrTemplate);
		jrDesign.removeTemplate(jrTemplate);
		//Delete the removed style from the cache. This because if the style is removed
		//edited and added another time we want to see it refreshed
		ExternalStylesManager.removeCachedStyle(jConfig, jrTemplate);
		jConfig.refreshCachedStyles();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#canUndo()
	 */
	@Override
	public boolean canUndo() {
		if (jrDesign == null || jrTemplate == null)
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
		if (elementPosition < 0 || elementPosition > jrDesign.getTemplatesList().size())
			jrDesign.addTemplate(jrTemplate);
		else
			jrDesign.addTemplate(elementPosition, jrTemplate);
		jConfig.refreshCachedStyles();
	}
}
