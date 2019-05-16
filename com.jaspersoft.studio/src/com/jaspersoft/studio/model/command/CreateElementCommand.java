/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.model.command;

import java.util.List;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;
import org.eclipse.ui.views.properties.IPropertySource;

import com.jaspersoft.studio.JSSCompoundCommand;
import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.editor.defaults.DefaultManager;
import com.jaspersoft.studio.editor.layout.LazyLayoutCommand;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.IGroupElement;
import com.jaspersoft.studio.model.MElementGroup;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.model.band.MBand;
import com.jaspersoft.studio.model.frame.MFrame;
import com.jaspersoft.studio.preferences.DesignerPreferencePage;
import com.jaspersoft.studio.property.SetValueCommand;
import com.jaspersoft.studio.statistics.UsageStatisticsIDs;
import com.jaspersoft.studio.utils.ModelUtils;
import com.jaspersoft.studio.utils.SelectionHelper;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.engine.JRBand;
import net.sf.jasperreports.engine.JRElement;
import net.sf.jasperreports.engine.JRElementGroup;
import net.sf.jasperreports.engine.design.JRDesignBand;
import net.sf.jasperreports.engine.design.JRDesignComponentElement;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JRDesignElementGroup;
import net.sf.jasperreports.engine.design.JRDesignFrame;
import net.sf.jasperreports.engine.design.JasperDesign;

/**
 * Command used to create an element inside the container. If the container is
 * too small it is resized and if it has a layout also it is rearranged
 * 
 * @author Slavic & Orlandin Marco
 * 
 */
public class CreateElementCommand extends Command {

	/**
	 * The JasperDesign of the current Report
	 */
	protected JasperDesign jasperDesign;

	/**
	 * The JasperReportsConfiguration of the current report
	 */
	protected JasperReportsConfiguration jConfig;

	/**
	 * The node to create
	 */
	protected MGraphicElement srcNode;

	/**
	 * The container of the new node
	 */
	protected ANode destNode;

	/**
	 * The JR Object of the node to create
	 */
	protected JRDesignElement jrElement;

	/**
	 * The group of the destination node, it could be different from the value of
	 * the destination node
	 */
	protected JRElementGroup jrGroup;

	/**
	 * The location of the created node
	 */
	protected Rectangle location;

	/**
	 * The index of the new node inside the container
	 */
	protected int index;

	/**
	 * The layout command used to relayout the container
	 */
	private LazyLayoutCommand lCmd;

	/**
	 * Flag used to execute some operation (the change of the selection) only on the
	 * first execution and not on eventually redo operations
	 */
	private boolean firstTime = true;

	/**
	 * The list of commands executed to resize the container if necessary and
	 * relayout it, or additional commands added from the outside
	 */
	private JSSCompoundCommand commands;

	/**
	 * Flag used to mark a command as cancelled during it's execution
	 */
	protected boolean operationCancelled = false;

	/**
	 * This flag defines if upon the creation of the new element the default
	 * template (if any) should be applied. This is by default true but can be
	 * disabled when the command is used to create an element after a movement into
	 * another parent or a paste operation
	 */
	private boolean applyDefault = true;

	protected CreateElementCommand() {
		super();
	}

	/**
	 * Instantiates a new creates the element command.
	 * 
	 * @param destNode
	 *            the dest node
	 * @param srcNode
	 *            the src node
	 * @param index
	 *            the index
	 */
	public CreateElementCommand(MElementGroup destNode, MGraphicElement srcNode, int index) {
		super();
		setContext(destNode, srcNode, index);
	}

	/**
	 * Instantiates a new creates the element command.
	 * 
	 * @param destNode
	 *            the dest node
	 * @param srcNode
	 *            the src node
	 * @param index
	 *            the index
	 */
	public CreateElementCommand(MFrame destNode, MGraphicElement srcNode, int index) {
		super();
		setContext(destNode, srcNode, index);
	}

	public CreateElementCommand(MFrame destNode, MGraphicElement srcNode, Rectangle position, int index) {
		super();
		this.location = position;
		setContext(destNode, srcNode, index);
	}

	/**
	 * Instantiates a new creates the element command.
	 * 
	 * @param destNode
	 *            the dest node
	 * @param srcNode
	 *            the src node
	 * @param index
	 *            the index
	 */
	public CreateElementCommand(MBand destNode, MGraphicElement srcNode, int index) {
		super();
		setContext(destNode, srcNode, index);
	}

	/**
	 * Instantiates a new creates the element command.
	 * 
	 * @param destNode
	 *            the dest node
	 * @param srcNode
	 *            the src node
	 * @param position
	 *            the position
	 * @param index
	 *            the index
	 */
	public CreateElementCommand(ANode destNode, MGraphicElement srcNode, Rectangle position, int index) {
		super();
		location = position;
		jrElement = (JRDesignElement) srcNode.getValue();
		if (destNode instanceof IGroupElement) {
			setContext(destNode, srcNode, index);
			// if (destNode instanceof MBand)
			// fixLocation(location, (MBand) destNode);
		} else if (destNode instanceof MFrame) {
			setContext(destNode, srcNode, index);
		} else
			setContext(destNode, srcNode, index);
	}

	/**
	 * Sets the context.
	 * 
	 * @param destNode
	 *            the dest node
	 * @param srcNode
	 *            the src node
	 * @param index
	 *            the index
	 */
	protected void setContext(ANode destNode, MGraphicElement srcNode, int index) {
		if (destNode != null) {
			this.jConfig = destNode.getJasperConfiguration();
			this.srcNode = srcNode;
			this.jasperDesign = destNode.getJasperDesign();
			this.jrElement = (JRDesignElement) srcNode.getValue();
			if (destNode instanceof IGroupElement)
				jrGroup = ((IGroupElement) destNode).getJRElementGroup();
			else if (destNode.getValue() instanceof JRElementGroup)
				jrGroup = (JRElementGroup) destNode.getValue();
			this.destNode = destNode;
			this.index = index;
		} else {
			this.destNode = null;
			// MessageDialog.openInformation(UIUtils.getShell(), "Unable to create the
			// element",
			// "The element can not be created because there aren't containers where it can
			// be placed");
		}
	}

	/**
	 * Manually set the jasperdesign for the command
	 * 
	 * @param design
	 *            the new jasper design
	 */
	public void setJasperDesign(JasperDesign design) {
		this.jasperDesign = design;
	}

	/**
	 * Check if the command was cancelled during the execution
	 * 
	 * @return true if the command was cancelled during the execution, false
	 *         otherwise
	 */
	public boolean isCancelled() {
		return operationCancelled;
	}

	@Override
	public boolean canExecute() {
		return destNode != null && destNode.canAcceptChildren(srcNode);
	}

	public void fixLocation(Rectangle position, MBand band) {
		if (location == null) {
			if (jrElement != null)
				location = new Rectangle(jrElement.getX(), jrElement.getY(), jrElement.getWidth(),
						jrElement.getHeight());
			else if (band != null)
				location = new Rectangle(band.getBounds().x, band.getBounds().y, 50, 30);
			else
				location = new Rectangle(0, 0, 100, 100);
		}
		if (band != null)
			location = fixLocation(location, band, jrElement);
	}

	public static Rectangle fixLocation(Rectangle position, MBand band, JRDesignElement jrElement) {
		int x = position.x - band.getBounds().x;
		int y = position.y - band.getBounds().y;
		position.setLocation(x, y);
		return position;
	}

	/**
	 * Creates the JRElement to add if it was not defined before. Esstentially the
	 * JRObject can be both passed from outside or create a new default one using it
	 * ANode type
	 */
	protected void createObject() {
		if (jrElement == null)
			jrElement = srcNode.createJRElement(jasperDesign);
		if (jrElement != null)
			setElementBounds();
	}

	protected void setElementBounds() {
		if (location == null)
			location = new Rectangle(jrElement.getX(), jrElement.getY(), jrElement.getWidth(), jrElement.getHeight());
		if (location.width <= 0)
			location.width = srcNode.getDefaultWidth();
		if (location.height <= 0)
			location.height = srcNode.getDefaultHeight();

		jrElement.setX(location.x);
		jrElement.setY(location.y);
		jrElement.setWidth(location.width);
		jrElement.setHeight(location.height);

		if (jrGroup instanceof JRDesignBand && destNode.getJasperConfiguration()
				.getPropertyBoolean(DesignerPreferencePage.P_RESIZE_CONTAINER, Boolean.TRUE)) {
			JRDesignBand band = (JRDesignBand) jrGroup;
			int height = jrElement.getY() + jrElement.getHeight();
			if (band.getHeight() < height) {
				int maxBandHeight = ModelUtils.getMaxBandHeight(band, jasperDesign);
				// If the element is too big it will be resized to the maximum band size
				if (maxBandHeight < height) {
					// The band can not grow to contain the elements, check if the element can enter
					// in the band
					if (jrElement.getHeight() < maxBandHeight) {
						// The element can enter, place it on the end of the band
						jrElement.setY(maxBandHeight - jrElement.getHeight());
						location.y = jrElement.getY();
					} else {
						// the element can enter, resize it and place it on the end
						height = maxBandHeight;
						jrElement.setHeight(height);
						jrElement.setY(maxBandHeight - jrElement.getHeight());
						location.y = jrElement.getY();
						location.height = jrElement.getHeight();
					}
				}
				SetValueCommand cmd = new SetValueCommand();
				cmd.setTarget((IPropertySource) destNode);
				cmd.setPropertyId(JRDesignBand.PROPERTY_HEIGHT);
				cmd.setPropertyValue(height);
				addCommand(cmd);
			}
		}

		if (applyDefault) {
			applayDefaultAttributes(srcNode.getClass(), jrElement);
		}
	}

	/**
	 * Apply the attribute from the default set, can be overridden to provide
	 * specific behavior
	 */
	protected void applayDefaultAttributes(Class<?> clazz, JRElement element) {
		DefaultManager.INSTANCE.applyDefault(clazz, element);
	}

	/**
	 * Add a command to the list of commands that will be executed after the element
	 * creation
	 * 
	 * @param command
	 *            command to add, if null it will not be added
	 */
	protected void addCommand(Command command) {
		if (commands == null)
			commands = new JSSCompoundCommand(srcNode);
		commands.add(command);
	}

	/**
	 * Add list of a command to the list of commands that will be executed after the
	 * element creation
	 * 
	 * @param cmds
	 *            commands to add, if null nothing will be added
	 */
	protected void addCommands(List<Command> cmds) {
		if (cmds != null)
			for (Command c : cmds)
				addCommand(c);
	}

	/**
	 * Execute all the secondary commands if at least one is defined
	 */
	protected void executeCommands() {
		if (commands != null)
			commands.execute();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	@Override
	public void execute() {
		// the creation of the object alter the drop location, we need to restore it at
		// the end of the execution
		// so if an undo and redo is done the calculation will be done on the origianl
		// value instead of the already
		// modified value
		
		// Create the object if necessary
		createObject();
		
		if (location == null)
			location = new Rectangle(jrElement.getX(), jrElement.getY(), jrElement.getWidth(), jrElement.getHeight());
		Rectangle originalLocation = location.getCopy();
		if (jrElement != null) {
			removeElement(jasperDesign, jrElement);
			if (jrGroup instanceof JRDesignElementGroup) {
				JRDesignElementGroup jrdgroup = (JRDesignElementGroup) jrGroup;
				if (index < 0 || index > jrGroup.getChildren().size())
					jrdgroup.addElement(jrElement);
				else
					jrdgroup.addElement(index, jrElement);
			} else if (jrGroup instanceof JRDesignFrame) {
				JRDesignFrame jFrame = (JRDesignFrame) jrGroup;
				if (index < 0 || index > jrGroup.getChildren().size())
					jFrame.addElement(jrElement);
				else
					jFrame.addElement(index, jrElement);
			}
			// create the command to relayout the parent
			if (lCmd == null) {
				lCmd = new LazyLayoutCommand(destNode);
				addCommand(lCmd);
			}
			executeCommands();
			if (firstTime) {
				SelectionHelper.setSelection(jrElement, false);
				firstTime = false;
			}

			// log the statistics of the creation of an element
			if (jrElement != null) {
				if (jrElement instanceof JRDesignComponentElement) {
					JRDesignComponentElement componentElement = (JRDesignComponentElement) jrElement;
					if (componentElement.getComponent() != null) {
						JaspersoftStudioPlugin.getInstance().getUsageManager().audit(
								componentElement.getComponent().getClass().getName(),
								UsageStatisticsIDs.CATEGORY_ELEMENT);
					} else {
						JaspersoftStudioPlugin.getInstance().getUsageManager().audit(
								jrElement.getClass().getName() + "[null_component]",
								UsageStatisticsIDs.CATEGORY_ELEMENT);
					}
				} else {
					JaspersoftStudioPlugin.getInstance().getUsageManager().audit(jrElement.getClass().getName(),
							UsageStatisticsIDs.CATEGORY_ELEMENT);
				}

			}
		}
		location = originalLocation;
	}

	/**
	 * This method defines if upon the creation of the new element the default
	 * template (if any) should be applied. This is by default true but can be
	 * disabled when the command is used to create an element after a movement into
	 * another parent or a paste operation
	 * 
	 * @param applyDefault
	 *            true if the default should be checked and applied if present,
	 *            false if it should not be applied in any case
	 */
	public void setApplyDefault(boolean applyDefault) {
		this.applyDefault = applyDefault;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#canUndo()
	 */
	@Override
	public boolean canUndo() {
		if (jrGroup == null || jrElement == null)
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	@Override
	public void undo() {
		if (commands != null)
			commands.undo();
		if (jrGroup instanceof JRDesignElementGroup)
			((JRDesignElementGroup) jrGroup).removeElement(jrElement);
		else if (jrGroup instanceof JRDesignFrame)
			((JRDesignFrame) jrGroup).removeElement(jrElement);
	}

	/**
	 * Gets the jr element that will be created
	 * 
	 * @return a not null JRDesignElement
	 */
	public JRDesignElement getJrElement() {
		return jrElement;
	}

	/**
	 * Gets the group of the destination node
	 * 
	 * @return the jr group
	 */
	public JRElementGroup getJrGroup() {
		return jrGroup;
	}

	/**
	 * Gets the location.
	 * 
	 * @return the location
	 */
	public Rectangle getLocation() {
		return location;
	}

	/**
	 * Gets the index.
	 * 
	 * @return the index
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * remove element from other containers
	 * 
	 * @param jasperDesign
	 * @param element
	 */
	public static void removeElement(JasperDesign jasperDesign, JRDesignElement element) {
		for (JRBand band : jasperDesign.getAllBands()) {
			JRDesignBand b = (JRDesignBand) band;
			b.removeElement(element);
			removeElement(element, b.getElements());
		}
	}

	public static void removeElement(JRDesignElement element, JRElement[] elements) {
		for (JRElement el : elements) {
			if (el instanceof IGroupElement) {
				JRDesignElementGroup egroup = (JRDesignElementGroup) ((IGroupElement) el).getJRElementGroup();
				egroup.removeElement(element);
				removeElement(element, egroup.getElements());
			} else if (el instanceof JRDesignFrame) {
				JRDesignFrame frame = (JRDesignFrame) el;
				frame.removeElement(element);
				removeElement(element, frame.getElements());
			}
		}
	}
}
