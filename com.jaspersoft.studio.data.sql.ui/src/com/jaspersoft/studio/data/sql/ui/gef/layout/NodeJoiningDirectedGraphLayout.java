package com.jaspersoft.studio.data.sql.ui.gef.layout;

import org.eclipse.draw2d.graph.DirectedGraph;
import org.eclipse.draw2d.graph.DirectedGraphLayout;

public class NodeJoiningDirectedGraphLayout extends DirectedGraphLayout {
	public void visit(DirectedGraph graph) {

		new DummyEdgeCreator().visit(graph);
		new ClusterEdgeCreator().visit(graph);

		super.visit(graph);
	}
}
