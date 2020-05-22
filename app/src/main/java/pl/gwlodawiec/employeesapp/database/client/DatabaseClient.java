package pl.gwlodawiec.employeesapp.database.client;

import android.content.Context;

import androidx.room.Room;
import pl.gwlodawiec.employeesapp.database.MainDatabase;

/**
 * Room in-memory database client
 */
public class DatabaseClient {

    private Context context;
    private static DatabaseClient instance;
    private MainDatabase database;

    private DatabaseClient(Context context){
        this.database = Room.inMemoryDatabaseBuilder(context, MainDatabase.class).build();
    }

    public static synchronized DatabaseClient getInstance(Context context){
        if(instance == null) {
            instance = new DatabaseClient(context);
        }
        return instance;
    }

    public MainDatabase getDatabase() {
        return database;
    }
}
