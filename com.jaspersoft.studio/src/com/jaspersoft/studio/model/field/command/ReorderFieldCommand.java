/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.model.field.command;

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.field.MField;
import com.jaspersoft.studio.model.field.MFields;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignField;

/*/*
 * The Class ReorderFieldCommand.
 */
public class ReorderFieldCommand extends Command {

	/** The new index. */
	private int oldIndex, newIndex;

	/** The jr field. */
	private JRDesignField jrField;

	/** The jr dataset. */
	private JRDesignDataset jrDataset;

	/**
	 * Instantiates a new reorder field command.
	 * 
	 * @param child
	 *            the child
	 * @param parent
	 *            the parent
	 * @param newIndex
	 *            the new index
	 */
	public ReorderFieldCommand(MField child, MFields parent, int newIndex) {
		super(Messages.common_reorder_elements);

		this.newIndex = Math.max(0, newIndex);
		this.jrDataset = (JRDesignDataset) parent.getValue();
		this.jrField = (JRDesignField) child.getValue();
	}

	public ReorderFieldCommand(JRDesignField child, JRDesignDataset jrDataset, int newIndex) {
		super(Messages.common_reorder_elements);
		this.newIndex = Math.max(0, newIndex);
		this.jrDataset = jrDataset;
		this.jrField = child;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	@Override
	public void execute() {
		try {
			oldIndex = jrDataset.getFieldsList().indexOf(jrField);
			jrDataset.removeField(jrField);
			if (newIndex < 0 || newIndex > jrDataset.getFieldsList().size())
				jrDataset.addField(jrField);
			else
				jrDataset.addField(newIndex, jrField);
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
			jrDataset.removeField(jrField);
			if (oldIndex < 0 || oldIndex > jrDataset.getFieldsList().size())
				jrDataset.addField(jrField);
			else
				jrDataset.addField(oldIndex, jrField);
		} catch (JRException e) {
			e.printStackTrace();
		}
	}
}
