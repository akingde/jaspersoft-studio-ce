/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.model.parameter.command;

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.editor.outline.actions.HideDefaultsParametersAction;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.parameter.MParameter;
import com.jaspersoft.studio.model.parameter.MParameters;
import com.jaspersoft.studio.preferences.DesignerPreferencePage;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignParameter;

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
	 *            the child
	 * @param parent
	 *            the parent
	 * @param newIndex
	 *            the new index
	 */
	public ReorderParameterCommand(MParameter child, MParameters<?> parent, int newIndex) {
		super(Messages.common_reorder_elements);
		jrContext = parent.getJasperConfiguration();
		this.newIndex = Math.max(0, newIndex);
		this.jrDataset = (JRDesignDataset) parent.getValue();
		this.jrParameter = (JRDesignParameter) child.getValue();
	}

	public ReorderParameterCommand(JRDesignParameter child, JRDesignDataset jrDataset,
			JasperReportsConfiguration jrContext, int newIndex) {
		super(Messages.common_reorder_elements);
		this.jrContext = jrContext;
		this.newIndex = Math.max(0, newIndex);
		this.jrDataset = jrDataset;
		this.jrParameter = child;
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
			boolean showDefaults = jrContext != null
					? jrContext.getPropertyBoolean(DesignerPreferencePage.P_SHOW_VARIABLES_DEFAULTS, Boolean.TRUE)
					: true;
			showDefaults = showDefaults && !HideDefaultsParametersAction.areDefaultParametersHidden(jrContext);
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

	@Override
	public boolean canExecute() {
		int i = 0;
		for (JRParameter v : jrDataset.getParametersList()) {
			if (v.isSystemDefined())
				i++;
			else
				break;
		}
		boolean showDefaults = jrContext != null
				? jrContext.getPropertyBoolean(DesignerPreferencePage.P_SHOW_VARIABLES_DEFAULTS, Boolean.TRUE)
				: true;
		showDefaults = showDefaults && !HideDefaultsParametersAction.areDefaultParametersHidden(jrContext);
		if (showDefaults) {
			return newIndex >= i;
		}
		return newIndex >= 0 && newIndex <= jrDataset.getParametersList().size();
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
