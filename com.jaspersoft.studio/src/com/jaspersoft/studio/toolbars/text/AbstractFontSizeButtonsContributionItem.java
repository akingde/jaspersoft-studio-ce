/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.toolbars.text;

import java.text.MessageFormat;
import java.util.List;

import org.eclipse.gef.commands.Command;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.views.properties.IPropertySource;

import com.jaspersoft.studio.JSSCompoundCommand;
import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.text.MTextElement;
import com.jaspersoft.studio.property.SetValueCommand;
import com.jaspersoft.studio.toolbars.CommonToolbarHandler;

import net.sf.jasperreports.engine.base.JRBaseFont;
import net.sf.jasperreports.engine.design.JRDesignStyle;

/**
 * Toolbar controls to change the font size of the selected elements. These are the common methods, but every
 * toolitem, corresponding to a font size increase or decrease action, is provided by the implementation.
 * One implementation must be always present in the text toolbar because it act as static element that will
 * give to the toolbar the correct height. The toolbar height can't not be set and it is handled natively,
 * it will be take by the highest static item. A static item is a toolitem with only an image or a text.
 * 
 * @author Orlandin Marco
 *
 */
public abstract class AbstractFontSizeButtonsContributionItem extends CommonToolbarHandler {

	/**
	 * Main container of the controls
	 */
	private Composite controlsArea;
	
	/**
	 * % factor for the increment\decrement
	 */
	public static Integer factor = 10;
	
	/**
	 * Listener called when the on of the fontsize buttons is pressed
	 */
	private SelectionAdapter fontSizeButtonSelect = new SelectionAdapter() {

		@Override
		public void widgetSelected(SelectionEvent e) {
			List<Object> selection = getSelectionForType(MTextElement.class);
			if (selection.isEmpty())
				return;
			JSSCompoundCommand cc = null;
			if (isIncrement()){
				cc = createIncremenrtsCommand(true, selection);
			} else {
				cc = createIncremenrtsCommand(false, selection);
			} 				
			getCommandStack().execute(cc);
		}

	};
	
	@Override
	protected Control createControl(Composite parent) {
		controlsArea = new Composite(parent, SWT.NONE);
		RowLayout layout = new RowLayout();
		layout.marginBottom = 0;
		layout.marginTop = 0;
		layout.marginLeft = 0;
		layout.marginRight = 0;
		controlsArea.setLayout(layout);
		
		ToolBar sizeButtons = new ToolBar(controlsArea, SWT.FLAT | SWT.WRAP);
		if (isIncrement()){
			createFontSizeButton(true, sizeButtons);
		} else {
			createFontSizeButton(false, sizeButtons);
		}

		return controlsArea;
	}
	
	@Override
	protected boolean fillWithToolItems(ToolBar parent) {
		if (isIncrement()){
			ToolItem item = createFontSizeButton(true, parent);
			getToolItems().add(item);
		} else {
			ToolItem item = createFontSizeButton(false, parent);
			getToolItems().add(item);
		}
		return true;
	}

	/**
	 * Create a single button into the toolbar
	 * 
	 * @param increment true if the button should be used for increment, false otherwise
	 */
	protected ToolItem createFontSizeButton(final boolean increment, ToolBar buttons){
		Image imageValue;
		String message;
		if (increment){
			imageValue = JaspersoftStudioPlugin.getInstance().getImage("/icons/resources/edit-size-up.png");
			message = MessageFormat.format(Messages.SPButon_Size_Increment, new Object[]{factor.toString()});
		} else {
			imageValue = JaspersoftStudioPlugin.getInstance().getImage("/icons/resources/edit-size-down.png");
			message = MessageFormat.format(Messages.SPButon_Size_Decrement, new Object[]{factor.toString()});
		}
		ToolItem button = new ToolItem(buttons, SWT.PUSH);
		button.setImage(imageValue);
		button.setToolTipText(message);		
		button.setData(WIDGET_DATA_KEY, JRDesignStyle.PROPERTY_FONT_SIZE);
		button.setWidth(25);
		button.addSelectionListener(fontSizeButtonSelect);
		return button;
	}

	/**
	 * Create a command to change the property of the element
	 * 
	 * @param model the element
	 * @param value the new value
	 * @param property the property
	 * @return the command to change the property
	 */
	protected Command createCommand(Object model, Object value, Object property) {
		if (!(model instanceof IPropertySource))
			return null;
		SetValueCommand cmd = new SetValueCommand();
		cmd.setTarget((IPropertySource) model);
		cmd.setPropertyId(property);
		cmd.setPropertyValue(value);
		return cmd;
	}

	@Override
	public boolean isVisible() {
		if (!super.isVisible()) {
			return false;
		}
		List<Object> selection = getSelectionForType(MTextElement.class);
		return selection.size() > 0;
	}
	
	/**
	 * Create the command to change the font size
	 * 
	 * @param increment true if you want to increment the font, false otherwise
	 */
	protected JSSCompoundCommand createIncremenrtsCommand(boolean increment, List<Object> models){
		JSSCompoundCommand changeSizeCommands = new JSSCompoundCommand(null);
		for (Object model : models){
			Object fontSize  = ((APropertyNode)model).getPropertyActualValue(JRBaseFont.PROPERTY_FONT_SIZE);
			Float newValue = 2.0f;
			if (fontSize != null && fontSize.toString().length()>0){
				newValue = Float.valueOf(fontSize.toString());
				Integer plus = null;
				if (increment) plus = Math.round((new Float(newValue) / 100)*factor)+1;
				else plus =  Math.round((new Float(newValue) / 100)*-factor)-1;
				if ((newValue+plus)>99) newValue = 99.0f;
				else if ((newValue+plus)>0) newValue += plus;

				Command c = createCommand(model, newValue, JRBaseFont.PROPERTY_FONT_SIZE );
				changeSizeCommands.setReferenceNodeIfNull(model);
				if (c != null) {
					changeSizeCommands.add(c);
				}
			}
		}
		return changeSizeCommands;
	}


	@Override
	public void dispose() {
		super.dispose();
		if (controlsArea != null) {
			controlsArea.dispose();
			controlsArea = null;
		}
		factor = 10;
	}
	
	protected abstract boolean isIncrement();
	
}
