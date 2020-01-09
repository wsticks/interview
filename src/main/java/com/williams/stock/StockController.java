package com.williams.stock;

import com.williams.stock.model.Response.StockResponse;
import com.williams.stock.model.entity.Stocks;
import com.williams.stock.model.request.UpsertStockRequest;
import com.williams.stock.service.facade.AccountFacade;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(path = "/stocks")
public class StockController {

    private  final AccountFacade accountFacade;

    public StockController(AccountFacade accountFacade) {
        Assert.notNull(accountFacade);
        this.accountFacade = accountFacade;
    }

    @Transactional
    @RequestMapping(method = RequestMethod.POST,
            consumes = "application/json",
            produces = "application/json")
    public Stocks saveStocks(UpsertStockRequest upsertStockRequest) throws Exception {
        Stocks stocks = accountFacade.createStock(upsertStockRequest);
        return stocks;
    }

    @Transactional
    @RequestMapping(method = RequestMethod.PUT,
            consumes = "application/json",
            produces = "application/json")
    public StockResponse updateStock(String stockey, UpsertStockRequest upsertStockRequest) throws Exception {
        StockResponse stockResponse = accountFacade.updateStock(stockey, upsertStockRequest);
        return stockResponse;
    }

    @Transactional
    @RequestMapping(method = RequestMethod.GET, path = "/{stocKey}",
            produces = "application/json")
    public Stocks getStock(String stockey){
        Stocks stockApiResponse = accountFacade.viewStock(stockey);
        return stockApiResponse;
    }

    @Transactional
    @RequestMapping(method = RequestMethod.GET,
            produces = "application/json")
    public List<Stocks> getAllStocks(){
        List<Stocks> stocksList = accountFacade.viewsStock();
        return stocksList;
    }


}
