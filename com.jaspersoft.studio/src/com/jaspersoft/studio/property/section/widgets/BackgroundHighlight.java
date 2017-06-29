/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.section.widgets;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.ResourceManager;

/**
 * highlight a control by changing its background color
 * 
 * @author Orlandin Marco
 *
 */
public class BackgroundHighlight implements IHighlightControl {

	/**
	 * Control to highlight
	 */
	private Control controlToHighlight;

	/**
	 * Color of the background prior the change
	 */
	private RGB oldBackground;
	private Color color;

	/**
	 * Create an instance of the class
	 * 
	 * @param control
	 *          control to highlight
	 */
	public BackgroundHighlight(Control control) {
		this(control, ColorConstants.orange);
	}

	public BackgroundHighlight(Control control, Color color) {
		this.controlToHighlight = control;
		this.color = color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * Highlight the control by changing its background color
	 */
	public void highLightControl() {
		if (controlToHighlight != null && !controlToHighlight.isDisposed()) {
			oldBackground = controlToHighlight.getBackground().getRGB();
			controlToHighlight.setBackground(color);
		}
	}

	public void deHighLightControl() {
		Color oldColor = null;
		if (oldBackground != null)
			oldColor = ResourceManager.getColor(oldBackground);
		deHighLightControl(oldColor);
	}

	/**
	 * Restore the control background to the default one
	 */
	public void deHighLightControl(Color oldColor) {
		if (oldBackground != null && !controlToHighlight.isDisposed()) {
			if (controlToHighlight.isFocusControl() && System.getProperty("os.name").toLowerCase().contains("mac")) {
				Point p = null;
				if (controlToHighlight instanceof Text)
					p = ((Text) controlToHighlight).getSelection();
				controlToHighlight.setEnabled(false);
				controlToHighlight.setBackground(oldColor);
				controlToHighlight.setEnabled(true);
				controlToHighlight.setFocus();
				if (controlToHighlight instanceof Text)
					((Text) controlToHighlight).setSelection(p);
			} else
				controlToHighlight.setBackground(oldColor);
			controlToHighlight.redraw();
		}
	}

}
