/**
 *  Copyright 2005-2017 Red Hat, Inc.
 *
 *  Red Hat licenses this file to you under the Apache License, version
 *  2.0 (the "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 *  implied.  See the License for the specific language governing
 *  permissions and limitations under the License.
 */
package io.fabric8.quickstarts.cxf.jaxrs;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class JDBCUtils {
	
	public static Connection getDriverConnection(String driver, String url, String user, String pass) throws Exception {
		Class.forName(driver);
		return DriverManager.getConnection(url, user, pass); 
	}

	public static void close(Connection conn) throws SQLException {
	    close(null, null, conn);
	}
	
	public static void close(Statement stmt) throws SQLException {
	    close(null, stmt, null);
	}
	
	public static void close(ResultSet rs) throws SQLException {
	    close(rs, null, null);
    }
	
	public static void close(Statement stmt, Connection conn) throws SQLException {
	    close(null, stmt, conn);
    }
	
	public static void close(ResultSet rs, Statement stmt) throws SQLException {
	    close(rs, stmt, null);
	}
	
	public static void close(ResultSet rs, Statement stmt, Connection conn) throws SQLException {
	  
	    if (null != rs) {
            rs.close();
            rs = null;
        }
        
        if(null != stmt) {
            stmt.close();
            stmt = null;
        }
        
        if(null != conn) {
            conn.close();
            conn = null;
        }
	}
	
	public static void products(List<Product> products, Connection conn, String sql) {
	    
	    System.out.println("SQL: " + sql);
	    
	    Statement stmt = null;
        ResultSet rs = null;
        
        try {
            stmt = conn.createStatement();
            boolean hasResults = stmt.execute(sql);
            if (hasResults) {
                rs = stmt.getResultSet();
                for (;rs.next();) {
                    Integer id = rs.getInt(1);
                    String symbol = rs.getString(2);
                    String company_name = rs.getString(3);
                    products.add(new Product(id, symbol, company_name));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                close(rs, stmt, conn);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

	}
	
	public static void stockPrices(List<StockPrice> stockPrices, Connection conn, String sql) {
        
	    System.out.println("SQL: " + sql);
	    
	    Statement stmt = null;
        ResultSet rs = null;
        
        try {
            stmt = conn.createStatement();
            boolean hasResults = stmt.execute(sql);
            if (hasResults) {
                rs = stmt.getResultSet();
                for (;rs.next();) {
                    String symbol = rs.getString(1);
                    BigDecimal price = rs.getBigDecimal(2);
                    stockPrices.add(new StockPrice(symbol, price));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                close(rs, stmt, conn);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

	public static void stocks(List<Stock> stocks, Connection conn, String sql) {
        
	    System.out.println("SQL: " + sql);
	    
	    Statement stmt = null;
        ResultSet rs = null;
        
        try {
            stmt = conn.createStatement();
            boolean hasResults = stmt.execute(sql);
            if (hasResults) {
                rs = stmt.getResultSet();
                for (;rs.next();) {
                    Integer id = rs.getInt(1);
                    String symbol = rs.getString(2);
                    BigDecimal price = rs.getBigDecimal(3);
                    String company_name = rs.getString(4);
                    stocks.add(new Stock(id, symbol, price, company_name));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                close(rs, stmt, conn);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }


	
}
