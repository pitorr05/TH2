import java.net.*;                     // UDP
import java.io.*;                      // Serialization
import java.util.*;
import UDP.Employee;
public class Object02 {
    public static void main(String[] args) throws Exception {
        DatagramSocket s = new DatagramSocket(); // Socket
        byte[] b = ";B23DCCN835;THAY_MA_CAU_HOI".getBytes("UTF-8"); // Sua ma de
        s.send(new DatagramPacket(b,b.length,InetAddress.getByName("36.50.135.242"),2209)); // Gui
        DatagramPacket p = new DatagramPacket(new byte[8192],8192);
        s.receive(p); // Nhan 8 byte id + object
        byte[] id = Arrays.copyOfRange(p.getData(),0,8); // Giu nguyen requestId
        ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(p.getData(),8,p.getLength()-8));
        Employee c = (Employee) in.readObject(); // Doc doi tuong
        String[] a = c.name.trim().toLowerCase(Locale.ROOT).split("\\s+");
        String name = "";
        for (String t : a) name += Character.toUpperCase(t.charAt(0)) + t.substring(1) + " ";
        c.name = name.trim(); // Chuan hoa ten
        String[] d = c.hireDate.split("-");
        int sum = 0;
        for (char x : d[0].toCharArray()) sum += x - '0'; // Tong chu so nam (theo mo ta)
        c.salary *= 1 + sum / 100.0; // Tang x phan tram
        c.hireDate = d[2] + "/" + d[1] + "/" + d[0];
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bos.write(id); // 8 byte requestId
        ObjectOutputStream out = new ObjectOutputStream(bos);
        out.writeObject(c); out.flush(); // Ghi object da sua
        b = bos.toByteArray();
        s.send(new DatagramPacket(b,b.length,p.getAddress(),p.getPort())); // Gui lai
        s.close(); // Dong socket
    }
}
