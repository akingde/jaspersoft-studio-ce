/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.crosstab.model;

import com.jaspersoft.studio.editor.expression.ExpressionContext;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.APropertyNode;

import net.sf.jasperreports.crosstabs.JRCrosstabDataset;
import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JasperDesign;

/**
 * Abstract node used to provide the correct expression context
 * for row and column groups and measures
 * 
 * @author Orlandin Marco
 *
 */
public abstract class MDatasetGroupNode extends APropertyNode {
	
	private static final long serialVersionUID = -2300888399622574623L;

	public MDatasetGroupNode(ANode parent, int newIndex) {
		super(parent, newIndex);
	}
	
	public MDatasetGroupNode() {
		super();
	}
	
	/**
	 * Get the dataset run for the current crosstab and uses it to get the used dataset 
	 * and build the expression context on the dataset, that will be used for the group row and
	 * column nodes and from the measure nodes. If the crosstab is not using a subdataset the dataset
	 * run will be null and this will return the standard expression context of the main dataset, which
	 * is right
	 */
	@Override
	public ExpressionContext getExpressionContext() {
		MCrosstab crosstab = getMCrosstab();
		JasperDesign jd = getJasperDesign();
		if (crosstab != null && jd != null){
			JRCrosstabDataset crosstabDataset = crosstab.getValue().getDataset();
			if (crosstabDataset != null && crosstabDataset.getDatasetRun() != null){
				String name = crosstabDataset.getDatasetRun().getDatasetName();
				JRDataset dataset = jd.getDatasetMap().get(name);
				if (dataset != null){
					return new ExpressionContext((JRDesignDataset)dataset, getJasperConfiguration());
				}
			}
		}
		return null;
	}
	
	/**
	 * Go up trough the hierarchy until the crosstab node is found
	 * 
	 * @return the crosstab node that contains the current node, or null if it can't be found
	 */
	public MCrosstab getMCrosstab() {
		return CrosstabUtil.getMCrosstab(this);
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public Object getAdapter(Class adapter) {
		if (ExpressionContext.class.equals(adapter)){
			return getExpressionContext();
		} else return super.getAdapter(adapter);
	}
}
