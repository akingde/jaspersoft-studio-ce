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
import net.sf.jasperreports.engine.component.ComponentKey;
import net.sf.jasperreports.engine.design.JRDesignComponentElement;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignDatasetRun;
import net.sf.jasperreports.engine.design.JRDesignExpression;

import org.eclipse.draw2d.geometry.Rectangle;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.MElementGroup;
import com.jaspersoft.studio.model.MFrame;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.model.band.MBand;
import com.jaspersoft.studio.model.command.CreateElementCommand;

/**
 * link nodes & together.
 * 
 * @author Chicu Veaceslav
 */
public class CreateTableCommand extends CreateElementCommand {

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
		super(destNode, srcNode, index);
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
		super(destNode, srcNode, index);
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
		super(destNode, srcNode, index);
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
	public CreateTableCommand(ANode destNode, MGraphicElement srcNode, Rectangle position, int index) {
		super(destNode, srcNode, position, index);
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
					"http://jasperreports.sourceforge.net/jasperreports/components", "jr", "table")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

			JRDesignDataset newDataset = new JRDesignDataset(false);
			String name = "Table Dataset "; //$NON-NLS-1$
			for (int i = 1;; i++) {
				if (!jasperDesign.getDatasetMap().containsKey(name + i)) {
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
			exp.setValueClassName("net.sf.jasperreports.engine.JRDataSource");// NOI18N //$NON-NLS-1$
			exp.setText("new net.sf.jasperreports.engine.JREmptyDataSource(1)");// NOI18N //$NON-NLS-1$

			datasetRun.setDataSourceExpression(exp);
			component.setDatasetRun(datasetRun);

			if (jrElement != null)
				setElementBounds();
		}
	}

}
