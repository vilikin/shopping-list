import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.*;
import java.util.ArrayList;

@Path("/items")
public class Items {
    private Connection conn;

    public Items() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            try {
                conn = DriverManager.getConnection("jdbc:mysql://" +
                        "mydb.tamk.fi/dbc4vkinnu1", "c4vkinnu", "Salasana123");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Item[] getItems() {
        try(Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM Items;");
            ArrayList<Item> items = new ArrayList<>();

            while (rs.next()) {
                items.add(new Item(rs.getString("name"),
                        rs.getInt("quantity")));
            }

            Item[] arr = new Item[items.size()];
            for (int i = 0; i < arr.length; i++) {
                arr[i] = items.get(i);
            }

            return arr;
        } catch (SQLException e) {
            e.printStackTrace();
            return new Item[0];
        }
    }


}

