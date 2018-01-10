/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.querydesigner.json;

import java.util.StringTokenizer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.data.JsonDataSource;
import net.sf.jasperreports.engine.util.JsonUtil;

/**
 * Utility class to select Json data given an input Json root node and a query string.
 * <p>
 * 
 * This class contains the code that is actually used inside the {@link JsonDataSource}.
 *  
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 * 
 * FIXME - THIS CLASS SHOULD BE REMOVED ONCE SOME OF THE CODE IN JR IS REFACTORED
 *
 */
public class JsonQueryHelper {

	private static final String PROPERTY_SEPARATOR = ".";
	private static final String ARRAY_LEFT = "[";
	private static final String ARRAY_RIGHT = "]";
	private static final String ATTRIBUTE_LEFT = "(";
	private static final String ATTRIBUTE_RIGHT = ")";
	
	private ObjectMapper mapper;
	
	public JsonQueryHelper(ObjectMapper mapper){
		this.mapper=mapper;
	}
	
	public JsonQueryHelper(){
		this.mapper=getDefaultObjectMapper();
	}
	
	private ObjectMapper getDefaultObjectMapper() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
		mapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
		return mapper;
	}

	/**
	 * Extracts the JSON nodes based on the query expression
	 * 
	 * @param rootNode
	 * @param jsonExpression
	 * @throws JRException
	 */
	public JsonNode getJsonData(JsonNode rootNode, String jsonExpression) throws JRException {
		if (jsonExpression == null || jsonExpression.length() == 0) {
			return rootNode;
		}
		JsonNode tempNode = rootNode;
		StringTokenizer tokenizer = new StringTokenizer(jsonExpression, PROPERTY_SEPARATOR);
		
		while(tokenizer.hasMoreTokens()) {
			String currentToken = tokenizer.nextToken();
			int currentTokenLength = currentToken.length();
			int indexOfLeftSquareBracket = currentToken.indexOf(ARRAY_LEFT);

			// got Left Square Bracket - LSB
			if (indexOfLeftSquareBracket != -1) {
				// a Right Square Bracket must be the last character in the current token
				if(currentToken.lastIndexOf(ARRAY_RIGHT) != (currentTokenLength-1)) {
					throw new JRException("Invalid expression: " + jsonExpression + "; current token " + currentToken + " not ended properly");
				}
				
				// LSB not first character
				if (indexOfLeftSquareBracket > 0) {
					// extract nodes at property
					String property = currentToken.substring(0, indexOfLeftSquareBracket);
					tempNode = goDownPathWithAttribute(tempNode, property);
				}

				String arrayOperators = currentToken.substring(indexOfLeftSquareBracket);
				StringTokenizer arrayOpsTokenizer = new StringTokenizer(arrayOperators,ARRAY_RIGHT);
				while(arrayOpsTokenizer.hasMoreTokens()) {
					if (tempNode == null || tempNode.isMissingNode() || !tempNode.isArray()) {
						return null;
					}

					String currentArrayOperator = arrayOpsTokenizer.nextToken();
					tempNode = tempNode.path(Integer.parseInt(currentArrayOperator.substring(1)));
				}
			} else {
				tempNode = goDownPathWithAttribute(tempNode, currentToken);
			}
		}
		
		return tempNode;
	}
	
	/**
	 * Extracts the JSON nodes that match the attribute expression
	 * 
	 * @param rootNode
	 * @param pathWithAttributeExpression : e.g. Orders(CustomerId == HILAA)
	 * @throws JRException
	 */
	protected JsonNode goDownPathWithAttribute(JsonNode rootNode, String pathWithAttributeExpression) throws JRException {
		// check if path has attribute selector
		int indexOfLeftRoundBracket = pathWithAttributeExpression.indexOf(ATTRIBUTE_LEFT); 
		if (indexOfLeftRoundBracket != -1) {
			
			// a Right Round Bracket must be the last character in the current pathWithAttribute
			if(pathWithAttributeExpression.indexOf(ATTRIBUTE_RIGHT) != (pathWithAttributeExpression.length() - 1)) {
				throw new JRException("Invalid attribute selection expression: " + pathWithAttributeExpression);
			}
			
			if(rootNode != null && !rootNode.isMissingNode()) {
				
				String path = pathWithAttributeExpression.substring(0, indexOfLeftRoundBracket);
				
				// an expression in a form like: attribute==value
				String attributeExpression = pathWithAttributeExpression.substring(indexOfLeftRoundBracket + 1, pathWithAttributeExpression.length() - 1);
				
				JsonNode result = null;
				if (rootNode.isObject()) {
					// select only those nodes for which the attribute expression applies
					if (!rootNode.path(path).isMissingNode()) {
						if (rootNode.path(path).isObject()) {
							if (isValidExpression(rootNode.path(path), attributeExpression)) {
								result = rootNode.path(path);
							}
						} else if (rootNode.path(path).isArray()) {
							result = mapper.createArrayNode();
							for (JsonNode node: rootNode.path(path)) {
								if (isValidExpression(node, attributeExpression)) {
									((ArrayNode)result).add(node);
								} 
							}
						}
					}
				} else if (rootNode.isArray()) {
					result = mapper.createArrayNode();
					for (JsonNode node: rootNode) {
						JsonNode deeperNode = node.path(path);
						if (!deeperNode.isMissingNode()) {
							if (deeperNode.isArray()) {
								for(JsonNode arrayNode: deeperNode) {
									if (isValidExpression(arrayNode, attributeExpression)) {
										((ArrayNode)result).add(arrayNode);
									}
								}
							} else if (isValidExpression(deeperNode, attributeExpression)){
								((ArrayNode)result).add(deeperNode);
							}
						} 
					}
				}
				return result;
			} 
			
		} else { // path has no attribute selectors
			return goDownPath(rootNode, pathWithAttributeExpression);
		}
		return rootNode;
	}
	
	
	/**
	 * Extracts the JSON nodes under the simple path
	 * 
	 * @param rootNode
	 * @param simplePath - a simple field name, with no selection by attribute
	 */
	private JsonNode goDownPath(JsonNode rootNode, String simplePath) {
		if(rootNode != null && !rootNode.isMissingNode()) {
			JsonNode result = null;
			if (rootNode.isObject()) {
				result = rootNode.path(simplePath);
			} else if (rootNode.isArray()) {
				result = mapper.createArrayNode();
				for (JsonNode node: rootNode) {
					JsonNode deeperNode = node.path(simplePath);
					if (!deeperNode.isMissingNode()) {
						if (deeperNode.isArray()) {
							for(JsonNode arrayNode: deeperNode) {
								((ArrayNode)result).add(arrayNode);
							}
						} else {
							((ArrayNode)result).add(deeperNode);
						}
					} 
				}
			}
			return result;
		} 
		return rootNode;
	}
	
	/**
	 * Validates an attribute expression on a JsonNode
	 * 
	 * @param operand
	 * @param attributeExpression
	 * @throws JRException
	 */
	private boolean isValidExpression(JsonNode operand, String attributeExpression) throws JRException{
		return JsonUtil.evaluateJsonExpression(operand, attributeExpression);
	}
}
