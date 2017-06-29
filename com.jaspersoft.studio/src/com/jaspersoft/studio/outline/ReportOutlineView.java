/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.outline;

import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.contentoutline.ContentOutlinePage;

import com.jaspersoft.studio.model.INode;

/*
 * The Class ReportOutlineView.
 * 
 * @author Chicu Veaceslav
 */
public class ReportOutlineView extends ContentOutlinePage {

	/** The model. */
	private INode model;

	/**
	 * Instantiates a new report outline view.
	 * 
	 * @param model
	 *          the model
	 */
	public ReportOutlineView(INode model) {
		this.model = model;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.contentoutline.ContentOutlinePage#createControl(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void createControl(Composite parent) {
		super.createControl(parent);
		TreeViewer treeViewer = getTreeViewer();
		treeViewer.setContentProvider(new ReportTreeContetProvider());
		treeViewer.setLabelProvider(new ReportTreeLabelProvider());
		treeViewer.setInput(model);
		treeViewer.expandToLevel(2);
		ColumnViewerToolTipSupport.enableFor(treeViewer);
		getSite().setSelectionProvider(treeViewer);
	}

	/**
	 * Sets the model.
	 * 
	 * @param model
	 *          the new model
	 */
	public void setModel(INode model) {
		this.model = model;
		getTreeViewer().setInput(model);
		getTreeViewer().expandToLevel(2);
	}

}
