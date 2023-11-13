package mtc.ncr.process.converters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import mtc.ncr.process.dto.GojeongDto;
import org.apache.commons.lang3.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

public class MessageDeserializer implements Deserializer<GojeongDto> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public GojeongDto deserialize(String s, byte[] bytes) {
        try {
            return objectMapper.readValue(new String(bytes), GojeongDto.class);
        } catch (JsonProcessingException e) {
            System.out.println(e.getOriginalMessage());
            throw new SerializationException(e);
        }
    }
}
