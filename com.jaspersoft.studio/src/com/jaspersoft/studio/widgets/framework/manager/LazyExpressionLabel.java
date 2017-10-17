/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.widgets.framework.manager;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;


/**
 * This class is a wrapper for the expression label control, it allow to create in a lazy
 * way the label only when it need to be shown. This improve the performance since most
 * of times the widgets are used in simple edit mode, and this avoid to create unused controls.
 * The label is created only when setting the bounds or changing the visibility
 * 
 * @author Orlandin Marco
 * 
 */
public class LazyExpressionLabel {
	
	/**
	 * The label control
	 */
	private Label label = null;
	
	/**
	 * The parent of the label
	 */
	private Composite parent;
	
	/**
	 * The list of mouse listeners of the label
	 */
	private List<MouseListener> mouseListeners = new ArrayList<MouseListener>();
	
	/**
	 * The tooltip text of the label
	 */
	private String toolTipText = null;
	
	/**
	 * The image of the label
	 */
	private Image image = null;
	
	/**
	 * Build the wrapper of the label but not the label itself
	 * 
	 * @param parent the future parent of the label
	 * 
	 */
	public LazyExpressionLabel(Composite parent){
		this.parent = parent;
	}
	
	/**
	 * Initialize the label control if needed, when it is initialized
	 * the mouse listener that were added to the wrapper are transferred
	 * to the label, and the same happen for the  tooltip text and 
	 * the image
	 */
	protected void initializeLabel(){
		if (label == null) {
			label = new Label(parent, SWT.NONE);
			for(MouseListener listener : mouseListeners){
				label.addMouseListener(listener);
			}
			label.setToolTipText(toolTipText);
			label.setImage(image);
		}
	}
	
	/**
	 * Set the visibility of the label, this trigger the initialization
	 * 
	 * @param true if visible, false otherwise
	 */
	public void setVisible(boolean visible){
		initializeLabel();
		label.setVisible(visible);
	}
	
	/**
	 * Set the bounds of the label, this trigger the initialization
	 */
	public void setBounds(int x, int y, int width, int height){
		initializeLabel();
		label.setBounds(x, y, width, height);
	}
	
	/**
	 * Set the bounds of the label, this trigger the initialization
	 */
	public void setBounds(Rectangle rect){
		initializeLabel();
		label.setBounds(rect);
	}
	
	/**
	 * Add a mouse listener on the label, this will not trigger
	 * the initialization so this is added when the label is created
	 * if it has to be initialized or immediately if it was already created
	 */
	public void addMouseListener(MouseListener listener){
		if (!mouseListeners.contains(listener)){
			mouseListeners.add(listener);
			if (label != null){
				label.addMouseListener(listener);
			}
		}
	}
	
	/**
	 * Set the tooltip text on the label, this will not trigger
	 * the initialization so this is set when the label is created
	 * if it has to be initialized or immediately if it was already created
	 */
	public void setToolTipText(String text){
		toolTipText = text;
		if (label != null){
			label.setToolTipText(text);
		}
	}
	
	/**
	 * Set the image on the label, this will not trigger
	 * the initialization so this is set when the label is created
	 * if it has to be initialized or immediately if it was already created
	 */
	public void setImage(Image image){
		this.image = image;
		if (label != null){
			label.setImage(image);
		}
	}

	/**
	 * Check if the label is already initialized
	 * 
	 * @return true if the control was created, false otherwise
	 */
	public boolean isInitialized(){
		return label != null;
	}
}
