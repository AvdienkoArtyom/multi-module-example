package in.regres;

import in.regres.model.User;
import in.regres.model.UserToken;
import in.regres.service.MainPageService;
import in.regres.service.MainPageServiceImpl;
import io.qameta.allure.Description;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MainTest {
    MainPageService mainPageService = new MainPageServiceImpl();

    @Test
    @Description("Проверка создания пользователя")
    public void createUserTest(){
        User newUser = User.builder()
                .name("simpleName")
                .job("simpleJob")
                .build();

        User addedUser = mainPageService.createUser(newUser);
        Assertions.assertEquals(newUser.getName(), addedUser.getName());
    }

    @Test
    @Description("Проверка получения списка всех пользователей на странице")
    public void getAllUserTest(){
        List<User> userList = mainPageService.getAllUser();
        Assertions.assertEquals(userList.size(), 6);
    }

    @Test
    @Description("Обновление пользователя")
    public void updateUserTest(){
        User newUser = User.builder()
                .name("simpleName")
                .job("simpleJob")
                .build();

        User updateUser = mainPageService.updateUser(newUser);

        Assertions.assertEquals(newUser.getName(), updateUser.getName());
    }

    @Test
    @Description("Регистрация пользователя")
    public void registerTest(){
        User userToRegister = User.builder()
                .email("eve.holt@reqres.in")
                .password("pistol")
                .build();

        UserToken userToken = mainPageService.register(userToRegister);

        Assertions.assertEquals(4, userToken.getId());
    }

    @Test
    @Description("Пользователь логинится")
    public void loginTest(){
        User userToLogin = User.builder()
                .email("eve.holt@reqres.in")
                .password("cityslicka")
                .build();

        UserToken userToken = mainPageService.login(userToLogin);

        Assertions.assertEquals("QpwL5tke4Pnpja7X4", userToken.getToken());
    }
}
