package edu.eci.arsw.blueprints.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;

@Service("Redundante")
public class BlueprintRedundante implements BlueprintFilters{

    @Override
    public Blueprint filtrar(Blueprint bp) {
        List<Point> puntos = bp.getPoints();
        List<Point> arregloPuntos = new ArrayList<Point>(); 

        for(Point punto: puntos){
            boolean bandera = false;

            for(Point puntoAr: arregloPuntos){
                if(punto.equals(puntoAr)){
                    bandera = true;
                    break;
                }
            }

            if(!bandera){
                arregloPuntos.add(punto);
            }
        }

        return new Blueprint(bp.getAuthor(), bp.getName(), arregloPuntos);
    }
    
}
