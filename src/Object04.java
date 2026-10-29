import java.net.*;                     // UDP
import java.io.*;                      // Serialization
import java.util.*;
import UDP.Product;
public class Object04 {
    public static void main(String[] args) throws Exception {
        DatagramSocket s = new DatagramSocket(); // Socket
        byte[] b = ";B23DCCN835;THAY_MA_CAU_HOI".getBytes("UTF-8"); // Sua ma de
        s.send(new DatagramPacket(b,b.length,InetAddress.getByName("36.50.135.242"),2209)); // Gui
        DatagramPacket p = new DatagramPacket(new byte[8192],8192);
        s.receive(p); // Nhan 8 byte id + object
        byte[] id = Arrays.copyOfRange(p.getData(),0,8); // Giu nguyen requestId
        ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(p.getData(),8,p.getLength()-8));
        Product c = (Product) in.readObject(); // Doc doi tuong
        String[] a = c.name.trim().split("\\s+"); // Doi cho tu dau, tu cuoi
        String x = a[0]; a[0] = a[a.length-1]; a[a.length-1] = x;
        c.name = String.join(" ", a);
        c.quantity = Integer.parseInt(new StringBuilder("" + c.quantity).reverse().toString());
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bos.write(id); // 8 byte requestId
        ObjectOutputStream out = new ObjectOutputStream(bos);
        out.writeObject(c); out.flush(); // Ghi object da sua
        b = bos.toByteArray();
        s.send(new DatagramPacket(b,b.length,p.getAddress(),p.getPort())); // Gui lai
        s.close(); // Dong socket
    }
}
