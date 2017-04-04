/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.widgets.framework.ui;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.data.DataAdapterManager;
import com.jaspersoft.studio.data.storage.ADataAdapterStorage;
import com.jaspersoft.studio.property.combomenu.ComboItem;
import com.jaspersoft.studio.property.combomenu.ComboItemAction;
import com.jaspersoft.studio.property.combomenu.ComboItemSeparator;
import com.jaspersoft.studio.property.combomenu.WritableComboMenuViewer;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;
import com.jaspersoft.studio.widgets.framework.IWItemProperty;
import com.jaspersoft.studio.widgets.framework.manager.DoubleControlComposite;
import com.jaspersoft.studio.widgets.framework.model.WidgetPropertyDescriptor;
import com.jaspersoft.studio.widgets.framework.model.WidgetsDescriptor;

import net.sf.jasperreports.eclipse.util.FileUtils;
import net.sf.jasperreports.engine.JRQuery;
import net.sf.jasperreports.engine.design.JRDesignDataset;

/**
 * Widget descriptor used to select a data adapter for Jaspersot Studio preview
 * 
 * @author Orlandin Marco
 *
 */
public class JSSDataAdapterPropertyDescription extends AbstractExpressionPropertyDescription<String> {
	
	private ADataAdapterStorage[] daStorage = new ADataAdapterStorage[0];

	public JSSDataAdapterPropertyDescription() {
		super();
	}

	public JSSDataAdapterPropertyDescription(String name, String label, String description, boolean mandatory, String defaultValue) {
		super(name, label, description, mandatory, defaultValue);
	}

	public JSSDataAdapterPropertyDescription(String name, String label, String description, boolean mandatory) {
		super(name, label, description, mandatory);
	}
	
	public Control createControl(final IWItemProperty wiProp, Composite parent) {
		DoubleControlComposite cmp = new DoubleControlComposite(parent, SWT.NONE);
		cmp.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Control expressionControl = super.createControl(wiProp, cmp.getFirstContainer());
		cmp.getFirstContainer().setData(expressionControl);
		cmp.setExpressionControlToHighlight(expressionControl);

		final WritableComboMenuViewer viewer = new WritableComboMenuViewer(cmp.getSecondContainer(), SWT.READ_ONLY);
		Control simpleControl = viewer.getControl();
		cmp.getSecondContainer().setData(viewer);
		cmp.setSimpleControlToHighlight(simpleControl);
		
		GridData comboData = new GridData(GridData.FILL_HORIZONTAL);
		comboData.verticalAlignment = SWT.CENTER;
		comboData.grabExcessVerticalSpace = true;
		simpleControl.setLayoutData(comboData);
		
		JRDesignDataset currentDataset = jConfig.getJasperDesign().getMainDesignDataset();
		JRQuery q = jConfig.getJasperDesign().getQuery();
		String language = q != null ? q.getLanguage() : null;
		List<ComboItem> items = new ArrayList<ComboItem>();
		if (currentDataset != null) {
			for (int i = 0; i < daStorage.length; i++) {
				final ADataAdapterStorage s = daStorage[i];
				for (DataAdapterDescriptor d : s.getDataAdapterDescriptors(currentDataset)) {
					if (language != null) {
						String[] langs = d.getLanguages();
						if (langs != null) {
							boolean exists = false;
							for (String l : langs)
								if (language.equalsIgnoreCase(l) || l.equals("*")) {
									exists = true;
									break;
								}
							if (!exists)
								continue;
						}
					}
					ComboItem m1 = new ComboItem(s.getLabel(d), true, d.getIcon(16), i, d, d);
					items.add(m1);
				}
				if (!s.getDataAdapterDescriptors(currentDataset).isEmpty() && i < daStorage.length - 1
						&& !daStorage[i + 1].getDataAdapterDescriptors(currentDataset).isEmpty()){
					items.add(new ComboItemSeparator(i));
				}
					
			}
		}
		
		viewer.addSelectionListener(new ComboItemAction() {			
			@Override
			public void exec() {
				if (wiProp.isRefresh())
					return;
				String tValue = viewer.getText();
				wiProp.setValue(tValue, null);
			}
		});
		viewer.setItems(items);
		
		if (isReadOnly()){
			simpleControl.setEnabled(false);
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
			combo.select(combo.getElementIndex(v));
			combo.setToolTipText(getToolTip());
			changeFallbackForeground(isFallback, combo.getControl());
			cmp.switchToSecondContainer();
		}
	}
	
	@Override
	public ItemPropertyDescription<String> clone(){
		JSSDataAdapterPropertyDescription result = new JSSDataAdapterPropertyDescription();
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
		IFile file = (IFile) jConfig.get(FileUtils.KEY_FILE);
		JSSDataAdapterPropertyDescription result = new JSSDataAdapterPropertyDescription(cpd.getName(), cd.getLocalizedString(cpd.getLabel()), cd.getLocalizedString(cpd.getDescription()), cpd.isMandatory(), cpd.getDefaultValue());
		result.daStorage = DataAdapterManager.getDataAdapter(file, jConfig);
		result.jConfig = jConfig; 
		result.setReadOnly(cpd.isReadOnly());
		result.setFallbackValue(cpd.getFallbackValue());
		return result;
	}
}
