/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.statistics;

import org.eclipse.core.runtime.Assert;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Container for a single statistic, can be converted into a JSON string
 * 
 * @author Orlandin Marco
 *
 */
public class UsageStatistic {
	
	/**
	 * The identifier of the action
	 */
	private String id;
	
	/**
	 * The category of the action
	 */
	private String category;
	
	/**
	 * How many times the action was used
	 */
	private int usagesNumber;
	
	/**
	 * define in which version the action was used the specific number of times
	 */
	private String version;
	
	/**
	 * Build the container of the statistic
	 * 
	 * @param id A not null identifier of the action
	 * @param category A not null category of the action
	 * @param version define in which version the action was used the specific number of times
	 * @param usagesNumber a positive number of usages for the action
	 */
	@JsonCreator
	public UsageStatistic(@JsonProperty("id") String id, 
												@JsonProperty("category") String category, 
												@JsonProperty("version") String version, 
												@JsonProperty("usagesNumber") int usagesNumber){
		Assert.isNotNull(id);
		Assert.isNotNull(category);
		Assert.isTrue(usagesNumber>=0);
		this.id = id;
		this.category = category;
		this.usagesNumber = usagesNumber;
		this.version = version;
	}
	
	/**
	 * Return the action id
	 * 
	 * @return A not null identifier of the action
	 */
	public String getId(){
		return id;
	}
	
	/**
	 * Return the action category
	 * 
	 * @return A not null category of the action
	 */
	public String getCategory(){
		return category;
	}
	
	/**
	 * Return the number of usages of the action
	 * 
	 * @return  a positive number of usages for the action
	 */
	public int getUsagesNumber(){
		return usagesNumber;
	}
	
	/**
	 * Define in which version the action was used the specific number of times
	 * 
	 * @return a not null string
	 */
	public String getVersion(){
		return version;
	}
}
