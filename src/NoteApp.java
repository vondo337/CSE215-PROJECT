import javax.swing.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public abstract class NoteApp {
    protected List<Note> notes = new ArrayList<>();

    public void validateInput(String input) throws InvalidSymbolException {
        if (!input.matches("[a-zA-Z0-9\\s]+")) {
            throw new InvalidSymbolException("Title contains invalid symbols. Only letters and numbers are allowed.");
        }
    }

    public void createNote(String title, String description) throws InvalidSymbolException{
        validateInput(title);
        notes.add(new Note(title, description));
    }

    public Note viewNote(String title){
        for(Note note: notes){
            if(note.getTitle().equalsIgnoreCase(title)){
                return note;
            }
        }
        JOptionPane.showMessageDialog(null,"Note not found.");
        return null;
    }

    public void editNote(String title, String description){
        Note note = viewNote(title);
        if(note != null){
            note.setDescription(description);
        }
    }

    public void deleteNote(String title) {
        Iterator<Note> iterator = notes.iterator();
        while(iterator.hasNext()){
            Note note = iterator.next();
            if(note.getTitle().equalsIgnoreCase(title)){
                iterator.remove();
                JOptionPane.showMessageDialog(null,"Note has been deleted.");
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Note not found.");
    }
}
