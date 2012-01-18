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


import net.sf.jasperreports.engine.JRDatasetParameter;
import net.sf.jasperreports.engine.JRDatasetRun;
import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.design.JRDesignDatasetRun;

/**
 * This interface gives the ability to change the information regarding a dataset run (usually a {@link JRDesignDatasetRun} instance).
 * A list of setters is provided so that when needed the user can implement a custom adapter to work with a specific dataset.
 * 
 * @author mrabbi
 *
 */
public interface IEditableDatasetRun {

	/**
	 * Sets the new dataset name for the dataset run.
	 * 
	 * @param newDatasetName new dataset name
	 */
	void setDatasetName(String newDatasetName);

	/**
	 * Sets the new expression for the parameters map of the dataset run.
	 * 
	 * @param newParametersMapExp new expression
	 */
	void setParametersMapExpression(JRExpression newParametersMapExp);

	/**
	 * Sets the array of the new parameters for the dataset run.
	 * 
	 * @param newParameters new parameters
	 */
	void setParameters(JRDatasetParameter[] newParameters);
	
	/**
	 * Adds a new parameter value to the list of current ones.
	 * 
	 * @param newParameter the parameter value to add
	 */
	void addParameter(JRDatasetParameter newParameter);
	
	/**
	 * Removes a parameter value from the list of current ones.
	 * 
	 * @param oldParameter the parameter value to remove
	 */
	void removeParameter(JRDatasetParameter oldParameter);

	/**
	 * Sets the new connection expression for the dataset run.
	 * 
	 * @param newConnectionExp new expression
	 */
	void setConnectionExpression(JRExpression newConnectionExp);

	/**
	 * Sets the new data source expression for the dataset run.
	 * 
	 * @param newDataSourceExp new expression
	 */
	void setDataSourceExpression(JRExpression newDataSourceExp);

	/**
	 * Returns the "editable" dataset to which this dataset run belongs to.
	 * 
	 * @return the container dataset editable instance
	 */
	IEditableDataset getEditableDataset();
	
	/**
	 * Returns the instance of the dataset run currently being manipulated.
	 * 
	 * @return the edited dataset run
	 */
	JRDatasetRun getJRDatasetRun();
		
	/**
	 * Checks if the dataset run is set or not.
	 * When no real dataset run instance exists, this means that the 
	 * report main dataset is used for all operations. 
	 * In this particular case calling any of the setter method will do nothing.
	 * <p>
	 * <b>NOTE</b>: The user can alter the behavior using the {@link #resetDatasetRun(boolean)} method.
	 *   
	 * @return <code>true</code> if the report instance is supposed to be used
	 */
	boolean useReportMainDataset();
	
	/**
	 * This method resets the information of the "editable" dataset run.
	 * If the input flag is set to <code>true</code>, than the dataset run instance is set to <code>null</code>.
	 * Otherwise the dataset run will become equivalent to a new empty dataset run.
	 * 
	 * @param nullableFlag specifies the kind of reset
	 * @see {@link #useReportMainDataset()}
	 */
	void resetDatasetRun(boolean nullableFlag);
}
