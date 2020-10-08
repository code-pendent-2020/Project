import java.util.ArrayList;
import java.util.UUID;

public class Message {


    private String messageId;
    private String subject;
    private Input input = Input.getInstance();
    private String body;
    private String senderID;
    private String sender;

    public Message(String subject, String body, String senderID, String sender){

    this.messageId= UUID.randomUUID().toString();
    this.body = body;
    this.subject = subject;
    this.senderID = senderID;
    this.sender = sender;
}

    public Message() {
    }


        public String getSubject() { return this.subject; }

        public String getMessageId() {return this.messageId; }


        public void setSubject(String subject) {
        this.subject = subject;
        }

        public Input getInput() {
        return input;
        }

        public void setInput(Input input) {
        this.input = input;
        }

        public String getBody() {
        return body;
        }

        public void setBody(String body) {
        this.body = body;
        }

        public String getSender() {
        return sender;
        }

        public void setSender(String sender) {
        this.sender = sender;
        }

        public String toString(){
            return "\nMessage ID: " + messageId + "\nSender's ID: " + senderID +"\nSender: " + sender + "\nTitle: " + subject + "\nMessage: " + body;
        }
}
        //moved it to storage,if you feel the need of any changes you can Axel
      /*  public ArrayList<Message>sendMessage(int messageId,String recipient) {
            String cusMessage=input.getInput("enter the customer Id of the person you want to send message to:  ");
            if (getCusInMes().equals(cusMessage)) {
                customerMessages.add(new Message(messageId,recipient));
                for( messageId=1;messageId>=100;messageId++){
                String typeMes = input.getInput("Type your message: ");
                System.out.print (messageId + typeMes);
                }
                System.out.print("Press enter to send the message.");
                return customerMessages;
            } else {
                System.out.print("There is no customer available with this Id :( ");
            }
            return null;
        }

    @Override
    public String toString() {
        String messageDisplay = this.messageId + " : " + this.customerMessages ;
        return messageDisplay;
    }


    public void viewMessages(){
            for (Message message: customerMessages) {
                System.out.println(toString());
            }
        }

        public void removeMessages(){
            viewMessages();
            int removeMesNum=input.getInt("Enter the message number you want to delete");
            this.customerMessages.removeIf(message -> message.messageId==(removeMesNum));
            System.out.println("The message is succesfully removed.");

        }*/

   /* @Override
    public static String toString() {
        String messageDisplay = this.messageId + " : " + this.customerMessages ;
        return messageDisplay;
   }*/
