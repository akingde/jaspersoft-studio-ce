/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.model;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.wb.swt.ResourceManager;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.ICopyable;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.MRoot;
import com.jaspersoft.studio.model.util.IIconDescriptor;
import com.jaspersoft.studio.model.util.NodeIconDescriptor;
import com.jaspersoft.studio.property.descriptor.text.NTextPropertyDescriptor;
import com.jaspersoft.studio.server.Activator;
import com.jaspersoft.studio.server.ServerIconDescriptor;
import com.jaspersoft.studio.server.model.server.MServerProfile;
import com.jaspersoft.studio.server.protocol.Feature;
import com.jaspersoft.studio.server.protocol.IConnection;
import com.jaspersoft.studio.server.publish.PublishOptions;

import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.engine.JRConstants;

/* 
 * 
 * @author schicu
 *
 */
public abstract class AMResource extends APropertyNode implements ICopyable {

	public static final ImageDescriptor LINK_DECORATOR = Activator.getDefault()
			.getImageDescriptor("/icons/link_decorator.png");

	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	private static IPropertyDescriptor[] descriptors;

	public AMResource(ANode parent, ResourceDescriptor rd, int index) {
		super(parent, index);
		setValue(rd);
	}

	/** The icon descriptor. */
	private static NodeIconDescriptor iconDescriptor;

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
			if (getParent() instanceof MReportUnit) {
				MReportUnit mrunit = (MReportUnit) getParent();
				if (mrunit.getValue() != null && getValue() != null) {
					String par = mrunit.getValue().getUriString() + "_files";
					if (!par.equals(getValue().getParentFolder()))
						tip += " - Referenced";
				}
			}
			if (getValue().isMainReport())
				tip += "\nIs Main Report";
			tip += "\ndescription: " + Misc.nvl(getValue().getDescription());
			tip += "\nPermission: " + getValue().getPermissionMask(getWsClient());
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
		ImageDescriptor icon16 = getThisIconDescriptor().getIcon16();
		if (getParent() instanceof MReportUnit) {
			MReportUnit mrunit = (MReportUnit) getParent();
			if (mrunit.getValue() != null && getValue() != null) {
				String par = mrunit.getValue().getUriString() + "_files";
				if (!par.equals(getValue().getParentFolder()))
					return ResourceManager.decorateImage(icon16, LINK_DECORATOR, ResourceManager.BOTTOM_LEFT);
			}
		}
		return icon16;
	}

	@Override
	public IPropertyDescriptor[] getDescriptors() {
		return descriptors;
	}

	@Override
	public void setDescriptors(IPropertyDescriptor[] descriptors1) {
		descriptors = descriptors1;
	}

	@Override
	public void createPropertyDescriptors(List<IPropertyDescriptor> desc) {
		NTextPropertyDescriptor textD = new NTextPropertyDescriptor("SOMEPROPERTIES", Messages.common_datasource_name);
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
		if (parent != null) {
			if (parent instanceof AMResource)
				if (parent instanceof MFolder)
					rd.setParentFolder(((AMResource) parent).getValue().getUriString());
				else
					rd.setParentFolder(((AMResource) parent).getValue().getUriString() + "_files");
			else
				rd.setParentFolder("/");
		}
		return rd;
	}

	private boolean isEditMode = false;

	public boolean isEditMode() {
		return isEditMode;
	}

	public void setEditMode(boolean isEditMode) {
		this.isEditMode = isEditMode;
	}

	public boolean isInsideReportUnit() {
		return getReportUnit() != null;
	}

	private ANode mroot;

	public void setMRoot(ANode mroot) {
		this.mroot = mroot;
	}

	private transient IConnection wsClient;

	public IConnection getWsClient() {
		Object obj = getRoot();
		if (obj == null)
			obj = mroot;
		if (obj instanceof MServerProfile) {
			try {
				wsClient = ((MServerProfile) obj).getWsClient(new NullProgressMonitor());
				return wsClient;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return wsClient;
	}

	public boolean isSupported(Feature f) {
		IConnection c = getWsClient();
		if (c != null)
			return c.isSupported(f);
		return false;
	}

	public MReportUnit getReportUnit() {
		INode node = this;
		while (node != null && node.getParent() != null && !(node instanceof MServerProfile) && !(node instanceof MRoot)
				&& !(node instanceof MReportUnit)) {
			node = node.getParent();
		}
		if (node instanceof MReportUnit)
			return (MReportUnit) node;
		return null;
	}

	public ICopyable.RESULT isCopyable2(Object parent) {
		if (parent instanceof MFolder || parent instanceof MReportUnit || parent instanceof MServerProfile)
			return ICopyable.RESULT.COPYABLE;
		return ICopyable.RESULT.NOT_COPYABLE;
	}

	private transient PublishOptions publishOptions;

	public PublishOptions getPublishOptions() {
		if (publishOptions == null)
			publishOptions = new PublishOptions();
		return publishOptions;
	}

	public void setPublishOptions(PublishOptions publishOptions) {
		this.publishOptions = publishOptions;
	}

	public abstract String getJRSUrl() throws UnsupportedEncodingException;

	@Override
	public boolean isCuttable(ISelection currentSelection) {
		return true;
	}
}
