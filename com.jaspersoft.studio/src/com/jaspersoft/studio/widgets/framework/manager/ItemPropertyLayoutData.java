/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.widgets.framework.manager;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;

import com.jaspersoft.studio.widgets.framework.WItemProperty;

/**
 * Layout data that can be set inside a {@link WItemProperty} to be used with a
 * {@link ItemPropertyLayout}.Trough this it is possible to define the size of the expression label,
 * the size and vertical alignment of the dialog button and if it is visible or not
 * 
 */
public class ItemPropertyLayoutData {
	
	/**
	 * Vertical alignment of the dialog button, valid value are SWT.TOP, SWT.CENTER
	 * and SWT.END
	 */
	public int buttonAlignment = SWT.TOP;
	
	/**
	 * Flag used to make the dialog button visible or not in the simple mode
	 */
	public boolean buttonVisibleSimpleMode = true;
	
	/**
	 * Flag used to make the dialog button visible or not in the expression mode
	 */
	public boolean buttonVisibleExpressionMode = true;
	
	/**
	 * Used to know if the simple control should fill vertically the size of the {@link WItemProperty} in which is created
	 * Default value is false
	 */
	public boolean widgetFillVertical = false;

	/**
	 * Used to know if the expression control should fill vertically the size of the {@link WItemProperty} in which is created
	 * Default value is false
	 */
	public boolean expressionFillVertical = false;
	
	/**
	 * Used to know the height hint of the simple control, used only if widgetFillVertical is false. If this space is available
	 * the control will be set to this height
	 * Default value is SWT.DEFAULT
	 */
	public int widgetHeightHint = SWT.DEFAULT;

	/**
	 * Used to know the height hint of the expression control, used only if expressionFillVertical is false. If this space is available
	 * the control will be set to this height
	 * Default value is 22
	 */
	public int expressionHeightHint = 22;	
	
	/**
	 * Default size of the dialog button
	 */
	protected Point buttonSize = new Point(24, 24);
	
	/**
	 * Default size of the expression editor label button
	 */
	protected Point labelSize = new Point(24, 24);
	
	/**
	 * Margin before the start of the first control
	 */
	public int leftMargin = 5;
	
	public void setButtonSize(Point size){
		this.buttonSize = size;
	}
	
	public Point getButtonSize(){
		return this.buttonSize;
	}
	
	public void setLabelSize(Point size){
		this.labelSize = size;
	}	
}
