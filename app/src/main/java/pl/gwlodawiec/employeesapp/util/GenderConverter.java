package pl.gwlodawiec.employeesapp.util;

import androidx.room.TypeConverter;
import pl.gwlodawiec.employeesapp.model.types.Gender;

public class GenderConverter {

    @TypeConverter
    public static Gender getByKey(long key){
        return Gender.getByKey(key);
    }

    @TypeConverter
    public static long fromGender(Gender gender){
        return gender.getKey();
    }
}
