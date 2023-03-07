package com.example.song.repository;

import java.util.*;
import com.example.song.model.*;

public interface SongRepository{
    ArrayList<Song> getSongs();
    Song addSong(Song song);
    Song getSongbyId(int songId);
    Song updateSong(Song song,int songId);

    void deleteSong(int songId);
    
}