/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.model.command;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.JRExpressionCollector;
import net.sf.jasperreports.engine.JasperReportsContext;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.field.MField;
import com.jaspersoft.studio.model.parameter.MParameter;
import com.jaspersoft.studio.model.variable.MVariable;
import com.jaspersoft.studio.utils.ModelUtils;

public class RenameDatasetObjectNameCommand extends Command {
	private String newvalue;
	private String oldvalue;
	private String type;
	private String type1;

	private JasperDesign jd;
	private JasperReportsContext jContext;
	private JRDataset dataset;
	private Set<JRDesignExpression> cexpr = new HashSet<JRDesignExpression>();
	private ANode node;

	public RenameDatasetObjectNameCommand(MField mfield, String oldvalue) {
		super();
		node = mfield;
		jd = mfield.getJasperDesign();
		jContext = mfield.getJasperConfiguration();
		dataset = ModelUtils.getDataset(mfield);
		type = "\\$F\\{";
		type1 = "$F{";
		this.newvalue = mfield.getValue().getName();
		this.oldvalue = oldvalue;
	}

	public RenameDatasetObjectNameCommand(MVariable mvar, String oldvalue) {
		super();
		node = mvar;
		jd = mvar.getJasperDesign();
		jContext = mvar.getJasperConfiguration();
		dataset = ModelUtils.getDataset(mvar);
		type = "\\$V\\{";
		type1 = "$V{";
		this.newvalue = mvar.getValue().getName();
		this.oldvalue = oldvalue;
	}

	public RenameDatasetObjectNameCommand(MParameter mparam, String oldvalue) {
		super();
		node = mparam;
		jd = mparam.getJasperDesign();
		jContext = mparam.getJasperConfiguration();
		dataset = ModelUtils.getDataset(mparam);
		type = "\\$P\\{";
		type1 = "$P{";
		this.newvalue = mparam.getValue().getName();
		this.oldvalue = oldvalue;
	}

	/**
	 * Search all the nodes that are using this styles and set the flag to tell the graphic manager to repaint them
	 * 
	 * @param childerns
	 *          the children of the actual level
	 */
	private void setModelRefresh(List<INode> childerns) {
		for (INode child : childerns) {
			setModelRefresh(child.getChildren());
		}
	}

	@Override
	public void execute() {
		cexpr.clear();
		if (dataset != null) {
			JRExpressionCollector reportCollector = JRExpressionCollector.collector(jContext, jd);
			JRExpressionCollector datasetCollector = reportCollector.getCollector(dataset);
			List<JRExpression> datasetExpressions = datasetCollector.getExpressions();
			// update expressions
			boolean modelAlreadyInitialized = false;
			for (JRExpression expr : datasetExpressions) {
				String s = expr.getText();
				if (s != null && s.length() > 4 && s.contains(type1 + oldvalue + "}")) {
					// If there are changes this will assure that the model of all the elements
					// is initialized, so the elements inside containers can be refreshed
					if (!modelAlreadyInitialized) {
						setModelRefresh(node.getRoot().getChildren());
						modelAlreadyInitialized = true;
					}

					s = s.replaceAll(type + Pattern.quote(oldvalue) + "}", type + Matcher.quoteReplacement(newvalue) + "}");
					JRDesignExpression dexpr = (JRDesignExpression) expr;
					dexpr.setText(s);
					cexpr.add((JRDesignExpression) expr);
				}
			}
			doSetQuery(oldvalue, newvalue);
		}
	}

	protected void doSetQuery(String oldVal, String newVal) {
		if (type1.equals("$P{")) {
			JRDesignQuery query = (JRDesignQuery) dataset.getQuery();
			if (query != null) {
				String q = query.getText();
				// replace $P{} in query
				query.setText(q.replaceAll(type + Pattern.quote(oldVal) + "}", type + Matcher.quoteReplacement(newVal) + "}"));
			}
		}
	}

	@Override
	public boolean canUndo() {
		return true;
	}

	@Override
	public void undo() {
		if (dataset != null) {
			for (JRDesignExpression de : cexpr) {
				de.setText(de.getText().replaceAll(Pattern.quote(newvalue), Matcher.quoteReplacement(oldvalue)));
			}
			doSetQuery(newvalue, oldvalue);
		}
	}
}
