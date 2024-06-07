import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class NoteFileStoring extends NoteApp{
    private static final String FILE_NAME = "notes.dat";

    public void saveNotes(List<Note> notes) throws IOException{
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))){
            oos.writeObject(notes);
        }
}
public List <Note> loadNotes() throws IOException, ClassNotFoundException{
        File file = new File(FILE_NAME);
        if(!file.exists())
            return new ArrayList<>();
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))){
            return (List<Note>) ois.readObject();
        }
    }
}
