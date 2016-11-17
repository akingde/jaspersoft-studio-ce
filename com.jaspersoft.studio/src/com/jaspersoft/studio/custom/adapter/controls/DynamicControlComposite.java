/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.custom.adapter.controls;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import net.sf.jasperreports.data.DataAdapter;
import net.sf.jasperreports.engine.JasperReportsContext;

import org.apache.commons.beanutils.BeanUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Text;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.jaspersoft.studio.data.ADataAdapterComposite;
import com.jaspersoft.studio.data.DataAdapterDescriptor;

/**
 * A dynamic configuration composite that create the control to edit a data adapter
 * from the castor mapping file of the data adapter itself
 * 
 * @author Orlandin Marco
 *
 */
public abstract class DynamicControlComposite extends ADataAdapterComposite {

	/**
	 * Key value for the type of a field
	 */
	protected static final String TYPE_KEY = "type";
	
	/**
	 * Key value for the name of a field
	 */
	protected static final String BIND_KEY = "bind";
	
	/**
	 * The descriptor of the data adapter
	 */
	protected DataAdapterDescriptor dataAdapterDescriptor;
	
	/**
	 * The list of controls generated to edit the data adapter
	 */
	protected List<Control> generatedControls = new ArrayList<Control>();

	/**
	 * Construct the class and initialize the controls
	 * 
	 * @param parent the parent of the controls
	 * @param style the style for the controls
	 * @param context the current JasperReportsContext
	 */
	protected DynamicControlComposite(Composite parent, int style, JasperReportsContext context) {
		super(parent, style,context);
		setLayout(new GridLayout(2, false));
	}
	
	/**
	 * Return the castor mapping file definition of the data adapter 
	 * 
	 * @return the castor mapping path in the workspace of the data adapter or null if it can't be found
	 */
	protected String getXmlDefinitionLocation(){
		 Properties props = new Properties();
		 try {
			props.load(dataAdapterDescriptor.getClass().getResourceAsStream("/jasperreports_extension.properties"));
			for(Object property : props.keySet()){
				if (property.toString().startsWith("net.sf.jasperreports.extension.castor.mapping")){
					return props.getProperty(property.toString());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Create a control inside the composite. The type of control change to reflect the type
	 * 
	 * @param type the type of control to create. Supported control are int, boolean, float and string
	 * @param bindName the name of the field binded to the control
	 * @param label the label of the control
	 * @return the text to place in the template to generate the control
	 */
	protected void createDynamicControl(String type, String bindName, String label){
		if (type.equals("int")){
			Label lbl = new Label(this, SWT.NONE);
			lbl.setText(label);
			Spinner control = new Spinner(this, SWT.BORDER);
			control.setData(BIND_KEY, bindName);
			control.setData(TYPE_KEY, type);
			control.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
			generatedControls.add(control);
		} else if (type.equals("boolean")){
			Label lbl = new Label(this, SWT.NONE);
			lbl.setText(label);
			Button control = new Button(this, SWT.CHECK);
			control.setData(BIND_KEY, bindName);
			control.setData(TYPE_KEY, type);
			generatedControls.add(control);
		} else if (type.equals("float")){
			Label lbl = new Label(this, SWT.NONE);
			lbl.setText(label);
			Text control = new Text(this, SWT.BORDER);
			control.setData(BIND_KEY, bindName);
			control.setData(TYPE_KEY, type);
			control.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
			control.addVerifyListener(new VerifyListener() {
				@Override
				public void verifyText(VerifyEvent e) {
					Text text = (Text) e.getSource();
					// get old text and create new text by using the
					// VerifyEvent.text
					final String oldS = text.getText();
					String newS = oldS.substring(0, e.start) + e.text + oldS.substring(e.end);
					boolean isFloat = true;
					try {
						Float.parseFloat(newS);
					} catch (NumberFormatException ex) {
						isFloat = false;
					}
					if (!isFloat)
						e.doit = false;
				}
			});
			generatedControls.add(control);
		} else if (type.equals("string")){
			Label lbl = new Label(this, SWT.NONE);
			lbl.setText(label);
			Text control = new Text(this, SWT.BORDER);
			control.setData(BIND_KEY, bindName);
			control.setData(TYPE_KEY, type);
			control.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		}
	}
	
	/**
	 * Generate the control to handle a list of vales
	 * 
	 * @param type the type of the values
	 * @param bindName the name of the collection field binded to this list
	 * @param label label of the list control
	 */
	protected void createDynamicArray(String type, String bindName, String label){
		new Label(this, SWT.NONE).setText(label);
		PropertyListControl listControl = new PropertyListControl(this, type);
		listControl.setData(BIND_KEY, bindName);
		listControl.setData(TYPE_KEY, "collection");
		listControl.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		generatedControls.add(listControl);
	}
	
	/**
	 * Given a field node of the castor mapping file it return
	 * the first bind-xml node
	 * 
	 * @param parent the field node of the castor mapping 
	 * @return the first children bind-xml node
	 */
	private Node getBindNode(Node parent){
		NodeList children = parent.getChildNodes();
		for (int i = 0; i < children.getLength(); ++i) {
			Node child = children.item(i);
			if (child.getNodeName().equals("bind-xml")) return child;
		}
		return null;
	}
	
	/**
	 * Read the content of a castor mapping file and create an appropriate
	 * control for every field inside the node list
	 * 
	 * @param fieldNodes list of the children of the root node of the castor mapping file
	 */
	protected void createDynamicControls(NodeList fieldNodes){
		for (int i = 0; i < fieldNodes.getLength(); ++i) {
			Node fieldNode = fieldNodes.item(i);
			if (fieldNode.getNodeName().equals("field")){
				String nameAttribute =  fieldNode.getAttributes().getNamedItem("name").getNodeValue();
				//name and class are handled by default
				if (!nameAttribute.equals("class") && !nameAttribute.equals("name")){
					String type = fieldNode.getAttributes().getNamedItem("type").getNodeValue();
					Node collectionNode = fieldNode.getAttributes().getNamedItem("collection");
					boolean isArray = collectionNode != null && collectionNode.getNodeValue().toLowerCase().equals("arraylist");
					Node bindNode = getBindNode(fieldNode);
					if (bindNode != null){
						String name = fieldNode.getAttributes().getNamedItem("name").getNodeValue();
						String bindName = bindNode.getAttributes().getNamedItem("name").getNodeValue();
						if (isArray){
							createDynamicArray(type,bindName,name);
						} else {
							createDynamicControl(type, bindName,name);
						}
					}
				}
			}
		}
	}
	
	/**
	 * Search a castor mapping file inside the data adapter jar and if it is found create the controls
	 * to edit it

	 */
	protected void createDynamicControls() {
		String xmlDefinition = getXmlDefinitionLocation();
		if (xmlDefinition != null) {
			DataAdapter adapter = dataAdapterDescriptor.getDataAdapter();
			InputStream is = dataAdapterDescriptor.getClass().getResourceAsStream("/" + xmlDefinition);
			if (null != is) {
				try {
					DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
					dbf.setValidating(false);
					dbf.setIgnoringComments(true);
					dbf.setNamespaceAware(false);
					DocumentBuilder builder = dbf.newDocumentBuilder();
					builder.setEntityResolver(new EntityResolver() {
						@Override
						public InputSource resolveEntity(String publicId, String systemId) throws SAXException, IOException {
							if (systemId.contains("http://castor.org/mapping.dtd")) {
								return new InputSource(new StringReader(""));
							} else {
								return null;
							}
						}
					});

					Document document = builder.parse(is);
					Node mapNode = document.getDocumentElement();
					if (mapNode.getNodeName().equals("mapping")) {
						NodeList adapterNodes = mapNode.getChildNodes();
						for (int j = 0; j < adapterNodes.getLength(); ++j) {
							Node adapterNode = adapterNodes.item(j);
							if (adapterNode.getNodeName().equals("class")) {
								String classAttribute = adapterNode.getAttributes().getNamedItem("name").getNodeValue();
								if (classAttribute != null && classAttribute.equals(adapter.getClass().getName())) {
									createDynamicControls(adapterNode.getChildNodes());
									is.close();
									return;
								}
							}
						}
					}
				} catch (Exception ex) {
					try {
						is.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
					ex.printStackTrace();
				}
			}

		}
	}

	/**
	 * Create the controls
	 */
	protected void initComponents() {
		//dispose the old components
		for(Control control : getChildren()){
			control.dispose();
		}
		generatedControls.clear();
		createDynamicControls();
	}
	
	/**
	 * Set the data adapter descriptor from outside and bind the created controls to it
	 */
	@Override
	public void setDataAdapter(DataAdapterDescriptor dataAdapterDescriptor) {
		this.dataAdapterDescriptor = dataAdapterDescriptor;
		DataAdapter dataAdapter = dataAdapterDescriptor.getDataAdapter();
		initComponents();
		bindWidgets(dataAdapter);
	}
	
	/**
	 * Given the data adapter uses it to initialize the values of the controls to
	 * reflect the current configuration
	 *  
	 * @param dataAdapter the not null data adapter
	 */
	protected void updateAdapterSettings(DataAdapter dataAdapter){
		for(Control control : generatedControls){
			String methodName = (String)control.getData(BIND_KEY);
			String type = (String)control.getData(TYPE_KEY);
			try {
				if (type.equals("string")){
					Text text = (Text)control;
					BeanUtils.setProperty(dataAdapter, methodName, text.getText());
				} else if (type.equals("float")){
					Text text = (Text)control;
					BeanUtils.setProperty(dataAdapter, methodName, text.getText());
				} else if (type.equals("int")){
					Spinner spinner = (Spinner)control;
					BeanUtils.setProperty(dataAdapter, methodName, String.valueOf(spinner.getSelection()));
				} else if (type.equals("boolean")){
					Button button = (Button)control;
					BeanUtils.setProperty(dataAdapter, methodName, String.valueOf(button.getSelection()));
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			} 
		}
	}

	/**
	 * Bind the data adapter to the widgets to have that every change on the
	 * controls widgets reflected on the data adapter
	 * 
	 * @param dataAdapter the data adapter
	 */
	@Override
	protected void bindWidgets(DataAdapter dataAdapter) {
		final DataAdapter adapter = (DataAdapter)dataAdapter;
		
		//Listeners used to detect the change of the value of a control
		ModifyListener valueChangedListener = new ModifyListener() {	
			@Override
			public void modifyText(ModifyEvent e) {
				updateAdapterSettings(adapter);
			}
		};
		SelectionAdapter buttonChangedListener = new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				updateAdapterSettings(adapter);
			}
		};
		
		//Set the initial value on every control reading it from the data adapter
		//and add the listener to detect the changes done by the user
		for(Control control : generatedControls){
			String methodName = (String)control.getData(BIND_KEY);
			String type = (String)control.getData(TYPE_KEY);
			try {
				String result = BeanUtils.getProperty(adapter, methodName);
				if (type.equals("string") || type.equals("float")){
					Text text = (Text)control;
					text.setText(result != null ? result : "");
					text.addModifyListener(valueChangedListener);
				} else if (type.equals("int")){
					Spinner spinner = (Spinner)control;
					if (result != null) spinner.setSelection(Integer.parseInt(result));
					spinner.addModifyListener(valueChangedListener); 
				} else if (type.equals("boolean")){
					Button button = (Button)control;
					if (result != null) button.setSelection(Boolean.parseBoolean(result));
					button.addSelectionListener(buttonChangedListener); 
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			} 
		}
	}
}
