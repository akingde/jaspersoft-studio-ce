/*
 * JasperReports - Free Java Reporting Library.
 * Copyright (C) 2001 - 2009 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of JasperReports.
 *
 * JasperReports is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JasperReports is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with JasperReports. If not, see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.server.model;

import java.util.List;
import java.util.Map;

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

/* 
 * 
 * @author schicu
 *
 */
public class MResource extends APropertyNode implements ICopyable {

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

	protected IIconDescriptor getThisIconDescriptor() {
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
		if (getValue().getDescription() != null)
			return getValue().getDescription();
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

	public static ResourceDescriptor createDescriptor(MResource parent) {
		ResourceDescriptor rd = new ResourceDescriptor();
		rd.setIsNew(true);
		rd.setParentFolder(parent.getValue().getUriString());
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
		while (!(node instanceof MServerProfile) && !(node instanceof MRoot)) {
			if (node.getParent() == null || node == null)
				return this;
			node = node.getParent();
		}
		return node;
	}

	public boolean isInsideReportUnit() {
		INode node = this;
		while (!(node instanceof MReportUnit)) {
			if (node.getParent() == null || node == null)
				return false;
			node = node.getParent();
		}
		return true;
	}

	public INode getReportUnit() {
		INode node = this;
		while (!(node instanceof MServerProfile) && !(node instanceof MRoot)
				&& !(node instanceof MReportUnit)) {
			if (node.getParent() == null || node == null)
				return this;
			node = node.getParent();
		}
		return node;
	}

	public boolean isCopyable2(Object parent) {
		if (parent instanceof MFolder || parent instanceof MReportUnit
				|| parent instanceof MServerProfile)
			return true;
		return false;
	}

}
