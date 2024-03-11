package ru.citilink.pages;

import static com.codeborne.selenide.Condition.not;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class ComparePage extends BasePage {
    private final String comparePageTitle = "//div[@class='ComparePage__header']//h2[text()]";
    private final String productTitle = "//div[normalize-space(text())='Модель']/following-sibling::div//a";
    private final String deleteProductButton = "//div[@class='Compare__product-row']//div[@class='Compare__image-wrapper']/button";
    private final String noProductsForCompare = "//div[normalize-space(text())='Нет товаров для сравнения' and @style='display: block;']";
    private final String compareValue = "//div[contains(@class, 'HeaderMenu__count') and text()]";

    public String getComparePageTitle() {
        return $x(comparePageTitle).shouldBe(visible, WAITING_TIME).getText();
    }

    public String getProductTitle() {
        return $x(productTitle).shouldBe(visible, WAITING_TIME).getText();
    }

    public void deleteProductButtonClick() {
        $x(deleteProductButton).shouldBe(visible, WAITING_TIME).click();
    }

    public boolean noProductsForCompareIsDisplayed() {
        return $x(noProductsForCompare).shouldBe(visible, WAITING_TIME).isDisplayed();
    }

    public boolean compareValueIsDisplayed() {
        return $x(compareValue).should(not(visible), WAITING_TIME).isDisplayed();
    }
}