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
package com.jaspersoft.studio.editor.outline.actions;

import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.requests.GroupRequest;
import org.eclipse.gef.ui.actions.DeleteAction;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

import com.jaspersoft.studio.editor.gef.parts.band.BandEditPart;
import com.jaspersoft.studio.editor.outline.ATreeEditPart;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.MReport;
import com.jaspersoft.studio.model.band.MBandGroupFooter;
import com.jaspersoft.studio.model.band.MBandGroupHeader;
import com.jaspersoft.studio.model.group.command.DeleteGroupCommand;

/**
 * The Class DeleteGroupReportAction.
 */
public class DeleteGroupReportAction extends DeleteAction {

	/** The Constant ID. */
	public static final String ID = "delete_group_report"; //$NON-NLS-1$

	/**
	 * Instantiates a new delete group report action.
	 * 
	 * @param editor
	 *          the editor
	 */
	public DeleteGroupReportAction(IEditorPart editor) {
		super(editor);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.ui.actions.DeleteAction#init()
	 */
	@Override
	protected void init() {
		super.init();
		setText(Messages.DeleteGroupReportAction_delete_group);
		setToolTipText(Messages.DeleteGroupReportAction_delete_group_tool_tip);
		setId(DeleteGroupReportAction.ID);
		ISharedImages sharedImages = PlatformUI.getWorkbench().getSharedImages();
		setImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_DELETE));
		setDisabledImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_DELETE_DISABLED));
		setEnabled(false);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.ui.actions.DeleteAction#createDeleteCommand(java.util.List)
	 */
	@Override
	public Command createDeleteCommand(List objects) {
		if (objects.isEmpty())
			return null;
		if (!(objects.get(0) instanceof EditPart))
			return null;

		GroupRequest deleteReq = new GroupRequest(RequestConstants.REQ_DELETE);
		deleteReq.setEditParts(objects);

		CompoundCommand compoundCmd = new CompoundCommand("Delete Report Group"); //$NON-NLS-1$
		for (int i = 0; i < objects.size(); i++) {
			EditPart part = (EditPart) objects.get(i);
			Command cmd = null;
			if (part instanceof ATreeEditPart || part instanceof BandEditPart) {
				ANode node = (ANode) part.getModel();
				if (node instanceof MBandGroupHeader) {
					cmd = new DeleteGroupCommand((MReport) node.getParent(), (MBandGroupHeader) node);
				}
				if (node instanceof MBandGroupFooter) {
					cmd = new DeleteGroupCommand((MReport) node.getParent(), (MBandGroupFooter) node);
				}
			}
			if (cmd != null)
				compoundCmd.add(cmd);
		}

		return compoundCmd;
	}
}
