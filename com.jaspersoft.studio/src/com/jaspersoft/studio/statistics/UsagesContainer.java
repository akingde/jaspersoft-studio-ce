/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.statistics;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.Assert;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Container for all the usage statistics collected. Can be converted
 * into a JSON string
 * 
 * @author Orlandin Marco
 *
 */
public class UsagesContainer {

	/**
	 * An anonymous identifier of the installation
	 */
	private String uuid;

	/**
	 * The list of all the statistics
	 */
	@JsonProperty("statistics")
	private List<UsageStatistic> statistics = new ArrayList<UsageStatistic>();

	/**
	 * Build the container 
	 * 
	 * @param uuid An anonymous and not null identifier of the installation
	 */
	@JsonCreator
	public UsagesContainer(@JsonProperty("uuid") String uuid) {
		Assert.isNotNull(uuid);
		this.uuid = uuid;
	}

	/**
	 * Add a statistic to the list
	 * 
	 * @param stat a not null usage statistic
	 * @return the current list of statistics with the new element added
	 */
	public List<UsageStatistic> addStat(UsageStatistic stat) {
		Assert.isNotNull(stat);
		statistics.add(stat);
		return statistics;
	}
	
	/**
	 * Return the uuid of the installation associated with the statistics
	 * 
	 * @return An anonymous and not null identifier of the installation
	 */
	public String getUUID(){
		return uuid;
	}
}
