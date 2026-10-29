import java.net.*;                     // UDP Socket / Packet
import java.util.*;                    // Collections, StringJoiner
import java.math.*;                    // BigInteger neu can

public class Char07 {
    public static void main(String[] args) throws Exception {
        DatagramSocket s = new DatagramSocket();       // Tao UDP socket
        byte[] b = ";B23DCCN835;THAY_MA_CAU_HOI".getBytes("UTF-8"); // SUA MA DE!
        s.send(new DatagramPacket(b, b.length, InetAddress.getByName("36.50.135.242"), 2208)); // Gui
        DatagramPacket p = new DatagramPacket(new byte[8192], 8192); // Goi nhan
        s.receive(p);                               // Nhan tu server
        String[] a = new String(p.getData(), 0, p.getLength(), "UTF-8").split(";", -1); // Tach chuoi
        Map<Character, Integer> f = new HashMap<>();
        for (char c : a[1].toCharArray()) f.put(c, f.getOrDefault(c, 0) + 1);
        List<Character> ds = new ArrayList<>(f.keySet());
        ds.sort((x,y) -> !f.get(x).equals(f.get(y)) ? f.get(y)-f.get(x) : Character.compare(x,y));
        StringJoiner kq = new StringJoiner(",");
        for (char c : ds) kq.add(c + ":" + f.get(c)); // Tan suat giam, ky tu tang
        b = (a[0] + ";" + kq).getBytes("UTF-8"); // requestId;ketqua
        s.send(new DatagramPacket(b, b.length, p.getAddress(), p.getPort())); // Gui dap an
        s.close();                            // Dong socket
    }
}
