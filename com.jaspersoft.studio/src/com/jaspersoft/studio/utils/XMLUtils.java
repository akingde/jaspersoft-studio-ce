package com.jaspersoft.studio.utils;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class XMLUtils {
	public static Document parseNoValidation(InputStream io) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setValidating(false);
		dbf.setIgnoringComments(true);
		dbf.setFeature("http://xml.org/sax/features/namespaces", false);
		dbf.setFeature("http://xml.org/sax/features/validation", false);
		dbf.setFeature("http://apache.org/xml/features/nonvalidating/load-dtd-grammar", false);
		dbf.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);

		DocumentBuilder db = dbf.newDocumentBuilder();

		return db.parse(io);
	}
}
