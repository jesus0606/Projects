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
        int num_user = 0;

        try {
            server = new ServerSocket(10000); // серверсокет прослушивает порт 4004
            System.out.println("Сервер запущен!"); // хорошо бы серверу
            // объявить о своем запуске
            clientSocket = server.accept(); // accept() будет ждать пока
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            // и отправлять
            out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            while (true) {
                if (!adivinado) {
                    out.writeBoolean(false);
                    num_user = in.readInt(); //recoger num del usuario

                    if (num_user == num_random) {
                        adivinado = true;
                        out.writeBoolean(true);
                    } else if (num_user > num_random) out.writeUTF("El número es menor que " + num_user);
                    else if (num_user < num_random) out.writeUTF("El número es mayor que " + num_user);
                    intentos++;
                } else if (adivinado) out.writeUTF("Has acertado! Con un total de " + intentos + " intentos.");
                else {
                    server.close();
                    clientSocket.close();
                }
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }
    private static int numRandom() {
        return (int) (Math.random() * 10 + 1);
    }
}

