import java.net.*;                     // Thư viện UDP
public class Char01 {
    public static void main(String[] args) throws Exception {
        DatagramSocket s = new DatagramSocket(); // Tạo socket
        byte[] b = ";B23DCCN835;f7SkrkJF".getBytes(); // Mã SV, mã đề
        s.send(new DatagramPacket(b,b.length,InetAddress.getByName("36.50.135.242"),2208)); // Gửi
        DatagramPacket p = new DatagramPacket(new byte[8192],8192); // Gói nhận
        s.receive(p);                     // Nhận dữ liệu
        String[] a = new String(p.getData(),0,p.getLength()).split(";",2); // Tách requestId và data
        String str = a[1];                 // Giữ nguyên chuỗi để đếm vị trí
        int[] f = new int[65536];          // Mảng đếm tần suất
        for(char c : str.toCharArray()) f[c]++; // Đếm ký tự
        char best = 0; int max = 0;        // Ký tự nhiều nhất
        for(char c : str.toCharArray())
            if(c != ' ' && f[c] > max) { max = f[c]; best = c; } // Bỏ qua dấu cách
        String kq = best + ":";            // Tạo kết quả
        for(int i=0;i<str.length();i++)
            if(str.charAt(i)==best) kq += (i+1)+","; // Vị trí từ 1, có dấu phẩy cuối
        b = (a[0]+";"+kq).getBytes();      // Ghép requestId
        s.send(new DatagramPacket(b,b.length,p.getAddress(),p.getPort())); // Gửi đáp án
        s.close();                         // Đóng socket
    }
}
