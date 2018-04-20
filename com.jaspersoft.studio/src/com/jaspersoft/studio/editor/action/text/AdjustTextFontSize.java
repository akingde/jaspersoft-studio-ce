/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.action.text;

import java.awt.font.TextAttribute;
import java.text.AttributedCharacterIterator.Attribute;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.eclipse.gef.commands.Command;
import org.eclipse.ui.IWorkbenchPart;

import com.jaspersoft.studio.JSSCompoundCommand;
import com.jaspersoft.studio.editor.action.ACachedSelectionAction;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.text.MStaticText;
import com.jaspersoft.studio.model.text.MTextElement;
import com.jaspersoft.studio.model.text.MTextField;
import com.jaspersoft.studio.property.SetValueCommand;
import com.jaspersoft.studio.utils.ModelUtils;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.engine.JRCommonText;
import net.sf.jasperreports.engine.JRStaticText;
import net.sf.jasperreports.engine.JRTextElement;
import net.sf.jasperreports.engine.JRTextField;
import net.sf.jasperreports.engine.base.JRBaseFont;
import net.sf.jasperreports.engine.design.JRDesignStaticText;
import net.sf.jasperreports.engine.design.JRDesignTextField;
import net.sf.jasperreports.engine.fill.JRMeasuredText;
import net.sf.jasperreports.engine.fill.JRTextMeasurer;
import net.sf.jasperreports.engine.fonts.FontUtil;
import net.sf.jasperreports.engine.util.JRStyledText;
import net.sf.jasperreports.engine.util.JRStyledTextParser;
import net.sf.jasperreports.engine.util.JRStyledTextUtil;
import net.sf.jasperreports.engine.util.JRTextMeasurerUtil;

/**
 * 
 * Action to convert a Static Text element into a text field element. All the common attributes are 
 * maintained in the conversion
 * 
 * @author Orlandin Marco
 *
 */
public class AdjustTextFontSize extends ACachedSelectionAction {

	/**
	 * Wrapper for the CreateElementCommand. This command allow to generate
	 * the commands that will be executed but without generating also the new elements.
	 * In this way the new elements are created only when the command is executed
	 * 
	 * @author Orlandin Marco
	 *
	 */
	private class LazyCreateTextFieldCommand extends Command{
		
		/**
		 * The executed create command
		 */
		private SetValueCommand cmd = null;
		
		/**
		 * The element to copy
		 */
		private MTextElement textElement;
		
		public LazyCreateTextFieldCommand(MTextElement elementToCopy){
			this.textElement = elementToCopy;
		}
		
		@Override
		public void execute() {
			Float fontSize = null;
			if (textElement instanceof MStaticText) {
				JRDesignStaticText labelObject = (JRDesignStaticText)textElement.getValue();
				fontSize = getTextSize(labelObject, textElement.getJasperConfiguration());
			} else if (textElement instanceof MTextField) {
				JRDesignTextField labelObject = (JRDesignTextField)textElement.getValue();
				fontSize = getTextSize(labelObject, textElement.getJasperConfiguration());
			}
			
			if (fontSize != null) {
				cmd = new SetValueCommand();
				cmd.setTarget(textElement);
				cmd.setPropertyValue(fontSize);
				cmd.setPropertyId(JRBaseFont.PROPERTY_FONT_SIZE);
				cmd.execute();
			}
		}
		
		@Override
		public void undo() {
			cmd.undo();
			cmd = null;
		}
		
		@Override
		public boolean canExecute() {
			return textElement != null;
		}
		
		@Override
		public boolean canUndo() {
			return cmd != null;
		}
	}

	/**
	 * The id of the action
	 */
	public static final String ID = "AdjustTextFontSize";  //$NON-NLS-1$
	
	public AdjustTextFontSize(IWorkbenchPart part) {
		super(part);
		setId(ID);
		setText(Messages.AdjustTextFontSize_action_name);
		setToolTipText(Messages.AdjustTextFontSize_action_tooltip);
	}

	private float getTextSize(JRStaticText textElement, JasperReportsConfiguration jConfig) {
		String text = textElement.getText();
		return getTextSize(textElement, text, jConfig);
	}
	
	private float getTextSize(JRTextField textElement, JasperReportsConfiguration jConfig) {
		String text = textElement.getExpression().getText();
		return getTextSize(textElement, text, jConfig);
	}
  
	/**
	 * Return the size that a {@link JRTextElement} must have to contains and show the text
	 * inside it
	 * 
	 * @param textElement a not null {@link JRTextElement}
	 * @return the size the text will have inside the static text
	 */
	private float getTextSize(JRTextElement textElement, String text, JasperReportsConfiguration jConfig) {
		JRTextMeasurer measurer = JRTextMeasurerUtil.getInstance(jConfig).createTextMeasurer(textElement, null);
		Map<Attribute, Object> attributes = new HashMap<>(); 
		FontUtil.getInstance(jConfig).getAttributesWithoutAwtFont(attributes, textElement);
		JRStyledText styledText = JRStyledTextParser.getInstance().getStyledText(attributes, text, !JRCommonText.MARKUP_NONE.equals(textElement.getMarkup()), Locale.getDefault());
		styledText = JRStyledTextUtil.getInstance(jConfig).resolveFonts(styledText, Locale.getDefault());
		JRMeasuredText measuredText = measurer.measure(styledText, 0, textElement.getHeight(), false);
		float currentSize = textElement.getFontsize();
		float originalSize = textElement.getFontsize();
		if (measuredText.getTextWidth() > textElement.getWidth() || measuredText.getTextHeight() > textElement.getHeight()) {
			do {
				currentSize--;
				attributes.put(TextAttribute.SIZE, currentSize);
				styledText.setGlobalAttributes(attributes);
				measuredText = measurer.measure(styledText, 0, textElement.getHeight(), false);
			} while(measuredText.getTextWidth() > textElement.getWidth() || measuredText.getTextHeight() > textElement.getHeight());
		} else {
			//can need enlargement
			while(true) {
				currentSize++;
				attributes.put(TextAttribute.SIZE, currentSize);
				styledText.setGlobalAttributes(attributes);
				measuredText = measurer.measure(styledText, 0, textElement.getHeight(), false);
				if (!(measuredText.getTextWidth() < textElement.getWidth() && measuredText.getTextHeight() < textElement.getHeight())) {
					currentSize--;
					break;
				}
			}
		}
	
		return !ModelUtils.safeEquals(originalSize, currentSize) ? currentSize : null;
	}
	
	/**
	 * Create the commands to create a new text field similar to the static text selected
	 * and to delete the static text
	 * 
	 * @return a compound command with two commands in it, one to remove the selected static texts, and one to 
	 * create in their place similar text fields
	 */
	@Override
	protected Command createCommand() {
		List<Object> editparts = editor.getSelectionCache().getSelectionModelForType(MTextElement.class);
		if (editparts.isEmpty())
			return null;
		JSSCompoundCommand command = new JSSCompoundCommand(null);
		for(Object part : editparts){
			MTextElement textElement = (MTextElement)part;

			command.setReferenceNodeIfNull(textElement);
			
			LazyCreateTextFieldCommand createCommand = new LazyCreateTextFieldCommand(textElement);
			
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
