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
package com.jaspersoft.studio.editor.action.pdf;

import org.eclipse.gef.commands.Command;
import org.eclipse.ui.IWorkbenchPart;

import com.jaspersoft.studio.model.MGraphicElement;


public class PdfActionTableHeader extends PdfActionAbstact {

	/** The Constant ID. */
	public static final String ID_TableHeader_Full = "PdfAction_TableHeader_Full"; //$NON-NLS-1$
	public static final String ID_TableHeader_Start = "PdfAction_TableHeader_Start"; //$NON-NLS-1$
	public static final String ID_TableHeader_End = "PdfAction_TableHeader_End"; //$NON-NLS-1$
	public static final String ID_TableHeader_None = "PdfAction_TableHeader_None"; //$NON-NLS-1$
	
	public PdfActionTableHeader(IWorkbenchPart part,Position action_position) {
		super(part, action_position, ID_TableHeader_Full, ID_TableHeader_Start, ID_TableHeader_End, ID_TableHeader_None);
	}

	
	public Command createCommand(MGraphicElement model){
		return null;
	}

	@Override
	protected boolean calculateEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
}
