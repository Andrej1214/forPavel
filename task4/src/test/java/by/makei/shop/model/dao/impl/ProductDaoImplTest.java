package by.makei.shop.model.dao.impl;

import by.makei.shop.exception.DaoException;
import by.makei.shop.model.connectionpool.DbConnectionPool;
import by.makei.shop.model.dao.ProductDao;
import by.makei.shop.model.entity.Product;
import org.junit.jupiter.api.*;

import java.util.Map;

class ProductDaoImplTest {
    static final int CORRECT_BRAND_ID = 1;
    static final int INCORRECT_BRAND_ID = 0;
    static final int INCORRECT_TYPE_ID = 0;
    static final int CORRECT_TYPE_ID = 1;
    static final int MIN_PRICE = 10;
    static final int MAX_PRICE = 1000;
    static final int MIN_POWER = 0;
    static final int MAX_POWER = 190;
    static final int SEARCH_FROM = 0;
    static final int SEARCH_TO = 4;
    static final String WORD = "";
    static final String ORDER_QUERY = "price ASC";
    static final int IN_STOCK = 1;
    static final int ALL_STOCK = 1;

    public static ProductDao productDao;

    @BeforeAll
    static void init() {
        productDao = ProductDaoImpl.getInstance();
    }

    @Test
    void findBySearchCorrectParamShouldReturnNotEmptyMap() throws DaoException {
        Map<Product, String> productsMap = productDao.findBySearchParam(
                CORRECT_BRAND_ID, CORRECT_TYPE_ID, MIN_PRICE, MAX_PRICE, MIN_POWER, MAX_POWER, SEARCH_FROM, SEARCH_TO,
                WORD, ORDER_QUERY, IN_STOCK);
        assert (productsMap.size() > 1);
    }

    @Test
    void findBySearchIncorrectParamShouldReturnNotEmptyMap() throws DaoException {
        Map<Product, String> productsMap = productDao.findBySearchParam(
                INCORRECT_BRAND_ID, INCORRECT_TYPE_ID, MIN_PRICE, MAX_PRICE, MIN_POWER, MAX_POWER, SEARCH_FROM,
                SEARCH_TO, WORD, ORDER_QUERY, IN_STOCK);
        assert (productsMap.size() > 1);
    }

    @Test
    void countBySearchCorrectParamShouldReturnNotZero() throws DaoException {
        int expected = productDao.countBySearchParam(
                CORRECT_BRAND_ID, CORRECT_TYPE_ID, MIN_PRICE, MAX_PRICE, MIN_POWER, MAX_POWER, WORD, IN_STOCK);
        assert (expected > 1);
    }

    @AfterAll
    static void teardown() {
        DbConnectionPool.getInstance().shutdown();
    }
}

