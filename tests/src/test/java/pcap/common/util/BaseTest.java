/** This code is licenced under the GPL version 2. */
package pcap.common.util;

import java.text.Format;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

/**
 * @author <a href="mailto:contact@ardikars.com">Ardika Rommy Sanjaya</a>
 * @since 1.0.0
 */
@RunWith(JUnitPlatform.class)
public class BaseTest {

  @Test
  public void doNoting() {
    DateTimeFormatter formatter =
        DateTimeFormatter.ofPattern(
            DateTimePattern.builder()
                .datePattern(DateTimePattern.DatePattern.DD_MM_YYYY_WITH_MINUS_AS_DELIMITER)
                .timePattern(DateTimePattern.TimePattern.HH_MM_SS_WITH_COLON_AS_DELIMITER)
                .timeBeforeDate(true)
                .build()
                .pattern());
    Format format = formatter.toFormat();
    Assertions.assertNotNull(format);
  }
}
