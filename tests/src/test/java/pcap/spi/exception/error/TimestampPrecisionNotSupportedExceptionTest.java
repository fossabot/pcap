/** This code is licenced under the GPL version 2. */
package pcap.spi.exception.error;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

/**
 * The requested time stamp precision is not supported ({@code -12}).
 *
 * @author <a href="mailto:contact@ardikars.com">Ardika Rommy Sanjaya</a>
 * @since 1.0.0
 */
@RunWith(JUnitPlatform.class)
public class TimestampPrecisionNotSupportedExceptionTest {

  @Test
  public void throwExceptionTest() {
    Assertions.assertThrows(
        TimestampPrecisionNotSupportedException.class,
        () -> {
          throw new TimestampPrecisionNotSupportedException("throwing exception.");
        });
  }
}
