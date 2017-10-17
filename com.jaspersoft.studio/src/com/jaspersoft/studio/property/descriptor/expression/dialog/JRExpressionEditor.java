/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.descriptor.expression.dialog;

import org.eclipse.jface.wizard.Wizard;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.graphics.Point;

import com.jaspersoft.studio.editor.expression.ExpressionContext;
import com.jaspersoft.studio.editor.expression.ExpressionEditorSupportUtil;
import com.jaspersoft.studio.messages.Messages;

import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.engine.design.JRDesignExpression;

public class JRExpressionEditor extends Wizard {
	private JRDesignExpression mExpression;
	private ExpressionContext exprContext;
	private JRExpressionPage page0;

	public JRDesignExpression getValue() {
		if (page0 != null)
			return page0.getValue();
		return mExpression;
	}

	public void setValue(JRDesignExpression value) {
		if (page0 != null)
			page0.setValue(value);
		this.mExpression = value;
	}
	
	public void setExpressionContext(ExpressionContext exprContext){
		this.exprContext=exprContext;
		if(page0!=null){
			page0.setExpressionContext(this.exprContext);
		}
	}

	public JRExpressionEditor() {
		super();
		ExpressionContext reportExpressionContext = ExpressionEditorSupportUtil.getReportExpressionContext();
		String reportLanguage = null;
		if(reportExpressionContext!=null){
			reportLanguage = reportExpressionContext.getJasperReportsConfiguration().getJasperDesign().getLanguage();
		}
		setWindowTitle(Messages.common_expression_editor + 
				NLS.bind(" ({0})", Misc.nvl(reportLanguage, Messages.JRExpressionEditor_undefinedLanguage)));	//$NON-NLS-1$
	}

	@Override
	public void addPages() {
		page0 = new JRExpressionPage("jrquery.editor"); //$NON-NLS-1$
		page0.setValue(mExpression);
		if(exprContext!=null){
			page0.setExpressionContext(exprContext);
		}
		addPage(page0);
	}

	@Override
	public boolean performFinish() {
		return true;
	}

	@Override
	public void dispose() {
		super.dispose();
		// Remove any expression context reference
		ExpressionEditorSupportUtil.setCurrentExpressionContext(null);
		// Notify closing
		ExpressionEditorSupportUtil.notifyExpressionEditorDialogClosing();
		// Remember window size and location if necessary
		if(page0!=null && page0.getShell()!=null && !page0.getShell().isDisposed()) {
			if(ExpressionEditorSupportUtil.shouldRememberExpEditorDialogSize()) {
				Point wsize = page0.getShell().getSize();
				ExpressionEditorSupportUtil.saveExpEditorDialogSize(wsize.x,wsize.y);				
			}
			if(ExpressionEditorSupportUtil.shouldRememberExpEditorDialogLocation()) {
				Point wlocation = page0.getShell().getLocation();
				ExpressionEditorSupportUtil.saveExpEditorDialogLocation(wlocation.x,wlocation.y);				
			}
		}
	}
	
}
