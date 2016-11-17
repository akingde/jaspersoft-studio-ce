/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.model.field.command;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRSortField;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignField;
import net.sf.jasperreports.engine.type.SortFieldTypeEnum;

import com.jaspersoft.studio.model.command.ADatasetObjectDeleteCommand;
import com.jaspersoft.studio.model.field.MField;
import com.jaspersoft.studio.model.field.MFields;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

/*
 * /* link nodes & together.
 * 
 * @author Chicu Veaceslav
 */
public class DeleteFieldCommand extends ADatasetObjectDeleteCommand{
	private JRDesignField jrField;
	private JRSortField jrSortField;
	private int oldSortFieldindex = 0;

	/**
	 * Instantiates a new delete field command.
	 * 
	 * @param destNode
	 *          the dest node
	 * @param srcNode
	 *          the src node
	 */
	public DeleteFieldCommand(MFields destNode, MField srcNode) {
		this(srcNode.getJasperConfiguration(), destNode.getValue(), srcNode.getValue(), null);
	}

	public DeleteFieldCommand(JasperReportsConfiguration jContext, JRDesignDataset destNode, JRDesignField srcNode,
			Boolean canceled) {
		super(canceled);
		this.jContext = jContext;
		jd = jContext.getJasperDesign();
		this.jrDataset = destNode;
		this.jrField = srcNode;
		objectName = "$F{" + jrField.getName() + "}";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	@Override
	public void execute() {
		canceled = Boolean.FALSE;
		elementPosition = jrDataset.getFieldsList().indexOf(jrField);
		jrDataset.removeField(jrField);

		if (jrSortField == null)
			for (JRSortField sf : jrDataset.getSortFieldsList()) {
				if (sf.getType().equals(SortFieldTypeEnum.FIELD) && sf.getName().equals(jrField.getName())) {
					jrSortField = sf;
					break;
				}
			}
		if (jrSortField != null) {
			oldSortFieldindex = jrDataset.getSortFieldsList().indexOf(jrSortField);
			jrDataset.removeSortField(jrSortField);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#canUndo()
	 */
	@Override
	public boolean canUndo() {
		if (jrDataset == null || jrField == null)
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
		if (canceled == null || canceled)
			return;
		try {
			if (elementPosition < 0 || elementPosition > jrDataset.getFieldsList().size())
				jrDataset.addField(jrField);
			else
				jrDataset.addField(elementPosition, jrField);

			if (jrSortField != null) {
				if (oldSortFieldindex >= 0 && oldSortFieldindex < jrDataset.getSortFieldsList().size())
					jrDataset.addSortField(oldSortFieldindex, jrSortField);
				else
					jrDataset.addSortField(jrSortField);
			}
		} catch (JRException e) {
			e.printStackTrace();
		}

	}
	
}
