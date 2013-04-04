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

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.nebula.widgets.gallery.Gallery;
import org.eclipse.nebula.widgets.gallery.GalleryItem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.DragSourceListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.wb.swt.ResourceManager;

import com.jaspersoft.studio.components.Activator;
import com.jaspersoft.studio.components.crosstab.messages.Messages;
import com.jaspersoft.studio.editor.style.TemplateStyle;
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
	 * Drag listener for the drag and drop of a style from a gallery 
	 * control
	 * 
	 * @author Orlandin Marco
	 *
	 */
	protected class StyleDragListener implements DragSourceListener{
		
		/**
		 * The gallery where the drag start
		 */
		private Gallery checkedGallery;
		
		/**
		 * Create an instance of the class
		 * 
		 * @param checkedGallery the gallery where the drag start
		 */
		public StyleDragListener(Gallery checkedGallery){
			this.checkedGallery = checkedGallery;
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
	 * Return an image that can be used as icon for the create style action
	 * 
	 * @return an SWT image
	 * 
	 */
	public Image getNewStyleImage() {
		Image image = ResourceManager.getImage("create-style");
		if (image == null){
			image = Activator.getDefault().getImageDescriptor("icons/create-style.png").createImage();
			ResourceManager.addImage("create-style", image);
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
		ti.setText(description.isEmpty() ? " " : description);
		Image previewImage = generatePreviewFigure(style);
		ti.setImage(previewImage);
		ti.setSelectedImage(previewImage);
		ti.setStandardImage(previewImage);
		ti.setData(style);
		return ti;
	}
	
	/**
	 * Build a preview image of a TempalteStyle
	 * 
	 * @param style the style
	 * @return a previw SWT image of the style
	 */
	public abstract Image generatePreviewFigure(final TemplateStyle style);
	
}
