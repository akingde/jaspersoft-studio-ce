/*******************************************************************************
 * Copyright (C) 2010 - 2012 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.data.xml;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import net.sf.jasperreports.data.DataAdapter;
import net.sf.jasperreports.data.DataAdapterService;
import net.sf.jasperreports.data.xml.XmlDataAdapter;
import net.sf.jasperreports.data.xml.XmlDataAdapterImpl;
import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.design.JRDesignField;
import net.sf.jasperreports.engine.util.xml.JRXPathExecuter;
import net.sf.jasperreports.engine.util.xml.JRXPathExecuterFactory;
import net.sf.jasperreports.engine.util.xml.JRXPathExecuterUtils;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.jaspersoft.studio.data.AWizardDataEditorComposite;
import com.jaspersoft.studio.data.Activator;
import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.data.DataAdapterEditor;
import com.jaspersoft.studio.data.IWizardDataEditorProvider;
import com.jaspersoft.studio.data.fields.IFieldsProvider;
import com.jaspersoft.studio.utils.ModelUtils;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class XMLDataAdapterDescriptor extends DataAdapterDescriptor implements IFieldsProvider,IWizardDataEditorProvider
{
	private XmlDataAdapter xmlDataAdapter = new XmlDataAdapterImpl();
	
	@Override
	public DataAdapter getDataAdapter() {
		return xmlDataAdapter;
	}

	@Override
	public void setDataAdapter(DataAdapter dataAdapter) {
		this.xmlDataAdapter = (XmlDataAdapter)dataAdapter;
	}

	@Override
	public DataAdapterEditor getEditor() {
		return new XMLDataAdapterEditor();
	}

	/* (non-Javadoc)
	 * @see com.jaspersoft.studio.data.DataAdapterFactory#getIcon(int)
	 */
	@Override
	public Image getIcon(int size) {
		// TODO Auto-generated method stub
		if (size == 16)
		{
			return  Activator.getImage("icons/blue-document-code.png"); //$NON-NLS-1$
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
		Throwable err=null;
		ArrayList<JRDesignField> fields = new ArrayList<JRDesignField>();
		try {
			String fileName = xmlDataAdapter.getFileName();
			File in = new File(fileName);
			Document doc=DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(in);	
			fields.addAll(getFieldsFromDocument(doc, jConfig, jDataset));
		} catch (SAXException e) {
			err=e;
		} catch (IOException e) {
			err=e;
		} catch (ParserConfigurationException e) {
			err=e;
		} 

		if(err!=null){
			throw new JRException(err);
		}
		return fields;
	}
	
	/**
	 * Returns the list of fields provided by an XML document and the related query.
	 * 
	 * @param doc the W3C XML document
	 * @param jConfig the JasperReports configuration instance
	 * @param jDataset the current dataset
	 * @return the list of fields
	 * @throws JRException
	 */
	protected List<JRDesignField> getFieldsFromDocument(Document doc,JasperReportsConfiguration jConfig, JRDataset jDataset) throws JRException{
		ArrayList<JRDesignField> fields = new ArrayList<JRDesignField>();
		JRXPathExecuterFactory xPathExecuterFactory = JRXPathExecuterUtils.getXPathExecuterFactory(jConfig);
		JRXPathExecuter xPathExecuter = xPathExecuterFactory.getXPathExecuter();
		NodeList nodes=xPathExecuter.selectNodeList(doc, jDataset.getQuery().getText());
		Node foundNode=null;
		if(nodes.getLength()>0){
			for(int i=0;i<nodes.getLength();i++){
				if(nodes.item(i).getNodeType()==Node.ELEMENT_NODE){
					// Basic idea: consider the first element node as template
					foundNode=nodes.item(i);
					break;
				}
			}
		}
		
		if(foundNode!=null){
			NodeList childNodes = foundNode.getChildNodes();
			for(int i=0;i<childNodes.getLength();i++){
				Node item = childNodes.item(i);
				JRDesignField f=new JRDesignField();
				f.setName(ModelUtils.getNameForField(fields, item.getNodeName()));
				f.setValueClass(String.class);
				if(item.getNodeType()==Node.ATTRIBUTE_NODE){
					f.setDescription("@"+item.getNodeName());					 //$NON-NLS-1$
					fields.add(f);
				}
				else if(item.getNodeType()==Node.ELEMENT_NODE){
					f.setDescription(item.getNodeName());					
					fields.add(f);
				}
			}
		}
		return fields;
	}

	@Override
	public AWizardDataEditorComposite createDataEditorComposite(
			Composite parent, WizardPage page) {
		return new XMLWizardDataEditorComposite(parent, page, this);
	}
}
