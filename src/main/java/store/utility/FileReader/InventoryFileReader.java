package store.utility.FileReader;

import static store.constant.Constants.*;
import store.dto.ParsedProductDto;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

import static store.utility.FileReader.BufferedReaderManager.getFileReaderFromPath;
import static store.utility.FileReader.BufferedReaderManager.readLine;

public class InventoryFileReader {
    public static List<ParsedProductDto> inputInventoryFromFile() {
        BufferedReader productFileReader = getFileReaderFromPath(PRODUCT_FILE_PATH);
        readLine(productFileReader);

        String productLine;
        List<ParsedProductDto> parsedProducts = new ArrayList<>();
        while ((productLine = readLine(productFileReader)) != null) {
            String[] splittedProduct = productLine.split(FILE_INPUT_DELIMITER);
            parsedProducts.add(generateProductDto(splittedProduct));
        }
        return parsedProducts;
    }

    private static ParsedProductDto generateProductDto(String[] splittedProduct) {
        String productName = splittedProduct[0];
        int productPrice = Integer.parseInt(splittedProduct[1]);
        int productQuantity = Integer.parseInt(splittedProduct[2]);
        String productPromotion = splittedProduct[3];
        return new ParsedProductDto(productName, productPrice, productQuantity, productPromotion);
    }
}
