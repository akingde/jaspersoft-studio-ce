/*******************************************************************************
 * Copyright (C) 2005 - 2015 TIBCO Software Inc. All rights reserved.
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
package com.jaspersoft.studio.rcp.workspace;

import java.io.File;
import java.net.URL;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.osgi.service.datalocation.Location;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.program.Program;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

import com.jaspersoft.studio.rcp.messages.Messages;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;

/**
 * Utility methods related to JSS workspace selection and other actions.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public class WorkspaceUtils {
	
	public static String METADATA_PLUGINS_FOLDER = ".metadata/.plugins"; //$NON-NLS-1$
	public static String E4_WORKBENCH_PLUGIN_REF = "org.eclipse.e4.workbench"; //$NON-NLS-1$

	/**
	 * Checks if the Jaspersoft Studio workspace is a "legacy" one.<br/>
	 * This means it has not been created/updated with a version prior to JSS 6.2.0.<br/>
	 * Jaspersoft Studio starting from version 6.2.0 uses Eclipse 4.5.1 as platform.<br/>
	 * 
	 * @param location the workspace location
	 * @return <code>true</code> if the workspace is a legacy one, 
	 * 			<code>false</code> otherwise.
	 */
	public static boolean isLegacyWorkspace(Location location) {
		if(location.isSet()) {
			URL locationURL = location.getURL();
			if(new File(locationURL.getPath() + "/" + METADATA_PLUGINS_FOLDER).exists()){ //$NON-NLS-1$
				return !(new File(locationURL.getPath() + "/" + METADATA_PLUGINS_FOLDER + "/" + E4_WORKBENCH_PLUGIN_REF).exists()); //$NON-NLS-1$ //$NON-NLS-2$
			}
		}
		return false;
	}
	
	/**
	 * Checks if the workspace identified by the input location exists.
	 * 
	 * @param location the workspace location
	 * @return <code>true</code> if the workspace exists,
	 * 			<code>false</code> otherwise
	 */
	public static boolean checkWorkspaceExists(Location location) {
		if(location.isSet()) {
			URL locationURL = location.getURL();
			return new File(locationURL.getPath()).exists();
		}
		return false;
	}
	
	/**
	 * Creates the message dialog to be shown when an old version of the workspace is detected.
	 * 
	 * @param workspacePath the current workspace
	 * @return the create dialog
	 */
	public static MessageDialog getLegacyWorkspaceWarningDialog(String workspacePath) {
		MessageDialog warningDialog = new MessageDialog(
				UIUtils.getShell(), Messages.WorkspaceUtils_WarningDialogTitle, null, 
				NLS.bind(Messages.WorkspaceUtils_WarningDialogMsg1, workspacePath)
				+ Messages.WorkspaceUtils_WarningDialogMsg2
				+ Messages.WorkspaceUtils_WarningDialogMsg3
				+ Messages.WorkspaceUtils_WarningDialogMsg4, 
				MessageDialog.INFORMATION,
				new String[] {IDialogConstants.OK_LABEL, IDialogConstants.NO_LABEL}, 0){
			@Override
			protected Control createCustomArea(Composite parent) {
				final String articleLink = "http://community.jaspersoft.com/wiki/import-projects-and-settings-previous-version-jaspersoft-studio"; //$NON-NLS-1$
				final String articleText = Messages.WorkspaceUtils_LinkText;
				final StyledText link = new StyledText(parent, SWT.READ_ONLY);
				link.setText(articleText);
				link.setBackground(parent.getBackground());
				link.setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, true,
						false, 2, 1));

				StyleRange style = new StyleRange();
				style.underline = true;
				style.underlineStyle = SWT.UNDERLINE_LINK;
				int[] ranges = { 0, articleText.length() };
				StyleRange[] styles = { style };
				link.setStyleRanges(ranges, styles);

				link.addListener(SWT.MouseDown, new Listener() {
					@Override
					public void handleEvent(Event event) {
						try {
							int offset = link.getOffsetAtLocation(new Point(
									event.x, event.y));
							StyleRange style = link
									.getStyleRangeAtOffset(offset);
							if (style != null && style.underline
									&& style.underlineStyle == SWT.UNDERLINE_LINK) {
								Program.launch(articleLink);
							}
						} catch (IllegalArgumentException e) {
							// no character under event.x, event.y
						}
					}
				});

				return link;
			}
		};
		return warningDialog;
	}
	
}
