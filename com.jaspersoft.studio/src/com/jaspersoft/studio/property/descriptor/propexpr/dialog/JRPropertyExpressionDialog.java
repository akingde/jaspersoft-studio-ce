/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.descriptor.propexpr.dialog;

import java.util.Collections;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import com.jaspersoft.studio.model.dataset.DatasetPropertyExpressionDTO;
import com.jaspersoft.studio.property.descriptor.properties.dialog.PropertyDTO;
import com.jaspersoft.studio.property.descriptor.propexpr.PropertyExpressionDTO;
import com.jaspersoft.studio.swt.events.ExpressionModifiedEvent;
import com.jaspersoft.studio.swt.events.ExpressionModifiedListener;
import com.jaspersoft.studio.swt.widgets.WTextExpression;

import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.type.PropertyEvaluationTimeEnum;
import net.sf.jasperreports.properties.PropertyMetadata;

/**
 * Dialog that extend the dialog to define a property as key and value. This extension allow to use an expression as
 * value
 * 
 * @author Orlandin Marco
 *
 */
public class JRPropertyExpressionDialog extends JRPropertyDialog {
	/**
	 * Checkbutton to choose if to use a textual value or an expression
	 */
	protected Button buseexpr;

	/**
	 * Control where the expression can be placed
	 */
	protected WTextExpression evalue;

	/**
	 * Container of the expression control
	 */
	protected Composite vexp;

	/**
	 * Boolean guard to avoid recursive calls when the text of the value is modified from the modify listener itself
	 */
	private boolean updating = false;

	private Combo cevalTime;

	public JRPropertyExpressionDialog(Shell parentShell) {
		super(parentShell);
	}

	/**
	 * The hints are initialized using the type of the actual node
	 */
	@Override
	protected void initializeHints() {
		hints = HintsPropertiesList.getElementProperties(value.getJrElement(), value.geteContext());
		Collections.sort(hints);
	}

	@Override
	protected ModifyListener getModifyListener() {
		return new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				String newtext = cprop.getText();
				if (propertiesSuggestions != null)
					propertiesSuggestions.showOnlyElement(newtext);
				value.setName(newtext);

				if (!value.isExpression())
					for (PropertyMetadata ed : HintsPropertiesList.getPropertiesMetadata(value.getJrElement(),
							value.geteContext()))
						if (ed.getName().equals(newtext) && ed.getDefaultValue() != null) {
							tvalue.setText(ed.getDefaultValue());
							break;
						}
			}
		};
	}

	private boolean showExpression = true;

	public void setShowExpression(boolean showExpression) {
		this.showExpression = showExpression;
	}

	/**
	 * Create the checkbox
	 */
	protected void createAdditionalControls(Composite parent) {
		if (showExpression) {
			buseexpr = new Button(parent, SWT.CHECK);
			buseexpr.setText(Messages.JRPropertyExpressionDialog_0);
			GridData gd = new GridData(GridData.FILL_HORIZONTAL);
			gd.horizontalSpan = 2;
			buseexpr.setLayoutData(gd);
		}
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite composite = (Composite) super.createDialogArea(parent);
		tvalue.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				synchText();
				value.setValue(tvalue.getText());
			}
		});
		vexp = createValueExpressionControl(stackComposite);
		if (buseexpr != null)
			buseexpr.addSelectionListener(new SelectionListener() {

				@Override
				public void widgetSelected(SelectionEvent e) {
					stackLayout.topControl = buseexpr.getSelection() ? vexp : vcmp;
					stackComposite.layout();
					((PropertyExpressionDTO) value).setExpression(buseexpr.getSelection());
					if (buseexpr.getSelection())
						value.setValue(evalue.getExpression().getText());
					else
						value.setValue(tvalue.getText());
				}

				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					widgetDefaultSelected(e);
				}
			});
		fillValue(value);
		return composite;
	}

	/**
	 * Crate the control to insert an expression
	 * 
	 * @param cmp
	 *          the parent where the control will be placed
	 * @return container of the control
	 */
	private Composite createValueExpressionControl(Composite cmp) {
		Composite composite = new Composite(cmp, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));

		if (value instanceof DatasetPropertyExpressionDTO) {
			Label label = new Label(composite, SWT.NONE);
			label.setText(Messages.JRPropertyExpressionDialog_1);

			cevalTime = new Combo(composite, SWT.READ_ONLY);
			cevalTime.setItems(new String[] { PropertyEvaluationTimeEnum.EARLY.getName(),
					PropertyEvaluationTimeEnum.REPORT.getName(), PropertyEvaluationTimeEnum.LATE.getName() });
			cevalTime.addModifyListener(new ModifyListener() {

				@Override
				public void modifyText(ModifyEvent e) {
					((DatasetPropertyExpressionDTO) value).setEvalTime(PropertyEvaluationTimeEnum.byName(cevalTime.getText()));
				}
			});
		}

		// Label label = new Label(composite, SWT.NONE);
		// label.setText("Value Expression");
		// GridData gd = new GridData();
		// gd.horizontalSpan = 2;
		// label.setLayoutData(gd);

		evalue = new WTextExpression(composite, SWT.NONE, 1);
		evalue.addModifyListener(new ExpressionModifiedListener() {
			@Override
			public void expressionModified(ExpressionModifiedEvent event) {
				synchText();
			}
		});
		GridData gd = new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_FILL);
		gd.horizontalSpan = 2;
		gd.heightHint = 80;
		evalue.setLayoutData(gd);
		evalue.addModifyListener(new ExpressionModifiedListener() {
			@Override
			public void expressionModified(ExpressionModifiedEvent event) {
				value.setValue(evalue.getExpression().getText());
			}
		});

		return composite;
	}

	/**
	 * Set the current value insde the value controls
	 * 
	 * @param value
	 *          value to set
	 */
	private void fillValue(PropertyDTO value) {
		evalue.setExpressionContext(value.geteContext());
		if (cprop != null)
			cprop.setText(Misc.nvl(value.getName()));
		if (buseexpr != null)
			buseexpr.setSelection(value.isExpression());
		if (value.isExpression()) {
			stackLayout.topControl = vexp;
			stackComposite.layout();
		}

		String text = Misc.nvl((String) value.getValue());
		tvalue.setText(text);
		evalue.setExpression(new JRDesignExpression(text));
		if (value instanceof DatasetPropertyExpressionDTO) {
			PropertyEvaluationTimeEnum etime = ((DatasetPropertyExpressionDTO) value).getEvalTime();
			cevalTime.setText(etime != null ? etime.getName() : ""); //$NON-NLS-1$
		}
	}

	/**
	 * Method called when one of the value controls are modfied and it synch the value on the other control
	 */
	protected synchronized void synchText() {
		if (!updating) {
			updating = true;
			if (buseexpr != null)
				if (buseexpr.getSelection())
					tvalue.setText(evalue.getText());
				else
					evalue.setExpression(new JRDesignExpression(tvalue.getText()));
			updating = false;
		}
	}

	/**
	 * Return a value as string, it can handle string and expressions. If the value can't be converted it return an empty
	 * string
	 */
	@Override
	protected String getValueText(Object value) {
		if (value instanceof String) {
			return (String) value;
		} else if (value instanceof JRExpression) {
			return ((JRExpression) value).getText();
		}
		return ""; //$NON-NLS-1$
	}
}
