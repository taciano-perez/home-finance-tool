package org.financetool.domain;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class OperationTest {

    @org.junit.jupiter.api.Test
    void equals() {

        // given
        final Date date1 = new Date();
        final Date date2 = new Date();
        final Operation op1a = new Operation(OperationType.CREDIT, date1, BigDecimal.ONE, "desc1");
        final Operation op1b = new Operation(OperationType.CREDIT, date1, BigDecimal.ONE, "desc1");
        final Operation op2 = new Operation(OperationType.CREDIT, date2, BigDecimal.ONE, "desc2");

        // then
        assertEquals(op1a, op1b);
        assertNotEquals(op1a, op2);
        assertNotEquals(op1b, op2);
    }
}