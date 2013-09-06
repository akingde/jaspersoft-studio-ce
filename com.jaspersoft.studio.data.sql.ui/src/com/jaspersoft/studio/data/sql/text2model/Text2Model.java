package com.jaspersoft.studio.data.sql.text2model;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.model.XtextDocument;
import org.eclipse.xtext.util.concurrent.IUnitOfWork;

import com.jaspersoft.studio.data.sql.DbObjectName;
import com.jaspersoft.studio.data.sql.SQLQueryDesigner;
import com.jaspersoft.studio.data.sql.SelectQuery;
import com.jaspersoft.studio.data.sql.SelectSubSet;
import com.jaspersoft.studio.data.sql.Util;
import com.jaspersoft.studio.data.sql.action.union.CreateUnion;
import com.jaspersoft.studio.data.sql.impl.ColImpl;
import com.jaspersoft.studio.data.sql.impl.ModelImpl;
import com.jaspersoft.studio.data.sql.impl.SelectImpl;
import com.jaspersoft.studio.data.sql.model.metadata.INotInMetadata;
import com.jaspersoft.studio.data.sql.model.metadata.MSQLColumn;
import com.jaspersoft.studio.data.sql.model.metadata.MSqlSchema;
import com.jaspersoft.studio.data.sql.model.metadata.MSqlTable;
import com.jaspersoft.studio.data.sql.model.metadata.MTables;
import com.jaspersoft.studio.data.sql.model.query.AMKeyword;
import com.jaspersoft.studio.data.sql.model.query.MHaving;
import com.jaspersoft.studio.data.sql.model.query.MUnion;
import com.jaspersoft.studio.data.sql.model.query.MWhere;
import com.jaspersoft.studio.data.sql.model.query.from.MFrom;
import com.jaspersoft.studio.data.sql.model.query.from.MFromTable;
import com.jaspersoft.studio.data.sql.model.query.select.MSelect;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.MRoot;
import com.jaspersoft.studio.model.util.KeyValue;
import com.jaspersoft.studio.model.util.ModelVisitor;
import com.jaspersoft.studio.utils.Misc;

public class Text2Model {

	public static void text2model(final SQLQueryDesigner designer, XtextDocument doc) {
		designer.refreshViewer();
		cleanDBMetadata(designer.getDbMetadata().getRoot());
		System.out.println("convert the model");
		doc.readOnly(new IUnitOfWork<String, XtextResource>() {
			public String exec(XtextResource resource) {
				ANode root = designer.getRoot();
				EList<?> list = resource.getContents();
				if (list != null && !list.isEmpty()) {
					for (Object obj : list) {
						if (obj instanceof ModelImpl) {
							SelectQuery sq = ((ModelImpl) obj).getQuery();
							if (sq instanceof SelectImpl) {
								convertSelect(designer, root, (SelectImpl) sq);
								EList<SelectSubSet> op = ((SelectImpl) sq).getOp();
								if (op != null && !op.isEmpty()) {
									for (SelectSubSet sss : op) {
										MUnion munion = null;
										if (sss.getOp() != null) {
											munion = CreateUnion.createUnion(root);
											String setop = sss.getOp().toUpperCase();
											if (setop.equals(AMKeyword.SET_OPERATOR_UNION) && sss.getAll() != null)
												setop += " ALL";
											munion.setValue(setop);
										}
										convertSelect(designer, Misc.nvl(munion, root), (SelectImpl) sss.getQuery());
									}
								}
							}
						}
						ConvertOrderBy.convertOrderBy(designer, ((ModelImpl) obj).getOrderByEntry());
					}
				}
				return "";
			}

		});
	}

	public static void convertSelect(SQLQueryDesigner designer, ANode qroot, SelectImpl sel) {
		ConvertTables.convertTables(designer, qroot, sel.getTbl());
		ConvertSelectColumns.convertSelectColumns(designer, qroot, sel.getCols());
		ConvertExpression.convertExpression(designer, qroot, Util.getKeyword(qroot, MWhere.class), sel.getWhereExpression());
		ConvertGroupBy.convertGroupBy(designer, qroot, sel.getGroupByEntry());
		ConvertExpression.convertExpression(designer, qroot, Util.getKeyword(qroot, MHaving.class), sel.getHavingEntry());
	}

	private static void cleanDBMetadata(MRoot dbRoot) {
		new ModelVisitor<Object>(dbRoot) {

			@Override
			public boolean visit(INode n) {
				return true;
			}

			@Override
			public void iterate(INode node) {
				List<INode> toRemove = null;
				for (INode n : node.getChildren()) {
					if (n instanceof INotInMetadata && ((INotInMetadata) n).isNotInMetadata()) {
						if (toRemove == null)
							toRemove = new ArrayList<INode>();
						toRemove.add(n);
						continue;
					}
					if (visit(n))
						iterate(n);
				}
				if (toRemove != null)
					((ANode) node).removeChildren(toRemove);
			}
		};
	}

	public static String getDbObjectName(EList<EObject> eContents, int i) {
		int size = eContents.size();
		if (size >= i) {
			EObject eobj = eContents.get(size - i);
			if (eobj instanceof DbObjectName)
				return ((DbObjectName) eobj).getDbname();
		}
		if (!eContents.isEmpty() && eContents.get(0) instanceof ColImpl) {
			ColImpl c = (ColImpl) eContents.get(0);
			size = c.getEntries().size();
			if (size >= 3 - i)
				return c.getEntries().get(3 - i).getDbname();
		}
		return null;
	}

	public static boolean isInSchema(MSqlTable msqltable, String schema) {
		MSqlSchema mschema = null;
		if (schema != null) {
			if (msqltable.getParent() != null && msqltable.getParent() instanceof MSqlSchema)
				mschema = (MSqlSchema) msqltable.getParent();
			else if (msqltable.getParent().getParent() != null && msqltable.getParent().getParent() instanceof MSqlSchema)
				mschema = (MSqlSchema) msqltable.getParent().getParent();
			if (mschema != null && !mschema.getValue().equalsIgnoreCase(schema))
				return false;
		}
		return true;
	}

	public static MSqlTable getTableUnknown(MRoot dbroot, String schema, final String table) {
		MSqlSchema msqlschem = findSchema(dbroot, Misc.nvl(schema));
		return new MSqlTable(msqlschem, Misc.nvl(table), true);
	}

	private static MSqlSchema findSchema(MRoot dbroot, String schema) {
		for (INode n : dbroot.getChildren()) {
			MSqlSchema ms = (MSqlSchema) n;
			if ((schema != null && ms.getValue().equalsIgnoreCase(schema)) || (schema == null && ms.isCurrent()))
				return ms;
		}
		if (schema == null)
			schema = "";
		if (schema.isEmpty())
			for (INode n : dbroot.getChildren()) {
				MSqlSchema ms = (MSqlSchema) n;
				if (ms.getValue().equalsIgnoreCase(schema))
					return ms;
			}
		return new MSqlSchema(dbroot, schema, null, true);
	}

	public static MSqlTable findTable(MRoot dbroot, String schema, final String table) {
		MSqlSchema ms = findSchema(dbroot, schema);
		return new ModelVisitor<MSqlTable>(ms) {
			@Override
			public boolean visit(INode n) {
				if (n instanceof MSqlTable) {
					MSqlTable mt = (MSqlTable) n;
					if (mt.getValue().equalsIgnoreCase(table)) {
						setObject(mt);
						stop();
					}
				} else if (n instanceof MTables)
					return true;
				return false;
			}
		}.getObject();
	}

	public static KeyValue<MSQLColumn, MFromTable> findColumn(final MSelect msel, final String schema, final String table, final String column) {
		return new ModelVisitor<KeyValue<MSQLColumn, MFromTable>>(Util.getKeyword(msel, MFrom.class)) {

			@Override
			public boolean visit(INode n) {
				if (n instanceof MFromTable) {
					MFromTable ft = (MFromTable) n;
					MSqlTable msqltable = ft.getValue();
					if (!Text2Model.isInSchema(msqltable, schema))
						return true;
					if (table != null && !msqltable.getValue().equalsIgnoreCase(table)) {
						if (ft.getAlias() == null || (ft.getAlias() != null && !ft.getAlias().equalsIgnoreCase(table)))
							return true;
					}
					for (INode c : msqltable.getChildren()) {
						if (c instanceof MSQLColumn) {
							MSQLColumn mcol = (MSQLColumn) c;
							if (mcol.getValue().equalsIgnoreCase(column)) {
								setObject(new KeyValue<MSQLColumn, MFromTable>(mcol, ft));
								stop();
							}
						}
					}
					return true;
				}
				return false;
			}
		}.getObject();
	}
}
