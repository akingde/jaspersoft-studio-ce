/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.preferences.bindings;

/**
 * Extension of a binding element that consider also the key sequence defined in the 
 * preferences 
 * 
 * @author Orlandin Marco
 * 
 */
public class PreferenceBindingElement extends BindingElement{
	
	/**
	 * The real sequence to activate the action, considering the one in the preferences
	 */
	private JSSKeySequence currentSequence;
	
	/**
	 * Create the class
	 * 
	 * @param baseElement the base element read from the extension point, must be not null
	 * @param currentSequence the sequence to activate this action, should be the one read from the preferences or the 
	 * default one if the first is not available
	 */
	public PreferenceBindingElement(BindingElement baseElement, JSSKeySequence currentSequence) {
		super(baseElement.getId(), baseElement.getName(), baseElement.getDescription(), baseElement.getContext(), baseElement.getDefault());
		this.currentSequence = currentSequence;
	}
	
	/**
	 * Set the sequence to activate this binding
	 * 
	 * @param sequence the sequence to activate this action, should be the one read from the preferences or the 
	 * default one if the first is not available
	 */
	public void setTrigger(JSSKeySequence sequence){
		currentSequence = sequence;
	}
	
	/**
	 * Return the sequence to activate this binding
	 * 
	 * @return the sequence to activate this action, should be the one read from the preferences or the 
	 * default one if the first is not available
	 */
	public JSSKeySequence getTrigger(){
		return currentSequence;
	}
	
	/**
	 * Check if the current element contains the passed one.
	 * An element is contained into another when their context is the same
	 * and all the trigger KeyStrokes of the first are used also in the second, even
	 * if in different order
	 * 
	 * @param element the element to check
	 * @return true if the passed element is contained into the current one, false otherwise
	 */
	public boolean contains(PreferenceBindingElement element){
		if (getContext() != null && element.getContext() != null){
			if (getContext().trim().equalsIgnoreCase(element.getContext().trim())){
				if (currentSequence != null && element.getTrigger() != null){
					return currentSequence.contains(element.getTrigger());
				}
			}
		}
		return false;
	}
	
	@Override
	public String toString() {
		return getName() + "(" + getTrigger().format() + ")";
	}
}
