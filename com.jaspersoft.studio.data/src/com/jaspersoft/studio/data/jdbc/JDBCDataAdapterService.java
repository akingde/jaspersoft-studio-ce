/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.jdbc;

import net.sf.jasperreports.data.jdbc.JdbcDataAdapter;
import net.sf.jasperreports.data.jdbc.JdbcDataAdapterService;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.ParameterContributorContext;

/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: JRBaseBand.java 4319 2011-05-17 09:22:14Z teodord $
 */
public class JDBCDataAdapterService extends JdbcDataAdapterService {

	JdbcDataAdapter jdbcDataAdapter = null;

	public JDBCDataAdapterService(ParameterContributorContext paramContribContext, JdbcDataAdapter jdbcDataAdapter) {
		super(paramContribContext, jdbcDataAdapter);

		this.jdbcDataAdapter = jdbcDataAdapter;
	}

	@Override
	public String getPassword() throws JRException {

		System.out.println("Asking for password.... " + jdbcDataAdapter.getPassword());
		if (jdbcDataAdapter.getPassword() == null) {
			throw new JRException("FIXME: Password dialog not implemented!");
		}

		return jdbcDataAdapter.getPassword();
	}

}
