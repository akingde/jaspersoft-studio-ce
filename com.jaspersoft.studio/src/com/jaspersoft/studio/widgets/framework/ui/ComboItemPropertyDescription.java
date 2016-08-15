package com.jaspersoft.studio.widgets.framework.ui;

import org.eclipse.swt.SWT;
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
import com.jaspersoft.studio.widgets.framework.IWItemProperty;
import com.jaspersoft.studio.widgets.framework.manager.DoubleControlComposite;
import com.jaspersoft.studio.widgets.framework.model.WidgetPropertyDescriptor;
import com.jaspersoft.studio.widgets.framework.model.WidgetsDescriptor;

public class ComboItemPropertyDescription<T> extends TextPropertyDescription<T> {

	protected String[][] keyValues;

	public ComboItemPropertyDescription() {
		super();
	}

	public ComboItemPropertyDescription(String name, String label, String description, boolean mandatory, T defaultValue, String[] values) {
		super(name, label, description, mandatory, defaultValue);
		keyValues = convert2KeyValue(values);
	}

	public ComboItemPropertyDescription(String name, String label, String description, boolean mandatory, String[] values) {
		super(name, label, description, mandatory);
		keyValues = convert2KeyValue(values);
	}

	public ComboItemPropertyDescription(String name, String label, String description, boolean mandatory, T defaultValue,	String[][] keyValues) {
		super(name, label, description, mandatory, defaultValue);
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
	
	protected Combo createComboControl(Composite parent){
		return new Combo(parent, SWT.NONE);
	}

	public Control createControl(final IWItemProperty wiProp, Composite parent) {
		DoubleControlComposite cmp = new DoubleControlComposite(parent, SWT.NONE);
		cmp.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Control expressionControl = super.createControl(wiProp, cmp.getFirstContainer());
		cmp.getFirstContainer().setData(expressionControl);

		final Combo simpleControl = createComboControl(cmp.getSecondContainer());
		cmp.getSecondContainer().setData(simpleControl);
		
		GridData comboData = new GridData(GridData.FILL_HORIZONTAL);
		comboData.verticalAlignment = SWT.CENTER;
		comboData.grabExcessVerticalSpace = true;
		simpleControl.setLayoutData(comboData);
		
		simpleControl.setItems(convert2Value(keyValues));
		simpleControl.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (wiProp.isRefresh())
					return;
				handleEdit(simpleControl, wiProp);
			}
		});
		simpleControl.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				if (wiProp.isRefresh())
					return;
				Point p = simpleControl.getSelection();
				handleEdit(simpleControl, wiProp);
				simpleControl.setSelection(p);
			}
		});
		setupContextMenu(simpleControl, wiProp);
		cmp.switchToSecondContainer();
		return cmp;
	}

	@Override
	public void update(Control c, IWItemProperty wip) {
		DoubleControlComposite cmp = (DoubleControlComposite) wip.getControl();
		if (wip.isExpressionMode()) {
			Text txt = (Text) cmp.getFirstContainer().getData();
			super.update(txt, wip);
			cmp.switchToFirstContainer();
		} else {
			Combo combo = (Combo) cmp.getSecondContainer().getData();
			String v = wip.getStaticValue();
			if (v == null && defaultValue != null){
				v = defaultValue.toString();
			}
			combo.setText(Misc.nvl(v));
			cmp.switchToSecondContainer();
		}
	}
	
	@Override
	public ItemPropertyDescription<T> clone(){
		ComboItemPropertyDescription<T> result = new ComboItemPropertyDescription<T>();
		result.defaultValue = defaultValue;
		result.description = description;
		result.jConfig = jConfig;
		result.label = label;
		result.mandatory = mandatory;
		result.name = name;
		result.keyValues = keyValues;
		return result;
	}
	
	@Override
	public ItemPropertyDescription<?> getInstance(WidgetsDescriptor cd, WidgetPropertyDescriptor cpd, JasperReportsConfiguration jConfig) {
		if (cpd.getComboOptions() != null) {
			String[][] opts = cpd.getComboOptions();
			String[][] i18nOpts = new String[opts.length][2];
			for (int i = 0; i < opts.length; i++) {
				i18nOpts[i][0] = opts[i][0];
				i18nOpts[i][1] = cd.getLocalizedString(opts[i][1]);
			}
			ComboItemPropertyDescription<String> result = new ComboItemPropertyDescription<String>(cpd.getName(), cd.getLocalizedString(cpd.getLabel()), cd.getLocalizedString(cpd.getDescription()), cpd.isMandatory(), cpd.getDefaultValue(), i18nOpts);
			result.setReadOnly(cpd.isReadOnly());
			return result;
		}
		return null;
	}
}
