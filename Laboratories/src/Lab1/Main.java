package Lab1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main{

    public static void main(String[] args) throws IOException {
        BufferedReader rd = new BufferedReader( new FileReader("C:/Users/user/Documents/running-example.csv"));
        String line = null;
        ArrayList lista = new ArrayList();
        String[] linea;
        while ((line = rd.readLine()) != null) {
            line = rd.readLine();
            System.out.println(line);
            linea = ";".split(line);
            lista.add(linea[3]);
        }
    }
}
