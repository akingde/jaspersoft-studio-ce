package com.jaspersoft.studio.editor.gef.decorator.error;

import java.util.List;

import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.actions.RetargetAction;

import com.jaspersoft.studio.editor.gef.decorator.ErrorDecorator;
import com.jaspersoft.studio.editor.gef.decorator.IDecorator;
import com.jaspersoft.studio.editor.gef.decorator.IElementDecorator;
import com.jaspersoft.studio.editor.gef.figures.ComponentFigure;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class PositionErrorDecorator implements IElementDecorator {

	private IDecorator decorator = new ErrorDecorator();

	@Override
	public void setupFigure(ComponentFigure fig, JasperReportsConfiguration jConfig) {
		fig.removeDecorator(decorator);
		// check if we should show this decorator
		if (true) {
			fig.addDecorator(decorator);
		}
	}

	@Override
	public void registerActions(ActionRegistry registry, List<String> selectionActions, GraphicalViewer gviewer,
			IWorkbenchPart part) {

		gviewer.setProperty(ShowErrorsAction.ID, true);
		IAction action = new ShowErrorsAction(gviewer);
		registry.registerAction(action);
	}

	@Override
	public void buildContextMenu(ActionRegistry registry, EditPartViewer viewer, IMenuManager menu) {
	}

	@Override
	public RetargetAction[] buildMenuActions() {
		return new RetargetAction[] { new RetargetAction(ShowErrorsAction.ID, Messages.ShowErrorsAction_title,
				IAction.AS_CHECK_BOX) };
	}

	@Override
	public void contribute2Menu(ActionRegistry registry, MenuManager menuManager) {
		menuManager.add(registry.getAction(ShowErrorsAction.ID));
	}

}
