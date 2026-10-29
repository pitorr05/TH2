import java.net.*;
import java.io.*;
import java.util.*;
import UDP.Customer;
public class Object03 {
    public static void main(String[] args) throws Exception {
        DatagramSocket s = new DatagramSocket(); // Socket
        byte[] b = ";B23DCCN835;GnHwpeYV".getBytes("UTF-8"); // Sua ma de
        s.send(new DatagramPacket(b,b.length,InetAddress.getByName("36.50.135.242"),2209)); // Gui
        DatagramPacket p = new DatagramPacket(new byte[8192],8192);
        s.receive(p); // Nhan 8 byte id + object
        byte[] id = Arrays.copyOfRange(p.getData(),0,8); // Giu nguyen requestId
        ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(p.getData(),8,p.getLength()-8));
        Customer c = (Customer) in.readObject(); // Doc doi tuong
        String[] a = c.getName().trim().toLowerCase(Locale.ROOT).split("\\s+");
        String ten = a[a.length-1], user = "", name = "";
        for (int i = 0; i < a.length-1; i++) {
            user += a[i].charAt(0);
            name += Character.toUpperCase(a[i].charAt(0)) + a[i].substring(1) + " ";
        }
        c.setName(ten.toUpperCase(Locale.ROOT) + ", " + name.trim());
        c.SetUsername(user + ten);
        String[] d = c.getDayOfBirth().split("-");
        c.setDayOfBirth(d[1] + "/" + d[0] + "/" + d[2]);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bos.write(id); // 8 byte requestId
        ObjectOutputStream out = new ObjectOutputStream(bos);
        out.writeObject(c); out.flush(); // Ghi object da sua
        b = bos.toByteArray();
        s.send(new DatagramPacket(b,b.length,p.getAddress(),p.getPort())); // Gui lai
        s.close(); // Dong socket
    }
}
