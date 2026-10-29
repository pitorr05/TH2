import java.net.*;                     // UDP
import java.io.*;                      // Serialization
import java.util.*;
import UDP.Book;
public class Object05 {
    public static void main(String[] args) throws Exception {
        DatagramSocket s = new DatagramSocket(); // Socket
        byte[] b = ";B23DCCN835;THAY_MA_CAU_HOI".getBytes("UTF-8"); // Sua ma de
        s.send(new DatagramPacket(b,b.length,InetAddress.getByName("36.50.135.242"),2209)); // Gui
        DatagramPacket p = new DatagramPacket(new byte[8192],8192);
        s.receive(p); // Nhan 8 byte id + object
        byte[] id = Arrays.copyOfRange(p.getData(),0,8); // Giu nguyen requestId
        ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(p.getData(),8,p.getLength()-8));
        Book c = (Book) in.readObject(); // Doc doi tuong
        String[] a = c.title.trim().toLowerCase(Locale.ROOT).split("\\s+");
        String title = "";
        for (String t : a) title += Character.toUpperCase(t.charAt(0)) + t.substring(1) + " ";
        c.title = title.trim();
        String[] ten = c.author.trim().split("\\s+");
        if (ten.length > 1) c.author = ten[0] + ", " + String.join(" ", Arrays.copyOfRange(ten,1,ten.length));
        String digits = c.isbn.replaceAll("[^0-9]", "");
        if (digits.length() == 13) c.isbn = digits.substring(0,3)+"-"+digits.substring(3,4)+"-"+digits.substring(4,6)+"-"+digits.substring(6,12)+"-"+digits.substring(12);
        String[] d = c.publishDate.split("-"); c.publishDate = d[1] + "/" + d[0];
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bos.write(id); // 8 byte requestId
        ObjectOutputStream out = new ObjectOutputStream(bos);
        out.writeObject(c); out.flush(); // Ghi object da sua
        b = bos.toByteArray();
        s.send(new DatagramPacket(b,b.length,p.getAddress(),p.getPort())); // Gui lai
        s.close(); // Dong socket
    }
}


/*

import java.net.*;
import java.io.*;
import java.util.*;
import UDP.Book;

public class Object05 {
    public static void main(String[] args) throws Exception {
        DatagramSocket s = new DatagramSocket();

        byte[] b = ";B23DCCN835;THAY_MA_CAU_HOI".getBytes("UTF-8"); // SUA: Ma cau hoi
        s.send(new DatagramPacket(b,b.length,
                InetAddress.getByName("36.50.135.242"),2209)); // SUA: Cong neu can

        DatagramPacket p = new DatagramPacket(new byte[8192],8192);
        s.receive(p);

        byte[] id = Arrays.copyOfRange(p.getData(),0,8); // GIU NGUYEN 8 byte ID
        ObjectInputStream in = new ObjectInputStream(
                new ByteArrayInputStream(p.getData(),8,p.getLength()-8));
        Book c = (Book) in.readObject();

        // 1. Chuan hoa title
        String[] a = c.title.trim().toLowerCase(Locale.ROOT).split("\\s+");
        String title = "";
        for(String t : a)
            title += Character.toUpperCase(t.charAt(0)) + t.substring(1) + " ";
        c.title = title.trim();

        // 2. Chuan hoa author
        String[] ten = c.author.trim().split("\\s+");
        if(ten.length > 1)
            c.author = ten[0] + ", " +
                    String.join(" ",Arrays.copyOfRange(ten,1,ten.length));
        // KIEM TRA: Ho o dau hay o cuoi ten?

        // 3. Chuan hoa ISBN
        String digits = c.isbn.replaceAll("[^0-9]","");
        if(digits.length() == 13)
            c.isbn = digits.substring(0,3)+"-"
                   + digits.substring(3,4)+"-"
                   + digits.substring(4,6)+"-"
                   + digits.substring(6,12)+"-"
                   + digits.substring(12);
        // KIEM TRA: Server co dung nhom 3-1-2-6-1 khong?

        // 4. Chuan hoa ngay xuat ban
        String[] d = c.publishDate.split("-");
        c.publishDate = d[1] + "/" + d[0]; // yyyy-MM-dd -> MM/yyyy

        // 5. Gui lai Object
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bos.write(id); // 8 byte requestId

        ObjectOutputStream out = new ObjectOutputStream(bos);
        out.writeObject(c);
        out.flush();

        b = bos.toByteArray();
        s.send(new DatagramPacket(b,b.length,p.getAddress(),p.getPort()));
        s.close();
    }
}

*/
