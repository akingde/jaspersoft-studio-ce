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

import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuCreator;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.editor.action.snap.SizeGridAction;
import com.jaspersoft.studio.editor.action.snap.SnapToGridAction;
import com.jaspersoft.studio.editor.action.snap.SnapToGuidesAction;
import com.jaspersoft.studio.editor.gef.ui.actions.RZoomComboContributionItem;
import com.jaspersoft.studio.editor.report.ReportEditor;
import com.jaspersoft.studio.plugin.AContributorAction;
import com.jaspersoft.studio.property.dataset.dialog.DatasetAction;

public class TFContainer extends Composite {
	private StackLayout stackLayout;
	private ToolBar toolBar;
	private ToolBar additionalToolbar;

	public TFContainer(Composite parent, int style) {
		super(parent, style);
		GridLayout layout = new GridLayout(2,false);
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		layout.verticalSpacing = 0;
		setLayout(layout);

		toolBar = new ToolBar(this, SWT.HORIZONTAL | SWT.FLAT | SWT.WRAP | SWT.RIGHT);
		
		additionalToolbar = new ToolBar(this, SWT.HORIZONTAL | SWT.FLAT | SWT.WRAP | SWT.RIGHT);
		GridData additionalToolbarGD = new GridData(SWT.RIGHT, SWT.CENTER, true, false);
		additionalToolbar.setLayoutData(additionalToolbarGD);
		additionalToolbarManager = new ToolBarManager(additionalToolbar);
		
		content = new Composite(this, SWT.NONE);
		stackLayout = new StackLayout();
		stackLayout.marginWidth = 0;
		stackLayout.marginHeight = 0;
		content.setLayout(stackLayout);
		content.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
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
		this.pack();
		this.layout(true);
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
	private ToolBarManager additionalToolbarManager;
	private boolean additionalToolbarPopulated;

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
		this.pack();
		this.layout(true);
	}
	
	public void update(TFItem tfItem) {
		for (ToolItem it : toolBar.getItems()) {
			if (it.getData() == tfItem) {	
				if(tfItem.getData() instanceof ReportEditor){
					populateAdditionalToolbar((ReportEditor) tfItem.getData());
				}
				it.setText(tfItem.getText());
				it.setImage(tfItem.getImage());
				toolBar.update();
				layout(true);
				break;
			}
		}
	}
	
	/*
	 * Enrich the toolbar manager registered for the additional toolbar on the right.
	 */
	private void populateAdditionalToolbar(ReportEditor reportEditor){
		if(!additionalToolbarPopulated){
			additionalToolbarManager.add(reportEditor.getActionRegistry().getAction(DatasetAction.ID));
			additionalToolbarManager.add(new Separator());
			additionalToolbarManager.add(reportEditor.getActionRegistry().getAction(GEFActionConstants.ZOOM_IN));
			additionalToolbarManager.add(reportEditor.getActionRegistry().getAction(GEFActionConstants.ZOOM_OUT));
			additionalToolbarManager.add(new RZoomComboContributionItem(reportEditor.getEditorSite().getPage()));
			additionalToolbarManager.add(new Separator());
			// Contributed actions
			List<AContributorAction> contributedActions = JaspersoftStudioPlugin.getExtensionManager().getActions();
			for (AContributorAction a : contributedActions){
				additionalToolbarManager.add(a);
			}
			additionalToolbarManager.add(new DropDownAction(reportEditor.getActionRegistry()));
			additionalToolbarManager.update(true);
			additionalToolbarPopulated=true;
		}
	}
	
	/*
	 * Dropdown action to contribute a settings menu with the menu items
	 * taken from the global View menu.
	 */
	private class DropDownAction extends Action implements IMenuCreator {
		private Menu menu;
		private ActionRegistry actionRegistry;

		public DropDownAction(ActionRegistry actionRegistry) {
			setText("Settings");
			setMenuCreator(this);
			this.actionRegistry=actionRegistry;
		}

		@Override
		public void dispose() {
			if (menu != null) {
				menu.dispose();
				menu = null;
			}
		}

		@Override
		public Menu getMenu(Menu parent) {
			return null;
		}

		@Override
		public Menu getMenu(Control parent) {
			if (menu != null)
				menu.dispose();
			menu = new Menu(parent);
			addActionToMenu(menu, actionRegistry.getAction(GEFActionConstants.TOGGLE_RULER_VISIBILITY));
			addActionToMenu(menu, actionRegistry.getAction(SnapToGuidesAction.ID));
			new MenuItem(menu, SWT.SEPARATOR);
			addActionToMenu(menu, actionRegistry.getAction(GEFActionConstants.TOGGLE_GRID_VISIBILITY));
			addActionToMenu(menu, actionRegistry.getAction(SnapToGridAction.ID));
			addActionToMenu(menu, actionRegistry.getAction(GEFActionConstants.TOGGLE_SNAP_TO_GEOMETRY));
			addActionToMenu(menu, actionRegistry.getAction(SizeGridAction.ID));
			new MenuItem(menu, SWT.SEPARATOR);
			for(String id : JaspersoftStudioPlugin.getDecoratorManager().getActionIDs()){
				addActionToMenu(menu, actionRegistry.getAction(id));
			}
			return menu;
		}
		
		/*
		 * Adds an item to the existing menu using, using the contributed action.
		 */
		private void addActionToMenu(Menu parent, IAction action) {
			ActionContributionItem item = new ActionContributionItem(action);
			item.fill(parent, -1);
		}

		@Override
		public void run() {
			// Do Nothing
		}
	}
}
