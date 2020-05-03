package com.studentapp.cucumber.steps;

import com.studentapp.cucumber.serenity.StudentSerenitySteps;
import com.studentapp.utils.TestUtils;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.Matchers.hasValue;
import static org.junit.Assert.assertThat;

public class StudentSteps {
    static String email = null;

    @Steps
    StudentSerenitySteps steps;

    Response response;

    @When("^User sends a GET request to the list endpoint,they must get back a valid status code 200$")
    public void verify_status_code_200_for_listendpoint() {
        SerenityRest.rest()
                .given()
                .when()
                .get("/list")
                .then()
                .statusCode(200);
    }

    @When("^I create a new student by providing the information firstName (.*) lastName (.*) email (.*) programme (.*) courses (.*)$")
    public void createStudent(String firstName, String lastName, String _email, String programme, String course) {
        List<String> courses = new ArrayList<>();
        courses.add(course);
        email = TestUtils.getRandomValue() + _email;

        steps.createStudent(firstName, lastName, email, programme, courses)
                .assertThat()
                .statusCode(201);
    }

    @Then("^I verify that the student with (.*) is created$")
    public void verifyStudent(String emailId) {
        HashMap<String, Object> actualValue = steps.getStudentInfoByEmailId(email);
        assertThat(actualValue, hasValue(email));
    }



    public void sendPostRequest() {
        List<String> courses = new ArrayList<>();
        courses.add("java");
        response= steps.postCreateStudent("mk", "oms", "mks@gm.com", "IT", courses);
    }

    public void verifyStatusCode() {
     response.then().statusCode(200);
    }
}
