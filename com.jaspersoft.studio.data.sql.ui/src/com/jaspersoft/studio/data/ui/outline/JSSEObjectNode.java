package com.jaspersoft.studio.data.ui.outline;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.graphics.Image;
import org.eclipse.xtext.ui.editor.outline.IOutlineNode;
import org.eclipse.xtext.ui.editor.outline.impl.EObjectNode;

public class JSSEObjectNode extends EObjectNode {
	private EObject eObject;

	public JSSEObjectNode(EObject eObject, IOutlineNode parent, Image image, Object text, boolean isLeaf) {
		super(eObject, parent, image, text, isLeaf);
		this.eObject = eObject;
	}

	public EObject getEObject() {
		return eObject;
	}
}
