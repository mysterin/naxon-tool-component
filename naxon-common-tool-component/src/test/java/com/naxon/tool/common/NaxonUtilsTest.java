package com.naxon.tool.common;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NaxonUtilsTest {

    public static Stream<Arguments> isEmptyData() {
        Stream<Arguments> stream = Stream.of(
                Arguments.of(0, false),
                Arguments.of(1, false),
                Arguments.of(new Object(), false),
                Arguments.of(null, true),
                Arguments.of("", true),
                Arguments.of("  ", false)
        );
        return stream;
    }

    @ParameterizedTest
    @MethodSource("isEmptyData")
    public void isEmpty(Object input, Boolean result) {
        assertEquals(result, NaxonUtils.isEmpty(input));
    }

    public static Stream<Arguments> isNotEmptyData() {
        Stream<Arguments> stream = Stream.of(
                Arguments.of(0, true),
                Arguments.of(1, true),
                Arguments.of(new Object(), true),
                Arguments.of(null, false),
                Arguments.of("", false),
                Arguments.of("  ", true)
        );
        return stream;
    }

    @ParameterizedTest
    @MethodSource("isNotEmptyData")
    void isNotEmpty(Object input, Boolean result) {
        assertEquals(result, NaxonUtils.isNotEmpty(input));
    }
}