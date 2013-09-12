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
package com.jaspersoft.studio.data.sql.ui.gef;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.MouseWheelHandler;
import org.eclipse.gef.MouseWheelZoomHandler;
import org.eclipse.gef.Request;
import org.eclipse.gef.dnd.AbstractTransferDropTargetListener;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gef.ui.parts.GraphicalViewerKeyHandler;
import org.eclipse.gef.ui.parts.ScrollingGraphicalViewer;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.jaspersoft.studio.data.sql.SQLQueryDesigner;
import com.jaspersoft.studio.data.sql.Util;
import com.jaspersoft.studio.data.sql.action.select.CreateColumn;
import com.jaspersoft.studio.data.sql.action.table.CreateTable;
import com.jaspersoft.studio.data.sql.model.metadata.MSQLColumn;
import com.jaspersoft.studio.data.sql.model.metadata.MSqlTable;
import com.jaspersoft.studio.data.sql.model.query.from.MFrom;
import com.jaspersoft.studio.data.sql.model.query.from.MFromTable;
import com.jaspersoft.studio.data.sql.model.query.from.TableJoin;
import com.jaspersoft.studio.data.sql.ui.gef.parts.ColumnEditPart;
import com.jaspersoft.studio.data.sql.ui.gef.parts.FromEditPart;
import com.jaspersoft.studio.data.sql.ui.gef.parts.QueryEditPart;
import com.jaspersoft.studio.data.sql.ui.gef.parts.SQLDesignerEditPartFactory;
import com.jaspersoft.studio.data.sql.ui.gef.parts.TableEditPart;
import com.jaspersoft.studio.dnd.NodeTransfer;
import com.jaspersoft.studio.model.ANode;

public class SQLQueryDiagram {
	private SQLQueryDesigner designer;
	private ScrollingGraphicalViewer viewer;
	public static final String SQLQUERYDIAGRAM = "SQLQUERYDIAGRAM";

	public SQLQueryDiagram(SQLQueryDesigner designer) {
		this.designer = designer;
	}

	public Control createDiagram(Composite parent) {
		viewer = new ScrollingGraphicalViewer();
		viewer.createControl(parent);
		viewer.setEditDomain(new DefaultEditDomain(null));
		viewer.setRootEditPart(new SQLDesignerRootEditPart());
		viewer.setEditPartFactory(new SQLDesignerEditPartFactory());
		viewer.setKeyHandler(new GraphicalViewerKeyHandler(viewer));
		viewer.setProperty(MouseWheelHandler.KeyGenerator.getKey(SWT.MOD1), MouseWheelZoomHandler.SINGLETON);
		viewer.setProperty(SQLQUERYDIAGRAM, designer);
		viewer.setContextMenu(new ContextMenuProvider(viewer) {

			@Override
			public void buildContextMenu(IMenuManager menu) {
				Object[] selection = null;
				IStructuredSelection s = (IStructuredSelection) viewer.getSelection();
				if (s != null) {
					List<Object> models = new ArrayList<Object>();
					for (Object obj : s.toList()) {
						if (obj instanceof EditPart) {
							if (obj instanceof ColumnEditPart) {
								models.add(obj);
							} else
								obj = ((EditPart) obj).getModel();
						}
						if (obj instanceof ANode)
							models.add((ANode) obj);
						if (obj instanceof TableJoin)
							models.add(((TableJoin) obj).getJoinTable());
					}
					if (!models.isEmpty())
						selection = models.toArray();
				}
				designer.getOutline().getAfactory().fillMenu(selection, menu);
			}
		});
		viewer.addDropTargetListener(new QueryDesignerDropTargetListener(viewer, NodeTransfer.getInstance()));

		refreshViewer();

		return viewer.getControl();
	}

	protected void refreshViewer() {
		viewer.setContents(designer.getRoot());

		TreeSelection s = (TreeSelection) designer.getOutline().getTreeViewer().getSelection();
		List<MFromTable> tables = new ArrayList<MFromTable>();
		for (Object obj : s.toList()) {
			if (obj instanceof MFromTable)
				tables.add((MFromTable) obj);
		}
		if (!tables.isEmpty()) {
			List<TableEditPart> parts = new ArrayList<TableEditPart>();
			EditPart ep = viewer.getRootEditPart();
			for (Object obj : ep.getChildren())
				doAddParts(obj, parts, tables);
			viewer.setSelection(new StructuredSelection(parts));
			if (!parts.isEmpty())
				viewer.reveal(parts.get(0));
		}
	}

	private void doAddParts(Object obj, List<TableEditPart> parts, List<MFromTable> tables) {
		if (obj instanceof QueryEditPart) {
			for (Object o : ((QueryEditPart) obj).getChildren())
				doAddParts(o, parts, tables);
		} else if (obj instanceof FromEditPart) {
			for (Object tep : ((FromEditPart) obj).getChildren()) {
				if (tep instanceof TableEditPart && tables.contains(((TableEditPart) tep).getModel()))
					parts.add((TableEditPart) tep);
			}
		}
	}

	public void scheduleRefresh() {
		refreshViewer();
	}

	public void dispose() {

	}

	public class QueryDesignerDropTargetListener extends AbstractTransferDropTargetListener {

		public QueryDesignerDropTargetListener(EditPartViewer viewer, Transfer xfer) {
			super(viewer, xfer);
		}

		@Override
		protected void updateTargetRequest() {
			((CreateRequest) getTargetRequest()).setLocation(getDropLocation());
		}

		@Override
		protected void handleDrop() {
			updateTargetRequest();
			updateTargetEditPart();

			doDrop(Util.getAllNodes(getCurrentEvent().data));
		}

		private void doDrop(List<ANode> node) {
			Set<MSqlTable> tablesset = new LinkedHashSet<MSqlTable>();
			Set<MSQLColumn> colsset = new LinkedHashSet<MSQLColumn>();
			Set<ANode> others = new LinkedHashSet<ANode>();
			Util.filterTables(node, tablesset, colsset, others);

			MFrom mfrom = null;
			EditPart ep = getTargetEditPart();
			if (ep instanceof ColumnEditPart)
				ep = ep.getParent();
			if (ep instanceof TableEditPart)
				ep = ep.getParent();

			if (ep instanceof FromEditPart)
				mfrom = (MFrom) ep.getModel();
			else
				mfrom = Util.getKeyword(designer.getRoot(), MFrom.class);

			if (!tablesset.isEmpty()) {
				// TODO for Slavic - Bugzilla #34318: TEMPORARY FIX THAT YOU SHOULD REVIEW
				// Forcing the loading of the tables information so the user can use smoothly
				// the graphical editor (Diagram Tab) without NPE.
				for(MSqlTable t : tablesset) {
					designer.getDbMetadata().loadTable(t);
				}
				CreateTable ct = designer.getOutline().getAfactory().getAction(CreateTable.class);
				if (ct.calculateEnabled(new Object[] { mfrom })) {
					ct.run(tablesset);
					refreshViewer();
				}
			}
			if (!colsset.isEmpty()) {
				CreateColumn ct = designer.getOutline().getAfactory().getAction(CreateColumn.class);
				if (ct.calculateEnabled(new Object[] { mfrom })) {
					ct.run(colsset);
					refreshViewer();
				}
			}
		}

		@Override
		protected Request createTargetRequest() {
			CreateRequest request = new CreateRequest();
			return request;
		}
	}
}
