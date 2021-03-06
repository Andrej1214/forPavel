package by.makei.shop.command.impl.user;

import by.makei.shop.command.AttributeName;
import by.makei.shop.command.Command;
import by.makei.shop.command.RedirectMessage;
import by.makei.shop.command.Router;
import by.makei.shop.exception.CommandException;
import by.makei.shop.exception.ServiceException;
import by.makei.shop.model.entity.AccessLevel;
import by.makei.shop.model.entity.User;
import by.makei.shop.model.service.UserService;
import by.makei.shop.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.Level;

import java.util.Optional;

import static by.makei.shop.command.PagePath.GO_TO_SHOW_ORDERS;

public class CancelOrderCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        UserService userService = UserServiceImpl.getInstance();
        HttpSession session = request.getSession();

        try {
            if (userService.cancelOrder(request)) {
                User user = (User) session.getAttribute(AttributeName.USER);
                if (user.getAccessLevel().equals(AccessLevel.USER)) {
                    Optional<User> optionalUser = userService.findUserByEmail(user.getEmail());
                    optionalUser.ifPresentOrElse(u -> session.setAttribute(AttributeName.USER, u),
                            () -> logger.log(Level.WARN, "user was not found"));
                }
                router.setRedirectType();
                router.setCurrentPage(GO_TO_SHOW_ORDERS + RedirectMessage.REDIRECT_MESSAGE
                        + RedirectMessage.UPDATE_SUCCESS);

            } else {
                logger.log(Level.ERROR, "Incorrect id or order status");
                router.setRedirectType();
                router.setCurrentPage(GO_TO_SHOW_ORDERS + RedirectMessage.REDIRECT_MESSAGE
                        + RedirectMessage.UPDATE_FAIL);
                //return false
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "CancelOrderCommand command error. {}", e.getMessage());
            throw new CommandException("CancelOrderCommand command error", e);
        }
        return router;
    }
}
