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
package com.jaspersoft.studio.table.model.table.command;

import net.sf.jasperreports.components.table.StandardTable;
import net.sf.jasperreports.engine.JRElementGroup;
import net.sf.jasperreports.engine.component.ComponentKey;
import net.sf.jasperreports.engine.design.JRDesignComponentElement;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignDatasetRun;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JRDesignElementGroup;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JRDesignFrame;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.IGroupElement;
import com.jaspersoft.studio.model.MElementGroup;
import com.jaspersoft.studio.model.MFrame;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.model.band.MBand;
import com.jaspersoft.studio.utils.ModelUtils;
import com.jaspersoft.studio.utils.SelectionHelper;

/**
 * link nodes & together.
 * 
 * @author Chicu Veaceslav
 */
public class CreateTableCommand extends Command {

	/** The src node. */
	private MGraphicElement srcNode;

	/** The jr element. */
	private JRDesignElement jrElement;

	/** The jr group. */
	private JRElementGroup jrGroup;

	/** The location. */
	private Point location;
	private JasperDesign jrDesign;

	/** The index. */
	private int index;

	/**
	 * Instantiates a new creates the element command.
	 * 
	 * @param destNode
	 *          the dest node
	 * @param srcNode
	 *          the src node
	 * @param index
	 *          the index
	 */
	public CreateTableCommand(MElementGroup destNode, MGraphicElement srcNode, int index) {
		super();
		setContext(destNode, srcNode, index);
	}

	/**
	 * Instantiates a new creates the element command.
	 * 
	 * @param destNode
	 *          the dest node
	 * @param srcNode
	 *          the src node
	 * @param index
	 *          the index
	 */
	public CreateTableCommand(MFrame destNode, MGraphicElement srcNode, int index) {
		super();
		setContext(destNode, srcNode, index);
	}

	/**
	 * Instantiates a new creates the element command.
	 * 
	 * @param destNode
	 *          the dest node
	 * @param srcNode
	 *          the src node
	 * @param index
	 *          the index
	 */
	public CreateTableCommand(MBand destNode, MGraphicElement srcNode, int index) {
		super();
		setContext(destNode, srcNode, index);
	}

	/**
	 * Instantiates a new creates the element command.
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
	public CreateTableCommand(ANode destNode, MGraphicElement srcNode, Point position, int index) {
		super();
		if (destNode instanceof IGroupElement)
			setContext(destNode, srcNode, index);
		else
			setContext(fixPosition(destNode, srcNode, position), srcNode, index);
	}

	/**
	 * Sets the context.
	 * 
	 * @param destNode
	 *          the dest node
	 * @param srcNode
	 *          the src node
	 * @param index
	 *          the index
	 */
	protected void setContext(ANode destNode, MGraphicElement srcNode, int index) {
		this.srcNode = srcNode;
		this.jrElement = (JRDesignElement) srcNode.getValue();
		if (destNode instanceof IGroupElement)
			this.jrGroup = ((IGroupElement) destNode).getJRElementGroup();
		else
			this.jrGroup = (JRElementGroup) destNode.getValue();
		this.index = index;
		this.jrDesign = destNode.getJasperDesign();
	}

	/**
	 * Fix position.
	 * 
	 * @param destNode
	 *          the dest node
	 * @param srcNode
	 *          the src node
	 * @param position
	 *          the position
	 * @return the a node
	 */
	protected ANode fixPosition(ANode destNode, MGraphicElement srcNode, Point position) {
		// calculate position, fix position relative to parent
		MBand band = ModelUtils.getBand4Point(destNode, position);
		// set proposed bounds
		int x = position.x - band.getBounds().x;
		int y = position.y - band.getBounds().y;

		this.location = new Point(x, y);
		return band;
	}

	/**
	 * Creates the object.
	 */
	protected void createObject() {
		if (jrElement == null) {
			// here put a wizard
			// BarcodeWizard wizard = new BarcodeWizard();
			// WizardDialog dialog = new WizardDialog(Display.getCurrent().getActiveShell(), wizard);
			// dialog.create();
			// if (dialog.open() == Dialog.OK) {
			// srcNode = wizard.getBarcode();
			// if (srcNode.getValue() == null)
			// jrElement = srcNode.createJRElement(srcNode.getJasperDesign());
			// else
			// jrElement = (JRDesignElement) srcNode.getValue();
			// }
			jrElement = new JRDesignComponentElement();
			StandardTable component = new StandardTable();

			// jrElement.setKey((String) wizardDescriptor.getProperty("basename"));
			((JRDesignComponentElement) jrElement).setComponent(component);
			((JRDesignComponentElement) jrElement).setComponentKey(new ComponentKey(
					"http://jasperreports.sourceforge.net/jasperreports/components", "jr", "table"));

			JRDesignDataset newDataset = new JRDesignDataset(false);
			String name = "Table Dataset ";
			for (int i = 1;; i++) {
				if (!jrDesign.getDatasetMap().containsKey(name + i)) {
					newDataset.setName(name + i);
					break;
				}
			}
			// try {
			// jd.addDataset(newDataset);
			// AddDatasetUndoableEdit edit2 = new AddDatasetUndoableEdit(newDataset, jd);
			// if (edit == null) edit = edit2;
			// else edit.concatenate(edit2);
			//
			// } catch (JRException ex) {
			// //Exceptions.printStackTrace(ex);
			// }

			JRDesignDatasetRun datasetRun = new JRDesignDatasetRun();

			datasetRun.setDatasetName(newDataset.getName());
			JRDesignExpression exp = new JRDesignExpression();
			exp.setValueClassName("net.sf.jasperreports.engine.JRDataSource");// NOI18N
			exp.setText("new net.sf.jasperreports.engine.JREmptyDataSource(1)");// NOI18N

			datasetRun.setDataSourceExpression(exp);
			component.setDatasetRun(datasetRun);
			if (jrElement != null) {
				if (location == null)
					location = new Point(0, 0);
				jrElement.setX(location.x);
				jrElement.setY(location.y);
				jrElement.setWidth(srcNode.getDefaultWidth());
				jrElement.setHeight(srcNode.getDefaultHeight());
			}
		}
	}

	public void setJrGroup(JRElementGroup jrGroup) {
		this.jrGroup = jrGroup;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	@Override
	public void execute() {
		createObject();
		if (jrElement != null) {
			if (jrGroup instanceof JRDesignElementGroup) {
				if (index < 0 || index > jrGroup.getChildren().size())
					((JRDesignElementGroup) jrGroup).addElement(jrElement);
				else
					((JRDesignElementGroup) jrGroup).addElement(index, jrElement);
			} else if (jrGroup instanceof JRDesignFrame) {
				if (index < 0 || index > jrGroup.getChildren().size())
					((JRDesignFrame) jrGroup).addElement(jrElement);
				else
					((JRDesignFrame) jrGroup).addElement(index, jrElement);
			}
		}
		if (firstTime) {
			SelectionHelper.setSelection(jrElement, false);
			firstTime = false;
		}
	}

	private boolean firstTime = true;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#canUndo()
	 */
	@Override
	public boolean canUndo() {
		if (jrGroup == null || jrElement == null)
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	@Override
	public void undo() {
		if (jrGroup instanceof JRDesignElementGroup)
			((JRDesignElementGroup) jrGroup).removeElement(jrElement);
		else if (jrGroup instanceof JRDesignFrame)
			((JRDesignFrame) jrGroup).removeElement(jrElement);
	}

	/**
	 * Gets the jr element.
	 * 
	 * @return the jr element
	 */
	public JRDesignElement getJrElement() {
		return jrElement;
	}

	/**
	 * Gets the jr group.
	 * 
	 * @return the jr group
	 */
	public JRElementGroup getJrGroup() {
		return jrGroup;
	}

	/**
	 * Gets the location.
	 * 
	 * @return the location
	 */
	public Point getLocation() {
		return location;
	}

	/**
	 * Gets the index.
	 * 
	 * @return the index
	 */
	public int getIndex() {
		return index;
	}
}
