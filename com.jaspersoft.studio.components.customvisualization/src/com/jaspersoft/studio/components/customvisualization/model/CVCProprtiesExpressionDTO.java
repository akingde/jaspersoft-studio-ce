/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.customvisualization.model;

import java.util.List;

import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.components.items.ItemProperty;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.JRCloneUtils;

/**
 * DTO used by the CVC when returning or setting the properties of the component. It carry
 * not only the properties but also information about the report and the component, this
 * information are necessary when handling the properties in the advanced view
 * 
 * @author Orlandin Marco
 *
 */
public class CVCProprtiesExpressionDTO {

	private List<ItemProperty> itemProps;
	
	private MCustomVisualization node;
	
	private JasperDesign jd;
	
	private JasperReportsConfiguration jConfig;
	
	public CVCProprtiesExpressionDTO(List<ItemProperty> itemProps, MCustomVisualization node, JasperDesign jd, JasperReportsConfiguration jConfig) {
		this.itemProps = itemProps;
		this.node = node;
		this.jd = jd;
		this.jConfig = jConfig;
	}

	public List<ItemProperty> getItemProps() {
		return itemProps;
	}

	public MCustomVisualization getNode() {
		return node;
	}

	public JasperDesign getJd() {
		return jd;
	}

	public JasperReportsConfiguration getjConfig() {
		return jConfig;
	}
	
	public CVCProprtiesExpressionDTO clone() {
		List<ItemProperty> data = JRCloneUtils.cloneList(itemProps);
		return new CVCProprtiesExpressionDTO(data, node, jd, jConfig);
	}
}
