/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.statistics;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.InetAddress;
import java.net.URI;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.regex.Pattern;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.FileUtils;
import net.sf.jasperreports.eclipse.util.HttpUtils;

import org.apache.commons.io.IOUtils;
import org.apache.commons.net.ntp.NTPUDPClient;
import org.apache.commons.net.ntp.TimeInfo;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.osgi.framework.Bundle;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jaspersoft.studio.ConfigurationManager;
import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.preferences.StudioPreferencePage;
import com.jaspersoft.studio.preferences.util.PropertiesHelper;
import com.jaspersoft.studio.statistics.heartbeat.Heartbeat;
import com.jaspersoft.studio.utils.ModelUtils;

/**
 * Manager used to handle, track and send to the server informations about an installation of jaspersoft studio, like
 * usage statistics
 * 
 * @author Orlandin Marco
 * 
 */
public class UsageManager {

	/**
	 * The string used when the current version can not be identified
	 */
	protected static final String UNKWNOW_VERSION = "Unknown"; //$NON-NLS-1$;

	/**
	 * The current used version of Jaspersfot Studio. It is initialized the first time is requested trough the appropriate
	 * method
	 */
	private static String CURRENT_VERSION = null;

	/**
	 * URL of the server where the statistics are sent
	 */
	private static final String STATISTICS_SERVER_URL = "http://heartbeat.jaspersoft.com/heartbeat/jss/statistics";//$NON-NLS-1$ //http://192.168.2.101/sf/statistics.php

	/**
	 * URL of the server where the heartbeat is sent
	 */
	private static final String HEARTBEAT_SERVER_URL = "http://jasperstudio.sf.net/jsslastversion.php";//$NON-NLS-1$

	/**
	 * Property name used in the preferences in the old version of Jaspersoft Studio to store a UUID of the application.
	 * This is used only for backward compatibility since the newer versions store the file inside an application folder
	 */
	private static final String BACKWARD_UUID_PROPERTY = "UUID"; //$NON-NLS-1$

	/**
	 * Time in ms that the process to write the statistics from the memory on the disk wait after the update of a value.
	 * This is done since some operations can update many values, doing this there is a time span to allow sequence of
	 * values to be written at one, minimizing the number of writes on the disk
	 */
	private static final int MINIMUM_WAIT_TIME = 5000;

	/**
	 * The URL of the server used to get the current hour and to check the Internet connection
	 */
	private static final String TIME_SERVER = "pool.ntp.org"; //$NON-NLS-1$

	/**
	 * Name of the file where the usage statistics are saved
	 */
	private static final String PROPERTIES_FILE_NAME = "jss.properties"; //$NON-NLS-1$

	/**
	 * Name of the file where the information on the installation are saved
	 */
	private static final String INFO_FILE_NAME = "info.properties"; //$NON-NLS-1$

	/**
	 * Information property key to set a lock when JSS is started and removed it when it is closed. Using this is possible
	 * to know when JSS was closed abnormally
	 */
	private static final String LOCK_INFO = "isLocked"; //$NON-NLS-1$

	/**
	 * Information property key used to know the last time that the usage statistics was sent to the server, it is used to
	 * calculate if the amount of time for the next upload is passed
	 */
	private static final String TIMESTAMP_INFO = "lastStatsTimestamp"; //$NON-NLS-1$

	/**
	 * Information property key used to store the version of JSS before an update. It is used to know if since the last
	 * startup it was done an update or if it is a new installation
	 */
	private static final String VERSION_INFO = "lastSubmittedVersion"; //$NON-NLS-1$

	/**
	 * Used inside the statistics properties file keys, to separate a name from it's category
	 */
	private static final String ID_CATEGORY_SEPARATOR = "|"; //$NON-NLS-1$

	/**
	 * Flag used to know if the usage collection is allowed or not
	 */
	private boolean allowUsageCollection = false;

	/**
	 * Properties file of the usage statistics, it is loaded the first time it is requested
	 */
	private Properties usageStats = null;

	/**
	 * Properties file of the installation informations, it is loaded the first time it is requested
	 */
	private Properties installationInfo = null;

	/**
	 * Flag used when a statistic is updated, used by the upload job to wait a certain amount of time since the last
	 * update to save the statistics properties file on the disk
	 */
	private boolean statisticUpdatedRecently = false;

	/**
	 * Job used to write the statistics properties file on the disk
	 */
	private Job writeStatsToDisk = new WriteUsageJob();

	/**
	 * The folder where all the statistics file are stored, this folder is different for each JSS installation
	 */
	private File statisticsFolder = ConfigurationManager.getAppDataFolder("Statistics");

	/**
	 * This job write the statistics properties file when it is changed. But it wait at least a specific amount of time
	 * since the last update to minimize the number of write on the disk
	 * 
	 * @author Orlandin Marco
	 * 
	 */
	private class WriteUsageJob extends Job {

		public WriteUsageJob() {
			super(Messages.UsageManager_writeJobName);
		}

		@Override
		protected IStatus run(IProgressMonitor monitor) {
			while (statisticUpdatedRecently) {
				try {
					Thread.sleep(MINIMUM_WAIT_TIME);
				} catch (Exception ex) {

				}
				statisticUpdatedRecently = false;
			}
			// At this point AT LEAST the MINIMUM_WAIT_TIME is passed
			if (!monitor.isCanceled()) {
				writeUsageStatisticsOnDisk();
			}
			monitor.done();
			return Status.OK_STATUS;
		}
	}

	/**
	 * Method used to write in the statistics file. It is thread safe to avoid concurrent access exception
	 * 
	 * @param key
	 *          the key to write
	 * @param value
	 *          the value to write
	 */
	protected void setProperty(String key, String value) {
		synchronized (UsageManager.this) {
			getStatisticsContainer().setProperty(key, value);
		}
	}

	/**
	 * Method used to read from the statistics file. It is thread safe to avoid concurrent access exception
	 * 
	 * @param key
	 *          the key of the value to read
	 * @return the valued associated with the key, can be null;
	 */
	private String getProperty(String key) {
		synchronized (UsageManager.this) {
			return getStatisticsContainer().getProperty(key);
		}
	}

	/**
	 * Write the statistics properties file on the disk
	 */
	private void writeUsageStatisticsOnDisk() {
		FileOutputStream out = null;
		synchronized (UsageManager.this) {
			try {
				File propertiesFile = new File(statisticsFolder, PROPERTIES_FILE_NAME);
				out = new FileOutputStream(propertiesFile.getAbsolutePath());
				getStatisticsContainer().store(out, "Usage informations"); //$NON-NLS-1$
			} catch (Exception ex) {
				ex.printStackTrace();
				JaspersoftStudioPlugin.getInstance().logError(Messages.UsageManager_errorWriteStatProperties, ex);
			} finally {
				FileUtils.closeStream(out);
			}
		}
	}

	/**
	 * Listen for changes in the preferences for property that enable the collecting of usage informations
	 */
	private IPropertyChangeListener preferencesListener = new IPropertyChangeListener() {

		@Override
		public void propertyChange(org.eclipse.jface.util.PropertyChangeEvent event) {
			if (event.getProperty().equals(StudioPreferencePage.JSS_SEND_USAGE_STATISTICS)) {
				allowUsageCollection = JaspersoftStudioPlugin.getInstance().getPreferenceStore()
						.getBoolean(StudioPreferencePage.JSS_SEND_USAGE_STATISTICS);
			}
		}
	};

	/**
	 * Return the current JSS version. The value is cached after the first time is request
	 * 
	 * @return a not null string representing the JSS version
	 */
	protected String getVersion() {
		if (CURRENT_VERSION == null) {
			CURRENT_VERSION = JaspersoftStudioPlugin.getInstance().getBundle().getVersion().toString();
			// check if it is a development version
			if (CURRENT_VERSION.endsWith(".qualifier")) {
				CURRENT_VERSION = CURRENT_VERSION.substring(0, CURRENT_VERSION.lastIndexOf(".qualifier")) + "-dev";
			}
			// check if it is a pro
			if (isPro()) {
				CURRENT_VERSION += "-pro";
			}
		}
		return CURRENT_VERSION;
	}

	/**
	 * Return the current eclipse version and product name
	 * 
	 * @return the eclipse version
	 */
	protected String getEclipseVersion() {
		try {
			String pluginId = Platform.getProduct().getId();
			Bundle bundle = Platform.getBundle("org.eclipse.platform");
			String eclipseVersion = "";
			if (bundle != null) {
				eclipseVersion = bundle.getVersion().toString();
			}
			if (eclipseVersion != null && !eclipseVersion.isEmpty()) {
				return pluginId + "[" + eclipseVersion + "]";
			} else {
				return pluginId + "[Unknown Version]";
			}
		} catch (Exception ex) {
			JaspersoftStudioPlugin.getInstance().logError(ex);
		}
		return UNKWNOW_VERSION;
	}

	/**
	 * Contact an NTP server to get the current time
	 * 
	 * @return the number of milliseconds since January 1, 1970, 00:00:00 GMT represented by this date.
	 * @throws Exception
	 *           throw an exception if the time can not be fetched
	 */
	protected long getCurrentTime() throws Exception {
		NTPUDPClient timeClient = new NTPUDPClient();
		InetAddress inetAddress = InetAddress.getByName(TIME_SERVER);
		TimeInfo timeInfo = timeClient.getTime(inetAddress);
		long returnTime = timeInfo.getMessage().getTransmitTimeStamp().getTime();
		Date time = new Date(returnTime);
		return time.getTime();
	}

	/**
	 * Check if the amount of time since the last statistics upload is passed (7 days) and so if it is time to re-send the
	 * statistics to the server
	 * 
	 * @return true if the amount of time for send the statistics to the server is passed false otherwise
	 */
	protected boolean checkUpload() {
		try {
			long actualMillis = getCurrentTime();
			String lastUpdate = getInstallationInfoContainer().getProperty(TIMESTAMP_INFO);
			if (lastUpdate == null) {
				// First time the check is done, write the current time on the file, as starting point
				setInstallationInfo(TIMESTAMP_INFO, String.valueOf(actualMillis));
			} else {
				long millisDiff = actualMillis - Long.parseLong(lastUpdate);
				int days = (int) (millisDiff / 86400000);
				// int minutes = (int) (millisDiff / 60000);
				// System.out.println("passed "+ minutes + "minutes since the last strartup ");
				// return minutes > 5;
				return days >= 7;
			}
		} catch (Exception ex) {
			JaspersoftStudioPlugin.getInstance().logWarning(Messages.UsageManager_errorGetTime, ex);
		}
		return false;
	}

	/**
	 * Get the usage statistics properties file. If it was already loaded then it is returned. If not it was loaded from
	 * the existing file. If the file dosen't exist a new properties file is created (it will be written on the disk as
	 * the first statistic of the section is provided)
	 * 
	 * @return a not null properties file for the statistics
	 */
	protected Properties getStatisticsContainer() {
		synchronized (UsageManager.this) {
			if (usageStats == null) {
				File propertiesFile = new File(statisticsFolder, PROPERTIES_FILE_NAME);
				if (propertiesFile.exists()) {
					FileInputStream input = null;
					try {
						input = new FileInputStream(propertiesFile.getAbsolutePath());
						usageStats = new Properties();
						usageStats.load(input);
					} catch (Exception e) {
						e.printStackTrace();
						JaspersoftStudioPlugin.getInstance().logError(Messages.UsageManager_errorReadStatProperties, e);
					} finally {
						FileUtils.closeStream(input);
					}
				} else {
					usageStats = new Properties();
				}
			}
		}
		return usageStats;
	}

	/**
	 * Get the installation information properties file. If it was already loaded then it is returned. If not it was
	 * loaded from the existing file. If the file dosen't exist a new properties file is created (it will be written on
	 * the disk as the first information is provided)
	 * 
	 * @return a not null properties file for the installation informations
	 */
	protected Properties getInstallationInfoContainer() {
		synchronized (UsageManager.this) {
			if (installationInfo == null) {
				File propertiesFile = new File(statisticsFolder, INFO_FILE_NAME);
				if (propertiesFile.exists()) {
					FileInputStream input = null;
					try {
						input = new FileInputStream(propertiesFile.getAbsolutePath());
						installationInfo = new Properties();
						installationInfo.load(input);
					} catch (Exception e) {
						e.printStackTrace();
						JaspersoftStudioPlugin.getInstance().logError(Messages.UsageManager_errorReadInfoProperties, e);
					} finally {
						FileUtils.closeStream(input);
					}
				} else {
					installationInfo = new Properties();
				}
			}
			return installationInfo;
		}
	}

	/**
	 * Write a property on the installation informations properties file. The property is written only if there isn't a
	 * property with the same key and value already save. After and if the property is written the file is saved in the
	 * disk
	 * 
	 * @param propertyName
	 *          the name of the property to write, must be not null
	 * @param newValue
	 *          the value of the property to write
	 */
	protected void setInstallationInfo(String propertyName, String newValue) {
		synchronized (UsageManager.this) {
			Properties info = getInstallationInfoContainer();
			String value = info.getProperty(propertyName);
			boolean equals = value == null ? newValue == null : value.equals(newValue);
			if (!equals) {
				FileOutputStream out = null;
				try {
					// Write the property only if there isn't a previous one with the same value
					info.setProperty(propertyName, newValue);
					File propertiesFile = new File(statisticsFolder, INFO_FILE_NAME);
					out = new FileOutputStream(propertiesFile.getAbsolutePath());
					info.store(
							out,
							"Installation information - This information are NEVER send to the statistics server, used only locally for configuration purpose"); //$NON-NLS-1$
				} catch (Exception ex) {
					ex.printStackTrace();
					JaspersoftStudioPlugin.getInstance().logError(Messages.UsageManager_errorWriteInfoProperties, ex);
				} finally {
					FileUtils.closeStream(out);
				}
			}
		}
	}

	/**
	 * Send the statistics to the defined server. They are read from the properties filed and converted into a JSON
	 * string. Then this string is sent to the server as a post parameter named data
	 */
	protected void sendStatistics() {
		BufferedReader responseReader = null;
		try {
			if (!STATISTICS_SERVER_URL.trim().isEmpty()) {
				//Set the proxy information if any
				Executor exec = Executor.newInstance();
				URI fullURI = new URI(STATISTICS_SERVER_URL);
				HttpUtils.setupProxy(exec, fullURI);
				HttpHost proxy = HttpUtils.getUnauthProxy(exec, fullURI);
				Request req = Request.Post(STATISTICS_SERVER_URL);
				req.addHeader("User-Agent", "Mozilla/5.0");
				req.addHeader("Accept-Language", "en-US,en;q=0.5");
				if (proxy != null)
					req.viaProxy(proxy);

				// Read and convert the statistics into a JSON string
				UsagesContainer container = new UsagesContainer(ConfigurationManager.getInstallationUUID());
				boolean fileChanged = false;
				synchronized (UsageManager.this) {
					Properties prop = getStatisticsContainer();
					for (Object key : new ArrayList<Object>(prop.keySet())) {
						try {
							String[] id_category = key.toString().split(Pattern.quote(ID_CATEGORY_SEPARATOR));
							String value = prop.getProperty(key.toString(), "0");
							int usageNumber = Integer.parseInt(value); //$NON-NLS-1$
							String version = getVersion();
							// Check if the id contains the version
							if (id_category.length == 3) {
								version = id_category[2];
							} else {
								// Old structure, remove the old entry and insert the new fixed one
								// this is a really limit case and should almost never happen
								prop.remove(key);
								String fixed_key = id_category[0] + ID_CATEGORY_SEPARATOR + id_category[1] + ID_CATEGORY_SEPARATOR
										+ version;
								prop.setProperty(fixed_key, value);
								fileChanged = true;
							}
							container.addStat(new UsageStatistic(id_category[0], id_category[1], version, usageNumber));
						} catch (Exception ex) {
							// if a key is invalid remove it
							ex.printStackTrace();
							prop.remove(key);
							fileChanged = true;
						}
					}
				}
				if (fileChanged) {
					// The statistics file was changed, maybe a fix or an invalid property removed
					// write it corrected on the disk
					writeStatsToDisk.cancel();
					writeStatsToDisk.setPriority(Job.SHORT);
					writeStatsToDisk.schedule(MINIMUM_WAIT_TIME);
				}

				ObjectMapper mapper = new ObjectMapper();
				String serializedData = mapper.writeValueAsString(container);

				// Send post request with the JSON string as the data parameter
				req.bodyForm(Form.form().add("data", serializedData).build());//$NON-NLS-1$
				
				Response resp = req.execute();
				
				HttpResponse response = resp.returnResponse();
				StatusLine statusLine = response.getStatusLine();
				HttpEntity entity = response.getEntity();
				int responseCode = statusLine.getStatusCode();

				responseReader = new BufferedReader(new InputStreamReader(entity.getContent()));
				String inputLine;
				StringBuffer textResponse = new StringBuffer();
				while ((inputLine = responseReader.readLine()) != null) {
					textResponse.append(inputLine);
				}		
						
				// Update the upload time
				if (responseCode == 200 && ModelUtils.safeEquals(textResponse.toString(), "ok")) {
					setInstallationInfo(TIMESTAMP_INFO, String.valueOf(getCurrentTime()));
				} else {
					// print result
					System.out.println("Response error: " + textResponse.toString());
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			JaspersoftStudioPlugin.getInstance().logError(Messages.UsageManager_errorStatUpload, ex);
		} finally {
			FileUtils.closeStream(responseReader);
		}
	}

	/**
	 * Check if the running JSS is a community or pro version. This is done looking for the plugin
	 * com.jaspersoft.studio.pro.doc
	 * 
	 * @return true if the current running JSS is a pro version, false otherwise
	 */
	protected boolean isPro() {
		return Platform.getBundle("com.jaspersoft.studio.pro.doc") != null; //$NON-NLS-1$
	}

	protected void moveStatisticsToFolder() {
		File applicationFolder = statisticsFolder.getParentFile();
		File infoFile = new File(applicationFolder, INFO_FILE_NAME);
		if (infoFile.exists()) {
			// Need to move the file into the new folder
			try {
				org.apache.commons.io.FileUtils.moveFileToDirectory(infoFile, statisticsFolder, true);
			} catch (Exception ex) {
				ex.printStackTrace();
				JaspersoftStudioPlugin.getInstance().logError(ex);
			} finally {
				infoFile.delete();
			}
		}
		File statisticsFile = new File(applicationFolder, PROPERTIES_FILE_NAME);
		if (statisticsFile.exists()) {
			// Need to move the file into the new folder
			try {
				org.apache.commons.io.FileUtils.moveFileToDirectory(statisticsFile, statisticsFolder, true);
			} catch (Exception ex) {
				ex.printStackTrace();
				JaspersoftStudioPlugin.getInstance().logError(ex);
			} finally {
				statisticsFile.delete();
			}
		}
	}

	/**
	 * Method called to start the UsageManager, it will check if the usage statistics must be uploaded and and it check
	 * for newer version. all is done inside separated Jobs and if the relative flags on the preferences are enabled. It
	 * create also the lock attribute to find used to find if JSS was closed normally, but only if the collecting of usage
	 * statistics is enabled
	 */
	public void start() {
		// Move the statistics on the new storage structure
		moveStatisticsToFolder();
		// Check if the collecting of statistics is enabled
		allowUsageCollection = JaspersoftStudioPlugin.getInstance().getPreferenceStore()
				.getBoolean(StudioPreferencePage.JSS_SEND_USAGE_STATISTICS);
		//statistics disabled for server shutdown
		allowUsageCollection = false;
		JaspersoftStudioPlugin.getInstance().getPreferenceStore().addPropertyChangeListener(preferencesListener);
		if (allowUsageCollection) {
			Job uploadUsageStats = new Job(Messages.UsageManager_uploadJobName) {
				@Override
				protected IStatus run(IProgressMonitor monitor) {
					boolean isLocekd = Boolean.parseBoolean(getInstallationInfoContainer().getProperty(LOCK_INFO,
							Boolean.FALSE.toString()));
					if (isLocekd) {
						audit(UsageStatisticsIDs.CRASH_ID, UsageStatisticsIDs.CATEGORY_ERROR);
					} else {
						try {
							setInstallationInfo(LOCK_INFO, Boolean.TRUE.toString());
						} catch (Exception ex) {
						}
					}
					if (checkUpload()) {
						sendStatistics();
					}
					return Status.OK_STATUS;
				}
			};
			uploadUsageStats.setPriority(Job.LONG);
			uploadUsageStats.schedule();
		}
		// Check for update
		if (!UIUtils.isDevMode()) { //$NON-NLS-1$
			Job job = new Job(Messages.UsageManager_checkVersionJobName) {
				@Override
				protected IStatus run(IProgressMonitor monitor) {
					Heartbeat.run();
					return Status.OK_STATUS;
				}

			};
			job.setSystem(true);
			job.schedule();
		}
	}

	/**
	 * Called when the application is closed. Remove the lock attribute if present and write the statistics properties
	 * file on the disk if it has some pending changes
	 */
	public void stop() {
		setInstallationInfo(LOCK_INFO, Boolean.FALSE.toString());
		writeStatsToDisk.cancel();
		if (statisticUpdatedRecently) {
			writeUsageStatisticsOnDisk();
		}
	}

	/**
	 * Log a statistic of a specific action. It will increment the counter for that action. The id of the action is done
	 * by concatenating the name of the action, its category and the version of JSS when the action was used. Doing this
	 * we have how many times an action was used with a specific version
	 * 
	 * @param used_action_id
	 *          the name of the action, must not contains the separator char (default |)
	 * @param cateogory
	 *          the category of the statistics, must not contains the separator char (default |)
	 */
	public void audit(String used_action_id, String cateogory) {
		synchronized (UsageManager.this) {
			if (allowUsageCollection) {
				// Check the separator is not used in the action
				String errorMessage = MessageFormat.format(Messages.UsageManager_errorSepratorReserved,
						new Object[] { ID_CATEGORY_SEPARATOR });
				Assert.isTrue(!used_action_id.contains(ID_CATEGORY_SEPARATOR) && !cateogory.contains(ID_CATEGORY_SEPARATOR),
						errorMessage);
				String id = used_action_id + ID_CATEGORY_SEPARATOR + cateogory + ID_CATEGORY_SEPARATOR + getVersion();
				String textUsageNumber = getProperty(id);
				if (textUsageNumber == null)
					textUsageNumber = "0"; //$NON-NLS-1$
				int usageNumber = 0;
				try {
					usageNumber = Integer.parseInt(textUsageNumber);
				} catch (Exception ex) {
					usageNumber = 0;
				}
				usageNumber++;
				setProperty(id, String.valueOf(usageNumber));
				statisticUpdatedRecently = true;
				writeStatsToDisk.cancel();
				writeStatsToDisk.setPriority(Job.SHORT);
				writeStatsToDisk.schedule();
			}
		}
	}

	/**
	 * Set the statistic of a specific action. It will set the amount of time it was used to a specific value. The id of
	 * the action is done by concatenating the name of the action and its category
	 * 
	 * @param used_action_id
	 *          the name of the action, must not contains the separator char (default |)
	 * @param cateogory
	 *          the category of the statistics, must not contains the separator char (default |)
	 * @param usageNumber
	 *          set the number of times that the specified action was used
	 */
	public void audit_set(String used_action_id, String cateogory, int usageNumber) {
		synchronized (this) {
			if (allowUsageCollection) {
				// Check the separator is not used in the action
				String errorMessage = MessageFormat.format(Messages.UsageManager_errorSepratorReserved,
						new Object[] { ID_CATEGORY_SEPARATOR });
				Assert.isTrue(!used_action_id.contains(ID_CATEGORY_SEPARATOR) && !cateogory.contains(ID_CATEGORY_SEPARATOR),
						errorMessage);
				String id = used_action_id + ID_CATEGORY_SEPARATOR + cateogory + ID_CATEGORY_SEPARATOR + getVersion();
				setProperty(id, String.valueOf(usageNumber));
				statisticUpdatedRecently = true;
				writeStatsToDisk.cancel();
				writeStatsToDisk.setPriority(Job.SHORT);
				writeStatsToDisk.schedule(MINIMUM_WAIT_TIME);
			}
		}
	}

	/**
	 * Check on the server if there is a newer version of Jaspersoft Studio
	 * 
	 * @return a not null VersionCheckResult that contains information on the new version and if there is a new version
	 */
	public VersionCheckResult checkVersion() {
		String uuid = ConfigurationManager.getInstallationUUID();
		String versionKnownByTheStats = getInstallationInfoContainer().getProperty(VERSION_INFO);
		int newInstallation = 0;
		// Read if there is an UUID in the preferences used to track the older versions
		PropertiesHelper ph = PropertiesHelper.getInstance();
		String backward_uuid = ph.getString(BACKWARD_UUID_PROPERTY, null);
		if (backward_uuid == null) {
			// If the backward value is null then i'm already using the new system, check if it
			// is a new installation or an update
			if (versionKnownByTheStats == null) {
				// Since the last version was not yet initialized it is a new installation
				newInstallation = 1;
			} else if (!versionKnownByTheStats.equals(getVersion())) {
				// There is a version stored in the file, that is the last version known by the server, if it is
				// different from the real version then there were an update
				newInstallation = 2;
			}
		} else {
			// If the backward value is != null then it isn't for sure a new installation, maybe there were
			// but since i'm inside the new code then it should be an update.
			newInstallation = 2;
			setInstallationInfo(VERSION_INFO, getVersion());
		}
		//Log the current OS
		String OS = OSIdentifier.getOSInfo();
		audit_set(OS, UsageStatisticsIDs.CATEGORY_OPERATIVE_SYSTEM, 1);
		StringBuilder urlBuilder = new StringBuilder();
		urlBuilder.append(HEARTBEAT_SERVER_URL);
		urlBuilder.append("?version=");//$NON-NLS-1$
		urlBuilder.append(getVersion());
		urlBuilder.append("&uuid=");//$NON-NLS-1$
		urlBuilder.append(uuid);
		urlBuilder.append("&new=");//$NON-NLS-1$
		urlBuilder.append(newInstallation);
		urlBuilder.append("&isRCP=");//$NON-NLS-1$
		boolean isRCP = JaspersoftStudioPlugin.isRCP();
		urlBuilder.append(String.valueOf(isRCP));
		// if it is the plugin version send also the eclipse version
		if (!isRCP) {
			urlBuilder.append("&eclipse_version=");
			urlBuilder.append(getEclipseVersion());
		}

		// get the java version
		String javaVersion = System.getProperty("java.runtime.version");
		if (javaVersion != null) {
			urlBuilder.append("&java_version=");
			urlBuilder.append(javaVersion);
		}

		String urlstr = urlBuilder.toString();
		System.out.println("Invoking URL: " + urlstr); //$NON-NLS-1$  
		try {
			Executor exec = Executor.newInstance();
			URI fullURI = new URI(urlstr);
			HttpUtils.setupProxy(exec, fullURI);
			HttpHost proxy = HttpUtils.getUnauthProxy(exec, fullURI);
			Request req = Request.Get(urlstr);
			if (proxy != null)
				req.viaProxy(proxy);
			HttpResponse resp = exec.execute(req).returnResponse();
			int statusCode = resp.getStatusLine().getStatusCode() ;
			if (statusCode == 200) {
				
				// Update the installation info only if the informations was given correctly to the server
				setInstallationInfo(VERSION_INFO, getVersion());
				// Remove the old backward compatibility value if present to switch to the new system
				if (backward_uuid != null) {
					ph.removeString(BACKWARD_UUID_PROPERTY, InstanceScope.SCOPE);
				}
				
				String response = IOUtils.toString(resp.getEntity().getContent());
				String serverVersion = null;
				String optmsg = ""; //$NON-NLS-1$ 
				for (String inputLine : IOUtils.readLines(new StringReader(response))) {
					if (serverVersion == null) {
						serverVersion = inputLine.trim();
					} else {
						optmsg += inputLine;
					}
				}
				return new VersionCheckResult(serverVersion, optmsg, getVersion());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			JaspersoftStudioPlugin.getInstance().logError(Messages.UsageManager_errorUpdateCheck, ex);
		}
		return new VersionCheckResult();
	}
}
