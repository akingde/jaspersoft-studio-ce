package com.jaspersoft.studio.property.descriptor.pattern.dialog;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.widgets.Composite;

public class TimePattern extends DatePattern {

	public TimePattern(Composite parent) {
		super(parent);
		setDescription("Time format is used to display the time portion of date values. Use date \nformat to display also the date portion.");
	}

	@Override
	protected List<String> getDefaults() {
		if (dList == null) {
			dList = new ArrayList<String>();
			dList.add("h:mm a");
			dList.add("h:mm:ss a");
			dList.add("h:mm:ss a z");
			dList.add("HH:mm a");
			dList.add("HH:mm:ss a");
			dList.add("HH:mm:ss zzzz");
			setPattern(dList.get(0));
		}
		return dList;
	}

}
