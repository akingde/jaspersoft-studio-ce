/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.model.command;

import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.JRGroup;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JRVariable;
import net.sf.jasperreports.engine.design.JRDesignDataset;
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

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.field.MField;
import com.jaspersoft.studio.model.parameter.MParameterSystem;
import com.jaspersoft.studio.model.text.MStaticText;
import com.jaspersoft.studio.model.text.MTextField;
import com.jaspersoft.studio.model.variable.MVariableSystem;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.utils.EnumHelper;
import com.jaspersoft.studio.utils.ModelUtils;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;
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
				Tag tag = new Tag("$F{%}", f.getValueClassName(), f.getName(), resolveClass(n, f.getValueClassName()));//$NON-NLS-1$
				tag.isField = true;
				return tag;
			} else if (n instanceof MParameterSystem) {
				JRParameter f = (JRParameter) n.getValue();
				Tag result = new Tag("$P{%}", f.getValueClassName(), f.getName(), resolveClass(n, f.getValueClassName()));//$NON-NLS-1$;
				return result;
			} else if (n instanceof MVariableSystem) {
				JRVariable f = (JRVariable) n.getValue();
				return new Tag("$V{%}", f.getValueClassName(), f.getName(), resolveClass(n, f.getValueClassName()));//$NON-NLS-1$ 
			}
		return new Tag("", "", "", null);
	}
	
	/**
	 * Resolve the class fullname of a class using the Studio classloader
	 * 
	 * @param node the node from where the classloader is retrive  
	 * @param classFullName the fullname of the class to resolve
	 * @return the class, or null if it can't be resolved
	 */
	protected static Class<?> resolveClass(ANode node, String classFullName){
		JasperReportsConfiguration jConfig = node.getJasperConfiguration();
		if (jConfig != null){
			try {
				Class<?> clazz = jConfig.getClassLoader().loadClass(classFullName);
				return clazz;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				JaspersoftStudioPlugin.getInstance().logError(e);
			}
		}
		return null;
	}
	
	/**
	 * Create a static text with a specific content 
	 * 
	 * @param text the content of the text
	 * @param the context of the static text
	 * @return a not null MStaticText with a new {@link JRDesignStaticText} inside
	 */
	public static MStaticText createStaticText(String text, JasperDesign jd) {
		MStaticText src = new MStaticText();
		JRDesignStaticText tf = new JRDesignStaticText(jd);
		tf.setText(text);
		src.setValue(tf);
		return src;
	}
	
	/**
	 * Create a static textfield with a specific content 
	 * 
	 * @param txtExp the textual expression
	 * @param classExp FIXME not used now, maybe to be removed
	 * @param the context of the static text
	 * @return a not null MTextField with a new {@link JRDesignTextField} inside
	 */
	public static MTextField createTextField(String txtExp, String classExp, JasperDesign jd) {
		MTextField src = new MTextField();
		JRDesignTextField tf = new JRDesignTextField(jd);
		src.setValue(tf);

		JRDesignExpression jre = new JRDesignExpression();
		jre.setText(txtExp);
		tf.setExpression(jre);
		return src;
	}

	public static JRDesignVariable createVariable(Tag tag, ResetTypeEnum rtype, JRGroup group, JRDesignDataset jDesign)
			throws CancelledOperationException {
		JRDesignVariable jrVariable = null;
		if (tag.isField) {
			String[] names = null;
			if (Number.class.isAssignableFrom(tag.clazz)) {
				names = EnumHelper.getEnumNames(CalculationEnum.values(), NullEnum.NOTNULL);
			}
			else {
				names = EnumHelper.getEnumNames(new CalculationEnum[]{
						CalculationEnum.NOTHING,CalculationEnum.COUNT,CalculationEnum.DISTINCT_COUNT}, NullEnum.NOTNULL);
			}
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
				throw new CancelledOperationException();
		}
		return jrVariable;
	}
}
