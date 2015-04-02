package com.jaspersoft.studio.kpi;


import net.sf.jasperreports.eclipse.ui.util.UIUtils;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuCreator;
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
import com.jaspersoft.studio.kpi.dialog.KPIConfiguratorWizard;
import com.jaspersoft.studio.kpi.dialog.KPIWizardDialog;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.server.WSClientHelper;
import com.jaspersoft.studio.server.model.MReportUnit;
import com.jaspersoft.studio.server.model.server.MServerProfile;
import com.jaspersoft.studio.server.protocol.restv2.RestV2ConnectionJersey;

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
		return isRunnable();
	}

	private boolean isRunnable() {
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
//								if ("resource.not.found".equals( ex.getMessage()) )
////								{
////									kpiReportUnit = null;
////								}
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

	private void fillMenu(){
		//Set kpi action
		MenuItem setKPI = new MenuItem(menu, SWT.PUSH);
		setKPI.setText("Set KPI");
		setKPI.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				execute();
			}
		});
		
		//another action
		MenuItem anotherAction = new MenuItem(menu, SWT.PUSH);
		anotherAction.setText("Another Action");
		anotherAction.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("Executed Another action");
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
}