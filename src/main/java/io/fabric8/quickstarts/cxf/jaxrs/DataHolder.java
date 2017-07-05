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

import static io.fabric8.quickstarts.cxf.jaxrs.JDBCUtils.*;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.teiid.core.util.Base64;

public class DataHolder {
    
    private static final String JDBC_DRIVER = "org.teiid.jdbc.TeiidDriver";
    private static final String JDBC_URL = "jdbc:teiid:Portfolio@mm://vdb-datafederation-swarm:31000;version=1";
    private static final String JDBC_USER = "teiidUser";
    private static final String JDBC_PASS = "password1!";
    
    private static String JDBC_URL_PATTERN = "jdbc:teiid:${DB_NAME}@mm://${DB_HOST}:31000;version=1";
    
    private static String SQL_PRODUCT = "SELECT * FROM Accounts.PRODUCT";
    private static String SQL_MARKETDATA = "SELECT SP.symbol, SP.price FROM (EXEC MarketData.getTextFiles('*.txt')) AS f, TEXTTABLE(f.file COLUMNS symbol string, price bigdecimal HEADER) AS SP";
    private static String SQL_STOCK = "SELECT * FROM Stock";
    
    List<Product> products = new ArrayList<>();
    List<StockPrice> stockPrices = new ArrayList<>();
    List<Stock> stocks = new ArrayList<>();
    
    public List<Product> getProducts() {
        if(products.size() == 0) {
            products(products, getConnection(), SQL_PRODUCT);
        }
//        products.add(new Product(1, "BA", "The Boeing Company"));
//        products.add(new Product(2, "MON", "Monsanto Company"));
        return products;
    }

    public List<StockPrice> getStockPrices() {
        if(stockPrices.size() == 0) {
            stockPrices(stockPrices, getConnection(), SQL_MARKETDATA);
        }
        return stockPrices;
    }
    
    public List<Stock> getStocks() {
        if(stocks.size() == 0) {
            stocks(stocks, getConnection(), SQL_STOCK);
        }
        return stocks;
    }
    
    private Connection getConnection() {
        try {
            String user = System.getProperty("DB_USERNAME", JDBC_USER);
            String password = System.getProperty("DB_PASSWORD", JDBC_PASS);
            String db = System.getProperty("DB_NAME", "Portfolio");
            String host = System.getProperty("DB_HOST", "vdb-datafederation-swarm");
            JDBC_URL_PATTERN = JDBC_URL_PATTERN.replace("${DB_NAME}", db);
            JDBC_URL_PATTERN = JDBC_URL_PATTERN.replace("${DB_HOST}", host);
            System.out.println("Create Connection[url=" + JDBC_URL_PATTERN + ", user=" + user + ", password=" + password + "]");
            return getDriverConnection(JDBC_DRIVER, JDBC_URL, JDBC_USER, JDBC_PASS);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
