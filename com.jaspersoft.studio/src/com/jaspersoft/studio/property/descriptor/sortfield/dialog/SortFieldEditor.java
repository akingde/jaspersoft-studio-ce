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
package com.jaspersoft.studio.property.descriptor.sortfield.dialog;

import java.util.List;

import org.eclipse.jface.wizard.Wizard;

public class SortFieldEditor extends Wizard {
	private List<?> list;
	private SortFieldPage page0;

	public List<?> getList() {
		if (page0 != null)
			return page0.getList();
		return list;
	}

	public void setList(List<?> list) {
		this.list = list;
	}

	public SortFieldEditor() {
		super();
		setWindowTitle(Messages.SortFieldEditor_sort_field_editor);
	}

	@Override
	public void addPages() {
		page0 = new SortFieldPage("sort.field.editor"); //$NON-NLS-1$
		page0.setList(getList());
		addPage(page0);
	}

	@Override
	public boolean performFinish() {
		return true;
	}

}
