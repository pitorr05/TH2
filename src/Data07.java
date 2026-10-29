import java.net.*;                     // UDP Socket / Packet
import java.util.*;                    // Collections, StringJoiner
import java.math.*;                    // BigInteger neu can

public class Data07 {
    public static void main(String[] args) throws Exception {
        DatagramSocket s = new DatagramSocket();       // Tao UDP socket
        byte[] b = ";B23DCCN835;THAY_MA_CAU_HOI".getBytes("UTF-8"); // SUA MA DE!
        s.send(new DatagramPacket(b, b.length, InetAddress.getByName("36.50.135.242"), 2207)); // Gui
        DatagramPacket p = new DatagramPacket(new byte[8192], 8192); // Goi nhan
        s.receive(p);                               // Nhan tu server
        String[] a = new String(p.getData(), 0, p.getLength(), "UTF-8").split(";", -1); // Tach chuoi
        TreeSet<Integer> so = new TreeSet<>(); // So phan biet, sap tang
        for (String t : a[1].split(",")) so.add(Integer.parseInt(t.trim()));
        int min2 = so.higher(so.first()), max2 = so.lower(so.last()); // Hang hai phan biet
        String kq = max2 + "," + min2;
        b = (a[0] + ";" + kq).getBytes("UTF-8"); // requestId;ketqua
        s.send(new DatagramPacket(b, b.length, p.getAddress(), p.getPort())); // Gui dap an
        s.close();                            // Dong socket
    }
}
