/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.model.band.command;

import java.util.List;

import net.sf.jasperreports.engine.JRBand;
import net.sf.jasperreports.engine.design.JRDesignBand;
import net.sf.jasperreports.engine.design.JRDesignSection;

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.MReport;
import com.jaspersoft.studio.model.band.MBand;
import com.jaspersoft.studio.model.band.MBandGroupFooter;
import com.jaspersoft.studio.model.band.MBandGroupHeader;

/*
 * /* The Class ReorderBandCommand.
 */
public class ReorderBandCommand extends Command {

	/** The new index. */
	private int oldIndex, newIndex;

	/** The jr band. */
	private JRDesignBand jrBand;

	/** The jr design section. */
	private JRDesignSection jrDesignSection;

	/**
	 * Instantiates a new reorder band command.
	 * 
	 * @param child
	 *          the child
	 * @param newIndex
	 *          the new index
	 */
	public ReorderBandCommand(MBandGroupHeader child, int newIndex) {
		super(Messages.common_reorder_elements);

		this.newIndex = Math.max(0, newIndex);
		this.jrDesignSection = (JRDesignSection) child.getJrGroup().getGroupHeaderSection();
		this.jrBand = (JRDesignBand) child.getValue();
	}

	/**
	 * Instantiates a new reorder band command.
	 * 
	 * @param child
	 *          the child
	 * @param newIndex
	 *          the new index
	 */
	public ReorderBandCommand(MBandGroupFooter child, int newIndex) {
		super(Messages.common_reorder_elements);

		this.newIndex = newIndex;
		this.jrDesignSection = (JRDesignSection) child.getJrGroup().getGroupFooterSection();
		this.jrBand = (JRDesignBand) child.getValue();
	}

	/**
	 * Instantiates a new reorder band command.
	 * 
	 * @param child
	 *          the child
	 * @param parent
	 *          the parent
	 * @param newIndex
	 *          the new index
	 */
	public ReorderBandCommand(MBand child, MReport parent, int newIndex) {
		super(Messages.common_reorder_elements);

		this.newIndex = Math.max(0, newIndex);
		this.jrDesignSection = (JRDesignSection) parent.getJasperDesign().getDetailSection();
		this.jrBand = (JRDesignBand) child.getValue();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	@Override
	public void execute() {
		oldIndex = doExecute(oldIndex, newIndex);
	}

	private int doExecute(int oldInd, int newInd) {
		List<JRBand> bList = jrDesignSection.getBandsList();
		oldInd = bList.indexOf(jrBand);
		bList.remove(jrBand);
		// System.out.println("---- old: " + oldInd + " new: " + newInd + " "
		// + Integer.toHexString(System.identityHashCode(bList)));
		if (newInd >= 0 && newInd < bList.size())
			bList.add(newInd, jrBand);
		else {
			bList.add(jrBand);
			newInd = bList.size() - 1;
		}
		// System.out.println("old: " + oldInd + " new: " + newInd + " size: " + bList.size());
		jrDesignSection.getEventSupport().fireIndexedPropertyChange(JRDesignSection.PROPERTY_BANDS, newInd, oldInd, -1);
		return oldInd;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	@Override
	public void undo() {
		doExecute(newIndex, oldIndex);
	}
}
