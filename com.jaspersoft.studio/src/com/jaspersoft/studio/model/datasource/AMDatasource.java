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
package com.jaspersoft.studio.model.datasource;

import java.io.StringWriter;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.xerces.dom.DocumentImpl;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.property.descriptor.text.NTextPropertyDescriptor;
import com.jaspersoft.studio.repository.RepositoryManager;

public abstract class AMDatasource extends APropertyNode {
	public AMDatasource(ANode parent, int index) {
		super(parent, index);
		setValue(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.INode#getDisplayText()
	 */
	@Override
	public String getDisplayText() {
		return (String) getPropertyValue(AMDatasource.PROPERTY_NAME);
	}

	private static IPropertyDescriptor[] descriptors;
	private static Map<String, Object> defaultsMap;

	@Override
	public Map<String, Object> getDefaultsMap() {
		return defaultsMap;
	}

	@Override
	public IPropertyDescriptor[] getDescriptors() {
		return descriptors;
	}

	@Override
	public void setDescriptors(IPropertyDescriptor[] descriptors1, Map<String, Object> defaultsMap1) {
		descriptors = descriptors1;
		defaultsMap = defaultsMap1;
	}

	@Override
	public void createPropertyDescriptors(List<IPropertyDescriptor> desc, Map<String, Object> defaultsMap) {

		NTextPropertyDescriptor textD = new NTextPropertyDescriptor(PROPERTY_NAME, "Datasource Name");
		desc.add(textD);

		defaultsMap.put(PROPERTY_NAME, RepositoryManager.checkDataSourceName("DataSource"));
	}

	public static final String PROPERTY_NAME = "PROPERTY_NAME";
	private String name;

	@Override
	public Object getPropertyValue(Object id) {
		if (id.equals(PROPERTY_NAME)) {
			return name;
		}
		return null;
	}

	@Override
	public void setPropertyValue(Object id, Object value) {
		if (id.equals(PROPERTY_NAME)) {
			name = (String) value;
		}

	}

	public AMDatasource getCopy() {
		AMDatasource r = null;
		try {
			r = this.getClass().newInstance();
			IPropertyDescriptor[] d = r.getPropertyDescriptors();
			for (int i = 0; i < d.length; i++) {
				r.setPropertyValue(d[i].getId(), this.getPropertyValue(d[i].getId()));
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return r;
	}

	public String getToString() throws ParserConfigurationException, TransformerException {
		// DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		// DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
		// DOMImplementation impl = docBuilder.getDOMImplementation();
		Document doc = new DocumentImpl();
		Element root = doc.createElement("DATASOURCE");
		root.setAttributeNS(null, "type", this.getClass().getName());

		IPropertyDescriptor[] desc = getPropertyDescriptors();
		for (int i = 0; i < desc.length; i++) {
			String id = (String) desc[i].getId();
			Object propertyValue = this.getPropertyValue(desc[i].getId());
			if (propertyValue instanceof String || propertyValue instanceof Integer || propertyValue instanceof Character
					|| propertyValue instanceof Boolean) {
				Element e = doc.createElementNS(null, id);
				Node n = doc.createTextNode(propertyValue.toString());
				e.appendChild(n);
				root.appendChild(e);
			}
		}
		doc.appendChild(root);

		StringWriter out = new StringWriter();
		Transformer serializer = TransformerFactory.newInstance().newTransformer();
		serializer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
		serializer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
		serializer.setOutputProperty(OutputKeys.INDENT, "yes");
		serializer.transform(new DOMSource(doc), new StreamResult(out));
		String res = out.toString();
		return res;
	}
}
