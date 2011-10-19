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
package com.jaspersoft.studio.plugin;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.eclipse.gef.palette.PaletteEntry;

import com.jaspersoft.studio.editor.palette.JDPaletteFactory;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.util.IIconDescriptor;

public class PaletteContributor implements IPaletteContributor {
	private Map<String, List<PaletteEntry>> map = new TreeMap<String, List<PaletteEntry>>();

	public Map<String, List<PaletteEntry>> getPaletteEntries() {
		return map;
	}

	public void add(String id, Class<? extends ANode> value) {
		List<PaletteEntry> lst = map.get(id);
		if (lst == null) {
			lst = new ArrayList<PaletteEntry>();
			map.put(id, lst);
		}
		try {
			IIconDescriptor idesc = (IIconDescriptor) value.getDeclaredMethod("getIconDescriptor", new Class[0]).invoke( //$NON-NLS-1$
					value, new Object[0]);
			lst.add(JDPaletteFactory.createJDEntry(idesc, value));
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}

	}

	public void add(Class<? extends ANode> value) {
		add("", value);
	}

}
