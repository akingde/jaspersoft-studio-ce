/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.preferences.bindings;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

import org.eclipse.jface.bindings.keys.ParseException;
import org.eclipse.jface.bindings.keys.SWTKeySupport;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.util.Util;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;

/**
 * <p>
 * A wrapper around the SWT text widget that traps literal key presses and
 * converts them into key sequences for display.
 * </p>
 *
 * @author Orlandin Marco
 */
public final class KeySequenceText {

	/**
	 * A key listener that traps incoming events and displays them in the
	 * wrapped text field. It has no effect on traversal operations.
	 */
	private class KeyTrapListener implements Listener {
		/**
		 * The index at which insertion should occur. This is used if there is a
		 * replacement occurring in the middle of the stroke
		 */
		private int insertionIndex = -1;

		/**
		 * Resets the insertion index to point nowhere. In other words, it is
		 * set to <code>-1</code>.
		 */
		void clearInsertionIndex() {
			insertionIndex = -1;
		}

		/**
		 * Deletes the current selection. If there is no selection, then it
		 * deletes the last key stroke.
		 *
		 * @param keyStrokes
		 *            The key strokes from which to delete. This list must not
		 *            be <code>null</code>, and must represent a valid key
		 *            sequence.
		 * @return An array of keystrokes minus the keystrokes that were
		 *         deleted.
		 */
		private final JSSKeyStroke[] deleteKeyStroke(final JSSKeyStroke[] keyStrokes) {
			clearInsertionIndex();

			if (hasSelection()) {
				/*
				 * Delete the current selection -- disallowing incomplete
				 * strokes in the middle of the sequence.
				 */
				final JSSKeyStroke[][] deletedKeyStrokes = new JSSKeyStroke[1][];
				deleteSelection(keyStrokes, true, deletedKeyStrokes);
				return deletedKeyStrokes[0];
			}
			
			//try to get where the stroke is
			int caretPosition = text.getCaretPosition();
			String[] strokes = text.getText().split(" ");
			int index = keyStrokes.length - 1;
			int parsedText = 0;
			for(String stroke : strokes) {
				parsedText += stroke.length() + 1;
				if (caretPosition < parsedText) {
					index = caretPosition;
					break;
				}
				caretPosition++;
			}
			
			// Remove the last key stroke.
			if (keyStrokes.length > 0 && keyStrokes.length < index) {
				final int newKeyStrokesLength = index;
				final JSSKeyStroke[] newKeyStrokes = new JSSKeyStroke[newKeyStrokesLength];
				System.arraycopy(keyStrokes, 0, newKeyStrokes, 0,
						newKeyStrokesLength);
				return newKeyStrokes;
			}

			return keyStrokes;
		}

		/**
		 * Handles the key pressed and released events on the wrapped text
		 * widget. This makes sure to either add the pressed key to the
		 * key stroke. if the pressed key are the arrows keys nothing is done
		 *
		 * @param event The triggering event; must not be <code>null</code>.
		 */
		@Override
		public void handleEvent(Event event) {
			
			//if (event.keyCode == SWT.ARROW_LEFT || event.keyCode == SWT.ARROW_RIGHT 
				//	|| event.keyCode == SWT.ARROW_DOWN || event.keyCode == SWT.ARROW_UP){
			//	return;
		//	}
			
			JSSKeyStroke[] keyStrokes = getKeySequence().getKeyStrokes();

			// Dispatch the event to the correct handler.
			if (event.type == SWT.KeyDown) {
				keyStrokes = handleKeyDown(event, keyStrokes);
			}

			// Update the underlying widget.
			setKeySequence(JSSKeySequence.getInstance(keyStrokes));

			// Prevent the event from reaching the widget.
			event.doit = false;
		}

		/**
		 * Handles the case where the key event is an <code>SWT.KeyDown</code>
		 * event. This either causes a deletion (if it is an unmodified
		 * backspace key stroke), or an insertion (if it is any other key).
		 *
		 * @param event
		 *            The trigger key down event; must not be <code>null</code>.
		 * @param keyStrokes
		 *            The current list of key strokes. This value must not be
		 *            <code>null</code>, and it must represent a valid key
		 *            sequence.
		 */
		private JSSKeyStroke[] handleKeyDown(Event event, JSSKeyStroke[] keyStrokes) {
			// Is it an unmodified backspace character?
			if ((event.character == SWT.BS || event.character == SWT.DEL) && (event.stateMask == 0)) {
				return deleteKeyStroke(keyStrokes);
			}
			
			return insertKeyStroke(event, keyStrokes);
		}

		/**
		 * <p>
		 * Handles the case where a key down event is leading to a key stroke
		 * being inserted. The current selection is deleted, and an invalid
		 * remanents of the stroke are also removed. The insertion is carried
		 * out at the cursor position.
		 * </p>
		 * <p>
		 * If only a natural key is selected (as part of a larger key stroke),
		 * then it is possible for the user to press a natural key to replace
		 * the old natural key. In this situation, pressing any modifier keys
		 * will replace the whole thing.
		 * </p>
		 * <p>
		 * If the insertion point is not at the end of the sequence, then
		 * incomplete strokes will not be immediately inserted. Only when the
		 * sequence is completed is the stroke inserted. This is a requirement
		 * as the widget must always represent a valid key sequence. The
		 * insertion point is tracked using <code>insertionIndex</code>,
		 * which is an index into the key stroke array.
		 * </p>
		 *
		 * @param event
		 *            The triggering key down event; must not be
		 *            <code>null</code>.
		 * @param keyStrokes
		 *            The key strokes into which the current stroke should be
		 *            inserted. This value must not be <code>null</code>, and
		 *            must represent a valid key sequence.
		 */
		private final JSSKeyStroke[] insertKeyStroke(final Event event, JSSKeyStroke[] keyStrokes) {
			// Compute the key stroke to insert.
			int key = SWTKeySupport.convertEventToUnmodifiedAccelerator(event);
			JSSKeyStroke[] strokes = convertAcceleratorToKeyStroke(key);
			JSSKeyStroke[] newStrokes = keyStrokes;
			for(JSSKeyStroke stroke : strokes){
				if (stroke.getNaturalKey() != JSSKeyStroke.NO_KEY){
					newStrokes = insertKeyStroke(stroke, keyStrokes);
				}
			}
			return newStrokes;
		}
		
		/**
		 * Given an SWT accelerator value, provide the corresponding key stroke.
		 *
		 * @param accelerator
		 *            The accelerator to convert; should be a valid SWT accelerator
		 *            value.
		 * @return The equivalent key stroke; never <code>null</code>.
		 */
		public JSSKeyStroke[] convertAcceleratorToKeyStroke(int accelerator) {
			int modifierKeys = accelerator & SWT.MODIFIER_MASK;
			if (accelerator == modifierKeys) {
				return new JSSKeyStroke[]{JSSKeyStroke.getInstance(modifierKeys)};
			} else {
				int naturalKey = accelerator - modifierKeys;
				return new JSSKeyStroke[]{JSSKeyStroke.getInstance(modifierKeys), JSSKeyStroke.getInstance(naturalKey)};
			}
		}
		
		private final JSSKeyStroke[] insertKeyStroke(JSSKeyStroke stroke, JSSKeyStroke[] keyStrokes) {
			/*
			 * Only insert the stroke if it is *not ScrollLock. Let's not get
			 * silly
			 */
			if ((SWT.NUM_LOCK == stroke.getNaturalKey())
					|| (SWT.CAPS_LOCK == stroke.getNaturalKey())
					|| (SWT.SCROLL_LOCK == stroke.getNaturalKey())) {
				return keyStrokes;
			}
			
			if (hasSelection()) {
				HashSet<JSSKeyStroke> selectedStrokes = getSelectKeystrokes(keyStrokes);
				//Don't add an already present stroke
				for(JSSKeyStroke keyStroke : keyStrokes){
					if (keyStroke.equals(stroke) && !selectedStrokes.contains(stroke)){
						return keyStrokes;
					}
				}
			
				// There is a selection that needs to be replaced.
				final JSSKeyStroke[][] deletedKeyStrokes = new JSSKeyStroke[1][];
				insertionIndex = deleteSelection(keyStrokes, true, deletedKeyStrokes);
				keyStrokes = deletedKeyStrokes[0];
				if (stroke.isModifier()){
					insertionIndex = 0;
				}
				keyStrokes = appenStrokeAt(keyStrokes, stroke, insertionIndex);
				clearInsertionIndex();

			} else {
				//Don't add an already present stroke
				for(JSSKeyStroke keyStroke : keyStrokes){
					if (keyStroke.equals(stroke)){
						return keyStrokes;
					}
				}
				
				// And then add the new stroke.
				if (stroke.isModifier()){
					keyStrokes = appenStrokeAt(keyStrokes, stroke, 0);
					clearInsertionIndex();					
				} else if ((keyStrokes.length == 0) || (insertionIndex >= keyStrokes.length) || (isCursorInLastPosition())) {
					keyStrokes = insertStrokeAt(keyStrokes, stroke, keyStrokes.length);
					clearInsertionIndex();
				} else {
					/*
					 * I'm just getting the insertionIndex here. No actual
					 * deletion should occur.
					 */
					final JSSKeyStroke[][] deletedKeyStrokes = new JSSKeyStroke[1][];
					insertionIndex = deleteSelection(keyStrokes, true, deletedKeyStrokes);
					keyStrokes = deletedKeyStrokes[0];
					keyStrokes = insertStrokeAt(keyStrokes, stroke, insertionIndex);
					clearInsertionIndex();
				}

			}

			return keyStrokes;
		}
	}
	

	/**
	 * A traversal listener that blocks all traversal except for tabs and arrow
	 * keys.
	 */
	private class TraversalFilter implements Listener {
		/**
		 * Handles the traverse event on the text field wrapped by this class.
		 * It swallows all traverse events example for tab and arrow key
		 * navigation. The other forms of navigation can be reached by tabbing
		 * off of the control.
		 *
		 * @param event
		 *            The trigger event; must not be <code>null</code>.
		 */
		@Override
		public void handleEvent(Event event) {
			switch (event.detail) {
			case SWT.TRAVERSE_ESCAPE:
			case SWT.TRAVERSE_MNEMONIC:
			case SWT.TRAVERSE_NONE:
			case SWT.TRAVERSE_PAGE_NEXT:
			case SWT.TRAVERSE_PAGE_PREVIOUS:
			case SWT.TRAVERSE_RETURN:
				event.type = SWT.None;
				event.doit = false;
				break;

			case SWT.TRAVERSE_TAB_NEXT:
			case SWT.TRAVERSE_TAB_PREVIOUS:
				// Check if modifiers other than just 'Shift' were
				// down.
				if ((event.stateMask & (SWT.MODIFIER_MASK ^ SWT.SHIFT)) != 0) {
					// Modifiers other than shift were down.
					event.type = SWT.None;
					event.doit = false;
					break;
				}

				//$FALL-THROUGH$ -- either no modifiers, or just shift.
			case SWT.TRAVERSE_ARROW_NEXT:
			case SWT.TRAVERSE_ARROW_PREVIOUS:
			default:
			}

		}
	}

	/**
	 * The manager responsible for installing and removing the traversal filter
	 * when the key sequence entry widget gains and loses focus.
	 */
	private class TraversalFilterManager implements FocusListener {
		/** The managed filter. We only need one instance. */
		private TraversalFilter filter = new TraversalFilter();

		private boolean filtering = false;

		/**
		 * Attaches the global traversal filter.
		 *
		 * @param event
		 *            Ignored.
		 */
		@Override
		public void focusGained(FocusEvent event) {
			Display.getCurrent().addFilter(SWT.Traverse, filter);
			filtering = true;
		}

		/**
		 * Detaches the global traversal filter.
		 *
		 * @param event
		 *            Ignored.
		 */
		@Override
		public void focusLost(FocusEvent event) {
			Display.getCurrent().removeFilter(SWT.Traverse, filter);
			filtering = false;
		}

		/**
		 * Remove the traverse filter if we close without focusOut.
		 */
		public void dispose() {
			if (filtering) {
				Display.getCurrent().removeFilter(SWT.Traverse, filter);
			}
		}
	}

	/**
	 * A modification listener that makes sure that external events to this
	 * class (i.e., direct modification of the underlying text) do not break
	 * this class' view of the world.
	 */
	private class UpdateSequenceListener implements ModifyListener {
		/**
		 * Handles the modify event on the underlying text widget.
		 *
		 * @param event
		 *            The triggering event; ignored.
		 */
		@Override
		public void modifyText(ModifyEvent event) {
			try {
				// The original sequence.
				JSSKeySequence originalSequence = getKeySequence();

				// The new sequence drawn from the text.
				String contents = getText();
				JSSKeySequence newSequence = JSSKeySequence.getInstance(contents);

				// Check to see if they're the same.
				if (!originalSequence.equals(newSequence)) {
					setKeySequence(newSequence);
				}

			} catch (ParseException e) {
				// Abort any cut/paste-driven modifications
				setKeySequence(getKeySequence());
			}
		}
	}

	static {
		TreeSet<JSSKeyStroke> trappedKeys = new TreeSet<JSSKeyStroke>();
		trappedKeys.add(convertAcceleratorToKeyStroke(SWT.TAB));
		trappedKeys.add(convertAcceleratorToKeyStroke(SWT.TAB	| SWT.SHIFT));
		trappedKeys.add(convertAcceleratorToKeyStroke(SWT.BS));
		trappedKeys.add(convertAcceleratorToKeyStroke(SWT.DEL));
		List<JSSKeyStroke> trappedKeyList = new ArrayList<JSSKeyStroke>(trappedKeys);
		TRAPPED_KEYS = Collections.unmodifiableList(trappedKeyList);
	}

	/** An empty string instance for use in clearing text values. */
	private static final String EMPTY_STRING = ""; //$NON-NLS-1$

	/**
	 * The special integer value for the maximum number of strokes indicating
	 * that an infinite number should be allowed.
	 */
	public static final int INFINITE = -1;

	/**
	 * The name of the property representing the current key sequence in this
	 * key sequence widget.
	 *
	 */
	public static final String P_KEY_SEQUENCE = "org.eclipse.jface.bindings.keys.KeySequenceText.KeySequence"; //$NON-NLS-1$

	/**
	 * The keys trapped by this widget. This list is guaranteed to be roughly
	 * accurate. Perfection is not possible, as SWT does not export traversal
	 * keys as constants.
	 */
	public static final List<JSSKeyStroke> TRAPPED_KEYS;

	/**
	 * The key filter attached to the underlying widget that traps key events.
	 */
	private final KeyTrapListener keyFilter = new KeyTrapListener();

	/**
	 * The text of the key sequence -- containing only the complete key strokes.
	 */
	private JSSKeySequence keySequence = JSSKeySequence.getInstance();

	/**
	 * Those listening to changes to the key sequence in this widget. This value
	 * may be <code>null</code> if there are no listeners.
	 */
	private Collection<IPropertyChangeListener> listeners = null;

	/** The maximum number of key strokes permitted in the sequence. */
	private int maxStrokes = INFINITE;

	/** The text widget that is wrapped for this class. */
	private final Text text;

	/**
	 * The listener that makes sure that the text widget remains up-to-date with
	 * regards to external modification of the text (e.g., cut & pasting).
	 */
	private final UpdateSequenceListener updateSequenceListener = new UpdateSequenceListener();

	/**
	 * Constructs an instance of <code>KeySequenceTextField</code> with the
	 * text field to use. If the platform is carbon (MacOS X), then the font is
	 * set to be the same font used to display accelerators in the menus.
	 *
	 * @param wrappedText
	 *            The text widget to wrap; must not be <code>null</code>.
	 */
	public KeySequenceText(Text wrappedText) {
		text = wrappedText;

		// Set the font if the platform is carbon.
		if (Util.isMac()) {
			// Don't worry about this font name here; it is the official menu
			// font and point size on the Mac.
			final Font font = new Font(text.getDisplay(),
					"Lucida Grande", 13, SWT.NORMAL); //$NON-NLS-1$
			text.setFont(font);
			text.addDisposeListener(new DisposeListener() {
				@Override
				public void widgetDisposed(DisposeEvent e) {
					font.dispose();
				}
			});
		}

		// Add the key listener.
		text.addListener(SWT.KeyUp, keyFilter);
		text.addListener(SWT.KeyDown, keyFilter);

		final TraversalFilterManager traversalFilterManager = new TraversalFilterManager();
		text.addFocusListener(traversalFilterManager);
		text.addDisposeListener(new DisposeListener() {
			@Override
			public void widgetDisposed(DisposeEvent e) {
				traversalFilterManager.dispose();
			}
		});

		// Add an internal modify listener.
		text.addModifyListener(updateSequenceListener);
	}

	/**
	 * Adds a property change listener to this key sequence widget. It will be
	 * notified when the key sequence changes.
	 *
	 * @param listener
	 *            The listener to be notified when changes occur; must not be
	 *            <code>null</code>.
	 */
	public final void addPropertyChangeListener(
			final IPropertyChangeListener listener) {
		if (listener == null) {
			return;
		}

		if (listeners == null) {
			listeners = new ArrayList<IPropertyChangeListener>(1);
		}

		listeners.add(listener);
	}

	/**
	 * Clears the text field and resets all the internal values.
	 */
	public void clear() {
		final JSSKeySequence oldKeySequence = keySequence;
		keySequence = JSSKeySequence.getInstance();
		text.setText(EMPTY_STRING);
		firePropertyChangeEvent(oldKeySequence);
	}

	/**
	 * Get the list of selected keystrokes in the text area
	 * 
	 * @param keyStrokes a list of all the keystrokes in the text area
	 * @return a not null set of the selected strokes
	 */
	private HashSet<JSSKeyStroke> getSelectKeystrokes(JSSKeyStroke[] keyStrokes){
		HashSet<JSSKeyStroke> result = new HashSet<JSSKeyStroke>();
		String selectedStrokes = text.getSelectionText().trim();
		int nSelectedStrokes = selectedStrokes.isEmpty() ? 0 : selectedStrokes.split(JSSKeySequence.KEY_STROKE_DELIMITER).length;
		if (nSelectedStrokes > 0){
			// Get the current selection.
			Point selection = text.getSelection();
			int start = selection.x;
			String strokesBefore = text.getText().substring(0, start).trim();
			int nStrokesBefore = strokesBefore.isEmpty() ? 0 : strokesBefore.split(JSSKeySequence.KEY_STROKE_DELIMITER).length;
			for(int i = 0; i < nSelectedStrokes; i++){
				if (i + nStrokesBefore < keyStrokes.length) {
					result.add(keyStrokes[i + nStrokesBefore]);
				}
			}
		}
		return result;
	}
	
	/**
	 * Removes the key strokes from the list corresponding the selection. If
	 * <code>allowIncomplete</code>, then invalid key sequences will be
	 * allowed (i.e., those with incomplete strokes in the non-terminal
	 * position). Otherwise, incomplete strokes will be removed. This modifies
	 * <code>keyStrokes</code> in place, and has no effect on the text widget
	 * this class wraps.
	 *
	 * @param keyStrokes
	 *            The list of key strokes from which the selection should be
	 *            removed; must not be <code>null</code>.
	 * @param allowIncomplete
	 *            Whether incomplete strokes should be allowed to exist in the
	 *            list after the deletion.
	 * @param deletedKeyStrokes
	 *            The list of keystrokes that were deleted by this operation.
	 *            Declared as final since it will hold a reference to the new
	 *            keyStroke array that has deleted the selected keystrokes.
	 * @return The index at which a subsequent insert should occur. This index
	 *         only has meaning to the <code>insertStrokeAt</code> method.
	 */
	private final int deleteSelection(final JSSKeyStroke[] keyStrokes, final boolean allowIncomplete, final JSSKeyStroke[][] deletedKeyStrokes) {
		// Get the current selection.
		Point selection = text.getSelection();
		int start = selection.x;
		int keyStrokesLength = keyStrokes.length;
		
		String selectedStrokes = text.getSelectionText().trim();
		int nSelectedStrokes = selectedStrokes.isEmpty() ? 0 : selectedStrokes.split(JSSKeySequence.KEY_STROKE_DELIMITER).length;
		if (nSelectedStrokes == 0){
				// return the current keystrokes, nothing has to be deleted
				deletedKeyStrokes[0] = keyStrokes;
				String strokesBefore = text.getText().substring(0, text.getCaretPosition());
				int nStrokesBefore = strokesBefore.split(JSSKeySequence.KEY_STROKE_DELIMITER).length;
				return nStrokesBefore;
		} else {
			String strokesBefore = text.getText().substring(0, start).trim();
			int nStrokesBefore = strokesBefore.isEmpty() ? 0 : strokesBefore.split(JSSKeySequence.KEY_STROKE_DELIMITER).length;
			int nStrokeAfter = keyStrokesLength - nStrokesBefore - nSelectedStrokes;
			int newLength = keyStrokesLength - nSelectedStrokes;
			
			deletedKeyStrokes[0] = new JSSKeyStroke[newLength];
			JSSKeyStroke keyStrokeResult[] = new JSSKeyStroke[newLength];	
			if (nStrokesBefore > 0) System.arraycopy(keyStrokes, 0, keyStrokeResult, 0, nStrokesBefore);
			if (nStrokeAfter > 0) System.arraycopy(keyStrokes, nStrokesBefore + nSelectedStrokes, keyStrokeResult, nStrokesBefore, nStrokeAfter);
			System.arraycopy(keyStrokeResult, 0, deletedKeyStrokes[0], 0, newLength);
			return nStrokesBefore;
		}
	}

	/**
	 * Fires a property change event to all of the listeners.
	 *
	 * @param oldKeySequence
	 *            The old key sequence; must not be <code>null</code>.
	 * @since 3.2
	 */
	protected final void firePropertyChangeEvent(
			final JSSKeySequence oldKeySequence) {
		if (listeners != null) {
			final Iterator<IPropertyChangeListener> listenerItr = listeners.iterator();
			final PropertyChangeEvent event = new PropertyChangeEvent(this, P_KEY_SEQUENCE, oldKeySequence, getKeySequence());
			while (listenerItr.hasNext()) {
				final IPropertyChangeListener listener = (IPropertyChangeListener) listenerItr.next();
				listener.propertyChange(event);
			}
		}
	}

	/**
	 * An accessor for the <code>KeySequence</code> that corresponds to the
	 * current state of the text field. This includes incomplete strokes.
	 *
	 * @return The key sequence representation; never <code>null</code>.
	 */
	public JSSKeySequence getKeySequence() {
		return keySequence;
	}

	/**
	 * An accessor for the underlying text widget's contents.
	 *
	 * @return The text contents of this entry; never <code>null</code>.
	 */
	private String getText() {
		return text.getText();
	}

	/**
	 * Tests whether the current text widget has some text selection.
	 *
	 * @return <code>true</code> if the number of selected characters it
	 *         greater than zero; <code>false</code> otherwise.
	 */
	private boolean hasSelection() {
		return (text.getSelectionCount() > 0);
	}

	/**
	 * Inserts the key stroke at the current insertion point. This does a
	 * regular delete and insert, as if the key had been pressed.
	 *
	 * @param stroke
	 *            The key stroke to insert; must not be <code>null</code>.
	 */
	public void insert(JSSKeyStroke stroke) {
		// Copy the key strokes in the current key sequence.
		final JSSKeySequence keySequence = getKeySequence();
		final JSSKeyStroke[] oldKeyStrokes = keySequence.getKeyStrokes();
		final JSSKeyStroke[] newKeyStrokes;
		if (!keySequence.isEmpty()) {
			final int newKeyStrokesLength = oldKeyStrokes.length - 1;
			newKeyStrokes = new JSSKeyStroke[newKeyStrokesLength];
			System.arraycopy(oldKeyStrokes, 0, newKeyStrokes, 0,
					newKeyStrokesLength);
		} else {
			newKeyStrokes = oldKeyStrokes;
		}

		JSSKeyStroke[][] deletedKeyStrokes = new JSSKeyStroke[1][];
		int index = deleteSelection(newKeyStrokes, true, deletedKeyStrokes);
		if (index == -1) {
			index = 0;
		}

		final JSSKeyStroke[] keyStrokes = insertStrokeAt(newKeyStrokes, stroke, index);
		keyFilter.clearInsertionIndex();
		setKeySequence(JSSKeySequence.getInstance(keyStrokes));
	}

	/**
	 * Inserts the stroke at the given index in the list of strokes. If the
	 * stroke currently at that index is incomplete, then it tries to merge the
	 * two strokes. If merging is a complete failure (unlikely), then it will
	 * simply overwrite the incomplete stroke. If the stroke at the index is
	 * complete, then it simply inserts the stroke independently.
	 *
	 * @param keyStrokes
	 *            The list of key strokes in which the key stroke should be
	 *            appended; must not be <code>null</code>.
	 * @param stroke
	 *            The stroke to insert; should not be <code>null</code>.
	 * @param index
	 *            The index at which to insert; must be a valid index into the
	 *            list of key strokes.
	 */
	private final JSSKeyStroke[] insertStrokeAt(final JSSKeyStroke[] keyStrokes, JSSKeyStroke stroke, int index) {
		final int keyStrokesLength = keyStrokes.length;
		final JSSKeyStroke currentStroke = (index >= keyStrokesLength) ? null : keyStrokes[index];
		if (currentStroke != null) {
			final int naturalKey = stroke.getNaturalKey();
			keyStrokes[index] = JSSKeyStroke.getInstance(naturalKey);
			return keyStrokes;
		}

		final JSSKeyStroke[] newKeyStrokes = new JSSKeyStroke[keyStrokesLength + 1];
		System.arraycopy(keyStrokes, 0, newKeyStrokes, 0, index);
		newKeyStrokes[index] = stroke;
		if (index < keyStrokesLength) {
			System.arraycopy(keyStrokes, index, newKeyStrokes, index + 1,
					keyStrokesLength-index);
		}
		return newKeyStrokes;
	}
	
	/**
	 * Append the passed keystroke into a specific position. If the position is not valid
	 * the stroke is appended to the end
	 * 
	 * @param keyStrokes the actual strokes
	 * @param stroke the stroke to append
	 * @param index the index where the stroke should be placed
	 * @return a not null list of the keystrokes with the new one appended
	 */
	private final JSSKeyStroke[] appenStrokeAt(final JSSKeyStroke[] keyStrokes, JSSKeyStroke stroke, int index) {
		final int keyStrokesLength = keyStrokes.length;		
		final JSSKeyStroke[] newKeyStrokes = new JSSKeyStroke[keyStrokesLength + 1];
		if (index >= 0 && index < keyStrokesLength){
			System.arraycopy(keyStrokes, 0, newKeyStrokes, 0, index);
			newKeyStrokes[index] = stroke;
			System.arraycopy(keyStrokes, index, newKeyStrokes, index + 1, keyStrokesLength-index);
		} else {
			System.arraycopy(keyStrokes, 0, newKeyStrokes, 0, index);
			newKeyStrokes[keyStrokesLength] = stroke;
		}	
		return newKeyStrokes;
	}
	
	/**
	 * Given an SWT accelerator value, provide the corresponding key stroke.
	 *
	 * @param accelerator
	 *            The accelerator to convert; should be a valid SWT accelerator
	 *            value.
	 * @return The equivalent key stroke; never <code>null</code>.
	 */
	private static final JSSKeyStroke convertAcceleratorToKeyStroke(int accelerator) {
		final int modifierKeys = accelerator & SWT.MODIFIER_MASK;
		return JSSKeyStroke.getInstance(modifierKeys);
	}

	/**
	 * Tests whether the cursor is in the last position. This means that the
	 * selection extends to the last position.
	 *
	 * @return <code>true</code> if the selection extends to the last
	 *         position; <code>false</code> otherwise.
	 */
	private boolean isCursorInLastPosition() {
		return (text.getSelection().y >= getText().length());
	}

	/**
	 * Removes the given listener from this key sequence widget.
	 *
	 * @param listener
	 *            The listener to be removed; must not be <code>null</code>.
	 * @since 3.2
	 */
	public final void removePropertyChangeListener(
			final IPropertyChangeListener listener) {
		if ((listener == null) || (listeners == null)) {
			return;
		}

		listeners.remove(listener);
	}

	/**
	 * <p>
	 * A mutator for the key sequence stored within this widget. The text and
	 * caret position are updated.
	 * </p>
	 * <p>
	 * All sequences are limited to maxStrokes number of strokes in length. If
	 * there are already that number of strokes, then it does not show
	 * incomplete strokes, and does not keep track of them.
	 * </p>
	 *
	 * @param newKeySequence
	 *            The new key sequence for this widget; may be <code>null</code>
	 *            if none.
	 */
	public void setKeySequence(JSSKeySequence newKeySequence) {
		final JSSKeySequence oldKeySequence = keySequence;

		if (newKeySequence == null) {
			text.setText(""); //$NON-NLS-1$
		} else {
			keySequence = newKeySequence;
		}

		// Trim any extra strokes.
		if (maxStrokes != INFINITE) {
			final JSSKeyStroke[] oldKeyStrokes = keySequence.getKeyStrokes();
			if (maxStrokes < oldKeyStrokes.length) {
				final JSSKeyStroke[] newKeyStrokes = new JSSKeyStroke[maxStrokes];
				System
						.arraycopy(oldKeyStrokes, 0, newKeyStrokes, 0,
								maxStrokes);
				keySequence = JSSKeySequence.getInstance(newKeyStrokes);
			}
		}

		// Check to see if the text has changed.
		String currentString = getText();
		String newString = keySequence.format();
		if (!currentString.equals(newString)) {
			// We need to update the text
			text.removeModifyListener(updateSequenceListener);
			text.setText(keySequence.format());
			text.addModifyListener(updateSequenceListener);
			text.setSelection(getText().length());
		}

		firePropertyChangeEvent(oldKeySequence);
	}

	/**
	 * Returns the maximum number of strokes that are permitted in this widget
	 * at one time.
	 *
	 * @return The maximum number of strokes; will be a positive integer or
	 *         <code>INFINITE</code>.
	 */
	public int getKeyStrokeLimit() {
		return maxStrokes;
	}

	/**
	 * A mutator for the maximum number of strokes that are permitted in this
	 * widget at one time.
	 *
	 * @param keyStrokeLimit
	 *            The maximum number of strokes; must be a positive integer or
	 *            <code>INFINITE</code>.
	 */
	public void setKeyStrokeLimit(int keyStrokeLimit) {
		if (keyStrokeLimit > 0 || keyStrokeLimit == INFINITE) {
			this.maxStrokes = keyStrokeLimit;
		} else {
			throw new IllegalArgumentException();
		}

		// Make sure we are obeying the new limit.
		setKeySequence(getKeySequence());
	}
}
