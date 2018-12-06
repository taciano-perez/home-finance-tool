package org.financetool.file.input;

import org.financetool.domain.Statement;
import org.financetool.task.StatementMerger;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InputDirectoryReader {

    public Statement readAllFilesInDir(String dirName) throws IOException {
        final InputFileReader inputFileReader = new InputTabFileReader();
        final List<String> inputFileNames = getInputFileNames(dirName);
        final List<Statement> statements = new ArrayList<>();
        for (final String fileName : inputFileNames) {
            System.out.println("Reading file " + fileName);
            statements.add(inputFileReader.readFile(fileName));
        }
        final StatementMerger merger = new StatementMerger();
        return merger.mergeStatements(statements);
    }

    private List<String> getInputFileNames(String dirName) {
        final List<String> results = new ArrayList<>();
        final File[] files = new File(dirName).listFiles();
        if (files != null) {
            for (final File file : files) {
                if (file.isFile()) {
                    results.add(file.getAbsolutePath());
                }
            }
        }
        return results;
    }
}
