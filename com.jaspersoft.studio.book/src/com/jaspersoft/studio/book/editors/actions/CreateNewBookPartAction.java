/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.book.editors.actions;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.JRSubreportParameter;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JRDesignPart;
import net.sf.jasperreports.engine.design.JRDesignSubreport;
import net.sf.jasperreports.engine.design.JRDesignSubreportParameter;
import net.sf.jasperreports.parts.subreport.StandardSubreportPartComponent;

import org.eclipse.gef.editparts.AbstractEditPart;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchPart;

import com.jaspersoft.studio.book.JRBookActivator;
import com.jaspersoft.studio.book.messages.Messages;
import com.jaspersoft.studio.book.model.MReportPart;
import com.jaspersoft.studio.book.model.MReportPartContainer;
import com.jaspersoft.studio.book.model.commands.CreatePartCommand;
import com.jaspersoft.studio.book.wizards.AddPageWizardPage;
import com.jaspersoft.studio.editor.outline.actions.ACreateAndSelectAction;
import com.jaspersoft.studio.model.subreport.MSubreport;
import com.jaspersoft.studio.model.subreport.command.wizard.NewSubreportPage;
import com.jaspersoft.studio.model.subreport.command.wizard.SubreportWizard;

/**
 * Add to the section a book part from a file in the filesystem
 * 
 * @author Marco
 *
 */
public class CreateNewBookPartAction extends ACreateAndSelectAction {

	public static final String ID = "create_book_part"; //$NON-NLS-1$
	
	public CreateNewBookPartAction(IWorkbenchPart part) {
		super(part);
	}

	@Override
	protected void init() {
		super.init();
		setText(Messages.CreateNewBookPartAction_actionTitle);
		setToolTipText(Messages.CreateNewBookPartAction_actionTooltip);
		setId(ID);
		setImageDescriptor(JRBookActivator.getDefault().getImageDescriptor("/icons/add.png")); //$NON-NLS-1$
		setEnabled(false);
	}
	
	@Override
	protected boolean calculateEnabled() {
		ISelection s = getSelection();
		if (s instanceof StructuredSelection) {
			Object obj = ((StructuredSelection) s).getFirstElement();
			if(obj instanceof AbstractEditPart && ((AbstractEditPart)obj).getModel() instanceof MReportPartContainer) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public void run() {
		ISelection s = getSelection();
		if (s instanceof StructuredSelection) {
			Object obj = ((StructuredSelection) s).getFirstElement();
			if(obj instanceof AbstractEditPart) {
				Object model = ((AbstractEditPart)obj).getModel();
				if (model instanceof MReportPartContainer) {
					MReportPartContainer partContainer = (MReportPartContainer)model;
					SubreportWizard wizard = new SubreportWizard(){
						
						protected NewSubreportPage getSubreportPage() {
							return new AddPageWizardPage();
						};
					};
					WizardDialog dialog = new WizardDialog(Display.getDefault().getActiveShell(), wizard);
					wizard.setConfig(partContainer.getJasperConfiguration(), false);
					dialog.create();
					if (dialog.open() == Dialog.OK) {
						MSubreport srcNode = wizard.getSubreport();
						JRDesignSubreport subreport = srcNode.getValue();
						String subReportExpression = subreport.getExpression().getText();
						JRDesignPart newBookPart = MReportPart.createJRElement(new JRDesignExpression(subReportExpression));
						setDataParameters(newBookPart, subreport.getConnectionExpression(), subreport.getDataSourceExpression(), subreport.getParameters());
						getCommandStack().execute(new CreatePartCommand(partContainer, newBookPart, -1));
					}
					/*MReportPartContainer container = (MReportPartContainer) model;
					List<String> reportFiles = selectReportFiles();
					if(!reportFiles.isEmpty()){
						for(String f : reportFiles){
							JRDesignPart newBookPart = MReportPart.createJRElement(new JRDesignExpression("\""+f+"\""));
							getCommandStack().execute(new CreatePartCommand(container, newBookPart, -1));
						}
					}*/
				}
			}
		}
	}
	
	/**
	 * Set the connection and datasource expressions on a part, this values are set only
	 * if they are not null. Set also the part parameters if not null
	 * 
	 * @param newPart The part where the expressions should be set, must have a component of 
	 * type StandardSubreportPartComponent
	 * @param connectionExp the connection expression or null to avoid to set it
	 * @param datasourceExp the datasource expression or null to avoid to set it
	 * @param parameters the parameters or null to avoid to set them
	 */
	private void setDataParameters(JRDesignPart newPart, JRExpression connectionExp, JRExpression datasourceExp, JRSubreportParameter[] parameters){
		if (newPart.getComponent() instanceof StandardSubreportPartComponent){
			StandardSubreportPartComponent subPart = (StandardSubreportPartComponent)newPart.getComponent();
			if (connectionExp != null) {
				JRDesignSubreportParameter param = new JRDesignSubreportParameter();
				param.setName(MReportPart.REPORT_CONNECTION_PROPERTY);
				param.setExpression(connectionExp);
				try {
					subPart.addParameter(param);
				} catch (JRException e) {
					e.printStackTrace();
					JRBookActivator.getDefault().logError(e);
				}
			}
			if (datasourceExp != null){
				JRDesignSubreportParameter param = new JRDesignSubreportParameter();
				param.setName(MReportPart.REPORT_DATASOURCE_PROPERTY);
				param.setExpression(datasourceExp);
				try {
					subPart.addParameter(param);
				} catch (JRException e) {
					e.printStackTrace();
					JRBookActivator.getDefault().logError(e);
				}
			}
			if (parameters != null){
				for(JRSubreportParameter parameter : parameters){
					if (!subPart.getParametersMap().containsKey(parameter.getName())){
						JRSubreportParameter newParam = (JRSubreportParameter)parameter.clone();
						try {
							subPart.addParameter(newParam);
						} catch (JRException e) {
							e.printStackTrace();
							JRBookActivator.getDefault().logError(e);
						}
					}
				}
			}
		}
	}
	
	/**
	 * Open the file dialog for jrxml files and return 
	 * the absolute path of each selected file
	 * 
	 * @return not null list of the selected files absolute path
	 */
	/*private List<String> selectReportFiles(){
		FileDialog fd = new FileDialog(UIUtils.getShell(), SWT.OPEN | SWT.MULTI);
		fd.setText(Messages.common_open);
		String[] filterExt = { "*.jrxml" }; //$NON-NLS-1$ 
		fd.setFilterExtensions(filterExt);
		String selected = fd.open();
		List<String> files = new ArrayList<String>();
		if (selected != null) {
			String[] fileNames = fd.getFileNames();
			File parentFolder = new File(selected).getParentFile();
			for(String fileName : fileNames){
				File actualFile = new File(parentFolder, fileName);
				selected = actualFile.getAbsolutePath();
				if (actualFile.isFile()){
					files.add(selected);
				}
			}
		}
		return files;
	}*/

}
