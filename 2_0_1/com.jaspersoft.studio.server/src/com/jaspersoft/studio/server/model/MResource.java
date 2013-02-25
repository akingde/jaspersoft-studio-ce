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
package com.jaspersoft.studio.server.model;

import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRConstants;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.ICopyable;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.MRoot;
import com.jaspersoft.studio.model.util.IIconDescriptor;
import com.jaspersoft.studio.property.descriptor.text.NTextPropertyDescriptor;
import com.jaspersoft.studio.server.ServerIconDescriptor;
import com.jaspersoft.studio.server.model.server.MServerProfile;
import com.jaspersoft.studio.server.publish.PublishOptions;
import com.jaspersoft.studio.utils.Misc;

/* 
 * 
 * @author schicu
 *
 */
public class MResource extends APropertyNode implements ICopyable {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	public MResource(ANode parent, ResourceDescriptor rd, int index) {
		super(parent, index);
		setValue(rd);
	}

	/** The icon descriptor. */
	private static IIconDescriptor iconDescriptor;

	/**
	 * Gets the icon descriptor.
	 * 
	 * @return the icon descriptor
	 */
	public static IIconDescriptor getIconDescriptor() {
		if (iconDescriptor == null)
			iconDescriptor = new ServerIconDescriptor("resource"); //$NON-NLS-1$
		return iconDescriptor;
	}

	public IIconDescriptor getThisIconDescriptor() {
		return getIconDescriptor();
	}

	@Override
	public ResourceDescriptor getValue() {
		return (ResourceDescriptor) super.getValue();
	}

	public String getDisplayText() {
		if (getValue().getLabel() != null)
			return getValue().getLabel();
		return getThisIconDescriptor().getTitle();
	}

	@Override
	public String getToolTip() {
		if (getValue() != null) {
			String tip = "name: " + getValue().getName();
			tip += "\nuri: " + getValue().getUriString();
			tip += "\ntype: " + getValue().getWsType();
			tip += "\ndescription: " + Misc.nvl(getValue().getDescription());
			return tip;
		}
		return getThisIconDescriptor().getToolTip();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.INode#getImagePath()
	 */
	public ImageDescriptor getImagePath() {
		return getThisIconDescriptor().getIcon16();
	}

	private static IPropertyDescriptor[] descriptors;
	private static Map<String, Object> defaultsMap;

	@Override
	public Map<String, Object> getDefaultsMap() {
		return defaultsMap;
	}

	@Override
	public IPropertyDescriptor[] getDescriptors() {
		return descriptors;
	}

	@Override
	public void setDescriptors(IPropertyDescriptor[] descriptors1,
			Map<String, Object> defaultsMap1) {
		descriptors = descriptors1;
		defaultsMap = defaultsMap1;
	}

	@Override
	public void createPropertyDescriptors(List<IPropertyDescriptor> desc,
			Map<String, Object> defaultsMap) {
		NTextPropertyDescriptor textD = new NTextPropertyDescriptor(
				"SOMEPROPERTIES", Messages.common_datasource_name);
		desc.add(textD);
	}

	public Object getPropertyValue(Object id) {
		return null;
	}

	public void setPropertyValue(Object id, Object value) {

	}

	public static ResourceDescriptor createDescriptor(ANode parent) {
		ResourceDescriptor rd = new ResourceDescriptor();
		rd.setIsNew(true);
		rd.setIsReference(false);
		rd.setName("NewResource");
		// rd.setLabel(rd.getName());
		if (parent instanceof MResource)
			rd.setParentFolder(((MResource) parent).getValue().getUriString());
		else
			rd.setParentFolder("/");
		return rd;
	}

	private boolean isEditMode = false;

	public boolean isEditMode() {
		return isEditMode;
	}

	public void setEditMode(boolean isEditMode) {
		this.isEditMode = isEditMode;
	}

	@Override
	public INode getRoot() {
		INode node = this;
		while (node != null && node.getParent() != null
				&& !(node instanceof MServerProfile)
				&& !(node instanceof MRoot)) {
			node = node.getParent();
		}
		return node;
	}

	public boolean isInsideReportUnit() {
		return getReportUnit() != null;
	}

	public MReportUnit getReportUnit() {
		INode node = this;
		while (node != null && node.getParent() != null
				&& !(node instanceof MServerProfile)
				&& !(node instanceof MRoot) && !(node instanceof MReportUnit)) {
			node = node.getParent();
		}
		if (node instanceof MReportUnit)
			return (MReportUnit) node;
		return null;
	}

	public boolean isCopyable2(Object parent) {
		if (parent instanceof MFolder || parent instanceof MReportUnit
				|| parent instanceof MServerProfile)
			return true;
		return false;
	}

	private PublishOptions publishOptions;

	public PublishOptions getPublishOptions() {
		if (publishOptions == null)
			publishOptions = new PublishOptions();
		return publishOptions;
	}

	public void setPublishOptions(PublishOptions publishOptions) {
		this.publishOptions = publishOptions;
	}
}
