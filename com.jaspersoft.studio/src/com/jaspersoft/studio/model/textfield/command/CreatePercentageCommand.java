/*
 * JasperReports - Free Java Reporting Library. Copyright (C) 2001 - 2009 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of JasperReports.
 * 
 * JasperReports is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser General
 * Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * JasperReports is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License along with JasperReports. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.model.textfield.command;

import java.math.BigDecimal;

import net.sf.jasperreports.engine.JRCloneable;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.JRGroup;
import net.sf.jasperreports.engine.JRVariable;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JRDesignTextField;
import net.sf.jasperreports.engine.design.JRDesignVariable;
import net.sf.jasperreports.engine.type.CalculationEnum;
import net.sf.jasperreports.engine.type.ResetTypeEnum;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.MElementGroup;
import com.jaspersoft.studio.model.MFrame;
import com.jaspersoft.studio.model.band.MBand;
import com.jaspersoft.studio.model.command.CreateElementCommand;
import com.jaspersoft.studio.model.textfield.MPercentage;
import com.jaspersoft.studio.model.textfield.command.wizard.PercentageWizard;
import com.jaspersoft.studio.utils.ModelUtils;
import com.jaspersoft.studio.utils.UIUtils;

/*
 * link nodes & together.
 * 
 * @author Chicu Veaceslav
 */
public class CreatePercentageCommand extends CreateElementCommand {

	/**
	 * Instantiates a new creates the page xof y command.
	 * 
	 * @param destNode
	 *          the dest node
	 * @param srcNode
	 *          the src node
	 * @param index
	 *          the index
	 */
	public CreatePercentageCommand(MElementGroup destNode, MPercentage srcNode, int index) {
		super(destNode, srcNode, index);
	}

	/**
	 * Instantiates a new creates the page xof y command.
	 * 
	 * @param destNode
	 *          the dest node
	 * @param srcNode
	 *          the src node
	 * @param index
	 *          the index
	 */
	public CreatePercentageCommand(MFrame destNode, MPercentage srcNode, int index) {
		super(destNode, srcNode, index);
	}

	/**
	 * Instantiates a new creates the page xof y command.
	 * 
	 * @param destNode
	 *          the dest node
	 * @param srcNode
	 *          the src node
	 * @param index
	 *          the index
	 */
	public CreatePercentageCommand(MBand destNode, MPercentage srcNode, int index) {
		super(destNode, srcNode, index);
	}

	/**
	 * Instantiates a new creates the page xof y command.
	 * 
	 * @param destNode
	 *          the dest node
	 * @param srcNode
	 *          the src node
	 * @param position
	 *          the position
	 * @param index
	 *          the index
	 */
	public CreatePercentageCommand(ANode destNode, MPercentage srcNode, Rectangle position, int index) {
		super(destNode, srcNode, position, index);
	}

	/**
	 * Creates the object.
	 */
	@Override
	protected void createObject() {
		if (jrElement == null) {
			JRCloneable field = null;
			ResetTypeEnum rtype = ResetTypeEnum.REPORT;
			JRGroup group = null;

			PercentageWizard wizard = new PercentageWizard();
			WizardDialog dialog = new WizardDialog(Display.getDefault().getActiveShell(), wizard);
			wizard.init(jasperDesign);
			dialog.create();
			if (dialog.open() == Dialog.OK) {
				field = wizard.getField();
				rtype = wizard.getResetType();
				group = wizard.getGroup();

				super.createObject();
				if (field != null) {
					JRDesignTextField tf = (JRDesignTextField) jrElement;
					
					
					// Create the expressions based of the reset type selected by the user...
					JRDesignVariable variable = null;
					try {
						if (field instanceof JRField)
						{
							variable = createVariable(((JRField) field).getName(),
									((JRField) field).getValueClassName(), rtype, group);
							
						}
						else if (field instanceof JRVariable)
						{
							variable = createVariable(((JRVariable) field).getName(),
									((JRVariable) field).getValueClassName(), rtype, group);
						}
						
						if (variable == null)
						{
							return; // we don't want to continue in this case...
						}
						
						jasperDesign.addVariable(variable);
						
					} catch (Exception e) {
						UIUtils.showError(e);
						
					}
					
					JRDesignExpression expression = new JRDesignExpression();
					if (field instanceof JRField)
						expression.setText(createExpression(((JRField) field).getName(), variable.getName(), ((JRField) field).getValueClass()));
					if (field instanceof JRVariable)
						expression.setText(createExpression(((JRVariable) field).getName(), variable.getName(), ((JRVariable) field).getValueClass()));
					tf.setExpression(expression);
					tf.setPattern("#,##0.00%");
					
					// Set the evaluation time of this textfield to AUTO
					tf.setEvaluationTime( net.sf.jasperreports.engine.type.EvaluationTimeEnum.AUTO);

					
				}
			}
		}
	}

	private String createExpression(String name, String vname, Class<?> clazz) {
		if (clazz.isAssignableFrom(Integer.class))
			return "new Integer($F{" + name + "}.intValue() / $V{" + vname + "}.intValue())";
		if (clazz.isAssignableFrom(Byte.class))
			return "new Byte($F{" + name + "}.byteValue() / $V{" + vname + "}.byteValue())";
		if (clazz.isAssignableFrom(Short.class))
			return "new Short($F{" + name + "}.shortValue() / $V{" + vname + "}.shortValue())";
		if (clazz.isAssignableFrom(Float.class))
			return "new Float($F{" + name + "}.floatValue() / $V{" + vname + "}.floatValue())";
		if (clazz.isAssignableFrom(Double.class))
			return "new Double($F{" + name + "}.doubleValue() / $V{" + vname + "}.doubleValue())";
		if (clazz.isAssignableFrom(BigDecimal.class))
			return "$F{" + name + "}.devide(new BigDecimal( $V{" + vname + "} ))";

		return "";
	}

	public static boolean isNumber(Class<?> clazz) {
		if (clazz.isAssignableFrom(Integer.class))
			return true;
		if (clazz.isAssignableFrom(Byte.class))
			return true;
		if (clazz.isAssignableFrom(Short.class))
			return true;
		if (clazz.isAssignableFrom(Float.class))
			return true;
		if (clazz.isAssignableFrom(Double.class))
			return true;
		if (clazz.isAssignableFrom(BigDecimal.class))
			return true;

		return false;
	}

	private JRDesignVariable jrVariable;

	private JRDesignVariable createVariable(String name, String clazz, ResetTypeEnum rtype, JRGroup group)
			throws Exception {
		jrVariable = new JRDesignVariable();
		jrVariable.setCalculation(CalculationEnum.SUM);
		
		String vname = name + "_SUM";
		int i=0;
		while (jasperDesign.getVariablesMap().containsKey(vname))
		{
			i++;
			vname = name + "_" + i + "_SUM";
		}
		
		jrVariable.setName(vname);
		jrVariable.setResetType(rtype);
		if (rtype.equals(ResetTypeEnum.GROUP))
			jrVariable.setResetGroup(group);

		jrVariable.setValueClassName(clazz);

		JRDesignExpression jre = new JRDesignExpression();
		jre.setText("$F{" + name + "}");
		jrVariable.setExpression(jre);

		return jrVariable;
	}

}
