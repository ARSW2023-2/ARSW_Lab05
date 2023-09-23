/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.persistence.impl;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.BlueprintsPersistence;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author hcadavid
 */
@Service ("Memory")
//@Qualifier("memory")
public class InMemoryBlueprintPersistence implements BlueprintsPersistence{

    private final Map<Tuple<String,String>,Blueprint> blueprints=new HashMap<>();

    public InMemoryBlueprintPersistence() {
        //load stub data
        Point[] pts=new Point[]{new Point(140, 140),new Point(115, 115)};
        Blueprint bp=new Blueprint("_authorname_", "_bpname_ ",pts);
        blueprints.put(new Tuple<>(bp.getAuthor(),bp.getName()), bp);

        Blueprint bp1=new Blueprint("Luisa", "Plano1",pts);
        Blueprint bp2=new Blueprint("Luisa", "Plano2",pts);

        Blueprint bp3=new Blueprint("karol", "Plano3",pts);
        Blueprint bp4=new Blueprint("Fernanda", "Plano4",pts);
        Blueprint bp5=new Blueprint("Daniela", "Plano5",pts);

        blueprints.put(new Tuple<>(bp.getAuthor(),bp.getName()), bp);
        blueprints.put(new Tuple<>(bp1.getAuthor(),bp1.getName()), bp1);
        blueprints.put(new Tuple<>(bp2.getAuthor(),bp2.getName()), bp2);

        blueprints.put(new Tuple<>(bp3.getAuthor(),bp3.getName()), bp3);
        blueprints.put(new Tuple<>(bp4.getAuthor(),bp4.getName()), bp4);
        blueprints.put(new Tuple<>(bp5.getAuthor(),bp5.getName()), bp5);
    }    
    
    @Override
    public void saveBlueprint(Blueprint bp) throws BlueprintPersistenceException {
        if (blueprints.containsKey(new Tuple<>(bp.getAuthor(),bp.getName()))){
            throw new BlueprintPersistenceException("The given blueprint already exists: "+bp);
        }
        else{
            blueprints.put(new Tuple<>(bp.getAuthor(),bp.getName()), bp);
        }        
    }

    @Override
    public Blueprint getBlueprint(String author, String bprintname) throws BlueprintNotFoundException {
        return blueprints.get(new Tuple<>(author, bprintname));
    }

    @Override
    public Set<Blueprint> getBlueprintsByAuthor(String author) throws BlueprintNotFoundException{
        return blueprints.entrySet().stream()
                        .filter((entry)->entry.getKey().getElem1().equals(author))
                        .map(entry -> entry.getValue())
                        .collect(Collectors.toSet());
    }

    @Override
    public Set<Blueprint> getAllBlueprints(){
        return blueprints.values().stream().collect(Collectors.toSet());
    }

    @Override
    public void setBluePrint(String author, String bprintname, Blueprint bluePrint) throws BlueprintNotFoundException{
        Blueprint blueAux = getBlueprint(author, bprintname);
        blueAux.setPuntos(bluePrint.getPoints());
    }
    
}
