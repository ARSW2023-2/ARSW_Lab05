package edu.eci.arsw.blueprints.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;

@Service("Submuestrear")
public class BluePrintSubmuestreando implements BlueprintFilters{

    @Override
    public Blueprint filtrar(Blueprint bp) {
        List<Point> puntos = bp.getPoints();
        List<Point> arregloPuntos = new ArrayList<Point>(); 

        boolean bandera = true;

        for(Point punto: puntos){
            if(bandera){
                arregloPuntos.add(punto);
                bandera = false;
            }else{
                bandera = true;
            }
        }

        return new Blueprint(bp.getAuthor(), bp.getName(), arregloPuntos);
        
    }

    
}
