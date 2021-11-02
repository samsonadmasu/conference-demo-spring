package com.plular.conferencedemo.controllers;

import com.plular.conferencedemo.models.Speaker;
import com.plular.conferencedemo.repositories.SpeakerRepository;
import org.hibernate.sql.Delete;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/speakers")
public class SpeakerController{

    @Autowired
    private SpeakerRepository speakerRepository;

    @GetMapping
    public List<Speaker> list(){
        return speakerRepository.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public Speaker get(@PathVariable Long id){
        return speakerRepository.getOne(id);
    }


    @PostMapping
    public Speaker create(@RequestBody Speaker speaker){
        return speakerRepository.saveAndFlush(speaker);
    }


    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable final Long id){
        speakerRepository.deleteById(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public Speaker update(@PathVariable final Long id, @RequestBody final Speaker speaker){
        Speaker exitingSpeaker = speakerRepository.getById(id);
        BeanUtils.copyProperties(speaker, exitingSpeaker, "speaker_id");
        return speakerRepository.saveAndFlush(exitingSpeaker);
    }
}
