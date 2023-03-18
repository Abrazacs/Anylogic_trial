package ru.sergeysemenov.deserializers;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.LocalTime;

public class CustomsTimeDeserializer extends StdDeserializer<LocalTime> {

    public CustomsTimeDeserializer(Class<?> vc) {
        super(vc);
    }

    public CustomsTimeDeserializer(){
        this(null);
    }

    @Override
    public LocalTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException, JacksonException {
        String[] str = jsonParser.getText().split(":");
        StringBuilder sb = new StringBuilder();
        if(str[0].length()<2){
            sb.append('0');
        }
        sb.append(str[0]).append(':').append(str[1]);
        return LocalTime.parse(sb);
    }
}
