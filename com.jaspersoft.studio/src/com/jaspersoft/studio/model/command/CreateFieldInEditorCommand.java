/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.model.command;

import java.util.UUID;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialogWithToggle;
import org.eclipse.swt.SWT;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.editor.layout.spreadsheet.SpreadsheetLayout;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.band.MBand;
import com.jaspersoft.studio.model.field.MField;
import com.jaspersoft.studio.model.text.MStaticText;
import com.jaspersoft.studio.model.text.MTextField;
import com.jaspersoft.studio.preferences.DesignerPreferencePage;
import com.jaspersoft.studio.property.dataset.dialog.DataQueryAdapters;
import com.jaspersoft.studio.utils.ModelUtils;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.engine.JRElementGroup;
import net.sf.jasperreports.engine.design.JRDesignBand;
import net.sf.jasperreports.engine.design.JRDesignField;
import net.sf.jasperreports.engine.design.JRDesignFrame;
import net.sf.jasperreports.engine.design.JRDesignStaticText;
import net.sf.jasperreports.engine.type.BandTypeEnum;

/**
 * Command used to create a field in the editor and eventually a label if the field is created into a detail
 * section
 * 
 * @author Orlandin Marco
 *
 */
public class CreateFieldInEditorCommand extends CreateE4ObjectCommand {
	
	/**
	 * The parent of the static text
	 */
	private JRElementGroup staticTextParent;
	
	/**
	 * The static text generated, can be null if no static text is created
	 */
	private MStaticText staticText = null;
	
	/**
	 * The number of dragged fields, the static text is created only if there is only one field
	 */
	private int createdFields = 1;
	
	public CreateFieldInEditorCommand(MField child, ANode parent, Rectangle location, int index) {
		super(child, parent, location, index);
	}

	/**
	 * Check if at the report was added a Field and if it it was added into the detail band. In this case it can create a
	 * static text as title for the field, and pace it into the column header or in the detail (if in the column header
	 * there isn't enough space or it is not present). The Static Text creation behavior is specified by a properties 
	 * read from the preference store
	 * 
	 * @param previusCommand
	 *          the command that create the filed, must be a CompundCommand that contains the create element command
	 */
	private MStaticText createLabelForField() {
		// get the column header
		ANode dest = ModelUtils.getReport(destNode).getBand(BandTypeEnum.COLUMN_HEADER);
		// It is a creation request, into the detail, with one or more commands encapsulated into a compound one
		int staticTextHeight = new MStaticText().getDefaultHeight();
		int defaultWidth = new MTextField().getDefaultWidth();
		boolean placeinTheHedaer = false;
		if ((dest != null) && (dest.getValue() != null)) {
			int columnHeaderHeight = ((MBand)dest).getValue().getHeight();
			if (columnHeaderHeight > 0) {
				placeinTheHedaer = true;
				if (columnHeaderHeight < staticTextHeight) {
					staticTextHeight = columnHeaderHeight;
				}
			}
		}
	
		if (!placeinTheHedaer) {
			if (createdFields > 1) {
				return null;
			}
			// If it is placed at the right of the textfield it has it same height
			staticTextHeight = new MTextField().getDefaultHeight();
			dest = destNode;
		}
		
		// Create the new element
		MStaticText newText = new MStaticText();
		JRDesignStaticText newTextElement = (JRDesignStaticText) newText.createJRElement(parent.getJasperDesign());
		String labelText = null;
		
		String plabel = ((MField)child).getValue().getPropertiesMap().getProperty(DataQueryAdapters.FIELD_LABEL);
		if (!Misc.isNullOrEmpty(plabel)) {
			labelText = plabel;
		} else {
			labelText = child.getDisplayText();
		}
		
		Boolean useDescription = child.getJasperConfiguration().getPropertyBoolean(DesignerPreferencePage.P_USE_FIELD_DESCRIPTION, false);
		if (useDescription) {
			Object description = ((APropertyNode)child).getPropertyValue(JRDesignField.PROPERTY_DESCRIPTION);
			if (description instanceof String) {
				labelText = (String) description;
			}
		}
		newTextElement.setText(labelText);
		if (placeinTheHedaer){
			String uuID = UUID.randomUUID().toString();
			newTextElement.getPropertiesMap().setProperty(SpreadsheetLayout.PROPERTY_ID, uuID);
			srcNode.getValue().getPropertiesMap().setProperty(SpreadsheetLayout.PROPERTY_ID, uuID);
		}
		newText.setValue(newTextElement);
		// Take the command of the text field to calculate the positions
		Rectangle location = null;
		String dragMessage = null;
		if (placeinTheHedaer) {
			// There is enough space in the Column header, the static text will be placed into it
			int x = srcNode.getValue().getX();
			int actualWidth = getLocation().width != -1 ? getLocation().width : defaultWidth;
			location = new Rectangle(x, 0, actualWidth, staticTextHeight);
			dragMessage = Messages.JSSTemplateTransferDropTargetListener_createLabelMessage2;
		} else {
			// There isn't enough space in the Column header, the static text will be placed into the detail if only one
			// field is dragged
			int actualWidth = getLocation().width != -1 ? getLocation().width : defaultWidth;
			int x = srcNode.getValue().getX() - actualWidth;
			int y = srcNode.getValue().getY();
			location = new Rectangle(x, y, actualWidth, staticTextHeight);
			dragMessage = Messages.JSSTemplateTransferDropTargetListener_createLabelMessage1;
		}
		// Check if was generated a command
		if (location != null) {
			// Get the behavior for the creation of the static text
			String dragBehavior = JaspersoftStudioPlugin.getInstance().getPreferenceStore().getString(DesignerPreferencePage.BEHAVIOR_ON_FIELD_DROP);

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
			if (dragBehavior.equals(DesignerPreferencePage.BEHAVIOR_CREATE_LABEL)){
				staticTextParent = (JRElementGroup)dest.getValue();
				new CreateElementCommand(dest, newText, location, index).execute();
				return newText;
			}
		}
		return null;
	}

	
	@Override
	public void execute() {
		super.execute();
		ANode parentBand = destNode;
		while (parentBand != null && !(parentBand instanceof MBand)){
			parentBand = parentBand.getParent();
		}
		if (parentBand != null && ((MBand)parentBand).getBandType() == BandTypeEnum.DETAIL){
			staticText = createLabelForField();
		}
	}

	@Override
	public void undo() {
		super.undo();
		if (staticText != null){
			if (staticTextParent instanceof JRDesignBand){
				((JRDesignBand)staticTextParent).removeElement(staticText.getValue());
			} else if (staticTextParent instanceof JRDesignFrame){
				((JRDesignFrame)staticTextParent).removeElement(staticText.getValue());
			}
			
			staticText = null;
		}
	}
	
	/**
	 * Set the number of dragged fields
	 * 
	 * @param createdFields the number of fileds dragged, should be >= 1
	 */
	public void setCreatedFields(int createdFields){
		this.createdFields = createdFields;
	}
}
