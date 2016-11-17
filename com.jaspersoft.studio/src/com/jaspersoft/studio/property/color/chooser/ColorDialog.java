/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.color.chooser;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.utils.AlfaRGB;

import net.sf.jasperreports.eclipse.ui.util.PersistentLocationDialog;

/**
 * An advanced color dialog that offers more functionalities towards the default one,
 * like the transparency and many other. It also provide a system independent way to choose the color. 
 * Different input mode are inserted into more tabs inside the dialog
 * 
 * @author Orlandin Marco
 *
 */
public class ColorDialog extends PersistentLocationDialog{

	// STYLEBITS
	
	/**
	 * This stylebit allow to show all the color selection tabs: advanced, webcolors and history
	 */
	public static int SHOW_ALL = 2;
	
	/**
	 * This stylebit allow to show only the advanced color selection tab
	 */
	public static int SHOW_ADVANCED = 4;
	
	/**
	 * This stylebit allow to show only the web color selection tab
	 */
	public static int SHOW_WEB = 6;
	
	/**
	 * This stylebit allow to show only the history color selection tab
	 */
	public static int SHOW_HISTORY= 8;
	
	/**
	 * String title of the dialog of the chooser
	 */
	private String shellTitle = null;
	
	/**
	 * Controls to define the color in an advanced mode
	 */
	private AdvancedColorWidget advancedColors = null;
	
	/**
	 * Controls to define the color from a color palette
	 */
	private WebColorsWidget webColors  = null;
	
	/**
	 * Control provider the select the previously colors
	 */
	private LastUsedColorsWidget lastColors = null;
	
	/**
	 * List of all the available controls provider
	 */
	private List<IColorProvider> colorsWidgets = new ArrayList<IColorProvider>();
	
	/**
	 * Folder where all the controls provider are placed
	 */
	private TabFolder folder;
	
  /**
   * Flag used to hide the alpha controls
   */
  private boolean hasAlpha = true;
  
  /**
   * index of the selected tab (so the selected input method)
   */
  private int selectedTab = 0;
	
	/**
	 * Color that we are changing from the color dialog, used to compare the new color 
	 * to the old one. If it is null it is not shown
	 */
	private AlfaRGB oldColor = null;
	
	/**
	 * Stylebit for the widget
	 */
	private int style;
	
	/**
	 * Construct an instance of the class. All the available tabs for the color are shown
	 * 
	 * @param parent shell for the new dialog
	 */
	public ColorDialog(Shell parent){
		super(parent);
		this.style = SHOW_ALL;
	}

	/**
	 * Construct an instance of the class. The tab shown where the color 
	 * can be selected depends from the stylebit
	 * 
	 * @param parent shell for the new dialog
	 * @param style, must be one between SHOW_ALL, SHOW_ADVANCED, SHOW_WEB, SHOW_HISTORY
	 */
	public ColorDialog(Shell parent, int style){
		super(parent);
		this.style = style;
	}
	
	/**
	 * Configure the shell to set the defined title if it is not null
	 */
	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		if (shellTitle != null){
			newShell.setText(shellTitle);
		}
	}
	
	/**
	 * Explicitly call the dispose of the color provider when the dialog
	 * is closed
	 */
	private void disposeControlsProvider(){
		for (IColorProvider provider : colorsWidgets){
			provider.dispose();
		}
	}
	
	/**
	 * Open the dialog an return the selected color when it is closed. If nothing
	 * is closed return null
	 * 
	 * @return an AlfaRGB color with the selected color if the dialog is closed
	 * with ok or null if it is closed with cancel
	 */
	public AlfaRGB openAlfaRGB(){
		int returnCode = super.open();
		disposeControlsProvider();
		if (returnCode == Dialog.CANCEL) return null;
		else {
			AlfaRGB newColor = colorsWidgets.get(selectedTab).getSelectedColor();
			LastUsedColorsWidget.addColor(newColor);

			return newColor;
		}
	}
	
	/**
	 * Open the dialog an return the selected color when it is closed. If nothing
	 * is closed return null. The control to define the alpha of the color are hidden.
	 * If the user try to get the alpha it return always 255
	 * 
	 * @return an RGB color with the selected color if the dialog is closed
	 * with ok or null if it is closed with cancel
	 */
	public RGB openRGB(){
		//When a simple RGB is requested hide the alpha control
		hasAlpha = false;
		int returnCode = super.open();
		disposeControlsProvider();
		if (returnCode == Dialog.CANCEL) return null;
		else {
			AlfaRGB newColor = colorsWidgets.get(selectedTab).getSelectedColor();
			LastUsedColorsWidget.addColor(newColor);
			return newColor != null ? newColor.getRgb() : null;
		}
	}
	
	/**
	 * Set the dialog title text. This must be set before open it to make it visible
	 * 
	 * @param title text to set in the dialog title
	 */
	public void setText(String title){
		shellTitle = title;
	}
	
	/**
	 * Set the actual color that will compared into the preview are with the new one
	 * 
	 * @param color rgb of the old color
	 */
	public void setRGB(RGB color){
		if (color != null){
			oldColor = new AlfaRGB(color, 255);
		}
	}
	
	/**
	 * Set the actual color that will compared into the preview are with the new one
	 * 
	 * @param color alfaRGB of the old color
	 */
	public void setRGB(AlfaRGB color){
		if (color != null){
			oldColor = color;
		}
	}
	
	/**
	 * Check if the stylebit is set to show the advanced color selection tab
	 * 
	 * @return true if the advanced color selection tab should be shown, false otherwise
	 */
	protected boolean isShowAdvanced(){
		return  (SHOW_ALL == style || SHOW_ADVANCED == style);
	}
	
	/**
	 * Check if the stylebit is set to show the web color selection tab
	 * 
	 * @return true if the web color selection tab should be shown, false otherwise
	 */
	protected boolean isShowWeb(){
		return  (SHOW_ALL == style || SHOW_WEB == style);
	}
	
	/**
	 * Check if the stylebit is set to show the history color selection tab
	 * 
	 * @return true if the advanced color history tab should be shown, false otherwise
	 */
	protected boolean isShowHistory(){
		return  (SHOW_ALL == style || SHOW_HISTORY == style);
	}
	
	/**
	 * Create the controls of the dialog
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		 Composite dialogArea = (Composite) super.createDialogArea(parent);
		 folder = new TabFolder(dialogArea, SWT.NONE);
		
		 //Add the tabs depending on the stylebit
		 if (isShowAdvanced()){
			 advancedColors = new AdvancedColorWidget(folder, SWT.NONE, oldColor, hasAlpha);
			 advancedColors.setLayoutData(new GridData(GridData.FILL_BOTH));
			 TabItem tab1 = new TabItem(folder, SWT.NONE);
			 tab1.setText(Messages.ColorDialog_advancedColorsLabel);
			 tab1.setControl(advancedColors);
			 colorsWidgets.add(advancedColors);
		 }
		 
		 if (isShowWeb()){
			 TabItem tab2 = new TabItem(folder, SWT.NONE);
			 tab2.setText(Messages.ColorDialog_webColorsLabel);
			 webColors = new WebColorsWidget(folder, SWT.NONE, oldColor);
			 tab2.setControl(webColors);
			 colorsWidgets.add(webColors);
		 }
		 
		 if (isShowHistory() && LastUsedColorsWidget.hasColors()){
			 lastColors = new LastUsedColorsWidget(folder, SWT.NONE, oldColor);
			 TabItem tab3 = new TabItem(folder, SWT.NONE);
			 tab3.setText(Messages.ColorDialog_lastUserdColorLabel);
			 tab3.setControl(lastColors);
			 colorsWidgets.add(lastColors);
		 }
		 
		 folder.addSelectionListener(new SelectionAdapter() {
			 @Override
			public void widgetSelected(SelectionEvent e) {
				selectedTab = folder.getSelectionIndex();
			}
		 });
		 return folder;
	}
	
}
