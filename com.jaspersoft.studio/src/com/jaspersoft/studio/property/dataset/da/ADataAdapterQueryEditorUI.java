/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.dataset.da;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.window.ToolTip;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.parameter.MParameter;
import com.jaspersoft.studio.property.dataset.da.widgets.ParameterPropertyWidget;
import com.jaspersoft.studio.property.dataset.fields.table.TColumn;
import com.jaspersoft.studio.property.dataset.fields.table.TColumnFactory;
import com.jaspersoft.studio.property.dataset.fields.table.column.PropertyColumnSupport;
import com.jaspersoft.studio.property.descriptor.EditableDialogCellEditor;
import com.jaspersoft.studio.swt.widgets.table.DeleteButton;
import com.jaspersoft.studio.swt.widgets.table.ListContentProvider;
import com.jaspersoft.studio.swt.widgets.table.NewButton;
import com.jaspersoft.studio.utils.UIUtil;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.data.http.HttpDataLocation;
import net.sf.jasperreports.data.http.HttpLocationParameter;
import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignParameter;
import net.sf.jasperreports.engine.design.events.CollectionElementAddedEvent;
import net.sf.jasperreports.engine.design.events.CollectionElementRemovedEvent;
import net.sf.jasperreports.engine.type.ParameterEvaluationTimeEnum;
import net.sf.jasperreports.engine.util.JRClassLoader;

public abstract class ADataAdapterQueryEditorUI implements IDataAdapterQueryEditorUI {
	protected boolean refresh = false;

	public boolean isRefresh() {
		return refresh;
	}

	public void setRefresh(boolean refresh) {
		this.refresh = refresh;
	}

	protected List<PropertiesTable> pTables = new ArrayList<>();
	protected List<ParameterPropertyWidget> ppwTable = new ArrayList<>();

	public Composite createPropertiesTable(Composite parent, List<HttpLocationParameter> lparams, String pname) {
		PropertiesTable pt = new PropertiesTable();
		pTables.add(pt);
		List<HttpLocationParameter> prms = new ArrayList<>();
		if (lparams != null)
			prms.addAll(lparams);
		return pt.create(parent, prms, pname);
	}

	public void createPropertyWidget(String p, Composite parent, String daValue, int width,
			JasperReportsConfiguration jConfig) {
		ParameterPropertyWidget ppw = TColumnFactory.createParameterPropertyWidget(p, parent, icon, daValue, dataset,
				width, jConfig);
		if (ppw != null)
			ppwTable.add(ppw);
	}

	public void listenDataset(final JRDesignDataset dataset) {
		dataset.getEventSupport().addPropertyChangeListener(evt -> {
			if (evt.getPropertyName().equals(JRDesignDataset.PROPERTY_PARAMETERS)) {
				if (evt instanceof CollectionElementRemovedEvent) {
					JRDesignParameter prm = (JRDesignParameter) evt.getOldValue();
					prm.getEventSupport().removePropertyChangeListener(listener);
					prm.getPropertiesMap().getEventSupport().removePropertyChangeListener(listener);
				} else if (evt instanceof CollectionElementAddedEvent) {
					JRDesignParameter prm = (JRDesignParameter) evt.getNewValue();
					prm.getEventSupport().addPropertyChangeListener(listener);
					prm.getPropertiesMap().getEventSupport().addPropertyChangeListener(listener);
				}
				listener.propertyChange(evt);
			}
		});
		for (JRParameter p : dataset.getParametersList())
			((JRDesignParameter) p).getPropertiesMap().getEventSupport().addPropertyChangeListener(listener);
	}

	private PropertyChangeListener listener = evt -> {
		if (refresh)
			return;
		for (PropertiesTable pt : pTables)
			pt.refresh();
		for (ParameterPropertyWidget ppw : ppwTable)
			ppw.refresh();
	};
	protected JRDesignDataset dataset;
	protected HttpDataLocation dloc;
	protected Image icon;

	class PropertiesTable {
		private List<HttpLocationParameter> input;
		private Map<String, JRParameter> fParam;
		private TableViewer tviewer;
		private List<HttpLocationParameter> lparams;
		private String pname;

		public Composite create(final Composite parent, final List<HttpLocationParameter> lparams, final String pname) {
			this.lparams = lparams;
			this.pname = pname;

			Composite sectionClient = new Composite(parent, SWT.NONE);
			sectionClient.setLayout(new GridLayout(3, false));
			sectionClient.setBackgroundMode(SWT.INHERIT_FORCE);

			tviewer = new TableViewer(sectionClient, SWT.V_SCROLL | SWT.MULTI | SWT.FULL_SELECTION | SWT.BORDER);
			tviewer.setContentProvider(new ListContentProvider());
			UIUtil.setViewerCellEditingOnDblClick(tviewer);
			ColumnViewerToolTipSupport.enableFor(tviewer, ToolTip.NO_RECREATE);

			GridData gd = new GridData(GridData.FILL_BOTH);
			gd.widthHint = 100;
			tviewer.getTable().setLayoutData(gd);
			tviewer.getTable().setHeaderVisible(true);
			tviewer.getTable().setLinesVisible(true);
			tviewer.getTable().setLayout(new TableLayout());

			TColumn c = new TColumn();
			c.setPropertyName("name");
			c.setLabel(Messages.ParametersTable_name);
			TColumnFactory.addColumn(c, tviewer, new PropertyColumnSupport(tviewer, c) {
				@Override
				protected boolean canEdit(Object element) {
					HttpLocationParameter hlp = (HttpLocationParameter) element;
					for (HttpLocationParameter p : lparams)
						if (p.getName().equals(hlp.getName()))
							return false;
					return super.canEdit(element);
				}

				@Override
				protected void setValue(Object element, Object value) {
					HttpLocationParameter hlp = (HttpLocationParameter) element;
					JRDesignParameter p = (JRDesignParameter) fParam.get(hlp.getName());
					if (p != null) {
						String v = (String) value;
						String error = null;
						if (Misc.isNullOrEmpty(v))
							error = "Name can't be empty";
						else
							for (HttpLocationParameter item : input)
								if (item.getName().equals(v)) {
									error = "This name is already used";
									break;
								}
						if (error == null) {
							fParam.remove(hlp.getName());
							fParam.put(v, p);
							try {
								refresh = true;
								p.getPropertiesMap().setProperty(pname, (String) value);
							} finally {
								refresh = false;
							}
							hlp.setName(v);
							viewer.update(element, null);
						} else
							UIUtils.showWarning(error);
					}
				}
			});

			c = new TColumn();
			c.setPropertyName("value");
			c.setLabel("Value");
			TColumnFactory.addColumn(c, tviewer, new PropertyColumnSupport(tviewer, c) {
				@Override
				protected CellEditor getCellEditor(Object element) {
					return new PropertyValueCellEditor(tviewer.getTable(), pname, lparams, fParam, c, tviewer);
				}

				@Override
				protected void setValue(Object element, Object value) {
					if (value instanceof HttpLocationParameter) {
						System.out.println(element);
					} else
						super.setValue(element, value);
				}

				@Override
				protected Object getValue(Object element) {
					return element;
				}

				@Override
				public String getText(Object element) {
					HttpLocationParameter hlp = (HttpLocationParameter) element;
					JRParameter prm = fParam.get(hlp.getName());
					if (prm != null)
						return prm.getName();
					for (HttpLocationParameter p : lparams)
						if (p.getName().equals(hlp.getName()))
							return Misc.nvl(p.getValue(), "");
					return "";
				}

				@Override
				public Image getImage(Object element) {
					HttpLocationParameter hlp = (HttpLocationParameter) element;
					JRParameter prm = fParam.get(hlp.getName());
					if (prm != null)
						return JaspersoftStudioPlugin.getInstance()
								.getImage(MParameter.getIconDescriptor().getIcon16());
					for (HttpLocationParameter p : lparams)
						if (p.getName().equals(hlp.getName()))
							return icon;
					return JaspersoftStudioPlugin.getInstance().getImage(MParameter.getIconDescriptor().getIcon16());
				}
			});

			Composite bGroup = new Composite(sectionClient, SWT.NONE);
			bGroup.setLayout(new GridLayout(1, false));
			bGroup.setLayoutData(new GridData(GridData.FILL_VERTICAL));
			bGroup.setBackgroundMode(SWT.INHERIT_FORCE);

			new NewButton().createNewButtons(bGroup, tviewer, (in, pos) -> {
				HttpParameterDialog d = new HttpParameterDialog(parent.getShell(), dataset, PropertiesTable.this.input,
						pname);
				if (d.open() == Dialog.OK) {
					fParam.put(d.getName(), d.getPrm());
					try {
						refresh = true;
						d.getPrm().getPropertiesMap().setProperty(pname, d.getName());
					} finally {
						refresh = false;
					}
					return new HttpLocationParameter(d.getName(), null);
				}
				return null;
			});
			new DeleteButton() {
				@Override
				protected void afterElementDeleted(Object element) {
					input.remove(element);
					JRDesignParameter p = (JRDesignParameter) fParam.get(((HttpLocationParameter) element).getName());
					if (p != null) {
						try {
							refresh = true;
							p.getPropertiesMap().removeProperty(pname);
						} finally {
							refresh = false;
						}
					}
					fParam.remove(((HttpLocationParameter) element).getName());
				}

				@Override
				protected boolean canRemove(Object obj) {
					return !lparams.contains(obj);
				}
			}.createDeleteButton(bGroup, tviewer);

			refresh();
			return sectionClient;
		}

		public void refresh() {
			input = new ArrayList<>(lparams);
			fParam = new HashMap<>();
			for (JRParameter p : dataset.getParametersList()) {
				if (!p.isSystemDefined() && p.getPropertiesMap().containsProperty(pname)) {
					String pp = p.getPropertiesMap().getProperty(pname);
					fParam.put(pp, p);
					boolean exists = false;
					for (HttpLocationParameter hlp : lparams)
						if (hlp.getName().equals(pp)) {
							exists = true;
							break;
						}
					if (!exists)
						input.add(new HttpLocationParameter(pp, null));
				}
			}
			if (!tviewer.getControl().isDisposed())
				tviewer.setInput(input);
		}
	}

	private class PropertyValueCellEditor extends EditableDialogCellEditor {
		private String pname;
		private Map<String, JRParameter> fParam;
		private TColumn c;
		private List<HttpLocationParameter> lparams;
		public TableViewer tviewer;

		public PropertyValueCellEditor(Composite parent, String pname, List<HttpLocationParameter> lparams,
				Map<String, JRParameter> fParam, TColumn c, TableViewer tviewer) {
			super(parent);
			this.pname = pname;
			this.fParam = fParam;
			this.lparams = lparams;
			this.tviewer = tviewer;
			this.c = c;
		}

		@Override
		protected Control createControl(Composite parent) {
			final Composite cmp = (Composite) super.createControl(parent);
			getDefaultLabel().addMouseListener(new MouseAdapter() {
				@Override
				public void mouseDown(MouseEvent e) {
					openDialogBox(cmp);
				}
			});
			return cmp;
		}

		@Override
		protected Object openDialogBox(Control cellEditorWindow) {
			final Menu menu = new Menu(cellEditorWindow.getShell(), SWT.POP_UP);
			final HttpLocationParameter v = (HttpLocationParameter) getValue();

			if (lparams.contains(v)) {
				final MenuItem mid = new MenuItem(menu, SWT.PUSH);
				mid.setText(Misc.nvl(v.getValue(), "< NULL >"));
				mid.setImage(icon);
				mid.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						for (JRParameter prm : dataset.getParameters())
							if (!prm.isSystemDefined())
								try {
									refresh = true;
									prm.getPropertiesMap().removeProperty(pname);
								} finally {
									refresh = false;
								}
						fParam.remove(v.getName());
						tviewer.refresh();
					}

				});
				new MenuItem(menu, SWT.SEPARATOR);
			}
			for (final JRParameter p : dataset.getParameters()) {
				if (!p.isSystemDefined()) {
					String cn = JRClassLoader.getClassRealName(c.getPropertyType());
					try {
						if (!p.getValueClass().isAssignableFrom(JRClassLoader.loadClassForName(cn)))
							continue;
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
					if (fParam.values().contains(p))
						continue;
					final MenuItem mi = new MenuItem(menu, SWT.PUSH);
					mi.setText(p.getName());
					mi.setImage(
							JaspersoftStudioPlugin.getInstance().getImage(MParameter.getIconDescriptor().getIcon16()));
					final String def = p.getDefaultValueExpression() != null ? p.getDefaultValueExpression().getText()
							: null;
					String tt = "Default: " + (def == null ? "" : "null");
					if (!Misc.isNullOrEmpty(p.getDescription()))
						tt += "\n\n" + p.getDescription();
					UIUtil.safeApplyMenuItemTooltip(mi, tt);
					p.getPropertiesMap().getEventSupport();
					mi.addSelectionListener(new SelectionAdapter() {

						@Override
						public void widgetSelected(SelectionEvent e) {
							try {
								refresh = true;
								for (JRParameter prm : dataset.getParameters())
									if (!prm.isSystemDefined())
										prm.getPropertiesMap().removeProperty(v.getName());
								fParam.put(v.getName(), p);
								p.getPropertiesMap().setProperty(pname, v.getName());
								((JRDesignParameter) p).setEvaluationTime(ParameterEvaluationTimeEnum.EARLY);
							} finally {
								refresh = false;
							}
							tviewer.refresh();
						}
					});
				}
			}

			Rectangle bounds = getDefaultLabel().getBounds();
			Point point = getDefaultLabel().toDisplay(bounds.x, bounds.y + bounds.height);
			menu.setLocation(point);
			menu.setVisible(true);

			return null;
		}

		@Override
		protected void updateContents(Object value) {
			Label dl = getDefaultLabel();
			if (dl == null)
				return;
			if (value instanceof HttpLocationParameter) {
				HttpLocationParameter hdl = (HttpLocationParameter) value;
				JRParameter p = fParam.get(hdl.getName());
				if (p != null)
					dl.setText(Misc.nvl(p.getName(), ""));
				else
					dl.setText(Misc.nvl(hdl.getValue(), ""));
			} else
				dl.setText(Misc.nvl(value, ""));
		}

	}
}
