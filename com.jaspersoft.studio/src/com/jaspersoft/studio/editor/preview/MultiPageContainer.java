/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2010 Jaspersoft Corporation. All
 * rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of Jaspersoft Open Studio.
 * 
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify it under the terms of the GNU Affero
 * General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * Jaspersoft Open Studio is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with Jaspersoft Open Studio. If not,
 * see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.editor.preview;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.editor.preview.view.APreview;

public class MultiPageContainer {
	protected LinkedHashMap<String, APreview> pmap = new LinkedHashMap<String, APreview>();
	private List<String> keys;
	private Composite composite;

	public void switchView(String key) {
		switchView(pmap.get(key));
	}

	public void switchView(APreview view) {
		((StackLayout) composite.getLayout()).topControl = view.getControl();
		composite.layout();
	}

	public List<String> getKeys() {
		if (keys == null)
			keys = new ArrayList<String>(pmap.keySet());
		return keys;
	}

	public void populate(Composite composite, LinkedHashMap<String, APreview> pmap) {
		this.composite = composite;
		this.pmap = pmap;
	}

	public void dispose() {
		for (APreview p : pmap.values()) {
			if (p != null)
				p.dispose();
		}
	}

	public void setEnabled(boolean enabled) {
		for (APreview p : pmap.values()) {
			if (p != null)
				p.setEnabled(enabled);
		}
	}
}
