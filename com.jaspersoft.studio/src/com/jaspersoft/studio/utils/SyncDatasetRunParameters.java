package com.jaspersoft.studio.utils;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.JRDatasetParameter;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRQuery;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignDatasetParameter;
import net.sf.jasperreports.engine.design.JRDesignDatasetRun;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.query.QueryExecuterFactory;
import net.sf.jasperreports.engine.util.JRQueryExecuterUtils;

import com.jaspersoft.studio.model.IDatasetContainer;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.MReport;
import com.jaspersoft.studio.model.dataset.MDataset;
import com.jaspersoft.studio.model.dataset.MDatasetRun;
import com.jaspersoft.studio.model.util.ModelVisitor;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class SyncDatasetRunParameters {

	public static void syncDatasetRun(MDatasetRun mDsRun, String oldName, String newName) throws JRException {
		JasperDesign jd = mDsRun.getJasperDesign();
		if (jd.getMainDataset().getQuery() != null && jd.getMainDataset().getQuery().getLanguage() != null) {
			String mLang = jd.getMainDataset().getQuery().getLanguage();
			JasperReportsConfiguration jConf = mDsRun.getJasperConfiguration();
			if (jConf == null)
				return;
			String mDsName = jd.getMainDataset().getName();
			JRQueryExecuterUtils qeUtils = JRQueryExecuterUtils.getInstance(jConf);
			QueryExecuterFactory qef = qeUtils.getExecuterFactory(mLang);
			Object[] bprms = qef.getBuiltinParameters();
			if (qef != null && bprms != null) {
				if (oldName != null && !mDsName.equals(newName))
					for (JRDataset ds : jd.getDatasetsList())
						if (ds.getName().equals(oldName) && ds.getQuery().getLanguage().equals(mLang)) {
							cleanDatasetRun(bprms, mDsRun.getValue());
							break;
						}
				if (newName != null && !mDsName.equals(newName))
					for (JRDataset ds : jd.getDatasetsList())
						if (ds.getName().equals(newName) && ds.getQuery().getLanguage().equals(mLang)) {
							setupDatasetRun(bprms, mDsRun.getValue());
							break;
						}
			}
		}
	}

	public static void syncDataset(MDataset mDsRun, String oldLang, String newLang) throws JRException {
		MReport mrep = (MReport) mDsRun.getRoot();
		if (mrep == null)
			return;
		JasperDesign jd = mDsRun.getJasperDesign();
		if (jd.getMainDataset().getQuery() != null && jd.getMainDataset().getQuery().getLanguage() != null) {
			JRDesignDataset subDS = mDsRun.getValue();
			String mDsName = jd.getMainDataset().getName();
			String mLang = jd.getMainDataset().getQuery().getLanguage();
			JasperReportsConfiguration jConf = mDsRun.getJasperConfiguration();
			if (jConf == null)
				return;
			JRQueryExecuterUtils qeUtils = JRQueryExecuterUtils.getInstance(jConf);
			QueryExecuterFactory qef = qeUtils.getExecuterFactory(mLang);
			Object[] bprms = qef.getBuiltinParameters();
			if (qef != null && bprms != null) {
				if (subDS == jd.getMainDesignDataset() || mDsName.equals(subDS.getName())) {
					for (JRDataset ds : jd.getDatasetsList()) {
						if (ds.getName().equals(oldLang))
							for (JRDesignDatasetRun dr : getDatasetRun(mrep, ds))
								cleanDatasetRun(bprms, dr);
					}
					for (JRDataset ds : jd.getDatasetsList()) {
						if (ds.getName().equals(newLang))
							for (JRDesignDatasetRun dr : getDatasetRun(mrep, ds))
								setupDatasetRun(bprms, dr);
					}
				} else {
					if (oldLang.equals(mLang)) {
						for (JRDesignDatasetRun dr : getDatasetRun(mrep, subDS))
							cleanDatasetRun(bprms, dr);
					}
					if (newLang.equals(mLang)) {
						for (JRDesignDatasetRun dr : getDatasetRun(mrep, subDS))
							setupDatasetRun(bprms, dr);
					}
				}
			}
		}
	}

	public static void sync(MReport mrep) {
		JasperReportsConfiguration jConf = mrep.getJasperConfiguration();
		JRQueryExecuterUtils qeUtil = JRQueryExecuterUtils.getInstance(jConf);
		JasperDesign jd = mrep.getValue();
		JRQuery query = jd.getMainDataset().getQuery();
		if (query != null){
			String mlang = query.getLanguage();
			for (JRDataset subds : jd.getDatasetsList())
				if (subds.getQuery() != null && mlang.equals(subds.getQuery().getLanguage())) {
					try {
						// find query executer, look if there are built-in parameters
						QueryExecuterFactory qef = qeUtil.getExecuterFactory(mlang);
						Object[] bprms = qef.getBuiltinParameters();
						if (qef != null && bprms != null) {
							// find all datasetrun that point to subdataset
							for (JRDesignDatasetRun dr : getDatasetRun(mrep, subds))
								setupDatasetRun(bprms, dr);
						}
					} catch (JRException e) {
						e.printStackTrace();
					}
				}
		}
	}

	public static void setupDatasetRun(Object[] bprms, JRDesignDatasetRun dr) throws JRException {
		for (int i = 0; i < bprms.length; i += 2) {
			String pname = (String) bprms[i];
			if (getParameter(dr, pname) != null)
				continue;
			JRDesignDatasetParameter prm = new JRDesignDatasetParameter();
			prm.setName(pname);
			prm.setExpression(new JRDesignExpression("$P{" + pname + "}"));
			dr.addParameter(prm);
		}
		// cleanup?
	}

	public static void cleanDatasetRun(Object[] bprms, JRDesignDatasetRun dr) throws JRException {
		for (int i = 0; i < bprms.length; i += 2) {
			String pname = (String) bprms[i];
			JRDatasetParameter p = getParameter(dr, pname);
			if (p.getExpression() != null && p.getExpression().getText() != null
					&& p.getExpression().getText().equals("$P{" + pname + "}"))
				dr.removeParameter(p);
		}
	}

	private static JRDatasetParameter getParameter(JRDesignDatasetRun dr, String name) {
		for (JRDatasetParameter p : dr.getParameters()) {
			if (p.getName().equals(name))
				return p;
		}
		return null;
	}

	public static List<JRDesignDatasetRun> getDatasetRun(MReport mrep, JRDataset jDataset) {
		final String dsName = jDataset.getName();
		final List<JRDesignDatasetRun> dsRuns = new ArrayList<JRDesignDatasetRun>();
		new ModelVisitor<Object>(mrep) {

			@Override
			public boolean visit(INode n) {
				if (n instanceof IDatasetContainer) {
					List<MDatasetRun> dsRunList = ((IDatasetContainer) n).getDatasetRunList();
					if (dsRunList != null)
						for (MDatasetRun mdsrun : dsRunList) {
							JRDesignDatasetRun dsrun = mdsrun.getValue();
							if (dsrun.getDatasetName() != null && dsrun.getDatasetName().equals(dsName))
								dsRuns.add(dsrun);
						}
				}
				return true;
			}
		};
		return dsRuns;
	}

}
