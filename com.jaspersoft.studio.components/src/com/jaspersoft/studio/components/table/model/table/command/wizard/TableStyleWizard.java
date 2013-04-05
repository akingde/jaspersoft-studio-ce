/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.components.table.model.table.command.wizard;

import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.components.table.messages.Messages;
import com.jaspersoft.studio.components.table.model.dialog.TableStyle;
import com.jaspersoft.studio.wizards.JSSWizard;

/**
 * A wizard only with the step to define the table style
 * 
 * @author Orlandin Marco
 *
 */
public class TableStyleWizard extends JSSWizard {

	/**
	 * The layout step of the wizard
	 */
	private TableWizardLayoutPage layoutPage;
	
	/**
	 * True if the dialog should have the title field
	 */
	private boolean showTitle;
	
	public TableStyleWizard(boolean showTitle) {
		super();
		setWindowTitle(Messages.common_table_wizard);
		setNeedsProgressMonitor(true);
		this.showTitle = showTitle;
	}
	
	public TableStyleWizard() {
		this(false);
	}

	/**
	 * Return the TableStyle selected in the layout step
	 * 
	 * @return a TableStyle
	 */
	public TableStyle getTableStyle(){
		return layoutPage.getSelectedStyle();
	}
	
	@Override
	public void createPageControls(Composite pageContainer) {
		super.createPageControls(pageContainer);
		layoutPage.setEnabledBottomPanel(false);
	}

	@Override
	public void addPages() {
		layoutPage = new TableWizardLayoutPage(showTitle);
		addPage(layoutPage);
		//Hide the Next and Previous buttons
		setForcePreviousAndNextButtons(false);
	}
}
