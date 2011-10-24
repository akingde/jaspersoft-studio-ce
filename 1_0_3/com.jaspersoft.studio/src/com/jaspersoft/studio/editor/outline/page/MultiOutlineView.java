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

package com.jaspersoft.studio.editor.outline.page;

import java.util.ArrayList;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.ui.part.Page;
import org.eclipse.ui.part.PageBook;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;

import com.jaspersoft.studio.editor.outline.JDReportOutlineView;

public class MultiOutlineView extends Page implements IContentOutlinePage, ISelectionProvider,
		ISelectionChangedListener, IAdaptable {
	private PageBook pagebook;
	private ISelection selection;
	private ArrayList<ISelectionChangedListener> listeners;
	private IContentOutlinePage currentPage;
	private IContentOutlinePage emptyPage;
	private IActionBars actionBars;
//	private EditorPart editor;

	public MultiOutlineView(EditorPart editor) {
//		this.editor = editor;
		listeners = new ArrayList<ISelectionChangedListener>();

	}

	public void addFocusListener(FocusListener listener) {
	}

	public void addSelectionChangedListener(ISelectionChangedListener listener) {
		listeners.add(listener);
	}

	@Override
	public void createControl(Composite parent) {
		pagebook = new PageBook(parent, SWT.NONE);
	}

	@Override
	public void dispose() {
		if (pagebook != null && !pagebook.isDisposed())
			pagebook.dispose();
		if (emptyPage != null) {
			emptyPage.dispose();
			emptyPage = null;
		}
		pagebook = null;
		listeners = null;
	}

	public boolean isDisposed() {
		return listeners == null;
	}

	@Override
	public Control getControl() {
		return pagebook;
	}

	public PageBook getPagebook() {
		return pagebook;
	}

	public ISelection getSelection() {
		return selection;
	}

	@Override
	public void makeContributions(IMenuManager menuManager, IToolBarManager toolBarManager,
			IStatusLineManager statusLineManager) {
	}

	public void removeFocusListener(FocusListener listener) {
	}

	public void removeSelectionChangedListener(ISelectionChangedListener listener) {
		listeners.remove(listener);
	}

	public void selectionChanged(SelectionChangedEvent event) {
		setSelection(event.getSelection());
	}

	@Override
	public void setActionBars(IActionBars actionBars) {
		this.actionBars = actionBars;
		registerToolbarActions(actionBars);
		if (currentPage != null)
			setPageActive(currentPage);

	}

	public IActionBars getActionBars() {
		return actionBars;
	}

	@Override
	public void setFocus() {
		if (currentPage != null)
			currentPage.setFocus();
	}

	private IContentOutlinePage getEmptyPage() {
		if (emptyPage == null)
			emptyPage = new EmptyOutlinePage();
		return emptyPage;
	}

	public void setPageActive(IContentOutlinePage page) {
		if (page == null) {
			page = getEmptyPage();
		}
		if (currentPage != null) {
			currentPage.removeSelectionChangedListener(this);
		}
		if (getActionBars() != null && getActionBars().getToolBarManager() != null)
			getActionBars().getToolBarManager().removeAll();
		if (getSite() != null && page instanceof JDReportOutlineView)
			((JDReportOutlineView) page).init(getSite());

		page.addSelectionChangedListener(this);
		this.currentPage = page;
		if (pagebook == null) {
			// still not being made
			return;
		}
		Control control = page.getControl();
		if (control == null || control.isDisposed()) {
			// first time
			page.createControl(pagebook);
			page.setActionBars(getActionBars());
			control = page.getControl();
		}
		pagebook.showPage(control);
		setSelection(page.getSelection());
		getActionBars().getToolBarManager().update(true);
	}

	/**
	 * Set the selection.
	 */
	public void setSelection(ISelection selection) {
		this.selection = selection;
		if (listeners == null)
			return;
		SelectionChangedEvent e = new SelectionChangedEvent(this, selection);
		for (int i = 0; i < listeners.size(); i++) {
			((ISelectionChangedListener) listeners.get(i)).selectionChanged(e);
		}
	}

	private void registerToolbarActions(IActionBars actionBars) {
		IToolBarManager toolBarManager = actionBars.getToolBarManager();
		if (toolBarManager != null) {
			// toolBarManager.add(new ToggleLinkWithEditorAction(editor));
			// toolBarManager.add(new SortingAction());
		}
	}

	public Object getAdapter(Class adapter) {
		if (currentPage instanceof IAdaptable)
			return ((IAdaptable) currentPage).getAdapter(adapter);
		return null;
	}
}
