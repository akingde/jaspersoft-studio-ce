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
package com.jaspersoft.studio.utils;

import java.awt.GraphicsEnvironment;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

import net.sf.jasperreports.engine.JRBand;
import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.JRElement;
import net.sf.jasperreports.engine.JRGroup;
import net.sf.jasperreports.engine.JROrigin;
import net.sf.jasperreports.engine.JRSection;
import net.sf.jasperreports.engine.design.JRDesignBand;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JRDesignGroup;
import net.sf.jasperreports.engine.design.JRDesignSection;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.query.QueryExecuterFactoryBundle;
import net.sf.jasperreports.engine.type.BandTypeEnum;
import net.sf.jasperreports.engine.type.HyperlinkTypeEnum;
import net.sf.jasperreports.engine.util.JRFontUtil;
import net.sf.jasperreports.engine.util.JRProperties;
import net.sf.jasperreports.engine.util.JRProperties.PropertySuffix;
import net.sf.jasperreports.engine.util.MarkupProcessorFactory;
import net.sf.jasperreports.extensions.ExtensionsEnvironment;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.jface.resource.ImageDescriptor;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.IGraphicElement;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.model.band.MBand;
import com.jaspersoft.studio.property.descriptor.NullEnum;

/**
 * The Class ModelUtils.
 */
public class ModelUtils {

	public static String[] getDataSources(JasperDesign jd) {
		List<?> datasetsList = jd.getDatasetsList();
		String[] res = new String[datasetsList.size() + 1];
		res[0] = jd.getMainDataset().getName();
		for (int i = 0; i < datasetsList.size(); i++)
			res[i + 1] = ((JRDataset) datasetsList.get(i)).getName();
		return res;
	}

	private static Map<String, String> mp = new HashMap<String, String>();

	/**
	 * Return the ordered list of bands available in the current report.
	 * 
	 * @param jd
	 *          the JasperDesign
	 * @return a list of bands
	 */
	public static List<JRBand> getBands(JasperDesign jd) {
		List<JRBand> list = new ArrayList<JRBand>();
		if (jd == null)
			return list;

		JRGroup[] groups = jd.getGroups();

		if (null != jd.getTitle())
			list.add(jd.getTitle());
		if (null != jd.getPageHeader())
			list.add(jd.getPageHeader());
		if (null != jd.getColumnHeader())
			list.add(jd.getColumnHeader());
		for (int i = 0; i < groups.length; ++i) {
			// if (null != groups[i].getGroupHeader())
			// list.add(groups[i].getGroupHeader());
			if (groups[i].getGroupHeaderSection() != null) {
				JRBand[] bandsList = groups[i].getGroupHeaderSection().getBands();
				for (int k = 0; bandsList != null && k < bandsList.length; ++k) {
					if (bandsList[k] != null) {
						list.add(bandsList[k]);
					}
				}
			}

		}
		// if (null != jd.getDetail()) list.add(jd.getDetail());
		if (jd.getDetailSection() != null) {
			JRBand[] bandsList = jd.getDetailSection().getBands();
			for (int k = 0; bandsList != null && k < bandsList.length; ++k) {
				if (bandsList[k] != null) {
					list.add(bandsList[k]);
				}
			}
		}

		for (int i = groups.length - 1; i >= 0; --i) {
			// if (null != groups[i].getGroupFooter())
			// list.add(groups[i].getGroupFooter());
			if (groups[i].getGroupFooterSection() != null) {
				JRBand[] bandsList = groups[i].getGroupFooterSection().getBands();
				for (int k = 0; bandsList != null && k < bandsList.length; ++k) {
					if (bandsList[k] != null) {
						list.add(bandsList[k]);
					}
				}
			}
		}
		if (null != jd.getColumnFooter())
			list.add(jd.getColumnFooter());
		if (null != jd.getPageFooter())
			list.add(jd.getPageFooter());
		if (null != jd.getLastPageFooter())
			list.add(jd.getLastPageFooter());
		if (null != jd.getSummary())
			list.add(jd.getSummary());
		// if (null != jd.getNoData())
		// list.add(jd.getNoData());
		// if (null != jd.getBackground())
		// list.add(jd.getBackground());

		return list;
	}

	/**
	 * Gets the design height.
	 * 
	 * @param jd
	 *          the jd
	 * @return the design height
	 */
	public static int getDesignHeight(JasperDesign jd) {
		int designHeight = 0;
		if (jd != null) {
			designHeight += jd.getTopMargin();
			designHeight += getDesignHeight(ModelUtils.getAllBands(jd));
			designHeight += jd.getBottomMargin();
		}
		/*
		 * // Detached background... if (IReportManager.getInstance().isBackgroundSeparated() && jd.getBackground() != null
		 * && jd.getBackground().getHeight() > 0) { designHeight += jd.getTopMargin(); designHeight += jd.getBottomMargin();
		 * designHeight += 40; }
		 */

		return designHeight;
	}

	/**
	 * Gets the design height.
	 * 
	 * @param bands
	 *          the bands
	 * @return the design height
	 */
	public static int getDesignHeight(List<JRBand> bands) {
		int designHeight = 0;
		for (JRBand b : bands) {
			designHeight += b.getHeight();
		}
		return designHeight;
	}

	/**
	 * Element exists.
	 * 
	 * @param jrBand
	 *          the jr band
	 * @param jrElement
	 *          the jr element
	 * @return true, if successful
	 */
	public static boolean elementExists(JRBand jrBand, JRElement jrElement) {
		JRElement[] elements = jrBand.getElements();
		for (int i = 0; i < elements.length; i++)
			if (elements[i] == jrElement) {
				return true;
			}
		return false;
	}

	/**
	 * Gets the band4 element.
	 * 
	 * @param bands
	 *          the bands
	 * @param jrElement
	 *          the jr element
	 * @return the band4 element
	 */
	public static int getBand4Element(List<JRBand> bands, JRElement jrElement) {
		for (int i = 0; i < bands.size(); i++) {
			JRBand cBand = bands.get(i);
			if (cBand != null && ModelUtils.elementExists(cBand, jrElement))
				return i;
		}
		return -1;
	}

	/**
	 * Gets the all bands.
	 * 
	 * @param jrDesign
	 *          the jr design
	 * @return the all bands
	 */
	public static List<JRBand> getAllBands(JasperDesign jrDesign) {
		List<JRBand> bands = new ArrayList<JRBand>();
		if (jrDesign.getTitle() != null)
			bands.add(jrDesign.getTitle());
		if (jrDesign.getPageHeader() != null)
			bands.add(jrDesign.getPageHeader());
		if (jrDesign.getColumnHeader() != null)
			bands.add(jrDesign.getColumnHeader());
		if (jrDesign.getGroups() != null)
			for (Object g : jrDesign.getGroupsList()) {
				JRDesignGroup gr = (JRDesignGroup) g;
				if (gr.getGroupHeaderSection() != null) {
					bands.addAll(((JRDesignSection) gr.getGroupHeaderSection()).getBandsList());
				}
			}
		if (jrDesign.getDetailSection() != null) {
			JRBand[] bandsList = jrDesign.getDetailSection().getBands();
			if (bandsList != null)
				bands.addAll(Arrays.asList(bandsList));
		}
		if (jrDesign.getGroupsList() != null) {
			for (ListIterator<?> ij = jrDesign.getGroupsList().listIterator(jrDesign.getGroupsList().size()); ij
					.hasPrevious();) {
				JRDesignGroup gr = (JRDesignGroup) ij.previous();
				if (gr.getGroupFooterSection() != null) {
					bands.addAll(((JRDesignSection) gr.getGroupFooterSection()).getBandsList());
				}
			}
		}
		if (jrDesign.getColumnFooter() != null)
			bands.add(jrDesign.getColumnFooter());
		if (jrDesign.getPageFooter() != null)
			bands.add(jrDesign.getPageFooter());
		if (jrDesign.getLastPageFooter() != null)
			bands.add(jrDesign.getLastPageFooter());
		if (jrDesign.getSummary() != null)
			bands.add(jrDesign.getSummary());

		return bands;
	}

	/**
	 * Gets the band4 point.
	 * 
	 * @param jd
	 *          the jd
	 * @param point
	 *          the point
	 * @return the band4 point
	 */
	public static MBand getBand4Point(ANode jd, Point point) {
		ANode res = jd;
		ANode rNode = jd; // root node from drag&drop operation
		int xband = jd.getJasperDesign().getTopMargin();
		// iterate IGraphicElements, and look at their position
		// find the top level container for this element
		for (INode n : rNode.getChildren()) {
			if (n instanceof IGraphicElement) {
				Object de = n.getValue();
				if (de instanceof JRDesignBand) {
					JRDesignBand deband = (JRDesignBand) de;
					res = (ANode) n;
					if (point.y >= xband && point.y < xband + deband.getHeight()) {
						// go to children, we have the band allready
						break;
					}
					xband += deband.getHeight();
				}
			}
		}
		return (MBand) res;
	}

	/**
	 * Gets the band location.
	 * 
	 * @param b
	 *          the b
	 * @param jd
	 *          the jd
	 * @return the band location
	 */
	public static int getBandLocation(JRBand b, JasperDesign jd) {

		int yLocation = jd.getTopMargin();
		List<JRBand> bands = ModelUtils.getBands(jd);

		for (JRBand tmpBand : bands) {
			// Detached background...
			if (tmpBand instanceof JRDesignBand) {
				if (((JRDesignBand) tmpBand).getOrigin().getBandType() == JROrigin.BACKGROUND) {
					// if (IReportManager.getInstance().isBackgroundSeparated())
					// {
					// yLocation += jd.getTopMargin();
					// yLocation += jd.getBottomMargin();
					// yLocation += 40;
					// }
				}
			}
			if (tmpBand == b)
				return yLocation;
			yLocation += tmpBand.getHeight();
		}

		return yLocation;
	}

	public static Point getY4Element(MGraphicElement mge) {
		JasperDesign jrDesign = mge.getJasperDesign();
		JRDesignElement jrElement = (JRDesignElement) mge.getValue();
		int y = jrElement.getY() + jrDesign.getTopMargin();
		List<JRBand> bands = ModelUtils.getAllBands(jrDesign);
		int pos = ModelUtils.getBand4Element(bands, jrElement);
		for (int i = 0; i < pos; i++) {
			y += bands.get(i).getHeight();
		}
		int x = jrElement.getX() + jrDesign.getLeftMargin();
		return new Point(x, y);
	}

	/**
	 * Returns an image descriptor for the image file at the given plug-in relative path.
	 * 
	 * @param path
	 *          the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(String path) {
		JaspersoftStudioPlugin.getInstance();
		return JaspersoftStudioPlugin.getImageDescriptor(path);
	}

	/**
	 * Name of.
	 * 
	 * @param b
	 *          the b
	 * @param jd
	 *          the jd
	 * @return the string
	 */
	public static String nameOf(JRBand b, JasperDesign jd) {
		JROrigin origin = ((JRDesignBand) b).getOrigin();
		if (origin.getBandType() == JROrigin.GROUP_HEADER) {

			JRGroup group = (JRGroup) jd.getGroupsMap().get(origin.getGroupName());
			int index = getBandIndex(group.getGroupHeaderSection(), b);
			return Messages.ModelUtils_groupheader_section + " " + origin.getGroupName() + " " + (index + 1); //$NON-NLS-1$ //$NON-NLS-2$

		} else if (origin.getBandType() == JROrigin.DETAIL) {
			int index = getBandIndex(jd.getDetailSection(), b);
			return Messages.ModelUtils_detail_section + " " + (index + 1); //$NON-NLS-1$
		} else if (origin.getBandType() == JROrigin.GROUP_FOOTER) {
			JRGroup group = (JRGroup) jd.getGroupsMap().get(origin.getGroupName());
			int index = getBandIndex(group.getGroupFooterSection(), b);
			return Messages.ModelUtils_groupfooter_section + " " + origin.getGroupName() + (index + 1); //$NON-NLS-1$
		}

		return nameOf(((JRDesignBand) b).getOrigin());
	}

	/**
	 * Return the index of band in the section. It return -1 if the band is not found in this section
	 * 
	 * @param section
	 *          the section
	 * @param band
	 *          the band
	 * @return the band index
	 */
	public static int getBandIndex(JRSection section, JRBand band) {
		JRBand[] bands = section.getBands();
		for (int i = 0; bands != null && i < bands.length; ++i) {
			if (bands[i] == band)
				return i;
		}
		return -1;

	}

	/**
	 * Name of.
	 * 
	 * @param origin
	 *          the origin
	 * @return the string
	 */
	public static String nameOf(JROrigin origin) {
		return origin.getBandTypeValue().getName();
	}

	/**
	 * This method summarize the JasperReports rules for bands height. The real check should be done by the JRVerifier
	 * class, probably we should move that code there providing a similar static method.
	 * 
	 * @param b
	 *          the b
	 * @param jd
	 *          the jd
	 * @return the max band height
	 */
	public static int getMaxBandHeight(JRDesignBand b, JasperDesign jd) {
		if (b == null || jd == null)
			return 0;

		JROrigin origin = b.getOrigin();

		int topBottomMargins = jd.getTopMargin() + jd.getBottomMargin();

		if ((origin.getBandTypeValue() == BandTypeEnum.TITLE && jd.isTitleNewPage())
				|| (origin.getBandTypeValue() == BandTypeEnum.SUMMARY) || // &&
				// jd.isSummaryNewPage()
				origin.getBandTypeValue() == BandTypeEnum.BACKGROUND || origin.getBandTypeValue() == BandTypeEnum.NO_DATA) {
			return jd.getPageHeight() - topBottomMargins;
		}

		int basicBandsHeight = 0;

		basicBandsHeight += topBottomMargins;
		basicBandsHeight += jd.getPageHeader() != null ? jd.getPageHeader().getHeight() : 0;
		basicBandsHeight += jd.getColumnHeader() != null ? jd.getColumnHeader().getHeight() : 0;
		basicBandsHeight += jd.getColumnFooter() != null ? jd.getColumnFooter().getHeight() : 0;

		if (b.getOrigin().getBandTypeValue() == BandTypeEnum.LAST_PAGE_FOOTER) {
			return jd.getPageHeight() - basicBandsHeight;
		}

		basicBandsHeight += jd.getPageFooter() != null ? jd.getPageFooter().getHeight() : 0;

		int heighestGroupHeader = 0;
		int heighestGroupFooter = 0;

		for (int i = 0; i < jd.getGroupsList().size(); ++i) {
			JRDesignGroup grp = (JRDesignGroup) jd.getGroupsList().get(i);
			JRBand[] bands = grp.getGroupHeaderSection().getBands();
			for (int k = 0; bands != null && k < bands.length; ++k) {
				heighestGroupHeader = Math.max(heighestGroupHeader, bands[k].getHeight());
			}
			bands = grp.getGroupFooterSection().getBands();
			for (int k = 0; bands != null && k < bands.length; ++k) {
				heighestGroupFooter = Math.max(heighestGroupFooter, bands[k].getHeight());
			}
		}

		if (b.getOrigin().getBandTypeValue() == BandTypeEnum.TITLE) {
			return jd.getPageHeight() - basicBandsHeight - Math.max(heighestGroupFooter, heighestGroupHeader);
		}

		if (b.getOrigin().getBandTypeValue() == BandTypeEnum.DETAIL) {
			return jd.getPageHeight() - basicBandsHeight;
		}

		int titleHeight = jd.getTitle() != null ? jd.getTitle().getHeight() : 0;
		if (jd.isTitleNewPage())
			titleHeight = 0;

		if (origin.getBandTypeValue() == BandTypeEnum.GROUP_FOOTER
				|| origin.getBandTypeValue() == BandTypeEnum.GROUP_HEADER) {
			return jd.getPageHeight() - basicBandsHeight - titleHeight;
		}

		// int summaryHeight = jd.getSummary() != null ? jd.getSummary().getHeight()
		// : 0;
		// if (!jd.isSummaryNewPage()) basicBandsHeight += summaryHeight;

		int detailHeight = 0;

		if (jd.getDetailSection() != null) {
			JRBand[] bandsList = jd.getDetailSection().getBands();
			for (int k = 0; bandsList != null && k < bandsList.length; ++k) {
				detailHeight = Math.max(detailHeight, bandsList[k].getHeight());
			}
		}

		int maxAlternativeSection = Math
				.max(detailHeight, Math.max(heighestGroupFooter, heighestGroupHeader) + titleHeight);

		basicBandsHeight += maxAlternativeSection;

		int res = jd.getPageHeight() - basicBandsHeight + b.getHeight();
		res = Math.min(res, jd.getPageHeight() - topBottomMargins);
		res = Math.max(res, 0);

		// Calcolate the design page without extra bands and the current band...
		return res;
	}

	/**
	 * Gets the default name.
	 * 
	 * @param map
	 *          the map
	 * @param name
	 *          the name
	 * @return the default name
	 */
	public static String getDefaultName(Map<?, ?> map, String name) {
		int i = 1;
		while (i < 100000) {
			String iname = name + i;
			if (map.get(iname) == null)
				return iname;
			i++;
		}
		return name;
	}

	public static String[] getQueryLanguages() {
		Set<String> langs = new HashSet<String>();
		List<?> bundles = ExtensionsEnvironment.getExtensionsRegistry().getExtensions(QueryExecuterFactoryBundle.class);
		for (Iterator<?> it = bundles.iterator(); it.hasNext();) {
			QueryExecuterFactoryBundle bundle = (QueryExecuterFactoryBundle) it.next();
			String[] l = bundle.getLanguages();
			for (int i = 0; i < l.length; i++)
				langs.add(l[i]);
		}
		List<String> lst = new ArrayList<String>();
		lst.add(""); //$NON-NLS-1$
		lst.addAll(langs);
		return lst.toArray(new String[lst.size()]);
	}

	public static String[] getMarkups() {
		List<String> lst = new ArrayList<String>();
		lst.add(""); //$NON-NLS-1$
		lst.add("none"); //$NON-NLS-1$
		lst.add("styled"); //$NON-NLS-1$
		List<PropertySuffix> props = JRProperties
				.getProperties(MarkupProcessorFactory.PROPERTY_MARKUP_PROCESSOR_FACTORY_PREFIX);
		for (PropertySuffix p : props) {
			lst.add(p.getSuffix());
		}
		return lst.toArray(new String[lst.size()]);
	}

	public static String[] getFontNames() {
		java.util.List<String> classes = new ArrayList<String>();
		classes.add(""); //$NON-NLS-1$
		ClassLoader oldCL = Thread.currentThread().getContextClassLoader();

		Collection<?> extensionFonts = JRFontUtil.getFontFamilyNames();
		for (Iterator<?> it = extensionFonts.iterator(); it.hasNext();) {
			String fname = (String) it.next();
			classes.add(fname);
		}

		Thread.currentThread().setContextClassLoader(oldCL);

		String[] names = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
		classes.add("______________"); //$NON-NLS-1$
		for (int i = 0; i < names.length; i++) {
			String name = names[i];
			classes.add(name);
		}

		return classes.toArray(new String[classes.size()]);
	}

	public static String[] getPDFFontNames() {
		java.util.List<String> classes = new ArrayList<String>();
		classes.add(""); //$NON-NLS-1$
		classes.add("Helvetica"); //$NON-NLS-1$
		classes.add("Helvetica-Bold"); //$NON-NLS-1$
		classes.add("Helvetica-BoldOblique"); //$NON-NLS-1$
		classes.add("Helvetica-Oblique"); //$NON-NLS-1$
		classes.add("Courier"); //$NON-NLS-1$
		classes.add("Courier-Bold"); //$NON-NLS-1$
		classes.add("Courier-BoldOblique"); //$NON-NLS-1$
		classes.add("Courier-Oblique"); //$NON-NLS-1$
		classes.add("Symbol"); //$NON-NLS-1$
		classes.add("Times-Roman"); //$NON-NLS-1$
		classes.add("Times-Bold"); //$NON-NLS-1$
		classes.add("Times-BoldItalic"); //$NON-NLS-1$
		classes.add("Times-Italic"); //$NON-NLS-1$
		classes.add("ZapfDingbats"); //$NON-NLS-1$
		classes.add("STSong-Light"); //$NON-NLS-1$
		classes.add("MHei-Medium"); //$NON-NLS-1$
		classes.add("MSung-Light"); //$NON-NLS-1$
		classes.add("HeiseiKakuGo-W5"); //$NON-NLS-1$
		classes.add("HeiseiMin-W3"); //$NON-NLS-1$
		classes.add("HYGoThic-Medium"); //$NON-NLS-1$
		classes.add("HYSMyeongJo-Medium"); //$NON-NLS-1$

		return classes.toArray(new String[classes.size()]);
	}

	public static String[] getPDFEncodings() {
		java.util.List<String> encodings = new ArrayList<String>();
		encodings.add(""); //$NON-NLS-1$

		mp.put("Cp1250", "CP1250 (Central European)"); //$NON-NLS-1$ //$NON-NLS-2$
		mp.put("Cp1251", "CP1251 (Cyrillic)"); //$NON-NLS-1$ //$NON-NLS-2$
		mp.put("Cp1252", "CP1252 (Western European ANSI aka WinAnsi)"); //$NON-NLS-1$ //$NON-NLS-2$
		mp.put("Cp1253", "CP1253 (Greek)"); //$NON-NLS-1$ //$NON-NLS-2$
		mp.put("Cp1254", "CP1254 (Turkish)"); //$NON-NLS-1$ //$NON-NLS-2$
		mp.put("Cp1255", "CP1255 (Hebrew)"); //$NON-NLS-1$ //$NON-NLS-2$
		mp.put("Cp1256", "CP1256 (Arabic)"); //$NON-NLS-1$ //$NON-NLS-2$
		mp.put("Cp1257", "CP1257 (Baltic)"); //$NON-NLS-1$ //$NON-NLS-2$
		mp.put("Cp1258", "CP1258 (Vietnamese)"); //$NON-NLS-1$ //$NON-NLS-2$
		mp.put("UniGB-UCS2-H", "UniGB-UCS2-H (Chinese Simplified)"); //$NON-NLS-1$ //$NON-NLS-2$
		mp.put("UniGB-UCS2-V", "UniGB-UCS2-V (Chinese Simplified)"); //$NON-NLS-1$ //$NON-NLS-2$
		mp.put("UniCNS-UCS2-H", "UniCNS-UCS2-H (Chinese traditional)"); //$NON-NLS-1$ //$NON-NLS-2$
		mp.put("UniCNS-UCS2-V", "UniCNS-UCS2-V (Chinese traditional)"); //$NON-NLS-1$ //$NON-NLS-2$
		mp.put("UniJIS-UCS2-H", "UniJIS-UCS2-H (Japanese)"); //$NON-NLS-1$ //$NON-NLS-2$
		mp.put("UniJIS-UCS2-V", "UniJIS-UCS2-V (Japanese)"); //$NON-NLS-1$ //$NON-NLS-2$
		mp.put("UniJIS-UCS2-HW-H", "UniJIS-UCS2-HW-H (Japanese)"); //$NON-NLS-1$ //$NON-NLS-2$
		mp.put("UniJIS-UCS2-HW-V", "UniJIS-UCS2-HW-V (Japanese)"); //$NON-NLS-1$ //$NON-NLS-2$
		mp.put("UniKS-UCS2-H", "UniKS-UCS2-H (Korean)"); //$NON-NLS-1$ //$NON-NLS-2$
		mp.put("UniKS-UCS2-V", "UniKS-UCS2-V (Korean)"); //$NON-NLS-1$ //$NON-NLS-2$
		mp.put("Identity-H", "Identity-H (Unicode with horizontal writing)"); //$NON-NLS-1$ //$NON-NLS-2$
		mp.put("Identity-V", "Identity-V (Unicode with vertical writing)"); //$NON-NLS-1$ //$NON-NLS-2$

		encodings.addAll(mp.values());

		return encodings.toArray(new String[encodings.size()]);
	}

	public static String getKey4PDFEncoding(String enc) {
		if (enc != null) {
			String res = mp.get(enc);
			if (res != null)
				return res;
		}
		return enc;
	}

	public static String getPDFEncoding2key(String key) {
		if (key != null) {
			for (String k : mp.keySet()) {
				String v = mp.get(k);
				if (v.equals(key))
					return v;
			}
		}
		return key;
	}

	public static String[] getFontSizes() {
		return new String[] { "", "8", "9", "10", "11", "12", "14", "16", "18", "20", "22", "24", "26" }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$ //$NON-NLS-9$ //$NON-NLS-10$ //$NON-NLS-11$ //$NON-NLS-12$ //$NON-NLS-13$
	}

	public static String[] getHyperLinkType() {
		List<String> list = new ArrayList<String>();
		list.addAll(Arrays.asList(EnumHelper.getEnumNames(HyperlinkTypeEnum.values(), NullEnum.NOTNULL)));

		return list.toArray(new String[list.size()]);
	}
}
