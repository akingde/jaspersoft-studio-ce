/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.preferences.bindings;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;

import org.eclipse.jface.bindings.TriggerSequence;
import org.eclipse.jface.bindings.keys.KeyStroke;
import org.eclipse.jface.bindings.keys.ParseException;
import org.eclipse.jface.util.Util;

/**
 * A JSSKeySequence is defined as a list of zero or more
 * JSSKeyStrokes. Each element of the sequence is a single key pressed.
 * The modifier count as independent keys
 * 
 * All JSSKeySequence objects have a formal string representation
 * available via the toString() method. There are a number of
 * methods to get instances of JSSKeySequence objects, including one
 * which can parse this formal string representation.

 * All JSSKeySequence objects, via the format()
 * method, provide a version of their formal string representation translated by
 * platform and locale, suitable for display to a user.

 * JSSKeySequence objects are immutable. Clients are not permitted
 * to extend this class.
 *
 * @author Orlandin Marco
 */
public class JSSKeySequence extends TriggerSequence implements Comparable<JSSKeySequence> {
	
	/**
	 * An empty key sequence instance for use by everyone.
	 */
	private final static JSSKeySequence EMPTY_KEY_SEQUENCE = new JSSKeySequence(new JSSKeyStroke[0]);

	/**
	 * The delimiter between multiple key strokes in a single key sequence --
	 * expressed in the formal key stroke grammar. This is not to be displayed
	 * to the user. It is only intended as an internal representation.
	 */
	public final static String KEY_STROKE_DELIMITER = "\u0020"; //$NON-NLS-1$

	/**
	 * The set of delimiters for <code>KeyStroke</code> objects allowed during
	 * parsing of the formal string representation.
	 */
	public final static String KEY_STROKE_DELIMITERS = KEY_STROKE_DELIMITER + "\b\r\u007F\u001B\f\n\0\t\u000B"; //$NON-NLS-1$
	
	/**
	 * Hashmap of strokes inside the sequence, keep for fast checs
	 */
	private HashSet<JSSKeyStroke> strokes;

	/**
	 * Gets an instance of <code>JSSKeySequence</code>.
	 *
	 * @return a key sequence. This key sequence will have no key strokes.
	 *         Guaranteed not to be <code>null</code>.
	 */
	public static final JSSKeySequence getInstance() {
		return EMPTY_KEY_SEQUENCE;
	}


	/**
	 * Creates an instance of <code>JSSKeySequence</code> given a key sequence
	 * and a key stroke.
	 *
	 * @param keySequence
	 *            a key sequence. Must not be <code>null</code>.
	 * @param keyStroke
	 *            a key stroke. Must not be <code>null</code>.
	 * @return a key sequence that is equal to the given key sequence with the
	 *         given key stroke appended to the end. Guaranteed not to be
	 *         <code>null</code>.
	 */
	public static final JSSKeySequence getInstance(final JSSKeySequence keySequence, final JSSKeyStroke keyStroke) {
		if (keySequence == null || keyStroke == null) {
			throw new NullPointerException();
		}

		final JSSKeyStroke[] oldKeyStrokes = keySequence.getKeyStrokes();
		final int oldKeyStrokeLength = oldKeyStrokes.length;
		final JSSKeyStroke[] newKeyStrokes = new JSSKeyStroke[oldKeyStrokeLength + 1];
		System.arraycopy(oldKeyStrokes, 0, newKeyStrokes, 0, oldKeyStrokeLength);
		newKeyStrokes[oldKeyStrokeLength] = keyStroke;
		return new JSSKeySequence(newKeyStrokes);
	}

	/**
	 * Creates an instance of <code>JSSKeySequence</code> given a single key
	 * stroke.
	 *
	 * @param keyStroke
	 *            a single key stroke. Must not be <code>null</code>.
	 * @return a key sequence. Guaranteed not to be <code>null</code>.
	 */
	public static final JSSKeySequence getInstance(final JSSKeyStroke keyStroke) {
		return new JSSKeySequence(new JSSKeyStroke[] { keyStroke });
	}

	/**
	 * Creates an instance of <code>JSSKeySequence</code> given an array of key
	 * strokes.
	 *
	 * @param keyStrokes
	 *            the array of key strokes. This array may be empty, but it must
	 *            not be <code>null</code>. This array must not contain
	 *            <code>null</code> elements.
	 * @return a key sequence. Guaranteed not to be <code>null</code>.
	 */
	public static final JSSKeySequence getInstance(final JSSKeyStroke[] keyStrokes) {
		return new JSSKeySequence(keyStrokes);
	}

	/**
	 * Creates an instance of <code>JSSKeySequence</code> given a list of key
	 * strokes.
	 *
	 * @param keyStrokes
	 *            the list of key strokes. This list may be empty, but it must
	 *            not be <code>null</code>. If this list is not empty, it
	 *            must only contain instances of <code>KeyStroke</code>.
	 * @return a key sequence. Guaranteed not to be <code>null</code>.
	 */
	public static final JSSKeySequence getInstance(final List<JSSKeyStroke> keyStrokes) {
		return new JSSKeySequence((JSSKeyStroke[]) keyStrokes.toArray(new JSSKeyStroke[keyStrokes.size()]));
	}

	/**
	 * Creates an instance of <code>JSSKeySequence</code> by parsing a given
	 * formal string representation.
	 *
	 * @param string
	 *            the formal string representation to parse.
	 * @return a key sequence. Guaranteed not to be <code>null</code>.
	 * @throws ParseException
	 *             if the given formal string representation could not be parsed
	 *             to a valid key sequence.
	 */
	public static final JSSKeySequence getInstance(final String string) throws ParseException {
		if (string == null) {
			throw new NullPointerException();
		}

		final List<JSSKeyStroke> keyStrokes = new ArrayList<JSSKeyStroke>();
		HashSet<JSSKeyStroke> addedStrokes = new HashSet<JSSKeyStroke>();
		final StringTokenizer stringTokenizer = new StringTokenizer(string, KEY_STROKE_DELIMITERS);

		try {
			while (stringTokenizer.hasMoreTokens()) {
				JSSKeyStroke decodedStroke = JSSKeyStroke.getInstance(stringTokenizer.nextToken());
				if (!addedStrokes.contains(decodedStroke)){
					addedStrokes.add(decodedStroke);
					if (decodedStroke.isModifier()){
						keyStrokes.add(0, decodedStroke);
					} else {
						keyStrokes.add(decodedStroke);
					}
				}
			}

			final JSSKeyStroke[] keyStrokeArray = (JSSKeyStroke[]) keyStrokes.toArray(new JSSKeyStroke[keyStrokes.size()]);
			return new JSSKeySequence(keyStrokeArray);
		} catch (final IllegalArgumentException e) {
			throw new ParseException("Could not construct key sequence with these key strokes: " + keyStrokes);//$NON-NLS-1$
		} catch (final NullPointerException e) {
			throw new ParseException("Could not construct key sequence with these key strokes: " + keyStrokes);//$NON-NLS-1$
		}
	}

	/**
	 * Constructs an instance of <code>JSSKeySequence</code> given a list of key
	 * strokes.
	 *
	 * @param keyStrokes
	 *            the list of key strokes. This list may be empty, but it must
	 *            not be <code>null</code>. If this list is not empty, it
	 *            must only contain instances of <code>KeyStroke</code>.
	 */
	protected JSSKeySequence(final JSSKeyStroke[] keyStrokes) {
		super(keyStrokes);
		strokes = new HashSet<JSSKeyStroke>();
		for(JSSKeyStroke stroke : keyStrokes){
			strokes.add(stroke);
		}
	}

	/**
	 * Returns the list of key strokes for this key sequence.
	 *
	 * @return the list of key strokes keys. This list may be empty, but is
	 *         guaranteed not to be <code>null</code>. If this list is not
	 *         empty, it is guaranteed to only contain instances of
	 *         <code>KeyStroke</code>.
	 */
	public JSSKeyStroke[] getKeyStrokes() {
		final int triggerLength = triggers.length;
		JSSKeyStroke[] keyStrokes = new JSSKeyStroke[triggerLength];
		System.arraycopy(triggers, 0, keyStrokes, 0, triggerLength);
		return keyStrokes;
	}
	
	/**
	 * Return the KeyStroke inside the sequence in a format compatible with the
	 * eclipse KeyStroke. This take all the modifier keys inside this sequence and apply
	 * them to any other key inside the sequence
	 * 
	 * @return a not null list of standard KeyStroke, reperesenting the current sequence into
	 * an eclipse format
	 */
	public KeyStroke[] getEclipseKeyStrokes(){
		JSSKeyStroke[] strokes = getKeyStrokes();
		int modifier = JSSKeyStroke.NO_KEY;
		List<JSSKeyStroke> notModifierKeys = new ArrayList<JSSKeyStroke>();
		for(JSSKeyStroke stroke : strokes){
			if (stroke.isModifier() && stroke.getNaturalKey() != JSSKeyStroke.NO_KEY){
				modifier = modifier | stroke.getNaturalKey();
			} else {
				notModifierKeys.add(stroke);
			}
		}
		List<KeyStroke> result = new ArrayList<KeyStroke>();
		for(JSSKeyStroke stroke : notModifierKeys){
			result.add(KeyStroke.getInstance(modifier, stroke.getNaturalKey()));
		}
		return result.toArray(new KeyStroke[result.size()]);
	}

	@Override
	public TriggerSequence[] getPrefixes() {
		final int numberOfPrefixes = triggers.length;
		final TriggerSequence[] prefixes = new TriggerSequence[numberOfPrefixes];
		prefixes[0] = JSSKeySequence.getInstance();
		for (int i = 0; i < numberOfPrefixes - 1; i++) {
			final JSSKeyStroke[] prefixKeyStrokes = new JSSKeyStroke[i + 1];
			System.arraycopy(triggers, 0, prefixKeyStrokes, 0, i + 1);
			prefixes[i + 1] = JSSKeySequence.getInstance(prefixKeyStrokes);
		}

		return prefixes;
	}

	/**
	 * Returns the formal string representation for this key sequence.
	 *
	 * @return The formal string representation for this key sequence.
	 *         Guaranteed not to be <code>null</code>.
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return format();
	}
	
	/**
	 * Formats this key sequence into the current default look.
	 *
	 * @return A string representation for this key sequence using the default
	 *         look; never <code>null</code>.
	 */
	@Override
	public String format() {
		StringBuffer stringBuffer = new StringBuffer();
		final JSSKeyStroke[] keyStrokes = getKeyStrokes();
		final int keyStrokesLength = keyStrokes.length;
		for (int i = 0; i < keyStrokesLength; i++) {
			stringBuffer.append(keyStrokes[i].format());

			if (i + 1 < keyStrokesLength) {
				stringBuffer.append(JSSKeySequence.KEY_STROKE_DELIMITER);
			}
		}

		return stringBuffer.toString();
	}
	
	/**
	 * Check if the current Sequence contains the passed sequence. A sequence is contained
	 * into another when all its keys are part also of the other (even if the order is different)
	 * 
	 * @param sequence the sequence to check if it is contained into the current one
	 * @return true if the passed sequence is contained in the current one, false otherwise
	 */
	public boolean contains(JSSKeySequence sequence){
		if (triggers.length > 0 && triggers.length >= sequence.triggers.length){
			for(JSSKeyStroke stroke : sequence.getKeyStrokes()){
				if (!strokes.contains(stroke)){
					return false;
				}
			}
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public int compareTo(JSSKeySequence o) {
		return Util.compare(triggers, o.triggers);
	}
	
	/**
	 * Return the number of KeyStroke in this sequence
	 * 
	 * @return a number >= 0 representing the number of elements in the 
	 * sequence
	 */
	public int getSize(){
		if (triggers != null){
			return triggers.length;
		}
		return 0;
	}
	
	/**
	 * Since the superclass has a final standard equal this is a second
	 * equal method that compare two sequences.
	 * 
	 * @param secondSequence the sequence to compare to this one
	 * @return true if the sequences have the same strokes, even if in different 
	 * orders, false otherwise
	 */
	public boolean isEqual(JSSKeySequence secondSequence){
		if (secondSequence == null) return false;
		if (strokes.size() != secondSequence.strokes.size()) return false;
		for (JSSKeyStroke stroke : strokes){
			if(!secondSequence.strokes.contains(stroke)){
				return false;
			}
		}
		return true;
	}
}
