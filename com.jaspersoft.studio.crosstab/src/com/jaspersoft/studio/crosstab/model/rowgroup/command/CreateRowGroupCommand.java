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

import net.sf.jasperreports.crosstabs.JRCrosstabColumnGroup;
import net.sf.jasperreports.crosstabs.design.JRDesignCellContents;
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstab;
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstabBucket;
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstabCell;
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstabRowGroup;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRStyle;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JRDesignTextField;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.Pair;

import org.eclipse.gef.commands.Command;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.swt.widgets.Display;

import com.jaspersoft.studio.crosstab.model.rowgroup.MRowGroup;
import com.jaspersoft.studio.crosstab.model.rowgroup.MRowGroups;
import com.jaspersoft.studio.model.text.MTextField;
import com.jaspersoft.studio.utils.ModelUtils;

/**
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
	 *          the dest node
	 * @param srcNode
	 *          the src node
	 * @param position
	 *          the position
	 * @param index
	 *          the index
	 */
	public CreateRowGroupCommand(MRowGroups destNode, MRowGroup srcNode, int index) {
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
			this.jrGroup = new JRDesignCrosstabRowGroup();
			this.jrGroup.setName(ModelUtils.getDefaultName(jrCrosstab.getRowGroupIndicesMap(), "Row Group"));
			this.jrGroup.setWidth(100);

			JRDesignExpression exp = new JRDesignExpression();
			exp.setValueClassName("java.lang.String");
			exp.setText("");
			JRDesignCrosstabBucket bucket = new JRDesignCrosstabBucket();
			bucket.setExpression(exp);
			this.jrGroup.setBucket(bucket);

			JRDesignCellContents headerCell = new JRDesignCellContents();
			jrGroup.setHeader(headerCell);
			// the width is the with of the current base cell...
			JRDesignCrosstabCell baseCell = (JRDesignCrosstabCell) jrCrosstab.getCellsMap().get(new Pair(null, null));
			int baseHeight = (baseCell.getHeight() != null) ? baseCell.getHeight()
					: ((baseCell.getContents() != null) ? baseCell.getContents().getHeight() : 30);

			exp = new JRDesignExpression();
			exp.setValueClassName("java.lang.String");
			exp.setText("$V{" + jrGroup.getName() + "}");

			JRDesignTextField tf = (JRDesignTextField) new MTextField().createJRElement(jasperDesign);
			tf.setX(0);
			tf.setY(0);
			tf.setWidth(60);
			tf.setHeight(20);
			if ("Crosstab Data Text" != null && jasperDesign.getStylesMap().containsKey("Crosstab Data Text")) {
				tf.setStyle((JRStyle) jasperDesign.getStylesMap().get("Crosstab Data Text"));
			}
			tf.setExpression(exp);

			headerCell.addElement(tf); // NOI18N
			jrGroup.setTotalHeader(new JRDesignCellContents());
		}
		if (jrGroup != null) {
			try {
				addRowGroup(jrCrosstab, jrGroup);
			} catch (JRException e) {
				e.printStackTrace();
				if (e.getMessage().startsWith("Duplicate declaration")) {
					String defaultName = ModelUtils.getDefaultName(jrCrosstab.getRowGroupIndicesMap(), "CopyOFRowGroup_");
					InputDialog dlg = new InputDialog(Display.getCurrent().getActiveShell(), "Row Group Name",
							"Please, enter unique Row Group name", defaultName, null);
					if (dlg.open() == InputDialog.OK) {
						jrGroup.setName(dlg.getValue());
						execute();
					}
				}
			}
		}
	}

	public static void addRowGroup(JRDesignCrosstab jrCross, JRDesignCrosstabRowGroup jrRowGr) throws JRException {
		jrCross.addRowGroup(jrRowGr);

		// I need to add the extra cells...
		JRCrosstabColumnGroup[] columns = jrCross.getColumnGroups();
		JRDesignCrosstabCell dT = new JRDesignCrosstabCell();
		dT.setRowTotalGroup(jrRowGr.getName());
		jrCross.addCell(dT);
		// for each column, we need to add the total...
		for (int i = 0; i < columns.length; ++i) {
			JRDesignCrosstabCell cell = new JRDesignCrosstabCell();
			cell.setRowTotalGroup(jrRowGr.getName());
			cell.setColumnTotalGroup(columns[i].getName());
			jrCross.addCell(cell);

			// Add some cells...
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
