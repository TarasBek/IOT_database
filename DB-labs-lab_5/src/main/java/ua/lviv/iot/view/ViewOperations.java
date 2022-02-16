package ua.lviv.iot.view;

import org.hibernate.TypeMismatchException;
import ua.lviv.iot.controller.AbstractController;
import ua.lviv.iot.entitymanager.EntityManager;
import ua.lviv.iot.model.*;

import ua.lviv.iot.service.AbstractService;
import ua.lviv.iot.service.impl.*;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.PersistenceException;
import java.io.Serializable;
import java.lang.reflect.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.*;

public class ViewOperations<T, K extends Serializable> {

    private static final String KEY_EXIT = "Q";
    private static final String ENTER_FIELD_OR_QUIT_FORMAT = "Enter %s or press '" + KEY_EXIT + "' to go back: ";
    private static final String ENTER_FIELD_FORMAT = "Enter %s: ";
    private static final String ENTER_REQUIRED = "Field is required! Enter it!!!";
    private static final String ERROR_INVALID_VALUE = "Entered invalid value";
    private static final String ENTER_CORRECT_INTEGER = "This field must contain [0-9] digits. Try again!!!";
    private static final String ENTER_CORRECT_LOCAL_DATE_TIME = "This field must match this pattern: yyyy-MM-ddThh:mm:ss. Also it must be valid! Try again!!!";
    private static final String ENTER_CORRECT_BOOLEAN = "This field must either 0 or 1! Try again!!!";
    private static final String CHOOSE_FIELD = "Choose field to update (enter name):";

    private static final Scanner input = new Scanner(System.in, StandardCharsets.UTF_8);

    private final AbstractController<T, K> controller;
    private final Formatter<T> formatter;
    private final EntityManager<T> entityManager;

    public ViewOperations(AbstractController<T, K> controller, Class<T> entityClass) {
        this.controller = controller;
        this.formatter = new Formatter<>(entityClass);
        this.entityManager = new EntityManager<>(entityClass);
    }

    public void findAll() {
        List<T> entities = controller.findAll();
        if (!entities.isEmpty()) {
            formatter.printFormattedTable(entities);
        } else {
            formatter.printNoMatchesFound();
        }
    }

    @SuppressWarnings("unchecked")
    public void findById() {
        String keyMenu;
        do {
            System.out.printf(ENTER_FIELD_OR_QUIT_FORMAT, "id");
            keyMenu = input.nextLine().toUpperCase();
            if (!keyMenu.equals(KEY_EXIT)) {
                T foundEntity;
                try {
                    Integer id = Integer.parseInt(keyMenu);
                    foundEntity = controller.findById((K) id);
                } catch (TypeMismatchException | NumberFormatException e) {
                    foundEntity = controller.findById((K) keyMenu);
                }
                if (foundEntity != null) {
                    formatter.printEntity(foundEntity);
                } else {
                    formatter.printNoMatchesFound();
                }
            }
        } while (!keyMenu.equals(KEY_EXIT));
    }

    public void create() {
        Field[] inputFields = entityManager.getInputFields().toArray(new Field[0]);
        try {
            T entity = entityManager.getClazz().getConstructor().newInstance();
            for (Field field : inputFields) {
                inputValueForField(field, entity);
            }
            T newEntity = controller.create(entity);
            if (newEntity != null) {
                formatter.printEntity(newEntity);
            } else {
                formatter.printCreateOrModifyError();
            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            System.out.println("Exception " + e.getClass() + " was thrown. Try again.");
        }
    }

    @SuppressWarnings("unchecked")
    public void update() {
        String keyMenu;
        do {
            System.out.println(String.format(ENTER_FIELD_OR_QUIT_FORMAT, "id"));
            keyMenu = input.nextLine().toUpperCase();
            if (!keyMenu.equals(KEY_EXIT)) {
                K id = (K) keyMenu;
                T foundEntity;
                try {
                    Integer idd = Integer.parseInt(keyMenu);
                    foundEntity = controller.findById((K) idd);
                } catch (TypeMismatchException | NumberFormatException e) {
                    foundEntity = controller.findById(id);
                }
                if (foundEntity != null) {
                    List<String> inputFieldsNames = entityManager.getInputFieldsNames();
                    while (true) {
                        System.out.println(CHOOSE_FIELD);
                        inputFieldsNames.forEach(colName -> System.out.println(" - " + colName));
                        String inputName = input.nextLine();
                        if (inputFieldsNames.contains(inputName)) {
                            Field foundField = entityManager.getInputFieldByName(inputName);
                            inputValueForField(foundField, foundEntity);
                            T oldEntity = controller.update(id, foundEntity);
                            if (oldEntity != null) {
                                List<T> entityHistory = new LinkedList();
                                entityHistory.add(oldEntity);
                                entityHistory.add(foundEntity);
                                formatter.printFormattedTable(entityHistory);
                                return;
                            } else {
                                formatter.printCreateOrModifyError();
                                break;
                            }
                        } else {
                            formatter.printNoMatchesFound();
                        }
                    }
                } else {
                    formatter.printNoMatchesFound();
                }
            } else {
                formatter.printNoMatchesFound();
            }
        } while (!keyMenu.equals(KEY_EXIT));
    }

    @SuppressWarnings("unchecked")
    public void delete() {
        String keyMenu;
        do {
            System.out.println(String.format(ENTER_FIELD_OR_QUIT_FORMAT, "id"));
            keyMenu = input.nextLine().toUpperCase();
            if (!keyMenu.equals(KEY_EXIT)) {
                K id = (K) keyMenu;
                T foundEntity;
                try {
                    Integer idd = Integer.parseInt(keyMenu);
                    foundEntity = controller.findById((K) idd);
                } catch (TypeMismatchException | NumberFormatException e) {
                    foundEntity = controller.findById(id);
                }
                if (foundEntity != null) {
                    try {
                        controller.delete(foundEntity);
                        formatter.printEntity(foundEntity);
                    } catch (PersistenceException e) {
                        System.out.println("Deletion of this entity is forbidden!!!");
                    }
                } else {
                    formatter.printNoMatchesFound();
                }
            } else {
                formatter.printNoMatchesFound();
            }
        } while (!keyMenu.equals(KEY_EXIT));
    }

    private void inputValueForField(Field field, T entity) {
        String fieldName = getFieldName(field);
        Class<?> fieldType = field.getType();
        getUserHintForField(fieldName, fieldType);

        while (true) {
            String inputText = input.nextLine();

            if (isNonNullFieldEmpty(field, inputText)) continue;

            field.setAccessible(true);
            // Fill field according to its type
            try {
                if (fieldType == Integer.class) {
                    Integer value = getValidatedInteger(inputText);
                    if (value == null) continue;
                    field.set(entity, value);
                } else if (fieldType == String.class) {
                    field.set(entity, inputText);
                } else if (fieldType == LocalDateTime.class) {
                    LocalDateTime localDateTime = getValidLocalDateTime(inputText);
                    if (localDateTime == null) continue;
                    field.set(entity, localDateTime);
                } else if (fieldType == Boolean.TYPE) {
                    if (!(inputText.equals("0") || inputText.equals("1"))) {
                        System.out.println(ENTER_CORRECT_BOOLEAN);
                        continue;
                    }
                    field.set(entity, inputText.equals("0") ? Boolean.FALSE : Boolean.TRUE);
                } else if (fieldType == Event.class) {
                    Integer id = getValidatedInteger(inputText);
                    if (id == null) continue;
                    AbstractService<Event, Integer> responseService = new EventServiceImpl();
                    field.set(entity, responseService.findById(id));
                } else if (fieldType == ArtistOrGroup.class) {
                    Integer id = getValidatedInteger(inputText);
                    if (id == null) continue;
                    AbstractService<ArtistOrGroup, Integer> clusterProgramService = new ArtistOrGroupServiceImpl();
                    field.set(entity, clusterProgramService.findById(id));
                } else if (fieldType == Order.class) {
                    Integer id = getValidatedInteger(inputText);
                    if (id == null) continue;
                    AbstractService<Order, Integer> speakerService = new OrderServiceImpl();
                    field.set(entity, speakerService.findById(id));
                } else if (fieldType == Transaction.class) {
                    AbstractService<Transaction, String> studentGroupService = new TransactionServiceImpl();
                    field.set(entity, studentGroupService.findById(inputText));
                } else if (fieldType == Ticket.class) {
                    AbstractService<Ticket, String> studentService = new TicketServiceImpl();
                    field.set(entity, studentService.findById(inputText));
                } else if (fieldType == DestinationAddress.class) {
                    Integer id = getValidatedInteger(inputText);
                    if (id == null) continue;
                    AbstractService<DestinationAddress, Integer> lecturerService = new DestinationAddressServiceImpl();
                    field.set(entity, lecturerService.findById(id));
                } else if (fieldType == TypeOfDelivery.class) {
                    Integer id = getValidatedInteger(inputText);
                    if (id == null) continue;
                    AbstractService<TypeOfDelivery, Integer> subjectService = new TypeOfDeliveryServiceImpl();
                    field.set(entity, subjectService.findById(id));
                } else {
                    System.out.println(ERROR_INVALID_VALUE);
                    continue;
                }
                break;
            } catch (IllegalAccessException e) {
                System.out.println("IllegalAccessException: " + e.getMessage());
            }
        }
    }

    private void getUserHintForField(String fieldName, Class<?> fieldType) {
        String hint = null;
        if (fieldType == Integer.class || fieldType == Event.class || fieldType == ArtistOrGroup.class
                || fieldType == DestinationAddress.class || fieldType == TypeOfDelivery.class || fieldType == Order.class) {
            hint = "integer using [0-9] digits";
        } else if (fieldType == String.class || fieldType == Ticket.class || fieldType == Transaction.class) {
            hint = "pass any String";
        } else if (fieldType == LocalDateTime.class) {
            hint = "pass timestamp in format yyyy-MM-ddThh:mm:ss";
        } else if (fieldType == Boolean.TYPE) {
            hint = "enter 0 if false and 1 if true";
        }
        System.out.printf((ENTER_FIELD_FORMAT) + "%n", String.format("%s (%s)", fieldName, hint));
    }

    private boolean isNonNullFieldEmpty(Field field, String inputText) {
        if (field.isAnnotationPresent(Column.class)) {
            Column column = field.getAnnotation(Column.class);
            if (!column.nullable() && inputText.equals("")) {
                System.out.println(ENTER_REQUIRED);
                return true;
            }
        } else if (field.isAnnotationPresent(JoinColumn.class)) {
            JoinColumn joinColumn = field.getAnnotation(JoinColumn.class);
            if (!joinColumn.nullable() && inputText.equals("")) {
                System.out.println(ENTER_REQUIRED);
                return true;
            }
        }
        return false;
    }

    private LocalDateTime getValidLocalDateTime(String inputText) {
        LocalDateTime localDateTime;
        try {
            localDateTime = LocalDateTime.parse(inputText);
        } catch (DateTimeParseException e) {
            System.out.println(ENTER_CORRECT_LOCAL_DATE_TIME);
            return null;
        }
        return localDateTime;
    }

    private String getFieldName(Field field) {
        String fieldName = null;
        if (field.isAnnotationPresent(Column.class)) {
            fieldName = field.getAnnotation(Column.class).name();
        } else if (field.isAnnotationPresent(JoinColumn.class)) {
            fieldName = field.getAnnotation(JoinColumn.class).name();
        }
        return fieldName;
    }

    private Integer getValidatedInteger(String inputText) {
        int value;
        try {
            value = Integer.parseInt(inputText);
        } catch (NumberFormatException e) {
            System.out.println(ENTER_CORRECT_INTEGER);
            return null;
        }
        return value;
    }
}
