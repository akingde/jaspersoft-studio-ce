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
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.jaspersoft.studio.data.sql.SQLQueryDesigner;
import com.jaspersoft.studio.data.sql.Util;
import com.jaspersoft.studio.data.sql.action.select.CreateColumn;
import com.jaspersoft.studio.data.sql.action.table.CreateTable;
import com.jaspersoft.studio.data.sql.model.metadata.MColumn;
import com.jaspersoft.studio.data.sql.model.metadata.MSqlTable;
import com.jaspersoft.studio.data.sql.model.query.from.MFrom;
import com.jaspersoft.studio.data.sql.model.query.from.TableJoin;
import com.jaspersoft.studio.data.sql.model.query.select.MSelect;
import com.jaspersoft.studio.data.sql.ui.gef.parts.ColumnEditPart;
import com.jaspersoft.studio.data.sql.ui.gef.parts.SQLDesignerEditPartFactory;
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
		Composite cmp = new Composite(parent, SWT.BORDER);
		GridLayout layout = new GridLayout();
		layout.marginWidth = 0;
		layout.marginHeight = 0;
		cmp.setLayout(layout);

		viewer = new ScrollingGraphicalViewer();
		viewer.createControl(cmp);
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

		return cmp;
	}

	protected void refreshViewer() {
		viewer.setContents(Util.getKeyword(designer.getRoot(), MFrom.class));
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
			Set<MColumn> colsset = new LinkedHashSet<MColumn>();
			Set<ANode> others = new LinkedHashSet<ANode>();
			Util.filterTables(node, tablesset, colsset, others);

			if (!tablesset.isEmpty()) {
				CreateTable ct = designer.getOutline().getAfactory().getAction(CreateTable.class);
				if (ct.calculateEnabled(new Object[] { Util.getKeyword(designer.getRoot(), MFrom.class) })) {
					ct.run(tablesset);
					refreshViewer();
				}
			}
			if (!colsset.isEmpty()) {
				CreateColumn ct = designer.getOutline().getAfactory().getAction(CreateColumn.class);
				if (ct.calculateEnabled(new Object[] { Util.getKeyword(designer.getRoot(), MSelect.class) })) {
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
