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

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.engine.design.JRDesignField;
import net.sf.jasperreports.engine.design.JRDesignImage;
import net.sf.jasperreports.engine.design.JRDesignSection;
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
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.editor.gef.parts.band.BandEditPart;
import com.jaspersoft.studio.editor.outline.part.NotDragableContainerTreeEditPart;
import com.jaspersoft.studio.editor.outline.part.TreeEditPart;
import com.jaspersoft.studio.editor.palette.JDCreationTool;
import com.jaspersoft.studio.editor.palette.JDPaletteCreationFactory;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.DialogEnabledCommand;
import com.jaspersoft.studio.model.IContainer;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.MReport;
import com.jaspersoft.studio.model.band.MBand;
import com.jaspersoft.studio.model.band.command.CreateBandDetailCommand;
import com.jaspersoft.studio.model.band.command.DeleteBandDetailCommand;
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
				int defaultHeight = new MStaticText().getDefaultHeight();
				int defaultWidth = new MTextField().getDefaultWidth();
				boolean placeinTheHedaer =  (dest != null) && (dest.getValue() != null) && (dest.getValue().getHeight() >= defaultHeight);
				if (!placeinTheHedaer) {
					dest = band;
					//If it is placed at the right of the textfield it has it same height
					defaultHeight = new MTextField().getDefaultHeight();
				}
				
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
					String labelText = field.getDisplayText();
					Boolean useDescription = field.getJasperConfiguration().getPropertyBoolean(DesignerPreferencePage.P_USE_FIELD_DESCRIPTION,false);
					if(useDescription){
						Object description = field.getPropertyValue(JRDesignField.PROPERTY_DESCRIPTION);
						if(description instanceof String) {
							labelText = (String) description;
						}
					}
					newTextElement.setText(labelText);
					newText.setValue(newTextElement);
					// Take the command of the text field to calculate the positions
					Rectangle location = null;
					if (placeinTheHedaer) {
						// There is enough space in the Column header, the static text will be placed into it
						int x = creatElementC.getLocation().x - band.getBounds().x;
						int y = creatElementC.getLocation().y - band.getBounds().y;
						int actualWidth = creatElementC.getLocation().width != -1 ? creatElementC.getLocation().width : defaultWidth;
						location = new Rectangle(x, y, actualWidth, defaultHeight);
						location.y = 0;
						dragMessage = Messages.JSSTemplateTransferDropTargetListener_createLabelMessage2;
					} else if (creationCommands.size()==1){
						// There isn't enough space in the Column header, the static text will be placed into the detail if only one field is dragged
						int x = creatElementC.getLocation().x - band.getBounds().x;
						int y = creatElementC.getLocation().y - band.getBounds().y;
						int actualWidth = creatElementC.getLocation().width != -1 ? creatElementC.getLocation().width : defaultWidth;
						location = new Rectangle(x, y, actualWidth, defaultHeight);
						location.x = location.getLocation().x - location.width;
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
	
	
  private int getItemIndex(TreeItem item){
  	TreeItem parent = item.getParentItem();
    TreeItem[] items = parent.getItems();
    int index = 0;
    for (int i = 0; i < items.length; i++) {
      if (items[i] == item) {
        index = i;
        break;
      }
    }
    return index;
  }
  
  
  /**
   * Generate the commands to move the selected bands into a specific location
   * 
   * @param moved list of the bands to move
   * @param location new position o the bands
   * @param parentPart root node of the report tree, used to refresh its children
   * @return a list of command
   */
  private CompoundCommand moveBandsCommand(List<MBand> moved, int location, final EditPart parentPart){
 	  final ANode report = moved.get(0).getParent(); 
  	
 	  /**
 	   * Customized compound command that after the execute and undo force a refresh of the editor and outline
 	   */
 	  CompoundCommand cmd = new CompoundCommand(){
 	  	
      //I create a fake command to force the refresh of the editor and outline panels
  		private void refreshVisuals(){
	 			 PropertyChangeEvent event = new PropertyChangeEvent(report.getJasperDesign(), "refresh", null, null);
	 			 report.propertyChange(event);
					 for(Object part : parentPart.getChildren()){
						 ((EditPart)part).refresh();
					 }
  		}
  		
  		private void refreshBandNumbers(){
  			for(INode node : report.getChildren()){
  				if (node instanceof MBand){
  					((MBand)node).refreshIndex();
  				}
  			}
  		}
  		
  		 @Override
  		public void execute() {
  			 super.execute();
  			 refreshBandNumbers();
  			 refreshVisuals();
  		}
  		 
  		 @Override
  		public void undo() {
  			 super.undo();
  			 refreshBandNumbers();
  			 refreshVisuals();
  		}
  	 };
     for (MBand bandNode : moved){
    	 //cmd.add(new SetDetailNumberCommand((MReport)bandNode.getParent(), bandNode.getDetailIndex(), bandNode.getValue(), true));
	     DeleteBandDetailCommand deleteBand = new DeleteBandDetailCommand(bandNode.getParent(), bandNode);
		   cmd.add(deleteBand);
		 	 CreateBandDetailCommand createBand = new CreateBandDetailCommand((MBand)bandNode, (MBand)bandNode,location);
		 	 cmd.add(createBand); 
		 	 //cmd.add(new SetDetailNumberCommand((MReport)bandNode.getParent(), bandNode.getDetailIndex(), bandNode.getValue(), false));
     }
     return cmd;
  }
	
  /**
   * Check if the user is dragging a detail band to move it before or after another detail band. In this
   * case it return the command to do this operation, otherwise null
   * 
   * @return command to move the detail band or null if the user is not moving the band
   */
	private CompoundCommand dropDetailBands(){
		if (getCurrentEvent().detail != DND.DROP_MOVE) return null;
		if (getCurrentEvent().item == null || !(getCurrentEvent().item instanceof TreeItem)) return null;
		Tree tree = ((TreeItem)getCurrentEvent().item).getParent();
		List<MBand> movedBands = new ArrayList<MBand>();
		for (TreeItem item : tree.getSelection()){
			TreeEditPart draggetItem = (TreeEditPart)item.getData();
			if (draggetItem.getModel() instanceof MBand){
				movedBands.add((MBand)draggetItem.getModel());
			}
		}
		if (movedBands.size() == 0) return null;
		TreeItem destinationItem = (TreeItem)getCurrentEvent().item;
    Point pt = tree.getDisplay().map(null, tree, getCurrentEvent().x, getCurrentEvent().y);
		org.eclipse.swt.graphics.Rectangle destinationBounds = destinationItem.getBounds();
		TreeItem firstItem = null;
		TreeItem secondItem = null;
		if (pt.y > (destinationBounds.y + destinationBounds.height/2)){
			firstItem = destinationItem;
			int destinationIndex = getItemIndex(destinationItem);
			secondItem = destinationItem.getParentItem().getItem(destinationIndex+1);
		} else {
			secondItem = destinationItem;
			int destinationIndex = getItemIndex(destinationItem);
			firstItem = destinationItem.getParentItem().getItem(destinationIndex-1);
		}
		Object model1 = ((NotDragableContainerTreeEditPart)firstItem.getData()).getModel();
		Object model2 = ((NotDragableContainerTreeEditPart)secondItem.getData()).getModel();
		MBand band1 = null;
		MBand band2 = null;
		if (model1 instanceof MBand) band1 = (MBand)model1;
		if (model2 instanceof MBand) band2 = (MBand)model2;
		if (band1 == null && band2 == null) return null;
		int destinationIndex = -1;
		//CASE: destination between two details, the index is the same of the second band
		if (band1 != null && band1.getBandType() == BandTypeEnum.DETAIL && band2 != null && band2.getBandType() == BandTypeEnum.DETAIL ) {
			destinationIndex = ((JRDesignSection)band2.getJasperDesign().getDetailSection()).getBandsList().indexOf(band2.getValue())-1;
		} else if (band2 != null && band2.getBandType() == BandTypeEnum.DETAIL ) { //CASE: only band 2 is a detail band, so i'm putting something at the top
			destinationIndex = 0;
		} else if (band1 != null && band1.getBandType() == BandTypeEnum.DETAIL){//CASE: only band 2 is a detail band, so i'm putting something at the bottom
			destinationIndex = ((JRDesignSection)band1.getJasperDesign().getDetailSection()).getBandsList().indexOf(band1.getValue())+1;
		}
	  return moveBandsCommand(movedBands, destinationIndex, ((NotDragableContainerTreeEditPart)firstItem.getData()).getParent());
	}
	
	@Override
	protected void handleDrop() {
		updateTargetRequest();
		updateTargetEditPart();
		CompoundCommand movingDetails = dropDetailBands();
		if (movingDetails != null){
			getViewer().getEditDomain().getCommandStack().execute(movingDetails);
		} else if (getTargetEditPart() != null) {
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
