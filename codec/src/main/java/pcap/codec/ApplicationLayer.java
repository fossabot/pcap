/** This code is licenced under the GPL version 2. */
package pcap.codec;

import java.util.HashMap;
import java.util.Map;
import pcap.common.annotation.Inclubating;
import pcap.common.memory.Memory;
import pcap.common.util.NamedNumber;

/** @author <a href="mailto:contact@ardikars.com">Ardika Rommy Sanjaya</a> */
@Inclubating
public final class ApplicationLayer extends NamedNumber<Short, ApplicationLayer> {

  private static final Map<ApplicationLayer, Short> REGISTRY =
      new HashMap<ApplicationLayer, Short>();

  private static final Map<Short, AbstractPacket.Builder> BUILDER =
      new HashMap<Short, AbstractPacket.Builder>();

  public ApplicationLayer(Short value, String name) {
    super(value, name);
  }

  public Packet newInstance(Memory buffer) {
    AbstractPacket.Builder packetBuilder = BUILDER.get(this.value());
    if (packetBuilder == null) {
      if (buffer == null || buffer.capacity() <= 0) {
        return null;
      }
      return new UnknownPacket.Builder().build(buffer);
    }
    return packetBuilder.build(buffer);
  }

  public static ApplicationLayer valueOf(short value) {
    for (Map.Entry<ApplicationLayer, Short> entry : REGISTRY.entrySet()) {
      if (entry.getValue() == value) {
        return entry.getKey();
      }
    }
    return new ApplicationLayer((short) -1, "Unknown");
  }

  /**
   * @param applicationLayer application type.
   * @param packetBuilder packet builder.
   */
  public static synchronized void register(
      ApplicationLayer applicationLayer, AbstractPacket.Builder packetBuilder) {
    BUILDER.put(applicationLayer.value(), packetBuilder);
    REGISTRY.put(applicationLayer, applicationLayer.value());
  }
}
