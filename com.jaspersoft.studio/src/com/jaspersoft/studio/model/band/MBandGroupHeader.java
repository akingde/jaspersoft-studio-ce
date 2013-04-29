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
package com.jaspersoft.studio.model.band;

import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRBand;
import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.design.JRDesignBand;
import net.sf.jasperreports.engine.design.JRDesignGroup;
import net.sf.jasperreports.engine.type.BandTypeEnum;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.group.MGroup;
import com.jaspersoft.studio.model.util.IIconDescriptor;
import com.jaspersoft.studio.model.util.NodeIconDescriptor;

/*
 * The Class MBandGroupHeader.
 * 
 * @author Chicu Veaceslav
 */
public class MBandGroupHeader extends MBand {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
	/** The icon descriptor. */
	private static IIconDescriptor iconDescriptor;

	/**
	 * Gets the icon descriptor.
	 * 
	 * @return the icon descriptor
	 */
	public static IIconDescriptor getIconDescriptor() {
		if (iconDescriptor == null)
			iconDescriptor = new NodeIconDescriptor("groupheader"); //$NON-NLS-1$
		return iconDescriptor;
	}

	/** The jr group. */
	private JRDesignGroup jrGroup;

	/**
	 * Instantiates a new m band group header.
	 */
	public MBandGroupHeader() {
		super();
	}

	/**
	 * Instantiates a new m band group header.
	 * 
	 * @param parent
	 *          the parent
	 * @param jrGroup
	 *          the jr group
	 * @param jrband
	 *          the jrband
	 * @param index
	 *          the index
	 */
	public MBandGroupHeader(ANode parent, JRDesignGroup jrGroup, JRBand jrband, int index) {
		super(parent, jrband, BandTypeEnum.GROUP_HEADER, index);
		this.jrGroup = jrGroup;
		mGroup = new MGroup(null, jrGroup, -1);
		setChildListener(mGroup);
		mGroupBand = new MGroupBand(jrGroup);
	}

	/**
	 * Gets the jr group.
	 * 
	 * @return the jr group
	 */
	public JRDesignGroup getJrGroup() {
		return jrGroup;
	}

	private MGroup mGroup;

	public MGroup getMGroup() {
		return mGroup;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.band.MBand#getDisplayText()
	 */
	@Override
	public String getDisplayText() {
		if (getJrGroup() == null)
			return super.getDisplayText();
		JRDesignBand value = (JRDesignBand) getValue();
		if (value != null)
			return jrGroup.getName() + " " + Messages.MBandGroupHeader_group_header + " [" + value.getHeight() + "px] ";// + value.hashCode(); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return jrGroup.getName() + " " + Messages.MBandGroupHeader_group_header; //$NON-NLS-1$
	}

	@Override
	public String getSimpleDisplayName() {
		if (getJrGroup() == null)
			return super.getSimpleDisplayName();
		return jrGroup.getName() + " " + Messages.MBandGroupHeader_group_header; //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.band.MBand#getImagePath()
	 */
	@Override
	public ImageDescriptor getImagePath() {
		return getIconDescriptor().getIcon16();
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
	public void setDescriptors(IPropertyDescriptor[] descriptors1, Map<String, Object> defaultsMap1) {
		descriptors = descriptors1;
		defaultsMap = defaultsMap1;
	}

	/**
	 * Creates the property descriptors.
	 * 
	 * @param desc
	 *          the desc
	 */
	@Override
	public void createPropertyDescriptors(List<IPropertyDescriptor> desc, Map<String, Object> defaultsMap) {
		super.createPropertyDescriptors(desc, defaultsMap);

		new MGroupBand(getJrGroup()).createPropertyDescriptors(desc, defaultsMap);
	}

	private MGroupBand mGroupBand;

	@Override
	public Object getPropertyValue(Object id) {
		Object obj = mGroupBand.getPropertyValue(id);
		if (obj != null)
			return obj;
		return super.getPropertyValue(id);
	}

	@Override
	public void setPropertyValue(Object id, Object value) {
		mGroupBand.setPropertyValue(id, value);
		super.setPropertyValue(id, value);
	}

	@Override
	public Object getPropertyDefaultValue(String id) throws Exception {
		Object obj = mGroupBand.getPropertyDefaultValue(id);
		if (obj != null)
			return obj;
		return super.getPropertyDefaultValue(id);
	}

}
