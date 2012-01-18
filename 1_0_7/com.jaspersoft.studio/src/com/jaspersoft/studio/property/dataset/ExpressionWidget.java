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
package com.jaspersoft.studio.property.dataset;

import java.lang.reflect.InvocationTargetException;

import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.design.JRDesignExpression;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.property.descriptor.expression.dialog.JRExpressionEditor;

public class ExpressionWidget {
	private String label;

	public ExpressionWidget(Composite parent, String label) {
		this.label = label;
		createControl(parent);
		if (label != null)
			expLabel.setText(label);
	}

	public void setEnabled(boolean enabled) {
		expButton.setEnabled(enabled);
		expText.setEnabled(enabled);
	}

	private void createControl(Composite parent) {
		if (label != null)
			expLabel = new Label(parent, SWT.NONE);

		expText = new Text(parent, SWT.BORDER);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.widthHint = 100;
		expText.setLayoutData(gd);
		expText.addModifyListener(new ModifyListener() {

			public void modifyText(ModifyEvent e) {
				setExpressionText(expText.getText(), null);
			}
		});

		expButton = new Button(parent, SWT.PUSH | SWT.FLAT);
		expButton.setText("...");
		expButton.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				JRExpressionEditor wizard = new JRExpressionEditor();
				wizard.setValue(expression);
				WizardDialog dialog = new WizardDialog(Display.getDefault().getActiveShell(), wizard);
				dialog.create();
				if (dialog.open() == Dialog.OK) {
					setExpressionText(wizard.getValue().getText(), wizard.getValue().getValueClassName());
				}
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});
	}

	private JRDesignExpression expression;
	private Text expText;
	private Label expLabel;

	public void setExpression(JRDesignExpression exp) {
		this.expression = exp;
		setOnParent(exp);
		
		if (exp != null && exp.getText() != null) {
			
			if (!exp.getText().equals(expText.getText()))
			{
				expText.setText(exp.getText());
				expText.setToolTipText(expText.getText());
			}
		}
		else{
			expText.setText("");
			expText.setToolTipText("");
		}
	}

	protected void setOnParent(JRDesignExpression exp) {
		try {
			if (obj != null)
				obj.getClass().getMethod("set" + property, new Class[] { JRExpression.class })
						.invoke(obj, new Object[] { exp });
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
	}

	private boolean isModMode = false;

	private void setExpressionText(String exptxt, String valueClass) {
		if (!isModMode) {
			isModMode = true;
			try {
				if (exptxt != null && !exptxt.isEmpty()) {
					if (expression == null)
						expression = new JRDesignExpression();
					expression.setText(exptxt);
				} else {
					expression = null;
				}
				if (valueClass != null && expression != null)
					expression.setValueClassName(valueClass);
				setExpression(expression);
			} finally {
				isModMode = false;
			}
		}
	}

	private Object obj;
	private String property;
	private Button expButton;

	public void bindObject(Object obj, String property) {
		this.obj = obj;
		this.property = property;
		try {
			JRDesignExpression expr = null;
			if (obj != null)
				expr = (JRDesignExpression) obj.getClass().getMethod("get" + property, new Class[0]).invoke(obj, new Object[0]);
			setExpression(expr);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
	}
}
