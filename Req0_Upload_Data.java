
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Req0_Upload_Data {

    public void WriteFile() {
        BufferedWriter bw = null;
        try {

            String[] lista = obtenerLista();
            /* This logic will make sure that the file 
          * gets created if it is not present at the
          * specified location*/

            String ftexto = "";

            for (String texto : lista) {
                ftexto = ReaderFile(texto);
                File file = new File("Fasta files/" + texto + ".fa");

                if (!file.exists()) {
                    file.createNewFile();
                }

                ftexto = fastaString(ftexto);

                FileWriter fw = new FileWriter(file, false);
                bw = new BufferedWriter(fw);
                bw.write(ftexto);
                System.out.println("File written Successfully");

            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (Exception ex) {
                System.out.println("Error in closing the BufferedWriter" + ex);
            }
        }
    }

    public String ReaderFile(String texto) {
        BufferedReader objReader = null;
        String final_text = "";
        try {
            String strCurrentLine;

            objReader = new BufferedReader(new FileReader("/txt files/" + texto));

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

    public String[] obtenerLista() {
        String sCarpAct = System.getProperty("user.dir", "/txt files");
        File carpeta = new File(sCarpAct);
        String[] lista = carpeta.list();
        if (lista.length > 0) {
            System.out.println(lista);
            return lista;
        } else {
            return null;
        }
    }

    public String fastaString(String texto_plano) {

        texto_plano = texto_plano.replace(" ", "");
        texto_plano = texto_plano.replace("\n", "");
        String[] sp1 = texto_plano.split("mRNA");
        String[] sp2 = sp1[0].split("PREDICTED");

        return sp2[0] + "\n" + sp1[1] + "\n";

    }
}
