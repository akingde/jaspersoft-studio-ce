/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.publish.wizard.page;

import java.util.List;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.fieldassist.FieldDecoration;
import org.eclipse.jface.fieldassist.FieldDecorationRegistry;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.wb.swt.ResourceManager;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.editor.expression.ExpressionContext;
import com.jaspersoft.studio.property.descriptor.expression.JRExpressionCellEditor;
import com.jaspersoft.studio.server.Activator;
import com.jaspersoft.studio.server.messages.Messages;
import com.jaspersoft.studio.server.model.AFileResource;
import com.jaspersoft.studio.server.model.AMJrxmlContainer;
import com.jaspersoft.studio.server.model.AMResource;
import com.jaspersoft.studio.server.model.MInputControl;
import com.jaspersoft.studio.server.model.MReportUnit;
import com.jaspersoft.studio.server.preferences.JRSPreferencesPage;
import com.jaspersoft.studio.server.publish.OverwriteEnum;
import com.jaspersoft.studio.server.publish.PublishOptions;
import com.jaspersoft.studio.server.publish.PublishUtil;
import com.jaspersoft.studio.server.publish.ResourcePublishMethod;
import com.jaspersoft.studio.server.publish.action.ReferenceResourceAction;
import com.jaspersoft.studio.server.publish.action.ResourceExpressionAction;
import com.jaspersoft.studio.server.publish.action.ResourceToFolderAction;
import com.jaspersoft.studio.server.publish.action.SelectLocalAction;
import com.jaspersoft.studio.swt.widgets.table.ListContentProvider;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;
import com.jaspersoft.studio.wizards.ContextHelpIDs;
import com.jaspersoft.studio.wizards.JSSHelpWizardPage;

import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.engine.design.JRDesignExpression;

public class ResourcesPage extends JSSHelpWizardPage {
	private JasperReportsConfiguration jConfig;
	private TableViewer tableViewer;

	public ResourcesPage(JasperReportsConfiguration jConfig) {
		super("serverrespublish"); //$NON-NLS-1$
		setTitle(Messages.ResourcesPage_title);
		setDescription(Messages.ResourcesPage_description);
		this.jConfig = jConfig;
	}

	private AMJrxmlContainer pres;

	public void setParentResource(AMJrxmlContainer pres) {
		this.pres = pres;
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

		tableViewer = new TableViewer(composite,
				SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
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
				AMResource fr = (AMResource) element;
				return fr.getDisplayText();
			}

			@Override
			public Image getImage(Object element) {
				setErrorMessage(null);
				ResourcesPage.this.setPageComplete(true);
				AMResource fr = (AMResource) element;
				ImageDescriptor id = fr.getThisIconDescriptor().getIcon16();
				PublishOptions popt = fr.getPublishOptions();
				if (popt.getPublishMethod() != ResourcePublishMethod.LOCAL && popt.getReferencedResource() == null) {
					FieldDecoration fd = FieldDecorationRegistry.getDefault()
							.getFieldDecoration(FieldDecorationRegistry.DEC_ERROR);
					setErrorMessage(Messages.ResourcesPage_0);
					ResourcesPage.this.setPageComplete(false);
					return ResourceManager.decorateImage(id.createImage(), fd.getImage(), ResourceManager.BOTTOM_LEFT);
				}
				if (popt.getPublishMethod() == ResourcePublishMethod.REFERENCE)
					return Activator.getDefault().getImage(
							ResourceManager.decorateImage(id, AMResource.LINK_DECORATOR, ResourceManager.BOTTOM_LEFT));
				return Activator.getDefault().getImage(id);
			}
		});

		viewerColumn = new TableViewerColumn(tableViewer, SWT.NONE);
		column = viewerColumn.getColumn();
		column.setText(Messages.ResourcesPage_table_overwrite);
		column.setWidth(80);
		viewerColumn.setLabelProvider(new TLabelProvider() {

			@Override
			public String getText(Object element) {
				AMResource fr = (AMResource) element;
				OverwriteEnum ovw = fr.getPublishOptions().getOverwrite(OverwriteEnum.IGNORE);
				if (ovw.equals(OverwriteEnum.OVERWRITE))
					return Messages.ResourcesPage_3;
				if (ovw.equals(OverwriteEnum.IGNORE))
					return Messages.ResourcesPage_5;
				if (ovw.equals(OverwriteEnum.ONLY_EXPRESSION))
					return Messages.ResourcesPage_6;
				return ovw.getValue();
			}

		});

		viewerColumn = new TableViewerColumn(tableViewer, SWT.NONE);
		column = viewerColumn.getColumn();
		column.setText(Messages.ResourcesPage_1);
		column.setWidth(100);
		viewerColumn.setLabelProvider(new TLabelProvider() {
			@Override
			public String getText(Object element) {
				AMResource fr = (AMResource) element;
				PublishOptions popt = fr.getPublishOptions();
				OverwriteEnum ovw = popt.getOverwrite(OverwriteEnum.IGNORE);
				if (ovw.equals(OverwriteEnum.IGNORE))
					return "";
				if (popt.getPublishMethod() == ResourcePublishMethod.REWRITEEXPRESSION)
					return popt.getRepoExpression();
				return Misc.nvl(popt.getExpression());
			}

			@Override
			public String getToolTipText(Object element) {
				String txt = getText(element);
				if (Misc.isNullOrEmpty(txt))
					txt = super.getToolTipText(element);
				return txt;
			}
		});

		viewerColumn = new TableViewerColumn(tableViewer, SWT.NONE);
		column = viewerColumn.getColumn();
		column.setText(Messages.ResourcesPage_2);
		column.setWidth(100);
		viewerColumn.setLabelProvider(new TLabelProvider() {

			@Override
			public String getText(Object element) {
				if (element instanceof AFileResource)
					return ((AFileResource) element).getHFFileSize();
				return ""; //$NON-NLS-1$
			}

		});

		viewerColumn = new TableViewerColumn(tableViewer, SWT.NONE);
		column = viewerColumn.getColumn();
		column.setText(Messages.ResourcesPage_4);
		column.setWidth(100);
		viewerColumn.setLabelProvider(new TLabelProvider() {

			@Override
			public String getText(Object element) {
				if (element instanceof AMResource) {
					AMResource mres = (AMResource) element;
					PublishOptions popt = mres.getPublishOptions();
					OverwriteEnum ovw = popt.getOverwrite(OverwriteEnum.IGNORE);
					if (ovw.equals(OverwriteEnum.IGNORE))
						return "";
					if (ovw.equals(OverwriteEnum.ONLY_EXPRESSION))
						return "";

					if (popt.getPublishMethod() == ResourcePublishMethod.RESOURCE)
						return sres.getText();
					if (popt.getPublishMethod() == ResourcePublishMethod.REFERENCE)
						return sresource.getText();
					if (popt.getPublishMethod() == ResourcePublishMethod.LOCAL)
						return slocal.getText();
				}
				return ""; //$NON-NLS-1$
			}

		});

		sresource = new ReferenceResourceAction(tableViewer);
		sres = new ResourceToFolderAction(tableViewer);
		slocal = new SelectLocalAction(tableViewer);

		attachCellEditors(tableViewer, table);

		MenuManager menuMgr = new MenuManager();
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener() {
			private ResourceExpressionAction rexp = new ResourceExpressionAction(tableViewer);

			public void menuAboutToShow(IMenuManager menu) {
				StructuredSelection s = (StructuredSelection) tableViewer.getSelection();
				if (s != null) {
					AMResource mres = (AMResource) s.getFirstElement();
					if (mres != null && mres.getPublishOptions().getOverwrite(OverwriteEnum.OVERWRITE)
							.equals(OverwriteEnum.OVERWRITE)) {
						if (sresource.calculateEnabled(mres))
							menu.add(sresource);
						if (sres.calculateEnabled(mres))
							menu.add(sres);
						if (slocal.calculateEnabled(mres))
							menu.add(slocal);
						if (rexp.calculateEnabled(mres))
							menu.add(rexp);
					}
				}
			}

		});
		Menu menu = menuMgr.createContextMenu(tableViewer.getControl());
		tableViewer.getControl().setMenu(menu);

		// fillData(false);
	}

	private ReferenceResourceAction sresource;
	private ResourceToFolderAction sres;
	private SelectLocalAction slocal;
	private ComboBoxCellEditor cOverwrite;

	private void attachCellEditors(final TableViewer viewer, Composite parent) {
		viewer.setCellModifier(new ICellModifier() {
			public boolean canModify(Object element, String property) {
				AMResource prop = (AMResource) element;
				PublishOptions po = prop.getPublishOptions();
				if (property.equals("VALUE")) //$NON-NLS-1$
					return true;
				if (property.equals("EXPRESSION") //$NON-NLS-1$
						&& po.getjExpression() != null && !po.getOverwrite().equals(OverwriteEnum.IGNORE))
					return true;
				if (property.equals("TYPE") && po.getOverwrite().equals(OverwriteEnum.OVERWRITE)) //$NON-NLS-1$
					return true;
				return false;
			}

			public Object getValue(Object element, String property) {
				AMResource prop = (AMResource) element;
				PublishOptions po = prop.getPublishOptions();
				if ("VALUE".equals(property)) { //$NON-NLS-1$
					if (prop instanceof AFileResource) {
						cOverwrite.setItems(new String[] { Messages.ResourcesPage_3, Messages.ResourcesPage_5,
								Messages.ResourcesPage_6 });
					} else {
						cOverwrite.setItems(new String[] { Messages.ResourcesPage_3, Messages.ResourcesPage_5 });
					}

					OverwriteEnum ovw = po.getOverwrite();
					if (ovw.equals(OverwriteEnum.OVERWRITE))
						return 0;
					else if (ovw.equals(OverwriteEnum.IGNORE))
						return 1;
					else if (ovw.equals(OverwriteEnum.ONLY_EXPRESSION))
						return 2;
					return 1;
				}
				if ("NAME".equals(property)) //$NON-NLS-1$
					return prop.getDisplayText();
				if ("FILESIZE".equals(property)) { //$NON-NLS-1$
					if (prop instanceof AFileResource)
						return ((AFileResource) element).getHFFileSize();
				}
				if (po.getOverwrite(OverwriteEnum.IGNORE).equals(OverwriteEnum.IGNORE))
					return ""; //$NON-NLS-1$
				if ("EXPRESSION".equals(property)) { //$NON-NLS-1$
					JRDesignExpression jd = new JRDesignExpression();
					jd.setText(prop.getPublishOptions().getExpression());
					return jd;
				}
				if ("TYPE".equals(property)) { //$NON-NLS-1$
					if (prop instanceof AFileResource) {
						AFileResource mres = (AFileResource) element;
						if (mres.getPublishOptions().getPublishMethod() == ResourcePublishMethod.RESOURCE)
							return 0;
						if (mres.getPublishOptions().getPublishMethod() == ResourcePublishMethod.REFERENCE)
							return 1;
						if (mres.getPublishOptions().getPublishMethod() == ResourcePublishMethod.LOCAL)
							return 2;
					}
					return 2;
				}
				return ""; //$NON-NLS-1$
			}

			public void modify(Object element, String property, Object value) {
				TableItem tableItem = (TableItem) element;
				AMResource data = (AMResource) tableItem.getData();
				PublishOptions po = data.getPublishOptions();
				if ("VALUE".equals(property)) { //$NON-NLS-1$
					int intValue = ((Integer) value).intValue();
					switch (intValue) {
					case 0:
						po.setOverwrite(OverwriteEnum.OVERWRITE);
						break;
					case 1:
						po.setOverwrite(OverwriteEnum.IGNORE);
						break;
					case 2:
						po.setOverwrite(OverwriteEnum.ONLY_EXPRESSION);
						break;
					}
				} else if ("EXPRESSION".equals(property)) //$NON-NLS-1$
					po.setExpression(value == null ? null : ((JRDesignExpression) value).getText());
				else if ("TYPE".equals(property)) { //$NON-NLS-1$
					if (value instanceof Integer) {
						int intValue = ((Integer) value).intValue();
						switch (intValue) {
						case 0:
							sres.calculateEnabled(data);
							sres.run();
							break;
						case 1:
							sresource.calculateEnabled(data);
							sresource.run();
							break;
						case 2:
							slocal.calculateEnabled(data);
							slocal.run();
							break;
						}
					}
				}
				tableViewer.update(element, new String[] { property });
				tableViewer.refresh();
			}
		});

		JRExpressionCellEditor expEditor = new JRExpressionCellEditor(parent, new ExpressionContext(jConfig));
		cOverwrite = new ComboBoxCellEditor(parent,
				new String[] { Messages.ResourcesPage_3, Messages.ResourcesPage_5, Messages.ResourcesPage_6 },
				SWT.READ_ONLY);
		viewer.setCellEditors(new CellEditor[] { new TextCellEditor(parent), cOverwrite, expEditor,
				new TextCellEditor(parent, SWT.RIGHT), new ComboBoxCellEditor(parent,
						new String[] { sres.getText(), sresource.getText(), slocal.getText() }, SWT.READ_ONLY) });
		viewer.setColumnProperties(new String[] { "NAME", "VALUE", "EXPRESSION", "FILESIZE", "TYPE" }); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
	}

	public void fillData(boolean isNew) {
		List<AMResource> res = PublishUtil.getResources(pres, new NullProgressMonitor(), jConfig);
		String b = jConfig.getProperty(JRSPreferencesPage.PUBLISH_REPORT_OVERRIDEBYDEFAULT, "true");
		if (isNew)
			for (AMResource r : res) {
				if (r instanceof AFileResource)
					continue;
				if (b.equals("overwrite") || b.equals("true"))
					r.getPublishOptions().setOverwrite(OverwriteEnum.OVERWRITE);
				else
					r.getPublishOptions().setOverwrite(OverwriteEnum.IGNORE);
			}
		else {
			if (pres instanceof MReportUnit && !pres.getValue().getIsNew()) {
				for (ResourceDescriptor n : pres.getValue().getChildren()) {
					if (n.getWsType() == null)
						continue;
					if (n.getWsType().equals(ResourceDescriptor.TYPE_INPUT_CONTROL)) {
						String icname = n.getName();
						for (AMResource r : res) {
							if (r instanceof MInputControl && r.getValue().getName().equals(icname)
									&& !r.getValue().getIsNew()) {
								r.getPublishOptions().setOverwrite(OverwriteEnum.IGNORE);
								break;
							}
						}
					}
				}
				for (AMResource r : res) {
					if (b.equals("overwrite"))
						r.getPublishOptions().setOverwrite(OverwriteEnum.OVERWRITE);
					else if (b.equals("ignore"))
						r.getPublishOptions().setOverwrite(OverwriteEnum.IGNORE);
					else if (!r.getValue().getIsNew())
						r.getPublishOptions().setOverwrite(OverwriteEnum.IGNORE);
					
				}
				// let's look and make a diff
			}
		}
		tableViewer.setInput(res);
		tableViewer.refresh();
	}

	abstract class TLabelProvider extends ColumnLabelProvider {

		@Override
		public String getToolTipText(Object element) {
			String tt = ""; //$NON-NLS-1$
			AMResource mres = (AMResource) element;
			tt += "ID: " + mres.getValue().getName(); //$NON-NLS-1$
			tt += "\nLabel: " + mres.getValue().getLabel(); //$NON-NLS-1$

			if (mres.getPublishOptions().getPublishMethod() == ResourcePublishMethod.LOCAL)
				tt += "\nURI: " + mres.getValue().getUriString(); //$NON-NLS-1$
			else if (mres.getPublishOptions().getPublishMethod() == ResourcePublishMethod.REFERENCE) {
				tt += "\nURI: " + mres.getValue().getUriString(); //$NON-NLS-1$
				if (mres.getPublishOptions().getReferencedResource() != null)
					tt += "\nReference To: " //$NON-NLS-1$
							+ mres.getPublishOptions().getReferencedResource().getUriString();
			} else if (mres.getPublishOptions().getPublishMethod() == ResourcePublishMethod.RESOURCE) {
				if (mres.getPublishOptions().getReferencedResource() != null)
					tt += "\nURI: " //$NON-NLS-1$
							+ mres.getPublishOptions().getReferencedResource().getUriString();
			} else if (mres.getPublishOptions().getPublishMethod() == ResourcePublishMethod.REWRITEEXPRESSION)
				if (mres.getPublishOptions().getReferencedResource() != null)
					tt += "\nURI: " //$NON-NLS-1$
							+ mres.getPublishOptions().getReferencedResource().getUriString();

			if (element instanceof AFileResource && ((AFileResource) element).getFile() != null)
				tt += "\nFile: " //$NON-NLS-1$
						+ ((AFileResource) element).getFile().getAbsolutePath();
			return tt;
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
