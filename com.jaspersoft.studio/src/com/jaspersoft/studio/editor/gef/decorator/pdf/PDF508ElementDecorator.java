package com.jaspersoft.studio.editor.gef.decorator.pdf;

import java.util.List;

import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.actions.RetargetAction;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.editor.action.layout.LayoutAction;
import com.jaspersoft.studio.editor.action.size.Size2BorderAction;
import com.jaspersoft.studio.editor.gef.decorator.IElementDecorator;
import com.jaspersoft.studio.editor.gef.figures.ComponentFigure;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class PDF508ElementDecorator implements IElementDecorator {

	@Override
	public void setupFigure(ComponentFigure fig, JasperReportsConfiguration jConfig) {
		// TODO Auto-generated method stub

	}

	@Override
	public void registerActions(ActionRegistry registry, List<String> selectionActions, GraphicalViewer gviewer,
			IWorkbenchPart part) {
		gviewer.setProperty(ShowPDFTagsAction.ID, true);
		IAction action = new ShowPDFTagsAction(gviewer);
		registry.registerAction(action);

		// TODO add pdf actions here
	}

	@Override
	public void buildContextMenu(ActionRegistry registry, EditPartViewer viewer, IMenuManager menu) {
		MenuManager submenu = new MenuManager("PDF Fonts", JaspersoftStudioPlugin.getImageDescriptor("icons/layout-6.png"), //$NON-NLS-1$ //$NON-NLS-2$
				LayoutAction.ID);

		IAction action = registry.getAction(Size2BorderAction.ID_SIZE_HEIGHT);
		if (action.isEnabled())
			submenu.add(action);

		menu.add(submenu);
	}

	@Override
	public RetargetAction[] buildMenuActions() {
		return new RetargetAction[] { new RetargetAction(ShowPDFTagsAction.ID, Messages.ShowPDFTagsAction_title,
				IAction.AS_CHECK_BOX) };
	}

	@Override
	public void contribute2Menu(ActionRegistry registry, MenuManager menuManager) {
		menuManager.add(registry.getAction(ShowPDFTagsAction.ID));
	}
}
