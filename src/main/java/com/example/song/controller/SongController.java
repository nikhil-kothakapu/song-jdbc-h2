package com.example.song.controller;

import org.springframework.web.bind.annotation.*;
import java.util.*;

import com.example.song.model.Song;
import com.example.song.service.SongH2Service;

import org.springframework.beans.factory.annotation.Autowired;


@RestController
 public class SongController{
   @Autowired
   public SongH2Service ss;
   @GetMapping("/songs")
   public ArrayList<Song> getSongs(){
    return ss.getSongs();
      
   }
   @PostMapping("/songs")
   public Song addSong(@RequestBody Song song){
      return ss.addSong(song);
   }

   @GetMapping("/songs/{songId}")
   public Song getSongbyId(@PathVariable("songId") int songId){
    return ss.getSongbyId(songId);
   }

   @PutMapping("/songs/{songId}")
   public Song updateSong(@RequestBody Song song,@PathVariable("songId") int songId){
      return ss.updateSong(song,songId);
   }

   @DeleteMapping("/songs/{songId}")
   public void deleteSong(@PathVariable("songId") int songId){
      ss.deleteSong(songId);
   }
}