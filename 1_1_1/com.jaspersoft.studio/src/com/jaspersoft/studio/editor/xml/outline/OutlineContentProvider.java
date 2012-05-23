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

import java.util.List;

import org.eclipse.jface.text.BadPositionCategoryException;
import org.eclipse.jface.text.DefaultPositionUpdater;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IPositionUpdater;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.xml.sax.helpers.LocatorImpl;

import com.jaspersoft.studio.editor.xml.xml.XMLElement;
import com.jaspersoft.studio.editor.xml.xml.XMLParser;

public class OutlineContentProvider implements ITreeContentProvider {

	private XMLElement root = null;
	private IEditorInput input;
	private IDocumentProvider documentProvider;

	protected final static String TAG_POSITIONS = "__tag_positions";
	protected IPositionUpdater positionUpdater = new DefaultPositionUpdater(TAG_POSITIONS);

	public OutlineContentProvider(IDocumentProvider provider) {
		super();
		this.documentProvider = provider;
	}

	public Object[] getChildren(Object parentElement) {
		if (parentElement == input) {
			if (root == null)
				return new Object[0];
			List<XMLElement> childrenDTDElements = root.getChildrenDTDElements();
			if (childrenDTDElements != null)
				return childrenDTDElements.toArray();
		} else {
			XMLElement parent = (XMLElement) parentElement;
			List<XMLElement> childrenDTDElements = parent.getChildrenDTDElements();
			if (childrenDTDElements != null)
				return childrenDTDElements.toArray();
		}
		return new Object[0];
	}

	public Object getParent(Object element) {
		if (element instanceof XMLElement)
			return ((XMLElement) element).getParent();
		return null;
	}

	public boolean hasChildren(Object element) {
		if (element == input)
			return true;
		else {
			return ((XMLElement) element).getChildrenDTDElements().size() > 0;
		}
	}

	public Object[] getElements(Object inputElement) {
		if (root == null)
			return new Object[0];
		List<XMLElement> childrenDTDElements = root.getChildrenDTDElements();
		if (childrenDTDElements != null)
			return childrenDTDElements.toArray();
		return new Object[0];
	}

	public void dispose() {
	}

	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {

		if (oldInput != null) {
			IDocument document = documentProvider.getDocument(oldInput);
			if (document != null) {
				try {
					document.removePositionCategory(TAG_POSITIONS);
				} catch (BadPositionCategoryException x) {
				}
				document.removePositionUpdater(positionUpdater);
			}
		}

		input = (IEditorInput) newInput;

		if (newInput != null) {
			IDocument document = documentProvider.getDocument(newInput);
			if (document != null) {
				document.addPositionCategory(TAG_POSITIONS);
				document.addPositionUpdater(positionUpdater);

				XMLElement rootElement = parseRootElement(document);
				if (rootElement != null) {
					root = rootElement;
				}
			}
		}
	}

	private XMLElement parseRootElement(IDocument document) {
		String text = document.get();
		XMLElement tagPositions = parseRootElements(text, document);
		return tagPositions;
	}

	private XMLElement parseRootElements(String text, IDocument document) {
		try {
			XMLParser xmlParser = new XMLParser();
			OutlineContentHandler contentHandler = new OutlineContentHandler();
			contentHandler.setDocument(document);
			contentHandler.setPositionCategory(TAG_POSITIONS);
			contentHandler.setDocumentLocator(new LocatorImpl());
			xmlParser.setContentHandler(contentHandler);
			xmlParser.doParse(text);
			XMLElement root = contentHandler.getRootElement();
			return root;
		} catch (Exception e) {
			return null;
		}
	}

}