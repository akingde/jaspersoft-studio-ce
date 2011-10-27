/*
 * JasperReports - Free Java Reporting Library. Copyright (C) 2001 - 2009 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of JasperReports.
 * 
 * JasperReports is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser General
 * Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * JasperReports is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License along with JasperReports. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.repository;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeViewerListener;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeExpansionEvent;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
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
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.views.properties.IPropertySheetPage;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.IPropertySource2;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertySheetPageContributor;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.MRoot;
import com.jaspersoft.studio.outline.ReportTreeContetProvider;
import com.jaspersoft.studio.outline.ReportTreeLabelProvider;
import com.jaspersoft.studio.plugin.ExtensionManager;

public class RepositoryView extends ViewPart implements ITabbedPropertySheetPageContributor {
	public RepositoryView() {
	}

	private TreeViewer treeViewer;
	private PropertyChangeListener propChangeListener = new PropertyChangeListener() {

		public void propertyChange(PropertyChangeEvent evt) {
			if (!treeViewer.getTree().isDisposed())
				treeViewer.refresh(true);
		}
	};

	@Override
	public Object getAdapter(Class type) {
		if (type == IPropertySource.class)
			return getPropertySheetPage();
		if (type == IPropertySource2.class)
			return getPropertySheetPage();
		if (type == IPropertySheetPage.class)
			return getPropertySheetPage();
		return super.getAdapter(type);
	}

	/** The property sheet page. */
	private IPropertySheetPage propertySheetPage;

	public IPropertySheetPage getPropertySheetPage() {
		if (propertySheetPage == null)
			propertySheetPage = new TabbedPropertySheetPage(this, true);
		return propertySheetPage;
	}

	public String getContributorId() {
		return getSite().getId();
	}

	@Override
	public void createPartControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(1, false));

		treeViewer = new TreeViewer(composite);
		treeViewer.getTree().setLayoutData(new GridData(GridData.FILL_BOTH));
		treeViewer.setContentProvider(new ReportTreeContetProvider());
		treeViewer.setLabelProvider(new ReportTreeLabelProvider());
		treeViewer.setInput(getResources()); // pass a non-null that will be ignored
		treeViewer.expandToLevel(2);
		getSite().setSelectionProvider(treeViewer);
		treeViewer.addDoubleClickListener(new IDoubleClickListener() {

			public void doubleClick(DoubleClickEvent event) {
				Display.getDefault().asyncExec(new Runnable() {
					public void run() {
						rprovs = getExtensionManager();
						for (IRepositoryViewProvider rp : rprovs) {
							rp.doubleClick(treeViewer);
						}
					}
				});
			}
		});
		treeViewer.addTreeListener(new ITreeViewerListener() {

			public void treeExpanded(TreeExpansionEvent event) {
				rprovs = getExtensionManager();
				for (IRepositoryViewProvider rp : rprovs)
					rp.handleTreeEvent(event);
			}

			public void treeCollapsed(TreeExpansionEvent event) {
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
		rprovs = getExtensionManager();
		for (IRepositoryViewProvider rp : rprovs) {
			rp.addPropertyChangeListener(propChangeListener);
		}
	}

	@Override
	public void dispose() {
		rprovs = getExtensionManager();
		for (IRepositoryViewProvider rp : rprovs) {
			rp.removePropertyChangeListener(propChangeListener);
		}
		super.dispose();
	}

	private IMemento memento;
	private List<IRepositoryViewProvider> rprovs;
	private ExtensionManager extensionManager;

	@Override
	public void init(IViewSite site, IMemento memento) throws PartInitException {
		init(site);
		this.memento = memento;
		new ExtensionManager().init();
	}

	@Override
	public void saveState(IMemento memento) {
		IStructuredSelection sel = (IStructuredSelection) treeViewer.getSelection();
		if (sel.isEmpty())
			return;
		memento = memento.createChild("selection"); //$NON-NLS-1$
		// Iterator<E> iter = sel.iterator();
		// while (iter.hasNext()) {
		// Word word = (Word) iter.next();
		// memento.createChild("descriptor", word.toString());
		// }

	}

	private void restoreState() {
		if (memento == null)
			return;
		memento = memento.getChild("selection"); //$NON-NLS-1$
		if (memento != null) {
			IMemento descriptors[] = memento.getChildren("descriptor"); //$NON-NLS-1$
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
		// updateActionEnablement();
	}

	private void hookGlobalActions() {
		// IActionBars bars = getViewSite().getActionBars();
		// bars.setGlobalActionHandler(IWorkbenchActionConstants.SELECT_ALL, selectAllAction);
		// bars.setGlobalActionHandler(IWorkbenchActionConstants.DELETE, deleteItemAction);
		treeViewer.getControl().addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent event) {
				rprovs = getExtensionManager();
				for (IRepositoryViewProvider rp : rprovs) {
					rp.hookKeyEvent(event);
				}
			}
		});
	}

	public void createActions() {

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
		IToolBarManager mgr = getViewSite().getActionBars().getToolBarManager();

		rprovs = getExtensionManager();
		for (IRepositoryViewProvider rp : rprovs) {
			Action[] actions = rp.getActions(treeViewer);
			if (actions != null) {
				for (Action a : actions)
					mgr.add(a);
			}
		}
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
		TreeSelection s = (TreeSelection) treeViewer.getSelection();
		TreePath[] p = s.getPaths();
		List<IAction> alist = null;
		for (int i = 0; i < p.length; i++) {
			Object obj = p[i].getLastSegment();
			if (obj instanceof ANode) {
				rprovs = getExtensionManager();
				List<IAction> tlist = new ArrayList<IAction>();
				for (IRepositoryViewProvider rp : rprovs) {
					List<IAction> t = rp.fillContextMenu(treeViewer, (ANode) obj);
					if (t != null)
						tlist.addAll(t);
				}
				if (tlist == null || tlist.isEmpty())
					return;
				if (alist == null) {
					alist = tlist;
				} else {
					List<IAction> todelete = new ArrayList<IAction>();
					for (IAction a : alist) {
						if (!tlist.contains(a)) {
							todelete.add(a);
						}
					}
					alist.removeAll(todelete);
					if (alist.isEmpty())
						return;
				}
			}
		}
		if (alist != null) {
			for (IAction act : alist)
				mgr.add(act);
		}
	}

	@Override
	public void setFocus() {
		treeViewer.getControl().setFocus();
	}

	public ANode getResources() {
		MRoot rootNode = new MRoot(null, null);

		rprovs = getExtensionManager();
		for (IRepositoryViewProvider rp : rprovs) {
			rp.getNode(rootNode);
		}

		return rootNode;
	}

	private List<IRepositoryViewProvider> getExtensionManager() {
		if (extensionManager == null)
			extensionManager = new ExtensionManager();
		if (rprovs == null)
			rprovs = extensionManager.getRepositoryProviders();
		return rprovs;
	}
}
