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
package com.jaspersoft.studio.toolbars;

import org.eclipse.jface.viewers.ISelection;

import com.jaspersoft.studio.components.table.model.column.MCell;
import com.jaspersoft.studio.components.table.model.column.action.CreateColumnBeforeAction;
import com.jaspersoft.studio.editor.action.SetWorkbenchAction;

/**
 * Create the toolbar button to add a column to the selected table
 * 
 * @author Orlandin Marco
 *
 */
public class CreateColumnBeforeContributionItem extends CreateColumnContributionItem{
	
	/**
	 * Action that will be executed to add the column
	 */
	@Override
	protected SetWorkbenchAction getAction(){ 
		return new CreateColumnBeforeAction(null){

			@Override
			protected ISelection getSelection() {
				return getLastRawSelection();
			}
		};
	}
	
	@Override
	public boolean isVisible() {
		if (!super.isVisible()) return false;
		if (getSelectionForType(MCell.class).size() == 1){
			setEnablement();
			return true;
		}
		return false;
	}
	
}
