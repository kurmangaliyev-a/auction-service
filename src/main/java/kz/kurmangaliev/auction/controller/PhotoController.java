package kz.kurmangaliev.auction.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.kurmangaliev.auction.dto.ResultMessage;
import kz.kurmangaliev.auction.service.PhotoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;

@RestController
@RequestMapping(value = "/photo")
@Slf4j
@RequiredArgsConstructor
@Tag(name = "PhotoController", description = "Работа с фотографиями товара")
public class PhotoController extends AbstractController {
    private final PhotoService photoService;

    @GetMapping("/{id}")
    @Operation(summary = "Получение фотографии товара")
    public ResponseEntity<InputStreamResource> get(@PathVariable String id) {
        try {
            return ResponseEntity.ok().contentType(MediaType.valueOf(MediaType.MULTIPART_FORM_DATA_VALUE)).body(photoService.getFile(id));
        }catch (FileNotFoundException err){
            log.error(err.getMessage());
            err.printStackTrace();
            return null;
        }
    }
    @PostMapping("/delete/{id}")
    @Operation(summary = "Удаление фотографии товара")
    public ResultMessage delete(@PathVariable String id){
        Long userId = getUserId();
        return photoService.deleteFile(id, userId);
    }
}
