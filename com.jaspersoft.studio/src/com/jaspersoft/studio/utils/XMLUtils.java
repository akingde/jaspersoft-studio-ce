/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.utils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import net.sf.jasperreports.data.xml.XmlDataAdapter;
import net.sf.jasperreports.eclipse.util.FileUtils;
import net.sf.jasperreports.engine.design.JasperDesign;

public class XMLUtils {
	private static DocumentBuilder db;
	
	private XMLUtils() {
	}

	public static Document parseNoValidation(InputStream io) throws ParserConfigurationException, SAXException,
			IOException {
		return getDocumentBuilder().parse(io);
	}

	public static Document parseNoValidation(String xml) throws ParserConfigurationException, SAXException, IOException {
		Document doc = null;
		try (ByteArrayInputStream is = new ByteArrayInputStream(xml.getBytes(FileUtils.UTF8_ENCODING));) {
			doc = getDocumentBuilder().parse(is);
			return doc;
		}
	}

	private static DocumentBuilder getDocumentBuilder() throws ParserConfigurationException {
		if (db == null) {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			dbf.setValidating(false);
			dbf.setIgnoringComments(true);
			dbf.setFeature("http://xml.org/sax/features/namespaces", false);
			dbf.setFeature("http://xml.org/sax/features/validation", false);
			dbf.setFeature("http://apache.org/xml/features/nonvalidating/load-dtd-grammar", false);
			dbf.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);

			db = dbf.newDocumentBuilder();
		}
		return db;
	}

	public static boolean isNamespaceAware(XmlDataAdapter xmlDataAdapter, JasperDesign jdesign) {
		if (xmlDataAdapter != null) {
			return xmlDataAdapter.isNamespaceAware();
		}
		if (jdesign != null) {
			String detectNamespaces = jdesign.getProperty("net.sf.jasperreports.xml.detect.namespaces");
			return (detectNamespaces != null && "true".equals(detectNamespaces));
		}
		return false;
	}
}
