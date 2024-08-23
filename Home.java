
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

public class Home {

    public static void main(String[] args) {

        String dict_file_name = "";
        HashMap<String, String> dict = new HashMap<>();
        Boolean condicion = true;
        Integer x = 4;
        Scanner scanner = new Scanner(System.in);
        while (condicion) {
            switch (x) {
                case 0:
                    dict_file_name = scanner.nextLine();
                    String doc_text = FileReader(dict_file_name);
                    dict = loadDict(doc_text);
                    System.out.println("Se cargó correctamente el archivo FASTA, puede usar las demas ópciones.");
                    x = 4;
                    break;
                case 1:
                    x = 4;
                    break;
                case 2:
                    x = 4;
                    break;
                case 3:
                    x = 4;
                    condicion = false;
                    break;
                case 4:
                System.out.println("\n\n\n");
                    System.out.println("NOTA: Para Ejecutar cualquiera de las opciones debe primero hacer la creación de los archivos FASTA en la\ncarpeta 'FastaFiles' y usar los archivos ahí presentes");
                    System.out.println("================================================================================================");
                    System.out.println("0) Cargar el archivo FASTA en diccionario.");
                    System.out.println("1) Eliminar las secuencias repetidas.");
                    System.out.println("2) Realizar la traducción a proteinas de una cadena de nucleotidos.");
                    System.out.println("3) Cerrar el programa.");
                    System.out.println("================================================================================================");
                    System.out.println("Eliga una opción:");
                    x = scanner.nextInt();
                    break;
                default:
                    System.out.println("Opción no valida, intente nuevamente.");
                    break;
            }    
        }
        scanner.close();
    }

    // REQ 1
    public static String FileReader(String texto) {
        BufferedReader objReader = null;
        String final_text = "";
        try {
            String strCurrentLine;

            objReader = new BufferedReader(new FileReader("FastaFiles/" + texto));

            while ((strCurrentLine = objReader.readLine()) != null) {
                final_text += strCurrentLine;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (objReader != null) {
                    objReader.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return final_text;
    }

    public static HashMap<String, String> loadDict(String texto_plano) {

        HashMap<String, String> Dict = new HashMap<>();
        ;
        String[] texto_array = texto_plano.split("\n");

        String specimen = "";
        String secuence = "";
        for (int linea = 0; linea < texto_array.length; linea++) {

            String actual = texto_array[linea];
            if (linea > 0) {
                Dict.put(specimen, secuence);
                specimen = actual;
            } else if (texto_array[linea].charAt(0) == '>') {
                specimen = actual;
            } else {
                secuence += linea;
            }
        }

        return Dict;

    }

    //REQ 2
    public static HashMap<String, String> remove_duplicates(HashMap<String, String> dictP) {

        for (String s : new ConcurrentHashMap<>(dictP).keySet()) {
            String value = dictP.get(s);
            for (Map.Entry<String, String> ss : new ConcurrentHashMap<>(dictP)
                    .entrySet()) {
                if (!s.equals(ss.getKey()) && value.equals(ss.getValue())) {
                    dictP.remove(ss.getKey());
                }
            }
        }
        return dictP;
    }

    // REQ 3
    public static HashMap<String, String> dictOfTranslation() {

        HashMap<String, String> translation_dict = new HashMap<>();

        translation_dict.put("TTT", "F");
        translation_dict.put("TTC", "F");
        translation_dict.put("TTA", "L");
        translation_dict.put("TTG", "L");

        translation_dict.put("CTT", "L");
        translation_dict.put("CTC", "L");
        translation_dict.put("CTA", "L");
        translation_dict.put("CTG", "L");

        translation_dict.put("ATT", "I");
        translation_dict.put("ATC", "I");
        translation_dict.put("ATA", "I");
        translation_dict.put("ATG", "M"); //Codon Inicio

        translation_dict.put("GTT", "V");
        translation_dict.put("GTC", "V");
        translation_dict.put("GTA", "V");
        translation_dict.put("GTG", "V");

        translation_dict.put("TCT", "S");
        translation_dict.put("TCC", "S");
        translation_dict.put("TCA", "S");
        translation_dict.put("TCG", "S");

        translation_dict.put("CCT", "P");
        translation_dict.put("CCC", "P");
        translation_dict.put("CCA", "P");
        translation_dict.put("CCG", "P");

        translation_dict.put("ACT", "T");
        translation_dict.put("ACC", "T");
        translation_dict.put("ACA", "T");
        translation_dict.put("ACG", "T");

        translation_dict.put("GCT", "A");
        translation_dict.put("GCC", "A");
        translation_dict.put("GCA", "A");
        translation_dict.put("GCG", "A");

        translation_dict.put("TAT", "Y");
        translation_dict.put("TAC", "Y");
        translation_dict.put("TAA", "STOP");
        translation_dict.put("TAG", "STOP");

        translation_dict.put("CAT", "H");
        translation_dict.put("CAC", "H");
        translation_dict.put("CAA", "Q");
        translation_dict.put("CAG", "Q");

        translation_dict.put("AAT", "N");
        translation_dict.put("AAC", "N");
        translation_dict.put("AAA", "K");
        translation_dict.put("AAG", "K");

        translation_dict.put("GAT", "D");
        translation_dict.put("GAC", "D");
        translation_dict.put("GAA", "E");
        translation_dict.put("GAG", "E");

        translation_dict.put("TGT", "C");
        translation_dict.put("TGC", "C");
        translation_dict.put("TGA", "STOP");
        translation_dict.put("TGG", "W");

        translation_dict.put("CGT", "R");
        translation_dict.put("CGC", "R");
        translation_dict.put("CGA", "R");
        translation_dict.put("CGG", "R");

        translation_dict.put("AGT", "S");
        translation_dict.put("AGC", "S");
        translation_dict.put("AGA", "R");
        translation_dict.put("AGG", "R");

        translation_dict.put("GGT", "G");
        translation_dict.put("GGC", "G");
        translation_dict.put("GGA", "G");
        translation_dict.put("GGG", "G");

        return translation_dict;
    }

    public static String translateADN(String secuence, HashMap<String, String> dict) {

        Character first = ' ';
        Character second = ' ';
        Character third = ' ';
        String trio = "";

        String aminoacids_secuence = "";
        String aminoacid = "";

        for (int i = 0; i < secuence.length() + 3; i += 3) {

            first = secuence.charAt(i);
            second = secuence.charAt(i + 1);
            third = secuence.charAt(i + 2);

            trio = "" + first + second + third;

            aminoacid = dict.get(trio);
            switch (aminoacid) {
                case "M" -> {
                    aminoacids_secuence = "";
                    aminoacids_secuence += aminoacid;
                }
                case "STOP" -> {
                    return aminoacids_secuence;
                }
                default ->
                    aminoacids_secuence += aminoacid;
            }

        }
        return " ";
    }

}
