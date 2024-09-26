import java.util.Date;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.text.DecimalFormat;

public class BMI_CSC215_Metric_BrandonVo {
    public static void main(String[] args){

        Scanner scan = new Scanner(System.in);

        // Welcome Message
        welcome();

        System.out.println();

        // Name
        String name = getName(scan);

        // Height
        double height = getHeight(scan, name);

        // Weight
        double weight = getWeight(scan, name);

        System.out.println();

        //Summary Report
        displaySummary(name,height,weight);

        System.out.println();

        //Low and high value
        System.out.print("Please enter a LOW weight value in kilograms for " + name + ": ");
        double low = scan.nextDouble();
        System.out.print("Please enter a HIGH weight value in kilograms for " + name + ": ");
        double high = scan.nextDouble();

        System.out.println();

        //Table
        displayTable(low, high, height, weight);

        System.out.println("\n");

        //Ending Message
        endingMessage(name);
    }

    /**************************/
    /**WELCOME**/
    public static void welcome(){
        System.out.println("------------------------------------------------------------------------------------------");
        System.out.println("-- Welcome to:");
        System.out.println("--            BODY MASS INDEX (BMI) Computation, CSC 215, Metric Version");
        System.out.println("--                                                                   by Brandon Vo");
        System.out.println("------------------------------------------------------------------------------------------");
    }

    /*************************/
    /**NAME**/
    public static String getName(Scanner scan){
        System.out.print("Please enter your full name: ");
        return scan.nextLine();
    }

    /**************************/
    /**HEIGHT**/
    // Takes height user input in form of cm, returns height converted to meters
    public static double getHeight(Scanner scan, String name){
        System.out.print("Please enter height in centimeters for " + name + ": ");
        int cm = scan.nextInt();
        return cm*0.01;
    }


    /**************************/
    /**WEIGHT**/
    // Takes weight input and returns it
    public static double getWeight(Scanner scan, String name){
        System.out.print("Please enter weight in kilograms for " + name + ": ");
        return scan.nextDouble();
    }

    /**************************/
    /**SUMMARY REPORT**/
    // Displays summary
    public static void displaySummary(String name, double height, double weight){
        System.out.printf("%-3s%s%s%n", "--", "SUMMARY REPORT FOR ", name.toUpperCase());
        System.out.printf("%-3s%-20s%s%n", "--", "Date and Time:", dateAndTime());
        System.out.printf("%-3s%-20s%s%n", "--", "BMI:", getBMI(height, weight) +" (or "+ roundedBMI(getBMI(height,weight)) + " if rounded)");
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

    public static double getBMI(double height, double weight){
        return weight/(Math.pow(height,2));
    }

    public static double roundedBMI(double bmi){
        return Math.round(bmi*10.0)/10.0;
    }

    public static String bmiStatus(double bmi){
        String status = "";
        double roundedBMI = roundedBMI(bmi);

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

    /**************************/
    /**TABLE**/
    public static void displayTable(double low, double high, double height, double currentWeight){
        System.out.println("------------------------------------------------------");
        System.out.printf("|  %-6s    |  %-8s   |  %-21s  |%n", "WEIGHT", "BMI", "WEIGHT STATUS");
        System.out.println(" ----------------------------------------------------");

        String weightCategoryPattern = "###.00";
        DecimalFormat weightCategory = new DecimalFormat(weightCategoryPattern);

        boolean currentPrinted = false;

        // System.out.printf("|  %-6s    |  %-8s   |  %-21s  |%n", weightCategory.format(low), statusFormat(getBMI(height, low)), bmiStatus(getBMI(height,low)) + "     (LOW)");

        for (double i = low; i<high; i+=2.5){
            if(i == low){
                System.out.printf("|  %-6s    |  %-8s   |  %-21s  |%n", weightCategory.format(low), statusFormat(getBMI(height, low)), bmiStatus(getBMI(height,low)) + "     (LOW)");
            }
            else if(i > currentWeight && !currentPrinted) {
                System.out.printf("|  %-6s    |  %-8s   |  %-21s  |%n", weightCategory.format(currentWeight), statusFormat(getBMI(height, currentWeight)), bmiStatus(getBMI(height, currentWeight)) + " (this)");
                currentPrinted = true;
                System.out.printf("|  %-6s    |  %-8s   |  %-21s  |%n", weightCategory.format(i), statusFormat(getBMI(height,i)), bmiStatus(getBMI(height,i)));
            }
            else {
                System.out.printf("|  %-6s    |  %-8s   |  %-21s  |%n", weightCategory.format(i), statusFormat(getBMI(height,i)), bmiStatus(getBMI(height,i)));
            }
        }
        System.out.printf("|  %-6s    |  %-8s   |  %-21s  |%n", weightCategory.format(high), statusFormat(getBMI(height,high)), bmiStatus(getBMI(height,high))+ "        (HIGH)");
        System.out.println("------------------------------------------------------");

    }

    // Returns a format based on the weight status of provided BMI
    public static String statusFormat(double bmi){
        String status = bmiStatus(bmi);
        String pattern = "";

        if(status.equals("Underweight")) {
            pattern = "##.00";
        }
        else if (status.equals("Healthy Weight")) {
            pattern = "##.000";
        }
        else if (status.equals("Overweight")) {
            pattern = "##.0000";
        }
        else {
            pattern = "##.00000";
        }

        DecimalFormat statusFormat = new DecimalFormat(pattern);
        return statusFormat.format(bmi);

    }

    /**************************/
    /**ENDING**/
    public static void endingMessage(String name){
        System.out.println("The SFSU Mashouf Wellness Center is at 755 Font Blvd. \n");
        System.out.println("-------------------------------------------------------------------------------------");
        System.out.println("-- Thank you for using my program, " + name + "!");

        if (name.equals("Otto Minion")){
            System.out.println("-- Poopaye!!!");
        }
        else if (name.equals("Minnie Mouse")){
            System.out.println("-- Ear-esistable!!!");
        }
        else if (name.equals("Baymax Hamada")) {
            System.out.println("-- Sayōnara!!!");
        }
        else if (name.equals("Goofy Dog")){
            System.out.println("-- Woof Woof!!!");
        }
        else {
            System.out.println("Goodbye!!!");
        }

        System.out.println("-------------------------------------------------------------------------------------");
    }
}