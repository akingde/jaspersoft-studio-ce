/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.commands.common.EventManager;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ICellEditorListener;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.IFontProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySheetEntry;
import org.eclipse.ui.views.properties.IPropertySheetEntryListener;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.IPropertySource2;
import org.eclipse.ui.views.properties.IPropertySourceProvider;

import com.jaspersoft.studio.properties.view.IResettablePropertySheetEntry;

/**
 * This class is almost the same of the GEF class PropertySheetEntry, but
 * the original class has really a lot of constraint that doesn't allow to
 * customize it due to visibility problem. This one can be relaxed inside studio.
 * Also the generic type were specialized in this class.
 * 
 * 
 * <code>CustomPropertySheetEntry</code> is an implementation of
 * <code>IPropertySheetEntry</code> which uses <code>IPropertySource</code>
 * and <code>IPropertyDescriptor</code> to interact with domain model objects.
 * <p>
 * Every property sheet entry has a single descriptor (except the root entry
 * which has none). This descriptor determines what property of its objects it
 * will display/edit.
 * </p>
 * <p>
 * Entries do not listen for changes in their objects. Since there is no
 * restriction on properties being independent, a change in one property may
 * affect other properties. The value of a parent's property may also change. As
 * a result we are forced to refresh the entire entry tree when a property
 * changes value.
 * </p>
 */
public class CustomPropertySheetEntry extends EventManager implements IResettablePropertySheetEntry {
	/**
	 * The values we are displaying/editing. These objects repesent the value of
	 * one of the properties of the values of our parent entry. Except for the
	 * root entry where they represent the input (selected) objects.
	 */
	private Object[] values = new Object[0];

	/**
	 * The property sources for the values we are displaying/editing.
	 */
	private Map<Object, IPropertySource> sources = new HashMap<Object, IPropertySource>(0);

	/**
	 * The value of this entry is defined as the the first object in its value
	 * array or, if that object is an <code>IPropertySource</code>, the value
	 * it returns when sent <code>getEditableValue</code>
	 */
	private Object editValue;

	protected CustomPropertySheetEntry parent;

	private IPropertySourceProvider propertySourceProvider;

	private IPropertyDescriptor descriptor;

	protected CellEditor editor;

	private String errorText;

	private CustomPropertySheetEntry[] childEntries = null;

	/**
	 * Create the CellEditorListener for this entry. It listens for value
	 * changes in the CellEditor, and cancel and finish requests.
	 */
	private ICellEditorListener cellEditorListener = new ICellEditorListener() {
		@Override
		public void editorValueChanged(boolean oldValidState,
				boolean newValidState) {
			if (!newValidState) {
				// currently not valid so show an error message
				setErrorText(editor.getErrorMessage());
			} else {
				// currently valid
				setErrorText(null);
			}
		}

		@Override
		public void cancelEditor() {
			setErrorText(null);
		}

		@Override
		public void applyEditorValue() {
			CustomPropertySheetEntry.this.applyEditorValue();
		}
	};

	@Override
	public void addPropertySheetEntryListener(
			IPropertySheetEntryListener listener) {
		addListenerObject(listener);
	}

	@Override
	public void applyEditorValue() {
		if (editor == null) {
			return;
		}

		// Check if editor has a valid value
		if (!editor.isValueValid()) {
			setErrorText(editor.getErrorMessage());
			return;
		}

		setErrorText(null);

		// See if the value changed and if so update
		Object newValue = editor.getValue();
		boolean changed = false;
		if (values.length > 1) {
			changed = true;
		} else if (editValue == null) {
			if (newValue != null) {
				changed = true;
			}
		} else if (!editValue.equals(newValue)) {
			changed = true;
		}

		// Set the editor value
		if (changed) {
			setValue(newValue);
		}
	}

	/**
	 * Return the unsorted intersection of all the
	 * <code>IPropertyDescriptor</code>s for the objects.
	 *
	 * @return List
	 */
	private List<IPropertyDescriptor> computeMergedPropertyDescriptors() {
		if (values.length == 0) {
			return new ArrayList<IPropertyDescriptor>(0);
		}

		IPropertySource firstSource = getPropertySource(values[0]);
		if (firstSource == null) {
			return new ArrayList<IPropertyDescriptor>(0);
		}

		if (values.length == 1) {
			return Arrays.asList(firstSource.getPropertyDescriptors());
		}

		// get all descriptors from each object
		@SuppressWarnings("unchecked")
		Map<Object, IPropertyDescriptor>[] propertyDescriptorMaps = new Map[values.length];
		for (int i = 0; i < values.length; i++) {
			Object object = values[i];
			IPropertySource source = getPropertySource(object);
			if (source == null) {
				// if one of the selected items is not a property source
				// then we show no properties
				return new ArrayList<IPropertyDescriptor>(0);
			}
			// get the property descriptors keyed by id
			propertyDescriptorMaps[i] = computePropertyDescriptorsFor(source);
		}

		// intersect
		Map<Object, IPropertyDescriptor> intersection = propertyDescriptorMaps[0];
		for (int i = 1; i < propertyDescriptorMaps.length; i++) {
			// get the current ids
			Object[] ids = intersection.keySet().toArray();
			for (int j = 0; j < ids.length; j++) {
				Object object = propertyDescriptorMaps[i].get(ids[j]);
				if (object == null ||
				// see if the descriptors (which have the same id) are
						// compatible
						!((IPropertyDescriptor) intersection.get(ids[j]))
								.isCompatibleWith((IPropertyDescriptor) object)) {
					intersection.remove(ids[j]);
				}
			}
		}

		// sorting is handled in the PropertySheetViewer, return unsorted (in
		// the original order)
		ArrayList<IPropertyDescriptor> result = new ArrayList<IPropertyDescriptor>(intersection.size());
		IPropertyDescriptor[] firstDescs = firstSource.getPropertyDescriptors();
		for (int i = 0; i < firstDescs.length; i++) {
			IPropertyDescriptor desc = firstDescs[i];
			if (intersection.containsKey(desc.getId())) {
				result.add(desc);
			}
		}
		return result;
	}

	/**
	 * Returns an map of property descritptors (keyed on id) for the given
	 * property source.
	 *
	 * @param source
	 *            a property source for which to obtain descriptors
	 * @return a table of decriptors keyed on their id
	 */
	private Map<Object, IPropertyDescriptor> computePropertyDescriptorsFor(IPropertySource source) {
		IPropertyDescriptor[] descriptors = source.getPropertyDescriptors();
		Map<Object, IPropertyDescriptor> result = new HashMap<Object, IPropertyDescriptor>(descriptors.length * 2 + 1);
		for (int i = 0; i < descriptors.length; i++) {
			result.put(descriptors[i].getId(), descriptors[i]);
		}
		return result;
	}

	/**
	 * Create our child entries.
	 */
	private void createChildEntries() {
		// get the current descriptors
		List<IPropertyDescriptor> descriptors = computeMergedPropertyDescriptors();

		// rebuild child entries using old when possible
		CustomPropertySheetEntry[] newEntries = new CustomPropertySheetEntry[descriptors.size()];
		for (int i = 0; i < descriptors.size(); i++) {
			IPropertyDescriptor d = (IPropertyDescriptor) descriptors.get(i);
			// create new entry
			CustomPropertySheetEntry entry = createChildEntry();
			entry.setDescriptor(d);
			entry.setParent(this);
			entry.setPropertySourceProvider(propertySourceProvider);
			entry.refreshValues();
			newEntries[i] = entry;
		}
		// only assign if successful
		childEntries = newEntries;
	}

	/**
	 * Factory method to create a new child <code>PropertySheetEntry</code>
	 * instance.
	 * <p>
	 * Subclasses may overwrite to create new instances of their own class.
	 * </p>
	 *
	 * @return a new <code>PropertySheetEntry</code> instance for the
	 *         descriptor passed in
	 * @since 3.1
	 */
	protected CustomPropertySheetEntry createChildEntry() {
		return new CustomPropertySheetEntry();
	}

	@Override
	public void dispose() {
		if (editor != null) {
			editor.dispose();
			editor = null;
		}
		// recursive call to dispose children
		CustomPropertySheetEntry[] entriesToDispose = childEntries;
		childEntries = null;
		if (entriesToDispose != null) {
			for (int i = 0; i < entriesToDispose.length; i++) {
				// an error in a property source may cause refreshChildEntries
				// to fail. Since the Workbench handles such errors we
				// can be left in a state where a child entry is null.
				if (entriesToDispose[i] != null) {
					entriesToDispose[i].dispose();
				}
			}
		}
	}

	/**
	 * The child entries of this entry have changed (children added or removed).
	 * Notify all listeners of the change.
	 */
	private void fireChildEntriesChanged() {
		Object[] array = getListeners();
		for (int i = 0; i < array.length; i++) {
			IPropertySheetEntryListener listener = (IPropertySheetEntryListener) array[i];
			listener.childEntriesChanged(this);
		}
	}

	/**
	 * The error message of this entry has changed. Notify all listeners of the
	 * change.
	 */
	private void fireErrorMessageChanged() {
		Object[] array = getListeners();
		for (int i = 0; i < array.length; i++) {
			IPropertySheetEntryListener listener = (IPropertySheetEntryListener) array[i];
			listener.errorMessageChanged(this);
		}
	}

	/**
	 * The values of this entry have changed. Notify all listeners of the
	 * change.
	 */
	private void fireValueChanged() {
		Object[] array = getListeners();
		for (int i = 0; i < array.length; i++) {
			IPropertySheetEntryListener listener = (IPropertySheetEntryListener) array[i];
			listener.valueChanged(this);
		}
	}

	@Override
	public String getCategory() {
		return descriptor.getCategory();
	}

	@Override
	public IPropertySheetEntry[] getChildEntries() {
		if (childEntries == null) {
			createChildEntries();
		}
		return childEntries;
	}

	@Override
	public String getDescription() {
		return descriptor.getDescription();
	}

	/**
	 * Returns the descriptor for this entry.
	 *
	 * @return the descriptor for this entry
	 * @since 3.1 (was previously private)
	 */
	protected IPropertyDescriptor getDescriptor() {
		return descriptor;
	}

	@Override
	public String getDisplayName() {
		return descriptor.getDisplayName();
	}

	@Override
	public CellEditor getEditor(Composite parent) {

		if (editor == null) {
			editor = descriptor.createPropertyEditor(parent);
			if (editor != null) {
				editor.addListener(cellEditorListener);
			}
		}
		if (editor != null) {
			refreshCellEditor(editor);
			editor.setValue(editValue);
			setErrorText(editor.getErrorMessage());
		}
		return editor;
	}
	
	/**
	 * Method called before a value is set inside a cell editor. 
	 * can be overridden to provide a refresh of the cell editor.
	 * This is useful in case of dynamic cell editors that need to 
	 * update often their internal status. The default implementation
	 * does nothing
	 * 
	 * @param editor the cell editor where the value was set
	 */
	protected void refreshCellEditor(CellEditor editor){
		
	}

	/**
	 * Returns the edit value for the object at the given index.
	 *
	 * @param index
	 *            the value object index
	 * @return the edit value for the object at the given index
	 */
	protected Object getEditValue(int index) {
		Object value = values[index];
		IPropertySource source = getPropertySource(value);
		if (source != null) {
			value = source.getEditableValue();
		}
		return value;
	}

	@Override
	public String getErrorText() {
		return errorText;
	}

	@Override
	public String getFilters()[] {
		return descriptor.getFilterFlags();
	}

	@Override
	public Object getHelpContextIds() {
		return descriptor.getHelpContextIds();
	}

	@Override
	public Image getImage() {
		ILabelProvider provider = descriptor.getLabelProvider();
		if (provider == null) {
			return null;
		}
		return provider.getImage(editValue);
	}

	/**
	 * Returns the parent of this entry.
	 *
	 * @return the parent entry, or <code>null</code> if it has no parent
	 * @since 3.1
	 */
	protected CustomPropertySheetEntry getParent() {
		return parent;
	}

	/**
	 * Returns an property source for the given object.
	 *
	 * @param object
	 *            an object for which to obtain a property source or
	 *            <code>null</code> if a property source is not available
	 * @return an property source for the given object
	 * @since 3.1 (was previously private)
	 */
	protected IPropertySource getPropertySource(Object object) {
		if (sources.containsKey(object))
			return sources.get(object);

		IPropertySource result = null;
		IPropertySourceProvider provider = propertySourceProvider;

		if (provider == null && object != null) {
			provider = getProperySourceProviderFromElement(object);
    }

		if (provider != null) {
			result = provider.getPropertySource(object);
		} else {
			result = getPropertySourceFromElement(object);
    }

		sources.put(object, result);
		return result;
	}
	
	protected IPropertySourceProvider getProperySourceProviderFromElement(Object object){
		if (object instanceof IPropertySourceProvider){
			return (IPropertySourceProvider)object;
		}

    if (object instanceof IAdaptable) {
        IAdaptable adaptable = (IAdaptable) object;
        IPropertySourceProvider result = (IPropertySourceProvider)adaptable.getAdapter(IPropertySourceProvider.class);
        if (result != null) {
            return result;
        }
    }
    return null;
	}
	
	protected IPropertySource getPropertySourceFromElement(Object object){
		if (object instanceof IPropertySource){
			return (IPropertySource)object;
		}

    if (object instanceof IAdaptable) {
        IAdaptable adaptable = (IAdaptable) object;
        IPropertySource result = (IPropertySource)adaptable.getAdapter(IPropertySource.class);
        if (result != null) {
            return result;
        }
    }
    return null;
	}

	@Override
	public String getValueAsString() {
		if (editValue == null) {
			return "";//$NON-NLS-1$
		}
		ILabelProvider provider = descriptor.getLabelProvider();
		if (provider == null) {
			return editValue.toString();
		}
		String text = provider.getText(editValue);
		if (text == null) {
			return "";//$NON-NLS-1$
		}
		return text;
	}

	/**
	 * Returns the value objects of this entry.
	 *
	 * @return the value objects of this entry
	 * @since 3.1 (was previously private)
	 */
	public Object[] getValues() {
		return values;
	}

	@Override
	public boolean hasChildEntries() {
		if (childEntries != null && childEntries.length > 0) {
			return true;
		}
		// see if we could have entires if we were asked
		return computeMergedPropertyDescriptors().size() > 0;
	}

	/**
	 * Update our child entries. This implementation tries to reuse child
	 * entries if possible (if the id of the new descriptor matches the
	 * descriptor id of the old entry).
	 */
	private void refreshChildEntries() {
		if (childEntries == null) {
			// no children to refresh
			return;
		}

		// get the current descriptors
		List<IPropertyDescriptor> descriptors = computeMergedPropertyDescriptors();

		// cache old entries by their descriptor id
		Map<Object, CustomPropertySheetEntry> entryCache = new HashMap<Object, CustomPropertySheetEntry>(childEntries.length * 2 + 1);
		for (int i = 0; i < childEntries.length; i++) {
			CustomPropertySheetEntry childEntry = childEntries[i];
			if (childEntry != null) {
				entryCache.put(childEntry.getDescriptor().getId(), childEntry);
			}
		}

		// create a list of entries to dispose
		List<CustomPropertySheetEntry> entriesToDispose = new ArrayList<CustomPropertySheetEntry>(Arrays.asList(childEntries));

		// clear the old entries
		this.childEntries = null;

		// rebuild child entries using old when possible
		CustomPropertySheetEntry[] newEntries = new CustomPropertySheetEntry[descriptors.size()];
		boolean entriesChanged = descriptors.size() != entryCache.size();
		for (int i = 0; i < descriptors.size(); i++) {
			IPropertyDescriptor d = (IPropertyDescriptor) descriptors.get(i);
			// see if we have an entry matching this descriptor
			CustomPropertySheetEntry entry = (CustomPropertySheetEntry) entryCache.get(d.getId());
			if (entry != null) {
				// reuse old entry
				entry.setDescriptor(d);
				entriesToDispose.remove(entry);
			} else {
				// create new entry
				entry = createChildEntry();
				entry.setDescriptor(d);
				entry.setParent(this);
				entry.setPropertySourceProvider(propertySourceProvider);
				entriesChanged = true;
			}
			entry.refreshValues();
			newEntries[i] = entry;
		}

		// only assign if successful
		this.childEntries = newEntries;

		if (entriesChanged) {
			fireChildEntriesChanged();
		}

		// Dispose of entries which are no longer needed
		for (int i = 0; i < entriesToDispose.size(); i++) {
			((IPropertySheetEntry) entriesToDispose.get(i)).dispose();
		}
	}

	/**
	 * Refresh the entry tree from the root down.
	 *
	 * @since 3.1 (was previously private)
	 */
	protected void refreshFromRoot() {
		if (parent == null) {
			refreshChildEntries();
		} else {
			parent.refreshFromRoot();
		}
	}

	/**
	 * Update our value objects. We ask our parent for the property values based
	 * on our descriptor.
	 */
	private void refreshValues() {
		// get our parent's value objects
		Object[] currentSources = parent.getValues();

		// loop through the objects getting our property value from each
		Object[] newValues = new Object[currentSources.length];
		for (int i = 0; i < currentSources.length; i++) {
			IPropertySource source = parent
					.getPropertySource(currentSources[i]);
			newValues[i] = source.getPropertyValue(descriptor.getId());
		}

		// set our new values
		setValues(newValues);
	}

	@Override
	public void removePropertySheetEntryListener(
			IPropertySheetEntryListener listener) {
		removeListenerObject(listener);
	}

	@Override
	public void resetPropertyValue() {
		if (parent == null) {
			// root does not have a default value
			return;
		}

		// Use our parent's values to reset our values.
		boolean change = false;
		Object[] objects = parent.getValues();
		for (int i = 0; i < objects.length; i++) {
			IPropertySource source = getPropertySource(objects[i]);
			if (source.isPropertySet(descriptor.getId())) {
				// fix for https://bugs.eclipse.org/bugs/show_bug.cgi?id=21756
				if (source instanceof IPropertySource2) {
					IPropertySource2 extendedSource = (IPropertySource2) source;
					// continue with next if property is not resettable
					if (!extendedSource
							.isPropertyResettable(descriptor.getId())) {
						continue;
					}
				}
				source.resetPropertyValue(descriptor.getId());
				change = true;
			}
		}
		if (change) {
			refreshFromRoot();
		}
	}

	/**
	 * Set the descriptor.
	 *
	 * @param newDescriptor
	 */
	private void setDescriptor(IPropertyDescriptor newDescriptor) {
		// if our descriptor is changing, we have to get rid
		// of our current editor if there is one
		if (descriptor != newDescriptor && editor != null) {
			editor.dispose();
			editor = null;
		}
		descriptor = newDescriptor;
	}

	/**
	 * Set the error text. This should be set to null when the current value is
	 * valid, otherwise it should be set to a error string
	 */
	private void setErrorText(String newErrorText) {
		errorText = newErrorText;
		// inform listeners
		fireErrorMessageChanged();
	}

	/**
	 * Sets the parent of the entry to be propertySheetEntry.
	 *
	 * @param propertySheetEntry
	 */
	private void setParent(CustomPropertySheetEntry propertySheetEntry) {
		parent = propertySheetEntry;
	}

	/**
	 * Sets a property source provider for this entry. This provider is used to
	 * obtain an <code>IPropertySource</code> for each of this entries
	 * objects. If no provider is set then a default provider is used.
	 *
	 * @param provider
	 *            IPropertySourceProvider
	 */
	public void setPropertySourceProvider(IPropertySourceProvider provider) {
		propertySourceProvider = provider;
	}

	/**
	 * Set the value for this entry.
	 * <p>
	 * We set the given value as the value for all our value objects. We then
	 * call our parent to update the property we represent with the given value.
	 * We then trigger a model refresh.
	 * <p>
	 *
	 * @param newValue
	 *            the new value
	 */
	private void setValue(Object newValue) {
		// Set the value
		for (int i = 0; i < values.length; i++) {
			values[i] = newValue;
		}

		// Inform our parent
		parent.valueChanged(this);

		// Refresh the model
		refreshFromRoot();
	}

	/**
	 * The <code>PropertySheetEntry</code> implmentation of this method
	 * declared on<code>IPropertySheetEntry</code> will obtain an editable
	 * value for the given objects and update the child entries.
	 * <p>
	 * Updating the child entries will typically call this method on the child
	 * entries and thus the entire entry tree is updated
	 * </p>
	 *
	 * @param objects
	 *            the new values for this entry
	 */
	@Override
	public void setValues(Object[] objects) {
		values = objects;
		sources = new HashMap<Object, IPropertySource>(values.length * 2 + 1);

		if (values.length == 0) {
			editValue = null;
		} else {
			// set the first value object as the entry's value
			Object newValue = values[0];

			// see if we should convert the value to an editable value
			IPropertySource source = getPropertySource(newValue);
			if (source != null) {
				newValue = source.getEditableValue();
			}
			editValue = newValue;
		}

		// update our child entries
		refreshChildEntries();

		// inform listeners that our value changed
		fireValueChanged();
	}

	/**
	 * The value of the given child entry has changed. Therefore we must set
	 * this change into our value objects.
	 * <p>
	 * We must inform our parent so that it can update its value objects
	 * </p>
	 * <p>
	 * Subclasses may override to set the property value in some custom way.
	 * </p>
	 *
	 * @param child
	 *            the child entry that changed its value
	 */
	protected void valueChanged(CustomPropertySheetEntry child) {
		for (int i = 0; i < values.length; i++) {
			IPropertySource source = getPropertySource(values[i]);
			source.setPropertyValue(child.getDescriptor().getId(), child.getEditValue(i));
		}

		// inform our parent
		if (parent != null) {
			parent.valueChanged(this);
		}
	}

	/**
	 * Returns the foreground color for the entry.
	 *
	 * @return the foreground color for the entry, or <code>null</code> to use the default
	 *         foreground color
	 * @since 3.7
	 */
	protected Color getForeground() {
		ILabelProvider provider = descriptor.getLabelProvider();
		if (provider instanceof IColorProvider) {
			return ((IColorProvider) provider).getForeground(this);
		}
		return null;
	}

	/**
	 * Returns the background color for the entry.
	 *
	 * @return the background color for the entry, or <code>null</code> to use the default
	 *         background color
	 * @since 3.7
	 */
	protected Color getBackground() {
		ILabelProvider provider = descriptor.getLabelProvider();
		if (provider instanceof IColorProvider) {
			return ((IColorProvider) provider).getBackground(this);
		}
		return null;
	}

	/**
	 * Returns the font for the entry.
	 *
	 * @return the font for the entry, or <code>null</code> to use the default font
	 * @since 3.7
	 */
	protected Font getFont() {
		ILabelProvider provider = descriptor.getLabelProvider();
		if (provider instanceof IFontProvider) {
			return ((IFontProvider) provider).getFont(this);
		}
		return null;
	}
	
	/**
	 * Inspect the entry and its children if the entry is of type {@link IJSSPropertySource}
	 * to find if the reset is available only on this entry or also on its children
	 */
	public RESET_TYPE getAvailableReset(){
		RESET_TYPE result = RESET_TYPE.NO_RESET;
		if (getParent() != null){
			// Use our parent's values to reset our values.
			Object[] objects = getParent().getValues();
			for (int i = 0; i < objects.length; i++) {
				IPropertySource source = getPropertySource(objects[i]);
				if (source.isPropertySet(getDescriptor().getId())) {
					result = RESET_TYPE.RESET_ELEMENT;
					break;
				} else if (source instanceof IJSSPropertySource){
					IJSSPropertySource sourceNode = (IJSSPropertySource)source;
					if (sourceNode.forcePropertyChildrenReset(getDescriptor().getId())){
						for(IPropertySheetEntry entry : getChildEntries()){
							if (entry instanceof JRPropertySheetEntry){
								JRPropertySheetEntry jrEntry = (JRPropertySheetEntry)entry;
								RESET_TYPE partialResult =  jrEntry.getAvailableReset();
								if (partialResult != RESET_TYPE.NO_RESET){
									result = RESET_TYPE.RESET_CHILDREN;
								}
							}
						}
					}
				}
			}
		}
		return result;
	}
	
}
