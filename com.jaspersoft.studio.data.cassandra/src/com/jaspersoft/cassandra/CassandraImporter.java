package com.jaspersoft.cassandra;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Statement;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.jaspersoft.cassandra.connection.CassandraConnection;

/**
 * 
 * @author Eric Diaz
 * 
 */
public class CassandraImporter {
    private Properties settings;

    private CassandraConnection cassandraConnection;

    private String cassandraJdbcUrl;

    private static final Logger logger = Logger.getLogger(CassandraImporter.class);

    public CassandraImporter() throws Exception {
        settings = new Properties();
        settings.load(getClass().getClassLoader().getResourceAsStream("CassandraImporter.properties"));
        cassandraJdbcUrl = settings.getProperty("com.jaspersoft.cassandra.remoteJdbcURL");
        cassandraConnection = new CassandraConnection(cassandraJdbcUrl);
    }

    protected void shutdown() {
        if (cassandraConnection != null) {
            cassandraConnection.close();
            cassandraConnection = null;
        }
    }

    public void runScript(String scriptFileName) throws Exception {
        BufferedReader reader = null;
        FileReader fileReader = null;
        Statement statement = null;
        try {
            File scriptFile = new File(scriptFileName);
            fileReader = new FileReader(scriptFile);
            reader = new BufferedReader(fileReader);
            String line;
            statement = cassandraConnection.getSqlConnection().createStatement();
            long count = 0;
            while ((line = reader.readLine()) != null) {
                statement.execute(line);
                if (++count % 100 == 0) {
                    logger.info("Lines processed: " + count);
                }
            }
            logger.info("Import done");
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (fileReader != null) {
                fileReader.close();
            }
            if (statement != null) {
                statement.close();
            }
        }
    }
}