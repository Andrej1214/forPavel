package by.makei.shop.model.dao;

import by.makei.shop.exception.DaoException;
import by.makei.shop.model.entity.Cart;
import by.makei.shop.model.entity.Order;
import by.makei.shop.model.entity.User;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserDao extends BaseDao<User>{

    Optional<User> findUserByLoginAndPassword(String login, String hashPassword) throws DaoException;

    boolean create(User user, String hashPassword) throws DaoException;

    boolean updateAccessLevel(Map<String, String> userDataMap) throws DaoException;

    boolean updatePassword(String email, String hashPassword) throws DaoException;

    boolean updateProfile(Map<String, String> userDataMap) throws DaoException;

    boolean updateMoneyAmount(int currentUserId, BigDecimal resultAmount) throws DaoException;

    boolean createOrderTransaction(User currentUser, Cart currentCart, Map<String, String> orderDataMap)
            throws DaoException;

    Map<User, double[]> findAllUsersOrdersSum() throws DaoException;

    boolean findOrderByParam(List<Order> orderList, Map<String, String> incomeParam) throws DaoException;

    boolean findOrderProductMap(int orderId, Map<Integer, Integer> productIdQuantity) throws DaoException;

    boolean cancelOrderTransaction(Order order) throws DaoException;

    boolean deliveryOrder(Order order) throws DaoException;

    boolean findOrderMapByParam(Map<Order, String[]> orderMap, Map<String, String> incomeParam) throws DaoException;
}

