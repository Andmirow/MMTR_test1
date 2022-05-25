
import com.MMTR.readers.TxtReader;
import com.MMTR.readers.db_reader.ConnectDB;
import com.MMTR.readers.db_reader.DbReader;
import com.MMTR.readers.db_reader.SettingDb;
import com.MMTR.servis.TypeLanguage;
import com.MMTR.servis.UserDAO;


public class ReaderTest {
    public static void main(String[] args) {
        //TxtReader txt = new TxtReader("EnglishTranslater.txt");
        //txt.Add("tree ","дерево");
        //txt.delite("tree");
        //System.out.println(txt.selectAll().toString());


        SettingDb settingDb = new SettingDb("flyway.english_words","english_word","flyway.russian_words","russian_word","flyway.english_transtator","english_id","russian_id");
        ConnectDB connectDB = new ConnectDB("postgres","1120697");
        DbReader dbReader = new DbReader(settingDb,connectDB);
        UserDAO userDAO = new UserDAO(dbReader,connectDB);
        userDAO.delite("wind");

        System.out.println(userDAO.selectAll().toString());





    }
}
