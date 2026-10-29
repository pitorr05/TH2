package UDP;
import java.io.Serializable;
public class Customer implements Serializable {
    private static final long serialVersionUID = 20151107L;
    private String id, code, name, dayOfBirth, userName;
    public Customer(String id, String code, String name, String dayOfBirth, String userName) { this.id=id; this.code=code; this.name=name; this.dayOfBirth=dayOfBirth; this.userName=userName; }
    public String getName() { return name; }
    public String getDayOfBirth() { return dayOfBirth; }
    public void setName(String x) { name=x; }
    public void setDayOfBirth(String x) { dayOfBirth=x; }
    public void SetUsername(String x) { userName=x; }
}
