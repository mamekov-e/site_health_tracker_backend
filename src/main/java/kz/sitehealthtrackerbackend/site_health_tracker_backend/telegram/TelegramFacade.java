package kz.sitehealthtrackerbackend.site_health_tracker_backend.telegram;

import kz.sitehealthtrackerbackend.site_health_tracker_backend.service.TelegramUserService;
import kz.sitehealthtrackerbackend.site_health_tracker_backend.telegram.handler.MessageHandler;
import kz.sitehealthtrackerbackend.site_health_tracker_backend.telegram.state.ChatMemberStatus;
import kz.sitehealthtrackerbackend.site_health_tracker_backend.telegram.state.MenuOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.ChatMemberUpdated;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.chatmember.ChatMember;

@Component
public class TelegramFacade {

    @Autowired
    private MessageHandler messageHandler;
    @Autowired
    private TelegramUserService telegramUserService;

    public BotApiMethod<?> handleUpdate(Update update) {
        ChatMemberUpdated myChatMember = update.getMyChatMember();

        if (myChatMember != null) {
            ChatMember newChatMember = myChatMember.getNewChatMember();
            String status = newChatMember.getStatus();
            if (ChatMemberStatus.KICKED.name().equalsIgnoreCase(status)) {
                long fromUserId = myChatMember.getFrom().getId();
                telegramUserService.deleteById(fromUserId);
            }
        } else {
            Message message = update.getMessage();
            if (message != null && message.hasText()) {
                return handleUserMessage(message);
            }
        }
        return null;
    }

    private BotApiMethod<?> handleUserMessage(Message message) {

        String text = message.getText().toLowerCase();
        MenuOptions menuOption;

        switch (text) {
            case "/start" -> menuOption = MenuOptions.START;
            case "подписаться", "/subscribe", "1" -> menuOption = MenuOptions.SUBSCRIBE;
            case "отписаться", "/unsubscribe", "2" -> menuOption = MenuOptions.UNSUBSCRIBE;
            default -> menuOption = MenuOptions.HELP;
        }

        return messageHandler.handle(message, menuOption);
    }
}
