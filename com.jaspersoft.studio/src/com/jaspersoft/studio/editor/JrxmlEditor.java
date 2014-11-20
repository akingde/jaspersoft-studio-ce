/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved. http://www.jaspersoft.com.
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.editor;

import java.util.HashSet;

import net.sf.jasperreports.eclipse.builder.jdt.JRErrorHandler;
import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.JRExpressionCollector;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.dialogs.IPageChangedListener;
import org.eclipse.jface.dialogs.PageChangedEvent;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.ui.part.MultiPageEditorPart;

import com.jaspersoft.studio.editor.preview.view.control.VErrorPreview;
import com.jaspersoft.studio.editor.report.CachedSelectionProvider;
import com.jaspersoft.studio.editor.report.CommonSelectionCacheProvider;
import com.jaspersoft.studio.editor.report.ReportContainer;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.util.ReportFactory;

/*
 * An example showing how to create a multi-page editor. This example has 3 pages: <ul> <li>page 0 contains a nested
 * text editor. <li>page 1 allows you to change the font used in page 2 <li>page 2 shows the words in page 0 in sorted
 * order </ul>
 */
public class JrxmlEditor extends AbstractJRXMLEditor implements IJROBjectEditor,
		 CachedSelectionProvider {
	
	/** The text editor used in page 0. */
	private ReportContainer reportContainer;
	
	/** The Editor ID */
	public static final String JRXML_EDITOR_ID = "com.jaspersoft.studio.editor.JrxmlEditor"; //$NON-NLS-1$

	/*
	 * (non-Javadoc)
	 * @see com.jaspersoft.studio.editor.AbstractJRXMLEditor#createDesignEditorPage()
	 */
	@Override
	protected void createDesignEditorPage() throws PartInitException {
		reportContainer = new ReportContainer(this, jrContext);
		reportContainer.addPageChangedListener(new IPageChangedListener() {

			public void pageChanged(PageChangedEvent event) {
				updateContentOutline(getActivePage());
			}
		});

		int index = addPage(reportContainer, getEditorInput());
		setPageText(index, Messages.JrxmlEditor_design);
	}

	/**
	 * @return the design editor
	 */
	public ReportContainer getReportContainer() {
		return reportContainer;
	}
	
	@Override
	protected String getEditorHelpID() {
		return "com.jaspersoft.studio.doc.editor_jrxml";
	}

	/**
	 * Gets the current active inner editor.
	 * The {@link ReportContainer} is itself a {@link MultiPageEditorPart}, so
	 * it can contains different opened editors (i.e. lists, tables, cross-tabs).
	 * 
	 * @return the second level active editor
	 */
	public IEditorPart getActiveEditor2() {
		IEditorPart iep = getActiveEditor();
		if (iep instanceof ReportContainer)
			return ((ReportContainer) iep).getActiveEditor();
		return iep;
	}

	/*
	 * (non-Javadoc)
	 * @see com.jaspersoft.studio.editor.AbstractJRXMLEditor#updateVisualView()
	 */
	@Override
	public void updateVisualView() {
		if (reportContainer != null)
			reportContainer.setModel(getModel());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.ide.IGotoMarker
	 */
	public void gotoMarker(IMarker marker) {
		if (activePage == PAGE_DESIGNER) {
			try {
				Object expr = marker.getAttribute(JRErrorHandler.MARKER_ERROR_JREXPRESSION);
				if (expr != null && expr instanceof String) {
					JRDesignExpression expression = new JRDesignExpression();
					expression.setId(new Integer((String) expr));
					JasperDesign jd = getJasperDesign();
					JRExpressionCollector rc = JRExpressionCollector.collector(jrContext, jd);
					if (!VErrorPreview.openExpressionEditor(jrContext, rc, (JRDesignDataset) jd.getMainDataset(), expression))
						for (JRDataset d : jd.getDatasetsList())
							if (VErrorPreview.openExpressionEditor(jrContext, rc, (JRDesignDataset) d, expression))
								return;
				}
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
		setActivePage(PAGE_SOURCEEDITOR);
		IDE.gotoMarker(xmlEditor, marker);
	}

	/**
	 * Opens an editor (even an internal one), using the 
	 * specified object and Anode information.
	 */
	public void openEditor(Object obj, ANode node) {
		reportContainer.openEditor(obj, node);
	}

	/**
	 * FIXME: This method is currently commented because it's to heavy.
	 * Should implement a faster solution if possible.
	 */
	public void refreshExternalStyles(HashSet<String> removedStyles) {
		// Very very heavy method, leave commented for future improovments
		/*
		 * JasperDesign jrDesign = getMReport().getJasperDesign(); for(JRDesignElement element :
		 * ModelUtils.getAllElements(jrDesign)){ if (element.getStyleNameReference() != null &&
		 * removedStyles.contains(element.getStyleNameReference())){ String styleName = element.getStyleNameReference();
		 * element.setStyleNameReference(null); element.setStyleNameReference(styleName); } } StyleHandlingReportConverter
		 * reportConverter =
		 * ((AEditPartFactory)reportContainer.getMainEditor().getGraphicalViewer().getEditPartFactory()).getReportConverter
		 * (); if (reportConverter != null) reportConverter.resetStyles(jrDesign);
		 */
	}

	@Override
	public CommonSelectionCacheProvider getSelectionCache() {
		return reportContainer.getSelectionCache();
	}

	@Override
	protected boolean isDesignerDirty() {
		return reportContainer.isDirty();
	}

	@Override
	protected ISelection getDesignerPageSelection() {
		return reportContainer.getActiveEditor().getSite().getSelectionProvider().getSelection();
	}

	@Override
	protected void setDesignerPageSelection(ISelection newSelection) {
		reportContainer.getActiveEditor().getSite().getSelectionProvider().setSelection(newSelection);
	}

	@Override
	protected EditorPart getDesignEditor() {
		return reportContainer;
	}

	@Override
	protected INode createEditorModel() {
		return ReportFactory.createReport(jrContext);
	}

}
