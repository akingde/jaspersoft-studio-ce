/*******************************************************************************
 * Copyright (C) 2010 - 2012 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.property;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.EventObject;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.commands.CommandStackListener;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.commands.ForwardUndoCompoundCommand;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.PropertySheetEntry;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.property.descriptor.checkbox.CheckBoxLabelProvider;

/*
 * /* The Class JRPropertySheetEntry.
 */
public class JRPropertySheetEntry extends org.eclipse.ui.views.properties.PropertySheetEntry {

	/** The listener. */
	private PropertyChangeListener listener;

	/** The command stack listener. */
	private CommandStackListener commandStackListener;

	/** The model. */
	private ANode model;

	/** The stack. */
	private CommandStack stack;

	/**
	 * Instantiates a new jR property sheet entry.
	 * 
	 * @param stack
	 *          the stack
	 * @param model
	 *          the model
	 */
	public JRPropertySheetEntry(CommandStack stack, ANode model) {
		super();
		setCommandStack(stack);
		setModel(model);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.PropertySheetEntry#createChildEntry()
	 */
	protected JRPropertySheetEntry createChildEntry() {
		return new JRPropertySheetEntry(stack, model);
	}

	/**
	 * Sets the command stack.
	 * 
	 * @param stack
	 *          the new command stack
	 */
	void setCommandStack(CommandStack stack) {
		this.stack = stack;
		commandStackListener = new CommandStackListener() {
			public void commandStackChanged(EventObject e) {
				refreshFromRoot();
			}
		};
		stack.addCommandStackListener(commandStackListener);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.PropertySheetEntry#resetPropertyValue()
	 */
	public void resetPropertyValue() {
		CompoundCommand cc = new CompoundCommand();
		ResetValueCommand restoreCmd;

		if (getParent() == null)
			// root does not have a default value
			return;

		// Use our parent's values to reset our values.
		boolean change = false;
		Object[] objects = getParent().getValues();
		for (int i = 0; i < objects.length; i++) {
			IPropertySource source = getPropertySource(objects[i]);
			if (source.isPropertySet(getDescriptor().getId())) {
				// source.resetPropertyValue(getDescriptor()getId());
				restoreCmd = new ResetValueCommand();
				restoreCmd.setTarget(source);
				restoreCmd.setPropertyId(getDescriptor().getId());
				cc.add(restoreCmd);
				change = true;
			}
		}
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
	protected void valueChanged(PropertySheetEntry child) {
		valueChanged((JRPropertySheetEntry) child, new ForwardUndoCompoundCommand());
	}

	boolean isRefresh = false;

	/**
	 * Value changed.
	 * 
	 * @param child
	 *          the child
	 * @param command
	 *          the command
	 */
	void valueChanged(JRPropertySheetEntry child, final CompoundCommand command) {
		if (!isRefresh) {
			isRefresh = true;
			for (int i = 0; i < getValues().length; i++) {
				Object newval = child.getValues()[i];
				Object propid = child.getDescriptor().getId();
				IPropertySource propertySource = getPropertySource(getValues()[i]);
				Object oldval = propertySource.getPropertyValue(propid);
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

				SetValueCommand setCommand = new SetValueCommand(child.getDisplayName());
				setCommand.setTarget(propertySource);
				setCommand.setPropertyId(propid);
				setCommand.setPropertyValue(newval);
				command.add(setCommand);
			}

			// inform our parent
			if (getParent() != null) {
				((JRPropertySheetEntry) getParent()).valueChanged(this, command);
				isRefresh = false;
			} else {
				// I am the root entry
				Display.getCurrent().asyncExec(new Runnable() {

					public void run() {
						stack.execute(command);
						isRefresh = false;
					}
				});
			}
		}
	}

	@Override
	public String getValueAsString() {
		ILabelProvider provider = getDescriptor().getLabelProvider();
		if (provider instanceof CheckBoxLabelProvider) {
			return provider.getText(editValue);//$NON-NLS-1$
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

	private Object editValue;

}
