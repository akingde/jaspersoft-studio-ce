package com.jaspersoft.studio.property.section;

import com.jaspersoft.studio.model.APropertyNode;

	abstract public class AbstractRealValueSection extends AbstractSection {
		public void refresh() {
			isRefreshing = true;
			APropertyNode element = getElement();
			if (element != null) {
				element.getPropertyDescriptors();
				for (Object key : widgets.keySet()) {
					widgets.get(key).setData(element, element.getPropertyActualValue(key));
				}
			}
			isRefreshing = false;
		}
}
