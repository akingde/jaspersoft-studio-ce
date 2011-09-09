package com.jaspersoft.studio.components.chart.property.widget;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.property.section.AbstractSection;

public class BtnNumber {
	private Text ftext;

	public BtnNumber(Composite parent, AbstractSection section,
			String property, String tooltip) {
		createComponent(parent, section, property, tooltip);
	}

	public void createComponent(Composite parent,
			final AbstractSection section, final String property, String tooltip) {
		ftext = new Text(parent, SWT.BORDER);
		ftext.addModifyListener(new ModifyListener() {

			public void modifyText(ModifyEvent e) {
				try {
					Number newValue = null;
					String tmp = ftext.getText();
					if (tmp != null && !tmp.trim().isEmpty()) {
						if (numType == Double.class)
							newValue = new Double(tmp);
						else if (numType == Integer.class)
							newValue = new Integer(tmp);
						else if (numType == Float.class)
							newValue = new Float(tmp);
					}
					section.changeProperty(property, newValue);
				} catch (NumberFormatException nfe) {

				}
			}
		});
		ftext.setToolTipText(tooltip);
		if (parent.getLayout() instanceof RowLayout) {
			RowData rd = new RowData();
			rd.width = 100;
			ftext.setLayoutData(rd);
		}
	}

	private Class<? extends Number> numType;

	public void setData(Integer f) {
		setDataNumber(f);
		numType = Integer.class;
	}

	public void setData(Float f) {
		setDataNumber(f);
		numType = Float.class;
	}

	public void setData(Double f) {
		setDataNumber(f);
		numType = Double.class;
	}

	public void setDataNumber(Number f) {
		if (f != null){
			int oldpos = ftext.getCaretPosition();
			ftext.setText(f.toString());
			if (f.toString().length() >= oldpos)
				ftext.setSelection(oldpos, oldpos);
		}else
			ftext.setText("");
	}
}
