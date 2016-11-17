/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.model.style.command;

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.model.style.MStyle;

import net.sf.jasperreports.engine.JRLineBox;
import net.sf.jasperreports.engine.JRPen;
import net.sf.jasperreports.engine.JRStyle;
import net.sf.jasperreports.engine.design.JRDesignStyle;

/**
 * Command that set the attributes of a style to the default values. Support the undo
 * 
 * @author Orlandin Marco
 *
 */
public class ResetStyleCommand extends Command {

	/**
	 * Style to reset
	 */
	private MStyle resettedStyle;
	
	/**
	 * JR element of a style to store the values of the reseted style,
	 * used to undo the command
	 */
	private JRDesignStyle backupValues;
	
	/**
	 * Create the command
	 * 
	 * @param resettedStyle the style to reset
	 */
	public ResetStyleCommand(MStyle resettedStyle) {
		super();
		this.resettedStyle = resettedStyle;
	}

	/**
	 * Copy all the attributes of the source style to the destination style
	 * 
	 * @param source source style
	 * @param dest destination style
	 */
	private void copyStyleAttributes(JRStyle source, JRDesignStyle dest){
		dest.setBackcolor(source.getOwnBackcolor());
		dest.setBlankWhenNull(source.isOwnBlankWhenNull());
		dest.setBold(source.isOwnBold());
		dest.setDefault(source.isDefault());
		dest.setFill(source.getOwnFillValue());
		dest.setFontName(source.getOwnFontName());
		dest.setFontSize(source.getOwnFontsize());
		dest.setForecolor(source.getOwnForecolor());
		dest.setHorizontalTextAlign(source.getOwnHorizontalTextAlign());
		dest.setHorizontalImageAlign(source.getOwnHorizontalImageAlign());
		dest.setItalic(source.isOwnItalic());
		dest.setMarkup(source.getOwnMarkup());
		dest.setMode(source.getOwnModeValue());
		dest.setParentStyle(source.getStyle());
		dest.setParentStyleNameReference(source.getStyleNameReference());
		dest.setPattern(source.getOwnPattern());
		dest.setPdfEmbedded(source.isOwnPdfEmbedded());
		dest.setPdfEncoding(source.getOwnPdfEncoding());
		dest.setPdfFontName(source.getOwnPdfFontName());
		dest.setRadius(source.getOwnRadius());
		dest.setRotation(source.getOwnRotationValue());
		dest.setScaleImage(source.getOwnScaleImageValue());
		dest.setStrikeThrough(source.isOwnStrikeThrough());
		dest.setUnderline(source.isOwnUnderline());
		dest.setVerticalTextAlign(source.getOwnVerticalTextAlign());
		dest.setVerticalImageAlign(source.getOwnVerticalImageAlign());
		
		//COPY THE BOX
		
		JRLineBox destLineBox = dest.getLineBox();
		JRLineBox sourceLineBox = source.getLineBox();
		
		if (destLineBox == null || sourceLineBox == null) return;
		
		destLineBox.setRightPadding(sourceLineBox.getOwnRightPadding());
		destLineBox.setLeftPadding(sourceLineBox.getOwnLeftPadding());
		destLineBox.setTopPadding(sourceLineBox.getOwnTopPadding());
		destLineBox.setBottomPadding(sourceLineBox.getOwnBottomPadding());
		destLineBox.setPadding(sourceLineBox.getOwnPadding());
		
		copyPenAttributrs(sourceLineBox.getPen(), destLineBox.getPen());
		copyPenAttributrs(sourceLineBox.getLeftPen(), destLineBox.getLeftPen());
		copyPenAttributrs(sourceLineBox.getRightPen(), destLineBox.getRightPen());
		copyPenAttributrs(sourceLineBox.getTopPen(), destLineBox.getTopPen());
		copyPenAttributrs(sourceLineBox.getBottomPen(), destLineBox.getBottomPen());
		
		//COPY THE PEN
		
		JRPen destLinePen = dest.getLinePen();
		JRPen sourceLinePen = source.getLinePen();
		copyPenAttributrs(sourceLinePen, destLinePen);
	}
	
	/**
	 * Copy the attribute of a source JRPen to a destination JRPen, only
	 * if both are not null
	 * 
	 * @param source the source pen
	 * @param dest the destination pen
	 */
	private void copyPenAttributrs(JRPen source, JRPen dest){
		if (source == null || dest == null) return;
		dest.setLineColor(source.getOwnLineColor());
		dest.setLineStyle(source.getOwnLineStyleValue());
		dest.setLineWidth(source.getOwnLineWidth());
	}
	
	@Override
	public void execute() {
		backupValues = new JRDesignStyle();
		copyStyleAttributes(resettedStyle.getValue(), backupValues);
		copyStyleAttributes(new JRDesignStyle(), (JRDesignStyle)resettedStyle.getValue());
	}

	@Override
	public boolean canUndo() {
		return (backupValues != null && canExecute());
	}
	
	@Override
	public boolean canExecute() {
		return (resettedStyle != null && resettedStyle.getValue() != null);
	}

	@Override
	public void undo() {
		copyStyleAttributes(backupValues, (JRDesignStyle)resettedStyle.getValue());
		backupValues = null;
	}
}
