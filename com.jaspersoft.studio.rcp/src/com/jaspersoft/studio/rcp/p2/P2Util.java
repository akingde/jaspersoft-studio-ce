/*******************************************************************************
 * Copyright (C) 2010 - 2012 Jaspersoft Corporation. All rights reserved.
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
package com.jaspersoft.studio.rcp.p2;

import java.net.URI;
import java.net.URISyntaxException;

import org.eclipse.equinox.internal.p2.ui.model.ElementUtils;
import org.eclipse.equinox.internal.p2.ui.model.MetadataRepositoryElement;
import org.eclipse.equinox.p2.ui.ProvisioningUI;

import com.jaspersoft.studio.rcp.Activator;
import com.jaspersoft.studio.rcp.messages.Messages;

/**
 * Utility class for P2 related tasks.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public class P2Util {

	// Main update site for JSS product 
	private static String UPDATE_SITE = "http://jasperstudio.sf.net/jssproductrepo/"; //$NON-NLS-1$
	  
	/**
	 * Sets the default repositories to look into.
	 * <p>
	 * 
	 * We programmatically set the repository as alternative to the p2.inf method.
	 * In fact, if the RCP application is installed into a user-write protected directory, 
	 * p2.inf will fail to be able to add the repositories (as it tries to modify the 
	 * configuration on the first RCP run).
	 * 
	 */
	public static void setRepositories() {
	    try {
	      final MetadataRepositoryElement element = new MetadataRepositoryElement(null, new URI(UPDATE_SITE), true);
	      ElementUtils.updateRepositoryUsingElements(ProvisioningUI.getDefaultUI(), new MetadataRepositoryElement[] {element}, null);
	    } catch (URISyntaxException e) {
	      Activator.getDefault().logError(Messages.P2Util_ErrorMessage, e);
	    }
	}
}
