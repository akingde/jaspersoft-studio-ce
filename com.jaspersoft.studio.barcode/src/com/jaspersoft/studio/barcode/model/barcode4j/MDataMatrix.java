package com.jaspersoft.studio.barcode.model.barcode4j;

import java.util.List;
import java.util.Map;

import net.sf.jasperreports.components.barcode4j.DataMatrixComponent;
import net.sf.jasperreports.engine.component.ComponentKey;
import net.sf.jasperreports.engine.design.JRDesignComponentElement;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.ui.views.properties.ComboBoxPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.model.ANode;

public class MDataMatrix extends MBarcode4j {
	public MDataMatrix() {
		super();
	}

	public MDataMatrix(ANode parent, JRDesignComponentElement jrBarcode, int newIndex) {
		super(parent, jrBarcode, newIndex);
	}

	@Override
	public JRDesignComponentElement createJRElement(JasperDesign jasperDesign) {
		JRDesignComponentElement el = new JRDesignComponentElement();
		el.setComponent(new DataMatrixComponent());
		el.setComponentKey(new ComponentKey("http://jasperreports.sourceforge.net/jasperreports/components", "jr",
				"DataMatrix"));
		return el;
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

		ComboBoxPropertyDescriptor shapeD = new ComboBoxPropertyDescriptor(DataMatrixComponent.PROPERTY_SHAPE, "Shape",
				Orientation.getItems());
		shapeD.setDescription("Shape.");
		desc.add(shapeD);

		shapeD.setCategory("Barcode Properties, DataMatrix");
	}

	@Override
	public Object getPropertyValue(Object id) {
		JRDesignComponentElement jrElement = (JRDesignComponentElement) getValue();
		DataMatrixComponent jrList = (DataMatrixComponent) jrElement.getComponent();

		if (id.equals(DataMatrixComponent.PROPERTY_SHAPE))
			return DataMatrixShape.getPos4Shape(jrList.getShape());

		return super.getPropertyValue(id);
	}

	@Override
	public void setPropertyValue(Object id, Object value) {
		JRDesignComponentElement jrElement = (JRDesignComponentElement) getValue();
		DataMatrixComponent jrList = (DataMatrixComponent) jrElement.getComponent();

		if (id.equals(DataMatrixComponent.PROPERTY_SHAPE))
			jrList.setShape(DataMatrixShape.getShape4Pos((Integer) value));

		super.setPropertyValue(id, value);
	}
}
