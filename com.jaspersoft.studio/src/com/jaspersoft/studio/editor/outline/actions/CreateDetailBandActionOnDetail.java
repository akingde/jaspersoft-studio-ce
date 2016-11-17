/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.outline.actions;

import java.util.List;

import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.band.MBand;

import net.sf.jasperreports.engine.type.BandTypeEnum;

/**
 * Action used to add a new detail band to the report, different from the standard 
 * add detail band action this is used only on detail bands and it is shown in a different
 * position in the context menu
 * 
 * @author Orlandin Marco
 * 
 */
public class CreateDetailBandActionOnDetail extends CreateDetailBandAction {

	/** The Constant ID. */
	public static final String ID = "create_detail_band_on_detail"; //$NON-NLS-1$

	/**
	 * Construct the action
	 * 
	 * @param part
	 *          the current editor
	 */
	public CreateDetailBandActionOnDetail(IWorkbenchPart part) {
		super(part);
		setLazyEnablementCalculation(true);
	}

	/**
	 * The action is enabled only if there is a single band selected and that band is a detail
	 */
	@Override
	protected boolean calculateEnabled() {
		List<Object> elements = editor.getSelectionCache().getSelectionModelForType(MBand.class);
		if  (elements.size() == 1 && ((MBand) elements.get(0)).getBandType() == BandTypeEnum.DETAIL){
			return true;
		}
		return false;
	}
	
	/**
	 * Initializes this action's text and images.
	 */
	@Override
	protected void init() {
		setText(Messages.CreateDetailBandAction_actionName);
		setToolTipText(Messages.CreateDetailBandAction_actionTooltip);
		setId(CreateDetailBandActionOnDetail.ID);
		ISharedImages sharedImages = PlatformUI.getWorkbench().getSharedImages();
		setImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_NEW_WIZARD));
		setDisabledImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_NEW_WIZARD_DISABLED));
		setEnabled(false);
	}
}
