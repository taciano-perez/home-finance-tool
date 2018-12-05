package org.financetool;

import org.financetool.domain.Statement;
import org.financetool.domain.Tag;
import org.financetool.file.input.InputFileReader;
import org.financetool.file.input.InputTabFileReader;
import org.financetool.file.input.TagFileReader;
import org.financetool.task.OperationTagger;

public class FinanceToolMain {

    public static void main(String[] args) throws Exception {
        final String fileName = "./test/data/TXT181202171552.TAB";
        System.out.println("Importing data from " + fileName);
        InputFileReader inputFileReader = new InputTabFileReader();
        Statement statement = inputFileReader.readFile(fileName);

        final String tagFileName = "./test/data/category-bills.txt";
        TagFileReader tagFileReader = new TagFileReader();
        Tag billTag = tagFileReader.readTagInputFile("Bill", tagFileName);

        OperationTagger tagger = new OperationTagger();
        Statement taggedStatement = tagger.tagStatement(statement, billTag);

        System.out.println(taggedStatement);
    }
}
