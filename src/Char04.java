import java.net.*;
import java.util.*;
import java.math.*;

public class Char04 {
    public static void main(String[] args) throws Exception {
        DatagramSocket s = new DatagramSocket();
        byte[] b = ";B23DCCN835;UCmKFaXT".getBytes("UTF-8");
        s.send(new DatagramPacket(b, b.length, InetAddress.getByName("36.50.135.242"), 2208));
        DatagramPacket p = new DatagramPacket(new byte[8192], 8192);
        s.receive(p);
        String[] a = new String(p.getData(), 0, p.getLength(), "UTF-8").split(";", -1);
        String kq = "";
        for (String t : a[1].trim().toLowerCase(Locale.ROOT).split("\\s+"))
            if (!t.isEmpty()) kq += Character.toUpperCase(t.charAt(0)) + t.substring(1) + " ";
        kq = kq.trim();
        b = (a[0] + ";" + kq).getBytes("UTF-8");
        s.send(new DatagramPacket(b, b.length, p.getAddress(), p.getPort()));
        s.close();
    }
}
