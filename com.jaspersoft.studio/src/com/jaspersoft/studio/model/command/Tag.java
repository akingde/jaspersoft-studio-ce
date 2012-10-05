/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2010 Jaspersoft Corporation. All
 * rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of Jaspersoft Open Studio.
 * 
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify it under the terms of the GNU Affero
 * General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * Jaspersoft Open Studio is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with Jaspersoft Open Studio. If not,
 * see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.model.command;

import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.JRGroup;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JRVariable;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JRDesignStaticText;
import net.sf.jasperreports.engine.design.JRDesignTextField;
import net.sf.jasperreports.engine.design.JRDesignVariable;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.type.CalculationEnum;
import net.sf.jasperreports.engine.type.ResetTypeEnum;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.field.MField;
import com.jaspersoft.studio.model.parameter.MParameterSystem;
import com.jaspersoft.studio.model.text.MStaticText;
import com.jaspersoft.studio.model.text.MTextField;
import com.jaspersoft.studio.model.variable.MVariableSystem;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.utils.EnumHelper;
import com.jaspersoft.studio.utils.ModelUtils;
import com.jaspersoft.studio.wizards.obj2text.Obj2TextWizard;

public class Tag {
	public boolean isField = false;
	public String name;
	public String txt;
	public String classname;
	public Class<?> clazz;

	public Tag(String txt, String classname, String name, Class<?> clazz) {
		this.txt = txt;
		this.classname = classname;
		this.clazz = clazz;
		this.name = name;
	}

	public static Tag getExpression(ANode n) {
		if (n.getValue() != null)
			if (n instanceof MField) {
				JRField f = (JRField) n.getValue();
				Tag tag = new Tag("$F{%}", f.getValueClassName(), f.getName(), f.getValueClass());//$NON-NLS-1$ //$NON-NLS-2$
				tag.isField = true;
				return tag;
			} else if (n instanceof MParameterSystem) {
				JRParameter f = (JRParameter) n.getValue();
				return new Tag("$P{%}", f.getValueClassName(), f.getName(), f.getValueClass());//$NON-NLS-1$ //$NON-NLS-2$
			} else if (n instanceof MVariableSystem) {
				JRVariable f = (JRVariable) n.getValue();
				return new Tag("$V{%}", f.getValueClassName(), f.getName(), f.getValueClass());//$NON-NLS-1$ //$NON-NLS-2$
			}
		return new Tag("", "", "", null);
	}

	public static MStaticText createStaticText(String txtExp) {
		MStaticText src = new MStaticText();
		JRDesignStaticText tf = new JRDesignStaticText();
		tf.setText(txtExp);
		src.setValue(tf);
		return src;
	}

	public static MTextField createTextField(String txtExp, String classExp) {
		MTextField src = new MTextField();
		JRDesignTextField tf = new JRDesignTextField();
		src.setValue(tf);

		JRDesignExpression jre = new JRDesignExpression();
		jre.setText(txtExp);
		tf.setExpression(jre);
		return src;
	}

	public static JRDesignVariable createVariable(Tag tag, ResetTypeEnum rtype, JRGroup group, JasperDesign jDesign)
			throws Exception {
		JRDesignVariable jrVariable = null;
		if (tag.isField) {
			String[] names = null;
			if (Number.class.isAssignableFrom(tag.clazz))
				names = EnumHelper.getEnumNames(CalculationEnum.values(), NullEnum.NOTNULL);
			else
				names = new String[] { CalculationEnum.COUNT.getName(), CalculationEnum.DISTINCT_COUNT.getName(),
						CalculationEnum.NOTHING.getName() };
			Obj2TextWizard wizard = new Obj2TextWizard(names);
			WizardDialog dialog = new WizardDialog(Display.getCurrent().getActiveShell(), wizard);
			dialog.create();
			if (dialog.open() == Dialog.OK) {
				CalculationEnum ce = wizard.getCalculation();
				if (ce != null && !CalculationEnum.NOTHING.equals(ce)) {
					jrVariable = new JRDesignVariable();
					jrVariable.setCalculation(ce);
					jrVariable.setName(ModelUtils.getDefaultName(jDesign.getVariablesMap(), tag.name));
					jrVariable.setResetType(rtype);
					if (rtype.equals(ResetTypeEnum.GROUP))
						jrVariable.setResetGroup(group);

					if (CalculationEnum.COUNT.equals(ce) || CalculationEnum.DISTINCT_COUNT.equals(ce))
						jrVariable.setValueClass(Integer.class);
					else
						// if (CalculationEnum.AVERAGE.equals(ce) ||
						// CalculationEnum.STANDARD_DEVIATION.equals(ce)
						// || CalculationEnum.SUM.equals(ce) ||
						// CalculationEnum.VARIANCE.equals(ce))
						// jrVariable.setValueClass(Double.class);
						jrVariable.setValueClassName(tag.classname);

					JRDesignExpression jre = new JRDesignExpression();
					jre.setText(tag.txt.replaceAll("%", tag.name));
					jrVariable.setExpression(jre);

					tag.name = jrVariable.getName();
					tag.txt = "$V{%}";
				}
			} else
				throw new Exception("canceled");
		}
		return jrVariable;
	}
}
