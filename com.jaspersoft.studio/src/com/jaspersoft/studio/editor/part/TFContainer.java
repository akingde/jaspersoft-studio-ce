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
package com.jaspersoft.studio.editor.part;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

public class TFContainer extends Composite {
	private StackLayout stackLayout;
	private ToolBar toolBar;

	public TFContainer(Composite parent, int style) {
		super(parent, style);
		GridLayout layout = new GridLayout();
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		layout.verticalSpacing = 0;
		setLayout(layout);

		toolBar = new ToolBar(this, SWT.HORIZONTAL | SWT.FLAT | SWT.WRAP | SWT.BORDER | SWT.RIGHT);

		content = new Composite(this, SWT.NONE);
		stackLayout = new StackLayout();
		stackLayout.marginWidth = 0;
		stackLayout.marginHeight = 0;
		content.setLayout(stackLayout);
		content.setLayoutData(new GridData(GridData.FILL_BOTH));
	}

	public Composite getContent() {
		return content;
	}

	private List<TFItem> tfitems = new ArrayList<TFItem>();
	private int selection = -1;

	public int indexOf(TFItem item) {
		return tfitems.indexOf(item);
	}

	public int getSelectionIndex() {
		return selection;
	}

	public void setSelection(int selection) {
		this.selection = selection;
		for (int i = 0; i < toolBar.getItemCount(); i++) {
			toolBar.getItem(i).setSelection(i == selection);
		}
		stackLayout.topControl = getItem(selection).getControl();

		getParent().layout();
	}

	public void removeItem(TFItem item) {
		int index = tfitems.indexOf(item);
		toolBar.getItem(index).dispose();
		tfitems.remove(item);
		toolBar.update();
		if (index == selection)
			setSelection(--index);
	}

	public TFItem getItem(int selectedIndex) {
		return tfitems.get(selectedIndex);
	}

	public int getItemCount() {
		return tfitems.size();
	}

	private List<SelectionListener> listeners = new ArrayList<SelectionListener>();
	private Composite content;

	public void addSelectionListener(SelectionListener listener) {
		listeners.add(listener);
	}

	public void createItem(final TFItem item, int index) {
		final ToolItem ti = new ToolItem(toolBar, SWT.RADIO);
		ti.setText("Item1" + item.getText());
		ti.setData(item);
		ti.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				if (ti.getSelection() && tfitems.indexOf(item) != selection)
					for (SelectionListener sl : listeners)
						sl.widgetSelected(e);
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});
		tfitems.add(index, item);
		toolBar.update();
		getParent().layout(true);
	}

	public void update(TFItem tfItem) {
		for (ToolItem it : toolBar.getItems()) {
			if (it.getData() == tfItem) {
				it.setText(tfItem.getText());
				it.setImage(tfItem.getImage());
				toolBar.update();
				layout(true);
				break;
			}
		}
	}
}
