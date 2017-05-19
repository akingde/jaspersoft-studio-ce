/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.widgets.framework.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.swt.widgets.NumericText;
import com.jaspersoft.studio.widgets.framework.IWItemProperty;
import com.jaspersoft.studio.widgets.framework.manager.DoubleControlComposite;
import com.jaspersoft.studio.widgets.framework.ui.widget.FallbackNumericText;

import net.sf.jasperreports.eclipse.util.Misc;

public abstract class NumberPropertyDescription<T extends Number> extends AbstractExpressionPropertyDescription<T> {
	
	protected T min;
	
	protected T max;

	public NumberPropertyDescription() {
	}
	
	public NumberPropertyDescription(String name, String label, String description, boolean mandatory, T defaultValue, T min, T max) {
		super(name, label, description, mandatory, defaultValue);
		this.min = min;
		this.max = max;
	}

	public NumberPropertyDescription(String name, String label, String description, boolean mandatory, T min, T max) {
		super(name, label, description, mandatory);
		this.min = min;
		this.max = max;
	}

	public T getMin() {
		return min;
	}

	public T getMax() {
		return max;
	}

	@Override
	public Control createControl(final IWItemProperty wiProp, Composite parent) {
		DoubleControlComposite cmp = new DoubleControlComposite(parent, SWT.NONE);
		cmp.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		lazyCreateExpressionControl(wiProp, cmp);

		final NumericText simpleControl = createSimpleEditor(cmp.getSecondContainer());
		cmp.getSecondContainer().setData(simpleControl);
		cmp.setSimpleControlToHighlight(simpleControl);
		GridData textData = new GridData(GridData.FILL_HORIZONTAL);
		textData.verticalAlignment = SWT.CENTER;
		textData.grabExcessVerticalSpace = true;
		simpleControl.setLayoutData(textData);
		simpleControl.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (wiProp.isRefresh())
					return;
				handleEdit(simpleControl, wiProp);
			}
			
		});
		setupContextMenu(simpleControl, wiProp);
		cmp.switchToFirstContainer();
		return cmp;
	}
	
	/**
	 * Create the editor control to edit the number directly
	 * 
	 * @param parent the parent of the element
	 * @return a not null NumericText to handle the numeric field
	 */
	protected abstract FallbackNumericText createSimpleEditor(Composite parent);
	
	/**
	 * Convert the string into a number for the simple control when necessary
	 */
	protected abstract Number convertValue(String v) throws NumberFormatException;
	
	/**
	 * Return the type of the number handled by this class (ie integer, long...)
	 * 
	 * @return a not null class
	 */
	public abstract Class<? extends Number> getType();
	
	/**
	 * Set the value inside the correct control, if the editor is in 
	 * expression mode or not
	 */
	@Override
	public void update(Control c, IWItemProperty wip) {
		DoubleControlComposite cmp = (DoubleControlComposite) wip.getControl();
		if (wip.isExpressionMode()) {
			lazyCreateExpressionControl(wip, cmp);
			Text expressionControl = (Text) cmp.getFirstContainer().getData();
			super.update(expressionControl, wip);
			cmp.switchToFirstContainer();
		} else {
			boolean isFallback = false;
			FallbackNumericText simpleControl = (FallbackNumericText)cmp.getSecondContainer().getData();
			String v = wip.getStaticValue();
			if (v == null && wip.getFallbackValue() != null){
				v = wip.getFallbackValue().toString();
				isFallback = true;
			}
			simpleControl.setFallback(isFallback);
			try{
				Number numericValue = convertValue(Misc.nvl(v));
				simpleControl.setValue(numericValue);
				simpleControl.setToolTipText(getToolTip());
			} catch (NumberFormatException ex){
				simpleControl.setUnparsedValue(Misc.nvl(v));
				simpleControl.setToolTipText("The current value can not be recognized \n" + getToolTip());
			}
			
			changeFallbackForeground(isFallback, simpleControl);
			cmp.switchToSecondContainer();
		}
	}

	@Override
	public String getToolTip() {
		String tt = super.getToolTip();
		if (getMin() != null)
			tt += "\nmin: " + getMin();
		if (getMax() != null)
			tt += "\nmax: " + getMax();
		return tt;
	}
}
