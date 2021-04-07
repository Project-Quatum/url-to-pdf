package org.apache.pdfbox.pdmodel.font;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.HashMap;
import java.util.Map;

class PDCIDFontTest {
    private static final float DEFAULT_WIDTH = 1000f;
    private static final float DEFAULT_AVERAGE_WIDTH = 0f;

    @Test
    public void test_getAverageFontWidth_when_widths_is_empty() {
        PDCIDFont pdcidFont = Mockito.mock(PDCIDFont.class, Mockito.CALLS_REAL_METHODS);

        // assign value to private property
        ReflectionTestUtils.setField(pdcidFont, "averageWidth", DEFAULT_AVERAGE_WIDTH);
        ReflectionTestUtils.setField(pdcidFont, "defaultWidth", DEFAULT_WIDTH);

        assertEquals(DEFAULT_WIDTH, pdcidFont.getAverageFontWidth());
    }

    @Test
    public void test_getAverageFontWidth_when_widths_is_not_empty() {
        PDCIDFont pdcidFont = Mockito.mock(PDCIDFont.class, Mockito.CALLS_REAL_METHODS);
        Map<Integer, Float> sampleWidths = new HashMap<>();
        sampleWidths.put(1, 100f);

        // assign value to private property
        ReflectionTestUtils.setField(pdcidFont, "averageWidth", DEFAULT_AVERAGE_WIDTH);
        ReflectionTestUtils.setField(pdcidFont, "widths", sampleWidths);

        assertNotEquals(pdcidFont.getAverageFontWidth(), DEFAULT_WIDTH);
    }
}
