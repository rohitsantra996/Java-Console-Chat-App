
import java.net.*;
import java.io.*;
public class Client {
    Socket socket;

    // BufferedReader is used to receive data from server
    BufferedReader br;

    // PrintWriter is used to send data to server
    PrintWriter out;

    public Client(){
       try {
           System.out.println(" sending request to server ");
          socket = new Socket("127.0.0.1",7777);
           System.out.println("Connnection Done...");



           br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
           out = new PrintWriter(socket.getOutputStream());   // doubt hain


           startReading();
           startWriting();

       }catch (Exception e){

       }
    }

    public void startReading(){
        Runnable r1 =()->{
            System.out.println("Read started...");

            while(true){

                try {

                    /* data accept kar raha h */
                    String msg  = br.readLine();

                    if (msg.equals("exit")){
                        System.out.println("Server Terminated the Chat");
                        socket.close();// connection off ho jayega
                        break;
                    }
                    System.out.println("Server : "+ msg);
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
        System.out.println("This is Client..");
  new Client();



    }
}
