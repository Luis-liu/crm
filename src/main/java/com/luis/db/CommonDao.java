package com.luis.db;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    private static final Logger logger = LoggerFactory.getLogger(CommonDao.class);

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

            ResultSet rs = meta.getTables(null, null, "MEMBER",
                    new String[] { "TABLE" });
            if (!rs.next()) {
                stmt = conn.createStatement();
                String sql = getCreateSql();
                stmt.execute(sql);
                logger.info("crateTable success");
            } else {
                logger.info("table already exist");
            }
            rs.close();
        } catch (SQLException e) {
            logger.error("crateTable error", e);
        } finally {
            releaseConnection(conn, stmt, null);
        }
    }

    private static String getCreateSql() {
        try {
            File file = new File("src/main/java/resources/config/ddl.sql");
            if (file.exists()) {
                String sql = FileUtils.readFileToString(file);
                return sql;
            }
        } catch (IOException e) {
            logger.error("getCreateSql error", e);
        }
        return null;
    }

    /**
     * 新增数据
     * @param sql
     * @throws SQLException
     */
    public static Integer addInfo(String sql) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int id = 0;
        try {
            conn = ConnectionPool.getInstance().getConnection();
            stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.executeUpdate();
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            while (generatedKeys.next()) {
                id = generatedKeys.getInt(1);
            }
        } finally {
            releaseConnection(conn, stmt, null);
        }
        return id;
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
            logger.error("getCreateSql error", e);
        }
    }

}
