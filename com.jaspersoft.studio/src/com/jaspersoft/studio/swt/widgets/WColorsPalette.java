/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.swt.widgets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.property.color.chooser.ColorDialog;
import com.jaspersoft.studio.swt.events.IRefreshableList;
import com.jaspersoft.studio.swt.events.PaletteListener;
import com.jaspersoft.studio.utils.AlfaRGB;
import com.jaspersoft.studio.utils.Colors;

/**
 * Widget that enables the use to add/edit/remove colors belonging to a palette.
 * The user has also the ability to re-order them.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public class WColorsPalette extends Composite implements IRefreshableList{
	
	private List<String> colors;
	private List<com.jaspersoft.studio.swt.events.PaletteListener> paletteListeners;
	private static List<String> clipboardColors = new ArrayList<String>();
	private int minimumColors;
	
	// Widgets stuff
	private TableViewer tableViewerElements;
	private Button btnAddElement;
	private Button btnModifyElement;
	private Button btnRemoveElement;
	private Button btnMoveUp;
	private Button btnMoveDown;
	private Button btnCopyPalette;
	private Button btnPastePalette;
	
	/**
	 * Creates the widget.
	 * 
	 * @param parent the parent composite
	 * @param style the style associated to the widget
	 * @param minimumColors the minimum number of colors that must be defined 
	 */
	public WColorsPalette(Composite parent, int style, int minimumColors) {
		super(parent, style);
		this.minimumColors = minimumColors;
		this.paletteListeners = new ArrayList<PaletteListener>();
		this.colors = new ArrayList<String>();
		GridLayout widgetGl = new GridLayout(2, false);
		widgetGl.verticalSpacing = 0;
		widgetGl.marginHeight = 0;
		this.setLayout(widgetGl);
		createWidgetContent();	
	}
	
	/*
	 * Creates the widget content.
	 */
	private void createWidgetContent() {
		tableViewerElements = new TableViewer(this, SWT.BORDER | SWT.V_SCROLL | SWT.MULTI);
		tableViewerElements.getTable().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 7));
		tableViewerElements.setLabelProvider(getPaletteLabelProvider());
		tableViewerElements.setContentProvider(getPaletteContentProvider());
		tableViewerElements.setInput(colors);
		tableViewerElements.addDoubleClickListener(new IDoubleClickListener() {
			public void doubleClick(DoubleClickEvent event) {
				modifyButtonPressed();
			}
		});
		tableViewerElements.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
				enableDefaultButtons();
			}
		});
		
		btnAddElement = new Button(this, SWT.NONE);
		btnAddElement.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false, 1, 1));
		btnAddElement.setText(Messages.WColorsPalette_Add);
		btnAddElement.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				addButtonPressed();
			}
		});
		
		btnModifyElement = new Button(this, SWT.NONE);
		btnModifyElement.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false, 1, 1));
		btnModifyElement.setText(Messages.WColorsPalette_Modify);
		btnModifyElement.addSelectionListener(new SelectionAdapter(){
			@Override
			public void widgetSelected(SelectionEvent e) {
				modifyButtonPressed();
			}
		});
		
		btnRemoveElement = new Button(this, SWT.NONE);
		btnRemoveElement.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false, 1, 1));
		btnRemoveElement.setText(Messages.WColorsPalette_Remove); 
		btnRemoveElement.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				removeButtonPressed();
			}
		});
		
		btnMoveUp = new Button(this, SWT.NONE);
		btnMoveUp.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false, 1, 1));
		btnMoveUp.setText(Messages.WColorsPalette_Up); 
		btnMoveUp.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				moveUpButtonPressed();
			}
		});
		
		btnMoveDown = new Button(this, SWT.NONE);
		btnMoveDown.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false, 1, 1));
		btnMoveDown.setText(Messages.WColorsPalette_Down); 
		btnMoveDown.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				moveDownButtonPressed();
			}
		});
		
		btnCopyPalette = new Button(this, SWT.NONE);
		btnCopyPalette.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false, 1, 1));
		btnCopyPalette.setText(Messages.WColorsPalette_CopyColors); 
		btnCopyPalette.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				copyCurrentPalette();
			}
		});
		
		btnPastePalette = new Button(this, SWT.NONE);
		btnPastePalette.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false, 1, 1));
		btnPastePalette.setText(Messages.WColorsPalette_PasteColors); 
		btnPastePalette.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				pasteClipboardPalette();
			}
		});
		
		enableDefaultButtons();
	}

	/**
	 * Returns the content provider associated to the widget.
	 * <p>
	 * 
	 * By default an {@link ArrayContentProvider} is returned.
	 * Subclasses may return a different one.
	 * 
	 * @return the content provider for the widget 
	 */
	protected IContentProvider getPaletteContentProvider() {
		return new ArrayContentProvider();
	}

	/**
	 * Returns the label provider associated to the widget.
	 * <p>
	 * 
	 * By default a {@link LabelProvider} is returned.
	 * Subclasses may return a different one.
	 * 
	 * @return the label provider for the widget 
	 */
	protected IBaseLabelProvider getPaletteLabelProvider() {
		return new LabelProvider() {

			// Cache map for color previews
			private HashMap<RGB, Image> colorIcons=new HashMap<RGB, Image>();
			
			@Override
			public Image getImage(Object element) {
				RGB rgb = Colors.decodeHexStringAsSWTRGB((String)element);
				Image image=colorIcons.get(rgb);
				if (image==null){
					image = Colors.getSWTColorPreview(Colors.getAWT4SWTRGBColor(AlfaRGB.getFullyOpaque(rgb)), 16, 16);
				}
				// Cache image
				colorIcons.put(rgb, image);
				return image;
			}

			@Override
			public void dispose() {
				// Dispose all SWT images representing the small icon preview for color ranges
				for (Image img : colorIcons.values()){
					img.dispose();
				}
				colorIcons.clear();
				super.dispose();
			}					
	
		};
	}

	/**
	 * Method invoked when the "Remove" button is pressed.
	 * <p>
	 * 
	 * It removes the selected element from the list and
	 * updates the action buttons according.
	 */
	protected void removeButtonPressed() {
		IStructuredSelection selection = (IStructuredSelection) tableViewerElements.getSelection();
		if (selection!=null){
			Object[] items = selection.toArray();
			for(Object item : items){
				colors.remove(item);
			}
			refreshList();
		}
		enableDefaultButtons();
	}
	
	/**
	 * Method invoked when the "Modify" button is pressed.
	 * <p>
	 * 
	 * It modifies the selected element in the list and
	 * updates the action buttons according.
	 */
	protected void modifyButtonPressed() {
		Object selected=((IStructuredSelection)tableViewerElements.getSelection()).getFirstElement();
		if(selected!=null){
			ColorDialog colorChooseDialog = new ColorDialog(getShell());
			colorChooseDialog.setText(Messages.WColorsPalette_PickNewColor);
			RGB decodedRGB = Colors.decodeHexStringAsSWTRGB((String)selected);
			colorChooseDialog.setRGB(decodedRGB);
			RGB newRGB = colorChooseDialog.openRGB();
			if(newRGB!=null){
				String hexEncodedRGBColor = Colors.getHexEncodedRGBColor(newRGB);
				int indexOfSelected = colors.indexOf(selected);
				colors.remove(indexOfSelected);
				colors.add(indexOfSelected, hexEncodedRGBColor);
				refreshList();
			}
		}
		enableDefaultButtons();
	}

	/**
	 * Method invoked when the "Add" button is pressed.
	 * <p>
	 * 
	 * It creates a new element, adds it to the list and
	 * updates the action buttons according.
	 */
	protected void addButtonPressed() {
		ColorDialog colorChooseDialog = new ColorDialog(getShell());
		colorChooseDialog.setText(Messages.WColorsPalette_PickNewColor);
		RGB newRGB = colorChooseDialog.openRGB();
		if(newRGB!=null){
			String hexEncodedRGBColor = Colors.getHexEncodedRGBColor(newRGB);
			colors.add(hexEncodedRGBColor);
			refreshList();
		}
		enableDefaultButtons();
	}
	
	/**
	 * Method invoked when the "Move down" button is pressed.
	 */
	protected void moveDownButtonPressed() {
		IStructuredSelection selection = (IStructuredSelection)tableViewerElements.getSelection();
		if(selection!=null && !selection.isEmpty()){
			Object[] items = selection.toArray();
			for (int i=items.length-1; i>=0; i--){
				int indexOf = colors.indexOf(items[i]);
				colors.remove(items[i]);
				colors.add(indexOf+1,(String)items[i]);
			}
			refreshList();
		}
		enableDefaultButtons();
	}

	/**
	 * Method invoked when the "Move up" button is pressed.
	 */
	protected void moveUpButtonPressed() {
		IStructuredSelection selection = (IStructuredSelection)tableViewerElements.getSelection();
		if(selection!=null && !selection.isEmpty()){
			Object[] items = selection.toArray();
			for (int i=0; i<items.length; i++){
				int indexOf = colors.indexOf(items[i]);
				colors.remove(items[i]);
				colors.add(indexOf-1,(String)items[i]);
			}
			refreshList();
		}
		enableDefaultButtons();
	}

	/**
	 * Method invoked when the selection on widget is changed
	 * or an operation is performed.
	 */
	protected void enableDefaultButtons() {
		// Add button (always enabled) 
		btnAddElement.setEnabled(true);
		// Paste colors button
		btnPastePalette.setEnabled(!clipboardColors.isEmpty());

		int itemCount = tableViewerElements.getTable().getItemCount();
		int selectionCount = tableViewerElements.getTable().getSelectionCount();
		if(selectionCount==0){
			// Nothing selected
			btnCopyPalette.setEnabled(false);
			btnModifyElement.setEnabled(false);
			btnRemoveElement.setEnabled(false);
			btnMoveDown.setEnabled(false);
			btnMoveUp.setEnabled(false);
		}
		else {
			btnCopyPalette.setEnabled(true);
			boolean isMultipleSelection = selectionCount>1;
			boolean allSelected = itemCount-selectionCount == 0;
			// Modify button
			btnModifyElement.setEnabled(!isMultipleSelection);
			// Remove button
			btnRemoveElement.setEnabled(itemCount>minimumColors && !allSelected);
			// Move up and Move down buttons 
			int colorsNum = colors.size();
			IStructuredSelection selection=(IStructuredSelection)tableViewerElements.getSelection();
			Object[] selectedItems = selection.toArray();
			int firstSelIndex = colors.indexOf(selectedItems[0]);
			int lastSelIndex = colors.indexOf(selectedItems[selectedItems.length-1]);
			btnMoveDown.setEnabled(lastSelIndex!=colorsNum-1);
			btnMoveUp.setEnabled(firstSelIndex!=0);
		}
	}
	
	/**
	 * @return the list of colors as array
	 */
	public String[] getColorsListAsArray(){
		return colors.toArray(new String[colors.size()]);
	}
	
	/**
	 * Returns the expression text representing the list of colors.
	 * As far as possible the text returned is coherent with the
	 * language specified as parameter (i.e: java or groovy).
	 * 
	 * @return the expression text for colors
	 */
	public String getColorsAsExpressionText(String language){
		// For now do not care about language		
		if(colors.isEmpty()) return null;
		StringBuffer sb = new StringBuffer("java.util.Arrays.asList("); //$NON-NLS-1$
		// Add colors
		for(String c : colors){
			sb.append("\"").append(c).append("\",");		 //$NON-NLS-1$ //$NON-NLS-2$
		}
		sb.setLength(sb.length()-1); // avoid last comma
		sb.append(")"); //$NON-NLS-1$
		return sb.toString();
	}
	
	/*
	 * Notifies the palette colors update.
	 */
	private void notifyPaletteUpdate(){
		for(PaletteListener l : paletteListeners){
			l.paletteModified(colors);
		}
	}

	@Override
	public void refreshList() {
		tableViewerElements.setInput(colors);
		notifyPaletteUpdate();
	}
	
	/**
	 * Adds a new palette listener.
	 * 
	 * @param listener the new listener
	 */
	public void addPaletteListener(PaletteListener listener){
		paletteListeners.add(listener);
	}
	
	/**
	 * Remove the specified palette listener.
	 * 
	 * @param listener the listener to remove
	 */
	public void removePaletteListener(PaletteListener listener){
		paletteListeners.remove(listener);
	}
	
	public TableViewer getPaletteViewer() {
		return tableViewerElements;
	}
	
	@Override
	public void dispose() {
		super.dispose();
		paletteListeners.clear();
		paletteListeners = null;
	}

	/**
	 * Updates the list of available colors for the palette with the new ones.
	 * 
	 * @param decodeHexColorsArray the new colors
	 */
	public void setColors(String[] decodeHexColorsArray) {
		Assert.isNotNull(decodeHexColorsArray);
		this.colors.clear();
		this.colors.addAll(Arrays.asList(decodeHexColorsArray));
		refreshList();
	}

	/*
	 * Copies the currently selected colors in the shared palette.
	 */
	private void copyCurrentPalette(){
		IStructuredSelection sel = (IStructuredSelection)tableViewerElements.getSelection();
		if(sel!=null) {
			clipboardColors.clear();
			clipboardColors.addAll(sel.toList());
		}
		enableDefaultButtons();
	}
	
	/*
	 * Pastes the colors currently in the shared palette.
	 */
	private void pasteClipboardPalette(){
		colors.addAll(clipboardColors);
		refreshList();
		enableDefaultButtons();
	}
}
