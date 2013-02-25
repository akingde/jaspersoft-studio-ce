/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.cassandra.query;

/**
 * 
 * @author Eric Diaz
 * 
 */
public class CassandraSqlInClause extends CassandraAbstractInClause {
	protected static final String OPERATOR_IN = "IN";

	private static CassandraSqlInClause instance = new CassandraSqlInClause();

	private CassandraSqlInClause() {
	}

	public static CassandraSqlInClause getInstance() {
		return instance;
	}

	@Override
	protected void appendInOperator(StringBuffer stringBuffer) {
		stringBuffer.append(OPERATOR_IN);
	}
}
