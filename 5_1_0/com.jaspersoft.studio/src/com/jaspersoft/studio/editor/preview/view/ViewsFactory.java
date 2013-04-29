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
package com.jaspersoft.studio.editor.preview.view;

import java.util.LinkedHashMap;

import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.editor.preview.view.report.file.CSVMetadataViewer;
import com.jaspersoft.studio.editor.preview.view.report.file.CSVViewer;
import com.jaspersoft.studio.editor.preview.view.report.file.TXTViewer;
import com.jaspersoft.studio.editor.preview.view.report.file.XMLImagesViewer;
import com.jaspersoft.studio.editor.preview.view.report.file.XMLViewer;
import com.jaspersoft.studio.editor.preview.view.report.html.HTMLViewer;
import com.jaspersoft.studio.editor.preview.view.report.html.LayeredHTMLViewer;
import com.jaspersoft.studio.editor.preview.view.report.html.XHTMLViewer;
import com.jaspersoft.studio.editor.preview.view.report.swt.SWTViewer;
import com.jaspersoft.studio.editor.preview.view.report.system.DocxViewer;
import com.jaspersoft.studio.editor.preview.view.report.system.ExcelAPIViewer;
import com.jaspersoft.studio.editor.preview.view.report.system.OdsViewer;
import com.jaspersoft.studio.editor.preview.view.report.system.OdtViewer;
import com.jaspersoft.studio.editor.preview.view.report.system.PdfViewer;
import com.jaspersoft.studio.editor.preview.view.report.system.PowerPointViewer;
import com.jaspersoft.studio.editor.preview.view.report.system.RTFViewer;
import com.jaspersoft.studio.editor.preview.view.report.system.XlsViewer;
import com.jaspersoft.studio.editor.preview.view.report.system.XlsxViewer;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class ViewsFactory {
	public static final String VIEWER_JAVA = "Java";

	public static LinkedHashMap<String, APreview> createPreviews(Composite composite, JasperReportsConfiguration jContext) {
		LinkedHashMap<String, APreview> pmap = new LinkedHashMap<String, APreview>();
		pmap.put(VIEWER_JAVA, new SWTViewer(composite, jContext));

		pmap.put("SEPARATOR1", null);

		pmap.put("Layered HTML", new LayeredHTMLViewer(composite, jContext));
		pmap.put("HTML", new HTMLViewer(composite, jContext));
		pmap.put("xHTML", new XHTMLViewer(composite, jContext));
		pmap.put("SEPARATOR1", null);
		pmap.put("SEPARATOR1", null);
		pmap.put("PDF", new PdfViewer(composite, jContext));

		pmap.put("SEPARATOR2", null);

		pmap.put("RTF", new RTFViewer(composite, jContext));
		pmap.put("DOCx", new DocxViewer(composite, jContext));
		pmap.put("ODT", new OdtViewer(composite, jContext));
		pmap.put("ODS", new OdsViewer(composite, jContext));
		pmap.put("PPTx", new PowerPointViewer(composite, jContext));
		pmap.put("Text", new TXTViewer(composite, jContext));

		pmap.put("SEPARATOR3", null);

		pmap.put("XLS", new XlsViewer(composite, jContext));
		pmap.put("XLSx", new XlsxViewer(composite, jContext));
		pmap.put("ExcelAPI", new ExcelAPIViewer(composite, jContext));
		pmap.put("CSV", new CSVViewer(composite, jContext));
		pmap.put("CSV Metadata", new CSVMetadataViewer(composite, jContext));

		pmap.put("SEPARATOR4", null);

		pmap.put("XML", new XMLViewer(composite, jContext));
		pmap.put("XML With Images", new XMLImagesViewer(composite, jContext));

		return pmap;
	}

}
