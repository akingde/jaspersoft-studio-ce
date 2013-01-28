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
package com.jaspersoft.studio.editor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.SubCoolBarManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.internal.provisional.action.ICoolBarManager2;
import org.eclipse.jface.internal.provisional.action.ToolBarContributionItem2;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IActionBars2;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.actions.LabelRetargetAction;
import org.eclipse.ui.actions.RetargetAction;
import org.eclipse.ui.internal.PartService;
import org.eclipse.ui.internal.WorkbenchPage;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.editor.report.ReportContainer;
import com.jaspersoft.studio.editor.toolitems.ISelectionContributionItem;
import com.jaspersoft.studio.editor.toolitems.ToolItem;
import com.jaspersoft.studio.editor.toolitems.ToolItemsManager;
import com.jaspersoft.studio.editor.toolitems.ToolItemsSet;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class JrxmlSelectionContributor {
	private ActionRegistry registry;
	private JrxmlEditorContributor editorContributor;

	public JrxmlSelectionContributor(JrxmlEditorContributor editorContributor) {
		this.editorContributor = editorContributor;

	}

	public void setRegistry(ActionRegistry registry) {
		this.registry = registry;
	}

	public void cleanBars(IActionBars bars) {
		for (String id : tbarID)
			bars.getToolBarManager().remove(id);
		tbarID.clear();
		if (bars instanceof IActionBars2) {
			if (((IActionBars2) bars).getCoolBarManager() instanceof SubCoolBarManager) {
				ICoolBarManager2 cbm2 = (ICoolBarManager2) ((SubCoolBarManager) ((IActionBars2) bars).getCoolBarManager())
						.getParent();
				for (String baritem : cbaritemID) {
					String[] bt = baritem.split(";");
					IContributionItem ictb = cbm2.find(bt[0]);
					if (ictb instanceof ToolBarContributionItem2) {
						IToolBarManager tbmanager = ((ToolBarContributionItem2) ictb).getToolBarManager();
						tbmanager.remove(bt[1]);
						tbmanager.update(true);
					}
				}
				for (String id : cbarID)
					cbm2.remove(id);
				cbm2.refresh();
				cbm2.update(true);
			}
		}
		tbarID.clear();
		cbaritemID.clear();
		cbarID.clear();

	}

	public void cleanBars(IActionBars bars, ISelection selection) {
		if (isSameSelection(bars, selection))
			return;
		else {
			lastselection = null;
			sobjects = null;
		}
		cleanBars(bars);
	}

	public void contributeToContextBars(IActionBars bars, ISelection selection) {
		if (isSameSelection(bars, selection))
			return;
		cleanBars(bars);

		contributeToContextMenu(bars.getMenuManager(), selection);
		contributeToContextToolBar(bars.getToolBarManager(), selection);
		if (bars instanceof IActionBars2)
			contributeToContextCoolBar(bars, ((IActionBars2) bars).getCoolBarManager(), selection);
		contributeToContextStatusLine(bars.getStatusLineManager(), selection);

		PartService pservice = ((WorkbenchPage) editorContributor.getPage()).getPartService();
		if (pservice.getActivePartReference() != null)
			pservice.firePartBroughtToTop(pservice.getActivePartReference());

		bars.updateActionBars();
	}

	public void contributeToContextStatusLine(IStatusLineManager statusLineManager, ISelection selection) {

	}

	private Set<String> cbarID = new HashSet<String>();
	private Set<String> cbaritemID = new HashSet<String>();
	private Set<String> tbarID = new HashSet<String>();

	private List<?> sobjects = null;
	private ISelection lastselection;

	private boolean isSameSelection(IActionBars bars, ISelection selection) {
		if (selection == null || !(selection instanceof StructuredSelection)) {
			lastselection = null;
			sobjects = null;
			return false;
		}

		if (lastselection == selection)
			return true;

		boolean isSame = sobjects != null && selection != null && !selection.isEmpty() && !sobjects.isEmpty();
		List<Object> list = new ArrayList<Object>();
		StructuredSelection sel = (StructuredSelection) selection;
		List<Object> slist = sel.toList();
		isSame = isSame && sobjects != null && slist.size() == sobjects.size();
		for (Object obj : slist) {
			if (obj instanceof EditPart)
				obj = ((EditPart) obj).getModel();
			if (isSame && sobjects != null)
				isSame = sobjects.contains(obj);
			list.add(obj);
		}
		sobjects = list;
		return isSame;
	}

	private boolean isToolbarVisible(ToolItemsSet ts) {
		if (sobjects != null && !sobjects.isEmpty()) {
			Object obj = sobjects.get(0);
			if (obj instanceof APropertyNode) {
				JasperReportsConfiguration jConfig = ((APropertyNode) obj).getJasperConfiguration();
				if (jConfig != null)
					return jConfig.getPropertyBoolean(ts.getId(), true);
			}
		}
		return JaspersoftStudioPlugin.getInstance().getPreferenceStore().getBoolean(ts.getId());
	}

	public void contributeToContextCoolBar(IActionBars bars, ICoolBarManager coolBarManager, ISelection selection) {
		IEditorPart lastEditor = editorContributor.getLastEditor();
		if (lastEditor instanceof ReportContainer)
			lastEditor = ((ReportContainer) lastEditor).getActiveEditor();
		if (coolBarManager instanceof SubCoolBarManager) {
			ICoolBarManager2 cbm2 = (ICoolBarManager2) ((SubCoolBarManager) coolBarManager).getParent();

			ToolItemsManager tm = JaspersoftStudioPlugin.getToolItemsManager();
			for (ToolItemsSet ts : tm.getSets()) {
				if (!isToolbarVisible(ts))
					continue;
				if (ts.getToolbarUri() != null) {
					for (ToolItem ti : ts.getToolItems()) {
						if (!isSelected(selection, ti.getClasses()))
							continue;
						if (ti.getContributionItem() != null) {
							addContributionToCoolbar(cbm2, ts, ti, selection);
						} else if (ti.getActionID() != null)
							addActionToCoolbar(bars, cbm2, ts, ti);
					}
				}
			}
			cbm2.update(true);
		}
	}

	private void addContributionToCoolbar(ICoolBarManager2 cbm2, ToolItemsSet ts, ToolItem ti, ISelection selection) {
		String tbarid = ts.getToolbarUri();
		IContributionItem item = getToolbarContributionItem(cbm2, tbarid);
		if (item != null && item instanceof ToolBarContributionItem2) {
			ToolBarContributionItem2 tbitem = (ToolBarContributionItem2) item;
			// add coolbarcontributionitem
			IContributionItem citem = ti.getContributionItem();
			tbitem.getToolBarManager().add(citem);
			// citem.update();
			if (citem instanceof ISelectionContributionItem) {
				((ISelectionContributionItem) citem).setSelection(selection);
				((ISelectionContributionItem) citem).setWorkbenchPart(editorContributor.getLastEditor());
			}

			cbaritemID.add(tbarid + ";" + ti.getId());
		}
	}

	private void addActionToCoolbar(final IActionBars bars, ICoolBarManager2 cbm2, ToolItemsSet ts, ToolItem ti) {
		IAction action = editorContributor.getAction(ti.getId());
		if (action == null)
			action = registry.getAction(ti.getId());
		if (action == null) {
			action = new LabelRetargetAction(ti.getActionID(), ti.getLabel()) {
				// @Override
				// public void partDeactivated(IWorkbenchPart part) {
				// // if (isSameSelection(bars, part.getSite().getSelectionProvider().getSelection()))
				// // return;
				// super.partDeactivated(part);
				// }

				@Override
				public void partBroughtToTop(IWorkbenchPart part) {
					partActivated(part);
				}
			};

			editorContributor.addRetargetAction((RetargetAction) action);

		}
		if (ti.getLabel() != null)
			action.setText(ti.getLabel());
		if (ti.getTooltip() != null)
			action.setToolTipText(ti.getTooltip());
		if (ti.getIcon() != null)
			action.setImageDescriptor(JaspersoftStudioPlugin.getInstance().getImageDescriptor(ti.getIcon()));
		addAction(cbm2, ts.getToolbarUri(), action);
	}

	private boolean isSelected(ISelection selection, List<Class<?>> classes) {
		StructuredSelection sel = (StructuredSelection) selection;
		for (Iterator<?> it = sel.iterator(); it.hasNext();) {
			Object obj = it.next();
			if (obj instanceof EditPart)
				obj = ((EditPart) obj).getModel();
			boolean iscompatible = false;
			for (Class<?> c : classes) {
				if (c.isAssignableFrom(obj.getClass())) {
					iscompatible = iscompatible || true;
					break;
				}
			}
			if (!iscompatible)
				return false;
		}
		return true;
	}

	public void contributeToContextToolBar(IToolBarManager tbm, ISelection selection) {
	}

	private void addAction(ICoolBarManager2 cbm2, String tbarid, IAction action) {
		IContributionItem item = getToolbarContributionItem(cbm2, tbarid);
		if (item != null && item instanceof ToolBarContributionItem2) {
			ToolBarContributionItem2 tbitem = (ToolBarContributionItem2) item;
			tbitem.getToolBarManager().add(action);
			cbaritemID.add(tbarid + ";" + action.getId());
		}
	}

	private IContributionItem getToolbarContributionItem(ICoolBarManager2 cbm2, String tbarid) {
		IContributionItem item = cbm2.find(tbarid);
		if (item == null) {
			item = new ToolBarContributionItem2(new ToolBarManager(), tbarid);
			cbm2.add(item);
			cbarID.add(tbarid);
		}
		return item;
	}

	private void addAction(IToolBarManager tbm, IAction action) {
		tbm.add(action);
		tbarID.add(action.getId());
	}

	public void contributeToContextMenu(IMenuManager manager, ISelection selection) {

	}
}
