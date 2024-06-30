package HW8;

public class Controller {
    private final EmailHost[] eHosts;
    private int nextHost;

    public Controller(int capacity) {
        this.eHosts = new EmailHost[capacity];
        this.nextHost = 0;
    }

    public boolean registerHost(String hostName, EmailHost eHost) {
        if (nextHost < eHosts.length) {
            for (EmailHost host : eHosts) {
                if (host != null && host.getHostname().equals(hostName)) {
                    return false; // Host already exists
                }
            }
            eHosts[nextHost++] = eHost;
            return true;
        }
        return false; // Capacity exceeded
    }

    public EmailHost findHost(String hostname) {
        for (EmailHost host : eHosts) {
            if (host != null && host.getHostname().equals(hostname)) {
                return host;
            }
        }
        return null; // Host not found
    }

    public EmailHost getHostFor(String address) {
        String[] parts = address.split("@");
        if (parts.length == 2) {
            String hostname = parts[1];
            return findHost(hostname);
        }
        return null; // Invalid address
    }
}
