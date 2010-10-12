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
package com.jaspersoft.studio.repository.wizard;

import java.util.Locale;
import java.util.TimeZone;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.model.datasource.AMDatasource;
import com.jaspersoft.studio.model.datasource.xml.MXMLDataSource;
import com.jaspersoft.studio.swt.widgets.WLocale;
import com.jaspersoft.studio.swt.widgets.WTimeZone;

public class XMLDatasourcePage extends AFileDatasourcePage {

	private Text xpathselectTXT;
	private WLocale localeC;
	private WTimeZone timezoneC;

	protected XMLDatasourcePage() {
		super("xmldatasourceeditor");
		setTitle("XML Datasource");
		setDescription("Creates a XML datasource.");
	}

	@Override
	public void dispose() {
		AMDatasource value = getValue();
		value.setPropertyValue(MXMLDataSource.PROPERTY_XPATHSELECT, xpathselectTXT.getText());
		value.setPropertyValue(MXMLDataSource.PROPERTY_XPATHTIMEZONE, timezoneC.getTimeZone());
		value.setPropertyValue(MXMLDataSource.PROPERTY_XPATHLOCALE, localeC.getLocale());
		super.dispose();
	}

	@Override
	protected void createMoreControls(Composite parent) {
		super.createMoreControls(parent);

		Label lbl1 = new Label(parent, SWT.NONE);
		lbl1.setText("XPath Select:");

		xpathselectTXT = new Text(parent, SWT.BORDER);
		xpathselectTXT.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		lbl1 = new Label(parent, SWT.NONE);
		lbl1.setText("XPath TimeZone:");

		timezoneC = new WTimeZone(parent, SWT.DROP_DOWN | SWT.BORDER);
		timezoneC.setSelection(TimeZone.getDefault());

		lbl1 = new Label(parent, SWT.NONE);
		lbl1.setText("XPath Locale:");

		localeC = new WLocale(parent, SWT.DROP_DOWN | SWT.BORDER);
		localeC.setSelection(Locale.getDefault());
	}

	@Override
	protected void setWidgets() {
		super.setWidgets();
		AMDatasource value = getValue();
		if (value != null) {
			String dsName = (String) value.getPropertyValue(MXMLDataSource.PROPERTY_XPATHSELECT);
			if (dsName == null)
				dsName = "";
			xpathselectTXT.setText(dsName);
			try {
				Locale locale = (Locale) value.getPropertyValue(MXMLDataSource.PROPERTY_XPATHLOCALE);
				if (locale == null)
					locale = (Locale) value.getPropertyDefaultValue(MXMLDataSource.PROPERTY_XPATHLOCALE);
				localeC.setSelection(locale);

				TimeZone timeZone = (TimeZone) value.getPropertyValue(MXMLDataSource.PROPERTY_XPATHTIMEZONE);
				if (timeZone == null)
					timeZone = (TimeZone) value.getPropertyDefaultValue(MXMLDataSource.PROPERTY_XPATHTIMEZONE);
				timezoneC.setSelection(timeZone);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	protected String[] getFilterExt() {
		return new String[] { "*.xml", "*.*" };
	}

}
