package com.ariellopes.inspiradoamazonmusic.playmusic.services;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


@RestController
@RequestMapping(value = "/v1")
public class OuvirService {

	// Essa anotação junta com a pai, cria o endpoint: /v1/music
    @RequestMapping("/music")
    @ResponseBody
    public ResponseEntity<InputStreamResource> listenMusic() throws IOException {
        // cria a variavel onde vai estar o arquivo
        File videoFile = ResourceUtils.getFile("classpath:mp3/01-disco-arranhado.mp3");

        // Pego o tamanho do arquivo
        long len = Files.size(Paths.get(videoFile.toURI()));

        MediaType mediaType = MediaType.parseMediaType("application/octet-stream");

        // Crio o Stream que vai ser usado para retornar no response
        InputStreamResource resource = new InputStreamResource(new FileInputStream(videoFile));

        // Crio o response
        return ResponseEntity.ok()
                .contentType(mediaType)
                .contentLength(len)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + videoFile.getName() + "\"")
                .body(resource);
    }
}
