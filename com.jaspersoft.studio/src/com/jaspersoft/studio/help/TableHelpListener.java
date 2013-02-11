/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
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
package com.jaspersoft.studio.help;

import java.net.URL;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.PlatformUI;

/**
 * 
 * This class implements an help listener for a table element. The table it is supposed to be a on properties table and when 
 * the help key is pressed a contextual help is opened, showing informations about the selected properties on the table.
 *
 * @author Orlandin Marco
 *
 */
public class TableHelpListener implements Listener {

		/**
		 * Prefix of the help document
		 */
		private static final String PREFIX = "net.sf.jasperreports.doc/docs/config.reference.html?cp=0_2#";
		
		/**
		 * Table where the help listener is applied
		 */
		private Table table;
		
		protected TableHelpListener(Table table){
			this.table = table;
		}
		
		/**
		 * Check if a row of a table is selected, then take the text from the first column of the selected 
		 * row to get the name of the properties. The name is concatenated to the prefix to show the help 
		 * window
		 */
		@Override
		public void handleEvent(Event event) {
			int selectedIndex = table.getSelectionIndex();
			if (selectedIndex != -1){
				String propertyName = table.getItem(selectedIndex).getText(0);
				URL url = PlatformUI.getWorkbench().getHelpSystem().resolve(PREFIX.concat(propertyName), false);
				PlatformUI.getWorkbench().getHelpSystem().displayHelpResource(url.toExternalForm());
			}
		}
		
		/**
		 * Receive a properties table and set the contextual help on that table. The table must be not null.
		 */
		public static void setTableHelp(Table table){
			if (table != null)
				table.addListener(SWT.Help, new TableHelpListener(table));
		}
}
