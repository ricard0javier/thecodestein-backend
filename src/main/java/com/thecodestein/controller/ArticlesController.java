package com.thecodestein.controller;

import com.thecodestein.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;
import java.util.stream.Collectors;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Provide the mechanisms to interact with the existent articles shared in the static folder
 */
@RestController
@RequestMapping(value = "/api")
public class ArticlesController {

    private final String contextUrl;

    private final String articlesRelativePath;

    private FileService fileService;

    private String articlesPath;

    @Autowired
    public ArticlesController(FileService fileService,
                              @Value("${thecodestein.articlesFullPath}") String articlesPath,
                              @Value("${thecodestein.contextUrl}") String contextUrl,
                              @Value("${thecodestein.articlesRelativePath}") String articlesRelativePath) {
        this.fileService = fileService;
        this.articlesPath = articlesPath;
        this.contextUrl = contextUrl;
        this.articlesRelativePath = articlesRelativePath;
    }

    @RequestMapping(value = "/articles", method = GET)
    public Set<String> getArticles() {
        Path path = Paths.get(articlesPath);
        return fileService.getFiles(path.toFile())
                .stream()
                .map(fileName -> contextUrl + articlesRelativePath + fileName)
                .collect(Collectors.toSet());
    }
}
