package HW8;

public class EmailHost {
    private final int capacity;
    private Message[] messages;
    private int nextMessage;
    private final String hostname;

    public EmailHost(String hostname) {
        this.hostname = hostname;
        this.capacity = 50;
        this.messages = new Message[capacity];
        this.nextMessage = 0;
    }

    public String getHostname() {
        return hostname;
    }

    public void send(Message msg) {
        if (!msg.getTo().endsWith("@" + hostname)) {
            System.out.println("Bad Host");
            return;
        }
        if (nextMessage < capacity) {
            messages[nextMessage++] = msg;
        } else {
            System.out.println("Host capacity reached");
        }
    }

    public Message[] getMessagesForUser(String userEmail) {
        int count = 0;
        for (Message msg : messages) {
            if (msg != null && msg.getTo().equals(userEmail)) {
                count++;
            }
        }
        Message[] userMessages = new Message[count];
        int index = 0;
        for (Message msg : messages) {
            if (msg != null && msg.getTo().equals(userEmail)) {
                userMessages[index++] = msg;
            }
        }
        return userMessages;
    }
}
