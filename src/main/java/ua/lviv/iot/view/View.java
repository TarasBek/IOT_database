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
        tablesMenu.put("1", "Table: User Info");
        tablesMenu.put("2", "Table: Order");
        tablesMenu.put("3", "Table: Products");
        tablesMenu.put("4", "Table: Social id");
        tablesMenu.put("5", "Table: User");
        tablesMenu.put("6", "Table: Transaction");
        tablesMenu.put("7", "Table: Destination address");
        tablesMenu.put("8", "Table: General user info");
        return tablesMenu;
    }

    private Map<String, Printable> generateTablesMenuMethods() {
        Map<String, Printable> tablesMenuMethods = new LinkedHashMap<>();
        tablesMenuMethods.put("1", this::showEventMenu);
        tablesMenuMethods.put("2", this::showOrderMenu);
        tablesMenuMethods.put("3", this::showTypeOfDeliveryMenu);
        tablesMenuMethods.put("4", this::showSocialMenu);
        tablesMenuMethods.put("5", this::showUserMainMenu);
        tablesMenuMethods.put("6", this::showTransactionMenu);
        tablesMenuMethods.put("7", this::showDestinationAddressMenu);
        tablesMenuMethods.put("8", this::showGeneralInfoMenu);
        return tablesMenuMethods;
    }

    /**
     * Show single table MENU
     */
    private void showEventMenu() {
        Map<String, String> menu = generateMenu();
        Map<String, Printable> menuMethods = generateUserMenuMethods();
        showMenuFromMaps(menu, menuMethods);
    }

    private void showOrderMenu() {
        Map<String, String> menu = generateMenu();
        Map<String, Printable> menuMethods = generateOrderMenuMethods();
        showMenuFromMaps(menu, menuMethods);
    }

    private void showTypeOfDeliveryMenu() {
        Map<String, String> menu = generateMenu();
        Map<String, Printable> menuMethods = generateProductsMenuMethods();
        showMenuFromMaps(menu, menuMethods);
    }

    private void showSocialMenu() {
        Map<String, String> menu = generateMenu();
        Map<String, Printable> menuMethods = generateSocialMenuMethods();
        showMenuFromMaps(menu, menuMethods);
    }

    private void showUserMainMenu() {
        Map<String, String> menu = generateMenu();
        Map<String, Printable> menuMethods = generateUserMainMenuMethods();
        showMenuFromMaps(menu, menuMethods);
    }

    private void showTransactionMenu() {
        Map<String, String> menu = generateMenu();
        Map<String, Printable> menuMethods = generateTransactionMenuMethods();
        showMenuFromMaps(menu, menuMethods);
    }

    private void showDestinationAddressMenu() {
        Map<String, String> menu = generateMenu();
        Map<String, Printable> menuMethods = generateDestinationAddressMenuMethods();
        showMenuFromMaps(menu, menuMethods);
    }

    private void showGeneralInfoMenu() {
        Map<String, String> menu = generateMenu();
        Map<String, Printable> menuMethods = generateGeneralInfoMenuMethods();
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
    private Map<String, Printable> generateUserMenuMethods() {
        AbstractController<UserInf, Integer> eventController = new EventControllerImpl();
        Formatter<UserInf, Integer> formatter = new Formatter<>(UserInf.class);
        ViewOperations<UserInf, Integer> eventOperation = new ViewOperations<>(eventController, formatter, UserInf.class);
        
        Map<String, Printable> menuMethods = new LinkedHashMap<>();
        menuMethods.put("1", eventOperation::findAll);
        menuMethods.put("2", eventOperation::findById);
        menuMethods.put("3", eventOperation::create);
        menuMethods.put("4", eventOperation::update);
        menuMethods.put("5", eventOperation::delete);
        return menuMethods;
    }



    private Map<String, Printable> generateOrderMenuMethods() {
        AbstractController<Order, Integer> orderController = new OrderControllerImpl();
        Formatter<Order, Integer> formatter = new Formatter<>(Order.class);
        ViewOperations<Order, Integer> orderOperation = new ViewOperations<>(orderController, formatter, Order.class);

        Map<String, Printable> menuMethods = new LinkedHashMap<>();
        menuMethods.put("1", orderOperation::findAll);
        menuMethods.put("2", orderOperation::findById);
        menuMethods.put("3", orderOperation::create);
        menuMethods.put("4", orderOperation::update);
        menuMethods.put("5", orderOperation::delete);
        return menuMethods;
    }
    
    private Map<String, Printable> generateProductsMenuMethods() {
        AbstractController<Products, Integer> typeOfDeliveryController = new TypeOfDeliveryControllerImpl();
        Formatter<Products, Integer> formatter = new Formatter<>(Products.class);
        ViewOperations<Products, Integer> typeOfDeliveryOperation = new ViewOperations<>(typeOfDeliveryController, formatter, Products.class);

        Map<String, Printable> menuMethods = new LinkedHashMap<>();
        menuMethods.put("1", typeOfDeliveryOperation::findAll);
        menuMethods.put("2", typeOfDeliveryOperation::findById);
        menuMethods.put("3", typeOfDeliveryOperation::create);
        menuMethods.put("4", typeOfDeliveryOperation::update);
        menuMethods.put("5", typeOfDeliveryOperation::delete);
        return menuMethods;	
    }
    
    private Map<String, Printable> generateSocialMenuMethods() {
        AbstractController<Social, Integer> typeOfEventController = new TypeOfEventControllerImpl();
        Formatter<Social, Integer> formatter = new Formatter<>(Social.class);
        ViewOperations<Social, Integer> typeOfEventOperation = new ViewOperations<>(typeOfEventController, formatter, Social.class);

        Map<String, Printable> menuMethods = new LinkedHashMap<>();
        menuMethods.put("1", typeOfEventOperation::findAll);
        menuMethods.put("2", typeOfEventOperation::findById);
        menuMethods.put("3", typeOfEventOperation::create);
        menuMethods.put("4", typeOfEventOperation::update);
        menuMethods.put("5", typeOfEventOperation::delete);
        return menuMethods;
    }
    
    private Map<String, Printable> generateUserMainMenuMethods() {
        AbstractController<User, String> ticketController = new TicketControllerImpl();
        Formatter<User, String> formatter = new Formatter<>(User.class);
        ViewOperations<User, String> ticketOperation = new ViewOperations<>(ticketController, formatter, User.class);

        Map<String, Printable> menuMethods = new LinkedHashMap<>();
        menuMethods.put("1", ticketOperation::findAll);
        menuMethods.put("2", ticketOperation::findById);
        menuMethods.put("3", ticketOperation::create);
        menuMethods.put("4", ticketOperation::update);
        menuMethods.put("5", ticketOperation::delete);
        return menuMethods;
    }

    private Map<String, Printable> generateTransactionMenuMethods() {
        AbstractController<Transaction, String> transactionController = new TransactionControllerImpl();
        Formatter<Transaction, String> formatter = new Formatter<>(Transaction.class);
        ViewOperations<Transaction, String> transactionVehicleOperation = new ViewOperations<>(transactionController, formatter, Transaction.class);

        Map<String, Printable> menuMethods = new LinkedHashMap<>();
        menuMethods.put("1", transactionVehicleOperation::findAll);
        menuMethods.put("2", transactionVehicleOperation::findById);
        menuMethods.put("3", transactionVehicleOperation::create);
        menuMethods.put("4", transactionVehicleOperation::update);
        menuMethods.put("5", transactionVehicleOperation::delete);
        return menuMethods;
    }

    private Map<String, Printable> generateDestinationAddressMenuMethods() {
        AbstractController<DestinationAddress, Integer> destinationAddressController = new DestinationAddressControllerImpl();
        Formatter<DestinationAddress, Integer> formatter = new Formatter<>(DestinationAddress.class);
        ViewOperations<DestinationAddress, Integer> destinationAddressOperation = new ViewOperations<>(destinationAddressController, formatter, DestinationAddress.class);

        Map<String, Printable> menuMethods = new LinkedHashMap<>();
        menuMethods.put("1", destinationAddressOperation::findAll);
        menuMethods.put("2", destinationAddressOperation::findById);
        menuMethods.put("3", destinationAddressOperation::create);
        menuMethods.put("4", destinationAddressOperation::update);
        menuMethods.put("5", destinationAddressOperation::delete);
        return menuMethods;
    }
    
    private Map<String, Printable> generateGeneralInfoMenuMethods() {
        AbstractController<GeneralInfo, Integer> artistOrGroupController = new ArtistOrGroupControllerImpl();
        Formatter<GeneralInfo, Integer> formatter = new Formatter<>(GeneralInfo.class);
        ViewOperations<GeneralInfo, Integer> artistOrGroupOperation = new ViewOperations<>(artistOrGroupController, formatter, GeneralInfo.class);

        Map<String, Printable> menuMethods = new LinkedHashMap<>();
        menuMethods.put("1", artistOrGroupOperation::findAll);
        menuMethods.put("2", artistOrGroupOperation::findById);
        menuMethods.put("3", artistOrGroupOperation::create);
        menuMethods.put("4", artistOrGroupOperation::update);
        menuMethods.put("5", artistOrGroupOperation::delete);
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
        keyName.entrySet().stream()
                .forEach(es -> System.out.printf("%3s - %s%n", es.getKey(), es.getValue()));
        System.out.printf("%3s - %s%n", KEY_EXIT, TEXT_GO_BACK);
    }
}
