package com.jaspersoft.studio.property.itemproperty.desc;

import net.sf.jasperreports.components.map.StandardItemProperty;
import net.sf.jasperreports.engine.design.JRDesignExpression;

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

public class ComboItemPropertyDescription<T> extends ItemPropertyDescription<T> {
	private String[] values;

	public ComboItemPropertyDescription(String name, String description, boolean mandatory, String[] values) {
		super(name, description, mandatory);
		this.values = values;
	}

	public ComboItemPropertyDescription(String name, String description, boolean mandatory, T defaultValue,
			String[] values) {
		super(name, description, mandatory, defaultValue);
		this.values = values;
	}

	public ComboItemPropertyDescription(String name, String label, String description, boolean mandatory, T defaultValue,
			String[] values) {
		super(name, label, description, mandatory, defaultValue);
		this.values = values;
	}

	public ComboItemPropertyDescription(String name, String label, String description, boolean mandatory, String[] values) {
		super(name, label, description, mandatory);
		this.values = values;
	}

	public void handleEdit(Control txt, StandardItemProperty value) {
		super.handleEdit(txt, value);
		if (txt instanceof Combo) {
			int indx = ((Combo) txt).getSelectionIndex();
			String tvalue = indx >= 0 && indx < values.length ? values[indx] : null;
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

		final Combo textExpression = new Combo(cmp, SWT.READ_ONLY);
		textExpression.setItems(values);
		textExpression.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (wiProp.isRefresh())
					return;

				handleEdit(textExpression, wiProp.getValue());
				wiProp.setValue(wiProp.getValue());
			}
		});

		textExpression.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				if (wiProp.isRefresh())
					return;
				Point p = textExpression.getSelection();

				handleEdit(textExpression, wiProp.getValue());
				wiProp.setValue(wiProp.getValue());
				textExpression.setSelection(p);
			}
		});
		return cmp;
	}

	@Override
	public void setValue(Control c, IWItemProperty wip) {
		Composite cmp = (Composite) wip.getControl();
		StackLayout layout = (StackLayout) cmp.getLayout();
		if (wip.getValue().getValueExpression() != null) {
			Text txt = (Text) cmp.getChildren()[0];
			super.setValue(txt, wip);
			layout.topControl = txt;
		} else {
			Combo combo = (Combo) cmp.getChildren()[1];
			layout.topControl = combo;
			String txt = wip.getLabelProvider().getText(wip.getValue());
			String v = wip.getValue().getValue();

			for (int i = 0; i < values.length; i++) {
				if (values[i].equals(v)) {
					combo.select(i);
					break;
				}
			}

			String tooltip = "";
			if (!Misc.isNullOrEmpty(txt))
				tooltip += "\n\n" + txt;
			tooltip += "\n\n" + getDescription();
			combo.setToolTipText(tooltip.trim());
		}
		cmp.layout();
	}
}
