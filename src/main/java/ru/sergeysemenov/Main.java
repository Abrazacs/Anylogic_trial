package ru.sergeysemenov;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalTime;


public class Main {
    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        TicketResponse tickets = mapper.readValue(new File("tickets.json"),TicketResponse.class);
        long flightTime = getTotalFlightTime(tickets);
        tickets.getTickets().sort(Ticket::compareTo);
        calculateAvgTimeFlight(tickets, flightTime);
        findPercentile(tickets);
    }

    private static void findPercentile(TicketResponse tickets) {
        BigDecimal percentile = new BigDecimal(0.9d);
        int idx = percentile.multiply(new BigDecimal(tickets.getTickets().size()-1)).setScale(0,RoundingMode.HALF_EVEN).intValue();;
        long value = tickets.getTickets().get(idx).flightTime;
        long hours = value/3600;
        long minutes = (value-hours*3600)/60;
        System.out.println("90-ый процентиль: "+hours+"ч:"+minutes+"м");
    }

    private static void calculateAvgTimeFlight(TicketResponse tickets, long flightTime) {
        long avgTime = flightTime / tickets.getTickets().size();
        long hours = avgTime/3600;
        long minutes = (avgTime-hours*3600)/60;
        System.out.println("среднее время полета: "+hours+"ч:"+minutes+"м");
    }

    private static long getTotalFlightTime(TicketResponse tickets) {
        long flightTime = 0;
        for (Ticket ticket: tickets.getTickets()) {
            long duration;
            if(ticket.arrival_date.equals(ticket.departure_date)){
                duration= ticket.arrival_time.toSecondOfDay()-ticket.departure_time.toSecondOfDay();
            }else {
                duration = LocalTime.MIDNIGHT.toSecondOfDay() - ticket.departure_time.toSecondOfDay() +ticket.arrival_time.toSecondOfDay();
            }
            ticket.setFlightTime(duration);
            flightTime +=duration;
        }
        return flightTime;
    }
}