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
package com.jaspersoft.studio.property.descriptor.classname;

import java.text.MessageFormat;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.fieldassist.IContentProposalProvider;
import org.eclipse.jface.fieldassist.TextContentAdapter;
import org.eclipse.jface.fieldassist.TextControlCreator;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.fieldassist.ContentAssistField;
import org.eclipse.ui.texteditor.ITextEditorActionDefinitionIds;

/**
 * A cell editor that manages a content assist field. The cell editor's value is the text string itself.
 * 
 * This class may be instantiated; it is not intended to be subclassed.
 * 
 */
public class ContentAssistCellEditor extends CellEditor {
	protected ContentAssistField field;

	protected IContentProposalProvider contentProposalProvider;

	protected char[] completionProposalAutoActivationCharacters;

	private ModifyListener modifyListener;

	public ContentAssistCellEditor(char[] completionProposalAutoActivationCharacters,
			IContentProposalProvider contentProposalProvider) {
		super();
		this.completionProposalAutoActivationCharacters = completionProposalAutoActivationCharacters;
		this.contentProposalProvider = contentProposalProvider;
	}

	/**
	 * Return the modify listener.
	 */
	private ModifyListener getModifyListener() {
		if (modifyListener == null) {
			modifyListener = new ModifyListener() {
				public void modifyText(ModifyEvent e) {
					editOccured(e);
				}
			};
		}
		return modifyListener;
	}

	public ContentAssistCellEditor(Composite parent, int style, char[] completionProposalAutoActivationCharacters,
			IContentProposalProvider contentProposalProvider) {
		super(parent, style);
	}

	public ContentAssistCellEditor(Composite parent, char[] completionProposalAutoActivationCharacters,
			IContentProposalProvider contentProposalProvider) {
		super(parent);
	}

	/**
	 * State information for updating action enablement
	 */
	private boolean isSelection = false;

	private boolean isDeleteable = false;

	private boolean isSelectable = false;

	private Text text;

	@Override
	protected Control createControl(Composite parent) {
		// SimpleContentProposalProvider proposelProvider = new SimpleContentProposalProvider( new String[] { "a", "b",
		// "c"});

		field = new ContentAssistField(parent, SWT.SINGLE, new TextControlCreator(), new TextContentAdapter(),
				contentProposalProvider, ITextEditorActionDefinitionIds.CONTENT_ASSIST_PROPOSALS,
				completionProposalAutoActivationCharacters);

		text = (Text) field.getControl();
		text.addSelectionListener(new SelectionAdapter() {
			public void widgetDefaultSelected(SelectionEvent e) {
				handleDefaultSelection(e);
			}
		});

		text.addKeyListener(new KeyAdapter() {
			// hook key pressed - see PR 14201
			public void keyPressed(KeyEvent e) {
				keyReleaseOccured(e);

				// as a result of processing the above call, clients may have
				// disposed this cell editor
				if ((getControl() == null) || getControl().isDisposed()) {
					return;
				}
				checkSelection(); // see explaination below
				checkDeleteable();
				checkSelectable();
			}
		});
		text.addTraverseListener(new TraverseListener() {
			public void keyTraversed(TraverseEvent e) {
				if (e.detail == SWT.TRAVERSE_ESCAPE || e.detail == SWT.TRAVERSE_RETURN) {
					e.doit = false;
				}
			}
		});
		// We really want a selection listener but it is not supported so we
		// use a key listener and a mouse listener to know when selection changes
		// may have occured
		text.addMouseListener(new MouseAdapter() {
			public void mouseUp(MouseEvent e) {
				checkSelection();
				checkDeleteable();
				checkSelectable();
			}
		});
		text.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				ContentAssistCellEditor.this.focusLost();
			}
		});
		text.setFont(parent.getFont());
		text.setBackground(parent.getBackground());
		text.setText("");//$NON-NLS-1$
		text.addModifyListener(getModifyListener());

		return field.getLayoutControl();
	}

	/**
	 * Checks to see if the "deleteable" state (can delete/ nothing to delete) has changed and if so fire an enablement
	 * changed notification.
	 */
	private void checkDeleteable() {
		boolean oldIsDeleteable = isDeleteable;
		isDeleteable = isDeleteEnabled();
		if (oldIsDeleteable != isDeleteable) {
			fireEnablementChanged(DELETE);
		}
	}

	/**
	 * Checks to see if the "selectable" state (can select) has changed and if so fire an enablement changed notification.
	 */
	private void checkSelectable() {
		boolean oldIsSelectable = isSelectable;
		isSelectable = isSelectAllEnabled();
		if (oldIsSelectable != isSelectable) {
			fireEnablementChanged(SELECT_ALL);
		}
	}

	/**
	 * Checks to see if the selection state (selection / no selection) has changed and if so fire an enablement changed
	 * notification.
	 */
	private void checkSelection() {
		boolean oldIsSelection = isSelection;
		isSelection = text.getSelectionCount() > 0;
		if (oldIsSelection != isSelection) {
			fireEnablementChanged(COPY);
			fireEnablementChanged(CUT);
		}
	}

	/**
	 * Processes a modify event that occurred in this text cell editor. This framework method performs validation and sets
	 * the error message accordingly, and then reports a change via fireEditorValueChanged. Subclasses should call this
	 * method at appropriate times. Subclasses may extend or reimplement.
	 * 
	 * @param e
	 *          the SWT modify event
	 */
	protected void editOccured(ModifyEvent e) {
		String value = text.getText();
		if (value == null) {
			value = "";//$NON-NLS-1$
		}
		Object typedValue = value;
		boolean oldValidState = isValueValid();
		boolean newValidState = isCorrect(typedValue);
		if (typedValue == null && newValidState) {
			Assert.isTrue(false, "Validator isn't limiting the cell editor's type range");//$NON-NLS-1$
		}
		if (!newValidState) {
			// try to insert the current value into the error message.
			setErrorMessage(MessageFormat.format(getErrorMessage(), new Object[] { value }));
		}
		valueChanged(oldValidState, newValidState);
	}

	/**
	 * Handles a default selection event from the text control by applying the editor value and deactivating this cell
	 * editor.
	 * 
	 * @param event
	 *          the selection event
	 */
	protected void handleDefaultSelection(SelectionEvent event) {
		// same with enter-key handling code in keyReleaseOccured(e);
		fireApplyEditorValue();
		deactivate();
	}

	@Override
	protected void doSetFocus() {
		if (field != null) {
			field.getControl().setFocus();
		}
	}

	@Override
	protected Object doGetValue() {
		return ((Text) field.getControl()).getText();
	}

	@Override
	protected void doSetValue(Object value) {
		((Text) field.getControl()).setText(value.toString());
	}

	/**
	 * The implementation of this method copies the current selection to the clipboard.
	 */
	public void performCopy() {
		text.copy();
	}

	/**
	 * The implementation of this method cuts the current selection to the clipboard.
	 */
	public void performCut() {
		text.cut();
		checkSelection();
		checkDeleteable();
		checkSelectable();
	}

	/**
	 * The implementation of this method deletes the current selection or, if there is no selection, the character next
	 * character from the current position.
	 */
	public void performDelete() {
		if (text.getSelectionCount() > 0) {
			// remove the contents of the current selection
			text.insert(""); //$NON-NLS-1$
		} else {
			// remove the next character
			int pos = text.getCaretPosition();
			if (pos < text.getCharCount()) {
				text.setSelection(pos, pos + 1);
				text.insert(""); //$NON-NLS-1$
			}
		}
		checkSelection();
		checkDeleteable();
		checkSelectable();
	}

	/**
	 * The implementation of this method pastes the the clipboard contents over the current selection.
	 */
	public void performPaste() {
		text.paste();
		checkSelection();
		checkDeleteable();
		checkSelectable();
	}

	/**
	 * The implementation of this method selects all of the current text.
	 */
	public void performSelectAll() {
		text.selectAll();
		checkSelection();
		checkDeleteable();
	}

	/**
	 * Since a text editor field is scrollable we don't set a minimumSize.
	 */
	public LayoutData getLayoutData() {
		return new LayoutData();
	}

	/**
	 * Processes a key release event that occurred in this cell editor.
	 * 
	 * The implementation of this framework method ignores when the RETURN key is pressed since this is handled in
	 * handleDefaultSelection. An exception is made for Ctrl+Enter for multi-line texts, since a default selection event
	 * is not sent in this case.
	 * 
	 * 
	 * @param keyEvent
	 *          the key event
	 */
	protected void keyReleaseOccured(KeyEvent keyEvent) {
		if (keyEvent.character == '\r') { // Return key
			// Enter is handled in handleDefaultSelection.
			// Do not apply the editor value in response to an Enter key event
			// since this can be received from the IME when the intent is -not-
			// to apply the value.
			// See bug 39074 [CellEditors] [DBCS] canna input mode fires bogus event from Text Control
			//
			// An exception is made for Ctrl+Enter for multi-line texts, since
			// a default selection event is not sent in this case.
			if (text != null && !text.isDisposed() && (text.getStyle() & SWT.MULTI) != 0) {
				if ((keyEvent.stateMask & SWT.CTRL) != 0) {
					super.keyReleaseOccured(keyEvent);
				}
			}
			return;
		}
		super.keyReleaseOccured(keyEvent);
	}
}
