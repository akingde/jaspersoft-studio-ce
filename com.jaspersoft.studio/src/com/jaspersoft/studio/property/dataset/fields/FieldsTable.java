/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.dataset.fields;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.gef.dnd.TemplateTransfer;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.window.ToolTip;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.part.PluginTransfer;

import com.jaspersoft.studio.dnd.NodeDragListener;
import com.jaspersoft.studio.dnd.NodeTableDropAdapter;
import com.jaspersoft.studio.dnd.NodeTransfer;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.dataset.MDataset;
import com.jaspersoft.studio.model.field.MField;
import com.jaspersoft.studio.property.dataset.dialog.AbstractModifyTable;
import com.jaspersoft.studio.property.dataset.fields.table.TColumn;
import com.jaspersoft.studio.property.dataset.fields.table.TColumnFactory;
import com.jaspersoft.studio.property.descriptor.propexpr.dialog.HintsPropertiesList;
import com.jaspersoft.studio.swt.widgets.table.DeleteButton;
import com.jaspersoft.studio.swt.widgets.table.EditButton;
import com.jaspersoft.studio.swt.widgets.table.IEditElement;
import com.jaspersoft.studio.swt.widgets.table.INewElement;
import com.jaspersoft.studio.swt.widgets.table.ListContentProvider;
import com.jaspersoft.studio.swt.widgets.table.ListOrderButtons;
import com.jaspersoft.studio.swt.widgets.table.NewButton;
import com.jaspersoft.studio.utils.ModelUtils;
import com.jaspersoft.studio.utils.UIUtil;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.JRPropertiesMap;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignField;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.properties.PropertiesMetadataUtil;
import net.sf.jasperreports.properties.PropertyMetadata;

public class FieldsTable extends AbstractModifyTable {
	private TableViewer tviewer;;
	private Composite composite;
	private JRDesignDataset dataset;
	private Color background;
	private MDataset mdataset;

	public FieldsTable(Composite parent, JRDesignDataset dataset, Color background, MDataset mdataset) {
		this.mdataset = mdataset;
		this.dataset = dataset;
		this.background = background;
		createControl(parent);
		if (dataset.getQuery() != null)
			((JRDesignQuery) dataset.getQuery()).getEventSupport().addPropertyChangeListener(
					JRDesignQuery.PROPERTY_LANGUAGE, evt -> refreshFields((String) evt.getNewValue()));

	}

	public Composite getControl() {
		return composite;
	}

	private List<TableViewerColumn> columns = new ArrayList<>();

	private void createControl(Composite parent) {
		composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		composite.setBackground(background);
		composite.setBackgroundMode(SWT.INHERIT_FORCE);

		wtable = new Table(composite, SWT.V_SCROLL | SWT.MULTI | SWT.FULL_SELECTION | SWT.BORDER);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.widthHint = 100;
		wtable.setLayoutData(gd);
		wtable.setHeaderVisible(true);
		wtable.setLinesVisible(true);

		tviewer = new TableViewer(wtable);
		tviewer.setContentProvider(new ListContentProvider());
		UIUtil.setViewerCellEditingOnDblClick(tviewer);
		ColumnViewerToolTipSupport.enableFor(tviewer, ToolTip.NO_RECREATE);
		addDropSupport();

		refreshFields(dataset.getQuery() == null ? "" : dataset.getQuery().getLanguage());

		Composite bGroup = new Composite(composite, SWT.NONE);
		bGroup.setLayout(new GridLayout(1, false));
		bGroup.setLayoutData(new GridData(GridData.FILL_VERTICAL));

		new NewButton() {
			@Override
			protected void afterElementAdded(Object selement) {
				try {
					dataset.addField((JRField) selement);
					fireModifyListeners();
				} catch (JRException e) {
					e.printStackTrace();
				}
			}
		}.createNewButtons(bGroup, tviewer, new INewElement() {

			public Object newElement(List<?> input, int pos) {
				JRDesignField f = new JRDesignField();
				f.setName(getName());
				f.setValueClass(String.class);
				return f;
			}

			private String getName() {
				List<JRDesignField> list = (List<JRDesignField>) tviewer.getInput();
				String name = "Field"; //$NON-NLS-1$
				boolean match = false;
				String tmp = name;
				for (int i = 1; i < 100000; i++) {
					tmp = ModelUtils.getNameFormat(name, i);

					for (JRDesignField f : list) {
						match = f.getName().equals(tmp);
						if (match)
							break;
					}
					if (!match)
						break;
				}
				return tmp;
			}
		});

		EditButton<JRDesignField> eb = new EditButton<>();
		eb.createEditButtons(bGroup, tviewer, new IEditElement<JRDesignField>() {

			@Override
			public void editElement(List<JRDesignField> input, int pos) {
				JRDesignField oldF = input.get(pos);
				JasperReportsConfiguration jConf = mdataset.getJasperConfiguration();
				jConf.put(HintsPropertiesList.COM_JASPERSOFT_STUDIO_DATASET_LANGUAGE,
						dataset.getQuery() != null ? dataset.getQuery().getLanguage() : null);
				PropertiesDialog<JRDesignField> d = new PropertiesDialog<>(tviewer.getTable().getShell(),
						(JRDesignField) oldF.clone(), tcolumns, MField.getIconDescriptor().getDescription(), jConf);
				if (d.open() == Dialog.OK) {
					dataset.removeField(oldF);
					try {
						dataset.addField(d.getElement());
						input.set(pos, d.getElement());
					} catch (JRException e) {
						UIUtils.showError(e);
					}
				}
				jConf.remove(HintsPropertiesList.COM_JASPERSOFT_STUDIO_DATASET_LANGUAGE);
			}
		});
		eb.editOnDoubleClick();

		new DeleteButton() {
			@Override
			protected void afterElementDeleted(Object element) {
				if (element != null) {
					dataset.removeField(((JRDesignField) element).getName());
					fireModifyListeners();
				}
			}
		}.createDeleteButton(bGroup, tviewer);

		new ListOrderButtons().createOrderButtons(bGroup, tviewer);

		List<JRField> fields = dataset.getFieldsList();
		if (fields == null)
			fields = new ArrayList<>();
		setFields(fields);
	}

	private void createPropertiesColumn() {
		TColumn c = new TColumn();
		c.setPropertyName("properties");
		c.setLabel(Messages.common_properties);
		c.setPropertyType(JRPropertiesMap.class.getName());
		c.setType("properties");
		c.setValue(mdataset);
		columns.add(TColumnFactory.addColumn(c, tviewer));
		tcolumns.add(c);
	}

	private void createDescriptionColumn() {
		TColumn c = new TColumn();
		c.setPropertyName("description");
		c.setLabel(Messages.common_descriptionLabel);
		columns.add(TColumnFactory.addColumn(c, tviewer));
		tcolumns.add(c);
	}

	private void createTypeColumn() {
		TColumn c = new TColumn();
		c.setPropertyName("valueClassName");
		c.setLabel(Messages.common_classTypeLabel);
		c.setPropertyType(Class.class.getName());
		columns.add(TColumnFactory.addColumn(c, tviewer));
		tcolumns.add(c);
	}

	private void createNameColumn() {
		TColumn c = new TColumn();
		c.setPropertyName("name");
		c.setLabel(Messages.common_fieldNameLabel);
		c.setValue(dataset);
		columns.add(TColumnFactory.addColumn(c, tviewer));
		tcolumns.add(c);
	}

	private void addDropSupport() {
		int ops = DND.DROP_COPY | DND.DROP_MOVE;
		Transfer[] transfers = new Transfer[] { TemplateTransfer.getInstance(), NodeTransfer.getInstance(),
				PluginTransfer.getInstance() };
		tviewer.addDragSupport(ops, transfers, new NodeDragListener(tviewer));

		transfers = new Transfer[] { NodeTransfer.getInstance() };
		NodeTableDropAdapter dropAdapter = new NodeTableDropAdapter(tviewer) {
			@Override
			public boolean performDrop(Object data) {
				if (data instanceof ANode[]) {
					ANode[] nodes = (ANode[]) data;
					List<JRField> fields = (List<JRField>) tviewer.getInput();
					for (ANode n : nodes) {
						JRDesignField f = (JRDesignField) n.getAdapter(JRDesignField.class);
						if (f != null) {
							// be sure that the name is ok
							f.setName(
									ModelUtils.getNameForField((List<JRDesignField>) tviewer.getInput(), f.getName()));
							fields.add(f);
						}
					}
					setFields(fields);
					return true;
				}
				return false;
			}
		};
		tviewer.addDropSupport(ops, transfers, dropAdapter);
	}

	public <T extends JRField> void setFields(List<T> fields) {
		fields = new ArrayList<>(fields);
		Map<String, JRField> oldFieldsMap = new HashMap<>();
		for (JRField f : dataset.getFields())
			oldFieldsMap.put(f.getName(), f);

		for (JRField f : dataset.getFields())
			dataset.removeField(f);

		List<JRField> newfields = new ArrayList<>();
		for (JRField f : fields)
			try {
				JRField oldField = oldFieldsMap.get(f.getName());
				if (oldField != null) {
					// merging properties, priority is for new properties. Ex.
					// field type or description, mapping is changed in
					// the database
					JRPropertiesMap oldProperties = oldField.getPropertiesMap();
					JRPropertiesMap newProperties = f.getPropertiesMap();
					for (String property : oldProperties.getOwnPropertyNames())
						if (Misc.isNullOrEmpty(newProperties.getProperty(property)))
							newProperties.setProperty(property, oldProperties.getProperty(property));
					if (Misc.isNullOrEmpty(f.getDescription()))
						f.setDescription(oldField.getDescription());
				}
				dataset.addField(f);
				newfields.add(f);
			} catch (JRException e) {
				e.printStackTrace();
			}

		tviewer.setInput(newfields);
		tviewer.refresh();
	}

	public List<JRDesignField> getFields() {
		return (List<JRDesignField>) tviewer.getInput();
	}

	private List<TColumn> tcolumns = new ArrayList<>();

	private void refreshFields(String newLang) {
		// ok depending on language we should show different fields
		for (TableViewerColumn tvc : columns)
			tvc.getColumn().dispose();
		columns.clear();
		tviewer.setColumnProperties(new String[0]);
		tviewer.setCellEditors(new CellEditor[0]);
		tviewer.getTable().setLayout(new TableLayout());
		tcolumns.clear();

		createNameColumn();
		createTypeColumn();
		createDescriptionColumn();

		PropertiesMetadataUtil pmu = PropertiesMetadataUtil.getInstance(mdataset.getJasperConfiguration());
		ClassLoader oldCl = Thread.currentThread().getContextClassLoader();
		try {
			Thread.currentThread().setContextClassLoader(JRLoader.class.getClassLoader());
			List<PropertyMetadata> props = pmu.getQueryExecuterFieldProperties(newLang);
			for (PropertyMetadata pm : props) {
				TColumn c = TColumnFactory.getTColumn(pm);
				c.setValue(dataset);
				columns.add(TColumnFactory.addColumn(c, tviewer));
				tcolumns.add(c);
			}
		} catch (JRException e) {
			e.printStackTrace();
		} finally {
			Thread.currentThread().setContextClassLoader(oldCl);
		}

		// ok, here we should read a config file, extension points?
		createPropertiesColumn();
		tviewer.getTable().layout(true);
		tviewer.refresh();
	}

}
