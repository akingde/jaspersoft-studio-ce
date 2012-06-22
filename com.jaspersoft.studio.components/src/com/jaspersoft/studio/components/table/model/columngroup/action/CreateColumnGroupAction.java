/*
 * JasperReports - Free Java Reporting Library. Copyright (C) 2001 - 2011 Jaspersoft Corporation. All rights reserved.
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
package com.jaspersoft.studio.components.table.model.columngroup.action;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

import com.jaspersoft.studio.components.table.messages.Messages;
import com.jaspersoft.studio.components.table.model.AMCollection;
import com.jaspersoft.studio.components.table.model.column.MColumn;
import com.jaspersoft.studio.components.table.model.column.command.MoveColumnCommand;
import com.jaspersoft.studio.components.table.model.columngroup.MColumnGroup;
import com.jaspersoft.studio.components.table.model.columngroup.command.CreateColumnGroupCommand;
import com.jaspersoft.studio.editor.outline.actions.ACreateAction;
import com.jaspersoft.studio.editor.palette.JDPaletteCreationFactory;
import com.jaspersoft.studio.model.ANode;

/*
 * The Class CreateGroupAction.
 */
public class CreateColumnGroupAction extends ACreateAction {

	/** The Constant ID. */
	public static final String ID = "create_table_column_group"; //$NON-NLS-1$

	/**
	 * Constructs a <code>CreateAction</code> using the specified part.
	 * 
	 * @param part
	 *            The part for this action
	 */
	public CreateColumnGroupAction(IWorkbenchPart part) {
		super(part);
		setCreationFactory(new JDPaletteCreationFactory(MColumnGroup.class));
	}

	/**
	 * Initializes this action's text and images.
	 */
	@Override
	protected void init() {
		super.init();
		setText(Messages.CreateColumnAction_create_column_group);
		setToolTipText(Messages.CreateColumnAction_create_column_group_tool_tip);
		setId(CreateColumnGroupAction.ID);
		ISharedImages sharedImages = PlatformUI.getWorkbench()
				.getSharedImages();
		setImageDescriptor(sharedImages
				.getImageDescriptor(ISharedImages.IMG_TOOL_NEW_WIZARD));
		setDisabledImageDescriptor(sharedImages
				.getImageDescriptor(ISharedImages.IMG_TOOL_NEW_WIZARD_DISABLED));
		setEnabled(false);
	}

	@Override
	public Command createCreateCommand(List<?> objects) {
		if (objects.isEmpty())
			return null;
		if (objects.size() == 1)
			return super.createCreateCommand(objects);
		List<MColumn> columns = new ArrayList<MColumn>();
		for (Object obj : objects) {
			if (!(obj instanceof EditPart))
				return super.createCreateCommand(objects);
			EditPart ep = (EditPart) obj;
			if (ep.getModel() instanceof MColumn)
				columns.add((MColumn) ep.getModel());
			else
				return super.createCreateCommand(objects);
		}
		CompoundCommand c = new CompoundCommand("New Column Group");

		MColumn fmc = columns.get(0);
		ANode mparent = fmc.getParent();
		MColumnGroup mcolgr = new MColumnGroup();
		int index = mparent.getChildren().indexOf(fmc);
		CreateColumnGroupCommand cmd = createGroup(index, mparent, mcolgr);
		mcolgr.setValue(cmd.createColumn(fmc.getJasperDesign(), fmc.getMTable()
				.getStandardTable()));
		c.add(createGroup(index, mparent, mcolgr));

		for (MColumn src : columns)
			c.add(new MoveColumnCommand(src, mcolgr));
		return c;
	}

	public CreateColumnGroupCommand createGroup(int index, ANode mparent,
			MColumnGroup mcolgr) {
		CreateColumnGroupCommand cmd = null;
		if (mparent instanceof MColumn)
			cmd = new CreateColumnGroupCommand((MColumn) mparent, mcolgr, index);
		if (mparent instanceof AMCollection)
			cmd = new CreateColumnGroupCommand((AMCollection) mparent, mcolgr,
					index);
		return cmd;
	}
}
