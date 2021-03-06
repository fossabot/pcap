/** This code is licenced under the GPL version 2. */
package pcap.spi;

/**
 * A callback function used to handle pcap_loop(..).
 *
 * @author <a href="mailto:contact@ardikars.com">Ardika Rommy Sanjaya</a>
 * @since 1.0.0
 */
@FunctionalInterface
public interface PacketHandler<T> {

  void gotPacket(T args, PacketHeader header, PacketBuffer buffer);
}
