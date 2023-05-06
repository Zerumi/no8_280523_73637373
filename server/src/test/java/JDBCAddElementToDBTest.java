import databaseLogic.databaseElementLogic.DBCollectionManager;
import models.Coordinates;
import models.Route;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.Instant;

public class JDBCAddElementToDBTest {
    @Test
    public void testAdd() throws SQLException, IOException {
        Route route = new Route() {{
            setName("ra9");
            setCoordinates(new Coordinates() {{
                setX(213);
                setY(342.34F);
            }});
            setCreationDate(Date.from(Instant.now()));
            setDistance(342);
        }};
        try (DBCollectionManager manager = new DBCollectionManager()) {
            System.out.println(manager.addElementToDataBase(route));
        }
    }
}
