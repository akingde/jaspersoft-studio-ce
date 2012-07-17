package com.jaspersoft.studio.data.querydesigner.json;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.jaspersoft.studio.data.designer.tree.ISelectableNodes;
import com.jaspersoft.studio.model.MRoot;
import com.jaspersoft.studio.model.datasource.json.JsonSupportNode;

/**
 * This class works with the specified Json data information.
 * Usually this will be read from an input file or an existing string.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public class JsonDataManager implements ISelectableNodes<JsonSupportNode> {
	private ObjectMapper mapper;
	private JsonNode jsonRoot;
	private MRoot jsonSupportModel;
	private Map<JsonSupportNode, JsonNode> jsonNodesMap;

	/**
	 * Tries to load a Json tree structure using the 
	 * specified stream as input source.
	 * 
	 * @param filename the name of the json file
	 * @throws JsonProcessingException
	 * @throws IOException
	 */
	public void loadJsonDataFile(String filename) throws JsonProcessingException, IOException {
		getJsonNodesMap().clear();
		FileInputStream ins=new FileInputStream(filename);
		jsonRoot=getJsonMapper().readTree(ins);
		ins.close();
		buildJsonSupportTree();
	}
	
	/**
	 * Tries to load a Json tree structure using the 
	 * specified string content as input source.
	 * 
	 * @param jsonData the string containing json data
	 * @throws IOException 
	 * @throws JsonProcessingException 
	 */
	public void loadJsonDataString(String jsonData) throws JsonProcessingException, IOException{
		jsonRoot=getJsonMapper().readTree(jsonData);
		buildJsonSupportTree();
	}
	
	/*
	 * Returns the Json object mapper.
	 */
	private ObjectMapper getJsonMapper() {
		if(mapper==null){
			mapper = new ObjectMapper();
			mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
			mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
			mapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
		}
		return mapper;
	}
	
	/**
	 * @return the current json root node
	 */
	public JsonNode getJsonTreeRoot(){
		return jsonRoot;
	}
	
	/**
	 * @return the current support model root
	 */
	public MRoot getJsonSupportModel(){
		return jsonSupportModel;
	}
	
	/*
	 * Creates the support tree that uses ANodes.
	 */
	private void buildJsonSupportTree(){
		jsonSupportModel=new MRoot(null, null);
		List<JsonSupportNode> children=getChildrenJsonNodes(getJsonTreeRoot());
		for(JsonSupportNode c : children){
			c.setParent(jsonSupportModel, -1);
		}
	}

	/*
	 * Extract the children ANodes for a specified Json node.
	 */
	private List<JsonSupportNode> getChildrenJsonNodes(JsonNode jsonNode) {
		List<JsonSupportNode> children=new ArrayList<JsonSupportNode>();
		Iterator<String> fieldNames = jsonNode.getFieldNames();
		while(fieldNames.hasNext()){
			String name = fieldNames.next();
			JsonNode tmpNode = jsonNode.get(name);
			if(tmpNode.isObject()){
				JsonSupportNode child=new JsonSupportNode();
				child.setNodeText(name);
				List<JsonSupportNode> innerChildren=getChildrenJsonNodes(tmpNode);
				for(JsonSupportNode innerChild : innerChildren){
					innerChild.setParent(child, -1);
				}
				getJsonNodesMap().put(child, tmpNode);
				children.add(child);
			}
			else if(tmpNode.isArray()){
				Iterator<JsonNode> elements = tmpNode.getElements();
				while(elements.hasNext()){
					JsonNode el=elements.next();
					JsonSupportNode child=new JsonSupportNode();
					child.setNodeText(name);
					List<JsonSupportNode> innerChildren=getChildrenJsonNodes(el);
					for(JsonSupportNode innerChild : innerChildren){
						innerChild.setParent(child, -1);
					}
					getJsonNodesMap().put(child, el);
					children.add(child);
				}
			}
			else if(tmpNode.isValueNode()){
				JsonSupportNode child=new JsonSupportNode();
				child.setNodeText(name);
				getJsonNodesMap().put(child, tmpNode);
				children.add(child);			
			}
		}
		return children;
	}

	/*
	 * (non-Javadoc)
	 * @see com.jaspersoft.studio.data.querydesigner.ISelectableNodes#getSelectableNodes(java.lang.String)
	 */
	public List<JsonSupportNode> getSelectableNodes(String query) {
		List<JsonSupportNode> selectedList=new ArrayList<JsonSupportNode>();
		JsonQueryHelper jsonQueryHelper = new JsonQueryHelper(mapper);
		try {
			JsonNode jsonData = jsonQueryHelper.getJsonData(jsonRoot, query);
			List<JsonNode> elementsList=new ArrayList<JsonNode>();
			if(jsonData.isArray()){
				Iterator<JsonNode> elements = jsonData.getElements();
				while(elements.hasNext()){
					elementsList.add(elements.next());
				}	
			}
			else if(jsonData.isObject()){
				elementsList.add(jsonData);				
			}
			
			for(JsonSupportNode sn : getJsonNodesMap().keySet()){
				if(elementsList.contains(getJsonNodesMap().get(sn))){
					selectedList.add(sn);
				}
			}
		} catch (JRException e) {
			// Do not care about error in node selection
		}
		
		return selectedList;
	}

	public Map<JsonSupportNode, JsonNode> getJsonNodesMap() {
		if(this.jsonNodesMap==null){
			this.jsonNodesMap=new HashMap<JsonSupportNode, JsonNode>();
		}
		return jsonNodesMap;
	}
	
	public String getQueryExpression(String existingQuery, JsonSupportNode selectedNode){
		String absoluteQuery=getAbsoluteQueryExpression(selectedNode);
		if(existingQuery!=null && absoluteQuery.startsWith(existingQuery)){
			// consider also an additional . selector
			int qLength = existingQuery.length();
			return absoluteQuery.substring(qLength+Math.min(qLength, 1));
		}
		return absoluteQuery;
	}
	
	private String getAbsoluteQueryExpression(JsonSupportNode selectedNode){
		StringBuffer queryBuff=new StringBuffer();
		queryBuff.insert(0, selectedNode.getNodeText());
		JsonSupportNode tmpNode=selectedNode;
		while(tmpNode.getParent()!=null && !(tmpNode.getParent() instanceof MRoot)){
			tmpNode=(JsonSupportNode) tmpNode.getParent();
			queryBuff.insert(0, tmpNode.getNodeText()+".");
		}
		return queryBuff.toString();
	}
}
