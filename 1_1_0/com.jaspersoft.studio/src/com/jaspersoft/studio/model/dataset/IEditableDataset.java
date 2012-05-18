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

import net.sf.jasperreports.engine.JRDatasetRun;
import net.sf.jasperreports.engine.JRElementDataset;
import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.JRGroup;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.type.IncrementTypeEnum;
import net.sf.jasperreports.engine.type.ResetTypeEnum;

/**
 * This interface gives the ability to change the information regarding a dataset (usually a {@link JRElementDataset} instance).
 * A list of setters is provided so that when needed the user can implement a custom adapter to work with a specific dataset.
 * 
 * @author mrabbi
 * 
 * @see JRElementDataset
 *
 */
public interface IEditableDataset {

	/**
	 * Sets the new dataset run information for the dataset.
	 * 
	 * @param newDatasetRun
	 */
	void setDatasetRun(JRDatasetRun newDatasetRun);	
	
	/**
	 * Sets the new group for the increment feature.
	 * 
	 * @param newIncrementGroup
	 */
	void setIncrementGroup(JRGroup newIncrementGroup);
	
	/**
	 * Sets the new kind of increment. 
	 * 
	 * @param newIncrementType
	 */
	void setIncrementType(IncrementTypeEnum newIncrementType);
	
	/**
	 * Sets the new conditional increment expression.
	 * 
	 * @param newIncrementWhenExpression
	 */
	void setIncrementWhenExpression(JRExpression newIncrementWhenExpression);
	
	/**
	 * Sets the new reset group for the dataset.
	 * 
	 * @param newResetGroup
	 */
	void setResetGroup(JRGroup newResetGroup);
	
	/**
	 * Sets the new kind of reset.
	 * 
	 * @param newResetType
	 */
	void setResetType(ResetTypeEnum newResetType);
	
	/**
	 * Returns the instance of the element dataset we are modifying.
	 * 
	 * @return the edited dataset
	 */
	JRElementDataset getJRElementDataset();
	
	/**
	 * Returns the {@link JasperDesign} instance of the component, to which the element dataset belongs to. 
	 * 
	 * @return
	 */
	JasperDesign getJasperDesign();
}
