import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Path("/items")
public class Resource {

    private Connection conn;

    public Resource() {
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
    @Produces(MediaType.APPLICATION_XML)
    public Items getItems() {
        List<Item> items = new ArrayList<>();
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM Items;");

            while (rs.next()) {
                items.add(new Item(rs.getString("name"),
                        rs.getInt("quantity")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new Items(items);
    }

    @POST
    @Consumes(MediaType.APPLICATION_XML)
    public Response addItem(Item item) {
        if (item.isValid()) {
            try (PreparedStatement stmt = conn.prepareStatement(
                    "SELECT * FROM Items WHERE name = ?;",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE)) {
                stmt.setString(1, item.getName());
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    rs.updateInt("quantity", rs.getInt("quantity") +
                            item.getQuantity());
                    rs.updateRow();
                } else {
                    PreparedStatement insert = conn.prepareStatement(
                            "INSERT INTO Items SET name = ?, quantity = ?;");
                    insert.setString(1, item.getName());
                    insert.setInt(2, item.getQuantity());
                    insert.executeUpdate();
                }

                return Response.ok().build();
            } catch (SQLException e) {
                e.printStackTrace();
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .build();
            }
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @Path("/{name}")
    @DELETE
    public Response removeItem(@PathParam("name") String name) {

        try (PreparedStatement stmt = conn.prepareStatement(
                "DELETE FROM Items WHERE name = ?;")) {
            stmt.setString(1, name);
            int rows = stmt.executeUpdate();

            if (rows == 0) {
                return Response.status(Response.Status.NOT_FOUND)
                        .build();
            } else {
                return Response.ok().build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }
}