package in.regres.service;

import in.regres.client.InRegresClient;
import in.regres.model.User;
import in.regres.model.UserToken;
import in.regres.model_api.create_user_result.ResultCreateUser;
import in.regres.model_api.list_users_result.ResultListUsers;
import in.regres.model_api.login_result.ResultLogin;
import in.regres.model_api.registr_result.ResultRegistr;
import in.regres.model_api.update_user_result.ResultUpdateUser;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.apache.http.NameValuePair;

import java.util.List;
import java.util.stream.Collectors;

public class MainPageServiceImpl implements MainPageService {

    private final InRegresClient client = new InRegresClient();

    @Override
    @Step("Создание юзера")
    public User createUser(User user) {
        List<NameValuePair> params = ParamHelpers.userToCreateParams(user);
        ResultCreateUser resultCreateUser = client.sendPost("/api/users", params, ResultCreateUser.class);
        return toUser(resultCreateUser);
    }

    @Attachment
    private User toUser(ResultCreateUser resultCreateUser) {
        return User.builder()
                .id(Long.parseLong(resultCreateUser.getId()))
                .name(resultCreateUser.getName())
                .job(resultCreateUser.getJob())
                .build();
    }

    @Override
    @Step("Обновление юзера")
    public User updateUser(User user) {
        List<NameValuePair> params = ParamHelpers.userToCreateParams(user);
        ResultUpdateUser resultUpdateUser = client.sendPut("/api/users/2", params, ResultUpdateUser.class);
        return toUser(resultUpdateUser);
    }

    @Attachment
    private User toUser(ResultUpdateUser resultUpdateUser) {
        return User.builder()
                .name(resultUpdateUser.getName())
                .job(resultUpdateUser.getJob())
                .build();
    }

    @Override
    @Step("Получение токена после регистрации")
    public UserToken register(User user) {
        List<NameValuePair> params = ParamHelpers.userToLoginData(user);
        ResultRegistr resultRegistr = client.sendPost("/api/register", params,
                ResultRegistr.class);
        return toUserToken(resultRegistr);
    }

    @Attachment
    private UserToken toUserToken(ResultRegistr resultRegistr) {
        return UserToken.builder()
                .id(resultRegistr.getId())
                .token(resultRegistr.getToken())
                .build();
    }

    @Override
    @Step("Получение токина после логина")
    public UserToken login(User user) {
        List<NameValuePair> params = ParamHelpers.userToLoginData(user);
        ResultLogin resultLogin = client.sendPost("/api/login", params,
                ResultLogin.class);
        return toUserToken(resultLogin);
    }

    @Attachment
    private UserToken toUserToken(ResultLogin resultLogin) {
        return UserToken.builder()
                .token(resultLogin.getToken())
                .build();
    }

    @Override
    @Step("РПолучение юзеров на 2 странице")
    public List<User> getAllUser() {
        ResultListUsers usersData = client.sendGet("/api/users?page=2", ResultListUsers.class);
        return toListUser(usersData);
    }

    @Attachment
    private List<User> toListUser(ResultListUsers usersData) {
        return usersData.getData().stream()
                .map(datum -> User.builder()
                        .id(datum.getId())
                        .name(datum.getFirstName())
                        .email(datum.getEmail())
                        .build())
                .collect(Collectors.toList());
    }
}
