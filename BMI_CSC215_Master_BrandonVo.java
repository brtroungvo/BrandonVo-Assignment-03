import java.util.Scanner;

public class BMI_CSC215_Master_BrandonVo {
    public static void main(String[] args) {

    }

    public static void pickVersion(){
        System.out.println("My CSC 215 BMI Calculator Projects:");
        System.out.println("   1. BMI, English");
        System.out.println("   2. BMI, Metric\n");
        System.out.println("[ USER MANUAL ] Enter an exclamation mark ! to end.");
        System.out.print("Please enter the version you want to try: ");

        Scanner scan = new Scanner(System.in);
        String version = scan.nextLine();
        version = fixString(version);

        while (!version.equals("english") || !version.equals("metric")) {
            System.out.println("Please enter the version you want to try: ");
            version = scan.nextLine();
            version = fixString(version);
        }

        if(version.equals("english")){
            EnglishBMI.main(new String[0]);
        }
        if(version.equals("metric")){
            MetricBMI.main(new String[0]);
        }
    }
}
