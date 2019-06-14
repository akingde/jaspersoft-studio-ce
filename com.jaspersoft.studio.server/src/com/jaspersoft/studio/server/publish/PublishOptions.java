/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.publish;

import java.util.ArrayList;
import java.util.List;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;

import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignExpression;

/**
 * @author slavic
 * 
 */
public class PublishOptions {
	private OverwriteEnum isOverwrite = OverwriteEnum.OVERWRITE;
	private List<JRDesignExpression> jExpression;
	private String expression;
	private JRDesignDataset dataset;
	private String filePath;

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setDataset(JRDesignDataset dataset) {
		this.dataset = dataset;
	}

	public JRDesignDataset getDataset() {
		return dataset;
	}

	public OverwriteEnum getOverwrite() {
		return isOverwrite;
	}

	public OverwriteEnum getOverwrite(OverwriteEnum ovw) {
		return Misc.nvl(isOverwrite, ovw);
	}

	public void setOverwrite(OverwriteEnum isOverwrite) {
		this.isOverwrite = isOverwrite;
	}

	public List<JRDesignExpression> getjExpression() {
		return jExpression;
	}

	public void setjExpression(JRDesignExpression jExpression) {
		if (this.jExpression == null)
			this.jExpression = new ArrayList<>();
		this.jExpression.add(jExpression);
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
	 * @return null if local, true if a reference, false if located in another place
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
		if (getReferencedResource() != null)
			return "\"repo:" + getReferencedResource().getUriString() + "\"";
		return null;
	}

	private ValueSetter<?> valueSetter;

	public void setValueSetter(ValueSetter<?> valueSetter) {
		this.valueSetter = valueSetter;
	}

	public ValueSetter<?> getValueSetter() {
		return valueSetter;
	}

	public abstract class ValueSetter<T> {

		public ValueSetter(T object) {
			this.object = object;
		}

		protected T object;

		public T getObject() {
			return object;
		}

		public abstract void setup();

		protected String value;

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

	}
}
