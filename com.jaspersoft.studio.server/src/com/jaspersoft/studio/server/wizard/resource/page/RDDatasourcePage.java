/*
 * JasperReports - Free Java Reporting Library. Copyright (C) 2001 - 2012 Jaspersoft Corporation. All rights reserved.
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
package com.jaspersoft.studio.server.wizard.resource.page;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.server.messages.Messages;
import com.jaspersoft.studio.server.model.datasource.MRDatasource;
import com.jaspersoft.studio.server.model.datasource.MRDatasourceCustom;

public class RDDatasourcePage extends AResourcePage {

	public RDDatasourcePage(ANode parent, MRDatasource resource) {
		super(Messages.RDDatasourcePage_id, parent, resource);
		setTitle(Messages.RDDatasourcePage_title);
		setDescription(Messages.RDDatasourcePage_desc);
	}

	public RDDatasourcePage(ANode parent, MRDatasourceCustom resource) {
		super(Messages.RDDatasourcePage_id, parent, resource);
		setTitle(Messages.RDDatasourcePage_title);
		setDescription(Messages.RDDatasourcePage_desc);
	}

}
