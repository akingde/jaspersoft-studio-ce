package com.jaspersoft.studio.components.chart.property.widget;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.JRGroup;
import net.sf.jasperreports.engine.design.JRDesignChart;
import net.sf.jasperreports.engine.type.EvaluationTimeEnum;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.property.section.AbstractSection;

public class BtnEvaluationTime {
	private CCombo evalTime;

	public BtnEvaluationTime(Composite parent, AbstractSection section,
			String property, String tooltip, String[] items) {
		createComponent(parent, section, property, tooltip, items);
	}

	public void createComponent(Composite parent,
			final AbstractSection section, final String property,
			String tooltip, String[] items) {
		evalTime = new CCombo(parent, SWT.BORDER | SWT.FLAT | SWT.READ_ONLY);
		evalTime.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				String group = null;
				Integer et = new Integer(1);
				section.changeProperty(JRDesignChart.PROPERTY_EVALUATION_TIME,
						et);
				section.changeProperty(JRDesignChart.PROPERTY_EVALUATION_GROUP,
						group);
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});
		evalTime.setToolTipText(tooltip);
	}

	public void setData(Integer et, String group, String[] items) {
		evalTime.setItems(items);
		// String[] items = theme.getItems();
		// int selection = 0;
		// for (int i = 0; i < items.length; i++) {
		// if (items[i].equals(b)) {
		// selection = i;
		// break;
		// }
		// }
		// theme.select(selection);
		// if (selection == 0 && b != null)
		// theme.setItem(0, b);
	}

	public String[] getItems(JRDataset dataset) {
		List<String> lsIncs = new ArrayList<String>();
		for (EvaluationTimeEnum en : EvaluationTimeEnum.values()) {
			if (en.equals(EvaluationTimeEnum.GROUP)) {
				for (JRGroup gr : dataset.getGroups())
					lsIncs.add(GROUPPREFIX + gr.getName());
			} else {
				lsIncs.add(en.getName());
			}
		}
		return lsIncs.toArray(new String[lsIncs.size()]);
	}

	private static final String GROUPPREFIX = "[Group] ";
}
