package edu.eci.arsw.blueprints.test.services;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.services.BluePrintSubmuestreando;

@ComponentScan("edu.eci.arsw")
@SpringBootTest(classes = BlueprintSubmuestreandoTest.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class BlueprintSubmuestreandoTest {
    @Autowired
    @Qualifier("Submuestrear")
    BluePrintSubmuestreando bps;

    @Test
    public void filtrarTest(){
        Point[] puntos1 = new Point[]{
            new Point(40,40),
            new Point (40,40),
            new Point (50,30),
            new Point (30,90)

        };

        Blueprint bp1 = new Blueprint("Luisa", "Plano1", puntos1);
        Blueprint bprAux1 = bps.filtrar(bp1);

        Point[] puntos2 = new Point[]{
            new Point(15,15),
            new Point(10, 10),
            new Point(25, 8),
            new Point(29, 80),
            new Point(5, 5)
        };

        Blueprint bp2 = new Blueprint("Luisa", "Plano1", puntos2);
        Blueprint bprAux2 = bps.filtrar(bp2);

        assertEquals(2, bprAux1.getPoints().size());
        assertEquals(3, bprAux2.getPoints().size());
    }

    
}
