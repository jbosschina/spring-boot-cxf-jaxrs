package io.fabric8.quickstarts.cxf.jaxrs;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Service;

@Path("/query")
@Service
public interface DataService {
    
    @GET
    @Path("")
    @Produces(MediaType.TEXT_PLAIN)
    String query();
    
    @GET
    @Path("/account/products")
    @Produces(MediaType.APPLICATION_JSON)
    List<Product> getProducts();
    
    @GET
    @Path("/marketData/stockPrices")
    @Produces(MediaType.APPLICATION_JSON)
    List<StockPrice> getStockPrices();
    
    @GET
    @Path("/stocks")
    @Produces(MediaType.APPLICATION_JSON)
    List<Stock> getStocks();

}
