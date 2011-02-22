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
package com.jaspersoft.studio.utils;

import org.eclipse.swt.widgets.Spinner;

public class UIUtils {

	/**
	 * Set the value of a spinner.
	 * For convenience this method takes an object as value, but if the obj is null, or if it is not an Integer the method does nothing.
	 * If the displayed value is the same as the one provided, nothing is done (preventing on windows the whole selection of the number).
	 * @param spinner
	 * @param obj
	 */
	public static void setSpinnerSelection(Spinner spinner, Object obj)
	{
			if (obj == null) return;
			if (!(obj instanceof Integer)) return;
			int num = ((Integer)obj).intValue();
			if (spinner.getSelection() != num)
			{
				spinner.setSelection(num);
			}
	}
	
	/**
	 * Set the value of a spinner.
	 * For convenience this method takes an object as value, but if the obj is null, or if it is not an Integer the method uses the defValue.
	 * If the displayed value is the same as the one provided, nothing is done (preventing on windows the whole selection of the number).
	 * @param spinner
	 * @param obj
	 */
	public static void setSpinnerSelection(Spinner spinner, Object obj, int defValue)
	{
			int num = defValue;
			if (obj != null && obj instanceof Integer)
			{
				num = ((Integer)obj).intValue();
			}

			if (spinner.getSelection() != num)
			{
				spinner.setSelection(num);
			}
	}
	
}
