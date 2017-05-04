/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.widgets.framework.manager;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;

import com.jaspersoft.studio.widgets.framework.WItemProperty;

/**
 * Layout used inside a {@link WItemProperty}, to handle the placement of the 
 * label to open the expression editor, the widget and the dialog button. Optionally
 * it can have as first element a title label.
 * As layout data it use an {@link ItemPropertyLayoutData}, set directly on the layouted {@link WItemProperty}
 * The layout data can be used to set the size of of fill status of both the expression control or simple control
 * If the {@link WItemProperty} layouted by this is not visible the size of everything will be 0
 */
public class ItemPropertyLayout extends Layout {
	
	private Label titleLabel;
	
	private LazyExpressionLabel expressionLabel;
	
	private Control mainControl;
	
	private Button dialogButton;
	
	private WItemProperty wItemProperty;
	
	/**
	 * Margin under the layout
	 */
	public int bottomMargin = 3;
	
	/**
	 * Margin between a control and the following one
	 */
	public int horizontalSpacing = 5;
	
	public ItemPropertyLayout(WItemProperty wItemProperty, Label titleLabel, LazyExpressionLabel expressionLabel, Control mainControl, Button dialogButton) {
		this.wItemProperty = wItemProperty;
		this.mainControl = mainControl;
		this.expressionLabel = expressionLabel;
		this.dialogButton = dialogButton;
		this.titleLabel = titleLabel;
	}

	@Override
	protected Point computeSize(Composite composite, int wHint, int hHint, boolean flushCache) {
		if(!wItemProperty.isVisible()){
			return new Point(0, 0);
		} else {
			boolean isExpressionMode = wItemProperty.isExpressionMode();
			//Get informations from the layout data
			ItemPropertyLayoutData data = wItemProperty.getContentLayoutData();
			int leftMargin = data.leftMargin;
			Point labelSize = data.labelSize;
			Point buttonSize = getButtonSize(hHint, data, isExpressionMode);
			
			int width = leftMargin;
			
			//Start as height from the higher between button and label
			int height = Math.max(labelSize.y, buttonSize.y);
			
			//compute the size of the label if present
			if (titleLabel != null){
				width += titleLabel.computeSize(wHint, height).x + horizontalSpacing;
			}
			
			if (isExpressionMode){
				width += labelSize.x + horizontalSpacing;
			} 
			
			//Add the width of the button
			width += buttonSize.x + horizontalSpacing;
			
			boolean isControlFillingVertical = isExpressionMode ? data.expressionFillVertical : data.widgetFillVertical;
			int heightHint = 0;
			if (isControlFillingVertical){
				heightHint = hHint;
			} else {
				heightHint = isExpressionMode ? data.expressionHeightHint : data.widgetHeightHint;
			}
			Point controlSize = mainControl.computeSize (wHint, heightHint, flushCache);
			width += controlSize.x;

			
			//Update the height with the one from the control
			height = Math.max(controlSize.y, height);
			
			return new Point(width, height + bottomMargin);
		}
	}
	
	/**
	 * Return the size of the dialog button as defined in the {@link ItemPropertyLayoutData}.
	 * If the size is bigger than the available space then it is reduced to the available 
	 * space
	 * 
	 * @param availableheight the maximum available space
	 * @param data the current {@link ItemPropertyLayoutData}, must be not null
	 * @param isExpressionMode true if the {@link WItemProperty} is in expression mode, false otherwise
	 * 
	 * @return a not null size for the dialog button
	 */	
	protected Point getButtonSize(int availableheight, ItemPropertyLayoutData data, boolean isExpressionMode){
		boolean isVisible = isExpressionMode ? data.buttonVisibleExpressionMode : data.buttonVisibleSimpleMode;
		if (isVisible){
			if (data.buttonSize.y <= availableheight || availableheight == -1){
				return data.buttonSize;
			} else {
				return new Point(data.buttonSize.x, availableheight);
			}
		} else {
			return new Point(0, 0);
		}
	}
	
	/**
	 * Return the start Y coordinate of the dialog button
	 * 
	 * @param availableheight the available height
	 * @param the current {@link ItemPropertyLayoutData}, must be not null
	 * @param isExpressionMode true if the {@link WItemProperty} is in expression mode, false otherwise
	 * 
	 * @return the Y coordinate of the dialog button
	 */
	protected int getButtonStart(int availableheight, ItemPropertyLayoutData data, boolean isExpressionMode){
		boolean isVisible = isExpressionMode ? data.buttonVisibleExpressionMode : data.buttonVisibleSimpleMode;
		if (isVisible){
			if (data.buttonAlignment == SWT.END){
				return availableheight - getButtonSize(availableheight, data, isExpressionMode).y;
			} else if (data.buttonAlignment == SWT.CENTER){
				int buttonHeight = getButtonSize(availableheight, data, isExpressionMode).y;
				return Math.round((availableheight - buttonHeight)/2f);
			} 
			//else reuturn the height 0
		}
		return 0;
	}

	@Override
	protected void layout(Composite composite, boolean flushCache) {
		if (!wItemProperty.isVisible()){
			if (titleLabel != null){
				titleLabel.setBounds(0, 0, 0, 0);
			}
			if (expressionLabel.isInitialized()) {
				//since this hide the label avoid to execute if it s not initialized
				expressionLabel.setBounds(0, 0, 0, 0);
			}
			mainControl.setBounds(0, 0, 0, 0);
			dialogButton.setBounds(0, 0, 0, 0);
		} else {	
			Rectangle compositeSize = composite.getClientArea();
			
			//Subtract from the available height the bottom margin
			compositeSize.height -= bottomMargin;
			
			//Check if the widget is in expression mode
			boolean isExpressionMode = wItemProperty.isExpressionMode();
			
			//Get informations from the layout data
			ItemPropertyLayoutData data = wItemProperty.getContentLayoutData();
			Point labelSize = data.labelSize;
			Point buttonSize = getButtonSize(compositeSize.height, data, isExpressionMode);
			
			//Check if the main control has to fill the area vertically
			boolean isControlFillingVertical = false;
			isControlFillingVertical = isExpressionMode ? data.expressionFillVertical : data.widgetFillVertical;
			
			int heightHint = 0;
			if (isControlFillingVertical){
				heightHint = compositeSize.height;
			} else {
				heightHint = isExpressionMode ? data.expressionHeightHint : data.widgetHeightHint;
			}
			
			int availableWidth = compositeSize.width - data.leftMargin;
			int startEditorX = data.leftMargin;
			
			//set the size of the label if present. The label is always centered vertically 
			//and horizontally is on the left and can never take more then half the available space
			if(titleLabel != null){
				Point titleSize = titleLabel.computeSize(SWT.DEFAULT, heightHint, flushCache);
				titleSize.x = Math.min(titleSize.x, availableWidth / 2);
				int titleStartY = Math.abs((compositeSize.height / 2) - titleSize.y/2);	;
				titleLabel.setBounds(new Rectangle(startEditorX, titleStartY, titleSize.x, titleSize.y));
				startEditorX += titleSize.x + horizontalSpacing;
				availableWidth -= titleSize.x + horizontalSpacing;
			}
			
			if (isExpressionMode){
				expressionLabel.setVisible(true);
				expressionLabel.setBounds(new Rectangle(startEditorX, 0, labelSize.x, labelSize.y));
				
				//Created the label, update the available space and start of the editor
				availableWidth -= labelSize.x + horizontalSpacing;
				startEditorX += labelSize.x + horizontalSpacing;
			} else if (expressionLabel.isInitialized()) {
				//since this hide the label avoid to execute if it s not initialized
				expressionLabel.setVisible(false);
				expressionLabel.setBounds(new Rectangle(0, 0, 0, 0));	
			}
			
			//Place the button to the end
			int buttonStart = getButtonStart(compositeSize.height, data, isExpressionMode);
			dialogButton.setBounds(new Rectangle(compositeSize.width - buttonSize.x, buttonStart, buttonSize.x, buttonSize.y));
			dialogButton.setVisible(isExpressionMode ? data.buttonVisibleExpressionMode : data.buttonVisibleSimpleMode);
			availableWidth -= buttonSize.x + horizontalSpacing;
			
			//Now we have the available width for the control
			Point controlSize = mainControl.computeSize (availableWidth, heightHint, flushCache);
			//If the height of the control is lower then the available height and the control should fill it start from 0
			if (controlSize.y > compositeSize.height){
				controlSize.y = compositeSize.height;
			}
			
			//center the control on the button if it is smaller than that
			if (controlSize.y < buttonSize.y){
				int startY = Math.round((buttonSize.y - controlSize.y) / 2f);
				mainControl.setBounds(startEditorX, startY, availableWidth, controlSize.y);
			} else {
				mainControl.setBounds(startEditorX, 0, availableWidth, controlSize.y);
			}
		}
	}
}
