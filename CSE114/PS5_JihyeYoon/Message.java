package CSE114_HW5_JihyeYoon_115960721;
//JihyeYoon_115960721_jihye.yoon@stonybrook.edu

public class Message {
    private String from;
    private String to;
    private String date;
    private String subject;
    private String body;
    private boolean returnReceiptRequested;

    public String getFrom(){
        return from;
    }
    public String getTo(){
        return to;
    }
    public String getDate(){
        return date;
    }
    public String getSubject(){
        return subject;
    }
    public String getBody(){
        return body;
    }
    public boolean getReturnReceiptRequested(){
        return returnReceiptRequested;
    }
    public int getLength(){
        return body.length();
    }
    public void setFrom(String from){
        this.from = from;
    }
    public void setTo(String to){
        this.to = to;
    }
    public void setDate(String date){
        this.date = date;
    }
    public void setSubject(String subject){
        this.subject = subject;
    }
    public void setBody(String body){
        this.body = body;
    }
    public void setReturnReceiptRequested(boolean returnReceiptRequested){
        this.returnReceiptRequested = returnReceiptRequested;
    }
    public Message(){
        this.from = "";
        this.to = "";
        this.date = "";
        this.subject = "";
        this.body = "";
        this.returnReceiptRequested = false;
    }
    public Message(String from, String to, String date, String subject, String body, boolean returnReceiptRequested){
        this.from = from;
        this.to = to;
        this.date = date;
        this.subject = subject;
        this.body = body;
        this.returnReceiptRequested = returnReceiptRequested;
    }

    public void print (){
        System.out.println("from: " + this.from);
        System.out.println("to: " + this.to);
        System.out.println("date: " + this.date);
        System.out.println("subject: " + this.subject);
        System.out.println("body: " + this.body);
        System.out.println("return Receipt Requested: " + this.returnReceiptRequested);
    }

    public boolean isImportant(){
       String keywords = subject.toLowerCase();
       if(date.contains("2024") &&
               (keywords.contains("notice") || keywords.contains("meeting") ||
                       keywords.contains("deadline") || keywords.contains("agent"))){
           return true;
       }
       return false;
    }


}
