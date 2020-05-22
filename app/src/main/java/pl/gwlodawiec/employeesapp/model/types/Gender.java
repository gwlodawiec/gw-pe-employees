package pl.gwlodawiec.employeesapp.model.types;

public enum Gender {
    MALE(1),
    FEMALE(2)
    ;

    private long key;

    private Gender(long key){
        this.key = key;
    }

    public long getKey() {
        return key;
    }

    public static Gender getByKey(long key){
        for (Gender elem: values()) {
            if(elem.key == key){
                return elem;
            }
        }
        throw new IllegalArgumentException("No Gender value for key: " + key);
    }
}
