/*******************************************************************************
 * Copyright (C) 2005 - 2016 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.data.querydesigner.jsonql;

import com.jaspersoft.studio.data.querydesigner.json.JsonQueryDesigner;

import net.sf.jasperreports.data.json.JsonExpressionLanguageEnum;

/**
 * Query designer for JSONQL language.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public class JsonQLQueryDesigner extends JsonQueryDesigner {

	@Override
	protected String getLanguage() {
		return JsonExpressionLanguageEnum.JSONQL.getName();
	}
	
}
