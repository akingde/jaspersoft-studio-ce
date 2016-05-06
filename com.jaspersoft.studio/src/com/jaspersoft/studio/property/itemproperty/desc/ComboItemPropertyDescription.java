package com.jaspersoft.studio.property.itemproperty.desc;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.utils.Misc;

import net.sf.jasperreports.components.items.StandardItemProperty;
import net.sf.jasperreports.engine.design.JRDesignExpression;

public class ComboItemPropertyDescription<T> extends ItemPropertyDescription<T> {
	private String[] values;
	private String[][] keyValues;

	public ComboItemPropertyDescription(String name, String description, boolean mandatory, String[] values) {
		super(name, description, mandatory);
		this.values = values;
		keyValues = convert2KeyValue(values);
	}

	public ComboItemPropertyDescription(String name, String description, boolean mandatory, T defaultValue,
			String[] values) {
		super(name, description, mandatory, defaultValue);
		this.values = values;
		keyValues = convert2KeyValue(values);
	}

	public ComboItemPropertyDescription(String name, String label, String description, boolean mandatory, T defaultValue,
			String[] values) {
		super(name, label, description, mandatory, defaultValue);
		this.values = values;
		keyValues = convert2KeyValue(values);
	}

	public ComboItemPropertyDescription(String name, String label, String description, boolean mandatory,
			String[] values) {
		super(name, label, description, mandatory);
		this.values = values;
		keyValues = convert2KeyValue(values);
	}

	public ComboItemPropertyDescription(String name, String label, String description, boolean mandatory, String[] values,
			boolean readOnly) {
		super(name, label, description, mandatory);
		this.values = values;
		keyValues = convert2KeyValue(values);
		this.readOnly = readOnly;
	}

	public ComboItemPropertyDescription(String name, String description, boolean mandatory, String[][] keyValues) {
		super(name, description, mandatory);
		this.values = convert2Value(keyValues);
		this.keyValues = keyValues;
	}

	public ComboItemPropertyDescription(String name, String description, boolean mandatory, T defaultValue,
			String[][] keyValues) {
		super(name, description, mandatory, defaultValue);
		this.values = convert2Value(keyValues);
		this.keyValues = keyValues;
	}

	public ComboItemPropertyDescription(String name, String label, String description, boolean mandatory, T defaultValue,
			String[][] keyValues) {
		super(name, label, description, mandatory, defaultValue);
		this.values = convert2Value(keyValues);
		this.keyValues = keyValues;
	}

	public ComboItemPropertyDescription(String name, String label, String description, boolean mandatory,
			String[][] keyValues) {
		super(name, label, description, mandatory);
		this.values = convert2Value(keyValues);
		this.keyValues = keyValues;
	}

	public ComboItemPropertyDescription(String name, String label, String description, boolean mandatory,
			String[][] keyValues, boolean readOnly) {
		super(name, label, description, mandatory);
		this.values = convert2Value(keyValues);
		this.keyValues = keyValues;
		this.readOnly = readOnly;
	}

	public static String[] convert2Value(String[][] keyValues) {
		String[] v = new String[keyValues.length];
		for (int i = 0; i < keyValues.length; i++)
			v[i] = keyValues[i][1];
		return v;
	}

	public static String[][] convert2KeyValue(String[] values) {
		String[][] kv = new String[values.length][2];
		for (int i = 0; i < values.length; i++) {
			kv[i][0] = values[i];
			kv[i][1] = values[i];
		}
		return kv;
	}

	public void handleEdit(Control txt, StandardItemProperty value) {
		super.handleEdit(txt, value);
		if (txt instanceof Combo) {
			int indx = ((Combo) txt).getSelectionIndex();
			String tvalue = indx >= 0 && indx < values.length ? keyValues[indx][0] : null;
			if (tvalue != null && tvalue.isEmpty())
				tvalue = null;
			if (value.getValueExpression() != null)
				((JRDesignExpression) value.getValueExpression()).setText(tvalue);
			else
				value.setValue(tvalue);
		}
	}

	public Control createControl(final IWItemProperty wiProp, Composite parent) {
		Composite cmp = new Composite(parent, SWT.NONE);
		StackLayout layout = new StackLayout();
		cmp.setLayout(layout);
		cmp.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		super.createControl(wiProp, cmp);

		final Combo c = new Combo(cmp, readOnly ? SWT.READ_ONLY : SWT.NONE);
		c.setItems(values);
		c.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (wiProp.isRefresh())
					return;
				StandardItemProperty v = wiProp.getValue();
				if (v == null)
					v = new StandardItemProperty(getName(), null, null);
				handleEdit(c, v);
				wiProp.setValue(wiProp.getValue());
			}
		});

		c.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				if (wiProp.isRefresh())
					return;
				Point p = c.getSelection();
				StandardItemProperty v = wiProp.getValue();
				if (v == null)
					v = new StandardItemProperty(getName(), null, null);
				handleEdit(c, v);
				wiProp.setValue(wiProp.getValue());
				c.setSelection(p);
			}
		});
		setupContextMenu(c, wiProp);
		layout.topControl = c;
		return cmp;
	}

	@Override
	public void setValue(Control c, IWItemProperty wip) {
		Composite cmp = (Composite) wip.getControl();
		StackLayout layout = (StackLayout) cmp.getLayout();
		if (wip.getValue() == null)
			wip.setValue(new StandardItemProperty(getName(), null, null));
		if (wip.getValue().getValueExpression() != null) {
			Text txt = (Text) cmp.getChildren()[0];
			super.setValue(txt, wip);
			layout.topControl = txt;
		} else {
			Combo combo = (Combo) cmp.getChildren()[1];
			layout.topControl = combo;
			String txt = wip.getLabelProvider().getText(wip.getValue());
			String v = wip.getValue().getValue();
			if (readOnly)
				for (int i = 0; i < values.length; i++) {
					if (keyValues[i][0].equals(v)) {
						combo.select(i);
						break;
					}
				}
			else
				combo.setText(Misc.nvl(v));

			String tooltip = "";
			if (!Misc.isNullOrEmpty(txt))
				tooltip += "\n\n" + txt;
			tooltip += "\n\n" + getToolTip();
			combo.setToolTipText(tooltip.trim());
		}
		cmp.layout();
	}
}
