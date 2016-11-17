/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.section.style.inerithance;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

import com.jaspersoft.studio.JSSCompoundCommand;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.APropertyNode;

/**
 * Open a contextual menu when a button is pressed. This menu offer 
 * the option to reset all the attribute of the actually selected
 * nodes to their default. This menu can be opened from the styles
 * list section
 * 
 * @author Orlandin Marco
 *
 */
public class ElementContextualMenu extends AbstractContextualMenu {

	public ElementContextualMenu(StylesListSection parentSection) {
		super(parentSection);
	}

	/**
	 * Return a single action to reset the properties of the selected elements
	 */
	@Override
	protected List<MenuItem> getItems(Menu parent) {
		List<MenuItem> items = new ArrayList<MenuItem>();
		MenuItem resetElementsItem = new MenuItem(parent, SWT.PUSH);
		// Add to the item the listener to reset the selection
		resetElementsItem.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				List<APropertyNode> selectedElements = parentSection.getElements();
				JSSCompoundCommand cc = new JSSCompoundCommand("Reset Elements", selectedElements.get(0)); //$NON-NLS-1$
				for (APropertyNode element : selectedElements) {
					createElementNullCommand(cc, element);
				}
				if (!cc.getCommands().isEmpty()) {
					parentSection.executeAndRefresh(cc);
				}
			}

		});
		items.add(resetElementsItem);
		return items;
	}
	
	/**
	 * Return different labels for the action if there are one or more element
	 * selected
	 */
	@Override
	protected String getItemText(MenuItem item) {
		if (parentSection.getElements().size()>1) {
			return Messages.ElementContextualMenu_contextualResetPlural;
		} else {
			return Messages.ElementContextualMenu_econtextualResetSingular;
		}
	}
}
