import com.MMTR.TxtReader;

import java.io.*;

public class TxtReaderTest {
    public static void main(String[] args) {
        TxtReader txt = new TxtReader("C:\\Users\\Admin\\Desktop\\MMTR_test\\Translater.txt");
        System.out.println(txt.find("tree").toString());


//        File file = new File("C:\\Users\\Admin\\Desktop\\MMTR_test\\Translater.txt");
//        FileInputStream fis = null;
//        try {
//            fis = new FileInputStream(file);
//            byte[] byteArray = new byte[(int) file.length()];
//            System.out.println("zero: " + file.length());
//            System.out.println("fist: " + byteArray.length);
//            fis.read(byteArray);
//            System.out.println("second: " + fis);
//            String data = new String(byteArray);
//            System.out.println("therd: " + data.length());
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e){
//            e.printStackTrace();
//        }








    }
}
