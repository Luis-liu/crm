package db;

import org.h2.jdbcx.JdbcConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * All rights Reserved, Designed By HQYG
 * Copyright:   Copyright(C) 2016
 * Company:     HQYG.
 *
 * @author: liuyuansheng
 * @create: 2019-02-15 09:59
 **/
public class ConnectionPool {
    private static ConnectionPool cp = null;
    private JdbcConnectionPool jdbcCP;

    private ConnectionPool() {
        String dbPath ="./config/member";
        jdbcCP = JdbcConnectionPool.create("jdbc:h2:" + dbPath, "member", "123456");
        jdbcCP.setMaxConnections(10);
    }

    public static ConnectionPool getInstance() {
        if (cp == null) {
            cp = new ConnectionPool();
        }
        return cp;
    }

    public Connection getConnection() throws SQLException {
        return jdbcCP.getConnection();
    }
}
