package org.maxim.RestApi.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.maxim.RestApi.model.User;

import java.io.IOException;

public class GetFileNameUtil extends StdSerializer<User> {
    public GetFileNameUtil() {
        this(null);
    }

    public GetFileNameUtil(Class<User> t) {
        super(t);
    }

    @Override
    public void serialize(User user, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("user", user.getId());
        jsonGenerator.writeEndObject();
    }
}
