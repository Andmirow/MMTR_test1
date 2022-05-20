import com.MMTR.readers.DbReader;

public class ReaderTest {
    public static void main(String[] args) {
//        TxtReader txt = new TxtReader("C:\\Users\\Admin\\Desktop\\MMTR_test\\Translater.txt");
//        //txt.Add("tree ","дерево");
//        txt.delite("tree");
//        System.out.println(txt.selectAll().toString());

        DbReader dbReader = new DbReader("postgres","1120697");
        System.out.println(dbReader.selectAll().toString());




    }
}
