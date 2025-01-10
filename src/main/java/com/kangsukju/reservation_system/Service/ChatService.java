package com.kangsukju.reservation_system.Service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ChatService {
    private static final Map<String,String> rm=new HashMap<>();

    static{
        rm.put("예약","날짜와 원하는 시간대를 선택하신 후 예약버튼을 눌러주세요");
        rm.put("안녕","안녕하세요");
        rm.put("종료","챗봇을 종료합니다.");
    }

    public String getResponse(String message) {
        return rm.getOrDefault(message, "죄송합니다. 이해하지 못했어요. 다시 말씀해주시겠어요?");
    }
}
