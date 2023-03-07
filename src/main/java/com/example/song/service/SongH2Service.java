 package com.example.song.service;


 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.http.HttpStatus;
 import org.springframework.jdbc.core.JdbcTemplate;
 import org.springframework.stereotype.Service;
 import org.springframework.web.server.ResponseStatusException;
 import java.util.ArrayList;

import com.example.song.repository.SongRepository;
import com.example.song.model.*;
 
 

 @Service
 public class SongH2Service implements SongRepository{
    @Autowired
    private JdbcTemplate db;
	private int songId;

    @Override
    public  ArrayList<Song> getSongs(){
      return (ArrayList<Song>) db.query("select * from playlist", new SongRowMapper());
    }

    @Override
    public Song addSong(Song song){
      db.update("insert into playlist(songName,lyricist,singer,musicDirector) values (?,?,?,?)",song.getSongName(),song.getLyricist(),song.getSinger(),song.getMusicDirector());

      Song newSong=db.queryForObject("select * from playlist where  songName=? and lyricist=?",new SongRowMapper(),song.getSongName(),song.getLyricist());

      return newSong;
    }
    @Override
    public Song getSongbyId(int songId){
      try{
            Song song = db.queryForObject("select * from playlist where  songId=?",new SongRowMapper(),songId); 
            return song;
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
       
    }
    @Override
    public Song updateSong(Song song,int songId){
         if(song.getSongName()!=null){
            db.update("update playlist set songName=? where songId=?",song.getSongName(),songId);
         }

         if(song.getLyricist()!=null){
            db.update("update playlist set lyricist=? where songId=?",song.getLyricist(),songId);
         }

         if(song.getSongName()!=null){
            db.update("update playlist set songName=? where songId=?",song.getSongName(),songId);
         }

         if(song.getSinger()!=null){
            db.update("update playlist set singer=? where songId=?",song.getSinger(),songId);
         }


         return getSongbyId(songId);
    }
    @Override
    public void deleteSong(int songId) {
        db.update("delete from playlist where songId = ?", songId);

    }


 }