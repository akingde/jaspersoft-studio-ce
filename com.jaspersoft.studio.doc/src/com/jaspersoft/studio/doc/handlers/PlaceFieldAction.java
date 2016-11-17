/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.doc.handlers;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.jface.action.Action;

import com.jaspersoft.studio.model.band.MBand;
import com.jaspersoft.studio.model.command.CreateE4ObjectCommand;
import com.jaspersoft.studio.model.field.MField;

public class PlaceFieldAction extends Action {

	@Override
	public void run() {
		MField field = (MField)HandlersUtil.getRootFields().getChildren().get(0);
		MBand band = (MBand)HandlersUtil.getBand();
		CreateE4ObjectCommand addField = new CreateE4ObjectCommand(field, band, new Rectangle(200, 200, -1, -1), -1);
		addField.execute();
	}
}
