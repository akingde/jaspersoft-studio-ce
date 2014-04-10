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

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;

import net.sf.jasperreports.engine.JRConstants;

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
import com.jaspersoft.studio.editor.preview.view.report.system.XlsMetadataViewer;
import com.jaspersoft.studio.editor.preview.view.report.system.XlsViewer;
import com.jaspersoft.studio.editor.preview.view.report.system.XlsxViewer;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class ViewsFactory {
	public static final String VIEWER_JAVA = "Java";
	private static LinkedHashMap<String, Class<? extends APreview>> pcmap = new LinkedHashMap<String, Class<? extends APreview>>();
	static {
		pcmap.put(VIEWER_JAVA, SWTViewer.class);

		pcmap.put("SEPARATOR1", null);

		pcmap.put("Layered HTML", LayeredHTMLViewer.class);
		pcmap.put("HTML", HTMLViewer.class);
		pcmap.put("xHTML", XHTMLViewer.class);
		pcmap.put("SEPARATOR1", null);
		pcmap.put("SEPARATOR1", null);
		pcmap.put("PDF", PdfViewer.class);

		pcmap.put("SEPARATOR2", null);

		pcmap.put("RTF", RTFViewer.class);
		pcmap.put("DOCx", DocxViewer.class);
		pcmap.put("ODT", OdtViewer.class);
		pcmap.put("ODS", OdsViewer.class);
		pcmap.put("PPTx", PowerPointViewer.class);
		pcmap.put("Text", TXTViewer.class);

		pcmap.put("SEPARATOR3", null);

		pcmap.put("XLS", XlsViewer.class);
		pcmap.put("XLS Metadata", XlsMetadataViewer.class);
		pcmap.put("XLSx", XlsxViewer.class);
		pcmap.put("ExcelAPI", ExcelAPIViewer.class);
		pcmap.put("CSV", CSVViewer.class);
		pcmap.put("CSV Metadata", CSVMetadataViewer.class);

		pcmap.put("SEPARATOR4", null);

		pcmap.put("XML", XMLViewer.class);
		pcmap.put("XML With Images", XMLImagesViewer.class);
	}

	public static LinkedHashMap<String, APreview> createPreviews(final Composite composite,
			final JasperReportsConfiguration jContext) {
		LinkedHashMap<String, APreview> pmap = new LinkedHashMap<String, APreview>() {
			public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

			@Override
			public APreview get(Object key) {
				APreview val = super.get(key);
				if (val == null && pcmap.get(key) != null) {
					Class<? extends APreview> clazz = pcmap.get(key);
					try {
						val = clazz.getConstructor(Composite.class, JasperReportsConfiguration.class).newInstance(composite,
								jContext);
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (SecurityException e) {
						e.printStackTrace();
					} catch (InstantiationException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					} catch (NoSuchMethodException e) {
						e.printStackTrace();
					}
					put((String) key, val);
				}
				return val;
			}
		};
		for (String key : pcmap.keySet())
			pmap.put(key, null);

		// pmap.put(VIEWER_JAVA, new SWTViewer(composite, jContext));
		//
		// pmap.put("SEPARATOR1", null);
		//
		// pmap.put("Layered HTML", new LayeredHTMLViewer(composite, jContext));
		// pmap.put("HTML", new HTMLViewer(composite, jContext));
		// pmap.put("xHTML", new XHTMLViewer(composite, jContext));
		// pmap.put("SEPARATOR1", null);
		// pmap.put("SEPARATOR1", null);
		// pmap.put("PDF", new PdfViewer(composite, jContext));
		//
		// pmap.put("SEPARATOR2", null);
		//
		// pmap.put("RTF", new RTFViewer(composite, jContext));
		// pmap.put("DOCx", new DocxViewer(composite, jContext));
		// pmap.put("ODT", new OdtViewer(composite, jContext));
		// pmap.put("ODS", new OdsViewer(composite, jContext));
		// pmap.put("PPTx", new PowerPointViewer(composite, jContext));
		// pmap.put("Text", new TXTViewer(composite, jContext));
		//
		// pmap.put("SEPARATOR3", null);
		//
		// pmap.put("XLS", new XlsViewer(composite, jContext));
		// pmap.put("XLS Metadata", new XlsMetadataViewer(composite, jContext));
		// pmap.put("XLSx", new XlsxViewer(composite, jContext));
		// pmap.put("ExcelAPI", new ExcelAPIViewer(composite, jContext));
		// pmap.put("CSV", new CSVViewer(composite, jContext));
		// pmap.put("CSV Metadata", new CSVMetadataViewer(composite, jContext));
		//
		// pmap.put("SEPARATOR4", null);
		//
		// pmap.put("XML", new XMLViewer(composite, jContext));
		// pmap.put("XML With Images", new XMLImagesViewer(composite, jContext));

		return pmap;
	}

}
