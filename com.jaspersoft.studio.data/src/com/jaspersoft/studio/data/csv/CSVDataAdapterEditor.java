/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2010 Jaspersoft Corporation. All
 * rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of Jaspersoft Open Studio.
 * 
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify it under the terms of the GNU Affero
 * General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * Jaspersoft Open Studio is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with Jaspersoft Open Studio. If not,
 * see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.data.csv;

import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.data.DataAdapter;
import com.jaspersoft.studio.data.DataAdapterEditor;

public class CSVDataAdapterEditor implements DataAdapterEditor {
	
	CSVDataAdapterComposite composite = null;

	@Override
	public void setDataAdapter(DataAdapter dataAdapter) {
		if (dataAdapter instanceof CSVDataAdapter) {
			this.composite.setDataAdapter((CSVDataAdapter)dataAdapter);
		}
	}

	@Override
	public DataAdapter getDataAdapter() {
		return this.composite.getDataAdapter();
	}

	@Override
	public Composite getComposite(Composite parent, int style) {
		if (composite == null) {
			composite = new CSVDataAdapterComposite(parent, style);
		}
		return composite;
	}

	@Override
	public String getHelpContextId() {
		return this.composite.getHelpContextId();
	}
}
