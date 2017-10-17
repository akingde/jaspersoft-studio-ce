/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.layout.spreadsheet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.editor.layout.AbstractLayout;
import com.jaspersoft.studio.editor.layout.FreeLayout;
import com.jaspersoft.studio.editor.layout.ILayout;
import com.jaspersoft.studio.editor.layout.ILayoutUIProvider;
import com.jaspersoft.studio.editor.layout.LayoutManager;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.MPage;
import com.jaspersoft.studio.model.MReport;
import com.jaspersoft.studio.model.band.MBand;
import com.jaspersoft.studio.model.frame.MFrame;
import com.jaspersoft.studio.utils.ModelUtils;
import com.jaspersoft.studio.utils.SelectionHelper;

import net.sf.jasperreports.engine.JRBand;
import net.sf.jasperreports.engine.JRElement;
import net.sf.jasperreports.engine.JRElementGroup;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.JRPropertiesHolder;
import net.sf.jasperreports.engine.JRPropertiesMap;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.type.BandTypeEnum;

/**
 * Layout used to define a spradsheet structure between a container and a band
 * 
 * @author Orlandin Marco
 */
public class SpreadsheetLayout extends AbstractLayout {
	
	private static HashMap<JasperDesign, SpreadSheetElementsCache> cache = new HashMap<JasperDesign, SpreadSheetElementsCache>();
	
	/**
	 * Property to associate an element to another one
	 */
	public static final String PROPERTY_ID = "com.jaspersoft.studio.spreadsheet.connectionID";
	
	/**
	 * Return the parent band of the selected element
	 * 
	 * @param element the starting element
	 * @return a {@link JRBand} that is ancestor of the passed element or null
	 * if it can't be found.
	 */
	private MBand getParentBand(ANode element){
		ANode parent = element;
		while (parent != null){
			if (parent instanceof MBand){
				return (MBand)parent;
			} else if (parent instanceof MPage){
				parent = ((MPage)parent).getRealParent();
			} else parent = parent.getParent();
		}
		return null;
	}
	
	/**
	 * Apply the layout. This work different from other layouts since it must layout other containers connected
	 * to the layouted one. Since the main container is always a detail band it look for the detail band, it propagates
	 * the changes done to this band to the detail connected elements, and then layout the detail. The layout of 
	 * the detail will update every other connected elements 
	 */
	public Map<JRElement, Rectangle> layout(JasperDesign jd, JRElementGroup container, JRElement[] elements, Dimension c) {
		MBand ancestorBand = getParentBand(SelectionHelper.getNode(container));
		if (ancestorBand.getBandType() != BandTypeEnum.DETAIL){
			//Search the detail band
			JRBand firstDetail = jd.getDetailSection().getBands()[0];
			
			
			List<JRDesignElement> detailElements = ModelUtils.getGElements(firstDetail);
			HashMap<String, JRDesignElement> detailElementsMap = new HashMap<String, JRDesignElement>();
			
			//remove the layout from the map (otherwise this will trigger a continuous refresh)
			firstDetail.getPropertiesMap().removeProperty(ILayout.KEY);
			
			//Store the elements in the detail band with their element uuid
			for(JRDesignElement detailElement : detailElements){
				String uuidProp = detailElement.getPropertiesMap().getProperty(PROPERTY_ID);
				if (uuidProp != null) detailElementsMap.put(uuidProp, detailElement);
			}
			
			//Propagate the changes to this band to the detail band
			for (JRElement el : elements) {
				String currentElementUUid = el.getPropertiesMap().getProperty(PROPERTY_ID);
				if (currentElementUUid != null){
					JRDesignElement detailElement = detailElementsMap.get(currentElementUUid);
					if (detailElement != null){
						detailElement.setWidth(el.getWidth());
					}
				}
			}
			
			//Restore the layout of the detail band
			firstDetail.getPropertiesMap().setProperty(ILayout.KEY, SpreadsheetLayout.class.getName());
			
			//laout the detail band
			return layoutDetail(jd, firstDetail, firstDetail.getElements(), new Dimension(jd.getPageWidth(), firstDetail.getHeight()));
		}
		return layoutDetail(jd, container, elements, c);
	}
	
	/**
	 * Layout the detail band and every connected element
	 * 
	 */
	private Map<JRElement, Rectangle> layoutDetail(JasperDesign jd, JRElementGroup container, JRElement[] elements, Dimension c) {
		Map<JRElement, Rectangle> map = new HashMap<JRElement, Rectangle>();
		int x = 0;
		int h = c.height;
		for (JRElement el : elements) {
			JRDesignElement del = (JRDesignElement) el;
			map.put(el, new Rectangle(el.getX(), el.getY(), el.getWidth(), el.getHeight()));
			del.setX(x);
			del.setY(0);
			x += del.getWidth();
			del.setWidth(del.getWidth());
			del.setHeight(h);
			LayoutManager.layout(jd, map, el);
			
			int absoluteX = el.getX();
			ANode elNode = SelectionHelper.getNode(el);
			if (elNode != null){
				ANode elParent = elNode.getParent();
				while(elParent != null){
					if (elParent.getValue() instanceof JRDesignElement){
						JRDesignElement elJRParent = (JRDesignElement)elParent.getValue();
						absoluteX += elJRParent.getX();
					}
					elParent = elParent.getParent();
				}
			}
			
			String elementUUID = del.getPropertiesMap().getProperty(SpreadsheetLayout.PROPERTY_ID);
			List<JRDesignElement> connectedElements = getElementForUUID(elementUUID, jd, el);
			if (connectedElements != null){
				for(JRDesignElement connectedElement : connectedElements){
					if (connectedElement != el){
						
						JRElementGroup connectedContainer = (JRElementGroup)SelectionHelper.getNode(connectedElement).getParent().getValue();
						String oldConnectedContainerLayout = null;
						if (connectedContainer != null){
							JRPropertiesMap connectedContainerMap = ((JRPropertiesHolder)connectedContainer).getPropertiesMap();
							oldConnectedContainerLayout = connectedContainerMap.getProperty(ILayout.KEY);
							if (!SpreadsheetLayout.class.getName().equals(oldConnectedContainerLayout)){
								continue;
							}
							connectedContainerMap.setProperty(ILayout.KEY, FreeLayout.class.getName());
						}
						
						
						map.put(connectedElement, new Rectangle(connectedElement.getX(), connectedElement.getY(), connectedElement.getWidth(), connectedElement.getHeight()));
						connectedElement.setX(absoluteX);
						connectedElement.setWidth(del.getWidth());
						connectedElement.setY(0);
						if (connectedContainer instanceof JRBand){
							connectedElement.setHeight(((JRBand)connectedContainer).getHeight());		
						} else if (connectedContainer instanceof JRElement){
							connectedElement.setHeight(((JRElement)connectedContainer).getHeight());
						}
					
						//connectedElement.setHeight(h);
						LayoutManager.layout(jd, map, connectedElement);
						
						if (connectedContainer != null){
							JRPropertiesMap connectedContainerMap = ((JRPropertiesHolder)connectedContainer).getPropertiesMap();	
							connectedContainerMap.setProperty(ILayout.KEY, oldConnectedContainerLayout);
						}
					}
				}
			}
		}
		
		return map;
	}

	@Override
	public String getName() {
		return "Spreadsheet Layout";
	}

	@Override
	public String getToolTip() {
		return "SpreadSheet Layout";
	}

	@Override
	public String getIcon() {
		return "icons/layout-3.png"; //$NON-NLS-1$
	}
	
	@Override
	public boolean showAdditionalControlsOnNode(JRPropertiesMap elementProperties, JRPropertiesMap parentProperties) {
		return false;
	}
	
	@Override
	public ILayoutUIProvider getControlsProvider() {
		return null;
	}
	
	@Override
	public boolean allowChildBoundChange(ANode resizedNode, Rectangle oldBounds, Rectangle newBounds) {
		if (oldBounds == null || newBounds  == null) return false;
 		if (ModelUtils.safeEquals(oldBounds, newBounds)) return true;
		JRPropertiesMap nodeMap = LayoutManager.getPropertyHolder(resizedNode).getPropertiesMap();
		if (!nodeMap.containsProperty(PROPERTY_ID)) return true;
		return oldBounds.x != newBounds.x && oldBounds.y != newBounds.y;
	}

	@Override
	public Map<Object, Rectangle> getLayoutPosition(Object[] elements, int insertPosition, Dimension parentSize) {
		Map<Object, Rectangle> map = new HashMap<Object, Rectangle>();
		int x = 0;
		int h = parentSize.height;
		List<JRElement> jrElements = new ArrayList<JRElement>();
		List<JRField> jrFields = new ArrayList<JRField>();
		for (Object el : elements) {
			if (el instanceof JRElement){
				jrElements.add((JRElement)el);
			} else if (el instanceof JRField){
				jrFields.add((JRField)el);
			}
		
		}	
		if (jrFields.isEmpty()){
			for(JRElement jrEl : jrElements){
				map.put(jrEl, new Rectangle(x, 0, jrEl.getWidth(), h));
				x += jrEl.getWidth();		
			}
		} else {
			if (insertPosition >= 0){
				for(int i = 0; i < insertPosition; i++){
					Object el = elements[i];
					if (el instanceof JRElement){
						x += ((JRElement)el).getWidth();
					}
				}
				map.put(jrFields.get(0), new Rectangle(x - 2, 0, 4, h));
			}
			
		}
 		return map;
	}
	
	private List<JRDesignElement> getElementForUUID(String uuid, JasperDesign jd, JRElement element){
		SpreadSheetElementsCache jdCache = cache.get(jd);
		if (jdCache == null){
			MReport report = ModelUtils.getReport(SelectionHelper.getNode(element));
			jdCache = new SpreadSheetElementsCache(jd, report);

			cache.put(jd, jdCache);
		}
		return jdCache.getElementsFor(uuid);
	}
	
	public static List<JRDesignElement> getElementsForUUID(String uuid, JasperDesign jd, MReport report){
		SpreadSheetElementsCache jdCache = cache.get(jd);
		if (jdCache == null){
			jdCache = new SpreadSheetElementsCache(jd, report);

			cache.put(jd, jdCache);
		}
		return jdCache.getElementsFor(uuid);
	}
	
	/**
	 * When the layout is deactivated it is removed the association also on the connected container
	 * 
	 * @param selectedNode the node where the layout is deactivated
	 * @return the command to remove the association from the connected container or null if there
	 * is no one
	 */
	@Override
	public Command deactivate(ANode selectedNode) {
		return null;
	}
	
	public static void refreshCache(JasperDesign jd){
		cache.remove(jd);
	}
	
	@Override
	public boolean isSelectable(ANode selectedNode) {
		if (selectedNode != null){
			ANode parent = selectedNode;
			while(parent != null){
				if (parent instanceof MBand){
					return true;
				}
				if (parent instanceof MFrame){
					parent = parent.getParent();
				} else {
					return false;
				}
				
			}
		}
		return true;
	}
	
	@Override
	public int getInsertPosition(ANode container, Point dropPosition) {
		int index = 0;
		int offsetLeft = 0;
		if (container instanceof MBand){
			offsetLeft += container.getJasperDesign().getLeftMargin();
		}
		for(INode child : container.getChildren()){
			JRDesignElement jrChild = (JRDesignElement)child.getValue();
			if (jrChild.getY() < dropPosition.y && dropPosition.y < jrChild.getY() + jrChild.getHeight()){
				int x = jrChild.getX() + offsetLeft;
				int span1 = x - 5;
				int span2 = x + 5;
				if (dropPosition.x > span1 && dropPosition.x < span2){
					return index;
				}
				span1 += jrChild.getWidth();
				span2 += jrChild.getWidth();
				if (dropPosition.x > span1 && dropPosition.x < span2){
					if (index < container.getChildren().size()){
						return index + 1;
					} else {
						return -1;
					}
				}
			} 
			index ++;
		}
		return -1;
	}
}
