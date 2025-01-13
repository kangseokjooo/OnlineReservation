package com.kangsukju.reservation_system.Controller;

import com.kangsukju.reservation_system.Dto.Chat.ChatRequestDto;
import com.kangsukju.reservation_system.Dto.Chat.ChatResponseDto;
import com.kangsukju.reservation_system.Service.ChatService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/chat")
public class ChatController {
    @Autowired
    private ChatService chatService;

    @PostMapping()
    @ResponseBody
    public ChatResponseDto chat(@Valid @RequestBody ChatRequestDto chatRequestDto){
        String response=chatService.getResponse(chatRequestDto.getMessage());
        return new ChatResponseDto(response);
    }
}
