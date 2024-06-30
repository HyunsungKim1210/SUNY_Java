public class UseController {

    public static void main (String[] args) {

	EmailHost[] emailHost = new EmailHost[10];

	Controller mailHostLookup = new Controller(10);
	EmailHost[] emailHosts = new EmailHost[10];
	emailHost[0] = new EmailHost("sunyk.ac.kr");
	mailHostLookup.registerHost("sunyk.ac.kr", emailHost[0]);
	emailHost[1] = new EmailHost("sunykorea.ac.kr");
	mailHostLookup.registerHost("sunykorea.ac.kr", emailHost[1]);
	emailHost[2] = new EmailHost("stonybrook.edu");
	mailHostLookup.registerHost("stonybrook.edu", emailHost[2]);
	emailHost[3] = new EmailHost("cs.sunykorea.ac.kr");
	mailHostLookup.registerHost("cs.sunykorea.ac.kr", emailHost[3]);
	emailHost[4] = new EmailHost("cs.stonybrook.edu");
	mailHostLookup.registerHost("cs.stonybrook.edu", emailHost[4]);
	emailHost[5] = new EmailHost("gmail.com");
	mailHostLookup.registerHost("gmail.com", emailHost[5]);

        Message msg1 = new Message();

        System.out.println("msg1.from:    " + msg1.getFrom());
        System.out.println("msg1.to:      " + msg1.getTo());
        System.out.println("msg1.date:    " + msg1.getDate());
        System.out.println("msg1.subject: " + msg1.getSubject());
        System.out.println("msg1.length:  " + msg1.getLength());
        System.out.println("msg1.body:    " + msg1.getBody());
        System.out.println();

        msg1.setFrom("alee@sunyk.ac.kr");
        msg1.setTo("jdoe@gmail.com");
        msg1.setDate("Fri, Apr 19, 2022 at 11:41 PM");
        msg1.setSubject("Department Meeting");
        msg1.setBody("Please make it to the meeting. We will be discussing recent faculty hires and impending curriculum changes.");
        
        System.out.println("msg1.from:                   " + msg1.getFrom());
        System.out.println("msg1.to:                     " + msg1.getTo());
        System.out.println("msg1.date:                   " + msg1.getDate());
	System.out.println("msg1.returnReceiptRequested: " + msg1.isReturnReceiptRequested());
        System.out.println("msg1.subject:                " + msg1.getSubject());
        System.out.println("msg1.length:                 " + msg1.getLength());
        System.out.println("msg1.body:                   " + msg1.getBody());
        System.out.println();

        Message msg2 = new Message("onoh@gmail.com",
                                   "cjones@sunyk.ac.kr",
                                   "Tue, Apr 23, 2019 at 8:23 AM",
				   true,
                                   "Midterm 4",
                                   "Objects will be in the midterm 4.");

        
        System.out.println("msg2.from:                   " + msg2.getFrom());
        System.out.println("msg2.to:                     " + msg2.getTo());
        System.out.println("msg2.date:                   " + msg2.getDate());
	System.out.println("msg2.returnReceiptRequested: " + msg2.isReturnReceiptRequested());
        System.out.println("msg2.subject:                " + msg2.getSubject());
        System.out.println("msg2.length:                 " + msg2.getLength());
        System.out.println("msg2.body:                   " + msg2.getBody());
        System.out.println();

        msg2.setBody("Note: the 4th midterm EXCLUDES ArrayLists.");
        System.out.println("msg2.length:  " + msg2.getLength());
        System.out.println("msg2.body:    " + msg2.getBody());
        System.out.println();

        System.out.println("msg2 is important (T/F): " + msg2.isImportant());
        System.out.println("msg1 is important (T/F): " + msg1.isImportant());
        System.out.println();

        System.out.println("Printing msg1 using print:");
        msg1.print();

        System.out.println("Printing msg1 twice using toString this time:");
        System.out.println(msg1);
        System.out.println(msg1.toString()); // redundant, but does the same

        Message[] messages = new Message[25];
        messages[0] = msg1;
	EmailHost eh = mailHostLookup.getHostFor(msg1.getTo());
	eh.send(msg1);
        messages[1] = msg2;
	eh = mailHostLookup.getHostFor(msg2.getTo());
	eh.send(msg2);
        messages[2] = new Message("abc@sunykorea.ac.kr",
                                  "gabc@gmail.com",
                                  "Wed, Apr 2, 2021 at 9:12 PM",
				  false,
                                  "Test 1",
                                  "This is a test 1.");
	eh = mailHostLookup.getHostFor(messages[2].getTo());
	eh.send(messages[2]);
        messages[3] = new Message("nbc@sunyk.ac.kr",
                                  "gnbc@sunykorea.ac.kr",
                                  "Thu, Apr 3, 2022 at 7:24 AM",
				  false,
                                  "Meeting time changed",
                                  "This is a test 2.");
	eh = mailHostLookup.getHostFor(messages[3].getTo());
	eh.send(messages[3]);
        messages[4] = new Message("cbs@sunyk.ac.kr",
                                  "gcbs@cs.stonybrook.edu",
                                  "Fri, Apr 4, 2022 at 11:43 AM",
				  true,
                                  "New deadline",
                                  "With a kite and plunge.");
	eh = mailHostLookup.getHostFor(messages[4].getTo());
	eh.send(messages[4]);
        messages[5] = new Message("cbs@sunyk.ac.kr",
                                  "gcbs@cs.stonybrook.edu",
                                  "Fri, Apr 4, 2022 at 11:43 AM",
				  true,
                                  "New deadline",
                                  "With a kite and plunge.");
	eh = mailHostLookup.getHostFor(messages[5].getTo());
	eh.send(messages[5]);
        messages[6] = new Message("cbs@sunyk.ac.kr",
                                  "gcbs@cs.stonybrook.edu",
                                  "Fri, Apr 4, 2022 at 11:43 AM",
				  true,
                                  "New deadline",
                                  "With a kite and plunge.");
	eh = mailHostLookup.getHostFor(messages[6].getTo());
	eh.send(messages[6]);
        messages[7] = new Message("gcbs@cs.stonybrook.edu",
                                  "gabc@gmail.com",
                                  "Fri, Apr 4, 2022 at 11:43 AM",
				  true,
                                  "New deadline",
                                  "With a kite and plunge.");
	eh = mailHostLookup.getHostFor(messages[7].getTo());
	eh.send(messages[7]);
        messages[8] = new Message("gcbs@cs.stonybrook.edu",
                                  "cbs@sunyk.ac.kr",
                                  "Fri, Apr 4, 2022 at 11:43 AM",
				  true,
                                  "New deadline",
                                  "With a kite and plunge.");
	eh = mailHostLookup.getHostFor(messages[8].getTo());
	eh.send(messages[8]);

        messages[9] = new Message("alee@gmail.com",
                                  "gnbc@sunykorea.ac.kr",
                                  "Thu, Apr 5, 2022 at 7:24 AM",
				  false,
                                  "This weekend",
                                  "This is a test 9k.");
	eh = mailHostLookup.getHostFor(messages[9].getTo());
	eh.send(messages[9]);

        System.out.println("\nPrinting all of them with print:");
        for (int i = 0; i < messages.length; i++) {
	    if (messages[i] == null) {
		break;
	    }
            messages[i].print();
        }

        // This time using toString
        System.out.println("\nPrinting all of them with toString:");
        for (int i = 0; i < messages.length; i++) {
	    if (messages[i] == null) {
		break;
	    }
            System.out.println(messages[i]);
        }

        // This time using toString, but only important ones
        System.out.println("\nPrinting only important messages with toString:");
        for (int i = 0; i < messages.length; i++) {
	    if (messages[i] == null) {
		break;
	    }
            if (messages[i].isImportant()) {
                System.out.println(messages[i]);
            }
        }

	String userEmail1 = "gnbc@sunykorea.ac.kr";
	String userEmail2 = "gabc@gmail.com";

	String eBits[] = userEmail1.split("[@]");
	EmailHost theHost = null;
	if (eBits.length < 2) {
	    System.out.println("Bad email address.");
	} else {
	    String hostName = eBits[1];
	    theHost = mailHostLookup.findHost(hostName);
	}
	if (theHost != null) {
	    System.out.println("\n\nMessages for user gnbc@sunykorea.ac.kr");
	    Message[] userMessages = theHost.getMessagesForUser(userEmail1);
	    printMessageList(userMessages);
	}

    	eBits = userEmail2.split("[@]");
	theHost = null;
	if (eBits.length < 2) {
	    System.out.println("Bad email address.");
	} else {
	    String hostName = eBits[1];
	    theHost = mailHostLookup.findHost(hostName);
	}
	if (theHost != null) {
	    System.out.println("\n\nMessages for user gabc@gmail.com");
	    Message[] userMessages = theHost.getMessagesForUser(userEmail2);
	    printMessageList(userMessages);
	}
}

    public static void printMessageList(Message[] msgs) {
	for (int i = 0; i < msgs.length; i++) {
	    msgs[i].print();
	}
    }



}
