/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.background.action;

import java.util.List;

import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.gef.commands.Command;
import org.eclipse.ui.IWorkbenchPart;

import com.jaspersoft.studio.JSSCompoundCommand;
import com.jaspersoft.studio.background.MBackgrounImage;
import com.jaspersoft.studio.editor.action.ACachedSelectionAction;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.property.SetValueCommand;

/**
 * Action to made the background fit the report page width
 * 
 * @author Orlandin Marco
 * 
 */
public class BackgroundFitAction extends ACachedSelectionAction {
  
  public static final String ID = "BackgroundFitWidth"; //$NON-NLS-1$
  
	public BackgroundFitAction(IWorkbenchPart part) {
		super(part);
		setLazyEnablementCalculation(true);
	}
	
	@Override
	protected void init() {
		super.init();
		setText(Messages.MBackgrounImage_labelFitWidth);
		setId(ID);
		setEnabled(false);
	}

	@Override
	protected Command createCommand() {
		List<Object> background = editor.getSelectionCache().getSelectionModelForType(MBackgrounImage.class);
		if (background.isEmpty()) return null;
		MBackgrounImage backgroundModel = (MBackgrounImage)background.get(0);
		
		JasperDesign jd = backgroundModel.getValue();
		
		JSSCompoundCommand cc = new JSSCompoundCommand(backgroundModel);
		
		SetValueCommand command = new SetValueCommand();
		command.setTarget((MBackgrounImage)background.get(0));
		command.setPropertyId(MBackgrounImage.PROPERTY_X);
		command.setPropertyValue(10);
		cc.add(command);
		
		command = new SetValueCommand();
		command.setTarget((MBackgrounImage)background.get(0));
		command.setPropertyId(MBackgrounImage.PROPERTY_Y);
		command.setPropertyValue(10);
		cc.add(command);
		
		command = new SetValueCommand();
		command.setTarget((MBackgrounImage)background.get(0));
		command.setPropertyId(MBackgrounImage.PROPERTY_WIDTH);
		command.setPropertyValue(jd.getPageWidth());
		cc.add(command);
		
		return cc;
	}
}

