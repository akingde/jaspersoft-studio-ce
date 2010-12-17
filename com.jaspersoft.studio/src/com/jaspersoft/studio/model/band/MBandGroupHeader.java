/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2010 Jaspersoft Corporation. All
 * rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of Jaspersoft Open Studio.
 * 
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify it under the terms of the GNU Affero
 * General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * Jaspersoft Open Studio is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with Jaspersoft Open Studio. If not,
 * see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.model.band;

import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRBand;
import net.sf.jasperreports.engine.design.JRDesignBand;
import net.sf.jasperreports.engine.design.JRDesignGroup;
import net.sf.jasperreports.engine.type.BandTypeEnum;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.PropertyDescriptor;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.IIconDescriptor;
import com.jaspersoft.studio.model.NodeIconDescriptor;

/**
 * The Class MBandGroupHeader.
 * 
 * @author Chicu Veaceslav
 */
public class MBandGroupHeader extends MBand {
	private static final String MAIN_GROUP = "MAIN_GROUP"; //$NON-NLS-1$
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
		if (value != null)
			return jrGroup.getName() + " Group Header [" + value.getHeight() + "px] ";// + value.hashCode();
		return jrGroup.getName() + " Group Header";
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
	public void createPropertyDescriptors(List<IPropertyDescriptor> desc, Map<String, Object> defaultsMap) {
		super.createPropertyDescriptors(desc, defaultsMap);

		PropertyDescriptor groupD = new PropertyDescriptor(MAIN_GROUP, Messages.MBandGroupHeader_group);
		desc.add(groupD);
	}

	private MGroupBand mGroupBand;

	@Override
	public Object getPropertyValue(Object id) {
		if (id.equals(MAIN_GROUP)) {
			if (mGroupBand == null) {
				mGroupBand = new MGroupBand(getJrGroup());
				setChildListener(mGroupBand);
			}
			return mGroupBand;
		}
		return super.getPropertyValue(id);
	}

}
