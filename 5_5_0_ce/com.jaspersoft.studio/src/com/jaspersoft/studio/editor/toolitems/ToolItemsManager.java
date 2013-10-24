/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.editor.toolitems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.internal.provisional.action.ICoolBarManager2;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class ToolItemsManager {
	public void init() {
		IConfigurationElement[] cToolItems = Platform.getExtensionRegistry().getConfigurationElementsFor(
				JaspersoftStudioPlugin.PLUGIN_ID, "toolitems"); //$NON-NLS-1$

		for (IConfigurationElement e : cToolItems) {
			ToolItem ti = new ToolItem();
			ti.setId(e.getAttribute("id"));
			ti.setActionID(e.getAttribute("ActionID"));
			try {
				if (e.getAttribute("contributionItemClass") != null) {
					Object obj = e.createExecutableExtension("contributionItemClass");
					if (obj instanceof IContributionItem)
						ti.setContribClass((IContributionItem) obj);
				}
			} catch (CoreException e1) {
				e1.printStackTrace();
			}
			ti.setLabel(e.getAttribute("label"));
			ti.setTooltip(e.getAttribute("tooltip"));
			ti.setIcon(e.getAttribute("icon"));
			for (IConfigurationElement ch : e.getChildren()) {
				ti.addClass(ch.getAttribute("class"));
			}
			titems.put(ti.getId(), ti);
		}

		IConfigurationElement[] cToolItemSets = Platform.getExtensionRegistry().getConfigurationElementsFor(
				JaspersoftStudioPlugin.PLUGIN_ID, "toolitemsets"); //$NON-NLS-1$  
		for (IConfigurationElement e : cToolItemSets) {
			ToolItemsSet tis = new ToolItemsSet();
			tis.setId(e.getAttribute("id"));
			tis.setName(e.getAttribute("name"));
			tis.setDescription(e.getAttribute("description"));
			tis.setVisibility(new Boolean(e.getAttribute("visibility")));
			tis.setToolbarUri(e.getAttribute("toolbaruri"));
			tis.setMenuUri(e.getAttribute("menuuri"));
			for (IConfigurationElement ch : e.getChildren()) {
				String id = ch.getAttribute("toolitemID");
				ToolItem ti = titems.get(id);
				if (ti != null)
					tis.addToolItems(ti);
			}
			sets.add(tis);
		}
	}

	private List<ToolItemsSet> sets = new ArrayList<ToolItemsSet>();
	private Map<String, ToolItem> titems = new HashMap<String, ToolItem>();

	public List<ToolItemsSet> getSets() {
		return sets;
	}

	public static boolean isToolbarVisible(ToolItemsSet ts, List<?> sobjects) {
		if (sobjects != null && !sobjects.isEmpty()) {
			Object obj = sobjects.get(0);
			if (obj instanceof APropertyNode) {
				JasperReportsConfiguration jConfig = ((APropertyNode) obj).getJasperConfiguration();
				if (jConfig != null)
					return jConfig.getPropertyBoolean(ts.getId(), true);
			}
		}
		return JaspersoftStudioPlugin.getInstance().getPreferenceStore().getBoolean(ts.getId());
	}

	public static IContributionItem findToolbar(ICoolBarManager2 cbm2, String tbarid) {
		for (IContributionItem ci : cbm2.getItems()) {
			if (ci.getId().equals(tbarid))
				return ci;
		}
		return null;
	}
}
