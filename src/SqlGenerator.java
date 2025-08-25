import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class SqlGenerator {

    public static void main(String[] args) {
        String inputFilePath = "resource/arquivo.txt"; // Altere para o caminho do seu arquivo de entrada
        String outputFilePath = "resource/script_update.sql"; // Altere para onde o arquivo de saída será salvo

        try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath));
             PrintWriter pw = new PrintWriter(new FileWriter(outputFilePath))) {

            String line;
            while ((line = br.readLine()) != null) {
                // A linha pode conter múltiplos valores separados por vírgula
                String[] values = line.split(",");

                for (String value : values) {
                    // Remove espaços em branco extras
                    String trimmedValue = value.trim();

                    if (!trimmedValue.isEmpty()) {
                        // Constrói a sentença SQL
                        String sql = "UPDATE UW_PROCESS \n" +
                                "SET STATUS_CD = 'WITHDRAWN' \n" +
                                "WHERE SUBJECT_REF_ID = '" + trimmedValue + "';";

                        // Escreve a sentença no arquivo de saída
                        pw.println(sql);

                        System.out.println("Gerado SQL para: " + trimmedValue);
                    }
                }
            }
            System.out.println("\nScript SQL gerado com sucesso em: " + outputFilePath);

        } catch (IOException e) {
            System.err.println("Ocorreu um erro de I/O: " + e.getMessage());
        }
    }
}