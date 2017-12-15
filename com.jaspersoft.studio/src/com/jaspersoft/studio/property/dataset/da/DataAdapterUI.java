/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.dataset.da;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.property.dataset.DatasetUtil;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.data.DataAdapter;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JasperDesign;

public class DataAdapterUI {

	private CTabItem bptab;

	public void refreshDaUI(CTabFolder tabFolder, Color background, JasperDesign jd, JRDesignDataset dataset,
			JasperReportsConfiguration jConfig) {
		DataAdapter da = DatasetUtil.refreshDataAdapter(jd, dataset, jConfig);
		List<IDataAdapterQueryEditorUI> ui = getUI(da);
		if (ui != null) {
			if (bptab != null)
				bptab.dispose();

			bptab = new CTabItem(tabFolder, SWT.NONE);
			bptab.setText("Data Adapter");

			Composite parent = tabFolder;
			if (ui.size() > 1) {
				parent = new Composite(tabFolder, SWT.NONE);
				parent.setLayout(new GridLayout());
				parent.setBackground(background);
				parent.setBackgroundMode(SWT.INHERIT_FORCE);
			}
			for (IDataAdapterQueryEditorUI daui : ui) {
				Composite c = daui.create(parent, da, dataset, jConfig);
				if (c != null) {
					c.setBackground(background);
					c.setBackgroundMode(SWT.INHERIT_FORCE);
					bptab.setControl(c);
				}
			}
		} else {
			if (bptab != null) {
				bptab.dispose();
				bptab = null;
			}
		}
	}

	private List<IDataAdapterQueryEditorUI> getUI(DataAdapter da) {
		if (da != null) {
			List<IDataAdapterQueryEditorUI> list = new ArrayList<IDataAdapterQueryEditorUI>();
			for (IDataAdapterQueryEditorUI item : classmap) {
				if (item.isForDataAdapter(da))
					list.add(item);
			}
			if (!list.isEmpty())
				return list;
		}
		return null;
	}

	private List<IDataAdapterQueryEditorUI> classmap = new ArrayList<IDataAdapterQueryEditorUI>();

	public DataAdapterUI() {
		IConfigurationElement[] config = Platform.getExtensionRegistry()
				.getConfigurationElementsFor(JaspersoftStudioPlugin.PLUGIN_ID, "daQueryUI"); //$NON-NLS-1$
		for (IConfigurationElement e : config) {
			try {
				classmap.add((IDataAdapterQueryEditorUI) e.createExecutableExtension("DAQueryUIClass")); //$NON-NLS-1$
			} catch (CoreException ex) {
				System.out.println(ex.getMessage());
			}
		}
	}
}
