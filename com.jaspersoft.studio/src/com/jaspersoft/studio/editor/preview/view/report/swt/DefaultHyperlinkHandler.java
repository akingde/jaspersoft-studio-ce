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
