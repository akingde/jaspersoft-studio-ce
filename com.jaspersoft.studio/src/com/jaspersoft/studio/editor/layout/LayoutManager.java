/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.layout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.ui.IWorkbenchPart;

import com.jaspersoft.studio.editor.action.layout.LayoutAction;
import com.jaspersoft.studio.editor.layout.grid.JSSGridBagLayout;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.IContainerLayout;
import com.jaspersoft.studio.model.IGraphicElementContainer;
import com.jaspersoft.studio.model.IGroupElement;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.property.metadata.PropertyMetadataRegistry;

import net.sf.jasperreports.annotations.properties.PropertyScope;
import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.eclipse.util.Pair;
import net.sf.jasperreports.engine.JRBoxContainer;
import net.sf.jasperreports.engine.JRCommonElement;
import net.sf.jasperreports.engine.JRElement;
import net.sf.jasperreports.engine.JRElementGroup;
import net.sf.jasperreports.engine.JRLineBox;
import net.sf.jasperreports.engine.JRPropertiesHolder;
import net.sf.jasperreports.engine.JRPropertiesMap;
import net.sf.jasperreports.engine.design.JRDesignBand;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.properties.PropertyMetadata;
import net.sf.jasperreports.properties.StandardPropertyMetadata;

public class LayoutManager {

	public static void initMetadata() {
		List<PropertyMetadata> pm = new ArrayList<PropertyMetadata>();

		StandardPropertyMetadata spm = new StandardPropertyMetadata();
		spm.setName(ILayout.KEY);
		spm.setLabel("Layout");
		spm.setDescription("Container Layout");
		spm.setValueType(Class.class.getName());
		List<PropertyScope> scopes = new ArrayList<PropertyScope>();
		scopes.add(PropertyScope.FRAME);
		scopes.add(PropertyScope.BAND);
		scopes.add(PropertyScope.CROSSTAB_CELL);
		scopes.add(PropertyScope.TABLE_CELL);
		spm.setScopes(scopes);
		spm.setCategory("com.jaspersoft.studio.designer:layout");
		pm.add(spm);

		spm = new StandardPropertyMetadata();
		spm.setName(JSSGridBagLayout.PROPERTY_X);
		spm.setLabel("Grid x");
		spm.setDescription("The key used to store the column position");
		spm.setValueType(Integer.class.getName());
		scopes = new ArrayList<PropertyScope>();
		scopes.add(PropertyScope.ELEMENT);
		spm.setScopes(scopes);
		spm.setCategory("com.jaspersoft.studio.designer:layout");
		pm.add(spm);

		spm = new StandardPropertyMetadata();
		spm.setName(JSSGridBagLayout.PROPERTY_Y);
		spm.setLabel("Grid y");
		spm.setDescription("The key used to store the row position");
		spm.setValueType(Integer.class.getName());
		scopes = new ArrayList<PropertyScope>();
		scopes.add(PropertyScope.ELEMENT);
		spm.setScopes(scopes);
		spm.setCategory("com.jaspersoft.studio.designer:layout");
		pm.add(spm);

		spm = new StandardPropertyMetadata();
		spm.setName(JSSGridBagLayout.PROPERTY_COLSPAN);
		spm.setLabel("Column Span");
		spm.setDescription("The key used to store the column span");
		spm.setValueType(Integer.class.getName());
		scopes = new ArrayList<PropertyScope>();
		scopes.add(PropertyScope.ELEMENT);
		spm.setScopes(scopes);
		spm.setCategory("com.jaspersoft.studio.designer:layout");
		pm.add(spm);

		spm = new StandardPropertyMetadata();
		spm.setName(JSSGridBagLayout.PROPERTY_ROWSPAN);
		spm.setLabel("Row Span");
		spm.setDescription("The key used to store the row span");
		spm.setValueType(Integer.class.getName());
		scopes = new ArrayList<PropertyScope>();
		scopes.add(PropertyScope.ELEMENT);
		spm.setScopes(scopes);
		spm.setCategory("com.jaspersoft.studio.designer:layout");
		pm.add(spm);

		spm = new StandardPropertyMetadata();
		spm.setName(JSSGridBagLayout.PROPERTY_WEIGHT_COLUMN);
		spm.setLabel("Column Weight");
		spm.setDescription("The key used to store the column weight");
		spm.setValueType(Float.class.getName());
		scopes = new ArrayList<PropertyScope>();
		scopes.add(PropertyScope.ELEMENT);
		spm.setScopes(scopes);
		spm.setCategory("com.jaspersoft.studio.designer:layout");
		pm.add(spm);

		spm = new StandardPropertyMetadata();
		spm.setName(JSSGridBagLayout.PROPERTY_WEIGHT_ROW);
		spm.setLabel("Row Weight");
		spm.setDescription("The key used to store the row weight");
		spm.setValueType(Float.class.getName());
		scopes = new ArrayList<PropertyScope>();
		scopes.add(PropertyScope.ELEMENT);
		spm.setScopes(scopes);
		spm.setCategory("com.jaspersoft.studio.designer:layout");
		pm.add(spm);

		spm = new StandardPropertyMetadata();
		spm.setName(JSSGridBagLayout.PROPERTY_IS_FIXED);
		spm.setLabel("Is Fixed");
		spm.setDescription("The key used to store the property for the fixed size of the element");
		spm.setValueType(Boolean.class.getName());
		scopes = new ArrayList<PropertyScope>();
		scopes.add(PropertyScope.ELEMENT);
		spm.setScopes(scopes);
		spm.setCategory("com.jaspersoft.studio.designer:layout");
		pm.add(spm);

		PropertyMetadataRegistry.addMetadata(pm);
	}

	public static ILayout getLayout(JRPropertiesHolder[] elements, JasperDesign jDesign, String uuid) {
		return getLayout(elements, jDesign, uuid, new FreeLayout());
	}

	public static ILayout getLayout(JRPropertiesHolder[] elements, JasperDesign jDesign, String uuid, ILayout def) {
		for (JRPropertiesHolder pholder : elements) {
			if (pholder == null || pholder.getPropertiesMap() == null)
				continue;
			String prop = pholder.getPropertiesMap().getProperty(ILayout.KEY);
			if (prop != null)
				return instLayout(prop);
		}
		if (uuid != null && jDesign != null) {
			String prop = jDesign.getPropertiesMap().getProperty(ILayout.KEY + "." + uuid);
			if (prop != null)
				return instLayout(prop);
		}
		return def;
	}

	public static ILayout instLayout(String prop) {
		try {
			return (ILayout) Class.forName(prop).newInstance();
		} catch (InstantiationException e) {
		} catch (IllegalAccessException e) {
		} catch (ClassNotFoundException e) {
		}
		return new FreeLayout();
	}

	private static final Class<?>[] layouts = new Class<?>[] { HorizontalRowLayout.class, VerticalRowLayout.class,
			JSSGridBagLayout.class };

	private static ILayout[] LAYOUTNAMES;

	private static Map<String, ILayout> layoutsMap;

	public static void addActions(ActionRegistry registry, IWorkbenchPart part, List<String> selectionActions) {
		// Add the free layout
		IAction action = new LayoutAction(part, FreeLayout.class);
		registry.registerAction(action);
		selectionActions.add(action.getId());
		for (Class<?> id : layouts) {
			action = new LayoutAction(part, id);
			registry.registerAction(action);
			selectionActions.add(action.getId());
		}
	}

	public static void addMenu(MenuManager submenu, ActionRegistry actionRegistry) {
		// Add the free layout
		IAction action = actionRegistry.getAction(FreeLayout.class.getName());
		if (action.isEnabled())
			submenu.add(action);
		for (Class<?> id : layouts) {
			action = actionRegistry.getAction(id.getName());
			if (action.isEnabled())
				submenu.add(action);
		}
	}

	public static ILayout[] getAllLayouts() {
		if (LAYOUTNAMES == null) {
			LAYOUTNAMES = new ILayout[] { new FreeLayout(), new HorizontalRowLayout(), new VerticalRowLayout(),
					new JSSGridBagLayout() };
			layoutsMap = new HashMap<String, ILayout>();
			for (ILayout layout : LAYOUTNAMES) {
				layoutsMap.put(layout.getClass().getName(), layout);
			}
		}
		return LAYOUTNAMES;
	}

	public static ILayout getLayout(String className) {
		getAllLayouts();
		return layoutsMap.get(className);
	}

	public static Map<JRElement, Rectangle> layout(Map<JRElement, Rectangle> map, JRElement el) {
		if (el instanceof JRElementGroup && el instanceof JRPropertiesHolder) {
			Dimension d = null;
			if (el instanceof JRCommonElement) {
				JRCommonElement jce = (JRCommonElement) el;
				d = new Dimension(jce.getWidth(), jce.getHeight());
			}
			ILayout layout = LayoutManager.getLayout(new JRPropertiesHolder[] { el }, null, null);
			JRElementGroup group = (JRElementGroup) el;
			layout.layout(group.getElements(), d);
		}
		return map;
	}

	/**
	 * Get the properties holder of the current node if it can be found
	 * 
	 * @param node
	 *          the node
	 * @return the properties holder related to the node or null if it can't be found
	 */
	public static JRPropertiesHolder getPropertyHolder(ANode node) {
		if (node != null && node.getValue() instanceof JRPropertiesHolder) {
			return (JRPropertiesHolder) node.getValue();
		}
		if (node instanceof IContainerLayout) {
			JRPropertiesHolder[] holders = ((IContainerLayout) node).getPropertyHolder();
			if (holders.length > 0)
				return holders[0];
		}
		return null;
	}

	/**
	 * Given a node return the property map associated to that node
	 * 
	 * @param node
	 *          a not null node
	 * @return the property map of the node if it can be found, null otherwise. The map could or couldn't be a copy,
	 *         depends on the implementation of the methods used to return it
	 */
	public static JRPropertiesMap getPropertyMap(ANode node) {
		if (node != null) {
			if (node instanceof APropertyNode) {
				APropertyNode aNode = (APropertyNode) node;
				Object map = aNode.getPropertyValue(MGraphicElement.PROPERTY_MAP);
				if (map != null)
					return (JRPropertiesMap) map;
			}
			if (node.getValue() instanceof JRPropertiesMap) {
				return (JRPropertiesMap) node.getValue();
			} else {
				JRPropertiesHolder pholder = getPropertyHolder(node);
				if (pholder != null)
					return pholder.getPropertiesMap();
			}
		}
		return null;
	}

	/**
	 * Return the layout class type of the container node
	 * 
	 * @param container
	 *          the container node
	 * @return the layout class type. If a layout can't be found it return the free layout type
	 */
	public static Class<? extends ILayout> getContainerLayout(ANode container) {
		if (container == null) {
			return FreeLayout.class;
		} else {
			// get the properties of the parent
			JRPropertiesMap map = getPropertyMap(container);
			if (map != null) {
				String str = map.getProperty(ILayout.KEY);
				if (str == null) {
					if (container instanceof IContainerLayout) {
						ILayout defLayout = ((IContainerLayout) container).getDefaultLayout();
						if (defLayout != null) {
							str = defLayout.getClass().getName();
						}
					}
					if (str == null)
						str = FreeLayout.class.getName();
				}
				return LayoutManager.getLayout(str).getClass();
			} else {
				return FreeLayout.class;
			}
		}
	}

	/**
	 * Return the size of the container considering also the padding size
	 * 
	 * @param containerToLayout
	 *          the container to layout
	 * @return a not null dimension that the elements can occupy inside the container
	 */
	public static Dimension getPaddedSize(IGraphicElementContainer containerToLayout) {
		Dimension d = containerToLayout.getSize();
		int width = d.width - Misc.nvl(containerToLayout.getLeftPadding(), 0)
				- Misc.nvl(containerToLayout.getRightPadding(), 0);
		if (width < 0)
			width = 0;
		int height = d.height - Misc.nvl(containerToLayout.getTopPadding(), 0)
				- Misc.nvl(containerToLayout.getBottomPadding(), 0);
		if (height < 0)
			height = 0;
		return new Dimension(width, height);
	}

	/**
	 * Return the size of the container considering also the padding size
	 * 
	 * @param jce
	 *          the container to layout
	 * @return a not null dimension that the elements can occupy inside the container
	 */
	public static Dimension getPaddedSize(JRCommonElement jce) {
		if (jce instanceof JRLineBox) {
			Dimension d = new Dimension(jce.getWidth(), jce.getHeight());
			return getPaddedSize((JRLineBox) jce, d);
		} else if (jce instanceof JRBoxContainer) {
			Dimension d = new Dimension(jce.getWidth(), jce.getHeight());
			return getPaddedSize((JRBoxContainer) jce, d);
		} else {
			return new Dimension(jce.getWidth(), jce.getHeight());
		}
	}

	/**
	 * Return the size of the container considering also the padding size and also its padding information
	 * 
	 * @param jce
	 *          the container to layout
	 * @return a Pair where the key is a not null dimension that the elements can occupy inside the container, and the
	 *         value is the padding information of the container. This can be null if the container doesn't support the
	 *         padding
	 */
	public static Pair<Dimension, JRLineBox> getPaddedSizeAndBox(JRCommonElement jce) {
		if (jce instanceof JRLineBox) {
			Dimension d = new Dimension(jce.getWidth(), jce.getHeight());
			return new Pair<Dimension, JRLineBox>(getPaddedSize((JRLineBox) jce, d), (JRLineBox) jce);
		} else if (jce instanceof JRBoxContainer) {
			JRBoxContainer boxContainer = (JRBoxContainer) jce;
			Dimension d = new Dimension(jce.getWidth(), jce.getHeight());
			return new Pair<Dimension, JRLineBox>(getPaddedSize(boxContainer, d), boxContainer.getLineBox());
		} else {
			return new Pair<Dimension, JRLineBox>(new Dimension(jce.getWidth(), jce.getHeight()), null);
		}
	}

	/**
	 * Reduce the size of a container considering the padding values
	 * 
	 * @param lineBox
	 *          the box of the container where the padding informations are stored, if null the size of the container is
	 *          the baseSize since there are no paddings
	 * @param baseSize
	 *          the full size of the container, must be not null
	 * @return a not null dimension that the elements can occupy inside the container
	 */
	public static Dimension getPaddedSize(JRLineBox lineBox, Dimension baseSize) {
		if (lineBox == null)
			return baseSize;
		int width = baseSize.width - Misc.nvl(lineBox.getLeftPadding(), 0) - Misc.nvl(lineBox.getRightPadding(), 0);
		if (width < 0)
			width = 0;
		int height = baseSize.height - Misc.nvl(lineBox.getTopPadding(), 0) - Misc.nvl(lineBox.getBottomPadding(), 0);
		if (height < 0)
			height = 0;
		return new Dimension(width, height);
	}

	/**
	 * Reduce the size of a container considering the padding values
	 * 
	 * @param boxContainer
	 *          the box of the container where the padding informations are stored, if null the size of the container is
	 *          the baseSize since there are no paddings
	 * @param baseSize
	 *          the full size of the container, must be not null
	 * @return a not null dimension that the elements can occupy inside the container
	 */
	public static Dimension getPaddedSize(JRBoxContainer boxContainer, Dimension baseSize) {
		if (boxContainer == null)
			return baseSize;
		return getPaddedSize(boxContainer.getLineBox(), baseSize);
	}

	/**
	 * Create a layout command to layout the container passed as parameter
	 * 
	 * @param containerToLayout
	 *          the container to layout, if null the result will be null
	 * @return a layout command to do the layout of the passed container, can be null if the Container can not be layout
	 *         (maybe it null or not a valid container)
	 */
	public static LayoutCommand createRelayoutCommand(ANode containerToLayout) {
		if (containerToLayout == null)
			return null;
		Object jrElement = containerToLayout.getValue();

		// Search the parent group
		JRElementGroup jrGroup = null;
		if (containerToLayout instanceof IGroupElement)
			jrGroup = ((IGroupElement) containerToLayout).getJRElementGroup();
		else if (containerToLayout.getValue() instanceof JRElementGroup)
			jrGroup = (JRElementGroup) containerToLayout.getValue();

		// search the size of the parent
		Dimension d = new Dimension();
		if (containerToLayout instanceof IGraphicElementContainer) {
			// d = ((IGraphicElementContainer) containerToLayout).getSize();
			d = getPaddedSize((IGraphicElementContainer) containerToLayout);
		}
		if (jrElement instanceof JRCommonElement) {
			d = getPaddedSize((JRCommonElement) jrElement);
			// JRCommonElement jce = (JRCommonElement) jrElement;
			// d.setSize(new Dimension(jce.getWidth(), jce.getHeight()));
		} else if (jrElement instanceof JRDesignBand) {
			JasperDesign jDesign = containerToLayout.getJasperDesign();
			int w = jDesign.getPageWidth() - jDesign.getLeftMargin() - jDesign.getRightMargin();
			d.setSize(new Dimension(w, ((JRDesignBand) jrElement).getHeight()));
		}

		// get the properties of the parent
		JRPropertiesMap map = getPropertyMap(containerToLayout);
		if (map != null && jrGroup != null) {
			String str = map.getProperty(ILayout.KEY);
			if (str == null) {
				if (containerToLayout instanceof IContainerLayout) {
					ILayout defLayout = ((IContainerLayout) containerToLayout).getDefaultLayout();
					if (defLayout != null) {
						str = defLayout.getClass().getName();
					}
				}
				if (str == null)
					str = FreeLayout.class.getName();
			}
			ILayout parentLayout = LayoutManager.getLayout(str);
			return new LayoutCommand(jrGroup, parentLayout, d);
		}
		return null;
	}

	/**
	 * Try to create a layout command for a container and if it was possible to create it then it is executed
	 * 
	 * @param containerToLayout
	 *          the container to layout, if null this doesn't do anything
	 */
	public static void layoutContainer(ANode containerToLayout) {
		LayoutCommand cmd = createRelayoutCommand(containerToLayout);
		if (cmd != null)
			cmd.execute();
	}

	/**
	 * Create a map that contains the position of every inside every the specified parent plus a list of passed elements
	 * (that will be considered by the layout engine like the are inside the parent), layout with the container specified
	 * layout. This is used to show visual feedback of a creation or a movement of a child into a container. It will use
	 * the layout of the container to calculate the new positions but without change the current one, since the result of
	 * the computation are simply returned
	 * 
	 * @param containerToLayout
	 *          the container to layout, if null this doesn't do anything
	 * @param additionalElements
	 *          elements that will be considered by the layout engine like additional children of the container
	 * @return the map of every element inside the container, plus the additionalElements, which for every element show
	 *         the position inside the container with its layout
	 */
	public static Map<JRElement, Rectangle> createLayoutPosition(ANode containerToLayout,
			List<JRElement> additionalElements) {
		if (containerToLayout == null)
			return null;
		Object jrElement = containerToLayout.getValue();

		// Search the parent group
		JRElementGroup jrGroup = null;
		if (containerToLayout instanceof IGroupElement)
			jrGroup = ((IGroupElement) containerToLayout).getJRElementGroup();
		else if (containerToLayout.getValue() instanceof JRElementGroup)
			jrGroup = (JRElementGroup) containerToLayout.getValue();

		// search the size of the parent
		Dimension d = new Dimension();
		int topPadding = 0;
		int leftPadding = 0;
		if (containerToLayout instanceof IGraphicElementContainer) {
			// d = ((IGraphicElementContainer) containerToLayout).getSize();
			IGraphicElementContainer graphContainer = (IGraphicElementContainer) containerToLayout;
			d = getPaddedSize(graphContainer);
			topPadding = Misc.nvl(graphContainer.getTopPadding(), 0);
			leftPadding = Misc.nvl(graphContainer.getLeftPadding(), 0);
		}
		if (jrElement instanceof JRCommonElement) {
			Pair<Dimension, JRLineBox> sizeAndBox = getPaddedSizeAndBox((JRCommonElement) jrElement);
			d = sizeAndBox.getKey();
			if (sizeAndBox.getValue() != null) {
				topPadding = Misc.nvl(sizeAndBox.getValue().getTopPadding(), 0);
				leftPadding = Misc.nvl(sizeAndBox.getValue().getLeftPadding(), 0);
			}
			// JRCommonElement jce = (JRCommonElement) jrElement;
			// d.setSize(new Dimension(jce.getWidth(), jce.getHeight()));
		} else if (jrElement instanceof JRDesignBand) {
			JasperDesign jDesign = containerToLayout.getJasperDesign();
			int w = jDesign.getPageWidth() - jDesign.getLeftMargin() - jDesign.getRightMargin();
			d.setSize(new Dimension(w, ((JRDesignBand) jrElement).getHeight()));
		}

		// get the properties of the parent
		JRPropertiesMap map = getPropertyMap(containerToLayout);
		if (map != null && jrGroup != null) {
			String str = map.getProperty(ILayout.KEY);
			if (str == null) {
				if (containerToLayout instanceof IContainerLayout) {
					ILayout defLayout = ((IContainerLayout) containerToLayout).getDefaultLayout();
					if (defLayout != null) {
						str = defLayout.getClass().getName();
					}
				}
				if (str == null)
					str = FreeLayout.class.getName();
			}
			ILayout parentLayout = LayoutManager.getLayout(str);
			List<JRElement> elements = new ArrayList<JRElement>();
			for (JRElement element : jrGroup.getElements()) {
				elements.add(element);
			}
			elements.addAll(additionalElements);

			Map<JRElement, Rectangle> result = parentLayout
					.getLayoutPosition(elements.toArray(new JRElement[elements.size()]), d);
			if (topPadding != 0 || leftPadding != 0) {
				for (Rectangle rect : result.values()) {
					rect.setX(rect.x + leftPadding);
					rect.setY(rect.y + topPadding);
				}
			}

			return result;
		}
		return null;
	}
}
