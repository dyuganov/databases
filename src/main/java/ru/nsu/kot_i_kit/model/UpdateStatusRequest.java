package ru.nsu.kot_i_kit.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateStatusRequest {
    private Long id;
    private Long orderId;
    private Long filmId;
    private Long devStatusId;

    public UpdateStatusRequest(){}
}
