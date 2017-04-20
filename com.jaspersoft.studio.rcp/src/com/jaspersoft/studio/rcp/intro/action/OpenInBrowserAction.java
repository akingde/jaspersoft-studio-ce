/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.rcp.intro.action;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.eclipse.ui.PartInitException;
import org.eclipse.ui.internal.browser.DefaultBrowserSupport;
import org.eclipse.ui.intro.IIntroSite;
import org.eclipse.ui.intro.config.IIntroAction;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.FileUtils;
import net.sf.jasperreports.eclipse.util.Misc;

public class OpenInBrowserAction implements IIntroAction {

	public void run(IIntroSite site, Properties params) {
		String csid = (String) params.get("csid");
		String enc = (String) params.get("enc");

		try {
			if (enc != null && enc.equals("base64"))
				csid = Misc.decodeBase64String(csid, FileUtils.LATIN1_ENCODING);
			new DefaultBrowserSupport().getExternalBrowser().openURL(
					new URL("http://" + csid));
		} catch (MalformedURLException e) {
			UIUtils.showError(e);
		} catch (PartInitException e) {
			UIUtils.showError(e);
		} catch (IOException e) {
			UIUtils.showError(e);
		}
	}

}
