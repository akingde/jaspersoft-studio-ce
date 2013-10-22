package com.jaspersoft.studio.components.table.model.column.action;

import java.util.List;
import java.util.Map;

import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.wb.swt.ResourceManager;

import com.jaspersoft.studio.components.Activator;
import com.jaspersoft.studio.components.table.model.column.MColumn;
import com.jaspersoft.studio.editor.action.IGlobalAction;
import com.jaspersoft.studio.editor.gef.util.CreateRequestUtil;
import com.jaspersoft.studio.editor.outline.actions.ACreateAction;
import com.jaspersoft.studio.editor.palette.JDPaletteCreationFactory;


public class TestColAction extends SelectionAction implements IGlobalAction {

	/** The Constant ID. */
	public static final String ID = "test_create_table_column_begin"; //$NON-NLS-1$

	
	
	/**
	 * Constructs a <code>CreateAction</code> using the specified part.
	 * 
	 * @param part
	 *            The part for this action
	 */
	public TestColAction(IWorkbenchPart part) {
		super(part);
		setId(ID);
		setImageDescriptor(Activator.getDefault().getImageDescriptor("icons/table-insert-column.png"));
		setDisabledImageDescriptor(Activator.getDefault().getImageDescriptor("icons/table-insert-column.png"));
	}
	
	
	@Override
	protected boolean calculateEnabled() {
		return true;
	}
	
	@Override
	public void run() {
		CreateColumnBeginAction action = new CreateColumnBeginAction(getWorkbenchPart());
		action.run();
	}

}