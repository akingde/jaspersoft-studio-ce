package com.jaspersoft.studio.data.xml;

import java.io.File;
import java.sql.Connection;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import org.w3c.dom.Document;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.query.JRXPathQueryExecuterFactory;
import net.sf.jasperreports.engine.util.JRXmlUtils;

import com.jaspersoft.studio.data.DataAdapter;
import com.jaspersoft.studio.data.DataAdapterEditor;
import com.jaspersoft.studio.utils.Misc;

public class XMLDataAdapter extends DataAdapter {

	private String fileName;
	private String selectExpression;
	private boolean useConnection = false;
	private Locale locale = null;
	private String datePattern = null;
	private String numberPattern = null;
	private TimeZone timeZone = null;

	/**
	 * Create a new instance of XMLDataAdapter
	 * 
	 */
	public XMLDataAdapter() {
	}
	
	@Override
	public Connection getConnection() {
		return super.getConnection();
	}

	@Override
	public boolean isJDBCConnection() {
		return false;
	}

	@Override
	public boolean isJRDataSource() {
		return !isUseConnection();
	}

	@Override
	public Map<String, String> getProperties() {
		
		Map<String, String> map = super.getProperties();
		map.put("Filename", Misc.nvl(getFileName(), ""));
		map.put("SelectExpression", Misc.nvl(getSelectExpression(), ""));
		map.put("UseConnection", Misc.nvl("" + isUseConnection(), "false"));
		
		if (getLocale() != null) {
			map.put("Locale_language", Misc.nvl(getLocale().getLanguage(), ""));
			map.put("Locale_country", Misc.nvl(getLocale().getCountry(), ""));
			map.put("Locale_variant", Misc.nvl(getLocale().getVariant(), ""));
		}
		
		map.put("DatePattern", Misc.nvl(getDatePattern(), ""));
		map.put("NumberPattern", Misc.nvl(getNumberPattern(), ""));
		
		if (getTimeZone() != null) {
			map.put("timeZone", Misc.nvl(getTimeZone().getID(), ""));
		}
		
		return map;
	}

	@Override
	public void loadProperties(Map<String, String> map) {
		
		setFileName( (String)map.get("Filename") );
		setSelectExpression( (String)map.get("SelectExpression") );
		setUseConnection( Boolean.valueOf(Misc.nvl(map.get("UseConnection"), "false")).booleanValue() );
		
		String language = (String)map.get("Locale_language");
		String country = (String)map.get("Locale_country");
		String variant = (String)map.get("Locale_variant");
		if (language != null && language.trim().length() > 0) {
			if (country != null && language.trim().length() > 0) {
				if (variant != null && variant.trim().length() > 0) {
					setLocale( new Locale(language, country, variant) );
				} else {
					setLocale( new Locale(language, country) );
				}
			} else {
				setLocale( new Locale(language) );
			}
		}
		
		String datePatternValue = (String)map.get("DatePattern");
		if (datePatternValue != null && datePatternValue.trim().length() >0) {
			setDatePattern(datePatternValue);
		}
		
		String numberPatternvalue = (String)map.get("NumberPattern");
		if (numberPatternvalue != null && numberPatternvalue.trim().length() > 0) {
			setNumberPattern(numberPatternvalue);
		}
		
		String timeZoneId = (String)map.get("timeZone");
		if (timeZoneId != null && timeZoneId.trim().length() > 0) {
			setTimeZone( TimeZone.getTimeZone(timeZoneId) );
		}
	}

	@Override
	public JRDataSource getJRDataSource() {
		try {
			net.sf.jasperreports.engine.data.JRXmlDataSource ds = new net.sf.jasperreports.engine.data.JRXmlDataSource(fileName, getSelectExpression() ); 
	           
	        if (getLocale()!=null) {
	        	ds.setLocale( getLocale());
	        }
	           
	        if (getTimeZone() != null) {
	        	ds.setTimeZone( getTimeZone());
	        }
	        
	        if (getDatePattern() != null && getDatePattern().trim().length()>0) {
	        	ds.setDatePattern( getDatePattern());
	        }
	           
	        if (getNumberPattern() != null && getNumberPattern().trim().length()>0) {
	        	ds.setNumberPattern( getNumberPattern());
	        }
	           
	        return ds; 
		} catch (Exception e) {
			return null;
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Map getSpecialParameters(Map map) throws JRException {
		if (isUseConnection()) {
            
			/*
            if (this.getFilename().toLowerCase().startsWith("https://") ||
                this.getFilename().toLowerCase().startsWith("http://") ||
                this.getFilename().toLowerCase().startsWith("file:"))
            {
                map.put(JRXPathQueryExecuterFactory.XML_URL, this.getFilename());
            }
            else
            {
            */
                Document document = JRXmlUtils.parse( new File(getFileName()) );
                map.put(JRXPathQueryExecuterFactory.PARAMETER_XML_DATA_DOCUMENT, document);
            //}
            
            
            if (getLocale() != null) {
            	map.put(JRXPathQueryExecuterFactory.XML_LOCALE, getLocale());
            }
           
            if (getTimeZone() != null) {
                map.put(JRXPathQueryExecuterFactory.XML_TIME_ZONE, getTimeZone());
            }
           
            if (getDatePattern() != null && getDatePattern().trim().length() > 0) {
               map.put(JRXPathQueryExecuterFactory.XML_DATE_PATTERN, getDatePattern());
            }
           
            if (getNumberPattern() != null && getNumberPattern().trim().length() > 0) {
               map.put(JRXPathQueryExecuterFactory.XML_NUMBER_PATTERN, getNumberPattern());
            }    
        }
        return map;
	}

	@Override
	// TODO implement test function
	public void test() throws Exception {
		try {
			java.io.File f = new java.io.File(getFileName());
			if (!f.exists()) {
				/*JOptionPane.showMessageDialog(Misc.getMainWindow(),
                        Misc.formatString( //"messages.connectionDialog.fileNotFound",
                        "File {0} not found", new Object[]{this.getFilename()}),
                        "Error",JOptionPane.ERROR_MESSAGE);
                throw new Exception();*/
			}
			/*JOptionPane.showMessageDialog(Misc.getMainWindow(),
                    //I18n.getString("messages.connectionDialog.connectionTestSuccessful",
                    "Connection test successful!","",JOptionPane.INFORMATION_MESSAGE);*/
            return;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public DataAdapterEditor getEditor() {
		return new XMLDataAdapterEditor();
	}

	// GETTERS AND SETTERS
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public String getSelectExpression() {
		return selectExpression;
	}

	public void setSelectExpression(String selectExpression) {
		this.selectExpression = selectExpression;
	}
	
	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public String getDatePattern() {
		return datePattern;
	}

	public void setDatePattern(String datePattern) {
		this.datePattern = datePattern;
	}

	public String getNumberPattern() {
		return numberPattern;
	}

	public void setNumberPattern(String numberPattern) {
		this.numberPattern = numberPattern;
	}

	public TimeZone getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(TimeZone timeZone) {
		this.timeZone = timeZone;
	}

	public boolean isUseConnection() {
		return useConnection;
	}
	
	public void setUseConnection(boolean useConnection) {
        this.useConnection = useConnection;
    }
}
