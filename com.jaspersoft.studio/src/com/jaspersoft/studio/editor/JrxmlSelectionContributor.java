/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.editor;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
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
import org.eclipse.jface.internal.provisional.action.IToolBarContributionItem;
import org.eclipse.jface.internal.provisional.action.ToolBarContributionItem2;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IActionBars2;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IPartService;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.ui.internal.PartService;
import org.eclipse.ui.internal.WorkbenchPage;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.editor.report.ReportContainer;
import com.jaspersoft.studio.editor.toolitems.ISelectionContributionItem;
import com.jaspersoft.studio.editor.toolitems.ToolItem;
import com.jaspersoft.studio.editor.toolitems.ToolItemsManager;
import com.jaspersoft.studio.editor.toolitems.ToolItemsSet;
import com.jaspersoft.studio.utils.SelectionHelper;

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
		long stime = System.currentTimeMillis();
		if (bars instanceof IActionBars2 && ((IActionBars2) bars).getCoolBarManager() instanceof SubCoolBarManager) {
			ICoolBarManager2 cbm2 = (ICoolBarManager2) ((SubCoolBarManager) ((IActionBars2) bars).getCoolBarManager())
					.getParent();
			for (String baritem : cbaritemID) {
				String[] bt = baritem.split(";");
				IContributionItem ictb = ToolItemsManager.findToolbar(cbm2, bt[0]);
				if (ictb instanceof IToolBarContributionItem) {
					IToolBarManager tbmanager = ((IToolBarContributionItem) ictb).getToolBarManager();
					for (IContributionItem ci : tbmanager.getItems()) {
						if (ci.equals(bt[1])) {
							tbmanager.remove(ci);
							ci.dispose();
						}
					}
					tbmanager.update(true);
				}
			}
			for (String id : cbarID) {
				IContributionItem ic = ToolItemsManager.findToolbar(cbm2, id);
				if (ic != null) {
					cbm2.remove(ic);
					ic.dispose();
				}
			}
			cbm2.refresh();
			cbm2.update(true);
		}
		cbaritemID.clear();
		cbarID.clear();
		System.out.println("cleared in: " + (System.currentTimeMillis() - stime));
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

		IPartService pservice = ((WorkbenchPage) editorContributor.getPage()).getWorkbenchWindow().getPartService();

		contributeToContextMenu(bars.getMenuManager(), selection);
		contributeToContextToolBar(bars.getToolBarManager(), selection);
		if (bars instanceof IActionBars2)
			contributeToContextCoolBar(bars, ((IActionBars2) bars).getCoolBarManager(), selection);
		contributeToContextStatusLine(bars.getStatusLineManager(), selection);
		if (pservice.getActivePartReference() != null && pservice instanceof PartService) {
			// I use reflection because methods are different in eclipse 3.x and 4.x
			try {
				Method m = null;
				try {
					m = PartService.class.getMethod("firePartBroughtToTop", IWorkbenchPartReference.class);
				} catch (NoSuchMethodException e) {
					try {
						m = PartService.class.getMethod("partBroughtToTop", IWorkbenchPartReference.class);
						m.invoke((PartService) pservice, pservice.getActivePartReference());
					} catch (NoSuchMethodException e1) {
						e1.printStackTrace();
					}
				}
				if (m != null)
					m.invoke((PartService) pservice, pservice.getActivePartReference());
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}

			// ((PartService) pservice).firePartBroughtToTop(pservice.getActivePartReference());
			// ((PartService) pservice).partBroughtToTop(pservice.getActivePartReference());
		} else {
			pservice.getActivePart();
		}

		bars.updateActionBars();
	}

	private Set<String> cbarID = new HashSet<String>();
	private Set<String> cbaritemID = new HashSet<String>();

	private List<?> sobjects = null;
	private ISelection lastselection;

	public void contributeToContextCoolBar(IActionBars bars, ICoolBarManager coolBarManager, ISelection selection) {
		long stime = System.currentTimeMillis();
		IEditorPart lastEditor = editorContributor.getLastEditor();
		if (lastEditor instanceof ReportContainer)
			lastEditor = ((ReportContainer) lastEditor).getActiveEditor();
		if (coolBarManager instanceof SubCoolBarManager) {
			ICoolBarManager2 cbm2 = (ICoolBarManager2) ((SubCoolBarManager) coolBarManager).getParent();

			ToolItemsManager tm = JaspersoftStudioPlugin.getToolItemsManager();
			for (ToolItemsSet ts : tm.getSets()) {
				if (!ToolItemsManager.isToolbarVisible(ts, sobjects))
					continue;
				if (ts.getToolbarUri() != null) {
					for (ToolItem ti : ts.getToolItems()) {
						if (!SelectionHelper.isSelected(selection, ti.getClasses()))
							continue;
						if (ti.getContributionItem() != null)
							addContributionToCoolbar(cbm2, ts, ti, selection);
						else if (ti.getActionID() != null)
							addActionToCoolbar(bars, cbm2, ts, ti);
					}
				}
			}
			cbm2.update(true);
		} else {
			System.out.println("haha");
		}
		System.out.println("contributed in: " + (System.currentTimeMillis() - stime));
	}

	private void addContributionToCoolbar(ICoolBarManager2 cbm2, ToolItemsSet ts, ToolItem ti, ISelection selection) {
		String tbarid = ts.getToolbarUri();
		IContributionItem item = getToolbarContributionItem(cbm2, tbarid);
		if (item != null && item instanceof IToolBarContributionItem) {
			IToolBarContributionItem tbitem = (IToolBarContributionItem) item;
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
			action = registry.getAction(ti.getActionID());
		if (action == null) {
			return;
			// action = new LabelRetargetAction(ti.getActionID(), ti.getLabel()) {

			// @Override
			// public void partBroughtToTop(IWorkbenchPart part) {
			// partActivated(part);
			// }
			// };

			// editorContributor.addRetargetAction((RetargetAction) action);
		}
		if (ti.getLabel() != null)
			action.setText(ti.getLabel());
		if (ti.getTooltip() != null)
			action.setToolTipText(ti.getTooltip());
		if (ti.getIcon() != null)
			action.setImageDescriptor(JaspersoftStudioPlugin.getInstance().getImageDescriptor(ti.getIcon()));
		addAction(cbm2, ts.getToolbarUri(), ti, action);
	}

	private void addAction(ICoolBarManager2 cbm2, String tbarid, ToolItem ti, IAction action) {
		IContributionItem item = getToolbarContributionItem(cbm2, tbarid);
		if (item != null && item instanceof IToolBarContributionItem) {
			IToolBarContributionItem tbitem = (IToolBarContributionItem) item;
			if (tbitem.getToolBarManager() != null) {
				tbitem.getToolBarManager().add(action);
				cbaritemID.add(tbarid + ";" + ti.getId());

				tbitem.getToolBarManager().update(true);
			}
		}
	}

	private IContributionItem getToolbarContributionItem(ICoolBarManager2 cbm2, String tbarid) {
		IContributionItem item = ToolItemsManager.findToolbar(cbm2, tbarid);
		// IContributionItem item = cbm2.find(tbarid);
		if (item == null) {
			item = new ToolBarContributionItem2(new ToolBarManager(), tbarid);
			cbm2.appendToGroup("group.editor", item);
		}
		if (item != null)
			cbarID.add(tbarid);
		return item;
	}

	public void contributeToContextToolBar(IToolBarManager tbm, ISelection selection) {
	}

	public void contributeToContextMenu(IMenuManager manager, ISelection selection) {
	}

	public void contributeToContextStatusLine(IStatusLineManager statusLineManager, ISelection selection) {
	}

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

}