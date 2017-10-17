/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.widgets.framework.ui;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.data.DataAdapterManager;
import com.jaspersoft.studio.data.storage.ADataAdapterStorage;
import com.jaspersoft.studio.data.storage.PreferencesDataAdapterStorage;
import com.jaspersoft.studio.model.dataset.SelectDefaultDatasetWizard;
import com.jaspersoft.studio.property.combomenu.ComboItem;
import com.jaspersoft.studio.property.combomenu.ComboItemAction;
import com.jaspersoft.studio.property.combomenu.ComboItemSeparator;
import com.jaspersoft.studio.property.combomenu.WritableComboMenuViewer;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;
import com.jaspersoft.studio.widgets.framework.IWItemProperty;
import com.jaspersoft.studio.widgets.framework.manager.DoubleControlComposite;
import com.jaspersoft.studio.widgets.framework.model.WidgetPropertyDescriptor;
import com.jaspersoft.studio.widgets.framework.model.WidgetsDescriptor;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.FileUtils;
import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.eclipse.util.Pair;
import net.sf.jasperreports.engine.design.JRDesignDataset;

/**
 * Widget descriptor used to select the path of a JasperReprots data adapter. It provide a combo 
 * to select a local data adapter and a button to select a data adapter from a path. It also 
 * allow direct typing of the path
 * 
 * @author Orlandin Marco
 *
 */
public class JRDataAdapterPropertyDescription extends AbstractExpressionPropertyDescription<String> {
	
	private ADataAdapterStorage[] daStorage = new ADataAdapterStorage[0];

	public JRDataAdapterPropertyDescription() {
		super();
	}

	public JRDataAdapterPropertyDescription(String name, String label, String description, boolean mandatory, String defaultValue, JasperReportsConfiguration jConfig) {
		super(name, label, description, mandatory, defaultValue);
		this.jConfig = jConfig;
		IFile file = (IFile) jConfig.get(FileUtils.KEY_FILE);
		daStorage = DataAdapterManager.getDataAdapter(file, jConfig);
	}

	public JRDataAdapterPropertyDescription(String name, String label, String description, boolean mandatory, JasperReportsConfiguration jConfig) {
		super(name, label, description, mandatory);
		this.jConfig = jConfig;
		IFile file = (IFile) jConfig.get(FileUtils.KEY_FILE);
		daStorage = DataAdapterManager.getDataAdapter(file, jConfig);
	}
	
	public Control createControl(final IWItemProperty wiProp, Composite parent) {
		DoubleControlComposite cmp = new DoubleControlComposite(parent, SWT.NONE);
		cmp.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		lazyCreateExpressionControl(wiProp, cmp);

		//container of the text area and the button
		Composite simpleContainer = new Composite(cmp.getSecondContainer(), SWT.NONE);
		GridLayout simpleLayout = new GridLayout(2, false);
		simpleLayout.verticalSpacing = 0;
		simpleLayout.marginHeight = 0;
		simpleLayout.marginWidth = 0;
		simpleContainer.setLayout(simpleLayout);
		simpleContainer.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		//create the combo
		final WritableComboMenuViewer viewer = new WritableComboMenuViewer(simpleContainer, WritableComboMenuViewer.NO_IMAGE);
		
		//crate and initialize the button
		Button browseButton = new Button(simpleContainer, SWT.PUSH);
		browseButton.setText("...");
		browseButton.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				String currentPropertyValue = wiProp.getPropertyEditor().getPropertyValue(wiProp.getPropertyName());
				IFile file = (IFile) jConfig.get(FileUtils.KEY_FILE);
				SelectDefaultDatasetWizard defaultDAwizard = new SelectDefaultDatasetWizard(currentPropertyValue, file);
				WizardDialog defaultDAdialog = new WizardDialog(UIUtils.getShell(), defaultDAwizard);
				if (defaultDAdialog.open() == WizardDialog.OK){
					if (wiProp.isRefresh())
						return;
					wiProp.setValue(defaultDAwizard.getValue(), null);
				}
			}	
		});
		
		Control simpleControl = viewer.getControl();
		cmp.getSecondContainer().setData(viewer);
		cmp.setSimpleControlToHighlight(simpleContainer);
		
		GridData comboData = new GridData(GridData.FILL_HORIZONTAL);
		comboData.verticalAlignment = SWT.CENTER;
		comboData.grabExcessVerticalSpace = true;
		simpleControl.setLayoutData(comboData);
		
		//Populate the combo
		JRDesignDataset currentDataset = jConfig.getJasperDesign().getMainDesignDataset();
		List<ComboItem> items = new ArrayList<ComboItem>();
		if (currentDataset != null) {
			for (int i = 0; i < daStorage.length; i++) {
				final ADataAdapterStorage s = daStorage[i];
				if (!(s instanceof PreferencesDataAdapterStorage)){
					for (DataAdapterDescriptor d : s.getDataAdapterDescriptors(currentDataset)) {
						Pair<DataAdapterDescriptor, ADataAdapterStorage> value = new Pair<DataAdapterDescriptor, ADataAdapterStorage>(d, s);
						ComboItem m1 = new ComboItem(s.getLabel(d), true, d.getIcon(16), i, d, value);
						items.add(m1);
					}
				}
				if (!s.getDataAdapterDescriptors(currentDataset).isEmpty() && i < daStorage.length - 1
						&& !daStorage[i + 1].getDataAdapterDescriptors(currentDataset).isEmpty()){
					items.add(new ComboItemSeparator(i));
				}
			}				
		}
		
		//add the selection listneer
		viewer.addSelectionListener(new ComboItemAction() {			
			@SuppressWarnings("unchecked")
			@Override
			public void exec() {
				if (wiProp.isRefresh())
					return;
				ComboItem selectedElement = viewer.getSelectedItem();
				if (selectedElement.getValue() instanceof Pair){
					//it is an entry selected by the combo
					Pair<DataAdapterDescriptor, ADataAdapterStorage> value = (Pair<DataAdapterDescriptor, ADataAdapterStorage>)selectedElement.getValue();
					String daURL = value.getValue().getUrl(value.getKey());
					if (daURL != null){
						wiProp.setValue(daURL, null);
					}
				} else {
					//it is a value typed by the user
					String tValue = viewer.getText();
					wiProp.setValue(tValue, null);
				}
			}
		});
		viewer.setItems(items);
		
		if (isReadOnly()){
			simpleControl.setEnabled(false);
			browseButton.setEnabled(false);
		} else {
			setupContextMenu(simpleControl, wiProp);
		}
		
		cmp.switchToSecondContainer();
		return cmp;
	}

	@Override
	public void update(Control c, IWItemProperty wip) {
		DoubleControlComposite cmp = (DoubleControlComposite) wip.getControl();
		boolean isFallback = false;
		if (wip.isExpressionMode()) {
			lazyCreateExpressionControl(wip, cmp);
			Text txt = (Text) cmp.getFirstContainer().getData();
			super.update(txt, wip);
			cmp.switchToFirstContainer();
		} else {
			WritableComboMenuViewer combo = (WritableComboMenuViewer) cmp.getSecondContainer().getData();
			String v = wip.getStaticValue();
			if (v == null && wip.getFallbackValue() != null){
				v = wip.getFallbackValue().toString();
				isFallback = true;
			}
			combo.setText(Misc.nvl(v));
			combo.setToolTipText(getToolTip());
			changeFallbackForeground(isFallback, combo.getControl());
			cmp.switchToSecondContainer();
		}
	}
	
	@Override
	public ItemPropertyDescription<String> clone(){
		JRDataAdapterPropertyDescription result = new JRDataAdapterPropertyDescription();
		result.defaultValue = defaultValue;
		result.description = description;
		result.jConfig = jConfig;
		result.label = label;
		result.mandatory = mandatory;
		result.name = name;
		result.fallbackValue = fallbackValue;
		return result;
	}
	
	@Override
	public ItemPropertyDescription<?> getInstance(WidgetsDescriptor cd, WidgetPropertyDescriptor cpd, JasperReportsConfiguration jConfig) {
		JRDataAdapterPropertyDescription result = new JRDataAdapterPropertyDescription(cpd.getName(), cd.getLocalizedString(cpd.getLabel()), cd.getLocalizedString(cpd.getDescription()), cpd.isMandatory(), cpd.getDefaultValue(), jConfig);
		result.setReadOnly(cpd.isReadOnly());
		result.setFallbackValue(cpd.getFallbackValue());
		return result;
	}
}
