/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.statistics;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;

import org.eclipse.jface.util.Util;

import com.jaspersoft.studio.JaspersoftStudioPlugin;

import net.sf.jasperreports.eclipse.JasperReportsPlugin;
import net.sf.jasperreports.eclipse.util.FileUtils;

/**
 * This class allow to identify the current operative system. Identifying the OS
 * is pretty easy in case of mac/windows, but in linux the system property return always
 * as system name "Linux" and as version its kernel version. This make really hard to understand
 * which distro of linux is really used. This class attempt to scan for some identifier files
 * that are defined in the most common distro
 *
 */
public class OSIdentifier {
	
  private class OSDescription {
    private String name;
    private String architecture;
    private String version;

    private OSDescription(final String name, final String version, final String architecture) {
      this.name = name;
      this.architecture = architecture;
      this.version = version;
    }

    public String getName() {
      return name;
    }

    public String getArchitecture() {
      return architecture;
    }

    public String getVersion() {
      return version;
    }
  }

  /**
   * The info of the current OS
   */
  private OSDescription currentOSDescription;

  /**
   * The instance of this resolver
   */
  private final static OSIdentifier INSTANCE;
  
  /**
   * Key used in linux to get the distro name from the platform file
   */
  private static final String KEY_PRETTY_NAME = "PRETTY_NAME";
  
  /**
   * Key used in linux to get the distro description from the LSB-RELEASE file 
   */
  private static final String KEY_LSB_DISTRIB_DESCRIPTION = "DISTRIB_DESCRIPTION";

  /**
   * Key used in linux to get the distro name from the LSB-RELEASE file 
   */
  private static final String KEY_LSB_DISTRIB_CODENAME = "DISTRIB_CODENAME";
  
  static{
  	String name = System.getProperty("os.name");
    String version = System.getProperty("os.version");
    String architecture = System.getProperty("os.arch");
    INSTANCE = new OSIdentifier(name, version, architecture);
  }
  
  /**
   * Constructor, it is protected because the class is created as a singleton
   */
  protected OSIdentifier(final String name, final String version, final String arch) {
    try{
	  	if (name != null) {
	      if (Util.isWindows()) {
	        currentOSDescription = new OSDescription(name, version, arch);
	      } else if (Util.isMac()) {
	        this.currentOSDescription = new OSDescription("MacOS", version, arch);
	      } else if (Util.isLinux()) {
	        initLinuxOsInfo(name, version, arch);
	      }         
	    }
    } catch (Exception ex){
    	//This class is initialized during the JaspersoftStudioPlugin initialization, this
    	//its instance field could no be assigned yet, so relay to the JasperReportsPlugin to log
    	//the exception
    	JasperReportsPlugin.getDefault().logError(ex);
    	ex.printStackTrace();
    	currentOSDescription = null;
    }
    if (currentOSDescription == null){
      currentOSDescription = new OSDescription(name, version, arch);
    }
  }

  /**
   * Return an informative name for the current OS
   * 
   * @return a not null string
   */
  public static String getName() {
    return INSTANCE.currentOSDescription.getName();
  }

  /**
   * Return the architecture for the current OS
   * 
   * @return a not null string
   */
  public static String getArchitecture() {
    return INSTANCE.currentOSDescription.getArchitecture();
  }

  /**
   * Return a version for the current OS
   * 
   * @return a not null string
   */
  public static String getVersion() {
    return INSTANCE.currentOSDescription.getVersion();
  }

  /**
   * Return an informative description for the current OS
   * 
   * @return a not null string
   */
  public static String getOSInfo(){
  	return getName() + " " + getVersion() + " " + getArchitecture();
  }

  /**
   * Search for linux identification file and try to read specific information
   * of the current distribution
   * 
   */
  private void initLinuxOsInfo(String name, String version, String arch) {
    OSDescription osInfo;
    // The most likely is to have a LSB compliant distro
    osInfo = getPlatformNameFromLsbRelease(name, version, arch);

    // Generic Linux platform name
    if (osInfo == null){
      osInfo = getPlatformNameFromFile(name, version, arch, "/etc/system-release");
    }

    if (osInfo == null){
    	// if generic 'system-release' file is not present, then try to find another one
    	File dir = new File("/etc/");
	    if (dir.exists()) {
	    	//Try to search a file ending with '-release'
	      osInfo = getPlatformNameFromFile(name, version, arch, getFileEndingWith(dir, "-release"));
	
	      // if generic file ending with '-release' is not present, then try to find '_version'
	      if (osInfo == null){
	        osInfo = getPlatformNameFromFile(name, version, arch, getFileEndingWith(dir, "_version"));
	      }
	
	      // try with /etc/issue file
	      if (osInfo == null){
	        osInfo = getPlatformNameFromFile(name, version, arch, "/etc/issue");
	      }
	    }
    }

    // if nothing found yet, looks for the version info
    File fileVersion = new File("/proc/version");
    if (fileVersion.exists()) {
      if (osInfo == null){
        osInfo = getPlatformNameFromFile(name, version, arch, fileVersion.getAbsolutePath());
      }
    }

    // if nothing found store fallback as general version
    if (osInfo == null){
      osInfo = new OSDescription(name, version, arch);
    }

    this.currentOSDescription = osInfo;
  }

  private String getFileEndingWith(File dir, final String fileEndingWith) {
    File[] fileList = dir.listFiles(new FilenameFilter() {
      public boolean accept(File dir, String filename) {
        return filename.endsWith(fileEndingWith);
      }
    });
    if (fileList.length > 0) {
      return fileList[0].getAbsolutePath();
    }
    return null;
  }

  private OSDescription getPlatformNameFromLsbRelease(String name, String version, String arch) {
  	OSDescription result = null;
  	String fileName = "/etc/lsb-release";
    File f = new File(fileName);
    FileReader fileReader = null;
    BufferedReader br = null;
    if (f.exists()) {
      try {
      	fileReader = new FileReader(fileName);
        br = new BufferedReader(fileReader);
        result = readPlatformNameFromLsb(name, version, arch, br);
      } catch (IOException ex) {
      	JaspersoftStudioPlugin.getInstance().logError(ex);
      } finally {
      	FileUtils.closeStream(fileReader);
      	FileUtils.closeStream(br);
      }
    }
    return result;
  }
  
  private OSDescription getPlatformNameFromFile(final String name, final String version, final String arch, final String filename) {
    OSDescription result = null;
    if (filename != null){
	    File f = new File(filename);
	    BufferedReader br = null;
	    FileReader fileReader = null;
	    if (f.exists()) {
	      try {
	      	fileReader = new FileReader(filename);
	        br = new BufferedReader(fileReader);
	        result = readPlatformName(name, version, arch, br);
	      } catch (IOException ex) {
	      	JaspersoftStudioPlugin.getInstance().logError(ex);
	      } finally {
	      	FileUtils.closeStream(fileReader);
	      	FileUtils.closeStream(br);
	      }
	    }
    }
    return result;
  }

  private OSDescription readPlatformName(String name, String version, String arch, BufferedReader br) throws IOException {
    String line;
    String lineToReturn = null;
    int lineNb = 0;
    while ((line = br.readLine()) != null) {
      if (lineNb++ == 0) {
        lineToReturn = line;
      }
      if (line.toUpperCase().trim().startsWith(KEY_PRETTY_NAME)) {
      	return new OSDescription(line.substring(13, line.length() - 1), version, arch);
      }
    }
    return new OSDescription(lineToReturn, version, arch);
  }

  private OSDescription readPlatformNameFromLsb(String name, String version, String arch, BufferedReader br) throws IOException {
    String distribDescription = null;
    String distribCodename = null;

    String line;
    while ((line = br.readLine()) != null) {
      if (line.startsWith(KEY_LSB_DISTRIB_DESCRIPTION)){
        distribDescription = line.replace(KEY_LSB_DISTRIB_DESCRIPTION + "=", "").replace("\"", "");
      } else if (line.startsWith(KEY_LSB_DISTRIB_CODENAME)) {
      	distribCodename = line.replace(KEY_LSB_DISTRIB_CODENAME + "=", "");
      }
    }
    if (distribDescription != null && distribCodename != null) {
      return new OSDescription(distribDescription + " (" + distribCodename + ")", version, arch);
    }
    return null;
  }
}