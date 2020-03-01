import java.util.LinkedList;
import java.util.Queue;

public class LunchLineGeneric {

    public static void main(String[] args) {
        Queue<Student> lunchQueue = new LinkedList<Student>();
        Student bob = new Student("Bob");
        NotStudent ralph = new NotStudent("Ralph");
        Student jack = new Student("Jack");
        Student samantha = new Student("Samantha");

        lunchQueue.add(bob);
        //lunchQueue.add(ralph);
        lunchQueue.add(jack);
        lunchQueue.add(samantha);

        System.out.println("The following students are in line for lunch: ");
        for(Student student : lunchQueue)
            System.out.println(student.getName());

    }
    private static class Student {
        private String name;

        public Student(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    private static class NotStudent {
        private String name;

        public NotStudent(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
