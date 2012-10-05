/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer.
 * Copyright (C) 2005 - 2010 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of Jaspersoft Open Studio.
 *
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Jaspersoft Open Studio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with Jaspersoft Open Studio. If not, see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.dnd;

import org.eclipse.core.resources.IContainer;
import org.eclipse.ui.part.IDropActionDelegate;

import com.jaspersoft.studio.model.ANode;

/**
 * Performs dropping of gadgets into views that contain resources.
 */
public class NodePluginDropAdapter implements IDropActionDelegate {
	/**
	 * Method declared on IDropActionDelegate
	 */
	public boolean run(Object source, Object target) {
		if (target instanceof IContainer) {
			NodeTransfer transfer = NodeTransfer.getInstance();
			ANode[] gadgets = transfer.fromByteArray((byte[]) source);
			IContainer parent = (IContainer) target;
			for (int i = 0; i < gadgets.length; i++) {
				writeGadgetFile(parent, gadgets[i]);
			}
			return true;
		}
		// drop was not successful so return false
		return false;
	}

	private void writeGadgetFile(IContainer parent, ANode gadget) {
		// try {
		// IFile file = parent.getFile(new Path(gadget.getName()));
		// ByteArrayInputStream in = createFileContents(gadget);
		// if (file.exists()) {
		// file.setContents(in, IResource.NONE, null);
		// } else {
		// file.create(in, IResource.NONE, null);
		// }
		// } catch (CoreException e) {
		// e.printStackTrace();
		// }
	}

//	private void writeGadgetString(ANode gadget, StringBuffer buf, int depth) {
//		// for (int i = 0; i < depth; i++)
//		// buf.append('\t');
//		// buf.append(gadget.getName());
//		// buf.append('\n');
//		// List<INode> children = gadget.getChildren();
//		// for (INode n : children)
//		// writeGadgetString((ANode) n, buf, depth + 1);
//	}
}