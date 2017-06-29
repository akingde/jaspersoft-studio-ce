/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.rcp.intro.action;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;

import org.eclipse.ui.PartInitException;
import org.eclipse.ui.internal.browser.DefaultBrowserSupport;
import org.eclipse.ui.intro.IIntroSite;
import org.eclipse.ui.intro.config.IIntroAction;
import org.w3c.tools.codec.Base64Decoder;
import org.w3c.tools.codec.Base64FormatException;

public class OpenInBrowserAction implements IIntroAction {

	public void run(IIntroSite site, Properties params) {
		String file = (String) params.get("csid");
		String enc = (String) params.get("enc");

		try {
			if (enc != null && enc.equals("base64"))
				file = new Base64Decoder(file).processString();
			new DefaultBrowserSupport().getExternalBrowser().openURL(
					new URL("http://" + file));
		} catch (MalformedURLException e) {
			UIUtils.showError(e);
		} catch (PartInitException e) {
			UIUtils.showError(e);
		} catch (Base64FormatException e) {
			UIUtils.showError(e);
		}

	}

}
