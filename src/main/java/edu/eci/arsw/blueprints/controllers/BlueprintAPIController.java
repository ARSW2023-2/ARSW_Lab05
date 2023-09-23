/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.controllers;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.services.BlueprintsServices;

/**
 *
 * @author hcadavid
 */
@RestController
@RequestMapping(value = "/blueprints")
public class BlueprintAPIController {

    @Autowired
    @Qualifier("Blueprint")
    private BlueprintsServices bps;
    
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getAllBlue(){
        try{
            //obtener datos que se enviarán a través del API
            return new ResponseEntity<>(bps.getAllBlueprints(),HttpStatus.ACCEPTED);

        }catch(Exception e){
            Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, e);
            return new ResponseEntity<>("Error al traer los Blueprint",HttpStatus.NOT_FOUND);
        }
    }
    
    @RequestMapping(value= "/{author}", method = RequestMethod.GET)
    public ResponseEntity<?> getAuthorBlue(@PathVariable String author){
        try{
            boolean autorNoExiste = bps.getBlueprintsByAuthor(author).isEmpty();
            if(autorNoExiste){
                return new ResponseEntity<>("El autor " + author + " no existe",HttpStatus.NOT_FOUND);
            }else{
                //obtener datos que se enviarán a través del API
                return new ResponseEntity<>(bps.getBlueprintsByAuthor(author),HttpStatus.ACCEPTED);
            }

        }catch(BlueprintNotFoundException e){
            Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, e);
            return new ResponseEntity<>("Error al traer el autor",HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value= "/{author}/{bpname}", method = RequestMethod.GET)
    public ResponseEntity<?> getBlue(@PathVariable String author, @PathVariable String bpname){
        try{
            boolean autorNoExiste = bps.getBlueprint(author,bpname) == null;
            if(autorNoExiste){
                return new ResponseEntity<>("El autor " + author + " no existe o el plano " + bpname + " no existe",HttpStatus.NOT_FOUND);
            }else{
                //obtener datos que se enviarán a través del API
                return new ResponseEntity<>(bps.getBlueprint(author, bpname),HttpStatus.ACCEPTED);
            }

        }catch(BlueprintNotFoundException e){
            Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, e);
            return new ResponseEntity<>("Error al traer el autor",HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/newBluePrint", method = RequestMethod.POST)
    public ResponseEntity<?> newBluePrint(@RequestBody Blueprint newBlue){
        try{
            bps.addNewBlueprint(newBlue);
            return new ResponseEntity<>(HttpStatus.CREATED);

        }catch(BlueprintPersistenceException ex){
            Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error al crear el BluePrint", HttpStatus.FORBIDDEN);

        }
    }

    @RequestMapping(value ="/{author}/{bpname}", method = RequestMethod.PUT)
    public ResponseEntity<?> setBluePriEntity(@PathVariable String author, @PathVariable String bpname, @RequestBody Blueprint blueprint){
        try{

            bps.setBluePrint(author, bpname, blueprint);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch(BlueprintNotFoundException ex){
            Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error al actualizar el BluePrint", HttpStatus.FORBIDDEN);

        }
    }
    
    
}

