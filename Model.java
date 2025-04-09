public class Model extends Classifications {
    private String age, defaulted, housing, loan;
    private int total, yesCount, noCount; 
    private double yesProbability, noProbability, probabilityTotal, calcYes, calcNo;

    public Model() {
        yesProbability = 0;
        noProbability = 0;
        total = 0;
        yesCount = 0;
        noCount = 0;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setAge(int age) {
        this.age = super.age(age);
    }

    public String getDefaulted() {
        return defaulted;
    }

    public void setDefaulted(String defaulted) {
        this.defaulted = super.yesNoFilter(defaulted);
    }

    public String getHousing() {
        return housing;
    }

    public void setHousing(String housing) {
        this.housing = super.yesNoFilter(housing);
    }

    public String getLoan() {
        return loan;
    }

    public void setLoan(String loan) {
        this.loan = super.yesNoFilter(loan);
    }

    public int getTotal() {
        return total;
    }

    public int getYesCount() {
        return yesCount;
    }

    public void yes() {
        this.yesCount++;
    }

    public int getNoCount() {
        return noCount;
    }

    public void addTotal() {
        this.total++;
    }

    public void no() {
        this.noCount++;
    }

    public double getYesProbability() {
        return yesProbability;
    }

    public double getNoProbability() {
        return noProbability;
    }

    public double getProbabilityTotal() {
        return probabilityTotal;
    }

    public double getCalcYes() {
        return calcYes;
    }

    public double getCalcNo() {
        return calcNo;
    }

    public void calculate(int rows) {
        this.total = yesCount + noCount;
        this.yesProbability = (double) yesCount / (double) rows;
        this.noProbability = (double) noCount / (double) rows;
        this.probabilityTotal = this.yesProbability + this.noProbability;
        //this.calcYes = this.probabilityTotal * this.yesProbability;
        //this.calcNo = this.probabilityTotal * this.noProbability;
    }
}
