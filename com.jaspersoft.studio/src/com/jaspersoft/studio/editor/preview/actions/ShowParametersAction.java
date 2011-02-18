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
package com.jaspersoft.studio.editor.preview.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.editor.preview.PreviewEditor;
import com.jaspersoft.studio.model.parameter.MParameter;

public class ShowParametersAction extends Action {
	public static final String ID = "SHOWPARAMETERSACTION"; //$NON-NLS-1$
	private PreviewEditor editor;

	public ShowParametersAction(PreviewEditor editor) {
		super();
		this.editor = editor;
		setId(ID);
		setText("Show Input Parameters");
		setDescription("Allways show input parameters dialog before running the report");
		setToolTipText("Allways show input parameters dialog before running the report");
		setImageDescriptor(JaspersoftStudioPlugin.getImageDescriptor(MParameter.getIconDescriptor().getIcon16Path())); //$NON-NLS-1$
		setDisabledImageDescriptor(JaspersoftStudioPlugin.getImageDescriptor("icons/resources/parameters-16d.png")); //$NON-NLS-1$
	}

	@Override
	public boolean isEnabled() {
		return super.isEnabled() && editor.isNotRunning();
	}

	@Override
	public int getStyle() {
		return SWT.PUSH;
	}

	@Override
	public void run() {
		editor.setShowParameters(!editor.isShowParameters());
		setChecked(editor.isShowParameters());
	}
}
