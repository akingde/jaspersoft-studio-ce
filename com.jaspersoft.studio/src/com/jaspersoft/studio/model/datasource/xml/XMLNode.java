/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.model.datasource.xml;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.wb.swt.ResourceManager;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.model.ANode;

import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.data.AbstractXmlDataSource;
import net.sf.jasperreports.engine.design.JRDesignField;

/**
 * ANode representing an XML document node.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public class XMLNode extends ANode {

	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
	private String nodeName;
	private String xpathExpression;

	public ImageDescriptor getImagePath() {
		return ResourceManager.getPluginImageDescriptor(JaspersoftStudioPlugin.PLUGIN_ID,
				"icons/resources/element_obj.gif");
	}

	public String getDisplayText() {
		return this.nodeName;
	}

	public String getName() {
		return this.nodeName;
	}

	public void setName(String nodeName) {
		this.nodeName = nodeName;
	}

	public String getXPathExpression() {
		return this.xpathExpression;
	}

	public void setXPathExpression(String xpathExp) {
		this.xpathExpression = xpathExp;
	}

	@Override
	public Object getAdapter(@SuppressWarnings("rawtypes") Class adapter) {
		if (adapter == JRDesignField.class || adapter == JRField.class) {
			JRDesignField field = new JRDesignField();
			field.setName(nodeName);
			field.setDescription(xpathExpression);
			field.setValueClass(String.class);
			field.getPropertiesMap().setProperty(AbstractXmlDataSource.PROPERTY_FIELD_EXPRESSION, xpathExpression);
			return field;
		}
		return super.getAdapter(adapter);
	}

}
