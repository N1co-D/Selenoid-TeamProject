package ru.citilink;

import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class CitilinkTestData {
    static Stream<Arguments> checkAddProductToCompareTestData() {
        String testLaptop = "Ноутбук Lenovo IdeaPad 1 15AMN7 82VG00LSUE, 15.6\", TN, AMD Ryzen 3 7320U, 4-ядерный, 8ГБ LPDDR5, 256ГБ SSD, AMD Radeon 610M, серый";
        String productCategory = "Ноутбуки Lenovo";
        return Stream.of(Arguments.of(testLaptop, productCategory));
    }

    /**
     * TC-ID7
     */
    static Stream<Arguments> checkIncreaseInQuantityWhenAddProductsToCartTestData() {
        String inputText = "Ноутбук Huawei MateBook D 14 53013XFA, 14";
        String rawMemoryRequiredParameter = "8 ГБ, LPDDR4x";
        String diskRequiredParameter = "SSD 512 ГБ";
        int expectedAmountOfProduct = 2;
        return Stream.of(Arguments.of(inputText, rawMemoryRequiredParameter,
                diskRequiredParameter, expectedAmountOfProduct));
    }

    /**
     * TC-ID8
     */
    static Stream<Arguments> checkProductAddToCompareSectionTestData() {
        int amountOfProductsForAdding = 2;
        return Stream.of(Arguments.of(amountOfProductsForAdding));
    }

    /**
     * TC-ID1
     */
    static Stream<Arguments> checkProductAddToCartTestData() {
        String inputText = "Переходники";
        String productFromDropDownList = "Переходники на евровилку";
        String observedProduct = "Адаптер-переходник на евровилку PREMIER 11626/20, темно-серый";
        String expectedProductCode = "1860968";
        return Stream.of(Arguments.of(inputText, productFromDropDownList, observedProduct, expectedProductCode));
    }

    /**
     * TC-ID2
     */
    static Stream<Arguments> checkTheDeletingOfProductFromCartTestData() {
        String inputText = "Переходники";
        String productFromDropDownList = "Переходники на евровилку";
        String observedProduct = "Адаптер-переходник на евровилку PREMIER 11626/20, темно-серый";
        return Stream.of(Arguments.of(inputText, productFromDropDownList, observedProduct));
    }
}