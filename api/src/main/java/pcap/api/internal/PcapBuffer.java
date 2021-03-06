/** This code is licenced under the GPL version 2. */
package pcap.api.internal;

import java.foreign.memory.Pointer;
import java.nio.ByteBuffer;
import pcap.common.annotation.Inclubating;
import pcap.spi.PacketBuffer;

/**
 * Wrapper for {@code pcap} packet buffer.
 *
 * @author <a href="mailto:contact@ardikars.com">Ardika Rommy Sanjaya</a>
 */
@Inclubating
public class PcapBuffer implements PacketBuffer {

  private final Pointer<Byte> pointer;
  private final ByteBuffer buffer;

  public PcapBuffer(Pointer<Byte> pointer, int size) throws IllegalAccessException {
    this.pointer = pointer;
    this.buffer = pointer.asDirectByteBuffer(size);
  }

  @Override
  public ByteBuffer buffer() {
    return buffer;
  }

  public Pointer<Byte> pointer() {
    return pointer;
  }
}
