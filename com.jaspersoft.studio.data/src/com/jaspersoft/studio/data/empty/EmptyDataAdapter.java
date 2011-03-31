package com.jaspersoft.studio.data.empty;

import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.jaspersoft.studio.data.Activator;
import com.jaspersoft.studio.data.DataAdapter;
import com.jaspersoft.studio.data.DataAdapterEditor;

public class EmptyDataAdapter extends DataAdapter {

	private Integer records = 1;

	public EmptyDataAdapter() {

	}

	public EmptyDataAdapter(int records) {
		setRecords(records);
	}

	public Integer getRecords() {
		return records;
	}

	public void setRecords(int records) {
		this.records = records;
	}

	@Override
	public Map<String, String> getProperties() {
		Map<String, String> map = super.getProperties();
		map.put("records", getRecords().toString());
		return map;
	}

	@Override
	public void loadProperties(Map<String, String> map) {
		String s = map.get("records");
		setRecords(Integer.parseInt(s));
	}

	@Override
	public ImageDescriptor getIcon16() {
		return AbstractUIPlugin.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "icons/EmptyDataAdapterIcon-16.gif");
	}

	@Override
	public JRDataSource getJRDataSource() {
		return new net.sf.jasperreports.engine.JREmptyDataSource(getRecords());
	}

	@Override
	public String getName() {
		return super.getName();
	}

	@Override
	public void setName(String name) {
		super.setName(name);
	}

	@Override
	public DataAdapterEditor getEditor() {
		return new EmptyDataAdapterEditor();
	}
}
