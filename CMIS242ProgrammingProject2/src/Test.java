public class Test {
    public static void main(String[] args) {
        Automobile test =  new Automobile("Subaru WRX", 27495);
        System.out.println(test.toString());
        Electric electricTest = new Electric("Hyundai Ioniq EV", 33000, 3371);
        System.out.println(electricTest.toString());
        Hybrid hybridTest = new Hybrid("Toyota Prius", 24000, 58);
        System.out.println(hybridTest.toString());
    }
}
