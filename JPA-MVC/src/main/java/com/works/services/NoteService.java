package com.works.services;

import com.works.entities.Note;
import com.works.repositories.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final NoteRepository noteRepository;

    public Note save(Note note){
        return noteRepository.save(note);
    }

    public List<Note> allNote(){
        return noteRepository.findAll();
    }
    public void deleteNote(Long nid){
        noteRepository.deleteById(nid);
    }
}
