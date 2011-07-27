/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer.
 * Copyright (C) 2005 - 2010 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of Jaspersoft Open Studio.
 *
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Jaspersoft Open Studio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with Jaspersoft Open Studio. If not, see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.rcp.intro.action;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.eclipse.ui.PartInitException;
import org.eclipse.ui.internal.browser.DefaultBrowserSupport;
import org.eclipse.ui.intro.IIntroSite;
import org.eclipse.ui.intro.config.IIntroAction;
import org.w3c.tools.codec.Base64Decoder;
import org.w3c.tools.codec.Base64FormatException;

import com.jaspersoft.studio.utils.UIUtils;

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
