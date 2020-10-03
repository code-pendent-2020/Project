
    public class Message {
        private Customer cusInMes;
        private String recipient;
        private String cc;
        private String subject;
        Helper body = new Helper();


        public Message(String recipient,String cc,String subject){
            this.recipient=recipient;
            this.cc=cc;
            this.subject=subject;
        }

        //   public Customer getCusInMes(int customerId) {
        //       return cusInMes;
        //   }
        public Message() {

        }

        public String getRecipient(){
            return this.recipient;
        }
        public String getCc(){
            return this.cc;
        }
        public String getSubject(){
            return this.subject;
        }
        private Customer getCusInMes() {
            return this.cusInMes;
        }

        public void sendMessage() {
            int cusInput = body.getInt("Enter the recipients ID to send message:  ");
            if (getCusInMes().equals(cusInput)) {

                String typeMes = body.getInput("Type your message: ");
                System.out.print("Press enter to send the message.");
            }else{
                System.out.print("There is no customer available ith this Id :( ");
            }


            {

            }


        }}
         //the message menu Drake I dont remember that you asked for it?
    // public class MessageMenu {
    //    private static Helper input = new Helper();
    //
    //    public static void MessageMenu(){
    //        System.out.println("Find your new friends here: /n Whom do you want to start conversation with? ");
