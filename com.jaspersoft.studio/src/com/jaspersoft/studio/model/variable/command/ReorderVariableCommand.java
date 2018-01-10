/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.model.variable.command;

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.editor.outline.actions.HideDefaultVariablesAction;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.variable.MVariable;
import com.jaspersoft.studio.model.variable.MVariables;
import com.jaspersoft.studio.preferences.DesignerPreferencePage;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRVariable;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignVariable;

/*
 * /* The Class ReorderVariableCommand.
 */
public class ReorderVariableCommand extends Command {

	/** The new index. */
	private int oldIndex, newIndex;

	/** The jr variable. */
	private JRDesignVariable jrVariable;

	/** The jr dataset. */
	private JRDesignDataset jrDataset;
	private JasperReportsConfiguration jrContext;

	/**
	 * Instantiates a new reorder variable command.
	 * 
	 * @param child
	 *            the child
	 * @param parent
	 *            the parent
	 * @param newIndex
	 *            the new index
	 */
	public ReorderVariableCommand(MVariable child, MVariables parent, int newIndex) {
		super(Messages.common_reorder_elements);
		jrContext = parent.getJasperConfiguration();
		this.newIndex = Math.max(0, newIndex);
		this.jrDataset = (JRDesignDataset) parent.getValue();
		this.jrVariable = (JRDesignVariable) child.getValue();
	}

	public ReorderVariableCommand(JRDesignVariable child, JRDesignDataset jrDataset,
			JasperReportsConfiguration jrContext, int newIndex) {
		super(Messages.common_reorder_elements);
		this.jrContext = jrContext;
		this.newIndex = Math.max(0, newIndex);
		this.jrDataset = jrDataset;
		this.jrVariable = child;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	@Override
	public void execute() {
		oldIndex = jrDataset.getVariablesList().indexOf(jrVariable);

		try {
			int i = 0;
			for (JRVariable v : jrDataset.getVariablesList()) {
				if (v.isSystemDefined())
					i++;
				else
					break;
			}
			boolean showDefaults = jrContext != null
					? jrContext.getPropertyBoolean(DesignerPreferencePage.P_SHOW_VARIABLES_DEFAULTS, Boolean.TRUE)
					: true;
			showDefaults = showDefaults && !HideDefaultVariablesAction.areDefaultVariablesHidden(jrContext);
			if (!showDefaults)
				newIndex += i;
			newIndex = Math.max(newIndex, i);
			jrDataset.removeVariable(jrVariable);
			if (newIndex < 0 || newIndex > jrDataset.getVariablesList().size())
				jrDataset.addVariable(jrVariable);
			else
				jrDataset.addVariable(newIndex, jrVariable);
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
			jrDataset.removeVariable(jrVariable);
			if (oldIndex < 0 || oldIndex > jrDataset.getVariablesList().size())
				jrDataset.addVariable(jrVariable);
			else
				jrDataset.addVariable(oldIndex, jrVariable);
		} catch (JRException e) {
			e.printStackTrace();
		}
	}
}
