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
package com.jaspersoft.studio.editor.report;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.design.JRDesignImage;
import net.sf.jasperreports.engine.design.JRDesignSubreport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.JRExpressionUtil;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.ui.IEditorActionBarContributor;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.part.MultiPageEditorActionBarContributor;
import org.eclipse.ui.part.MultiPageEditorPart;
import org.eclipse.ui.views.properties.IPropertySheetPage;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertySheetPageContributor;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import com.jaspersoft.studio.ExtensionManager;
import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.editor.IJROBjectEditor;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.MPage;
import com.jaspersoft.studio.model.MRoot;
import com.jaspersoft.studio.utils.SelectionHelper;

/**
 * The Class ReportContainer.
 * 
 * @author Chicu Veaceslav
 */
public class ReportContainer extends MultiPageEditorPart implements ITabbedPropertySheetPageContributor,
		IJROBjectEditor {

	/** The model. */
	private INode model = null;

	/** The editors. */
	private List<AbstractVisualEditor> editors = new ArrayList<AbstractVisualEditor>();

	/** The parent. */
	private EditorPart parent;
	private PropertyChangeSupport propertyChangeSupport;

	public PropertyChangeSupport getPropertyChangeSupport() {
		if (propertyChangeSupport == null)
			propertyChangeSupport = new PropertyChangeSupport(this);
		return propertyChangeSupport;
	}

	/**
	 * Instantiates a new report container.
	 * 
	 * @param parent
	 *          the parent
	 */
	public ReportContainer(EditorPart parent) {
		this.parent = parent;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.part.MultiPageEditorPart#getActiveEditor()
	 */
	@Override
	public IEditorPart getActiveEditor() {
		return super.getActiveEditor();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.part.MultiPageEditorPart#createPages()
	 */
	@Override
	protected void createPages() {
		((CTabFolder) getContainer()).setTabPosition(SWT.TOP);
		((CTabFolder) getContainer()).setUnselectedCloseVisible(true);
		try {
			ReportEditor reportEditor = new ReportEditor();
			int index = addPage(reportEditor, getEditorInput());
			setPageText(index, Messages.ReportContainer_main_report);
			setPageImage(index, reportEditor.getPartImage());
			editors.add(reportEditor);
		} catch (PartInitException e) {
			ErrorDialog.openError(getSite().getShell(), Messages.common_error_creating_nested_visual_editor, null,
					e.getStatus());
		}
		getEditorSite().getActionBarContributor();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.part.EditorPart#doSave(org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public void doSave(IProgressMonitor monitor) {
		for (AbstractVisualEditor editor : editors) {
			editor.doSave(monitor);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.part.EditorPart#doSaveAs()
	 */
	@Override
	public void doSaveAs() {
		for (AbstractVisualEditor editor : editors) {
			editor.doSaveAs();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.part.EditorPart#isSaveAsAllowed()
	 */
	@Override
	public boolean isSaveAsAllowed() {
		return true;
	}

	private PropertyChangeListener modelListener = new PropertyChangeListener() {

		public void propertyChange(PropertyChangeEvent evt) {
			if (evt.getNewValue() != null && evt.getOldValue() == null) {
				createEditorPage(evt.getNewValue());
			} else if (evt.getNewValue() == null && evt.getOldValue() != null) {
				AbstractVisualEditor obj = ccMap.get(evt.getOldValue());
				if (obj != null)
					removeEditorPage(evt, obj);
			}
			getPropertyChangeSupport().firePropertyChange(evt);
		}

	};

	/**
	 * Sets the model.
	 * 
	 * @param model
	 *          the new model
	 */
	public void setModel(INode model) {
		if (this.model != null && this.model.getChildren() != null && !this.model.getChildren().isEmpty())
			this.model.getChildren().get(0).getPropertyChangeSupport().addPropertyChangeListener(modelListener);
		if (model != null && model.getChildren() != null && !model.getChildren().isEmpty())
			model.getChildren().get(0).getPropertyChangeSupport().addPropertyChangeListener(modelListener);
		this.model = model;
		updateVisualView();
	}

	private Map<Object, AbstractVisualEditor> ccMap = new HashMap<Object, AbstractVisualEditor>();

	private ExtensionManager m = JaspersoftStudioPlugin.getExtensionManager();

	private AbstractVisualEditor createEditorPage(Object obj) {
		AbstractVisualEditor ave = ccMap.get(obj);
		try {
			if (ave == null) {
				JasperDesign jd = getModel().getJasperDesign();
				MRoot root = new MRoot(null, jd);
				MPage rep = new MPage(root, jd);
				ANode node = m.createNode(rep, obj, -1);

				ave = m.getEditor(obj);
				if (ave != null) {
					int index = addPage(ave, getEditorInput());

					editors.add(ave);
					ccMap.put(node.getValue(), ave);
					ave.setModel(root);
					setPageText(index, ave.getPartName());
					setPageImage(index, ave.getPartImage());

					rep.getPropertyChangeSupport().addPropertyChangeListener(modelListener);
				}
			}
		} catch (PartInitException e) {
			e.printStackTrace();
		}
		return ave;
	}

	private void removeEditorPage(PropertyChangeEvent evt, AbstractVisualEditor ave) {
		ave.getModel().getPropertyChangeSupport().addPropertyChangeListener(modelListener);
		ave.setModel(null);
		int ind = editors.indexOf(ave);
		removePage(ind);
		editors.remove(ind);
		ccMap.remove(evt.getOldValue());
		ave.dispose();
	}

	/**
	 * Update visual view.
	 */
	public void updateVisualView() {
		AbstractVisualEditor ave = getMainEditor();
		if (ave != null)
			ave.setModel(this.model);
	}

	public AbstractVisualEditor getMainEditor() {
		if (editors != null && !editors.isEmpty())
			return editors.get(0);
		return null;
	}

	/**
	 * Gets the model.
	 * 
	 * @return the model
	 */
	public INode getModel() {
		return model;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.part.MultiPageEditorPart#getAdapter(java.lang.Class)
	 */
	@Override
	public Object getAdapter(Class type) {
		if (type == IPropertySource.class)
			return getPropertySheetPage();
		if (type == IPropertySheetPage.class)
			return getPropertySheetPage();
		return super.getAdapter(type);
	}

	/** The property sheet page. */
	private IPropertySheetPage propertySheetPage;

	/**
	 * Gets the property sheet page.
	 * 
	 * @return the property sheet page
	 */
	public IPropertySheetPage getPropertySheetPage() {
		TabbedPropertySheetPage tpsp = new TabbedPropertySheetPage(ReportContainer.this, true);
		propertySheetPage = tpsp;

		return propertySheetPage;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.tabbed.ITabbedPropertySheetPageContributor#getContributorId()
	 */
	public String getContributorId() {
		return "com.jaspersoft.studio.editor.report.ReportContainer"; //$NON-NLS-1$
	}

	@Override
	protected void pageChange(int newPageIndex) {
		super.pageChange(newPageIndex);
		AbstractVisualEditor activeEditor = editors.get(newPageIndex);
		activeEditor.setModel(activeEditor.getModel());

		IEditorActionBarContributor contributor = parent.getEditorSite().getActionBarContributor();
		if (contributor != null && contributor instanceof MultiPageEditorActionBarContributor) {

			((MultiPageEditorActionBarContributor) contributor).setActivePage(activeEditor);
		}
	}

	public void openEditor(Object obj) {
		if (getEditorInput() instanceof FileEditorInput) {
			if (obj instanceof JRDesignSubreport) {
				if (getEditorInput() instanceof FileEditorInput) {
					JRDesignSubreport s = (JRDesignSubreport) obj;
					if (s.getExpression() != null)
						SelectionHelper.openEditor((FileEditorInput) getEditorInput(),
								JRExpressionUtil.getSimpleExpressionText(s.getExpression()));
				}
				return;
			}
			if (obj instanceof JRDesignImage) {
				if (getEditorInput() instanceof FileEditorInput) {
					JRDesignImage s = (JRDesignImage) obj;
					if (s.getExpression() != null)
						SelectionHelper.openEditor((FileEditorInput) getEditorInput(),
								JRExpressionUtil.getSimpleExpressionText(s.getExpression()));
				}
				return;
			}
		}
		if (obj instanceof JasperDesign) {
			setActivePage(0);
		} else {
			AbstractVisualEditor ave = createEditorPage(obj);
			if (getActiveEditor() != ave) {
				int index = editors.indexOf(ave);
				if (index > 0 && index <= editors.size() - 1) {
					setActivePage(index);
					// if (obj instanceof JRDesignElement)
					// SelectionHelper.setSelection((JRDesignElement) obj, true);
					// ave.getGraphicalViewer().setSelection(new StructuredSelection(obj));
				}
			}
		}
	}
}
