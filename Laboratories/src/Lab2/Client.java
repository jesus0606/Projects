package Lab2;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Client {

    private static Socket clientSocket; //сокет для общения
    private static BufferedReader reader; // нам нужен ридер читающий с консоли, иначе как
    // мы узнаем что хочет сказать клиент?
    private static BufferedReader in; // поток чтения из сокета
    private static BufferedWriter out; // поток записи в сокет

    public static void main(String[] args) throws IOException {

        Scanner scn = new Scanner(System.in);
        var numUser= 0;
        var mistakes = false;
        var guessed = false;

        try {
                // адрес - локальный хост, порт - 4004, такой же как у сервера
                clientSocket = new Socket("127.0.0.1", 10000); // этой строкой мы запрашиваем
                // у сервера доступ на соединение
                reader = new BufferedReader(new InputStreamReader(System.in));
                // читать соообщения с сервера
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                // писать туда же
                out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            do{
                //PEDIR NUMERO AL USUARIO
                do{
                    mistakes = false;
                    try{
                        System.out.print("Enter the numbers of the number of the 1-10:");
                        numUser = scn.nextInt();
                    }catch(InputMismatchException e){
                        System.out.print("\n enter numeric values!\n");
                        mistakes = true;
                        scn.nextLine(); //evitar bucle de error
                    }
                }while(mistakes);
                out.writeInt(numUser); //enviar num_usuario al server
                out.flush();
                guessed = in.readBoolean();
                System.out.println(in.readUTF());//mostrar resultado del server
            }while(!guessed);
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host ");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to "+ e);
            System.exit(1);
        }
        finally
        {
            scn.close();
            clientSocket.close();
        }

    }

}
