import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class CSVReader extends Classifications {
    private String file_path;
    private int rows = 0;
    public double outputYes = 0;
    public double outputNo = 0;

    public CSVReader(String file_path) {
        this.file_path = file_path;
    }

    public ArrayList<Model> read() {
        ArrayList<Model> models = new ArrayList<Model>();
        try {
            Model model = new Model();
            int columns = 1;
            Scanner sc = new Scanner(new File(this.getWorkingDirectory() + '\\' + this.file_path));
            sc.useDelimiter("\n");
            while (sc.hasNext()) {
                Scanner item = new Scanner(sc.next());
                item.useDelimiter(",");
                while (item.hasNext()) {
                    columns++;

                    if (this.rows == 0) {
                        item.next();
                        if (columns == 6) {
                            this.rows++;
                            columns = 0;
                        }
                    } else {
                        String col = item.next();
                        if (columns == 1) { // Age

                            System.out.print("\rHarap tunggu. Sedang memproses dataset row ke-" + rows + "...");
                            model.setAge(super.age(Integer.parseInt(col)));

                        } else if (columns == 2) { // Defaulted
                            model.setDefaulted(col.toLowerCase());

                        } else if (columns == 3) { // Housing
                            model.setHousing(col.toLowerCase());

                        } else if (columns == 4) { // Loan
                            model.setLoan(col.toLowerCase());

                        } else if (columns == 5) { // Target
                            if (col.toLowerCase().contains("yes")) {
                                outputYes++;
                                model.yes();
                            } else {
                                outputNo++;
                                model.no();
                            }

                            boolean exist = false;
                            for (Model m : models) {
                                if (m.getAge() == model.getAge() && m.getDefaulted() == model.getDefaulted()
                                        && m.getHousing() == model.getHousing() && m.getLoan() == model.getLoan()) {

                                    if (model.getYesCount() > 0) {
                                        m.yes();
                                    } else {
                                        m.no();
                                    }
                                    model.addTotal();
                                    exist = true;
                                }
                            }
                            if (!exist) {
                                model.addTotal();
                                models.add(model);
                                
                            }
                            columns = 0;
                            this.rows++;
                            model = new Model();
                        }
                    }
                }
            }
            sc.close();

            if (this.rows - 1 == -1) {
                System.out.println("Error: File dataset tidak valid.");
                return null;
            }

            System.out.println("\nBerhasil memproses " + (this.rows - 1) + " row dataset.");
            return models;
        } catch (FileNotFoundException ex) {
            System.out.println("Error: File dataset tidak ditemukan.");
            return null;
        } catch (Exception e) {
            System.out.println("Error: Terjadi kesalahan yang tidak diketahui. Sepertinya file dataset tidak valid.");
            return null;
        }
    }

    public String getWorkingDirectory() {
        return System.getProperty("user.dir");
    }

    public int getTotalRow() {
        return this.rows - 1;
    }

    public double getProbabilityYesTotal() {
        return (double) outputYes / (double) (this.rows - 1);
    }

    public double getProbabilityNoTotal() {
        return (double) outputNo / (double) (this.rows - 1);
    }
}
