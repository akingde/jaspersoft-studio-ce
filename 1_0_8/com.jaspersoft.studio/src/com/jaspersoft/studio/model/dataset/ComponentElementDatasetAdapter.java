/*
 * JasperReports - Free Java Reporting Library. Copyright (C) 2001 - 2011 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of JasperReports.
 * 
 * JasperReports is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser General
 * Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * JasperReports is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License along with JasperReports. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.model.dataset;

import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.JRDatasetRun;
import net.sf.jasperreports.engine.JRElementDataset;
import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.JRGroup;
import net.sf.jasperreports.engine.design.JRDesignElementDataset;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.type.IncrementTypeEnum;
import net.sf.jasperreports.engine.type.ResetTypeEnum;


/**
 * Adapter for a dataset instance used in a generic component element.
 * The dataset used MUST be a valid subclass of the {@link JRDesignElementDataset} class.
 * This adapter can be used as input when dealing with generic dialogs/wizards/forms that modify an a "generic" dataset (instance of {@link JRElementDataset}).
 *  
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 *  @see JRElementDataset
 *  @see JRDesignElementDataset
 * 	@see JRDataset
 */
public class ComponentElementDatasetAdapter implements IEditableDataset{
	/* The wrapped dataset instance for the component element */
	private JRDesignElementDataset componentElementDataset;
	
	/* The JasperDesign object for the component element*/
	private JasperDesign jasperDesign;
	
	public ComponentElementDatasetAdapter(JRDesignElementDataset widgetDataset, JasperDesign jasperDesign){
		this.componentElementDataset=widgetDataset;
		this.jasperDesign=jasperDesign;
	}

	public void setDatasetRun(JRDatasetRun newDatasetRun) {
		componentElementDataset.setDatasetRun(newDatasetRun);
	}

	public void setIncrementGroup(JRGroup newIncrementGroup) {
		componentElementDataset.setIncrementGroup(newIncrementGroup);
	}

	public void setIncrementType(IncrementTypeEnum newIncrementType) {
		componentElementDataset.setIncrementType(newIncrementType);
	}

	public void setIncrementWhenExpression(JRExpression newIncrementWhenExpression) {
		componentElementDataset.setIncrementWhenExpression(newIncrementWhenExpression);
	}

	public void setResetGroup(JRGroup newResetGroup) {
		componentElementDataset.setResetGroup(newResetGroup);
	}

	public void setResetType(ResetTypeEnum newResetType) {
		componentElementDataset.setResetType(newResetType);
	}

	public JRElementDataset getJRElementDataset() {
		return componentElementDataset;
	}

	public JasperDesign getJasperDesign() {
		return jasperDesign;
	}
}
