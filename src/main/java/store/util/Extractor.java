package store.util;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class Extractor {

    /**
     * [name-value],[name1,value1]... 형태에서 name,value 로 된 Map 을 추출함.
     *
     * @param input 추출이 필요한 문자열
     * @return 추출된 값.
     */
    public static Map<String, Integer> getNameAndQuantityMap(String input) {
        return Arrays.stream(input.split(","))
                .map(s -> s.substring(1, s.length() - 1))
                .map(s->s.split("-"))
                .collect(Collectors.toMap(s->s[0],s->Integer.parseInt(s[1])));
    }
}
