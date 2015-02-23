/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved. http://www.jaspersoft.com.
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.model.parameter.command;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignParameter;

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.parameter.MParameter;
import com.jaspersoft.studio.model.parameter.MParameters;
import com.jaspersoft.studio.preferences.DesignerPreferencePage;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

/*
 * /* The Class ReorderParameterCommand.
 */
public class ReorderParameterCommand extends Command {

	/** The new index. */
	private int oldIndex, newIndex;

	/** The jr parameter. */
	private JRDesignParameter jrParameter;

	/** The jr dataset. */
	private JRDesignDataset jrDataset;
	private JasperReportsConfiguration jrContext;

	/**
	 * Instantiates a new reorder parameter command.
	 * 
	 * @param child
	 *          the child
	 * @param parent
	 *          the parent
	 * @param newIndex
	 *          the new index
	 */
	public ReorderParameterCommand(MParameter child, MParameters<?> parent, int newIndex) {
		super(Messages.common_reorder_elements);
		jrContext = parent.getJasperConfiguration();
		this.newIndex = Math.max(0, newIndex);
		this.jrDataset = (JRDesignDataset) parent.getValue();
		this.jrParameter = (JRDesignParameter) child.getValue();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	@Override
	public void execute() {
		oldIndex = jrDataset.getParametersList().indexOf(jrParameter); 
		try {
			int i = 0;
			for (JRParameter v : jrDataset.getParametersList()) {
				if (v.isSystemDefined())
					i++;
				else
					break;
			}
			boolean showDefaults = jrContext != null ? jrContext.getPropertyBoolean(
					DesignerPreferencePage.P_SHOW_VARIABLES_DEFAULTS, Boolean.TRUE) : true;
			if (!showDefaults)
				newIndex += i;
			newIndex = Math.max(newIndex, i);
			jrDataset.removeParameter(jrParameter);
			if (newIndex < 0 || newIndex > jrDataset.getParametersList().size())
				jrDataset.addParameter(jrParameter);
			else
				jrDataset.addParameter(newIndex, jrParameter);
		} catch (JRException e) {
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	@Override
	public void undo() {
		try {
			jrDataset.removeParameter(jrParameter);
			if (oldIndex < 0 || oldIndex > jrDataset.getParametersList().size())
				jrDataset.addParameter(jrParameter);
			else
				jrDataset.addParameter(oldIndex, jrParameter);
		} catch (JRException e) {
			e.printStackTrace();
		}
	}
}
