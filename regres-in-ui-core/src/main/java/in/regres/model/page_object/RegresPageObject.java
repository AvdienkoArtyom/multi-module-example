package in.regres.model.page_object;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$$;

public class RegresPageObject {
    public static final String URL = "https://reqres.in";

    @Step("Получение верхнего описания страницы")
    public String getTopDescription(){
       return $$(By.cssSelector(".the-sell .wrap h2")).get(0).getText();
    }

    @Step("Получение нижнего описания страницы")
    public String getbottomDescription(){
        return $$(By.cssSelector(".the-sell .wrap h2")).get(1).getText();
    }
}
