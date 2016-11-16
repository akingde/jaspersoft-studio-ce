/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.book.wizards;

import net.sf.jasperreports.engine.design.JRDesignGroup;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.jface.wizard.Wizard;

import com.jaspersoft.studio.editor.expression.ExpressionContext;
import com.jaspersoft.studio.editor.expression.IExpressionContextSetter;
import com.jaspersoft.studio.model.group.MGroup;
import com.jaspersoft.studio.wizards.group.WizardBandGroupPage;

public class AddGroupWizard extends Wizard implements IExpressionContextSetter {
	
	private MGroup group;
	private ExpressionContext expContext;
	private JasperDesign jd;
	private WizardBandGroupPage step1;
	
	public AddGroupWizard(JasperDesign jd){
		super();
		this.jd = jd;
		setWindowTitle("Group for book sections");
		setNeedsProgressMonitor(false);
	}
	
	@Override
	public void addPages() {
		this.group = new MGroup();
		group.setValue(MGroup.createJRGroup(jd.getMainDesignDataset()));

		step1 = new WizardBandGroupPage(jd);
		addPage(step1);
		step1.setGroup(group);
		if (expContext != null) {
			step1.setExpressionContext(expContext);
		}
	}

	@Override
	public boolean performFinish() {
		JRDesignGroup gr = (JRDesignGroup) group.getValue();
		if (jd.getMainDesignDataset().getGroupsMap().get(gr.getName()) != null)
			return false;
		return true;
	}

	@Override
	public void setExpressionContext(ExpressionContext expContext) {
		this.expContext = expContext;
		if(step1!=null){
			step1.setExpressionContext(expContext);
		}
	}

	public MGroup getGroup() {
		return group;
	}
}
