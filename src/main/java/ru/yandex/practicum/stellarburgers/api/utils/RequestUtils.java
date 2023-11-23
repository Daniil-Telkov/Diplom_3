package ru.yandex.practicum.stellarburgers.api.utils;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.apache.http.HttpStatus;
import ru.yandex.practicum.stellarburgers.api.model.User;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.junit.Assert.assertTrue;
import static ru.yandex.practicum.stellarburgers.api.config.APIConfig.*;
import static ru.yandex.practicum.stellarburgers.api.config.APIConfig.USER_MODIFY_API;
import static ru.yandex.practicum.stellarburgers.api.cridentials.UserCredentials.*;

public class RequestUtils {
    @Step("Отправка get-запроса")
    public static Response sendGetRequest(String api) {
        return given()
                .filters(new RequestLoggingFilter(), new ResponseLoggingFilter())
                .contentType(JSON)
                .get(api);
    }
    @Step("Отправка post-запроса")
    public static Response sendPostRequest(String api, Object body) {
        return given()
                .filters(new RequestLoggingFilter(), new ResponseLoggingFilter())
                .contentType(JSON)
                .body(body)
                .post(api);
    }

    @Step("Отправка delete-запроса")
    public static Response sendDeleteRequest(String api, String accessToken) {
        return given()
                .filters(new RequestLoggingFilter(), new ResponseLoggingFilter())
                .header("Authorization", accessToken)
                .delete(api);
    }

    @Step("Создание пользователя в системе")
    public static void defaultUserCreate() {
        RestAssured.baseURI = BASE_URL;
        User user = new User(DEFAULT_USER_EMAIL, DEFAULT_USER_PASSWORD, DEFAULT_USER_NAME);
        sendPostRequest(USER_CREATE_API, user)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK);
    }

    @Step("Удаление пользователя в системе")
    public static void defaultUserDelete(){
        User createdUser = new User(DEFAULT_USER_EMAIL, DEFAULT_USER_PASSWORD);
        String accessToken = sendPostRequest(USER_AUTH_API, createdUser)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .path("accessToken");

        Boolean deleteResult = sendDeleteRequest(USER_MODIFY_API, accessToken)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_ACCEPTED)
                .extract()
                .path("success");
        assertTrue(deleteResult);
    }
}
