package com.jaspersoft.studio.translation;

import com.jaspersoft.translation.resources.TranslationInformation;

public class ExtendedTranslationInformation extends TranslationInformation {

	private String hostPluginName = "";
	
	private String hostPluginVersion = "";
	
	private String bundleName = "";
	
	private String bundleVersion = "";
	
	private String bundleProducer = "";
	
	public ExtendedTranslationInformation(TranslationInformation baseInfo) {
		super(baseInfo.getPluginName(), baseInfo.getResources());
	}
	
	public void setHostPluginName(String hostPluginName){
		this.hostPluginName = hostPluginName;
	}
	
	public void setHostPluginVersion(String hostPluginVersion){
		this.hostPluginVersion = hostPluginVersion;
	}
	
	public void setBundleName(String bundleName){
		this.bundleName = bundleName;
	}
	
	public void setBundleVersion(String bundleVersion){
		this.bundleVersion = bundleVersion;
	}
	
	public void setBundleProducer(String bundleProducer){
		this.bundleProducer = bundleProducer;
	}
	
	public String getHostPluginName(){
		return hostPluginName;
	}
	
	public String getHostPluginVersion(){
		return hostPluginVersion;
	}
	
	public String getBundleName(){
		return bundleName;
	}
	
	public String getBundleVersion(){
		return bundleVersion;
	}
	
	public String getBundleProducer(){
		return bundleProducer;
	}

}
