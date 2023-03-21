
import javax.imageio.stream.FileImageOutputStream;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Warehouse {
    private Object[] wareHouse;
    private int amountOfObject;


    public Warehouse() throws IOException, URISyntaxException {

        //
        File file = new File("C:\\Quan_Ly_Ban_Hang\\File_txt\\Objects.txt");
        if(!file.exists())
        {
            file.createNewFile();
        }
        //
        int count = 0;
        //String path = ("C:\\For Code\\Java\\Code\\Quan_Ly_Ban_Hang_1\\src\\res\\");
        Scanner myReader = new Scanner(file);
        while(myReader.hasNext())
        {
            count ++;
            myReader.nextLine();
        }
        amountOfObject = count / 7;
        wareHouse = new Object[amountOfObject];
        myReader.close();
        Scanner myReader1 = new Scanner(file);
        for(int i = 0; i < amountOfObject; i++)
        {
            String a = null;
            String b = null;
            int c = 0;
            int d = 0;
            int e = 0;
            String f = null;
            String u = null;
            try{a = myReader1.nextLine();}catch(Exception ex) {a = "";};
            try{b = myReader1.nextLine();}catch(Exception ex) {b = "";};
            try{c = Integer.valueOf(myReader1.nextLine());}catch(Exception ex) {c = 0;};
            try{u = myReader1.nextLine();}catch(Exception ex) {u = "";}
            try{d = Integer.valueOf(myReader1.nextLine());}catch(Exception ex) {d = 0;};
            try{e = Integer.valueOf(myReader1.nextLine());}catch(Exception ex) {e = 0;};
            try{f = myReader1.nextLine();}catch(Exception ex) {f = "";};
            wareHouse[i] = new Object(a,b,c,u,d,e,f);
        }
        myReader1.close();
    }

    public Object[] getWareHouse()
    {
        return wareHouse;
    }

    public Object getObject(int i)
    {
        return wareHouse[i];
    }

    public int getAmountOfObjects()
    {
        return amountOfObject;
    }

    public void display() {

    }
}
