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
package com.jaspersoft.studio.model;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import net.sf.jasperreports.crosstabs.JRCrosstab;
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstab;
import net.sf.jasperreports.engine.JRBand;
import net.sf.jasperreports.engine.JRChart;
import net.sf.jasperreports.engine.JRElementGroup;
import net.sf.jasperreports.engine.JRFrame;
import net.sf.jasperreports.engine.JRReportTemplate;
import net.sf.jasperreports.engine.JRSubreport;
import net.sf.jasperreports.engine.JRVariable;
import net.sf.jasperreports.engine.design.JRDesignBand;
import net.sf.jasperreports.engine.design.JRDesignBreak;
import net.sf.jasperreports.engine.design.JRDesignChart;
import net.sf.jasperreports.engine.design.JRDesignConditionalStyle;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignEllipse;
import net.sf.jasperreports.engine.design.JRDesignField;
import net.sf.jasperreports.engine.design.JRDesignFrame;
import net.sf.jasperreports.engine.design.JRDesignGroup;
import net.sf.jasperreports.engine.design.JRDesignImage;
import net.sf.jasperreports.engine.design.JRDesignLine;
import net.sf.jasperreports.engine.design.JRDesignParameter;
import net.sf.jasperreports.engine.design.JRDesignRectangle;
import net.sf.jasperreports.engine.design.JRDesignReportTemplate;
import net.sf.jasperreports.engine.design.JRDesignScriptlet;
import net.sf.jasperreports.engine.design.JRDesignSection;
import net.sf.jasperreports.engine.design.JRDesignSortField;
import net.sf.jasperreports.engine.design.JRDesignStaticText;
import net.sf.jasperreports.engine.design.JRDesignStyle;
import net.sf.jasperreports.engine.design.JRDesignSubreport;
import net.sf.jasperreports.engine.design.JRDesignTextField;
import net.sf.jasperreports.engine.design.JRDesignVariable;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.type.BandTypeEnum;

import com.jaspersoft.studio.ExtensionManager;
import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.model.band.MBand;
import com.jaspersoft.studio.model.band.MBandGroupFooter;
import com.jaspersoft.studio.model.band.MBandGroupHeader;
import com.jaspersoft.studio.model.dataset.MDataset;
import com.jaspersoft.studio.model.field.MField;
import com.jaspersoft.studio.model.field.MFields;
import com.jaspersoft.studio.model.group.MGroup;
import com.jaspersoft.studio.model.group.MGroups;
import com.jaspersoft.studio.model.parameter.MParameter;
import com.jaspersoft.studio.model.parameter.MParameterSystem;
import com.jaspersoft.studio.model.parameter.MParameters;
import com.jaspersoft.studio.model.scriptlet.MScriptlet;
import com.jaspersoft.studio.model.scriptlet.MScriptlets;
import com.jaspersoft.studio.model.sortfield.MSortField;
import com.jaspersoft.studio.model.sortfield.MSortFields;
import com.jaspersoft.studio.model.style.MConditionalStyle;
import com.jaspersoft.studio.model.style.MStyle;
import com.jaspersoft.studio.model.style.MStyleTemplate;
import com.jaspersoft.studio.model.style.MStyles;
import com.jaspersoft.studio.model.textfield.MTextField;
import com.jaspersoft.studio.model.variable.MVariable;
import com.jaspersoft.studio.model.variable.MVariableSystem;
import com.jaspersoft.studio.model.variable.MVariables;

/**
 * A factory for creating Report objects.
 * 
 * @author Chicu Veaceslav
 */
public class ReportFactory {

	/**
	 * Creates a new Report object.
	 * 
	 * @param jd
	 *          the jd
	 * @return the i node
	 */
	public static INode createReport(JasperDesign jd) {

		ANode node = new MRoot(null, jd);
		ANode report = new MReport(node, jd);
		// create first level
		// create Styles
		ANode nStyle = new MStyles(report);
		if (jd.getTemplates() != null) {
			for (Iterator<JRReportTemplate> it = jd.getTemplatesList().iterator(); it.hasNext();) {
				createNode(nStyle, it.next(), -1);
			}
		}
		if (jd.getStyles() != null) {
			for (Iterator<JRDesignStyle> it = jd.getStylesList().iterator(); it.hasNext();) {
				JRDesignStyle jrstyle = it.next();
				ANode mstyle = createNode(nStyle, jrstyle, -1);
				if (jrstyle.getConditionalStyleList() != null) {
					for (Object jrc : jrstyle.getConditionalStyleList()) {
						createNode(mstyle, jrc, -1);
					}
				}
			}
		}
		// create datasets
		createDataset(report, jd.getMainDesignDataset(), false);

		if (jd.getDatasetsList() != null) {
			for (Iterator<JRDesignDataset> it = jd.getDatasetsList().iterator(); it.hasNext();) {
				JRDesignDataset jrDataset = it.next();
				createDataset(new MDataset(report, jrDataset, -1), jrDataset, true);
			}

		}

		MBand title = new MBand(report, jd.getTitle(), BandTypeEnum.TITLE, -1);
		if (jd.getTitle() != null)
			createElementsForBand(title, jd.getTitle().getChildren());

		MBand pageHeader = new MBand(report, jd.getPageHeader(), BandTypeEnum.PAGE_HEADER, -1);
		if (jd.getPageHeader() != null)
			createElementsForBand(pageHeader, jd.getPageHeader().getChildren());

		MBand columnHeader = new MBand(report, jd.getColumnHeader(), BandTypeEnum.COLUMN_HEADER, -1);
		if (jd.getColumnHeader() != null)
			createElementsForBand(columnHeader, jd.getColumnHeader().getChildren());

		if (jd.getGroupsList() != null) {
			for (Object group : jd.getGroupsList()) {
				JRDesignGroup gr = (JRDesignGroup) group;
				if (gr.getGroupHeaderSection() != null) {
					List<?> grhBands = ((JRDesignSection) gr.getGroupHeaderSection()).getBandsList();
					if (grhBands != null) {
						if (grhBands.isEmpty()) {
							new MBandGroupHeader(report, gr, null, -1);
						} else
							for (Iterator<?> it = grhBands.iterator(); it.hasNext();) {
								JRDesignBand jrDB = (JRDesignBand) it.next();
								MBandGroupHeader b = new MBandGroupHeader(report, gr, jrDB, -1);
								createElementsForBand(b, jrDB.getChildren());
							}
					}
				}
			}
		}

		if (jd.getDetailSection() != null) {
			JRBand[] bandsList = jd.getDetailSection().getBands();
			if (bandsList != null)
				for (int k = 0; k < bandsList.length; k++) {
					if (bandsList[k] != null) {
						MBand band = new MBand(report, bandsList[k], BandTypeEnum.DETAIL, -1);
						createElementsForBand(band, bandsList[k].getChildren());
						//
						// for (JRElement element : bandsList[k].getElements())
						// createNode(band, element, -1);
					}
				}
		} else {
			// what we do if null?
		}

		if (jd.getGroupsList() != null) {
			for (ListIterator<?> ij = jd.getGroupsList().listIterator(jd.getGroupsList().size()); ij.hasPrevious();) {
				JRDesignGroup gr = (JRDesignGroup) ij.previous();
				if (gr.getGroupFooterSection() != null) {
					List<?> grhBands = ((JRDesignSection) gr.getGroupFooterSection()).getBandsList();
					if (grhBands != null) {
						if (grhBands.isEmpty()) {
							new MBandGroupFooter(report, gr, null, -1);
						} else
							for (Iterator<?> it = grhBands.iterator(); it.hasNext();) {
								JRDesignBand jrDB = (JRDesignBand) it.next();
								MBandGroupFooter b = new MBandGroupFooter(report, gr, jrDB, -1);
								createElementsForBand(b, jrDB.getChildren());
							}
					}
				}
			}
		}

		MBand columnFooter = new MBand(report, jd.getColumnFooter(), BandTypeEnum.COLUMN_FOOTER, -1);
		if (jd.getColumnFooter() != null)
			createElementsForBand(columnFooter, jd.getColumnFooter().getChildren());

		MBand footer = new MBand(report, jd.getPageFooter(), BandTypeEnum.PAGE_FOOTER, -1);
		if (jd.getPageFooter() != null)
			createElementsForBand(footer, jd.getPageFooter().getChildren());

		MBand lastPageFooter = new MBand(report, jd.getLastPageFooter(), BandTypeEnum.LAST_PAGE_FOOTER, -1);
		if (jd.getLastPageFooter() != null)
			createElementsForBand(lastPageFooter, jd.getLastPageFooter().getChildren());

		MBand summary = new MBand(report, jd.getSummary(), BandTypeEnum.SUMMARY, -1);
		if (jd.getSummary() != null)
			createElementsForBand(summary, jd.getSummary().getChildren());

		MBand nodata = new MBand(report, jd.getNoData(), BandTypeEnum.NO_DATA, -1);
		if (jd.getNoData() != null)
			createElementsForBand(footer, jd.getNoData().getChildren());

		MBand background = new MBand(report, jd.getBackground(), BandTypeEnum.BACKGROUND, -1);
		if (jd.getBackground() != null)
			createElementsForBand(background, jd.getBackground().getChildren());

		return node;
	}

	/**
	 * Creates a new Report object.
	 * 
	 * @param nDataset
	 *          the n dataset
	 * @param dataSet
	 *          the data set
	 * @param showGroups
	 *          the show groups
	 */
	public static void createDataset(ANode nDataset, JRDesignDataset dataSet, boolean showGroups) {
		// create parameters
		ANode nParameters = new MParameters(nDataset, dataSet);
		if (dataSet.getParameters() != null) {
			for (Iterator<JRDesignParameter> it = dataSet.getParametersList().iterator(); it.hasNext();) {
				createNode(nParameters, it.next(), -1);
			}
		}
		// create fields
		ANode nFields = new MFields(nDataset, dataSet);
		if (dataSet.getFields() != null) {
			for (Iterator<JRDesignField> it = dataSet.getFieldsList().iterator(); it.hasNext();) {
				createNode(nFields, it.next(), -1);
			}
		}
		// create sort fields
		ANode nSortFields = new MSortFields(nDataset, dataSet);
		if (dataSet.getSortFields() != null) {
			for (Iterator<JRDesignSortField> it = dataSet.getSortFieldsList().iterator(); it.hasNext();) {
				createNode(nFields, it.next(), -1);
			}
		}
		// create variables
		ANode nVariables = new MVariables(nDataset, dataSet);
		if (dataSet.getVariables() != null) {
			for (Iterator<JRVariable> it = dataSet.getVariablesList().iterator(); it.hasNext();) {
				createNode(nVariables, (JRDesignVariable) it.next(), -1);
			}
		}
		// create scriplets
		ANode nScriptlets = new MScriptlets(nDataset, dataSet);
		if (dataSet.getScriptlets() != null) {
			for (Iterator<JRDesignScriptlet> it = dataSet.getScriptletsList().iterator(); it.hasNext();) {
				createNode(nScriptlets, it.next(), -1);
			}
		}

		if (showGroups) {
			// create scriplets
			ANode nGroups = new MGroups(nDataset, dataSet);
			if (dataSet.getGroups() != null) {
				for (Iterator<JRDesignGroup> it = dataSet.getGroupsList().iterator(); it.hasNext();) {
					createNode(nGroups, it.next(), -1);
				}
			}
		}
	}

	/**
	 * Creates a new Report object.
	 * 
	 * @param band
	 *          the band
	 * @param list
	 *          the list
	 */
	public static void createElementsForBand(ANode band, List<?> list) {
		for (Object element : list) {
			ANode node = createNode(band, element, -1);
			ExtensionManager m = JaspersoftStudioPlugin.getExtensionManager();
			List<?> children = m.getChildren4Element(element);
			if (children != null) {
				createElementsForBand(node, children);
			} else if (element instanceof JRDesignFrame) {
				JRDesignFrame frame = (JRDesignFrame) element;
				createElementsForBand(node, frame.getChildren());
			} else if (element instanceof JRElementGroup) {
				JRElementGroup group = (JRElementGroup) element;
				createElementsForBand(node, group.getChildren());
			}
		}
	}

	/**
	 * Creates a new Report object.
	 * 
	 * @param parent
	 *          the parent
	 * @param jrObject
	 *          the jr object
	 * @param newIndex
	 *          the new index
	 * @return the a node
	 */
	public static ANode createNode(ANode parent, Object jrObject, int newIndex) {
		ExtensionManager m = JaspersoftStudioPlugin.getExtensionManager();
		ANode n = m.createNode(parent, jrObject, newIndex);
		if (n != null)
			return n;
		else if (jrObject instanceof JRDesignBand) {
			return new MBand(parent, (JRDesignBand) jrObject, ((JRDesignBand) jrObject).getOrigin().getBandTypeValue(),
					newIndex);
		} else if (jrObject instanceof JRFrame) {
			return new MFrame(parent, (JRDesignFrame) jrObject, newIndex);
		} else if (jrObject instanceof JRElementGroup) {
			return new MElementGroup(parent, (JRElementGroup) jrObject, newIndex);

		} else if (jrObject instanceof JRChart) {
			return new MChart(parent, (JRDesignChart) jrObject, newIndex);
		} else if (jrObject instanceof JRSubreport) {
			return new MSubreport(parent, (JRDesignSubreport) jrObject, newIndex);
		} else if (jrObject instanceof JRCrosstab) {
			return new MCrossTab(parent, (JRDesignCrosstab) jrObject, newIndex);

		} else if (jrObject instanceof JRDesignEllipse) {
			return new MEllipse(parent, (JRDesignEllipse) jrObject, newIndex);
		} else if (jrObject instanceof JRDesignRectangle) {
			return new MRectangle(parent, (JRDesignRectangle) jrObject, newIndex);
		} else if (jrObject instanceof JRDesignLine) {
			return new MLine(parent, (JRDesignLine) jrObject, newIndex);
		} else if (jrObject instanceof JRDesignImage) {
			return new MImage(parent, (JRDesignImage) jrObject, newIndex);
		} else if (jrObject instanceof JRDesignImage) {
			return new MImage(parent, (JRDesignImage) jrObject, newIndex);
		} else if (jrObject instanceof JRDesignStaticText) {
			return new MStaticText(parent, (JRDesignStaticText) jrObject, newIndex);
		} else if (jrObject instanceof JRDesignBreak) {
			return new MBreak(parent, (JRDesignBreak) jrObject, newIndex);
		} else if (jrObject instanceof JRDesignCrosstab) {
			return new MCrossTab(parent, (JRDesignCrosstab) jrObject, newIndex);
		} else if (jrObject instanceof JRDesignChart) {
			return new MChart(parent, (JRDesignChart) jrObject, newIndex);
		} else if (jrObject instanceof JRDesignTextField) {
			return new MTextField(parent, (JRDesignTextField) jrObject, newIndex);
			// styles
		} else if (jrObject instanceof JRDesignStyle) {
			if (newIndex != -1) {
				JRReportTemplate[] templates = parent.getJasperDesign().getTemplates();
				if (templates != null && templates.length > 0)
					newIndex += templates.length;
			}
			return new MStyle(parent, (JRDesignStyle) jrObject, newIndex);
		} else if (jrObject instanceof JRDesignConditionalStyle) {
			return new MConditionalStyle(parent, (JRDesignConditionalStyle) jrObject, newIndex);
		} else if (jrObject instanceof JRDesignReportTemplate) {
			return new MStyleTemplate(parent, (JRDesignReportTemplate) jrObject, newIndex);
			// parameters
		} else if (jrObject instanceof JRDesignParameter) {
			JRDesignParameter jrParameter = (JRDesignParameter) jrObject;
			if (jrParameter.isSystemDefined())
				return new MParameterSystem(parent, jrParameter, newIndex);
			return new MParameter(parent, jrParameter, newIndex);
		} else if (jrObject instanceof JRDesignField) {
			return new MField(parent, (JRDesignField) jrObject, newIndex);
		} else if (jrObject instanceof JRDesignSortField) {
			return new MSortField(parent, (JRDesignSortField) jrObject, newIndex);
		} else if (jrObject instanceof JRDesignGroup) {
			return new MGroup(parent, (JRDesignGroup) jrObject, newIndex);
		} else if (jrObject instanceof JRDesignVariable) {
			JRDesignVariable jrVariable = (JRDesignVariable) jrObject;
			if (jrVariable.isSystemDefined())
				return new MVariableSystem(parent, jrVariable, -1);
			else
				return new MVariable(parent, jrVariable, -1);
		} else if (jrObject instanceof JRDesignScriptlet) {
			return new MScriptlet(parent, (JRDesignScriptlet) jrObject, newIndex);
		} else if (jrObject instanceof JRDesignDataset) {
			return new MDataset(parent, (JRDesignDataset) jrObject, newIndex);
		} else {
			newIndex++;
		}
		return null;
	}
}
