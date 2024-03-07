package ru.citilink;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ru.citilink.pages.CatalogPage;
import ru.citilink.pages.ComparePage;
import ru.citilink.pages.MainPage;
import ru.citilink.utilities.ConfProperties;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.*;

public class BruceLeeTest extends BaseTest {
    private final MainPage mainPage = new MainPage();
    private final CatalogPage catalogPage = new CatalogPage();
    private final ComparePage comparePage = new ComparePage();
    private final ConfProperties confProperties = new ConfProperties();

    @ParameterizedTest
    @CsvSource({
            "'Ноутбук Lenovo IdeaPad 1 15AMN7 82VG00LSUE, 15.6\", TN, AMD Ryzen 3 7320U, 4-ядерный, 8ГБ LPDDR5, 256ГБ SSD, AMD Radeon 610M, серый', Ноутбуки Lenovo"
    })
    public void checkingRemovalOfProductFromComparison(String testLaptop, String productCategory) {
        open(confProperties.getProperty("test-site"));
        mainPage.enterDataSearchField("lenovo").productSearchExtraResultListClick(productCategory);
        assertEquals(catalogPage.getSubcategoryPageTitle(), productCategory,
                String.format("Указан заголовок некорректной страницы. Ожидаем = %s, факт = %s",
                        productCategory, catalogPage.getSubcategoryPageTitle()));
        catalogPage.detailedCatalogModeButtonClick().comparingCurrentProductButtonClick(testLaptop);
        assertTrue(mainPage.compareValueIsDisplayed(), "Товар не добавлен в сравнение");
        mainPage.compareButtonClick();
        assertAll(
                () -> assertEquals(comparePage.getComparePageTitle(), "Сравнение товаров",
                        String.format("Указан заголовок некорректной страницы. Ожидаем = Сравнение товаров, факт = %s",
                                comparePage.getComparePageTitle())),
                () -> assertEquals(comparePage.getTitleOfCurrentProduct(), testLaptop,
                        String.format("Товар для сравнения не корректный. Ожидаем = %s, факт = %s",
                                testLaptop, comparePage.getComparePageTitle())));
        comparePage.deleteCurrentProductButtonClick();
        assertAll(
                () -> assertFalse(comparePage.compareValueIsDisplayed(), "Товар не удалён из сравнение"),
                () -> assertTrue(comparePage.productsEmptyIsDisplayed(), "Отсутствует уведомление Нет товаров для сравнения"));
    }
}