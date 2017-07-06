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

import java.util.List;

import io.swagger.annotations.Api;

@Api("/query")
public class DataServiceImpl implements DataService {

    public String query() {
        System.out.println("/rest/query");
        return "This is VDB Query Rest Service";
    }
    
    DataHolder dataHolder = new DataHolder();

    @Override
    public List<Product> getProducts() {
        System.out.println("/rest/query/account/products");
        return dataHolder.getProducts();
    }

    @Override
    public List<StockPrice> getStockPrices() {
        System.out.println("/rest/query/marketData/stockPrices");
        return dataHolder.getStockPrices();
    }

    @Override
    public List<Stock> getStocks() {
        System.out.println("/rest/query/stocks");
        return dataHolder.getStocks();
    }
}
