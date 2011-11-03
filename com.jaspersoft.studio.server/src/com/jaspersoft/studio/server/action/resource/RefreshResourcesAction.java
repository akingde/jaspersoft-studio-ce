package com.jaspersoft.studio.server.action.resource;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Display;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.server.WSClientHelper;
import com.jaspersoft.studio.server.model.MResource;
import com.jaspersoft.studio.utils.UIUtils;

public class RefreshResourcesAction extends Action {
	private static final String REFRESHRESOURCEDESCRIPTOR = "REFRESHRESOURCEDESCRIPTOR";
	private TreeViewer treeViewer;

	public RefreshResourcesAction(TreeViewer treeViewer) {
		super();
		setId(REFRESHRESOURCEDESCRIPTOR);
		setText("Refresh");
		setToolTipText(Messages.common_delete);
		setImageDescriptor(JaspersoftStudioPlugin
				.getImageDescriptor("icons/eclipseicons/reload.gif"));
		setDisabledImageDescriptor(JaspersoftStudioPlugin
				.getImageDescriptor("icons/eclipseicons/reloaddgif"));
		this.treeViewer = treeViewer;
	}

	@Override
	public void run() {
		final TreeSelection s = (TreeSelection) treeViewer.getSelection();
		TreePath[] p = s.getPaths();
		for (int i = 0; i < p.length; i++) {
			final Object obj = p[i].getLastSegment();
			if (obj instanceof MResource) {
				ProgressMonitorDialog pm = new ProgressMonitorDialog(Display
						.getDefault().getActiveShell());
				try {
					pm.run(true, true, new IRunnableWithProgress() {
						public void run(IProgressMonitor monitor)
								throws InvocationTargetException,
								InterruptedException {
							try {
								MResource res = (MResource) obj;
								WSClientHelper.refreshResource(res, monitor);
								Display.getDefault().asyncExec(new Runnable() {

									public void run() {
										treeViewer.refresh(true);
										treeViewer.setSelection(s);
									}
								});

							} catch (Throwable e) {
								throw new InvocationTargetException(e);
							} finally {
								monitor.done();
							}
						}

					});
				} catch (InvocationTargetException e) {
					UIUtils.showError(e);
				} catch (InterruptedException e) {
					UIUtils.showError(e);
				}
			}
		}
	}
}
