package com.jaspersoft.studio.editor.action.xls;

import java.util.List;

import net.sf.jasperreports.engine.JRPropertiesMap;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.ui.IWorkbenchPart;

import com.jaspersoft.studio.editor.action.pdf.PropertiesList;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.property.SetValueCommand;

/**
 * Action used to set an XSL attribute
 * @author Orlandin Marco
 *
 */
public class XLSAction extends SelectionAction{
	
	/** Embedded attributes ids*/
	public static String FIT_COL_ID = "net.sf.jasperreports.export.xls.auto.fit.column";
	
	public static String FIT_ROW_ID = "net.sf.jasperreports.export.xls.auto.fit.row";
	
	public static String BREAK_AFTER_ROW_ID = "net.sf.jasperreports.export.xls.break.after.row";
	
	public static String BREAK_BEFORE_ROW_ID = "net.sf.jasperreports.export.xls.break.before.row";
	
	public static String CELL_HIDDEN_ID = "net.sf.jasperreports.export.xls.cell.hidden";
	
	public static String CELL_LOCKED_ID = "net.sf.jasperreports.export.xls.cell.locked";
	
	public static String AUTOFILTER_ID = "net.sf.jasperreports.export.xls.auto.filter";
	
	private String value;
	
	private String attributeId;
	
	public 	XLSAction(IWorkbenchPart part,String actionId, String value, String actionName){
		this(part,actionId,actionId,value,actionName);
	}
	
	public 	XLSAction(IWorkbenchPart part,String actionId, String attributeId, String value, String actionName){
		super(part);
		setId(actionId);
		this.attributeId = attributeId;
		this.value = value;
		//the property need to be registered
		PropertiesList.AddItem(actionId);
		setText(actionName);
	}
	
	/**
	 * Create the command for the selected action
	 * @param model Model of the selected item
	 * @return the command to execute
	 */
	public Command createCommand(MGraphicElement model){
		SetValueCommand cmd = new SetValueCommand();
		cmd.setTarget(model);
		cmd.setPropertyId(MGraphicElement.PROPERTY_MAP);
		JRPropertiesMap v = (JRPropertiesMap)model.getPropertyValue(MGraphicElement.PROPERTY_MAP);
		if (v == null){
			v = new JRPropertiesMap();
		} else {
			v.removeProperty(attributeId);
		}
		if (value != null) v.setProperty(attributeId, value);
		cmd.setPropertyValue(v);
		return cmd;
	}
	

	/**
	 * Performs the create action on the selected objects.
	 */
	@Override
	public void run() {
		execute(createAlignmentCommand());
	}
	
	/**
 * Returns the list of editparts which will participate in PDF Editing.
 * 
 * @return the list of parts which will be aligned
 */
private Command createAlignmentCommand() {
	List<?> editparts = getSelectedObjects();
	if (editparts.isEmpty() || !(editparts.get(0) instanceof GraphicalEditPart)){
		return null;
	} 
	CompoundCommand command = new CompoundCommand();
	command.setDebugLabel(getText());
	for (int i = 0; i < editparts.size(); i++) {
		EditPart editpart = (EditPart) editparts.get(i);
		if (editpart.getModel() instanceof MGraphicElement)
			command.add(createCommand((MGraphicElement)editpart.getModel()));
	}
	return command;
}


	@Override
	protected boolean calculateEnabled() {
		Command cmd = createAlignmentCommand();
		if (cmd == null)
			return false;
		return cmd.canExecute();
	}

}
