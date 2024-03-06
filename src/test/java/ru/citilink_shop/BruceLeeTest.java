package ru.citilink_shop;

import org.junit.jupiter.api.Test;
import ru.citilink_shop.utilities.ConfProperties;
import ru.citilink_shop.utilities.pages.MainPage;
import ru.citilink_shop.utilities.pages.OrderPage;
import ru.citilink_shop.utilities.pages.ProductPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BruceLeeTest extends BaseTest {
    private final static String HUAWEI_NOVA_MGA_LX3_BLACK = "Смартфон Huawei nova Y72 8/128Gb,  MGA-LX3,  черный";
    private final ConfProperties confProperties = new ConfProperties();

    @Test
    public void checkAddItemToShoppingBasket() {
        open(confProperties.getProperty("test-site"));
        new MainPage().enterProductSearchInputLine(HUAWEI_NOVA_MGA_LX3_BLACK);
        new ProductPage()
                .clickButtonForAddingItemToBasket(HUAWEI_NOVA_MGA_LX3_BLACK)
                .clickButtonCloseUpSaleBasketLayout()
                .clickButtonBasketFresnelContainer();
        assertEquals("Смартфон Huawei nova Y72 8/128Gb, MGA-LX3, черный"
                , new OrderPage().getNameProductFromBasketSnippet(HUAWEI_NOVA_MGA_LX3_BLACK));
    }
}