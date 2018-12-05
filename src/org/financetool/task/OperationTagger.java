package org.financetool.task;

import org.financetool.domain.Operation;
import org.financetool.domain.Statement;
import org.financetool.domain.Tag;

import java.util.stream.Collectors;

public class OperationTagger {

    public Statement tagStatement(Statement inputStatement, Tag tag) {
        Statement outputStatement = new Statement();
        for (Operation operation : inputStatement.getOperationList()) {
            if (matchesTag(operation, tag)) {
                operation.addTag(tag);
                outputStatement.addOperation(operation);
            }
        }
        return outputStatement;
    }

    private boolean matchesTag(Operation operation, Tag tag) {
        for (String pattern : tag.getDescriptionPatterns()) {
            if (operation.getDescription().toUpperCase().contains(pattern.toUpperCase())) {
                return true;
            }
        }
        return false;
    }
}
