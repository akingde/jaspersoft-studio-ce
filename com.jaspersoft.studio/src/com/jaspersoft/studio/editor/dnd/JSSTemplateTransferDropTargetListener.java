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
package com.jaspersoft.studio.editor.dnd;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.engine.design.JRDesignImage;
import net.sf.jasperreports.engine.design.JRDesignStaticText;
import net.sf.jasperreports.engine.type.BandTypeEnum;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.dnd.TemplateTransferDropTargetListener;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gef.requests.CreationFactory;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialogWithToggle;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.editor.gef.parts.band.BandEditPart;
import com.jaspersoft.studio.editor.palette.JDCreationTool;
import com.jaspersoft.studio.editor.palette.JDPaletteCreationFactory;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.DialogEnabledCommand;
import com.jaspersoft.studio.model.IContainer;
import com.jaspersoft.studio.model.MReport;
import com.jaspersoft.studio.model.band.MBand;
import com.jaspersoft.studio.model.command.CreateE4ObjectCommand;
import com.jaspersoft.studio.model.command.CreateElementCommand;
import com.jaspersoft.studio.model.field.MField;
import com.jaspersoft.studio.model.image.command.dialog.ImageCreationDialog;
import com.jaspersoft.studio.model.text.MStaticText;
import com.jaspersoft.studio.model.text.MTextField;
import com.jaspersoft.studio.preferences.DesignerPreferencePage;

/**
 * Custom transfer drop listener for DND operations that supports 
 * the {@link DialogEnabledCommand} commands.
 * <p>
 * 
 * The code in the {@link #handleDrop()} method is similar to the one used in 
 * {@link JDCreationTool#performCreation(int)}. We want to emulate the same behavior.<br/>
 * A working example for this situation is the {@link ImageCreationDialog} that is
 * popup when creating an {@link JRDesignImage} element. 
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 * @see DialogEnabledCommand
 *
 */
public class JSSTemplateTransferDropTargetListener extends TemplateTransferDropTargetListener {

	public JSSTemplateTransferDropTargetListener(EditPartViewer viewer) {
		super(viewer);
	}
	
	@Override
	protected CreationFactory getFactory(Object template) {
		return new JDPaletteCreationFactory(template);
	}
	
	/**
	 * Get a compound command and a list of commands and add to the compound command every
	 * command in the list
	 * 
	 * @param compCommand the compound command
	 * @param commandsToAdd a list of command that will be added to the compound one
	 */
	private void addAll(CompoundCommand compCommand, List<Command> commandsToAdd){
		for(Command command : commandsToAdd)
			compCommand.add(command);
	}

	/**
	 * Check if at the report was added a Field and if it it was added into the detail band.
	 * In this case it can create a static text as title for the field, and pace it into the 
	 * column header or in the detail (if in the column header there isn't enough space). The Static 
	 * Text creation behavior is specified by a properties read from the preference store
	 * 
	 * @param previusCommand the command that create the filed, must be a CompundCommand that contains
	 * the create element command
	 */
	private void createLabelForField(Command previusCommand){
		Request target = getTargetRequest();
		if (target instanceof CreateRequest) {
			EditPart container = getContainer();
			if (container instanceof BandEditPart && 
							((MBand) container.getModel()).getBandType() == BandTypeEnum.DETAIL &&
																							previusCommand instanceof CompoundCommand) {
				CompoundCommand compCommand = (CompoundCommand) previusCommand;
				List<Command> commandToAdd = new ArrayList<Command>();
				// I'm creating something inside the detail band
				MBand band = (MBand) container.getModel();
				// get the column header
				MBand dest = ((MReport) band.getParent()).getBand(BandTypeEnum.COLUMN_HEADER);
				//It is a creation request, into the detail, with one or more commands encapsulated into a compound one
				int defaultHeight = new MTextField().getDefaultHeight();
				int defaultWidth = new MTextField().getDefaultWidth();
				boolean placeinTheHedaer =  dest != null && dest.getValue() != null && dest.getValue().getHeight() > defaultHeight + 2;
				if (!placeinTheHedaer) dest = band;
				
				List<CreateE4ObjectCommand> creationCommands = new ArrayList<CreateE4ObjectCommand>();
				//Build a list of all the created fields
				for (Object command : compCommand.getCommands()) {
					if (command instanceof CreateE4ObjectCommand && ((CreateE4ObjectCommand) command).getChild() instanceof MField) 
							creationCommands.add((CreateE4ObjectCommand) command);
				}
				String dragMessage = null; 
				for(CreateE4ObjectCommand creatElementC :creationCommands){
					// Create the new element
					MStaticText newText = new MStaticText();
					MField field = (MField) creatElementC.getChild();
					JRDesignStaticText newTextElement = (JRDesignStaticText) newText.createJRElement(band.getJasperDesign());
					newTextElement.setText(field.getDisplayText());
					newText.setValue(newTextElement);
					// Take the command of the text field to calculate the positions
					Rectangle location = null;
					if (placeinTheHedaer) {
						// There is enough space in the Column header, the static text will be placed into it
						int x = creatElementC.getLocation().x - band.getBounds().x;
						int y = creatElementC.getLocation().y - band.getBounds().y;
						int actualWidth = creatElementC.getLocation().width != -1 ? creatElementC.getLocation().width : defaultWidth;
						location = new Rectangle(x, y, actualWidth, defaultHeight);
						location.setY(2);
						dragMessage = Messages.JSSTemplateTransferDropTargetListener_createLabelMessage2;
					} else if (creationCommands.size()==1){
						// There isn't enough space in the Column header, the static text will be placed into the detail if only one field is dragged
						int x = creatElementC.getLocation().x - band.getBounds().x;
						int y = creatElementC.getLocation().y - band.getBounds().y;
						int actualWidth = creatElementC.getLocation().width != -1 ? creatElementC.getLocation().width : defaultWidth;
						location = new Rectangle(x, y, actualWidth, defaultHeight);
						location.setX(location.getLocation().x - location.width);
						dragMessage = Messages.JSSTemplateTransferDropTargetListener_createLabelMessage1;
					}
					//Check if was generated a command
					if (location != null){
							// Get the behavior for the creation of the static text
							String dragBehavior = JaspersoftStudioPlugin.getInstance().getPreferenceStore()
									.getString(DesignerPreferencePage.BEHAVIOR_ON_FIELD_DROP);
		
							if (dragBehavior.equals(DesignerPreferencePage.BEHAVIOR_ASK_EVERYTIME)) {
								// The behavior say to ask to the user
								MessageDialogWithToggle question = MessageDialogWithToggle.open(MessageDialogWithToggle.QUESTION,
										UIUtils.getShell(), Messages.JSSTemplateTransferDropTargetListener_createLabelTitle, dragMessage,
										null, false, null, null, SWT.NONE);
								// Update the behavior with the choice of the user
								if (question.getReturnCode() == IDialogConstants.YES_ID)
									dragBehavior = DesignerPreferencePage.BEHAVIOR_CREATE_LABEL;
								else
									dragBehavior = DesignerPreferencePage.BEHAVIOR_DO_NOTHING;
		
								// Check if the choice must be saved
								if (question.getToggleState()) {
									JaspersoftStudioPlugin.getInstance().getPreferenceStore()
											.setValue(DesignerPreferencePage.BEHAVIOR_ON_FIELD_DROP, dragBehavior);
								}
							}
							if (dragBehavior.equals(DesignerPreferencePage.BEHAVIOR_CREATE_LABEL))
								commandToAdd.add(new CreateElementCommand(dest, newText, location, -1));
					}
					location = null;
				}
				//Add to the compund command all the command for the label, if they are requested
				addAll(compCommand, commandToAdd);
			}

		}
	}
	
	/**
	 * Take an edit part and search it's container if it is not a container. 
	 * 
	 * @return the container of the actual target edit part, if the target edit part is not a container, or 
	 * the target edit part itself otherwise
	 */
	private EditPart getContainer() {
		EditPart target = getTargetEditPart();
		if (!(target instanceof IContainer)){
			ANode parentModel = ((ANode) target.getModel()).getParent();
			// This use the model for the search because every EditPart in the report has the same father.
			for (Object actualChild : target.getParent().getChildren()) {
				EditPart actualChildPart = (EditPart) actualChild;
				if (parentModel == actualChildPart.getModel()) return actualChildPart;
			}
		}
		return target;
	}
	
	@Override
	protected void handleDrop() {
		updateTargetRequest();
		updateTargetEditPart();
		if (getTargetEditPart() != null) {
			Command command = getCommand();
			
			createLabelForField(command);
			
			if(command instanceof DialogEnabledCommand && command.canExecute()){
				// If we have a special command that supports dialog (i.e: image creation)
				// we'll show the popup dialog and continue with creation only if
				// the user has confirmed.
				if(((DialogEnabledCommand)command).openDialog()==Dialog.CANCEL){
					getCurrentEvent().detail = DND.DROP_NONE;
					return;
				}
			}
			if (command != null && command.canExecute())
				if (command instanceof CompoundCommand){
					//If the command is a compound command i execute its content one by one
					for(Object singleCmd : ((CompoundCommand)command).getCommands()){
						if (singleCmd instanceof CreateElementCommand){
							CreateElementCommand cmd = (CreateElementCommand)singleCmd;
							getViewer().getEditDomain().getCommandStack().execute(cmd);
							//if one command is cancelled during the execution even the following are skipped
							if (cmd.isCancelled()) break;
						} else getViewer().getEditDomain().getCommandStack().execute((Command)singleCmd);
					}
				}
				else getViewer().getEditDomain().getCommandStack().execute(command);
			else
				getCurrentEvent().detail = DND.DROP_NONE;
		} else
			getCurrentEvent().detail = DND.DROP_NONE;
		selectAddedObject();
	}

	private void selectAddedObject() {
		Object model = getCreateRequest().getNewObject();
		if (model == null)
			return;
		EditPartViewer viewer = getViewer();
		viewer.getControl().forceFocus();
		Object editpart = viewer.getEditPartRegistry().get(model);
		if (editpart instanceof EditPart) {
			// Force a layout first.
			getViewer().flush();
			viewer.select((EditPart) editpart);
		}
	}
	
}
