package com.jaspersoft.studio.editor.layout;

import java.util.List;

import net.sf.jasperreports.engine.design.JRDesignElement;

import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.ui.IWorkbenchPart;

import com.jaspersoft.studio.editor.action.layout.LayoutAction;

public class LayoutManager {
	public static ILayout getLayout(JRDesignElement element) {
		String prop = element.getPropertiesMap().getProperty(ILayout.KEY);
		if (prop != null) {
			return instLayout(prop);
		}
		return new HorizontalRowLayout();
	}

	public static ILayout instLayout(String prop) {
		try {
			return (ILayout) Class.forName(prop).newInstance();
		} catch (InstantiationException e) {
		} catch (IllegalAccessException e) {
		} catch (ClassNotFoundException e) {
		}
		return new HorizontalRowLayout();
	}

	private static final Class<?>[] layouts = new Class<?>[] { FreeLayout.class, HorizontalRowLayout.class,
			VerticalRowLayout.class };

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
}
