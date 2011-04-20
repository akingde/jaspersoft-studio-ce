/*
 * JasperReports - Free Java Reporting Library.
 * Copyright (C) 2001 - 2009 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of JasperReports.
 *
 * JasperReports is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JasperReports is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with JasperReports. If not, see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.editor.style.command;

import net.sf.jasperreports.engine.JRSimpleTemplate;
import net.sf.jasperreports.engine.JRTemplateReference;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.gef.commands.Command;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.dialogs.FilteredResourcesSelectionDialog;

import com.jaspersoft.studio.model.style.MStyleTemplateReference;
import com.jaspersoft.studio.model.style.MStyles;
import com.jaspersoft.studio.model.style.MStylesTemplate;
/*
 * link nodes & together.
 * 
 * @author Chicu Veaceslav
 */
public class CreateStyleTemplateReferenceCommand extends Command {

	private JRTemplateReference jrTemplate;

	private JRSimpleTemplate jrDesign;

	private int index;

	/**
	 * Instantiates a new creates the style template command.
	 * 
	 * @param destNode
	 *          the dest node
	 * @param srcNode
	 *          the src node
	 * @param index
	 *          the index
	 */
	public CreateStyleTemplateReferenceCommand(MStylesTemplate destNode, MStyleTemplateReference srcNode, int index) {
		super();
		this.jrDesign = (JRSimpleTemplate) destNode.getValue();
		this.index = index;
		if (srcNode != null && srcNode.getValue() != null)
			this.jrTemplate = (JRTemplateReference) srcNode.getValue();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	@Override
	public void execute() {
		createObject();
		if (jrTemplate != null) {
			if (index < 0 || index > jrDesign.getIncludedTemplatesList().size())
				jrDesign.addIncludedTemplate(jrTemplate);
			else
				jrDesign.addIncludedTemplate(index, jrTemplate);
		}
	}

	private void createObject() {
		if (jrTemplate == null) {
			FilteredResourcesSelectionDialog fd = new FilteredResourcesSelectionDialog(Display.getCurrent().getActiveShell(),
					false, ResourcesPlugin.getWorkspace().getRoot(), IResource.FILE);
			fd.setInitialPattern("*.jrtx");//$NON-NLS-1$
			if (fd.open() == Dialog.OK) {
				IFile file = (IFile) fd.getFirstResult();

				this.jrTemplate = MStyleTemplateReference.createJRTemplate();
				jrTemplate.setLocation(file.getProjectRelativePath().toPortableString());

			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#canUndo()
	 */
	@Override
	public boolean canUndo() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	@Override
	public void undo() {
		jrDesign.removeIncludedTemplate(jrTemplate);
	}
}
