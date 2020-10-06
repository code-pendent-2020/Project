import java.util.ArrayList;

public class Message {
        private Customer cusInMes;
        private String recipient;
        private int messageId;
        private String subject;
        private Input input = Input.getInstance();
        private ArrayList<Message> customerMessages=new ArrayList<>();

        public Message(int messageId,String recipient,String subject){
            this.recipient=recipient;
            this.subject=subject;
            this.messageId=messageId;
        }
        public Message() {

        }

        public String getRecipient(){ return this.recipient;  }

        public String getSubject(){ return this.subject; }

        public int getMessageId(){return this.messageId; }

        private Customer getCusInMes() {
            return this.cusInMes;
        }
        public ArrayList<Message>sendMessage(String recipient,String subject) {
            String cusMessage=input.getInput("enter the customer Id of the person you want to send message to:  ");
            if (getCusInMes().equals(cusMessage)) {
                customerMessages.add(new Message(messageId,recipient, subject));
                for(int messageId=1;messageId>=100;messageId++){
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

        }


    }