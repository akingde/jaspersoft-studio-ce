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
package com.jaspersoft.studio.style.view.text;

import java.awt.Color;

import net.sf.jasperreports.engine.JRFont;
import net.sf.jasperreports.engine.JRLineBox;
import net.sf.jasperreports.engine.base.JRBaseFont;
import net.sf.jasperreports.engine.base.JRBoxPen;
import net.sf.jasperreports.engine.design.JRDesignTextElement;
import net.sf.jasperreports.engine.type.HorizontalAlignEnum;
import net.sf.jasperreports.engine.type.ModeEnum;
import net.sf.jasperreports.engine.type.RotationEnum;
import net.sf.jasperreports.engine.type.VerticalAlignEnum;

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.model.text.MTextElement;

/**
 * The command to add a TextStyle to a textual element, support the undo
 * 
 * @author Orlandin Marco
 *
 */
public class UpdateStyleCommand extends Command{
	
	/**
	 * The textual element
	 */
	private JRDesignTextElement jrElement;
	
	/**
	 * The style of the text element before the change
	 */
	private TextStyle oldStyle;
	
	/**
	 * The new style
	 */
	private TextStyle newStyleTemplate;
	
	/**
	 * Create the command to apply the textual style to an element
	 * 
	 * @param element a textual element
	 * @param newStyle The new styles to apply to the textual element
	 */
	public UpdateStyleCommand(MTextElement element, TextStyle newStyle){
		this((JRDesignTextElement)element.getValue(), newStyle);
	}
	
	/**
	 * Create the command to apply the textual style to an element
	 * 
	 * @param element a jr textual element
	 * @param newStyle The new styles to apply to the textual element
	 */
	public UpdateStyleCommand(JRDesignTextElement element, TextStyle newStyle){
		this.jrElement = element;
		this.newStyleTemplate = newStyle;
		oldStyle = null;
	}

	@Override
	public void execute() {
		//Create the backup of the old values for the undo
		oldStyle = new TextStyle();
		HorizontalAlignEnum horizontalAlignment = jrElement.getOwnHorizontalAlignmentValue();
		VerticalAlignEnum verticalAlignment = jrElement.getOwnVerticalAlignmentValue();
		RotationEnum rotation = jrElement.getRotationValue();
		Color backColor = jrElement.getOwnBackcolor();
		Color foreColor = jrElement.getOwnForecolor();
		Boolean transparent = jrElement.getOwnModeValue() != null ? jrElement.getOwnModeValue().equals(ModeEnum.TRANSPARENT) : null;
		String fontName = jrElement.getOwnFontName();
		Integer fontSize = jrElement.getOwnFontSize();
		Boolean isItalic = jrElement.isOwnItalic();
		Boolean isBold = jrElement.isOwnBold();
		Boolean isUnderline = jrElement.isOwnUnderline();
		Boolean isStriketrought = jrElement.isOwnStrikeThrough();
		JRBaseFont font = new JRBaseFont();
		font.setFontName(fontName);
		font.setFontSize(fontSize);
		font.setBold(isBold);
		font.setItalic(isItalic);
		font.setUnderline(isUnderline);
		font.setStrikeThrough(isStriketrought);
		JRLineBox borders = jrElement.getLineBox().clone(null);
		oldStyle.setHorizontalAlignmen(horizontalAlignment);
		oldStyle.setVerticalAlignmen(verticalAlignment);
		oldStyle.setRotation(rotation);
		oldStyle.setTransparent(transparent);
		oldStyle.setBackGround(backColor);
		oldStyle.setForeGround(foreColor);
		oldStyle.setFont(font);
		oldStyle.setBorders(borders);
		//Applay the new style to the element
		applayStyle(newStyleTemplate);
	}
	
	/**
	 * Copy the attribute of a JRBoxPen from the source to the destination.
	 * 
	 * @param source of the copy
	 * @param dest destination of the copy
	 */
	private void setPenValues(JRBoxPen source, JRBoxPen dest){
		dest.setLineColor(source.getLineColor());
		dest.setLineStyle(source.getLineStyleValue());
		dest.setLineWidth(source.getLineWidth());
	}
	
	/**
	 * Apply the passed style to the stored jrElement reference
	 * 
	 * @param style the style to apply
	 */
	private void applayStyle(TextStyle style){
		jrElement.setHorizontalAlignment(style.getHorizontalAlignmen());
		jrElement.setVerticalAlignment(style.getVerticalAlignmen());
		jrElement.setRotation(style.getRotation());
		jrElement.setBackcolor(style.getBackGroundColor());
		jrElement.setForecolor(style.getForeGroundColor());
		jrElement.setMode(style.isTransparent() ? ModeEnum.TRANSPARENT : ModeEnum.OPAQUE);
		JRFont font = style.getFont();
		jrElement.setFontName(font.getOwnFontName());
		jrElement.setFontSize(font.getOwnFontSize());
		jrElement.setBold(font.isOwnBold());
		jrElement.setItalic(font.isOwnItalic());
		jrElement.setUnderline(font.isOwnUnderline());
		jrElement.setStrikeThrough(font.isOwnStrikeThrough());
		JRLineBox sourceLineBox = style.getBorders();
		JRLineBox destLineBox = jrElement.getLineBox();
		destLineBox.setPadding(sourceLineBox.getPadding());
		destLineBox.setLeftPadding(sourceLineBox.getLeftPadding());
		destLineBox.setRightPadding(sourceLineBox.getRightPadding());
		destLineBox.setTopPadding(sourceLineBox.getTopPadding());
		destLineBox.setBottomPadding(sourceLineBox.getBottomPadding());
		setPenValues(sourceLineBox.getPen(), destLineBox.getPen());
		setPenValues(sourceLineBox.getLeftPen(), destLineBox.getLeftPen());
		setPenValues(sourceLineBox.getRightPen(), destLineBox.getRightPen());
		setPenValues(sourceLineBox.getBottomPen(), destLineBox.getBottomPen());
		setPenValues(sourceLineBox.getTopPen(), destLineBox.getTopPen());
	}
	
	@Override
	public void undo() {
		applayStyle(oldStyle);
		oldStyle = null;
	}
	
	/**
	 * Undo is available if the text element and the style previous the update are available 
	 */
	@Override
	public boolean canUndo() {
		return (jrElement != null && oldStyle != null);
	}

}
