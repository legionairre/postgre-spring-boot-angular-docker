package com.example.demo.controller;

import com.example.demo.model.Kolon;
import com.example.demo.model.Response;
import com.example.demo.model.Tables;
import com.example.demo.model.Tablo;
import com.example.demo.repository.KolonRepository;
import com.example.demo.repository.TabloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class TabloController {


    @Autowired
    TabloRepository tabloRepository;
    @Autowired
    KolonRepository kolonRepository;


    @GetMapping("/tables")
    public ResponseEntity<List<Tablo>> getAllTutorials(@RequestParam(required = false) String tableName) {
        try {
            List<Tablo> tables = new ArrayList<>();

            if (tableName == null || tableName.isEmpty())
                tables = tabloRepository.findAll();
            else
                tables = tabloRepository.findByTableName(tableName);

            if (tables.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(tables, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/tables/{id}")
    public ResponseEntity<Response> getTutorialById(@PathVariable("id") long id) {
        Optional<Tablo> data = tabloRepository.findById(id);
        List<Object> lo = kolonRepository.findAllByTableId(id);
        Response response=new Response();
        response.setId(id);
        response.setTableName(data.get().getTableName());
        response.setColumns(lo);
        if (data.isPresent()) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/tables", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Tables> createTutorial(@RequestBody Tables tables) {
        try {

            tabloRepository.save(new Tablo(tables.getTableName()));
            List<Tablo> t = tabloRepository.findByTableName(tables.getTableName());
            for (Object column : tables.getColumns()) {
                Kolon k = new Kolon();
                k.setColumnName(((LinkedHashMap) column).get("column").toString());
                k.setTableId(t.get(0).getId());
                kolonRepository.save(k);
            }
            return new ResponseEntity<>(tables, HttpStatus.CREATED);


        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @PutMapping("/tables/{id}")
//    public ResponseEntity<Tables> updateTutorial(@PathVariable("id") long id, @RequestBody Tables tablo) {
//        Optional<Tables> tutorialData = tabloRepository.findById(id);
//
//        if (tutorialData.isPresent()) {
//            Tables _tablo = tutorialData.get();
//            _tablo.setTitle(tablo.getTitle());
//            _tablo.setDescription(tablo.getDescription());
//            _tablo.setPublished(tablo.isPublished());
//            return new ResponseEntity<>(tabloRepository.save(_tablo), HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }

    @DeleteMapping("/tables/{id}")
    public ResponseEntity<HttpStatus> deleteTablesById(@PathVariable("id") long id) {
        try {
            tabloRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/tables/column/{id}")
    public ResponseEntity<HttpStatus> deleteColumnsById(@PathVariable("id") long id) {
        try {
            kolonRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/tables")
    public ResponseEntity<HttpStatus> deleteAllTutorials() {
        try {
            tabloRepository.deleteAll();
            kolonRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}