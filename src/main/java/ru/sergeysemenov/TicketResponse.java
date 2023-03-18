package ru.sergeysemenov;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.ArrayList;;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketResponse {
    private ArrayList<Ticket> tickets;
}
