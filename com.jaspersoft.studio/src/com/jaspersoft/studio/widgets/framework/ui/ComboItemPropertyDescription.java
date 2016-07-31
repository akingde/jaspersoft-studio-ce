package com.jaspersoft.studio.widgets.framework.ui;

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
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;
import com.jaspersoft.studio.widgets.framework.IPropertyEditor;
import com.jaspersoft.studio.widgets.framework.IWItemProperty;
import com.jaspersoft.studio.widgets.framework.model.WidgetPropertyDescriptor;
import com.jaspersoft.studio.widgets.framework.model.WidgetsDescriptor;

public class ComboItemPropertyDescription<T> extends TextPropertyDescription<T> {

	private String[][] keyValues;

	public ComboItemPropertyDescription() {
		super();
	}
	
	public ComboItemPropertyDescription(IPropertyEditor propertyEditor) {
		super(propertyEditor);
	}

	public ComboItemPropertyDescription(String name, String label, String description, boolean mandatory, T defaultValue, String[] values, IPropertyEditor editor) {
		super(name, label, description, mandatory, defaultValue, editor);
		keyValues = convert2KeyValue(values);
	}

	public ComboItemPropertyDescription(String name, String label, String description, boolean mandatory, String[] values, IPropertyEditor editor) {
		super(name, label, description, mandatory, editor);
		keyValues = convert2KeyValue(values);
	}

	public ComboItemPropertyDescription(String name, String label, String description, boolean mandatory, String[] values, boolean readOnly, IPropertyEditor editor) {
		super(name, label, description, mandatory, editor);
		keyValues = convert2KeyValue(values);
		this.readOnly = readOnly;
	}

	public ComboItemPropertyDescription(String name, String label, String description, boolean mandatory, T defaultValue,	String[][] keyValues, IPropertyEditor editor) {
		super(name, label, description, mandatory, defaultValue, editor);
		this.keyValues = keyValues;
	}
	
	public static String[][] convert2KeyValue(String[] values) {
		String[][] kv = new String[values.length][2];
		for (int i = 0; i < values.length; i++) {
			kv[i][0] = values[i];
			kv[i][1] = values[i];
		}
		return kv;
	}
	
	private String[] convert2Value(String[][] keyValues) {
		String[] v = new String[keyValues.length];
		for (int i = 0; i < keyValues.length; i++)
			v[i] = keyValues[i][1];
		return v;
	}


	public void handleEdit(Control txt, IWItemProperty wProp) {
		super.handleEdit(txt, wProp);
		if (txt instanceof Combo) {
			int indx = ((Combo) txt).getSelectionIndex();
			String tvalue = indx >= 0 && indx < keyValues.length ? keyValues[indx][0] : null;
			if (tvalue != null && tvalue.isEmpty())
				tvalue = null;
			wProp.setValue(tvalue, null);
		}
	}

	public Control createControl(final IWItemProperty wiProp, Composite parent) {
		Composite cmp = new Composite(parent, SWT.NONE);
		StackLayout layout = new StackLayout();
		cmp.setLayout(layout);
		cmp.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		super.createControl(wiProp, cmp);

		final Combo c = new Combo(cmp, readOnly ? SWT.READ_ONLY : SWT.NONE);
		c.setItems(convert2Value(keyValues));
		c.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (wiProp.isRefresh())
					return;
				handleEdit(c, wiProp);
			}
		});
		c.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				if (wiProp.isRefresh())
					return;
				Point p = c.getSelection();
				handleEdit(c, wiProp);
				c.setSelection(p);
			}
		});
		setupContextMenu(c, wiProp);
		layout.topControl = c;
		return cmp;
	}

	@Override
	public void update(Control c, IWItemProperty wip) {
		Composite cmp = (Composite) wip.getControl();
		StackLayout layout = (StackLayout) cmp.getLayout();
		if (wip.getExpressionValue() != null) {
			Text txt = (Text) cmp.getChildren()[0];
			super.update(txt, wip);
			layout.topControl = txt;
		} else {
			Combo combo = (Combo) cmp.getChildren()[1];
			layout.topControl = combo;
			String v = wip.getStaticValue();
			if (readOnly){
				for (int i = 0; i < keyValues.length; i++) {
					if (keyValues[i][0].equals(v)) {
						combo.select(i);
						break;
					}
				}
			} else {
				combo.setText(Misc.nvl(v));
			}
		}
		cmp.layout();
	}
	
	@Override
	public ItemPropertyDescription<T> clone(IPropertyEditor editor){
		ComboItemPropertyDescription<T> result = new ComboItemPropertyDescription<T>(editor);
		result.defaultValue = defaultValue;
		result.description = description;
		result.jConfig = jConfig;
		result.label = label;
		result.mandatory = mandatory;
		result.name = name;
		result.readOnly = readOnly;
		result.keyValues = keyValues;
		return result;
	}
	
	@Override
	public ItemPropertyDescription<?> getInstance(WidgetsDescriptor cd, WidgetPropertyDescriptor cpd, JasperReportsConfiguration jConfig, IPropertyEditor editor) {
		if (cpd.getComboOptions() != null) {
			String[][] opts = cpd.getComboOptions();
			String[][] i18nOpts = new String[opts.length][2];
			for (int i = 0; i < opts.length; i++) {
				i18nOpts[i][0] = opts[i][0];
				i18nOpts[i][1] = cd.getLocalizedString(opts[i][1]);
			}
			ComboItemPropertyDescription<String> result = new ComboItemPropertyDescription<String>(cpd.getName(), cd.getLocalizedString(cpd.getLabel()), cd.getLocalizedString(cpd.getDescription()), cpd.isMandatory(), cpd.getDefaultValue(), i18nOpts, editor);
			result.setReadOnly(cpd.isReadOnly());
			return result;
		}
		return null;
	}
}
