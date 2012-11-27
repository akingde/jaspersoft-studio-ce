package com.jaspersoft.studio.editor.toolitems;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.action.IContributionItem;

public class ToolItem {
	private String id;

	private String actionID;
	private IContributionItem contributionItem;

	private String label;
	private String tooltip;
	private String icon;

	private List<Class<?>> classes = new ArrayList<Class<?>>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getActionID() {
		return actionID;
	}

	public void setActionID(String actionID) {
		this.actionID = actionID;
	}

	public IContributionItem getContributionItem() {
		return contributionItem;
	}

	public void setContribClass(IContributionItem contribClass) {
		this.contributionItem = contribClass;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getTooltip() {
		return tooltip;
	}

	public void setTooltip(String tooltip) {
		this.tooltip = tooltip;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public List<Class<?>> getClasses() {
		return classes;
	}

	public void addClass(String clazz) {
		try {
			classes.add(Class.forName(clazz));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
