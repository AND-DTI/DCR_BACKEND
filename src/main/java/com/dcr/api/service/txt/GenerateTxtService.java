package com.dcr.api.service.txt;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.springframework.stereotype.Service;

import com.dcr.api.model.as400.Dcrlayout;
import com.dcr.api.service.as400.DcrlayoutService;

@Service
public class GenerateTxtService {

	
	public void gerarArquivoTXT() {
        try (FileWriter fw = new FileWriter("arquivoTeste.txt");
             BufferedWriter bw = new BufferedWriter(fw)) {
             bw.write("teste");
             
            System.out.println("Arquivo de texto gerado com sucesso: ");
        } catch (IOException e) {
            System.out.println("Erro ao gerar o arquivo de texto: " + e.getMessage());
        }
    }
}
