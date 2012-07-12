package com.jaspersoft.studio.editor.layout;

import java.util.List;

import net.sf.jasperreports.engine.JRPropertiesHolder;

import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.ui.IWorkbenchPart;

import com.jaspersoft.studio.editor.action.layout.LayoutAction;

public class LayoutManager {
	public static ILayout getLayout(JRPropertiesHolder[] elements) {
		for (JRPropertiesHolder pholder : elements) {
			String prop = pholder.getPropertiesMap().getProperty(ILayout.KEY);
			if (prop != null)
				return instLayout(prop);
		}
		return new FreeLayout();
	}

	public static ILayout instLayout(String prop) {
		try {
			return (ILayout) Class.forName(prop).newInstance();
		} catch (InstantiationException e) {
		} catch (IllegalAccessException e) {
		} catch (ClassNotFoundException e) {
		}
		return new FreeLayout();
	}

	private static final Class<?>[] layouts = new Class<?>[] { HorizontalRowLayout.class, VerticalRowLayout.class };
	private static ILayout[] LAYOUTNAMES;

	public static void addActions(ActionRegistry registry, IWorkbenchPart part, List<String> selectionActions) {
		for (Class<?> id : layouts) {
			IAction action = new LayoutAction(part, id);
			registry.registerAction(action);
			selectionActions.add(action.getId());
		}
	}

	public static void addMenu(MenuManager submenu, ActionRegistry actionRegistry) {
		for (Class<?> id : layouts) {
			IAction action = actionRegistry.getAction(id.getName());
			if (action.isEnabled())
				submenu.add(action);
		}
	}

	public static ILayout[] getAllLayouts() {
		if (LAYOUTNAMES == null)
			LAYOUTNAMES = new ILayout[] { new FreeLayout(), new HorizontalRowLayout(), new VerticalRowLayout() };
		return LAYOUTNAMES;
	}
}
