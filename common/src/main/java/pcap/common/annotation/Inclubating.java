/** This code is licenced under the GPL version 2. */
package pcap.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Indicates that a feature is incubating (Unstable). This means that the feature is currently a
 * work-in-progress and may change at any time.
 *
 * <p>
 *
 * @author <a href="mailto:contact@ardikars.com">Ardika Rommy Sanjaya</a>
 * @since 1.0.0
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface Inclubating {}
