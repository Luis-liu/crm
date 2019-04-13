package com.luis.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.sql.*;

/**
 * All rights Reserved, Designed By HQYG
 * Copyright:   Copyright(C) 2016
 * Company:     HQYG.
 *
 * @author: liuyuansheng
 * @create: 2019-03-12 10:06
 **/
public class DBOperater extends AbstractDB {

    private static final Logger logger = LoggerFactory.getLogger(DBOperater.class);

    /**
     * 初始化数据库
     */
    public static void initDB() {
        crateTable();
        updateTable1();
        updateTable2();
    }

    /**
     * 更新表1
     */
    public static void updateTable1() {
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = ConnectionPool.getInstance().getConnection();
            DatabaseMetaData meta = conn.getMetaData();
            ResultSet rs = meta.getColumns(null, null, "ALUMINUM_ALLOY",
                    "MATERIAL");
            if (!rs.next()) {
                stmt = conn.createStatement();
                String sql = getSql("update1.sql");
                stmt.execute(sql);
                logger.info("updateTable1 success");
            } else {
                logger.info("updateTable1 exist");
            }
        } catch (SQLException e) {
            logger.info("updateTable2 error", e);
        } finally {
            releaseConnection(conn, stmt, null);
        }
    }

    /**
     * 更新表2
     */
    public static void updateTable2() {
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = ConnectionPool.getInstance().getConnection();
            DatabaseMetaData meta = conn.getMetaData();
            ResultSet rs = meta.getColumns(null, null, "ALUMINUM_ALLOY",
                    "TYPE");
            if (!rs.next()) {
                stmt = conn.createStatement();
                String sql = getSql("update2.sql");
                stmt.execute(sql);
                logger.info("updateTable2 success");
            } else {
                logger.info("updateTable2 exist");
            }
        } catch (SQLException e) {
            logger.info("updateTable2 error", e);
        } finally {
            releaseConnection(conn, stmt, null);
        }
    }

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
                String sql = getSql("ddl.sql");
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

    private static String getSql(String fileName) {
        try {
            InputStream inputStream = CommonDao.class.getResourceAsStream("/config/" + fileName);
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes);
            String str = new String(bytes);
            return str;
        } catch (Exception e) {
            logger.error("getSql error", e);
        }
        return null;
    }
}
