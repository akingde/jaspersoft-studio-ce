/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.preview;

import java.util.Map;

import com.jaspersoft.studio.editor.preview.actions.RunStopAction;
import com.jaspersoft.studio.editor.preview.view.control.JiveRunner;

/**
 * This interface should be implemented by whose clients who want to contribute
 * to the extension-point <code>com.jaspersoft.studio.previewModeInfo</code>.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public interface PreviewModeDetails {

	/** Extension point id information */
	String EXTENSION_POINT_ID = "previewModeInfo";

	/** Constant for Local Preview Mode */
	String PREVIEW_MODE_LOCAL = RunStopAction.MODERUN_LOCAL;

	/** Constant for Jive Preview Mode */
	String PREVIEW_MODE_JIVE = JiveRunner.ID;

	/**
	 * @return the id of the preview mode to which this details apply to
	 */
	String getPreviewModeID();

	/**
	 * @return a map of properties that should be set in the specified preview mode
	 *         ({@link #getPreviewModeID()}).
	 */
	Map<String, String> getPreviewModeProperties();

}
