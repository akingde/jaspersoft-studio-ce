/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.components.table.figure;

import java.awt.Rectangle;
import java.util.Collection;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.dnd.AbstractTransferDropTargetListener;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.nebula.widgets.gallery.Gallery;
import org.eclipse.nebula.widgets.gallery.GalleryItem;
import org.eclipse.nebula.widgets.gallery.NoGroupRenderer;
import org.eclipse.nebula.widgets.gallery.RoundedGalleryItemRenderer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSource;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.ui.PlatformUI;
import org.eclipse.wb.swt.ResourceManager;

import com.jaspersoft.studio.components.Activator;
import com.jaspersoft.studio.components.commonstyles.CommonViewProvider;
import com.jaspersoft.studio.components.table.model.dialog.TableStyle;
import com.jaspersoft.studio.components.table.model.dialog.TableStyle.BorderStyleEnum;
import com.jaspersoft.studio.components.table.model.table.command.wizard.TableStyleWizard;
import com.jaspersoft.studio.editor.style.TemplateStyle;
import com.jaspersoft.studio.style.view.TemplateStyleView;

/**
 * Extension to show inside a gallery a list of TableStyle that can be drag and dropped 
 * on a Table to apply them
 * 
 * @author Orlandin Marco
 *
 */
public class TableStyleView extends CommonViewProvider {

	/**
	 * Height of every image in the gallery
	 */
	private static final int GALLERY_HEIGHT = 100;
	
	/**
	 * Width of every image in the gallery
	 */
	private static final int GALLERY_WIDTH = 100;
	
	/**
	 * The gallery
	 */
	private Gallery tableGallery;	
	
	/**
	 * The gallery root item
	 */
	private GalleryItem tableGroup;
	
	/**
	 * Create a gallery with inside all the table styles with their previews
	 */
	@Override
	public void createControls(Composite parent) {
		tableGallery = new Gallery(parent, SWT.VIRTUAL | SWT.V_SCROLL | SWT.BORDER);
		final NoGroupRenderer gr = new NoGroupRenderer();
		gr.setMinMargin(2);
		gr.setItemSize(GALLERY_WIDTH, GALLERY_HEIGHT);
		gr.setAutoMargin(true);
		GridData gd = new GridData(GridData.FILL_BOTH);
		tableGallery.setLayoutData(gd);
		tableGallery.setGroupRenderer(gr);
		RoundedGalleryItemRenderer ir = new RoundedGalleryItemRenderer();
		ir.setShowLabels(true);
		tableGallery.setItemRenderer(ir);
		addDragSupport();
		
	    Menu popupMenu = new Menu(tableGallery);
	    MenuItem newItem = new MenuItem(popupMenu, SWT.NONE);
	    newItem.setText("Create style");
	    newItem.setImage(getNewStyleImage());
	    newItem.addSelectionListener(new SelectionAdapter() {
	    	@Override
	    	public void widgetSelected(SelectionEvent e) {
	    		TableStyleWizard wizard = new TableStyleWizard();
	    		WizardDialog dialog = getEditorDialog(wizard);
	    		if (dialog.open() == Dialog.OK){
	    			TableStyle newStyle = wizard.getTableStyle();
	    			TemplateStyleView.getTemplateStylesStorage().addStyle(newStyle);
	    			getItem(newStyle, tableGroup);
	    			tableGallery.redraw();
	    		}
	    	}
		});
	    tableGallery.setMenu(popupMenu);
	}

	/**
	 * The name of the tab
	 * 
	 * @return a string that will be used as title of the tab
	 */
	@Override
	public String getTabName() {
		return "Table Styles";
	}

	/**
	 * Called when the styles need to be inserted in the gallery. Here are passed all the template styles read from
	 * the properties file, so only the one with type TableStyle will be shown
	 * 
	 * @param styles a list of all the TemplateStyles read from the properties file
	 */
	@Override
	public void fillStyles(Collection<TemplateStyle> styles) {
		tableGroup = new GalleryItem(tableGallery, SWT.NONE);
		tableGallery.setRedraw(false);
		for(TemplateStyle style : styles)
			if (style instanceof TableStyle) getItem(style, tableGroup);
		tableGallery.setRedraw(true);
	}
	
	/**
	 * Add the drag support
	 */
	private void addDragSupport(){
		int operations = DND.DROP_MOVE;
		final Transfer[] types = new Transfer[] { TableRestrictedTransferType.getInstance() };
		DragSource source = new DragSource(tableGallery, operations);
		source.setTransfer(types);
		source.addDragListener(new StyleDragListener(tableGallery));
	}

	/**
	 * Build a preview image of a Table
	 * 
	 * @param style the style of the table
	 * @return a preview SWT image of the table
	 */
	public Image generatePreviewFigure(final TemplateStyle style){
		String key = "tableTemplates_"+style.toString();
		Image image = ResourceManager.getImage(key);
		if (image == null && style instanceof TableStyle){
			TableStyle tableStyle = (TableStyle)style;
			image = new Image(null,new org.eclipse.swt.graphics.Rectangle(0, 0, GALLERY_WIDTH, GALLERY_HEIGHT));
			GC graphics = new GC(image);
			int y = 1;
		    int x = 1;
		    int w = GALLERY_WIDTH-2;
		    int h = GALLERY_HEIGHT-2;
	        int rowHeight = h/7;
	        
	        Rectangle row_bounds = new Rectangle(x,y + rowHeight*2, w, rowHeight);
	        graphics.setForeground(ColorConstants.white);
		    graphics.fillRectangle(row_bounds.x, row_bounds.y, row_bounds.width, row_bounds.height);
		    row_bounds = new Rectangle(x,y + rowHeight*3, w, rowHeight);
		    
		    RGB c = null;
		    Color swtColor = null;
		    Display disp = PlatformUI.getWorkbench().getDisplay();
		    if (tableStyle.hasAlternateColor())
		    {
		    	c = style.getColor(TableStyle.COLOR_DETAIL);
		    	swtColor = new Color(disp,c);
		    	graphics.setBackground(swtColor);
		    }
		    graphics.fillRectangle(row_bounds.x, row_bounds.y, row_bounds.width, row_bounds.height);
		    row_bounds = new Rectangle(x,y + rowHeight*4, w, rowHeight);
		    if (swtColor != null) swtColor.dispose();
		    graphics.setBackground(ColorConstants.white);
		    graphics.fillRectangle(row_bounds.x, row_bounds.y, row_bounds.width, row_bounds.height);

		    // TABLE HEADER
		    row_bounds = new Rectangle(x,y + rowHeight*0, w, rowHeight);
		    c = style.getColor(TableStyle.COLOR_TABLE_HEADER);
		    swtColor = new Color(disp,c);
		    graphics.setBackground(swtColor);
		    graphics.fillRectangle(row_bounds.x, row_bounds.y, row_bounds.width, row_bounds.height);

		    // TABLE FOOTER
		    row_bounds = new Rectangle(x,y + rowHeight*6, w, rowHeight);
		    swtColor.dispose();
		    swtColor = new Color(disp,c);
		    graphics.setBackground(swtColor);
		    graphics.fillRectangle(row_bounds.x, row_bounds.y, row_bounds.width, row_bounds.height);


		    // COLUMN HEADER
		    row_bounds = new Rectangle(x,y + rowHeight*1, w, rowHeight);
		    swtColor.dispose();
		    c = style.getColor(TableStyle.COLOR_COL_HEADER);
		    swtColor = new Color(disp,c);
		    graphics.setBackground(swtColor);
		    graphics.fillRectangle(row_bounds.x, row_bounds.y, row_bounds.width, row_bounds.height);

		    // COLUMN FOOTER
		    row_bounds = new Rectangle(x,y + rowHeight*5, w, rowHeight);
		    swtColor.dispose();
		    swtColor = new Color(disp,c);
		    graphics.setBackground(swtColor);
		    graphics.fillRectangle(row_bounds.x, row_bounds.y, row_bounds.width, row_bounds.height);
		    swtColor.dispose();
		    
		    c = tableStyle.getRGBBorderColor();
		    swtColor = new Color(disp,c);
		    graphics.setForeground(swtColor);
		    // Draw border...
		    for (int i=0; i<8; ++i)
		    {
		    	graphics.drawLine(x, y+rowHeight*i, x+w, y+rowHeight*i);
		    }

		    h = rowHeight*7;
		    if (tableStyle.getBorderStyle() == BorderStyleEnum.FULL)
		    {
		        for (int i=0; i<3; ++i)
		        {
		        	graphics.drawLine(x+(i*(w/3)), y, x+(i*(w/3)), y+h);
		        }
		        graphics.drawLine(x+w, y, x+w, y+h-1);
		    }
		    if (tableStyle.getBorderStyle() == BorderStyleEnum.ONLY_HORIZONTAL)
		    {
		    	graphics.drawLine(x, y, x, y+h);
		    	graphics.drawLine(x+w, y, x+w, y+h-1);
		    }
		    swtColor.dispose();
			graphics.dispose();
			ResourceManager.addImage(key, image);
			
		}
		return image;
	}

	/**
	 * Return the drop listener to handle the drag and drop of an element from the tab to the editor, it can be null
	 * if the drag operation is not wanted
	 * 
	 * @param viewer the viewer of the editor
	 * @return the drop listener that will be added to the editor, it will handle the drag of a tablestyle on a table
	 */
	@Override
	public AbstractTransferDropTargetListener getDropListener(EditPartViewer viewer) {
		return new TableStyleTransferDropListener(viewer);
	}

	/**
	 * Return an empty table style that can be used to build a real TableStyle starting from the XML reperesentation
	 * of a table Style
	 */
	@Override
	public TemplateStyle getBuilder() {
		return new TableStyle();
	}

	/**
	 * Return the icon image that will be used on the tab
	 * 
	 * @return and SWT icon
	 */
	@Override
	public Image getTabImage() {
		Image image = ResourceManager.getImage("table-style-16");
		if (image == null){
			image = Activator.getDefault().getImageDescriptor("icons/table-style-16.png").createImage();
			ResourceManager.addImage("table-style-16", image);
		}
		return image;
	}
}