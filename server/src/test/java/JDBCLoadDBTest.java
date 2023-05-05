import databaseElementLogic.DBCollectionLoader;
import models.Route;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashSet;

public class JDBCLoadDBTest {
    @Test
    public void test() throws SQLException, IOException {
        HashSet<Route> toSet = new HashSet<>();
        DBCollectionLoader<HashSet<Route>> dbCollectionLoader = new DBCollectionLoader<>(toSet);
        dbCollectionLoader.loadFromDB();
        System.out.println(toSet.size());
        //System.out.println(toSet.stream().findFirst().get());
    }
}
