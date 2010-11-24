package com.jaspersoft.studio.chart.model.dataset;

import java.beans.PropertyChangeEvent;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.charts.design.JRDesignTimeSeriesDataset;
import net.sf.jasperreports.engine.JRElementGroup;
import net.sf.jasperreports.engine.design.JRDesignElementGroup;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.design.events.CollectionElementAddedEvent;

import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.ReportFactory;
import com.jaspersoft.studio.property.descriptor.classname.ClassTypePropertyDescriptor;

public class MChartTimeSeriesDataset extends MChartDataset {

	public MChartTimeSeriesDataset(ANode parent, JRDesignTimeSeriesDataset value, JasperDesign jasperDesign) {
		super(parent, value, jasperDesign);
	}

	private static IPropertyDescriptor[] descriptors;
	private static Map<String, Object> defaultsMap;

	@Override
	public Map<String, Object> getDefaultsMap() {
		return defaultsMap;
	}

	@Override
	public IPropertyDescriptor[] getDescriptors() {
		return descriptors;
	}

	@Override
	public void setDescriptors(IPropertyDescriptor[] descriptors1, Map<String, Object> defaultsMap1) {
		descriptors = descriptors1;
		defaultsMap = defaultsMap1;
	}

	/**
	 * Creates the property descriptors.
	 * 
	 * @param desc
	 *          the desc
	 */
	public void createPropertyDescriptors(List<IPropertyDescriptor> desc, Map<String, Object> defaultsMap) {
		super.createPropertyDescriptors(desc, defaultsMap);

		ClassTypePropertyDescriptor timePeriodD = new ClassTypePropertyDescriptor(
				JRDesignTimeSeriesDataset.PROPERTY_TIME_PERIOD, Messages.MChartTimeSeriesDataset_time_period);
		timePeriodD.setDescription(Messages.MChartTimeSeriesDataset_time_period_description);
		desc.add(timePeriodD);

		timePeriodD.setCategory(Messages.MChartTimeSeriesDataset_chart_time_period_dataset_category);

		defaultsMap.put(JRDesignTimeSeriesDataset.PROPERTY_TIME_PERIOD, null);
	}

	@Override
	public Object getPropertyValue(Object id) {
		JRDesignTimeSeriesDataset jrElement = (JRDesignTimeSeriesDataset) getValue();

		if (id.equals(JRDesignTimeSeriesDataset.PROPERTY_TIME_PERIOD))
			return jrElement.getTimePeriod().toString();

		return super.getPropertyValue(id);
	}

	@Override
	public void setPropertyValue(Object id, Object value) {
		JRDesignTimeSeriesDataset jrElement = (JRDesignTimeSeriesDataset) getValue();

		if (id.equals(JRDesignTimeSeriesDataset.PROPERTY_TIME_PERIOD)) {
			Class<?> v = null;
			if (value != null) {
				try {
					v = Class.forName((String) value);
					jrElement.setTimePeriod(v);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		} else
			super.setPropertyValue(id, value);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals("timeSeries")) { //$NON-NLS-1$
			if (evt.getSource() == getValue()) {
				if (evt.getOldValue() == null && evt.getNewValue() != null) {
					int newIndex = -1;
					if (evt instanceof CollectionElementAddedEvent) {
						newIndex = ((CollectionElementAddedEvent) evt).getAddedIndex();
					}
					ReportFactory.createNode(this, evt.getNewValue(), newIndex);
				} else if (evt.getOldValue() != null && evt.getNewValue() == null) {
					// delete
					for (INode n : getChildren()) {
						if (n.getValue() == evt.getOldValue()) {
							removeChild((ANode) n);
							break;
						}
					}
				} else {
					// changed
					for (INode n : getChildren()) {
						if (n.getValue() == evt.getOldValue())
							n.setValue(evt.getNewValue());
					}
				}
			}
		}
		super.propertyChange(evt);
	}
}
