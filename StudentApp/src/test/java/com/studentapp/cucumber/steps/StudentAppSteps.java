package com.studentapp.cucumber.steps;

import com.studentapp.cucumber.serenity.StudentImpl;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;

import java.util.ArrayList;
import java.util.List;

public class StudentAppSteps {

    @Steps
    StudentImpl student;

    Response response;

    @Given("^a student app url$")
    public void a_student_app_url() throws Throwable {
        student.sample();
    }

    @When("^student get request is hit$")
    public void studentGetRequestIsHit() {
        //student.provideUrl();
        SerenityRest.rest()
                .given()
                .when()
                .get("/list")
                .then()
                .statusCode(200);
    }

    @Then("^response code should return '(\\d+)' status code$")
    public void response_code_should_return_status_code(int code) {
        List<String> courses = new ArrayList<>();
        courses.add("java");
        response = student.postCreateStudent("mk", "oms", "mks@gm.com", "IT", courses);
    }
}

