package com.jaspersoft.studio.editor.action.layout;

import java.util.List;

import net.sf.jasperreports.engine.JRElementGroup;
import net.sf.jasperreports.engine.design.JRDesignBand;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.ui.IWorkbenchPart;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.editor.layout.ILayout;
import com.jaspersoft.studio.editor.layout.LayoutCommand;
import com.jaspersoft.studio.editor.layout.LayoutManager;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.IGraphicElement;
import com.jaspersoft.studio.model.IGraphicElementContainer;
import com.jaspersoft.studio.model.IGroupElement;

public class LayoutAction extends SelectionAction {

	/** The Constant ID. */
	public static final String ID = "LayoutGroup"; //$NON-NLS-1$

	private ILayout layout;

	/**
	 * Constructs a <code>CreateAction</code> using the specified part.
	 * 
	 * @param part
	 *          The part for this action
	 */
	public LayoutAction(IWorkbenchPart part, Class<?> type) {
		super(part);
		setLazyEnablementCalculation(false);
		layout = LayoutManager.instLayout(type.getName());
		setText(layout.getName());
		setToolTipText(layout.getToolTip());
		setId(type.getName());
		setImageDescriptor(JaspersoftStudioPlugin.getImageDescriptor(layout.getIcon()));
		setDisabledImageDescriptor(JaspersoftStudioPlugin.getImageDescriptor(layout.getIcon()));
		setEnabled(false);
	}

	/**
	 * Returns <code>true</code> if the selected objects can be created. Returns <code>false</code> if there are no
	 * objects selected or the selected objects are not {@link EditPart}s.
	 * 
	 * @return if the command should be enabled
	 */
	protected boolean calculateEnabled() {
		Command cmd = createReorderCommand(getSelectedObjects());
		if (cmd == null)
			return false;
		return cmd.canExecute();
	}

	/**
	 * Create a command to create the selected objects.
	 * 
	 * @param objects
	 *          The objects to be deleted.
	 * @return The command to remove the selected objects.
	 */
	public Command createReorderCommand(List<?> objects) {
		if (objects == null || objects.isEmpty())
			return null;
		Object obj = objects.get(0);
		if (obj instanceof EditPart) {
			ANode n = (ANode) ((EditPart) obj).getModel();
			if (!(n instanceof IGraphicElement))
				return null;

			JRElementGroup container = getContainer(n);
			if (container == null)
				return null;

			Dimension size = null;
			if (container instanceof JRDesignElement) {
				JRDesignElement c = (JRDesignElement) container;
				size = new Dimension(c.getWidth(), c.getHeight());
			} else if (container instanceof JRDesignBand) {
				int h = ((JRDesignBand) container).getHeight();
				JasperDesign jDesign = n.getJasperDesign();
				int w = jDesign.getPageWidth() - jDesign.getLeftMargin() - jDesign.getRightMargin();
				size = new Dimension(w, h);
			} else if (n instanceof IGraphicElementContainer) {
				size = ((IGraphicElementContainer) n).getSize();
				size.expand(((IGraphicElementContainer) n).getLeftPadding(), ((IGraphicElementContainer) n).getTopPadding());
			} else if (n.getParent() instanceof IGraphicElementContainer) {
				IGraphicElementContainer prnt = (IGraphicElementContainer) n.getParent();
				size = prnt.getSize();
				size.expand(prnt.getLeftPadding(), prnt.getTopPadding());
			}

			return new LayoutCommand(container, layout, size);
		}
		return null;
	}

	private JRElementGroup getContainer(ANode n) {
		Object val = n.getValue();
		if (n instanceof IGroupElement)
			return ((IGroupElement) n).getJRElementGroup();
		if (val instanceof JRElementGroup)
			return (JRElementGroup) val;
		if (val instanceof JRDesignElement)
			return getContainer(n.getParent());
		return null;
	}

	/**
	 * Performs the create action on the selected objects.
	 */
	public void run() {
		execute(createReorderCommand(getSelectedObjects()));
	}

}
