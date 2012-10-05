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
package com.jaspersoft.studio.editor.preview.view.report.swt;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRPrintHyperlink;
import net.sf.jasperreports.engine.type.HyperlinkTypeEnum;
import net.sf.jasperreports.view.JRHyperlinkListener;

import org.eclipse.swt.custom.BusyIndicator;

import com.jasperassistant.designer.viewer.util.BrowserUtils;

public class DefaultHyperlinkHandler implements JRHyperlinkListener {

	public void gotoHyperlink(final JRPrintHyperlink link) throws JRException {
		if (link != null) {
			BusyIndicator.showWhile(null, new Runnable() {
				public void run() {
					gotoHyperlinkBusy(link);
				}
			});
		}
	}

	private void gotoHyperlinkBusy(JRPrintHyperlink link) {
		if (link.getHyperlinkTypeValue().equals(HyperlinkTypeEnum.REFERENCE)) {
			openLink(link.getHyperlinkReference());
		} else if (link.getHyperlinkTypeValue().equals(HyperlinkTypeEnum.REMOTE_ANCHOR)) {
			if (link.getHyperlinkReference() != null) {
				String href = link.getHyperlinkReference();
				if (link.getHyperlinkAnchor() != null)
					href += "#" + link.getHyperlinkAnchor(); //$NON-NLS-1$
				openLink(href);
			}
		} else if (link.getHyperlinkTypeValue().equals(HyperlinkTypeEnum.REMOTE_PAGE)) {
			if (link.getHyperlinkReference() != null) {
				String href = link.getHyperlinkReference();
				if (link.getHyperlinkPage() != null)
					href += "#JR_PAGE_ANCHOR_0_" + link.getHyperlinkPage(); //$NON-NLS-1$
				openLink(href);
			}
		}
	}

	private void openLink(String href) {
		BrowserUtils.openLink(href);
	}
}
