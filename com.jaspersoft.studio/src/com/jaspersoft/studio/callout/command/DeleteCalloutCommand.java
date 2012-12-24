package com.jaspersoft.studio.callout.command;

import net.sf.jasperreports.engine.design.JRDesignElement;

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.callout.MCallout;
import com.jaspersoft.studio.model.ANode;

public class DeleteCalloutCommand extends Command {
	private ANode parent;
	private MCallout mcallout;

	public DeleteCalloutCommand(ANode parent, MCallout child) {
		super("Delete Callout");
		this.parent = (ANode) parent.getRoot();
		this.mcallout = child;
	}

	@Override
	public void execute() {
		mcallout.deleteCallout();
	}

	@Override
	public void undo() {
		mcallout.setParent(parent, -1);
		mcallout.setPropertyValue(JRDesignElement.PROPERTY_X, mcallout.getPropertyValue(JRDesignElement.PROPERTY_X));
	}

	@Override
	public boolean canExecute() {
		return true;
	}
}
