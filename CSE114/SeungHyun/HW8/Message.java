package HW8;

public class Message {
    private String from;
    private String to;
    private String date;
    private boolean getReturnReceiptRequested;
    private String subject;
    private String body;

    // Default constructor
    public Message() {
        this.from = "";
        this.to = "";
        this.date = "";
        this.getReturnReceiptRequested = false;
        this.subject = "";
        this.body = "";
    }

    // Constructor with arguments
    public Message(String from, String to, String date, boolean returnReceiptRequested, String subject, String body) {
        this.from = from;
        this.to = to;
        this.date = date;
        this.getReturnReceiptRequested = returnReceiptRequested;
        this.subject = subject;
        this.body = body;
    }

    // Getters
    public String getFrom() {
        return this.from;
    }

    public String getTo() {
        return this.to;
    }

    public String getDate() {
        return this.date;
    }

    public boolean isReturnReceiptRequested() {
        return this.getReturnReceiptRequested;
    }

    public String getSubject() {
        return this.subject;
    }

    public String getBody() {
        return this.body;
    }

    public int getLength() {
        return this.body.length();
    }

    // Setters
    public void setFrom(String from) {
        this.from = from;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setReturnReceiptRequested(boolean returnReceiptRequested) {
        this.getReturnReceiptRequested = returnReceiptRequested;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setBody(String body) {
        this.body = body;
    }

    // Method to check if the message is important
    public boolean isImportant() {
        String[] keywords = {"notice", "meeting", "deadline", "agent"};
        String lowerCaseSubject = subject.toLowerCase();
        int currentYear = 2024;
        if ((lowerCaseSubject.contains("notice") || lowerCaseSubject.contains("meeting") ||
                lowerCaseSubject.contains("deadline") || lowerCaseSubject.contains("agent")) &&
                date.contains(String.valueOf(currentYear))) {
            return true;
        }
        return false;
    }

    // Method to print the state information of the message
    public void print() {
        System.out.println("From: " + this.from);
        System.out.println("To: " + this.to);
        System.out.println("Date: " + this.date);
        System.out.println("Return Receipt Requested: " + this.getReturnReceiptRequested);
        System.out.println("Subject: " + this.subject);
        System.out.println("Body: " + this.body);
        System.out.println("Length of Body: " + this.getLength());
    }
}

