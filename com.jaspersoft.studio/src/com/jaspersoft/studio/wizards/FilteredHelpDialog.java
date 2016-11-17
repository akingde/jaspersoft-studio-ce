/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.wizards;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.FilteredResourcesSelectionDialog;

/**
 * Dialog used to show a filtered result of the workspace resources
 * and provide also a custom help binded with the provided id
 * 
 * @author Orlandin Marco
 *
 */
public class FilteredHelpDialog extends FilteredResourcesSelectionDialog{

		/**
		 * The ID of the contextual help
		 */
		private String contextHelpID = null;
	
		/**
		 * Creates a new instance of the class, 
		 * 
		 * @param shell
		 *            the parent shell
		 * @param multi
		 *            the multi selection flag
		 * @param container
		 *            the container to select resources from, e.g. the workspace root
		 * @param typesMask
		 *            a mask specifying which resource types should be shown in the dialog.
		 *            The mask should contain one or more of the resource type bit masks
		 *            defined in {@link IResource#getType()}
		 */
		public FilteredHelpDialog(Shell shell, boolean multi, IContainer container, int typesMask) {
			super(shell, multi, container, typesMask);
		}		
		
		/**
		 * Creates a new instance of the class, with the specified ID for the contextual help
		 * 
		 * @param shell
		 *            the parent shell
		 * @param multi
		 *            the multi selection flag
		 * @param container
		 *            the container to select resources from, e.g. the workspace root
		 * @param typesMask
		 *            a mask specifying which resource types should be shown in the dialog.
		 *            The mask should contain one or more of the resource type bit masks
		 *            defined in {@link IResource#getType()}
		 * @param contextHelpID the id of the contextual help, if null it acts as a standard FilteredResourcesSelectionDialog
		 */
		public FilteredHelpDialog(Shell shell, boolean multi, IContainer container, int typesMask, String contextHelpID) {
			this(shell, multi, container, typesMask);
			this.contextHelpID = contextHelpID;
		}

		/**
		 * Set the help data that should be seen in this step
		 */
		@Override
		protected void configureShell(Shell shell){
			super.configureShell(shell);
			if (contextHelpID != null){
				PlatformUI.getWorkbench().getHelpSystem().setHelp(shell, contextHelpID);
			}
		}
		
	}
