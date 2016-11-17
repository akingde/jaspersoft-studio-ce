/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
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
import com.jaspersoft.studio.utils.ModelUtils;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JasperDesign;

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
	 * Enable only if there is at least one image to refresh
	 */
	@Override
	protected boolean calculateEnabled() {
		return !getSelectedImages().isEmpty();
	}

	@Override
	public void run() {
		List<MImage> images = getSelectedImages();
		JasperDesign jd = null;
		for(MImage image : images){
			JasperReportsConfiguration jConfig = image.getJasperConfiguration();
			//Remove the old resource from the cache
			LazyImageConverter.getInstance().removeCachedImage(jConfig, image);
			if (jd == null){
				jd = image.getJasperDesign();
			}
		}
		//Force the refresh of all the elements of the report because a single image can be used in other places
		for (JRDesignElement element : ModelUtils.getAllElements(jd)) {
			element.getEventSupport().firePropertyChange(MGraphicElement.FORCE_GRAPHICAL_REFRESH, false, true);
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
