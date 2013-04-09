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
package com.jaspersoft.studio.editor.action.text;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.JRGroup;
import net.sf.jasperreports.engine.JRStyle;
import net.sf.jasperreports.engine.design.JRDesignStaticText;
import net.sf.jasperreports.engine.design.JRDesignTextField;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.ui.IWorkbenchPart;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.editor.gef.parts.text.StaticTextFigureEditPart;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.command.CreateElementCommand;
import com.jaspersoft.studio.model.command.DeleteElementCommand;
import com.jaspersoft.studio.model.text.MStaticText;
import com.jaspersoft.studio.model.text.MTextField;
import com.jaspersoft.studio.property.descriptor.expression.ExprUtil;

/**
 * 
 * Action to convert a Static Text element into a text field element. All the common attributes are 
 * maintained in the conversion
 * 
 * @author Orlandin Marco
 *
 */
public class ConvertStaticIntoText extends SelectionAction {

	/**
	 * The id of the action
	 */
	public static final String ID = "ConvertStaticIntoText"; 
	
	public ConvertStaticIntoText(IWorkbenchPart part) {
		super(part);
		setId(ID);
		setText(Messages.ConvertStaticIntoText_actionName);
		setToolTipText(Messages.ConvertStaticIntoText_actionTooltip);
		setImageDescriptor(JaspersoftStudioPlugin.getInstance().getImageDescriptor("icons/resources/convert_to_field.png")); //$NON-NLS-1$
	}

	@Override
	protected boolean calculateEnabled() {
		return true;
	}
	
	/**
	 * Action visible if it is selected at least one StaticTextFigureEdit Part
	 */
	@Override
	public boolean isEnabled() {
		List<?> editparts = new ArrayList<Object>(getSelectedObjects());
		for(Object part : editparts)
			if (part instanceof StaticTextFigureEditPart) return true;
		return false;
	}

	/**
	 * Check if the text into the static text seems to be valid for an expression
	 * 
	 * @param value text into the static text
	 * @return true if the text could be an expression, otherwise false
	 */
	private boolean isValidExpression(String value){
		if (value.startsWith("\"") && value.endsWith("\"")) return true; 
		if (value.startsWith("$P{") && value.endsWith("}")) return true; 
		if (value.startsWith("$V{") && value.endsWith("}")) return true; 
		if (value.startsWith("$R{") && value.endsWith("}")) return true;
		if (value.startsWith("$F{") && value.endsWith("}")) return true; 
		return false;
	}
	
	/**
	 * Copy all the common attributes from the static text to the new text field element 
	 * 
	 * @param textObject the new text field element, that will substitute the static text
	 * @param labelObject the substituted static text
	 */
	private void cloneTextField(JRDesignTextField textObject, JRDesignStaticText labelObject)
	{
		String staticTextValue = labelObject.getText();
		//If the text is not valid for an expression it will be handled as a string
		if (!isValidExpression(staticTextValue)){
			if (!staticTextValue.startsWith("\"")) staticTextValue = "\"".concat(staticTextValue); //$NON-NLS-1$ //$NON-NLS-2$
			if (!staticTextValue.endsWith("\"")) staticTextValue = staticTextValue.concat("\""); //$NON-NLS-1$ //$NON-NLS-2$
		}
		textObject.setExpression(ExprUtil.setValues(textObject.getExpression(), staticTextValue));
		
		textObject.setHeight(labelObject.getHeight());
		textObject.setWidth(labelObject.getWidth());
		textObject.setX(labelObject.getX());
		textObject.setY(labelObject.getY());
		textObject.setFontName(labelObject.getFontName());
		textObject.setFontSize(labelObject.getFontSize());
		textObject.setBackcolor(labelObject.getBackcolor()); 
		textObject.setForecolor(labelObject.getForecolor());
		
		JRStyle originStyle = labelObject.getStyle();
		textObject.setStyle(originStyle != null ? (JRStyle)originStyle.clone() : null);
		
		textObject.setStyleNameReference(labelObject.getStyleNameReference());
		textObject.setBold(labelObject.isBold());
		textObject.setItalic(labelObject.isItalic());
		textObject.setUnderline(labelObject.isUnderline());
		textObject.setStrikeThrough(labelObject.isStrikeThrough());
		textObject.setHorizontalAlignment(labelObject.getHorizontalAlignmentValue());
		textObject.setVerticalAlignment(labelObject.getVerticalAlignmentValue());
		textObject.setMode(labelObject.getModeValue());
		textObject.setRotation(labelObject.getRotationValue());
		textObject.setStretchType(labelObject.getStretchTypeValue());
		textObject.setKey(labelObject.getKey());
		textObject.setMarkup(labelObject.getMarkup());
		textObject.setPdfEmbedded(labelObject.isPdfEmbedded());
		textObject.setPdfEncoding(labelObject.getPdfEncoding());
		textObject.setPdfFontName(labelObject.getPdfFontName());
		textObject.setPositionType(labelObject.getPositionTypeValue());
		textObject.setPrintInFirstWholeBand(labelObject.isPrintInFirstWholeBand());
		textObject.setPrintRepeatedValues(labelObject.isPrintRepeatedValues());
		textObject.setPrintWhenDetailOverflows(labelObject.isPrintWhenDetailOverflows());
		
		JRExpression originExpression = labelObject.getPrintWhenExpression();
		textObject.setPrintWhenExpression(originExpression != null ? (JRExpression)originExpression.clone() : null);
		
		JRGroup originGroup = labelObject.getPrintWhenGroupChanges();
		textObject.setPrintWhenGroupChanges(originGroup != null ? (JRGroup)originGroup.clone() : null);
		
		textObject.setRemoveLineWhenBlank(labelObject.isRemoveLineWhenBlank());
	}
	
	/**
	 * Create the commands to create a new text field similar to the static text selected
	 * and to delete the static text
	 * 
	 * @return a compound command with two commands in it, one to remove the selected static texts, and one to 
	 * create in their place similar text fields
	 */
	protected Command createAlignmentCommand() {
		CompoundCommand command = new CompoundCommand();
		List<?> editparts = new ArrayList<Object>(getSelectedObjects());
		if (editparts.isEmpty() || !(editparts.get(0) instanceof GraphicalEditPart))
			return command;
		for(Object part : editparts){
			if (part instanceof StaticTextFigureEditPart){
				StaticTextFigureEditPart editPart = (StaticTextFigureEditPart)part;
				MStaticText textField = (MStaticText)editPart.getModel();
				DeleteElementCommand deleteCommand = new DeleteElementCommand(null, textField);
				MTextField modelText = new MTextField();
				
				JRDesignStaticText labelObject = (JRDesignStaticText)textField.getValue();
				JRDesignTextField textObject =  (JRDesignTextField)modelText.createJRElement(textField.getJasperDesign());

				cloneTextField(textObject, labelObject);
				
				
				modelText.setValue(textObject);
				Rectangle position = new Rectangle(labelObject.getX(),labelObject.getY(),labelObject.getWidth(),labelObject.getHeight());

				CreateElementCommand createCommand = new CreateElementCommand(textField.getParent(), modelText, position, -1);
				
				command.add(deleteCommand);
				command.add(createCommand);
			}
		}
		return command;
	}

	/**
	 * @see org.eclipse.jface.action.IAction#run()
	 */
	public void run() {
		execute(createAlignmentCommand());
	}
}
