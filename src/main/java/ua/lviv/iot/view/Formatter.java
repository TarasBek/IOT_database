package ua.lviv.iot.view;

import dnl.utils.text.table.TextTable;
import ua.lviv.iot.manager.EntityManager;

import java.util.*;

public class Formatter<T, K> {

    private static final String BREAK = "-------------------------------------------------------------";
    private static final String NO_MATCHES_FOUND = "No matches found";
    private static final String ERROR_CREATE_OR_MODIFY = "Unable to create or modify this object";

    private final EntityManager<T, K> entityManager;

    public Formatter(Class<T> clazz) {
        this.entityManager = new EntityManager<>(clazz);
    }


    public void printFormattedTable(List<T> entities) {
        String[] fieldNames = entityManager.getFieldsNamesArr();
        Object[][] fieldsValuesArr = new Object[entities.size()][fieldNames.length];
        for (int i = 0; i < entities.size(); i++) {
            fieldsValuesArr[i] = entityManager.getFieldsValuesArr(entities.get(i));
        }
        TextTable tt = new TextTable(fieldNames, fieldsValuesArr);
        tt.printTable();
        System.out.println();
    }

    public void printNoMatchesFound() {
        System.out.println(BREAK);
        System.out.println(NO_MATCHES_FOUND);
        System.out.println(BREAK + "\n");
    }

    public void printEntity(T entity) {
        String[] fieldNames = entityManager.getFieldsNamesArr();
        Object[][] fieldsValuesArr = {entityManager.getFieldsValuesArr(entity)};
        TextTable tt = new TextTable(fieldNames, fieldsValuesArr);
        tt.printTable();
        System.out.println();
    }

    public void printCreateOrModifyError() {
        System.out.println(BREAK);
        System.out.println(ERROR_CREATE_OR_MODIFY);
        System.out.println(BREAK + "\n");
    }
}
