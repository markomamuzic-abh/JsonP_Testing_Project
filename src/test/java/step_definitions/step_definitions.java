package step_definitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import substeps.generic_substeps;
import java.io.IOException;


public class step_definitions {
    @Steps
    generic_substeps theApplication;
    @Given("user has access to JsonPlaceholder")
    public void userHasAccessToJsonPlaceholder() {

        theApplication.GetStatusCodeFromPage().then().statusCode(200);
    }

    @When("user successfully created album")
    public void userSuccessfullyCreatedAlbum() throws IOException {

        theApplication.SendPostRequest();
    }

    @And("user is able to list all of the albums")
    public void userIsAbleToListAllOfTheAlbums() {
        theApplication.GetList().then().statusCode(200);
    }

    @Then("verify the album belongs to the appropriate user")
    public void verifyTheAlbumBelongsToTheAppropriateUser() throws IOException {
        theApplication.VerifyThatAlbumBelongsToUser();
    }

    @And("delete user")
    public void deleteUser() {
    }
}
