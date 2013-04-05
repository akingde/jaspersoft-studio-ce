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
package com.jaspersoft.studio.components.commonstyles;

import java.awt.Rectangle;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.nebula.widgets.gallery.Gallery;
import org.eclipse.nebula.widgets.gallery.GalleryItem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.DragSourceListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.wb.swt.ResourceManager;

import com.jaspersoft.studio.components.Activator;
import com.jaspersoft.studio.components.crosstab.messages.Messages;
import com.jaspersoft.studio.editor.style.TemplateStyle;
import com.jaspersoft.studio.style.view.TemplateStyleView;
import com.jaspersoft.studio.style.view.TemplateViewProvider;
import com.jaspersoft.studio.utils.IOUtils;
import com.jaspersoft.studio.wizards.JSSWizard;

/**
 * 
 * Class with some common methods to contribute a TemplateStyle view
 * 
 * @author Orlandin Marco
 *
 */
public abstract class CommonViewProvider implements TemplateViewProvider{
	
	/**
	 * The gallery that show the element
	 */
	protected Gallery checkedGallery;
	
	
	/**
	 * The common delete action
	 */
	protected MenuItem deleteAction = null;
	
	/**
	 * The common edit action
	 */
	protected MenuItem editAction = null;
	
	/**
	 * The standard create action
	 */
	protected MenuItem createAction = null;
	
	
	/**
	 * Drag listener for the drag and drop of a style from a gallery 
	 * control
	 * 
	 * @author Orlandin Marco
	 *
	 */
	protected class StyleDragListener implements DragSourceListener{
		
		public StyleDragListener(){
			
		}
		
		@Override
		public void dragStart(DragSourceEvent event) {
			dragSetData(event);
		}
		
		/**
		 * The set data method insert into the event the selected templatestyle,
		 * if any
		 */
		@Override
		public void dragSetData(DragSourceEvent event) {
			if (checkedGallery.getSelection().length>0){
				Object data = checkedGallery.getSelection()[0].getData();
				byte[] serializedData = IOUtils.writeToByteArray(data);
				event.data = serializedData;
			}
			
		}
		
		@Override
		public void dragFinished(DragSourceEvent event) {	
		}
	}
	
	/**
	 * Handler for the right click of the gallery, if the right click is outside an element then
	 * the selected element will be deselected and the delete and update actions (if present) will 
	 * be disabled
	 */
	protected class GalleryRightClick implements MouseListener {

		public GalleryRightClick(){}
		
		@Override
		public void mouseUp(MouseEvent e) {	
		}
		
		@Override
		public void mouseDown(MouseEvent e) {
			if (e.button == 3 && checkedGallery.getItem(new Point(e.x,e.y))==null){
				checkedGallery.deselectAll();
				if (deleteAction != null) deleteAction.setEnabled(false);
				if (editAction != null) editAction.setEnabled(false);
			} else {
				if (deleteAction != null) deleteAction.setEnabled(true);
				if (editAction != null) editAction.setEnabled(true);
			}
		}
		
		@Override
		public void mouseDoubleClick(MouseEvent e) {
		}
	}
	
	/**
	 * Create a standard contextual delete action
	 */
	protected void initializeDeleteAction(){
	    deleteAction = new MenuItem(checkedGallery.getMenu(), SWT.NONE);
	    deleteAction.setText(com.jaspersoft.studio.components.commonstyles.messages.Messages.CommonViewProvider_deleteStyleLabel);
	    deleteAction.setImage(getDeleteStyleImage());
	    deleteAction.addSelectionListener(new SelectionAdapter() {
	    	@Override
	    	public void widgetSelected(SelectionEvent e) {
	    		doDelete();
	    	}
		});   
	}
	
	/**
	 * Code to execute when the delete contextual action is called
	 */
	protected void doDelete(){
		Shell actualShell = PlatformUI.getWorkbench().getDisplay().getActiveShell();
		if (MessageDialog.openQuestion(actualShell, com.jaspersoft.studio.components.commonstyles.messages.Messages.CommonViewProvider_deleteStyleQuestionTitle, com.jaspersoft.studio.components.commonstyles.messages.Messages.CommonViewProvider_deleteStyleQuestionText)){
			GalleryItem selectedItem = checkedGallery.getSelection()[0];
			TemplateStyleView.getTemplateStylesStorage().removeStyle((TemplateStyle)selectedItem.getData());
			checkedGallery.remove(selectedItem);
			checkedGallery.redraw();
		}
	}
	
	/**
	 * Create a standard contextual delete action
	 */
	protected void initializeCreateAction(){
		createAction = new MenuItem(checkedGallery.getMenu(), SWT.NONE);
		createAction.setText(com.jaspersoft.studio.components.commonstyles.messages.Messages.CommonViewProvider_createStyleLabel);
		createAction.setImage(getNewStyleImage());
		createAction.addSelectionListener(new SelectionAdapter() {
	    	@Override
	    	public void widgetSelected(SelectionEvent e) {
	    		doCreate();
	    	}
		});
	}
	
	/**
	 * Code to execute when the delete contextual action is called
	 */
	protected void doCreate(){}
	
	/**
	 * Return an image that can be used as icon for the create style action
	 * 
	 * @return an SWT image
	 * 
	 */
	public Image getNewStyleImage() {
		Image image = ResourceManager.getImage("create-style"); //$NON-NLS-1$
		if (image == null){
			image = Activator.getDefault().getImageDescriptor("icons/create-style.png").createImage(); //$NON-NLS-1$
			ResourceManager.addImage("create-style", image); //$NON-NLS-1$
		}
		return image;
	}
	
	/**
	 * Return an image that can be used as icon for the delete style action
	 * 
	 * @return an SWT image
	 * 
	 */
	public Image getDeleteStyleImage() {
		Image image = ResourceManager.getImage("delete-style"); //$NON-NLS-1$
		if (image == null){
			image = Activator.getDefault().getImageDescriptor("icons/delete_style.gif").createImage(); //$NON-NLS-1$
			ResourceManager.addImage("delete-style", image); //$NON-NLS-1$
		}
		return image;
	}

	/**
	 * Create a standard dialog from a wizard. It also substitute the ok button text of the dialog with
	 * a finish text
	 * 
	 * @param wizardPage page to put inside the dialog
	 * @return a dialog that can be opened
	 */
	protected WizardDialog getEditorDialog(JSSWizard wizardPage){
			WizardDialog dialog = new WizardDialog(Display.getDefault().getActiveShell(), wizardPage){
			//Ovverride this method to change the default text of the finish button with another text
			@Override
			protected Button createButton(Composite parent, int id, String label, boolean defaultButton) {
				Button button = super.createButton(parent, id, label, defaultButton);
				if (id == IDialogConstants.FINISH_ID) button.setText(Messages.EditCrosstabStyleAction_okButton);
				return button;
			}
		};
		return dialog;
	}
	
	/**
	 * Build a gallery item for a TemplateStyle
	 * 
	 * @param style the style
	 * @param rootItem the root of all the items
	 * @return A gallery item
	 */
	protected GalleryItem getItem(TemplateStyle style, GalleryItem rootItem) {
		GalleryItem ti = new GalleryItem(rootItem, SWT.NONE);
		String description = style.getDescription();
		ti.setText(description.isEmpty() ? " " : description); //$NON-NLS-1$
		Image previewImage = generatePreviewFigure(style);
		ti.setImage(previewImage);
		ti.setSelectedImage(previewImage);
		ti.setStandardImage(previewImage);
		ti.setData(style);
		return ti;
	}
	
	/**
	 * Create a shadow effect on a Rectangle
	 * 
	 * @param gc the graphics used to design
	 * @param bounds bounds of the element where to create the shadow
	 * @param xOffset how much the shadow is translated of the x axe
	 * @param yOffset how much the shadow is translated of the y axe
	 * @param radius the radious of the shadow
	 */
	public static void fillRoundRectangleDropShadow(GC gc, Rectangle bounds, int xOffset, int yOffset, int radius) {
		gc.setAdvanced(true);
		gc.setAntialias(SWT.ON);
		Color oldColor = gc.getBackground();
		gc.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_BLACK));
		gc.setAlpha(0x8f / radius);

		for (int i = 0; i < radius; i++) {
			Rectangle shadowBounds = new Rectangle(bounds.x + xOffset, bounds.y + yOffset, bounds.width - i, bounds.height - i);

			gc.fillRoundRectangle(shadowBounds.x, shadowBounds.y, shadowBounds.width, shadowBounds.height, radius, radius);
		}
		gc.setBackground(oldColor);
		gc.setAlpha(0xff);
	}
	
	
	/**
	 * Build a preview image of a TempalteStyle
	 * 
	 * @param style the style
	 * @return a previw SWT image of the style
	 */
	public abstract Image generatePreviewFigure(final TemplateStyle style);
	
}
