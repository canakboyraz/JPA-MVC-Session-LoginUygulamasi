package com.works.controller;

import com.works.entities.Note;
import com.works.repositories.NoteRepository;
import com.works.repositories.ProductRepository;
import com.works.services.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class NoteController {
    final NoteService noteService;
    final NoteRepository noteRepository;

    @GetMapping("/note")
    public String note(Model model){
        model.addAttribute("note",noteService.allNote());
        return "/note";
    }
    @PostMapping("/noteSave")
    public String noteSave(Note note){
        noteService.save(note);
        return "redirect:/note";
    }
    @GetMapping("/deleteNote/{nid}")
    public String deleteNote(@PathVariable Long nid){
        noteService.deleteNote(nid);
        return "redirect:/note";
    }

}
