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
package com.jaspersoft.studio.data.storage;

import java.io.StringReader;

import net.sf.jasperreports.data.DataAdapter;
import net.sf.jasperreports.data.empty.EmptyDataAdapterImpl;
import net.sf.jasperreports.engine.util.JRXmlUtils;
import net.sf.jasperreports.util.CastorUtil;

import org.eclipse.core.runtime.Status;
import org.osgi.service.prefs.BackingStoreException;
import org.osgi.service.prefs.Preferences;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.data.DataAdapterFactory;
import com.jaspersoft.studio.data.DataAdapterManager;
import com.jaspersoft.studio.data.empty.EmptyDataAdapterDescriptor;
import com.jaspersoft.studio.data.empty.EmptyDataAdapterFactory;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.preferences.util.PropertiesHelper;

public class PreferencesDataAdapterStorage extends ADataAdapterStorage {
	private static final String PREF_KEYS_DATA_ADAPTERS = "dataAdapters";//$NON-NLS-1$
	private Preferences prefs;
	private int ID = 0;

	public PreferencesDataAdapterStorage() {
		prefs = PropertiesHelper.INSTANCE_SCOPE.getNode(JaspersoftStudioPlugin.getUniqueIdentifier());
	}

	@Override
	public void addDataAdapter(String url, DataAdapterDescriptor adapter) {
		super.addDataAdapter(url.isEmpty() ? getNewID() : url, adapter);
		save(url, adapter);
	}

	private String getNewID() {
		return "" + ID++;
	}

	@Override
	public void findAll() {
		String xml = prefs.get(PREF_KEYS_DATA_ADAPTERS, null);
		if (xml != null) {
			try {
				Document document = JRXmlUtils.parse(new InputSource(new StringReader(xml)));

				NodeList adapterNodes = document.getDocumentElement().getChildNodes();// .getElementsByTagName("dataAdapter");

				for (int i = 0; i < adapterNodes.getLength(); ++i) {
					Node adapterNode = adapterNodes.item(i);

					if (adapterNode.getNodeType() == Node.ELEMENT_NODE) {
						// 1. Find out the class of this data adapter...
						String adapterClassName = adapterNode.getAttributes().getNamedItem("class").getNodeValue(); //$NON-NLS-1$

						DataAdapterFactory factory = DataAdapterManager.findFactoryByDataAdapterClass(adapterClassName);

						if (factory == null) {
							// we should at least log a warning here....
							JaspersoftStudioPlugin
									.getInstance()
									.getLog()
									.log(
											new Status(Status.WARNING, JaspersoftStudioPlugin.getUniqueIdentifier(), Status.OK,
													Messages.DataAdapterManager_nodataadapterfound + adapterClassName, null));
							continue;
						}

						DataAdapterDescriptor dataAdapterDescriptor = factory.createDataAdapter();

						DataAdapter dataAdapter = dataAdapterDescriptor.getDataAdapter();

						dataAdapter = (DataAdapter) CastorUtil.read(adapterNode, dataAdapter.getClass());

						dataAdapterDescriptor.setDataAdapter(dataAdapter);

						addDataAdapter(getNewID(), dataAdapterDescriptor);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (daDescriptors.isEmpty()) {
			EmptyDataAdapterFactory edaf = new EmptyDataAdapterFactory();
			EmptyDataAdapterDescriptor edad = edaf.createDataAdapter();
			EmptyDataAdapterImpl dataAdapter = new EmptyDataAdapterImpl();
			dataAdapter.setName(Messages.DataAdapterManager_oneemptyrecord);
			dataAdapter.setRecordCount(1);
			edad.setDataAdapter(dataAdapter);
			addDataAdapter(getNewID(), edad);
		}
	}

	@Override
	public void save(String url, DataAdapterDescriptor adapter) {
		try {
			StringBuffer xml = new StringBuffer();
			xml.append("<dataAdapters>\n"); //$NON-NLS-1$
			for (DataAdapterDescriptor desc : getDataAdapterDescriptors()) {
				xml.append(desc.toXml());
			}
			xml.append("</dataAdapters>"); //$NON-NLS-1$

			prefs.put("dataAdapters", xml.toString()); //$NON-NLS-1$ 
			prefs.flush();
		} catch (BackingStoreException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(String url) {
		save(url, null);
	}

}
