package com.robertorebolledonaharro.bichoapi.files.controller;


import com.robertorebolledonaharro.bichoapi.files.dto.FileResponse;
import com.robertorebolledonaharro.bichoapi.files.service.StorageService;
import com.robertorebolledonaharro.bichoapi.files.utils.MediaTypeUrlResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class FileController {

    private final StorageService storageService;


    @Operation(summary = "Upload files")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Files uploaded successfully",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = FileResponse.class)),
                            examples = @ExampleObject(
                                    value = """
                [
                    {"name": "file1.jpg", "size": 1024, "type": "image/jpeg", "uri": "http://example.com/download/file1.jpg"},
                    {"name": "file2.jpg", "size": 2048, "type": "image/jpeg", "uri": "http://example.com/download/file2.jpg"}
                ]"""
                            )
                    )
            )
    })
    @PostMapping("/upload/files")
    public ResponseEntity<?> upload(@RequestPart("files") MultipartFile[] files) {

        //FileResponse response = uploadFile(file);

        List<FileResponse> result = Arrays.stream(files)
                .map(this::uploadFile)
                .toList();

        return ResponseEntity
                //.created(URI.create(response.getUri()))
                .status(HttpStatus.CREATED)
                .body(result);
    }

    @Operation(summary = "Upload a file")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "File uploaded successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = FileResponse.class),
                            examples = @ExampleObject(
                                    value = """
                {"name": "file1.jpg", "size": 1024, "type": "image/jpeg", "uri": "http://example.com/download/file1.jpg"}"""
                            )
                    )
            )
    })
    @PostMapping("/upload")
    public ResponseEntity<?> upload(@RequestPart("file") MultipartFile file) {

        FileResponse response = uploadFile(file);

        return ResponseEntity.created(URI.create(response.getUri())).body(response);
    }

    private FileResponse uploadFile(MultipartFile file) {
        String name = storageService.store(file);

        String uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(name)
                .toUriString();

        return FileResponse.builder()
                .name(name)
                .size(file.getSize())
                .type(file.getContentType())
                .uri(uri)
                .build();
    }

    @Operation(summary = "Download a file by filename")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "File retrieved successfully",
                    content = @Content(mediaType = "image/jpeg")
            ),
            @ApiResponse(responseCode = "404", description = "File not found")
    })
    @GetMapping("/download/{filename:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String filename) throws IOException {
        MediaTypeUrlResource resource =
                (MediaTypeUrlResource) storageService.loadAsResource(filename);

        return ResponseEntity.status(HttpStatus.OK)
                .header("Content-Type", resource.getType())
                .body(resource);
    }

    @Operation(summary = "Download a scaled file by filename")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Scaled file retrieved successfully",
                    content = @Content(mediaType = "image/jpeg")
            ),
            @ApiResponse(responseCode = "404", description = "File not found")
    })
    @GetMapping("/download/{filename:.+}/scaled")
    public ResponseEntity<Resource> getScaledFile(@PathVariable String filename, @RequestParam int width, @RequestParam int height) throws IOException {
        MediaTypeUrlResource resource = (MediaTypeUrlResource) storageService.loadAsResource(filename);

        // Load the image
        BufferedImage originalImage = ImageIO.read(resource.getInputStream());
        if(originalImage==null)
            originalImage = ImageIO.read(((MediaTypeUrlResource) storageService.loadAsResource("sebusca.jpg")).getInputStream());

        // Scale the image
        BufferedImage scaledImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        scaledImage.createGraphics().drawImage(originalImage.getScaledInstance(width, height, BufferedImage.SCALE_SMOOTH), 0, 0, null);

        // Convert the scaled image to a Resource
        byte[] imageBytes;
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            ImageIO.write(scaledImage, "jpg", baos);
            baos.flush();
            imageBytes = baos.toByteArray();
        }

        InputStreamResource inputStreamResource = new InputStreamResource(new ByteArrayInputStream(imageBytes));

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.IMAGE_JPEG)
                .body(inputStreamResource);
    }

}
