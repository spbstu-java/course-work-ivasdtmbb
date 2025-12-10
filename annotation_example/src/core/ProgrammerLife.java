package annotation_example.core;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import annotations.WeeklySchedule;
import entities.CrazyProgrammer;


public class ProgrammerLife {
    static void main(String[] args) {
        surviveOneWeek(new CrazyProgrammer());
    }


    public static void surviveOneWeek(CrazyProgrammer programmer) {
        System.out.println("BEGINNING OF THE RELEASE WEEK...\n");

        Method[] methods = programmer.getClass().getDeclaredMethods();

        for (Method method : methods) {
            if (isMethodSuitable(method)) {
                System.out.println("1. Give access to: " + method.getName());
                method.setAccessible(true);

                processMethod(programmer, method);
            }
        }

        if (programmer.getMentalHealth() > 0) {
            System.out.println("END OF WEEK! IT WAS A GREAT WEEK! :-P ");
        } else {
            System.out.println("END OF WEEK! YOU SHOULD CARE BETTER ABOUT YOURSELF, OTHERWISE YOU GONNA BURN OUT! :-(");
        }
    }

    private static boolean isMethodSuitable(Method method) {
        boolean hasAnnotation = method.isAnnotationPresent(WeeklySchedule.class);
        boolean isPublic = Modifier.isPublic(method.getModifiers());

        return hasAnnotation && !isPublic;
    }

    private static void processMethod(Object programmer, Method method) {
        System.out.println("2. Got a method: " + method.getName());

        Object[] parameters = createParametersForMethod(method);

        invokeMethodMultipleTimes(programmer, method, parameters);

        System.out.println("--------------------------------------------\n");
    }


    private static Object[] createParametersForMethod(Method method) {
        Class<?>[] paramTypes = method.getParameterTypes();

        if (paramTypes.length == 0) {
            System.out.println("3. No any parameters");
            return new Object[0];
        }

        System.out.println("3. Build parameters: " + paramTypes.length + " pcs.");

        Object[] parameters = new Object[paramTypes.length];

        for (int i = 0; i < paramTypes.length; i++) {
            if (paramTypes[i] == String.class) {
                parameters[i] = "flying red button exploding with press user action";
            } else if (paramTypes[i] == int.class) {
                parameters[i] = 12;
            } else {
                parameters[i] = null;
                System.out.println("null - unknown parameter's type");
            }
        }

        return parameters;
    }

    private static void invokeMethodMultipleTimes(Object programmer, Method method, Object[] parameters) {
        WeeklySchedule annotation = method.getAnnotation(WeeklySchedule.class);
        int timesToDo = annotation.value();

        System.out.println("4. Call " + timesToDo + " times...\n");

        for (int i = 0; i < timesToDo; i++) {
            try {
                method.invoke(programmer, parameters);
            } catch (Exception e) {
                System.out.println("Error while method invocation: " + e.getMessage());
            }
        }
    }
}
