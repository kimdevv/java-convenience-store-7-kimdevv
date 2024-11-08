package store.utility.FileReader;

import store.dto.ParsedPromotionDto;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

import static store.utility.FileReader.BufferedReaderManager.getFileReaderFromPath;
import static store.utility.FileReader.BufferedReaderManager.readLine;

public class PromotionFileReader {
    public static List<ParsedPromotionDto> inputPromotionsFromFile() {
        BufferedReader promotionFileReader = getFileReaderFromPath("src/main/resources/promotions.md");
        readLine(promotionFileReader);

        String promotionLine;
        List<ParsedPromotionDto> parsedPromotions = new ArrayList<>();
        while ((promotionLine = readLine(promotionFileReader)) != null) {
            String[] splittedPromotion = promotionLine.split(",");
            parsedPromotions.add(generatePromotionDto(splittedPromotion));
        }
        return parsedPromotions;
    }

    private static ParsedPromotionDto generatePromotionDto(String[] splittedPromotion) {
        String promotionName = splittedPromotion[0];
        int buyCount = Integer.parseInt(splittedPromotion[1]);
        int getCount = Integer.parseInt(splittedPromotion[2]);
        String startDate = splittedPromotion[3];
        String endDate = splittedPromotion[4];
        return new ParsedPromotionDto(promotionName, buyCount, getCount, startDate, endDate);
    }
}
