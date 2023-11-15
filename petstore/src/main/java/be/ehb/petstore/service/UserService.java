package be.ehb.petstore.service;

import be.ehb.petstore.configuration.IAuthenticationFacade;
import be.ehb.petstore.model.Item;
import be.ehb.petstore.model.Order;
import be.ehb.petstore.model.OrderItem;
import be.ehb.petstore.model.User;
import be.ehb.petstore.repository.OrderRepository;
import be.ehb.petstore.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final IAuthenticationFacade iAuthenticationFacade;
    private final HttpSession httpSession;
    private User loggedInUser;

    @Autowired
    public UserService(UserRepository userRepository, OrderRepository orderRepository, IAuthenticationFacade iAuthenticationFacade, HttpSession httpSession) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.iAuthenticationFacade = iAuthenticationFacade;
        this.httpSession = httpSession;
    }

    public void processOAuthPostLogin(String googleNameId, String name, String givenName, String email) {
        User existUser = userRepository.getUserByGoogleNameIdAndEmailAddress(googleNameId, email);

        if (existUser == null) {
            User newUser = new User();
            newUser.setGoogleNameId(googleNameId);
            newUser.setProvider(User.Provider.GOOGLE);
            newUser.setUsername(name);
            newUser.setFirstName(givenName);
            newUser.setEmailAddress(email);
            newUser.setEnabled(true);

            userRepository.save(newUser);
            existUser = userRepository.getUserByGoogleNameIdAndEmailAddress(googleNameId, email);
        }

        this.loggedInUser = existUser;
    }

    public User getLoggedInUser() {
        if (iAuthenticationFacade.getAuthentication().isAuthenticated()) {
            if (this.loggedInUser != null) {
                return this.loggedInUser;
            }

            String googleNameId = iAuthenticationFacade.getAuthentication().getName();
            Optional<User> getUser = userRepository.getUserByGoogleNameId(googleNameId);

            if (getUser.isPresent()){
                this.loggedInUser = getUser.get();
                return this.loggedInUser;
            }

            httpSession.invalidate();
            //todo throw error
        }
        return null;
    }
}
