package by.makei.shop.connectionpool;

import by.makei.shop.model.connectionpool.DbConnectionPool;
import by.makei.shop.model.connectionpool.ProxyConnection;
import org.junit.jupiter.api.*;

import java.sql.SQLException;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//@TestMethodOrder(MethodOrderer.Random.class) //для рандомного проведения тестов, тогда @Order не нужен над методами
class DbConnectionPoolTest {
    public DbConnectionPool dbConnectionPool;

    @BeforeEach
        //если написал эту аннотацию, то аннотация @Test уже не нужна,
        //в противном случае аннотация @BeforTest не будет восприниматься
        //@Test  //поэтому закомментировал, если не прав, то верни назад))
    void getInstanceTest() {
        dbConnectionPool = DbConnectionPool.getInstance();
        assert (dbConnectionPool != null);
    }

    @Order(3)
    @Test
    void takeConnectionTest() throws SQLException {
        ProxyConnection connection = dbConnectionPool.takeConnection();
        assert (connection.isValid(100));
        dbConnectionPool.shutdown();
    }

    @Order(1)
    @Test
    void returnConnection() throws SQLException {
        ProxyConnection connection = dbConnectionPool.takeConnection();
        assert (dbConnectionPool.returnConnection(connection));
        dbConnectionPool.shutdown();
    }

    @Order(2)
    @Test
    void shutdownTest() throws SQLException {
        assert (dbConnectionPool.shutdown());
        dbConnectionPool.shutdown();
    }
}