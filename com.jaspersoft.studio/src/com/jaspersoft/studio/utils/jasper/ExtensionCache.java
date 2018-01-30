/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.utils.jasper;

import java.util.List;

/**
 * Utility class used as cache for a specific JasperReports Extension. It allow
 * to configure is the cache can be invalidated or not
 * 
 * @author Orlandin Marco
 */
public class ExtensionCache <T>{
	
	/**
	 * flag used to mark if the Extension need to be refreshed
	 */
	private boolean needRefresh = true;
	
	/**
	 * Flag used to mark if the extension is not refreshed on invalidation
	 */
	private boolean avoidInvalidation = false;
	
	/**
	 * The cached value
	 */
	private List<T> cache = null;
	
	/**
	 * Invalidate the cache discarding the cached value, but only if 
	 * the avoidInvalidation flag is set to valse
	 */
	public void invalidate() {
		if (!avoidInvalidation) {
			cache = null;
			needRefresh = true;
		}
	}
	
	/**
	 * Check if the cache is valid
	 * 
	 * @return if the avoid invalidation flag is set to true it will only
	 * check if there actually is a value of the cache, otherwise it will
	 * check also the needRefresh flag
	 */
	public boolean isValid() {
		if (avoidInvalidation) {
			return cache != null;
		} else {
			return cache != null && !needRefresh;
		}
	}
	
	/**
	 * Set the value inside the cache, marking it as valid
	 * 
	 * @param value the value to set, should be not null
	 */
	public void setValue(List<T> value) {
		this.cache = value;
		needRefresh = false;
	}
	
	/**
	 * Return the value from the cache, could be null if 
	 * the cahce is in invalid state
	 */
	public List<T> getValue(){
		return cache;
	}
	
	/**
	 * Set the avoid Invalidation flag, when set to true it 
	 * will avoid the cache to invalidate (even if the method invalidate is 
	 * called) and the only requirements to be vaild is the value to be 
	 * different from null
	 */
	public void setAvoidInvalidation(boolean value) {
		this.avoidInvalidation = value;
	}
}
