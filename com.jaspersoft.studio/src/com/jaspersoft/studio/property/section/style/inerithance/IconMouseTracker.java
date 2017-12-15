/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.section.style.inerithance;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseTrackAdapter;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Widget;

import com.jaspersoft.studio.utils.UIUtil;

/**
 * Class to manage the events of the mouse move event on the style list section, the mouse over and the mouse exit, 
 * used to show the button to delete an attribute when the mouse pointer is over it. Essentially this assume that
 * the first children of the container of the widget that triggered the event (or the widget itself if it is a container)
 * is the button label to delete the attribute, so it is shown when the mouse enter and hidden when the mouse exit
 * 
 * @author Orlandin Marco
 * 
 */
public class IconMouseTracker extends MouseTrackAdapter {

	/**
	 * Last element where the remove button was shown
	 */
	Composite lastElementSelected = null;

	/**
	 * Set the last element to null, necessary after a refresh of the widget because the old element were deallocated,
	 * so this pointer need to be reseted too.
	 */
	public void refresh() {
		lastElementSelected = null;
	}

	/**
	 * Return the container of the widget that triggered the event, if it's not
	 * already the composite container
	 * 
	 * @param eventWidget the widget that triggered the event
	 * @return the container of the widget of the container or the widget itself if
	 * it is already a container
	 */
	private Composite getParentLayout(Widget eventWidget){
		if (eventWidget instanceof Composite)
			return ((Composite) eventWidget);
		else
			return((Control) eventWidget).getParent();
	}
	
	/**
	 * Handle the event of the mouse enter on area that show the delete image, eventually hide the old one. Change event
	 * the mouse cursor to an hand.
	 */
	@Override
	public void mouseEnter(MouseEvent e) {
		Composite parentLayout = getParentLayout(e.widget);

		if (lastElementSelected == null)
			lastElementSelected = parentLayout;

		// We must be sure that the properties view is the one that currently is active
		if (parentLayout.getChildren().length > 1 && UIUtil.isPropertiesViewFocused()) {
			lastElementSelected.getChildren()[0].setVisible(false);
			lastElementSelected = parentLayout;
			parentLayout.getChildren()[0].setVisible(true);
			parentLayout.getChildren()[0].setCursor(new Cursor(null, SWT.CURSOR_HAND));
		}
	}

	/**
	 * Handle the event of the mouse exit the area that show the delete image, hide the image
	 */
	@Override
	public void mouseExit(MouseEvent e) {
		Composite parentLayout = getParentLayout(e.widget);
		if (parentLayout.getChildren().length > 1)
			parentLayout.getChildren()[0].setVisible(false);
	}
}
