/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.preferences.bindings;

import java.util.HashSet;
import java.util.Locale;

import org.eclipse.jface.bindings.Trigger;
import org.eclipse.jface.bindings.keys.IKeyLookup;
import org.eclipse.jface.bindings.keys.KeyLookupFactory;
import org.eclipse.jface.bindings.keys.ParseException;
import org.eclipse.jface.bindings.keys.SWTKeySupport;
import org.eclipse.jface.bindings.keys.formatting.IKeyFormatter;
import org.eclipse.jface.bindings.keys.formatting.KeyFormatterFactory;
import org.eclipse.jface.util.Util;
import org.eclipse.swt.SWT;

/**
 * A JSSKeyStroke represent the press of a single key of the keyboard
 * 
 *  @author Orlandin Marco
 */
public final class JSSKeyStroke extends Trigger {
	
	/**
	 * Static variable that contains the complete list of the modifier keys with their
	 * SWT code
	 */
	private static HashSet<Integer> modifierKeys = getModifierKeys();

	/**
	 * The representation for no key.
	 */
	public static final int NO_KEY = 0;

	/**
	 * Creates an instance of <code>JSSKeyStroke</code> given a natural key.
	 *
	 * @param naturalKey the pressed key id, as defined in the SWT toolkit
	 * @return a not null keystroke
	 */
	public static final JSSKeyStroke getInstance(final int naturalKey) {
		return new JSSKeyStroke(naturalKey);
	}

	/**
	 * Creates an instance of <code>KeyStroke</code> by parsing a given a formal
	 * string representation.
	 *
	 * @param string the formal string representation to parse.
	 * @return a key stroke. Guaranteed not to be <code>null</code>.
	 * @throws ParseException if the given formal string representation could not be parsed
	 * to a valid key stroke.
	 */
	public static final JSSKeyStroke getInstance(final String string) throws ParseException {
		if (string == null) {
			throw new NullPointerException("Cannot parse a null string"); //$NON-NLS-1$
		}

		final IKeyLookup lookup = KeyLookupFactory.getDefault();
		String token = string.toUpperCase(Locale.ENGLISH);
		int naturalKey = lookup.formalModifierLookup(token);
		if (naturalKey == NO_KEY){
			Integer simpleKey =  lookup.formalKeyLookupInteger(token);
			naturalKey = simpleKey != null ? simpleKey : NO_KEY;
		}
		if (naturalKey == NO_KEY) {
			throw new ParseException( "Cannot create key stroke with duplicate or non-existent modifier key: " + token);
		}
		return new JSSKeyStroke(naturalKey);
	}

	/**
	 * The natural key for this key stroke. This value is <code>NO_KEY</code>
	 * if the key stroke is incomplete (i.e., has no natural key).
	 */
	private final int naturalKey;

	/**
	 * Constructs an instance of <code>JSSKeyStroke</code>
	 *
	 * @param naturalKey
	 *            the natural key. The format of this integer is defined by
	 *            whichever widget toolkit you are using; <code>NO_KEY</code>
	 *            always means no natural key.
	 * @see SWTKeySupport
	 */
	private JSSKeyStroke(final int naturalKey) {
		this.naturalKey = naturalKey;
	}

	@Override
	public final int compareTo(final Object object) {
		if (!(object instanceof JSSKeyStroke)) {
			return -1;
		}
		final JSSKeyStroke keyStroke = (JSSKeyStroke) object;
		return  Util.compare(naturalKey, keyStroke.naturalKey);
	}

	@Override
	public final boolean equals(final Object object) {
		if (!(object instanceof JSSKeyStroke)) {
			return false;
		}

		final JSSKeyStroke keyStroke = (JSSKeyStroke) object;
		return (naturalKey == keyStroke.naturalKey);
	}

	/**
	 * Formats this key stroke into the current default look.
	 *
	 * @return A string representation for this key stroke using the default
	 *         look; never <code>null</code>.
	 */
	public final String format() {
		IKeyFormatter formatter = KeyFormatterFactory.getDefault();
		if (isModifier()){
			String formattedKey = formatter.format(org.eclipse.jface.bindings.keys.KeyStroke.getInstance(naturalKey, NO_KEY));
			if (formattedKey.endsWith("+")){
				formattedKey = formattedKey.substring(0, formattedKey.length() -1);
			}
			return formattedKey;
		} else return formatter.format(org.eclipse.jface.bindings.keys.KeyStroke.getInstance(NO_KEY, naturalKey));
	}
	
	/**
	 * Check if the current keystroke is a modifier. The complete list of the modifiers is 
	 * contained into the modiferKeys map
	 * 
	 * @return if the current keystroke is a modifier, false otherwise
	 */
	public boolean isModifier(){
		return modifierKeys.contains(naturalKey);
	}

	/**
	 * Returns the natural key for this key stroke.
	 *
	 * @return The natural key for this key stroke. 
	 */
	public final int getNaturalKey() {
		return naturalKey;
	}

	@Override
	public final int hashCode() {
		return naturalKey;
	}


	/**
	 * Returns the formal string representation for this key stroke.
	 *
	 * @return The formal string representation for this key stroke. Guaranteed
	 *         not to be <code>null</code>.
	 * @see java.lang.Object#toString()
	 */
	@Override
	public final String toString() {
		IKeyFormatter formatter = KeyFormatterFactory.getFormalKeyFormatter();
		if (isModifier()){
			String formattedKey = formatter.format(org.eclipse.jface.bindings.keys.KeyStroke.getInstance(naturalKey, NO_KEY));
			if (formattedKey.endsWith("+")){
				formattedKey = formattedKey.substring(0, formattedKey.length() -1);
			}
			return formattedKey;
		} else return formatter.format(org.eclipse.jface.bindings.keys.KeyStroke.getInstance(NO_KEY, naturalKey));
	}
	
	/**
	 * Return a set of all the modifier keys with their SWT code
	 */
	private static HashSet<Integer> getModifierKeys(){
		HashSet<Integer> modifierKeyTable = new HashSet<Integer>();
		final Integer alt = new Integer(SWT.ALT);
		final Integer command = new Integer(SWT.COMMAND);
		final Integer ctrl = new Integer(SWT.CTRL);
		final Integer shift = new Integer(SWT.SHIFT);
		modifierKeyTable.add(alt);
		modifierKeyTable.add(command);
		modifierKeyTable.add(ctrl);
		modifierKeyTable.add(shift);
		modifierKeyTable.add(Util.isMac() ? command : ctrl);
		modifierKeyTable.add(shift);
		modifierKeyTable.add(alt);
		modifierKeyTable.add(Util.isMac() ? ctrl : command);
		return modifierKeyTable;
	}
}
