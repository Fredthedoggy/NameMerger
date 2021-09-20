package me.fredthedoggy.namemerger;

import java.util.Arrays;
import java.util.stream.Collectors;

public class NameMerger {
    public static String merge(String name1, String name2) {
        return extractName(name1)[0] + extractName(name2)[extractName(name2).length - 1];
    }

    private static String[] extractName(String name) {
        String finalName = name.replaceAll("(?<=[A-Za-z])0(?=[A-Za-z])", "o").replaceAll("(?<=[A-Za-z])3(?=[A-Za-z])", "e");
        String numberless = name.replaceAll("[0-9]", "");
        if (numberless.length() > 5) finalName = numberless;
        if (finalName.replaceAll("[A-Z]", "").length() < 3) finalName = finalName.toLowerCase();
        String[] extractWords = new String[]{"the", "is", "not", "da", "The", "Is", "Not"};
        String[] removeWords = new String[]{"_", "lol"};
        return Arrays.stream(finalName.replace("(" + String.join("|", removeWords) + ")", "").split("((?=\\p{Lu})" + Arrays.stream(extractWords).map(w -> "|(?=" + w + ")|(?<=" + w + ")").collect(Collectors.joining()) + ")")).map(str -> str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase()).toArray(String[]::new);
    }
}
