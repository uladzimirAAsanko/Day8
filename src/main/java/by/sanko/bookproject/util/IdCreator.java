package by.sanko.bookproject.util;

public class IdCreator {
    private static final long MIN_ID = 0;
    private static final long MAX_ID = 100000;

    private static long id;

    public IdCreator(){
    }

    public static long generateId(){
        id++;

        if (id > MAX_ID) {
            id = MIN_ID;
        }

        return id;
    }
}
