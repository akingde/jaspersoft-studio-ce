/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.model.datasource.json;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.wb.swt.ResourceManager;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.model.ANode;

import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.data.JsonDataSource;
import net.sf.jasperreports.engine.design.JRDesignField;

public class JsonSupportNode extends ANode {

	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
	private String nodeText;
	private String expression;

	public ImageDescriptor getImagePath() {
		return ResourceManager.getPluginImageDescriptor(JaspersoftStudioPlugin.PLUGIN_ID,
				"icons/resources/element_obj.gif");
	}

	public String getDisplayText() {
		return this.nodeText;
	}

	public Image getImage() {
		return ResourceManager.getImage(getImagePath());
	}

	public String getNodeText() {
		return nodeText;
	}

	public void setNodeText(String nodeText) {
		this.nodeText = nodeText;
	}

	public String getExpression() {
		return this.expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}

	@Override
	public Object getAdapter(@SuppressWarnings("rawtypes") Class adapter) {
		if (adapter == JRDesignField.class || adapter == JRField.class) {
			JRDesignField field = new JRDesignField();
			field.setName(nodeText);
			field.setDescription(expression);
			field.getPropertiesMap().setProperty(JsonDataSource.PROPERTY_FIELD_EXPRESSION, expression);
			field.setValueClass(String.class);
			return field;
		}
		return super.getAdapter(adapter);
	}
}
