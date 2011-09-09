package com.jaspersoft.studio.components.chart.property.widget;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.property.section.AbstractSection;

public class BtnText {
	private Text ftext;

	public BtnText(Composite parent, AbstractSection section, String property,
			String tooltip) {
		createComponent(parent, section, property, tooltip);
	}

	public void createComponent(Composite parent,
			final AbstractSection section, final String property, String tooltip) {
		ftext = new Text(parent, SWT.BORDER);

		ftext.addModifyListener(new ModifyListener() {

			public void modifyText(ModifyEvent e) {
				section.changeProperty(property, ftext.getText());
			}
		});
		ftext.setToolTipText(tooltip);
		if (parent.getLayout() instanceof RowLayout) {
			RowData rd = new RowData();
			rd.width = 100;
			ftext.setLayoutData(rd);
		}
	}

	public void setData(String f) {
		if (f != null) {
			int oldpos = ftext.getCaretPosition();
			ftext.setText(f.toString());
			if (f.toString().length() >= oldpos)
				ftext.setSelection(oldpos, oldpos);
		} else
			ftext.setText("");
	}
}
