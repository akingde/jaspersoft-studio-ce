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
package com.jaspersoft.studio.editor.preview.stats;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.JRAbstractScriptlet;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.scriptlets.ScriptletFactory;
import net.sf.jasperreports.engine.scriptlets.ScriptletFactoryContext;

public class RecordCountScriptletFactory implements ScriptletFactory {

	public RecordCountScriptletFactory() {
	}

	private List<JRAbstractScriptlet> list;

	public List<JRAbstractScriptlet> getScriplets(ScriptletFactoryContext context) throws JRException {
		if (list == null) {
			list = new ArrayList<JRAbstractScriptlet>();
		}
		list.add(new RecordCountScriptlet());
		return list;
	}

	public int getRecordCount() {
		int count = 0;
		if (list != null)
			for (JRAbstractScriptlet s : list)
				if (s instanceof RecordCountScriptlet)
					count += ((RecordCountScriptlet) s).getCounter();
		return count;
	}
}
