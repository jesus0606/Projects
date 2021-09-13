package Lab2;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private static Socket clientSocket = null; //сокет для общения
    private static ServerSocket server; // серверсокет
    private static BufferedReader in; // поток чтения из сокета
    private static BufferedWriter out; // поток записи в сокет

    public static void main(String[] args) {
        int num_random = numRandom();
        int intentos = 0;
        boolean adivinado = false;
        boolean errores = false;
        int num_user ;

        try {
            server = new ServerSocket(10000); // серверсокет прослушивает порт 4004
            System.out.println("Сервер запущен!"); // хорошо бы серверу
            // объявить о своем запуске
            clientSocket = server.accept(); // accept() будет ждать пока
            System.out.println(clientSocket + " connected!");
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            // и отправлять
            out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            int answer = numRandom();
            int numberOfTries = 3;
            for (int i = 0; i<numberOfTries;i++) {
                String a = in.readLine().trim();
                System.out.println(a);
                try {
                    int userAnswer = Integer.parseInt(a);
                    if (userAnswer == answer) {
                        out.write("SUCCESS\n");
                        out.flush();
                        break;
                    } else {
                        out.write("FAILED.. you have " + (2-i) +" tries left");
                        out.flush();
                    }
                } catch (NumberFormatException e) {
                    out.write("Enter number from 1-10");
                    out.flush();
                }
            }
//            while (true) {
//                String a = in.readLine();
//                Integer i = Integer.parseInt(a);
//                if (!adivinado) {
//                    out.writeBoolean(false);
//                    num_user = in.readInt(); //recoger num del usuario
//
//                    if (num_user == num_random) {
//                        adivinado = true;
//                        out.writeBoolean(true);
//                    } else if (num_user > num_random) out.writeUTF("El número es menor que " + num_user);
//                    else if (num_user < num_random) out.writeUTF("El número es mayor que " + num_user);
//                    intentos++;
//                } else if (adivinado) out.writeUTF("Has acertado! Con un total de " + intentos + " intentos.");
//                else {
//                    server.close();
//                    clientSocket.close();
//                }
//            }
        } catch (IOException e) {
            System.err.println(e);
        } finally {
            try {
                in.close();
                out.close();
                server.close();
            } catch (IOException ignored) {}
        }
    }
    private static int numRandom() {
        return (int) (Math.random() * 10 + 1);
    }
}

