import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * Implements API of shopping list.
 *
 * @author Vili Kinnunen vili.kinnunen@cs.tamk.fi
 * @version 2016.1121
 * @since 1.8
 */
@ApplicationPath("/")
public class Main extends Application{

    /**
     * Chooses to use Resource class as a resource.
     *
     * @return  Classes to use as resources
     */
    @Override
    public Set<Class<?>> getClasses() {
        HashSet h = new HashSet<Class<?>>();
        h.add( Resource.class );
        return h;
    }
}