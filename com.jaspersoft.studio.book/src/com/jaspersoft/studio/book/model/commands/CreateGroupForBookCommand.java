/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.book.model.commands;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignGroup;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.gef.commands.Command;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.wizard.WizardDialog;

import com.jaspersoft.studio.book.wizards.AddGroupWizard;
import com.jaspersoft.studio.editor.expression.ExpressionEditorSupportUtil;
import com.jaspersoft.studio.model.DialogEnabledCommand;
import com.jaspersoft.studio.model.MReport;
import com.jaspersoft.studio.model.group.MGroup;

public class CreateGroupForBookCommand extends Command implements DialogEnabledCommand{
	
	private JasperDesign jd;
	private MReport report;
	private JRDesignGroup jrGroup;
	private JRDesignDataset mainDataset;

	public CreateGroupForBookCommand(MReport report){
		this.report = report;
		this.jd = report.getJasperDesign();
		this.mainDataset = (JRDesignDataset) jd.getMainDataset();
	}

	@Override
	public boolean canExecute() {
		return this.report!=null && this.jd!=null && this.mainDataset!=null;
	}

	@Override
	public void execute() {
		try {
			this.jd.addGroup(this.jrGroup);
		} catch (JRException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void undo() {
		this.jd.removeGroup(jrGroup);
	}
	
	@Override
	public boolean canUndo() {
		return this.jd!=null && this.jrGroup!=null;
	}
	
	@Override
	public int openDialog() {
		AddGroupWizard wizard = new AddGroupWizard(jd);
		wizard.setExpressionContext(ExpressionEditorSupportUtil.getReportExpressionContext());
		WizardDialog dialog = new WizardDialog(UIUtils.getShell(), wizard);
		dialog.create();
		if (dialog.open() == Dialog.OK) {
			MGroup srcNode = wizard.getGroup();
			if (srcNode.getValue() == null){
				jrGroup = MGroup.createJRGroup(mainDataset);
			}
			else {
				jrGroup = (JRDesignGroup) srcNode.getValue();
			}
			return Dialog.OK;
		}
		else {
			return Dialog.CANCEL;
		}
	}
	

}
