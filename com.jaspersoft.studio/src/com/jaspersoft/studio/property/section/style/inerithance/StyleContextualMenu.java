/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.section.style.inerithance;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.design.JRDesignStyle;

import org.eclipse.gef.commands.Command;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

import com.jaspersoft.studio.JSSCompoundCommand;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.style.MStyle;
import com.jaspersoft.studio.model.style.command.ResetConditionalStyleCommand;
import com.jaspersoft.studio.model.style.command.ResetStyleCommand;

/**
 * Open a contextual menu when a button is pressed. This menu offer 
 * the option to reset all the attribute of style. 
 * This menu can be opened from the styles list section
 * 
 * @author Orlandin Marco
 *
 */
public class StyleContextualMenu extends AbstractContextualMenu {
	
	/**
	 * The style to reset
	 */
	private MStyle styleToReset;
	
	/**
	 * Create the SelectionListener to open the menu
	 * 
	 * @param parentSection the parent section
	 * @param styleToReset the style to reset
	 */
	public StyleContextualMenu(StylesListSection parentSection, MStyle styleToReset) {
		super(parentSection);
		this.styleToReset = styleToReset;
	}

	@Override
	protected List<MenuItem> getItems(Menu parent) {
		List<MenuItem> items = new ArrayList<MenuItem>();
		MenuItem resetStyleItem = new MenuItem(parent, SWT.PUSH);
		resetStyleItem.setText(Messages.ResetStyleAction_actionTitle);
		// Add to the item the listener to reset the selection
		resetStyleItem.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				JSSCompoundCommand cc = new JSSCompoundCommand("Reset Style", styleToReset); //$NON-NLS-1$
				Command resetCommand = null;
				if (styleToReset.getValue() instanceof JRDesignStyle) resetCommand = new ResetStyleCommand(styleToReset);
				else resetCommand = new ResetConditionalStyleCommand(styleToReset.getJasperDesign(), styleToReset);
				cc.add(resetCommand);
				if (!cc.getCommands().isEmpty()) {
					parentSection.executeAndRefresh(cc);
				}
			}

		});
		items.add(resetStyleItem);
		return items;
	}
}
