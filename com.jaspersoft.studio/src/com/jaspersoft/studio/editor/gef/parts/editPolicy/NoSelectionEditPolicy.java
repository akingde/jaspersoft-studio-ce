package com.jaspersoft.studio.editor.gef.parts.editPolicy;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.Handle;
import org.eclipse.gef.Request;
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;

public class NoSelectionEditPolicy extends NonResizableEditPolicy {
	@Override
	protected List<?> createSelectionHandles() {
		List<?> list = new ArrayList<Handle>();
		createMoveHandle(list);
		return list;
	}

	@Override
	public boolean understandsRequest(Request request) {
		if (REQ_MOVE.equals(request.getType()))
			return isDragAllowed();
		return false;
	}

}
