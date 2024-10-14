package ru.mirea.ChepelIV.pkmn;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;

public class CustomObjectInputStream extends ObjectInputStream {
    public CustomObjectInputStream(InputStream in) throws IOException {
        super(in);
    }

    @Override
    protected Class<?> resolveClass(ObjectStreamClass desc) throws IOException, ClassNotFoundException {
        String originalName = desc.getName();
        if (originalName.startsWith("ru.mirea.")){
            String[] parts = originalName.split("\\.");
            if(parts.length >= 3){
                parts[2] = "ChepelIV";
            }
            return Class.forName(String.join(".", parts));
        }
        return super.resolveClass(desc);
    }
}
