/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved. http://www.jaspersoft.com.
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.widgets.framework.manager;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;

import com.jaspersoft.studio.widgets.framework.WItemProperty;

/**
 * Layout used inside a {@link WItemProperty}, to handle the placement of the 
 * label to open the expression editor, the widget and the dialog button. Optionally
 * it can have as first element a title label
 * As layout data it can use a standard grid data, but from that only the 
 * vertical alignment with value SWT.Fill is used, if the value is not SWT.Fill
 * will be used also the height hint to know which should be the height of the widget.
 * The layout data is read only from the control widget.
 * If the {@link WItemProperty} layouted by this is not visible the size of everything will be 0
 */
public class ItemPropertyLayout extends Layout {
	
	private Label titleLabel;
	
	private Label expressionLabel;
	
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
	
	/**
	 * Margin before the start of the first control
	 */
	public int leftMargin = 5;
	
	/**
	 * Default size of the dialog button
	 */
	protected Point buttonSize = new Point(24, 24);
	
	/**
	 * Default size of the expression editor label button
	 */
	protected Point labelSize = new Point(24, 24);
	
	public ItemPropertyLayout(WItemProperty wItemProperty, Label titleLabel, Label expressionLabel, Control mainControl, Button dialogButton) {
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
			int width = leftMargin;
			
			//Start as height from the higher between button and label
			int height = Math.max(labelSize.y, buttonSize.y);
			
			//compute the size of the label if present
			if (titleLabel != null){
				width += titleLabel.computeSize(wHint, height).x + horizontalSpacing;
			}
			
			if (wItemProperty.isExpressionMode()){
				width += labelSize.x + horizontalSpacing;
			} 
			
			//Add the width of the button
			width += buttonSize.x + horizontalSpacing;
			
			GridData data = (GridData)mainControl.getLayoutData();
			if (data == null) data = new GridData();
			int heightHint = 0;
			if (data.verticalAlignment == SWT.FILL){
				heightHint = hHint;
			} else {
				heightHint = data.heightHint;
			}
			Point controlSize = mainControl.computeSize (wHint, heightHint, flushCache);
			width += controlSize.x;
			
			//Update the height with the one from the control
			height = Math.max(controlSize.y, height);
			
			return new Point(width, height + bottomMargin);
		}
	}

	@Override
	protected void layout(Composite composite, boolean flushCache) {
		if (!wItemProperty.isVisible()){
			if (titleLabel != null){
				titleLabel.setBounds(0, 0, 0, 0);
			}
			expressionLabel.setBounds(0, 0, 0, 0);
			mainControl.setBounds(0, 0, 0, 0);
			dialogButton.setBounds(0, 0, 0, 0);
		} else {	
			Rectangle compositeSize = composite.getClientArea();
			
			//Subtract from the available height the bottom margin
			compositeSize.height -= bottomMargin;
			
			//Check if the main control has to fill the area vertically
			boolean isControlFillingVertical = false;
			GridData data = (GridData)mainControl.getLayoutData();
			if (data == null) data = new GridData();
			if (data.verticalAlignment == SWT.FILL){
				isControlFillingVertical = true;
			}
			int heightHint = 0;
			if (isControlFillingVertical){
				heightHint = compositeSize.height;
			} else {
				heightHint = data.heightHint;
			}
			
			int availableWidth = compositeSize.width - leftMargin;
			int startEditorX = leftMargin;
			
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
			
			if (wItemProperty.isExpressionMode()){
				expressionLabel.setVisible(true);
				int labelStartY =  0;
				expressionLabel.setBounds(new Rectangle(startEditorX, labelStartY, labelSize.x, labelSize.y));
				
				//Created the label, update the available space and start of the editor
				availableWidth -= labelSize.x + horizontalSpacing;
				startEditorX += labelSize.x + horizontalSpacing;
			} else {
				expressionLabel.setVisible(false);
				expressionLabel.setBounds(new Rectangle(0, 0, 0, 0));	
			}
			
			//Place the button to the end
			int dialogButtonStartY = 0;
			dialogButton.setBounds(new Rectangle(compositeSize.width - buttonSize.x, dialogButtonStartY, buttonSize.x, buttonSize.y));
			availableWidth -= buttonSize.x + horizontalSpacing;
			
			//Now we have the available width for the control
			Point controlSize = mainControl.computeSize (availableWidth, heightHint, flushCache);
			int startEditorY = 0;
			//If the height of the control is lower then the available height and the control should fill it start from 0
			//otherwise it is aligned into the center
			if (controlSize.y < compositeSize.height){
				startEditorY = Math.abs((compositeSize.height / 2) - controlSize.y/2);	
			} else if (controlSize.y > compositeSize.height){
				controlSize.y = compositeSize.height;
			}
			mainControl.setBounds(startEditorX, startEditorY, availableWidth, controlSize.y);
		}
	}

	public void setButtonSize(Point size){
		this.buttonSize = size;
	}
	
	public void setLabelSize(Point size){
		this.labelSize = size;
	}
}
