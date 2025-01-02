package com.kangsukju.reservation_system.Dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor(staticName = "of")
public class ResultDto<D> {
    private final String msg;
    private final D data;
}
