package AnalisisGenómico;

public class ConteoGenes {
    public static int contarGenes(String dna) throws Exception {
        if (!dna.startsWith("ATG")) {
            throw new Exception("El patrón de ADN debe comenzar con 'ATG'");
        }

        if (!dna.matches("[AGTC]*")) {
            throw new Exception("El patrón de ADN contiene un carácter no permitido");
        }

        int count = 0;
        for (int i = 0; i <= dna.length() - 3; i += 3) {
            String sub = dna.substring(i, i + 3);
            if (sub.matches("[AGTC]{3}")) {
                count++;
            }
        }
        return count;
    }
}