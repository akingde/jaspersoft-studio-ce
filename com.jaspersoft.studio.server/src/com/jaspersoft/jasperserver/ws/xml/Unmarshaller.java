/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/

package com.jaspersoft.jasperserver.ws.xml;

import java.io.InputStream;
import java.io.StringReader;
import java.util.Date;

import org.w3c.dom.*;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.Argument;
import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ListItem;
import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.OperationResult;
import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.Request;
import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceProperty;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.xml.sax.InputSource;


/**
 *
 * @author gtoffoli
 */
public class Unmarshaller {
    
    protected static final Log log = LogFactory.getLog(Unmarshaller.class);
    
    private String encoding = "UTF-8";
    
    /** Creates a new instance of XMLMarchaller */
    public Unmarshaller() {
    }
    
    static public String readPCDATA(Node textNode) {
        return readPCDATA(textNode,true);
    }

    static public String readPCDATA(Node textNode, boolean trim) {
        NodeList list_child = textNode.getChildNodes();
        for (int ck=0; ck< list_child.getLength(); ck++) {
            Node child_child = (Node)list_child.item(ck);

            // --- start solution: if there is another node this should be the PCDATA-node
            Node ns = child_child.getNextSibling();
            if (ns != null)
            child_child = ns;
            // --- end solution

            final short nt = child_child.getNodeType();
            
            // 1. look for a CDATA first...
            if (nt == Node.CDATA_SECTION_NODE) {
               if (trim) return ((String)child_child.getNodeValue()).trim();
                return (String)child_child.getNodeValue();
            }
        }
        
        for (int ck=0; ck< list_child.getLength(); ck++) {
            Node child_child = (Node)list_child.item(ck);
            
            // --- start solution: if there is another node this should be the PCDATA-node
            Node ns = child_child.getNextSibling();
            if (ns != null)
            child_child = ns;
            // --- end solution

            final short nt = child_child.getNodeType();
            // 1. look for a CDATA first...
            if (nt == Node.TEXT_NODE) {
               if (trim) return ((String)child_child.getNodeValue()).trim();
                return (String)child_child.getNodeValue();
            }
        }
        
        return "";
    }

    /**
     * Class c is not used, the method firm is done to be compatible
     * with old code.
     *
     */
    public static Object unmarshal(Class c, StringReader sr) throws Exception
    {
        Unmarshaller u = new Unmarshaller();
        return  u.unmarshal(sr);
    }

    public static Object unmarshalXml(String xmlString) throws Exception
    {
        Unmarshaller u = new Unmarshaller();
        return u.unmarshal(xmlString);
    }

    public static Object unmarshal(Class c, Element elm){
        //c instanceof ResourceDescriptor.class
        if (c.equals(ResourceDescriptor.class)){
            return readResourceDescriptor(elm);
        }

        throw new UnsupportedOperationException();
    }

    /*
     * This method unmarshall the xml. If the xml rapresents an OperationResult, an OperationResult
     * will be retun, otherwise it will return a ResourceDescritor...
     * The default encoding used is UTF-8.
     */
    public Object unmarshal(String xml) throws Exception
    {
        StringReader sreader = new java.io.StringReader(xml);
        return unmarshal(sreader);
    }
    /*
     * This method unmarshall the xml. If the xml rapresents an OperationResult, an OperationResult
     * will be retun, otherwise it will return a ResourceDescritor...
     * The default encoding used is UTF-8.
     */
    public Object unmarshal(StringReader sreader) throws Exception
    {
        try {
                // Use parser defined at the Java level, not a specific parser
            
                DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
                
                InputSource input_source  = new InputSource( sreader );
                Document document = docBuilder.parse(input_source);

                Node rootNode = document.getDocumentElement();
                
                if (rootNode.getNodeName().equals("request"))
                {
                    return readRequest( rootNode );
                }
                else if (rootNode.getNodeName().equals("operationResult"))
                {
                    return readOperationResult( rootNode );
                }
                
        } catch (Exception e) {
            log.error(e);
            throw e;
        }
    
        return null;
    }
  
    
    private Request readRequest( Node requestNode)
    {
        
        Request request = new Request();
        
        NamedNodeMap nodeAttributes = requestNode.getAttributes();

        if (nodeAttributes.getNamedItem("operationName") != null)
            request.setOperationName( nodeAttributes.getNamedItem("operationName").getNodeValue() );
        
        if (nodeAttributes.getNamedItem("locale") != null)
            request.setLocale( nodeAttributes.getNamedItem("locale").getNodeValue() );
        

        NodeList childsOfChild = requestNode.getChildNodes();
        for (int c_count=0; c_count< childsOfChild.getLength(); c_count++) {
            Node child_child = (Node)childsOfChild.item(c_count);
            
            if (child_child.getNodeType() == Node.ELEMENT_NODE && child_child.getNodeName().equals("argument")) {
                request.getArguments().add( readArgument( child_child ) );
            }
            
            if (child_child.getNodeType() == Node.ELEMENT_NODE && child_child.getNodeName().equals("resourceDescriptor")) {
                request.setResourceDescriptor(  readResourceDescriptor( (Element)child_child ) );
            }

        }
        return request;
    }
    
    private Argument readArgument( Node argumentNode)
    {
        Argument argument = new Argument();
        NamedNodeMap nodeAttributes = argumentNode.getAttributes();

        if (nodeAttributes.getNamedItem("name") != null)
            argument.setName( nodeAttributes.getNamedItem("name").getNodeValue() );
        
        argument.setValue( readPCDATA( argumentNode ) );
        
        return argument;
    }
    
    public static ResourceDescriptor readResourceDescriptor(Element rpNode) {
      
       ResourceDescriptor rd = new ResourceDescriptor();
       NamedNodeMap nodeAttributes;
        
        if (rpNode instanceof Element){
            nodeAttributes = rpNode.getAttributes();
        }
        else{
            // the current implementation currently rely on rpNode being an Element
            throw new IllegalStateException("repNode is not an Element");
        }

       if (nodeAttributes.getNamedItem("name") != null)
            rd.setName( nodeAttributes.getNamedItem("name").getNodeValue() );
       if (nodeAttributes.getNamedItem("wsType") != null)
            rd.setWsType( nodeAttributes.getNamedItem("wsType").getNodeValue() );
       if (nodeAttributes.getNamedItem(ResourceDescriptor.REFERENCE_TYPE) != null)
            rd.setReferenceType( nodeAttributes.getNamedItem(ResourceDescriptor.REFERENCE_TYPE).getNodeValue() );
       if (nodeAttributes.getNamedItem("uriString") != null)
            rd.setUriString( nodeAttributes.getNamedItem("uriString").getNodeValue() );
       if (nodeAttributes.getNamedItem("isNew") != null)
            rd.setIsNew( nodeAttributes.getNamedItem("isNew").getNodeValue().equals("true") );
        
       NodeList childsOfChild = rpNode.getChildNodes();
        for (int c_count=0; c_count< childsOfChild.getLength(); c_count++) {
            Node child_child = (Node)childsOfChild.item(c_count);
            
            if (child_child.getNodeType() == Node.ELEMENT_NODE && child_child.getNodeName().equals("label")) {
                rd.setLabel( readPCDATA( child_child ) );
            }
            else if (child_child.getNodeType() == Node.ELEMENT_NODE && child_child.getNodeName().equals("description")) {
                rd.setDescription( readPCDATA( child_child ) );
            }
            else if (child_child.getNodeType() == Node.ELEMENT_NODE && child_child.getNodeName().equals("creationDate")) {
            	Date creationDate = readCreationDate(child_child);
            	if (creationDate != null) {
					rd.setCreationDate(creationDate);
				}
            }
            else if (child_child.getNodeType() == Node.ELEMENT_NODE && child_child.getNodeName().equals("resourceProperty")) {
                rd.setResourceProperty( readResourceProperty(child_child)  );
            }
            else if (child_child.getNodeType() == Node.ELEMENT_NODE && child_child.getNodeName().equals("resourceDescriptor")) {
                rd.getChildren().add( readResourceDescriptor((Element) child_child)  );
            }
            else if (child_child.getNodeType() == Node.ELEMENT_NODE && child_child.getNodeName().equals("parameter")) {
                rd.getParameters().add( readResourceParameter(child_child)  );
            }
        }
       
       return rd;
    }

	protected static Date readCreationDate(Node node) {
		Date creationDate = null;
		String dateStr = readPCDATA(node, true);
		if (dateStr.length() > 0) {
			try {
	            // deserializing timestamp
				long timestamp = Long.parseLong(dateStr);
				creationDate = new Date(timestamp);
			} catch (NumberFormatException e) {
				throw new RuntimeException("Error parsing resource creation date timestamp \"" 
						+ dateStr + "\"");
			}
		}
		return creationDate;
	}

    private static ResourceProperty readResourceProperty(Node rpNode) {
       
       
       ResourceProperty rp = new ResourceProperty(null);
       NamedNodeMap nodeAttributes = rpNode.getAttributes();

       if (nodeAttributes.getNamedItem("name") != null)
            rp.setName( nodeAttributes.getNamedItem("name").getNodeValue() );
        
       NodeList childsOfChild = rpNode.getChildNodes();
        for (int c_count=0; c_count< childsOfChild.getLength(); c_count++) {
            Node child_child = (Node)childsOfChild.item(c_count);
            
            if (child_child.getNodeType() == Node.ELEMENT_NODE && child_child.getNodeName().equals("value")) {
                rp.setValue( readPCDATA( child_child ) );
            }
            else if (child_child.getNodeType() == Node.ELEMENT_NODE && child_child.getNodeName().equals("resourceProperty")) {
                rp.getProperties().add( readResourceProperty(child_child)  );
            }
        }       
       return rp;
    }
    
    private static ListItem readResourceParameter(Node rpNode) {
       
       ListItem rp = new ListItem();
       NamedNodeMap nodeAttributes = rpNode.getAttributes();

       if (nodeAttributes.getNamedItem("name") != null)
            rp.setLabel( nodeAttributes.getNamedItem("name").getNodeValue() );
       
       if (nodeAttributes.getNamedItem("isListItem") != null)
            rp.setIsListItem( nodeAttributes.getNamedItem("isListItem").getNodeValue().equals("true") );
        
       rp.setValue( readPCDATA( rpNode ) );
       
       return rp;
    }
    
    
    private OperationResult readOperationResult( Node operationResultNode)
    {
       OperationResult or = new OperationResult();
        
       NamedNodeMap nodeAttributes = operationResultNode.getAttributes();

       if (nodeAttributes.getNamedItem("version") != null)
            or.setVersion( nodeAttributes.getNamedItem("version").getNodeValue() );
        
       NodeList childsOfChild = operationResultNode.getChildNodes();
        for (int c_count=0; c_count< childsOfChild.getLength(); c_count++) {
            Node child_child = (Node)childsOfChild.item(c_count);
            
            if (child_child.getNodeType() == Node.ELEMENT_NODE && child_child.getNodeName().equals("returnCode")) {
                or.setReturnCode( Integer.parseInt( readPCDATA( child_child ) ) );
            }
            else if (child_child.getNodeType() == Node.ELEMENT_NODE && child_child.getNodeName().equals("returnMessage")) {
                or.setMessage( readPCDATA( child_child ) );
            }
            else if (child_child.getNodeType() == Node.ELEMENT_NODE && child_child.getNodeName().equals("resourceDescriptor")) {
                or.getResourceDescriptors().add( readResourceDescriptor( (Element)child_child)  );
            }
        }
       
       return or;
    }
    
    
}
