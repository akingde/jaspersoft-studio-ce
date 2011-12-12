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

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.NumberFormat;
import java.util.Map;

import org.apache.commons.validator.routines.BigDecimalValidator;
import org.apache.commons.validator.routines.DoubleValidator;
import org.apache.commons.validator.routines.FloatValidator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;

public class BigNumericInput extends ADataInput {
	private Text num;

	public boolean isForType(Class<?> valueClass) {
		return (Long.class.isAssignableFrom(valueClass) || BigInteger.class.isAssignableFrom(valueClass)
				|| BigDecimal.class.isAssignableFrom(valueClass) || Float.class.isAssignableFrom(valueClass)
				|| Double.class.isAssignableFrom(valueClass) || Number.class.isAssignableFrom(valueClass));
	}

	@Override
	public void createInput(Composite parent, final IParameter param, final Map<String, Object> params) {
		super.createInput(parent, param, params);
		if (Number.class.isAssignableFrom(param.getValueClass())) {
			num = new Text(parent, SWT.BORDER);
			setMandatory(param, num);

			num.setToolTipText(param.getDescription());
			updateInput();
			num.addListener(SWT.Verify, new Listener() {

				public void handleEvent(Event e) {
					try {
						String number = e.text;
						String oldText = ((Text) e.widget).getText();
						if (e.start == 0)
							number = e.text + oldText;
						else
							number = oldText.substring(0, e.start) + e.text;
						if (oldText.length() - 1 > e.start + 1)
							number += oldText.substring(e.start + 1);

						if (number.equals("-")) //$NON-NLS-1$
							number = "-0";//$NON-NLS-1$
						if (number.equals(".")) //$NON-NLS-1$
							number = "0.";//$NON-NLS-1$

						if (param.getValueClass().equals(Long.class)) {
							Long.parseLong(number);
						} else if (param.getValueClass().equals(BigInteger.class)) {
							new BigInteger(number);
						} else if (param.getValueClass().equals(BigDecimal.class)) {
							e.doit = BigDecimalValidator.getInstance().isValid(number);
						} else if (param.getValueClass().equals(Float.class)) {
							e.doit = FloatValidator.getInstance().isValid(number);
						} else if (param.getValueClass().equals(Double.class)) {
							e.doit = DoubleValidator.getInstance().isValid(number);
						}
					} catch (NumberFormatException ne) {
						e.doit = false;
					}
				}
			});
			ModifyListener listener = new ModifyListener() {

				public void modifyText(ModifyEvent e) {
					try {
						Number n = null;
						if (param.getValueClass().equals(Long.class)) {
							n = new Long(num.getText());
						} else if (param.getValueClass().equals(BigInteger.class)) {
							n = new BigInteger(num.getText());
						} else if (param.getValueClass().equals(BigDecimal.class)) {
							n = new BigDecimal(num.getText());
						} else if (param.getValueClass().equals(Float.class)) {
							n = new Float(num.getText());
						} else if (param.getValueClass().equals(Double.class)) {
							n = new Double(num.getText());
						}
						updateModel(n);
					} catch (NumberFormatException ne) {

					}
				}
			};
			num.addModifyListener(listener);
			GridData gd = new GridData(GridData.FILL_HORIZONTAL);
			gd.horizontalIndent = 8;
			num.setLayoutData(gd);
		}
	}

	public void updateInput() {
		Object value = params.get(param.getName());
		if (value != null && value instanceof Number)
			num.setText(NumberFormat.getInstance().format(value));
	}

}
