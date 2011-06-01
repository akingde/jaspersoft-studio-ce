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
/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2010 Jaspersoft Corporation. All
 * rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of iReport.
 * 
 * iReport is free software: you can redistribute it and/or modify it under the terms of the GNU Affero General Public
 * License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * iReport is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with iReport. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.crosstab.model.rowgroup.command;

import java.util.List;

import net.sf.jasperreports.crosstabs.JRCrosstabColumnGroup;
import net.sf.jasperreports.crosstabs.design.JRDesignCellContents;
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstab;
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstabBucket;
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstabCell;
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstabRowGroup;
import net.sf.jasperreports.crosstabs.type.CrosstabTotalPositionEnum;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRStyle;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JRDesignStaticText;
import net.sf.jasperreports.engine.design.JRDesignTextField;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.Pair;

import org.eclipse.gef.commands.Command;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.swt.widgets.Display;

import com.jaspersoft.studio.crosstab.messages.Messages;
import com.jaspersoft.studio.crosstab.model.rowgroup.MRowGroup;
import com.jaspersoft.studio.crosstab.model.rowgroup.MRowGroups;
import com.jaspersoft.studio.model.text.MTextField;
import com.jaspersoft.studio.utils.ModelUtils;

/*
 * link nodes & together.
 * 
 * @author Chicu Veaceslav
 */
public class CreateRowGroupCommand extends Command {

	private JRDesignCrosstabRowGroup jrGroup;
	private JRDesignCrosstab jrCrosstab;

	private int index;
	private JasperDesign jasperDesign;

	/**
	 * Instantiates a new creates the parameter command.
	 * 
	 * @param destNode
	 *            the dest node
	 * @param srcNode
	 *            the src node
	 * @param position
	 *            the position
	 * @param index
	 *            the index
	 */
	public CreateRowGroupCommand(MRowGroups destNode, MRowGroup srcNode,
			int index) {
		super();
		this.jrCrosstab = (JRDesignCrosstab) destNode.getValue();
		this.index = index;
		if (srcNode != null && srcNode.getValue() != null)
			this.jrGroup = (JRDesignCrosstabRowGroup) srcNode.getValue();
		jasperDesign = destNode.getJasperDesign();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	@Override
	public void execute() {
		if (jrGroup == null) {
			jrGroup = createRowGroup(jasperDesign, jrCrosstab,
					Messages.CreateRowGroupCommand_row_group);
		}
		if (jrGroup != null) {
			try {
				addRowGroup(jrCrosstab, jrGroup, index);
			} catch (JRException e) {
				e.printStackTrace();
				if (e.getMessage().startsWith("Duplicate declaration")) { //$NON-NLS-1$
					String defaultName = ModelUtils.getDefaultName(
							jrCrosstab.getRowGroupIndicesMap(),
							"CopyOFRowGroup_"); //$NON-NLS-1$
					InputDialog dlg = new InputDialog(
							Display.getCurrent().getActiveShell(),
							Messages.CreateRowGroupCommand_row_group_name,
							Messages.CreateRowGroupCommand_row_group_dialog_text,
							defaultName, null);
					if (dlg.open() == InputDialog.OK) {
						jrGroup.setName(dlg.getValue());
						execute();
					}
				}
			}
		}
	}

	public static JRDesignCrosstabRowGroup createRowGroup(
			JasperDesign jasperDesign, JRDesignCrosstab jrCrosstab, String name) {
		JRDesignCrosstabRowGroup jrGroup = new JRDesignCrosstabRowGroup();
		jrGroup.setName(ModelUtils.getDefaultName(
				jrCrosstab.getRowGroupIndicesMap(), name));
		jrGroup.setWidth(60);

		JRDesignExpression exp = new JRDesignExpression();
		exp.setValueClassName("java.lang.String"); //$NON-NLS-1$
		exp.setText(""); //$NON-NLS-1$
		JRDesignCrosstabBucket bucket = new JRDesignCrosstabBucket();
		bucket.setExpression(exp);
		jrGroup.setBucket(bucket);

		JRDesignCellContents headerCell = new JRDesignCellContents();
		jrGroup.setHeader(headerCell);
		// the width is the with of the current base cell...
		// JRDesignCrosstabCell baseCell = (JRDesignCrosstabCell)
		// jrCrosstab.getCellsMap().get(new Pair(null, null));
		// int baseHeight = (baseCell != null && baseCell.getHeight() != null) ?
		// baseCell.getHeight()
		// : ((baseCell.getContents() != null) ?
		// baseCell.getContents().getHeight() : 30);

		exp = new JRDesignExpression();
		exp.setValueClassName("java.lang.String"); //$NON-NLS-1$
		exp.setText("$V{" + jrGroup.getName() + "}"); //$NON-NLS-1$ //$NON-NLS-2$

		JRDesignTextField tf = (JRDesignTextField) new MTextField()
				.createJRElement(jasperDesign);
		tf.setX(0);
		tf.setY(0);
		tf.setWidth(jrGroup.getWidth());
		tf.setHeight(20);
		if ("Crosstab Data Text" != null && jasperDesign.getStylesMap().containsKey("Crosstab Data Text")) { //$NON-NLS-1$ //$NON-NLS-2$
			tf.setStyle((JRStyle) jasperDesign.getStylesMap().get(
					"Crosstab Data Text")); //$NON-NLS-1$
		}
		tf.setExpression(exp);

		headerCell.addElement(tf); // NOI18N
		JRDesignCellContents totalCell = new JRDesignCellContents();
		JRDesignStaticText stext = new JRDesignStaticText();
		stext.setX(0);
		stext.setY(0);
		stext.setWidth(jrGroup.getWidth());
		stext.setHeight(20);
		stext.setText(Messages.common_total + " " + jrGroup.getName()); //$NON-NLS-1$
		totalCell.addElement(stext);
		jrGroup.setTotalHeader(totalCell);
		jrGroup.setTotalPosition(CrosstabTotalPositionEnum.END);
		return jrGroup;
	}

	public static void addRowGroup(JRDesignCrosstab jrCross,
			JRDesignCrosstabRowGroup jrRowGr, int index) throws JRException {
		if (index >= 0 && index < jrCross.getRowGroupsList().size())
			jrCross.addRowGroup(index, jrRowGr);
		else
			jrCross.addRowGroup(jrRowGr);

		if (!jrCross.getCellsMap().containsKey(new Pair(null, null))) {
			JRDesignCrosstabCell dT = new JRDesignCrosstabCell();
			dT.setColumnTotalGroup(null);
			dT.setRowTotalGroup(null);
			jrCross.addCell(dT);
			dT.setHeight(20);
			dT.setWidth(jrRowGr.getWidth());
		}

		JRDesignCrosstabCell dT = new JRDesignCrosstabCell();
		dT.setRowTotalGroup(jrRowGr.getName());
		jrCross.addCell(dT);
		dT.setHeight(20);
		dT.setWidth(jrRowGr.getWidth());

		List<JRCrosstabColumnGroup> columns = jrCross.getColumnGroupsList();
		if (columns != null)
			for (JRCrosstabColumnGroup c : columns) {
				JRDesignCrosstabCell cell = new JRDesignCrosstabCell();
				cell.setRowTotalGroup(jrRowGr.getName());
				cell.setColumnTotalGroup(c.getName());
				jrCross.addCell(cell);
				cell.setHeight(c.getHeight());
				cell.setWidth(jrRowGr.getWidth());
			}

		jrCross.preprocess();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#canUndo()
	 */
	@Override
	public boolean canUndo() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	@Override
	public void undo() {
		DeleteRowGroupCommand.removeRowGroup(jrCrosstab, jrGroup);
	}
}
