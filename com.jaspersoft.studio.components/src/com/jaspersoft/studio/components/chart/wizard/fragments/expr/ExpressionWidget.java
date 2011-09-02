package com.jaspersoft.studio.components.chart.wizard.fragments.expr;

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
	public ExpressionWidget(Composite parent, String label) {
		createControl(parent);
		expLabel.setText(label);
	}

	public void setEnabled(boolean enabled) {
		expButton.setEnabled(enabled);
		expText.setEnabled(enabled);
	}

	private void createControl(Composite parent) {
		expLabel = new Label(parent, SWT.NONE);

		expText = new Text(parent, SWT.BORDER);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.widthHint = 100;
		expText.setLayoutData(gd);
		expText.addModifyListener(new ModifyListener() {

			public void modifyText(ModifyEvent e) {
				setExpressionText(expText.getText());
			}
		});

		expButton = new Button(parent, SWT.PUSH);
		expButton.setText("...");
		expButton.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				JRExpressionEditor wizard = new JRExpressionEditor();
				wizard.setValue(expression);
				WizardDialog dialog = new WizardDialog(Display.getDefault()
						.getActiveShell(), wizard);
				dialog.create();
				if (dialog.open() == Dialog.OK) {
					setExpressionText(wizard.getValue().getText());
					if (expression != null)
						expression.setValueClassName(wizard.getValue()
								.getValueClassName());
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

	private void setExpression(JRDesignExpression exp) {
		this.expression = exp;
		try {
			if (obj != null)
				obj.getClass()
						.getMethod("set" + property,
								new Class[] { JRExpression.class })
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
		if (exp != null) {
			expText.setText(exp.getText());
			expText.setToolTipText(expText.getText());
		} else {
			expText.setText("");
			expText.setToolTipText("");
		}
	}

	private boolean isModMode = false;

	private void setExpressionText(String exptxt) {
		if (!isModMode) {
			isModMode = true;
			if (exptxt != null && !exptxt.isEmpty()) {
				if (expression == null)
					expression = new JRDesignExpression();
				expression.setText(exptxt);
			} else {
				expression = null;
			}
			setExpression(expression);
			isModMode = false;
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
				expr = (JRDesignExpression) obj.getClass()
						.getMethod("get" + property, new Class[0])
						.invoke(obj, new Object[0]);
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
