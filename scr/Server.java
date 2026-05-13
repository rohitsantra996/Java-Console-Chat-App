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


    public void startReading(){
    Runnable r1 =()->{
        System.out.println("Read started...");

        while(true){

           try {

               /* data accept kar raha h */
               String msg  = br.readLine();

               if (msg.equals("exit")){
                   System.out.println("Client Terminated the Chat");
                   socket.close();// connection off ho jayega
                   break;
               }
               System.out.println("Client : "+ msg);
           }catch (Exception e){
             e.printStackTrace();
           }
        }
    };
        new Thread(r1).start();

    }



    public void startWriting(){
        Runnable r2 =()->{
            System.out.println("Write started...");

            while (true){
                try {

                    // Reading message from keyboard
                    BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
                    // Taking input from server user
                    String content = br1.readLine();
                    // Sending message to client
                    out.println(content);

                    // flush() forces data to send immediately
                    out.flush();

                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        };
        new Thread(r2).start();
    }

    public static void main(String[] args) {
    new Server();
    }
}
