package CSE114_HW5_JihyeYoon_115960721;
//JihyeYoon_115960721_jihye.yoon@stonybrook.edu

public class UseMessage {
    public static void main(String[] args) {
        Message msg1 = new Message();
        System.out.println("Message 1: ");
        msg1.print();

        msg1.setFrom("alee@sunykorea.ac.kr");
        msg1.setTo("jdoe@gmail.com");
        msg1.setDate("Fri, Apr 19, 2019 at 11:28 PM");
        msg1.setSubject("Meeting time");
        msg1.setBody("Hey, John, The meeting was moved up to this coming Tuesday. We will be discussing the new proposed feature.");
        msg1.setReturnReceiptRequested(true);
        msg1.print();

        Message msg2 = new Message("ailen@sunykorea.ac.kr", "jack@gmail.com","Fri, Apr 19, 2024 at 4:57 PM","Meeting time","Hey, Jijye, The meeting was moved up to this coming Monday. We will be discussing the new proposed feature.",true);
        System.out.println("Message 2: ");
        msg2.print();

        msg2.setBody("It is too hot to hike and too cold to plunge.");

    }

}
