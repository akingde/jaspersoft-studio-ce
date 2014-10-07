/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.model.style.command;

import net.sf.jasperreports.engine.JRStyle;
import net.sf.jasperreports.engine.design.JRDesignStyle;

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.model.style.MStyle;

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
		dest.setHorizontalAlignment(source.getOwnHorizontalAlignmentValue());
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
		dest.setVerticalAlignment(source.getOwnVerticalAlignmentValue());
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
