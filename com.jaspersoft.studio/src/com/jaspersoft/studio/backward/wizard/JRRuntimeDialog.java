/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.backward.wizard;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.backward.JRBackwardManager;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.swt.widgets.table.DeleteButton;
import com.jaspersoft.studio.swt.widgets.table.EditButton;
import com.jaspersoft.studio.swt.widgets.table.IEditElement;

import net.sf.jasperreports.eclipse.builder.CompatibilityManager;
import net.sf.jasperreports.eclipse.builder.JRDefinition;
import net.sf.jasperreports.eclipse.ui.ATitledDialog;
import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.engine.design.JasperDesign;

public class JRRuntimeDialog extends ATitledDialog {
	private TableViewer viewer;

	public JRRuntimeDialog(Shell parentShell) {
		super(parentShell);
		setTitle(Messages.ShowInstallationsWizardPage_pageTitle);
		setDescription(Messages.ShowInstallationsWizardPage_pageDescription);
		setDefaultSize(700, 450);
	}

	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
	}

	@Override
	protected Control createDialogArea(final Composite parent) {
		Composite cmp = (Composite) super.createDialogArea(parent);
		cmp.setLayout(new GridLayout(2, false));

		viewer = new TableViewer(cmp, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
		viewer.setContentProvider(ArrayContentProvider.getInstance());
		viewer.setComparator(new ViewerComparator() {
			public int compare(Viewer viewer, Object e1, Object e2) {
				JRDefinition t1 = (JRDefinition) e1;
				JRDefinition t2 = (JRDefinition) e2;
				return -t1.getVersion().compareTo(t2.getVersion());
			};
		});

		TableViewerColumn colFirstName = new TableViewerColumn(viewer, SWT.NONE);
		colFirstName.getColumn().setWidth(100);
		colFirstName.getColumn().setText(Messages.JRVersionPage_1);
		colFirstName.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return ((JRDefinition) element).getVersion();
			}
		});

		colFirstName = new TableViewerColumn(viewer, SWT.NONE);
		colFirstName.getColumn().setWidth(1000);
		colFirstName.getColumn().setText(Messages.JRVersionPage_2);
		colFirstName.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return ((JRDefinition) element).getResourceURL();
			}
		});

		// make lines and header visible
		final Table table = viewer.getTable();
		table.setHeaderVisible(true);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.widthHint = 300;
		gd.heightHint = 300;
		table.setLayoutData(gd);

		Composite c = new Composite(cmp, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		c.setLayout(layout);
		gd = new GridData(GridData.VERTICAL_ALIGN_BEGINNING);
		gd.widthHint = 140;
		c.setLayoutData(gd);

		Button bUrl = new Button(c, SWT.PUSH);
		bUrl.setText(Messages.JRVersionPage_3);
		bUrl.setLayoutData(new GridData(GridData.FILL_HORIZONTAL | GridData.VERTICAL_ALIGN_BEGINNING));
		bUrl.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				JRDefinition value = new JRDefinition("", ""); //$NON-NLS-1$ //$NON-NLS-2$
				WizardDialog d = new WizardDialog(UIUtils.getShell(), new JRRuntimeURLWizard(value)) {
					@Override
					protected void createButtonsForButtonBar(Composite parent) {
						super.createButtonsForButtonBar(parent);
						getButton(IDialogConstants.FINISH_ID).setText(Messages.JRRuntimeDialog_2);
					}
				};
				d.setPageSize(800, 40);
				if (d.open() == Dialog.OK) {
					setVersion(value);
					viewer.refresh();
					viewer.setSelection(new StructuredSelection(value));
					viewer.reveal(value);
					save();
				}
			}
		});

		Button bPath = new Button(c, SWT.PUSH);
		bPath.setText(Messages.JRVersionPage_4);
		bPath.setLayoutData(new GridData(GridData.FILL_HORIZONTAL | GridData.VERTICAL_ALIGN_BEGINNING));
		bPath.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				JRVersionPathDialog d = new JRVersionPathDialog(parent.getShell(), new JRDefinition("", "")); //$NON-NLS-1$ //$NON-NLS-2$
				if (d.open() == Dialog.OK) {
					setVersion(d.getValue());
					viewer.refresh();
					viewer.setSelection(new StructuredSelection(d.getValue()));
					viewer.reveal(d.getValue());
					save();
				}
			}
		});

		EditButton<JRDefinition> eb = new EditButton<JRDefinition>() {
			public void setEnabled(boolean enable) {
				StructuredSelection s = (StructuredSelection) viewer.getSelection();
				if (s.isEmpty())
					enable = false;
				else {
					String rurl = ((JRDefinition) s.getFirstElement()).getResourceURL();
					if (enable && (rurl.equals(Messages.JRVersionPage_5)
							|| rurl.startsWith(JRBackwardManager.storage.getAbsolutePath() + "/"))) //$NON-NLS-1$
						enable = false;
				}
				super.setEnabled(enable);
			};

			protected void afterElementModified(Object element, java.util.List<JRDefinition> inlist, int ind) {
				save();
			}
		};
		eb.createEditButtons(c, viewer, new IEditElement<JRDefinition>() {

			@Override
			public void editElement(List<JRDefinition> input, int pos) {
				JRVersionPathDialog d = new JRVersionPathDialog(parent.getShell(), input.get(pos));
				if (d.open() == Dialog.OK)
					setVersion(d.getValue());
			}
		});
		eb.editOnDoubleClick();

		new DeleteButton() {
			@Override
			protected boolean canRemove(Object obj) {
				if (((JRDefinition) obj).getResourceURL().equals(Messages.JRVersionPage_5))
					return false;
				return super.canRemove(obj);
			}

			protected void afterElementDeleted(Object element) {
				JRDefinition d = (JRDefinition) element;
				if (d.getResourceURL().startsWith(JRBackwardManager.storage.getAbsolutePath() + "/")) { //$NON-NLS-1$
					File f = new File(d.getResourceURL());
					if (f.exists())
						try {
							FileUtils.deleteDirectory(f);
						} catch (IOException e) {
							UIUtils.showError(e);
						}
				}
				save();
			}
		}.createDeleteButton(c, viewer);
		fill();
		return cmp;
	}

	private void setVersion(JRDefinition d) {
		@SuppressWarnings("unchecked")
		List<JRDefinition> vals = (List<JRDefinition>) viewer.getInput();
		for (int i = 0; i < vals.size(); i++) {
			if (vals.get(i).getVersion().equals(d.getVersion())) {
				vals.set(i, d);
				return;
			}
		}
		vals.add(d);
	}

	private void fill() {
		List<JRDefinition> values = new ArrayList<JRDefinition>();
		values.add(new JRDefinition(Messages.JRVersionPage_5, JasperDesign.class.getPackage().getImplementationVersion()));
		values.addAll(getJRDefinitions());
		viewer.setInput(values);
	}

	public static List<JRDefinition> getJRDefinitions() {
		List<JRDefinition> list = new ArrayList<JRDefinition>();
		IPreferenceStore pstore = JaspersoftStudioPlugin.getInstance().getPreferenceStore();
		String versions = pstore.getString(CompatibilityManager.PROP_JR_VERSIONS);
		if (!Misc.isNullOrEmpty(versions)) {
			ObjectMapper mapper = new ObjectMapper();
			try {
				list = mapper.readValue(versions, new TypeReference<List<JRDefinition>>() {
				});
			} catch (JsonParseException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	public List<JRDefinition> getVersions() {
		List<JRDefinition> list = new ArrayList<JRDefinition>();
		for (int i = 0; i < ((List) viewer.getInput()).size(); i++) {
			JRDefinition d = (JRDefinition) viewer.getElementAt(i);
			if (d.getResourceURL().equals(Messages.JRVersionPage_5))
				continue;
			list.add(d);
		}
		return list;
	}

	private void save() {
		IPreferenceStore pstore = JaspersoftStudioPlugin.getInstance().getPreferenceStore();
		try {
			String v = new ObjectMapper().writeValueAsString(getVersions());
			pstore.setValue(CompatibilityManager.PROP_JR_VERSIONS, v);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
