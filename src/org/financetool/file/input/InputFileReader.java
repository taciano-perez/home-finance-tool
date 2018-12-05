package org.financetool.file.input;

import org.financetool.domain.Statement;

import java.io.IOException;

public interface InputFileReader {

    public Statement readFile(String fileName) throws IOException;
}
