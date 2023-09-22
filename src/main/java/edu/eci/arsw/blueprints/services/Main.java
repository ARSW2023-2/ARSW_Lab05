package edu.eci.arsw.blueprints.services;

import java.util.Set;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;

public class Main {
    public static void main(String[] args) {
        ApplicationContext application = new ClassPathXmlApplicationContext("applicationContext.xml");
        BlueprintsServices bps = application.getBean(BlueprintsServices.class);
        Point[] puntos = new Point[]{new Point(0, 0), new Point(10, 10), new Point(4, 3)};
        Blueprint bp1 = new Blueprint("Luisa", "Casa1", puntos);

        Blueprint bp2 = new Blueprint("Karol", "Casa2", puntos);

        Blueprint bp3 = new Blueprint("Fernanda", "Casa3", puntos);

        try{
            bps.addNewBlueprint(bp1);
            bps.addNewBlueprint(bp2);
            bps.addNewBlueprint(bp3);

            Blueprint getBlue = bps.getBlueprint("Karol", "Casa2");
            System.out.println("-------------------------------------------------------------------");
            System.out.println(getBlue);
            System.out.println("-------------------------------------------------------------------");

            Set<Blueprint> getAllBlue = bps.getAllBlueprints();
            getAllBlue.forEach((entry)->{
                System.out.println(entry);
            });
            System.out.println("-------------------------------------------------------------------");

            Set<Blueprint> getAuthorBlue = bps.getBlueprintsByAuthor("Luisa");
            getAuthorBlue.forEach((entry)->{
                System.out.println(entry);
            });
            System.out.println("-------------------------------------------------------------------");

        }catch(BlueprintPersistenceException e){
            e.printStackTrace();

        }catch(BlueprintNotFoundException e){
            e.printStackTrace();

        }
    }
    
}
