package com.welch;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2018-4-8.
 */
public class PostgreSQLJDBC {
    public static void main(String args[]) {
        String password = args[0];
        PostgreSQLJDBC postgres = new PostgreSQLJDBC();
        try(Connection connection = postgres.openConnection(password)){
            postgres.getCompany(connection).forEach(hashMap ->{
                String result = String.format("ID=%s, Name=%s, Age=%s, Address=%s, Salary=%s",
                        hashMap.get("ID"),
                        hashMap.get("Name"),
                        hashMap.get("Age"),
                        hashMap.get("Address").trim(),
                        hashMap.get("Salary")
                );
                System.out.println(result);
            });
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public Connection openConnection(String password) throws SQLException {
        DriverManager.registerDriver(new org.postgresql.Driver());
        String url = "jdbc:postgresql://localhost:5432/testdb";
        String user = "postgres";
        Connection connection = DriverManager.getConnection(url,user, password);
        return connection;
    }

    public List<HashMap<String, String>> getCompany(Connection connection){
        String sql = "select * from company";
        List list = new ArrayList<HashMap>();
        try(Statement statement = connection.createStatement(); ResultSet rs = statement.executeQuery(sql)){
            while (rs.next()){
                HashMap<String, String> hashMap = new HashMap();
                hashMap.put("ID", rs.getInt("id")+"");
                hashMap.put("Name", rs.getString("name"));
                hashMap.put("Age", rs.getInt("age")+"");
                hashMap.put("Address", rs.getString("address"));
                hashMap.put("Salary", rs.getFloat("salary")+"");
                list.add(hashMap);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
