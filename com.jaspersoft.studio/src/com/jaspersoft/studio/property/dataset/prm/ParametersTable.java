/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.dataset.prm;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.gef.dnd.TemplateTransfer;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.window.ToolTip;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.part.PluginTransfer;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.dnd.NodeDragListener;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.dataset.MDataset;
import com.jaspersoft.studio.model.parameter.MParameter;
import com.jaspersoft.studio.model.parameter.MParameterSystem;
import com.jaspersoft.studio.property.dataset.DatasetUtil;
import com.jaspersoft.studio.property.dataset.dialog.AbstractModifyTable;
import com.jaspersoft.studio.property.dataset.dialog.DataQueryAdapters;
import com.jaspersoft.studio.property.dataset.fields.PropertiesDialog;
import com.jaspersoft.studio.property.dataset.fields.table.TColumn;
import com.jaspersoft.studio.property.dataset.fields.table.TColumnFactory;
import com.jaspersoft.studio.property.dataset.fields.table.column.CheckboxColumnSupport;
import com.jaspersoft.studio.property.dataset.fields.table.column.ExpressionColumnSupport;
import com.jaspersoft.studio.property.dataset.fields.table.column.JRPropertiesColumnSupport;
import com.jaspersoft.studio.property.dataset.fields.table.column.JRPropertyColumnSupport;
import com.jaspersoft.studio.property.descriptor.propexpr.JPropertyExpressionsCellEditor;
import com.jaspersoft.studio.property.descriptor.propexpr.PropertyExpressionDTO;
import com.jaspersoft.studio.property.descriptor.propexpr.cell.JRPropertyExpressionCellEditor;
import com.jaspersoft.studio.property.descriptor.propexpr.dialog.PropertyDialogHelper;
import com.jaspersoft.studio.swt.widgets.table.DeleteButton;
import com.jaspersoft.studio.swt.widgets.table.EditButton;
import com.jaspersoft.studio.swt.widgets.table.IEditElement;
import com.jaspersoft.studio.swt.widgets.table.INewElement;
import com.jaspersoft.studio.swt.widgets.table.ListContentProvider;
import com.jaspersoft.studio.swt.widgets.table.NewButton;
import com.jaspersoft.studio.utils.UIUtil;

import net.sf.jasperreports.annotations.properties.PropertyScope;
import net.sf.jasperreports.data.DataAdapter;
import net.sf.jasperreports.eclipse.ui.ATitledDialog;
import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JRPropertiesMap;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JRDesignParameter;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.properties.PropertyMetadata;

public class ParametersTable extends AbstractModifyTable {
	private TableViewer tviewer;
	private Composite composite;
	private JRDesignDataset dataset;
	private Color background;
	private boolean isMainDataset;
	private MDataset mdataset;
	private ToolItem bDA;
	private ToolItem bSystem;

	public ParametersTable(Composite parent, final JRDesignDataset dataset, Color background, boolean isMainDataset,
			MDataset mdataset) {
		this.mdataset = mdataset;
		this.background = background;
		this.isMainDataset = isMainDataset;
		this.dataset = dataset;
		createControl(parent);
		dataset.getPropertiesMap().getEventSupport().addPropertyChangeListener(new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				String pname = evt.getPropertyName();
				if (pname.equals(DataQueryAdapters.DEFAULT_DATAADAPTER)
						|| pname.equals(DataQueryAdapters.DEFAULT_DATAADAPTER)) {
					refreshDataAdapter();
					refreshProperties(da);
					tviewer.refresh(true);
					treeviewer.refresh(true);
				}
			}
		});
		((JRDesignQuery) dataset.getQuery()).getEventSupport()
				.addPropertyChangeListener(JRDesignQuery.PROPERTY_LANGUAGE, new PropertyChangeListener() {

					@Override
					public void propertyChange(PropertyChangeEvent evt) {
						UIUtils.getDisplay().asyncExec(new Runnable() {

							@Override
							public void run() {
								List<JRParameter> fields = dataset.getParametersList();
								if (fields == null)
									fields = new ArrayList<JRParameter>();
								setFields(fields);
							}
						});
					}
				});
	}

	public Composite getControl() {
		return composite;
	}

	private DataAdapter da;

	private void refreshDataAdapter() {
		da = DatasetUtil.refreshDataAdapter(mdataset.getJasperDesign(), dataset, mdataset.getJasperConfiguration());
	}

	private void createControl(Composite parent) {
		composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout());
		composite.setBackground(background);
		composite.setBackgroundMode(SWT.INHERIT_FORCE);

		createButtons();

		pcmp = new Composite(composite, SWT.NONE);
		layout = new StackLayout();
		pcmp.setLayout(layout);
		pcmp.setLayoutData(new GridData(GridData.FILL_BOTH));

		tblCmp = new Composite(pcmp, SWT.NONE);
		tblCmp.setLayout(new GridLayout(2, false));
		createParametersTable(tblCmp);

		treeCmp = new Composite(pcmp, SWT.NONE);
		treeCmp.setLayout(new GridLayout(2, false));
		createParametersTree(treeCmp);

		layout.topControl = tblCmp;
	}

	private void createButtons() {
		ToolBar buttons = new ToolBar(composite, SWT.FLAT);
		buttons.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		bSystem = new ToolItem(buttons, SWT.CHECK);
		bSystem.setImage(
				JaspersoftStudioPlugin.getInstance().getImage(MParameterSystem.getIconDescriptor().getIcon16()));
		bSystem.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event event) {
				filter.setShowSystem(!bSystem.getSelection());
				bDA.setSelection(false);
				tviewer.refresh();
				treeviewer.refresh();
				// if (layout.topControl == treeCmp) {
				// layout.topControl = tblCmp;
				// pcmp.layout();
				// }
			}

		});
		bSystem.setToolTipText("Hide Built-In parameters");
		bSystem.setSelection(false);

		bDA = new ToolItem(buttons, SWT.CHECK);
		bDA.setImage(JaspersoftStudioPlugin.getInstance().getImage("icons/resources/parameter-da-16.png"));
		bDA.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event event) {
				if (bDA.getSelection())
					layout.topControl = treeCmp;
				else
					layout.topControl = tblCmp;
				pcmp.layout();
			}

		});
		bDA.setToolTipText("Show parameter properties");
	}

	private void createParametersTree(Composite parent) {
		Tree wtree = new Tree(parent, SWT.V_SCROLL | SWT.MULTI | SWT.FULL_SELECTION | SWT.BORDER);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.widthHint = 100;
		wtree.setLayoutData(gd);
		wtree.setHeaderVisible(true);
		wtree.setLinesVisible(true);

		treeviewer = new TreeViewer(wtree);
		treeviewer.setContentProvider(new ITreeContentProvider() {

			@Override
			public boolean hasChildren(Object element) {
				if (element instanceof JRDesignParameter)
					return !((JRDesignParameter) element).getPropertiesMap().isEmpty();
				return false;
			}

			@Override
			public Object getParent(Object element) {
				return null;
			}

			@Override
			public Object[] getElements(Object element) {
				return getChildren(element);
			}

			@Override
			public Object[] getChildren(Object element) {
				if (element instanceof Collection<?>) {
					List<Object> res = new ArrayList<Object>();
					for (Object el : (Collection<?>) element) {
						if (el instanceof JRDesignParameter && !filter.showSystem
								&& ((JRDesignParameter) el).isSystemDefined())
							continue;
						res.add(el);
					}
					return res.toArray();
				}
				if (element instanceof JRDesignParameter) {
					JRPropertiesMap pm = ((JRDesignParameter) element).getPropertiesMap();
					if (!pm.isEmpty()) {
						Object[][] props = new Object[pm.getPropertyNames().length][3];
						int i = 0;
						for (String key : pm.getPropertyNames()) {
							props[i] = new Object[] { key, pm.getProperty(key), element };
							i++;
						}
						return props;
					}
				}
				return null;
			}
		});
		UIUtil.setViewerCellEditingOnDblClick(treeviewer);
		ColumnViewerToolTipSupport.enableFor(treeviewer, ToolTip.NO_RECREATE);

		TColumn c = new TColumn();
		c.setPropertyName("name");
		c.setLabel(Messages.ParametersTable_name);
		c.setValue(mdataset);
		TColumnFactory.addColumn(c, treeviewer);

		TColumn cde = new TColumn();
		cde.setPropertyName("defaultValueExpression");
		cde.setLabel(Messages.MParameter_default_value_expression);
		cde.setType("expression");
		cde.setValue(mdataset);
		TColumnFactory.addColumn(cde, treeviewer);

		c = new TColumn();
		c.setPropertyName("key");
		c.setLabel("Property");
		c.setType("text");
		c.setValue(mdataset);
		TColumnFactory.addColumn(c, treeviewer, new JRPropertyColumnSupport(treeviewer, c) {
			@Override
			protected boolean canEdit(Object element) {
				return false;
			}

			@Override
			public String getText(Object element) {
				PropertyMetadata pm = DatasetUtil.getPmap(mdataset.getJasperConfiguration())
						.get(super.getText(element));
				if (pm != null)
					return pm.getLabel();
				return super.getText(element);
			}

			@Override
			public String getToolTipText(Object element) {
				String tt = super.getToolTipText(element);
				String txt = super.getText(element);
				if (!txt.equals(tt.trim()))
					tt += "\n" + super.getText(element);
				PropertyMetadata pm = DatasetUtil.getPmap(mdataset.getJasperConfiguration())
						.get(super.getText(element));
				if (pm != null && !Misc.isNullOrEmpty(pm.getDescription()))
					tt += "\n\n" + pm.getDescription();
				return tt;
			}

			@Override
			protected Object getValue(Object element) {
				if (element instanceof Object[])
					return ((Object[]) element)[0];
				return "";
			}
		});

		c = new TColumn();
		c.setPropertyName("value");
		c.setLabel("Value");
		c.setType("text");
		c.setValue(mdataset);
		TColumnFactory.addColumn(c, treeviewer, new JRPropertyColumnSupport(treeviewer, c) {
			private JRPropertyExpressionCellEditor ce;

			@Override
			protected CellEditor createCellEditor() {
				if (ce == null)
					ce = new JRPropertyExpressionCellEditor((Composite) viewer.getControl(), false, false,
							mdataset.getJasperConfiguration()) {
						@Override
						protected String getDialogTitle() {
							PropertyExpressionDTO v = (PropertyExpressionDTO) getValue();
							PropertyMetadata pm = DatasetUtil.getPmap(mdataset.getJasperConfiguration())
									.get(v.getName());
							if (pm != null)
								return pm.getLabel();
							return super.getDialogTitle();
						}
					};
				return ce;
			}

			@Override
			protected boolean canEdit(Object element) {
				return element instanceof Object[];
			}

			@Override
			protected Object getValue(Object element) {
				if (element instanceof Object[]) {
					Object[] row = (Object[]) element;
					return new PropertyExpressionDTO(false, (String) row[0], (String) row[1]);
				}
				return null;
			}

			@Override
			protected void setValue(Object element, Object value) {
				if (element instanceof Object[]) {
					Object[] row = (Object[]) element;
					JRPropertiesMap pm = ((JRDesignParameter) row[2]).getPropertiesMap();
					if (value instanceof PropertyExpressionDTO)
						value = ((PropertyExpressionDTO) value).getValue();
					if (value == null || value.toString().isEmpty())
						pm.removeProperty((String) row[0]);
					else {
						pm.setProperty((String) row[0], (String) value);
						row[1] = value;
					}
					// viewer.update(element, null);
				} else
					super.setValue(element, value);
			}
		});

		Composite bGroup = new Composite(parent, SWT.NONE);
		bGroup.setLayout(new GridLayout(1, false));
		bGroup.setLayoutData(new GridData(GridData.FILL_VERTICAL));
		bGroup.setBackground(background);

		final Button baddprm = new Button(bGroup, SWT.PUSH);
		baddprm.setText("Add Parameter");
		baddprm.setLayoutData(new GridData(GridData.FILL_HORIZONTAL | GridData.VERTICAL_ALIGN_BEGINNING));
		baddprm.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				StructuredSelection sel = (StructuredSelection) treeviewer.getSelection();
				final JRDesignParameter p = new JRDesignParameter();
				p.setName(getName());
				List<JRDesignParameter> prms = (List<JRDesignParameter>) treeviewer.getInput();
				int indx = -1;
				Object obj = sel.getFirstElement();
				if (obj != null)
					indx = prms.indexOf(obj);
				if (indx >= 0) {
					prms.add(indx, p);
					try {
						dataset.addParameter(indx, p);
					} catch (JRException e1) {
						e1.printStackTrace();
					}
				} else {
					prms.add(p);
					try {
						dataset.addParameter(p);
					} catch (JRException e1) {
						e1.printStackTrace();
					}
				}
				p.getPropertiesMap().getEventSupport().addPropertyChangeListener(new PropertyChangeListener() {

					@Override
					public void propertyChange(PropertyChangeEvent evt) {
						treeviewer.refresh(true);
						treeviewer.expandToLevel(p, 1);
					}
				});
				fireModifyListeners();

				treeviewer.refresh();
				treeviewer.expandToLevel(p, 2);
				treeviewer.setSelection(new StructuredSelection(p));
				treeviewer.reveal(p);
			}
		});

		final Button badd = new Button(bGroup, SWT.PUSH);
		badd.setText("Add Property");
		badd.setEnabled(false);
		badd.setLayoutData(new GridData(GridData.FILL_HORIZONTAL | GridData.VERTICAL_ALIGN_BEGINNING));
		badd.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				StructuredSelection sel = (StructuredSelection) treeviewer.getSelection();
				Object obj = sel.getFirstElement();
				JRDesignParameter p = null;
				if (obj instanceof JRDesignParameter)
					p = (JRDesignParameter) obj;
				if (obj instanceof Object[])
					p = (JRDesignParameter) ((Object[]) obj)[2];

				NewPropertyDialog d = new NewPropertyDialog(badd.getShell(), p);
				if (d.open() == Dialog.OK) {
					String key = d.getKey();
					String value = "";
					PropertyMetadata pm = DatasetUtil.getPmap(mdataset.getJasperConfiguration()).get(key);
					if (pm != null)
						value = pm.getDefaultValue();

					p.getPropertiesMap().setProperty(key, value);

					treeviewer.refresh();
					treeviewer.expandToLevel(p, 2);
					treeviewer.setSelection(new StructuredSelection(p));
					treeviewer.reveal(p);
				}
			}
		});

		final Button bedit = new Button(bGroup, SWT.PUSH);
		bedit.setText("Edit");
		bedit.setEnabled(false);
		bedit.setLayoutData(new GridData(GridData.FILL_HORIZONTAL | GridData.VERTICAL_ALIGN_BEGINNING));
		final SelectionAdapter editListener = new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				StructuredSelection sel = (StructuredSelection) treeviewer.getSelection();
				Object obj = sel.getFirstElement();
				if (obj instanceof JRDesignParameter) {
					JRDesignParameter oldF = (JRDesignParameter) obj;
					PropertiesDialog<JRDesignParameter> d = new PropertiesDialog<JRDesignParameter>(
							tviewer.getTable().getShell(), (JRDesignParameter) oldF.clone(), tcolumns,
							MParameter.getIconDescriptor().getDescription(), mdataset.getJasperConfiguration());
					if (d.open() == Dialog.OK) {
						int pos = dataset.getParametersList().indexOf(oldF);
						dataset.removeParameter(oldF.getName());
						try {
							obj = d.getElement();
							if (pos >= 0 && pos < dataset.getParametersList().size())
								dataset.addParameter(pos, d.getElement());
							else
								dataset.addParameter(d.getElement());
						} catch (JRException ex) {
							UIUtils.showError(ex);
						}
						params.clear();
						fillTree();
					}
				} else if (obj instanceof Object[]) {
					Object[] row = (Object[]) obj;
					PropertyExpressionDTO v = new PropertyExpressionDTO(false, (String) row[0], (String) row[1]);
					JRDesignParameter pold = (JRDesignParameter) row[2];
					JRDesignParameter pclone = (JRDesignParameter) pold.clone();
					if (PropertyDialogHelper.showPropertyDialog(v, pclone, mdataset.getJasperConfiguration())) {
						for (String key : pold.getPropertiesMap().getPropertyNames())
							pold.getPropertiesMap().removeProperty(key);
						for (String key : pclone.getPropertiesMap().getPropertyNames())
							pold.getPropertiesMap().setProperty(key, pclone.getPropertiesMap().getProperty(key));
						row[0] = v.getName();
						row[1] = v.getValue();
					}
				}
				treeviewer.refresh();
				treeviewer.expandToLevel(obj, 2);
				treeviewer.setSelection(new StructuredSelection(obj));
				treeviewer.reveal(obj);
			}
		};
		bedit.addSelectionListener(editListener);
		treeviewer.addDoubleClickListener(new IDoubleClickListener() {

			@Override
			public void doubleClick(DoubleClickEvent event) {
				if (bedit.isEnabled())
					editListener.widgetSelected(null);
			}
		});

		final Button bdel = new Button(bGroup, SWT.PUSH);
		bdel.setText(Messages.common_delete);
		bdel.setEnabled(false);
		bdel.setLayoutData(new GridData(GridData.FILL_HORIZONTAL | GridData.VERTICAL_ALIGN_BEGINNING));
		bdel.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				StructuredSelection sel = (StructuredSelection) treeviewer.getSelection();
				List<JRDesignParameter> prms = (List<JRDesignParameter>) treeviewer.getInput();
				for (Object obj : sel.toList()) {
					if (obj instanceof JRDesignParameter)
						prms.remove(obj);
					else if (obj instanceof Object[]) {
						Object[] row = (Object[]) obj;
						((JRDesignParameter) row[2]).getPropertiesMap().removeProperty((String) row[0]);
					}
				}
				treeviewer.refresh();
			}
		});
		treeviewer.addSelectionChangedListener(new ISelectionChangedListener() {

			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				StructuredSelection sel = (StructuredSelection) treeviewer.getSelection();
				Object obj = sel.getFirstElement();
				badd.setEnabled(!sel.isEmpty() && (obj instanceof JRDesignParameter || obj instanceof Object[]));
				bedit.setEnabled(!sel.isEmpty());
				bdel.setEnabled(!sel.isEmpty());
			}
		});
		treeviewer.addDragSupport(DND.DROP_COPY | DND.DROP_MOVE,
				new Transfer[] { TemplateTransfer.getInstance(), PluginTransfer.getInstance() },
				new NodeDragListener(treeviewer));
		treeviewer.expandAll();

		params = new ArrayList<JRParameter>();
		if (dataset.getParametersList() != null)
			fillTree();
		treeviewer.setInput(params);
	}

	private void fillTree() {
		for (JRParameter p : dataset.getParametersList()) {
			params.add(p);
			final JRParameter prm = p;
			p.getPropertiesMap().getEventSupport().addPropertyChangeListener(new PropertyChangeListener() {

				@Override
				public void propertyChange(PropertyChangeEvent evt) {
					treeviewer.refresh(true);
					treeviewer.expandToLevel(prm, 1);
				}
			});
		}
	}

	class NewPropertyDialog extends ATitledDialog {
		private JRDesignParameter p;
		private String key;

		public NewPropertyDialog(Shell shell, JRDesignParameter p) {
			super(shell);
			this.p = p;
			setTitle("New Property");
			setDescription("Add a property or select one provided by Data Adapter.");
		}

		public String getKey() {
			return key;
		}

		@Override
		protected Control createDialogArea(Composite parent) {
			Composite cmp = (Composite) super.createDialogArea(parent);
			cmp.setLayout(new GridLayout());

			Label lbl = new Label(cmp, SWT.NONE);
			lbl.setText("Property");

			final Text lprop = new Text(cmp, SWT.BORDER);
			GridData gd = new GridData(GridData.FILL_HORIZONTAL);
			gd.horizontalSpan = 2;
			lprop.setLayoutData(gd);
			lprop.addModifyListener(new ModifyListener() {

				@Override
				public void modifyText(ModifyEvent e) {
					key = lprop.getText();
				}
			});

			final org.eclipse.swt.widgets.List cmb = new org.eclipse.swt.widgets.List(cmp, SWT.BORDER | SWT.SINGLE);
			gd = new GridData(GridData.FILL_BOTH);
			gd.widthHint = 400;
			gd.heightHint = 300;
			cmb.setLayoutData(gd);
			final List<String> items = new ArrayList<String>();
			Map<String, PropertyMetadata> pmap = DatasetUtil.getPmap(mdataset.getJasperConfiguration());
			for (String key : pmap.keySet()) {
				List<PropertyScope> scopes = pmap.get(key).getScopes();
				if (scopes != null && scopes.contains(PropertyScope.PARAMETER)
						&& !p.getPropertiesMap().containsProperty(key))
					items.add(key);
			}
			Collections.sort(items);

			for (String it : items)
				cmb.add(pmap.get(it).getLabel());
			cmb.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseDoubleClick(MouseEvent e) {
					lprop.setText(items.get(cmb.getSelectionIndex()));
					key = lprop.getText();
					close();
				}
			});
			cmb.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					lprop.setText(items.get(cmb.getSelectionIndex()));
				}
			});
			cmb.addListener(SWT.MouseMove, new Listener() {
				@Override
				public void handleEvent(Event event) {
					int itemTop = 0;
					for (int i = 0; i < cmb.getItemCount(); i++) {
						if (event.y >= itemTop && event.y <= itemTop + cmb.getItemHeight()) {
							PropertyMetadata pm = DatasetUtil.getPmap(mdataset.getJasperConfiguration())
									.get(items.get(cmb.getTopIndex() + i));
							if (pm != null) {
								cmb.setToolTipText(pm.getName() + "\n\n" + Misc.nvl(pm.getDescription()));
							} else
								cmb.setToolTipText("");
						}
						itemTop += cmb.getItemHeight();
					}
				}
			});
			if (items.size() > 0) {
				cmb.select(0);
				lprop.setText(items.get(cmb.getSelectionIndex()));
			}
			return cmp;
		}
	}

	private void createParametersTable(Composite parent) {
		wtable = new Table(parent, SWT.V_SCROLL | SWT.MULTI | SWT.FULL_SELECTION | SWT.BORDER);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.widthHint = 100;
		wtable.setLayoutData(gd);
		wtable.setHeaderVisible(true);
		wtable.setLinesVisible(true);

		tviewer = new TableViewer(wtable);
		tviewer.setContentProvider(new ListContentProvider());
		UIUtil.setViewerCellEditingOnDblClick(tviewer);
		ColumnViewerToolTipSupport.enableFor(tviewer, ToolTip.NO_RECREATE);
		tviewer.setFilters(filter);

		refreshDataAdapter();
		refreshProperties(da);

		Composite bGroup = new Composite(parent, SWT.NONE);
		bGroup.setLayout(new GridLayout(1, false));
		bGroup.setLayoutData(new GridData(GridData.FILL_VERTICAL));
		bGroup.setBackground(background);

		new NewButton() {
			@Override
			protected void afterElementAdded(Object selement) {
				try {
					dataset.removeParameter((JRParameter) selement);
					dataset.addParameter((JRDesignParameter) selement);
					fireModifyListeners();
				} catch (JRException e) {
					e.printStackTrace();
				}
			}
		}.createNewButtons(bGroup, tviewer, new INewElement() {

			public Object newElement(List<?> input, int pos) {
				JRDesignParameter f = new JRDesignParameter();
				f.setName(getName());
				f.setValueClass(String.class);
				return f;
			}

		});

		EditButton<JRDesignParameter> eb = new EditButton<JRDesignParameter>() {
			@Override
			public void setEnabled(boolean enable) {
				StructuredSelection s = (StructuredSelection) tviewer.getSelection();
				super.setEnabled(
						enable && !s.isEmpty() && !((JRDesignParameter) s.getFirstElement()).isSystemDefined());
			}
		};
		eb.createEditButtons(bGroup, tviewer, new IEditElement<JRDesignParameter>() {

			@Override
			public void editElement(List<JRDesignParameter> input, int pos) {
				JRDesignParameter oldF = input.get(pos);
				PropertiesDialog<JRDesignParameter> d = new PropertiesDialog<JRDesignParameter>(
						tviewer.getTable().getShell(), (JRDesignParameter) oldF.clone(), tcolumns,
						MParameter.getIconDescriptor().getDescription(), mdataset.getJasperConfiguration());
				if (d.open() == Dialog.OK) {
					dataset.removeParameter(oldF);
					try {
						dataset.addParameter(d.getElement());
						input.set(pos, d.getElement());
					} catch (JRException e) {
						UIUtils.showError(e);
					}
				}
			}
		});
		eb.editOnDoubleClick();

		final DeleteButton delb = new DeleteButton() {
			@Override
			protected void afterElementDeleted(Object element) {
				JRParameter todel = null;
				for (JRParameter p : dataset.getParametersList())
					if (p.getName().equals(((JRDesignParameter) element).getName())) {
						todel = p;
						break;
					}
				if (todel != null) {
					dataset.removeParameter(todel);
					fireModifyListeners();
				}
			}
		};
		delb.createDeleteButton(bGroup, tviewer);

		List<JRParameter> fields = dataset.getParametersList();
		if (fields == null)
			fields = new ArrayList<JRParameter>();
		setFields(fields);

		tviewer.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				StructuredSelection sel = (StructuredSelection) event.getSelection();
				if (!sel.isEmpty()) {
					JRDesignParameter prm = (JRDesignParameter) sel.getFirstElement();
					delb.setEnabled(!prm.isSystemDefined());
				}
			}
		});

		tviewer.addDragSupport(DND.DROP_COPY | DND.DROP_MOVE,
				new Transfer[] { TemplateTransfer.getInstance(), PluginTransfer.getInstance() },
				new NodeDragListener(tviewer));
	}

	public String getName() {
		List<JRDesignParameter> list = (List<JRDesignParameter>) tviewer.getInput();
		String name = "Parameter"; //$NON-NLS-1$
		boolean match = false;
		String tmp = name;
		for (int i = 1; i < 100000; i++) {
			tmp = name + i;// ModelUtils.getNameFormat(name, i);

			for (JRDesignParameter f : list) {
				match = f.getName().equals(tmp);
				if (match)
					break;
			}
			if (!match)
				break;
		}
		return tmp;
	}

	private Filter filter = new Filter();

	public class Filter extends ViewerFilter {

		private boolean showSystem = true;

		public void setShowSystem(boolean showSystem) {
			this.showSystem = showSystem;
		}

		@Override
		public boolean select(Viewer viewer, Object parentElement, Object element) {
			JRDesignParameter p = (JRDesignParameter) element;
			if (!showSystem && p.isSystemDefined())
				return false;

			return true;
		}
	}

	private List<TableViewerColumn> columns = new ArrayList<TableViewerColumn>();

	private void createNameColumn() {
		TColumn c = new TColumn();
		c.setPropertyName("name");
		c.setLabel(Messages.ParametersTable_name);
		c.setValue(mdataset);
		columns.add(TColumnFactory.addColumn(c, tviewer));
		tcolumns.add(c);
	}

	private void createIsForPrompt() {
		TColumn c = new TColumn();
		c.setPropertyName("forPrompting");
		c.setLabel(Messages.ParametersTable_isForPrompt);
		c.setPropertyType(boolean.class.getName());
		columns.add(TColumnFactory.addColumn(c, tviewer, new CheckboxColumnSupport(tviewer, c) {

			@Override
			protected Object getValue(Object element) {
				JRDesignParameter p = (JRDesignParameter) element;
				if (p.isSystemDefined())
					return null;
				return super.getValue(element);
			}

			@Override
			public String getText(Object element) {
				return "";
			}

			@Override
			public Image getImage(Object element) {
				if (element instanceof JRDesignParameter) {
					JRDesignParameter p = (JRDesignParameter) element;
					if (!p.isSystemDefined() && p.isForPrompting())
						return super.getImage(true);
				} else if (element instanceof Boolean)
					return super.getImage(element);
				return null;
			}

			@Override
			protected boolean canEdit(Object element) {
				JRDesignParameter p = (JRDesignParameter) element;
				return !p.isSystemDefined() && super.canEdit(element);
			}
		}));
		tcolumns.add(c);
	}

	private void createTypeColumn() {
		TColumn c = new TColumn();
		c.setPropertyName("valueClassName");
		c.setLabel(Messages.ParametersTable_class);
		c.setPropertyType(Class.class.getName());
		columns.add(TColumnFactory.addColumn(c, tviewer));
		tcolumns.add(c);
	}

	private void createDefaultExpression() {
		TColumn c = new TColumn();
		c.setPropertyName("defaultValueExpression");
		c.setLabel(Messages.MParameter_default_value_expression);
		c.setPropertyType(JRDesignExpression.class.getName());
		c.setValue(mdataset);
		columns.add(TColumnFactory.addColumn(c, tviewer, new ExpressionColumnSupport(tviewer, c) {
			@Override
			protected boolean canEdit(Object element) {
				if (((JRDesignParameter) element).isSystemDefined())
					return false;
				return super.canEdit(element);
			}
		}));
		tcolumns.add(c);
	}

	private void createDescriptionColumn() {
		TColumn c = new TColumn();
		c.setPropertyName("description");
		c.setLabel(Messages.ParametersTable_description);
		c.setValue(dataset);
		columns.add(TColumnFactory.addColumn(c, tviewer));
		tcolumns.add(c);
	}

	private void createPropertiesColumn() {
		TColumn c = new TColumn();
		c.setPropertyName("properties");
		c.setLabel(Messages.common_properties);
		c.setPropertyType(JRPropertiesMap.class.getName());
		c.setType("properties");
		c.setValue(mdataset);
		columns.add(TColumnFactory.addColumn(c, tviewer, new JRPropertiesColumnSupport(tviewer, c) {
			@Override
			protected CellEditor getCellEditor(Object element) {
				return new JPropertyExpressionsCellEditor((Composite) viewer.getControl(), false);
			}

			@Override
			protected boolean canEdit(Object element) {
				return !((JRDesignParameter) element).isSystemDefined() && super.canEdit(element);
			}
		}));
		tcolumns.add(c);
	}

	public <T extends JRParameter> void setFields(List<T> fields) {
		tviewer.setInput(new ArrayList(fields));
		tviewer.refresh();

		if (treeviewer != null) {
			params = new ArrayList<JRParameter>();
			if (dataset.getParametersList() != null)
				fillTree();
			treeviewer.setInput(params);
			treeviewer.refresh();
			treeviewer.expandAll();
		}
	}

	public List<JRDesignParameter> getParameters() {
		return (List<JRDesignParameter>) tviewer.getInput();
	}

	private PropertyChangeSupport propertyChangeSupport;
	private StackLayout layout;
	private Composite tblCmp;
	private Composite treeCmp;
	private Composite pcmp;
	private TreeViewer treeviewer;

	public PropertyChangeSupport getPropertyChangeSupport() {
		if (propertyChangeSupport == null)
			propertyChangeSupport = new PropertyChangeSupport(this);
		return propertyChangeSupport;
	}

	private List<TColumn> tcolumns = new ArrayList<TColumn>();
	private List<JRParameter> params;

	private void refreshProperties(DataAdapter da) {
		for (TableViewerColumn tvc : columns)
			tvc.getColumn().dispose();
		columns.clear();
		tviewer.setColumnProperties(new String[0]);
		tviewer.setCellEditors(new CellEditor[0]);
		tviewer.getTable().setLayout(new TableLayout());
		tcolumns.clear();

		createNameColumn();
		if (isMainDataset)
			createIsForPrompt();
		createTypeColumn();
		createDescriptionColumn();
		createDefaultExpression();
		createPropertiesColumn();

		tviewer.getTable().layout(true);
	}

}
