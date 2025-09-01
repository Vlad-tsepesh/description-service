package com.apiservice.infrastructure;

import com.apiservice.domain.ImageDescription;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.content.Media;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.util.MimeTypeUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OpenAiService {

    private final OpenAiChatModel chatModel;
    private final ObjectMapper objectMapper;
    @Value("${ai.prompts.adobe-stock.description}")
    private String prompt;

    public ImageDescription generateDescription(Resource image) throws JsonProcessingException {

        var userMessage = UserMessage.builder()
                .text(prompt)
                .media(List.of(new Media(MimeTypeUtils.IMAGE_PNG, image)))
                .build();

        var response =  chatModel.call(
                    new Prompt(
                            List.of(userMessage),
                            OpenAiChatOptions.builder()
                                    .model("gpt-4o")
                                    .build())
                            .getUserMessage());

        return objectMapper.readValue(response, ImageDescription.class);

    }
}
