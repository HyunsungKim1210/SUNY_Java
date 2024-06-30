//JihyeYoon_115960721_jihye.yoon@stonybrook.edu
public class Controller {
    private EmailHost[] eHosts;
    private int nextHost = 0;
    public Controller(int capacity){
        eHosts = new EmailHost[capacity];
    }

    public boolean registerHost(String hostname, EmailHost eHost) {
        if (nextHost >= eHosts.length)
            return false;
        for (int i = 0; i < nextHost; i++) {
            if (eHosts[i].getHostname().equals(hostname))
                return false;
        }
        eHosts[nextHost] = eHost;
        nextHost++;
        return true;
    }
    public EmailHost findHost(String hostname){
        for (int i = 0; i < nextHost; i++) {
            if(eHosts[i].getHostname().equals(hostname)){
                return eHosts[i];
            }
        }
        return null;
    }
    public EmailHost getHostFor(String address){
        String hostname = address.substring(address.indexOf("@") + 1);
        return findHost(hostname);
    }

}
