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

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.JRGroup;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JRVariable;
import net.sf.jasperreports.engine.design.JRDesignBand;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JRDesignStaticText;
import net.sf.jasperreports.engine.design.JRDesignTextField;
import net.sf.jasperreports.engine.design.JRDesignVariable;
import net.sf.jasperreports.engine.type.BandTypeEnum;
import net.sf.jasperreports.engine.type.CalculationEnum;
import net.sf.jasperreports.engine.type.ResetTypeEnum;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.band.MBand;
import com.jaspersoft.studio.model.band.MBandGroupFooter;
import com.jaspersoft.studio.model.field.MField;
import com.jaspersoft.studio.model.parameter.MParameter;
import com.jaspersoft.studio.model.text.MStaticText;
import com.jaspersoft.studio.model.text.MTextField;
import com.jaspersoft.studio.model.variable.MVariable;
import com.jaspersoft.studio.utils.ModelUtils;
import com.jaspersoft.studio.wizards.obj2text.Obj2TextWizard;

public class CreateE4ObjectCommand extends CreateElementCommand {
	private ANode child;
	private ANode parent;

	public CreateE4ObjectCommand(ANode child, ANode parent, Rectangle location, int index) {
		super();
		this.child = child;
		this.parent = parent;
		this.location = location;
		this.index = index;
		this.jasperDesign = parent.getJasperDesign();
	}

	@Override
	protected void createObject() {
		try {
			Tag tag = getExpression(child);
			ANode n = fixPosition(parent, child, location);
			if (n instanceof MBand) {
				JRDesignBand b = (JRDesignBand) n.getValue();
				BandTypeEnum btype = b.getOrigin().getBandTypeValue();
				if (btype.equals(BandTypeEnum.DETAIL)) {
					createTextField(tag.txt.replaceAll("%", tag.name), tag.classname);
				} else if (btype.equals(BandTypeEnum.COLUMN_FOOTER) || btype.equals(BandTypeEnum.COLUMN_HEADER)) {
					createVariable(tag, ResetTypeEnum.COLUMN, null);
					createTextField(tag.txt.replaceAll("%", tag.name), tag.classname);
				} else if (btype.equals(BandTypeEnum.GROUP_FOOTER) || btype.equals(BandTypeEnum.GROUP_HEADER)) {
					createVariable(tag, ResetTypeEnum.GROUP, ((MBandGroupFooter) n).getJrGroup());
					createTextField(tag.txt.replaceAll("%", tag.name), tag.classname);
				} else if (btype.equals(BandTypeEnum.SUMMARY) || btype.equals(BandTypeEnum.TITLE)) {
					createVariable(tag, ResetTypeEnum.REPORT, null);
					createTextField(tag.txt.replaceAll("%", tag.name), tag.classname);
				} else if (btype.equals(BandTypeEnum.PAGE_FOOTER) || btype.equals(BandTypeEnum.PAGE_HEADER)
						|| btype.equals(BandTypeEnum.LAST_PAGE_FOOTER)) {
					createVariable(tag, ResetTypeEnum.PAGE, null);
					createTextField(tag.txt.replaceAll("%", tag.name), tag.classname);

				} else {
					createStaticText(tag.name);
				}
				setContext(n, srcNode, index);
			}

			super.createObject();
		} catch (Exception e) {

		}
	}

	private JRDesignVariable jrVariable;

	private JRDesignVariable createVariable(Tag tag, ResetTypeEnum rtype, JRGroup group) throws Exception {
		if (tag.isField) {
			Obj2TextWizard wizard = new Obj2TextWizard();
			WizardDialog dialog = new WizardDialog(Display.getCurrent().getActiveShell(), wizard);
			dialog.create();
			if (dialog.open() == Dialog.OK) {
				CalculationEnum ce = wizard.getCalculation();
				if (ce != null && !CalculationEnum.NOTHING.equals(ce)) {
					jrVariable = new JRDesignVariable();
					jrVariable.setCalculation(ce);
					jrVariable.setName(ModelUtils.getDefaultName(jasperDesign.getVariablesMap(), tag.name));
					jrVariable.setResetType(rtype);
					if (rtype.equals(ResetTypeEnum.GROUP))
						jrVariable.setResetGroup(group);

					if (CalculationEnum.COUNT.equals(ce) || CalculationEnum.DISTINCT_COUNT.equals(ce))
						jrVariable.setValueClass(Integer.class);
					else
						// if (CalculationEnum.AVERAGE.equals(ce) || CalculationEnum.STANDARD_DEVIATION.equals(ce)
						// || CalculationEnum.SUM.equals(ce) || CalculationEnum.VARIANCE.equals(ce))
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

	private void createStaticText(String txtExp) {
		srcNode = new MStaticText();
		JRDesignStaticText tf = new JRDesignStaticText();
		tf.setText(txtExp);
		srcNode.setValue(tf);
	}

	private void createTextField(String txtExp, String classExp) {
		srcNode = new MTextField();
		JRDesignTextField tf = new JRDesignTextField();
		srcNode.setValue(tf);

		JRDesignExpression jre = new JRDesignExpression();
		jre.setText(txtExp);
		tf.setExpression(jre);
	}

	protected Tag getExpression(ANode n) {
		if (n.getValue() != null)
			if (n instanceof MField) {
				JRField f = (JRField) n.getValue();
				Tag tag = new Tag("$F{%}", f.getValueClassName(), f.getName());//$NON-NLS-1$ //$NON-NLS-2$
				tag.isField = true;
				return tag;
			} else if (n instanceof MParameter) {
				JRParameter f = (JRParameter) n.getValue();
				return new Tag("$P{%}", f.getValueClassName(), f.getName());//$NON-NLS-1$ //$NON-NLS-2$
			} else if (n instanceof MVariable) {
				JRVariable f = (JRVariable) n.getValue();
				return new Tag("$V{%}", f.getValueClassName(), f.getName());//$NON-NLS-1$ //$NON-NLS-2$
			}
		return new Tag("", "", "");
	}

	private class Tag {
		public boolean isField = false;
		public String name;
		public String txt;
		public String classname;

		public Tag(String txt, String classname, String name) {
			this.txt = txt;
			this.classname = classname;
			this.name = name;
		}
	}

	@Override
	public void execute() {
		super.execute();
		try {
			if (jrVariable != null)
				jasperDesign.addVariable((JRDesignVariable) jrVariable);
		} catch (JRException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void undo() {
		super.undo();
		if (jrVariable != null)
			jasperDesign.removeVariable(jrVariable);
	}
}
