package com.naxon.tool.common;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.stream.Stream;

@Slf4j
class NaxonUtilsTest {

//    public static Stream<Arguments> isEmptyData() {
//        Stream<Arguments> stream = Stream.of(
//                Arguments.of(0, false),
//                Arguments.of(1, false),
//                Arguments.of(new Object(), false),
//                Arguments.of(null, true),
//                Arguments.of("", true),
//                Arguments.of("  ", false)
//        );
//        return stream;
//    }
//
//    @ParameterizedTest
//    @MethodSource("isEmptyData")
//    public void isEmpty(Object input, Boolean result) {
//        assertEquals(result, NaxonUtils.isEmpty(input));
//    }
//
//    public static Stream<Arguments> isNotEmptyData() {
//        Stream<Arguments> stream = Stream.of(
//                Arguments.of(0, true),
//                Arguments.of(1, true),
//                Arguments.of(new Object(), true),
//                Arguments.of(null, false),
//                Arguments.of("", false),
//                Arguments.of("  ", true)
//        );
//        return stream;
//    }
//
//    @ParameterizedTest
//    @MethodSource("isNotEmptyData")
//    void isNotEmpty(Object input, Boolean result) {
//        assertEquals(result, NaxonUtils.isNotEmpty(input));
//    }
//
//    @Test
//    void randomNumber() {
//        String s = NaxonUtils.randomNumber();
//        log.debug(s);
//        assertEquals(6, s.length());
//
//        String s1 = NaxonUtils.randomNumber(1);
//        log.debug(s1);
//        assertTrue(Integer.valueOf(s1) > 0 && Integer.valueOf(s1) < 10);
//    }
//
//    @Test
//    void urlParams() {
//        String text = "userId=1&name=xx";
//        Map<String, String> map = NaxonUtils.urlParams(text);
//        assertEquals("1", map.get("userId"));
//        assertEquals("xx", map.get("name"));
//
//        String s = NaxonUtils.urlParams(map);
//        assertEquals(text, s);
//    }

}