/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * Licensed under commercial Jaspersoft Subscription License Agreement
 ******************************************************************************/
package com.jaspersoft.studio.components.customvisualization.properties;

import java.util.List;

import net.sf.jasperreports.components.map.ItemProperty;
import net.sf.jasperreports.components.map.StandardItemProperty;
import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.forms.widgets.Section;

import com.jaspersoft.jasperreports.customvisualization.design.CVDesignComponent;
import com.jaspersoft.studio.components.customvisualization.messages.Messages;
import com.jaspersoft.studio.components.customvisualization.model.CVItemPropertiesDescriptor;
import com.jaspersoft.studio.components.customvisualization.ui.ComponentDescriptor;
import com.jaspersoft.studio.components.customvisualization.ui.ComponentPropertyDescriptor;
import com.jaspersoft.studio.components.customvisualization.ui.ComponentSectionDescriptor;
import com.jaspersoft.studio.components.customvisualization.ui.UIManager;
import com.jaspersoft.studio.editor.expression.ExpressionContext;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.util.ItemPropertyUtil;
import com.jaspersoft.studio.property.itemproperty.WItemProperty;
import com.jaspersoft.studio.property.itemproperty.desc.ADescriptor;
import com.jaspersoft.studio.property.itemproperty.desc.ItemPropertyDescription;
import com.jaspersoft.studio.property.itemproperty.dialog.FormItemDialog;
import com.jaspersoft.studio.property.itemproperty.event.ItemPropertyModifiedEvent;
import com.jaspersoft.studio.property.itemproperty.event.ItemPropertyModifiedListener;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.property.section.widgets.ASPropertyWidget;
import com.jaspersoft.studio.utils.ExpressionInterpreter;
import com.jaspersoft.studio.utils.ExpressionUtil;
import com.jaspersoft.studio.utils.Misc;
import com.jaspersoft.studio.utils.ModelUtils;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

/**
 * Widget to modify the {@link CVDesignComponent#PROPERTY_ITEM_PROPERTIES}
 * property in the dedicated Property section.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 * 
 */
public class SPCVItemPropertiesList extends
		ASPropertyWidget<CVItemPropertiesDescriptor> {

	private TableViewer propertiesTV;
	private Button btnAddProperty;
	private Button btnModifyProperty;
	private Button btnRemoveProperty;
	private Group propertiesGrp;
	private List<ItemProperty> itemProps;
	private StackLayout stackLayout;
	private Composite form;
	private Composite cmp;

	public SPCVItemPropertiesList(Composite parent, AbstractSection section,
			CVItemPropertiesDescriptor pdescriptor) {
		super(parent, section, pdescriptor);
	}

	@Override
	protected void createComponent(Composite parent) {
		cmp = new Composite(parent, SWT.NONE);
		stackLayout = new StackLayout();
		cmp.setLayout(stackLayout);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		cmp.setLayoutData(gd);

		createPropertiesTable(cmp);
		stackLayout.topControl = propertiesGrp;

		form = new Composite(cmp, SWT.NONE);
		form.setLayout(new GridLayout(2, false));
	}

	private void addNewPropertyBtnPressed() {
		CVItemPropertyDialog d = new CVItemPropertyDialog(UIUtils.getShell(),
				null, null);
		d.setExpressionContext(getExpressionContext());
		if (d.open() == Window.OK) {
			itemProps.add(d.getItemProperty());
			section.changeProperty(CVDesignComponent.PROPERTY_ITEM_PROPERTIES,
					itemProps);
		}
	}

	private void modifyPropertyBtnPressed() {
		ItemProperty p = getCurrentSelectedProperty();
		if (p != null) {
			ItemProperty clonedP = (ItemProperty) p.clone();
			CVItemPropertyDialog d = new CVItemPropertyDialog(
					UIUtils.getShell(), clonedP, null);
			d.setExpressionContext(getExpressionContext());
			if (d.open() == Window.OK) {
				int idx = itemProps.indexOf(p);
				itemProps.remove(p);
				itemProps.add(idx, clonedP);
				section.changeProperty(
						CVDesignComponent.PROPERTY_ITEM_PROPERTIES, itemProps);
			}
		}
	}

	private void removePropertyBtnPressed() {
		ItemProperty p = getCurrentSelectedProperty();
		if (p != null) {
			itemProps.remove(p);
			section.changeProperty(CVDesignComponent.PROPERTY_ITEM_PROPERTIES,
					itemProps);
		}
	}

	private ItemProperty getCurrentSelectedProperty() {
		Object selEl = ((IStructuredSelection) propertiesTV.getSelection())
				.getFirstElement();
		if (selEl instanceof ItemProperty) {
			return (ItemProperty) selEl;
		}
		return null;
	}

	private TableViewer createPropertiesTable(Composite parent) {
		propertiesGrp = new Group(parent, SWT.NONE);
		propertiesGrp.setLayout(new GridLayout(2, false));

		Composite cmpItemPropertiesTableViewer = new Composite(propertiesGrp,
				SWT.NONE);
		cmpItemPropertiesTableViewer.setLayoutData(new GridData(SWT.FILL,
				SWT.FILL, true, true, 1, 3));
		TableColumnLayout tl_itemPropertiesTableViewer = new TableColumnLayout();
		cmpItemPropertiesTableViewer.setLayout(tl_itemPropertiesTableViewer);

		propertiesTV = new TableViewer(cmpItemPropertiesTableViewer, SWT.BORDER
				| SWT.V_SCROLL | SWT.SINGLE | SWT.FULL_SELECTION);
		propertiesTV.getTable().setHeaderVisible(true);
		propertiesTV.getTable().setLinesVisible(true);

		TableViewerColumn tvcName = new TableViewerColumn(propertiesTV,
				SWT.NONE);
		tvcName.getColumn().setText(Messages.SPCVItemPropertiesList_ColName);
		tvcName.setLabelProvider(new ItemPropertyNameLabelProvider());
		tl_itemPropertiesTableViewer.setColumnData(tvcName.getColumn(),
				new ColumnWeightData(1, ColumnWeightData.MINIMUM_WIDTH, true));

		TableViewerColumn tvcValue = new TableViewerColumn(propertiesTV,
				SWT.NONE);
		tvcValue.getColumn().setText(Messages.SPCVItemPropertiesList_ColValue);
		tvcValue.setLabelProvider(new ItemPropertyValueLabelProvider());
		tl_itemPropertiesTableViewer.setColumnData(tvcValue.getColumn(),
				new ColumnWeightData(1, ColumnWeightData.MINIMUM_WIDTH, true));

		propertiesTV.setContentProvider(new ArrayContentProvider());

		propertiesTV.addDoubleClickListener(new IDoubleClickListener() {
			@Override
			public void doubleClick(DoubleClickEvent event) {
				modifyPropertyBtnPressed();
			}
		});

		btnAddProperty = new Button(propertiesGrp, SWT.PUSH);
		btnAddProperty.setText(Messages.SPCVItemPropertiesList_Add);
		btnAddProperty.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false,
				false));
		btnAddProperty.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				addNewPropertyBtnPressed();
			}
		});

		btnModifyProperty = new Button(propertiesGrp, SWT.PUSH);
		btnModifyProperty.setText(Messages.SPCVItemPropertiesList_Edit);
		btnModifyProperty.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false,
				false));
		btnModifyProperty.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				modifyPropertyBtnPressed();
			}
		});

		btnRemoveProperty = new Button(propertiesGrp, SWT.PUSH);
		btnRemoveProperty.setText(Messages.SPCVItemPropertiesList_Remove);
		btnRemoveProperty.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false,
				false));
		btnRemoveProperty.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				removePropertyBtnPressed();
			}
		});
		return propertiesTV;
	}

	@Override
	@SuppressWarnings("unchecked")
	public void setData(APropertyNode pnode, Object value) {
		itemProps = (List<ItemProperty>) value;
		propertiesTV.setInput(itemProps);

		JasperDesign jd = pnode.getJasperDesign();
		JasperReportsConfiguration jConf = pnode.getJasperConfiguration();
		JRDesignDataset dataset = null;
		if (dataset == null)
			dataset = ModelUtils.getDataset(pnode);
		if (dataset == null)
			dataset = (JRDesignDataset) jd.getMainDataset();

		ExpressionInterpreter expIntr = ExpressionUtil.getCachedInterpreter(
				dataset, jd, jConf);
		for (ItemProperty ip : itemProps) {
			// let's get our description
			if (ip.getName().equals("module")) {
				String module = ItemPropertyUtil.getItemPropertyString(
						(StandardItemProperty) ip, expIntr);
				if (!Misc.isNullOrEmpty(module)) {
					ComponentDescriptor cd = UIManager.getDescriptor(jConf,
							module);
					for (Control c : form.getChildren())
						c.dispose();
					ExpressionContext ec = getExpressionContext();
					CVCPropertyDescriptor descriptor = new CVCPropertyDescriptor();
					for (ComponentSectionDescriptor csd : cd.getSections()) {
						Composite c = null;
						if (csd.isExpandable())
							c = createSection(form, csd.getName());
						else {
							c = form;
							FormItemDialog.createSeparator(form);
						}
						for (ComponentPropertyDescriptor pd : csd
								.getProperties()) {
							ItemPropertyDescription<?> ipdesc = UIManager
									.createItemPropertyDescriptor(pd);
							descriptor.addItemPropertyDescriptor(ipdesc);
							createItemProperty(c, ipdesc, descriptor, ec);
						}
					}
					form.layout(true);
					stackLayout.topControl = form;
					cmp.layout(true);
					return;
				}
			}
		}
		stackLayout.topControl = propertiesGrp;
		cmp.layout(true);
	}

	@Override
	public Control getControl() {
		return propertiesGrp;
	}

	private ExpressionContext getExpressionContext() {
		return ModelUtils.getElementExpressionContext(null,
				section.getElement());
	}

	protected WItemProperty createItemProperty(Composite cmp,
			ItemPropertyDescription<?> ipd, CVCPropertyDescriptor descriptor,
			ExpressionContext ec) {
		Label lbl = new Label(cmp, SWT.NONE);
		lbl.setText(ipd.getLabel());
		lbl.setToolTipText(ipd.getDescription());

		final WItemProperty expr = new WItemProperty(cmp, SWT.NONE, 1,
				descriptor, ipd);
		expr.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		expr.setToolTipText(ipd.getDescription());
		expr.setExpressionContext(ec);
		expr.setEnabled(ipd.isReadOnly());
		final ItemProperty p = ItemPropertyUtil.getProperty(itemProps,
				ipd.getName());
		if (p != null)
			expr.setValue((StandardItemProperty) p);
		expr.addModifyListener(new ItemPropertyModifiedListener() {

			@Override
			public void itemModified(ItemPropertyModifiedEvent event) {
				UIUtils.getDisplay().asyncExec(new Runnable() {

					@Override
					public void run() {
						if (p != null) {
							int idx = itemProps.indexOf(p);
							if (idx >= 0)
								itemProps.set(idx, expr.getValue());
							else
								itemProps.add(expr.getValue());
						} else
							itemProps.add(expr.getValue());
						section.changeProperty(
								CVDesignComponent.PROPERTY_ITEM_PROPERTIES,
								itemProps);
					}
				});

			}
		});

		return expr;
	}

	protected Composite createSection(Composite parent, String text) {
		Section ec = new Section(parent, Section.TREE_NODE);
		ec.setText(text);

		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		ec.setLayoutData(gd);

		Composite c = new Composite(ec, SWT.WRAP);
		c.setLayout(new GridLayout(2, false));
		ec.setClient(c);
		return c;
	}

	private class CVCPropertyDescriptor extends ADescriptor {

		public void addItemPropertyDescriptor(ItemPropertyDescription<?> ipd) {
			if (itemProperties == null) {
				itemProperties = new ItemPropertyDescription<?>[] { ipd };
				return;
			}
			ItemPropertyDescription<?>[] ip = new ItemPropertyDescription<?>[itemProperties.length + 1];
			System.arraycopy(itemProperties, 0, ip, 0, itemProperties.length);
			ip[ip.length - 1] = ipd;
			itemProperties = ip;
		}

		@Override
		protected void initItemPropertyDescriptors() {

		}

	}

}
