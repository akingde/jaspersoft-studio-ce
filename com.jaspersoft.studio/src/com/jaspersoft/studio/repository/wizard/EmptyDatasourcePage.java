/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer.
 * Copyright (C) 2005 - 2010 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of Jaspersoft Open Studio.
 *
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Jaspersoft Open Studio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with Jaspersoft Open Studio. If not, see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.repository.wizard;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.datasource.AMDatasource;
import com.jaspersoft.studio.model.datasource.empty.MEmptyDataSource;

public class EmptyDatasourcePage extends ADatasourcePage {

	private Spinner sizeTxt;

	protected EmptyDatasourcePage() {
		super("emptydatasourceeditor"); //$NON-NLS-1$
		setTitle(Messages.EmptyDatasourcePage_empty_datasource);
		setDescription(Messages.EmptyDatasourcePage_description);
	}

	@Override
	public void dispose() {
		AMDatasource value = getValue();
		value.setPropertyValue(MEmptyDataSource.PROPERTY_SIZE, new Integer(sizeTxt.getSelection()));
		super.dispose();
	}

	@Override
	protected void createMoreControls(Composite parent) {
		Label lbl1 = new Label(parent, SWT.NONE);
		lbl1.setText(Messages.common_size+":"); //$NON-NLS-1$

		sizeTxt = new Spinner(parent, SWT.BORDER);
		sizeTxt.setValues(0, 0, Integer.MAX_VALUE, 0, 1, 10);
		sizeTxt.setToolTipText(Messages.EmptyDatasourcePage_size_tool_tip);
	}

	@Override
	protected void setWidgets() {
		super.setWidgets();
		AMDatasource value = getValue();
		if (value != null) {
			Integer dsName = (Integer) value.getPropertyValue(MEmptyDataSource.PROPERTY_SIZE);
			if (dsName == null)
				dsName = new Integer(1);
			sizeTxt.setSelection(dsName);

		}
	}
}
