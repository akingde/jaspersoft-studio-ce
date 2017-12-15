/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.section.report;

import net.sf.jasperreports.engine.design.JasperDesign;

/**
 * Utility methods to update the page size to have valid values
 * 
 * @author Orlandin Marco
 *
 */
public class PageFormatUtils {

	/**
	 * Update the column width and eventually the column space according to the current
	 * page and margins width and the number of columns
	 * 
	 * @param jd the jasperdesign
	 */
	public static void updateColumnWidth(JasperDesign jd) {
		int pagespace = jd.getPageWidth() - jd.getLeftMargin() - jd.getRightMargin();
		int nrcolspace = jd.getColumnCount() - 1;
		int colspace = nrcolspace * jd.getColumnSpacing();
		int mspace = Math.max(0, nrcolspace > 0 ? colspace / nrcolspace : pagespace);
		int maxspace = Math.max(0, nrcolspace > 0 ? pagespace / nrcolspace : pagespace);
		if (mspace > maxspace)
			mspace = maxspace;

		if (mspace < jd.getColumnSpacing()){
			jd.setColumnSpacing(mspace);
		}

		int cw = (int) Math.floor((double) (pagespace - nrcolspace * jd.getColumnSpacing()) / (jd.getColumnCount()));
		jd.setColumnWidth(cw);
	}
	
}
