package com.freenow.blog.users;

import com.freenow.blog.commontasks.CommonRequestSpec;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;

public class UserCalls {

  public static Response getUserDetails(String user) {
    return SerenityRest.given().spec(CommonRequestSpec.blogReqSpec())
        .basePath("users")
        .queryParam("username", user)
        .get().then().extract().response();
  }
}