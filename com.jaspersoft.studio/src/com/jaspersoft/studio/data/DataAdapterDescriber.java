package com.jaspersoft.studio.data;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;

import org.eclipse.core.runtime.content.IContentDescription;
import org.eclipse.core.runtime.content.XMLContentDescriber;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.jaspersoft.studio.utils.XMLUtils;

public class DataAdapterDescriber extends XMLContentDescriber {
	@Override
	public int describe(InputStream in, IContentDescription description) throws IOException {
		try {
			Document document = XMLUtils.parseNoValidation(in);
			String adapterClassName = document.getDocumentElement().getAttribute("class");
			if (adapterClassName != null && !adapterClassName.isEmpty()) {
				DataAdapterFactory factory = DataAdapterManager.findFactoryByDataAdapterClass(adapterClassName);
				if (factory != null)
					return VALID;
			}
		} catch (ParserConfigurationException e) {
		} catch (SAXException e) {
		}

		return INVALID;
	}
}
