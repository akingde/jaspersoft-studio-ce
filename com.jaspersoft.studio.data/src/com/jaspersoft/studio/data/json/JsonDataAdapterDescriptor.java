/*******************************************************************************
 * Copyright (C) 2005 - 2012 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License  as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero  General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package com.jaspersoft.studio.data.json;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.data.DataAdapter;
import net.sf.jasperreports.data.DataAdapterService;
import net.sf.jasperreports.data.json.JsonDataAdapter;
import net.sf.jasperreports.data.json.JsonDataAdapterImpl;
import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.design.JRDesignField;

import org.codehaus.jackson.JsonProcessingException;
import org.eclipse.swt.graphics.Image;

import com.jaspersoft.studio.data.Activator;
import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.data.DataAdapterEditor;
import com.jaspersoft.studio.data.fields.IFieldsProvider;
import com.jaspersoft.studio.data.querydesigner.json.JsonDataManager;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.datasource.json.JsonSupportNode;
import com.jaspersoft.studio.utils.ModelUtils;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class JsonDataAdapterDescriptor extends DataAdapterDescriptor implements IFieldsProvider {
	private JsonDataAdapterImpl jsonDataAdapter = new JsonDataAdapterImpl();

	@Override
	public JsonDataAdapter getDataAdapter() {
		return jsonDataAdapter;
	}

	@Override
	public void setDataAdapter(DataAdapter dataAdapter) {
		this.jsonDataAdapter = (JsonDataAdapterImpl) dataAdapter;
	}

	@Override
	public DataAdapterEditor getEditor() {
		return new JsonDataAdapterEditor();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.data.DataAdapterFactory#getIcon(int)
	 */
	@Override
	public Image getIcon(int size) {
		if (size == 16) {
			return Activator.getImage("icons/json.png");
		}
		return null;
	}

	@Override
	public boolean supportsGetFieldsOperation(JasperReportsConfiguration jConfig) {
		return true;
	}

	@Override
	public List<JRDesignField> getFields(DataAdapterService con,
			JasperReportsConfiguration jConfig, JRDataset jDataset)
			throws JRException, UnsupportedOperationException {
		List<JRDesignField> fields=new ArrayList<JRDesignField>();
		Throwable err=null;
		try {
			JsonDataManager m=new JsonDataManager();
			m.loadJsonDataFile(jsonDataAdapter.getFileName());
			List<JsonSupportNode> selectableNodes = m.getSelectableNodes(jDataset.getQuery().getText());
			if(!selectableNodes.isEmpty()){
				// Basic idea: consider the first element node as template
				JsonSupportNode jsonSupportNode = selectableNodes.get(0);
				for(INode node : jsonSupportNode.getChildren()){
					String name = ((JsonSupportNode)node).getNodeText();
					JRDesignField f=new JRDesignField();
					f.setName(ModelUtils.getNameForField(fields, name));
					f.setDescription(name);
					f.setValueClass(String.class);
					fields.add(f);
				}
			}
		} catch (JsonProcessingException e) {
			err=e;
		} catch (IOException e) {
			err=e;
		} 
		if(err!=null){
			throw new JRException(err);
		}
		
		return fields;
	}
}
