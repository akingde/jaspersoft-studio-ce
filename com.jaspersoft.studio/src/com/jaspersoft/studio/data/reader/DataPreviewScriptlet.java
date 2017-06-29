/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved. http://www.jaspersoft.com.
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.data.reader;

import java.util.List;

import net.sf.jasperreports.engine.JRDefaultScriptlet;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JRScriptletException;
import net.sf.jasperreports.engine.JRSortField;
import net.sf.jasperreports.engine.JasperReport;

import com.jaspersoft.studio.messages.Messages;

/**
 * Custom scriptlet used for data preview.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 * 
 */
public class DataPreviewScriptlet extends JRDefaultScriptlet {

	/** Location (plugin relative) of the custom report used for data preview */
	public static final String PREVIEW_REPORT_PATH = "/resources/data.jrxml"; //$NON-NLS-1$
	/** Parameter name for data preview columns */
	public static final String PARAM_COLUMNS = "jss.datapreview.columns"; //$NON-NLS-1$
	/** Parameter name for data preview listeners */
	public static final String PARAM_LISTENERS = "jss.datapreview.listeners"; //$NON-NLS-1$

	private List<String> columns;
	private List<DatasetReaderListener> listeners;
	private boolean isSorted = false;
	private JasperReport ds = null;

	private boolean isSorted() throws JRScriptletException {
		if (ds == null) {
			Object obj = getParameterValue(JRParameter.JASPER_REPORT);
			if (obj instanceof JasperReport) {
				ds = (JasperReport) obj;
				JRSortField[] sf = ds.getSortFields();
				if (sf != null && sf.length > 0)
					isSorted = true;
			}
		}
		return isSorted;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void afterDetailEval() throws JRScriptletException {
		if (isSorted()) {
			// ignore first itaration over dataset, because data is not sorted
			// also we avoid duplication in this way
			Object obj = getParameterValue(JRParameter.REPORT_DATA_SOURCE);
			if (obj instanceof JRResultSetDataSource)
				return;
		}

		if (columns == null) {
			columns = (List<String>) getParameterValue(PARAM_COLUMNS);
		}
		if (listeners == null) {
			listeners = (List<DatasetReaderListener>) getParameterValue(PARAM_LISTENERS);
		}

		Object[] record = new Object[columns.size()];
		int i = 0;
		for (String col : columns) {
			record[i++] = getFieldValue(col);
		}

		for (DatasetReaderListener l : listeners) {
			if (!l.isValidStatus()) {
				// This "dirty" solution will stop report running
				throw new DataPreviewInterruptedException(Messages.DataPreviewScriptlet_InterruptErrorMsg);
			}
			l.newRecord(record);
		}

		super.afterDetailEval();
	}

}
