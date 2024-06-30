public class Test {
    public static void main(String[] args) {
        Child c1 = new Child();
        Child c2 = new Child();
        Parent p1 = c1;
        Parent p2 = new Parent();
        System.out.println(p1.equals(c2));
        System.out.println(p2.equals(c2));
        //실제 내용을 따라간다!
    }
}

class Parent{
    int a;
    public Parent(){
        a = 3;
    }

    public boolean equals(Parent newP){
        if(a == newP.a){
            System.out.println("Parent equals");
            return true;
        }
        return false;
    }
}

class Child extends Parent{
    public boolean equals(Parent newP){
        if(a == newP.a){
            System.out.println("Child equals");
            return true;
        }
        return false;
    }
}