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
package com.jaspersoft.studio.data;

import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

public class QueryDesigner extends AQueryDesigner {
	private boolean refresh = false;

	private final class QueryListener implements ModifyListener {

		public void modifyText(ModifyEvent e) {
			if (!refresh) {
				refresh = true;
				((JRDesignQuery) jDataset.getQuery()).setText(control.getText());
				refresh = false;
			}
		}
	}

	protected Text control;

	public QueryDesigner() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.data.IQueryDesigner#getControl()
	 */
	public Control getControl() {
		return control;
	}

	public Control createControl(Composite parent) {
		control = new Text(parent, SWT.MULTI | SWT.WRAP | SWT.V_SCROLL | SWT.H_SCROLL | SWT.BORDER);
		control.addModifyListener(new QueryListener());
		return control;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.data.IQueryDesigner#setQuery(java.lang.String)
	 */
	public void setQuery(JasperDesign jDesign, JRDataset jDataset) {
		super.setQuery(jDesign, jDataset);
		refresh = true;
		control.setText(jDataset.getQuery().getText());
		refresh = false;
	}

	public void dispose() {

	}
}
