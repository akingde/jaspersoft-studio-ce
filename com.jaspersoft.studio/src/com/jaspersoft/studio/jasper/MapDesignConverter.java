package com.jaspersoft.studio.jasper;

import net.sf.jasperreports.components.map.MapComponent;
import net.sf.jasperreports.components.map.type.MapImageTypeEnum;
import net.sf.jasperreports.components.map.type.MapScaleEnum;
import net.sf.jasperreports.components.map.type.MapTypeEnum;
import net.sf.jasperreports.engine.JRComponentElement;
import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.JRElement;
import net.sf.jasperreports.engine.JRElementDataset;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.JRPrintElement;
import net.sf.jasperreports.engine.Renderable;
import net.sf.jasperreports.engine.RenderableUtil;
import net.sf.jasperreports.engine.base.JRBasePrintImage;
import net.sf.jasperreports.engine.component.ComponentDesignConverter;
import net.sf.jasperreports.engine.convert.ElementIconConverter;
import net.sf.jasperreports.engine.convert.ReportConverter;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.type.HorizontalAlignEnum;
import net.sf.jasperreports.engine.type.OnErrorTypeEnum;
import net.sf.jasperreports.engine.type.ScaleImageEnum;
import net.sf.jasperreports.engine.type.VerticalAlignEnum;
import net.sf.jasperreports.engine.util.JRImageLoader;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.swt.widgets.Display;

import com.jaspersoft.studio.editor.AMultiEditor;
import com.jaspersoft.studio.model.util.KeyValue;
import com.jaspersoft.studio.utils.CacheMap;
import com.jaspersoft.studio.utils.ExpressionUtil;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

/**
 * 
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: MapDesignConverter.java 5877 2013-01-07 19:51:14Z teodord $
 */
public class MapDesignConverter extends ElementIconConverter implements ComponentDesignConverter {

	public static final Float DEFAULT_LONGITUDE = new Float(12.337967);
	public static final Float DEFAULT_LATITUDE = new Float(45.433967);
	/**
	 *
	 */
	private final static MapDesignConverter INSTANCE = new MapDesignConverter();

	/**
	 *
	 */
	private MapDesignConverter() {
		super(JRImageLoader.COMPONENT_IMAGE_RESOURCE);
	}

	/**
	 *
	 */
	public static MapDesignConverter getInstance() {
		return INSTANCE;
	}

	private CacheMap<JRComponentElement, Renderable> cache = new CacheMap<JRComponentElement, Renderable>(3000000);
	private CacheMap<JRElement, KeyValue<String, Long>> running = new CacheMap<JRElement, KeyValue<String, Long>>(3000);

	/**
	 *
	 */
	public JRPrintElement convert(final ReportConverter reportConverter, final JRComponentElement element) {
		Renderable cacheRenderer = cache.get(element);
		KeyValue<String, Long> last = running.get(element);
		if (cacheRenderer == null || last == null
				|| (last.value != null && System.currentTimeMillis() - last.value.longValue() > 1000)) {
			final KeyValue<String, Long> kv = new KeyValue<String, Long>(null, null);
			Job job = new Job("load map") {
				protected IStatus run(IProgressMonitor monitor) {
					try {
						MapComponent map = (MapComponent) element.getComponent();
						final JasperReportsConfiguration jConfig = (JasperReportsConfiguration) reportConverter
								.getJasperReportsContext();
						JasperDesign jd = jConfig.getJasperDesign();
						JRDataset jrd = jd.getMainDataset();
						JRElementDataset dataset = null;
						if (map.getMarkerData() != null) {
							dataset = map.getMarkerData().getDataset();
						}
						if (dataset != null && dataset.getDatasetRun() != null) {
							String dname = dataset.getDatasetRun().getDatasetName();
							if (dname != null)
								jrd = jd.getDatasetMap().get(dname);
						}
						Float latitude = evaluate(map.getLatitudeExpression(), jrd, jConfig, DEFAULT_LATITUDE);
						Float longitude = evaluate(map.getLongitudeExpression(), jrd, jConfig, DEFAULT_LONGITUDE);

						Integer zoom = evaluate(map.getZoomExpression(), jrd, jConfig, MapComponent.DEFAULT_ZOOM);

						String mapType = map.getMapType() != null ? map.getMapType().getName() : MapTypeEnum.ROADMAP.getName();
						String mapScale = map.getMapScale() != null ? map.getMapScale().getName() : MapScaleEnum.ONE.getName();
						String mapFormat = MapImageTypeEnum.PNG.getName();
						String language = evaluate(map.getLanguageExpression(), jrd, jConfig, "");
						String markers = "";

						String imageLocation = "http://maps.google.com/maps/api/staticmap?center=" + latitude + "," + longitude
								+ "&size=" + element.getWidth() + "x" + element.getHeight() + "&zoom=" + zoom
								+ (mapType == null ? "" : "&maptype=" + mapType) + (mapFormat == null ? "" : "&format=" + mapFormat)
								+ (mapScale == null ? "" : "&scale=" + mapScale) + markers + "&sensor=false"
								+ (language == null ? "" : "&language=" + language);
						kv.key = imageLocation;
						final Renderable r = RenderableUtil.getInstance(jConfig).getRenderable(imageLocation,
								OnErrorTypeEnum.ERROR, false);
						r.getImageData(jConfig);
						Display.getDefault().asyncExec(new Runnable() {

							@Override
							public void run() {
								cache.put(element, r);
								kv.value = System.currentTimeMillis();
								AMultiEditor.refresh(jConfig);
							}

						});
					} catch (JRException e) {
					}
					return Status.OK_STATUS;
				}
			};
			job.setSystem(true);
			job.setPriority(Job.SHORT);
			job.schedule();
			running.put(element, kv);
			if (cacheRenderer == null)
				return convert(reportConverter, (JRElement) element);
		}
		JRBasePrintImage printImage = new JRBasePrintImage(element.getDefaultStyleProvider());
		printImage.setUUID(element.getUUID());
		printImage.setX(element.getX());
		printImage.setY(element.getY());
		printImage.setWidth(element.getWidth());
		printImage.setHeight(element.getHeight());
		printImage.setStyle(element.getStyle());
		printImage.setMode(element.getModeValue());
		printImage.setBackcolor(element.getBackcolor());
		printImage.setForecolor(element.getForecolor());
		printImage.setLazy(false);

		// FIXMEMAP there are no scale image, alignment and onError attributes
		// defined for the map element
		printImage.setScaleImage(ScaleImageEnum.CLIP);
		printImage.setHorizontalAlignment(HorizontalAlignEnum.LEFT);
		printImage.setVerticalAlignment(VerticalAlignEnum.TOP);
		printImage.setRenderable(cacheRenderer);
		return printImage;
	}

	private <T> T evaluate(JRExpression expr, JRDataset jrd, JasperReportsConfiguration jConfig, T def) {
		if (expr != null) {
			Object l = ExpressionUtil.eval(expr, jrd, jConfig);
			if (l != null)
				try {
					return (T) l;
				} catch (Exception e) {
					return def;
				}
		}
		return def;
	}
}
