package ru.citilink.pages;

import com.codeborne.selenide.ex.UIAssertionError;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Страница "Корзина" на сайте Citilink
 */
public class CartPage extends BasePage {
    private final String sideDescriptionOfCart = "//div[@data-meta-name='BasketSummary']";
    private final String amountOfProductInCart = "//input[@data-meta-name='Count__input']";
    private final String increaseTheAmountOfProductInCartButton = "//button[@data-meta-name='Count__button-plus']";
    private final String codeNumberOfProductInCart = "//span[text()='Код товара: ']";
    //div[@data-meta-name="BasketSnippet"]//a//span[contains(text(),'Снегоуборщик Huter SGC 4000L,  бензиновый, 6.5л.с., самоходный [70/7/22]')]
    private final String productNameInCart = "//div[@data-meta-name='BasketSnippet']//a//span[text()]";

    public CartPage checkIfCorrectPageOpen() {
        try {
            $x(sideDescriptionOfCart).should(visible, WAITING_TIME);
        } catch (UIAssertionError e) {
            fail("Не удалось подтвердить открытие ожидаемой страницы. " +
                    "Уникальный элемент страницы 'sideDescriptionOfCart' не был найден в течение заданного времени.");
        }
        return this;
    }

    public CartPage increaseTheAmountOfProductInCartButtonClick() {
        jsClick($x(increaseTheAmountOfProductInCartButton));
        return this;
    }

    public String getAmountOfProductInCart() {
        return $x(amountOfProductInCart).should(visible, WAITING_TIME)
                .getAttribute("value");
    }

    public String getCodeNumberOfProductInCart() {
        return $x(codeNumberOfProductInCart).should(visible, WAITING_TIME)
                .getText();
    }

    public CartPage checkIsCorrectCodeNumberOfProductInCart(String expectedProductCode) {
        assertTrue(getCodeNumberOfProductInCart().contains(expectedProductCode),
                String.format("Фактическое значение кода добавленного товара = %s " +
                                " не соответствует ожидаемому = %s",
                        getCodeNumberOfProductInCart().substring(getCodeNumberOfProductInCart().indexOf(":") + 1),
                        expectedProductCode));
        return this;
    }
    public String getProductNameInCart() {
        return $x(productNameInCart).should(visible, WAITING_TIME)
                .getText();
    }
    public CartPage checkProductNameInCart(String expectedProductName) {
        assertTrue(getProductNameInCart().contains(expectedProductName),
                String.format("Фактическое имя добавленного товара = %s " +
                                " не соответствует ожидаемому = %s",
                        getProductNameInCart(),
                        expectedProductName));
        return this;
    }
}