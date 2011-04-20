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

package com.jaspersoft.studio.preferences.editor.pages;

import org.eclipse.jface.preference.FieldEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;

public class PagesFieldEditor extends FieldEditor {
	private Composite container;
	private Button btAll;
	private Button btRange;
	private Spinner pageFrom;
	private Spinner pageTo;
	private Button btPage;
	private Spinner page;

	public PagesFieldEditor() {
		super();
	}

	public PagesFieldEditor(String name, String labelText, Composite parent) {
		super(name, labelText, parent);
		init(name, labelText);
	}

	@Override
	protected void adjustForNumColumns(int numColumns) {
		((GridData) container.getLayoutData()).horizontalSpan = numColumns;
	}

	@Override
	protected void doFillIntoGrid(Composite parent, int numColumns) {
		container = createEncodingGroup(parent, numColumns);
	}

	protected Composite createEncodingGroup(Composite parent, int numColumns) {
		Group container = new Group(parent, SWT.NONE);
		container.setText("Pages To Export");
		container.setLayout(new GridLayout(5, false));
		GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.grabExcessHorizontalSpace = true;
		gridData.horizontalSpan = 2;
		container.setLayoutData(gridData);

		btAll = new Button(container, SWT.RADIO);
		btAll.setText("Export All Pages");
		GridData gd = new GridData();
		gd.horizontalSpan = 5;
		btAll.setLayoutData(gd);

		createPage(container);

		createRange(container);

		return container;
	}

	private void createPage(Group container) {
		GridData gd;
		btPage = new Button(container, SWT.RADIO);
		btPage.setText("Export Page:");

		page = new Spinner(container, SWT.BORDER);
		gd = new GridData();
		gd.horizontalSpan = 4;
		page.setLayoutData(gd);
		page.setEnabled(false);
		page.setValues(0, 0, Integer.MAX_VALUE, 0, 1, 10);

		btPage.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				page.setEnabled(btPage.getSelection());
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});
	}

	private void createRange(Group container) {
		btRange = new Button(container, SWT.RADIO);
		btRange.setText("Export Pages From:");

		pageFrom = new Spinner(container, SWT.BORDER);
		pageFrom.setEnabled(false);
		pageFrom.setValues(0, 0, Integer.MAX_VALUE, 0, 1, 10);

		Label lbl = new Label(container, SWT.NONE);
		lbl.setText("To:");

		pageTo = new Spinner(container, SWT.BORDER);
		pageTo.setEnabled(false);
		pageTo.setValues(0, 0, Integer.MAX_VALUE, 0, 1, 10);

		btRange.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				pageFrom.setEnabled(btRange.getSelection());
				pageTo.setEnabled(btRange.getSelection());
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});
	}

	@Override
	protected void doLoad() {
		String resourcePreference = getStoredValue();
		setProperty(resourcePreference);
	}

	private void setProperty(String resourcePreference) {
		Pages p = new Pages();
		p.parseString(resourcePreference);
		if (p.getPage() != null) {
			btPage.setSelection(true);
			page.setSelection(p.getPage());
			page.setEnabled(true);
		} else if (p.getFrom() != null) {
			btRange.setSelection(true);

			pageFrom.setSelection(p.getFrom());
			pageFrom.setEnabled(true);
			pageTo.setSelection(p.getTo());
			pageTo.setEnabled(true);
		} else
			btAll.setSelection(true);
	}

	@Override
	protected void doLoadDefault() {
		btAll.setSelection(true);
		btPage.setSelection(false);
		btRange.setSelection(false);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.internal.ide.dialogs.AbstractEncodingFieldEditor#getStoredValue()
	 */
	protected String getStoredValue() {
		return getPreferenceStore().getString(getPreferenceName());
	}

	private String getProperty() {
		if (btAll.getSelection())
			return "all";
		if (btPage.getSelection())
			return Integer.toString(page.getSelection());
		int from = pageFrom.getSelection();
		int to = pageTo.getSelection();
		if (to < from)
			to = from;
		return Integer.toString(from) + ";" + Integer.toString(to);
	}

	protected boolean hasSameProperty(String prop) {

		String current = getStoredValue();

		if (prop == null) {
			// Changed if default is selected and there is no setting
			return current == null || current.length() == 0;
		}
		return prop.equals(current);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.preference.FieldEditor#doStore()
	 */
	protected void doStore() {
		String prop = getProperty();

		if (hasSameProperty(prop)) {
			return;
		}

		if (prop.equals("all")) {
			getPreferenceStore().setToDefault(getPreferenceName());
		} else {
			getPreferenceStore().setValue(getPreferenceName(), prop);
		}
	}

	@Override
	public int getNumberOfControls() {
		return 1;
	}

}
