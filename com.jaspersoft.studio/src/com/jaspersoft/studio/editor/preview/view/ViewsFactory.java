/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2010 Jaspersoft Corporation. All
 * rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of Jaspersoft Open Studio.
 * 
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify it under the terms of the GNU Affero
 * General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * Jaspersoft Open Studio is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with Jaspersoft Open Studio. If not,
 * see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.editor.preview.view;

import java.util.LinkedHashMap;

import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.editor.preview.view.report.html.CSVMetadataViewer;
import com.jaspersoft.studio.editor.preview.view.report.html.CSVViewer;
import com.jaspersoft.studio.editor.preview.view.report.html.DocxViewer;
import com.jaspersoft.studio.editor.preview.view.report.html.ExcelAPIViewer;
import com.jaspersoft.studio.editor.preview.view.report.html.HTMLViewer;
import com.jaspersoft.studio.editor.preview.view.report.html.OdsViewer;
import com.jaspersoft.studio.editor.preview.view.report.html.OdtViewer;
import com.jaspersoft.studio.editor.preview.view.report.html.PdfViewer;
import com.jaspersoft.studio.editor.preview.view.report.html.PowerPointViewer;
import com.jaspersoft.studio.editor.preview.view.report.html.RTFViewer;
import com.jaspersoft.studio.editor.preview.view.report.html.TXTViewer;
import com.jaspersoft.studio.editor.preview.view.report.html.XHTMLViewer;
import com.jaspersoft.studio.editor.preview.view.report.html.XlsViewer;
import com.jaspersoft.studio.editor.preview.view.report.html.XlsxViewer;
import com.jaspersoft.studio.editor.preview.view.report.swt.SWTViewer;
import com.jaspersoft.studio.preferences.util.PropertiesHelper;

public class ViewsFactory {
	public static final String VIEWER_JAVA = "Java";

	public static LinkedHashMap<String, APreview> createPreviews(Composite composite, PropertiesHelper ph) {
		LinkedHashMap<String, APreview> pmap = new LinkedHashMap<String, APreview>();
		pmap.put(VIEWER_JAVA, new SWTViewer(composite, ph));

		pmap.put("HTML", new HTMLViewer(composite, ph));
		pmap.put("xHTML", new XHTMLViewer(composite, ph));

		pmap.put("PDF", new PdfViewer(composite, ph));

		pmap.put("SEPARATOR1", null);

		pmap.put("RTF", new RTFViewer(composite, ph));
		pmap.put("Docx", new DocxViewer(composite, ph));
		pmap.put("Odt", new OdtViewer(composite, ph));
		pmap.put("Ods", new OdsViewer(composite, ph));
		pmap.put("Power Point", new PowerPointViewer(composite, ph));
		pmap.put("Text", new TXTViewer(composite, ph));

		pmap.put("SEPARATOR2", null);

		pmap.put("XLS", new XlsViewer(composite, ph));
		pmap.put("XLSx", new XlsxViewer(composite, ph));
		pmap.put("ExcelAPI", new ExcelAPIViewer(composite, ph));
		pmap.put("CSV", new CSVViewer(composite, ph));
		pmap.put("CSV Metadata", new CSVMetadataViewer(composite, ph));

		return pmap;
	}

}
