package app;

import filter.GsonProvider;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("")
public class App extends Application {

    @Override
    public Set<Object> getSingletons() {
        Set<Object> sets = new HashSet<>(2);
        sets.add(new UserController());
        sets.add(new PaymentController());
        return sets;
    }

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> sets = new HashSet<>(1);
        sets.add(GsonProvider.class);
        return sets;
    }
}
