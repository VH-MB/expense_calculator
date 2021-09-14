package com.expensecalculator.modules.event;

import com.expensecalculator.modules.event.dto.EventDto;
import org.springframework.stereotype.Component;

@Component
public class EventFacade {

    public EventDto eventToEventDto(Event event){

        return EventDto.builder()
                .id(event.getIdEvent())
                .name(event.getName())
                .startDataTime(event.getStartDataTime())
                .build();
    }
}
