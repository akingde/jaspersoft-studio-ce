/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.server.editor;

import java.util.LinkedHashMap;
import java.util.Set;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.Argument;
import com.jaspersoft.studio.editor.preview.view.APreview;
import com.jaspersoft.studio.editor.preview.view.AViewsFactory;
import com.jaspersoft.studio.editor.preview.view.report.file.CSVViewer;
import com.jaspersoft.studio.editor.preview.view.report.file.XMLViewer;
import com.jaspersoft.studio.editor.preview.view.report.html.HTMLViewer;
import com.jaspersoft.studio.editor.preview.view.report.swt.SWTViewer;
import com.jaspersoft.studio.editor.preview.view.report.system.DocxViewer;
import com.jaspersoft.studio.editor.preview.view.report.system.OdsViewer;
import com.jaspersoft.studio.editor.preview.view.report.system.PdfViewer;
import com.jaspersoft.studio.editor.preview.view.report.system.RTFViewer;
import com.jaspersoft.studio.editor.preview.view.report.system.XlsViewer;
import com.jaspersoft.studio.editor.preview.view.report.system.XlsxViewer;

public class ReportUnitViewsFactory extends AViewsFactory {
	public static final String DEFAULT = Argument.RUN_OUTPUT_FORMAT_JRPRINT;

	private static LinkedHashMap<String, Class<? extends APreview>> pcmap = new LinkedHashMap<String, Class<? extends APreview>>();
	static {
		pcmap.put(Argument.RUN_OUTPUT_FORMAT_JRPRINT, SWTViewer.class);

		pcmap.put("SEPARATOR1", null);

		pcmap.put(Argument.RUN_OUTPUT_FORMAT_HTML, HTMLViewer.class);
		pcmap.put("SEPARATOR1", null);
		pcmap.put(Argument.RUN_OUTPUT_FORMAT_PDF, PdfViewer.class);

		pcmap.put("SEPARATOR2", null);

		pcmap.put(Argument.RUN_OUTPUT_FORMAT_RTF, RTFViewer.class);
		pcmap.put(Argument.RUN_OUTPUT_FORMAT_DOCX, DocxViewer.class);
		// pcmap.put("ODT", OdtViewer.class);
		pcmap.put(Argument.RUN_OUTPUT_FORMAT_ODS, OdsViewer.class);
		// pcmap.put("PPTx", PowerPointViewer.class);
		// pcmap.put("Text", TXTViewer.class);

		pcmap.put("SEPARATOR3", null);

		pcmap.put(Argument.RUN_OUTPUT_FORMAT_XLS, XlsViewer.class);
		pcmap.put(Argument.RUN_OUTPUT_FORMAT_XLSX, XlsxViewer.class);
		pcmap.put(Argument.RUN_OUTPUT_FORMAT_CSV, CSVViewer.class);

		pcmap.put("SEPARATOR4", null);

		pcmap.put(Argument.RUN_OUTPUT_FORMAT_XML, XMLViewer.class);
	}

	@Override
	public String getLabel(String key) {
		if (key.equals(DEFAULT))
			return "Java";
		return super.getLabel(key);
	}

	/**
	 * Return the available keys for the preview area, may contains separator
	 */
	public Set<String> getKeys() {
		return pcmap.keySet();
	}

	@Override
	protected LinkedHashMap<String, Class<? extends APreview>> getMap() {
		return pcmap;
	}

}
