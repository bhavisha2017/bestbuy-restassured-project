package com.bestbuy.crudtest;

import com.bestbuy.model.ServicesPojo;
import com.bestbuy.model.StorePojo;
import com.bestbuy.testbase.TestBase;
import com.bestbuy.utils.TestUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class StoresCRUDTest extends TestBase {

    static String name = "Minnetonka" + TestUtils.getRandomValue();
    static String type = "BigBox" +TestUtils.getRandomValue();
    static String address = "13513 Ridgedale Dr" +TestUtils.getRandomValue();
    static String address2 = "";
    static String city = "Hopkins" + TestUtils.getRandomValue();
    static String state = "MN" + TestUtils.getRandomValue();
    static String zip = "55305" +TestUtils.getRandomValue();
    static Double lat = 44.969658;
    static Double lng = -93.449539;
    static String hours = "Mon: 10-9; Tue: 10-9; Wed: 10-9; Thurs: 10-9; Fri: 10-9; Sat: 10-9; Sun: 10-8";
    static String services = "{}";
    static int id;

    @Before
    public  void setUP(){
        RestAssured.basePath= "/stores";
    }

    @Test
    public void test001(){
        StorePojo storePojo = new StorePojo();
        storePojo.setName(name);
        storePojo.setType(type);
        storePojo.setAddress(address);
        storePojo.setAddress2(address2);
        storePojo.setCity(city);
        storePojo.setState(state);
        storePojo.setZip(zip);
        storePojo.setLat(lat);
        storePojo.setLng(lng);
        storePojo.setHours(hours);
        ServicesPojo servicesPojo = new ServicesPojo();
        servicesPojo.setName("service1");
        storePojo.setServices(servicesPojo);

        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .body(storePojo)
                .post();
        response.prettyPrint();
        response.then().log().all().statusCode(201);

    }
    @Test
    public void test002()
    {
        StorePojo storePojo = new StorePojo();
        storePojo.setName(name);
        storePojo.setType(type);
        storePojo.setAddress(address);
        storePojo.setAddress2(address2);
        storePojo.setLat(lat);
        storePojo.setLng(lng);
        storePojo.setHours(hours);
        storePojo.setCity(city);
        storePojo.setState(state);
        storePojo.setZip(zip);
        storePojo.setName("service1");
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .body(storePojo)
                .patch();
        response.then().log().all().statusCode(200);

    }
    @Test
    public void test003() {
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get();
        response.prettyPrint();
        response.then().log().all().statusCode(200);
    }

    //Delete Data
    @Test
    public void test004()
    {
        given()
                .pathParam("id", id)
                .when()
                .delete("/{id}")
                .then()
                .statusCode(404);
    }


}
