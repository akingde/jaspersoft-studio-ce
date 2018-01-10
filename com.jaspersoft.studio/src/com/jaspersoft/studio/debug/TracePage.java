/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.debug;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CheckboxCellEditor;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.part.Page;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.editor.JrxmlEditor;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.MReport;
import com.jaspersoft.studio.model.MRoot;
import com.jaspersoft.studio.model.dataset.MDataset;
import com.jaspersoft.studio.model.field.MField;
import com.jaspersoft.studio.model.field.MFields;
import com.jaspersoft.studio.model.parameter.MParameterSystem;
import com.jaspersoft.studio.model.parameter.MParameters;
import com.jaspersoft.studio.model.variable.MVariableSystem;
import com.jaspersoft.studio.model.variable.MVariables;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.descriptor.checkbox.CheckBoxLabelProvider;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.JRGroup;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JRVariable;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignField;
import net.sf.jasperreports.engine.design.JRDesignParameter;
import net.sf.jasperreports.engine.design.JRDesignVariable;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.design.events.CollectionElementAddedEvent;
import net.sf.jasperreports.engine.design.events.CollectionElementRemovedEvent;

public class TracePage extends Page {

	private JrxmlEditor editor;

	public TracePage(JrxmlEditor editor) {
		this.editor = editor;
	}

	private TreeViewer treeViewer;
	private Tree tree;
	private Combo cmb;

	/*
	 * (non-Javadoc) Method declared on IPage.
	 */
	public void createControl(Composite parent) {
		cmp = new Composite(parent, SWT.NONE);
		cmp.setLayout(new GridLayout(2, false));

		Label lbl = new Label(cmp, SWT.NONE);
		lbl.setText("Data Sets");

		cmb = new Combo(cmp, SWT.READ_ONLY | SWT.BORDER);
		cmb.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		cmb.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setupDatasets();
				treeViewer.refresh(true);
				treeViewer.expandAll();
			}
		});

		tree = new Tree(cmp, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		tree.setHeaderVisible(true);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.horizontalSpan = 2;
		tree.setLayoutData(gd);

		treeViewer = new TreeViewer(tree);

		TreeColumn column1 = new TreeColumn(tree, SWT.LEFT);
		tree.setLinesVisible(true);
		column1.setAlignment(SWT.LEFT);
		column1.setText("Report Objects");
		column1.setWidth(160);

		treeViewer.setContentProvider(new TreeContentProvider());
		treeViewer.setLabelProvider(new TableLabelProvider());
		ColumnViewerToolTipSupport.enableFor(treeViewer);

		treeViewer.setCellModifier(new ICellModifier() {
			public boolean canModify(Object element, String property) {
				return !property.isEmpty()
						&& (element instanceof MField || element instanceof MVariableSystem || element instanceof MParameterSystem);
			}

			public Object getValue(Object element, String property) {
				JasperReportsConfiguration jrConfig = editor.getJrContext();
				String type = null;
				String name = null;
				if (element instanceof MField) {
					type = TraceGovernor.TYPE_FIELD;
					name = ((MField) element).getValue().getName();
				} else if (element instanceof MVariableSystem) {
					type = TraceGovernor.TYPE_VARIABLE;
					name = ((MVariableSystem) element).getValue().getName();
				} else if (element instanceof MParameterSystem) {
					type = TraceGovernor.TYPE_PARAMETER;
					name = ((MParameterSystem) element).getValue().getName();
				} else
					return null;
				return jrConfig.getPropertyBoolean(TraceGovernor.PREFIX + type + dataset.getName() + "." + property + name,
						false);
			}

			public void modify(Object element, String property, Object value) {
				Object e = element;
				if (element instanceof TreeItem)
					e = ((TreeItem) element).getData();

				JasperReportsConfiguration jrConfig = editor.getJrContext();
				String type = null;
				String name = null;
				if (e instanceof MField) {
					type = TraceGovernor.TYPE_FIELD;
					name = ((MField) e).getValue().getName();
				} else if (e instanceof MVariableSystem) {
					type = TraceGovernor.TYPE_VARIABLE;
					name = ((MVariableSystem) e).getValue().getName();
				} else if (e instanceof MParameterSystem) {
					type = TraceGovernor.TYPE_PARAMETER;
					name = ((MParameterSystem) e).getValue().getName();
				} else
					return;
				if (value instanceof Boolean) {
					Boolean b = (Boolean) value;
					jrConfig.setProperty(TraceGovernor.PREFIX + type + dataset.getName() + "." + property + name, b.toString());

					treeViewer.update(element, new String[] { property });
					treeViewer.refresh();
				}
			}
		});

		setupData();
	}

	private void createColumns() {
		for (TreeColumn c : cols)
			c.dispose();

		createColumn("Before Report Init", TraceGovernor.BEFORE_REPORT_INIT);
		createColumn("After Report Init", TraceGovernor.AFTER_REPORT_INIT);

		createColumn("Before Page Init", TraceGovernor.BEFORE_PAGE_INIT);
		createColumn("After Page Init", TraceGovernor.AFTER_PAGE_INIT);

		createColumn("Before Column Init", TraceGovernor.BEFORE_COLUMN_INIT);
		createColumn("After Column Init", TraceGovernor.AFTER_COLUMN_INIT);

		for (JRGroup gr : dataset.getGroupsList()) {
			String n = gr.getName();
			createColumn("Before Group [" + n + "] Init", TraceGovernor.BEFORE_GROUP_INIT + n + ".");
			createColumn("After Group [" + n + "] Init", TraceGovernor.AFTER_GROUP_INIT + n + ".");
		}

		createColumn("Before Detail Eval", TraceGovernor.BEFORE_DETAIL_EVAL);
		createColumn("After Detail Eval", TraceGovernor.AFTER_DETAIL_EVAL);

		CellEditor[] editors = new CellEditor[tree.getColumnCount()];
		String[] props = new String[tree.getColumnCount()];
		for (int i = 0; i < tree.getColumns().length; i++) {
			if (i == 0)
				editors[i] = new TextCellEditor(cmp);
			else
				editors[i] = new CheckboxCellEditor(cmp);
			props[i] = Misc.nvl(tree.getColumn(i).getData("COLNAME"), "");
		}
		treeViewer.setCellEditors(editors);
		treeViewer.setColumnProperties(props);
	}

	private List<TreeColumn> cols = new ArrayList<TreeColumn>();

	private void createColumn(String label, String property) {
		// TreeViewerColumn tvc = new TreeViewerColumn(treeViewer, SWT.RIGHT);
		// tvc.setLabelProvider(new StyledCellLabelProvider() {
		// @Override
		// public void update(ViewerCell cell) {
		// super.update(cell);
		// }
		//
		// @Override
		// public String getToolTipText(Object element) {
		// return "aha";
		// }
		// });

		TreeColumn c = new TreeColumn(tree, SWT.RIGHT);
		c.setAlignment(SWT.LEFT);
		c.setText(label);
		c.setWidth(100);
		c.setData("COLNAME", property);

		cols.add(c);
	}

	private void setupData() {
		UIUtils.getDisplay().asyncExec(new Runnable() {

			@Override
			public void run() {
				setupDatasets();
				if (editor != null) {
					treeViewer.setInput(editor.getModel());
					treeViewer.expandAll();
				}
			}
		});
	}

	private void setupDatasets() {
		String sel = null;
		if (cmb.getSelectionIndex() >= 0)
			sel = cmb.getItem(cmb.getSelectionIndex());
		List<String> datasets = new ArrayList<String>();
		datasets.add("Main Data Set");
		if (editor != null && editor.getJrContext() != null && editor.getJrContext().getJasperDesign() != null) {
			JasperDesign jd = editor.getJrContext().getJasperDesign();
			for (JRDataset ds : jd.getDatasetsList()) {
				datasets.add(ds.getName());
				JRDesignDataset jrds = (JRDesignDataset) ds;
				jrds.getEventSupport().removePropertyChangeListener(jrdsListener);
				jrds.getEventSupport().addPropertyChangeListener(jrdsListener);

				for (JRField f : ds.getFields()) {
					((JRDesignField) f).getEventSupport().removePropertyChangeListener(jrdsObjListener);
					((JRDesignField) f).getEventSupport().addPropertyChangeListener(jrdsObjListener);
				}
				for (JRVariable f : ds.getVariables()) {
					((JRDesignVariable) f).getEventSupport().removePropertyChangeListener(jrdsObjListener);
					((JRDesignVariable) f).getEventSupport().addPropertyChangeListener(jrdsObjListener);
				}
				for (JRParameter f : ds.getParameters()) {
					((JRDesignParameter) f).getEventSupport().removePropertyChangeListener(jrdsObjListener);
					((JRDesignParameter) f).getEventSupport().addPropertyChangeListener(jrdsObjListener);
				}
			}
			cmb.setItems(datasets.toArray(new String[datasets.size()]));
			jd.getEventSupport().removePropertyChangeListener(dsListener);
			jd.getEventSupport().addPropertyChangeListener(dsListener);

			// setup selection
			int indx = 0;
			if (sel != null)
				for (int i = 0; i < cmb.getItemCount(); i++) {
					if (cmb.getItem(i).equals(sel)) {
						indx = i;
						break;
					}
				}
			cmb.select(indx);
			setDataset();
			createColumns();
		}
	}

	private JRDesignDataset dataset;

	private void setDataset() {
		int indx = cmb.getSelectionIndex();
		JasperDesign jd = editor.getJrContext().getJasperDesign();
		if (indx > 0) {
			String dsname = cmb.getItem(indx);
			for (JRDataset ds : jd.getDatasetsList()) {
				if (ds.getName().equals(dsname)) {
					dataset = (JRDesignDataset) ds;
					break;
				}
			}
		} else
			dataset = jd.getMainDesignDataset();
	}

	private PropertyChangeListener jrdsObjListener = new PropertyChangeListener() {

		@Override
		public void propertyChange(PropertyChangeEvent arg0) {
			JasperReportsConfiguration jrContext = editor.getJrContext();
			JRDataset ds = getDataset(jrContext.getJasperDesign(), arg0.getSource());
			if (ds == null)
				return;

			String key = TraceGovernor.PREFIX + TraceGovernor.TYPE_FIELD;
			if (arg0.getSource() instanceof JRField)
				key = TraceGovernor.PREFIX + TraceGovernor.TYPE_FIELD;
			else if (arg0.getSource() instanceof JRVariable)
				key = TraceGovernor.PREFIX + TraceGovernor.TYPE_VARIABLE;
			else if (arg0.getSource() instanceof JRParameter)
				key = TraceGovernor.PREFIX + TraceGovernor.TYPE_PARAMETER;

			// let's look to which dataset it belongs

			String pname = arg0.getPropertyName();
			if (pname.equals(JRDesignDataset.PROPERTY_NAME))
				renameDSObject(jrContext, key, (String) arg0.getOldValue(), (String) arg0.getNewValue(), ds);
		}

		private JRDataset getDataset(JasperDesign jd, Object obj) {
			if (getDataset(jd.getMainDataset(), obj))
				return jd.getMainDataset();
			for (JRDataset ds : jd.getDatasetsList()) {
				if (getDataset(ds, obj))
					return ds;
			}
			return null;
		}

		private boolean getDataset(JRDataset ds, Object obj) {
			if (obj instanceof JRField)
				for (JRField f : ds.getFields())
					if (f == obj)
						return true;
			if (obj instanceof JRVariable)
				for (JRVariable f : ds.getVariables())
					if (f == obj)
						return true;
			if (obj instanceof JRParameter)
				for (JRParameter f : ds.getParameters())
					if (f == obj)
						return true;

			return false;
		}

	};
	private PropertyChangeListener jrdsListener = new PropertyChangeListener() {

		@Override
		public void propertyChange(PropertyChangeEvent arg0) {
			JasperReportsConfiguration jrContext = editor.getJrContext();
			JRDataset ds = (JRDataset) arg0.getSource();
			String pname = arg0.getPropertyName();
			if (pname.equals(JRDesignDataset.PROPERTY_NAME)) {
				renameDS(jrContext, (String) arg0.getOldValue(), (String) arg0.getNewValue(), ds);
			} else if (pname.equals(JRDesignDataset.PROPERTY_FIELDS)) {
				if (arg0 instanceof CollectionElementAddedEvent) {
					JRDesignField f = (JRDesignField) ((CollectionElementAddedEvent) arg0).getNewValue();
					f.getEventSupport().addPropertyChangeListener(jrdsObjListener);
				} else if (arg0 instanceof CollectionElementRemovedEvent) {
					JRField f = (JRField) ((CollectionElementRemovedEvent) arg0).getRemovedValue();
					cleanup(jrContext, TraceGovernor.PREFIX + TraceGovernor.TYPE_FIELD, f.getName(), ds);
				}
			} else if (pname.equals(JRDesignDataset.PROPERTY_VARIABLES)) {
				if (arg0 instanceof CollectionElementAddedEvent) {
					JRDesignVariable f = (JRDesignVariable) ((CollectionElementAddedEvent) arg0).getNewValue();
					f.getEventSupport().addPropertyChangeListener(jrdsObjListener);
				} else if (arg0 instanceof CollectionElementRemovedEvent) {
					JRVariable f = (JRVariable) ((CollectionElementRemovedEvent) arg0).getRemovedValue();
					cleanup(jrContext, TraceGovernor.PREFIX + TraceGovernor.TYPE_VARIABLE, f.getName(), ds);
				}
			} else if (pname.equals(JRDesignDataset.PROPERTY_PARAMETERS)) {
				if (arg0 instanceof CollectionElementAddedEvent) {
					JRDesignParameter f = (JRDesignParameter) ((CollectionElementAddedEvent) arg0).getNewValue();
					f.getEventSupport().addPropertyChangeListener(jrdsObjListener);
				} else if (arg0 instanceof CollectionElementRemovedEvent) {
					JRParameter f = (JRParameter) ((CollectionElementRemovedEvent) arg0).getRemovedValue();
					cleanup(jrContext, TraceGovernor.PREFIX + TraceGovernor.TYPE_PARAMETER, f.getName(), ds);
				}
			}
		}
	};
	private PropertyChangeListener dsListener = new PropertyChangeListener() {

		@Override
		public void propertyChange(PropertyChangeEvent arg0) {
			if (arg0.getPropertyName().equals(JasperDesign.PROPERTY_DATASETS)) {
				if (arg0 instanceof CollectionElementRemovedEvent) {
					JasperReportsConfiguration jrContext = editor.getJrContext();
					JRDataset ds = (JRDataset) ((CollectionElementRemovedEvent) arg0).getRemovedValue();
					// remove all properties for this dataset
					String key = TraceGovernor.PREFIX + TraceGovernor.TYPE_FIELD;
					for (JRField f : ds.getFields())
						cleanup(jrContext, key, f.getName(), ds);

					key = TraceGovernor.PREFIX + TraceGovernor.TYPE_VARIABLE;
					for (JRVariable f : ds.getVariables())
						cleanup(jrContext, key, f.getName(), ds);

					key = TraceGovernor.PREFIX + TraceGovernor.TYPE_PARAMETER;
					for (JRParameter f : ds.getParameters())
						cleanup(jrContext, key, f.getName(), ds);
				}
				setupDatasets();
			}
		}

	};

	private void cleanup(JasperReportsConfiguration jrContext, String key, String fn, JRDataset ds) {
		String n = ds.getName();
		jrContext.removeProperty(key + n + "." + TraceGovernor.BEFORE_REPORT_INIT + fn);
		jrContext.removeProperty(key + n + "." + TraceGovernor.AFTER_REPORT_INIT + fn);

		jrContext.removeProperty(key + n + "." + TraceGovernor.BEFORE_PAGE_INIT + fn);
		jrContext.removeProperty(key + n + "." + TraceGovernor.AFTER_PAGE_INIT + fn);

		jrContext.removeProperty(key + n + "." + TraceGovernor.BEFORE_COLUMN_INIT + fn);
		jrContext.removeProperty(key + n + "." + TraceGovernor.AFTER_COLUMN_INIT + fn);

		for (JRGroup gr : ds.getGroups()) {
			jrContext.removeProperty(key + n + "." + TraceGovernor.BEFORE_GROUP_INIT + gr.getName() + "." + fn);
			jrContext.removeProperty(key + n + "." + TraceGovernor.AFTER_GROUP_INIT + gr.getName() + "." + fn);
		}

		jrContext.removeProperty(key + n + "." + TraceGovernor.BEFORE_DETAIL_EVAL + fn);
		jrContext.removeProperty(key + n + "." + TraceGovernor.AFTER_DETAIL_EVAL + fn);
	}

	private void renameDS(JasperReportsConfiguration jrContext, String fn, String newFn, JRDataset ds) {
		renameDSByType(jrContext, TraceGovernor.BEFORE_REPORT_INIT, fn, newFn, ds);
		renameDSByType(jrContext, TraceGovernor.AFTER_REPORT_INIT, fn, newFn, ds);

		renameDSByType(jrContext, TraceGovernor.BEFORE_PAGE_INIT, fn, newFn, ds);
		renameDSByType(jrContext, TraceGovernor.AFTER_PAGE_INIT, fn, newFn, ds);

		renameDSByType(jrContext, TraceGovernor.BEFORE_COLUMN_INIT, fn, newFn, ds);
		renameDSByType(jrContext, TraceGovernor.AFTER_COLUMN_INIT, fn, newFn, ds);

		for (JRGroup gr : ds.getGroups()) {
			renameDSByType(jrContext, TraceGovernor.BEFORE_GROUP_INIT + gr.getName(), fn, newFn, ds);
			renameDSByType(jrContext, TraceGovernor.AFTER_GROUP_INIT + gr.getName(), fn, newFn, ds);
		}

		renameDSByType(jrContext, TraceGovernor.BEFORE_DETAIL_EVAL, fn, newFn, ds);
		renameDSByType(jrContext, TraceGovernor.AFTER_DETAIL_EVAL, fn, newFn, ds);
	}

	private void renameDSByType(JasperReportsConfiguration jrContext, String type, String fn, String newFn, JRDataset ds) {
		String prefix = TraceGovernor.PREFIX + TraceGovernor.TYPE_FIELD;
		for (JRField f : ds.getFields()) {
			String sufix = "." + type + f.getName();
			rename(jrContext, prefix + fn + sufix, prefix + newFn + sufix);
		}
		prefix = TraceGovernor.PREFIX + TraceGovernor.TYPE_VARIABLE;
		for (JRVariable f : ds.getVariables()) {
			String sufix = "." + type + f.getName();
			rename(jrContext, prefix + fn + sufix, prefix + newFn + sufix);
		}
		prefix = TraceGovernor.PREFIX + TraceGovernor.TYPE_PARAMETER;
		for (JRParameter f : ds.getParameters()) {
			String sufix = "." + type + f.getName();
			rename(jrContext, prefix + fn + sufix, prefix + newFn + sufix);
		}
	}

	private void renameDSObject(JasperReportsConfiguration jrContext, String key, String fn, String newFn, JRDataset ds) {
		String n = ds.getName();
		String skey = key + n + "." + TraceGovernor.BEFORE_REPORT_INIT;
		rename(jrContext, skey + fn, skey + newFn);
		skey = key + n + "." + TraceGovernor.BEFORE_REPORT_INIT;
		rename(jrContext, skey + fn, skey + newFn);
		skey = key + n + "." + TraceGovernor.AFTER_REPORT_INIT;
		rename(jrContext, skey + fn, skey + newFn);

		skey = key + n + "." + TraceGovernor.BEFORE_PAGE_INIT;
		rename(jrContext, skey + fn, skey + newFn);
		skey = key + n + "." + TraceGovernor.AFTER_PAGE_INIT;
		rename(jrContext, skey + fn, skey + newFn);

		skey = key + n + "." + TraceGovernor.BEFORE_COLUMN_INIT;
		rename(jrContext, skey + fn, skey + newFn);
		skey = key + n + "." + TraceGovernor.AFTER_COLUMN_INIT;
		rename(jrContext, skey + fn, skey + newFn);

		for (JRGroup gr : ds.getGroups()) {
			skey = key + n + "." + TraceGovernor.BEFORE_GROUP_INIT + gr.getName() + ".";
			rename(jrContext, skey + fn, skey + newFn);
			skey = key + n + "." + TraceGovernor.AFTER_GROUP_INIT + gr.getName() + ".";
			rename(jrContext, skey + fn, skey + newFn);
		}

		skey = key + n + "." + TraceGovernor.BEFORE_DETAIL_EVAL;
		rename(jrContext, skey + fn, skey + newFn);
		skey = key + n + "." + TraceGovernor.AFTER_DETAIL_EVAL;
		rename(jrContext, skey + fn, skey + newFn);
	}

	private void rename(JasperReportsConfiguration jrContext, String oldkey, String newKey) {
		String old = jrContext.getProperty(oldkey);
		if (old != null) {
			jrContext.setProperty(newKey, old);
			jrContext.removeProperty(oldkey);
		}
	}

	private Composite cmp;

	class TableLabelProvider extends ColumnLabelProvider implements ITableLabelProvider {
		private CheckBoxLabelProvider cblp = new CheckBoxLabelProvider(NullEnum.NOTNULL);

		public Image getColumnImage(Object element, int columnIndex) {
			if (columnIndex == 0) {
				INode n = (INode) element;
				return JaspersoftStudioPlugin.getInstance().getImage(n.getImagePath());
			} else if (element instanceof MField || element instanceof MVariableSystem || element instanceof MParameterSystem) {
				JasperReportsConfiguration jrConfig = editor.getJrContext();
				String type = null;
				String name = null;
				if (element instanceof MField) {
					type = TraceGovernor.TYPE_FIELD;
					name = ((MField) element).getValue().getName();
				} else if (element instanceof MVariableSystem) {
					type = TraceGovernor.TYPE_VARIABLE;
					name = ((MVariableSystem) element).getValue().getName();
				} else if (element instanceof MParameterSystem) {
					type = TraceGovernor.TYPE_PARAMETER;
					name = ((MParameterSystem) element).getValue().getName();
				} else
					return null;
				String level = (String) tree.getColumn(columnIndex).getData("COLNAME");
				Boolean b = jrConfig.getPropertyBoolean(TraceGovernor.PREFIX + type + dataset.getName() + "." + level + name,
						false);
				return cblp.getCellEditorImage(b);
			}
			return null;
		}

		public String getColumnText(Object element, int columnIndex) {
			switch (columnIndex) {
			case 0:
				INode n = (INode) element;
				if (n instanceof MReport)
					return "Main Dataset";
				return n.getDisplayText();
			case 1:
				return getCellValue(element, TraceGovernor.BEFORE_REPORT_INIT);
			case 2:
				return getCellValue(element, TraceGovernor.AFTER_REPORT_INIT);
			case 3:
				return getCellValue(element, TraceGovernor.BEFORE_PAGE_INIT);
			case 4:
				return getCellValue(element, TraceGovernor.AFTER_PAGE_INIT);
			case 5:
				return getCellValue(element, TraceGovernor.BEFORE_COLUMN_INIT);
			case 6:
				return getCellValue(element, TraceGovernor.AFTER_COLUMN_INIT);
			case 7:
				return getCellValue(element, TraceGovernor.BEFORE_GROUP_INIT);
			case 8:
				return getCellValue(element, TraceGovernor.AFTER_GROUP_INIT);
			case 9:
				return getCellValue(element, TraceGovernor.BEFORE_DETAIL_EVAL);
			case 10:
				return getCellValue(element, TraceGovernor.AFTER_DETAIL_EVAL);
			default:
				return "haha";
			}
		}

		@Override
		public String getToolTipText(Object element) {
			return super.getToolTipText(element);
		}

		private String getCellValue(Object element, String level) {
			if (element instanceof INode) {
				JasperReportsConfiguration jrConfig = editor.getJrContext();
				String type = null;
				String name = null;
				if (element instanceof MField) {
					type = TraceGovernor.TYPE_FIELD;
					name = ((MField) element).getValue().getName();
				} else if (element instanceof MVariableSystem) {
					type = TraceGovernor.TYPE_VARIABLE;
					name = ((MVariableSystem) element).getValue().getName();
				} else if (element instanceof MParameterSystem) {
					type = TraceGovernor.TYPE_PARAMETER;
					name = ((MParameterSystem) element).getValue().getName();
				} else
					return "";

				Boolean b = jrConfig.getPropertyBoolean(TraceGovernor.PREFIX + type + dataset.getName() + "." + level + name,
						false);
				return b.toString();
			}
			return "";
		}

		public void addListener(ILabelProviderListener listener) {
		}

		public void dispose() {
		}

		public boolean isLabelProperty(Object element, String property) {
			return false;
		}

		public void removeListener(ILabelProviderListener listener) {
		}
	}

	class TreeContentProvider implements ITreeContentProvider {
		public Object[] getChildren(Object parentElement) {
			if (parentElement instanceof INode) {
				List<INode> nodes = new ArrayList<INode>();
				INode n = (INode) parentElement;
				MReport mrep = null;
				if (n instanceof MRoot) {
					for (INode child : n.getChildren()) {
						if (child instanceof MReport) {
							mrep = (MReport) child;
							break;
						}
					}
				} else if (n instanceof MReport) {
					if (((MReport) n).getValue().getMainDataset().equals(dataset))
						for (INode child : n.getChildren()) {
							if (child instanceof MParameters<?>)
								nodes.add(child);
							else if (child instanceof MVariables)
								nodes.add(child);
							else if (child instanceof MFields)
								nodes.add(child);
						}
				} else if (n instanceof MDataset) {
					if (((MDataset) n).getValue().equals(dataset))
						for (INode child : n.getChildren()) {
							if (child instanceof MParameters<?>)
								nodes.add(child);
							else if (child instanceof MVariables)
								nodes.add(child);
							else if (child instanceof MFields)
								nodes.add(child);
						}
				} else if (n instanceof MParameters<?>) {
					nodes.addAll(n.getChildren());
				} else if (n instanceof MVariables) {
					nodes.addAll(n.getChildren());
				} else if (n instanceof MFields) {
					nodes.addAll(n.getChildren());
				}
				if (mrep != null) {
					if (mrep.getValue().getMainDataset().equals(dataset))
						nodes.add(mrep);
					for (INode child : mrep.getChildren()) {
						if (child instanceof MDataset)
							if (((MDataset) child).getValue().equals(dataset))
								nodes.add(child);
					}
				}
				return nodes.toArray();
			}
			return new Object[0];
		}

		public Object getParent(Object element) {
			if (element instanceof INode)
				return ((INode) element).getParent();
			return null;
		}

		public boolean hasChildren(Object element) {
			if (element instanceof INode)
				return getChildren(element).length > 0;
			return false;
		}

		public Object[] getElements(Object elements) {
			return getChildren(elements);
		}

		public void dispose() {
		}

		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}
	}

	@Override
	public void dispose() {
		JasperDesign jd = editor.getJrContext().getJasperDesign();
		for (JRDataset ds : jd.getDatasetsList()) {
			JRDesignDataset jrds = (JRDesignDataset) ds;
			jrds.getEventSupport().removePropertyChangeListener(jrdsListener);

			for (JRField f : ds.getFields())
				((JRDesignField) f).getEventSupport().removePropertyChangeListener(jrdsObjListener);
			for (JRVariable f : ds.getVariables())
				((JRDesignVariable) f).getEventSupport().removePropertyChangeListener(jrdsObjListener);
			for (JRParameter f : ds.getParameters())
				((JRDesignParameter) f).getEventSupport().removePropertyChangeListener(jrdsObjListener);
		}
		jd.getEventSupport().removePropertyChangeListener(dsListener);
		super.dispose();
	}

	/*
	 * (non-Javadoc) Method declared on IPage.
	 */
	public Control getControl() {
		return cmp;
	}

	public void setFocus() {
		cmp.setFocus();
	}
}
