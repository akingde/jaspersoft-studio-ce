/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.sql.ui.gef;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.MouseWheelHandler;
import org.eclipse.gef.MouseWheelZoomHandler;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.dnd.AbstractTransferDropTargetListener;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gef.requests.CreationFactory;
import org.eclipse.gef.requests.GroupRequest;
import org.eclipse.gef.ui.actions.ZoomInAction;
import org.eclipse.gef.ui.actions.ZoomOutAction;
import org.eclipse.gef.ui.parts.GraphicalViewerKeyHandler;
import org.eclipse.gef.ui.parts.ScrollingGraphicalViewer;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.wb.swt.Keyboard;

import com.jaspersoft.studio.data.sql.SQLQueryDesigner;
import com.jaspersoft.studio.data.sql.Util;
import com.jaspersoft.studio.data.sql.action.LayoutAction;
import com.jaspersoft.studio.data.sql.action.TableModeShowAction;
import com.jaspersoft.studio.data.sql.action.TableSelectAllAction;
import com.jaspersoft.studio.data.sql.action.select.CreateColumn;
import com.jaspersoft.studio.data.sql.action.table.CreateTable;
import com.jaspersoft.studio.data.sql.action.table.DeleteTable;
import com.jaspersoft.studio.data.sql.messages.Messages;
import com.jaspersoft.studio.data.sql.model.metadata.MSQLColumn;
import com.jaspersoft.studio.data.sql.model.metadata.MSqlTable;
import com.jaspersoft.studio.data.sql.model.query.from.MFrom;
import com.jaspersoft.studio.data.sql.model.query.from.MFromTable;
import com.jaspersoft.studio.data.sql.model.query.from.MFromTableJoin;
import com.jaspersoft.studio.data.sql.model.query.from.TableJoin;
import com.jaspersoft.studio.data.sql.model.query.from.TableJoinDetail;
import com.jaspersoft.studio.data.sql.prefs.SQLEditorPreferencesPage;
import com.jaspersoft.studio.data.sql.ui.gef.command.AddTableCommand;
import com.jaspersoft.studio.data.sql.ui.gef.parts.ColumnEditPart;
import com.jaspersoft.studio.data.sql.ui.gef.parts.FromEditPart;
import com.jaspersoft.studio.data.sql.ui.gef.parts.QueryEditPart;
import com.jaspersoft.studio.data.sql.ui.gef.parts.SQLDesignerEditPartFactory;
import com.jaspersoft.studio.data.sql.ui.gef.parts.TableEditPart;
import com.jaspersoft.studio.dnd.NodeTransfer;
import com.jaspersoft.studio.model.AMapElement;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.DialogEnabledCommand;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.util.ModelVisitor;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.FileUtils;
import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.engine.design.JRDesignDataset;

public class SQLQueryDiagram {
	private SQLQueryDesigner designer;
	private ScrollingGraphicalViewer viewer;
	public static final String SQLQUERYDIAGRAM = "SQLQUERYDIAGRAM"; //$NON-NLS-1$

	public SQLQueryDiagram(SQLQueryDesigner designer) {
		this.designer = designer;
	}

	private ZoomInAction zoomIn;
	private ZoomOutAction zoomOut;

	public Control createDiagram(Composite parent) {
		viewer = new ScrollingGraphicalViewer();
		viewer.createControl(parent);
		viewer.setEditDomain(new DefaultEditDomain(null));
		viewer.setRootEditPart(new SQLDesignerRootEditPart());
		viewer.setEditPartFactory(new SQLDesignerEditPartFactory());

		final ZoomManager zoomManager = (ZoomManager) viewer.getProperty(ZoomManager.class.toString());
		zoomIn = new ZoomInAction(zoomManager);
		zoomOut = new ZoomOutAction(zoomManager);
		viewer.setKeyHandler(new GraphicalViewerKeyHandler(viewer) {
			@Override
			public boolean keyPressed(KeyEvent event) {
				if (event.keyCode == SWT.DEL || event.keyCode == SWT.BS)
					doDeleteTable();
				else if ((event.stateMask & Keyboard.getCtrlKey()) != 0)
					switch (event.keyCode) {
					case SWT.KEYPAD_ADD:
					case '+':
					case '=':
						if (zoomManager.canZoomIn())
							zoomIn.run();
						break;
					case '-':
					case SWT.KEYPAD_SUBTRACT:
						if (zoomManager.canZoomOut())
							zoomOut.run();
						break;
					case '0':
						zoomManager.setZoom(1);
						break;
					case 'z':
						if (viewer.getEditDomain().getCommandStack().canUndo()) {
							viewer.getEditDomain().getCommandStack().undo();
							refreshViewer(false, false);
						}
						break;
					case 'y':
						if (viewer.getEditDomain().getCommandStack().canRedo()) {
							viewer.getEditDomain().getCommandStack().redo();
							refreshViewer(false, false);
						}
						break;
					}
				else if (((event.stateMask & SWT.ALT) != 0 && event.keyCode == 'z')) {
					if (viewer.getEditDomain().getCommandStack().canRedo()) {
						viewer.getEditDomain().getCommandStack().redo();
						refreshViewer(false, false);
					}
				}
				return super.keyPressed(event);
			}
		});
		viewer.setProperty(MouseWheelHandler.KeyGenerator.getKey(SWT.MOD1), MouseWheelZoomHandler.SINGLETON);
		viewer.setProperty(SQLQUERYDIAGRAM, designer);
		viewer.setContextMenu(new ContextMenuProvider(viewer) {

			@Override
			public void buildContextMenu(IMenuManager menu) {
				Object[] selection = null;
				IStructuredSelection s = (IStructuredSelection) viewer.getSelection();

				if (s != null) {
					List<Object> models = new ArrayList<>();
					for (Object obj : s.toList()) {
						if (obj instanceof EditPart) {
							if (obj instanceof ColumnEditPart)
								models.add(obj);
							else
								obj = ((EditPart) obj).getModel();
						}
						if (obj instanceof ANode)
							models.add((ANode) obj);
						if (obj instanceof TableJoin)
							models.add(((TableJoin) obj).getJoinTable());
						if (obj instanceof TableJoinDetail)
							models.add(obj);
					}
					if (!models.isEmpty())
						selection = models.toArray();
				}
				designer.getOutline().getAfactory().fillMenu(selection, menu);
				for (IContributionItem c : menu.getItems()) {
					if (c instanceof ActionContributionItem
							&& ((ActionContributionItem) c).getAction() instanceof DeleteTable) {
						menu.insertAfter(c.getId(), new Action(Messages.SQLQueryDiagram_1) {
							public void run() {
								doDeleteTable();
							}
						});
						menu.remove(c);
					}
				}
				LayoutAction action = new LayoutAction(designer);
				if (action.isEnabled()) {
					menu.add(action);
					menu.add(new org.eclipse.jface.action.Separator());
					menu.add(zoomIn);
					menu.add(zoomOut);
				}
				menu.add(new org.eclipse.jface.action.Separator());

				TableSelectAllAction sa = new TableSelectAllAction(designer, Messages.SQLQueryDiagram_0);
				if (sa.calculateEnabled(selection))
					menu.add(sa);

				TableModeShowAction ms = new TableModeShowAction(designer, "keys", Messages.SQLQueryDiagram_2); //$NON-NLS-1$
				if (ms.calculateEnabled(selection))
					menu.add(ms);
				ms = new TableModeShowAction(designer, null, Messages.SQLQueryDiagram_3);
				if (ms.calculateEnabled(selection))
					menu.add(ms);
				ms = new TableModeShowAction(designer, "short", //$NON-NLS-1$
						Messages.SQLQueryDiagram_9);
				if (ms.calculateEnabled(selection))
					menu.add(ms);

			}
		});
		viewer.addDropTargetListener(new QueryDesignerDropTargetListener(viewer, NodeTransfer.getInstance()));

		refreshViewer(false, true);

		return viewer.getControl();
	}

	public ScrollingGraphicalViewer getViewer() {
		return viewer;
	}

	public static final String SQL_EDITOR_TABLES = "com.jaspersoft.studio.data.sql.tables"; //$NON-NLS-1$

	class Table {
		public String tbl;
		public String id;
		public int x;
		public int y;
		public String sm;
	}

	protected void refreshViewer(boolean refreshSource, boolean reread) {
		JRDesignDataset ds = designer.getjDataset();
		if (reread && ds != null) {
			String tbls = ds.getPropertiesMap().getProperty(SQL_EDITOR_TABLES);
			if (!Misc.isNullOrEmpty(tbls)) {
				final List<Table> map = new ArrayList<>();
				try {
					String[] tables = net.sf.jasperreports.eclipse.util.Misc
							.decodeBase64String(tbls, FileUtils.LATIN1_ENCODING).split(";"); //$NON-NLS-1$
					for (String t : tables) {
						String[] tprm = t.split(","); //$NON-NLS-1$
						Table tbl = new Table();
						tbl.tbl = tprm.length > 0 ? tprm[0] : null;
						String xs = tprm.length > 1 ? tprm[1] : null;
						String ys = tprm.length > 2 ? tprm[2] : null;
						tbl.id = tprm.length > 3 ? tprm[3] : null;
						tbl.sm = tprm.length > 4 ? tprm[4] : null;
						if (tbl != null && xs != null && ys != null)
							try {
								tbl.x = Integer.parseInt(xs);
								tbl.y = Integer.parseInt(ys);
								map.add(tbl);
							} catch (NumberFormatException nfe) {
								// ignore
							}
					}
					final List<AMapElement> processed = new ArrayList<>();
					new ModelVisitor<Object>(designer.getRoot()) {

						@Override
						public boolean visit(INode n) {
							Table key = null;
							if (n instanceof MFromTable) {
								MFromTable ft = (MFromTable) n;
								String t = ft.getValue().toSQLString() + ft.getAliasKeyString();
								for (Table kv : map) {
									if (!kv.tbl.equals("\t\tFROM") //$NON-NLS-1$
											&& kv.tbl.equals(t) && kv.id.equals(ft.getId())) {
										key = setupTable(processed, ft, kv);
										break;
									}
								}
							} else if (n instanceof MFrom) {
								MFrom ft = (MFrom) n;
								for (Table kv : map) {
									if (kv.tbl.equals("\t\tFROM") //$NON-NLS-1$
											&& kv.id.equals(ft.getId())) {
										key = setupTable(processed, ft, kv);
										break;
									}
								}
							}
							if (key != null)
								map.remove(key);
							return true;
						}
					};
					if (!map.isEmpty()) {
						new ModelVisitor<Object>(designer.getRoot()) {

							@Override
							public boolean visit(INode n) {
								Table key = null;
								if (n instanceof MFromTable && !processed.contains(n)) {
									MFromTable ft = (MFromTable) n;
									String t = ft.getValue().toSQLString() + ft.getAliasKeyString();
									for (Table kv : map) {
										if (!kv.tbl.equals("\t\tFROM") //$NON-NLS-1$
												&& kv.tbl.equals(t)) {
											key = setupTable(processed, ft, kv);
											break;
										}
									}
								}
								if (key != null)
									map.remove(key);
								return true;
							}
						};
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		viewer.setContents(designer.getRoot());

		TreeSelection s = (TreeSelection) designer.getOutline().getTreeViewer().getSelection();
		List<MFromTable> tables = new ArrayList<>();
		for (Object obj : s.toList()) {
			if (obj instanceof MFromTable)
				tables.add((MFromTable) obj);
		}
		if (!tables.isEmpty()) {
			List<TableEditPart> parts = new ArrayList<>();
			EditPart ep = viewer.getRootEditPart();
			for (Object obj : ep.getChildren())
				doAddParts(obj, parts, tables);
			viewer.setSelection(new StructuredSelection(parts));
			if (!parts.isEmpty())
				viewer.reveal(parts.get(0));
		}
		if (refreshSource)
			designer.refreshQueryText();
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

	public void scheduleRefresh(boolean refreshSource, boolean reread) {
		refreshViewer(refreshSource, reread);
	}

	public void dispose() {
		// do nothing
	}

	protected void doDeleteTable() {
		List<EditPart> parts = new ArrayList<>(viewer.getSelectedEditParts());
		if (parts.size() > 1)
			Collections.sort(parts, new Comparator<EditPart>() {

				@Override
				public int compare(EditPart o1, EditPart o2) {
					if (o1 == o2)
						return 0;
					if (o1.getModel() instanceof MFromTableJoin)
						return -1;
					else
						return 1;
				}
			});
		CompoundCommand cc = new CompoundCommand() {
			private boolean firstRun = false;
			private boolean run = true;

			@Override
			public void execute() {
				if (!firstRun)
					run = UIUtils.showDeleteConfirmation(viewer.getControl().getShell(), Messages.SQLQueryDiagram_6);
				firstRun = true;
				if (run)
					super.execute();
			}

			@Override
			public void undo() {
				if (run)
					super.undo();
			}
		};
		for (EditPart p : parts) {
			GroupRequest deleteReq = new GroupRequest(RequestConstants.REQ_DELETE);
			Map<String, String> extendedData = new HashMap<>();
			extendedData.put(Messages.SQLQueryDiagram_7, Messages.SQLQueryDiagram_8);
			deleteReq.setExtendedData(extendedData);
			deleteReq.setEditParts(p);
			cc.add(p.getCommand(deleteReq));
		}
		viewer.getEditDomain().getCommandStack().execute(cc);
		designer.refreshQueryText();
		refreshViewer(true, false);
	}

	private Table setupTable(final List<AMapElement> processed, AMapElement ft, Table kv) {
		Table key;
		ft.setNoEvents(true);
		ft.setPropertyValue(MFromTable.PROP_X, kv.x);
		ft.setPropertyValue(MFromTable.PROP_Y, kv.y);
		ft.setPropertyValue(MFromTable.SHOW_MODE_PROPERTY, kv.sm);
		ft.setNoEvents(false);
		key = kv;
		processed.add(ft);
		return key;
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
			if (getCurrentEvent() == null)
				return;
			final Set<MSqlTable> tablesset = new LinkedHashSet<>();
			Set<MSQLColumn> colsset = new LinkedHashSet<>();
			Set<ANode> others = new LinkedHashSet<>();
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
				// TODO for Slavic - Bugzilla #34318: TEMPORARY FIX THAT YOU
				// SHOULD
				// REVIEW
				// Forcing the loading of the tables information so the user can
				// use
				// smoothly
				// the graphical editor (Diagram Tab) without NPE.
				Set<MSqlTable> tmp = new LinkedHashSet<>();
				for (MSqlTable t : tablesset) {
					MSqlTable mt = Util.getTable(designer.getDbMetadata().getRoot(), t);
					designer.getDbMetadata().loadTable(mt);
					tmp.add(mt);
				}
				tablesset.clear();
				tablesset.addAll(tmp);

				CreateTable ct = designer.getOutline().getAfactory().getAction(CreateTable.class);
				if (ct.calculateEnabled(new Object[] { mfrom })) {
					CreateRequest tgReq = (CreateRequest) getTargetRequest();
					tgReq.setFactory(new CreationFactory() {

						@Override
						public Object getObjectType() {
							return MSqlTable.class;
						}

						@Override
						public Object getNewObject() {
							return tablesset;
						}

					});
					Command command = null;
					if (getTargetEditPart() != null) {
						command = getTargetEditPart().getCommand(tgReq);
						if (command != null) {
							if (command instanceof AddTableCommand) {
								AddTableCommand c = (AddTableCommand) command;
								c.setJoinOnDND(designer.getjConfig().getProperty(SQLEditorPreferencesPage.P_JOIN_ON_DND,
										SQLEditorPreferencesPage.DROP));
								c.setDnDDetail(getCurrentEvent().detail);
							}
							if (command instanceof DialogEnabledCommand && command.canExecute()) {
								// If we have a special command that supports
								// dialog
								// (i.e: image
								// creation)
								// we'll show the popup dialog and continue with
								// creation only if
								// the user has confirmed.
								if (((DialogEnabledCommand) command).openDialog() == Dialog.CANCEL) {
									getCurrentEvent().detail = DND.DROP_NONE;
									return;
								}
							}
						}
					}
					if (command != null && command.canExecute())
						getViewer().getEditDomain().getCommandStack().execute(command);
					else
						getCurrentEvent().detail = DND.DROP_NONE;
					refreshViewer(true, false);
				}
			}
			if (!colsset.isEmpty()) {
				CreateColumn ct = designer.getOutline().getAfactory().getAction(CreateColumn.class);
				if (ct.calculateEnabled(new Object[] { mfrom })) {
					ct.run(colsset);
					refreshViewer(true, false);
				}
			}
		}

		@Override
		protected Request createTargetRequest() {
			return new CreateRequest();
		}

	}
}
