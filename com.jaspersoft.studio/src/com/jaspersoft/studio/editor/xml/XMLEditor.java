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
package com.jaspersoft.studio.editor.xml;

import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.parts.TreeViewer;
import org.eclipse.ui.editors.text.TextEditor;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;

import com.jaspersoft.studio.editor.outline.JDReportOutlineView;
import com.jaspersoft.studio.editor.outline.page.EmptyOutlinePage;

/*
 * The Class XMLEditor.
 */
public class XMLEditor extends TextEditor {

	/** The color manager. */
	private ColorManager colorManager;

	/** The action registry. */
	private ActionRegistry actionRegistry = new ActionRegistry() {
		@Override
		public org.eclipse.jface.action.IAction getAction(Object key) {
			return XMLEditor.this.getAction((String) key);
		};
	};

	/**
	 * Instantiates a new xML editor.
	 */
	public XMLEditor() {
		super();
		colorManager = new ColorManager();
		setSourceViewerConfiguration(new XMLConfiguration(colorManager));
		setDocumentProvider(new XMLDocumentProvider());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.editors.text.TextEditor#dispose()
	 */
	@Override
	public void dispose() {
		colorManager.dispose();
		super.dispose();
	}

	/**
	 * Gets the action registry.
	 * 
	 * @return the action registry
	 */
	public ActionRegistry getActionRegistry() {
		if (actionRegistry == null)
			actionRegistry = new ActionRegistry();
		return actionRegistry;
	}

	private IContentOutlinePage outlinePage;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.editors.text.TextEditor#getAdapter(java.lang.Class)
	 */
	@Override
	public Object getAdapter(Class adapter) {
		if (adapter == ActionRegistry.class)
			return getActionRegistry();
		if (IContentOutlinePage.class.equals(adapter))
			return getOutlineView();

		return super.getAdapter(adapter);
	}

	protected IContentOutlinePage getOutlineView() {
		if (outlinePage == null) {
			outlinePage = new EmptyOutlinePage();
//			outlinePage = new XMLContentOutlinePage(this);
		}
		return outlinePage;
	}
}
