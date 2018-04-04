package com.welch.writer;

import com.welch.conversion.Converter;
import com.welch.conversion.ConverterCreator;
import com.welch.db.SQLHelper;
import com.welch.exception.CriticalException;
import com.welch.exception.NoResultException;
import com.welch.factory.TableFactory;
import com.welch.main.Main;
import com.welch.util.PropertiesReader;
import com.welch.util.UtilsCollection;

import javax.crypto.Cipher;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by pdclwc on 09/09/2017.
 */
public class XmlGeneratorCreator {

    public static XmlGenerator createXmlGenerator(Main main) {
        XmlGenerator.testModel = main.testModel;
        XmlGenerator.groups = main.groups;
        if(main.envType.equalsIgnoreCase("LOCAL")){
            return createLocalXmlGenerator(main);
        }else if (main.envType.equalsIgnoreCase("GUI")){
            return createGuiXmlGenerator(main);
        }else{
            Main.logger.writeToLog("LM_ERROR", "Invalid environment type: " + main.envType);
            return null;
        }
    }

    private static XmlGenerator createLocalXmlGenerator(Main main) {
        try {
            SQLHelper srcSqlHelper = new SQLHelper(Main.logger, main.oraHost, main.oraPort, main.oraSid, main.oraUser, main.oraPwd);
            Converter converter = null;
            if(main.testModel){
                converter = ConverterCreator.createConverter();
            }
            TableFactory tableFactory = new TableFactory(main.logger, srcSqlHelper, converter);
            return new XmlGenerator(main, tableFactory);
        } catch (CriticalException e) {
            Main.logger.writeToLog("LM_ERROR", "Not able to create DB connection: " + main.oraSid);
            Main.logger.writeToLog("LM_ERROR", e.getMessage());
        } catch (IOException e) {
            Main.logger.writeToLog("LM_ERROR", "Not able to create xml converter for test environment: " + main.oraSid);
            Main.logger.writeToLog("LM_ERROR", e.getMessage());
        }
        return null;
    }

    private static XmlGenerator createGuiXmlGenerator(Main main) {
        SQLHelper envSqlHelper = createEnvSqlHelper(main);
        SQLHelper srcSqlHelper = createSrcSqlHelper(main, envSqlHelper);
        Converter converter = null;
        if(main.testModel){
            converter = new Converter(main.logger, main.envName, envSqlHelper);
        }
        TableFactory tableFactory = new TableFactory(main.logger, srcSqlHelper, converter);
        return new XmlGenerator(main, tableFactory);
    }

    private static SQLHelper createEnvSqlHelper(Main main) {
        try {
            PropertiesReader propertiesReader = new PropertiesReader(main.propFile, Main.logger);
            main.outputFile = propertiesReader.getProp("OUTPUTFILE").replace("${userId}",main.userId);
            String host = propertiesReader.getProp("HOSTNAME");
            String port = propertiesReader.getProp("PORT");
            String sid = propertiesReader.getProp("SID");
            String user = propertiesReader.getProp("USER");
            String pwd  = UtilsCollection.encryptOrDecrypt(propertiesReader.getProp("PASSWORD"), Cipher.DECRYPT_MODE);
            return new SQLHelper(Main.logger, host, port, sid, user, pwd);
        } catch (CriticalException e) {
            Main.logger.writeToLog("LM_ERROR", e.getMessage());
        }
        return null;
    }

    private static SQLHelper createSrcSqlHelper(Main main, SQLHelper envSqlHelper) {
        String srcSql = "Select * From Ctlm_Database where database_name = '" + main.dbName + "'";
        try {
            ResultSet rs = envSqlHelper.execMultiSelect(srcSql);
            do{
                String host = rs.getString("DATABASE_HOSTNAME");
                String port = rs.getString("DATABASE_PORT");
                String sid = rs.getString("DATABASE_URL");
                String user = rs.getString("DATABASE_USERNAME");
                String pwd = UtilsCollection.encryptOrDecrypt(rs.getString("DATABASE_PASSWORD"), Cipher.DECRYPT_MODE);
                return new SQLHelper(Main.logger, host, port, sid, user, pwd);
            }while (rs.next());
        } catch (CriticalException e) {
            Main.logger.writeToLog("LM_ERROR", e.getMessage());
        } catch (NoResultException e) {
            Main.logger.writeToLog("LM_ERROR", "No result returned from: " + srcSql);
            Main.logger.writeToLog("LM_ERROR", e.getMessage());
        }catch (SQLException e) {
            Main.logger.writeToLog("LM_ERROR", "SQL error: " + srcSql);
            Main.logger.writeToLog("LM_ERROR", e.getMessage());
        }
        return null;
    }
}
