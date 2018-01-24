/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.preferences.bindings;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.WorkbenchException;
import org.eclipse.ui.XMLMemento;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.plugin.ExtensionManager;

import net.sf.jasperreports.eclipse.JasperReportsPlugin;
import net.sf.jasperreports.eclipse.util.FileUtils;

/**
 * Class that handle the storage of the bindings in the preferences and the recovery.
 * It can also check if the combination associated with a specific binding is actually pressed
 * 
 * @author Orlandin Marco
 */
public class BindingsPreferencePersistence {
	
	/**
	 * The name of the key binding element in the commands extension point.
	 */
	private static final String TAG_KEY_BINDING = "keyBinding"; //$NON-NLS-1$
	
	/**
	 * The name of the key binding root element in the xml stored in the preferences
	 */	
	private static final String TAG_BINDINGS_ROOT = "keyBindings"; //$NON-NLS-1$
	
	/**
	 * The name of the attribute storing the trigger sequence for a binding.
	 */
	private static final String ATT_KEY_SEQUENCE = "keySequence"; //$NON-NLS-1$
	
	/**
	 * The name of the attribute storing the id for a binding.
	 */
	private static final String BINDING_ID = "id"; //$NON-NLS-1$
	
	/**
	 * Hashmap used as cache of the defined bindings, the key is the id of a binding, the value
	 * is the sequence of key to press to have this binding active
	 */
	private static HashMap<String, JSSKeySequence> bindings = null;
	
	/**
	 * Write the passed binding to the preferences of the application
	 * 
	 * @param elements a not null list of bindings to save. The sequence saved for each
	 * binding is the one returned by the getTrigger() method
	 */
	public static void writeBindingsToPreferences(List<PreferenceBindingElement> elements){
		HashMap<String, JSSKeySequence> newBindings = new HashMap<String, JSSKeySequence>();
		XMLMemento xmlMemento = XMLMemento.createWriteRoot(TAG_BINDINGS_ROOT);
		for(PreferenceBindingElement model : elements){
			newBindings.put(model.getId(), model.getTrigger());
			writeBindingToPreferences(xmlMemento, model);
		}
		bindings = newBindings;
	
		// Write the XML block to the workbench preference store.
		IPreferenceStore preferenceStore = JaspersoftStudioPlugin.getInstance().getPreferenceStore();
		Writer writer = new StringWriter();
		try {
			xmlMemento.save(writer);
			preferenceStore.setValue(TAG_BINDINGS_ROOT, writer.toString());
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			FileUtils.closeStream(writer);
		}
	}
	
	/**
	 * Writes the binding to the memento. This creates a new child element on
	 * the memento, and places the properties of the binding as its attributes.
	 *
	 * @param parent
	 *            The parent memento for the binding element; must not be
	 *            <code>null</code>.
	 * @param binding
	 *            The binding to write; must not be <code>null</code>.
	 */
	private static void writeBindingToPreferences(IMemento parent, PreferenceBindingElement binding) {
		final IMemento element = parent.createChild(TAG_KEY_BINDING);
		element.putString(BINDING_ID, binding.getId());
		element.putString(ATT_KEY_SEQUENCE, binding.getTrigger().toString());
	}
	
	/**
	 * Read the current list of bindings from the preference storage
	 * 
	 * @return a not null Hashmap used as cache of the defined bindings, the key is the id of a binding, the value
	 * is the sequence of key to press to have this binding active
	 */
	public static HashMap<String, JSSKeySequence> readBindingsFromPreferences(){
		HashMap<String, JSSKeySequence> result = new HashMap<String, JSSKeySequence>();
		IPreferenceStore preferenceStore = JaspersoftStudioPlugin.getInstance().getPreferenceStore();
		String preferenceString = preferenceStore.getString(TAG_BINDINGS_ROOT);
		IMemento preferenceMemento = null;
		if ((preferenceString != null) && (preferenceString.length() > 0)) {
			Reader reader = new StringReader(preferenceString);
			try {
				preferenceMemento = XMLMemento.createReadRoot(reader);
				IMemento[] preferenceMementos = preferenceMemento.getChildren(TAG_BINDINGS_ROOT);
				int preferenceMementoCount = preferenceMementos.length;
				for (int i = preferenceMementoCount - 1; i >= 0; i--) {
					try{
						final IMemento memento = preferenceMementos[i];
						// Read out the command id.
						String id = memento.getString(BINDING_ID);
						if (id != null){
							String keySequenceText = memento.getString(ATT_KEY_SEQUENCE);
							JSSKeySequence keySequence = null;
							if (keySequenceText != null) {
								keySequence = JSSKeySequence.getInstance(keySequenceText);
							} else {
								keySequence = JSSKeySequence.getInstance(new JSSKeyStroke[]{});
							}
							result.put(id, keySequence);
						}
					} catch(Exception ex){
						ex.printStackTrace();
						JaspersoftStudioPlugin.getInstance().logError(ex);
					}
				}
			} catch (final WorkbenchException ex) {
				ex.printStackTrace();
			} finally {
				FileUtils.closeStream(reader);
			}
		}
		return result;
	}
	
	/**
	 * Return the bindings stored in the preferences. It check first if they are available in cache. If not
	 * the are read from the preferences and then stored in the cache
	 * 
	 * @return a not null Hashmap used as cache of the defined bindings, the key is the id of a binding, the value
	 * is the sequence of key to press to have this binding active
	 */
	protected static HashMap<String, JSSKeySequence> getPreferenceBindings(){
		if (bindings == null){
			bindings = readBindingsFromPreferences();
		}
		return bindings;
	}
	
	/**
	 * Return the key sequence associated with a specific binding ID
	 * 
	 * @param actionID a not null action id
	 * @return the sequence of the associated with the action id, or null
	 * if the action doesn't match any binding
	 */
	public static JSSKeySequence getBinding(String actionID){
		JSSKeySequence binding = getPreferenceBindings().get(actionID);
		if (binding != null){
			return binding;
		}
		BindingElement element = ExtensionManager.getContributedBindings().get(actionID);
		return element != null ? element.getDefault() : null;
	}
	
	/**
	 * Check if the keys associated to a specific binding id are pressed or not. It will does
	 * a perfect match to be sure the key of the binding are all pressed
	 * 
	 * @param a not null binding id
	 * @return true if the keys associated with the binding id are pressed, false otherwise
	 */
	public static boolean isPressed(String bindingID){
		return isPressed(bindingID, true);
	}
	
	/**
	 * Check if the keys associated to a specific binding id are pressed or not
	 * 
	 * @param a not null binding id
	 * @return true if the keys associated with the binding id are pressed, false otherwise
	 */
	public static boolean isPressed(String bindingID, boolean perfectMatch){
		JSSKeySequence keySequence = getBinding(bindingID);
		if (keySequence == null) return false;
		//the sequence start as matched if there is at least an element.
		//so in the case the sequence is empty the cycle will and and false
		//will be returned
		boolean sequenceMatched = (keySequence.getSize() > 0);
		if (perfectMatch) {
			sequenceMatched = keySequence.getSize() == JasperReportsPlugin.getPressedKeysNumber();
		}
		if (sequenceMatched) {
			for(JSSKeyStroke keyStroke : keySequence.getKeyStrokes()){
				int key = Character.toLowerCase(keyStroke.getNaturalKey());
				if (key != JSSKeyStroke.NO_KEY && !JasperReportsPlugin.isPressed(key)){
					sequenceMatched = false;
					break;
				}
			}
		}
		return sequenceMatched;
	}
}
