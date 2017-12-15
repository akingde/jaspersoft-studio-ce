package com.jaspersoft.studio.editor.layout.spreadsheet;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.jaspersoft.studio.model.MReport;
import com.jaspersoft.studio.utils.ModelUtils;

import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JasperDesign;

public class SpreadSheetElementsCache {

	private HashMap<String, List<JRDesignElement>> cacheMap = null;

	private JasperDesign jd;
	
	public SpreadSheetElementsCache(JasperDesign jd, MReport report){
		this.jd = jd;
		report.getPropertyChangeSupport().addPropertyChangeListener(new PropertyChangeListener() {
			
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				if (evt.getPropertyName().equals("children")){
					cacheMap = null;
				}
			}
		});
	}
	
	public List<JRDesignElement> getElementsFor(String uuid){
		if (cacheMap == null){
			cacheMap = new HashMap<String, List<JRDesignElement>>();
			List<JRDesignElement> elements = ModelUtils.getAllElements(jd);
			for(JRDesignElement element : elements){
				String elementUUID = element.getPropertiesMap().getProperty(SpreadsheetLayout.PROPERTY_ID);
				if (elementUUID != null){	
					List<JRDesignElement> elementsList = cacheMap.get(elementUUID);
					if (elementsList == null){
						elementsList = new ArrayList<JRDesignElement>();
						cacheMap.put(elementUUID, elementsList);
					}
					elementsList.add(element);
				}
			}
		}
		return cacheMap.get(uuid);
	}

}
