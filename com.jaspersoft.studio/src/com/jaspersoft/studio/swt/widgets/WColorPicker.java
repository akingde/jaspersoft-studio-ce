/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.swt.widgets;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.jface.util.Util;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.wb.swt.SWTResourceManager;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.property.color.chooser.ColorDialog;
import com.jaspersoft.studio.swt.events.ColorSelectedEvent;
import com.jaspersoft.studio.swt.events.ColorSelectionListener;
import com.jaspersoft.studio.utils.AlfaRGB;
import com.jaspersoft.studio.utils.Colors;
import com.jaspersoft.studio.utils.ModelUtils;

/**
 * A custom widget that lets the user pick a color using the standard {@link ColorDialog} window. The widgets is
 * composed by three parts:
 * <ul>
 * <li>a squared-box with the preview of the chosen color;</li>
 * <li>a readonly textbox with the hexadecimal representation of the color;</li>
 * <li>an icon button that enables the user to select the color.</li>
 * </ul>
 * 
 * Users can retrieve the modified information through the different getters: {@link #getSelectedColorAsAWTColor()},
 * {@link #getSelectedColorAsRGB()} and {@link #getSelectedColorAsSWTColor()}.<br>
 * Otherwise they can listen to the color changes, adding a {@link ColorSelectionListener} to this widget instance.
 * 
 * @author mrabbi
 * 
 * 
 */
public class WColorPicker extends Composite {
	
	/**
	 * Modify listener set on the text area
	 */
	private ModifyListener textModifyListener = new ModifyListener() {
		
		@Override
		public void modifyText(ModifyEvent e) {
			AlfaRGB newColor = decodeColor(textColorValue.getText(),htmlColorNamesSupported);
			if (newColor != null){
				currentState = VALIDATION_RESULT.VALID;
				setColor(newColor);
				updateBackground(defaultBackgroundColor);
			} else {
				currentState = VALIDATION_RESULT.NOT_VALID;
				//invalid, update the validation status
				updateBackground(ColorConstants.red);
			}
		}
	};
	
	/**
	 * Enumeration used as a result of the validation, valid it means the inserted text is 
	 * a valid hex color, not valid means that the inserted value is not the hex of a color
	 */
	protected enum VALIDATION_RESULT {VALID, NOT_VALID};
	
	/**
	 * Flag to know if this widget support the transparency
	 */
	private boolean haveTransparency = false;

	private static final String BUTTON_ICON_LOCATION = "icons/resources/colorwheel-16.png"; //$NON-NLS-1$
	private static final String BUTTON_DISABLED_ICON_LOCATION = "icons/resources/colorwheel-16-disabled.png"; //$NON-NLS-1$
	private AlfaRGB selectedRGB;
	private CLabel imgColorPreview;
	private Text textColorValue;
	private List<ColorSelectionListener> colorSelectionListeners;
	private ToolItem buttonColorChoser;
	
	/**
	 * The background color to use when there aren't validation errors
	 */
	private Color defaultBackgroundColor;
	
	/**
	 * Used to store the tooltip text when it is replaced with the error tooltip
	 */
	private String toolTipText = null;
	
	/**
	 * The status of the value displayed
	 */
	protected VALIDATION_RESULT currentState = VALIDATION_RESULT.VALID;
	
	/** Flag to specify if the support for the HTML color names is activated */
	private boolean htmlColorNamesSupported = false;

	public WColorPicker(AlfaRGB preselectedRGB, Composite parent) {
		this(preselectedRGB, parent, SWT.NONE);
	}

	public WColorPicker(AlfaRGB preselectedRGB, Composite parent, int style) {
		super(parent, style);
		this.selectedRGB = preselectedRGB;
		this.colorSelectionListeners = new ArrayList<ColorSelectionListener>();
		createControl(parent);
	}

	public void setHaveTransparency(boolean haveTransparency) {
		this.haveTransparency = haveTransparency;
	}

	/*
	 * Creates the widget content.
	 */
	private void createControl(Composite parent) {
		this.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
		GridLayout colorChooserLayout = new GridLayout(3, false);
		colorChooserLayout.marginHeight = 0;
		colorChooserLayout.marginWidth = 0;
		this.setLayout(colorChooserLayout);

		imgColorPreview = new CLabel(this, SWT.NONE);
		GridData gridData1 = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		imgColorPreview.setLayoutData(gridData1);
		imgColorPreview.setImage(Colors.getSWTColorPreview(Colors.getAWT4SWTRGBColor(selectedRGB), 16, 16));

		textColorValue = new Text(this, SWT.BORDER | SWT.BORDER_SOLID);
		GC tmpGC = new GC(textColorValue);
		int charHeight = tmpGC.getFontMetrics().getHeight();
		int averageCharWidth = tmpGC.getFontMetrics().getAverageCharWidth();
		tmpGC.dispose();
		GridData gridData2 = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gridData2.widthHint = textColorValue.computeSize(averageCharWidth * 10, SWT.DEFAULT).x;
		gridData2.heightHint = charHeight;
		textColorValue.setLayoutData(gridData2);
		textColorValue.setText(Colors.getHexEncodedRGBColor(selectedRGB));
		defaultBackgroundColor = textColorValue.getBackground();
		textColorValue.addModifyListener(textModifyListener);

		ToolBar toolBar = new ToolBar(this, SWT.NONE);
		toolBar.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		buttonColorChoser = new ToolItem(toolBar, SWT.FLAT);
		buttonColorChoser.setImage(JaspersoftStudioPlugin.getInstance().getImage(BUTTON_ICON_LOCATION));
		buttonColorChoser.setDisabledImage(JaspersoftStudioPlugin.getInstance().getImage(BUTTON_DISABLED_ICON_LOCATION));
		buttonColorChoser.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent e) {
				ColorDialog cd = new ColorDialog(getShell());
				cd.setText(Messages.ColorsSection_element_forecolor);
				cd.setRGB(selectedRGB);
				AlfaRGB newColor = null;
				if (haveTransparency) {
					newColor = cd.openAlfaRGB();
				} else {
					RGB rgb = cd.openRGB();
					if (rgb != null)
						newColor = AlfaRGB.getFullyOpaque(rgb);
				}
				if (newColor != null){
					setColor(newColor);
					updateBackground(defaultBackgroundColor);
				}
			}
		});
	}
	
	/**
	 * Enables/disables the ability for the color picker to detect 
	 * HTML color names when entered in the text box.
	 * 
	 * @param enabled the enablement flag
	 */
	public void setHtmlColorNamesSupport(boolean enabled) {
		this.htmlColorNamesSupported = enabled;
	}
	
	/**
	 * Verifies if the html color names support is enabled.
	 * 
	 * @return <code>true</code> if the support is enabled, <code>false</code> otherwise
	 */
	public boolean isHtmlColorNamesSupportEnabled() {
		return this.htmlColorNamesSupported;
	}
	
	/**
	 * Convert the text into a alfa RGB color, but only if there are exactly seven chars, a # symbol followed by three pair of hex values
	 * or simply the three pair of hex value.<br/>
	 * If specified it allows also to detect possible HTML color names (i.e green, darkblue etc.) that are properly converted into the 
	 * related hex value.
	 * 
	 * @param text a text representing a color as HexDecimal value
	 * @param htmlColorNamesSupported the flag to enable the HTML color names support
	 * @return an Alfa RGB color. If the color is provided as hex or as RGB the alpha is 255
	 */
	public static AlfaRGB decodeColor(String text, boolean htmlColorNamesSupported){
		if (text != null) {
			try {
				if(htmlColorNamesSupported && StringUtils.isAlpha(text)) {
					String htmlColorHex = Colors.getHtmlColorHex(text);
					if(!StringUtils.isEmpty(htmlColorHex)){
						return AlfaRGB.getFullyOpaque(new RGB(Integer.valueOf(htmlColorHex.substring(1, 3), 16), Integer.valueOf(htmlColorHex.substring(3, 5), 16), Integer.valueOf(htmlColorHex.substring(5, 7), 16)));
					}
				}
				if ((text.startsWith("#") && text.length() == 7)) { //$NON-NLS-1$
					AlfaRGB newColor = AlfaRGB.getFullyOpaque(new RGB(Integer.valueOf(text.substring(1, 3), 16), Integer.valueOf(text.substring(3, 5), 16), Integer.valueOf(text.substring(5, 7), 16)));
					return newColor;
				} else if ( text.length() == 6){
					AlfaRGB newColor = AlfaRGB.getFullyOpaque(new RGB(Integer.valueOf(text.substring(0, 2), 16), Integer.valueOf(text.substring(2, 4), 16), Integer.valueOf(text.substring(4, 6), 16)));
					return newColor;
				}
			} catch (Exception ex) {
			}
		}
		return null;
	}
	
	/**
	 * On macos the update of the color need some additional operation because of an SWT bug
	 * (https://bugs.eclipse.org/bugs/show_bug.cgi?id=346361). If the widget is focused it need
	 * to lose the focus to be updated correctly. For this reason the widget is forced to loose
	 * the focus and the it will regain it
	 * 
	 * @param color the color to set
	 */
	protected void updateBackground(Color color){
		if (Util.isMac() && textColorValue.isFocusControl() && !ModelUtils.safeEquals(color, textColorValue.getBackground())){
			Point caretPosition = textColorValue.getSelection();
			boolean oldEnabled = textColorValue.getEnabled();
			textColorValue.setEnabled(false);//Force the focus lost
			textColorValue.setBackground(color);
			textColorValue.setEnabled(oldEnabled);
			textColorValue.setFocus();
			textColorValue.setSelection(caretPosition.x);
		} else {
			textColorValue.setBackground(color);
		}
		updateTooltipText();
	}

	/*
	 * Notify listeners for the color change.
	 */
	private void notifyColorSelection() {
		for (ColorSelectionListener l : colorSelectionListeners) {
			ColorSelectedEvent evt = new ColorSelectedEvent(this);
			evt.selectedColor = selectedRGB;
			l.changed(evt);
		}
	}
	
	/**
	 * Update the tooltip text on all the subwidgets that compose the color widget
	 * 
	 * @param text the text to set
	 */
	protected void setWidgetsToolTipText(String text){
		super.setToolTipText(text);
		imgColorPreview.setToolTipText(text);
		textColorValue.setToolTipText(text);
		buttonColorChoser.setToolTipText(text);
	}

	@Override
	public void setToolTipText(String text) {
		toolTipText = text;
		setWidgetsToolTipText(text);
	}
	
	/**
	 * Update the tooltip text accordingly with the validation status
	 */
	protected void updateTooltipText(){
		if (VALIDATION_RESULT.NOT_VALID == currentState){
			String errorTooltip = "The current value can not be recognized";
			if (toolTipText != null){
				errorTooltip += " \n" + toolTipText;
			}
			setWidgetsToolTipText(errorTooltip);
		} else {
			setToolTipText(toolTipText);
		}
	}

	/**
	 * Sets the new color (as {@link RGB} value). It also notifies all listeners of the color change occurred.
	 * 
	 * @param newColor
	 *          the new RGB color to set
	 */
	public void setColor(AlfaRGB newColor) {
		// Updates color information
		selectedRGB = newColor;
		Point caretPosition = textColorValue.getSelection();
		textColorValue.removeModifyListener(textModifyListener);
		textColorValue.setText(Colors.getHexEncodedRGBColor(selectedRGB));
		textColorValue.addModifyListener(textModifyListener);
		textColorValue.setSelection(caretPosition);
		updatePreviewImage(isEnabled());
		notifyColorSelection();
	}

	/**
	 * Add a new listener for color changes.
	 * 
	 * @param listener
	 *          the listener to add
	 */
	public void addColorSelectionListener(ColorSelectionListener listener) {
		this.colorSelectionListeners.add(listener);
	}

	/**
	 * Remove the specified {@link ColorSelectionListener} instance.
	 * 
	 * @param listener
	 *          the listener to be removed
	 */
	public void removeColorSelectionListener(ColorSelectionListener listener) {
		this.colorSelectionListeners.remove(listener);
	}

	/**
	 * Gets the selected color as {@link java.awt.Color} instance.
	 * 
	 * @return the AWT selected color
	 */
	public java.awt.Color getSelectedColorAsAWTColor() {
		return Colors.getAWT4SWTRGBColor(selectedRGB);
	}

	/**
	 * Gets the selected color as {@link Color} instance.
	 * <p>
	 * <b>NOTE</b>: the {@link Color} instance returned must be directly disposed by the user in order to release system
	 * resources. No automatic disposal is given.
	 * 
	 * @return the SWT selected color
	 */
	public Color getSelectedColorAsSWTColor() {
		return selectedRGB != null ? SWTResourceManager.getColor(selectedRGB.getRgb()) : null;
	}

	/**
	 * Gets the selected color as {@link RGB} instance.
	 * 
	 * @return the RGB of the selected color
	 */
	public AlfaRGB getSelectedColorAsRGB() {
		return selectedRGB;
	}

	@Override
	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);
		updatePreviewImage(enabled);
		this.imgColorPreview.setEnabled(enabled);
		this.textColorValue.setEnabled(enabled);
		this.buttonColorChoser.setEnabled(enabled);
	}

	/*
	 * Internally updates the color preview image.
	 */
	private void updatePreviewImage(boolean enabled) {
		// Remember to dispose the old image no longer needed in order to release system resources
		Image oldImage = imgColorPreview.getImage();
		if (oldImage != null) {
			oldImage.dispose();
		}
		Image newImage = Colors.getSWTColorPreview(Colors.getAWT4SWTRGBColor(selectedRGB), 16, 16);
		if (enabled) {
			imgColorPreview.setImage(newImage);
		} else {
			Image disabledImg = new Image(getDisplay(), newImage, SWT.IMAGE_DISABLE);
			imgColorPreview.setImage(disabledImg);
			newImage.dispose();
		}
	}
}
