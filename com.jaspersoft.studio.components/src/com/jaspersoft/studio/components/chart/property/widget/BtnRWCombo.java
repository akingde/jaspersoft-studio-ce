package com.jaspersoft.studio.components.chart.property.widget;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.property.section.AbstractSection;

public class BtnRWCombo {
	private CCombo theme;

	public BtnRWCombo(Composite parent, AbstractSection section,
			String property, String tooltip, String[] items) {
		createComponent(parent, section, property, tooltip, items);
	}

	public void createComponent(Composite parent,
			final AbstractSection section, final String property,
			String tooltip, String[] items) {
		theme = new CCombo(parent, SWT.BORDER | SWT.FLAT);
		theme.setItems(items);
		theme.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				section.changeProperty(property,
						theme.getItem(theme.getSelectionIndex()));
			}

			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		theme.setToolTipText(tooltip);
	}

	public void setData(String b) {
		String[] items = theme.getItems();
		int selection = 0;
		for (int i = 0; i < items.length; i++) {
			if (items[i].equals(b)) {
				selection = i;
				break;
			}
		}
		theme.select(selection);
		if (selection == 0 && b != null)
			theme.setItem(0, b);
	}
}
