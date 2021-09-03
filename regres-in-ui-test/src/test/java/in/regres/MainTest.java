package in.regres;

import in.regres.model.page_object.RegresPageObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;

public class MainTest {

    @Test
    public void mainPageTest(){
        RegresPageObject pageObject = open(RegresPageObject.URL, RegresPageObject.class);

        String topDescription = pageObject.getTopDescription();

        String bottomDescription = pageObject.getbottomDescription();

        Assertions.assertEquals("Test your front-end against a real API", topDescription);

        Assertions.assertEquals("A hosted REST-API ready to respond to your AJAX requests.", bottomDescription);
    }
}
