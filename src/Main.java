import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String destinationPath = "/Users/kicia/Desktop/destinationCode/destinationCode.txt";
        String sourcePath = "/Users/kicia/Desktop/sourceCode";
        new Main().getData(destinationPath, sourcePath);
    }

    private void getData(String destinationPath, String sourcePath ) {
        File[] htmList = htmFiles(sourcePath);

        for (File sourceFile : htmList) {
            File srcFile = new File(sourcePath + "/" + sourceFile.getName());

            try {
                Scanner reader = new Scanner(srcFile);
                FileWriter fw = new FileWriter(destinationPath, true);
                StringBuilder s = new StringBuilder();

                // Read a line
                while (reader.hasNextLine())
                    s.append(reader.nextLine());

                //Write to output file
                fw.write(buildTemplate(s.toString()));
                fw.flush();
                reader.close();
                fw.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private File[] htmFiles(String sourcePath) {
        final File folder = new File(sourcePath);
        File[] htmList = folder.listFiles();
        assert htmList != null;
        Arrays.sort(htmList);
        return htmList;
    }

    private String buildTemplate(String readings) {
        String rma0 = getRma(readings);
        String dateRecive1 = getDate(readings);
        String owner3 = getOwner(readings);
        String mobile5 = getMobile(readings);
        String compModel7 = getModel(readings);
        String serialNumber8 = getSerialNo(readings);
        String fault9 = getFault(readings);

        String dateBack2 = "";
        String nip4 = "";
        String mail6 = "";
        String solution10 = "";
        String notes11 = "";
        String price12 = "";
        String income13 = "";

        String template =

                "0|" + rma0 + "|" + rma0 + "\n" +

                        "1|" + rma0 + "|" + dateRecive1 + "\n" +

                        "2|" + rma0 + "|" + dateBack2 + "\n" +

                        "3|" + rma0 + "|" + owner3 + "\n" +

                        "4|" + rma0 + "|" + nip4 + "\n" +

                        "5|" + rma0 + "|"+ mobile5 + "\n" +

                        "6|" + rma0 + "|" + mail6 + "\n" +

                        "7|" + rma0 + "|" + compModel7 + "\n" +

                        "8|" + rma0 + "|" + serialNumber8 + "\n" +

                        "9|" + rma0 + "|" + fault9 + "\n" +

                        "10|" + rma0 + "|" + solution10 + "\n" +

                        "11|" + rma0 + "|" + notes11 + "\n" +

                        "12|" + rma0 + "|" + price12 + "\n" +

                        "13|" + rma0 + "|" + income13 + "\n";
        return template;
    }

    private String getFault(String sourceText) {
        String keyWord = "Email:";
        String toBeReturned = "";

        if (sourceText.contains(keyWord)) {
            String helper = sourceText.substring(sourceText.indexOf(keyWord));
            int startIndex = helper.indexOf(">", 227);
            int endIndex = helper.indexOf("<", 228);
            toBeReturned = helper.substring(startIndex, endIndex);
        }
        return toBeReturned;
    }

    private String getSerialNo(String sourceText) {
        // S/N: &nbsp;<b>001781-SKU24K14120066048</b>
        String keyWord = "S/N:";
        String toBeReturned = "";

        if (sourceText.contains(keyWord)) {
            String helper = sourceText.substring(sourceText.indexOf(keyWord));
            int startIndex = helper.indexOf(">") + 1;
            int endIndex = helper.indexOf("<", 12);
            toBeReturned = helper.substring(startIndex, endIndex);
        }
        return toBeReturned;
    }

    private String getModel(String sourceText) {
        // Model: &nbsp;<b>MSI GT72</b>
        String keyWord = "Model:";
        String toBeReturned = "";

        if (sourceText.contains(keyWord)) {
            String helper = sourceText.substring(sourceText.indexOf(keyWord));
            int startIndex = helper.indexOf(">") + 1;
            int endIndex = helper.indexOf("<", 16);
            toBeReturned = helper.substring(startIndex, endIndex);
        }
        return toBeReturned;
    }

    private String getMobile(String sourceText) {
        // Telephone: &nbsp;<b>022 86t6 7012</b>
        String toBeReturned = "";
        String keyWord = "Telephone:";

        if (sourceText.contains(keyWord)) {
            String helper = sourceText.substring(sourceText.indexOf("Telephone:"));
            int startIndex = helper.indexOf(">") + 1;
            int endIndex = helper.indexOf("<", 20);
            toBeReturned = helper.substring(startIndex, endIndex);
        }
        return toBeReturned;
    }

    private String getOwner(String sourceText) {
        // Name/Company: &nbsp;<b>Alien Computers</b>
        String toBeReturned = "";

        if (sourceText.contains("Name/Company:")) {

            String helper = sourceText.substring(sourceText.indexOf("Name/Company:"));

            int startIndex = helper.indexOf(">") + 1;

            int endIndex = helper.indexOf("<", 23);

            toBeReturned = helper.substring(startIndex, endIndex);
        }
        return toBeReturned;
    }

    private String getDate(String sourceText) {
        // DATE:&nbsp;2019-07-07&nbsp
        String toBeReturned = "";
        String keyWord = "DATE:";

        if (sourceText.contains(keyWord)) {
            String helper = sourceText.substring(sourceText.indexOf(keyWord));
            int startIndex = helper.indexOf(";") + 1;
            int endIndex = helper.indexOf("&", 10);
            toBeReturned = helper.substring(startIndex, endIndex);
        }
        return toBeReturned;
    }

    private String getRma(String sourceText) {
        // RMA3518&nbsp;
        String toBeReturned = "";
        String keyWord = "RMA";

        if (sourceText.contains(keyWord)) {
            String helper = sourceText.substring(sourceText.indexOf(keyWord));
            int startIndex = helper.indexOf("A") + 1;
            int endIndex = helper.indexOf("&");
            toBeReturned = helper.substring(startIndex, endIndex);
        }
        return toBeReturned;
    }
}
