/** This code is licenced under the GPL version 2. */
package pcap.common.memory;

import java.nio.ByteBuffer;
import pcap.common.annotation.Inclubating;
import pcap.common.util.Validate;

/** @author <a href="mailto:contact@ardikars.com">Ardika Rommy Sanjaya</a> */
@Inclubating
class PooledByteBuf extends ByteBuf implements Pooled {

  PooledByteBuf(int capacity, int maxCapacity) {
    super(capacity, maxCapacity);
  }

  PooledByteBuf(int capacity, int maxCapacity, int readerIndex, int writerIndex) {
    super(capacity, maxCapacity, readerIndex, writerIndex);
  }

  PooledByteBuf(
      int baseIndex,
      ByteBuffer buffer,
      int capacity,
      int maxCapacity,
      int readerIndex,
      int writerIndex) {
    super(baseIndex, buffer, capacity, maxCapacity, readerIndex, writerIndex);
  }

  @Override
  public Memory capacity(int newCapacity) {
    Validate.notIllegalArgument(
        newCapacity <= maxCapacity,
        String.format("newCapacity < maxCapacity: %s <= %s", newCapacity, maxCapacity));
    this.capacity = newCapacity;
    return this;
  }

  @Override
  public Memory copy(int index, int length) {
    byte[] b = new byte[length];
    int currentIndex = baseIndex + index;
    getBytes(currentIndex, b, 0, length);
    ByteBuffer copy = ByteBuffer.allocateDirect(length);
    copy.put(b);
    return new PooledByteBuf(
        baseIndex, copy, capacity(), maxCapacity(), readerIndex(), writerIndex());
  }

  @Override
  public Memory slice(int index, int length) {
    return new PooledSlicedByteBuf(
        baseIndex + index,
        buffer.duplicate(),
        length,
        maxCapacity(),
        readerIndex() - index,
        writerIndex() - index);
  }

  @Override
  public Memory duplicate() {
    ByteBuf duplicated =
        new PooledByteBuf(
            baseIndex, buffer.duplicate(), capacity(), maxCapacity(), readerIndex(), writerIndex());
    return duplicated;
  }

  @Override
  public void release() {
    Memories.offer(this);
  }
}
