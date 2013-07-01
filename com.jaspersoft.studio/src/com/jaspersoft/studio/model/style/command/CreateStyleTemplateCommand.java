/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.model.style.command;

import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JRDesignReportTemplate;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.gef.commands.Command;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.FilteredResourcesSelectionDialog;

import com.jaspersoft.studio.model.style.MStyleTemplate;
import com.jaspersoft.studio.model.style.MStyles;
import com.jaspersoft.studio.wizards.ContextHelpIDs;
/*
 * link nodes & together.
 * 
 * @author Chicu Veaceslav
 */
public class CreateStyleTemplateCommand extends Command {

	/** The jr template. */
	private JRDesignReportTemplate jrTemplate;

	/** The jr design. */
	private JasperDesign jrDesign;

	/** The index. */
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
	public CreateStyleTemplateCommand(MStyles destNode, MStyleTemplate srcNode, int index) {
		super();
		this.jrDesign = destNode.getJasperDesign();
		this.index = index;
		if (srcNode != null && srcNode.getValue() != null)
			this.jrTemplate = (JRDesignReportTemplate) srcNode.getValue();
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
			if (index < 0 || index > jrDesign.getTemplatesList().size())
				jrDesign.addTemplate(jrTemplate);
			else
				jrDesign.addTemplate(index, jrTemplate);
		}
	}

	private class FilteredHelpDialog extends FilteredResourcesSelectionDialog{

		public FilteredHelpDialog(Shell shell, boolean multi, IContainer container, int typesMask) {
			super(shell, multi, container, typesMask);
		}

		/**
		 * Set the help data that should be seen in this step
		 */
		@Override
		protected void configureShell(Shell shell){
			super.configureShell(shell);
			PlatformUI.getWorkbench().getHelpSystem().setHelp(shell, ContextHelpIDs.WIZARD_STYLE_TEMPLATE_LOAD);
		}
		
	}
	
	private void createObject() {
		if (jrTemplate == null) {
			FilteredResourcesSelectionDialog fd = new FilteredHelpDialog(Display.getCurrent().getActiveShell(),
					false, ResourcesPlugin.getWorkspace().getRoot(), IResource.FILE);
			fd.setInitialPattern("*.jrtx");//$NON-NLS-1$
			if (fd.open() == Dialog.OK) {
				IFile file = (IFile) fd.getFirstResult();

				this.jrTemplate = MStyleTemplate.createJRTemplate();

				JRDesignExpression jre = new JRDesignExpression();
				jre.setText("\"" + file.getProjectRelativePath().toPortableString() + "\"");//$NON-NLS-1$ //$NON-NLS-2$
				((JRDesignReportTemplate) jrTemplate).setSourceExpression(jre);

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
		jrDesign.removeTemplate(jrTemplate);
	}
}
