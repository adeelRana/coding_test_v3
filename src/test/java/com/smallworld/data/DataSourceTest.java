package com.smallworld.data;

import com.smallworld.services.TransactionDataFetcher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;


@SpringBootTest
public class DataSourceTest {


    @Autowired
    DataSource dataSource;

    @Test
    public void loadDataTest() {
        Assert.isTrue(dataSource.loadData(), "Data Load Failed");
    }

    @Test
    public void getAllTransactionsTest() {
        Assert.notNull(dataSource.getAllTransactions(), "Data Fetch Failed");
    }

}
