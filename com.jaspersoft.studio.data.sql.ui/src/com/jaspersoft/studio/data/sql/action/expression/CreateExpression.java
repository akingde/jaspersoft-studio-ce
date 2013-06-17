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
package com.jaspersoft.studio.data.sql.action.expression;

import java.util.Collection;
import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.widgets.Display;

import com.jaspersoft.studio.data.sql.SQLQueryDesigner;
import com.jaspersoft.studio.data.sql.Util;
import com.jaspersoft.studio.data.sql.action.AAction;
import com.jaspersoft.studio.data.sql.action.table.CreateTable;
import com.jaspersoft.studio.data.sql.dialogs.EditExpressionDialog;
import com.jaspersoft.studio.data.sql.model.enums.Operator;
import com.jaspersoft.studio.data.sql.model.metadata.MColumn;
import com.jaspersoft.studio.data.sql.model.metadata.MSqlTable;
import com.jaspersoft.studio.data.sql.model.query.MExpression;
import com.jaspersoft.studio.data.sql.model.query.MExpressionGroup;
import com.jaspersoft.studio.data.sql.model.query.MHaving;
import com.jaspersoft.studio.data.sql.model.query.MWhere;
import com.jaspersoft.studio.data.sql.model.query.from.MFrom;
import com.jaspersoft.studio.data.sql.model.query.from.MFromTable;
import com.jaspersoft.studio.data.sql.model.query.from.MFromTableJoin;
import com.jaspersoft.studio.data.sql.model.query.operand.FieldOperand;
import com.jaspersoft.studio.data.sql.model.query.select.MSelectColumn;
import com.jaspersoft.studio.data.sql.widgets.Factory;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.MRoot;

public class CreateExpression extends AAction {
	private CreateTable ct;

	public CreateExpression(SQLQueryDesigner designer) {
		super("&Add Expression", designer);
	}

	@Override
	public boolean calculateEnabled(Object[] selection) {
		super.calculateEnabled(selection);
		return selection != null && selection.length == 1 && isInSelect(selection[0]);
	}

	public static boolean isInSelect(Object element) {
		return element instanceof MWhere || element instanceof MHaving || element instanceof MExpression || element instanceof MFromTableJoin || element instanceof MExpressionGroup;
	}

	@Override
	public void run() {
		Object sel = selection[0];
		MExpression mexpr = null;
		if (sel instanceof MExpression)
			mexpr = run(null, (MExpression) sel);
		else if (isInSelect(sel))
			mexpr = run(null, (ANode) sel, -1);
		mexpr.getOperands().add(Factory.getDefaultOperand(mexpr));
		mexpr.getOperands().add(Factory.getDefaultOperand(mexpr));
		showDialog(mexpr);
	}

	public void run(Collection<MColumn> nodes) {
		Object sel = selection[0];
		MExpression mexpr = null;
		List<MFromTable> tbls = Util.getFromTables((ANode) sel);
		for (MColumn t : nodes) {
			MSqlTable tbl = (MSqlTable) t.getParent();
			MFromTable mftable = null;
			for (MFromTable ft : tbls) {
				if (ft.getValue().equals(tbl)) {
					mftable = ft;
					break;
				}
			}
			if (mftable == null) {
				if (ct == null)
					ct = new CreateTable(designer);
				MRoot r = (MRoot) ((ANode) sel).getRoot();
				for (INode n : r.getChildren()) {
					if (n instanceof MFrom) {
						mftable = ct.run(tbl, (MFrom) n, -1);
						break;
					}
				}
			}
			if (sel instanceof MExpression)
				mexpr = run(t, (MExpression) sel);
			else if (isInSelect(sel))
				mexpr = run(t, (ANode) sel, -1);
			sel = mexpr;
			mexpr.getOperands().add(new FieldOperand(t, mftable, mexpr));
			mexpr.getOperands().add(Factory.getDefaultOperand(mexpr));
		}
		showDialog(mexpr);
	}

	public void run(ANode node, MSelectColumn selcol) {
		MExpression mexpr = run(selcol.getValue(), node, -1);
		mexpr.getOperands().add(new FieldOperand(selcol.getValue(), selcol.getMFromTable(), mexpr));
		mexpr.getOperands().add(Factory.getDefaultOperand(mexpr));
		showDialog(mexpr);
	}

	protected void showDialog(MExpression mexpr) {
		EditExpressionDialog dialog = new EditExpressionDialog(Display.getDefault().getActiveShell());
		dialog.setValue(mexpr);
		if (dialog.open() == Dialog.OK) {
			mexpr.setOperator(Operator.getOperator((dialog.getOperator())));
			mexpr.setPrevCond(dialog.getPrevcond());
			mexpr.setOperands(dialog.getOperands());
			selectInTree(mexpr);
		} else {
			ANode p = mexpr.getParent();
			p.removeChild(mexpr);
			selectInTree(p);
		}
	}

	protected MExpression run(Object node, MExpression mtable) {
		ANode mfrom = mtable.getParent();
		return run(node, mfrom, mfrom.getChildren().indexOf(mtable) + 1);
	}

	public MExpression run(Object node, ANode select, int index) {
		return new MExpression(select, node, index);
	}

}
