/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2010 Jaspersoft Corporation. All
 * rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of Jaspersoft Open Studio.
 * 
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify it under the terms of the GNU Affero
 * General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * Jaspersoft Open Studio is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with Jaspersoft Open Studio. If not,
 * see <http://www.gnu.org/licenses/>.
 */

package com.jaspersoft.studio.editor.preview.action;

import net.sf.jasperreports.engine.JRAbstractExporter;
import net.sf.jasperreports.engine.export.JRRtfExporter;

import org.eclipse.core.resources.IResource;

import com.jasperassistant.designer.viewer.IReportViewer;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.preferences.util.PropertiesHelper;

public class ExportAsRtfAction extends AbstractExportAction {

	public ExportAsRtfAction(IReportViewer viewer, IResource file) {
		super(viewer, file);

		setText(Messages.ExportAsRtfAction_title);
		setToolTipText(Messages.ExportAsRtfAction_tooltip);

		setFileExtensions(new String[] { "*.rtf" }); //$NON-NLS-1$
		setFilterNames(new String[] { Messages.ExportAsRtfAction_filtername });
		setDefaultFileExtension("rtf"); //$NON-NLS-1$
	}

	@Override
	protected JRAbstractExporter getExporter(PropertiesHelper ph) {
		JRRtfExporter exp = new JRRtfExporter();
		return exp;
	}
}
