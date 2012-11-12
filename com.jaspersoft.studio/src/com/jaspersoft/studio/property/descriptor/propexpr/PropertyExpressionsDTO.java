package com.jaspersoft.studio.property.descriptor.propexpr;

import net.sf.jasperreports.engine.JRPropertiesMap;
import net.sf.jasperreports.engine.JRPropertyExpression;

import com.jaspersoft.studio.model.ANode;

public class PropertyExpressionsDTO {
	private JRPropertyExpression[] propExpressions;
	private JRPropertiesMap propMap;
	private ANode pnode;

	public PropertyExpressionsDTO(JRPropertyExpression[] propExpressions, JRPropertiesMap propMap, ANode pnode) {
		super();
		this.propExpressions = propExpressions;
		this.propMap = propMap;
		this.pnode = pnode;
	}

	public JRPropertyExpression[] getPropExpressions() {
		return propExpressions;
	}

	public void setPropExpressions(JRPropertyExpression[] propExpressions) {
		this.propExpressions = propExpressions;
	}

	public JRPropertiesMap getPropMap() {
		return propMap;
	}

	public void setPropMap(JRPropertiesMap propMap) {
		this.propMap = propMap;
	}

	public ANode getPnode() {
		return pnode;
	}

	public void setPnode(ANode pnode) {
		this.pnode = pnode;
	}

}
