package com.cetin.flightsearchapi.mapper;

import com.cetin.flightsearchapi.entity.Flight;
import com.cetin.flightsearchapi.model.SearchResponse;
import com.cetin.flightsearchapi.model.response.DepartureFlightResponse;
import com.cetin.flightsearchapi.model.response.ReturnFlightResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SearchMapper {

    // Flight nesnesini Kalkış için oluşturulan DepartureFlightResponse classını dönüştürmek
    private DepartureFlightResponse mapToDepartureResponse(Flight flight) {
        return DepartureFlightResponse.builder()
                .departureAirport(flight.getDepartureAirport().getCity())
                .arrivalAirport(flight.getArrivalAirport().getCity())
                .departureDate(flight.getDepartureDate())
                .build();
    }

    // Flight nesnesini Kalkış için oluşturulan ReturnFlightResponse nesnesine dönüştürmek
    private ReturnFlightResponse mapToReturnResponse(Flight flight) {
        return ReturnFlightResponse.builder()
                .departureAirport(flight.getArrivalAirport().getCity())
                .arrivalAirport(flight.getDepartureAirport().getCity())
                .returnDate(flight.getReturnDate())
                .build();
    }

    // Flight nesnesini SearchResponse nesnesine dönüştürmek (çift yönlü)
    public SearchResponse mapToSearchResponse(Flight flight) {
        return SearchResponse.builder()
                .id(flight.getId())
                .departureFlight(mapToDepartureResponse(flight))
                .returnFlight(mapToReturnResponse(flight))
                .price(flight.getPrice())
                .build();
    }

    // Flight nesnesini SearchResponse nesnesine dönüştürmek (tek yönlü)
    public SearchResponse mapToSearchResponseOneWay(Flight flight) {
        return SearchResponse.builder()
                .id(flight.getId())
                .departureFlight(mapToDepartureResponse(flight))
                .price(flight.getPrice())
                .build();
    }

    // Flight listesini SearchResponse listesine dönüştürmek
    public List<SearchResponse> mapToSearchResponseList(List<Flight> flights) {
        return flights.stream()
                .map(this::mapToSearchResponse)
                .collect(Collectors.toList());
    }

    // Flight listesini SearchResponse listesine dönüştürmek
    public List<SearchResponse> mapToSearchResponseListOneWay(List<Flight> flights) {
        return flights.stream()
                .map(this::mapToSearchResponseOneWay)
                .collect(Collectors.toList());
    }
}
