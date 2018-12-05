package org.financetool.file.input;

import org.financetool.domain.Tag;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class TagFileReader {

    public Tag readTagInputFile(String tagName, String fileName) throws IOException {
        Tag tag = new Tag();
        tag.setName(tagName);
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            stream.forEach(tag::addDescriptionPattern);
        }
        return tag;
    }
}
