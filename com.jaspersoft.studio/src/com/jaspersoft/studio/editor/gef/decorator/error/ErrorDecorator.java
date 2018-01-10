/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.gef.decorator.error;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.util.List;

import net.sf.jasperreports.engine.design.JRDesignElement;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.wb.swt.ResourceManager;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.editor.gef.decorator.IDecorator;
import com.jaspersoft.studio.editor.gef.figures.ComponentFigure;
import com.jaspersoft.studio.properties.view.validation.ValidationError;

/**
 * Design a border and a warning icon on an element that is out of bounds to warn the user about a validation error on
 * the element and also can optionally provide a tooltip message on the error
 * 
 * @author Orlandin Marco
 * 
 */
public class ErrorDecorator implements IDecorator {

	/**
	 * The color of the warning border
	 */
	private static Color JSS_WARNING_BORDER_COLOR = new Color(255, 0, 0, 128);
	private static Color JSS_ERROR_BORDER_COLOR = new Color(255, 0, 0);
	/**
	 * The size of the warning border
	 */
	private static float JSS_WARNING_BORDER_SIZE = 1.0f;

	/**
	 * The tooltip to provide
	 */
	private String errorTooltip;

	/**
	 * Print the warning icon when an element is out of bound and set the tooltip text.
	 */
	@Override
	public void paint(Graphics graphics, ComponentFigure fig) {
		if (fig.getJrElement() instanceof JRDesignElement) {
			Rectangle r = fig.getBounds();
			Graphics2D g = ComponentFigure.getG2D(graphics);
			if (g != null) {
				Stroke oldStroke = g.getStroke();
				Color oldColor = g.getColor();
				if (hasErrors)
					g.setColor(JSS_ERROR_BORDER_COLOR);
				else
					g.setColor(JSS_WARNING_BORDER_COLOR);
				g.setStroke(new BasicStroke(JSS_WARNING_BORDER_SIZE));

				PrecisionRectangle tempRect = new PrecisionRectangle();
				tempRect.setBounds(fig.getBounds());
				if (JSS_WARNING_BORDER_SIZE % 2 == 1) {
					tempRect.width--;
					tempRect.height--;
				}
				tempRect.width = tempRect.width - (int) Math.ceil(JSS_WARNING_BORDER_SIZE);
				tempRect.height = tempRect.height - (int) Math.ceil(JSS_WARNING_BORDER_SIZE);
				tempRect.shrink(JSS_WARNING_BORDER_SIZE, JSS_WARNING_BORDER_SIZE);
				g.setStroke(new BasicStroke(JSS_WARNING_BORDER_SIZE));
				g.drawRect(tempRect.x, tempRect.y, tempRect.width, tempRect.height);
				if (hasErrors)
					graphics.drawImage(PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_DEC_FIELD_ERROR),
							r.x + r.width - 10, r.y +1);
				else
					RoundedPolygon.paintComplexWarning(r.x + r.width - 5, r.y - 2, 6, 12, JSS_WARNING_BORDER_SIZE, g);
				// Check if there is a tooltip message
				if (errorTooltip != null) {
					if (hasErrors)
						fig.setToolTip(new org.eclipse.draw2d.Label(errorTooltip,
								PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_DEC_FIELD_ERROR)));
					else
						fig.setToolTip(new org.eclipse.draw2d.Label(errorTooltip,
								ResourceManager.getPluginImage(JaspersoftStudioPlugin.PLUGIN_ID, "icons/resources/warning2.png"))); // $NON-NLS-2$
				}
				g.setStroke(oldStroke);
				g.setColor(oldColor);
			}
		}
	}

	/**
	 * Set the color of the border of an element when it is out of bounds
	 * 
	 * @param newColor
	 *          the new color
	 */
	public void setBorderColor(Color newColor) {
		JSS_WARNING_BORDER_COLOR = newColor;
	}

	/**
	 * Set the size of the border of an element when it is out of bounds
	 * 
	 * @param newSize
	 *          the new size
	 */
	public void setBorderSize(float newSize) {
		JSS_WARNING_BORDER_SIZE = newSize;
	}

	@Override
	public boolean equals(Object obj) {
		return this.getClass().equals(obj.getClass());
	};

	private List<ValidationError> errorMessages;
	private boolean hasErrors = false;

	public void setErrorMessages(List<ValidationError> errorMessages) {
		this.errorMessages = errorMessages;
		String msg = "";
		String del = "";
		for (ValidationError error : errorMessages) {
			msg += del + error.getMessage();
			del = "\n";
			if (!error.isWarning())
				hasErrors = true;
		}
		setErrorTooltip(msg);
	}

	public List<ValidationError> getErrorMessages() {
		return errorMessages;
	}

	/**
	 * Set the tooltip message for the error. By default there is no tooltip message
	 * 
	 * @param message
	 *          the message to show, null means no tooltip message.
	 */
	public void setErrorTooltip(String message) {
		errorTooltip = message;
	}

}
