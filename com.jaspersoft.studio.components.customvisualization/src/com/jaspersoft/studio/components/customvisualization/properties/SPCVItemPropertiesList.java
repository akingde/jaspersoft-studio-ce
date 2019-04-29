/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.customvisualization.properties;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.components.customvisualization.messages.Messages;
import com.jaspersoft.studio.components.customvisualization.model.CVCProprtiesExpressionDTO;
import com.jaspersoft.studio.components.customvisualization.model.CVItemPropertiesDescriptor;
import com.jaspersoft.studio.components.customvisualization.model.MCustomVisualization;
import com.jaspersoft.studio.components.customvisualization.ui.UIManager;
import com.jaspersoft.studio.components.customvisualization.ui.framework.CVCWidgetsDescriptor;
import com.jaspersoft.studio.editor.expression.ExpressionContext;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.util.ItemPropertyUtil;
import com.jaspersoft.studio.properties.layout.StackLayout;
import com.jaspersoft.studio.properties.view.SectionContainerComposite;
import com.jaspersoft.studio.property.itemproperty.desc.ADescriptor;
import com.jaspersoft.studio.property.itemproperty.desc.DescriptorPropertyLabelProvider;
import com.jaspersoft.studio.property.itemproperty.dialog.FormItemDialog;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.property.section.widgets.ASPropertyWidget;
import com.jaspersoft.studio.utils.ExpressionInterpreter;
import com.jaspersoft.studio.utils.ExpressionUtil;
import com.jaspersoft.studio.utils.ModelUtils;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;
import com.jaspersoft.studio.widgets.framework.IPropertyEditor;
import com.jaspersoft.studio.widgets.framework.WItemProperty;
import com.jaspersoft.studio.widgets.framework.manager.DoubleControlComposite;
import com.jaspersoft.studio.widgets.framework.manager.WidgetFactory;
import com.jaspersoft.studio.widgets.framework.model.SectionPropertyDescriptor;
import com.jaspersoft.studio.widgets.framework.model.WidgetPropertyDescriptor;
import com.jaspersoft.studio.widgets.framework.ui.ItemPropertyDescription;

import net.sf.jasperreports.components.items.ItemProperty;
import net.sf.jasperreports.components.items.StandardItemProperty;
import net.sf.jasperreports.customvisualization.design.CVDesignComponent;
import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JasperDesign;

/**
 * Widget to modify the {@link CVDesignComponent#PROPERTY_ITEM_PROPERTIES}
 * property in the dedicated Property section.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 * 
 */
public class SPCVItemPropertiesList extends ASPropertyWidget<CVItemPropertiesDescriptor> {

	private TableViewer propertiesTV;
	private Button btnAddProperty;
	private Button btnModifyProperty;
	private Button btnRemoveProperty;
	private Group propertiesGrp;
	private CVCProprtiesExpressionDTO itemProps;
	private StackLayout stackLayout;
	private Composite form;
	private Composite cmp;
	private Composite mainContainer;
	private CVCWidgetsDescriptor currentDescriptor;
	private List<WItemProperty> wIProps = new ArrayList<WItemProperty>();
	private SectionContainerComposite propertyPageContainer;

	public SPCVItemPropertiesList(Composite parent, AbstractSection section, CVItemPropertiesDescriptor pdescriptor) {
		super(parent, section, pdescriptor);
	}

	@Override
	protected void createComponent(Composite parent) {
		mainContainer = new Composite(parent, SWT.NONE);
		GridLayout mainContainerLayout = new GridLayout(1, false);
		mainContainerLayout.horizontalSpacing = 0;
		mainContainerLayout.marginHeight = 0;
		mainContainerLayout.marginWidth = 0;
		mainContainerLayout.verticalSpacing = 0;
		mainContainer.setLayout(mainContainerLayout);
		
		cmp = new Composite(mainContainer, SWT.NONE);
		cmp.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		stackLayout = new StackLayout();
		cmp.setLayout(stackLayout);

		createPropertiesTable(cmp);
		stackLayout.setTopControl(propertiesGrp);

		form = new Composite(cmp, SWT.NONE);
		GridLayout formLayout = new GridLayout(2, false);
		formLayout.horizontalSpacing = 0;
		formLayout.marginHeight = 0;
		formLayout.marginWidth = 0;
		formLayout.verticalSpacing = 0;
		form.setLayout(formLayout);
		
		if (parent instanceof SectionContainerComposite){
			propertyPageContainer = (SectionContainerComposite)parent;
		} else {
			propertyPageContainer = null;
		}
	}

	private void addNewPropertyBtnPressed() {
		CVItemPropertyDialog d = new CVItemPropertyDialog(UIUtils.getShell(), null, null);
		d.setExpressionContext(getExpressionContext());
		if (d.open() == Window.OK) {
			itemProps.getItemProps().add(d.getItemProperty());
			section.changeProperty(CVDesignComponent.PROPERTY_ITEM_PROPERTIES, itemProps);
		}
	}

	private void modifyPropertyBtnPressed() {
		ItemProperty p = getCurrentSelectedProperty();
		if (p != null) {
			ItemProperty clonedP = (ItemProperty) p.clone();
			CVItemPropertyDialog d = new CVItemPropertyDialog(UIUtils.getShell(), clonedP, null);
			d.setExpressionContext(getExpressionContext());
			if (d.open() == Window.OK) {
				int idx = itemProps.getItemProps().indexOf(p);
				itemProps.getItemProps().remove(p);
				itemProps.getItemProps().add(idx, clonedP);
				section.changeProperty(CVDesignComponent.PROPERTY_ITEM_PROPERTIES, itemProps);
			}
		}
	}

	private void removePropertyBtnPressed() {
		ItemProperty p = getCurrentSelectedProperty();
		if (p != null) {
			itemProps.getItemProps().remove(p);
			section.changeProperty(CVDesignComponent.PROPERTY_ITEM_PROPERTIES, itemProps);
		}
	}

	private ItemProperty getCurrentSelectedProperty() {
		Object selEl = ((IStructuredSelection) propertiesTV.getSelection()).getFirstElement();
		if (selEl instanceof ItemProperty) {
			return (ItemProperty) selEl;
		}
		return null;
	}

	private TableViewer createPropertiesTable(Composite parent) {
		propertiesGrp = new Group(parent, SWT.NONE);
		propertiesGrp.setLayout(new GridLayout(2, false));

		//Composite cmpItemPropertiesTableViewer = new Composite(propertiesGrp, SWT.NONE);
		//cmpItemPropertiesTableViewer.setLayoutData(new GridData(GridData.FILL_BOTH));
		//TableColumnLayout tl_itemPropertiesTableViewer = new TableColumnLayout();
		//cmpItemPropertiesTableViewer.setLayout(tl_itemPropertiesTableViewer);
		final Composite tableContainer = new Composite(propertiesGrp, SWT.NONE);
		tableContainer.setLayout(new GridLayout(1, false));
		tableContainer.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		
		propertiesTV = new TableViewer(tableContainer, SWT.BORDER | SWT.V_SCROLL | SWT.SINGLE | SWT.FULL_SELECTION);
		propertiesTV.getTable().setHeaderVisible(true);
		propertiesTV.getTable().setLinesVisible(true);
		propertiesTV.getTable().setLayoutData(new GridData(GridData.FILL_BOTH));

		TableViewerColumn tvcName = new TableViewerColumn(propertiesTV, SWT.NONE);
		tvcName.getColumn().setText(Messages.SPCVItemPropertiesList_ColName);
		tvcName.getColumn().setWidth(100);
		tvcName.setLabelProvider(new ItemPropertyNameLabelProvider());

		TableViewerColumn tvcValue = new TableViewerColumn(propertiesTV, SWT.NONE);
		tvcValue.getColumn().setText(Messages.SPCVItemPropertiesList_ColValue);
		tvcValue.setLabelProvider(new ItemPropertyValueLabelProvider());
		tvcValue.getColumn().setWidth(100);

		propertiesTV.setContentProvider(new ArrayContentProvider());

		propertiesTV.addDoubleClickListener(new IDoubleClickListener() {
			@Override
			public void doubleClick(DoubleClickEvent event) {
				modifyPropertyBtnPressed();
			}
		});

		Composite buttonsComposite = new Composite(propertiesGrp,SWT.NONE);
		GridLayout buttonsCompositeLayout = new GridLayout(1, false);
		buttonsCompositeLayout.horizontalSpacing = 0;
		buttonsCompositeLayout.marginWidth = 0;
		buttonsCompositeLayout.marginHeight = 0;
		buttonsComposite.setLayout(buttonsCompositeLayout);
		buttonsComposite.setLayoutData(new GridData(GridData.FILL_VERTICAL));
		
		btnAddProperty = new Button(buttonsComposite, SWT.PUSH);
		btnAddProperty.setText(Messages.SPCVItemPropertiesList_Add);
		btnAddProperty.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		btnAddProperty.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				addNewPropertyBtnPressed();
			}
		});

		btnModifyProperty = new Button(buttonsComposite, SWT.PUSH);
		btnModifyProperty.setText(Messages.SPCVItemPropertiesList_Edit);
		btnModifyProperty.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		btnModifyProperty.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				modifyPropertyBtnPressed();
			}
		});

		btnRemoveProperty = new Button(buttonsComposite, SWT.PUSH);
		btnRemoveProperty.setText(Messages.SPCVItemPropertiesList_Remove);
		btnRemoveProperty.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		btnRemoveProperty.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				removePropertyBtnPressed();
			}
		});
		propertiesTV.addSelectionChangedListener(new ISelectionChangedListener() {

			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				enablePropertiesTVButtons();
			}
		});
		propertiesTV.getTable().addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if (btnRemoveProperty.isEnabled() && (e.keyCode == SWT.DEL || e.keyCode == SWT.BS))
					removePropertyBtnPressed();
			}
		});
		
		enablePropertiesTVButtons();
		return propertiesTV;
	}

	@Override
	public void setData(APropertyNode pnode, Object value) {
		itemProps = (CVCProprtiesExpressionDTO) value;
		if (itemProps == null) {
			itemProps = new CVCProprtiesExpressionDTO(new ArrayList<ItemProperty>(),(MCustomVisualization)pnode, pnode.getJasperDesign(), pnode.getJasperConfiguration());
		}
		propertiesTV.setInput(itemProps.getItemProps());

		JasperDesign jd = pnode.getJasperDesign();
		JasperReportsConfiguration jConf = pnode.getJasperConfiguration();
		JRDesignDataset dataset = null;
		if (dataset == null)
			dataset = ModelUtils.getDataset(pnode);
		if (dataset == null)
			dataset = (JRDesignDataset) jd.getMainDataset();

		ExpressionInterpreter expIntr = ExpressionUtil.getCachedInterpreter(dataset, jd, jConf);
		String module = null;
		for (ItemProperty ip : itemProps.getItemProps()) {
			// let's get our description
			if (ip.getName().equals("module")) {
				 module = ItemPropertyUtil.getItemPropertyString((StandardItemProperty) ip, expIntr);
				if (!Misc.isNullOrEmpty(module)) {
					CVCWidgetsDescriptor newDescriptor = UIManager.getDescriptor(jConf, module);
					if (newDescriptor == null) {
						stackLayout.setTopControl(propertiesGrp);
						//Refresh the scrollbars
						if (propertyPageContainer != null){
							propertyPageContainer.refreshPageComposite();
						}
					} else {
						if (newDescriptor == currentDescriptor) {
							//same descriptor, only update the data
							setDataIntoWidgets();
							stackLayout.setTopControl(form);
						} else {
							//need to dispose the old controls and create the new ones
							currentDescriptor = newDescriptor;
							wIProps.clear();
							for (Control c : form.getChildren()){
								c.dispose();
							}
							ExpressionContext ec = getExpressionContext();
							CVCPropertyDescriptor descriptor = new CVCPropertyDescriptor();
							DescriptorPropertyLabelProvider descriptorLabelProvider = new DescriptorPropertyLabelProvider(descriptor);
							if (currentDescriptor != null && currentDescriptor.getSections() != null) {
								boolean first = true;
								for (SectionPropertyDescriptor csd : currentDescriptor.getSections()) {
									Composite c = null;
									if (csd.isExpandable()){
										c = createSection(form, csd.getName());
									} else if (!Misc.isNullOrEmpty(csd.getName())){
										c = createGroup(form, csd.getName());
									} else {
										c = form;
										if (!first){
											FormItemDialog.createSeparator(form);
										}
									}
									first = false;
									SectionCVCPropertyEditor editor = new SectionCVCPropertyEditor(this.section, itemProps);
									for (WidgetPropertyDescriptor pd : csd.getProperties()) {
										ItemPropertyDescription<?> ipdesc = WidgetFactory.createItemPropertyDescriptor(currentDescriptor, pd, jConf);
										if (ipdesc != null){
											descriptor.addItemPropertyDescriptor(ipdesc);
											wIProps.add(createItemProperty(c, ipdesc, descriptorLabelProvider, ec, editor));	
										}
									}
								}
								setDataIntoWidgets();
								stackLayout.setTopControl(form);
								//Refresh the scrollbars
								if (propertyPageContainer != null){
									//refresh the properties view
									propertyPageContainer.getParent().layout(true, true); 
									propertyPageContainer.refreshPageComposite();
								}
							}
						}
					}
				}
			}
		}
		if (Misc.isNullOrEmpty(module)){
			stackLayout.setTopControl(propertiesGrp);
			cmp.layout(true);
		}
	}
	
	private void setDataIntoWidgets(){
		//update the editor, if I'm refreshing a CVC the descriptor is the same between
		//two CVC of the same type, but I need to update the editor with a new one
		//with the properties of the currently selected
		SectionCVCPropertyEditor editor = new SectionCVCPropertyEditor(this.section, itemProps);
		for (WItemProperty wip : wIProps){
			wip.setPropertyEditor(editor);
			wip.updateWidget();
		}
	}


	@Override
	public Control getControl() {
		return mainContainer;
	}

	private ExpressionContext getExpressionContext() {
		return ModelUtils.getElementExpressionContext(null, section.getElement());
	}

	protected Composite createSection(Composite parent, String text) {
		Composite c = section.getWidgetFactory().createSection(parent, text, true, 2, 2);
		return c;
	}

	protected Composite createGroup(Composite parent, String text) {
		Composite c = section.getWidgetFactory().createSection(parent, text, false, 2, 2);
		return c;
	}

	private void enablePropertiesTVButtons() {
		btnModifyProperty.setEnabled(!propertiesTV.getSelection().isEmpty());
		btnRemoveProperty.setEnabled(!propertiesTV.getSelection().isEmpty());
	}

	protected WItemProperty createItemProperty(Composite cmp, ItemPropertyDescription<?> ipd,  DescriptorPropertyLabelProvider descriptorLabelProvider, ExpressionContext ec, IPropertyEditor editor) {
		Label lbl = new Label(cmp, SWT.NONE);
		lbl.setText(Misc.nvl(ipd.getLabel()));
		lbl.setToolTipText(ipd.getToolTip());

		final WItemProperty expr = new WItemProperty(cmp, SWT.NONE, ipd, editor);
		DoubleControlComposite control = (DoubleControlComposite)expr.getControl();
		Control simpleControl = control.getSimpleControlToHighlight();
		if (simpleControl instanceof Combo || simpleControl instanceof Text) {
			/**
			 * Assuring that the width has an hint in case of grid layout, doing this will force the
			 * text to not grow too much depending on the text content 
			 */
			
			Object layoutData = simpleControl.getLayoutData();
			if (layoutData != null && layoutData.getClass().equals(GridData.class)) {
				GridData oldData = (GridData)layoutData;
				if (oldData.grabExcessHorizontalSpace && oldData.horizontalAlignment == SWT.FILL && oldData.widthHint == SWT.DEFAULT);
				GridData newGridData = new GridData(oldData.horizontalAlignment, oldData.verticalAlignment, oldData.grabExcessHorizontalSpace, 
														oldData.grabExcessVerticalSpace, oldData.horizontalSpan, oldData.verticalSpan);
				int w = getCharWidth(simpleControl) * 15;
				if (w > 50) w = 50;
				newGridData.widthHint = w;
				simpleControl.setLayoutData(newGridData);
			}
		}
		expr.setLabelProvider(descriptorLabelProvider);
		expr.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		expr.setExpressionContext(ec);
		if (ipd.isReadOnly()){
			expr.setEnabled(false);
		}
		return expr;
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
		
		@Override
		public IPropertyEditor getPropertyEditor() {
			return null;
		}

	}
}
