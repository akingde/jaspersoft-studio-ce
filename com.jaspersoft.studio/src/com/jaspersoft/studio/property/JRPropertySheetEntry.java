/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EventObject;
import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.commands.CommandStackListener;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.commands.ForwardUndoCompoundCommand;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySheetEntry;
import org.eclipse.ui.views.properties.IPropertySource;

import com.jaspersoft.studio.JSSCompoundCommand;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.property.descriptor.checkbox.CheckBoxLabelProvider;

/*
 * /* The Class JRPropertySheetEntry.
 */
public class JRPropertySheetEntry extends CustomPropertySheetEntry {

	/** The listener. */
	private PropertyChangeListener listener;

	/** The command stack listener. */
	private CommandStackListener commandStackListener;

	/** The model. */
	private ANode model;

	/** The stack. */
	private CommandStack stack;
	
	private Object editValue;
	
	boolean isRefresh = false;

	/**
	 * Instantiates a new jR property sheet entry.
	 * 
	 * @param stack
	 *          the stack
	 * @param model
	 *          the model
	 */
	public JRPropertySheetEntry(CommandStack stack, ANode model) {
		this(stack, model, true);
	}

	/**
	 * Instantiates a new jR property sheet entry.
	 * 
	 * @param stack
	 *          the stack
	 * @param model
	 *          the model
	 * @param addListener
	 *          flag to add or not the listener on the command stack to refresh all the entries
	 */
	public JRPropertySheetEntry(CommandStack stack, ANode model, boolean addListener) {
		super();
		setCommandStack(stack, addListener);
		setModel(model);
	}

	@Override
	public IPropertyDescriptor getDescriptor() {
		return super.getDescriptor();
	}

	/**
	 * Sets the model.
	 * 
	 * @param model
	 *          the new model
	 */
	public void setModel(ANode model) {
		if (listener != null && this.model != null)
			this.model.getPropertyChangeSupport().removePropertyChangeListener(listener);
		if (model != null) {
			if (listener == null) {
				listener = new PropertyChangeListener() {

					public void propertyChange(PropertyChangeEvent evt) {
						if (evt.getSource() instanceof IPropertySource)
							JRPropertySheetEntry.this.setValues(new Object[] { evt.getSource() });
					}
				};
			}
			model.getPropertyChangeSupport().addPropertyChangeListener(listener);
		}
		this.model = model;
	}

	/**
	 * Gets the model.
	 * 
	 * @return the model
	 */
	public ANode getModel() {
		return model;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.PropertySheetEntry#setValues(java.lang.Object[])
	 */
	@Override
	public void setValues(Object[] objects) {
		if (objects.length > 0) { // TODO WORK WITH COLLECTION
			if (objects[0] instanceof EditPart) {
				if (((EditPart) objects[0]).getModel() instanceof ANode)
					setModel((ANode) ((EditPart) objects[0]).getModel());
			}
		} else
			setModel(null);

		if (objects.length == 0) {
			editValue = null;
		} else {
			// set the first value object as the entry's value
			Object newValue = objects[0];

			// see if we should convert the value to an editable value
			IPropertySource source = getPropertySource(newValue);
			if (source != null) {
				newValue = source.getEditableValue();
			}
			editValue = newValue;
		}
		super.setValues(objects);
	}

	/**
	 * The child dosen't have the listener on the stack because the listener refresh always from root to all the children,
	 * so one listener on the root is the only one needed
	 */
	protected JRPropertySheetEntry createChildEntry() {
		return new JRPropertySheetEntry(stack, model, false);
	}

	/**
	 * Sets the command stack.
	 * 
	 * @param stack
	 *          the new commands stack
	 * @param addListener
	 *          flag to add or not the listener on the command stack to refresh all the entries
	 */
	void setCommandStack(CommandStack stack, boolean addListener) {
		this.stack = stack;
		if (addListener) {
			// First remove any previous listener
			if (commandStackListener != null) {
				stack.removeCommandStackListener(commandStackListener);
				commandStackListener = null;
			}
			// Then create and add the new one
			commandStackListener = new CommandStackListener() {
				public void commandStackChanged(EventObject e) {
					refreshFromRoot();
				}
			};
			stack.addCommandStackListener(commandStackListener);
		}
	}

	/**
	 * Gets the command stack.
	 * 
	 * @return the command stack
	 */
	public CommandStack getCommandStack() {
		// only the root has, and is listening too, the command stack
		if (getParent() != null)
			return ((JRPropertySheetEntry) getParent()).getCommandStack();
		return stack;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.PropertySheetEntry#dispose()
	 */
	@Override
	public void dispose() {
		if (stack != null)
			stack.removeCommandStackListener(commandStackListener);
		if (getModel() != null)
			getModel().getPropertyChangeSupport().removePropertyChangeListener(listener);
		super.dispose();
	}
	
	/**
	 * Create the reset value command for a the element and eventually for its children adding
	 * them to the {@link JSSCompoundCommand} container
	 * 
	 * @param cc a not null {@link JSSCompoundCommand}
	 * @return true if one of the command can change the value, false otherwise
	 */
	protected boolean resetPropertyValue(JSSCompoundCommand cc) {
		ResetValueCommand restoreCmd;
		boolean change = false;
		if (getParent() != null){
			// Use our parent's values to reset our values.
			Object[] objects = getParent().getValues();
			for (int i = 0; i < objects.length; i++) {
				IPropertySource source = getPropertySource(objects[i]);
				if (source.isPropertySet(getDescriptor().getId())) {
					// source.resetPropertyValue(getDescriptor()getId());
					restoreCmd = new ResetEntryValueCommand(this);
					restoreCmd.setTarget(source);
					restoreCmd.setPropertyId(getDescriptor().getId());
					cc.add(restoreCmd);
					cc.setReferenceNodeIfNull(source);
					change = true;
				} else if (source instanceof IJSSPropertySource){
					IJSSPropertySource sourceNode = (IJSSPropertySource)source;
					if (sourceNode.forcePropertyChildrenReset(getDescriptor().getId())){
						for(IPropertySheetEntry entry : getChildEntries()){
							if (entry instanceof JRPropertySheetEntry){
								JRPropertySheetEntry jrEntry = (JRPropertySheetEntry)entry;
								change = jrEntry.resetPropertyValue(cc) | change;
							}
						}
					}
				}
			}
		}
		return change;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.PropertySheetEntry#resetPropertyValue()
	 */
	public void resetPropertyValue() {
		Object[] objects = getParent().getValues();
		ANode node = null;
		if (objects != null){
			for (Object object : objects){
				if (object instanceof ANode){
					node = (ANode)object;
					break;
				}
			}
		}
		JSSCompoundCommand cc = new JSSCompoundCommand(node);
		boolean change = resetPropertyValue(cc);
		if (change) {
			getCommandStack().execute(cc);
			refreshFromRoot();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.views.properties.PropertySheetEntry#valueChanged(org.eclipse.ui.views.properties.PropertySheetEntry)
	 */
	protected void valueChanged(CustomPropertySheetEntry child) {
		valueChanged((JRPropertySheetEntry) child, new ForwardUndoCompoundCommand(), Arrays.asList(getValues()));
	}

	/**
	 * Value changed.
	 * 
	 * @param child
	 *          the child
	 * @param command
	 *          the command
	 * @param selections
	 *          the actually selected elements
	 */
	void valueChanged(JRPropertySheetEntry child, final CompoundCommand command, List<?> selections) {
		if (!isRefresh && child.getValues().length > 0) {
			isRefresh = true;
			// The value and the property is the same for all the selected elements, so i take it from the first one
			// propertysheet
			Object newval = child.getValues()[0];
			Object propid = child.getDescriptor().getId();
			List<Object> remainingSelection = new ArrayList<Object>(selections);
			for (Object obj : selections) {
				Object rawModel = null;
				if (obj instanceof EditPart)
					rawModel = ((EditPart) obj).getModel();
				else if (obj instanceof ANode)
					rawModel = obj;
				if (rawModel != null && rawModel instanceof APropertyNode) {
					APropertyNode aNode = (APropertyNode) rawModel;
					IPropertySource propertySource = getPropertySource(aNode);
					Object oldval = aNode.getPropertyValue(propid);
					if (newval instanceof Command) {
						command.add((Command) newval);
						continue;
					}
					if (!(oldval instanceof INode)) {
						if (oldval != null && newval != null && oldval.equals(newval))
							continue;
						if (oldval == null && newval == null)
							continue;
					}
					
					//Check if the node provide a SetValueCommand provide and use it in case, otherwise
					//create a standard SetValueCommand
					ISetValueCommandProvider setCmdProvider = (ISetValueCommandProvider)aNode.getAdapter(ISetValueCommandProvider.class);
					if (setCmdProvider != null){
						command.add(setCmdProvider.getSetValueCommand(propertySource, child.getDisplayName(), propid, newval));
					} else {				
						SetValueCommand setCommand = new SetValueCommand(child.getDisplayName());
						setCommand.setTarget(propertySource);
						setCommand.setPropertyId(propid);
						setCommand.setPropertyValue(newval);
						command.add(setCommand);
					}
					remainingSelection.remove(obj);
				}
			}

			// inform our parent
			if (getParent() != null) {
				((JRPropertySheetEntry) getParent()).valueChanged(this, command, remainingSelection);
				isRefresh = false;
			} else {
				// I am the root entry
				stack.execute(command);
				isRefresh = false;
			}
		}
	}

	@Override
	public String getValueAsString() {
		ILabelProvider provider = getDescriptor().getLabelProvider();
		if (provider instanceof CheckBoxLabelProvider) {
			return provider.getText(editValue);// $NON-NLS-1$
		}
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
	 * Method called before a value is set inside a cell editor. 
	 * It check if the cell editor support the refresh basing its status
	 * on the model, and in case call the appropriate method
	 * 
	 * @param editor the cell editor where the value was set
	 */
	@Override
	protected void refreshCellEditor(CellEditor editor) {
		if (model != null && editor instanceof IRefreshableCellEditor){
			((IRefreshableCellEditor)editor).refresh(model);
		}
	}
}
