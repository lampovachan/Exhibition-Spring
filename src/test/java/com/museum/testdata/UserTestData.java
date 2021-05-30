package com.museum.testdata;

import com.museum.model.Role;
import com.museum.model.User;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

import static com.museum.web.AbstractControllerTest.REST_URL;

public class UserTestData {
    public static final String USERS_URL = REST_URL + "users/";

    public static final Long START_SEQ = 0L;
    public static final Long USER_ID = START_SEQ;
    public static final Long ADMIN_ID = START_SEQ + 1;

    public static final User USER = new User(USER_ID, "User", "user@yandex.ru", "user", Role.ROLE_USER);
    public static final User ADMIN = new User(ADMIN_ID, "Admin", "admin@gmail.com", "admin", Role.ROLE_ADMIN, Role.ROLE_USER);

    public static Map<String, Object> getCreatedUser() {
        return getStringObjectMapUser("New user", "user@local.loc", "password", "ROLE_USER", "ROLE_ADMIN");
    }

    public static Map<String, Object> getUpdateUser() {
        return getStringObjectMapUser("Update user", "user@yandex.ru", "user", "ROLE_USER");
    }

    private static Map<String, Object> getStringObjectMapUser(String name, String email, String password, @NotNull String... roles) {
        return new HashMap<>() {{
            put("name", name);
            put("email", email);
            put("password", password);
            put("roles", roles);
        }};
    }
}
