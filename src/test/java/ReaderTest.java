import com.MMTR.readers.DbReader;
import com.MMTR.readers.TxtReader;
import com.MMTR.servis.TypeLanguage;

public class ReaderTest {
    public static void main(String[] args) {
        TxtReader txt = new TxtReader("EnglishTranslater.txt");
        //txt.Add("tree ","дерево");
        //txt.delite("tree");
        System.out.println(txt.selectAll().toString());


//        DbReader dbReader = new DbReader("postgres","1120697", TypeLanguage.english);
//        dbReader.selectAll();
        //System.out.println(dbReader.selectAll().toString());




    }
}
