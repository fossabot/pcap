/** This code is licenced under the GPL version 2. */
package pcap.api.internal;

import java.foreign.memory.Pointer;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Iterator;
import pcap.api.internal.foreign.pcap_mapping;
import pcap.api.internal.util.PcapAddressIterator;
import pcap.common.annotation.Inclubating;
import pcap.spi.Address;

/**
 * {@code Pcap} {@link Address} implementation
 *
 * @author <a href="mailto:contact@ardikars.com">Ardika Rommy Sanjaya</a>
 */
@Inclubating
public class PcapAddress implements Address {

  Address next;
  InetAddress address;
  InetAddress netmask;
  InetAddress broadcast;
  InetAddress destination;

  PcapAddress(pcap_mapping.pcap_addr pcap_addr) {
    this.address = parse(pcap_addr.addr$get());
    this.netmask = parse(pcap_addr.netmask$get());
    this.broadcast = parse(pcap_addr.broadaddr$get());
    this.destination = parse(pcap_addr.dstaddr$get());
    if (!pcap_addr.next$get().isNull()) {
      this.next = new PcapAddress(pcap_addr.next$get().get());
    }
  }

  public Address next() {
    return next;
  }

  public InetAddress address() {
    return address;
  }

  public InetAddress netmask() {
    return netmask;
  }

  public InetAddress broadcast() {
    return broadcast;
  }

  public InetAddress destination() {
    return destination;
  }

  @Override
  public Iterator<Address> iterator() {
    return new PcapAddressIterator(this);
  }

  @Override
  public String toString() {
    return new StringBuilder()
        .append("{\n")
        .append("\t\"address\": \"")
        .append(address)
        .append("\",\n")
        .append("\t\"netmask\": \"")
        .append(netmask)
        .append("\",\n")
        .append("\t\"broadcast\": \"")
        .append(broadcast)
        .append("\",\n")
        .append("\t\"destination\": \"")
        .append(destination)
        .append("\"\n")
        .append("}")
        .toString();
  }

  private InetAddress parse(Pointer<pcap_mapping.sockaddr> pointer) {
    try {
      if (!pointer.isNull()) {
        pcap_mapping.sockaddr sockaddr = pointer.get();
        if (sockaddr.sa_family$get() == 2) {
          byte[] data = new byte[4];
          for (int i = 0; i < data.length; i++) {
            data[i] = sockaddr.sa_data$get().get(i + 2);
          }
          return Inet4Address.getByAddress(data);

        } else if (sockaddr.sa_family$get() == 10) {
          byte[] data = new byte[16];
          for (int i = 0; i < data.length; i++) {
            data[i] = sockaddr.sa_data$get().get(i);
          }
          return Inet6Address.getByAddress(data);
        }
      } else {
        // LOGGER.warn("pointer (null)");
      }
    } catch (UnknownHostException e) {
    }
    return null;
  }
}
