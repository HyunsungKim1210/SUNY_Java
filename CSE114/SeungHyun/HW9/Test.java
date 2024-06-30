public class Test extends Parent{
    int expectedLifeTime;
        public String toString(){
            return "("+super.toString()+"One should live about"+this.expectedLifeTime+"years)";
        }

}

