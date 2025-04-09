public class Classifications {
    public String age(int age) {
        if (age >= 12 && age <= 25) {
            return "Remaja";
        } else if (age >= 26 && age <= 45) {
            return "Dewasa";
        } else if (age >= 46 && age <= 65) {
            return "Lansia";
        } else {
            return "Manula";
        }
    }

    public String yesNoFilter(String defaulted) {
        if (defaulted.toLowerCase().equals("yes") || defaulted.toLowerCase().equals("ya") 
                || defaulted.toLowerCase().equals("y")) {
            return "yes";
        } else {
            return "no";
        }
    }
}