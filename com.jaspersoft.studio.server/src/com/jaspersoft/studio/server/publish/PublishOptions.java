/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
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
package com.jaspersoft.studio.server.publish;

import net.sf.jasperreports.engine.design.JRDesignExpression;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;

/**
 * @author slavic
 * 
 */
public class PublishOptions {
	private boolean isOverwrite = true;
	private JRDesignExpression jExpression;
	private String expression;

	public boolean isOverwrite() {
		return isOverwrite;
	}

	public void setOverwrite(boolean isOverwrite) {
		this.isOverwrite = isOverwrite;
	}

	public JRDesignExpression getjExpression() {
		return jExpression;
	}

	public void setjExpression(JRDesignExpression jExpression) {
		this.jExpression = jExpression;
	}

	public String getExpression() {
		return expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}

	private ResourceDescriptor referencedResource;
	private ResourcePublishMethod publishMethod = ResourcePublishMethod.LOCAL;

	public ResourceDescriptor getReferencedResource() {
		return referencedResource;
	}

	public void setReferencedResource(ResourceDescriptor referencedResource) {
		this.referencedResource = referencedResource;
	}

	/**
	 * @return null if local, true if a reference, false if located in another
	 *         place
	 */
	public ResourcePublishMethod getPublishMethod() {
		return publishMethod;
	}

	public void setPublishMethod(ResourcePublishMethod publishMethod) {
		this.publishMethod = publishMethod;
		if (publishMethod == ResourcePublishMethod.LOCAL)
			referencedResource = null;
	}

	public String getRepoExpression() {
		return "\"repo:" + getReferencedResource().getUriString() + "\"";
	}

}
