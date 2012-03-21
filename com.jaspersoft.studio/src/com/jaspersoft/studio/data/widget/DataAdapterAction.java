package com.jaspersoft.studio.data.widget;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuCreator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.ToolBar;

import com.jasperassistant.designer.viewer.IReportViewerListener;
import com.jasperassistant.designer.viewer.ReportViewerEvent;
import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.data.storage.ADataAdapterStorage;

public class DataAdapterAction extends Action implements IMenuCreator, PropertyChangeListener, IReportViewerListener {
	public static final String ID = "DATAADAPTERACTION"; //$NON-NLS-1$
	private IDataAdapterRunnable editor;
	private ADataAdapterStorage[] dastorages;

	public DataAdapterAction(IDataAdapterRunnable editor, ADataAdapterStorage[] dastorages) {
		super();
		setId(ID);
		setMenuCreator(this);

		setText(" -- No Data Adapter -- ");
		setDescription("Select a Data Adapter");
		setToolTipText("Select a Data Adapter");
		this.editor = editor;
		this.dastorages = dastorages;
	}

	@Override
	public boolean isEnabled() {
		return super.isEnabled() && editor.isNotRunning();
	}

	@Override
	public void run() {
		editor.runReport(selectedDA);
	}

	private Menu listMenu;

	public void dispose() {
		if (listMenu != null)
			listMenu.dispose();
	}

	private Control parent;

	public Menu getMenu(Control parent) {
		this.parent = parent;
		if (listMenu != null)
			listMenu.dispose();
		listMenu = new Menu(parent);

		SelectionAdapter listener = new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				MenuItem menuItem = (MenuItem) e.getSource();
				menuItem.setSelection(true);

				setSelected((DataAdapterDescriptor) menuItem.getData("da.key"));
				// do run
				run();
			}
		};
		if (dastorages != null)
			for (int i = 0; i < dastorages.length; i++) {
				ADataAdapterStorage s = dastorages[i];
				for (DataAdapterDescriptor d : s.getDataAdapterDescriptors()) {
					MenuItem m1 = new MenuItem(listMenu, SWT.PUSH);
					m1.setText(d.getName());
					m1.setImage(JaspersoftStudioPlugin.getImage(d.getIcon16()));
					m1.addSelectionListener(listener);
					m1.setData("da.key", d);
				}
				if (!s.getDataAdapterDescriptors().isEmpty() && i < dastorages.length - 1
						&& !dastorages[i + 1].getDataAdapterDescriptors().isEmpty())
					new MenuItem(listMenu, SWT.SEPARATOR);
			}

		return listMenu;
	}

	private void refresh() {

	}

	public void setDataAdapterStorages(ADataAdapterStorage[] dastorages) {
		if (this.dastorages != null) {
			for (ADataAdapterStorage das : dastorages)
				das.removePropertyChangeListener(this);
		}

		this.dastorages = dastorages;
		if (dastorages != null) {
			for (ADataAdapterStorage das : dastorages)
				das.addPropertyChangeListener(this);
		}
	}

	public Menu getMenu(Menu parent) {
		return null;
	}

	public void propertyChange(PropertyChangeEvent evt) {
		refresh();
	}

	public void viewerStateChanged(ReportViewerEvent arg0) {
		refresh();
	}

	private DataAdapterDescriptor selectedDA;

	public void setSelected(String d) {
		if (d != null && dastorages != null) {
			for (ADataAdapterStorage das : dastorages)
				for (DataAdapterDescriptor dad : das.getDataAdapterDescriptors()) {
					if (dad.getName().equals(d)) {
						setSelected(dad);
						return;
					}
				}
		}
	}

	public void setSelected(DataAdapterDescriptor d) {
		selectedDA = d;
		// set current
		String name = d.getName();
		if (name.length() > 17)
			name = name.substring(0, 17);
		setText(name);
		setDescription(d.getDescription());
		if (parent != null) {
			ToolBar toolBar = (ToolBar) parent;
			toolBar.pack(true);
			toolBar.getParent().getParent().layout(true);
		}
	}

	public DataAdapterDescriptor getSelected() {
		return selectedDA;
	}
}
