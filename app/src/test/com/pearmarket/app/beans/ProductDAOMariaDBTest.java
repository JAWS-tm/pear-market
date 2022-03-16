package com.pearmarket.app.beans;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class ProductDAOMariaDBTest {
    static ProductDAO productDAO;

    @BeforeAll
    public static void initBdd() {
        productDAO = DAOFactory.getInstance().getProductDAO(DAOFactory.DBType.MariaDB);
    }

    @Test
    public void testBadFetch() {

        assertNull(productDAO.getProductById(-1));

        assertNotNull(productDAO.getProductById(1));
    }

    @Test
    public void testLimitSize() {
        assertEquals(productDAO.getProductsByCategory(1, 3).size(), 3);
    }
}