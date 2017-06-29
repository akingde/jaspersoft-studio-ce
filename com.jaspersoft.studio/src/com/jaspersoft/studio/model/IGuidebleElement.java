/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.model;

import com.jaspersoft.studio.editor.gef.rulers.ReportRulerGuide;

public interface IGuidebleElement {

	public abstract ReportRulerGuide getVerticalGuide();

	public abstract void setVerticalGuide(ReportRulerGuide verticalGuide);

	public abstract ReportRulerGuide getHorizontalGuide();

	public abstract void setHorizontalGuide(ReportRulerGuide horizontalGuide);

}
