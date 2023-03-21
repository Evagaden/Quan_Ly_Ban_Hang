import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Test {
    public Test() throws URISyntaxException, IOException {
        //URL res = getClass().getResource("/File_txt/Objects");
        //String path = getClass().getResource("/res/File_txt/Objects.txt").toURI().getPath();
        //String a = path.substring(0,path.indexOf("1") + 1);
        //System.out.println(a);
        Path path = Paths.get(getClass().getResource("/res").toURI());
        System.out.println(path);

        //File file = new File(path.toString());
        //Scanner sc = new Scanner(file);
        //System.out.println(sc.nextLine());
        //System.out.println(new File(path.toString()).getPath());

        Path resourceDirectory = Paths.get("src" ,"res");
        //String absolutePath = resourceDirectory.toFile().getAbsolutePath();
        String absolutePath = resourceDirectory.toString();
        File file = new File(absolutePath + "/File_txt/Objects.txt");
        Scanner sc = new Scanner(file);
        System.out.println(sc.nextLine());
        System.out.println(file.getAbsolutePath());


    }
    public static void main(String[] args) throws URISyntaxException, IOException {
        new Test();
    }
}
