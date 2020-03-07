import java.util.ArrayList;
import java.util.LinkedList;

public class Students {

    public static void main(String[] args) {
        LinkedList<String> lunchLine = new LinkedList<>();
        ArrayList<String> classRoster = new ArrayList<>();

        //Adding students to class roster in order of their class ids
        classRoster.add("Bob");
        classRoster.add("Janice");
        classRoster.add("Tom");
        classRoster.add("Alice");
        classRoster.add("Ralph");

        System.out.println("The student with id 3 is " + classRoster.get(3));

        lunchLine.add(classRoster.get(2));
        lunchLine.add(classRoster.get(4));
        lunchLine.add(classRoster.get(0));

        //After first student in line gets lunch, the line is easily changed to the students remaining in line
        System.out.println("The first student in line for lunch is: " + lunchLine.getFirst());
        lunchLine.removeFirst();
        System.out.println("Now the first student in line for lunch is: " + lunchLine.getFirst());

    }

}
