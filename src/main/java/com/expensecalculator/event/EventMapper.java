package com.expensecalculator.event;


import com.expensecalculator.event.dto.EventDto;
import com.expensecalculator.payment.Payment;
import com.expensecalculator.payment.dto.PaymentDto;
import com.expensecalculator.user.User;
import com.expensecalculator.user.dto.UserDto;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
abstract class EventMapper {

    public UserDto userToUserDto(User user) {
        return UserDto.builder()
                .id(user.getIdUser())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .event(eventToEventDto(user.getEvent()))
                .build();
    }

    public User userDtoToUser(UserDto userDto) {
        User user = new User();
        user.setIdUser(userDto.getId());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPayments(paymentsDtoToPayments(userDto.getPayments()));
        return user;
    }

    public EventDto eventToEventDto(Event event) {
        return EventDto.builder()
                .id(event.getIdEvent())
                .name(event.getName())
                .startDataTime(event.getStartDataTime())
                .users(usersToUsersDto(event.getUsers()))
                .build();
    }

    public Event eventDtoToEvent(EventDto eventDto) {
        Event event = new Event();
        event.setIdEvent(eventDto.getId());
        event.setName(eventDto.getName());
        event.setUsers(usersDtoToUsers(eventDto.getUsers()));
        return event;
    }

    public abstract List<EventDto> eventsToEventsDto(List<Event> eventsDto);

    public abstract List<Event> eventsDtoToEvents(List<EventDto> eventsDto);

    public abstract Set<UserDto> usersToUsersDto(Set<User> usersDto);

    public abstract Set<User> usersDtoToUsers(Set<UserDto> usersDto);

    public abstract List<Payment> paymentsDtoToPayments(List<PaymentDto> paymentsDto);

    public abstract List<PaymentDto> paymentsToPaymentsDto(List<Payment> paymentsDto);
}
