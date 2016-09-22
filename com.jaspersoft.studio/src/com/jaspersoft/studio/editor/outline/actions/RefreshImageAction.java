/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.editor.outline.actions;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.ui.IWorkbenchPart;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.editor.action.ACachedSelectionAction;
import com.jaspersoft.studio.jasper.LazyImageConverter;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.model.image.MImage;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

/**
 * Action to reload the physical resource of an image and update
 * its element
 * 
 * @author Orlandin Marco
 *
 */
public class RefreshImageAction extends ACachedSelectionAction {

	/** The Constant ID. */
	public static final String ID = "refresh_image_action"; //$NON-NLS-1$

	/**
	 * Constructs a <code>CreateAction</code> using the specified part.
	 * 
	 * @param part
	 *          The part for this action
	 */
	public RefreshImageAction(IWorkbenchPart part) {
		super(part);
	}

	/**
	 * Initializes this action's text and images.
	 */
	@Override
	protected void init() {
		super.init();
		setText(Messages.RefreshImageAction_actionName);
		setToolTipText(Messages.RefreshImageAction_actionDescription);
		setId(RefreshImageAction.ID);
		setImageDescriptor(JaspersoftStudioPlugin.getInstance().getImageDescriptor("icons/resources/refresh_image_action.png")); //$NON-NLS-1$
		setEnabled(false);
	}

	/**
	 * Enable only if there is at least one style that can be exported
	 */
	@Override
	protected boolean calculateEnabled() {
		return !getSelectedImages().isEmpty();
	}

	@Override
	public void run() {
		List<MImage> images = getSelectedImages();
		for(MImage image : images){
			JasperReportsConfiguration jConfig = image.getJasperConfiguration();
			//Remove the old resource from the cache
			LazyImageConverter.getInstance().removeCachedImage(jConfig, image.getValue());
			//Now the resource is no more on the cache and an update of the element will trigger a reload
			image.getValue().getEventSupport().firePropertyChange(MGraphicElement.FORCE_GRAPHICAL_REFRESH, null, null);
		}
	}
	
	/**
	 * Return the list of all the selected Images. 
	 * 
	 * @return a not null list of MImage
	 */
	private List<MImage> getSelectedImages(){
		List<Object> templates = editor.getSelectionCache().getSelectionModelForType(MImage.class);
		List<MImage> result = new ArrayList<MImage>();
		for (Object template : templates){
			result.add((MImage)template);
		}
		return result;
	}
}
