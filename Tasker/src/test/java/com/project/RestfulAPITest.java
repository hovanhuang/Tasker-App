package com.project;

import io.restassured.response.Response;
import org.junit.Assert;
import org.testng.annotations.Test;
import org.junit.jupiter.api.Assertions;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class RestfulAPITest {
    private static String requestBody = "{\n" +
            "  \"title\": \"task1\",\n" +
            "  \"date\": \"2020-02-02 7pm\",\n" +
            "  \"reminder\": \"true\" \n}";

    @Test
    public void callIncorrectEndPoint_returnNotFound () {
        Response response = given()
                .header("Content-type", "application/json")
                .when()
                .get("/tasker")
                .then()
                .extract().response();
        Assertions.assertEquals(404, response.getStatusCode());
    }
    @Test
    public void createEntity_returnSuccessStatus () {
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(requestBody)
                .when()
                .post("/task")
                .then()
                .extract().response();
        Assertions.assertEquals(200, response.getStatusCode());
        Assertions.assertEquals("task1", response.jsonPath().getString("title"));
        Assertions.assertEquals("2020-02-02 7pm", response.jsonPath().getString("date"));
    }

    @Test
    public void getAllTasks_returnExpectedNumberOfTasks () {
        Response response = getAllResponse();
        List<String> titles = response.jsonPath().get("title");
        Assertions.assertEquals(2, titles.size());
        Assert.assertTrue(titles.contains("task1"));
    }

    @Test
    public void deleteTask_returnDeletedIdNotExist () {
        //Before deletion
        Response responseBeforeDelete = getAllResponse();
        List<Long> entriesBeforeDeletion = responseBeforeDelete.jsonPath().get("id");

        //Delete Request
        Long deleteId = 7L;
        Response response = given()
                .header("Content-type", "application/json")
                .when()
                .delete("/task/" + deleteId);

        // After deletion
        Response responseAfterDelete = getAllResponse();
        List<Long> entriesAfterDeletion = responseAfterDelete.jsonPath().get("id");

        Assertions.assertEquals(entriesBeforeDeletion.size() - 1, entriesAfterDeletion.size());
        Assert.assertFalse(entriesAfterDeletion.contains(deleteId));
    }

    private static Response getAllResponse() {
        return given()
                .header("Content-type", "application/json")
                .when()
                .get("/task/all")
                .then()
                .extract().response();
    }

}
