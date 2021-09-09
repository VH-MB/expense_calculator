package com.expensecalculator.event;

import com.expensecalculator.event.dto.EventDto;
import org.springframework.stereotype.Component;

@Component
class EventFacade {

    public EventDto eventToEventDto(Event event){

        return EventDto.builder()
                .id(event.getIdEvent())
                .name(event.getName())
                .startDataTime(event.getStartDataTime())
                .build();
    }
}
