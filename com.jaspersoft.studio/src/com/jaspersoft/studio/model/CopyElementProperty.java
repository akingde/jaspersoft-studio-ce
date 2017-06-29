/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.model;

import net.sf.jasperreports.engine.JRPropertiesMap;

import org.eclipse.gef.commands.Command;

/**
 * Class to make an property of an element copyable. It also provide the command 
 * to paste the property on one or more elements
 * 
 * @author Orlandin Marco
 *
 */
public class CopyElementProperty extends CopyElementExpressionProperty {

	/**
	 * Command to paste the copied property on a single target. The command
	 * support the undo
	 * 
	 * @author Orlandin Marco
	 *
	 */
	private class PastePropertyCommand extends Command {

		/**
		 * Flag used for the undo, true if the element has before
		 * the paste a property with the same name
		 */
		private boolean alreadyPresent = false;
		
		/**
		 * Used for the undo, the old value that has the property before the paste
		 */
		private String oldValue;
		
		/**
		 * Target node of the paste, not null
		 */
		private APropertyNode target;

		/**
		 * Create the command 
		 *  
		 * @param target Target node of the paste, must be not null
		 * to execute the command
		 */
		public PastePropertyCommand(APropertyNode target) {
			this.target = target;
		}

		/**
		 * The command can execute if there is a valid property name and a valid target
		 */
		@Override
		public boolean canExecute() {
			return propertyName != null && target != null;
		}

		@Override
		public void execute() {
			JRPropertiesMap elementProperties = (JRPropertiesMap) target.getPropertyValue(APropertyNode.PROPERTY_MAP);
			if (elementProperties == null) {
				elementProperties = new JRPropertiesMap();
			}
			if (elementProperties.containsProperty(propertyName)) {
				alreadyPresent = true;
				oldValue = elementProperties.getProperty(propertyName);
			}
			elementProperties.setProperty(propertyName, value);
			target.setPropertyValue(APropertyNode.PROPERTY_MAP, elementProperties);
		}

		@Override
		public void undo() {
			JRPropertiesMap elementProperties = (JRPropertiesMap) target.getPropertyValue(APropertyNode.PROPERTY_MAP);
			if (elementProperties == null) {
				elementProperties = new JRPropertiesMap();
			}
			if (alreadyPresent) {
				elementProperties.setProperty(propertyName, oldValue);
			} else {
				elementProperties.removeProperty(propertyName);
			}
			target.setPropertyValue(APropertyNode.PROPERTY_MAP, elementProperties);
		}
	}

	/**
	 * Create the definition of a copied property. Please notice that a property it is
	 * different from an expression property since they uses two different storage location inside the element
	 * 
	 * @param propertyName the name of the copied property, must be not null
	 * @param value the value of the copied property
	 */
	public CopyElementProperty(String propertyName, Object value) {
		super(propertyName, value);
	}

	/**
	 * Return the paste command for a single target
	 * 
	 * @param target target of the paste command, must be not null
	 * @return the command to paste the property on the target, not null
	 */
	@Override
	protected Command getPasteCommand(APropertyNode target){
		return new PastePropertyCommand(target);
	}
	
	/**
	 * Return if the copied property is an expression property or not
	 * 
	 * @return true if the copied property is an expression property, false
	 * if it is a standard value property
	 */
	@Override
	public boolean isExpression() {
		return false;
	}
}
