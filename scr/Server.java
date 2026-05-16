import java.net.*;
import java.io.*;

public class Server {

    ServerSocket server;
    Socket socket;
    // BufferedReader is used to receive data from client
    BufferedReader br;

    // PrintWriter is used to send data to client
    PrintWriter out;


   public Server(){
        try {
            server = new ServerSocket(7777);
            System.out.println("Server is ready to accept connection ");
            System.out.println("Waiting......");
            socket= server.accept();

          br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
          out = new PrintWriter(socket.getOutputStream());   // doubt hain


          startReading();
          startWriting();
        }catch (Exception e){

        }
    }
    //
     //

//

    //


    public void startReading(){
    Runnable r1 =()->{
        System.out.println("Read started...");
        try {
            while (true) {



                /* data accept kar raha h */
                String msg = br.readLine();

                if (msg.equals("exit")) {
                    System.out.println("Client Terminated the Chat");
                    socket.close();// connection off ho jayega
                    break;
                }
                System.out.println("Client : " + msg);

            }
            System.out.println("Connection Closed.....in Reader");

        }catch (Exception e){
            System.out.println("Connection Closed.....in Reader");
        }



    };
        new Thread(r1).start();

    }



    public void startWriting(){
        Runnable r2 =()->{
            System.out.println("Write started...");

            try {
                while (!socket.isClosed()) {


                    // Reading message from keyboard
                    BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
                    // Taking input from server user
                    String content = br1.readLine();
                    // Sending message to client
                    out.println(content);

                    // flush() forces data to send immediately
                    out.flush();

                    if (content.equals("exit")){
                        socket.close();
                        break;
                    }
                }
                System.out.println("Connection is closed in Writer");

            }catch (Exception e){
                System.out.println("Connection is closed in Writer");        }
        };
        new Thread(r2).start();
    }

    public static void main(String[] args) {
    new Server();
    }
}
