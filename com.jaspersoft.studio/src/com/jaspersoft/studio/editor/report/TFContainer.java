package com.jaspersoft.studio.editor.report;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.ToolBar;

public class TFContainer extends Composite {
	private StackLayout stackLayout;
	private ToolBar toolBar;

	public TFContainer(Composite parent, int style) {
		super(parent, style);
		setLayout(new RowLayout(SWT.VERTICAL));

		toolBar = new ToolBar(this, SWT.HORIZONTAL | SWT.FLAT | SWT.WRAP);

		Composite content = new Composite(this, SWT.NONE);
		stackLayout = new StackLayout();
		content.setLayout(stackLayout);
	}

	private List<TFItem> tfitems = new ArrayList<TFItem>();
	private int selection;

	public int indexOf(TFItem item) {
		return tfitems.indexOf(item);
	}

	public int getSelectionIndex() {
		return selection;
	}

	public void setSelection(int selection) {
		this.selection = selection;
	}

	public TFItem getItem(int selectedIndex) {
		return tfitems.get(selectedIndex);
	}

	public int getItemCount() {
		return tfitems.size();
	}

	private List<SelectionListener> listeners = new ArrayList<SelectionListener>();

	public void addSelectionListener(SelectionListener listener) {
		listeners.add(listener);
	}

	public void createItem(TFItem item, int index) {
		// TODO
	}
}
