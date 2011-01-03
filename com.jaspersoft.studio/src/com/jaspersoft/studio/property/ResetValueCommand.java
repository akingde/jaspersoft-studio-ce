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
package com.jaspersoft.studio.property;

import org.eclipse.gef.commands.Command;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.IPropertySource2;

import com.jaspersoft.studio.messages.Messages;

// TODO: Auto-generated Javadoc
/**
 * A Command used to restore the default value of a property.
 * 
 * @author Pratik Shah
 */

public class ResetValueCommand extends Command {

	/** the property that has to be reset. */
	protected Object propertyName;
	
	/** the current non-default value of the property. */
	protected Object undoValue;
	
	/** the property source whose property has to be reset. */
	protected IPropertySource target;

	/**
	 * Default Constructor: Sets the label for the Command.
	 * 
	 * @since 3.1
	 */
	public ResetValueCommand() {
		super(Messages.ResetValueCommand_restore_default_value);
	}

	/**
	 * Returns <code>true</code> IFF:<br>
	 * 1) the target and property have been specified<br>
	 * 2) the property has a default value<br>
	 * 3) the value set for that property is not the default.
	 * 
	 * @return true, if successful
	 * @see org.eclipse.gef.commands.Command#canExecute()
	 */
	public boolean canExecute() {
		boolean answer = false;
		if (target != null && propertyName != null) {
			answer = target.isPropertySet(propertyName);
			if (target instanceof IPropertySource2)
				answer = answer && (((IPropertySource2) target).isPropertyResettable(propertyName));
		}
		return answer;
	}

	/**
	 * Caches the undo value and invokes redo().
	 * 
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	public void execute() {
		undoValue = target.getPropertyValue(propertyName);
		if (undoValue instanceof IPropertySource)
			undoValue = ((IPropertySource) undoValue).getEditableValue();
		redo();
	}

	/**
	 * Sets the IPropertySource.
	 * 
	 * @param propSource
	 *          the IPropertySource whose property has to be reset
	 */
	public void setTarget(IPropertySource propSource) {
		target = propSource;
	}

	/**
	 * Resets the specified property on the specified IPropertySource.
	 * 
	 * @see org.eclipse.gef.commands.Command#redo()
	 */
	public void redo() {
		target.resetPropertyValue(propertyName);
	}

	/**
	 * Sets the property that is to be reset.
	 * 
	 * @param pName
	 *          the property to be reset
	 */
	public void setPropertyId(Object pName) {
		propertyName = pName;
	}

	/**
	 * Restores the non-default value that was reset.
	 * 
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	public void undo() {
		target.setPropertyValue(propertyName, undoValue);
	}

}