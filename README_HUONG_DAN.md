# BỘ 24 BÀI UDP JAVA — ÔN THI LẬP TRÌNH MẠNG

**Phạm vi:** 10 bài Data (`Data01`–`Data10`), 9 bài Character (`Char01`–`Char09`), 5 bài Object (`Object01`–`Object05`). Ngoài ra có 5 lớp Serializable trong `src/UDP/`.

**Tình trạng:** Toàn bộ 29 file `.java` đã **biên dịch thành công** bằng JDK 21. **Chưa kiểm thử kết nối Exam Server** cho các bài mới, vì vậy KHÔNG thể khẳng định tất cả Accepted. Một số ví dụ trong đề có mâu thuẫn/thiếu quy tắc, được liệt kê dưới đây.

## Cách dùng trong IntelliJ

1. Tạo Java Project, có `src` là Sources Root.
2. Chép các file `DataXX.java`, `CharXX.java`, `ObjectXX.java` trực tiếp vào `src`, và 5 class `Student.java`, `Customer.java`, `Employee.java`, `Product.java`, `Book.java` vào **package `UDP`** (`src/UDP`).
3. Mở đúng bài, sửa `THAY_MA_CAU_HOI` thành mã câu hỏi được cấp. Kiểm tra mã sinh viên `B23DCCN835`; nếu khác thì thay.
4. Chạy class chứa `main`. Chương trình gửi yêu cầu, nhận một gói UDP, xử lý và gửi đáp án rồi đóng socket.
5. Khi làm bài thật, **ưu tiên cổng trong đề cá nhân**: bản bộ đề cũ ghi `807/808/809`, các đề Exam Server bạn đã làm lại dùng `2207/2208/2209`. Mã nguồn trong gói này mặc định **2207/2208/2209** tương ứng Data/Character/Object. Nếu đề chính thức ghi cổng khác, phải sửa.
6. Biên dịch kiểm tra (PowerShell, từ thư mục `src`): `javac -encoding UTF-8 -d ../out *.java UDP/*.java`.

## Danh mục bài

| File | Nội dung |
|---|---|
| Data01 | Số còn thiếu (không trùng) |
| Data02 | Ghép các đoạn chuỗi theo thứ tự `text:position` |
| Data03 | Tổng chữ số của số nguyên lớn |
| Data04 | Số còn thiếu (có trùng) |
| Data05 | Max, min |
| Data06 | Giải mã Caesar dịch lùi |
| Data07 | Max thứ 2, min thứ 2 theo **giá trị phân biệt** |
| Data08 | n số nguyên tố đầu tiên |
| Data09 | Max từng cửa sổ k |
| Data10 | Tổng, hiệu số nguyên lớn với `BigInteger` |
| Char01 | Ký tự xuất hiện nhiều nhất, vị trí 1-based, có dấu phẩy cuối |
| Char02 | Tổng hai số nhị phân, kết quả thập phân |
| Char03 | Loại ký tự từ chuỗi 1 nếu có ở chuỗi 2 |
| Char04 | Viết hoa đầu mỗi từ |
| Char05 | Loại số, ký tự đặc biệt, ký tự lặp |
| Char06 | Đếm tần suất từng ký tự theo thứ tự xuất hiện |
| Char07 | Sắp xếp ký tự theo tần suất giảm, chữ tăng |
| Char08 | Chọn từ chỉ gồm nguyên âm (theo **câu chữ**, không theo ví dụ sai) |
| Char09 | Sắp xếp từ theo từ điển ngược |
| Object01 | Student: chuẩn hóa tên, email |
| Object02 | Employee: chuẩn tên, lương, ngày |
| Object03 | Customer: chuẩn tên, username, ngày sinh |
| Object04 | Product: đổi từ đầu/cuối, đảo chữ số quantity |
| Object05 | Book: chuẩn title, author, ISBN, ngày |

## Các điểm PHẢI CHÚ Ý để tránh WA

- **Mã câu hỏi riêng**: các ví dụ trong bộ đề không chắc là mã câu hỏi của tài khoản bạn. Phải thay đúng mã `qCode` được cấp.
- **Cổng UDP**: kiểm tra chính đề đang nộp, không mặc định mọi server dùng cùng một số cổng.
- **Char01**: phân biệt hoa/thường, tính cả khoảng trắng nếu được gửi; vị trí từ **1**; có dấu `,` sau vị trí cuối. Khi đồng hạng, code chọn ký tự xuất hiện trước tiên trong chuỗi, nhưng đề không nói rõ quy tắc đồng hạng.
- **Char08**: đề nói **tất cả ký tự là nguyên âm** nhưng ví dụ cho `This is an example` → `is,an` (hai từ có phụ âm). Code đang tuân thủ **câu chữ**, sẽ không chọn `is,an`. Cần xem Expected/Submitted thực tế để xác định luật chấm.
- **Data06**: mô tả Caesar hơi nhập nhằng giữa mã hóa và giải mã. Code giả định ký tự mã hóa đã dịch **tiến** `s`, giải mã thì dịch **lùi** `s`; cần đối chiếu test thực tế.
- **Data07**: code chọn phần tử lớn/nhỏ thứ hai **phân biệt**; nếu giảng viên tính cả giá trị lặp thì kết quả khác.
- **Object02**: đề ghi tăng `salary` với x = tổng chữ số **năm sinh**, nhưng lớp Employee chỉ có `hireDate`, không có ngày sinh. Code **tạm giả định** lấy tổng chữ số năm trong `hireDate`; cần làm rõ từ Expected hoặc code AC để xác nhận.
- **Object05**: đề chỉ có mẫu định dạng ISBN và tên tác giả `Họ, Tên`, chưa quy định rõ cách chia nhóm ISBN cho mọi đầu vào hoặc cách xử lý tên tác giả nhiều từ. Code dùng nhóm 3-1-2-6-1 nếu ISBN có 13 chữ số; author lấy từ đầu làm họ. **Chưa bảo đảm AC**.
- **Object04**: code đảo từ đầu và cuối (không đảo toàn bộ từ) và đảo các chữ số của quantity; nếu có số 0 cuối thì int làm mất số 0 đầu (ví dụ `120` → `21`) theo kiểu số nguyên.
- **Object**: Java serialization yêu cầu đúng class name/package `UDP.*`, `serialVersionUID`, tên và kiểu field. Các lớp `Student` và `Customer` đã được đối chiếu với repo Trung To; `Employee`, `Product`, `Book` được xây dựng theo **mô tả đề**, cần xác minh với lớp mẫu nếu server báo `InvalidClassException`.
- **Một lần chạy UDP thực**: kết quả `javac` thành công không phải bằng chứng Accepted. Có thể không nhận được response do firewall, server hoặc timeout. Muốn xác nhận phải kiểm tra mục Lịch sử giao tiếp của Exam Server.

## Công thức thuộc nhanh

**UDP String:** `DatagramSocket → send → receive → split(';') → xử lý → send → close`.

**UDP Object:** `send → receive → tách 8 byte requestId → ObjectInputStream → sửa field → ObjectOutputStream → ghép 8 byte → send → close`.

## Những bài đã có minh chứng Accepted từ cuộc trò chuyện

- **Data04** (tìm số còn thiếu, kể cả trùng): nhiều lịch sử Accepted.
- **Char01** (ký tự nhiều nhất): có một lần Accepted với ví dụ nhất định, nhưng cũng có Wrong Answer do tính sai vị trí và xử lý chuỗi.
- Các bài còn lại chỉ có **đặc tả**, **code trao đổi**, hoặc kết quả suy luận; chưa có log Accepted cụ thể trong tài liệu hiện có.

/*
DATA07
import java.net.*;                       // UDP
import java.util.*;                      // ArrayList, Collections

public class Data07 {
public static void main(String[] args) throws Exception {
DatagramSocket s = new DatagramSocket(); // Tạo socket
byte[] b = ";B23DCCN835;THAY_MA_CAU_HOI".getBytes(); // Mã SV, mã đề
s.send(new DatagramPacket(b,b.length,InetAddress.getByName("36.50.135.242"),2207)); // Gửi

        DatagramPacket p = new DatagramPacket(new byte[8192],8192); // Gói nhận
        s.receive(p);                       // Nhận dữ liệu

        String[] a = new String(p.getData(),0,p.getLength()).split(";",2); // Tách chuỗi
        List<Integer> so = new ArrayList<>(); // Danh sách giữ số trùng

        for(String x : a[1].split(","))
            so.add(Integer.parseInt(x.trim())); // Thêm số

        Collections.sort(so);                 // Sắp xếp tăng dần
        int min2 = so.get(1);                 // Số nhỏ thứ 2
        int max2 = so.get(so.size()-2);       // Số lớn thứ 2

        b = (a[0]+";"+max2+","+min2).getBytes(); // Ghép đáp án
        s.send(new DatagramPacket(b,b.length,p.getAddress(),p.getPort())); // Gửi
        s.close();                            // Đóng socket
    }
}

*/