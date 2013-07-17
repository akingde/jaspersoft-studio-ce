/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.server.publish.wizard;

import net.sf.jasperreports.engine.design.JRDesignExpression;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CheckboxCellEditor;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import com.jaspersoft.studio.editor.expression.ExpressionContext;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.descriptor.checkbox.CheckBoxLabelProvider;
import com.jaspersoft.studio.property.descriptor.expression.JRExpressionCellEditor;
import com.jaspersoft.studio.server.Activator;
import com.jaspersoft.studio.server.messages.Messages;
import com.jaspersoft.studio.server.model.AFileResource;
import com.jaspersoft.studio.server.model.MResource;
import com.jaspersoft.studio.server.publish.PublishUtil;
import com.jaspersoft.studio.swt.widgets.table.ListContentProvider;
import com.jaspersoft.studio.utils.Misc;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;
import com.jaspersoft.studio.wizards.ContextHelpIDs;
import com.jaspersoft.studio.wizards.JSSHelpWizardPage;

public class ResourcesPage extends JSSHelpWizardPage {
	private JasperReportsConfiguration jConfig;
	private TableViewer tableViewer;

	protected ResourcesPage(JasperReportsConfiguration jConfig) {
		super("serverrespublish"); //$NON-NLS-1$
		setTitle(Messages.ResourcesPage_title);
		setDescription(Messages.ResourcesPage_description);
		this.jConfig = jConfig;
	}

	/**
	 * Return the context name for the help of this page
	 */
	@Override
	protected String getContextName() {
		return ContextHelpIDs.WIZARD_SELECT_RESOURCES;
	}

	public boolean isEmpty() {
		return tableViewer.getTable().getItemCount() > 0;
	}

	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		setControl(composite);
		composite.setLayout(new GridLayout());

		tableViewer = new TableViewer(composite, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
		tableViewer.setContentProvider(new ListContentProvider());
		ColumnViewerToolTipSupport.enableFor(tableViewer);
		Table table = (Table) tableViewer.getControl();
		table.setHeaderVisible(true);
		table.setLayoutData(new GridData(GridData.FILL_BOTH));

		TableViewerColumn viewerColumn = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn column = viewerColumn.getColumn();
		column.setText(Messages.ResourcesPage_table_resource);
		column.setWidth(300);
		viewerColumn.setLabelProvider(new TLabelProvider() {
			@Override
			public String getText(Object element) {
				MResource fr = (MResource) element;
				return fr.getDisplayText();
			}

			@Override
			public Image getImage(Object element) {
				MResource fr = (MResource) element;
				return Activator.getDefault().getImage(fr.getThisIconDescriptor().getIcon16());
			}
		});

		viewerColumn = new TableViewerColumn(tableViewer, SWT.NONE);
		column = viewerColumn.getColumn();
		column.setText(Messages.ResourcesPage_table_overwrite);
		column.setWidth(100);
		viewerColumn.setLabelProvider(new TLabelProvider() {
			private CheckBoxLabelProvider chLabelProvider = new CheckBoxLabelProvider(NullEnum.NOTNULL);

			@Override
			public String getText(Object element) {
				MResource fr = (MResource) element;
				return chLabelProvider.getText(fr.getPublishOptions().isOverwrite());
			}

			@Override
			public Image getImage(Object element) {
				MResource fr = (MResource) element;
				return chLabelProvider.getCellEditorImage(fr.getPublishOptions().isOverwrite());
			}
		});

		viewerColumn = new TableViewerColumn(tableViewer, SWT.NONE);
		column = viewerColumn.getColumn();
		column.setText("Expression");
		column.setWidth(100);
		viewerColumn.setLabelProvider(new TLabelProvider() {
			@Override
			public String getText(Object element) {
				MResource fr = (MResource) element;
				JRDesignExpression exp = fr.getPublishOptions().getjExpression();
				if (exp != null)
					return Misc.nvl(exp.getText());
				return "";
			}

			@Override
			public String getToolTipText(Object element) {
				return getText(element);
			}
		});

		viewerColumn = new TableViewerColumn(tableViewer, SWT.NONE);
		column = viewerColumn.getColumn();
		column.setText("File Size");
		column.setWidth(100);
		viewerColumn.setLabelProvider(new TLabelProvider() {

			@Override
			public String getText(Object element) {
				if (element instanceof AFileResource)
					return ((AFileResource) element).getHFFileSize();
				return "";
			}

		});

		attachCellEditors(tableViewer, table);

		fillData();
	}

	private void attachCellEditors(final TableViewer viewer, Composite parent) {
		viewer.setCellModifier(new ICellModifier() {
			public boolean canModify(Object element, String property) {
				if (property.equals("VALUE")) //$NON-NLS-1$
					return true;
				if (property.equals("EXPRESSION") && ((MResource) element).getPublishOptions().getjExpression() != null)
					return true;
				return false;
			}

			public Object getValue(Object element, String property) {
				MResource prop = (MResource) element;
				if ("VALUE".equals(property)) //$NON-NLS-1$
					return prop.getPublishOptions().isOverwrite();
				if ("NAME".equals(property)) //$NON-NLS-1$
					return prop.getDisplayText();
				if ("FILESIZE".equals(property)) {
					if (prop instanceof AFileResource)
						return ((AFileResource) element).getHFFileSize();
				}
				if ("EXPRESSION".equals(property))
					return prop.getPublishOptions().getjExpression();
				return ""; //$NON-NLS-1$
			}

			public void modify(Object element, String property, Object value) {
				TableItem tableItem = (TableItem) element;
				MResource data = (MResource) tableItem.getData();
				if ("VALUE".equals(property)) //$NON-NLS-1$
					data.getPublishOptions().setOverwrite((Boolean) value);
				tableViewer.update(element, new String[] { property });
				tableViewer.refresh();
			}
		});

		JRExpressionCellEditor expEditor = new JRExpressionCellEditor(parent, new ExpressionContext(jConfig));
		viewer.setCellEditors(new CellEditor[] { new TextCellEditor(parent), new CheckboxCellEditor(parent), expEditor, new TextCellEditor(parent, SWT.RIGHT) });
		viewer.setColumnProperties(new String[] { "NAME", "VALUE", "EXPRESSION", "FILESIZE" }); //$NON-NLS-1$ //$NON-NLS-2$
	}

	public void fillData() {
		tableViewer.setInput(PublishUtil.getResources(jConfig));
		tableViewer.refresh();
	}

	abstract class TLabelProvider extends ColumnLabelProvider {

		@Override
		public String getToolTipText(Object element) {
			if (element instanceof AFileResource && ((AFileResource) element).getFile() != null)
				return ((AFileResource) element).getFile().getAbsolutePath();
			return "";
		}

		@Override
		public int getToolTipDisplayDelayTime(Object object) {
			return 100; // msec
		}

		@Override
		public int getToolTipTimeDisplayed(Object object) {
			return 5000; // msec
		}
	}

}
