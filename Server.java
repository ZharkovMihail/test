import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private static Socket clientSocket; //сокет для общения
    private static ServerSocket server; // серверсокет
    private static BufferedReader in; // поток чтения из сокета
    private static BufferedWriter out; // поток записи в сокет

    public static void main(String[] args) {
        try {
            try {
                server = new ServerSocket(4004); // серверсокет прослушивает порт 4004
                System.out.println("Сервер запущен!"); // хорошо бы серверу
                //   объявить о своем запуске
                clientSocket = server.accept(); // accept() будет ждать пока
                //кто-нибудь не захочет подключиться
                try { // установив связь и воссоздав сокет для общения с клиентом можно перейти
                    // к созданию потоков ввода/вывода.
                    // теперь мы можем принимать сообщения
                    String word="  ";
                    String q="exit";
                    while(word!=q){
                        //System.out.println(".....");
                        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    // и отправлять
                        out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                        //System.out.println(".....2");
                        word = in.readLine(); // ждём пока клиент что-нибудь нам напишет
                        //System.out.println(".....3");
                        //System.out.println("пользователь написал : "+word);
                        System.out.println(word);
                        if(word.equals("exit")){
                            System.out.println("ура");
                        }
                        if(word.toString()=="exit"){
                        //if(true){
                            System.out.println("конец");
                        }
                        else{
                            System.out.println("сасай");
                        }
                        out.write("Привет, это Сервер! Подтверждаю, вы написали : " + word + "\n");
                    // не долго думая отвечает клиенту
                        out.flush(); // выталкиваем все из буфера
                    }

                } finally { // в любом случае сокет будет закрыт
                    //System.out.println("dfjkhgkdf");
                    clientSocket.close();
                    // потоки тоже хорошо бы закрыть
                    in.close();
                    out.close();
                }
            } finally {
                System.out.println("Сервер закрыт!");
                server.close();
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
