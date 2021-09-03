package in.regres.service;


import in.regres.model.User;
import in.regres.model.UserToken;
import io.qameta.allure.Step;

import java.util.List;

public interface MainPageService {

    @Step("Создание юзера")
    User createUser(User user);

    User updateUser(User user);

    UserToken register(User user);

    UserToken login(User user);

    List<User> getAllUser();
}
