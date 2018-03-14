/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.model.parameter.command;

import org.eclipse.gef.commands.Command;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.swt.widgets.Display;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.parameter.MParameter;
import com.jaspersoft.studio.model.parameter.MParameters;
import com.jaspersoft.studio.utils.ModelUtils;
import com.jaspersoft.studio.utils.SelectionHelper;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignParameter;

/*
 * link nodes & together.
 * 
 * @author Chicu Veaceslav
 */
public class CreateParameterCommand extends Command {

	/** The jr parameter. */
	private JRDesignParameter jrParameter;

	/** The jr dataset. */
	private JRDesignDataset jrDataset;

	/** The index. */
	private int index;
	private JasperReportsConfiguration jrContext;

	/**
	 * Instantiates a new creates the parameter command.
	 * 
	 * @param destNode
	 *            the dest node
	 * @param srcNode
	 *            the src node
	 * @param position
	 *            the position
	 * @param index
	 *            the index
	 */
	public CreateParameterCommand(MParameters<?> destNode, MParameter srcNode, int index) {
		super();
		this.jrDataset = (JRDesignDataset) destNode.getValue();
		this.index = index;
		if (srcNode != null && srcNode.getValue() != null)
			this.jrParameter = (JRDesignParameter) srcNode.getValue();
		jrContext = destNode.getJasperConfiguration();
	}

	public CreateParameterCommand(JRDesignDataset jDataset, JRParameter jParam, JasperReportsConfiguration jrContext,
			int index) {
		super();
		this.jrContext = jrContext;
		this.jrDataset = jDataset;
		this.index = index;
		this.jrParameter = (JRDesignParameter) jParam;
	}

	private ReorderParameterCommand rc;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	@Override
	public void execute() {
		if (rc != null) {
			rc.execute();
			return;
		}
		if (jrParameter == null) {
			this.jrParameter = MParameter.createJRParameter(jrDataset);
		}
		if (jrParameter != null) {
			try {
				if (jrDataset.getParametersList().contains(jrParameter)) {
					rc = new ReorderParameterCommand(jrParameter, jrDataset, jrContext, index);
					rc.execute();
					return;
				}

				if (index < 0 || index > jrDataset.getParametersList().size())
					jrDataset.addParameter(jrParameter);
				else
					jrDataset.addParameter(index, jrParameter);
				SelectionHelper.setOutlineSelection(jrParameter);
			} catch (JRException e) {
				e.printStackTrace();
				if (e.getMessage().startsWith("Duplicate declaration")) { //$NON-NLS-1$
					String defaultName = ModelUtils.getDefaultName(jrDataset.getParametersMap(), "CopyOFParameter_"); //$NON-NLS-1$
					InputDialog dlg = new InputDialog(Display.getCurrent().getActiveShell(),
							Messages.CreateParameterCommand_parameter_name,
							Messages.CreateParameterCommand_parameter_name_dialog_text, defaultName, null);
					if (dlg.open() == InputDialog.OK) {
						jrParameter.setName(dlg.getValue());
						execute();
					} else
						return;
				}
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
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	@Override
	public void undo() {
		if (rc != null)
			rc.undo();
		else
			jrDataset.removeParameter(jrParameter);
	}
}
