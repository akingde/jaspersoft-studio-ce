package com.jaspersoft.studio.editor.toolitems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.action.IContributionItem;

import com.jaspersoft.studio.JaspersoftStudioPlugin;

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
}
