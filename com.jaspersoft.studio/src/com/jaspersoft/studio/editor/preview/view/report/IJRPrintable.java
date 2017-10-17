/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.preview.view.report;

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.fill.FillListener;

import com.jaspersoft.studio.editor.preview.stats.Statistics;

public interface IJRPrintable extends FillListener {
	public void setJRPRint(Statistics stats, JasperPrint jrprint) throws Exception;

	public void setJRPRint(Statistics stats, JasperPrint jrprint, boolean refresh) throws Exception;

	public JasperPrint getJrPrint();
}
