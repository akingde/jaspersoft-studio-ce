/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.preferences;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.ListEditor;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.List;

import com.jaspersoft.studio.editor.expression.ExpressionEditorSupportUtil;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.property.descriptor.expression.dialog.JRExpressionEditor;

import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.eclipse.util.StringUtils;
import net.sf.jasperreports.engine.design.JRDesignExpression;

/**
 * Field editor for a list of user defined expressions.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public class ExpressionListFieldEditor extends ListEditor{
	
	public static final String EXPRESSION_SEP="\n"; //$NON-NLS-1$

	public ExpressionListFieldEditor(String name, String labelText,
			Composite parent) {
		super(name, labelText, parent);
	}

	@Override
	protected String createList(String[] items) {
		return ExpressionEditorPreferencePage.encodeUserDefinedExpression(Arrays.asList(items));
	}

	@Override
	protected String getNewInputObject() {
		if(!ExpressionEditorSupportUtil.isExpressionEditorDialogOpen()) {
			JRExpressionEditor wizard = new JRExpressionEditor();
			WizardDialog dialog = 
					ExpressionEditorSupportUtil.getExpressionEditorWizardDialog(getShell(),wizard);
			if (dialog.open() == Dialog.OK) {
				JRDesignExpression newExp = wizard.getValue();
				if(newExp!=null &&
						!Misc.nvl(newExp.getText()).equals("")){ //$NON-NLS-1$
					String newExprString = newExp.getText();
					java.util.List<String> itemsLst = Arrays.asList(getList().getItems());
					if(itemsLst.contains(newExprString)){
						MessageDialog.openWarning(getShell(), Messages.ExpressionListFieldEditor_CustomExpressionCreationWarningTitle, Messages.ExpressionListFieldEditor_CustomExpressionCreationWarningMsg);
						return null;
					}
					else{
						return newExprString;
					}
				}
			}
		}
		return null;
	}

	@Override
	protected String[] parseString(String stringList) {
        StringTokenizer st = new StringTokenizer(stringList, EXPRESSION_SEP);//$NON-NLS-1$
        ArrayList<String> v = new ArrayList<String>();
        while (st.hasMoreElements()) {
            String nextElement = (String)st.nextElement();
            v.add(StringUtils.safeDecode64(nextElement));
        }
        return v.toArray(new String[v.size()]);
	}
	
	@Override
	public List getListControl(Composite parent) {
		List listControl = super.getListControl(parent);
		if(listControl!=null){
			listControl.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					// activated on double-click, edit the selected expression
					editCurrentExpression();
				}
			});
		}
		return listControl;
	}
	
	/*
	 * Allows editing the currently selected expression text.
	 * A warning is raised if another item with the same text is present.
	 * No action is taken in this case.
	 */
	private void editCurrentExpression() {
		int selectionIndex = getList().getSelectionIndex();
		String currExprTxt = getList().getItem(selectionIndex);
		if(!ExpressionEditorSupportUtil.isExpressionEditorDialogOpen()) {
			JRExpressionEditor wizard = new JRExpressionEditor();
			wizard.setValue(new JRDesignExpression(currExprTxt));
			WizardDialog dialog = 
					ExpressionEditorSupportUtil.getExpressionEditorWizardDialog(getShell(),wizard);
			if (dialog.open() == Dialog.OK) {
				JRDesignExpression newExp = wizard.getValue();
				java.util.List<String> itemsLst = Arrays.asList(getList().getItems());
				if(newExp!=null &&
						!Misc.nvl(newExp.getText()).equals("")){ //$NON-NLS-1$
					String newExprString = newExp.getText();
					if(!currExprTxt.equals(newExprString)){
						if(itemsLst.contains(newExprString)){
							MessageDialog.openWarning(getShell(), Messages.ExpressionListFieldEditor_CustomExpressionEditWarningTitle, Messages.ExpressionListFieldEditor_CustomExpressionEditWarningMessage);
						}
						else{
							getList().setItem(selectionIndex, newExprString);
						}
					}
				}
			}
		}
	}
	
}
