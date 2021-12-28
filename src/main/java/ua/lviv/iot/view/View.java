package ua.lviv.iot.view;

import ua.lviv.iot.controller.*;
import ua.lviv.iot.controller.impl.*;
import ua.lviv.iot.model.*;

import java.nio.charset.StandardCharsets;
import java.util.*;

public class View {

    private static final String KEY_EXIT = "Q";
    private static final String TEXT_GO_BACK = "Go back or quit";
    private static final String TEXT_SELECT_MENU_OPTION = "Please choose menu option: ";
    private static final String ERROR_NO_SUCH_OPTION = "No such option found. Try again.";

    private static final Scanner input = new Scanner(System.in, StandardCharsets.UTF_8);

    public void show() {
        showTablesMenu();
    }

    /**
     * Show general tables MENU
     */
    private void showTablesMenu() {
        Map<String, String> tablesMenu = generateTablesMenu();
        Map<String, Printable> tablesMenuMethods = generateTablesMenuMethods();
        showMenuFromMaps(tablesMenu, tablesMenuMethods);
    }

    /**
     * Generate general tables MENU and MENU METHODS
     */
    private Map<String, String> generateTablesMenu() {
        Map<String, String> tablesMenu = new LinkedHashMap<>();
        tablesMenu.put("1", "Table: Response");
        tablesMenu.put("2", "Table: Speaker");
        tablesMenu.put("3", "Table: Subject");
        tablesMenu.put("4", "Table: Student");
        tablesMenu.put("5", "Table: Student Group");
        tablesMenu.put("6", "Table: Lecturer");
        tablesMenu.put("7", "Table: Cluster Program");
        return tablesMenu;
    }

    private Map<String, Printable> generateTablesMenuMethods() {
        Map<String, Printable> tablesMenuMethods = new LinkedHashMap<>();
        tablesMenuMethods.put("1", this::showResponseMenu);
        tablesMenuMethods.put("2", this::showSpeakerMenu);
        tablesMenuMethods.put("3", this::showSubjectMenu);
        tablesMenuMethods.put("4", this::showStudentMenu);
        tablesMenuMethods.put("5", this::showStudentGroupMenu);
        tablesMenuMethods.put("6", this::showLecturerMenu);
        tablesMenuMethods.put("7", this::showClusterProgramMenu);
        return tablesMenuMethods;
    }

    /**
     * Show single table MENU
     */
    private void showResponseMenu() {
        Map<String, String> menu = generateMenu();
        Map<String, Printable> menuMethods = generateResponseMenuMethods();
        showMenuFromMaps(menu, menuMethods);
    }

    private void showSpeakerMenu() {
        Map<String, String> menu = generateMenu();
        Map<String, Printable> menuMethods = generateSpeakerMenuMethods();
        showMenuFromMaps(menu, menuMethods);
    }

    private void showSubjectMenu() {
        Map<String, String> menu = generateMenu();
        Map<String, Printable> menuMethods = generateSubjectMenuMethods();
        showMenuFromMaps(menu, menuMethods);
    }

    private void showStudentMenu() {
        Map<String, String> menu = generateMenu();
        Map<String, Printable> menuMethods = generateStudentMenuMethods();
        showMenuFromMaps(menu, menuMethods);
    }

    private void showStudentGroupMenu() {
        Map<String, String> menu = generateMenu();
        Map<String, Printable> menuMethods = generateStudentGroupMenuMethods();
        showMenuFromMaps(menu, menuMethods);
    }

    private void showLecturerMenu() {
        Map<String, String> menu = generateMenu();
        Map<String, Printable> menuMethods = generateLecturerMenuMethods();
        showMenuFromMaps(menu, menuMethods);
    }

    private void showClusterProgramMenu() {
        Map<String, String> menu = generateMenu();
        Map<String, Printable> menuMethods = generateClusterProgramMenuMethods();
        showMenuFromMaps(menu, menuMethods);
    }

    /**
     * Generate single table MENU
     */
    private Map<String, String> generateMenu() {
        Map<String, String> menu = new LinkedHashMap<>();
        menu.put("1", "Select all");
        menu.put("2", "Select");
        menu.put("3", "Create");
        menu.put("4", "Update");
        menu.put("5", "Delete");
        return menu;
    }

    /**
     * Generate single table MENU METHODS
     */
    private Map<String, Printable> generateResponseMenuMethods() {
        AbstractController<Response, Integer> responseController = new ResponseControllerImpl();
        ViewOperations<Response, Integer> responseOperation = new ViewOperations<>(responseController, Response.class);

        Map<String, Printable> menuMethods = new LinkedHashMap<>();
        menuMethods.put("1", responseOperation::findAll);
        menuMethods.put("2", responseOperation::findById);
        menuMethods.put("3", responseOperation::create);
        menuMethods.put("4", responseOperation::update);
        menuMethods.put("5", responseOperation::delete);
        return menuMethods;
    }



    private Map<String, Printable> generateSpeakerMenuMethods() {
        AbstractController<Speaker, Integer> speakerController = new SpeakerControllerImpl();
        ViewOperations<Speaker, Integer> speakerOperation = new ViewOperations<>(speakerController, Speaker.class);

        Map<String, Printable> menuMethods = new LinkedHashMap<>();
        menuMethods.put("1", speakerOperation::findAll);
        menuMethods.put("2", speakerOperation::findById);
        menuMethods.put("3", speakerOperation::create);
        menuMethods.put("4", speakerOperation::update);
        menuMethods.put("5", speakerOperation::delete);
        return menuMethods;
    }

    private Map<String, Printable> generateSubjectMenuMethods() {
        AbstractController<Subject, Integer> subjectController = new SubjectControllerImpl();
        ViewOperations<Subject, Integer> subjectOperation = new ViewOperations<>(subjectController, Subject.class);

        Map<String, Printable> menuMethods = new LinkedHashMap<>();
        menuMethods.put("1", subjectOperation::findAll);
        menuMethods.put("2", subjectOperation::findById);
        menuMethods.put("3", subjectOperation::create);
        menuMethods.put("4", subjectOperation::update);
        menuMethods.put("5", subjectOperation::delete);
        return menuMethods;
    }

    private Map<String, Printable> generateStudentMenuMethods() {
        AbstractController<Student, String> studentController = new StudentControllerImpl();
        ViewOperations<Student, String> studentOperation = new ViewOperations<>(studentController, Student.class);

        Map<String, Printable> menuMethods = new LinkedHashMap<>();
        menuMethods.put("1", studentOperation::findAll);
        menuMethods.put("2", studentOperation::findById);
        menuMethods.put("3", studentOperation::create);
        menuMethods.put("4", studentOperation::update);
        menuMethods.put("5", studentOperation::delete);
        return menuMethods;
    }

    private Map<String, Printable> generateStudentGroupMenuMethods() {
        AbstractController<StudentGroup, String> studentGroupController = new StudentGroupControllerImpl();
        ViewOperations<StudentGroup, String> studentGroupOperation = new ViewOperations<>(studentGroupController, StudentGroup.class);

        Map<String, Printable> menuMethods = new LinkedHashMap<>();
        menuMethods.put("1", studentGroupOperation::findAll);
        menuMethods.put("2", studentGroupOperation::findById);
        menuMethods.put("3", studentGroupOperation::create);
        menuMethods.put("4", studentGroupOperation::update);
        menuMethods.put("5", studentGroupOperation::delete);
        return menuMethods;
    }

    private Map<String, Printable> generateLecturerMenuMethods() {
        AbstractController<Lecturer, Integer> lecturerController = new LecturerControllerImpl();
        ViewOperations<Lecturer, Integer> lecturerOperation = new ViewOperations<>(lecturerController, Lecturer.class);

        Map<String, Printable> menuMethods = new LinkedHashMap<>();
        menuMethods.put("1", lecturerOperation::findAll);
        menuMethods.put("2", lecturerOperation::findById);
        menuMethods.put("3", lecturerOperation::create);
        menuMethods.put("4", lecturerOperation::update);
        menuMethods.put("5", lecturerOperation::delete);
        return menuMethods;
    }

    private Map<String, Printable> generateClusterProgramMenuMethods() {
        AbstractController<ClusterProgram, Integer> clusterProgramController = new ClusterProgramControllerImpl();
        ViewOperations<ClusterProgram, Integer> clusterProgramOperation = new ViewOperations<>(clusterProgramController, ClusterProgram.class);

        Map<String, Printable> menuMethods = new LinkedHashMap<>();
        menuMethods.put("1", clusterProgramOperation::findAll);
        menuMethods.put("2", clusterProgramOperation::findById);
        menuMethods.put("3", clusterProgramOperation::create);
        menuMethods.put("4", clusterProgramOperation::update);
        menuMethods.put("5", clusterProgramOperation::delete);
        return menuMethods;
    }

    /**
     * Show menu from Maps
     */
    private void showMenuFromMaps(Map<String, String> keyName, Map<String, Printable> keyMethod) {
        String keyMenu;
        do {
            printMenu(keyName);
            System.out.println(TEXT_SELECT_MENU_OPTION);
            keyMenu = input.nextLine().toUpperCase();
            Printable method = keyMethod.get(keyMenu);
            if (method != null) {
                method.print();
            } else if (!keyMenu.equals(KEY_EXIT)) {
                System.out.println(ERROR_NO_SUCH_OPTION);
            }
        } while (!keyMenu.equals(KEY_EXIT));
    }

    private void printMenu(Map<String, String> keyName) {
        keyName.forEach((key, value) -> System.out.printf("%3s - %s%n", key, value));
        System.out.printf("%3s - %s%n", KEY_EXIT, TEXT_GO_BACK);
    }
}
