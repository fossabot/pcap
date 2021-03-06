package pcap.spi.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
public class WarningExceptionTest {

  @Test
  public void throwExceptionTest() {
    Assertions.assertThrows(
        WarningException.class,
        () -> {
          throw new WarningException("throwing exception.");
        });
  }
}
