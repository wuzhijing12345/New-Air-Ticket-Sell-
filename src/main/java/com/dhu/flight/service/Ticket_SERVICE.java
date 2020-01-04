package com.dhu.flight.service;

import java.util.Map;

public interface Ticket_SERVICE {
    Map<String,Object> searchFlight(Map<String, String> params);
    Map<String,Object> searchFlighttwo(Map<String, String> params);
    Map<String,Object> searchFlightById(Map<String, String> params);
    Map<String,Object> buyTicket(Map<String, String> params);
    Map<String,Object> searchClientById(Map<String, String> params);
    Map<String,Object> findTicketByCId(Map<String, String> params);
}
