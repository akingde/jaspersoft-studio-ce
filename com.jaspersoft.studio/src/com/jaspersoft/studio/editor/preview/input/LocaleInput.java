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
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.swt.widgets.WLocale;

public class LocaleInput implements IDataInput {
	public boolean isForType(Class<?> valueClass) {
		if (Locale.class.isAssignableFrom(valueClass))
			return true;
		return false;
	}

	public boolean createInput(Composite parent, final IParameter param, final Map<String, Object> params) {
		Class<?> valueClass = param.getValueClass();
		if (Locale.class.isAssignableFrom(valueClass)) {
			final WLocale txt = new WLocale(parent, SWT.DROP_DOWN | SWT.BORDER);
			txt.setToolTipText(param.getDescription());
			txt.setBackground(txt.getDisplay().getSystemColor(SWT.COLOR_WHITE));
			txt.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
			if (params.get(param.getName()) != null)
				txt.setSelection((Locale) params.get(param.getName()));
			txt.addSelectionListener(new SelectionListener() {

				public void widgetSelected(SelectionEvent e) {
					params.put(param.getName(), txt.getLocale());
				}

				public void widgetDefaultSelected(SelectionEvent e) {

				}
			});
			return true;
		}
		return false;
	}
}
