package lib;

import controller.ConsoleHandler;
import dao.SmartPhoneDao;
import dao.SmartPhoneDaoImpl;
import dao.TabletDao;
import factory.SmartPhoneDaoFactory;
import factory.TabletDaoFactory;

import java.lang.reflect.Field;

public class Injector {
    public static void injectDependency() throws IllegalAccessException {
        Class<ConsoleHandler> consoleHandlerClass = ConsoleHandler.class;
        Class<SmartPhoneDaoImpl> smartPhoneDaoImplClass = SmartPhoneDaoImpl.class;
        Field[] consoleHandlerFields = consoleHandlerClass.getDeclaredFields();
        for (Field field : consoleHandlerFields) {
            if (field.getDeclaredAnnotation(Inject.class) != null) {
                if (field.getType().equals(SmartPhoneDao.class)) {
                    if (smartPhoneDaoImplClass.getDeclaredAnnotation(Dao.class) != null) {
                        field.setAccessible(true);
                        field.set(null, SmartPhoneDaoFactory.getSmartPhoneDao());
                    }
                }
                if (field.getType().equals(TabletDao.class)) {
                    if (smartPhoneDaoImplClass.getDeclaredAnnotation(Dao.class) != null) {
                        field.setAccessible(true);
                        field.set(null, TabletDaoFactory.getTabletDao());
                    }
                }
            }
        }
    }
}
