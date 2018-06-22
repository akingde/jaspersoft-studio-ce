/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.querydesigner.json;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.eclipse.jface.dialogs.MessageDialog;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.jaspersoft.studio.data.designer.tree.ISelectableNodes;
import com.jaspersoft.studio.data.messages.Messages;
import com.jaspersoft.studio.model.MRoot;
import com.jaspersoft.studio.model.datasource.json.JsonSupportNode;
import com.jaspersoft.studio.utils.ModelUtils;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.data.DataFile;
import net.sf.jasperreports.data.DataFileStream;
import net.sf.jasperreports.data.DataFileUtils;
import net.sf.jasperreports.data.json.JsonExpressionLanguageEnum;
import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.ParameterContributorContext;
import net.sf.jasperreports.engine.data.JsonDataSource;
import net.sf.jasperreports.engine.data.JsonQLDataSource;
import net.sf.jasperreports.engine.design.JRDesignField;
import net.sf.jasperreports.engine.json.JRJsonNode;
import net.sf.jasperreports.engine.util.JsonUtil;
import net.sf.jasperreports.engine.util.json.DefaultJsonQLExecuter;

/**
 * This class works with the specified Json data information. Usually this will
 * be read from an input file or an existing string.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public class JsonDataManager implements ISelectableNodes<JsonSupportNode> {
	private ObjectMapper mapper;
	private JsonNode jsonRoot;
	private MRoot jsonSupportModel;
	private Map<JsonSupportNode, JsonNode> jsonNodesMap;
	private String language;

	public JsonDataManager(String language) {
		this.language = language;
	}

	/**
	 * Tries to load a JSON tree structure using the specified data file and
	 * context.
	 * 
	 * @param dataFile
	 *            the resource information to get the JSON
	 * @param jconfig
	 *            the context
	 * @param jDataset
	 * @throws IOException
	 * @throws JRException
	 */
	public void loadJsonDataFile(DataFile dataFile, JasperReportsConfiguration jconfig, JRDataset jDataset)
			throws IOException, JRException {
		getJsonNodesMap().clear();
		DataFileStream ins = null;
		try {
			Map<String, Object> parameters = jconfig.getJRParameters();
			if (parameters == null) {
				parameters = new HashMap<>();
			}
			ParameterContributorContext paramContributorCtx = new ParameterContributorContext(jconfig, jDataset,
					parameters);
			
			
			ins = DataFileUtils.instance(paramContributorCtx).getDataStream(dataFile, parameters);
			jsonRoot = getJsonMapper().readTree(ins);
			buildJsonSupportTree();
		} finally {
			IOUtils.closeQuietly(ins);
		}
	}

	/**
	 * Tries to load a Json tree structure using the specified string content as
	 * input source.
	 * 
	 * @param jsonData
	 *            the string containing json data
	 * @throws IOException
	 * @throws JsonProcessingException
	 */
	public void loadJsonDataString(String jsonData) throws IOException {
		jsonRoot = getJsonMapper().readTree(jsonData);
		buildJsonSupportTree();
	}

	/*
	 * Returns the Json object mapper.
	 */
	private ObjectMapper getJsonMapper() {
		if (mapper == null) {
			mapper = JsonUtil.createObjectMapper();
		}
		return mapper;
	}

	/**
	 * @return the current json root node
	 */
	public JsonNode getJsonTreeRoot() {
		return jsonRoot;
	}

	/**
	 * @return the current support model root
	 */
	public MRoot getJsonSupportModel() {
		return jsonSupportModel;
	}

	/*
	 * Creates the support tree that uses ANodes.
	 */
	private void buildJsonSupportTree() {
		jsonSupportModel = new MRoot(null, null);
		List<JsonSupportNode> children = getChildrenJsonNodes(getJsonTreeRoot());
		for (JsonSupportNode c : children) {
			c.setParent(jsonSupportModel, -1);
		}
		if(children.isEmpty()) {
			UIUtils.getDisplay().asyncExec(() -> 
				MessageDialog.openWarning(UIUtils.getShell(), Messages.JsonDataManager_errorTitle, 
					Messages.JsonDataManager_errorMsg));
		}
	}

	/*
	 * Extract the children ANodes for a specified Json node.
	 */
	private List<JsonSupportNode> getChildrenJsonNodes(JsonNode jsonNode) {
		List<JsonSupportNode> children = new ArrayList<>();
		if (jsonNode.isArray() && jsonNode.equals(jsonRoot)) {
			// Assumption: consider the first element as a template
			jsonNode = jsonNode.get(0);
		}
		Iterator<String> fieldNames = jsonNode.fieldNames();
		while (fieldNames.hasNext()) {
			String name = fieldNames.next();
			JsonNode tmpNode = jsonNode.get(name);
			if (tmpNode.isObject()) {
				JsonSupportNode child = new JsonSupportNode();
				child.setNodeText(name);
				List<JsonSupportNode> innerChildren = getChildrenJsonNodes(tmpNode);
				for (JsonSupportNode innerChild : innerChildren) {
					innerChild.setParent(child, -1);
				}
				getJsonNodesMap().put(child, tmpNode);
				children.add(child);
			} else if (tmpNode.isArray()) {
				Iterator<JsonNode> elements = tmpNode.elements();
				while (elements.hasNext()) {
					JsonNode el = elements.next();
					JsonSupportNode child = new JsonSupportNode();
					child.setNodeText(name);
					List<JsonSupportNode> innerChildren = getChildrenJsonNodes(el);
					for (JsonSupportNode innerChild : innerChildren) {
						innerChild.setParent(child, -1);
					}
					getJsonNodesMap().put(child, el);
					children.add(child);
				}
			} else if (tmpNode.isValueNode()) {
				JsonSupportNode child = new JsonSupportNode();
				child.setNodeText(name);
				getJsonNodesMap().put(child, tmpNode);
				children.add(child);
			}
		}
		return children;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.data.querydesigner.ISelectableNodes#
	 * getSelectableNodes(java.lang.String)
	 */
	public List<JsonSupportNode> getSelectableNodes(String query) {
		List<JsonSupportNode> selectedList = new ArrayList<>();
		if (language.equalsIgnoreCase(JsonExpressionLanguageEnum.JSONQL.getName())) {
			JRJsonNode jrJsonNode = new JRJsonNode(null, jsonRoot);
			DefaultJsonQLExecuter jsonqlExec = new DefaultJsonQLExecuter();
			try {
				List<JRJsonNode> jrSelectedNodes = jsonqlExec.selectNodes(jrJsonNode, query);
				List<JsonNode> elementsList = new ArrayList<>();
				if (jrSelectedNodes != null) {
					for (JRJsonNode n : jrSelectedNodes) {
						elementsList.add(n.getDataNode());
					}
					for (JsonSupportNode sn : getJsonNodesMap().keySet()) {
						if (elementsList.contains(getJsonNodesMap().get(sn))) {
							selectedList.add(sn);
						}
					}
				}
			} catch (Exception e) {
				// Do not care about error in node selection
			}
		} else {
			JsonQueryHelper jsonQueryHelper = new JsonQueryHelper(mapper);
			try {
				JsonNode jsonData = jsonQueryHelper.getJsonData(jsonRoot, query);
				if (jsonData != null) {
					List<JsonNode> elementsList = new ArrayList<>();
					if (jsonData.isArray()) {
						Iterator<JsonNode> elements = jsonData.elements();
						while (elements.hasNext()) {
							elementsList.add(elements.next());
						}
					} else if (jsonData.isObject()) {
						elementsList.add(jsonData);
					}

					for (JsonSupportNode sn : getJsonNodesMap().keySet()) {
						if (elementsList.contains(getJsonNodesMap().get(sn))) {
							selectedList.add(sn);
						}
					}
				}
			} catch (Exception e) {
				// Do not care about error in node selection
			}
		}
		return selectedList;
	}

	/**
	 * Given a JSON selection query, extracts from the result set the list of
	 * possible fields that can be used in a report.
	 * 
	 * @param query
	 *            the JSON query text
	 * @return a list of fields
	 */
	public List<JRDesignField> extractFields(String query) {
		ArrayList<JRDesignField> result = new ArrayList<>();
		JsonNode jsonData = null;
		try {
			if (language.equalsIgnoreCase(JsonExpressionLanguageEnum.JSONQL.getName())) {
				JRJsonNode jrJsonNode = new JRJsonNode(null, jsonRoot);
				DefaultJsonQLExecuter jsonqlExec = new DefaultJsonQLExecuter();
				List<JRJsonNode> jrSelectedNodes = jsonqlExec.selectNodes(jrJsonNode, query);
				List<JsonNode> elementsList = new ArrayList<>();
				if (jrSelectedNodes != null) {
					for (JRJsonNode n : jrSelectedNodes) {
						elementsList.add(n.getDataNode());
					}
				}
				if (!elementsList.isEmpty()) {
					// FIXME - for now select the first found
					// we should find a good way to digg all possible ones
					jsonData = elementsList.get(0);
				}
			} else {
				JsonQueryHelper jsonQueryHelper = new JsonQueryHelper(mapper);
				jsonData = jsonQueryHelper.getJsonData(jsonRoot, query);
			}
		} catch (Exception e) {
			// Do not care about error in node selection
		}
		if (jsonData != null) {
			if (jsonData.isArray()) {
				return getFieldsFromArrayNode((ArrayNode) jsonData, result);
			} else if (jsonData.isObject()) {
				return getFieldsFromObjectNode((ObjectNode) jsonData, result);
			} else {
				return getFieldFromGenericJsonNode(jsonData, result);
			}
		}
		return result;
	}

	/*
	 * Gets a field from a generic JSON node.
	 */
	private List<JRDesignField> getFieldFromGenericJsonNode(JsonNode node, List<JRDesignField> fields) {
		JRDesignField f = new JRDesignField();
		f.setName(ModelUtils.getNameForField(fields, "node")); //$NON-NLS-1$
		String infoStr = "."; //$NON-NLS-1$
		if (language.equalsIgnoreCase(JsonExpressionLanguageEnum.JSONQL.getName())) {
			infoStr = "[0]"; //$NON-NLS-1$
		}
		f.setDescription(infoStr);
		f.getPropertiesMap().setProperty(getFieldExpressionName(), infoStr);
		f.setValueClass(String.class); // FIXME improve with type checking
		fields.add(f);
		return fields;
	}

	/*
	 * Gets the fields from a JSON node of type object.
	 */
	private List<JRDesignField> getFieldsFromObjectNode(ObjectNode node, List<JRDesignField> fields) {
		Iterator<String> fieldNames = node.fieldNames();
		while (fieldNames.hasNext()) {
			String name = fieldNames.next();
			JRDesignField f = new JRDesignField();
			f.setName(ModelUtils.getNameForField(fields, name));
			f.setDescription(name);
			f.getPropertiesMap().setProperty(getFieldExpressionName(), name);
			f.setValueClass(String.class);
			fields.add(f);
		}
		return fields;
	}

	/*
	 * Gets the fields from a JSON node of type array.
	 */
	private List<JRDesignField> getFieldsFromArrayNode(ArrayNode node, List<JRDesignField> fields) {
		// Assumption: consider the first element as template
		JsonNode firstEl = node.get(0);
		if (firstEl instanceof ObjectNode) {
			return getFieldsFromObjectNode((ObjectNode) firstEl, fields);
		} else if (firstEl instanceof ArrayNode) {
			return getFieldsFromArrayNode((ArrayNode) firstEl, fields);
		} else {
			return getFieldFromGenericJsonNode(firstEl, fields);
		}
	}

	private String getFieldExpressionName() {
		if (JsonExpressionLanguageEnum.JSONQL.getName().equalsIgnoreCase(language)) {
			return JsonQLDataSource.PROPERTY_FIELD_EXPRESSION;
		} else {
			return JsonDataSource.PROPERTY_FIELD_EXPRESSION;
		}
	}

	public Map<JsonSupportNode, JsonNode> getJsonNodesMap() {
		if (this.jsonNodesMap == null) {
			this.jsonNodesMap = new HashMap<>();
		}
		return jsonNodesMap;
	}

	public String getQueryExpression(String existingQuery, JsonSupportNode selectedNode) {
		String absoluteQuery = getAbsoluteQueryExpression(selectedNode);
		if (existingQuery != null && absoluteQuery.startsWith(existingQuery)
				&& absoluteQuery.length() > existingQuery.length()) {
			// consider also an additional . selector
			int qLength = existingQuery.length();
			return absoluteQuery.substring(qLength + Math.min(qLength, 1));
		}
		return absoluteQuery;
	}

	private String getAbsoluteQueryExpression(JsonSupportNode selectedNode) {
		StringBuilder querysb = new StringBuilder();
		querysb.insert(0, selectedNode.getNodeText());
		JsonSupportNode tmpNode = selectedNode;
		while (tmpNode.getParent() != null && !(tmpNode.getParent() instanceof MRoot)) {
			tmpNode = (JsonSupportNode) tmpNode.getParent();
			querysb.insert(0, tmpNode.getNodeText() + "."); //$NON-NLS-1$
		}
		return querysb.toString();
	}
}
