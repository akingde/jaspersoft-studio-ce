/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.gef.decorator.csv;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.swt.widgets.table.DeleteButton;
import com.jaspersoft.studio.swt.widgets.table.INewElement;
import com.jaspersoft.studio.swt.widgets.table.ListOrderButtons;
import com.jaspersoft.studio.swt.widgets.table.NewButton;
import com.jaspersoft.studio.utils.ModelUtils;

import net.sf.jasperreports.eclipse.ui.util.PersistentLocationDialog;
import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.engine.JRPropertiesMap;
import net.sf.jasperreports.engine.JRTextElement;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRCsvMetadataExporter;
import net.sf.jasperreports.export.CsvMetadataReportConfiguration;

/**
 * A dialog that allows to specify the column names that should be
 * used for CSV metadata exporting.
 * 
 * PLEASE NOTE: we can not play with their order. See Community Bug #5136.
 * 
 * @author Orlandin Marco
 * 
 */
public class ColumnNamesSettingDialog extends PersistentLocationDialog {

	/**
	 * A textual representation of the column names
	 */
	private List<String> columnNames;
	private JasperDesign jd;

	/**
	 * Build the dialog
	 * 
	 * @param parentShell
	 * @param columnNames
	 *          list of the column names, comma separated
	 */
	public ColumnNamesSettingDialog(Shell parentShell, JasperDesign jd) {
		super(parentShell);
		this.jd = jd;
		String[] cols = new String[0];
		JRPropertiesMap map = jd.getPropertiesMap();
		if (map != null) {
			String names = map.getProperty(CsvMetadataReportConfiguration.PROPERTY_COLUMN_NAMES_PREFIX);
			if (!Misc.isNullOrEmpty(names))
				cols = names.split(","); //$NON-NLS-1$
		}
		columnNames = new ArrayList<String>(Arrays.asList(cols));
	}

	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText(getDialogTitle());
	}

	/**
	 * Create contents of the dialog.
	 * 
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite area = (Composite) super.createDialogArea(parent);
		Composite container = new Composite(area, SWT.NONE);
		container.setLayout(new GridLayout(2, false));
		container.setLayoutData(new GridData(GridData.FILL_BOTH));

		TableViewer viewer = new TableViewer(container, SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION
				| SWT.BORDER);
		// create the columns
		// not yet implemented
		createColumns(viewer);

		// make lines and header visible
		final Table table = viewer.getTable();
		table.setHeaderVisible(false);
		table.setLinesVisible(true);

		viewer.setContentProvider(ArrayContentProvider.getInstance());
		viewer.setInput(columnNames);

		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.verticalSpan = 5;
		gd.grabExcessHorizontalSpace = true;
		table.setLayoutData(gd);

		Composite cmp = new Composite(container, SWT.NONE);
		cmp.setLayout(new GridLayout());
		gd = new GridData(GridData.VERTICAL_ALIGN_BEGINNING);
		gd.widthHint = 80;
		cmp.setLayoutData(gd);

		new NewButton().createNewButtons(cmp, viewer, new INewElement() {

			@Override
			public Object newElement(List<?> input, int pos) {
				if (tels == null) {
					tels = new ArrayList<JRDesignElement>();
					List<JRDesignElement> els = ModelUtils.getAllGElements(jd);
					for (JRDesignElement de : els)
						if (de instanceof JRTextElement && de.getPropertiesMap() != null
								&& de.getPropertiesMap().containsProperty(JRCsvMetadataExporter.PROPERTY_COLUMN_NAME))
							tels.add(de);
				}
				List<String> newNames = new ArrayList<String>();
				for (JRDesignElement te : tels) {
					JRPropertiesMap map = te.getPropertiesMap();
					if (map != null) {
						String name = map.getProperty(JRCsvMetadataExporter.PROPERTY_COLUMN_NAME);
						if (name != null) {
							boolean exists = false;
							for (String key : columnNames) {
								if (name.equals(key)) {
									exists = true;
									break;
								}
							}
							if (!exists)
								newNames.add(name);
						}
					}
				}
				if (!newNames.isEmpty()) {
					NameComboDialog dialog = new NameComboDialog(UIUtils.getShell(), newNames);
					if (dialog.open() == Dialog.OK)
						return dialog.getName();
				} else
					UIUtils.showWarning(Messages.ColumnNamesSettingDialog_1);
				return null;
			}
		});
		new ListOrderButtons().createOrderButtons(cmp, viewer);
		new DeleteButton().createDeleteButton(cmp, viewer);

		return area;
	}

	private List<JRDesignElement> tels;

	private void createColumns(TableViewer viewer) {
		TableViewerColumn colFirstName = new TableViewerColumn(viewer, SWT.NONE);
		colFirstName.getColumn().setWidth(200);
		colFirstName.getColumn().setText(Messages.ColumnNamesSettingDialog_2);
		colFirstName.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return (String) element;
			}
		});
	}

	/**
	 * return the specified column names
	 * 
	 * @return In this string there are the sequence of column names, comma separated
	 */
	public String getColumnNames() {
		String r = ""; //$NON-NLS-1$
		String sep = ""; //$NON-NLS-1$
		for (String item : columnNames) {
			r += sep + item;
			sep = ","; //$NON-NLS-1$
		}

		return r;
	}

	/**
	 * @return the title for the dialog
	 */
	protected String getDialogTitle() {
		return Messages.ColumnNamesSettingDialog_Title;
	}
}
