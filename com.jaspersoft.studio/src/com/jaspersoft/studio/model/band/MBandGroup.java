/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.model.band;

import java.util.List;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.group.MGroup;
import com.jaspersoft.studio.model.util.IIconDescriptor;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.engine.JRBand;
import net.sf.jasperreports.engine.JRSection;
import net.sf.jasperreports.engine.design.JRDesignBand;
import net.sf.jasperreports.engine.design.JRDesignGroup;
import net.sf.jasperreports.engine.type.BandTypeEnum;

/**
 * Abstract class with the common methods for the MBandGroupFooter and MBandGroupHeader
 * 
 * @author Orlandin Marco
 *
 */
public abstract class MBandGroup extends MBand {

	private static final long serialVersionUID = 2542088264599647492L;

	private static IPropertyDescriptor[] descriptors;
	
	/** 
	 * The jr group. 
	 */
	protected JRDesignGroup jrGroup;
	
	/**
	 * The model of the JRGroup
	 */
	protected MGroup mGroup;
	
	protected MGroupBand mGroupBand;

	/**
	 * Instantiates a new m band group header.
	 */
	public MBandGroup() {
		super();
	}


	public MBandGroup(ANode parent, JRDesignGroup jrGroup, JRBand jrband, BandTypeEnum bandtype, int newIndex){
		super(parent, jrband, bandtype, newIndex);
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
		//Since the refresh index of this method relay on the 
		//jrGroup != null, so when it is called by the super constructor chain
		//it dosen't do anything, so it must be called after the group node is completely
		//initialized
		refreshIndex(null, getValue());
	}

	/**
	 * Return the icon descriptor for the element 
	 * 
	 * @return a not null icon descriptor
	 */
	protected abstract IIconDescriptor getLocalIconDescriptor();
	
	/**
	 * Gets the jr group.
	 * 
	 * @return the jr group
	 */
	public JRDesignGroup getJrGroup() {
		return jrGroup;
	}

	/**
	 * Refresh the index of the band with the current number returned by getDesignIndex
	 */
	protected void refreshIndex(JRDesignBand oldValue, JRDesignBand newValue) {
		if (jrGroup != null){
			super.refreshIndex(oldValue, newValue);
		}
	}
	
	/**
	 * Return the JRSection of the actual band
	 * 
	 * @return a JRSection
	 */
	public abstract JRSection getSection();

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.band.MBand#getImagePath()
	 */
	@Override
	public ImageDescriptor getImagePath() {
		return getLocalIconDescriptor().getIcon16();
	}


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
		return super.isSameBandType(band) && jrGroup != null && jrGroup == ((MBandGroup) band).getJrGroup();
	}
	
	//Descriptor section
	

	/**
	 * Creates the property descriptors, they are identical for group header and footer
	 * 
	 * @param desc the desc
	 */
	@Override
	public void createPropertyDescriptors(List<IPropertyDescriptor> desc) {
		super.createPropertyDescriptors(desc);

		new MGroupBand(getJrGroup()).createPropertyDescriptors(desc);
	}

	@Override
	public IPropertyDescriptor[] getDescriptors() {
		return descriptors;
	}

	@Override
	public void setDescriptors(IPropertyDescriptor[] descriptors1) {
		descriptors = descriptors1;
	}

}
