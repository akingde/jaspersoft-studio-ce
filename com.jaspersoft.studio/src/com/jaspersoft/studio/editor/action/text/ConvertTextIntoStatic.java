/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.action.text;

import java.util.List;

import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.JRGroup;
import net.sf.jasperreports.engine.JRLineBox;
import net.sf.jasperreports.engine.JRParagraph;
import net.sf.jasperreports.engine.JRPropertyExpression;
import net.sf.jasperreports.engine.JRStyle;
import net.sf.jasperreports.engine.design.JRDesignStaticText;
import net.sf.jasperreports.engine.design.JRDesignTextField;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;
import org.eclipse.ui.IWorkbenchPart;

import com.jaspersoft.studio.JSSCompoundCommand;
import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.editor.action.ACachedSelectionAction;
import com.jaspersoft.studio.editor.gef.parts.text.TextFieldFigureEditPart;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.ANode;
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
public class ConvertTextIntoStatic extends ACachedSelectionAction {

	/**
	 * Wrapper for the CreateElementCommand. This command allow to generate
	 * the commands that will be executed but without generating also the new elements.
	 * In this way the new elements are created only when the command is executed
	 * 
	 * @author Orlandin Marco
	 *
	 */
	private class LazyCreateStaticTextCommand extends Command{
		
		/**
		 * The executed create command
		 */
		private CreateElementCommand cmd = null;
		
		/**
		 * The parent of the converted node
		 */
		private ANode parent;
		
		/**
		 * The element to copy
		 */
		private MTextField elementToCopy;
		
		private int oldIndex;
		
		public LazyCreateStaticTextCommand(MTextField elementToCopy){
			this.elementToCopy = elementToCopy;
			//Need to store some values because if the copied node is deleted
			//its parent is no longer reachable
			this.parent = elementToCopy.getParent();
			this.oldIndex = ModelUtils.getChildrenPosition(elementToCopy);
		}
		
		@Override
		public void execute() {
			MStaticText modelText = new MStaticText();
			
			JRDesignStaticText labelObject = (JRDesignStaticText)modelText.createJRElement(elementToCopy.getJasperDesign());
			JRDesignTextField textObject = (JRDesignTextField)elementToCopy.getValue();

			cloneTextField(labelObject, textObject);
			
			
			modelText.setValue(labelObject);
			Rectangle position = new Rectangle(textObject.getX(),textObject.getY(),textObject.getWidth(),textObject.getHeight());

			cmd = new CreateElementCommand(parent, modelText, position, oldIndex);
			cmd.setJasperDesign(parent.getJasperDesign());
			cmd.execute();
		}
		
		@Override
		public void undo() {
			cmd.undo();
			cmd = null;
		}
		
		@Override
		public boolean canExecute() {
			return elementToCopy != null && parent != null;
		}
		
		@Override
		public boolean canUndo() {
			return cmd != null;
		}
	}
	
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

	/**
	 * Copy the box section from the a linebox to another
	 * 
	 * @param fieldBox destination
	 * @param staticBox source
	 */
	private void cloneBox(JRLineBox fieldBox, JRLineBox staticBox){
		if (fieldBox == null || staticBox == null) return;
		fieldBox.setBottomPadding(staticBox.getOwnBottomPadding());
		fieldBox.setLeftPadding(staticBox.getOwnLeftPadding());
		fieldBox.setPadding(staticBox.getOwnPadding());
		fieldBox.setRightPadding(staticBox.getOwnRightPadding());
		fieldBox.setTopPadding(staticBox.getOwnTopPadding());
		
		fieldBox.copyTopPen(staticBox.getTopPen());
		fieldBox.copyBottomPen(staticBox.getBottomPen());
		fieldBox.copyLeftPen(staticBox.getLeftPen());
		fieldBox.copyRightPen(staticBox.getRightPen());
		fieldBox.copyPen(staticBox.getPen());
	}
	
	private void cloneParagraph(JRParagraph fieldBox, JRParagraph staticBox){
			if (fieldBox == null || staticBox == null) return;
			fieldBox.setFirstLineIndent(staticBox.getOwnFirstLineIndent());
			fieldBox.setLeftIndent(staticBox.getOwnLeftIndent());
			fieldBox.setLineSpacing(staticBox.getOwnLineSpacing());
			fieldBox.setLineSpacingSize(staticBox.getOwnLineSpacingSize());
			fieldBox.setRightIndent(staticBox.getOwnRightIndent());
			fieldBox.setSpacingAfter(staticBox.getOwnSpacingAfter());
			fieldBox.setSpacingBefore(staticBox.getOwnSpacingBefore());
			fieldBox.setTabStopWidth(staticBox.getOwnTabStopWidth());
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
		labelObject.setFontName(textObject.getOwnFontName());
		labelObject.setFontSize(textObject.getOwnFontsize());
		labelObject.setBackcolor(textObject.getOwnBackcolor()); 
		labelObject.setForecolor(textObject.getOwnForecolor());
		
		//The element use the same style
		JRStyle originStyle = textObject.getStyle();
		labelObject.setStyle(originStyle);
		
		labelObject.setStyleNameReference(textObject.getStyleNameReference());
		labelObject.setBold(textObject.isOwnBold());
		labelObject.setItalic(textObject.isOwnItalic());
		labelObject.setUnderline(textObject.isOwnUnderline());
		labelObject.setStrikeThrough(textObject.isOwnStrikeThrough());
		labelObject.setHorizontalTextAlign(textObject.getOwnHorizontalTextAlign());
		labelObject.setVerticalTextAlign(textObject.getOwnVerticalTextAlign());
		labelObject.setMode(textObject.getOwnModeValue());
		labelObject.setRotation(textObject.getOwnRotationValue());
		labelObject.setStretchType(textObject.getStretchTypeValue());
		labelObject.setKey(textObject.getKey());
		labelObject.setMarkup(textObject.getOwnMarkup());
		labelObject.setPdfEmbedded(textObject.isOwnPdfEmbedded());
		labelObject.setPdfEncoding(textObject.getOwnPdfEncoding());
		labelObject.setPdfFontName(textObject.getOwnPdfFontName());
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
		
		//Transfer the properties map and expression from an element to the other
		for(String propertyName : textObject.getPropertiesMap().getPropertyNames()){
			String propertyValue = textObject.getPropertiesMap().getProperty(propertyName);
			labelObject.getPropertiesMap().setProperty(propertyName, propertyValue);
		}
		for(JRPropertyExpression propertyExpression : textObject.getPropertyExpressionsList()){
			labelObject.addPropertyExpression((JRPropertyExpression)propertyExpression.clone());
		}
	}
	
	/**
	 * Create the commands to create a new static text similar to the text field selected
	 * and to delete the text field
	 * 
	 * @return a compound command with two commands in it, one to remove the selected text fields, and one to 
	 * create in their place similar static texts
	 */
	@Override
	protected Command createCommand() {
		List<Object> editparts = editor.getSelectionCache().getSelectionPartForType(TextFieldFigureEditPart.class);
		if (editparts.isEmpty())
			return null;
		JSSCompoundCommand command = new JSSCompoundCommand(null);
		for(Object part : editparts){
			TextFieldFigureEditPart editPart = (TextFieldFigureEditPart)part;
			MTextField textField = (MTextField)editPart.getModel();
			
			command.setReferenceNodeIfNull(textField);
			
			DeleteElementCommand deleteCommand = new DeleteElementCommand(textField);
			LazyCreateStaticTextCommand createCommand = new LazyCreateStaticTextCommand(textField);
			
			command.add(deleteCommand);
			command.add(createCommand);
		}
		return command;
	}

	/**
	 * @see org.eclipse.jface.action.IAction#run()
	 */
	public void run() {
		execute(createCommand());
	}
}
