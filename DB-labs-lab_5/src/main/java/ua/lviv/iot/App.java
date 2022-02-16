package ua.lviv.iot;

import ua.lviv.iot.hibernate.HibernateUtil;
import ua.lviv.iot.view.View;

public class App {

    public static void main(String[] args) {
        HibernateUtil.getSessionFactory(); // stub to print Hibernate logs before actual view
        new View().show();
    }
}
