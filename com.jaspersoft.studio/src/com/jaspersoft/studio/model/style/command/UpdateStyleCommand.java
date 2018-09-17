/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.model.style.command;

import org.eclipse.gef.commands.Command;

import net.sf.jasperreports.engine.JRConditionalStyle;
import net.sf.jasperreports.engine.JRLineBox;
import net.sf.jasperreports.engine.JRParagraph;
import net.sf.jasperreports.engine.JRPen;
import net.sf.jasperreports.engine.base.JRBaseStyle;
import net.sf.jasperreports.engine.base.JRBoxPen;
import net.sf.jasperreports.engine.design.JRDesignStyle;

/**
 * Command used to copy all the attributes (Except the name) from 
 * a style to another
 * 
 * @author Orlandin Marco
 *
 */
public class UpdateStyleCommand extends Command {
	
	/**
	 * The source style from where the properties are read
	 */
	private JRDesignStyle sourceStyle;
	
	/**
	 * The target style where the properties are copied
	 */
	private JRDesignStyle targetStyle;
	
	/**
	 * A clone of the target style created during the execute, used for the undo
	 */
	private JRDesignStyle targetStyleCopy = null;
	
	/**
	 * Create the command
	 * 
	 * @param sourceStyle The source style from where the properties are read, must be not null
	 * @param targetStyle The target style where the properties are copied, must be not null
	 */
	public UpdateStyleCommand(JRDesignStyle sourceStyle, JRDesignStyle targetStyle){
		this.sourceStyle = sourceStyle;
		this.targetStyle = targetStyle;
	}
	
	@Override
	public boolean canExecute() {
		return sourceStyle != null && targetStyle != null;
	}

	protected void copyParentStyleAttributes(JRDesignStyle targetStyle, JRDesignStyle sourceStyle) {
		targetStyle.setDefault(sourceStyle.isDefault());
		targetStyle.setParentStyle(sourceStyle.getStyle());
		targetStyle.setParentStyleNameReference(sourceStyle.getStyleNameReference());
		int index = 0;
		for(JRConditionalStyle condStyle : sourceStyle.getConditionalStyleList()) {
			if (targetStyle.getConditionalStyleList().size() > index) {
				copyAttributes((JRBaseStyle)targetStyle.getConditionalStyleList().get(index), (JRBaseStyle)condStyle);
			}
			index++;
		}
	}
	
	/**
	 * Copy all the attributes from the source style to the target style, except the 
	 * name. The style attributes read from the source are the own values
	 * 
	 * @param sourceStyle The source style from where the properties are read, must be not null
	 * @param targetStyle The target style where the properties are copied, must be not null
	 */
	protected void copyAttributes(JRBaseStyle targetStyle, JRBaseStyle sourceStyle){
		targetStyle.setBackcolor(sourceStyle.getOwnBackcolor());
		targetStyle.setBlankWhenNull(sourceStyle.isOwnBlankWhenNull());
		targetStyle.setBold(sourceStyle.isOwnBold());
		targetStyle.setItalic(sourceStyle.isOwnItalic());

		targetStyle.setFill(sourceStyle.getOwnFillValue());
		targetStyle.setFontName(sourceStyle.getOwnFontName());
		targetStyle.setFontSize(sourceStyle.getOwnFontsize());
		targetStyle.setForecolor(sourceStyle.getOwnForecolor());
		targetStyle.setHorizontalImageAlign(sourceStyle.getOwnHorizontalImageAlign());
		targetStyle.setHorizontalTextAlign(sourceStyle.getOwnHorizontalTextAlign());
		targetStyle.setMarkup(sourceStyle.getOwnMarkup());
		targetStyle.setMode(sourceStyle.getOwnModeValue());

		targetStyle.setPattern(sourceStyle.getOwnPattern());
		targetStyle.setPdfEmbedded(sourceStyle.isOwnPdfEmbedded());
		targetStyle.setPdfEncoding(sourceStyle.getOwnPdfEncoding());
		targetStyle.setPdfFontName(sourceStyle.getOwnPdfFontName());
		targetStyle.setRadius(sourceStyle.getOwnRadius());
		targetStyle.setRotation(sourceStyle.getOwnRotationValue());
		targetStyle.setScaleImage(sourceStyle.getOwnScaleImageValue());
		targetStyle.setStrikeThrough(sourceStyle.isOwnStrikeThrough());
		targetStyle.setUnderline(sourceStyle.isOwnUnderline());
		targetStyle.setVerticalImageAlign(sourceStyle.getOwnVerticalImageAlign());
		targetStyle.setVerticalTextAlign(sourceStyle.getOwnVerticalTextAlign());
		
		//copy the paragraph
		JRParagraph targetParagraph = targetStyle.getParagraph();
		JRParagraph sourceParagraph = sourceStyle.getParagraph();
		targetParagraph.setFirstLineIndent(sourceParagraph.getOwnFirstLineIndent());
		targetParagraph.setLeftIndent(sourceParagraph.getOwnLeftIndent());
		targetParagraph.setLineSpacing(sourceParagraph.getOwnLineSpacing());
		targetParagraph.setLineSpacingSize(sourceParagraph.getOwnLineSpacingSize());
		targetParagraph.setRightIndent(sourceParagraph.getOwnRightIndent());
		targetParagraph.setSpacingAfter(sourceParagraph.getOwnSpacingAfter());
		targetParagraph.setSpacingBefore(sourceParagraph.getOwnSpacingBefore());
		targetParagraph.setTabStopWidth(sourceParagraph.getOwnTabStopWidth());
		
		//copy the line pen
		JRPen targetPen = targetStyle.getLinePen();
		JRPen sourcePen = sourceStyle.getLinePen();
		targetPen.setLineColor(sourcePen.getOwnLineColor());
		targetPen.setLineStyle(sourcePen.getOwnLineStyleValue());
		targetPen.setLineWidth(sourcePen.getOwnLineWidth());
		
		//Copy the line box
		JRLineBox targetBox = targetStyle.getLineBox();
		JRLineBox sourceBox = sourceStyle.getLineBox();
		
		targetBox.setBottomPadding(sourceBox.getOwnBottomPadding());
		targetBox.setLeftPadding(sourceBox.getOwnLeftPadding());
		targetBox.setPadding(sourceBox.getOwnPadding());
		targetBox.setRightPadding(sourceBox.getOwnRightPadding());
		targetBox.setTopPadding(sourceBox.getOwnTopPadding());
		
		 copyLineBox(targetBox.getPen(), sourceBox.getPen());
		 copyLineBox(targetBox.getLeftPen(), sourceBox.getLeftPen());
		 copyLineBox(targetBox.getRightPen(), sourceBox.getRightPen());
		 copyLineBox(targetBox.getBottomPen(), sourceBox.getBottomPen());
		 copyLineBox(targetBox.getTopPen(), sourceBox.getTopPen());
		 
		 if (targetStyle instanceof JRDesignStyle && sourceStyle instanceof JRDesignStyle) {
			 copyParentStyleAttributes((JRDesignStyle)targetStyle, (JRDesignStyle)sourceStyle);
		 }
	}
	
	/**
	 * Copy the attributes of a source line pen to a target line pen
	 * 
	 * @param targetPen the target, must be not null
	 * @param sourcePen the source, must be not null
	 */
	protected void copyLineBox(JRBoxPen targetPen, JRBoxPen sourcePen){
		targetPen.setLineColor(sourcePen.getOwnLineColor());
		targetPen.setLineStyle(sourcePen.getOwnLineStyleValue());
		targetPen.setLineWidth(sourcePen.getOwnLineWidth());
	}
	
	
	@Override
	public void execute() {
		targetStyleCopy = (JRDesignStyle)targetStyle.clone();
		copyAttributes(targetStyle, sourceStyle);
	}
	
	@Override
	public boolean canUndo() {
		return targetStyleCopy != null;
	}
	
	@Override
	public void undo() {
		copyAttributes(targetStyle, targetStyleCopy);
		targetStyleCopy = null;
	}
	
}
