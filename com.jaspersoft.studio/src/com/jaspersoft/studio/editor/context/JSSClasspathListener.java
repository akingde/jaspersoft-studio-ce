package com.jaspersoft.studio.editor.context;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.utils.ModelUtils;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.util.CastorMapping;

public class JSSClasspathListener implements PropertyChangeListener {
	private JasperReportsConfiguration jConf;
	private AEditorContext cntx;

	public JSSClasspathListener(AEditorContext cntx, JasperReportsConfiguration jConf) {
		this.jConf = jConf;
		this.cntx = cntx;
	}

	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		cntx.initClassloader();
		jConf.resetCaches(arg0);
		// trigger the reload of mappings
		jConf.getExtensions(CastorMapping.class);
		JasperDesign jd = jConf.getJasperDesign();
		if (jd != null) {
			List<JRDesignElement> allElements = ModelUtils.getAllGElements(jd);
			for (JRDesignElement element : allElements)
				element.getEventSupport().firePropertyChange(MGraphicElement.FORCE_GRAPHICAL_REFRESH, true, false);
		}
	}
}