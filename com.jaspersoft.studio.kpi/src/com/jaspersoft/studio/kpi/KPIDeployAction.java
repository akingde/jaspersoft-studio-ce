package com.jaspersoft.studio.kpi;


import java.io.File;
import java.text.MessageFormat;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuCreator;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.kpi.dialog.KPIConfiguratorWizard;
import com.jaspersoft.studio.kpi.dialog.KPIWizardDialog;
import com.jaspersoft.studio.kpi.messages.Messages;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.server.ServerManager;
import com.jaspersoft.studio.server.WSClientHelper;
import com.jaspersoft.studio.server.model.MReportUnit;
import com.jaspersoft.studio.server.model.server.MServerProfile;
import com.jaspersoft.studio.server.protocol.restv2.RestV2ConnectionJersey;
import com.jaspersoft.studio.utils.SelectionHelper;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class KPIDeployAction extends Action implements IMenuCreator{
	
	private static final String ID = "KPIDEPLOYACTION"; //$NON-NLS-1$
	
	private TreeViewer treeViewer;

	private Menu menu;
	
	public KPIDeployAction(TreeViewer treeViewer) {
		super();
		setId(ID);
		setText("KPI");
		//setDescription("Handle the KPI for the current Report Unit");
		setToolTipText("Handle the KPI for the current Report Unit");
		setImageDescriptor(Activator.getImageDescriptor("icons/key.png")); //$NON-NLS-1$
		setDisabledImageDescriptor(Activator.getImageDescriptor("icons/key.png")); //$NON-NLS-1$
		this.treeViewer = treeViewer;
		setMenuCreator(this);
	}

	@Override
	public boolean isEnabled() {
		return isReportUnit();
	}

	private boolean isReportUnit() {
		final TreeSelection s = (TreeSelection) treeViewer.getSelection();
		TreePath[] p = s.getPaths();
		for (int i = 0; i < p.length; i++) {
			if (!isInReportUnit(p[i].getLastSegment()))
				return false;
		}
		return true;
	}

	private boolean isInReportUnit(Object obj) {
		if (obj == null)
			return false;
		
		MReportUnit repunit = null;
		if (obj instanceof MReportUnit)
			repunit = (MReportUnit) obj;
		else if (((ANode) obj).getParent() instanceof MReportUnit)
			repunit = (MReportUnit) ((ANode) obj).getParent();
		if (repunit == null)
			return false;
		int pmask = repunit.getValue().getPermissionMask(repunit.getWsClient());
		return pmask == 1 || (pmask & 32) == 32 || (pmask & 2) == 2;
	}

	
	private void execute(){
		final TreeSelection s = (TreeSelection) treeViewer.getSelection();
		TreePath[] p = s.getPaths();
		for (int i = 0; i < p.length; i++) {
			Object obj = p[i].getLastSegment();
			if (obj instanceof MReportUnit) {
				final MReportUnit node = (MReportUnit) obj;
				Job job = new Job("Reading Report Unit Option") {
					@Override
					protected IStatus run(IProgressMonitor monitor) {
						monitor.beginTask("Reading Report Unit Option", IProgressMonitor.UNKNOWN);
						try {
							final ResourceDescriptor rd = WSClientHelper.getResource(monitor, node, node.getValue());
							
							// We need to work here with the Jersay based REST client...
							
							final RestV2ConnectionJersey client = new RestV2ConnectionJersey();
							client.connect(monitor, node.getWsClient().getServerProfile());
							
							
							// Check if we already have a KPI...
							String reportUnitFolderUri = rd.getUriString() + "_files/KPI";
							
							ResourceDescriptor folderResourceDescriptor = new ResourceDescriptor();
							folderResourceDescriptor.setUriString(reportUnitFolderUri);
							folderResourceDescriptor.setWsType(ResourceDescriptor.TYPE_REPORTUNIT);
							
							ResourceDescriptor kpiReportUnit;
							try {
								kpiReportUnit = client.get(monitor, folderResourceDescriptor, null);
								
								// If we got the kpiReportUnit, let's get the jrxml and load it as JasperDesign...
							} catch (Exception ex)
							{
								kpiReportUnit = null;
							}
							
							
							final ResourceDescriptor kpiReportUnitFinal = kpiReportUnit;
							
							UIUtils.getDisplay().asyncExec(new Runnable() {
								
								@Override
								public void run() {
									
									//KPIDefinitionPanelController wizard = new KPIDefinitionPanelController(client, rd, kpiReportUnitFinal, (MServerProfile)node.getRoot());
									KPIConfiguratorWizard wizard = new KPIConfiguratorWizard(client, rd, kpiReportUnitFinal, (MServerProfile)node.getRoot());
									WizardDialog dialog = new KPIWizardDialog(UIUtils.getShell(), wizard);
									dialog.open();
									//MessageDialog.openInformation(UIUtils.getShell(), "KPI Deploy", "Ready to deploy a kpi to " + rd.getUriString());
								}
							});
							
							
						} catch (Exception e) {
							UIUtils.showError(e);
						} finally {
							monitor.done();
						}
						return Status.OK_STATUS;
					}
				};
				job.setPriority(Job.LONG);
				job.schedule();

				break;
			}
		}
	}

	
	/**
	 * Add all the entries to the KPI menu.
	 */
	private void fillMenu(){
		//Set kpi action
		MenuItem setKPI = new MenuItem(menu, SWT.PUSH);
		setKPI.setText("Create / Edit KPI");
		setKPI.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				execute();
			}
		});
		
		//Delete KPI action
		MenuItem deleteAction = new MenuItem(menu, SWT.PUSH);
		deleteAction.setText("Delete KPI");
		deleteAction.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				executeDeleteAction();
			}
		});
		
		MenuItem openInEditorAction = new MenuItem(menu, SWT.PUSH);
		openInEditorAction.setText("Export the KPI Jrxml");
		openInEditorAction.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				executeOpenInEditorAction();
			}
		});
		
		MenuItem publishAction = new MenuItem(menu, SWT.PUSH);
		publishAction.setText("Create KPI from a Jrxml");
		publishAction.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				executePublish();
			}
		});
		
	}
	
	@Override
	public void dispose() {
		if (menu != null){
			menu.dispose();
		}
	}
	
	@Override
	public Menu getMenu(Control parent) {
		if (menu != null) {
			menu.dispose();
		}
		menu = new Menu(parent);
		fillMenu();
		return menu;
	}
	
	@Override
	public Menu getMenu(Menu parent) {
		if (menu != null) {
			menu.dispose();
		}
		menu = new Menu(parent);
		fillMenu();
		return menu;
	}
	
	/**
	 * Detele the current KPI (if any)
	 */
	private void executeDeleteAction(){
		final TreeSelection s = (TreeSelection) treeViewer.getSelection();
		TreePath[] p = s.getPaths();
		for (int i = 0; i < p.length; i++) {
			Object obj = p[i].getLastSegment();
			if (obj instanceof MReportUnit) {
				
				boolean res = MessageDialog.openConfirm(UIUtils.getShell(), Messages.KPIWizardDialog_removeTitle, Messages.KPIWizardDialog_removeMessage);
				if (!res)
				{
					return;
				}
				
				final MReportUnit node = (MReportUnit) obj;
				Job job = new Job("Reading Report Unit Option") {
					@Override
					protected IStatus run(IProgressMonitor monitor) {
						monitor.beginTask("Reading Report Unit Option", IProgressMonitor.UNKNOWN);
						try {
							final ResourceDescriptor rd = WSClientHelper.getResource(monitor, node, node.getValue());
							
							// We need to work here with the Jersay based REST client...
							
							final RestV2ConnectionJersey client = new RestV2ConnectionJersey();
							client.connect(monitor, node.getWsClient().getServerProfile());
							
							try {
								KPIUtils.deleteReportUnitKPI(client, rd.getUriString());
								
								UIUtils.getDisplay().asyncExec(new Runnable() {
									
									@Override
									public void run() {
										MessageDialog.openInformation(UIUtils.getShell(), Messages.KPIWizardDialog_removeTitle, Messages.KPIWizardDialog_removeSuccess);
									}
								});
								
								
							} catch (final Exception ex)
							{
								UIUtils.getDisplay().asyncExec(new Runnable() {
									
									@Override
									public void run() {
										MessageDialog.openError(UIUtils.getShell(), Messages.KPIWizardDialog_removeErrorTitle, Messages.KPIWizardDialog_removeErrorMessage +  ex.getMessage());
									}
								});
								
								JaspersoftStudioPlugin.getInstance().logError(ex);
							}
							
							
						} catch (Exception e) {
							UIUtils.showError(e);
						} finally {
							monitor.done();
						}
						return Status.OK_STATUS;
					}
				};
				job.setPriority(Job.LONG);
				job.schedule();

				break;
			}
		}
	}
	
	
	
	
	/**
	 * Invoked on the menu item Open KPI Jrxml in editor.
	 * It checks if the selected node is a ReportUnit and locate the KPI jrxml (if exists), and open it in editor.
	 */
	public void executeOpenInEditorAction() {
		final TreeSelection s = (TreeSelection) treeViewer.getSelection();
		TreePath[] p = s.getPaths();
		for (int i = 0; i < p.length; i++) {
			Object obj = p[i].getLastSegment();
			if (obj instanceof MReportUnit) {
				
				final MReportUnit node = (MReportUnit) obj;
				
				
				Job job = new Job("Reading Report Unit Option") {
					@Override
					protected IStatus run(IProgressMonitor monitor) {
						monitor.beginTask("Reading Report Unit Option", IProgressMonitor.UNKNOWN);
						try {
							final ResourceDescriptor rd = WSClientHelper.getResource(monitor, node, node.getValue());
							
							// We need to work here with the Jersay based REST client...
							
							final RestV2ConnectionJersey client = new RestV2ConnectionJersey();
							client.connect(monitor, node.getWsClient().getServerProfile());
								
								ResourceDescriptor kpiReportUnit = KPIUtils.getReportUnitKPI(client, rd.getUriString());
								
								if (kpiReportUnit == null)
								{
									UIUtils.getDisplay().asyncExec(new Runnable() {
										
										@Override
										public void run() {
											MessageDialog.openInformation(UIUtils.getShell(), "Export KPI Jrxml", "This Report Unit does not contain any KPI");
										}
									});
								}
								else
								{
									// Create a temporary file to save the jrxml data.
									MServerProfile serverProfile = (MServerProfile)node.getRoot();
									IFolder folder = serverProfile.getTmpDir(monitor);
									
									// Get a free name for this file...
									// The name has the format kpi_<resource_name>.jrxml.
									// If this name is taken, we try kpi_<resource_name>_<n>.jrxml
									
									int i=0;
									java.text.MessageFormat mf = new MessageFormat("kpi_" + rd.getName() + "{0}.jrxml");
									
									String name = mf.format(new Object[]{""}); // This will give us simply kpi_<resource_name>.jrxml
									while (folder.getFile( name ).exists())
									{
										i++;
										name = mf.format(new Object[]{"_"+i}); // This will give us kpi_<resource_name>_<n>.jrxml
									}
									
									final IFile file = folder.getFile(name);
									
									
									// We are ready to store a file inside the project...
									File outputFile = new File(file.getRawLocationURI());
									
									
									ResourceDescriptor jrxmlResourceDescriptor = null;
									
									for (ResourceDescriptor r : kpiReportUnit.getChildren())
									{
										if (r.isMainReport())
										{
											jrxmlResourceDescriptor = r;
											break;
										}
									}
									
									// If we have found the jrxml, let's load it inside the editor
									if (jrxmlResourceDescriptor != null)
									{
										jrxmlResourceDescriptor = client.get(monitor, jrxmlResourceDescriptor, outputFile);
										
										// Now we have to import this file inside a project, and open the file...
										String fkeyname = ServerManager.getKey(node, jrxmlResourceDescriptor.getUriString(), null);
										
										// Refresh the file with the content...
										file.refreshLocal(1, monitor);
										
										JasperReportsConfiguration jrconf = JasperReportsConfiguration.getDefaultJRConfig(file);
										
										
										try {
											//jrconf.getPrefStore().setValue(JRSEditorContributor.KEY_PUBLISH2JSS_SILENT,true);
											
											UIUtils.getDisplay().asyncExec(new Runnable() {

												public void run() {
													SelectionHelper.openEditor(file);
													
													MessageDialog.openInformation(
															UIUtils.getShell(), 
															"Export KPI Jrxml",
															MessageFormat.format("The KPI jrxml has been saved in your workspace:\n{0}", file.getFullPath()) );
												}
											});
											
										} finally {
											//jrconf.dispose();
										}
									
									}
									
									
								}
							
						} catch (Exception e) {
							UIUtils.showError(e);
						} finally {
							monitor.done();
						}
						return Status.OK_STATUS;
					}
				};
				job.setPriority(Job.LONG);
				job.schedule();

				break;
			}
		}
	}

		
	
	
	
	private void executePublish(){
		final TreeSelection s = (TreeSelection) treeViewer.getSelection();
		TreePath[] p = s.getPaths();
		for (int i = 0; i < p.length; i++) {
			Object obj = p[i].getLastSegment();
			if (obj instanceof MReportUnit) {
				final MReportUnit node = (MReportUnit) obj;
				Job job = new Job("Reading Report Unit Option") {
					@Override
					protected IStatus run(IProgressMonitor monitor) {
						monitor.beginTask("Reading Report Unit Option", IProgressMonitor.UNKNOWN);
						try {
							final ResourceDescriptor rd = WSClientHelper.getResource(monitor, node, node.getValue());
							
							// We need to work here with the Jersay based REST client...
							
							final RestV2ConnectionJersey client = new RestV2ConnectionJersey();
							client.connect(monitor, node.getWsClient().getServerProfile());
							
							
							// Check if we already have a KPI...
							String reportUnitFolderUri = rd.getUriString() + "_files/KPI";
							
							ResourceDescriptor folderResourceDescriptor = new ResourceDescriptor();
							folderResourceDescriptor.setUriString(reportUnitFolderUri);
							folderResourceDescriptor.setWsType(ResourceDescriptor.TYPE_REPORTUNIT);
							
							ResourceDescriptor kpiReportUnit;
							try {
								kpiReportUnit = client.get(monitor, folderResourceDescriptor, null);
								
								// If we got the kpiReportUnit, let's get the jrxml and load it as JasperDesign...
							} catch (Exception ex)
							{
								kpiReportUnit = null;
							}
							
							
							final ResourceDescriptor kpiReportUnitFinal = kpiReportUnit;
							
							UIUtils.getDisplay().asyncExec(new Runnable() {
								
								@Override
								public void run() {
									
									KPIDefinitionPanelController wizard = new KPIDefinitionPanelController(client, rd, kpiReportUnitFinal, (MServerProfile)node.getRoot());
									WizardDialog dialog = new WizardDialog(UIUtils.getShell(), wizard);
									dialog.open();
								}
							});
							
							
						} catch (Exception e) {
							UIUtils.showError(e);
						} finally {
							monitor.done();
						}
						return Status.OK_STATUS;
					}
				};
				job.setPriority(Job.LONG);
				job.schedule();

				break;
			}
		}
	}
	
	
	
	
	
}