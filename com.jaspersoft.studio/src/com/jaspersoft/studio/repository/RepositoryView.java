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
package com.jaspersoft.studio.repository;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.GroupMarker;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ViewPart;

import com.jaspersoft.studio.outline.ReportTreeContetProvider;
import com.jaspersoft.studio.outline.ReportTreeLabelProvider;
import com.jaspersoft.studio.repository.actions.CreateDataSourceAction;
import com.jaspersoft.studio.repository.actions.DeleteDataSourceAction;
import com.jaspersoft.studio.repository.actions.EditDataSourceAction;

public class RepositoryView extends ViewPart {

	private TreeViewer treeViewer;
	private Action deleteItemAction;
	// private Action selectAllAction;
	private Action addItemAction;
	private Action editItemAction;

	@Override
	public void createPartControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(1, false));

		treeViewer = new TreeViewer(composite);
		treeViewer.getTree().setLayoutData(new GridData(GridData.FILL_BOTH));
		treeViewer.setContentProvider(new ReportTreeContetProvider());
		treeViewer.setLabelProvider(new ReportTreeLabelProvider());
		treeViewer.setInput(RepositoryManager.getResources()); // pass a non-null that will be ignored
		treeViewer.expandToLevel(2);
		treeViewer.addDoubleClickListener(new IDoubleClickListener() {

			public void doubleClick(DoubleClickEvent event) {
				Display.getCurrent().asyncExec(new Runnable() {
					public void run() {
						new EditDataSourceAction(treeViewer).run();
					}
				});
			}
		});

		// Create menu and toolbars.
		createActions();
		createMenu();
		createToolbar();
		createContextMenu();
		hookGlobalActions();

		// Restore state from the previous session.
		restoreState();
	}

	private IMemento memento;

	public void init(IViewSite site, IMemento memento) throws PartInitException {
		init(site);
		this.memento = memento;
	}

	public void saveState(IMemento memento) {
		IStructuredSelection sel = (IStructuredSelection) treeViewer.getSelection();
		if (sel.isEmpty())
			return;
		memento = memento.createChild("selection");
		// Iterator<E> iter = sel.iterator();
		// while (iter.hasNext()) {
		// Word word = (Word) iter.next();
		// memento.createChild("descriptor", word.toString());
		// }

	}

	private void restoreState() {
		if (memento == null)
			return;
		memento = memento.getChild("selection");
		if (memento != null) {
			IMemento descriptors[] = memento.getChildren("descriptor");
			if (descriptors.length > 0) {
				List<IMemento> objList = new ArrayList<IMemento>(descriptors.length);
				// for (int nX = 0; nX < descriptors.length; nX++) {
				// String id = descriptors[nX].getID();
				// Word word = input.find(id);
				// if (word != null)
				// objList.add(word);
				// }
				treeViewer.setSelection(new StructuredSelection(objList));
			}
		}
		memento = null;
		updateActionEnablement();
	}

	private void hookGlobalActions() {
		// IActionBars bars = getViewSite().getActionBars();
		// bars.setGlobalActionHandler(IWorkbenchActionConstants.SELECT_ALL, selectAllAction);
		// bars.setGlobalActionHandler(IWorkbenchActionConstants.DELETE, deleteItemAction);
		treeViewer.getControl().addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent event) {
				if (event.character == SWT.DEL && event.stateMask == 0 && deleteItemAction.isEnabled()) {
					deleteItemAction.run();
				}
			}
		});
	}

	public void createActions() {
		addItemAction = new CreateDataSourceAction(treeViewer);
		editItemAction = new EditDataSourceAction(treeViewer);

		deleteItemAction = new DeleteDataSourceAction(treeViewer);

		// selectAllAction = new Action("Select All") {
		// public void run() {
		// ISelection s = treeViewer.getSelection();
		// if (s instanceof TreeSelection) {
		// }
		// }
		// };

		// Add selection listener.
		treeViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
				updateActionEnablement();
			}
		});
	}

	private void updateActionEnablement() {
		IStructuredSelection sel = (IStructuredSelection) treeViewer.getSelection();
		deleteItemAction.setEnabled(sel.size() > 0);
		editItemAction.setEnabled(sel.size() > 0);
	}

	/**
	 * Create menu.
	 */
	private void createMenu() {
		// IMenuManager mgr = getViewSite().getActionBars().getMenuManager();
		// mgr.add(selectAllAction);
	}

	/**
	 * Create toolbar.
	 */
	private void createToolbar() {
		// IToolBarManager mgr = getViewSite().getActionBars().getToolBarManager();
		// mgr.add(addItemAction);
	}

	private void createContextMenu() {
		// Create menu manager.
		MenuManager menuMgr = new MenuManager();
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager mgr) {
				fillContextMenu(mgr);
			}
		});

		// Create menu.
		Menu menu = menuMgr.createContextMenu(treeViewer.getControl());
		treeViewer.getControl().setMenu(menu);

		// Register menu for extension.
		getSite().registerContextMenu(menuMgr, treeViewer);
	}

	private void fillContextMenu(IMenuManager mgr) {
		if (addItemAction.isEnabled())
			mgr.add(addItemAction);
		if (editItemAction.isEnabled())
			mgr.add(editItemAction);
		mgr.add(new GroupMarker(IWorkbenchActionConstants.MB_ADDITIONS));
		mgr.add(new Separator());
		if (deleteItemAction.isEnabled())
			mgr.add(deleteItemAction);
		// mgr.add(new Separator());
		// mgr.add(selectAllAction);
	}

	@Override
	public void setFocus() {
		treeViewer.getControl().setFocus();
	}

}
