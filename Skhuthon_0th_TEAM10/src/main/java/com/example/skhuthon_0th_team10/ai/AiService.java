package com.example.skhuthon_0th_team10.ai;

import com.example.skhuthon_0th_team10.ai.dto.AiRequestDto;
import com.example.skhuthon_0th_team10.ai.dto.AiResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AiService {

    private final ChatClient chatClient;

    // AI에게 조언 구하기
    @Transactional
    public AiResponseDto askForAdvice(){
        ChatResponse response = callChat();
        if (response == null) {
            response = callChat();
        }

        return AiResponseDto.builder()
                .answer(response.getResult().getOutput().getContent()).build();

    }

    // AI 응답 메서드
    private ChatResponse callChat() {
        return chatClient.call(
                new Prompt(
                        ("요즘 가장 핫플인 해외 도시 하나 추천해줘. 좀 인디한 곳으로. 50글자 이하로 "
                               ),
                        OpenAiChatOptions.builder()
                                .withTemperature(0.4F)
                                .withFrequencyPenalty(0.7F)
                                .withModel("gpt-3.5-turbo")
                                .build()
                ));
    }
}
