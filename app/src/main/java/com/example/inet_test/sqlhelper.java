package com.example.inet_test;

import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class sqlhelper {
    Connection con;
    String uname, pass, ip, port, database, classes;

    public Connection connectionclass(){
        uname="meisterdesbieres";
        pass="Kaliumhexacyanoferrat@3";
        ip="sql-volle-pumpe.database.windows.net";
        port="1433";
        database="strichliste";
        classes = "net.sourceforge.jtds.jdbc.Driver";
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection connection=null;

        String connectionString = "jdbc:jtds:sqlserver://"+ip+":"+port+"/"+database+";ssl=require";
        String hier = "jdbc:jtds:sqlserver://sql-volle-pumpe.database.windows.net:1433;DatabaseName=volle-pumpe-bier;user=meisterdesbieres@sql-volle-pumpe;password="+pass+";hostNameInCertificate=*.database.windows.net;ssl=require";
        try {
            Class.forName(classes);
            connection = DriverManager.getConnection(hier, uname,pass);
        }
        catch (Exception ex){
            Log.e("Error ",ex.getMessage());
        }
        return connection;
    }

}
