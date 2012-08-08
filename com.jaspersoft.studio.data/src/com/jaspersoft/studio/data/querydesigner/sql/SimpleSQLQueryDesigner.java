/*******************************************************************************
 * Copyright (C) 2005 - 2012 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License  as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero  General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package com.jaspersoft.studio.data.querydesigner.sql;

import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.jaspersoft.studio.data.designer.QueryDesigner;

/**
 * Simple SQL query designer that simply provides syntax coloring support.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public class SimpleSQLQueryDesigner extends QueryDesigner {

	// Line style for SQL code
	private SQLLineStyler sqlLineStyler;
	
	public SimpleSQLQueryDesigner() {
		super();
		sqlLineStyler=getSQLBasedLineStyler();
	}

	@Override
	public Control createControl(Composite parent) {
		super.createControl(parent);
		control.addLineStyleListener(sqlLineStyler);
		control.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				sqlLineStyler.parseBlockComments(control.getText());
			}
		});
		return control;
	}
	
	/**
	 * @return the SQL line styler
	 */
	protected SQLLineStyler getSQLBasedLineStyler(){
		return new SQLLineStyler();
	}
	
}
