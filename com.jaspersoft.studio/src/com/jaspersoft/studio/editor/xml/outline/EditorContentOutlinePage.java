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

package com.jaspersoft.studio.editor.xml.outline;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.texteditor.ITextEditor;
import org.eclipse.ui.views.contentoutline.ContentOutlinePage;

import com.jaspersoft.studio.editor.xml.xml.XMLElement;

 
public class EditorContentOutlinePage extends ContentOutlinePage
{

	private ITextEditor editor;
	private IEditorInput input;
	private OutlineContentProvider outlineContentProvider;
	private OutlineLabelProvider outlineLabelProvider;

	public EditorContentOutlinePage(ITextEditor editor)
	{
		super();
		this.editor = editor;
	}

	public void createControl(Composite parent)
	{
		super.createControl(parent);
		TreeViewer viewer = getTreeViewer();
		outlineContentProvider = new OutlineContentProvider(editor.getDocumentProvider());
		viewer.setContentProvider(outlineContentProvider);
		outlineLabelProvider = new OutlineLabelProvider();
		viewer.setLabelProvider(outlineLabelProvider);
		viewer.addSelectionChangedListener(this);

		//control is created after input is set
		if (input != null)
			viewer.setInput(input);
	}

	/**
	 * Sets the input of the outline page
	 */
	public void setInput(Object input)
	{
		this.input = (IEditorInput) input;
		update();
	}

	/*
	 * Change in selection
	 */
	public void selectionChanged(SelectionChangedEvent event)
	{
		super.selectionChanged(event);
		//find out which item in tree viewer we have selected, and set highlight range accordingly

		ISelection selection = event.getSelection();
		if (selection.isEmpty())
			editor.resetHighlightRange();
		else
		{
			XMLElement element = (XMLElement) ((IStructuredSelection) selection).getFirstElement();		
			
			int start = element.getPosition().getOffset();
			int length = element.getPosition().getLength();
			try
			{
				editor.setHighlightRange(start, length, true);
			}
			catch (IllegalArgumentException x)
			{
				editor.resetHighlightRange();
			}
		}
	}

	/**
	 * The editor is saved, so we should refresh representation
	 * 
	 * @param tableNamePositions
	 */
	public void update()
	{
		//set the input so that the outlines parse can be called
		//update the tree viewer state
		TreeViewer viewer = getTreeViewer();

		if (viewer != null)
		{
			Control control = viewer.getControl();
			if (control != null && !control.isDisposed())
			{
				control.setRedraw(false);
				viewer.setInput(input);
				viewer.expandAll();
				control.setRedraw(true);
			}
		}
	}

}