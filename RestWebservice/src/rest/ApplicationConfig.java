package rest;
import java.util.Set;

import javax.ws.rs.core.Application;

@javax.ws.rs.ApplicationPath("rest")

public class ApplicationConfig extends Application {

@Override

public Set<Class<?>> getClasses(){

         Set<Class<?>> resources = new java.util.HashSet<>();

            addRestResourcesClasses(resources);

       return resources;

}

private void addRestResourcesClasses(Set<Class<?>> resources) {

       // configure the Demo class as part of the application resources
       resources.add(VotingService.class);
    }

}

