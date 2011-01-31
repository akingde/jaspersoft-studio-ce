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
