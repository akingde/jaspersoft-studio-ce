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
package com.jaspersoft.studio.editor.preview.view.report.swt.action;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.editor.preview.view.report.swt.IReportViewer;

public class LastPageAction extends AReportAction {

	public LastPageAction(IReportViewer rviewer) {
		super(rviewer);
		setText("Last Page"); //$NON-NLS-1$
		setToolTipText("Go to last page"); //$NON-NLS-1$
		setImageDescriptor(JaspersoftStudioPlugin.getImageDescriptor("icons/resources/nav/last.gif"));
		setDisabledImageDescriptor(JaspersoftStudioPlugin.getImageDescriptor("icons/resources/nav/lastd.gif"));
	}

	public boolean isActionEnabled() {
		return rviewer.canGotoLastPage();
	}

	@Override
	public void run() {
		rviewer.gotoLastPage();
	}

}
