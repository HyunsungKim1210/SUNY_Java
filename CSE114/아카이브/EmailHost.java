//JihyeYoon_115960721_jihye.yoon@stonybrook.edu
public class EmailHost {
    private final int capacity = 100;
    private Message[] messages = new Message[capacity];
    private int nextMessage = 0;
    private String hostname;

    public EmailHost(String hostname) {
        this.hostname = hostname;
    }

    public String getHostname() {
        return hostname;
    }

    public void send(Message msg) {
        if (!msg.getTo().endsWith(hostname)) {
            System.out.println("Bad Host");
            return;
        }
        if (nextMessage < capacity) {
            messages[nextMessage++] = msg;
        }
    }

    public Message[] getMessagesForUser(String userEmail) {
        int count = 0;
        for (int i = 0; i < messages.length; i++) {
            if (messages[i] != null && messages[i].getTo().equals(userEmail)) {
                count++;
            }
        }

        Message[] result = new Message[count];
        int index = 0;
        for (int i = 0; i < messages.length; i++) {
            if (messages[i] != null && messages[i].getTo().equals(userEmail)) {
                result[index++] = messages[i];
            }
        }
        return result;
    }
}
