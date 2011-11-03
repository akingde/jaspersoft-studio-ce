package com.jaspersoft.studio.server.action.resource;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.ui.actions.Clipboard;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.server.model.MResource;

public class CutResourceAction extends Action {
	private static final String DELETERESOURCEDESCRIPTOR = "DELETERESOURCEDESCRIPTOR";
	private TreeViewer treeViewer;

	public CutResourceAction(TreeViewer treeViewer) {
		super();
		setId(DELETERESOURCEDESCRIPTOR);
		setText(Messages.common_cut);
		setToolTipText(Messages.common_cut);
		ISharedImages sharedImages = PlatformUI.getWorkbench()
				.getSharedImages();
		setImageDescriptor(sharedImages
				.getImageDescriptor(ISharedImages.IMG_TOOL_CUT));
		setDisabledImageDescriptor(sharedImages
				.getImageDescriptor(ISharedImages.IMG_TOOL_CUT_DISABLED));
		this.treeViewer = treeViewer;
	}

	@Override
	public void run() {
		TreeSelection s = (TreeSelection) treeViewer.getSelection();
		TreePath[] p = s.getPaths();
		List<MResource> rlist = new ArrayList<MResource>();
		for (int i = 0; i < p.length; i++) {
			final Object obj = p[i].getLastSegment();
			if (obj instanceof MResource) {
				((MResource) obj).setCut(true);
				rlist.add((MResource) obj);
			}
		}
		if (!rlist.isEmpty())
			Clipboard.getDefault().setContents(rlist);
	}
}
