/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property;

/**
 * Command used when resetting the value of an entry. This command first set 
 * the reset value also inside the editor, because many editors have by default
 * listeners of the focus lost and after the reset there is a focus lost before
 * to display the new value, so the editor has still the old value inside and it
 * could overwrite the default one
 * 
 * @author Orlandin Marco
 */
public class ResetEntryValueCommand extends ResetValueCommand {

	private JRPropertySheetEntry entry;
	
	public ResetEntryValueCommand(JRPropertySheetEntry entry){
		super();
		this.entry = entry;
	}
	
	
	@Override
	public void redo() {
		if (entry != null && entry.editor != null){
			if (target instanceof IJSSPropertySource){
				entry.editor.setValue(((IJSSPropertySource)target).getResetValue(propertyName));
			} else {
				entry.editor.setValue(null);
			}
		}
	
		super.redo();
	}
}
