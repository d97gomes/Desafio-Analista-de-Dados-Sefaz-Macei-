package pipeline;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class Extractor {

    public static List<String> extrairLinhas(String caminhoDoZip) {

        List<String> linhas = new ArrayList<>();
        File arquivoZip = new File(caminhoDoZip);

        if (!arquivoZip.exists()) {
            System.err.println("Arquivo não encontrado: " + caminhoDoZip);
            return linhas;
        }

        try (ZipFile zip = new ZipFile(arquivoZip)) {

            Enumeration<? extends ZipEntry> entries = zip.entries();

            while (entries.hasMoreElements()) {

                ZipEntry entry = entries.nextElement();

                if (entry.getName().endsWith(".csv")) {

                    try (InputStream is = zip.getInputStream(entry);
                         BufferedReader reader = new BufferedReader(
                                 new InputStreamReader(is, "ISO-8859-1"))) {

                        String linha;
                        int linhaNumero = 0;

                        while ((linha = reader.readLine()) != null) {

                            linhaNumero++;

                            if (linhaNumero <= 4) continue;

                            linhas.add(linha);
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return linhas;
    }
}