import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.io.IOException;

public class Main extends JFrame {
    private NoteApp noteapp = new NoteApp() {};
    private NoteFileStoring notefilestoring = new NoteFileStoring();
    public Main(){
        setTitle("Note Application");
        setSize(1200,800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(7, 1));

        JButton createButton = new JButton("Create Note");
        JButton viewButton = new JButton("View note");
        JButton editButton = new JButton("Edit Note");
        JButton deleteButton = new JButton("Delete Note");
        JButton listButton = new JButton("View Note List");
        JButton searchButton = new JButton("Search Notes");
        JButton exitButton = new JButton("Exit");

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createNote();
            }
        });
        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewNote();
            }
        });
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editNote();
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteNote();
            }
        });
        listButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listNotes();
            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchNotes();
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exitApp();
            }
        });

        add(createButton);
        add(viewButton);
        add(editButton);
        add(deleteButton);
        add(listButton);
        add(searchButton);
        add(exitButton);

        loadNotes();
    }
    private void createNote(){
        String title = JOptionPane.showInputDialog(this, "Enter a note title: ");
        String description = JOptionPane.showInputDialog(this, "Enter note description: ");
        try{
            noteapp.createNote(title, description);
            JOptionPane.showMessageDialog(this, "Note created.");
        }catch(InvalidSymbolException e){
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void viewNote(){
        String title = JOptionPane.showInputDialog(this, "Enter a existing note title to view: ");
        Note note = noteapp.viewNote(title);
        if(note != null){
            JOptionPane.showMessageDialog(this, note.toString());
        }
    }

    private void editNote(){
        String title = JOptionPane.showInputDialog(this, "Enter the existing note title to edit: ");
        String newDescription = JOptionPane.showInputDialog(this, "Enter new description: ");
        noteapp.editNote(title, newDescription);
        JOptionPane.showMessageDialog(this,"Note has been updated");
    }

    private void deleteNote(){
        String title = JOptionPane.showInputDialog(this, "Enter the existing note title to delete: ");
        noteapp.deleteNote(title);
    }

    private void listNotes(){
        StringBuilder noteList = new StringBuilder();
        int i = 0;
        for(Note note: noteapp.notes){
            noteList.append(i + 1).append(". ").append(note.getTitle()).append("\n");
            i++;
        }
        JOptionPane.showMessageDialog(this, noteList.length() == 0 ? "No notes available" : noteList.toString());
    }

    private void searchNotes(){
        String keyword = JOptionPane.showInputDialog(this, "Search keyword: ");
        StringBuilder resultList = new StringBuilder();
        boolean found = false;
        int i = 0;
        for(Note note: noteapp.notes){
            if(note.getTitle().toLowerCase().contains(keyword.toLowerCase())){
                resultList.append(i + 1).append(". ").append(note.getTitle()).append("\n");
                found = true;
                i++;
            }
        }
        JOptionPane.showMessageDialog(this, found ? resultList.toString() : "No matching notes found.");
    }

    private void exitApp(){
        try{
            notefilestoring.saveNotes(noteapp.notes);
            JOptionPane.showMessageDialog(this, "Notes saved. Exiting Application.");
        }catch (IOException e){
            JOptionPane.showMessageDialog(this, "Error saving notes.Exiting Application.");
        }
        System.exit(0);
    }

    private void loadNotes(){
        try{
            noteapp.notes = notefilestoring.loadNotes();
        }catch (IOException | ClassNotFoundException e){
            JOptionPane.showMessageDialog(this, "Could not load notes.");
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main().setVisible(true);
            }
        });
    }
}