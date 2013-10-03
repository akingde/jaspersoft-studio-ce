package com.jaspersoft.studio.translation;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import net.sf.jasperreports.eclipse.util.FileUtils;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.eclipse.core.runtime.Platform;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageLoader;

import com.jaspersoft.translation.resources.ITranslationResource;
import com.jaspersoft.translation.resources.TranslationInformation;

public class FragmentCreationUtil {
	
	public final static String SEPARATOR =  System.getProperty("file.separator");
	
	private static final String TEMPLATES_LOCATION_PREFIX = "com/jaspersoft/studio/translation/templates/";
	
	private static final String MANIFEST_TEMPLATE = TEMPLATES_LOCATION_PREFIX + "manifestFile.vm";
	
	private static final String BUILD_TEMPLATE = TEMPLATES_LOCATION_PREFIX + "buildFile.vm";
	
	private static final String FRAGMENT_COMMAND_TEMPLATE = TEMPLATES_LOCATION_PREFIX + "fragmentCommandFile.vm";
	
	private static final String FRAGMENT_XML_FILE = TEMPLATES_LOCATION_PREFIX + "fragmentXmlFile.vm";
	

	
	public static String generateManifest(ExtendedTranslationInformation pluginInfo, boolean isSingleton){
			// Configure the Velocity Engine
			VelocityEngine ve = new VelocityEngine();
			ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath"); 
			ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
			ve.init();
			
			VelocityContext functionContext = new VelocityContext();
			functionContext.put("hostPlugin", pluginInfo.getHostPluginName());
			functionContext.put("bundleName", pluginInfo.getBundleName());
			functionContext.put("qualifier", pluginInfo.getBundleVersion());
			functionContext.put("pluginVersion", pluginInfo.getHostPluginVersion());
			functionContext.put("vendor", pluginInfo.getBundleProducer());
			String singleton = "";
			if (isSingleton) singleton = ";singleton:=true";
			functionContext.put("singleton", singleton);
			
			Template functionTemplate = ve.getTemplate(MANIFEST_TEMPLATE);
			StringWriter fsw = new StringWriter();
			functionTemplate.merge( functionContext, fsw );
			return fsw.toString();
	}
	
	public static void createFragmentXml(File destinationDir, List<ImageLocale> locales){
		String commands = "";
		File iconDir = new File(destinationDir.getAbsolutePath() + SEPARATOR + "icons");
		iconDir.mkdirs();
		for(ImageLocale loc : locales){
			
			String imagePath = "";
			if (loc.getImage() != null){ 
				ImageLoader loader = new ImageLoader();
		    loader.data = new ImageData[] {loc.getImage().getImageData()};
		    String imageName = loc.getLocale().toString()+".png";
		    loader.save(iconDir.getAbsolutePath() + SEPARATOR + imageName, SWT.IMAGE_PNG);
		    imagePath = "icons/"+imageName;
			}
	    
	    VelocityEngine ve = new VelocityEngine();
			ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath"); 
			ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
			ve.init();
			
			VelocityContext commandContext = new VelocityContext();
			commandContext.put("iconpath", imagePath);
			commandContext.put("languageParameter", loc.getLocale().toString());
			commandContext.put("languageName", loc.getLocale().getDisplayLanguage(loc.getLocale()));
			commandContext.put("languageCommandId", "com.jaspersoft.studio.switchlanguage.menus."+loc.getLocale().getDisplayLanguage(new Locale("en", "EN")));
			
			Template commandTemplate = ve.getTemplate(FRAGMENT_COMMAND_TEMPLATE);
			StringWriter fsw = new StringWriter();
			commandTemplate.merge( commandContext, fsw );
			commands += fsw.toString()+"\n";
		}
		
		// Create the fragment.xml file
		VelocityEngine ve = new VelocityEngine();
		ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath"); 
		ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
		ve.init();
		
		VelocityContext fragmentContext = new VelocityContext();
		fragmentContext.put("commands", commands);
		Template fragmentTemplate = ve.getTemplate(FRAGMENT_XML_FILE);
		StringWriter fsw = new StringWriter();
		fragmentTemplate.merge( fragmentContext, fsw );
		try {
			FileUtils.writeFile(new File(destinationDir.getAbsolutePath() + SEPARATOR + "fragment.xml"), fsw.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String generateQualifier(){
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmm");
		Date date = new Date();
		return dateFormat.format(date);
	}
	
	public static void createBuildFile(TranslationInformation pluginInfo, File pluginDir, List<String> rootFileNames){	
		String buildInclusion = "";
		if (rootFileNames.size()>0){
			for(int i=0; i<rootFileNames.size(); i++){
				buildInclusion += ",\\\n"+rootFileNames.get(i);
			}
		}
		// Configure the Velocity Engine
		VelocityEngine ve = new VelocityEngine();
		ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath"); 
		ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
		ve.init();
		VelocityContext functionContext = new VelocityContext();
		functionContext.put("rootFiles", buildInclusion);
		Template functionTemplate = ve.getTemplate(BUILD_TEMPLATE);
		StringWriter fsw = new StringWriter();
		functionTemplate.merge( functionContext, fsw );
		try {
			FileUtils.writeFile(new File(pluginDir.getAbsolutePath() + SEPARATOR + "build.properties"), fsw.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static String getPathFromPackageName(String name){
		String[] names = name.split("\\.");
		String path = "";
		for(String packageName : names){
			path += packageName + System.getProperty("file.separator");
		}
		return path;
	}
	

	private static void createPackages(File srcFolder, TranslationInformation plugin){
		for(ITranslationResource resource : plugin.getResources()){
			if (!resource.isFile()){
				String packageFolderPath = srcFolder.getAbsolutePath()+  FragmentCreationUtil.SEPARATOR + getPathFromPackageName(resource.getResourceName());
				File packageFolder = new File(packageFolderPath);
				packageFolder.mkdirs();
				for(ITranslationResource packageContent : resource.getChildren()){
					if (packageContent.isFile()){
						JarFileUtils.copyFile(new File(packageContent.getResourcePath()), packageFolder);
					}
				}
			} 
		}
	}
	
	private static void forceCreateFragmentRcp(String destinationPath, List<ImageLocale> languagesProvided){
		String tmpDirectory = System.getProperty("java.io.tmpdir"); 
		List<String> rootFileNames = new ArrayList<String>();
		String rcpPluginName = "com.jaspersoft.studio.rcp";
		File pluginDir = new File(tmpDirectory + FragmentCreationUtil.SEPARATOR + rcpPluginName);
		if (pluginDir.exists()) JarFileUtils.delete(pluginDir);
		pluginDir.mkdirs();
		FragmentCreationUtil.createFragmentXml(pluginDir, languagesProvided);
		rootFileNames.add("fragment.xml");
		rootFileNames.add("icons/");
		
		TranslationInformation baseInfo = new TranslationInformation(rcpPluginName);
		String qualifiedName = baseInfo.getPluginName()+"_translation";
		String version = Platform.getBundle(baseInfo.getPluginName()).getHeaders().get("Bundle-Version");
		ExtendedTranslationInformation rcpPlugin = CreateTranslationFragmentCommand.generateExtendedInfo(baseInfo, qualifiedName, version);
		
		String jarName = rcpPlugin.getBundleName() + "_" + rcpPlugin.getBundleVersion() + ".jar";
		FragmentCreationUtil.createBuildFile(rcpPlugin, pluginDir, rootFileNames);
		String manifest = FragmentCreationUtil.generateManifest(rcpPlugin, true);
		JarFileUtils.createJar(destinationPath, pluginDir, jarName, manifest);
	}
	
	public static void createFragment(String destinationPath, List<ExtendedTranslationInformation> translations, List<ImageLocale> languagesProvided)
	{
		boolean rcpPluginFound = false;
		String tmpDirectory = System.getProperty("java.io.tmpdir"); 
		for(ExtendedTranslationInformation plugin : translations){
			List<String> rootFileNames = new ArrayList<String>();
			boolean hasPackage = false;
			File pluginDir = new File(tmpDirectory + FragmentCreationUtil.SEPARATOR + plugin.getPluginName());
			if (pluginDir.exists()) JarFileUtils.delete(pluginDir);
			pluginDir.mkdirs();
			for(ITranslationResource resource : plugin.getResources()){
				if (resource.isFile()){
					rootFileNames.add(resource.getResourceName());
					JarFileUtils.copyFile(new File(resource.getResourcePath()), pluginDir);
				} else hasPackage = true;
			}
			
			if (hasPackage){
				createPackages(pluginDir, plugin);
			}
			
			String manifest = "";
			if (plugin.getHostPluginName().equals("com.jaspersoft.studio.rcp")){
				rcpPluginFound = true;
				FragmentCreationUtil.createFragmentXml(pluginDir, languagesProvided);
				rootFileNames.add("fragment.xml");
				rootFileNames.add("icons/");
				manifest = FragmentCreationUtil.generateManifest(plugin, true);
			} else {
				manifest = FragmentCreationUtil.generateManifest(plugin, false);
			}
			
			String jarName = plugin.getBundleName() + "_" + plugin.getBundleVersion() + ".jar";
			FragmentCreationUtil.createBuildFile(plugin, pluginDir, rootFileNames);
			JarFileUtils.createJar(destinationPath, pluginDir, jarName, manifest);
		}
		
		if (!rcpPluginFound){
			forceCreateFragmentRcp(destinationPath, languagesProvided);
		}
	}
	
}
