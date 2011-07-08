/*
 * JasperReports - Free Java Reporting Library. Copyright (C) 2001 - 2009 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of JasperReports.
 * 
 * JasperReports is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser General
 * Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * JasperReports is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License along with JasperReports. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.editor.action.snap;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.SnapToGrid;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.ui.forms.FormDialog;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.jaspersoft.studio.messages.Messages;

public class SizeGridAction extends Action {
	private final class SizeDialog extends FormDialog {
		private int w;
		private int h;

		private SizeDialog(Shell shell, Dimension d) {
			super(shell);

			setText(Messages.SizeGridAction_grid_editor);
			w = d.width;
			h = d.height;
		}

		@Override
		protected void configureShell(Shell newShell) {
			super.configureShell(newShell);
			newShell.setText(Messages.SizeGridAction_grid_editor);
		}

		@Override
		protected void createFormContent(IManagedForm mform) {
			mform.getForm().setText(Messages.SizeGridAction_grid_size);

			FormToolkit toolkit = mform.getToolkit();

			mform.getForm().getBody().setLayout(new GridLayout(4, false));

			toolkit.createLabel(mform.getForm().getBody(), Messages.SizeGridAction_spacing_x + ":"); //$NON-NLS-1$
			final Spinner width = new Spinner(mform.getForm().getBody(), SWT.BORDER);
			width.setValues(w, 0, Integer.MAX_VALUE, 0, 1, 10);
			width.setToolTipText(Messages.SizeGridAction_grid_space_width_tool_tip);
			width.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					w = width.getSelection();
				}
			});

			toolkit.createLabel(mform.getForm().getBody(), Messages.SizeGridAction_spacing_y + ":"); //$NON-NLS-1$

			final Spinner height = new Spinner(mform.getForm().getBody(), SWT.BORDER);
			height.setValues(h, 0, Integer.MAX_VALUE, 0, 1, 10);
			height.setToolTipText(Messages.SizeGridAction_grid_space_height_tool_tip);
			height.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					h = height.getSelection();
				}
			});
		}

		public int getWidth() {
			return w;
		}

		public int getHeight() {
			return h;
		}
	}

	public static final String ID = "sizegridaction"; //$NON-NLS-1$
	private GraphicalViewer diagramViewer;

	/**
	 * Constructor
	 * 
	 * @param diagramViewer
	 *          the GraphicalViewer whose grid enablement and visibility properties are to be toggled
	 */
	public SizeGridAction(GraphicalViewer diagramViewer) {
		super(Messages.SizeGridAction_set_grid_size);
		this.diagramViewer = diagramViewer;
		setToolTipText(Messages.SizeGridAction_set_grid_size_tool_tip);
		setId(ID);
		setActionDefinitionId(ID);
	}

	/**
	 * @see org.eclipse.jface.action.IAction#run()
	 */
	@Override
	public void run() {
		Dimension dp = (Dimension) diagramViewer.getProperty(SnapToGrid.PROPERTY_GRID_SPACING);
		if (dp == null)
			dp = new Dimension(10, 10);
		final Dimension d = dp;

		SizeDialog dlg = new SizeDialog(Display.getDefault().getActiveShell(), d);
		if (dlg.open() == Window.OK) {
			diagramViewer.setProperty(SnapToGrid.PROPERTY_GRID_SPACING, new Dimension(dlg.getWidth(), dlg.getHeight()));
		}
	}
}
