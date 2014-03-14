/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Jaspersoft Studio Team - initial API and implementation
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
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

/*
 * The Class MBandGroupFooter.
 * 
 * @author Chicu Veaceslav
 */
public class MBandGroupFooter extends MBand {
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
			iconDescriptor = new NodeIconDescriptor("groupfooter"); //$NON-NLS-1$
		return iconDescriptor;
	}

	/** The jr group. */
	private JRDesignGroup jrGroup;

	/**
	 * Instantiates a new m band group footer.
	 */
	public MBandGroupFooter() {
		super();
	}

	/**
	 * Instantiates a new m band group footer.
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
	public MBandGroupFooter(ANode parent, JRDesignGroup jrGroup, JRBand jrband, int index) {
		super(parent, jrband, BandTypeEnum.GROUP_FOOTER, index);
		this.jrGroup = jrGroup;
		mGroup = new MGroup(null, jrGroup, -1);
		setChildListener(mGroup);
		mGroupBand = new MGroupBand(jrGroup);
		// Fix missing jasper configuration
		if (parent != null) {
			JasperReportsConfiguration jconfig = parent.getJasperConfiguration();
			if (jconfig != null) {
				mGroup.setJasperConfiguration(jconfig);
				mGroupBand.setJasperConfiguration(jconfig);
			}
		}
		bandIndex = 1;
		refreshIndex();
	}

	private MGroup mGroup;

	public MGroup getMGroup() {
		return mGroup;
	}
	
	/**
	 * Update the name validator for the group when a new band connected
	 * to the group is selected
	 */
	@Override
	protected void postDescriptors(IPropertyDescriptor[] descriptors) {
		if (mGroup != null){
			mGroup.updateValidator();
		}
		super.postDescriptors(descriptors);
	}


	/**
	 * Gets the jr group.
	 * 
	 * @return the jr group
	 */
	public JRDesignGroup getJrGroup() {
		return jrGroup;
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
		String index = "";
		if (bandIndex != -1)
			index = " " + String.valueOf(bandIndex);
		if (value != null)
			return jrGroup.getName()
					+ " " + Messages.MBandGroupFooter_group_footer + index + " [" + value.getHeight() + "px] ";// + value.hashCode(); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return jrGroup.getName() + " " + Messages.MBandGroupFooter_group_footer + index; //$NON-NLS-1$
	}

	@Override
	public String getSimpleDisplayName() {
		if (getJrGroup() == null)
			return super.getSimpleDisplayName();
		String index = "";
		if (bandIndex != -1)
			index = " " + String.valueOf(bandIndex);
		return jrGroup.getName() + " " + Messages.MBandGroupFooter_group_footer + index; //$NON-NLS-1$
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

	@Override
	public boolean isSameBandType(MBand band) {
		return super.isSameBandType(band) && jrGroup != null && jrGroup == ((MBandGroupFooter) band).getJrGroup();
	}
}
