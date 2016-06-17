/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved. http://www.jaspersoft.com.
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.jface.dialogs;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

/**
 * Dialog proposed when an image needs to be selected.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 * 
 */
public class StyleTemplateSelectionDialog extends FileSelectionDialog {

	/**
	 * Expression that will be shown in the dialog once opened
	 */
	private String initialExpression = null;	
	
	/**
	 * Create the dialog.
	 * 
	 * @param parentShell
	 */
	public StyleTemplateSelectionDialog(Shell parentShell) {
		super(parentShell);
	}

	/**
	 * @return the title for the dialog
	 */
	protected String getDialogTitle() {
		return "Select a Style Template";
	}

	/**
	 * Returns an array of strings containing the title for the modes section, plus the title of every mode.
	 * <p>
	 * 
	 * Default implementation would return 6 strings, including 1 title and the following 5 modes:
	 * <ol>
	 * <li>workspace resource;</li>
	 * <li>absolute path in filesystem;</li>
	 * <li>URL;</li>
	 * <li>no image;</li>
	 * <li>custom expression</li>
	 * </ol>
	 * 
	 * @return the title and labels for the group of modes
	 */
	protected String[] getImageModesAndHeaderTitles() {
		return new String[] { "Style Template selection mode", "Workspace resource (an element inside the workspace)",
				"Absolute Path in the filesystem (use only for quick testing, never use in real reports)",
				"URL (a remote URL referring to a Style Template, will be the expression value)",
				"No Style Template (no Style Template reference will be set)",
				"Custom expression (enter an expression for the Style Template using the expression editor)" };
	}

	@Override
	protected String getFileExtension() {
		return "*.jrtx";
	}

	@Override
	protected String[] getFileExtensions() {
		return new String[] { "*.jrtx", "*.*" };
	}
	
	/**
	 * Set the expression that will be shown in the dialog 
	 * once opened
	 * 
	 * @param expression the expression text, can be null if 
	 * nothing should be shown
	 */
	public void setInitialExpression(String expression){
		this.initialExpression = expression;
	}
	
	@Override
	protected Control createDialogArea(Composite parent) {
		Control control = super.createDialogArea(parent);
		if (initialExpression != null){
			showCustomExpression(initialExpression);	
		}
		return control;
	}
}
