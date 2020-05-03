package com.studentapp.cucumber.serenity;

import com.studentapp.model.StudentClass;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class StudentImpl {
    Response response;

    @Step
    public void provideUrl() {
        //RestAssured.baseURI = "http://localhost:3030/student";
        List<String> courses = new ArrayList<>();
        courses.add("java");
        response = postCreateStudent("mk", "oms", "mks@gm.com", "IT", courses);
    }

    @Step
    public void sample() {
        System.out.println("given step");
    }

    @Step
    public void statusCodeCheck(int statusCode) {
        Assert.assertEquals(response.then().statusCode(201), statusCode);
        System.out.println("mk");
    }

    @Step
    public Response postCreateStudent(String firstName,String lastName, String email, String programme,
            List<String> courses) {

        StudentClass student = new StudentClass();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setEmail(email);
        student.setProgramme(programme);
        student.setCourses(courses);

        return SerenityRest.rest().given()
                .contentType(ContentType.JSON)
                .when()
                .body(student)
                .post();
    }
}
