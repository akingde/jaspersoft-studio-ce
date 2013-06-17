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
package com.jaspersoft.studio.data.sql.ui.gef.layout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.draw2d.AbsoluteBendpoint;
import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.draw2d.graph.DirectedGraph;
import org.eclipse.draw2d.graph.Edge;
import org.eclipse.draw2d.graph.Node;
import org.eclipse.draw2d.graph.NodeList;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

import com.jaspersoft.studio.data.sql.ui.gef.figures.SqlTableFigure;
import com.jaspersoft.studio.data.sql.ui.gef.parts.FromEditPart;
import com.jaspersoft.studio.data.sql.ui.gef.parts.RelationshipPart;
import com.jaspersoft.studio.data.sql.ui.gef.parts.TableEditPart;

public class DirectedGraphLayoutVisitor {
	Map<AbstractGraphicalEditPart, Object> partToNodesMap;
	DirectedGraph graph;

	/**
	 * Public method for reading graph nodes
	 */
	public void layoutDiagram(FromEditPart diagram) {
		partToNodesMap = new HashMap<AbstractGraphicalEditPart, Object>();
		graph = new DirectedGraph();
		addNodes(diagram);
		if (graph.nodes.size() > 0) {
			addEdges(diagram);
			new NodeJoiningDirectedGraphLayout().visit(graph);
			applyResults(diagram);
		}
	}

	// ******************* SchemaDiagramPart contribution methods **********/

	protected void addNodes(FromEditPart diagram) {
		for (Object obj : diagram.getChildren())
			addNodes((TableEditPart) obj);
	}

	/**
	 * Adds nodes to the graph object for use by the GraphLayoutManager
	 */
	protected void addNodes(TableEditPart tablePart) {
		Node n = new Node(tablePart);
		n.width = tablePart.getFigure().getPreferredSize(400, 300).width;
		n.height = tablePart.getFigure().getPreferredSize(400, 300).height;
		n.setPadding(new Insets(10, 8, 10, 12));
		partToNodesMap.put(tablePart, n);
		graph.nodes.add(n);
	}

	protected void addEdges(FromEditPart diagram) {
		for (int i = 0; i < diagram.getChildren().size(); i++) {
			TableEditPart tablePart = (TableEditPart) diagram.getChildren().get(i);
			addEdges(tablePart);
		}
	}

	// ******************* TablePart contribution methods **********/

	protected void addEdges(TableEditPart tablePart) {
		List<?> outgoing = tablePart.getSourceConnections();
		for (int i = 0; i < outgoing.size(); i++) {
			RelationshipPart relationshipPart = (RelationshipPart) tablePart.getSourceConnections().get(i);
			addEdges(relationshipPart);
		}
	}

	// ******************* RelationshipPart contribution methods **********/

	protected void addEdges(RelationshipPart relationshipPart) {
		Node source = (Node) partToNodesMap.get(relationshipPart.getSource());
		Node target = (Node) partToNodesMap.get(relationshipPart.getTarget());
		Edge e = new Edge(relationshipPart, source, target);
		e.weight = 2;
		graph.edges.add(e);
		partToNodesMap.put(relationshipPart, e);
	}

	// ******************* SchemaDiagramPart apply methods **********/

	protected void applyResults(FromEditPart diagram) {
		applyChildrenResults(diagram);
	}

	protected void applyChildrenResults(FromEditPart diagram) {
		for (int i = 0; i < diagram.getChildren().size(); i++) {
			TableEditPart tablePart = (TableEditPart) diagram.getChildren().get(i);
			applyResults(tablePart);
		}
	}

	protected void applyOwnResults(FromEditPart diagram) {
	}

	// ******************* TablePart apply methods **********/

	public void applyResults(TableEditPart tablePart) {

		Node n = (Node) partToNodesMap.get(tablePart);
		SqlTableFigure tableFigure = tablePart.getFigure();

		Rectangle bounds = new Rectangle(n.x, n.y, tableFigure.getPreferredSize().width, tableFigure.getPreferredSize().height);

		tableFigure.setBounds(bounds);

		for (int i = 0; i < tablePart.getSourceConnections().size(); i++) {
			RelationshipPart relationship = (RelationshipPart) tablePart.getSourceConnections().get(i);
			applyResults(relationship);
		}
	}

	// ******************* RelationshipPart apply methods **********/

	protected void applyResults(RelationshipPart relationshipPart) {
		Edge e = (Edge) partToNodesMap.get(relationshipPart);
		NodeList nodes = e.vNodes;

		PolylineConnection conn = (PolylineConnection) relationshipPart.getConnectionFigure();
		conn.setTargetDecoration(new PolygonDecoration());
		if (nodes != null) {
			List<AbsoluteBendpoint> bends = new ArrayList<AbsoluteBendpoint>();
			for (int i = 0; i < nodes.size(); i++) {
				Node vn = nodes.getNode(i);
				int x = vn.x;
				int y = vn.y;
				if (e.isFeedback()) {
					bends.add(new AbsoluteBendpoint(x, y + vn.height));
					bends.add(new AbsoluteBendpoint(x, y));
				} else {
					bends.add(new AbsoluteBendpoint(x, y));
					bends.add(new AbsoluteBendpoint(x, y + vn.height));
				}
			}
			conn.setRoutingConstraint(bends);
		} else {
			conn.setRoutingConstraint(Collections.EMPTY_LIST);
		}

	}
}
