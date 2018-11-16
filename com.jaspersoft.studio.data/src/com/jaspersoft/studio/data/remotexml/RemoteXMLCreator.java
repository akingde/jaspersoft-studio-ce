/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.remotexml;

import java.util.Locale;
import java.util.TimeZone;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.data.adapter.IDataAdapterCreator;

import net.sf.jasperreports.data.xml.XmlDataAdapterImpl;
import net.sf.jasperreports.eclipse.util.DataFileUtils;

/**
 * Creator to build a JSS Remote XML data adapter from the xml definition of an
 * iReport Remote XML data adapter
 * 
 * @author Orlandin Marco
 */
public class RemoteXMLCreator implements IDataAdapterCreator {

	@Override
	public DataAdapterDescriptor buildFromXML(Document docXML) {
		XmlDataAdapterImpl result = new XmlDataAdapterImpl();

		NamedNodeMap rootAttributes = docXML.getChildNodes().item(0).getAttributes();
		String connectionName = rootAttributes.getNamedItem("name").getTextContent();
		result.setName(connectionName);

		NodeList children = docXML.getChildNodes().item(0).getChildNodes();
		String localeVariant = null;
		String localeLanguage = null;
		String localeCountry = null;
		for (int i = 0; i < children.getLength(); i++) {
			Node node = children.item(i);
			if (node.getNodeName().equals("connectionParameter")) {
				String paramName = node.getAttributes().getNamedItem("name").getTextContent();
				String textContent = node.getTextContent();
				if (paramName.equals("Locale_country")) {
					localeCountry = textContent;
				}
				if (paramName.equals("Locale_variant")) {
					localeVariant = textContent;
				}
				if (paramName.equals("Locale_language")) {
					localeLanguage = textContent;
				}
				if (paramName.equals("timeZone")) {
					result.setTimeZone(TimeZone.getTimeZone(textContent));
				}
				if (paramName.equals("NumberPattern")) {
					result.setNumberPattern(textContent);
				}
				if (paramName.equals("UseConnection")) {
					result.setUseConnection(textContent.equals("true"));
				}
				if (paramName.equals("Filename")) {
					result.setDataFile(DataFileUtils.getDataFile(textContent));
				}
				if (paramName.equals("DatePattern")) {
					result.setDatePattern(textContent);
				}
				if (paramName.equals("SelectExpression")) {
					result.setSelectExpression(textContent);
				}
			}
		}

		if (localeCountry != null && localeLanguage != null) {
			Locale locale = new Locale(localeLanguage, localeCountry, localeVariant);
			result.setLocale(locale);
		}
		RemoteXMLDataAdapterDescriptor desc = new RemoteXMLDataAdapterDescriptor();
		desc.setDataAdapter(result);
		return desc;
	}

	@Override
	public String getID() {
		return "com.jaspersoft.jrx.JRXMLDataSourceConnection";
	}

}
