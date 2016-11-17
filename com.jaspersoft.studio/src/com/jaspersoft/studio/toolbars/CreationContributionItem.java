/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.toolbars;

import java.util.List;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.callout.MCallout;
import com.jaspersoft.studio.callout.pin.command.CreatePinCommand;

import net.sf.jasperreports.engine.design.JRDesignElement;

/**
 * Create the toolbar button to create a pin
 * 
 * @author Orlandin Marco
 *
 */
public class CreationContributionItem extends CommonToolbarHandler {

	@Override
	protected Control createControl(Composite parent) {
		ToolBar buttons = new ToolBar(parent, SWT.FLAT | SWT.WRAP);
		
		ToolItem createPin = new ToolItem(buttons, SWT.PUSH);
		createPin.setImage(JaspersoftStudioPlugin.getInstance().getImage("icons/pin-16.png"));
		createPin.addSelectionListener(new SelectionAdapter() {
		
			@Override
			public void widgetSelected(SelectionEvent e) {
				List<Object> selection = getSelectionForType(MCallout.class);
				if (selection.size() == 1){
					Rectangle location = new Rectangle();
					MCallout mcallout = (MCallout)selection.get(0);
					location.x = 20 + (Integer) mcallout.getPropertyValue(JRDesignElement.PROPERTY_X);
					location.y = -24 + (Integer) mcallout.getPropertyValue(JRDesignElement.PROPERTY_Y);
					Command cmd = new CreatePinCommand(mcallout, location);
					if (cmd != null){
						getCommandStack().execute(cmd);
					}
				}
			}
		});
		
		return buttons;
	}
	
	@Override
	protected boolean fillWithToolItems(ToolBar parent) {
		ToolItem createPin = new ToolItem(parent, SWT.PUSH);
		createPin.setImage(JaspersoftStudioPlugin.getInstance().getImage("icons/pin-16.png"));
		createPin.addSelectionListener(new SelectionAdapter() {
		
			@Override
			public void widgetSelected(SelectionEvent e) {
				List<Object> selection = getSelectionForType(MCallout.class);
				if (selection.size() == 1){
					Rectangle location = new Rectangle();
					MCallout mcallout = (MCallout)selection.get(0);
					location.x = 20 + (Integer) mcallout.getPropertyValue(JRDesignElement.PROPERTY_X);
					location.y = -24 + (Integer) mcallout.getPropertyValue(JRDesignElement.PROPERTY_Y);
					Command cmd = new CreatePinCommand(mcallout, location);
					if (cmd != null){
						getCommandStack().execute(cmd);;
					}
				}
			}
		});
		getToolItems().add(createPin);
		return true;
	}
	
	@Override
	public boolean isVisible() {
		if (!super.isVisible()) return false;
		List<Object> selection = getSelectionForType(MCallout.class);
		return selection.size() == 1;
	}
}
