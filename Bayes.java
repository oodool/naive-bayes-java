import java.util.ArrayList;
public class Bayes implements InputInterface{
    private ArrayList<Model> dataset = new ArrayList<Model>();
    private CSVReader reader = null;
    protected int totalClassifications = 0;
    protected String age, defaulted, housing, loan;

    public void initReader() {
        reader = new CSVReader("\\dataset\\bank-large.csv");
    }

    public void initReader(String file) {
        reader = new CSVReader("\\dataset\\" + file);
    }

    public void read() {
        dataset = reader.read();
        synchronized (dataset) {
            for (Model model : dataset) {
                model.calculate(reader.getTotalRow());
                totalClassifications++;
            }
        }

        //System.out.println("Jumlah klasifikasi: " + totalClassifications);
    }

    public int getTotalRow() {
        return reader.getTotalRow();
    }

    public double[] calculate() {
        boolean exist = false;
        double yes = 0;
        double no = 0;
        double divider = 0;
        double probabilityYes = 0;
        double probabilityNo = 0;
		double total = 0;

        for (Model m : dataset) {
            divider += (reader.getProbabilityYesTotal() * m.getYesProbability())
                    + (reader.getProbabilityNoTotal() * m.getNoProbability());
                    
            if (m.getAge() == age && m.getDefaulted() == this.defaulted
                    && m.getHousing() == this.housing && m.getLoan() == this.loan) {

                yes = m.getYesProbability();
                no = m.getNoProbability();
				
                exist = true;
            }
        }

        if (exist) {
            probabilityYes = (double) reader.getProbabilityYesTotal() * yes / divider;
            probabilityNo = (double) reader.getProbabilityNoTotal() * no / divider;

			double bersyarat = 0;
            double accurate = 0;
			
            if (probabilityYes > probabilityNo) {
                accurate = (probabilityYes / (probabilityYes + probabilityNo)) * 100;
            } else {
                accurate = (probabilityNo / (probabilityYes + probabilityNo)) * 100;
            }
			System.out.println("yes: " + yes + " no: " + no);
			bersyarat = calculate(yes, no);
            double[] result = { probabilityYes, probabilityNo, accurate, bersyarat };
            return result;

        }
        double[] result = { 0, 0, 0, 0 };
        return result;
    }
	
	private double calculate(double yes, double no){
		double result = 0;
		
		if (yes > no) {
			result = yes / reader.getProbabilityYesTotal();
			
		} else {
			result = no / reader.getProbabilityNoTotal();
			
		}
		
		return result;
	}
}
