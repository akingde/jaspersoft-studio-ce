/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.utils.jasper;

import net.sf.jasperreports.repo.Resource;

public interface IFileResolverService {
	public <K extends Resource> K getResource(String uri, Class<K> resourceType, JasperReportsConfiguration jConfig);
}
