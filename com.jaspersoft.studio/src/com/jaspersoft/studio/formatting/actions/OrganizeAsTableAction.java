package com.jaspersoft.studio.formatting.actions;

import java.util.List;

import net.sf.jasperreports.engine.design.JRDesignElement;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.ui.IWorkbenchPart;

import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.property.SetValueCommand;

public class OrganizeAsTableAction extends AbstractFormattingAction {

	/** The Constant ID. */
	public static final String ID = "organizeastable"; //$NON-NLS-1$
	
	public OrganizeAsTableAction(IWorkbenchPart part) {
		super(part);
		setText("Organize as Table");
		setToolTipText("Change the position of the selected elements to obtain a tabular disposition");
		setId(ID);
	}

	@Override
	protected boolean calculateEnabled() {
		return true;
	} 

  	
	protected Command createAlignmentCommand() {
		List<APropertyNode> nodes = getOperationSet();
		CompoundCommand command = new CompoundCommand();
		command.setDebugLabel(getText());
		
		if (nodes.isEmpty()) return command;
	  nodes = sortXY(nodes);
	  
	  int currentX = 0;
	  command.add(AlignMarginTopAction.generateCommand(nodes));
	  int i=1;
	  for (APropertyNode element : nodes)
	  {
	      // 1. Find the parent...
	      Rectangle oldBounds = getElementBounds((JRDesignElement)element.getValue());
  			SetValueCommand setCommand = new SetValueCommand();
  			setCommand.setTarget(element);
  			setCommand.setPropertyId(JRDesignElement.PROPERTY_X);
  			setCommand.setPropertyValue(currentX);
	      command.add(setCommand);
	      currentX += oldBounds.width+ 5*i;
	      i++;
	  }
	  command.add(SameHeightMinAction.generateCommand(nodes));
	 
		return command;
	}

}
