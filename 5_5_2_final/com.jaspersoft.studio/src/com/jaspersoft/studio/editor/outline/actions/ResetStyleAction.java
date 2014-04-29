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
package com.jaspersoft.studio.editor.outline.actions;

import net.sf.jasperreports.engine.design.JRDesignStyle;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.ui.IWorkbenchPart;

import com.jaspersoft.studio.JSSCompoundCommand;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.style.MStyle;
import com.jaspersoft.studio.model.style.command.ResetConditionalStyleCommand;
import com.jaspersoft.studio.model.style.command.ResetStyleCommand;

/**
 * Reset the attributes of a style (except the name) to the default value
 * 
 * @author Orlandin Marco
 *
 */
public class ResetStyleAction extends SelectionAction {

	/** The Constant ID. */
	public static final String ID = "reset_style_properites"; //$NON-NLS-1$

	/**
	 * Constructs a <code>CreateAction</code> using the specified part.
	 * 
	 * @param part
	 *          The part for this action
	 */
	public ResetStyleAction(IWorkbenchPart part) {
		super(part);
		setLazyEnablementCalculation(false);
	}

	@Override
	protected void init() {
		super.init();
		setText(Messages.ResetStyleAction_actionTitle);
		setToolTipText(Messages.ResetStyleAction_actionTooltip);
		setId(ID);
		setEnabled(false);
	}
		
	@Override
	public void run() {
		JSSCompoundCommand commands = new JSSCompoundCommand(null);
		for (Object obj : getSelectedObjects()){
			if (obj instanceof EditPart){
				EditPart part = (EditPart)obj;
				commands.setReferenceNodeIfNull(part.getModel());
				if (part.getModel() instanceof MStyle){
					MStyle style = (MStyle)part.getModel();
					Command resetCommand = null;
 					if (style.getValue() instanceof JRDesignStyle) resetCommand = new ResetStyleCommand(style.getJasperDesign(), style);
 					else resetCommand = new ResetConditionalStyleCommand(style.getJasperDesign(), style);
					commands.add(resetCommand);
				}
			}
		}
		execute(commands);
	}
	
	/**
	 * Only work if the selected elements are MSytle
	 */
	@Override
	protected boolean calculateEnabled() {
		if (getSelectedObjects().isEmpty()) return false;
		for (Object obj : getSelectedObjects()){
			if (obj instanceof EditPart){
				if (!(((EditPart)obj).getModel() instanceof MStyle)) return false;
			}
		}
		return true;
	}

}
