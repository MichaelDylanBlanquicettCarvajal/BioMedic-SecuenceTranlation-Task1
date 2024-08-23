
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

public class Home {

    public static void main(String[] args) {

        HashMap<String, String> dict_aminoacids = new HashMap<>();
        HashMap<String, String> traduccion = new HashMap<>();
        String dict_file_name = "";
        HashMap<String, String> dict_general = new HashMap<>();
        Boolean condicion = true;
        Integer x = 5;
        Scanner scanner = new Scanner(System.in);
        while (condicion) {
            switch (x) {
                case 0:
                    String mensaje = "Por favor escriba la ruta completa del archivo Fasta:";
                    String doc_text = FileReader(text(mensaje));
                    System.out.println(doc_text);
                    dict_general = loadDict(doc_text);
                    System.out.println("Se cargó correctamente el archivo FASTA, puede usar las demas ópciones.");
                    x = 5;
                    break;
                case 1:
                    dict_general = remove_duplicates(dict_general);
                    System.out.println("Secuencias duplicadas eliminadas del diccionario.");
                    x = 5;
                    break;
                case 2:
                    if (!dict_aminoacids.containsKey(x)) {
                        dict_aminoacids = dictOfTranslation();
                    }
                    String texto_archivo = "";
                    traduccion = new HashMap<>();
                    String secuenciaTraducida = "";
                    String nombreSecuencia = "";
                    for (Map.Entry<String, String> entry : dict_general.entrySet()) {
                        nombreSecuencia = entry.getKey();
                        secuenciaTraducida = entry.getValue();
                        System.out.println(nombreSecuencia);
                        System.out.println(secuenciaTraducida);
                        secuenciaTraducida = translateADN(entry.getValue(), dict_aminoacids);
                        traduccion.put(">" + nombreSecuencia + "\n", secuenciaTraducida);
                        texto_archivo = texto_archivo + "\n" + nombreSecuencia + "\n" + secuenciaTraducida;
                    }
                    String nombre_archivo = text("Introduzca el nombre que desea poner al archivo (sera .fa, no debe añadir el sufijo)");
                    FileWriter(nombre_archivo, texto_archivo);
                    x = 5;
                    break;
                case 3:
                    for (int idx = 0; idx < traduccion.size(); idx++) {
                        System.out.println(traduccion.get(idx) + "\n");
                    }
                    x = 5;
                    break;
                case 4:
                    x = 5;
                    condicion = false;
                    break;
                case 5:
                    System.out.println("\n\n\n");
                    System.out.println("NOTA: Para Ejecutar cualquiera de las opciones debe primero hacer la creación de los archivos FASTA en la\ncarpeta 'FastaFiles' y usar los archivos ahí presentes");
                    System.out.println("================================================================================================");
                    System.out.println("0) Cargar el archivo FASTA en diccionario.");
                    System.out.println("1) Eliminar las secuencias repetidas.");
                    System.out.println("2) Realizar la traducción a proteinas de una cadena de nucleotidos.");
                    System.out.println("3) Imprimir resultado del punto 2 (traduccion de proteinas).");
                    System.out.println("4) Cerrar el programa.");
                    System.out.println("================================================================================================");
                    System.out.println("Eliga una opción:");
                    x = scanner.nextInt();
                    break;
                case 6:
                    System.out.println(dict_general);
                    for (Map.Entry<String, String> entry : dict_general.entrySet()) {
                        System.out.println(entry.getKey() + " = " + entry.getValue());
                    }

                    x = 5;
                    break;
                default:
                    System.out.println("Opción no valida, intente nuevamente.");
                    break;
            }
        }
        scanner.close();
    }

    // REQ 1
    public static String text(String mensaje) {
        System.out.println(mensaje);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static String FileReader(String texto) {
        BufferedReader objReader = null;
        String final_text = "";
        try {
            String strCurrentLine;

            objReader = new BufferedReader(new FileReader(texto));

            while ((strCurrentLine = objReader.readLine()) != null) {
                final_text += strCurrentLine + "\n";
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

    public static void FileWriter(String nombreArchivo, String texto) {
        BufferedWriter writer;
        writer = null;
        try {
            File dir = new File("FastaFiles");

            // Crear el archivo en el directorio especificado
            File archivo = new File(dir, nombreArchivo + ".fa");
            writer = new BufferedWriter(new FileWriter(archivo));

            // Escribir el texto en el archivo
            writer.write(texto);
            System.out.println("Archivo escrito exitosamente en: " + archivo.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static HashMap<String, String> loadDict(String texto_plano) {

        HashMap<String, String> Dict = new HashMap<>();
        String[] texto_array = texto_plano.split("\n");

        String specimen = "";
        String secuence = "";
        Boolean cond = true;
        for (int linea = 0; linea < texto_array.length; linea++) {

            String actual = texto_array[linea];
            //System.out.println(actual);
            if (actual.charAt(0) == '>') {
                cond = !cond;
                if (cond) {
                    Dict.put(specimen, secuence);
                }
                specimen = actual;
                secuence = "";
            } else {
                secuence = reemplazarN(secuence);
                secuence = secuence + actual;
            }
        }

        return Dict;

    }

    public static String reemplazarN(String secuencia) {
        Random random = new Random();
        char[] opciones = {'T', 'G', 'A', 'C'};
        StringBuilder resultado = new StringBuilder();

        for (char c : secuencia.toCharArray()) {
            if (c == 'N') {
                // Reemplazar 'N' por un carácter aleatorio de las opciones
                char reemplazo = opciones[random.nextInt(opciones.length)];
                resultado.append(reemplazo);
            } else {
                resultado.append(c);
            }
        }

        return resultado.toString();
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

        String aminoacids_secuence = " ";
        String aminoacid = "";
        Boolean escritura_habilitada = false;

        for (int i = 0; i < secuence.length() - 3; i += 3) {

            first = secuence.charAt(i);
            second = secuence.charAt(i + 1);
            third = secuence.charAt(i + 2);

            trio = "" + first + second + third;
            //System.out.println(trio);
            aminoacid = dict.get(trio);
            if (aminoacid.equals("M")) {
                aminoacids_secuence += aminoacid;
                escritura_habilitada = true;
            } else if (aminoacid.equals("STOP")) {
                escritura_habilitada = false;
            } else if (escritura_habilitada) {
                aminoacids_secuence += aminoacid;
            }

        }
        return aminoacids_secuence;
    }

}
