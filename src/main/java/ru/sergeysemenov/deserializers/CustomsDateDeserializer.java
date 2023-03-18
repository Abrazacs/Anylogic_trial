package ru.sergeysemenov.deserializers;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.LocalDate;


public class CustomsDateDeserializer extends StdDeserializer<LocalDate> {

    public CustomsDateDeserializer(Class<?> vc) {
        super(vc);
    }

    public CustomsDateDeserializer(){
        this(null);
    }

    @Override
    public LocalDate deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException, JacksonException {
        String[] str = jsonParser.getText().split("\\.");
        StringBuilder sb = new StringBuilder();
        sb.append("20").append(str[2]).append('-');
        for (int i = 1; i > -1; i--) {
            sb.append(str[i]).append('-');
        }
        sb.deleteCharAt(sb.length()-1);
        return LocalDate.parse(sb);
    }
}
