package com.grability.rappi.result;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.grability.rappi.R;
import com.grability.rappi.model.Result;

/**
 * Created by jorgesanmartin on 4/12/16.
 */
public class ResultEngine {
    private final HashMap<String, List<Result>> results;

    public ResultEngine() {
        this.results = buildResults();
    }

    private HashMap<String, List<Result>> buildResults() {

        HashMap<String, List<Result>> results = new HashMap<String, List<Result>>();

        int reading_very_top_level = R.string.reading_very_top_level;
        int reading_top_level = R.string.reading_top_level;
        int reading_medium_level = R.string.reading_medium_level;
        int reading_very_medium_level = R.string.reading_very_medium_level;
        int reading_low_level = R.string.reading_low_level;
        int very_low_level = R.string.reading_very_low_level;

        List<Result> eightAgeResults = new ArrayList<>();
        eightAgeResults.add(new Result.ResultBuilder(140, 140, reading_very_top_level).build());
        eightAgeResults.add(new Result.ResultBuilder(125, 139, reading_top_level).build());
        eightAgeResults.add(new Result.ResultBuilder(111, 124, reading_medium_level).build());
        eightAgeResults.add(new Result.ResultBuilder(97, 110, reading_very_medium_level).build());
        eightAgeResults.add(new Result.ResultBuilder(85, 96, reading_low_level).build());
        eightAgeResults.add(new Result.ResultBuilder(84, 84, very_low_level).build());

        List<Result> nineAgeResults = new ArrayList<>();
        eightAgeResults.add(new Result.ResultBuilder(168, 168, reading_very_top_level).build());
        eightAgeResults.add(new Result.ResultBuilder(150, 167, reading_top_level).build());
        eightAgeResults.add(new Result.ResultBuilder(136, 149, reading_medium_level).build());
        eightAgeResults.add(new Result.ResultBuilder(120, 135, reading_very_medium_level).build());
        eightAgeResults.add(new Result.ResultBuilder(104, 119, reading_low_level).build());
        eightAgeResults.add(new Result.ResultBuilder(103, 103, very_low_level).build());

        List<Result> tenAgeResults = new ArrayList<>();
        eightAgeResults.add(new Result.ResultBuilder(196, 196, reading_very_top_level).build());
        eightAgeResults.add(new Result.ResultBuilder(178, 195, reading_top_level).build());
        eightAgeResults.add(new Result.ResultBuilder(161, 177, reading_medium_level).build());
        eightAgeResults.add(new Result.ResultBuilder(143, 160, reading_very_medium_level).build());
        eightAgeResults.add(new Result.ResultBuilder(125, 142, reading_low_level).build());
        eightAgeResults.add(new Result.ResultBuilder(124, 124, very_low_level).build());

        List<Result> eleveAgeResults = new ArrayList<>();
        eightAgeResults.add(new Result.ResultBuilder(214, 214, reading_very_top_level).build());
        eightAgeResults.add(new Result.ResultBuilder(194, 213, reading_top_level).build());
        eightAgeResults.add(new Result.ResultBuilder(174, 193, reading_medium_level).build());
        eightAgeResults.add(new Result.ResultBuilder(154, 173, reading_very_medium_level).build());
        eightAgeResults.add(new Result.ResultBuilder(135, 153, reading_low_level).build());
        eightAgeResults.add(new Result.ResultBuilder(134, 134, very_low_level).build());

        List<Result> twelveAgeResults = new ArrayList<>();
        eightAgeResults.add(new Result.ResultBuilder(214, 214, reading_very_top_level).build());
        eightAgeResults.add(new Result.ResultBuilder(194, 213, reading_top_level).build());
        eightAgeResults.add(new Result.ResultBuilder(174, 193, reading_medium_level).build());
        eightAgeResults.add(new Result.ResultBuilder(154, 173, reading_very_medium_level).build());
        eightAgeResults.add(new Result.ResultBuilder(135, 153, reading_low_level).build());
        eightAgeResults.add(new Result.ResultBuilder(134, 134, very_low_level).build());

        results.put("8", eightAgeResults);
        results.put("9", nineAgeResults);
        results.put("10", tenAgeResults);
        results.put("11", eleveAgeResults);
        results.put("12", twelveAgeResults);

        return results;
    }

    public Result getResultByAgeAndCorrectWords(String age, int correctWords) {
        List<Result> resultByAge = getResults().get(age);
        if (resultByAge != null) {
            for (Result ageResult : resultByAge) {
                if (ageResult.getMinSpeed() >= correctWords && ageResult.getMaxSpeed() <= correctWords) {
                    return ageResult;
                }
            }
        }
        return null;
    }

    public HashMap<String, List<Result>> getResults() {
        return results;
    }

}
