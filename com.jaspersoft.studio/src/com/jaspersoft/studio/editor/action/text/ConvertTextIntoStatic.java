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
import net.sf.jasperreports.engine.JRLineBox;
import net.sf.jasperreports.engine.JRParagraph;
import net.sf.jasperreports.engine.JRStyle;
import net.sf.jasperreports.engine.design.JRDesignStaticText;
import net.sf.jasperreports.engine.design.JRDesignTextField;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.ui.IWorkbenchPart;

import com.jaspersoft.studio.JSSCompoundCommand;
import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.editor.gef.parts.text.TextFieldFigureEditPart;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.command.CreateElementCommand;
import com.jaspersoft.studio.model.command.DeleteElementCommand;
import com.jaspersoft.studio.model.text.MStaticText;
import com.jaspersoft.studio.model.text.MTextField;
import com.jaspersoft.studio.utils.ModelUtils;

/**
 * 
 * Action to convert a Text Field element into a static text element. All the common attributes are 
 * maintained in the conversion
 * 
 * @author Orlandin Marco
 *
 */
public class ConvertTextIntoStatic extends SelectionAction {

	/**
	 * The id of the action
	 */
	public static final String ID = "ConvertTextIntoStatic"; //$NON-NLS-1$
	
	public ConvertTextIntoStatic(IWorkbenchPart part) {
		super(part);
		setId(ID);
		setText(Messages.ConvertTextIntoStatic_actionName);
		setToolTipText(Messages.ConvertTextIntoStatic_actionToolTip);
		setImageDescriptor(JaspersoftStudioPlugin.getInstance().getImageDescriptor("icons/resources/convert_to_text.png")); //$NON-NLS-1$
	}

	@Override
	protected boolean calculateEnabled() {
		return true;
	}
	
	/**
	 * Action visible if it is selected at least one TextFieldFigureEdit Part
	 */
	@Override
	public boolean isEnabled() {
		List<?> editparts = new ArrayList<Object>(getSelectedObjects());
		for(Object part : editparts)
			if (part instanceof TextFieldFigureEditPart) return true;
		return false;
	}
	
	/**
	 * Copy the box section from the a linebox to another
	 * 
	 * @param fieldBox destination
	 * @param staticBox source
	 */
	private void cloneBox(JRLineBox fieldBox, JRLineBox staticBox){
		if (fieldBox == null || staticBox == null) return;
		fieldBox.setBottomPadding(staticBox.getBottomPadding());
		fieldBox.setLeftPadding(staticBox.getLeftPadding());
		fieldBox.setPadding(staticBox.getPadding());
		fieldBox.setRightPadding(staticBox.getRightPadding());
		fieldBox.setTopPadding(staticBox.getTopPadding());
		
		fieldBox.copyTopPen(staticBox.getTopPen());
		fieldBox.copyBottomPen(staticBox.getBottomPen());
		fieldBox.copyLeftPen(staticBox.getLeftPen());
		fieldBox.copyRightPen(staticBox.getRightPen());
		fieldBox.copyPen(staticBox.getPen());
	}
	
	private void cloneParagraph(JRParagraph fieldBox, JRParagraph staticBox){
			if (fieldBox == null || staticBox == null) return;
			fieldBox.setFirstLineIndent(staticBox.getFirstLineIndent());
			fieldBox.setLeftIndent(staticBox.getLeftIndent());
			fieldBox.setLineSpacing(staticBox.getLineSpacing());
			fieldBox.setLineSpacingSize(staticBox.getLineSpacingSize());
			fieldBox.setRightIndent(staticBox.getRightIndent());
			fieldBox.setSpacingAfter(staticBox.getSpacingAfter());
			fieldBox.setSpacingBefore(staticBox.getSpacingBefore());
			fieldBox.setTabStopWidth(staticBox.getTabStopWidth());
	}
	

	/**
	 * Copy all the common attributes from the text field to the new static text element 
	 * 
	 * @param labelObject the new static text element, that will substitute the text field
	 * @param textObject the substituted text field
	 */
	private void cloneTextField(JRDesignStaticText labelObject, JRDesignTextField textObject)
	{
		labelObject.setText(textObject.getExpression().getText());
		labelObject.setHeight(textObject.getHeight());
		labelObject.setWidth(textObject.getWidth());
		labelObject.setX(textObject.getX());
		labelObject.setY(textObject.getY());
		labelObject.setFontName(textObject.getFontName());
		labelObject.setFontSize(textObject.getFontSize());
		labelObject.setBackcolor(textObject.getBackcolor()); 
		labelObject.setForecolor(textObject.getForecolor());
		
		JRStyle originStyle = textObject.getStyle();
		labelObject.setStyle(originStyle != null ? (JRStyle)originStyle.clone() : null);
		
		labelObject.setStyleNameReference(textObject.getStyleNameReference());
		labelObject.setBold(textObject.isBold());
		labelObject.setItalic(textObject.isItalic());
		labelObject.setUnderline(textObject.isUnderline());
		labelObject.setStrikeThrough(textObject.isStrikeThrough());
		labelObject.setHorizontalAlignment(textObject.getHorizontalAlignmentValue());
		labelObject.setVerticalAlignment(textObject.getVerticalAlignmentValue());
		labelObject.setMode(textObject.getModeValue());
		labelObject.setRotation(textObject.getRotationValue());
		labelObject.setStretchType(textObject.getStretchTypeValue());
		labelObject.setKey(textObject.getKey());
		labelObject.setMarkup(textObject.getMarkup());
		labelObject.setPdfEmbedded(textObject.isPdfEmbedded());
		labelObject.setPdfEncoding(textObject.getPdfEncoding());
		labelObject.setPdfFontName(textObject.getPdfFontName());
		labelObject.setPositionType(textObject.getPositionTypeValue());
		labelObject.setPrintInFirstWholeBand(textObject.isPrintInFirstWholeBand());
		labelObject.setPrintRepeatedValues(textObject.isPrintRepeatedValues());
		labelObject.setPrintWhenDetailOverflows(textObject.isPrintWhenDetailOverflows());
		
		cloneBox(labelObject.getLineBox(), textObject.getLineBox());

		cloneParagraph(labelObject.getParagraph(), textObject.getParagraph());
		
		JRExpression originExpression = textObject.getPrintWhenExpression();
		labelObject.setPrintWhenExpression(originExpression != null ? (JRExpression)originExpression.clone() : null);
		
		JRGroup originGroup = textObject.getPrintWhenGroupChanges();
		labelObject.setPrintWhenGroupChanges(originGroup != null ? (JRGroup)originGroup.clone() : null);
		
		labelObject.setRemoveLineWhenBlank(textObject.isRemoveLineWhenBlank());
	}
	
	/**
	 * Create the commands to create a new static text similar to the text field selected
	 * and to delete the text field
	 * 
	 * @return a compound command with two commands in it, one to remove the selected text fields, and one to 
	 * create in their place similar static texts
	 */
	protected Command createAlignmentCommand() {
		JSSCompoundCommand command = new JSSCompoundCommand(null);
		List<?> editparts = new ArrayList<Object>(getSelectedObjects());
		if (editparts.isEmpty() || !(editparts.get(0) instanceof GraphicalEditPart))
			return command;
		for(Object part : editparts){
			if (part instanceof TextFieldFigureEditPart){
				TextFieldFigureEditPart editPart = (TextFieldFigureEditPart)part;
				MTextField textField = (MTextField)editPart.getModel();
				
				command.setReferenceNodeIfNull(textField);
				
				DeleteElementCommand deleteCommand = new DeleteElementCommand(null, textField);
				MStaticText modelText = new MStaticText();
				
				JRDesignStaticText labelObject = (JRDesignStaticText)modelText.createJRElement(textField.getJasperDesign());
				JRDesignTextField textObject = (JRDesignTextField)textField.getValue();

				cloneTextField(labelObject, textObject);
				
				
				modelText.setValue(labelObject);
				Rectangle position = new Rectangle(textObject.getX(),textObject.getY(),textObject.getWidth(),textObject.getHeight());

				int oldIndex = ModelUtils.getChildrenPosition(textField);
				CreateElementCommand createCommand = new CreateElementCommand(textField.getParent(), modelText, position, oldIndex);
				
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
