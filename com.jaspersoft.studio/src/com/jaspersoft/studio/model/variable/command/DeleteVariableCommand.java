/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.model.variable.command;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.JRSortField;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignVariable;
import net.sf.jasperreports.engine.type.SortFieldTypeEnum;

import com.jaspersoft.studio.model.command.ADatasetObjectDeleteCommand;
import com.jaspersoft.studio.model.variable.MVariable;
import com.jaspersoft.studio.model.variable.MVariables;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

/*
 * /* link nodes & together.
 * 
 * @author Chicu Veaceslav
 */
public class DeleteVariableCommand extends ADatasetObjectDeleteCommand{

	private JRDesignVariable jrVariable;
	private JRSortField jrSortField;
	private int oldSortFieldindex = 0;

	/**
	 * Instantiates a new delete variable command.
	 * 
	 * @param destNode
	 *          the dest node
	 * @param srcNode
	 *          the src node
	 */
	public DeleteVariableCommand(MVariables destNode, MVariable srcNode) {
		this(srcNode.getJasperConfiguration(), destNode.getValue(), srcNode.getValue(), null);
	}

	public DeleteVariableCommand(JasperReportsConfiguration jContext, JRDesignDataset destNode, JRDesignVariable srcNode, Boolean canceled) {
		super(canceled);
		this.jContext = jContext;
		jd = jContext.getJasperDesign();
		this.jrDataset = destNode;
		this.jrVariable = srcNode;
		objectName = "$V{" + jrVariable.getName() + "}";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	@Override
	public void execute() {
		elementPosition = jrDataset.getVariablesList().indexOf(jrVariable);
		jrDataset.removeVariable(jrVariable);

		if (jrSortField == null)
			for (JRSortField sf : jrDataset.getSortFieldsList()) {
				if (sf.getType().equals(SortFieldTypeEnum.VARIABLE) && sf.getName().equals(jrVariable.getName())) {
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
		if (jrDataset == null || jrVariable == null)
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
			if (elementPosition < 0 || elementPosition > jrDataset.getVariablesList().size())
				jrDataset.addVariable(jrVariable);
			else
				jrDataset.addVariable(elementPosition, jrVariable);

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
	
	/**
	 * Check if the expression passed is the variable expression or variable initial value expression.
	 * The check is done on the reference, they must be the same object, not two objects with the same 
	 * value
	 * 
	 * @param expr the expression 
	 * @return true if the expression belong to the deleted variable, false otherwise. 
	 */
	@Override
	protected boolean isOwnExpression(JRExpression expr) {
		return jrVariable.getExpression() == expr || jrVariable.getInitialValueExpression() == expr;
	}
}
