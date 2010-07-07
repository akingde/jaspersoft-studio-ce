package com.jaspersoft.studio.property.descriptor.classname;

import org.eclipse.jface.fieldassist.IContentProposalProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.PropertyDescriptor;

public class ContentAssistFieldDescriptor extends PropertyDescriptor {
	protected char[] completionProposalAutoActivationCharacters;
	protected IContentProposalProvider contentProposalProvider;

	/**
	 * Creates an property descriptor with the given id and display name.
	 * 
	 * @param id
	 *          the id of the property
	 * @param displayName
	 *          the name to display for the property
	 */
	public ContentAssistFieldDescriptor(Object id, String displayName, char[] completionProposalAutoActivationCharacters,
			IContentProposalProvider contentProposalProvider) {
		super(id, displayName);

		this.completionProposalAutoActivationCharacters = completionProposalAutoActivationCharacters;
		this.contentProposalProvider = contentProposalProvider;
	}

	/**
	 * The ContentAssistFieldPropertyDescriptor implementation of this IPropertyDescriptor method creates and returns a
	 * new TextCellEditor.
	 * 
	 * The editor is configured with the current validator if there is one.
	 * 
	 */
	@Override
	public CellEditor createPropertyEditor(Composite parent) {
		ContentAssistCellEditor editor = new ContentAssistCellEditor(parent, completionProposalAutoActivationCharacters,
				contentProposalProvider);
		if (getValidator() != null) {
			editor.setValidator(getValidator());
		}
		return editor;
	}
}
