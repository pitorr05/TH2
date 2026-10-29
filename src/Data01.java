import java.net.*;                     // UDP Socket / Packet
import java.util.*;                    // Collections, StringJoiner
import java.math.*;                    // BigInteger neu can

public class Data01 {
    public static void main(String[] args) throws Exception {
        DatagramSocket s = new DatagramSocket();       // Tao UDP socket
        byte[] b = ";B23DCCN835;THAY_MA_CAU_HOI".getBytes("UTF-8"); // SUA MA DE!
        s.send(new DatagramPacket(b, b.length, InetAddress.getByName("36.50.135.242"), 2207)); // Gui
        DatagramPacket p = new DatagramPacket(new byte[8192], 8192); // Goi nhan
        s.receive(p);                               // Nhan tu server
        String[] a = new String(p.getData(), 0, p.getLength(), "UTF-8").split(";", -1); // Tach chuoi
        int n = Integer.parseInt(a[1].trim()); // Gioi han 1..n
        Set<String> so = new HashSet<>(Arrays.asList(a[2].trim().split(","))); // Cac so co
        StringJoiner kq = new StringJoiner(","); // Ket qua
        for (int x = 1; x <= n; x++) if (!so.contains(""+x)) kq.add(""+x); // Tim thieu
        b = (a[0] + ";" + kq).getBytes("UTF-8"); // requestId;ketqua
        s.send(new DatagramPacket(b, b.length, p.getAddress(), p.getPort())); // Gui dap an
        s.close();                            // Dong socket
    }
}
