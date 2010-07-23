package com.jaspersoft.studio.property.descriptor.pattern.dialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;

public class DatePattern extends APattern {

	public DatePattern(Composite parent) {
		super(parent, new SimpleDateFormat(), new Date());
		setDescription("Date format is used to display date and time part of date values. Use time \nformat to display only time portion.");
	}

	@Override
	public Control createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new GridLayout());
		Label l = new Label(container, SWT.NONE);
		l.setText("Template Formats");

		list = new List(container, SWT.BORDER);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.verticalSpan = 3;
		gd.heightHint = 200;
		gd.widthHint = 100;
		list.setLayoutData(gd);

		SimpleDateFormat f = (SimpleDateFormat) getFormatter();
		for (String s : getDefaults()) {
			f.applyPattern(s);
			list.add(f.format(getSample()));
		}
		list.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				formatChanged();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});
		return container;
	}

	protected java.util.List<String> dList;
	protected List list;

	protected java.util.List<String> getDefaults() {
		if (dList == null) {
			dList = new ArrayList<String>();
			dList.add("M/d/yy");
			dList.add("MMM d, yyyy");
			dList.add("MMMM d, yyyy");
			dList.add("M/d/yy h:mm a");
			dList.add("MMM d, yyyy h:mm:ss a");
			dList.add("MMM d, yyyy h:mm:ss a z");
			setPattern(dList.get(0));
		}
		return dList;
	}

	@Override
	protected void formatChanged() {
		int sel = list.getSelectionIndex();
		if (sel > 0) {
			setPattern(dList.get(sel));
		}
		super.formatChanged();
	}
}
