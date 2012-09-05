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
package com.jaspersoft.studio.editor.preview.input;

import java.util.Locale;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.swt.widgets.WLocale;

public class LocaleInput extends ADataInput {
	private WLocale wlocal;

	public boolean isForType(Class<?> valueClass) {
		return Locale.class.isAssignableFrom(valueClass);
	}

	@Override
	public void createInput(Composite parent, final IParameter param, final Map<String, Object> params) {
		super.createInput(parent, param, params);
		if (Locale.class.isAssignableFrom(param.getValueClass())) {
			wlocal = new WLocale(parent, SWT.DROP_DOWN | SWT.BORDER);
			wlocal.setToolTipText(param.getDescription());
			wlocal.addFocusListener(focusListener);
			GridData gd = new GridData(GridData.FILL_HORIZONTAL);
			gd.horizontalIndent = 8;
			wlocal.setLayoutData(gd);
			if (params.get(param.getName()) != null)
				wlocal.setSelection((Locale) params.get(param.getName()));
			wlocal.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					updateModel(wlocal.getLocale());
				}

			});
		}
	}

	public void updateInput() {
		Object value = params.get(param.getName());
		if (value != null && value instanceof Locale)
			wlocal.setSelection((Locale) value);
	}
}
