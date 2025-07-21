// model/User.java
package Model;

public class User {
    private int    userId;
    private String name;

    public User(int userId, String name) {
        this.userId = userId;
        this.name   = name;
    }

    public User(){}

    public int    getUserId()          { return userId; }
    public void   setUserId(int id)    { this.userId = id; }
    public String getName()            { return name; }
    public void   setName(String n)    { this.name = n; }
}
