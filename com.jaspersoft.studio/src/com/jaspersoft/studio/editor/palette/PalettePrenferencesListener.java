/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.palette;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteEntry;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;

import com.jaspersoft.studio.preferences.PalettePreferencePage;

/**
 * Listener used to refresh the visibility of the tool entry on the palette when the property to show or hide an element
 * is changed
 * 
 * @author Orlandin Marco
 *
 */
public class PalettePrenferencesListener implements IPropertyChangeListener {

	/**
	 * The list of drawer in which the entry are searched
	 */
	private List<PaletteDrawer> drawerToCheck;

	public PalettePrenferencesListener() {
		drawerToCheck = new ArrayList<PaletteDrawer>();
	}

	public void addDrawer(PaletteDrawer drawer) {
		if (!drawerToCheck.contains(drawer)) {
			drawerToCheck.add(drawer);
		}
	}

	/**
	 * When the property of the visibility of the palette changes a tool entry with the id of the changed is searched and
	 * its visiblity updated
	 */
	@Override
	public void propertyChange(PropertyChangeEvent event) {
		if (event.getProperty().startsWith("com.jaspersoft.studio.palette")) {
			for (PaletteDrawer drawer : drawerToCheck) {
				for (Object rawEntry : drawer.getChildren()) {
					PaletteEntry entry = (PaletteEntry) rawEntry;
					String id = PalettePreferencePage.getId(entry);
					if (event.getProperty().equals(id)) {
						if (event.getNewValue() instanceof Boolean)
							entry.setVisible(!((Boolean) event.getNewValue()));
						else if (event.getNewValue() instanceof String)
							entry.setVisible(!Boolean.getBoolean((String) event.getNewValue()));
						break;
					}
				}
			}
		}
	}

}
