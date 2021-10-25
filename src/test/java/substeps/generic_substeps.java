package substeps;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import io.restassured.response.ValidatableResponse;
import org.apache.commons.io.IOUtils;
import org.junit.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;


public class generic_substeps {

    String url = "https://jsonplaceholder.typicode.com";
    public static ResponseOptions<Response> response;

    public Response GetStatusCodeFromPage() {

        return RestAssured.given().get(url);
    }
    public ValidatableResponse SendPostRequest() throws IOException {

        FileInputStream fileInputStream = new FileInputStream(new File("/Users/marko/Downloads/API Testing Project/src/test/resources/json_file_input/jsonPost.json"));
        RestAssured.baseURI = url;
        ValidatableResponse response = RestAssured.given()
                        .header("Content-Type","application/json")
                        .and()
                        .body(IOUtils.toString(fileInputStream, StandardCharsets.UTF_8))
                    .when()
                        .post("/albums")
                    .then()
                        .statusCode(201);
        return response;
    }
    public Response GetList() {
        return RestAssured.given().get(url + "/albums");
    }
    /*public Response GetAlbumById(int id) {
        return RestAssured.given().get(url + "/albums/id");
    }*/

    public void VerifyThatAlbumBelongsToUser() throws IOException {
        /*Map<String, Object> data = new HashMap<String, Object>();
        data.put( "userId", 1 );
        data.put( "id", 9 );
        data.put( "title", "saepe unde necessitatibus rem" );*/

        JSONObject json = new JSONObject(1,9,"saepe unde necessitatibus rem");
        /*json.putAll( data );*/
        /*FileInputStream fileInputStream = new FileInputStream(new File("/Users/marko/Downloads/API Testing Project/src/test/resources/json_file_input/jsonPost.json"));*/
        response = RestAssured.given().get(url + "/albums/9");

        Assert.assertEquals(json.userId, response.body().equals(json.userId));
    }

    private class JSONObject {
        private final int userId;
        private final int id;
        private final String title;

        public JSONObject(int userId, int id, String title) {
            this.userId = userId;
            this.id = id;
            this.title = title;
        }
    }
}

