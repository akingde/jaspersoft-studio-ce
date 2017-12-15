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

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.Argument;
import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ListItem;
import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.OperationResult;
import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.Request;
import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceProperty;

/**
 *
 * @author gtoffoli
 */
public class Marshaller {
    
    private String currentIndentation = "";
    
    /*
     * This method generate the XML rapresentation of the request or the operationResult.
     * The default encoding used is UTF-8.
     */
    public static void marshal(Object obj, java.io.StringWriter out)
    {
        Marshaller marshaller = new Marshaller();
        if (obj instanceof Request)
        {
            out.write( marshaller.marshal((Request)obj) );
        }
        else if (obj instanceof OperationResult)
        {
            out.write( marshaller.marshal((OperationResult)obj) );
        }
    }
    
    public static final String[] special_chars= new String[]
    {
        "&","&amp;",
        "\"","&quot;",
        "'","&apos;",
        "<","&lt;",
        ">","&gt;"
    };
                                    
    private String encoding = "UTF-8";
    
    /** Creates a new instance of XMLMarchaller */
    public Marshaller() {
    }
    
    /**
     * Replave invalid xml chars according to the following table:
     *  & => &amp;
     *  " => &quot;
     *  ' => &apos;
     *  < => &lt;
     *  > => &gt;
     */
    public static String xmlEscape(String text)
    {
        if( text == null) return "";
        int i=0;
        String tmp = "";
        for (i=0; i < special_chars.length; i+=2)
        {
          text = string_replace(special_chars[i+1], special_chars[i], text);
          //text = string_replace(special_chars[i], special_chars[i+1], text);
        }

        return text;
    }
    
    /**
     *    Replace s2 with s1 in s3
     **/
    public static String string_replace(String s1, String s2, String s3) {
            String string="";
            string = "";

            if (s2 == null || s3 == null || s2.length() == 0) return s3;

            int pos_i = 0; // posizione corrente.
            int pos_f = 0; // posizione corrente finale

            int len = s2.length();
            while ( (pos_f = s3.indexOf(s2, pos_i)) >= 0) {
                    string += s3.substring(pos_i,pos_f)+s1;
                    //+string.substring(pos+ s2.length());
                    pos_f = pos_i = pos_f + len;

            }

            string += s3.substring(pos_i);

            return string;
    }
    
    /*
     * This method generate the XML rapresentation of the request.
     * The default encoding used is UTF-8.
     */
    public String marshal(Request request)
    {
        StringBuffer xml = new StringBuffer();
        xml.append("<?xml version=\"1.0\" encoding=\"" + getEncoding() + "\"?>\n");
        xml.append("<request operationName=\"" + request.getOperationName() + "\"");
        
        if (request.getLocale() != null) xml.append(" locale=\"" + request.getLocale() +"\"");
        xml.append(">\n");
        
        currentIndentation = "\t";
        
        for (int i=0; i<request.getArguments().size(); ++i)
        {
            Argument a = (Argument)request.getArguments().get(i);
            String value = (a.getValue() == null) ? "/>" : "><![CDATA[" + a.getValue() + "]]></argument>";
            xml.append(currentIndentation + "<argument name=\"" +  xmlEscape( a.getName() ) + "\"" + value + "\n");
        }
        
        xml.append(  writeResourceDescriptor( request.getResourceDescriptor() ) );
        
        xml.append("</request>\n");
        return xml.toString();
    }
  
    
    
    public String writeResourceDescriptor( ResourceDescriptor rd)
    {
        if (rd == null) return "";
        
        StringBuffer xml = new StringBuffer();
        xml.append(currentIndentation + "<resourceDescriptor");

        if (rd.getName()!=null && !rd.getName().equals(""))
            xml.append(" name=\"" +  xmlEscape( rd.getName() ) +"\"");

        if (rd.getWsType()!=null && !rd.getWsType().equals("")){
            xml.append(" wsType=\"" + xmlEscape( rd.getWsType()) +"\"");
            if (rd.getWsType().equals(ResourceDescriptor.TYPE_REFERENCE) && rd.getReferenceType()!=null && !rd.getReferenceType().equals("")){
                xml.append(" "+ResourceDescriptor.REFERENCE_TYPE+"=\"" + xmlEscape( rd.getReferenceType()) +"\"");
            }
        }


        if (rd.getUriString()!=null && !rd.getUriString().equals(""))
            xml.append(" uriString=\"" +  xmlEscape(rd.getUriString() )+"\"");

        xml.append(" isNew=\"" + rd.getIsNew()  +"\"");
        xml.append(">\n");
        
        currentIndentation += "\t";
        
        if (rd.getLabel()!=null && !rd.getLabel().equals(""))
            xml.append(currentIndentation + "<label><![CDATA[" + rd.getLabel() + "]]></label>\n");

        if (rd.getDescription() != null && rd.getDescription().length() > 0)
        {
            xml.append(currentIndentation + "<description><![CDATA[" + rd.getDescription() + "]]></description>\n");
        }
        
        if (rd.getCreationDate() != null)
        {
            xml.append(currentIndentation);
            xml.append("<creationDate>");
            writeCreationDateText(rd, xml);
            xml.append("</creationDate>\n");
        }
        
        if (rd.getProperties() != null)
        {
            for (int i=0; i<rd.getProperties().size(); i++)
            {

                ResourceProperty rp = (ResourceProperty)rd.getProperties().get(i);

                String s = writeResourceProperty(rd, rp);
                xml.append(s );
            }
        }


        if (rd.getChildren() != null)
        {
            for (int i=0; i<rd.getChildren().size(); ++i)
            {
                ResourceDescriptor rdchild = (ResourceDescriptor)rd.getChildren().get(i);
                xml.append( writeResourceDescriptor( rdchild ));
            }
        }

        if (rd.getParameters() != null)
        {
            for (int i=0; i<rd.getParameters().size(); ++i)
            {
                ListItem rdchild = (ListItem)rd.getParameters().get(i);
                xml.append( writeResourceParameter( rdchild ));
            }
        }

        currentIndentation = currentIndentation.substring(0, currentIndentation.length() -1 );
        xml.append(currentIndentation + "</resourceDescriptor>\n");
        return xml.toString();
    }

	protected void writeCreationDateText(ResourceDescriptor rd, StringBuffer xml) {
        // serializing as timestamp
		long timestamp = rd.getCreationDate().getTime();
		xml.append(timestamp);
	}

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    private String writeResourceProperty(ResourceDescriptor rd, ResourceProperty rp) {

        StringBuffer xml = new StringBuffer();
        xml.append(currentIndentation + "<resourceProperty name=\"" +  xmlEscape( rp.getName() ) + "\">\n");

        currentIndentation += "\t";
        if (rp.getValue() != null)
        {
            xml.append(currentIndentation + "<value><![CDATA[" + rp.getValue() + "]]></value>\n");
        }

        for (int i=0; i<rp.getProperties().size(); i++)
        {
            ResourceProperty rpChild = (ResourceProperty)rp.getProperties().get(i);
            xml.append( writeResourceProperty(rd, rpChild) );
        }
        
        currentIndentation = currentIndentation.substring(0, currentIndentation.length() -1 );
        xml.append(currentIndentation + "</resourceProperty>\n");

//        if (rp.getName().equals(ResourceDescriptor.PROP_FILERESOURCE_HAS_DATA) && rd.getData()!=null){
//            xml.append(currentIndentation + "<resourceProperty name=\"" +  ResourceDescriptor.PROP_DATA + "\">\n");
//
//            xml.append(currentIndentation + "<value><![CDATA[" + Base64.encodeBase64String(rd.getData()) + "]]></value>\n");
//            xml.append(currentIndentation + "</resourceProperty>\n");
//        }

        return xml.toString();
    }
    
    private String writeResourceParameter(ListItem rp) {
        
        StringBuffer xml = new StringBuffer();
        xml.append(currentIndentation + "<parameter name=\"" +  xmlEscape( rp.getLabel() ) + "\"");
        if (rp.isIsListItem())
        {
             xml.append(" isListItem=\"true\"");
        }
        
        xml.append("><![CDATA[");
        if (rp.getValue() != null)
        {
        	xml.append(rp.getValue());
        }
        xml.append("]]></parameter>\n");
        
        return xml.toString();
    }
    
    
    public String marshal(OperationResult response)
    {
        StringBuffer xml = new StringBuffer();
        xml.append("<?xml version=\"1.0\" encoding=\"" + getEncoding() + "\"?>\n");
        xml.append("<operationResult version=\"" + response.getVersion() + "\">\n");
        
        currentIndentation = "\t";
        
        xml.append(currentIndentation + "<returnCode><![CDATA[" + response.getReturnCode() + "]]></returnCode>\n");
        
        if (response.getMessage() != null && response.getMessage().length() > 0)
        {
            xml.append(currentIndentation + "<returnMessage><![CDATA[" + response.getMessage() + "]]></returnMessage>\n");
        }
        
        
        if (response.getResourceDescriptors() != null)
        {
            for (int i=0; i<response.getResourceDescriptors().size(); ++i)
            {
                ResourceDescriptor rd = (ResourceDescriptor)response.getResourceDescriptors().get(i);
                xml.append(writeResourceDescriptor( rd ));
            }
        }
        
        currentIndentation = currentIndentation.substring(0, currentIndentation.length() -1 );
        xml.append("</operationResult>\n");
        return xml.toString();
    }
    
    
}
