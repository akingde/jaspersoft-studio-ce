package com.jaspersoft.studio.editor.action.size;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.requests.AlignmentRequest;
import org.eclipse.gef.tools.ToolUtilities;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.ui.IWorkbenchPart;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.editor.gef.commands.ResizeCommand;

public class Size2BorderAction extends SelectionAction {

	/**
	 * Indicates that the bottom edges should be aligned.
	 */
	public static final String ID_SIZE_WIDTH = "band_WIDTH";

	/**
	 * Indicates that the horizontal centers should be aligned.
	 */
	public static final String ID_SIZE_HEIGHT = "band_HEIGHT";

	/**
	 * Indicates that the left edges should be aligned.
	 */
	public static final String ID_SIZE_BOTH = "band_BOTH";

	public final static int WIDTH = 0;
	public final static int HEIGHT = 1;
	public final static int BOTH = 2;

	private int alignment;
	private List<?> operationSet;

	public Size2BorderAction(IWorkbenchPart part, int alignment) {
		super(part);
		this.alignment = alignment;
		initUI();
	}

	/**
	 * Returns the alignment rectangle to which all selected parts should be aligned.
	 * 
	 * @param request
	 *          the alignment Request
	 * @return the alignment rectangle
	 */
	protected Rectangle calculateAlignmentRectangle(Request request) {
		List<?> editparts = getOperationSet(request);
		if (editparts == null || editparts.isEmpty())
			return null;
		GraphicalEditPart part = (GraphicalEditPart) editparts.get(editparts.size() - 1);
		Rectangle rect = new PrecisionRectangle(part.getFigure().getBounds());
		part.getFigure().translateToAbsolute(rect);
		return rect;
	}

	/**
	 * @see org.eclipse.gef.ui.actions.WorkbenchPartAction#calculateEnabled()
	 */
	protected boolean calculateEnabled() {
		operationSet = null;
		Command cmd = createAlignmentCommand();
		if (cmd == null)
			return false;
		return cmd.canExecute();
	}

	private Command createAlignmentCommand() {
		AlignmentRequest request = new AlignmentRequest(RequestConstants.REQ_ALIGN);
		request.setAlignmentRectangle(calculateAlignmentRectangle(request));
		request.setAlignment(alignment);
		List<?> editparts = getOperationSet(request);
		if (editparts.isEmpty())
			return null;

		CompoundCommand command = new CompoundCommand();
		command.setDebugLabel(getText());
		for (int i = 0; i < editparts.size(); i++) {
			EditPart editpart = (EditPart) editparts.get(i);
			command.add(new ResizeCommand(alignment, editpart));
			// command.add(editpart.getCommand(request));
		}
		return command;
	}

	/**
	 * @see org.eclipse.gef.Disposable#dispose()
	 */
	public void dispose() {
		operationSet = Collections.EMPTY_LIST;
		super.dispose();
	}

	/**
	 * Returns the list of editparts which will participate in alignment.
	 * 
	 * @param request
	 *          the alignment request
	 * @return the list of parts which will be aligned
	 */
	protected List<?> getOperationSet(Request request) {
		if (operationSet != null)
			return operationSet;
		List<?> editparts = new ArrayList(getSelectedObjects());
		if (editparts.isEmpty() || !(editparts.get(0) instanceof GraphicalEditPart))
			return Collections.EMPTY_LIST;
		Object primary = editparts.get(editparts.size() - 1);
		editparts = ToolUtilities.getSelectionWithoutDependants(editparts);
		ToolUtilities.filterEditPartsUnderstanding(editparts, request);
		if (!editparts.contains(primary))
			return Collections.EMPTY_LIST;
		EditPart parent = ((EditPart) editparts.get(0)).getParent();
		for (int i = 1; i < editparts.size(); i++) {
			EditPart part = (EditPart) editparts.get(i);
			if (part.getParent() != parent)
				return Collections.EMPTY_LIST;
		}
		return editparts;
	}

	/**
	 * Initializes the actions UI presentation.
	 */
	protected void initUI() {
		switch (alignment) {
		case WIDTH:
			setId(ID_SIZE_WIDTH);
			setText("Fit &Width");
			setToolTipText("Fit width");
			setImageDescriptor(JaspersoftStudioPlugin.getImageDescriptor("icons/eclipseapps/size_to_control_width.gif"));
			setDisabledImageDescriptor(JaspersoftStudioPlugin
					.getImageDescriptor("icons/eclipseapps/size_to_control_width.gif"));
			break;

		case HEIGHT:
			setId(ID_SIZE_HEIGHT);
			setText("Fit &Height");
			setToolTipText("Fit height");
			setImageDescriptor(JaspersoftStudioPlugin.getImageDescriptor("icons/eclipseapps/size_to_control_height.gif"));
			setDisabledImageDescriptor(JaspersoftStudioPlugin
					.getImageDescriptor("icons/eclipseapps/size_to_control_height.gif"));
			break;

		case BOTH:
			setId(ID_SIZE_BOTH);
			setText("Fit &Both");
			setToolTipText("Fit size to container");
			setImageDescriptor(JaspersoftStudioPlugin.getImageDescriptor("icons/eclipseapps/size_to_control.gif"));
			setDisabledImageDescriptor(JaspersoftStudioPlugin.getImageDescriptor("icons/eclipseapps/size_to_control.gif"));
			break;
		}
	}

	/**
	 * @see org.eclipse.jface.action.IAction#run()
	 */
	public void run() {
		operationSet = null;
		execute(createAlignmentCommand());
	}
}
