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
package com.jaspersoft.studio.property;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.EventObject;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.commands.CommandStackListener;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.commands.ForwardUndoCompoundCommand;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ICellEditorListener;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.PropertySheetEntry;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.property.descriptor.checkbox.CheckBoxLabelProvider;

// update properties when model fields are updated
/**
 * The Class JRPropertySheetEntry.
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

	/**
	 * Value changed.
	 * 
	 * @param child
	 *          the child
	 * @param command
	 *          the command
	 */
	void valueChanged(JRPropertySheetEntry child, CompoundCommand command) {
		CompoundCommand cc = new CompoundCommand();
		command.add(cc);

		SetValueCommand setCommand;
		for (int i = 0; i < getValues().length; i++) {
			setCommand = new SetValueCommand(child.getDisplayName());
			setCommand.setTarget(getPropertySource(getValues()[i]));
			setCommand.setPropertyId(child.getDescriptor().getId());
			setCommand.setPropertyValue(child.getValues()[i]);
			cc.add(setCommand);
		}

		// inform our parent
		if (getParent() != null)
			((JRPropertySheetEntry) getParent()).valueChanged(this, command);
		else {
			// I am the root entry
			stack.execute(command);
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
	private CellEditor editor;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySheetEntry#getEditor(org.eclipse.swt.widgets.Composite)
	 */
	public CellEditor getEditor(Composite parent) {
		editor = super.getEditor(parent);
		if (editor != null)
			editor.addListener(cellEditorListener);
		return editor;
	}

	/**
	 * Create the CellEditorListener for this entry. It listens for value changes in the CellEditor, and cancel and finish
	 * requests.
	 */
	private ICellEditorListener cellEditorListener = new ICellEditorListener() {
		public void editorValueChanged(boolean oldValidState, boolean newValidState) {

		}

		public void cancelEditor() {

		}

		public void applyEditorValue() {
			JRPropertySheetEntry.this.applyEditorValue();
		}
	};

}