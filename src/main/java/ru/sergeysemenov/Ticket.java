package ru.sergeysemenov;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import ru.sergeysemenov.deserializers.CustomsDateDeserializer;
import ru.sergeysemenov.deserializers.CustomsTimeDeserializer;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ticket implements Comparable<Ticket>{
    public String origin;
    public String origin_name;
    public String destination;
    public String destination_name;
    @JsonDeserialize(using = CustomsDateDeserializer.class)
    public LocalDate departure_date;
    @JsonDeserialize(using = CustomsTimeDeserializer.class)
    public LocalTime departure_time;
    @JsonDeserialize(using = CustomsDateDeserializer.class)
    public LocalDate arrival_date;
    @JsonDeserialize(using = CustomsTimeDeserializer.class)
    public LocalTime arrival_time;
    public String carrier;
    public int stops;
    public int price;
    @JsonIgnoreProperties
    public long flightTime;

    @Override
    public int compareTo(Ticket o) {
        return Long.compare(o.flightTime,flightTime);
    }
}
