/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.map.model.itemdata.dto;
 
import net.sf.jasperreports.components.items.ItemData;
import net.sf.jasperreports.engine.JRCloneable;
import net.sf.jasperreports.engine.JRElementDataset;

/**
 * This DTO allows to wrap the dataset that can be used by an {@link ItemData} element.
 * 
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public class MapDataDatasetDTO implements JRCloneable{

	private JRElementDataset dataset;
	private String name;

	public JRElementDataset getDataset() {
		return dataset;
	}

	public void setDataset(JRElementDataset dataset) {
		this.dataset = dataset;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Object clone() {
		MapDataDatasetDTO clone = new MapDataDatasetDTO();
		clone.setName(getName());
		clone.setDataset((JRElementDataset) getDataset().clone());
		return clone;
	}
}
