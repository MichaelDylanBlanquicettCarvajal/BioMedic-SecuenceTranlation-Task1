import java.util.HashMap;

public class Req2_Protein_Traduction {

    public HashMap<String, String> dictOfTranslation(){

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
        translation_dict.put("CAA", "G");
        translation_dict.put("CAG", "G");

        translation_dict.put("AAT", "N");
        translation_dict.put("AAC", "N");
        translation_dict.put("AAA", "K");
        translation_dict.put("AAG", "K");

        translation_dict.put("TAT", "D");
        translation_dict.put("TAC", "D");
        translation_dict.put("TAA", "E");
        translation_dict.put("TAG", "E");

        


        return translation_dict;
    }


}
