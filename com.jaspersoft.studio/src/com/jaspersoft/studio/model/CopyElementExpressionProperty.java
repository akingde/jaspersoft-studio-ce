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
package com.jaspersoft.studio.model;

import java.util.Collection;

import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JRDesignExpression;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;

import com.jaspersoft.studio.property.descriptor.propexpr.PropertyExpressionsDTO;

/**
 * Class to make an expression property of an element copyable. It also provide the command 
 * to paste the expression property on one or more elements
 * 
 * @author Orlandin Marco
 *
 */
public class CopyElementExpressionProperty implements ICopyable {

	/**
	 * Copied property name, must be not null
	 */
	protected String propertyName;
	
	/**
	 * Copied property value
	 */
	protected String value;

	/**
	 * Command to paste the copied expression property on a single target. The command
	 * support the undo
	 * 
	 * @author Orlandin Marco
	 *
	 */
	private class PastePropertyExpressionCommand extends Command{
		
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
		public PastePropertyExpressionCommand(APropertyNode target) {
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
			PropertyExpressionsDTO elementProperties = (PropertyExpressionsDTO)target.getPropertyValue(JRDesignElement.PROPERTY_PROPERTY_EXPRESSIONS);
			//This property should return always an object, if this not happen probably the element doesen't support it, so skip the operations on it
			if (elementProperties != null){
				if (elementProperties.hasProperty(propertyName, isExpression())){
					alreadyPresent = true;
					oldValue = elementProperties.getProperty(propertyName, isExpression()).getValue();
				}
				elementProperties.setProperty(propertyName, value, isExpression());
				target.setPropertyValue(JRDesignElement.PROPERTY_PROPERTY_EXPRESSIONS, elementProperties);
			}
		}
		
		@Override
		public void undo() {
			PropertyExpressionsDTO elementProperties = (PropertyExpressionsDTO)target.getPropertyValue(JRDesignElement.PROPERTY_PROPERTY_EXPRESSIONS);
			//This property should return always an object, if this not happen probably the element doesen't support it, so skip the operations on it
			if (elementProperties != null){
				if (alreadyPresent){
					elementProperties.setProperty(propertyName, oldValue, isExpression());
				} else {
					elementProperties.removeProperty(propertyName, isExpression());
				}
			}
			target.setPropertyValue(JRDesignElement.PROPERTY_PROPERTY_EXPRESSIONS, elementProperties);
		}
	}
	
	/**
	 * Create the definition of a copied expression property. Please notice that a property it is
	 * different from an expression property since they uses two different storage location inside the element
	 * 
	 * @param propertyName the name of the copied property, must be not null
	 * @param value the value of the copied property
	 */
	public CopyElementExpressionProperty(String propertyName, Object value){
		this.propertyName = propertyName;
		if (value instanceof JRDesignExpression){
			this.value = ((JRDesignExpression)value).getText();
		} else {
			this.value = (String)value;
		}
	}
	
	@Override
	public boolean isCopyable2(Object parent) {
		return (parent instanceof APropertyNode);
	}

	/**
	 * Create the command to paste the property to a list of targets
	 * 
	 * @param targets a not null list of targets. This method handle 
	 * both APropertyNode and edit part
	 * 
	 * @return the command to paste the property on all the valid targets.
	 */
	public Command getPasteCommand(Collection<?> targets) {
		CompoundCommand cc = new CompoundCommand();
		for(Object target : targets){
			if (target instanceof EditPart){
				target = ((EditPart)target).getModel();
			}
			if (target instanceof APropertyNode){
				Command pasteCommand = getPasteCommand((APropertyNode)target);
				cc.add(pasteCommand);
			}
		}
		return cc;
	}
	
	/**
	 * Return the paste command for a single target
	 * 
	 * @param target target of the paste command, must be not null
	 * @return the command to paste the property on the target, not null
	 */
	protected Command getPasteCommand(APropertyNode target){
		return new PastePropertyExpressionCommand(target);
	}
	
	/**
	 * Return if the copied property is an expression property or not
	 * 
	 * @return true if the copied property is an expression property, false
	 * if it is a standard value property
	 */
	public boolean isExpression(){
		return true;
	}

	/**
	 * Return the name of the property
	 */
	public String getPropertyName() {
		return propertyName;
	}

	/**
	 * Return the value of the property as string
	 */
	public String getValue() {
		return value;
	}
}
