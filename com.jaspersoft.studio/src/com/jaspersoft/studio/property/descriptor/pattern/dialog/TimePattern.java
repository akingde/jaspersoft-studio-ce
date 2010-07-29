/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer.
 * Copyright (C) 2005 - 2010 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of Jaspersoft Open Studio.
 *
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Jaspersoft Open Studio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with Jaspersoft Open Studio. If not, see <http://www.gnu.org/licenses/>.
 */
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
