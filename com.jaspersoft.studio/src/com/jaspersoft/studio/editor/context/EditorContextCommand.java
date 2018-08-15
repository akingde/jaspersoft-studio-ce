/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.context;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.State;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.internal.core.JavaProject;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.handlers.RadioState;

import com.jaspersoft.studio.JaspersoftStudioPlugin;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.Misc;

public class EditorContextCommand extends AbstractHandler {

	public EditorContextCommand() {
		UIUtils.getDisplay().asyncExec(this::listenSelection);
	}

	private void listenSelection() {
		IWorkbenchWindow w = JaspersoftStudioPlugin.getInstance().getWorkbench().getActiveWorkbenchWindow();
		ICommandService service = w.getService(ICommandService.class);
		Command cmd = service.getCommand("com.jaspersoft.studio.editor.context.type");
		ISelectionService ss = w.getSelectionService();
		ss.addSelectionListener((part, selection) -> {
			if (selection instanceof IStructuredSelection && ((IStructuredSelection) selection).size() == 1) {
				Object obj = ((IStructuredSelection) selection).getFirstElement();
				if (obj instanceof JavaProject)
					obj = ((JavaProject) obj).getProject();
				if (obj instanceof IResource && isSelectable((IResource) obj)) {
					setBaseEnabled(true);
					cmd.setEnabled(true);
					try {
						String p = ((IResource) obj).getPersistentProperty(EditorContextUtil.EC_KEY);

						State state = cmd.getState(RadioState.STATE_ID);
						if (state != null)
							if (p == null)
								state.setValue("reset");
							else
								state.setValue(Misc.nvl(p, AEditorContext.NAME));
					} catch (CoreException e) {
						e.printStackTrace();
					}
					return;
				}
			}
			setBaseEnabled(false);
			cmd.setEnabled(false);
		});
	}

	private boolean isSelectable(IResource obj) {
		return !((obj instanceof IFolder && obj.getName().equals("JR-INF"))
				|| (obj.getParent() != null && !isSelectable(obj.getParent())));
	}

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		ISelection sel = HandlerUtil.getActiveWorkbenchWindow(event).getActivePage().getSelection();
		if (sel instanceof IStructuredSelection) {
			Object obj = ((IStructuredSelection) sel).getFirstElement();
			if (obj instanceof JavaProject)
				obj = ((JavaProject) obj).getProject();
			if (obj instanceof IResource)
				try {
					if (HandlerUtil.matchesRadioState(event))
						return null;
					String state = event.getParameter(RadioState.PARAMETER_ID);

					((IResource) obj).setPersistentProperty(EditorContextUtil.EC_KEY, getPersistentState(state));

					HandlerUtil.updateRadioState(event.getCommand(), state);
					EditorContextUtil.fireContextChanged(((IResource) obj));
				} catch (CoreException e) {
					UIUtils.showError(e);
				}
		}
		return null;
	}

	private String getPersistentState(String state) {
		if (state != null && state.equals("reset"))
			return null;
		if (state != null && state.equals(AEditorContext.NAME))
			return state;
		return state;
	}

}
