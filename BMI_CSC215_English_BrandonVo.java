import java.util.Date;
import java.util.Scanner;
import java.text.SimpleDateFormat;

public class BMI_CSC215_English_BrandonVo {
    public static void main(String[] args){

        Scanner engScan = new Scanner(System.in);

        // Name
        String name = getName(engScan);

        // Height
        int height = getHeight(engScan, name);

        // Weight
        int weight = getWeight(engScan, name);

        //Summary Report
        displaySummary(name,height,weight);

        //Low and high value

    }


    /**************************/
    public static String getName(Scanner scan){
        System.out.print("Please enter your full name: ");
        return scan.nextLine();
    }

    /**************************/
    // Takes height input in form of feet and inches, returns result of convertInches
    public static int getHeight(Scanner scan, String name){
        System.out.print("Please enter height in feet and inches for " + name + ": ");
        int feet = scan.nextInt();
        int inches = scan.nextInt();
        return convertInches(feet, inches);
    }

    // Converts feet and inches, into just inches
    public static int convertInches(int ft, int in) {
        return (ft*12)+in;
    }

    /**************************/
    // Takes weight input and returns it
    public static int getWeight(Scanner scan, String name){
        System.out.print("Please enter weight in pounds for " + name + ": ");
        return scan.nextInt();
    }

    /**************************/
    // Displays summary
    public static void displaySummary(String name, int height, int weight){
        System.out.printf("%-3s%s%s%n", "--", "SUMMARY REPORT for ", name);
        System.out.printf("%-3s%-20s%s%n", "--", "Date and Time:", dateAndTime());
        System.out.printf("%-3s%-20s%s%n", "--", "BMI:", getBMI(height, weight));
        System.out.printf("%-3s%-20s%s%n", "--", "Weight Status:    ", bmiStatus(getBMI(height,weight)));
    }

    // Gets current date and time
    public static String dateAndTime(){
        String patternDate = "MMMM dd, yyyy";
        SimpleDateFormat date = new SimpleDateFormat(patternDate);

        String patternTime = "HH:mm:ss a";
        SimpleDateFormat time = new SimpleDateFormat(patternTime);

        String dateTime = date.format(new Date()) + " at " + time.format(new Date());

        return dateTime;
    }

    public static double getBMI(int height, int weight){
        return weight/(Math.pow(height,2))*703;
    }

    public static String bmiStatus(double bmi){
        String status = "";
        double roundedBMI =

        if (roundedBMI < 18.5)
        {
            status = "Underweight";
        }
        else if(18.5 <= roundedBMI && roundedBMI <= 24.9){
            status = "Healthy Weight";
        }
        else if(25 <= roundedBMI && roundedBMI <= 29.9){
            status = "Overweight";
        }
        else{
            status = "Obesity";
        }

        return status;
    }


}
