package db;

import constant.SqlConstant;
import entity.Member;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * All rights Reserved, Designed By HQYG
 * Copyright:   Copyright(C) 2016
 * Company:     HQYG.
 *
 * @author: liuyuansheng
 * @create: 2019-02-15 10:21
 **/
public class CommonDao {

    /**
     * 创建表
     * @throws SQLException
     */
    public static void crateTable() {
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = ConnectionPool.getInstance().getConnection();
            DatabaseMetaData meta = conn.getMetaData();

            ResultSet rsTables = meta.getTables(null, null, "member",
                    new String[] { "TABLE" });
            if (!rsTables.next()) {
                stmt = conn.createStatement();
                String sql = getCreateSql();
                stmt.execute(sql);
                System.out.println("crateTable success");
            } else {
                System.out.println("table already exist");
            }
            rsTables.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            releaseConnection(conn, stmt, null);
        }
    }

    private static String getCreateSql() {
        try {
            File file = new File("config/ddl.sql");
            if (file.exists()) {
                String sql = FileUtils.readFileToString(file);
                return sql;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 新增数据
     * @param sql
     * @throws SQLException
     */
    public static void addInfo(String sql) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = ConnectionPool.getInstance().getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.execute();
        } finally {
            releaseConnection(conn, stmt, null);
        }
    }

    /**
     * 查询信息
     * @param sql
     * @return
     * @throws SQLException
     */
    public static List<Map<String, Object>> queryInfo(String sql)
            throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        List<Map<String, Object>> list = new ArrayList<>();
        try {
            conn = ConnectionPool.getInstance().getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            //获得结果集结构信息,元数据
            ResultSetMetaData md = rs.getMetaData();
            //获得列数
            int columnCount = md.getColumnCount();
            while (rs.next()) {
                Map<String,Object> rowData = new HashMap<String,Object>();
                for (int i = 1; i <= columnCount; i++) {
                    rowData.put(md.getColumnName(i), rs.getObject(i));
                }
                list.add(rowData);
            }
        } finally {
            releaseConnection(conn, stmt, rs);
        }
        return list;
    }

    private static void releaseConnection(Connection conn, Statement stmt, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        CommonDao.crateTable();
        Member member = new Member("张三", "18855444251");
        for (int i = 1; i < 20; i++) {
            member.setName("张三" + i);
            String sql = String.format(SqlConstant.SQL_ADD_MEMBER, member.getName(), member.getPhone(), member.getCreateDate());
            CommonDao.addInfo(sql);
        }

    }

}
