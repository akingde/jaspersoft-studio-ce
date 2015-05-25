/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.editor.action;

import java.io.File;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.eclipse.builder.JasperReportErrorHandler;
import net.sf.jasperreports.eclipse.builder.JasperReportsBuilder;
import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.FileUtils;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.jface.action.IMenuCreator;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MenuAdapter;
import org.eclipse.swt.events.MenuEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPart;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.backward.ConsoleExecuter;
import com.jaspersoft.studio.backward.JRBackwardManager;
import com.jaspersoft.studio.backward.JRDefinition;
import com.jaspersoft.studio.backward.wizard.DownloadJRWizardDialog;
import com.jaspersoft.studio.editor.AbstractJRXMLEditor;
import com.jaspersoft.studio.editor.JrxmlEditor;
import com.jaspersoft.studio.editor.preview.view.control.JRErrorHandler;
import com.jaspersoft.studio.editor.preview.view.control.JRMarkerErrorHandler;
import com.jaspersoft.studio.editor.report.AbstractVisualEditor;
import com.jaspersoft.studio.editor.report.ReportEditor;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.utils.Console;
import com.jaspersoft.studio.utils.SelectionHelper;
import com.jaspersoft.studio.utils.SubreportsUtil;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class CompileAction extends SelectionAction implements IMenuCreator {
	
	public static final String ID = "compileAction"; //$NON-NLS-1$
	
	/**
	 * Drop down menu of the action, where the backward compatibility options are shown
	 */
	private Menu menu;

	/**
	 * Listener called when the compilation with an older version of JR is requested
	 */
	private SelectionAdapter compileSelected = new SelectionAdapter() {

		public void widgetSelected(SelectionEvent e) {
			JRDefinition def = (JRDefinition)e.widget.getData();
			String jrPath = JRBackwardManager.INSTANCE.getJRPath(def);
			JasperReportsConfiguration jConfig = getMDatasetToShow();
			IFile mfile = (IFile) jConfig.get(FileUtils.KEY_FILE);
			if (jrPath != null && mfile != null){
				new ConsoleExecuter(mfile, jrPath);
			}
		};		
	};
	
	/**
	 * Listener called when the option to change the destination path for the backward compiled 
	 * file is selected
	 */
	private SelectionAdapter setSelectionPath = new SelectionAdapter() {
		public void widgetSelected(SelectionEvent e) {
			if (e.widget.getData() == null) JRBackwardManager.INSTANCE.setDestinationPath(null);
			else  JRBackwardManager.INSTANCE.setDestinationPath((String)e.widget.getData());
		};
		
	};
	
	
	public CompileAction(IWorkbenchPart part) {
		super(part);
		setLazyEnablementCalculation(false);
		setMenuCreator(this);
	}

	protected void init() {
		super.init();
		setText(Messages.CompileAction_actionName);
		setToolTipText(Messages.CompileAction_actionTooltip);
		setImageDescriptor(JaspersoftStudioPlugin.getInstance().getImageDescriptor("/icons/build_tab.gif")); //$NON-NLS-1$
		setId(ID);
		setEnabled(true);
	}

	@Override
	public void run() {
		final JasperReportsConfiguration jConfig = getMDatasetToShow();
		final Console console = getCleanConsole();
		if (jConfig != null) {
			Job job = new Job(Messages.CompileAction_jobName) {
				@Override
				protected IStatus run(IProgressMonitor monitor) {
					IStatus status = actionCompile(jConfig, monitor, true, console);
					return status;
				}
			};
			job.setPriority(Job.SHORT);
			job.schedule();
		}
	}
	
	/**
	 * Execute the action of compilation of the report and its subreports, adding the errors to the error view 
	 * and various information to the console.
	 * 
	 * @param jConfig jasper configuration of the compiled report
	 * @param monitor monitor for the execution
	 * @param compileMain true if the main file need to be compiled, false to compile only the subreports
	 * @param console console where print info and errors
	 * @return Status.OK_STATUS if the compilation finished without exception (this dosen't means that the report 
	 * hasen't Compilation error), Status.CANCEL_STATUS otherwise
	 */
	public IStatus actionCompile(final JasperReportsConfiguration jConfig, IProgressMonitor monitor, boolean compileMain, final Console console) {
		
		IFile mfile = (IFile) jConfig.get(FileUtils.KEY_FILE);
		if (mfile != null){
			try{
				if (console != null) {
					console.addMessage(MessageFormat.format(Messages.CompileAction_consoleMessage1, mfile.getName()));
				}
				
				//Use a custom builder that redirect the output errors found during the compilation process to the console
				JasperReportsBuilder builder = new JasperReportsBuilder(){
					
					@Override
					protected JasperReportErrorHandler getErrorHandler(IFile resource) {
						JRErrorHandler errorHandler = new JRMarkerErrorHandler(console, resource);
						errorHandler.reset();
						return errorHandler;
					};
				};
				
				// ATTENTION! this can generate possible errors, because we are not calling builders in the right order
				// we are also not looking very good for for subreports, because expression evaluation is not good
				// file.getProject().build(IncrementalProjectBuilder.INCREMENTAL_BUILD, monitor);
				if (compileMain){
					IFile destFIle = builder.compileJRXML(mfile, monitor);
					if (console != null && destFIle != null){
						File file =  destFIle.getRawLocation().toFile();
						if (file.exists()){
							console.addMessage(MessageFormat.format(Messages.CompileAction_consoleMessage2, file.toString()));
						} else {
							console.addMessage(Messages.CompileAction_consoleMessage3);
						}
					}
				}
				Map<File, IFile> fmap = getSubreports(jConfig, mfile, jConfig.getJasperDesign(), monitor);
				for (File f : fmap.keySet()) {
					IFile file = fmap.get(f);
					if (file != null) {
						builder.compileJRXML(file, monitor);
					} else {
						try {
							JasperCompileManager.compileReportToFile(f.getAbsolutePath());
						} catch (JRException e) {
							e.printStackTrace();
						}
					}
				}
			} catch(CoreException ex){
				return Status.CANCEL_STATUS;
			}
		}
		return Status.OK_STATUS;
	}
	
	protected Map<File, IFile> getSubreports(JasperReportsConfiguration jConfig, IFile mfile, JasperDesign jd,IProgressMonitor monitor){
		return SubreportsUtil.getSubreportFiles(jConfig, mfile, jConfig.getJasperDesign(), monitor);
	}
	
	/**
	 * Return the output console for the current opened editor, the console is 
	 * cleaned before the return. If the console can not be found the null is 
	 * returned 
	 * 
	 * @return reference to a clean console of the current editor or null if it 
	 * is not available
	 */
	protected Console getCleanConsole(){
		AbstractJRXMLEditor editor = (AbstractJRXMLEditor)SelectionHelper.getActiveJRXMLEditor();
		if (editor != null) {
			final Console console = editor.getConsole();
			if (console != null){ 
				Display.getDefault().asyncExec(new Runnable() {
					public void run() {
						console.clearConsole();
					}
				});
			}
			return editor.getConsole();
		}
		else return null;
	}

	protected JasperReportsConfiguration getMDatasetToShow() {
		ISelection selection = getSelection();
		if (selection instanceof IStructuredSelection) {
			Object firstElement = ((IStructuredSelection) selection).getFirstElement();
			if (firstElement instanceof EditPart && ((EditPart) firstElement).getModel() instanceof ANode) {
				ANode currentNode = (ANode) ((EditPart) firstElement).getModel();
				return currentNode.getJasperConfiguration();
			}
		}
		final AbstractVisualEditor part = (AbstractVisualEditor) getWorkbenchPart();
		if (part instanceof ReportEditor) {
			ReportEditor rpeditor = (ReportEditor) part;
			return rpeditor.getJrContext();
		} else if (!part.getModel().getChildren().isEmpty()) {
			ANode firstChild = (ANode) part.getModel().getChildren().get(0);
			return firstChild.getJasperConfiguration();
		}
		IEditorPart activeJRXMLEditor = SelectionHelper.getActiveJRXMLEditor();
		if (activeJRXMLEditor != null && activeJRXMLEditor instanceof JrxmlEditor) {
			JrxmlEditor jrEditor = (JrxmlEditor) activeJRXMLEditor;
			return ((ANode) jrEditor.getModel()).getJasperConfiguration();
		}
		return null;
	}

	@Override
	protected boolean calculateEnabled() {
		return true;
	}

	public static IStatus doRun(final JasperReportsConfiguration jConfig, IProgressMonitor monitor, boolean compileMain) {
		IFile mfile = (IFile) jConfig.get(FileUtils.KEY_FILE);
		if (mfile != null)
			try {
				// ATTENTION! this can generate possible errors, because we are not calling builders in the right order
				// we are also not looking very good for for subreports, because expression evaluation is not good
				// file.getProject().build(IncrementalProjectBuilder.INCREMENTAL_BUILD, monitor);

				JasperReportsBuilder builder = new JasperReportsBuilder();
				if (compileMain)
					builder.compileJRXML(mfile, monitor);
				Map<File, IFile> fmap = SubreportsUtil.getSubreportFiles(jConfig, mfile, jConfig.getJasperDesign(), monitor);
				for (File f : fmap.keySet()) {
					IFile file = fmap.get(f);
					if (file != null) {
						builder.compileJRXML(file, monitor);
					} else {
						try {
							JasperCompileManager.compileReportToFile(f.getAbsolutePath());
						} catch (JRException e) {
							e.printStackTrace();
						}
					}
					if (monitor.isCanceled())
						break;
				}
			} catch (CoreException e) {
				return Status.CANCEL_STATUS;
			}
		return Status.OK_STATUS;
	}
	
	/**
	 * Create the submenu for the handling of the destination of the backward compiled
	 * file
	 * 
	 * @param parentMenu menu where the entry is placed
	 */
	private void createDestinationSubmenu(Menu parentMenu){
		MenuItem managePaths = new MenuItem(parentMenu, SWT.CASCADE);
		managePaths.setText(Messages.CompileAction_binaryDestination);
		final Menu managePathsMenu = new Menu (menu);
		managePaths.setMenu (managePathsMenu);
		final List<MenuItem> selectableItems = new ArrayList<MenuItem>();
		
		//Default destination path path (same as source)
		MenuItem sourceLocation = new MenuItem(managePathsMenu, SWT.CHECK);
		sourceLocation.setText(Messages.CompileAction_asSource);
		sourceLocation.addSelectionListener(setSelectionPath);
		selectableItems.add(sourceLocation);
		//Custom destination paths
		for(String path : JRBackwardManager.INSTANCE.getDestinationFolders()){
			final String elementPath = path;
			 MenuItem item = new MenuItem(managePathsMenu, SWT.CHECK);
			 item.setData(elementPath);
			 item.setText(elementPath);
			 item.addSelectionListener(setSelectionPath);
			 selectableItems.add(item);
		}
		
		//Item to add a new path
		new MenuItem(managePathsMenu, SWT.SEPARATOR);
		MenuItem addPath = new MenuItem(managePathsMenu, SWT.PUSH);
		addPath.setText(Messages.CompileAction_addPath);
		addPath.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				 DirectoryDialog dialog = new DirectoryDialog(UIUtils.getShell());
			   dialog.setFilterPath(System.getProperty("user.home")); //$NON-NLS-1$
			   String result = dialog.open();
			   if (result != null){
			  	 JRBackwardManager.INSTANCE.addDestinationPath(result, true);
			   }
			}
			
		});
		
		//Adapter used when the menu is shown to select the item associated
		//with the currently selected destination path
		managePathsMenu.addMenuListener(new MenuAdapter() {
			
			@Override
			public void menuShown(MenuEvent e) {
				String destination = JRBackwardManager.INSTANCE.getDestinationFolder();
				if (destination == null){
					for(MenuItem item : selectableItems){
						item.setSelection(false);
					}
					selectableItems.get(0).setSelection(true);
				} else {
					for(MenuItem item : selectableItems){
						item.setSelection(destination.equals(item.getData()));
					}
				}
			}
		});
	}

	/**
	 * Return the menu used to handle the backward compatibility
	 */
	@Override
	public Menu getMenu(Control parent) {
		if (menu != null) {
			menu.dispose();
		}
		menu = new Menu(parent);
		//Populate the list of the available backward JR compiler
		List<JRDefinition> definitions = JRBackwardManager.INSTANCE.getInstallerJRs();
		for(JRDefinition def : definitions){
			MenuItem item = new MenuItem(menu, SWT.PUSH);
			item.setText(MessageFormat.format(Messages.CompileAction_compile, new Object[]{def.getVersion()}));
			item.setData(def);
			item.addSelectionListener(compileSelected);
		}
		//If there are no older version of JR show an empty informative element
		if (definitions.isEmpty()){
			MenuItem fakeItem = new MenuItem(menu, SWT.PUSH);
			fakeItem.setEnabled(false);
			fakeItem.setText(Messages.CompileAction_noJRInstalled);
		}
		
		//create the path submenu
		new MenuItem(menu, SWT.SEPARATOR);
		createDestinationSubmenu(menu);
		//Create the option to manage the installed JR
		MenuItem manage = new MenuItem(menu, SWT.PUSH);
		manage.setText(Messages.CompileAction_manage);
		manage.addSelectionListener(new SelectionAdapter(){
			@Override
			public void widgetSelected(SelectionEvent e) {
				DownloadJRWizardDialog dialog = new DownloadJRWizardDialog(UIUtils.getShell());
				dialog.open();
			}
		});
		return menu;
	}

	@Override
	public Menu getMenu(Menu parent) {
		return null;
	}
}
