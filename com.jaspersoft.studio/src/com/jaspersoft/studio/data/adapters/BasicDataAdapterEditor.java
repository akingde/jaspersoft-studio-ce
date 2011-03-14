package com.jaspersoft.studio.data.adapters;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.data.DataAdapter;
import com.jaspersoft.studio.data.DataAdapterEditor;
import com.jaspersoft.studio.data.adapters.ui.BasicDataAdapterComposite;

public class BasicDataAdapterEditor implements DataAdapterEditor {

	/**
	 * The data adapter to configure.
	 */
	private DataAdapter adapter = null;
	
	/**
	 * This is the UI panel used to configure this DataAdapter
	 */
	private Composite composite = null;
	
	public DataAdapter getDataAdapter() {
		
		// Update the properties from the editor...
		
		return adapter;
	}

	public Composite getComposite(Composite parent, int style) {
		
		if (composite == null || parent != composite.getParent())
		{
			Map<String,String> map = adapter.getProperties();
			
			List<DataAdapterProperty> properties = new ArrayList<DataAdapterProperty>();
			
	    java.util.Iterator<String> iterator = map.keySet().iterator();
	    
	    while (iterator.hasNext())
	    {
	        String key = iterator.next();
	        String value = map.get(key);
	        
	        properties.add(new DataAdapterProperty(key, value));
	    }
	    
	    composite = new BasicDataAdapterComposite(parent, style, properties);
		}
		return composite;
	}

	public void setDataAdapter(DataAdapter dataAdapter) {
		this.adapter = dataAdapter;
	}

}
