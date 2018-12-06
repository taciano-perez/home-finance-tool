package org.financetool.file.input;

import org.financetool.domain.Operation;
import org.financetool.domain.OperationType;
import org.financetool.domain.Statement;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.stream.Stream;

public class InputTabFileReader implements InputFileReader {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyyMMdd");
    private static final String VALUE_PATTERN = "#,##0.0#";

    public Statement readFile(String fileName) throws IOException {
        Statement statement = new Statement();

        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            stream.forEach(line -> parseLine(line, statement));
        }

        return statement;
    }

    private void parseLine(String line, Statement statement) {
        Operation operation = new Operation();

        String[] cols = line.split("\\t");
        // date (2)
        try {
            operation.setDate(DATE_FORMAT.parse(cols[2]));
        } catch (ParseException e) {
            System.err.println("UNEXPECTED ERROR PARSING DATE");
            e.printStackTrace();
        }
        // value (6)
        try {
            operation.setValue(parseBigDecimal(cols[6]));
            if ((operation.getValue().doubleValue() >= 0)) {
                operation.setType(OperationType.CREDIT);
            } else {
                operation.setType(OperationType.DEBIT);
            }
        } catch (ParseException e) {
            System.err.println("UNEXPECTED ERROR PARSING VALUE");
            e.printStackTrace();
        }
        // description (7)
        String entry = cols[7];
        if (entry.startsWith("/TRTP")) {
            parseTrtpEntry(entry, operation);
        } else if (entry.startsWith("ABN AMRO Bank N.V.")) {
            parseAbnAmroEntry(entry, operation);
        } else {
            // BEA or GEA entry
            parseBeaEntry(entry, operation);
        }

        statement.addOperation(operation);
    }

    private void parseBeaEntry(String beaEntry, Operation operation) {
        String[] descCols = beaEntry.split("\\s+");
        StringBuilder descriptionBuilder = new StringBuilder();
        Arrays.stream(descCols).skip(3).forEach(chunk -> descriptionBuilder.append(chunk).append(" "));
        String descPlusPasNummer = descriptionBuilder.toString();
        if (descPlusPasNummer.contains(",PAS")) {
            String[] descParts = descriptionBuilder.toString().split(",PAS");
            operation.setDescription(descParts[0]);
        } else {
            operation.setDescription(descPlusPasNummer);
        }
    }

    private void parseTrtpEntry(String trtpEntry, Operation operation) {
        String[] entryCols = trtpEntry.split("/");
        int offset = 0;
        for (int i = 0; i < entryCols.length; i++) {
            if ("NAME".equals(entryCols[i])) {
                offset = i + 1;
                break;
            }
        }
        String name = entryCols[offset];
        operation.setDescription(name);
    }

    private void parseAbnAmroEntry(String abnAmroEntry, Operation operation) {
        String descriptionPart = abnAmroEntry.substring(33, 61).trim();
        operation.setDescription(descriptionPart);
    }

    private BigDecimal parseBigDecimal(String string) throws ParseException {
        return (BigDecimal) getDecimalFormat().parse(string);
    }

    private DecimalFormat getDecimalFormat() {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator('.');
        symbols.setDecimalSeparator(',');
        DecimalFormat decimalFormat = new DecimalFormat(VALUE_PATTERN, symbols);
        decimalFormat.setParseBigDecimal(true);
        return decimalFormat;
    }

}
